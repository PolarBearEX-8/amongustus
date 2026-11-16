package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.PacketBuffer;
import java.io.IOException;

public class C06PacketPlayerPosLook extends C03PacketPlayer {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.coordinate1 = packet.readDouble();
      this.coordinate = packet.readDouble();
      this.coordinate3 = packet.readDouble();
      this.yawRotation5 = packet.readFloat();
      this.yawRotation = packet.readFloat();
      super.processAction(packet);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      super.processAction((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeDouble(this.coordinate1);
      packet.writeDouble(this.coordinate);
      packet.writeDouble(this.coordinate3);
      packet.writeFloat(this.yawRotation5);
      packet.writeFloat(this.yawRotation);
      super.processAction2(packet);
   }

   public C06PacketPlayerPosLook(double posX, double posY, double posZ, float factor, float factor2, boolean flag) {
      this.coordinate1 = posX;
      this.coordinate = posY;
      this.coordinate3 = posZ;
      this.yawRotation5 = factor;
      this.yawRotation = factor2;
      this.onGround = flag;
      this.onGround4 = true;
      this.onGround2 = true;
   }

   public C06PacketPlayerPosLook() {
      this.onGround2 = true;
      this.onGround4 = true;
   }
}
