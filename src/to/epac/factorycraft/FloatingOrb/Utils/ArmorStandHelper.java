package to.epac.factorycraft.FloatingOrb.Utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import to.epac.factorycraft.FloatingOrb.Main;

public class ArmorStandHelper {
	
	public static ArmorStand createArmorStand(String type, Location loc) {
		ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		
		as.setCustomName(Main.orbManager.getCustomName(type));
		as.setCustomNameVisible(Main.orbManager.getCustomNameVisible(type));
		as.setGlowing(Main.orbManager.getGlowing(type));
		as.setGravity(Main.orbManager.getGravity(type));
		as.setInvulnerable(Main.orbManager.getInvulnerable(type));
		as.setSilent(Main.orbManager.getSilent(type));
		as.setAI(Main.orbManager.getAI(type));
		as.setCanPickupItems(Main.orbManager.getCanPickUpItems(type));
		as.setCollidable(Main.orbManager.getCollidable(type));
		as.setRemoveWhenFarAway(Main.orbManager.getRemoveWhenFarAway(type));
		as.setArms(Main.orbManager.getArm(type));
		as.setBasePlate(Main.orbManager.getBasePlate(type));
		as.setMarker(Main.orbManager.getMarker(type));
		as.setSmall(Main.orbManager.getSmall(type));
		as.setVisible(Main.orbManager.getVisible(type));
		
		if (Main.orbManager.getHelmet(type) != null)
			as.setHelmet(Main.orbManager.getHelmet(type));
		if (Main.orbManager.getChestPlate(type) != null)
			as.setHelmet(Main.orbManager.getChestPlate(type));
		if (Main.orbManager.getLeggings(type) != null)
			as.setHelmet(Main.orbManager.getLeggings(type));
		if (Main.orbManager.getBoots(type) != null)
			as.setHelmet(Main.orbManager.getBoots(type));
		
		return as;
	}
}
