package to.epac.factorycraft.FloatingOrb.Utils;

public class Utils {
	public static String getFileName(String type) {
		if (type.equalsIgnoreCase("config"))
			return "Configuration";
		else if (type.equalsIgnoreCase("datas"))
			return "Datas";
		else if (type.equalsIgnoreCase("effects"))
			return "Effects";
		else if (type.equalsIgnoreCase("hovers"))
			return "Hovers";
		else if (type.equalsIgnoreCase("orbs"))
			return "Orbs";
		else if (type.equalsIgnoreCase("particles"))
			return "Particles";
		else if (type.equalsIgnoreCase("rotates"))
			return "Rotates";
		else if (type.equalsIgnoreCase("lang"))
			return "Language";
		else if (type.equalsIgnoreCase("all"))
			return "Everything";
		else
			return "Files";
	}
}
