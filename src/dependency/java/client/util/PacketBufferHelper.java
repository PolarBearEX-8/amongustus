package client.util;

public class PacketBufferHelper {
   public static PacketBuffer resolvePacketBuffer(PacketBuffer packet, int count) {
      int count2;
      for (count2 = count; (count2 & -128) != 0; count2 >>>= 7) {
         packet.writeByte(count2 & 127 | 128);
      }

      packet.writeByte(count2);
      return packet;
   }
}
