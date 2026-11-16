package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class ItemStackEntryAStarPipeline extends AStarPipeline {
   public static final double value15 = Math.sqrt(2.0);

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      applyItemStackEntry(
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

   public static void applyItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, int count4, int count5, HeuristicCalculator heuristicCalculator) {
      if (AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 + 1, count5)) {
         IBlockState blockState = itemStackEntry.resolveIBlockState(count4, count2, count5);
         boolean flag = false;
         boolean flag2 = false;
         IBlockState blockState2;
         IBlockState blockState3;
         if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count4, count2, count5, blockState)) {
            flag = true;
            if (!itemStackEntry.enabled5
               || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count, count2 + 2, count3)
               || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2, count5, blockState)
               || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 + 2, count5)) {
               return;
            }

            blockState2 = blockState;
            blockState3 = itemStackEntry.resolveIBlockState(count, count2 - 1, count3);
         } else {
            blockState2 = itemStackEntry.resolveIBlockState(count4, count2 - 1, count5);
            blockState3 = itemStackEntry.resolveIBlockState(count, count2 - 1, count3);
            if (!AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 - 1, count5, blockState2)) {
               flag2 = true;
               if (!itemStackEntry.enabled12
                  || !AStarPredictor.matchesItemStackEntry(itemStackEntry, count4, count2 - 2, count5)
                  || !AStarPredictor.matchesItemStackEntry2(itemStackEntry, count4, count2 - 1, count5, blockState2)) {
                  return;
               }
            }
         }

         double offset = 4.63284688441047;
         if (blockState2.getBlock() == Blocks.block126) {
            offset += 2.316423442205235;
         } else if (blockState2.getBlock() == Blocks.blockLava2) {
            offset += itemStackEntry.value6 * value15;
         }

         Block block = blockState3.getBlock();
         if (block != Blocks.block81 && block != Blocks.block144) {
            if (block == Blocks.block126) {
               offset += 2.316423442205235;
            }

            if (!AStarPredictor.matchesBlock2(itemStackEntry.resolveIBlockState(count, count2 - 1, count5).getBlock())) {
               if (!AStarPredictor.matchesBlock2(itemStackEntry.resolveIBlockState(count4, count2 - 1, count3).getBlock())) {
                  Block block2 = itemStackEntry.resolveBlock(count, count2, count3);
                  boolean flag3 = false;
                  if (AStarPredictor.matchesBlock(block2) || AStarPredictor.matchesBlock(blockState.getBlock())) {
                     if (flag) {
                        return;
                     }

                     offset = itemStackEntry.value5;
                     flag3 = true;
                  }

                  IBlockState blockState4 = itemStackEntry.resolveIBlockState(count, count2, count5);
                  IBlockState blockState5 = itemStackEntry.resolveIBlockState(count4, count2, count3);
                  if (flag) {
                     boolean flag4 = AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count, count2 + 2, count5);
                     boolean flag5 = AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count, count2 + 1, count5);
                     boolean flag6 = AStarPredictor.matchesItemStackEntry2(itemStackEntry, count, count2, count5, blockState4);
                     boolean flag7 = AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 + 2, count3);
                     boolean flag8 = AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 + 1, count3);
                     boolean flag9 = AStarPredictor.matchesItemStackEntry2(itemStackEntry, count4, count2, count3, blockState5);
                     if ((flag4 && flag5 && flag6 || flag7 && flag8 && flag9)
                        && !AStarPredictor.matchesBlockByBlock(blockState4.getBlock())
                        && !AStarPredictor.matchesBlockByBlock(blockState5.getBlock())
                        && (!flag4 || !flag5 || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count, count2, count5, blockState4))
                        && (!flag7 || !flag8 || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2, count3, blockState5))
                        && (flag4 || !flag5 || !flag6)
                        && (flag7 || !flag8 || !flag9)) {
                        heuristicCalculator.value = offset * value15 + value14;
                        heuristicCalculator.count3 = count4;
                        heuristicCalculator.count = count5;
                        heuristicCalculator.count2 = count2 + 1;
                     }
                  } else {
                     double offset2 = AStarPredictor.computeValue(itemStackEntry, count, count2, count5, blockState4, false);
                     double offset3 = AStarPredictor.computeValue(itemStackEntry, count4, count2, count3, blockState5, false);
                     if (offset2 == 0.0 || offset3 == 0.0) {
                        IBlockState blockState6 = itemStackEntry.resolveIBlockState(count, count2 + 1, count5);
                        double offset4 = offset2 + AStarPredictor.computeValue(itemStackEntry, count, count2 + 1, count5, blockState6, true);
                        if (offset4 == 0.0 || offset3 == 0.0) {
                           IBlockState blockState7 = itemStackEntry.resolveIBlockState(count4, count2 + 1, count3);
                           if (offset4 != 0.0
                              || (!AStarPredictor.matchesBlockByBlock(blockState5.getBlock()) || blockState5.getBlock() == Blocks.blockLava2)
                                 && !AStarPredictor.matchesBlockByBlock(blockState7.getBlock())) {
                              double offset5 = offset3 + AStarPredictor.computeValue(itemStackEntry, count4, count2 + 1, count3, blockState7, true);
                              if (offset4 == 0.0 || offset5 == 0.0) {
                                 if (offset5 != 0.0
                                    || (!AStarPredictor.matchesBlockByBlock(blockState4.getBlock()) || blockState4.getBlock() == Blocks.blockLava2)
                                       && !AStarPredictor.matchesBlockByBlock(blockState6.getBlock())) {
                                    if (offset4 != 0.0 || offset5 != 0.0) {
                                       offset *= value15 - 0.001;
                                       if (block2 == Blocks.block81 || block2 == Blocks.block144) {
                                          return;
                                       }
                                    } else if (itemStackEntry.enabled11 && !flag3) {
                                       offset *= 0.7692444761225944;
                                    }

                                    heuristicCalculator.value = offset * value15;
                                    if (flag2) {
                                       heuristicCalculator.value = heuristicCalculator.value + Math.max(valueArray[1], 0.9265693768820937);
                                       heuristicCalculator.count2 = count2 - 1;
                                    } else {
                                       heuristicCalculator.count2 = count2;
                                    }

                                    heuristicCalculator.count3 = count4;
                                    heuristicCalculator.count = count5;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private ItemStackEntryAStarPipeline(
      ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3, EnumFacing enumFacing, int count
   ) {
      this(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos(enumFacing).resolveBetterBlockPosXSYSZSBlockPos2(count), betterBlockPosXSYSZSBlockPos2, betterBlockPosXSYSZSBlockPos3);
   }

   @Override
   public boolean matchesHeuristicPlannerByHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      return true;
   }

   @Override
   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      EntityPlayerSP entityPlayerSP = this.staticListener.getEntityPlayerSP();
      double offset = 0.25;
      double posX = entityPlayerSP.posX;
      double offset2 = entityPlayerSP.posY - 1.0;
      double posZ = entityPlayerSP.posZ;
      if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos)) {
         return true;
      } else if (AStarPredictor.matchesStaticListener(
            this.staticListener,
            new BlockPos(this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12 - 1, this.betterBlockPosXSYSZSBlockPos2.count10)
         )
         && AStarPredictor.matchesStaticListener(
            this.staticListener,
            new BlockPos(this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos.count12 - 1, this.betterBlockPosXSYSZSBlockPos.count10)
         )) {
         return true;
      } else {
         return !this.staticListener
                  .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                  .equals(
                     new BetterBlockPosXSYSZSBlockPos(
                        this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos2.count10
                     )
                  )
               && !this.staticListener
                  .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                  .equals(
                     new BetterBlockPosXSYSZSBlockPos(
                        this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos.count10
                     )
                  )
            ? true
            : AStarPredictor.matchesStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(posX + offset, offset2, posZ + offset))
               || AStarPredictor.matchesStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(posX + offset, offset2, posZ - offset))
               || AStarPredictor.matchesStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(posX - offset, offset2, posZ + offset))
               || AStarPredictor.matchesStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(posX - offset, offset2, posZ - offset));
      }
   }

   private boolean isActive2() {
      if (AStarPredictor.matchesStaticListener2(this.staticListener, this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
         && !(Boolean)BaritoneManager.getSettings().sprintInWater.dataRef) {
         return false;
      } else {
         for (int index = 0; index < 4; index++) {
            if (!AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[index])) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public List resolveListByHeuristicManager(HeuristicManager heuristicManager) {
      if (this.list != null) {
         return this.list;
      } else {
         java.util.ArrayList list = new java.util.ArrayList();

         for (int index = 4; index < 6; index++) {
            if (!AStarPredictor.matchesHeuristicManagerByHeuristicManager(
               heuristicManager,
               this.betterBlockPosXSYSZSBlockPosArray[index].count9,
               this.betterBlockPosXSYSZSBlockPosArray[index].count12,
               this.betterBlockPosXSYSZSBlockPosArray[index].count10
            )) {
               list.add(this.betterBlockPosXSYSZSBlockPosArray[index]);
            }
         }

         this.list = list;
         return list;
      }
   }

   private ItemStackEntryAStarPipeline(
      ActiveListener activeListener,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos3,
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos4
   ) {
      super(
         activeListener,
         betterBlockPosXSYSZSBlockPos,
         betterBlockPosXSYSZSBlockPos2,
         new BetterBlockPosXSYSZSBlockPos[]{
            betterBlockPosXSYSZSBlockPos3, betterBlockPosXSYSZSBlockPos3.getBetterBlockPosXSYSZSBlockPos3(), betterBlockPosXSYSZSBlockPos4, betterBlockPosXSYSZSBlockPos4.getBetterBlockPosXSYSZSBlockPos3(), betterBlockPosXSYSZSBlockPos2, betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3()
         }
      );
   }

   public ItemStackEntryAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, EnumFacing enumFacing, EnumFacing enumFacing2, int count) {
      this(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing), betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing2), enumFacing2, count);
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)) {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
      } else if (this.isActiveAsFlag()
         || AStarPredictor.matchesStaticListener2(this.staticListener, this.betterBlockPosXSYSZSBlockPos)
            && this.getSet().contains(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getBetterBlockPosXSYSZSBlockPos3())) {
         if (this.betterBlockPosXSYSZSBlockPos2.count12 > this.betterBlockPosXSYSZSBlockPos.count12) {
            double count = (double)this.betterBlockPosXSYSZSBlockPos.count12;
            if (this.staticListener.getEntityPlayerSP().posY < count + 0.1 && this.staticListener.getEntityPlayerSP().isCollidedHorizontally) {
               heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true);
            }
         }

         if (this.isActive2()) {
            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SPRINT, true);
         }

         AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos2);
         return heuristicPlanner;
      } else {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
      }
   }

   @Override
   public List resolveList(HeuristicManager heuristicManager) {
      if (this.list3 == null) {
         this.list3 = new java.util.ArrayList();
      }

      java.util.ArrayList list = new java.util.ArrayList();

      for (int index = 0; index < 4; index++) {
         if (!AStarPredictor.matchesHeuristicManagerByHeuristicManager(
            heuristicManager,
            this.betterBlockPosXSYSZSBlockPosArray[index].count9,
            this.betterBlockPosXSYSZSBlockPosArray[index].count12,
            this.betterBlockPosXSYSZSBlockPosArray[index].count10
         )) {
            list.add(this.betterBlockPosXSYSZSBlockPosArray[index]);
         }
      }

      this.list3 = list;
      return this.list3;
   }

   @Override
   public Set of() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(
         this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos2.count10
      );
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = new BetterBlockPosXSYSZSBlockPos(
         this.betterBlockPosXSYSZSBlockPos2.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos.count10
      );
      if (this.betterBlockPosXSYSZSBlockPos2.count12 < this.betterBlockPosXSYSZSBlockPos.count12) {
         return ImmutableSet.of(
            this.betterBlockPosXSYSZSBlockPos,
            this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3(),
            betterBlockPosXSYSZSBlockPos,
            betterBlockPosXSYSZSBlockPos2,
            this.betterBlockPosXSYSZSBlockPos2,
            betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4(),
            new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4()}
         );
      } else {
         return this.betterBlockPosXSYSZSBlockPos2.count12 > this.betterBlockPosXSYSZSBlockPos.count12
            ? ImmutableSet.of(
               this.betterBlockPosXSYSZSBlockPos,
               this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3(),
               betterBlockPosXSYSZSBlockPos,
               betterBlockPosXSYSZSBlockPos2,
               this.betterBlockPosXSYSZSBlockPos2,
               betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3(),
               new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3()}
            )
            : ImmutableSet.of(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2);
      }
   }
}
