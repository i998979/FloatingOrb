package to.epac.factorycraft.FloatingOrb.Managers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public abstract class AbstractManager {
    private final Plugin plugin;

    private String name;
    private File file;
    private FileConfiguration fileConf;

    public AbstractManager(Plugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        load();
    }
    
    public void load() {
    	this.file = new File(plugin.getDataFolder(), name);
    	if (!file.exists())
    		plugin.saveResource(name, false);
        this.fileConf = YamlConfiguration.loadConfiguration(file);
        saveFile();
    }
    public Plugin getInstance() {
        return plugin;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FileConfiguration getFileConf() {
        return fileConf;
    }
    public void setFileConf(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    /**
     * Copy from JavaPlugin#saveResource, slightly modified
     * Same effect as
     * plugin.saveResource(name, true);
     */
    public void copyFile() {
        try {
            InputStream in = plugin.getResource(name);
            OutputStream out = new FileOutputStream(getFile());
            byte[] buf = new byte[1024];
            int len;
            while ((len = in .read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close(); in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove comments except header and save file
     */
    public void saveFile() {
        try {
            getFileConf().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // saveFileWithComments();
    }
    /**
     * Save comments and save file, but saving ItemStack
     * directly into file will make it error
     */
    /*@Deprecated
    public void saveFileWithComments() {
    	try {
			ConfigUpdater.update(getInstance(), name, getFile(), new ArrayList<>());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }*/
}