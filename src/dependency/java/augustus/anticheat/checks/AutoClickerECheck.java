package augustus.anticheat.checks;

import client.util.Packet;
import client.util.PlayerDiggingActionType;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class AutoClickerECheck extends AntiCheatCheck {
   public final ArrayList arrayList;
   public int count = 0;

   @Override
   public void updateState2() {
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         this.count++;
      }

      if (packet instanceof C07PacketPlayerDigging) {
         C07PacketPlayerDigging packet2 = (C07PacketPlayerDigging)packet;
         if (packet2.getPlayerDiggingActionType() == PlayerDiggingActionType.START_DESTROY_BLOCK) {
            this.count = 0;
         } else if (packet2.getPlayerDiggingActionType() == PlayerDiggingActionType.ABORT_DESTROY_BLOCK) {
            BlockPos blockPos = packet2.getBlockPos();
            EnumFacing enumFacing = packet2.getEnumFacing();
            if (minecraft.objectMouseOver != null && blockPos.equals(minecraft.objectMouseOver.getBlockPos()) && !enumFacing.equals(minecraft.objectMouseOver.sideHit)) {
               this.arrayList.add(this.count);
               if (this.arrayList.size() > 40) {
                  double offset = PlayerUtil.computeValueByList(this.arrayList);
                  System.out.println(offset);
                  if (offset < 0.325) {
                     RayTraceUtil.applyString("AutoClickerE: " + String.format("D: %s", offset) + "  " + (1.325 - offset));
                     RayTraceUtil.applyString(Arrays.toString(this.arrayList.toArray()));
                  }

                  this.arrayList.clear();
               }
            }

            this.count = 0;
         }
      }
   }

   public AutoClickerECheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.arrayList = new ArrayList();
   }
}
