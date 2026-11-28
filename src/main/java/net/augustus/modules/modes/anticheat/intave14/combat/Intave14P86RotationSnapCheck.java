package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P86RotationSnapCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private static final String c = "suspicious rotation snap";

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14P86RotationSnapCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(86, c, AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }
}
