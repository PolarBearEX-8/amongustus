package net.augustus.modules.modes.anticheat.intave14.inventory;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P131FastCloseCheck extends ModuleMode {
   private int ticksSinceClick;
   private boolean clickedInInventory;
   private final AntiCheatCheckTracker antiCheatCheckTracker;

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14P131FastCloseCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(131, "closed inventory too quickly (0)", AntiCheatCategory.KILLAURA);
   }
}
