package to.epac.factorycraft.FloatingOrb;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import to.epac.factorycraft.FloatingOrb.Events.EffectHandler;
import to.epac.factorycraft.FloatingOrb.Utils.ArmorStandHelper;
import to.epac.factorycraft.FloatingOrb.Utils.HoverHelper;
import to.epac.factorycraft.FloatingOrb.Utils.RotateHelper;

public class FloatingOrb {
    /**
     * Start the FloatingOrb with specified ArmorStand
     * @param as The specified ArmorStand
     * @param loc Location of the FloatingOrb
     * @param type Type of the FloatingOrb
     */
    public static void start(ArmorStand as, Location loc, String type) {
        HoverHelper hoverHelper = new HoverHelper(as);
        RotateHelper rotateHelper = new RotateHelper(as);

        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                hoverHelper.hover(Main.orbManager.getHover(type));
                rotateHelper.rotate(Main.orbManager.getRotate(type));
            }
        }, 0L, 0L);

        EffectHandler effHandler = new EffectHandler();
        effHandler.start(as, loc, type);

        Main.dataManager.saveOrb(as.getUniqueId(), type, loc);
    }
    /**
     * Start the FloatingOrb with a new ArmorStand
     * @param loc Location of the FloatingOrb
     * @param type Type of the FloatingOrb
     */
    public static void start(Location loc, String type) {
        ArmorStand as = ArmorStandHelper.createArmorStand(type, loc);
        start(as, loc, type);
    }
    /**
     * Restart everything. Load up all saved FloatingOrb again
     */
    public static int restart() {
    	int count = 0;
    	
        // Cancel all scheduler tasks created by the plugin
        Bukkit.getScheduler().cancelTasks(Main.getInstance());

        List<String> orbs;
        try {
            orbs = Main.dataManager.getOrbs();
        } catch (NullPointerException e) {
            return 0;
        }

        for (String id : orbs) {
            UUID uid = UUID.fromString(id);
            ArmorStand as = (ArmorStand) Bukkit.getEntity(uid);
            
            // Create a new FloatingOrb if the previous one is dead
            if (as == null || as.isDead()) {
            	// Start a new FloatingOrb with old datas
                start(Main.dataManager.getLocation(uid), Main.dataManager.getType(uid));
                // Remove old FloatingOrb datas
                Main.dataManager.removeOrb(uid);
                // Clear old FloatingOrb EventId
                Main.dataManager.clearEventId(uid);
            }
            // Otherwise just restart the exist one
            else {
            	// Clear old FloatingOrb EventId
            	Main.dataManager.clearEventId(uid);
            	// Restart the old FloatingOrb
                start(as, Main.dataManager.getLocation(uid), Main.dataManager.getType(uid));
            }
            count++;
        }
        return count;
    }
}