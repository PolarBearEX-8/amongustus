package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class S29PacketSoundEffect implements Packet {
   private String soundName;
   private int posX;
   private int posY = Integer.MAX_VALUE;
   private int posZ;
   private float soundVolume;
   private int soundPitch;

   public String getSoundName() {
      return this.soundName;
   }

   public float getPitch() {
      return (float)this.soundPitch / 63.0F;
   }

   public S29PacketSoundEffect(String str, double d, double d2, double d3, float f, float f2) {
      Validate.notNull(str, "name", new Object[0]);
      this.soundName = str;
      this.posX = (int)(d * 8.0);
      this.posY = (int)(d2 * 8.0);
      this.posZ = (int)(d3 * 8.0);
      this.soundVolume = f;
      this.soundPitch = (int)(f2 * 63.0F);
      f2 = MathHelper.clamp_float(f2, 0.0F, 255.0F);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSoundEffect(this);
   }

   public S29PacketSoundEffect() {
   }

   public double getZ() {
      return (double)((float)this.posZ / 8.0F);
   }

   public double getY() {
      return (double)((float)this.posY / 8.0F);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.soundName);
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeFloat(this.soundVolume);
      buf.writeByte(this.soundPitch);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public double getX() {
      return (double)((float)this.posX / 8.0F);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.soundName = buf.readStringFromBuffer(256);
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.soundVolume = buf.readFloat();
      this.soundPitch = buf.readUnsignedByte();
   }

   public float getVolume() {
      return this.soundVolume;
   }
}
