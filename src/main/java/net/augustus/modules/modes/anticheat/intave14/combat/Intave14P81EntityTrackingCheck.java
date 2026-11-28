package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P81EntityTrackingCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14P81EntityTrackingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(81, "follows entity movement too precisely", AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }
}
