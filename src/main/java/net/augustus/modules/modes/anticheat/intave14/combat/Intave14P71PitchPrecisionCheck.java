package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P71PitchPrecisionCheck extends ModuleMode {
   private AntiCheatCheckTracker antiCheatCheckTracker;
   private static final String c = "rotated pitch too precisely";

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14P71PitchPrecisionCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(71, c, AntiCheatCategory.KILLAURA);
   }
}
