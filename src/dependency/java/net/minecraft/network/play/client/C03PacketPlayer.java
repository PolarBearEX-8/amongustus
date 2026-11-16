package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C03PacketPlayer implements Packet {
   public double coordinate1;
   public double coordinate;
   public double coordinate3;
   public float yawRotation5;
   public float yawRotation;
   public boolean onGround;
   public boolean onGround2;
   public boolean onGround4;

   public boolean isOnGround() {
      return this.onGround;
   }

   public void setCoordinate3(double coordinate2) {
      this.coordinate3 = coordinate2;
   }

   public boolean isOnGround2() {
      return this.onGround2;
   }

   public double getCoordinate3() {
      return this.coordinate3;
   }

   public double getCoordinate() {
      return this.coordinate;
   }

   public float getYawRotation() {
      return this.yawRotation;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.onGround = packet.readUnsignedByte() != 0;
   }

   public void setOnGround4(boolean onGround3) {
      this.onGround4 = onGround3;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayServer)iNetHandler);
   }

   public void setOnGround2(boolean onGround3) {
      this.onGround2 = onGround3;
   }

   public void setCoordinate1(double coordinate2) {
      this.coordinate1 = coordinate2;
   }

   public float getYawRotation5() {
      return this.yawRotation5;
   }

   public void processAction(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.handleC03PacketPlayer(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(((this.onGround) ? 1 : 0));
   }

   public void setOnGround(boolean onGround3) {
      this.onGround = onGround3;
   }

   public boolean isOnGround4() {
      return this.onGround4;
   }

   public void setCoordinate(double coordinate2) {
      this.coordinate = coordinate2;
   }

   public double getCoordinate1() {
      return this.coordinate1;
   }

   public C03PacketPlayer() {
   }

   public C03PacketPlayer(boolean flag) {
      this.onGround = flag;
   }
}
