package net.augustus.modules.modes.anticheat.intave14.combat;

import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P91SuspiciousRotationCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private final List pitchDiffSamples;
   private final List yawDiffSamples;
   private static final String c = "rotated suspiciously";

   public Intave14P91SuspiciousRotationCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.yawDiffSamples = Lists.newArrayList();
      this.pitchDiffSamples = Lists.newArrayList();
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(91, c, AntiCheatCategory.KILLAURA);
   }

   private double calculateAverage(List list) {
      double d = 0.0;
      for (Object obj : list) {
         d += ((Double)obj).doubleValue();
      }
      if (d == 0.0) {
         return 0.0;
      }
      return d / (double)list.size();
   }

   @Override
   public void onTick() {
   }

   private double unboxDouble(Double d) {
      return d.doubleValue();
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
