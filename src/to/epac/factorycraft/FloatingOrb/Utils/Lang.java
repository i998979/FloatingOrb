package to.epac.factorycraft.FloatingOrb.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import to.epac.factorycraft.FloatingOrb.Main;

public enum Lang {
    PREFIX("prefix", "&e[&3Floating&bOrb&e]&r "),

    RELOAD("reload", "&e{0} &areloaded."),
    INVALID_RELOAD("invalid_reload", "&cInvalid reload parameters. Only (all|config|datas|effects|hovers|orbs|particles|rotates|lang)"),
    INVALID_LIST("invalid_list", "&cInvalid list parameters. Only (datas|effects|hovers|orbs|particles|rotates)"),

    NO_PERMISSION("no_permission", "&cYou don't have permission to perform this command."),
    NON_PLAYER("non_player", "&cYou must be a player to execute this command."),

    NO_TYPE("no_type", "&cPlease enter a FloatingOrb type."),
    NO_RANGE("no_range", "&cPlease enter a range."),
    NO_BOOLEAN("no_boolean", "&cPlease enter &atrue&c/&cfalse&c."),
    NOT_EXIST("not_exist", "&cThe FloatingOrb you have entered does not exist."),
    INVALID_RANGE("invalid_range", "&cInvalid range. Valid range is {0} - {1}."),
    INVALID_TYPE("invalid_type", "Invalid FloatingOrb type."),

    SPAWN("spawn", "&eSpawned \"&a{0}&e\" at the location you are at."),
    RESTART("restart", "&aRestarted &e{0} &aFloatingOrb."),
    REMOVE_DEAD("remove_dead", "&aRemoved &e{0} &adead FloatingOrb from database."),
    REMOVE_BY_TYPE("remove_by_type", "&aRemoved &e{0} &aFloatingOrb in type \"&e{1}&a\"."),
    REMOVE_NEARBY("remove_nearby", "&aRemoved &e{0} &aFloatingOrb in range \"&e{0}&a\"."),
    REMOVE_IN_SIGHT("remove_in_sight", "Removed \"&e{0}&a\" at &e{1}, {2}, {3}&a."),

    NO_DAMAGE_SOUND("no_damage_sound", "&eNo damage sound on everything is now {0}&e."),

    ENABLED("enabled", "&aenabled"),
    DISABLED("disabled", "&cdisabled"), ;

    private String path, def;
    private static FileConfiguration LANG;

    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    public static void loadLang() {
        String lang = Main.configManager.getLang();
        File file = new File(Main.getInstance().getDataFolder(), "locales/" + lang + ".yml");
        File dir = new File(Main.getInstance().getDataFolder(), "locales");
        if (!file.exists()) {
            Bukkit.getLogger().warning("locales/" + lang + ".yml not found. Copying default value.");

            dir.mkdirs();
            InputStream in = Main.getInstance().getResource("locales/en_US.yml");
            try {
                OutputStream out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close(); in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setFile(YamlConfiguration.loadConfiguration(file));
    }
    public static void setFile(FileConfiguration config) {
        LANG = config;
    }
    public String getDefault() {
        return this.def;
    }
    public String getPath() {
        return this.path;
    }
    public String format(Object...args) {
        String value = ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, this.def));

        if (args == null) return value;
        else {
            if (args.length == 0) return value;

            for (int i = 0; i < args.length; i++) {
                String arg = ChatColor.translateAlternateColorCodes('&', args[i].toString());
                value = value.replace("{" + i + "}", arg);
            }
        }
        return value;
    }
    /**
     * Send message
     */
    public void send(CommandSender sender, Object...args) {
        sender.sendMessage(format(args));
    }
    /**
     * Send message with prefix
     */
    public void sendWP(CommandSender sender, Object...args) {
        sender.sendMessage(Lang.PREFIX.format() + format(args));
    }
}