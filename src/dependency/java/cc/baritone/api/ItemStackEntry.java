package cc.baritone.api;

import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BaritoneManager;
import cc.baritone.utils.BreadthFirstPolicy;
import cc.baritone.utils.DepthFirstEvaluator;
import cc.baritone.utils.DepthFirstPolicy;
import cc.baritone.utils.DirectCustom;
import cc.baritone.utils.HeuristicManager;
import client.util.IBlockState;
import client.util.LvlManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemStackEntry {
   public static final ItemStack itemStack = new ItemStack(Items.waterBucket);
   public final boolean enabled;
   public final ActiveListener activeListener;
   public final World world;
   public final DepthFirstEvaluator depthFirstEvaluator;
   public final HeuristicManager heuristicManager;
   public final DepthFirstPolicy depthFirstPolicy;
   public final boolean enabled14;
   public final boolean enabled4;
   public final boolean enabled11;
   public final double value4;
   public final boolean enabled6;
   public final List list;
   public final boolean enabled13;
   public final boolean enabled9;
   public final boolean enabled10;
   public final boolean enabled2;
   public final boolean enabled7;
   public boolean enabled3;
   public final boolean enabled12;
   public final boolean enabled5;
   public final boolean enabled8;
   public int count;
   public int count3;
   public final int count2;
   public final double value5;
   public final double value2;
   public double value3;
   public double value;
   public final double value6;
   public final DirectCustom directCustom;
   public final BreadthFirstPolicy breadthFirstPolicy = new BreadthFirstPolicy();

   public IBlockState resolveIBlockState(BlockPos blockPos) {
      return this.resolveIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public static boolean matchesEntityPlayerByEntityPlayer(EntityPlayer player) {
      boolean flag = false;

      for (int index = 0; index <= 8; index++) {
         ItemStack stack = player.inventoryPlayer.processAction3(index);
         if (stack != null && stack.matchesItemStack(itemStack)) {
            flag = true;
            break;
         }
      }

      return flag;
   }

   public double getValue4() {
      return this.value4;
   }

   public final ActiveListener getActiveListener() {
      return this.activeListener;
   }

   public IBlockState resolveIBlockState(int count4, int count5, int count6) {
      return this.heuristicManager.resolveIBlockState(count4, count5, count6);
   }

   public double computeValueByIBlockState(int count4, int count5, int count6, IBlockState blockState) {
      if (!this.enabled4) {
         return 1000000.0;
      } else if (this.checkIndex(count4, count5, count6)) {
         return 1000000.0;
      } else {
         return !this.directCustom.checkIndex(count4, count6) ? 1000000.0 : this.value4;
      }
   }

   public Block resolveBlock(int count4, int count5, int count6) {
      return this.resolveIBlockState(count4, count5, count6).getBlock();
   }

   public boolean checkIndex(int count4, int count5, int count6) {
      return false;
   }

   public boolean checkIndex(int count4, int count5) {
      return this.heuristicManager.checkIndexByIndex(count4, count5);
   }

   public double computeValue(int count4, int count5, int count6, IBlockState blockState) {
      if (!this.enabled6 && !this.list.contains(blockState.getBlock())) {
         return 1000000.0;
      } else {
         return this.checkIndex(count4, count5, count6) ? 1000000.0 : 1.0;
      }
   }

   public ItemStackEntry(ActiveListener activeListener2) {
      this(activeListener2, false);
   }

   public ItemStackEntry(ActiveListener activeListener2, boolean flag) {
      this.enabled = flag;
      this.activeListener = activeListener2;
      EntityPlayerSP entityPlayerSP = activeListener2.getStaticListener().getEntityPlayerSP();
      this.world = activeListener2.getStaticListener().getWorld();
      this.depthFirstEvaluator = (DepthFirstEvaluator)activeListener2.getStaticListener().getNativeListener();
      this.heuristicManager = new HeuristicManager(activeListener2.getStaticListener(), flag);
      this.depthFirstPolicy = new DepthFirstPolicy(entityPlayerSP);
      this.enabled4 = (Boolean)BaritoneManager.getSettings().allowPlace.dataRef && ((BaritoneManager)activeListener2).getRememberingToMoveManager().isActive();
      this.enabled14 = (Boolean)BaritoneManager.getSettings().allowWaterBucketFall.dataRef
         && matchesEntityPlayerByEntityPlayer(entityPlayerSP)
         && this.world.secondaryFloatArrayEntry.getCountAsIndex() != -1;
      this.enabled11 = (Boolean)BaritoneManager.getSettings().allowSprint.dataRef && entityPlayerSP.getFoodLevelManager().getIndex() > 6;
      this.value4 = (Double)BaritoneManager.getSettings().blockPlacementPenalty.dataRef;
      this.enabled6 = (Boolean)BaritoneManager.getSettings().allowBreak.dataRef;
      this.list = new ArrayList((Collection)BaritoneManager.getSettings().allowBreakAnyway.dataRef);
      this.enabled13 = (Boolean)BaritoneManager.getSettings().allowParkour.dataRef;
      this.enabled9 = (Boolean)BaritoneManager.getSettings().allowParkourPlace.dataRef;
      this.enabled10 = (Boolean)BaritoneManager.getSettings().allowJumpAt256.dataRef;
      this.enabled2 = (Boolean)BaritoneManager.getSettings().allowParkourAscend.dataRef;
      this.enabled7 = (Boolean)BaritoneManager.getSettings().assumeWalkOnWater.dataRef;
      this.enabled3 = false;
      this.enabled12 = (Boolean)BaritoneManager.getSettings().allowDiagonalDescend.dataRef;
      this.enabled5 = (Boolean)BaritoneManager.getSettings().allowDiagonalAscend.dataRef;
      this.enabled8 = (Boolean)BaritoneManager.getSettings().allowDownward.dataRef;
      this.count = 3;
      this.count3 = (Integer)BaritoneManager.getSettings().maxFallHeightNoWater.dataRef;
      this.count2 = (Integer)BaritoneManager.getSettings().maxFallHeightBucket.dataRef;
      int count4 = LvlManager.computeCount2(entityPlayerSP);
      if (count4 > 3) {
         count4 = 3;
      }

      float factor = (float)count4 / 3.0F;
      this.value5 = 9.09090909090909 * (double)(1.0F - factor) + 4.63284688441047 * (double)factor;
      this.value2 = (Double)BaritoneManager.getSettings().blockBreakAdditionalPenalty.dataRef;
      this.value3 = (Double)BaritoneManager.getSettings().backtrackCostFavoringCoefficient.dataRef;
      this.value = (Double)BaritoneManager.getSettings().jumpPenalty.dataRef;
      this.value6 = (Double)BaritoneManager.getSettings().walkOnWaterOnePenalty.dataRef;
      this.directCustom = new DirectCustom(this.world.getInternalListEntry());
   }
}
