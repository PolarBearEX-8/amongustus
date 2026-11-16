package net.minecraft.network.play.server;

import client.util.DataWatcher;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.SkippingEntityWithIdManager;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class S0FPacketSpawnMob implements Packet {
   public int count3;
   public int count7;
   public int count2;
   public int count5;
   public int count6;
   public int count;
   public int count8;
   public int count4;
   public byte byteFlag2;
   public byte byteFlag3;
   public byte byteFlag;
   public DataWatcher dataWatcher;
   public List list;

   public int getCount7() {
      return this.count7;
   }

   public List getList() {
      if (this.list == null) {
         this.list = this.dataWatcher.getList();
      }

      return this.list;
   }

   public byte getByteFlag2() {
      return this.byteFlag2;
   }

   public int getCount8() {
      return this.count8;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count3 = packet.getInt3();
      this.count7 = packet.readByte() & 255;
      this.count2 = packet.readInt();
      this.count5 = packet.readInt();
      this.count6 = packet.readInt();
      this.byteFlag2 = packet.readByte();
      this.byteFlag3 = packet.readByte();
      this.byteFlag = packet.readByte();
      this.count = packet.readShort();
      this.count8 = packet.readShort();
      this.count4 = packet.readShort();
      this.list = DataWatcher.resolveList(packet);
   }

   public int getCount5() {
      return this.count5;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount4() {
      return this.count4;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count3);
      packet.writeByte(this.count7 & 0xFF);
      packet.writeInt(this.count2);
      packet.writeInt(this.count5);
      packet.writeInt(this.count6);
      packet.writeByte(this.byteFlag2);
      packet.writeByte(this.byteFlag3);
      packet.writeByte(this.byteFlag);
      packet.writeShort(this.count);
      packet.writeShort(this.count8);
      packet.writeShort(this.count4);
      this.dataWatcher.applyPacketBuffer(packet);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0FPacketSpawnMob(this);
   }

   public int getCount6() {
      return this.count6;
   }

   public byte getByteFlag3() {
      return this.byteFlag3;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   public S0FPacketSpawnMob() {
   }

   public S0FPacketSpawnMob(EntityLivingBase livingEntity) {
      this.count3 = livingEntity.getEntityId();
      this.count7 = (byte)SkippingEntityWithIdManager.computeCount(livingEntity);
      this.count2 = MathHelper.ceiling_double_int(livingEntity.posX * 32.0);
      this.count5 = MathHelper.ceiling_double_int(livingEntity.posY * 32.0);
      this.count6 = MathHelper.ceiling_double_int(livingEntity.posZ * 32.0);
      this.byteFlag2 = (byte)((int)(livingEntity.rotationYaw * 256.0F / 360.0F));
      this.byteFlag3 = (byte)((int)(livingEntity.rotationPitch * 256.0F / 360.0F));
      this.byteFlag = (byte)((int)(livingEntity.scaleFactor12 * 256.0F / 360.0F));
      double offset = 3.9;
      double value = livingEntity.motionX;
      double value2 = livingEntity.motionY;
      double value3 = livingEntity.motionZ;
      if (value < -offset) {
         value = -offset;
      }

      if (value2 < -offset) {
         value2 = -offset;
      }

      if (value3 < -offset) {
         value3 = -offset;
      }

      if (value > offset) {
         value = offset;
      }

      if (value2 > offset) {
         value2 = offset;
      }

      if (value3 > offset) {
         value3 = offset;
      }

      this.count = (int)(value * 8000.0);
      this.count8 = (int)(value2 * 8000.0);
      this.count4 = (int)(value3 * 8000.0);
      this.dataWatcher = livingEntity.getDataWatcher();
   }
}
