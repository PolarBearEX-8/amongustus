package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2BPacketChangeGameState implements Packet {
   public static final String[] MESSAGE_NAMES;
   private int state;
   private float value;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S2BPacketChangeGameState(int n, float f) {
      this.state = n;
      this.value = f;
   }

   static {
      String str = "tile.bed.notValid";
      MESSAGE_NAMES = new String[]{str};
   }

   public void setValue(float f) {
      this.value = f;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.state = buf.readUnsignedByte();
      this.value = buf.readFloat();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.state);
      buf.writeFloat(this.value);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleChangeGameState(this);
   }

   public int getGameState() {
      return this.state;
   }

   public S2BPacketChangeGameState() {
   }

   public float getValue() {
      return this.value;
   }
}
