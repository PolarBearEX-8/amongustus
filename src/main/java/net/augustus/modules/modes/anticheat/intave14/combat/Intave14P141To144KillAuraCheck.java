package net.augustus.modules.modes.anticheat.intave14.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;

public class Intave14P141To144KillAuraCheck extends ModuleMode {
   private final AntiCheatCheckTracker check142Tracker;
   private int hitCount;
   private int swingCount;
   private int tickCounter;
   private int consecutiveHits;
   private int missedHits;
   private boolean attackedThisTick;
   private final AntiCheatCheckTracker check144Tracker;
   private final AntiCheatCheckTracker check143Tracker;
   private boolean swungThisTick;
   private final AntiCheatCheckTracker check141Tracker = new AntiCheatCheckTracker(141, "", AntiCheatCategory.KILLAURA);

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTick() {
   }

   public Intave14P141To144KillAuraCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.check142Tracker = new AntiCheatCheckTracker(142, "", AntiCheatCategory.KILLAURA);
      this.check143Tracker = new AntiCheatCheckTracker(143, "", AntiCheatCategory.KILLAURA);
      this.check144Tracker = new AntiCheatCheckTracker(144, "", AntiCheatCategory.KILLAURA);
   }
}
