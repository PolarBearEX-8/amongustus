package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class ItemStackAStarPipeline extends AStarPipeline {
   public static final ItemStack itemStack = new ItemStack(Items.waterBucket);
   public static final ItemStack itemStack2 = new ItemStack(Items.bucket);

   public int computeCount(ItemStack stack) {
      for (int index = 0; index <= 8; index++) {
         ItemStack stack2 = this.staticListener.getEntityPlayerSP().inventoryPlayer.processAction3(index);
         if (stack2 != null && stack2.matchesItemStack(stack)) {
            return index;
         }
      }

      return 0;
   }

   private static BetterBlockPosXSYSZSBlockPos[] resolveBetterBlockPosXSYSZSBlockPos(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      int count = betterBlockPosXSYSZSBlockPos.getX() - betterBlockPosXSYSZSBlockPos2.getX();
      int count2 = betterBlockPosXSYSZSBlockPos.getZ() - betterBlockPosXSYSZSBlockPos2.getZ();
      BetterBlockPosXSYSZSBlockPos[] betterBlockPosXSYSZSBlockPosArray = new BetterBlockPosXSYSZSBlockPos[betterBlockPosXSYSZSBlockPos.getY() - betterBlockPosXSYSZSBlockPos2.getY() + 2];

      for (int index = 0; index < betterBlockPosXSYSZSBlockPosArray.length; index++) {
         betterBlockPosXSYSZSBlockPosArray[index] = new BetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos.getX() - count, betterBlockPosXSYSZSBlockPos.getY() + 1 - index, betterBlockPosXSYSZSBlockPos.getZ() - count2);
      }

      return betterBlockPosXSYSZSBlockPosArray;
   }

   @Override
   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      return this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos)
         || heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING;
   }

   private EnumFacing getEnumFacing() {
      for (int index = 0; index < 15; index++) {
         IBlockState blockState = this.staticListener
            .getWorld()
            .getBlockState(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().resolveBetterBlockPosXSYSZSBlockPos4(index));
         if (blockState.getBlock() == Blocks.block81) {
            return (EnumFacing)blockState.getComparable(BlockLadder.autoWalkPath);
         }
      }

      return null;
   }

   @Override
   public boolean matchesHeuristicPlannerByHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      if (heuristicPlanner.getWAITINGCANCELEDEnum() == WAITINGCANCELEDEnum.WAITING) {
         return true;
      } else {
         for (int index = 0; index < 4 && index < this.betterBlockPosXSYSZSBlockPosArray.length; index++) {
            if (!AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[index])) {
               return super.matchesHeuristicPlannerByHeuristicPlanner(heuristicPlanner);
            }
         }

         return true;
      }
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         DefaultCustom defaultCustom = BreadthFirstRecorder.resolveDefaultCustom(
            this.staticListener.getVecAsVec3(), Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2), this.staticListener.getDefaultCustom()
         );
         DefaultCustom defaultCustom2 = null;
         Block block = this.staticListener.getWorld().getBlockState(this.betterBlockPosXSYSZSBlockPos2).getBlock();
         boolean flag = block == Blocks.blockLava2 || block == Blocks.blockFlowingLava;
         if (!flag && this.isActive2() && !betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2)) {
            if (!ItemStackEntry.matchesEntityPlayerByEntityPlayer(this.staticListener.getEntityPlayerSP())
               || this.staticListener.getWorld().secondaryFloatArrayEntry.getCountAsIndex() == -1) {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
            }

            if (this.staticListener.getEntityPlayerSP().posY - (double)this.betterBlockPosXSYSZSBlockPos2.getY()
                  < this.staticListener.getDefaultListener().getValue()
               && !this.staticListener.getEntityPlayerSP().onGround) {
               this.staticListener.getEntityPlayerSP().inventoryPlayer.currentItem = this.computeCount(itemStack);
               defaultCustom2 = new DefaultCustom(defaultCustom.getFactor(), 90.0F);
               if (this.staticListener.matchesBlockPos(this.betterBlockPosXSYSZSBlockPos2)
                  || this.staticListener.matchesBlockPos(this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4())) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
               }
            }
         }

         if (defaultCustom2 != null) {
            heuristicPlanner.withAStarHandler(new AStarHandler(defaultCustom2, true));
         } else {
            heuristicPlanner.withAStarHandler(new AStarHandler(defaultCustom, false));
         }

         if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2) && (this.staticListener.getEntityPlayerSP().posY - (double)betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() < 0.094 || flag)) {
            if (!flag) {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            }

            if (ItemStackEntry.matchesEntityPlayerByEntityPlayer(this.staticListener.getEntityPlayerSP())) {
               this.staticListener.getEntityPlayerSP().inventoryPlayer.currentItem = this.computeCount(itemStack2);
               if (this.staticListener.getEntityPlayerSP().motionY >= 0.0) {
                  return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
               }

               return heuristicPlanner;
            }

            if (this.staticListener.getEntityPlayerSP().motionY >= 0.0) {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            }
         }

         Vec3 vec = Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2);
         if (Math.abs(this.staticListener.getEntityPlayerSP().posX + this.staticListener.getEntityPlayerSP().motionX - vec.xCoord) > 0.1
            || Math.abs(this.staticListener.getEntityPlayerSP().posZ + this.staticListener.getEntityPlayerSP().motionZ - vec.zCoord) > 0.1) {
            if (!this.staticListener.getEntityPlayerSP().onGround && Math.abs(this.staticListener.getEntityPlayerSP().motionY) > 0.4) {
               heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
            }

            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
         }

         Object entry = Optional.ofNullable(this.getEnumFacing()).map(EnumFacing::getVec3i).orElse(null);
         if (entry == null) {
            entry = this.betterBlockPosXSYSZSBlockPos.getBlockPos(this.betterBlockPosXSYSZSBlockPos2);
         } else if (Math.abs((double)((Vec3i)entry).getX() * (vec.xCoord - (double)((Vec3i)entry).getX() / 2.0 - this.staticListener.getEntityPlayerSP().posX))
               + Math.abs((double)((Vec3i)entry).getZ() * (vec.zCoord - (double)((Vec3i)entry).getZ() / 2.0 - this.staticListener.getEntityPlayerSP().posZ))
            < 0.6) {
            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
         } else if (!this.staticListener.getEntityPlayerSP().onGround) {
            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, false);
         }

         if (defaultCustom2 == null) {
            Vec3 vec2 = new Vec3(vec.xCoord + 0.125 * (double)((Vec3i)entry).getX(), vec.yCoord, vec.zCoord + 0.125 * (double)((Vec3i)entry).getZ());
            heuristicPlanner.withAStarHandler(
               new AStarHandler(
                  BreadthFirstRecorder.resolveDefaultCustom(this.staticListener.getVecAsVec3(), vec2, this.staticListener.getDefaultCustom()), false
               )
            );
         }

         return heuristicPlanner;
      }
   }

   public ItemStackAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2, resolveBetterBlockPosXSYSZSBlockPos(betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2));
   }

   @Override
   public Set of() {
      HashSet set = new HashSet();
      set.add(this.betterBlockPosXSYSZSBlockPos);

      for (int index = this.betterBlockPosXSYSZSBlockPos.count12 - this.betterBlockPosXSYSZSBlockPos2.count12; index >= 0; index--) {
         set.add(this.betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos2(index));
      }

      return set;
   }

   private boolean isActive2() {
      ItemStackEntry itemStackEntry = new ItemStackEntry(this.activeListener);
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      return ItemStackEntryAStarPipelineImpl.matchesItemStackEntry(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.betterBlockPosXSYSZSBlockPos2.count9,
         this.betterBlockPosXSYSZSBlockPos2.count10,
         0.0,
         itemStackEntry.resolveIBlockState(
            this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos.count12 - 2, this.betterBlockPosXSYSZSBlockPos2.count10
         ),
         heuristicCalculator
      );
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      ItemStackEntryAStarPipelineImpl.applyItemStackEntry(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.betterBlockPosXSYSZSBlockPos2.count9,
         this.betterBlockPosXSYSZSBlockPos2.count10,
         heuristicCalculator
      );
      return heuristicCalculator.count2 != this.betterBlockPosXSYSZSBlockPos2.count12 ? 1000000.0 : heuristicCalculator.value;
   }
}
