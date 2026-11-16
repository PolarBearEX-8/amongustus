package augustus.anticheat.checks;

import client.util.DecimalFormatter;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.UseEntityAction;
import java.util.ArrayList;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class AutoClickerP6Check extends AntiCheatCheck {
   public double value;
   public final ArrayList arrayList;
   public int count3;
   public final ArrayList arrayList2;
   public int count;
   public final ArrayList arrayList3;
   public final int count2 = 20;

   @Override
   public void updateState2() {
   }

   @Override
   public void clear() {
      this.value = 0.0;
      this.arrayList2.clear();
      this.arrayList.clear();
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C0APacketAnimation && !MinecraftAccessor.minecraft.playerController.state) {
         this.count3++;
      }

      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
         this.count3 = Math.max(0, this.count3 - 1);
      }

      if (packet instanceof C03PacketPlayer) {
         this.count = this.count3 == 0 ? this.count + 1 : 0;
         if (this.count > 4) {
            return;
         }

         this.arrayList2.add(this.count3);

         while (this.arrayList2.size() > 20) {
            this.arrayList2.remove(0);
         }

         this.count3 = 0;
         if (this.arrayList2.size() == 20) {
            double coordinate = PlayerUtil.getCoordinate(this.arrayList2);
            double offset = PlayerUtil.computeValueByList(this.arrayList2);
            this.arrayList.add(coordinate);
            this.arrayList3.add(offset);
            this.arrayList2.clear();
         }

         if (this.arrayList.size() >= 7) {
            double offset2 = PlayerUtil.computeValue3(this.arrayList);
            if (offset2 < 0.008) {
               RayTraceUtil.applyString("AutoClickerP6 vl: " + this.value + " " + DecimalFormatter.formatDecimal(offset2, 3));
               this.value++;
            } else {
               this.value = Math.max(this.value - 0.05, 0.0);
            }

            this.arrayList.clear();
         }

         if (this.arrayList3.size() >= 7) {
            double offset3 = PlayerUtil.computeValue3(this.arrayList3);
            if (offset3 < 0.008) {
               RayTraceUtil.applyString("AutoClickerP6 vl: " + this.value + " " + DecimalFormatter.formatDecimal(offset3, 3));
               this.value++;
            } else {
               this.value = Math.max(this.value - 0.05, 0.0);
            }

            this.arrayList3.clear();
         }
      }
   }

   public AutoClickerP6Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.count3 = 0;
      this.value = 0.0;
      this.count = 0;
      this.arrayList2 = new ArrayList();
      this.arrayList = new ArrayList();
      this.arrayList3 = new ArrayList();
      super.state = true;
   }
}
