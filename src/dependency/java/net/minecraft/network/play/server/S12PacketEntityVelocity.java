package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;

public class S12PacketEntityVelocity implements Packet {
   public int entityId;
   public int entityId3;
   public int entityId2;
   public int entityId4;
   public boolean onGround1 = true;
   public boolean onGround = true;

   public void setEntityId4(int entityId5) {
      this.entityId4 = entityId5;
   }

   public int getEntityId2() {
      return this.entityId2;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public void processAction(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.handleS12PacketEntityVelocity(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.entityId = packet.getInt3();
      this.entityId3 = packet.readShort();
      this.entityId2 = packet.readShort();
      this.entityId4 = packet.readShort();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayClient)iNetHandler);
   }

   public boolean isOnGround1() {
      return this.onGround1;
   }

   public void setEntityId2(int entityId5) {
      this.entityId2 = entityId5;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.entityId);
      packet.writeShort(this.entityId3);
      packet.writeShort(this.entityId2);
      packet.writeShort(this.entityId4);
   }

   public int getEntityId3() {
      return this.entityId3;
   }

   public void setOnGround1(boolean onGround2) {
      this.onGround1 = onGround2;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public void setEntityId3(int entityId5) {
      this.entityId3 = entityId5;
   }

   public void setOnGround(boolean onGround2) {
      this.onGround = onGround2;
   }

   public int getEntityId4() {
      return this.entityId4;
   }

   public S12PacketEntityVelocity(Entity entity) {
      this(entity.getEntityId(), entity.motionX, entity.motionY, entity.motionZ);
   }

   public S12PacketEntityVelocity() {
   }

   public S12PacketEntityVelocity(int count, double posX, double posY, double posZ) {
      this.entityId = count;
      double offset = 3.9;
      if (posX < -offset) {
         posX = -offset;
      }

      if (posY < -offset) {
         posY = -offset;
      }

      if (posZ < -offset) {
         posZ = -offset;
      }

      if (posX > offset) {
         posX = offset;
      }

      if (posY > offset) {
         posY = offset;
      }

      if (posZ > offset) {
         posZ = offset;
      }

      this.entityId3 = (int)(posX * 8000.0);
      this.entityId2 = (int)(posY * 8000.0);
      this.entityId4 = (int)(posZ * 8000.0);
   }
}
