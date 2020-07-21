package to.epac.factorycraft.FloatingOrb;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitTask;

import to.epac.factorycraft.FloatingOrb.Events.EffectHandler;
import to.epac.factorycraft.FloatingOrb.Utils.ArmorStandHelper;
import to.epac.factorycraft.FloatingOrb.Utils.HoverHelper;
import to.epac.factorycraft.FloatingOrb.Utils.RotateHelper;

public class Orb {
	private ArmorStand as;
	private Location loc;
	private String type;
	private HoverHelper hoverHelper;
	private RotateHelper rotateHelper;
	private EffectHandler effHandler;
	private BukkitTask task;
	
	public Orb(ArmorStand as, Location loc, String type, HoverHelper hoverHelper, RotateHelper rotateHelper, EffectHandler effHandler) {
		this.as = as;
		this.loc = loc;
		this.type = type;
		this.hoverHelper = hoverHelper;
		this.rotateHelper = rotateHelper;
		this.effHandler = effHandler;
	}
	public Orb(ArmorStand as, Location loc, String type) {
		this(as, loc, type, new HoverHelper(as), new RotateHelper(as), new EffectHandler());
	}
	
	public void start(ArmorStand as, Location loc, String type) {
		task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                hoverHelper.hover(Main.orbManager.getHover(type));
                rotateHelper.rotate(Main.orbManager.getRotate(type));
            }
        }, 0L, 0L);
		
		effHandler.start(as, loc, type);
		Main.dataManager.saveOrb(as.getUniqueId(), type, loc);
	}
	   /**
     * Start the FloatingOrb with a new ArmorStand
     * @param loc Location of the FloatingOrb
     * @param type Type of the FloatingOrb
     */
    public void start(Location loc, String type) {
        as = ArmorStandHelper.createArmorStand(type, loc);
        start(as, loc, type);
    }
    /**
     * Restart everything. Load up all saved FloatingOrb again
     */
	public int restart() {
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
                start(Main.dataManager.getLocation(uid), Main.dataManager.getType(uid));
                Main.dataManager.removeOrb(uid);
            }
            // Otherwise just restart the exist one
            else {
                start(as, Main.dataManager.getLocation(uid), Main.dataManager.getType(uid));
            }
            count++;
        }
        return count;
	}
	
	public UUID getUniqueId() {
		return as.getUniqueId();
	}
	/* * * * * * * * * */
	public ArmorStand getStand() {
		return as;
	}
	public void setStand(ArmorStand as) {
		this.as = as;
	}
	public Location getLocation() {
		return loc;
	}
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HoverHelper getHoverHelper() {
		return hoverHelper;
	}
	public void setHoverHelper(HoverHelper hoverHelper) {
		this.hoverHelper = hoverHelper;
	}
	public RotateHelper getRotateHelper() {
		return rotateHelper;
	}
	public void setRotateHelper(RotateHelper rotateHelper) {
		this.rotateHelper = rotateHelper;
	}
	public EffectHandler getEffHandler() {
		return effHandler;
	}
	public void setEffHandler(EffectHandler effHandler) {
		this.effHandler = effHandler;
	}
	public BukkitTask getTask() {
		return task;
	}
	public void setTask(BukkitTask task) {
		this.task = task;
	}
}
