package net.augustus.modules.modes.anticheat.aim;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;

public class AimDistanceCheck extends ModuleMode {
   private int ticksSinceAttack = 0;
   private int violationCount;
   private final TimeHelper timeHelper;
   private final DoubleSampleWindow distanceSamples = new DoubleSampleWindow(25);
   private EntityLivingBase lastTarget;

   @Override
   public void onTick() {
   }

   public AimDistanceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.timeHelper = new TimeHelper();
      this.violationCount = 0;
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
