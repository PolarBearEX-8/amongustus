package net.augustus.modules.modes.anticheat.intave14.scaffold;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.utils.ChatUtil;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.block.material.Material;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class Intave14TimeBlockPlaceCheck extends ModuleMode {
   private final boolean strictMode;
   private long lastSneakTime;
   private long lastJumpTime;
   private int placeCount;
   private final boolean debugSneakTicks;
   private long lastPlaceTime;
   private final List placeIntervalHistory;
   private int sneakTicks;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private long lastSprintTime;
   private final List placedBlockPositions;

   private boolean isBridgingStraight(List list) {
      boolean flag = false;
      boolean flag2 = false;
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      boolean flag3 = false;
      for (Object obj : list) {
         BlockPos pos = (BlockPos)obj;
         if (flag3) {
            if (n2 != pos.getY()) {
               return false;
            }
            if (n == pos.getX()) {
               flag = true;
            } else if (flag) {
               return false;
            }
            if (n3 == pos.getZ()) {
               flag2 = true;
            } else if (flag2) {
               return false;
            }
         }
         n = pos.getX();
         n2 = pos.getY();
         n3 = pos.getZ();
         flag3 = true;
      }
      return flag || flag2;
   }

   @Override
   public void onTick() {
   }

   private boolean isBlockDirectlyBelowPlayer(BlockPos pos) {
      return new Vec3((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()).addVector(0.0, 1.0, 0.0).distanceTo(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ) < 1.3;
   }

   public Intave14TimeBlockPlaceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2, boolean flag) {
      super(str, antiCheatPlayerData2);
      this.placeIntervalHistory = new ArrayList();
      this.sneakTicks = 0;
      this.lastPlaceTime = 0L;
      this.placedBlockPositions = new ArrayList();
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(1337, "suspicious block-placement", AntiCheatCategory.SCAFFOLD);
      this.strictMode = false;
      this.debugSneakTicks = flag;
   }

   @Override
   public void onMoveUpdate() {
      this.lastJumpTime = System.currentTimeMillis();
   }

   private static double toDouble(Long val) {
      return (double)val.longValue();
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   public double computePlaceTimeDeficit(BlockPos pos, BlockPos pos2) {
      if (!this.isBlockDirectlyBelowPlayer(pos)) {
         return 0.0;
      }
      if (this.countAdjacentSolidBlocks(pos) >= 2) {
         return 0.0;
      }
      if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
         return 0.0;
      }
      ArrayList<Long> list = new ArrayList<Long>(this.placeIntervalHistory);
      if (list.size() >= 8) {
         list.remove(0);
      }
      if (pos.getY() != pos2.getY()) {
         list.add(System.currentTimeMillis() - this.lastPlaceTime + 1000L);
      } else {
         list.add(System.currentTimeMillis() - this.lastPlaceTime);
      }
      if (list.size() < 8) {
         return 0.0;
      }
      double d = list.stream().mapToDouble(Intave14TimeBlockPlaceCheck::toDouble).average().orElse(500.0);
      boolean isBridgingStraight = this.isBridgingStraight(this.placedBlockPositions);
      boolean flag = System.currentTimeMillis() - this.lastSprintTime > 8000L;
      boolean flag2 = System.currentTimeMillis() - this.lastSneakTime > 8000L;
      boolean flag3 = System.currentTimeMillis() - this.lastJumpTime < 750L;
      double d2;
      if (isBridgingStraight) {
         if (flag3) {
            d2 = 450.0;
         } else if (flag) {
            d2 = flag2 ? 500.0 : 300.0;
         } else {
            d2 = flag2 ? 350.0 : 200.0;
         }
      } else {
         d2 = 150.0;
      }
      int n = mc.thePlayer.isPotionActive(Potion.moveSpeed)
         ? mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1
         : 0;
      double d3 = d2 / (0.15 * (double)n * (double)n + 1.0);
      if (d3 <= d) {
         return 0.0;
      }
      return d3 - d;
   }

   private int countAdjacentSolidBlocks(BlockPos pos) {
      int n = 0;
      if (mc.theWorld.getBlockState(pos.south()).getBlock().getMaterial() != Material.air) {
         ++n;
      }
      if (mc.theWorld.getBlockState(pos.east()).getBlock().getMaterial() != Material.air) {
         ++n;
      }
      if (mc.theWorld.getBlockState(pos.north()).getBlock().getMaterial() != Material.air) {
         ++n;
      }
      if (mc.theWorld.getBlockState(pos.west()).getBlock().getMaterial() != Material.air) {
         ++n;
      }
      return n;
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
      super.onMoveInput(event);
      if (event.isSneak()) {
         ++this.sneakTicks;
         if (this.sneakTicks >= 2) {
            this.lastSneakTime = System.currentTimeMillis();
         }
      } else {
         if (this.debugSneakTicks && this.sneakTicks > 0) {
            ChatUtil.sendChat("Sneakticks: " + this.sneakTicks);
         }
         this.sneakTicks = 0;
      }
   }
}
