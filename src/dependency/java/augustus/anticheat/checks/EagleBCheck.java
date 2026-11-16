package augustus.anticheat.checks;

import client.util.Packet;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleBCheck extends AntiCheatCheck {
   public int count3;
   public int count4;
   public int count2 = 0;
   public int count;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      long value = 1366079884L;
      char character = '\uaf6d';
      if (packet instanceof C03PacketPlayer) {
         this.count++;
      }

      if (packet instanceof C08PacketPlayerBlockPlacement) {
         if (((C08PacketPlayerBlockPlacement)packet).getEntityId() <= 6) {
            this.count2 = this.count;
         }
      } else if (packet instanceof C0BPacketEntityAction && ((C0BPacketEntityAction)packet).getEntityPlayerAction() == EntityPlayerAction.STOP_SNEAKING) {
         if (this.count - this.count2 == this.count3) {
            if (++this.count4 >= 20) {
               String text = "diff: " + this.count3;
               this.applyTextByText(text);
               this.count4 = 0;
            }
         } else {
            this.count4 = 0;
         }

         this.count3 = this.count - this.count2;
      }
   }

   public EagleBCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count3 = 0;
      this.count4 = 0;
      this.count = 0;
   }
}
