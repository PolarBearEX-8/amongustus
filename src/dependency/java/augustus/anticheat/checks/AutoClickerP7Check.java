package augustus.anticheat.checks;

import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.util.ArrayList;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoClickerP7Check extends AntiCheatCheck {
   public double value = 0.0;
   public final ArrayList arrayList = new ArrayList();
   public final TimeHelper timer = new TimeHelper();
   public int count;

   @Override
   public void updateState2() {
   }

   @Override
   public void clear() {
      this.arrayList.clear();
      this.value = 0.0;
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         if (this.isActive()) {
            this.arrayList.add(this.count);

            while (this.arrayList.size() > 20) {
               this.arrayList.remove(0);
            }

            if (this.arrayList.size() == 20) {
               double coordinate = PlayerUtil.getCoordinate(this.arrayList);
               double offset = PlayerUtil.computeValueByList(this.arrayList);
               int count2 = PlayerUtil.computeCountByList(this.arrayList);
               int count3 = PlayerUtil.computeCount(this.arrayList);
               if (coordinate > 0.1 && offset < 0.35 || count2 > 0 || count3 < 2 && coordinate > 0.7) {
                  this.value++;
                  if (offset == 0.0) {
                     this.value++;
                  }

                  if (count2 > 0) {
                     this.value += 2.0;
                  }

                  RayTraceUtil.applyString("AutoClickerP7 vl: " + this.value + " " + offset);
                  this.arrayList.clear();
               } else {
                  this.value = Math.max(this.value - 0.001, 0.0);
               }

               if (this.value > 10.0) {
                  this.value = 0.0;
                  RayTraceUtil.applyString("Banned I think! Reset Violations");
               }
            }
         }

         this.count = 0;
      }

      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
         this.count++;
      }
   }

   public AutoClickerP7Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      super.state = true;
   }
}
