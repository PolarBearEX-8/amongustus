package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.EntityXporb;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb implements Packet {
   public int count2;
   public int count4;
   public int count5;
   public int count;
   public int count3;

   public int getCount() {
      return this.count;
   }

   public int getCount5() {
      return this.count5;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS11PacketSpawnExperienceOr(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.count4 = packet.readInt();
      this.count5 = packet.readInt();
      this.count = packet.readInt();
      this.count3 = packet.readShort();
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.writeInt(this.count4);
      packet.writeInt(this.count5);
      packet.writeInt(this.count);
      packet.writeShort(this.count3);
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount4() {
      return this.count4;
   }

   public S11PacketSpawnExperienceOrb(EntityXporb entityXporb) {
      this.count2 = entityXporb.getEntityId();
      this.count4 = MathHelper.ceiling_double_int(entityXporb.posX * 32.0);
      this.count5 = MathHelper.ceiling_double_int(entityXporb.posY * 32.0);
      this.count = MathHelper.ceiling_double_int(entityXporb.posZ * 32.0);
      this.count3 = entityXporb.getCount14();
   }

   public S11PacketSpawnExperienceOrb() {
   }
}
