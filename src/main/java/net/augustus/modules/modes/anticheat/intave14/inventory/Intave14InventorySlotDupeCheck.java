package net.augustus.modules.modes.anticheat.intave14.inventory;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14InventorySlotDupeCheck extends ModuleMode {
   private int lastSlotId;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private int duplicateCount;

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14InventorySlotDupeCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastSlotId = 0;
      this.duplicateCount = 0;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(9669, "sent slot twice", AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }
}
