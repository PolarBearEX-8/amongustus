package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.setting.MovementModeProperty;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Objects;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class UnableClimbAStarPipeline extends AStarPipeline {
   public static boolean matchesItemStackEntry3(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      return itemStackEntry.resolveIBlockState(count + 1, count2, count3).getBlock().isActive4()
         || itemStackEntry.resolveIBlockState(count - 1, count2, count3).getBlock().isActive4()
         || itemStackEntry.resolveIBlockState(count, count2, count3 + 1).getBlock().isActive4()
         || itemStackEntry.resolveIBlockState(count, count2, count3 - 1).getBlock().isActive4();
   }

   private static AStarHandler resolveAStarHandler(DefaultCustom defaultCustom) {
      return new AStarHandler(defaultCustom, true);
   }

   public static double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      IBlockState blockState = itemStackEntry.resolveIBlockState(count, count2, count3);
      Block block = blockState.getBlock();
      boolean flag = block == Blocks.block81 || block == Blocks.block144;
      IBlockState blockState2 = itemStackEntry.resolveIBlockState(count, count2 - 1, count3);
      if (!flag) {
         if (blockState2.getBlock() == Blocks.block81 || blockState2.getBlock() == Blocks.block144) {
            return 1000000.0;
         }

         if (blockState2.getBlock() instanceof ExtraBlockAdapter
            && !((ExtraBlockAdapter)blockState2.getBlock()).isActive5()
            && blockState2.getComparable(ExtraBlockAdapter.immutableSetEntry) == MovementModeProperty.BOTTOM) {
            return 1000000.0;
         }
      }

      if (block == Blocks.block144 && !matchesItemStackEntry3(itemStackEntry, count, count2, count3)) {
         return 1000000.0;
      } else {
         IBlockState blockState3 = itemStackEntry.resolveIBlockState(count, count2 + 2, count3);
         Block block2 = blockState3.getBlock();
         if (block2 instanceof BlockAcaciaFenceGate) {
            return 1000000.0;
         } else {
            Block block3 = null;
            if (AStarPredictor.matchesBlock(block2) && AStarPredictor.matchesBlock(block)) {
               block3 = itemStackEntry.resolveIBlockState(count, count2 + 1, count3).getBlock();
               if (AStarPredictor.matchesBlock(block3)) {
                  return 8.51063829787234;
               }
            }

            double offset = 0.0;
            if (!flag) {
               offset = itemStackEntry.computeValueByIBlockState(count, count2, count3, blockState);
               if (offset >= 1000000.0) {
                  return 1000000.0;
               }

               if (blockState2.getBlock() == Blocks.air) {
                  offset += 0.1;
               }
            }

            if (block instanceof BlockLiquid || blockState2.getBlock() instanceof BlockLiquid && itemStackEntry.enabled7) {
               return 1000000.0;
            } else if ((block == Blocks.block33 || block == Blocks.block141) && blockState2.getBlock() instanceof BlockLiquid) {
               return 1000000.0;
            } else {
               double offset2 = AStarPredictor.computeValue(itemStackEntry, count, count2 + 2, count3, blockState3, true);
               if (offset2 >= 1000000.0) {
                  return 1000000.0;
               } else {
                  if (offset2 != 0.0) {
                     if (block2 != Blocks.block81 && block2 != Blocks.block144) {
                        if (itemStackEntry.resolveIBlockState(count, count2 + 3, count3).getBlock() instanceof CustomBlockAdapter) {
                           if (block3 == null) {
                              block3 = itemStackEntry.resolveIBlockState(count, count2 + 1, count3).getBlock();
                           }

                           if (!(block2 instanceof CustomBlockAdapter) || !(block3 instanceof CustomBlockAdapter)) {
                              return 1000000.0;
                           }
                        }
                     } else {
                        offset2 = 0.0;
                     }
                  }

                  return flag ? 8.51063829787234 + offset2 * 5.0 : value14 + offset + itemStackEntry.value + offset2;
               }
            }
         }
      }
   }

   public static BlockPos resolveBlockPos(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      if (itemStackEntry.resolveIBlockState(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos5()).getBlock().isActive4()) {
         return betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos5();
      } else if (itemStackEntry.resolveIBlockState(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()).getBlock().isActive4()) {
         return betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      } else if (itemStackEntry.resolveIBlockState(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos2()).getBlock().isActive4()) {
         return betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos2();
      } else {
         return itemStackEntry.resolveIBlockState(betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos()).getBlock().isActive4() ? betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos() : null;
      }
   }

   public UnableClimbAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2, new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos2(2)}, betterBlockPosXSYSZSBlockPos);
   }

   @Override
   public boolean matchesHeuristicPlannerByHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos)
         || this.staticListener
            .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
            .equals(this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4())) {
         Block block = HeuristicManager.resolveBlock(this.staticListener, this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4());
         if (block == Blocks.block81 || block == Blocks.block144) {
            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
         }
      }

      return AStarPredictor.matchesStaticListener4(this.staticListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3())
         ? true
         : super.matchesHeuristicPlannerByHeuristicPlanner(heuristicPlanner);
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 < this.betterBlockPosXSYSZSBlockPos.count12) {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
      } else {
         IBlockState blockState = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos);
         if (AStarPredictor.matchesBlock(blockState.getBlock()) && AStarPredictor.matchesStaticListener4(this.staticListener, this.betterBlockPosXSYSZSBlockPos2)) {
            heuristicPlanner.withAStarHandler(
               new AStarHandler(
                  BreadthFirstRecorder.resolveDefaultCustom(
                     this.staticListener.getVecAsVec3(), Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2), this.staticListener.getDefaultCustom()
                  ),
                  false
               )
            );
            Vec3 vec = Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2);
            if (Math.abs(this.staticListener.getEntityPlayerSP().posX - vec.xCoord) > 0.2
               || Math.abs(this.staticListener.getEntityPlayerSP().posZ - vec.zCoord) > 0.2) {
               heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
            }

            return this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)
               ? heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS)
               : heuristicPlanner;
         } else {
            boolean flag = blockState.getBlock() == Blocks.block81 || blockState.getBlock() == Blocks.block144;
            boolean flag2 = blockState.getBlock() == Blocks.block144;
            DefaultCustom defaultCustom = BreadthFirstRecorder.resolveDefaultCustom(
               this.staticListener.getVecAsVec3(), Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos3), this.staticListener.getDefaultCustom()
            );
            if (!flag) {
               heuristicPlanner.withAStarHandler(new AStarHandler(this.staticListener.getDefaultCustom().resolveDefaultCustom(defaultCustom.getFactor2()), true));
            }

            boolean flag3 = AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPos) || flag;
            if (flag) {
               Object entry = flag2
                  ? resolveBlockPos(new ItemStackEntry(this.activeListener), this.betterBlockPosXSYSZSBlockPos)
                  : this.betterBlockPosXSYSZSBlockPos
                     .resolveBetterBlockPosXSYSZSBlockPos(((EnumFacing)blockState.getComparable(BlockLadder.autoWalkPath)).getEnumFacing5());
               if (entry == null) {
                  this.applyText("Unable to climb vines. Consider disabling allowVines.");
                  return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
               } else if (!this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(((BlockPos)entry).up())
                  && !this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)) {
                  if (AStarPredictor.matchesIBlockStateByIBlockState(
                     HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4())
                  )) {
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true);
                  }

                  AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, (BlockPos)entry);
                  return heuristicPlanner;
               } else {
                  return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
               }
            } else if (!((BaritoneManager)this.activeListener)
               .getRememberingToMoveManager()
               .checkFlag(true, this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos.count10)
               )
             {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
            } else {
               heuristicPlanner.resolveHeuristicPlanner(
                  BaseCustom.SNEAK,
                  this.staticListener.getEntityPlayerSP().posY > (double)this.betterBlockPosXSYSZSBlockPos2.getY()
                     || this.staticListener.getEntityPlayerSP().posY < (double)this.betterBlockPosXSYSZSBlockPos.getY() + 0.2
               );
               double offset = this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5);
               double offset2 = this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5);
               double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
               double offset4 = Math.sqrt(
                  this.staticListener.getEntityPlayerSP().motionX * this.staticListener.getEntityPlayerSP().motionX
                     + this.staticListener.getEntityPlayerSP().motionZ * this.staticListener.getEntityPlayerSP().motionZ
               );
               if (offset3 > 0.17) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
                  heuristicPlanner.withAStarHandler(new AStarHandler(defaultCustom, true));
               } else if (offset4 < 0.05) {
                  heuristicPlanner.resolveHeuristicPlanner(
                     BaseCustom.JUMP, this.staticListener.getEntityPlayerSP().posY < (double)this.betterBlockPosXSYSZSBlockPos2.getY()
                  );
               }

               if (!flag3) {
                  IBlockState blockState2 = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos);
                  if (!(blockState2.getBlock() instanceof BlockAir) && !blockState2.getBlock().getMaterial().canExecute()) {
                     BreadthFirstRecorder.resolveOptional(
                           this.staticListener, this.betterBlockPosXSYSZSBlockPos, this.staticListener.getDefaultListener().getValue()
                        )
                        .map(item -> UnableClimbAStarPipeline.resolveAStarHandler((DefaultCustom)item))
                        .ifPresent(handler -> heuristicPlanner.withAStarHandler((AStarHandler)handler));
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, false);
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
                     flag3 = false;
                  } else if (this.staticListener.getEntityPlayerSP().isSprinting()
                     && (
                        Objects.equals(
                              this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4(), this.staticListener.getMovingObjectPosition().getBlockPos()
                           )
                           || Objects.equals(this.betterBlockPosXSYSZSBlockPos, this.staticListener.getMovingObjectPosition().getBlockPos())
                     )
                     && this.staticListener.getEntityPlayerSP().posY > (double)this.betterBlockPosXSYSZSBlockPos2.getY() + 0.1) {
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
                  }
               }

               return this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2) && flag3
                  ? heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS)
                  : heuristicPlanner;
            }
         }
      }
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      return computeValue(itemStackEntry, this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos.count10);
   }

   @Override
   public Set of() {
      return ImmutableSet.of(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2);
   }
}
