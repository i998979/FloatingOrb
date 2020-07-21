package to.epac.factorycraft.FloatingOrb.Commands;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import to.epac.factorycraft.FloatingOrb.Main;

public class ListCommand {

    public static void listDatas(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------&3Floating&bOrb &aDatas&b----------"));
        if (Main.dataManager.getOrbs().size() == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2No datas."));
            return;
        }

        for (String id: Main.dataManager.getOrbs()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d" + id + ":"));
            UUID uid = UUID.fromString(id);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aEvent Id:"));
            for (Integer eventid: Main.dataManager.getEventIdList(uid))
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &e" + eventid));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Type: " + Main.dataManager.getType(uid)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aLocation:"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eWorld: " + Main.dataManager.getLocation(uid).getWorld().getName()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eX: " + Main.dataManager.getLocation(uid).getX()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eY: " + Main.dataManager.getLocation(uid).getY()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eZ: " + Main.dataManager.getLocation(uid).getZ()));
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------------------------------------"));
    }

    public static void listEffects(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------&3Floating&bOrb &aEffects&b----------"));
        if (Main.effectManager.getEffectsGroup() == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2No datas."));
            return;
        }

        for (String effectGrp: Main.effectManager.getEffectsGroup()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d" + effectGrp + ":"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eEnabled: " +
                (Main.effectManager.getEnabled(effectGrp) ? "&atrue" : "&cfalse")));
            for (String effect: Main.effectManager.getEffects(effectGrp)) {
                if (effect.equals("Enabled")) continue;

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &b" + effect + ":"));

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &aMin range: " + Main.effectManager.getMinRange(effectGrp, effect)));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &2Max range: " + Main.effectManager.getMaxRange(effectGrp, effect)));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &aSeconds: " + Main.effectManager.getSeconds(effectGrp, effect)));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &2Amplifier: " + Main.effectManager.getAmplifier(effectGrp, effect)));
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------------------------------------"));
        }
    }

    public static void listHovers(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------&3Floating&bOrb &aHovers&b----------"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eDir: Direction the FloatingOrb will go."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ex/y/z: Accurate degrees in 3 axis."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eNext: Move to next phase when internal count reach this number."));
        if (Main.hoverManager.getHovers() == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2No datas."));
            return;
        }

        for (String type: Main.hoverManager.getHovers()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d" + type + ":"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &eDir x y z next"));
            for (String style: Main.hoverManager.getHoverStyle(type)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &a" + style));
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------------------------------------"));
        }
    }

    public static void listOrbs(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------&3Floating&bOrb &aOrbs&b----------"));
        if (Main.orbManager.getOrbs() == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2No datas."));
            return;
        }

        for (String orb: Main.orbManager.getOrbs()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dName: " + Main.orbManager.getCustomName(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aName visible: " + Main.orbManager.getCustomNameVisible(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Glowing: " + Main.orbManager.getGlowing(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aGravity: " + Main.orbManager.getGravity(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Invulnerable: " + Main.orbManager.getInvulnerable(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aSilent: " + Main.orbManager.getSilent(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2AI: " + Main.orbManager.getAI(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aCan pickup items: " + Main.orbManager.getCanPickUpItems(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Collidable: " + Main.orbManager.getCollidable(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aRemove when far away: " + Main.orbManager.getRemoveWhenFarAway(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Arm: " + Main.orbManager.getArm(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aBase plate: " + Main.orbManager.getBasePlate(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Marker: " + Main.orbManager.getMarker(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aSmall: " + Main.orbManager.getSmall(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Visible: " + Main.orbManager.getVisible(orb)));
            if (Main.orbManager.getHelmet(orb) != null)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &bHelmet: " + Main.orbManager.getHelmet(orb)));
            if (Main.orbManager.getChestPlate(orb) != null)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &9Chestplate: " + Main.orbManager.getChestPlate(orb)));
            if (Main.orbManager.getLeggings(orb) != null)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &bLeggings: " + Main.orbManager.getLeggings(orb)));
            if (Main.orbManager.getBoots(orb) != null)
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &9Boots: " + Main.orbManager.getBoots(orb)));
            /*sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aParticles:"));
            for (String particle : Main.orbManager.getParticles(orb))
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &e" + particle));*/
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Effects: "));
            for (String effect: Main.orbManager.getEffects(orb))
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &e" + effect));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &aHover: " + Main.orbManager.getHover(orb)));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &2Rotate: " + Main.orbManager.getRotate(orb)));
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------------------------------------"));
    }

    public static void listParticles(CommandSender sender) {

    }

    public static void listRotates(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------&3Floating&bOrb &aRotates&b----------"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eDir: Direction the FloatingOrb will go. Clockwise/Anti-Clockwise."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ex/y/z: Accurate degrees in 3 axis."));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eNext: Move to next phase when internal count reach this degree."));
        if (Main.rotateManager.getRotates() == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2No datas."));
            return;
        }

        for (String type: Main.rotateManager.getRotates()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d" + type + ":"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &eDir x y z next"));
            for (String style: Main.rotateManager.getRotateStyle(type)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &a" + style));
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b----------------------------------------"));
        }
    }
}