package net.augustus.modules.modes.anticheat.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;

public class HitDelayFixCCheck extends ModuleMode {
   private int swingsThisTick = 0;
   private final DoubleSampleWindow primaryLongWindow;
   private int tickCounter = 0;
   private final DoubleSampleWindow shortWindow;
   private int consecutiveHits = 0;
   private final DoubleSampleWindow microWindow = new DoubleSampleWindow(2);
   private final DoubleSampleWindow secondaryLongWindow;

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0APacketAnimation) {
         this.swingsThisTick++;
      }
   }

   public HitDelayFixCCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.shortWindow = new DoubleSampleWindow(5);
      this.primaryLongWindow = new DoubleSampleWindow(30);
      this.secondaryLongWindow = new DoubleSampleWindow(30);
   }

   @Override
   public void onTick() {
   }
}
