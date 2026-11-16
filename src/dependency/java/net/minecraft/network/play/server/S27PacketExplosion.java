package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class S27PacketExplosion implements Packet {
   public double value3;
   public double value2;
   public double value;
   public float factor3;
   public List list;
   public float factor4;
   public float factor2;
   public float factor;
   public boolean enabled2 = false;
   public boolean enabled = false;

   public void processAction(float factor5) {
      this.factor = factor5;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void processAction2(float factor5) {
      this.factor2 = factor5;
   }

   public float getScaleFactor() {
      return this.factor;
   }

   public void processAction3(float factor5) {
      this.factor4 = factor5;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.value3 = (double)packet.readFloat();
      this.value2 = (double)packet.readFloat();
      this.value = (double)packet.readFloat();
      this.factor3 = packet.readFloat();
      int count = packet.readInt();
      this.list = Lists.newArrayListWithCapacity(count);
      int value4 = (int)this.value3;
      int value5 = (int)this.value2;
      int value6 = (int)this.value;

      for (int index = 0; index < count; index++) {
         int count2 = packet.readByte() + value4;
         int count3 = packet.readByte() + value5;
         int count4 = packet.readByte() + value6;
         this.list.add(new BlockPos(count2, count3, count4));
      }

      this.factor4 = packet.readFloat();
      this.factor2 = packet.readFloat();
      this.factor = packet.readFloat();
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public double getCoordinate() {
      return this.value;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public float getScaleFactor2() {
      return this.factor4;
   }

   public void setEnabled2(boolean enabled3) {
      this.enabled2 = enabled3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeFloat((float)this.value3);
      packet.writeFloat((float)this.value2);
      packet.writeFloat((float)this.value);
      packet.writeFloat(this.factor3);
      packet.writeInt(this.list.size());
      int value4 = (int)this.value3;
      int value5 = (int)this.value2;
      int value6 = (int)this.value;

      for (BlockPos blockPos : (Iterable<BlockPos>)(Iterable<?>)this.list) {
         int count = blockPos.getX() - value4;
         int count2 = blockPos.getY() - value5;
         int count3 = blockPos.getZ() - value6;
         packet.writeByte(count);
         packet.writeByte(count2);
         packet.writeByte(count3);
      }

      packet.writeFloat(this.factor4);
      packet.writeFloat(this.factor2);
      packet.writeFloat(this.factor);
   }

   public List getList() {
      return this.list;
   }

   public double getCoordinate2() {
      return this.value3;
   }

   public float getScaleFactor3() {
      return this.factor2;
   }

   public float getScaleFactor4() {
      return this.factor3;
   }

   public double getCoordinate3() {
      return this.value2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS27PacketExplosion(this);
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public S27PacketExplosion(double posX, double posY, double posZ, float factor5, List list2, Vec3 vec) {
      this.value3 = posX;
      this.value2 = posY;
      this.value = posZ;
      this.factor3 = factor5;
      this.list = Lists.newArrayList(list2);
      if (vec != null) {
         this.factor4 = (float)vec.xCoord;
         this.factor2 = (float)vec.yCoord;
         this.factor = (float)vec.zCoord;
      }
   }

   public S27PacketExplosion() {
   }
}
