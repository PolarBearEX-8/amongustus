package augustus.anticheat.checks;

import client.util.Packet;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleACheck extends AntiCheatCheck {
   public int count3;
   public int count2 = 0;
   public int count = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      long value = 1366079884L;
      char character = '\uaf6d';
      if (packet instanceof C03PacketPlayer) {
         this.count3++;
      }

      if (packet instanceof C08PacketPlayerBlockPlacement) {
         if (((C08PacketPlayerBlockPlacement)packet).getEntityId() <= 6) {
            this.count2 = this.count3;
         }
      } else if (packet instanceof C0BPacketEntityAction && ((C0BPacketEntityAction)packet).getEntityPlayerAction() == EntityPlayerAction.START_SNEAKING) {
         int count4 = this.count3 - this.count2;
         if (count4 == 0) {
            if (++this.count >= 5) {
               String text = "diff: " + count4;
               this.applyTextByText(text);
               this.count = 0;
            }
         } else {
            this.count = 0;
         }
      }
   }

   public EagleACheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count3 = 0;
   }
}
