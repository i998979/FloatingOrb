package to.epac.factorycraft.FloatingOrb;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import to.epac.factorycraft.FloatingOrb.Commands.Commands;
import to.epac.factorycraft.FloatingOrb.Events.OrbDestroyHandler;
import to.epac.factorycraft.FloatingOrb.Events.OrbInteractHandler;
import to.epac.factorycraft.FloatingOrb.Events.SoundPacketHandler;
import to.epac.factorycraft.FloatingOrb.Managers.ConfigManager;
import to.epac.factorycraft.FloatingOrb.Managers.DataManager;
import to.epac.factorycraft.FloatingOrb.Managers.EffectManager;
import to.epac.factorycraft.FloatingOrb.Managers.HoverManager;
import to.epac.factorycraft.FloatingOrb.Managers.OrbManager;
import to.epac.factorycraft.FloatingOrb.Managers.RotateManager;
import to.epac.factorycraft.FloatingOrb.Utils.Lang;

public class Main extends JavaPlugin {

    private static Main instance;

    public static ConfigManager configManager;
    public static DataManager dataManager;
    public static EffectManager effectManager;
    public static HoverManager hoverManager;
    public static OrbManager orbManager;
    // public static ParticleManager particleManager;
    public static RotateManager rotateManager;
    
    public static SoundPacketHandler sph;
    
    public void onEnable() {
        instance = this;

        PluginManager pm = getServer().getPluginManager();
        
        pm.registerEvents(new OrbInteractHandler(), this);
        pm.registerEvents(new OrbDestroyHandler(), this);

        getCommand("FloatingOrb").setExecutor(new Commands());
        
        configManager = new ConfigManager(this);
        dataManager = new DataManager(this);
        effectManager = new EffectManager(this);
        hoverManager = new HoverManager(this);
        orbManager = new OrbManager(this);
        // particleManager = new ParticleManager(this);
        rotateManager = new RotateManager(this);
        
        sph = new SoundPacketHandler();

        if (pm.isPluginEnabled("ProtocolLib")) {
            if (configManager.getCancelSound()) {
            	if (!sph.hasListener())
            		sph.addListener();
            	getLogger().info("ProtocolLib found. No damage sound is ENABLED.");
            	getLogger().info("Players will receive no damage sound on attack.");
            }
            else {
            	getLogger().info("ProtocolLib found. No damage sound is DISABLED.");
            	getLogger().info("I'm not going to load anything.");
            }
        }
        else {
        	getLogger().info("ProtocolLib not found. I'm not going to load anything.");
        }
        
        int count = FloatingOrb.restart();
        getLogger().info("Restarted " + count + " FloatingOrb.");
        
        Lang.loadLang();
    }

    public void onDisable() {
        instance = null;
    }

    public static Plugin getInstance() {
        return instance;
    }
}