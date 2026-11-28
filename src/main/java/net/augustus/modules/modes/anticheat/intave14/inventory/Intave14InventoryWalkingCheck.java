package net.augustus.modules.modes.anticheat.intave14.inventory;

import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14InventoryWalkingCheck extends ModuleMode {
   private int walkingClickViolations = 0;

   @Override
   public void onClientPacket(Packet packet) {
   }

   public Intave14InventoryWalkingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
   }

   @Override
   public void onTick() {
   }
}
