package client.util;

import client.network.CustomDataPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class StatusRequestRotationProfiler implements RotationProfiler {
   public static final IChatComponent iChatComponent;
   public final VanillaRunnable vanillaRunnable;
   public final NetworkManager networkManager;
   public boolean enabled;

   @Override
   public void applyCommonPacketAdapter(CommonPacketAdapter packet) {
      if (this.enabled) {
         this.networkManager.applyIChatComponent(iChatComponent);
      } else {
         this.enabled = true;
         this.networkManager.handlePacket6(new CustomDataPacket(this.vanillaRunnable.getIChatComponentEntry()));
      }
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent2) {
   }

   static {
      String text = "Status request has been handled.";
      iChatComponent = new ChatComponentText(text);
   }

   public StatusRequestRotationProfiler(VanillaRunnable vanillaRunnable2, NetworkManager networkManager2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.networkManager = networkManager2;
   }

   @Override
   public void applyExtendedPacketHandler(ExtendedPacketHandler packet) {
      this.networkManager.handlePacket6(new AdvancedPacketAdapter(packet.getTimestamp()));
      this.networkManager.applyIChatComponent(iChatComponent);
   }
}
