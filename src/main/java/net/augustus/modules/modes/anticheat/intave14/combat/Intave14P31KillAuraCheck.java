package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P31KillAuraCheck extends ModuleMode {
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private boolean attackedThisTick = false;

   public Intave14P31KillAuraCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(31, "", AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
