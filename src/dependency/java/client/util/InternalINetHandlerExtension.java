package client.util;

public interface InternalINetHandlerExtension extends INetHandler {
   void applyExtraPacketHandler(ExtraPacketHandler packet);

   void applyCustomPacketAdapter(CustomPacketAdapter packet);
}
