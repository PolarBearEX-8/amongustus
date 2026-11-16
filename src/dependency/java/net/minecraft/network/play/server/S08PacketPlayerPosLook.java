package net.minecraft.network.play.server;

import client.setting.NetHandlerPlayClientXType;
import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.Set;

public class S08PacketPlayerPosLook implements Packet {
   public double coordinate;
   public double coordinate1;
   public double coordinate4;
   public float yawRotation2;
   public float yawRotation3;
   public float yawRotation = 0.0F;
   public Set entrySet;

   public double getCoordinate4() {
      return this.coordinate4;
   }

   public Set getEntrySet() {
      return this.entrySet;
   }

   public void processAction(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.processAction(this);
   }

   public float getYawRotation() {
      return this.yawRotation;
   }

   public float getYawRotation3() {
      return this.yawRotation3;
   }

   public void setYawRotation(float yawRotation4) {
      this.yawRotation = yawRotation4;
   }

   public float getYawRotation2() {
      return this.yawRotation2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.coordinate = packet.readDouble();
      this.coordinate1 = packet.readDouble();
      this.coordinate4 = packet.readDouble();
      this.yawRotation2 = packet.readFloat();
      this.yawRotation3 = packet.readFloat();
      this.entrySet = NetHandlerPlayClientXType.getSet(packet.readUnsignedByte());
   }

   public double getCoordinate() {
      return this.coordinate;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayClient)iNetHandler);
   }

   public void setYawRotation3(float yawRotation4) {
      this.yawRotation3 = yawRotation4;
   }

   public double getCoordinate1() {
      return this.coordinate1;
   }

   public void setYawRotation2(float yawRotation4) {
      this.yawRotation2 = yawRotation4;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeDouble(this.coordinate);
      packet.writeDouble(this.coordinate1);
      packet.writeDouble(this.coordinate4);
      packet.writeFloat(this.yawRotation2);
      packet.writeFloat(this.yawRotation3);
      packet.writeByte(NetHandlerPlayClientXType.getIndex(this.entrySet));
   }

   public void setCoordinate(double coordinate2) {
      this.coordinate = coordinate2;
   }

   public void setCoordinate1(double coordinate2) {
      this.coordinate1 = coordinate2;
   }

   public void setCoordinate4(double coordinate2) {
      this.coordinate4 = coordinate2;
   }

   public S08PacketPlayerPosLook(double posX, double posY, double posZ, float factor, float factor2, Set set) {
      this.coordinate = posX;
      this.coordinate1 = posY;
      this.coordinate4 = posZ;
      this.yawRotation2 = factor;
      this.yawRotation3 = factor2;
      this.entrySet = set;
   }

   public S08PacketPlayerPosLook() {
   }
}
