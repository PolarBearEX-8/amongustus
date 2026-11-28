package net.augustus.modules.modes.anticheat.intave14.scaffold;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14PostBlockPlaceCheck extends ModuleMode {
   private long lastPlaceTime;
   private List postDelaySamples;
   private long lastTickTime;
   private AntiCheatCheckTracker antiCheatCheckTracker;
   private int violationBuffer;
   private static final String c = "suspicious block-placement (Post Build)";

   public Intave14PostBlockPlaceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastTickTime = 0L;
      this.postDelaySamples = new ArrayList();
      this.lastPlaceTime = 0L;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(1337, c, AntiCheatCategory.SCAFFOLD);
   }

   private double calculateAverageLong(List list) {
      double d = 0.0;
      for (Object obj : list) {
         d += ((Long)obj).doubleValue();
      }
      if (d == 0.0) {
         return 0.0;
      }
      return d / (double)list.size();
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
