package to.epac.factorycraft.FloatingOrb.Events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import to.epac.factorycraft.FloatingOrb.Main;

public class OrbInteractHandler implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event) {
		// If damaged entity is not ArmorStand, return
		if (!event.getRightClicked().getType().equals(EntityType.ARMOR_STAND))
			return;
		
		// If clicked entity is a FloatingOrb, cancel
		if (Main.dataManager.hasOrb(event.getRightClicked().getUniqueId())) {
			event.setCancelled(true);
			// TODO - Execute commands / give items to player / etc...
		}
	}
}
