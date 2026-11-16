package net.minecraft.network.play.server;

import client.util.HUIHRVManager;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class S2CPacketSpawnGlobalEntity implements Packet {
   public int count4;
   public int count3;
   public int count;
   public int count5;
   public int count2;

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count4 = packet.getInt3();
      this.count2 = packet.readByte();
      this.count3 = packet.readInt();
      this.count = packet.readInt();
      this.count5 = packet.readInt();
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2CPacketSpawnGlobalEntity(this);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount4() {
      return this.count4;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count4);
      packet.writeByte(this.count2);
      packet.writeInt(this.count3);
      packet.writeInt(this.count);
      packet.writeInt(this.count5);
   }

   public int getCount2() {
      return this.count2;
   }

   public int getCount5() {
      return this.count5;
   }

   public S2CPacketSpawnGlobalEntity() {
   }

   public S2CPacketSpawnGlobalEntity(Entity entity) {
      this.count4 = entity.getEntityId();
      this.count3 = MathHelper.ceiling_double_int(entity.posX * 32.0);
      this.count = MathHelper.ceiling_double_int(entity.posY * 32.0);
      this.count5 = MathHelper.ceiling_double_int(entity.posZ * 32.0);
      if (entity instanceof HUIHRVManager) {
         this.count2 = 1;
      }
   }
}
