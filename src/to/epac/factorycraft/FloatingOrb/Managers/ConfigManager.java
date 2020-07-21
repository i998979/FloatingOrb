package to.epac.factorycraft.FloatingOrb.Managers;

import org.bukkit.plugin.Plugin;

public class ConfigManager extends AbstractManager {

    public ConfigManager(Plugin plugin) {
        super(plugin, "config.yml");
    }

    public String getLang() {
        return getFileConf().getString("FloatingOrb.Language");
    }
    public void setLang(String lang) {
    	getFileConf().set("FloatingOrb.Language", lang);
    	
    	saveFile();
    }

    public boolean getCancelSound() {
        return getFileConf().getBoolean("FloatingOrb.Cancel_NoDamage_Sound");
    }
    public void setCancelSound(boolean cancel) {
    	getFileConf().set("FloatingOrb.Cancel_NoDamage_Sound", cancel);
    	
    	saveFile();
    }
}