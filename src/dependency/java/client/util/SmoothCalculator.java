package client.util;

import client.network.CustomDataPacket;

public interface SmoothCalculator extends INetHandler {
   void applyCustomDataPacket(CustomDataPacket packet);

   void applyAdvancedPacketAdapter(AdvancedPacketAdapter packet);
}
