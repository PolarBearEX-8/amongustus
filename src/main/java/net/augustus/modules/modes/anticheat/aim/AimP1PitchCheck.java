package net.augustus.modules.modes.anticheat.aim;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.ClientManagers;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;

public class AimP1PitchCheck extends ModuleMode {
   private final ArrayList pitchDiffHistory;
   private float violationBuffer = 0.0F;
   private float lastPitch;

   public void onTick() {
   }

   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook || packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
         if ((double)NumberFormatUtil.angleDistance(this.lastPitch, ClientManagers.rotationController.getPreviousRotation().pitch) > 0.5) {
            if (this.isMotionAboveThreshold(0.04)) {
               if (this.antiCheatPlayerData.getTargetEntity() != null) {
                  double d2 = 0.04;
                  if (this.isPlayerMovingFasterThan(this.antiCheatPlayerData, d2)) {
                     float f = NumberFormatUtil.angleDistance(this.antiCheatPlayerData.getPitchToTarget(), ClientManagers.rotationController.getPreviousRotation().pitch);
                     this.pitchDiffHistory.add(f);

                     while (this.pitchDiffHistory.size() > 16) {
                        this.pitchDiffHistory.remove(0);
                     }

                     double d3 = this.averageDoubles(this.pitchDiffHistory);
                     if (f < 1.0F) {
                        this.violationBuffer++;
                        if (this.violationBuffer > 8.0F) {
                           this.sendAlert("Too precise Pitch (single)! PitchDiff: " + f);
                           this.violationBuffer = 0.0F;
                        }
                     } else {
                        this.violationBuffer -= 0.3F;
                     }

                     if (this.pitchDiffHistory.size() >= 15 && d3 < 1.5) {
                        this.violationBuffer++;
                        this.sendAlert("Too precise Pitch (avg)! PitchDiff: " + d3);
                        this.pitchDiffHistory.clear();
                        this.violationBuffer = 0.0F;
                     }
                  }
               }
            }
         }

         this.lastPitch = ClientManagers.rotationController.getPreviousRotation().pitch;
      }
   }

   public AimP1PitchCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.pitchDiffHistory = new ArrayList();
      this.lastPitch = 0.0F;
      this.visibleInGui = true;
   }


}
