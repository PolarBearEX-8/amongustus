package augustus.anticheat.checks;

import client.Client;
import client.event.EventPacket;
import client.util.ArrayListData;
import client.util.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class NoJumpDelayACheck extends AntiCheatCheck {
   public int count5;
   public int count3 = 0;
   public int count2;
   public int count4;
   public int count;
   public ArrayListData arrayListData;
   public boolean enabled = false;

   @Override
   public void updateState2() {
   }

   @Override
   public void onPacket(EventPacket packet) {
      if (packet.getPacket() instanceof S08PacketPlayerPosLook) {
         this.enabled = true;
      }
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C03PacketPlayer && !this.enabled) {
         Client.getClient().getClass();
      }

      if (packet instanceof C06PacketPlayerPosLook && !((C06PacketPlayerPosLook)packet).isOnGround()) {
         this.enabled = false;
      }
   }

   public NoJumpDelayACheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count2 = 0;
      this.count = 0;
      this.count5 = 0;
      this.count4 = 1;
      this.arrayListData = new ArrayListData(80);
   }
}
