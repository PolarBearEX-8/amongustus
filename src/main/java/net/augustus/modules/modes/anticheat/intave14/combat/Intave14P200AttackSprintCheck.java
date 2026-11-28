package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P200AttackSprintCheck extends ModuleMode {
   private int attackSprintAlignCount;
   private boolean sprintedOnAttack;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private int ticksSinceAttack;

   public Intave14P200AttackSprintCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.ticksSinceAttack = 0;
      this.sprintedOnAttack = false;
      this.attackSprintAlignCount = 0;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(200, "sprint-toggles aligned with attacks", AntiCheatCategory.KILLAURA);
   }

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
