package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.PacketBuffer;
import java.io.IOException;

public class C05PacketPlayerLook extends C03PacketPlayer {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
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
      packet.writeFloat(this.yawRotation5);
      packet.writeFloat(this.yawRotation);
      super.processAction2(packet);
   }

   public C05PacketPlayerLook(float factor, float factor2, boolean flag) {
      this.yawRotation5 = factor;
      this.yawRotation = factor2;
      this.onGround = flag;
      this.onGround4 = true;
   }

   public C05PacketPlayerLook() {
      this.onGround4 = true;
   }
}
