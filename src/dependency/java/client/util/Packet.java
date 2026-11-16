package client.util;

import java.io.IOException;

public interface Packet {
   void processAction(PacketBuffer packet) throws IOException;

   void processAction(INetHandler iNetHandler);

   void processAction2(PacketBuffer packet) throws IOException;
}
