package to.epac.factorycraft.FloatingOrb.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class EffectManager extends AbstractManager {
	
	public EffectManager(Plugin plugin) {
        super(plugin, "effects.yml");
    }
	
	public List<String> getEffectsGroup() {
		List<String> list = new ArrayList<>();
		ConfigurationSection effects = getFileConf().getConfigurationSection("FloatingOrb.Effects");
		
		list.addAll(effects.getKeys(false));
		
		return list;
	}
	
	public List<String> getEffects(String effectGrp) {
		List<String> list = new ArrayList<>();
		ConfigurationSection effects = getFileConf().getConfigurationSection("FloatingOrb.Effects." + effectGrp);
		
		list.addAll(effects.getKeys(false));
		
		return list;
	}
	
	public boolean getEnabled(String effect) {
		return getFileConf().getBoolean("FloatingOrb.Effects." + effect + ".Enabled");
	}
	
	public int getInterval(String effectGroup, String effect) {
		return getFileConf().getInt("FloatingOrb.Effects." + effectGroup + "." + effect + ".Interval");
	}
	public void setInterval(String effectGroup, String effect, int interval) {
		getFileConf().set("FloatingOrb.Effects." + effectGroup + "." + effect + ".Interval", interval);
		
		saveFile();
	}
	
	public double getMinRange(String effectGroup, String effect) {
		return getFileConf().getDouble("FloatingOrb.Effects." + effectGroup + "." + effect + ".Min_Range");
	}
	public void setMinRange(String effectGroup, String effect, double range) {
		getFileConf().set("FloatingOrb.Effects." + effectGroup + "." + effect + ".Min_Range", range);
		
		saveFile();
	}
	
	public double getMaxRange(String effectGroup, String effect) {
		return getFileConf().getDouble("FloatingOrb.Effects." + effectGroup + "." + effect + ".Max_Range");
	}
	public void setMaxRange(String effectGroup, String effect, double max) {
		getFileConf().set("FloatingOrb.Effects." + effectGroup + "." + effect + ".Max_Range", max);
		
		saveFile();
	}
	
	public int getSeconds(String effectGroup, String effect) {
		return getFileConf().getInt("FloatingOrb.Effects." + effectGroup + "." + effect + ".Seconds");
	}
	public void setSeconds(String effectGroup, String effect, int seconds) {
		getFileConf().set("FloatingOrb.Effects." + effectGroup + "." + effect + ".Seconds", seconds);
		
		saveFile();
	}
	
	public int getAmplifier(String effectGroup, String effect) {
		return getFileConf().getInt("FloatingOrb.Effects." + effectGroup + "." + effect + ".Amplifier");
	}
	public void setAmplifier(String effectGroup, String effect, int amplifier) {
		getFileConf().set("FloatingOrb.Effects." + effectGroup + "." + effect + ".Amplifier", amplifier);
		
		saveFile();
	}
}
