package net.augustus.modules.modes.anticheat.intave13;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.utils.ChatUtil;
import net.augustus.settings.Setting;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class Intave13P19AttackTimingCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private long lastPosLookTime;

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) {
         this.lastPosLookTime = System.currentTimeMillis();
      }

      if (packet instanceof C02PacketUseEntity) {
         long l = System.currentTimeMillis() - this.lastPosLookTime;
         if (l <= 22L && l != 0L && l >= 0L) {
            this.antiCheatCheckTracker.addViolation(1.0);
            int violationLevel = (int)this.antiCheatCheckTracker.getViolationLevel();
            if (this.antiCheatCheckTracker.getViolationLevel() > 10.0) {
               this.antiCheatCheckTracker.addViolation(-(this.antiCheatCheckTracker.getViolationLevel() - 10.0));
            }

            if (violationLevel >= 6) {
               ChatUtil.sendChat("P19 (Intave13 Debug) " + mc.thePlayer.getName() + " l: " + l + " vl: " + violationLevel);
               if (violationLevel >= 9) {
                  ChatUtil.sendChat("P19: Hits get canceled. Level: " + this.antiCheatCheckTracker.getViolationLevel());
               }
            }
         } else if (this.antiCheatCheckTracker.getViolationLevel() > 0.0) {
            this.antiCheatCheckTracker.addViolation(-1.0);
         }
      }
   }

   public Intave13P19AttackTimingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastPosLookTime = 0L;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(19, "Attacks to fast after tick", AntiCheatCategory.KILLAURA);
      this.visibleInGui = true;
   }

   @Override
   public void onTick() {
   }
}
