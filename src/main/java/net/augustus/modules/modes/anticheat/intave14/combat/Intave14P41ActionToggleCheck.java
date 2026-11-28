package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.HitbugFlagType;
import net.augustus.modules.modes.anticheat.FlagCertainty;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;

public class Intave14P41ActionToggleCheck extends ModuleMode {
   private boolean flagEnabled;
   private int sneakToggleCount;
   private int sprintToggleCount;
   private AntiCheatCheckTracker antiCheatCheckTracker;
   private static final String c = "sent too many sprint/sneak toggles";

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0BPacketEntityAction) {
         C0BPacketEntityAction.Action action = ((C0BPacketEntityAction)packet).getAction();
         boolean flag = action == C0BPacketEntityAction.Action.START_SPRINTING || action == C0BPacketEntityAction.Action.STOP_SPRINTING;
         boolean flag2 = action == C0BPacketEntityAction.Action.START_SNEAKING || action == C0BPacketEntityAction.Action.STOP_SNEAKING;
         if (flag || flag2) {
            if (mc.thePlayer.isSpectator()) {
               this.resetToggleCounts();
            } else {
               boolean flag3;
               if (flag) {
                  flag3 = this.sprintToggleCount++ > 0;
               } else {
                  flag3 = this.sneakToggleCount++ > 0;
               }
               if (flag3) {
                  if (this.flagEnabled) {
                     this.antiCheatCheckTracker.addViolation(1.0);
                     this.antiCheatCheckTracker.flag(FlagCertainty.PROBABLE);
                  }
                  if (flag) {
                     this.antiCheatCheckTracker.reportHitbug(HitbugFlagType.CANCEL);
                  }
               }
            }
         }
      }
      if (packet instanceof C03PacketPlayer || packet instanceof C0CPacketInput) {
         this.resetToggleCounts();
      }
   }

   public void resetToggleCounts() {
      this.sprintToggleCount = 0;
      this.sneakToggleCount = 0;
   }

   @Override
   public void onTick() {
   }

   public Intave14P41ActionToggleCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.flagEnabled = true;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(41, c, AntiCheatCategory.KILLAURA);
   }
}
