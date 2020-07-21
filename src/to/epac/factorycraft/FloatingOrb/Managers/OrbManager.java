package to.epac.factorycraft.FloatingOrb.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class OrbManager extends AbstractManager {
	
	public OrbManager(Plugin plugin) {
        super(plugin, "orbs.yml");
    }
	
	public List<String> getOrbs() {
		List<String> list = new ArrayList<>();
		ConfigurationSection orbs = getFileConf().getConfigurationSection("FloatingOrb.Orbs");
		
		list.addAll(orbs.getKeys(false));
		
		return list;
	}
	
	public String getCustomName(String orb) {
		return ChatColor.translateAlternateColorCodes('&',
				getFileConf().getString("FloatingOrb.Orbs." + orb + ".CustomName"));
	}
	public boolean getCustomNameVisible(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".CustomName_Visible");
	}
	public boolean getGlowing(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Glowing");
	}
	public boolean getGravity(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Gravity");
	}
	public boolean getInvulnerable(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Invulnerable");
	}
	public boolean getSilent(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Silent");
	}
	
	public boolean getAI(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".AI");
	}
	public boolean getCanPickUpItems(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".CanPickUpItems");
	}
	public boolean getCollidable(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Collidable");
	}
	public boolean getRemoveWhenFarAway(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".RemoveWhenFarAway");
	}
	
	public boolean getArm(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Arm");
	}
	public boolean getBasePlate(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".BasePlate");
	}
	public boolean getMarker(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Marker");
	}
	public boolean getSmall(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Small");
	}
	public boolean getVisible(String orb) {
		return getFileConf().getBoolean("FloatingOrb.Orbs." + orb + ".Visible");
	}
	
	public ItemStack getHelmet(String orb) {
		return getFileConf().getItemStack("FloatingOrb.Orbs." + orb + ".Helmet");
	}
	public ItemStack getChestPlate(String orb) {
		return getFileConf().getItemStack("FloatingOrb.Orbs." + orb + ".ChestPlate");
	}
	public ItemStack getLeggings(String orb) {
		return getFileConf().getItemStack("FloatingOrb.Orbs." + orb + ".Leggings");
	}
	public ItemStack getBoots(String orb) {
		return getFileConf().getItemStack("FloatingOrb.Orbs." + orb + ".Boots");
	}
	
	public List<String> getParticles(String orb) {
		return getFileConf().getStringList("FloatingOrb.Orbs." + orb + ".Particles");
	}
	public List<String> getEffects(String orb) {
		return getFileConf().getStringList("FloatingOrb.Orbs." + orb + ".Effects");
	}
	public String getHover(String orb) {
		return getFileConf().getString("FloatingOrb.Orbs." + orb + ".Hover");
	}
	public String getRotate(String orb) {
		return getFileConf().getString("FloatingOrb.Orbs." + orb + ".Rotate");
	}
	public List<ItemStack> getDrops(String orb) {
		return (List<ItemStack>) getFileConf().getList("FloatingOrb.Orbs." + orb + ".Drops");
	}
	public void addDrop(String orb, ItemStack drop) {
		List<ItemStack> drops = new ArrayList<>();
		if (getDrops(orb) != null)
			drops = getDrops(orb);
		drops.add(drop);
		
		setDrop(orb, drops);
		saveFile();
	}
	public void setDrop(String orb, List<ItemStack> drops) {
		getFileConf().set("FloatingOrb.Orbs." + orb + ".Drops", drops);
		saveFile();
	}
}
