package to.epac.factorycraft.FloatingOrb.Events;

import org.bukkit.Sound;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import to.epac.factorycraft.FloatingOrb.Main;

public class SoundPacketHandler {
	
	private ProtocolManager protocolManager;
	private PacketAdapter adapter;
	
	public SoundPacketHandler() {
		protocolManager = ProtocolLibrary.getProtocolManager();
	}
	
	public ProtocolManager getProtocolManager() {
		return this.protocolManager;
	}
	public void removeListener() {
		protocolManager.removePacketListeners(Main.getInstance());
	}
	public boolean hasListener() {
		return protocolManager.getPacketListeners().contains(adapter);
	}
	public void addListener() {
	    protocolManager.addPacketListener(adapter = new PacketAdapter(Main.getInstance(), new PacketType[]{PacketType.Play.Server.NAMED_SOUND_EFFECT}) {
	        @Override
	        public void onPacketSending(PacketEvent event) {
	            if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
	                PacketContainer packet = event.getPacket();
	                Sound sound = packet.getSoundEffects().read(0);
	                
	                if (sound == Sound.ENTITY_PLAYER_ATTACK_NODAMAGE)
	                	event.setCancelled(true);
	            }
	        }
	    });
	}
}
