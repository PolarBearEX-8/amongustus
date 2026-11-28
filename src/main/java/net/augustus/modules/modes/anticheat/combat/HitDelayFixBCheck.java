package net.augustus.modules.modes.anticheat.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixBCheck extends ModuleMode {
   private final DoubleSampleWindow shortIntervalWindow;
   private final DoubleSampleWindow longIntervalWindow;
   private final DoubleSampleWindow secondaryMediumWindow;
   private int hitCount;
   private int tickCounter;
   private final DoubleSampleWindow mediumIntervalWindow;
   private int swingsThisTick = 0;

   public HitDelayFixBCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.hitCount = 0;
      this.tickCounter = 0;
      this.shortIntervalWindow = new DoubleSampleWindow(5);
      this.mediumIntervalWindow = new DoubleSampleWindow(20);
      this.longIntervalWindow = new DoubleSampleWindow(35);
      this.secondaryMediumWindow = new DoubleSampleWindow(20);
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0APacketAnimation) {
         this.swingsThisTick++;
      }
   }

   @Override
   public void onTick() {
   }
}
