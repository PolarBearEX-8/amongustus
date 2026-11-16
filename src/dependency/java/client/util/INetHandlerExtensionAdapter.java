package client.util;

import net.minecraft.network.NetworkManager;
import net.minecraft.util.IChatComponent;

public class INetHandlerExtensionAdapter implements AuxiliaryINetHandlerExtension {
   public final VanillaRunnable vanillaRunnable;
   public final NetworkManager networkManager;

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
   }

   public INetHandlerExtensionAdapter(VanillaRunnable vanillaRunnable2, NetworkManager networkManager2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.networkManager = networkManager2;
   }

   @Override
   public void applyBasePacketAdapter(BasePacketAdapter packet) {
      this.networkManager.applyCombatKeybindProperty(packet.getCombatKeybindProperty());
      this.networkManager.applyINetHandler(new ListenerAdapter3(this.vanillaRunnable, this.networkManager));
   }
}
