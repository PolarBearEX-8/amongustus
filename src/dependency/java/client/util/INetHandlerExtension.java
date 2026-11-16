package client.util;

public interface INetHandlerExtension extends INetHandler {
   void applySecondaryPacketAdapter(SecondaryPacketAdapter packet);

   void applyTertiaryPacketAdapter(TertiaryPacketAdapter packet);

   void applyStandardPacketAdapter(StandardPacketAdapter packet);

   void applySpecialPacketAdapter(SpecialPacketAdapter packet);
}
