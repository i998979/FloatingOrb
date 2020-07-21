package to.epac.factorycraft.FloatingOrb.Utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import to.epac.factorycraft.FloatingOrb.Main;

public class RotateHelper {

    private ArmorStand as;
    private int phase = 0;
    private boolean clockwise;
    private double degree;
    
    /**
     * Initialize RotateHelper with arguments
     * @param as The ArmorStand
     * @param clockwise Rotate clockwise
     * @param degree What degree the FloatingOrb is at
     */
    public RotateHelper(ArmorStand as, boolean clockwise, int degree) {
        this.as = as;
        this.clockwise = clockwise;
        this.degree = degree;
    }
    /**
     * Initialize RotateHelper without arguments
     * @param as The ArmorStand
     */
    public RotateHelper(ArmorStand as) {
        this(as, true, 0);
    }
    /**
     * Start rotate the FloatingOrb
     * @param type FloatingOrb type
     */
    public void rotate(String type) {
    	if (!Main.rotateManager.getEnabled(type)) return;
    	
    	if (phase >= Main.rotateManager.getRotateStyle(type).size()) phase = 0;
    	
    	String style0 = Main.rotateManager.getRotateStyle(type).get(phase);
    	
    	String[] style = style0.split("\\s+");
    	boolean clockwise = style[0].equals("CW");
    	double x = Double.parseDouble(style[1]);
    	double y = Double.parseDouble(style[2]);
    	double z = Double.parseDouble(style[3]);
    	double next = Double.parseDouble(style[4]);
    	
    	if (this.clockwise) {
    		if (!clockwise) this.clockwise = false;
    		
    		rotate(Math.toRadians(x), Math.toRadians(degree += y), Math.toRadians(z)); ;
    		
    		if (degree > next) phase++;
    	}
    	else {
    		if (clockwise) this.clockwise = true;
    		
    		rotate(Math.toRadians(x), Math.toRadians(degree -= y), Math.toRadians(z)); ;
    		
    		if (degree < next) phase++;
    	}
    }
    /**
     * Rotate the FloatingOrb
     * @param x X-axis value
     * @param y Y-axis value
     * @param z Z-axis value
     */
    public void rotate(double x, double y, double z) {
        as.setHeadPose(new EulerAngle(x, y, z));
    }
}