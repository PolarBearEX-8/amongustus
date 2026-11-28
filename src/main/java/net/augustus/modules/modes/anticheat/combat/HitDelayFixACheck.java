package net.augustus.modules.modes.anticheat.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixACheck extends ModuleMode {
   private int swingsThisTick = 0;
   private int ticksSinceSwing;
   private final DoubleSampleWindow hitDelaySamples;
   private int hitDelayTicks = 0;
   private double violationLevel;

   @Override
   public void onTick() {
   }

   public HitDelayFixACheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.ticksSinceSwing = 0;
      this.violationLevel = 0.0;
      this.hitDelaySamples = new DoubleSampleWindow(15);
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0APacketAnimation) {
         this.swingsThisTick++;
      }
   }
}
