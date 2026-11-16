package augustus.anticheat.checks;

import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.UseEntityAction;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class AutoClickerP5Check extends AntiCheatCheck {
   public int count2 = 0;
   public int count;
   public int count3 = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void clear() {
      this.count = 0;
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
         this.count3++;
      }

      if (packet instanceof C0APacketAnimation) {
         this.count2++;
      }

      if (packet instanceof C03PacketPlayer) {
         if (MinecraftAccessor.minecraft.playerController.processAction()
            && (
               this.count3 > 0 && this.count2 > this.count3
                  || this.count3 > this.count2
                  || this.count3 == 0 && this.count2 > 1 && !MinecraftAccessor.minecraft.playerController.state
            )) {
            this.count++;
            RayTraceUtil.applyString("AutoClickerP5 lv: " + this.count);
         }

         this.count3 = 0;
         this.count2 = 0;
      }
   }

   public AutoClickerP5Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count = 0;
      super.state = true;
   }
}
