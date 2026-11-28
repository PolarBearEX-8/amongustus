package net.augustus.modules.modes.anticheat.intave14.combat;

import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P92PreciseYawCheck extends ModuleMode {
   private final List yawDiffSamples;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private final List deltaYawSamples;

   private double unboxDouble(Double d) {
      return d.doubleValue();
   }

   public Intave14P92PreciseYawCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.yawDiffSamples = Lists.newArrayList();
      this.deltaYawSamples = Lists.newArrayList();
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(92, "precise rotation yaw", AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }

   private double calculateAverage(List list) {
      double d = 0.0;

      for (Object obj : list) {
         d += ((Double)obj).doubleValue();
      }

      return d == 0.0 ? 0.0 : d / (double)list.size();
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
