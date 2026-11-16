package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.Blocks;
import net.minecraft.util.EnumFacing;

public class LOLSorryAStarPipeline extends AStarPipeline {
   public static final BetterBlockPosXSYSZSBlockPos[] betterBlockPosXSYSZSBlockPosArray2 = new BetterBlockPosXSYSZSBlockPos[0];
   public final EnumFacing enumFacing;
   public final int count;
   public final boolean enabled;

   public static LOLSorryAStarPipeline resolveLOLSorryAStarPipeline(ItemStackEntry itemStackEntry, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, EnumFacing enumFacing2) {
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      applyItemStackEntry(itemStackEntry, betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, enumFacing2, heuristicCalculator);
      int count2 = Math.abs(heuristicCalculator.count3 - betterBlockPosXSYSZSBlockPos.count9) + Math.abs(heuristicCalculator.count - betterBlockPosXSYSZSBlockPos.count10);
      return new LOLSorryAStarPipeline(itemStackEntry.getActiveListener(), betterBlockPosXSYSZSBlockPos, count2, enumFacing2, heuristicCalculator.count2 > betterBlockPosXSYSZSBlockPos.count12);
   }

   @Override
   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      return heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING;
   }

   private static double computeValue(int count2) {
      switch (count2) {
         case 2:
            return 9.26569376882094;
         case 3:
            return 13.89854065323141;
         case 4:
            return 14.255167498218103;
         default:
            throw new IllegalStateException("LOL " + count2);
      }
   }

   @Override
   public Set of() {
      HashSet set = new HashSet();

      for (int index = 0; index <= this.count; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            set.add(this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.enumFacing, index).resolveBetterBlockPosXSYSZSBlockPos2(index2));
         }
      }

      return set;
   }

   private LOLSorryAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, int count2, EnumFacing enumFacing2, boolean flag) {
      super(
         activeListener,
         betterBlockPosXSYSZSBlockPos,
         betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing2, count2).resolveBetterBlockPosXSYSZSBlockPos2(flag ? 1 : 0),
         betterBlockPosXSYSZSBlockPosArray2,
         betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(enumFacing2, count2).resolveBetterBlockPosXSYSZSBlockPos4(flag ? 0 : 1)
      );
      this.enumFacing = enumFacing2;
      this.count = count2;
      this.enabled = flag;
   }

   private static boolean matchesHeuristicManager3(HeuristicManager heuristicManager, int count2, int count3, int count4) {
      return !AStarPredictor.matchesBlockByBlock(heuristicManager.resolveIBlockState(count2, count3, count4).getBlock())
         && !AStarPredictor.matchesBlockByBlock(heuristicManager.resolveIBlockState(count2, count3 + 1, count4).getBlock());
   }

   public static void applyItemStackEntry(ItemStackEntry itemStackEntry, int count2, int count3, int count4, EnumFacing enumFacing2, HeuristicCalculator heuristicCalculator) {
      if (itemStackEntry.enabled13) {
         if (count3 != 256 || itemStackEntry.enabled10) {
            int intItem = enumFacing2.getInt2();
            int intItem2 = enumFacing2.getInt();
            if (AStarPredictor.matchesItemStackEntry2(itemStackEntry, count2 + intItem, count3, count4 + intItem2)) {
               IBlockState blockState = itemStackEntry.resolveIBlockState(count2 + intItem, count3 - 1, count4 + intItem2);
               if (!AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count2 + intItem, count3 - 1, count4 + intItem2, blockState)) {
                  if (!AStarPredictor.matchesBlockByBlock(blockState.getBlock())
                     || blockState.getBlock() == Blocks.blockLava2
                     || blockState.getBlock() == Blocks.blockFlowingLava) {
                     if (AStarPredictor.matchesItemStackEntry2(itemStackEntry, count2 + intItem, count3 + 1, count4 + intItem2)) {
                        if (AStarPredictor.matchesItemStackEntry2(itemStackEntry, count2 + intItem, count3 + 2, count4 + intItem2)) {
                           if (AStarPredictor.matchesItemStackEntry2(itemStackEntry, count2, count3 + 2, count4)) {
                              IBlockState blockState2 = itemStackEntry.resolveIBlockState(count2, count3 - 1, count4);
                              if (blockState2.getBlock() != Blocks.block144
                                 && blockState2.getBlock() != Blocks.block81
                                 && !(blockState2.getBlock() instanceof BlockRedSandstoneStairs)
                                 && !AStarPredictor.matchesIBlockStateByIBlockState(blockState2)) {
                                 if (!itemStackEntry.enabled7 || !(blockState2.getBlock() instanceof BlockLiquid)) {
                                    if (!(itemStackEntry.resolveBlock(count2, count3, count4) instanceof BlockLiquid)) {
                                       byte byteValue;
                                       if (blockState2.getBlock() == Blocks.block126) {
                                          byteValue = 2;
                                       } else if (itemStackEntry.enabled11) {
                                          byteValue = 4;
                                       } else {
                                          byteValue = 3;
                                       }

                                       int count5 = 1;

                                       for (int index = 2; index <= byteValue; count5 = index++) {
                                          int count6 = count2 + intItem * index;
                                          int count7 = count4 + intItem2 * index;
                                          if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count6, count3 + 1, count7)
                                             || !AStarPredictor.matchesItemStackEntry2(itemStackEntry, count6, count3 + 2, count7)) {
                                             break;
                                          }

                                          IBlockState blockState3 = itemStackEntry.heuristicManager.resolveIBlockState(count6, count3, count7);
                                          if (!AStarPredictor.matchesItemStackEntry3(itemStackEntry, count6, count3, count7, blockState3)) {
                                             if (index <= 3
                                                && itemStackEntry.enabled2
                                                && itemStackEntry.enabled11
                                                && AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count6, count3, count7, blockState3)
                                                && matchesHeuristicManager3(itemStackEntry.heuristicManager, count6 + intItem, count3 + 1, count7 + intItem2)) {
                                                heuristicCalculator.count3 = count6;
                                                heuristicCalculator.count2 = count3 + 1;
                                                heuristicCalculator.count = count7;
                                                heuristicCalculator.value = (double)index * 3.563791874554526 + itemStackEntry.value;
                                                return;
                                             }
                                             break;
                                          }

                                          IBlockState blockState4 = itemStackEntry.heuristicManager.resolveIBlockState(count6, count3 - 1, count7);
                                          if (blockState4.getBlock() != Blocks.block7
                                             && AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count6, count3 - 1, count7, blockState4)) {
                                             if (matchesHeuristicManager3(itemStackEntry.heuristicManager, count6 + intItem, count3, count7 + intItem2)) {
                                                heuristicCalculator.count3 = count6;
                                                heuristicCalculator.count2 = count3;
                                                heuristicCalculator.count = count7;
                                                heuristicCalculator.value = computeValue(index) + itemStackEntry.value;
                                                return;
                                             }
                                             break;
                                          }

                                          if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count6, count3 + 3, count7)) {
                                             break;
                                          }
                                       }

                                       if (itemStackEntry.enabled9) {
                                          for (int index2 = count5; index2 > 1; index2--) {
                                             int count8 = count2 + index2 * intItem;
                                             int count9 = count4 + index2 * intItem2;
                                             IBlockState blockState5 = itemStackEntry.resolveIBlockState(count8, count3 - 1, count9);
                                             double offset = itemStackEntry.computeValueByIBlockState(count8, count3 - 1, count9, blockState5);
                                             if (!(offset >= 1000000.0)
                                                && AStarPredictor.matchesIBlockState2(count8, count3 - 1, count9, blockState5, itemStackEntry.heuristicManager)
                                                && matchesHeuristicManager3(itemStackEntry.heuristicManager, count8 + intItem, count3, count9 + intItem2)) {
                                                for (int index3 = 0; index3 < 5; index3++) {
                                                   int count10 = count8 + enumFacingArray[index3].getInt2();
                                                   int count11 = count3 - 1 + enumFacingArray[index3].getInt3();
                                                   int count12 = count9 + enumFacingArray[index3].getInt();
                                                   if ((count10 != count8 - intItem || count12 != count9 - intItem2)
                                                      && AStarPredictor.matchesHeuristicManager2(itemStackEntry.heuristicManager, count10, count11, count12)) {
                                                      heuristicCalculator.count3 = count8;
                                                      heuristicCalculator.count2 = count3;
                                                      heuristicCalculator.count = count9;
                                                      heuristicCalculator.value = computeValue(index2) + offset + itemStackEntry.value;
                                                      return;
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
                  }
               }
            }
         }
      }
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 < this.betterBlockPosXSYSZSBlockPos.count12) {
         this.applyTextByText("sorry");
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
      } else {
         if (this.count >= 4 || this.enabled) {
            heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SPRINT, true);
         }

         AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos2);
         if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)) {
            Block block = HeuristicManager.resolveBlock(this.staticListener, this.betterBlockPosXSYSZSBlockPos2);
            if (block == Blocks.block144 || block == Blocks.block81) {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            }

            if (this.staticListener.getEntityPlayerSP().posY - (double)this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getY() < 0.094) {
               heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            }
         } else if (!this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos)) {
            if (!this.staticListener
                  .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                  .equals(this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.enumFacing))
               && !(this.staticListener.getEntityPlayerSP().posY - (double)this.betterBlockPosXSYSZSBlockPos.count12 > 1.0E-4)) {
               if (!this.staticListener
                  .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                  .equals(this.betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos(this.enumFacing, -1))) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SPRINT, false);
                  if (this.staticListener
                     .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                     .equals(this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.enumFacing, -1))) {
                     AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos);
                  } else {
                     AStarPredictor.applyStaticListener(
                        this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(this.enumFacing, -1)
                     );
                  }
               }
            } else {
               if ((Boolean)BaritoneManager.getSettings().allowPlace.dataRef
                  && ((BaritoneManager)this.activeListener).getRememberingToMoveManager().isActive()
                  && !AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4())
                  && !this.staticListener.getEntityPlayerSP().onGround
                  && AStarPredictor.resolveDepthFirstNavigator(
                        heuristicPlanner, this.activeListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4(), true, false
                     )
                     == DepthFirstNavigator.READY_TO_PLACE) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
               }

               if (this.count == 3 && !this.enabled) {
                  double offset = (double)this.betterBlockPosXSYSZSBlockPos.count9 + 0.5 - this.staticListener.getEntityPlayerSP().posX;
                  double offset2 = (double)this.betterBlockPosXSYSZSBlockPos.count10 + 0.5 - this.staticListener.getEntityPlayerSP().posZ;
                  if (Math.max(Math.abs(offset), Math.abs(offset2)) < 0.7) {
                     return heuristicPlanner;
                  }
               }

               heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true);
            }
         }

         return heuristicPlanner;
      }
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      applyItemStackEntry(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.enumFacing,
         heuristicCalculator
      );
      return heuristicCalculator.count3 == this.betterBlockPosXSYSZSBlockPos2.count9
            && heuristicCalculator.count2 == this.betterBlockPosXSYSZSBlockPos2.count12
            && heuristicCalculator.count == this.betterBlockPosXSYSZSBlockPos2.count10
         ? heuristicCalculator.value
         : 1000000.0;
   }
}
