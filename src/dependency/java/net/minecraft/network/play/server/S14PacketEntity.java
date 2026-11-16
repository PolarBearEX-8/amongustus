package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class S14PacketEntity implements Packet {
   public int count;
   public byte byteFlag;
   public byte byteFlag3;
   public byte byteFlag4;
   public byte byteFlag2;
   public byte byteFlag5;
   public boolean enabled2;
   public boolean enabled;

   public void setCount(int count2) {
      this.count = count2;
   }

   public Entity resolveEntity(World world) {
      return world.getEntityByID(this.count);
   }

   public byte getByteFlag5() {
      return this.byteFlag5;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
   }

   public void setByteFlag3(int byteFlag6) {
      this.byteFlag3 = (byte)byteFlag6;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public byte getByteFlag3() {
      return this.byteFlag3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
   }

   public void setByteFlag(int byteFlag6) {
      this.byteFlag = (byte)byteFlag6;
   }

   public int getIndex() {
      return this.count;
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   public byte getByteFlag2() {
      return this.byteFlag2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public byte getByteFlag4() {
      return this.byteFlag4;
   }

   public void setByteFlag4(int byteFlag6) {
      this.byteFlag4 = (byte)byteFlag6;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS14PacketEntity(this);
   }

   public S14PacketEntity(int count2) {
      this.count = count2;
   }

   public S14PacketEntity() {
   }

   @Override
   public String toString() {
      return "Entity_" + super.toString();
   }
}
