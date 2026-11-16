package net.minecraft.network.play.server;

import client.util.DataWatcher;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class S0CPacketSpawnPlayer implements Packet {
   public int count4;
   public UUID uUID;
   public int count;
   public int count2;
   public int count3;
   public byte byteFlag;
   public byte byteFlag2;
   public int count5;
   public DataWatcher dataWatcher;
   public List list;

   public UUID getUUID() {
      return this.uUID;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount3() {
      return this.count3;
   }

   public byte getByteFlag2() {
      return this.byteFlag2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count4 = packet.getInt3();
      this.uUID = packet.getUUID();
      this.count = packet.readInt();
      this.count2 = packet.readInt();
      this.count3 = packet.readInt();
      this.byteFlag = packet.readByte();
      this.byteFlag2 = packet.readByte();
      this.count5 = packet.readShort();
      this.list = DataWatcher.resolveList(packet);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   public List getList() {
      if (this.list == null) {
         this.list = this.dataWatcher.getList();
      }

      return this.list;
   }

   public int getCount5() {
      return this.count5;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count4);
      packet.applyUUID(this.uUID);
      packet.writeInt(this.count);
      packet.writeInt(this.count2);
      packet.writeInt(this.count3);
      packet.writeByte(this.byteFlag);
      packet.writeByte(this.byteFlag2);
      packet.writeShort(this.count5);
      this.dataWatcher.applyPacketBuffer(packet);
   }

   public int getCount2() {
      return this.count2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0CPacketSpawnPlayer(this);
   }

   public int getCount4() {
      return this.count4;
   }

   public S0CPacketSpawnPlayer(EntityPlayer player) {
      this.count4 = player.getEntityId();
      this.uUID = player.getGameProfile().getId();
      this.count = MathHelper.ceiling_double_int(player.posX * 32.0);
      this.count2 = MathHelper.ceiling_double_int(player.posY * 32.0);
      this.count3 = MathHelper.ceiling_double_int(player.posZ * 32.0);
      this.byteFlag = (byte)((int)(player.rotationYaw * 256.0F / 360.0F));
      this.byteFlag2 = (byte)((int)(player.rotationPitch * 256.0F / 360.0F));
      ItemStack slot = player.inventoryPlayer.getCurrentItem();
      this.count5 = slot == null ? 0 : Item.getIndex(slot.getItem());
      this.dataWatcher = player.getDataWatcher();
   }

   public S0CPacketSpawnPlayer() {
   }
}
