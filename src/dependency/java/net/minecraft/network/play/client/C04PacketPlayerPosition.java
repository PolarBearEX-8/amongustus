package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.PacketBuffer;
import java.io.IOException;

public class C04PacketPlayerPosition extends C03PacketPlayer {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.coordinate1 = packet.readDouble();
      this.coordinate = packet.readDouble();
      this.coordinate3 = packet.readDouble();
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
      super.processAction2(packet);
   }

   public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean flag) {
      this.coordinate1 = posX;
      this.coordinate = posY;
      this.coordinate3 = posZ;
      this.onGround = flag;
      this.onGround2 = true;
   }

   public C04PacketPlayerPosition() {
      this.onGround2 = true;
   }
}
