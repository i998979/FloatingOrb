package to.epac.factorycraft.FloatingOrb.Managers;

import org.bukkit.plugin.Plugin;

public class ParticleManager extends AbstractManager {
	// https://dev.bukkit.org/projects/effectlib
	// https://github.com/ByteZ1337/ParticleLib
	
	public ParticleManager(Plugin plugin) {
        super(plugin, "particles.yml");
    }
	
}
