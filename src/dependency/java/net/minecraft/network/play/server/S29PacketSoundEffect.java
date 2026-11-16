package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class S29PacketSoundEffect implements Packet {
   public String labelText;
   public int count3;
   public int count2 = Integer.MAX_VALUE;
   public int count4;
   public float factor;
   public int count;

   public String getText() {
      return this.labelText;
   }

   public double getDouble() {
      return (double)((float)this.count3 / 8.0F);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(256);
      this.count3 = packet.readInt();
      this.count2 = packet.readInt();
      this.count4 = packet.readInt();
      this.factor = packet.readFloat();
      this.count = packet.readUnsignedByte();
   }

   public float getFactor() {
      return this.factor;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public double getDouble2() {
      return (double)((float)this.count2 / 8.0F);
   }

   public double getDouble3() {
      return (double)((float)this.count4 / 8.0F);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.writeInt(this.count3);
      packet.writeInt(this.count2);
      packet.writeInt(this.count4);
      packet.writeFloat(this.factor);
      packet.writeByte(this.count);
   }

   public float getFactorAsFloat() {
      return (float)this.count / 63.0F;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS29PacketSoundEffect(this);
   }

   public S29PacketSoundEffect(String text, double posX, double posY, double posZ, float factor2, float factor3) {
      Validate.notNull(text, "name", new Object[0]);
      this.labelText = text;
      this.count3 = (int)(posX * 8.0);
      this.count2 = (int)(posY * 8.0);
      this.count4 = (int)(posZ * 8.0);
      this.factor = factor2;
      this.count = (int)(factor3 * 63.0F);
      MathHelper.clamp_float(factor3, 0.0F, 255.0F);
   }

   public S29PacketSoundEffect() {
   }
}
