package net.augustus.modules.modes.anticheat.intave14.scaffold;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14InvalidBlockPlaceCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14InvalidBlockPlaceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(1337, "suspicious block-placement", AntiCheatCategory.SCAFFOLD);
   }
}
