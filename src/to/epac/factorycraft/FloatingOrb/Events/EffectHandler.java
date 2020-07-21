package to.epac.factorycraft.FloatingOrb.Events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import to.epac.factorycraft.FloatingOrb.Main;

public class EffectHandler {
	
	public void start(ArmorStand as, Location loc, String type) {
		
		for (String effGroup : Main.orbManager.getEffects(type)) {
			if (!Main.effectManager.getEnabled(effGroup))
				continue;
			
			for (String effect : Main.effectManager.getEffects(effGroup)) {
				
				int interval = Main.effectManager.getInterval(effGroup, effect);
				double min = Main.effectManager.getMinRange(effGroup, effect);
				double max = Main.effectManager.getMaxRange(effGroup, effect);
				
				BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
			        @Override
			        public void run() {
			        	List<Entity> entity_in_max = as.getNearbyEntities(max, max, max);
			        	List<Entity> entity_in_min = as.getNearbyEntities(min, min, min);
			        	
			        	entity_in_max.removeAll(entity_in_min);
			        	
			        	for (Entity entity : entity_in_max) {
			        		if (entity instanceof Player) {
			        			Player player = (Player) entity;
			        			
			        			PotionEffect pot = new PotionEffect(
										PotionEffectType.getByName(effect),
										Main.effectManager.getSeconds(effGroup, effect) * 20,
										Main.effectManager.getAmplifier(effGroup, effect));
								pot.apply(player);
			        		}
			        	}
			        }
			    }, 0L, interval);
				Main.dataManager.addEventId(as.getUniqueId(), task.getTaskId());
			}
		}
	}
}
