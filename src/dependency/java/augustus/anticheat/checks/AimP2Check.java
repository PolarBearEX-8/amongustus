package augustus.anticheat.checks;

import client.ClientGlobals;
import client.util.DecimalFormatter;
import client.util.Packet;
import java.util.ArrayList;
import net.minecraft.network.play.client.C05PacketPlayerLook;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class AimP2Check extends AntiCheatCheck {
   public float factor = 0.0F;
   public float factor2;
   public final ArrayList arrayList = new ArrayList();

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      long value = 1366079884L;
      char character = '\uaf6d';
      if (packet instanceof C06PacketPlayerPosLook || packet instanceof C05PacketPlayerLook) {
         if ((double)DecimalFormatter.abs(ClientGlobals.rotationManager.getRotation3().yaw, this.factor2) > 0.5) {
            double offset = 0.04;
            if (this.checkDouble(offset) && super.antiCheatManager.getEntityLivingBase() != null) {
               double offset2 = 0.04;
               AntiCheatManager antiCheatManager = super.antiCheatManager;
               if (this.matchesAntiCheatManager(antiCheatManager, offset2)) {
                  float factor3 = DecimalFormatter.abs(super.antiCheatManager.getFactor4(), ClientGlobals.rotationManager.getRotation3().yaw);
                  this.arrayList.add(factor3);

                  while (this.arrayList.size() > 16) {
                     this.arrayList.remove(0);
                  }

                  ArrayList arrayList2 = this.arrayList;
                  double offset3 = this.computeValue2(arrayList2);
                  if (factor3 < 1.0F) {
                     this.factor++;
                     if (this.factor > 8.0F) {
                        String text = "Too precise Yaw (single)! YawDiff: " + factor3;
                        this.applyTextByText(text);
                        this.factor = 0.0F;
                     }
                  } else {
                     this.factor -= 0.3F;
                  }

                  if (this.arrayList.size() >= 15 && offset3 < 1.5) {
                     this.factor++;
                     String text2 = "Too precise Yaw (avg)! YawDiff: " + offset3;
                     this.applyTextByText(text2);
                     this.arrayList.clear();
                     this.factor = 0.0F;
                  }
               }
            }
         }

         this.factor2 = ClientGlobals.rotationManager.getRotation3().yaw;
      }
   }

   public AimP2Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.factor2 = 0.0F;
      super.state = true;
   }
}
