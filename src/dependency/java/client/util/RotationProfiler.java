package client.util;

public interface RotationProfiler extends INetHandler {
   void applyExtendedPacketHandler(ExtendedPacketHandler packet);

   void applyCommonPacketAdapter(CommonPacketAdapter packet);
}
