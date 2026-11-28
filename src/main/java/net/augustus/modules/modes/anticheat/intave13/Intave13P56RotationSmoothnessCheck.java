package net.augustus.modules.modes.anticheat.intave13;

import net.minecraft.network.play.client.C03PacketPlayer;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.modules.ModuleMode;
import net.augustus.ClientManagers;
import net.augustus.utils.ChatUtil;
import net.augustus.settings.Setting;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class Intave13P56RotationSmoothnessCheck extends ModuleMode {
   private int violationLevel;
   private final TimeHelper timeHelper;

   public Intave13P56RotationSmoothnessCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.timeHelper = new TimeHelper();
      this.violationLevel = 0;
      this.visibleInGui = true;
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook || packet instanceof C03PacketPlayer.C05PacketPlayerLook) {
         float rotationYaw = mc.thePlayer.rotationYaw;
         float rotationPitch = mc.thePlayer.rotationPitch;
         float yaw = ClientManagers.rotationController.getPreviousRotation().yaw;
         float pitch = ClientManagers.rotationController.getPreviousRotation().pitch;
         float f = Math.abs(yaw - rotationYaw);
         float f2 = Math.abs(pitch - rotationPitch);
         double d = Math.abs((double)f);
         double d2 = Math.abs((double)f2);
         long time = this.timeHelper.getTime();
         double d3 = d + d2;
         if (d3 > 8.0 && time < 4000L) {
            this.violationLevel++;
            if (d3 > 30.0 && time < 250L) {
               this.violationLevel++;
               if (d3 > 60.0) {
                  this.violationLevel++;
               }
            }
         } else if (this.violationLevel > 0) {
            if (d3 < 3.0 || time > 5000L) {
               this.violationLevel = 0;
            } else {
               this.violationLevel--;
            }
         }
      }

      if (packet instanceof C02PacketUseEntity) {
         this.timeHelper.reset();
      }
   }

   @Override
   public void onTick() {
      if (this.violationLevel > 12) {
         ChatUtil.sendChat("P56: Hits get canceled. Level: " + this.violationLevel);
         if (this.violationLevel > 50) {
            if (this.violationLevel >= 100 && (!ThreadLocalRandom.current().nextBoolean() || !ThreadLocalRandom.current().nextBoolean())) {
               return;
            }

            ChatUtil.sendChat("P56 (Intave13 Debug) " + mc.thePlayer.getName() + " is rotating suspiciously unsmooth | vl: " + this.violationLevel);
         }
      }
   }

}
