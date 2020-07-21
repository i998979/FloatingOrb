package to.epac.factorycraft.FloatingOrb.Utils;

import org.bukkit.entity.ArmorStand;

import to.epac.factorycraft.FloatingOrb.Main;

public class HoverHelper {

    private ArmorStand as;
    private int phase = 0;
    private boolean up;
    private double level;

    /**
     * Initialize HoverHelper with arguments
     * @param as The ArmorStand
     * @param up Hover upward
     * @param level How high the FloatingOrb is at
     */
    public HoverHelper(ArmorStand as, boolean up, int level) {
        this.as = as;
        this.up = up;
        this.level = level;
    }
    /**
     * Initialize HoverHelper without arguments
     * @param as The ArmorStand
     */
    public HoverHelper(ArmorStand as) {
        this(as, true, 0);
    }
    /**
     * Start hover the FloatingOrb
     * @param type FloatingOrb type
     */
    public void hover(String type) {
        if (!Main.hoverManager.getEnabled(type)) return;

        if (phase >= Main.hoverManager.getHoverStyle(type).size()) phase = 0;

        String style0 = Main.hoverManager.getHoverStyle(type).get(phase);

        String[] style = style0.split("\\s+");
        boolean up = style[0].equals("Up") ? true : false;
        double x = Double.parseDouble(style[1]);
        double y = Double.parseDouble(style[2]);
        double z = Double.parseDouble(style[3]);
        double next = Double.parseDouble(style[4]);

        if (this.up) {
            if (!up) this.up = false;
            
            hoverUp(x, y, z);
            level += y;

            if (level > next) phase++;
        } else {
            if (up) this.up = true;
            
            hoverDown(x, y, z);
            level -= y;

            if (level < next) phase++;
        }
    }
    /**
     * Hover the FloatingOrb upward
     * @param x X-axis value
     * @param y Y-axis value
     * @param z Z-axis value
     */
    public void hoverUp(double x, double y, double z) {
        as.teleport(as.getLocation().add(x, y, z));
    }
    /**
     * Hover the FloatingOrb downward
     * @param x X-axis value
     * @param y Y-axis value
     * @param z Z-axis value
     */
    public void hoverDown(double x, double y, double z) {
        as.teleport(as.getLocation().subtract(x, y, z));
    }
}