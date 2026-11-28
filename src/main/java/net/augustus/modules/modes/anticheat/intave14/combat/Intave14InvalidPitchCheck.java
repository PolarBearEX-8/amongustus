package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14InvalidPitchCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private static final String c = "sent invalid rotation";

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTick() {
   }

   public Intave14InvalidPitchCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(9669, c, AntiCheatCategory.KILLAURA);
   }
}
