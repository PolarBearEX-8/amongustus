package client.util;

import java.io.IOException;
import net.minecraft.util.IChatComponent;

public class SpecialPacketAdapter implements Packet {
   public IChatComponent iChatComponent;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.iChatComponent = packet.getChatComponent();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.applyINetHandlerExtension((INetHandlerExtension)iNetHandler);
   }

   public void applyINetHandlerExtension(INetHandlerExtension iNetHandlerExtension) {
      iNetHandlerExtension.applySpecialPacketAdapter(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.setChatComponent(this.iChatComponent);
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public SpecialPacketAdapter(IChatComponent iChatComponent2) {
      this.iChatComponent = iChatComponent2;
   }

   public SpecialPacketAdapter() {
   }
}
