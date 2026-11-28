package net.augustus.modules.modes.anticheat.eagle;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class EagleCEdgeDeviationCheck extends ModuleMode {
   private final StatisticalSampleBuffer edgeOffsetBuffer = new StatisticalSampleBuffer(20);
   private double lastSneakPosX;
   private double lastSneakPosZ;
   private static final String b = "std: ";

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0BPacketEntityAction) {
         if (((C0BPacketEntityAction)packet).getAction() == C0BPacketEntityAction.Action.START_SNEAKING) {
            double d = Math.abs((double)((int)Math.round(mc.thePlayer.posX)) - mc.thePlayer.posX);
            double d2 = Math.abs((double)((int)Math.round(mc.thePlayer.posZ)) - mc.thePlayer.posZ);
            double d3 = Math.abs(mc.thePlayer.posX - this.lastSneakPosX);
            double d4 = Math.abs(mc.thePlayer.posZ - this.lastSneakPosZ);
            if (d3 > 0.9 && d4 < 0.2) {
               this.edgeOffsetBuffer.add(d);
            } else if (d4 > 0.9 && d3 < 0.2) {
               this.edgeOffsetBuffer.add(d2);
            }
            if (this.edgeOffsetBuffer.isFull()) {
               double d5 = this.edgeOffsetBuffer.stdDev();
               if (d5 < 0.015) {
                  this.sendAlert(b + d5);
                  this.edgeOffsetBuffer.clear();
               }
            }
            this.lastSneakPosX = mc.thePlayer.posX;
            this.lastSneakPosZ = mc.thePlayer.posZ;
         }
      }
   }

   public EagleCEdgeDeviationCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
   }

   @Override
   public void onTick() {
   }
}
