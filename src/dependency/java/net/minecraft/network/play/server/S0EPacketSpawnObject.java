package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class S0EPacketSpawnObject implements Packet {
   public int count9;
   public int count4;
   public int count6;
   public int count5;
   public int count7;
   public int count8;
   public int count10;
   public int count11;
   public int count2;
   public int count3;
   public int count;

   public int getCount10() {
      return this.count10;
   }

   public void setCount5(int count12) {
      this.count5 = count12;
   }

   public void setCount10(int count12) {
      this.count10 = count12;
   }

   public int getCount4() {
      return this.count4;
   }

   public void setCount8(int count12) {
      this.count8 = count12;
   }

   public int getCount7() {
      return this.count7;
   }

   public int getCount6() {
      return this.count6;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0EPacketSpawnObject(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count9 = packet.getInt3();
      this.count3 = packet.readByte();
      this.count4 = packet.readInt();
      this.count6 = packet.readInt();
      this.count5 = packet.readInt();
      this.count11 = packet.readByte();
      this.count2 = packet.readByte();
      this.count = packet.readInt();
      if (this.count > 0) {
         this.count7 = packet.readShort();
         this.count8 = packet.readShort();
         this.count10 = packet.readShort();
      }
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount11() {
      return this.count11;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void setCount4(int count12) {
      this.count4 = count12;
   }

   public void setCount7(int count12) {
      this.count7 = count12;
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount5() {
      return this.count5;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count9);
      packet.writeByte(this.count3);
      packet.writeInt(this.count4);
      packet.writeInt(this.count6);
      packet.writeInt(this.count5);
      packet.writeByte(this.count11);
      packet.writeByte(this.count2);
      packet.writeInt(this.count);
      if (this.count > 0) {
         packet.writeShort(this.count7);
         packet.writeShort(this.count8);
         packet.writeShort(this.count10);
      }
   }

   public int getCount8() {
      return this.count8;
   }

   public void setCount(int count12) {
      this.count = count12;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount9() {
      return this.count9;
   }

   public void setCount6(int count12) {
      this.count6 = count12;
   }

   public S0EPacketSpawnObject(Entity entity, int count12, int count13) {
      this.count9 = entity.getEntityId();
      this.count4 = MathHelper.ceiling_double_int(entity.posX * 32.0);
      this.count6 = MathHelper.ceiling_double_int(entity.posY * 32.0);
      this.count5 = MathHelper.ceiling_double_int(entity.posZ * 32.0);
      this.count11 = MathHelper.floor_float(entity.rotationPitch * 256.0F / 360.0F);
      this.count2 = MathHelper.floor_float(entity.rotationYaw * 256.0F / 360.0F);
      this.count3 = count12;
      this.count = count13;
      if (count13 > 0) {
         double motionX = entity.motionX;
         double motionY = entity.motionY;
         double motionZ = entity.motionZ;
         double offset = 3.9;
         if (motionX < -offset) {
            motionX = -offset;
         }

         if (motionY < -offset) {
            motionY = -offset;
         }

         if (motionZ < -offset) {
            motionZ = -offset;
         }

         if (motionX > offset) {
            motionX = offset;
         }

         if (motionY > offset) {
            motionY = offset;
         }

         if (motionZ > offset) {
            motionZ = offset;
         }

         this.count7 = (int)(motionX * 8000.0);
         this.count8 = (int)(motionY * 8000.0);
         this.count10 = (int)(motionZ * 8000.0);
      }
   }

   public S0EPacketSpawnObject() {
   }

   public S0EPacketSpawnObject(Entity entity, int count12) {
      this(entity, count12, 0);
   }
}
