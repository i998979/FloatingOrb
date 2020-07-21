package to.epac.factorycraft.FloatingOrb.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class HoverManager extends AbstractManager {
	
	public HoverManager(Plugin plugin) {
        super(plugin, "hovers.yml");
    }
	
	public List<String> getHovers() {
		List<String> list = new ArrayList<>();
		ConfigurationSection hovers = getFileConf().getConfigurationSection("FloatingOrb.Hover");
		
		list.addAll(hovers.getKeys(false));
		return list;
	}
	
	public boolean getEnabled(String type) {
		return getFileConf().getBoolean("FloatingOrb.Hover." + type + ".Enabled");
	}
	
	public List<String> getHoverStyle(String type) {
		return (List<String>) getFileConf().getList("FloatingOrb.Hover." + type + ".Style");
	}
}
