package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class S18PacketEntityTeleport implements Packet {
   public int count4;
   public int count3;
   public int count2;
   public int count;
   public byte byteFlag;
   public byte byteFlag2;
   public boolean enabled;

   public byte getByteFlag2() {
      return this.byteFlag2;
   }

   public void setCount(int count5) {
      this.count = count5;
   }

   public int getIndex() {
      return this.count4;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS18PacketEntityTeleport(this);
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count4 = packet.getInt3();
      this.count3 = packet.readInt();
      this.count2 = packet.readInt();
      this.count = packet.readInt();
      this.byteFlag = packet.readByte();
      this.byteFlag2 = packet.readByte();
      this.enabled = packet.readBoolean();
   }

   public int getCount() {
      return this.count;
   }

   public void setCount4(int count5) {
      this.count4 = count5;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count4);
      packet.writeInt(this.count3);
      packet.writeInt(this.count2);
      packet.writeInt(this.count);
      packet.writeByte(this.byteFlag);
      packet.writeByte(this.byteFlag2);
      packet.writeBoolean(this.enabled);
   }

   public void setCount3(int count5) {
      this.count3 = count5;
   }

   public int getCount2() {
      return this.count2;
   }

   public void setCount2(int count5) {
      this.count2 = count5;
   }

   public S18PacketEntityTeleport(int count5, int count6, int count7, int count8, byte byteValue, byte byteValue2, boolean flag) {
      this.count4 = count5;
      this.count3 = count6;
      this.count2 = count7;
      this.count = count8;
      this.byteFlag = byteValue;
      this.byteFlag2 = byteValue2;
      this.enabled = flag;
   }

   public S18PacketEntityTeleport(Entity entity) {
      this.count4 = entity.getEntityId();
      this.count3 = MathHelper.ceiling_double_int(entity.posX * 32.0);
      this.count2 = MathHelper.ceiling_double_int(entity.posY * 32.0);
      this.count = MathHelper.ceiling_double_int(entity.posZ * 32.0);
      this.byteFlag = (byte)((int)(entity.rotationYaw * 256.0F / 360.0F));
      this.byteFlag2 = (byte)((int)(entity.rotationPitch * 256.0F / 360.0F));
      this.enabled = entity.onGround;
   }

   public S18PacketEntityTeleport() {
   }
}
