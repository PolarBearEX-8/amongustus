package net.augustus.modules.modes.anticheat.aim;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.ClientManagers;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;

public class AimP4ServerYawCheck extends ModuleMode {
   private float violationBuffer = 0.0F;
   private float lastYaw;
   private final ArrayList yawDiffHistory = new ArrayList();

   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook || packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
         if ((double)NumberFormatUtil.angleDistance(this.lastYaw, ClientManagers.rotationController.getPreviousRotation().yaw) > 0.5) {
            if (this.isMotionAboveThreshold(0.04)) {
               if (this.antiCheatPlayerData.getTargetEntity() != null) {
                  double d2 = 0.04;
                  if (this.isPlayerMovingFasterThan(this.antiCheatPlayerData, d2)) {
                     float f = NumberFormatUtil.angleDistance(this.antiCheatPlayerData.getServerYawToTarget(), ClientManagers.rotationController.getPreviousRotation().yaw);
                     this.yawDiffHistory.add(f);

                     while (this.yawDiffHistory.size() > 16) {
                        this.yawDiffHistory.remove(0);
                     }

                     double d3 = this.averageDoubles(this.yawDiffHistory);
                     if (f < 1.0F) {
                        this.violationBuffer++;
                        if (this.violationBuffer > 8.0F) {
                           this.sendAlert("Too precise Yaw (single)! YawDiff: " + f);
                           this.violationBuffer = 0.0F;
                        }
                     } else {
                        this.violationBuffer -= 0.5F;
                     }

                     if (this.yawDiffHistory.size() >= 15 && d3 < 1.5) {
                        this.violationBuffer++;
                        this.sendAlert("Too precise Yaw (avg)! YawDiff: " + d3);
                        this.yawDiffHistory.clear();
                        this.violationBuffer = 0.0F;
                     }
                  }
               }
            }
         }

         this.lastYaw = ClientManagers.rotationController.getPreviousRotation().yaw;
      }
   }

   public AimP4ServerYawCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastYaw = 0.0F;
      this.visibleInGui = true;
   }

   public void onTick() {
   }


}
