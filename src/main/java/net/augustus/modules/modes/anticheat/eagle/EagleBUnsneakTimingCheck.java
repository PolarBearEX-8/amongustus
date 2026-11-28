package net.augustus.modules.modes.anticheat.eagle;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleBUnsneakTimingCheck extends ModuleMode {
   private int repeatedDiffBuffer;
   private int lastUnsneakDiff;
   private int lastPlaceTick = 0;
   private int tickCounter;
   private static final String b = "diff: ";

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         ++this.tickCounter;
      }
      if (packet instanceof C08PacketPlayerBlockPlacement) {
         if (((C08PacketPlayerBlockPlacement)packet).getPlacedBlockDirection() <= 6) {
            this.lastPlaceTick = this.tickCounter;
         }
      } else if (packet instanceof C0BPacketEntityAction) {
         if (((C0BPacketEntityAction)packet).getAction() == C0BPacketEntityAction.Action.STOP_SNEAKING) {
            if (this.tickCounter - this.lastPlaceTick == this.lastUnsneakDiff) {
               if (++this.repeatedDiffBuffer >= 20) {
                  this.sendAlert(b + this.lastUnsneakDiff);
                  this.repeatedDiffBuffer = 0;
               }
            } else {
               this.repeatedDiffBuffer = 0;
            }
            this.lastUnsneakDiff = this.tickCounter - this.lastPlaceTick;
         }
      }
   }

   @Override
   public void onTick() {
   }

   public EagleBUnsneakTimingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastUnsneakDiff = 0;
      this.repeatedDiffBuffer = 0;
      this.tickCounter = 0;
   }
}
