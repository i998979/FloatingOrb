package to.epac.factorycraft.FloatingOrb.Managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.FloatingOrb.Main;

public class DataManager extends AbstractManager {
	
	public DataManager(Plugin plugin) {
        super(plugin, "datas.yml");
    }
	
	/**
	 * Get all FloatingOrbs
	 * @return
	 */
	public List<String> getOrbs() {
		List<String> list = new ArrayList<>();
		ConfigurationSection orbs = getFileConf().getConfigurationSection("FloatingOrb.Datas");
		
		list.addAll(orbs.getKeys(false));
		return list;
	}
	/**
	 * Get whether the specified UUID is a FloatingOrb
	 * @param id Entity's UUID
	 * @return true when it is a FloatingOrb
	 */
	public boolean hasOrb(UUID uid) {
		return getOrbs().contains(uid.toString());
	}
	/**
	 * Save FloatingOrb into database (datas.yml)
	 * @param id FloatingOrb's UUID
	 * @param type FloatingOrb's type
	 * @param loc FloatingOrb's location
	 */
	public void saveOrb(UUID uid, String type, Location loc) {
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".Type", type);
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".Location", loc);
		
		saveFile();
	}
	/**
	 * Remove FloatingOrb by UUID
	 * @param id FloatingOrb's UUID
	 */
	public void removeOrb(UUID uid) {
		if (Main.dataManager.getEventIdList(uid) != null) {
			for (int taskid : getEventIdList(uid)) {
				Bukkit.getScheduler().cancelTask(taskid);			
			}
		}
		if (Bukkit.getEntity(uid) != null && !Bukkit.getEntity(uid).isDead())
			Bukkit.getEntity(uid).remove();
		getFileConf().set("FloatingOrb.Datas." + uid.toString(), null);
		
		saveFile();
	}
	/**
	 * Remove FloatingOrb by player's sight
	 * @param player Origin player
	 */
	public void removeInSight(Player player) {
    	// https://www.spigotmc.org/threads/how-to-detect-an-entity-the-player-is-looking-at.139310/page-2
    	// LivingEntity#hasLineOfSight()
    	//ChatColor.translateAlternateColorCodes('&', "&aRemoved."));
    	/*Bukkit.broadcastMessage(Bukkit.getServer().getScheduler().isCurrentlyRunning(Integer.valueOf(args[1])) + "");
    	Bukkit.getServer().getScheduler().cancelTask(Integer.valueOf(args[1]));
    	for (BukkitTask worker : Bukkit.getScheduler().getPendingTasks()) {
    		Bukkit.broadcastMessage("" + worker.getTaskId());
    	}
    	Bukkit.broadcastMessage("done");*/
		//removeOrb(uid);
	}
    /**
     * Remove all dead FloatingOrb from database (datas.yml)
     */
    public int removeDead() {
    	int count = 0;
    	
        for (String id: Main.dataManager.getOrbs()) {
            UUID uid = UUID.fromString(id);
            ArmorStand as = (ArmorStand) Bukkit.getEntity(uid);

            if (as == null || as.isDead()) {
            	removeOrb(uid);
            	count++;
            }
        }
        return count;
    }
	/**
	 * Remove FloatingOrbs by type
	 * @param type FloatingOrb's type
	 */
	public int removeByType(String type) {
		int count = 0;
		
		for (String id : getOrbs()) {
			UUID uid = UUID.fromString(id);
			if (type.equalsIgnoreCase(getType(uid))) {
				removeOrb(uid);
				count++;
			}
		}
		return count;
	}
	/**
	 * Remove nearby FloatingOrbs
	 * @param player Player that will be the origin
	 * @param range Range to check from origin
	 */
	public int removeNearby(Player player, double range) {
		int count = 0;
		
    	List<Entity> nearby = player.getNearbyEntities(range, range, range);
    	for (String id : Main.dataManager.getOrbs()) {
    		UUID uid = UUID.fromString(id);
    		Entity entity = Bukkit.getEntity(uid);
    		if (nearby.contains(entity)) {
    			removeOrb(uid);
    			count++;
    		}
    	}
    	return count;
	}
	/**
	 * Get FloatingOrb's event id by UUID
	 * @param id FloatignOrb's UUID
	 * @return FloatingOrb's event id
	 */
	public List<Integer> getEventIdList(UUID uid) {
		return (List<Integer>) getFileConf().getList("FloatingOrb.Datas." + uid.toString() + ".EventId");
	}
	public void addEventId(UUID uid, int id) {
		List<Integer> list = new ArrayList<>();
		if (getEventIdList(uid) != null)
			list = getEventIdList(uid);
		list.add(id);
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".EventId", list);
		
		saveFile();
	}
	public void clearEventId(UUID uid) {
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".EventId", null);
		
		saveFile();
	}
	/**
	 * Get FloatingOrb's type by UUID
	 * @param id FloatignOrb's UUID
	 * @return FloatingOrb's type
	 */
	public String getType(UUID uid) {
		return getFileConf().getString("FloatingOrb.Datas." + uid.toString() + ".Type");
	}
	public void setType(UUID uid, String type) {
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".Type", type);
		
		saveFile();
	}
	/**
	 * Get FloatingOrb's location by UUID
	 * @param id FloatingOrb's UUID
	 * @return FloatingOrb's location
	 */
	public Location getLocation(UUID uid) {
		return (Location) getFileConf().get("FloatingOrb.Datas." + uid.toString() + ".Location");
	}
	public void setLocation(UUID uid, Location loc) {
		getFileConf().set("FloatingOrb.Datas." + uid.toString() + ".Location", loc);
		
		saveFile();
	}
}
