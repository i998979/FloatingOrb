package to.epac.factorycraft.FloatingOrb.Events;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import to.epac.factorycraft.FloatingOrb.Main;

public class OrbDestroyHandler implements Listener {
	
	@EventHandler
	public void onDestroy(EntityDamageByEntityEvent event) {
		UUID uid = event.getEntity().getUniqueId();
		Location loc = event.getEntity().getLocation();
		
		// If damaged entity is not ArmorStand, return
		if (!event.getEntityType().equals(EntityType.ARMOR_STAND))
			return;
		
		// If damaged entity is a FloatingOrb, remove
		if (Main.dataManager.hasOrb(uid)) {
			String type = Main.dataManager.getType(uid);
			
			// If FloatingOrb is invulnerable, then cancel its death
			if (Main.orbManager.getInvulnerable(type))
				event.setCancelled(true);
			// Otherwise, it is dead, and remove from database
			else
				Main.dataManager.removeOrb(uid);
			
			// No matter it is dead or not, drop items
			for (ItemStack drop : Main.orbManager.getDrops(type)) {
				event.getEntity().getWorld().dropItemNaturally(loc, drop);
			}
		}
	}
}
