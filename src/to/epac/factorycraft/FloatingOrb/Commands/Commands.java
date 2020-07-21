package to.epac.factorycraft.FloatingOrb.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import to.epac.factorycraft.FloatingOrb.FloatingOrb;
import to.epac.factorycraft.FloatingOrb.Main;
import to.epac.factorycraft.FloatingOrb.Utils.Lang;
import to.epac.factorycraft.FloatingOrb.Utils.Utils;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("FloatingOrb.Admin")) {
            Lang.NO_PERMISSION.sendWP(sender);
            return false;
        }
        if (args.length == 0) {
            HelpPage(sender);
        }
        else if (args[0].equalsIgnoreCase("help")) {
            HelpPage(sender);
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            if (args.length == 1 || args[1].equalsIgnoreCase("all")) {
                Main.configManager.load();
                Main.dataManager.load();
                Main.effectManager.load();
                Main.hoverManager.load();
                Main.orbManager.load();
                //Main.particleManager.load();
                Main.rotateManager.load();
                Lang.loadLang();
            }
            else if (args[1].equalsIgnoreCase("config"))
                Main.configManager.load();
            else if (args[1].equalsIgnoreCase("datas"))
                Main.dataManager.load();
            else if (args[1].equalsIgnoreCase("effects"))
                Main.effectManager.load();
            else if (args[1].equalsIgnoreCase("hovers"))
                Main.hoverManager.load();
            else if (args[1].equalsIgnoreCase("orbs"))
                Main.orbManager.load();
            else if (args[1].equalsIgnoreCase("particles")) {}
            	// Main.particleManager.load();
            else if (args[1].equalsIgnoreCase("rotates"))
                Main.rotateManager.load();
            else if (args[1].equalsIgnoreCase("lang"))
                Lang.loadLang();
            Lang.RELOAD.sendWP(sender, Utils.getFileName(args.length == 1 ? "all" : args[1]));
        }
        else if (args[0].equalsIgnoreCase("restart")) {
            int count = FloatingOrb.restart();
            Lang.RESTART.sendWP(sender, count);
        }
        else if (args[0].equalsIgnoreCase("removedead")) {
            int count = Main.dataManager.removeDead();
            Lang.REMOVE_DEAD.sendWP(sender, count);
        }
        else if (args[0].equalsIgnoreCase("removebytype")) {
            if (args.length == 1) {
                Lang.NO_TYPE.sendWP(sender);
                return false;
            }
            String type = "";
            for (int i = 1; i < args.length; i++) {
                type += (i == args.length - 1 ? args[i] : args[i] + " ");
            }
            if (!Main.orbManager.getOrbs().contains(type)) {
                Lang.INVALID_TYPE.sendWP(sender);
                return false;
            }
            int count = Main.dataManager.removeByType(type);
            Lang.REMOVE_BY_TYPE.sendWP(sender, count, type);
        }
        else if (args[0].equalsIgnoreCase("list")) {
            if (args.length == 1)
                Lang.INVALID_LIST.sendWP(sender);
            else if (args[1].equalsIgnoreCase("datas"))
                ListCommand.listDatas(sender);
            else if (args[1].equalsIgnoreCase("effects"))
                ListCommand.listEffects(sender);
            else if (args[1].equalsIgnoreCase("hovers"))
                ListCommand.listHovers(sender);
            else if (args[1].equalsIgnoreCase("orbs"))
                ListCommand.listOrbs(sender);
            else if (args[1].equalsIgnoreCase("rotates"))
                ListCommand.listRotates(sender);
            else
                Lang.INVALID_LIST.sendWP(sender);
        }
        else if (args[0].equalsIgnoreCase("gui")) {
        	// GUI
        	List<ItemStack> items = new ArrayList<>();
        	items.add(((Player) sender).getInventory().getItemInMainHand());
        	Main.orbManager.getFileConf().set("FloatingOrb.Orbs.Radiant Power Orb.Drops", items);
        	Main.orbManager.saveFile();
        }
        else if (args[0].equalsIgnoreCase("nodamagesound")) {
            if (args.length == 1) {
                Lang.NO_BOOLEAN.sendWP(sender);
                return false;
            }
            boolean hassound = Boolean.parseBoolean(args[1]);
            Main.configManager.setCancelSound(hassound);
            if (hassound && !Main.sph.hasListener())
                Main.sph.addListener();
            else if (!hassound && Main.sph.hasListener())
                Main.sph.removeListener();
            Lang.NO_DAMAGE_SOUND.sendWP(sender, hassound ? Lang.ENABLED.format() : Lang.DISABLED.format());
        }
        else {
            if (!(sender instanceof Player)) {
                Lang.NON_PLAYER.sendWP(sender);
                return false;
            }
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("spawn")) {
                if (args.length == 1) {
                    Lang.NO_TYPE.sendWP(sender);
                    return false;
                }
                String type = "";
                for (int i = 1; i < args.length; i++) {
                    type += (i == args.length - 1 ? args[i] : args[i] + " ");
                }
                if (!Main.orbManager.getOrbs().contains(type)) {
                    Lang.INVALID_TYPE.sendWP(sender);
                    return false;
                }
                FloatingOrb.start(player.getLocation(), type);
                Lang.SPAWN.sendWP(sender, type);
            }
            else if (args[0].equalsIgnoreCase("remove")) {
                Main.dataManager.removeInSight(player);
                // Lang.REMOVE_IN_SIGHT.sendWP(sender, "&aRadiant Power Orb", "0", "64", "0");
            }
            else if (args[0].equalsIgnoreCase("removenearby")) {
                if (args.length == 1) {
                    Lang.NO_RANGE.sendWP(sender);
                    return false;
                }
                double range = 0;
                try {
                    range = Double.parseDouble(args[1]);
                } catch (NumberFormatException e) {
                    Lang.INVALID_RANGE.sendWP(sender);
                    return false;
                }
                int count = Main.dataManager.removeNearby((Player) sender, range);
                Lang.REMOVE_NEARBY.sendWP(sender, count, range);
            }
            else if (args[0].equalsIgnoreCase("adddrop")) {
            	if (args.length == 1) {
                    Lang.NO_TYPE.sendWP(sender);
                    return false;
                }
                String type = "";
                for (int i = 1; i < args.length; i++) {
                    type += (i == args.length - 1 ? args[i] : args[i] + " ");
                }
                if (!Main.orbManager.getOrbs().contains(type)) {
                    Lang.INVALID_TYPE.sendWP(sender);
                    return false;
                }
            	ItemStack helditem = player.getInventory().getItemInMainHand();
            	Main.orbManager.addDrop(type, helditem);
            	Bukkit.broadcastMessage("added");
            }
            else {
                HelpPage(player);
            }
        }
        return false;
    }

    public static void HelpPage(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9--------------------&6[&aFloatingOrb&6]&9--------------------"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dMain command: /fo, /flo"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c<>: Required &d[]: Optional"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/FloatingOrb &d[arg]&b: &3Base command."));

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHelp: &2Show this help page."));
        //sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReload &d[all|config|datas|effects|hovers|orbs|particles|rotates]&a: &2Reload files."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReload &d[all|config|datas|effects|hovers|orbs|rotates]&a: &2Reload files."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSpawn &c<Type>&a: &2Spawn FloatingOrb."));
        //sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aList &c<datas|effects|hovers|orbs|particles|rotates>&a: &2List all datas."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aList &c<datas|effects|hovers|orbs|rotates>&a: &2List datas."));
        //sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aGui&a: &2Open FloatingOrb GUI."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRestart: &2Restart all FloatingOrb from database."));

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRemoveDead: &2Remove all dead FloatingOrb."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRemoveByType &c<Type>&a: &2Remove FloatingOrb by type."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aRemoveNearby &c<Range>&a: &2Remove FloatingOrb in specified range."));

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9--------------------&6[&aFloatingOrb&6]&9--------------------"));
    }
}