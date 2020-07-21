package to.epac.factorycraft.FloatingOrb.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class RotateManager extends AbstractManager {
	
	public RotateManager(Plugin plugin) {
        super(plugin, "rotates.yml");
    }
	
	public List<String> getRotates() {
		List<String> list = new ArrayList<>();
		ConfigurationSection rotates = getFileConf().getConfigurationSection("FloatingOrb.Rotate");
		
		list.addAll(rotates.getKeys(false));
		
		return list;
	}
	
	public boolean getEnabled(String type) {
		return getFileConf().getBoolean("FloatingOrb.Rotate." + type + ".Enabled");
	}
	
	public List<String> getRotateStyle(String type) {
		return (List<String>) getFileConf().getList("FloatingOrb.Rotate." + type + ".Style");
	}
}
