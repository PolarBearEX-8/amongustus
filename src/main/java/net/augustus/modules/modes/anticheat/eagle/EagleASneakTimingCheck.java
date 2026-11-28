package net.augustus.modules.modes.anticheat.eagle;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleASneakTimingCheck extends ModuleMode {
   private int zeroTickBuffer;
   private int lastPlaceTick = 0;
   private int tickCounter;
   private static final String b = "diff: ";

   @Override
   public void onTick() {
   }

   public EagleASneakTimingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.zeroTickBuffer = 0;
      this.tickCounter = 0;
   }

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
         if (((C0BPacketEntityAction)packet).getAction() == C0BPacketEntityAction.Action.START_SNEAKING) {
            int n = this.tickCounter - this.lastPlaceTick;
            if (n == 0) {
               if (++this.zeroTickBuffer >= 5) {
                  this.sendAlert(b + n);
                  this.zeroTickBuffer = 0;
               }
            } else {
               this.zeroTickBuffer = 0;
            }
         }
      }
   }
}
