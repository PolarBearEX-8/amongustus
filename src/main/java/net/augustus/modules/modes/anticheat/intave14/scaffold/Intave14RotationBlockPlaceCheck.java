package net.augustus.modules.modes.anticheat.intave14.scaffold;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;

public class Intave14RotationBlockPlaceCheck extends ModuleMode {
   private long lastPlaceTime;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private final List recentPlacedBlocks;
   private long lastRotationTime;
   private final List rotationActivitySamples;
   private static final String c = "high rotation activity while placing blocks";

   @Override
   public void onTick() {
   }

   public Intave14RotationBlockPlaceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.lastPlaceTime = 0L;
      this.lastRotationTime = 0L;
      this.rotationActivitySamples = new ArrayList();
      this.recentPlacedBlocks = new ArrayList();
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(1337, c, AntiCheatCategory.SCAFFOLD);
   }

   private boolean containsBlockPos(BlockPos pos) {
      for (Object obj : this.recentPlacedBlocks) {
         if (Math.sqrt(((BlockPos)obj).distanceSq((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())) == 0.0) {
            return true;
         }
      }
      return false;
   }

   @Override
   public void onClientPacket(Packet packet) {
   }
}
