package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P82ZeroYawDiffCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private static final String c = "rotated yaw too precise (0.0)";

   public Intave14P82ZeroYawDiffCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(82, c, AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTick() {
   }
}
