package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class WrongCoordinateAStarPipeline extends AStarPipeline {
   public boolean enabled = true;

   public static double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3, int count4, int count5) {
      IBlockState blockState = itemStackEntry.resolveIBlockState(count4, count2 + 1, count5);
      IBlockState blockState2 = itemStackEntry.resolveIBlockState(count4, count2, count5);
      IBlockState blockState3 = itemStackEntry.resolveIBlockState(count4, count2 - 1, count5);
      IBlockState blockState4 = itemStackEntry.resolveIBlockState(count, count2 - 1, count3);
      Block block = blockState4.getBlock();
      boolean flag = AStarPredictor.matchesItemStackEntry(itemStackEntry, count, count2 - 1, count3, blockState4);
      if (AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count4, count2 - 1, count5, blockState3)) {
         double offset = 4.63284688441047;
         boolean flag2 = false;
         if (!AStarPredictor.matchesBlock(blockState.getBlock()) && !AStarPredictor.matchesBlock(blockState2.getBlock())) {
            if (blockState3.getBlock() == Blocks.block126) {
               offset += 2.316423442205235;
            } else if (blockState3.getBlock() == Blocks.blockLava2) {
               offset += itemStackEntry.value6;
            }

            if (block == Blocks.block126) {
               offset += 2.316423442205235;
            }
         } else {
            offset = itemStackEntry.value5;
            flag2 = true;
         }

         double offset2 = AStarPredictor.computeValue(itemStackEntry, count4, count2, count5, blockState2, false);
         if (offset2 >= 1000000.0) {
            return 1000000.0;
         } else {
            double offset3 = AStarPredictor.computeValue(itemStackEntry, count4, count2 + 1, count5, blockState, true);
            if (offset2 == 0.0 && offset3 == 0.0) {
               if (!flag2 && itemStackEntry.enabled11) {
                  offset *= 0.7692444761225944;
               }

               return offset;
            } else {
               if (block == Blocks.block81 || block == Blocks.block144) {
                  offset2 *= 5.0;
                  offset3 *= 5.0;
               }

               return offset + offset2 + offset3;
            }
         }
      } else if (block != Blocks.block81 && block != Blocks.block144) {
         if (!AStarPredictor.matchesIBlockState2(count4, count2 - 1, count5, blockState3, itemStackEntry.heuristicManager)) {
            return 1000000.0;
         } else {
            boolean flag3 = AStarPredictor.matchesBlock(blockState.getBlock()) || AStarPredictor.matchesBlock(blockState2.getBlock());
            if (AStarPredictor.matchesBlock(blockState3.getBlock()) && flag3) {
               return 1000000.0;
            } else {
               double offset4 = itemStackEntry.computeValueByIBlockState(count4, count2 - 1, count5, blockState3);
               if (offset4 >= 1000000.0) {
                  return 1000000.0;
               } else {
                  double offset5 = AStarPredictor.computeValue(itemStackEntry, count4, count2, count5, blockState2, false);
                  if (offset5 >= 1000000.0) {
                     return 1000000.0;
                  } else {
                     double offset6 = AStarPredictor.computeValue(itemStackEntry, count4, count2 + 1, count5, blockState, true);
                     double offset7 = flag3 ? itemStackEntry.value5 : 4.63284688441047;

                     for (int index = 0; index < 5; index++) {
                        int count6 = count4 + enumFacingArray[index].getInt2();
                        int count7 = count2 - 1 + enumFacingArray[index].getInt3();
                        int count8 = count5 + enumFacingArray[index].getInt();
                        if ((count6 != count || count8 != count3) && AStarPredictor.matchesHeuristicManager2(itemStackEntry.heuristicManager, count6, count7, count8)) {
                           return offset7 + offset4 + offset5 + offset6;
                        }
                     }

                     if (block == Blocks.block126 || block instanceof ExtraBlockAdapter && !((ExtraBlockAdapter)block).isActive5()) {
                        return 1000000.0;
                     } else if (!flag) {
                        return 1000000.0;
                     } else {
                        Block block2 = itemStackEntry.resolveBlock(count, count2, count3);
                        return (block2 == Blocks.block33 || block2 == Blocks.block141) && block instanceof BlockLiquid
                           ? 1000000.0
                           : offset7 * 3.3207692307692307 + offset4 + offset5 + offset6;
                     }
                  }
               }
            }
         }
      } else {
         return 1000000.0;
      }
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

      return super.matchesHeuristicPlannerByHeuristicPlanner(heuristicPlanner);
   }

   @Override
   public Set of() {
      return ImmutableSet.of(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2);
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      IBlockState blockState = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[0]);
      IBlockState blockState2 = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[1]);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         if (!(Boolean)BaritoneManager.getSettings().walkWhileBreaking.dataRef) {
            return heuristicPlanner;
         } else if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.PREPPING) {
            return heuristicPlanner;
         } else if (AStarPredictor.matchesBlockByBlock(blockState.getBlock())) {
            return heuristicPlanner;
         } else if (AStarPredictor.matchesBlockByBlock(blockState2.getBlock())) {
            return heuristicPlanner;
         } else if (Math.max(
               Math.abs(this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5)),
               Math.abs(this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5))
            )
            < 0.83) {
            return heuristicPlanner;
         } else if (!heuristicPlanner.getAStarHandler().ofNullable().isPresent()) {
            return heuristicPlanner;
         } else {
            float factor = BreadthFirstRecorder.resolveDefaultCustom(
                  this.staticListener.getVecAsVec3(),
                  Vec3Helper.resolveVec3(this.staticListener.getWorld(), this.betterBlockPosXSYSZSBlockPos2),
                  this.staticListener.getDefaultCustom()
               )
               .getFactor();
            float factor2 = ((DefaultCustom)heuristicPlanner.getAStarHandler().ofNullable().get()).getFactor2();
            if (blockState.getBlock().processAction14()
               || blockState.getBlock() instanceof BlockAir && (blockState2.getBlock().processAction14() || blockState2.getBlock() instanceof BlockAir)) {
               factor2 = 26.0F;
            }

            return heuristicPlanner.withAStarHandler(new AStarHandler(new DefaultCustom(factor, factor2), true))
               .resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true)
               .resolveHeuristicPlanner(BaseCustom.SPRINT, true);
         }
      } else {
         heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, false);
         Block block = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4()).getBlock();
         boolean flag = block == Blocks.block81 || block == Blocks.block144;
         if (blockState.getBlock() instanceof BlockDarkOakDoor || blockState2.getBlock() instanceof BlockDarkOakDoor) {
            boolean flag2 = blockState.getBlock() instanceof BlockDarkOakDoor
                  && !AStarPredictor.matchesStaticListenerByStaticListener(
                     this.staticListener, this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2
                  )
               || blockState2.getBlock() instanceof BlockDarkOakDoor
                  && !AStarPredictor.matchesStaticListenerByStaticListener(
                     this.staticListener, this.betterBlockPosXSYSZSBlockPos2, this.betterBlockPosXSYSZSBlockPos
                  );
            boolean flag3 = !Blocks.ironDoor.equals(blockState.getBlock()) && !Blocks.ironDoor.equals(blockState2.getBlock());
            if (flag2 && flag3) {
               return heuristicPlanner.withAStarHandler(
                     new AStarHandler(
                        BreadthFirstRecorder.resolveDefaultCustom(
                           this.staticListener.getVecAsVec3(),
                           Vec3Helper.resolveVec3(this.staticListener.getWorld(), this.betterBlockPosXSYSZSBlockPosArray[0]),
                           this.staticListener.getDefaultCustom()
                        ),
                        true
                     )
                  )
                  .resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
            }
         }

         if (blockState.getBlock() instanceof BlockAcaciaFenceGate || blockState2.getBlock() instanceof BlockAcaciaFenceGate) {
            BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = !AStarPredictor.matchesStaticListener(
                  this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[0], this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3()
               )
               ? this.betterBlockPosXSYSZSBlockPosArray[0]
               : (
                  !AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPosArray[1], this.betterBlockPosXSYSZSBlockPos)
                     ? this.betterBlockPosXSYSZSBlockPosArray[1]
                     : null
               );
            if (betterBlockPosXSYSZSBlockPos != null) {
               Optional optional = BreadthFirstRecorder.resolveOptional(this.staticListener, betterBlockPosXSYSZSBlockPos);
               if (optional.isPresent()) {
                  return heuristicPlanner.withAStarHandler(new AStarHandler((DefaultCustom)optional.get(), true)).resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
               }
            }
         }

         boolean flag4 = AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPos3) || flag;
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() != this.betterBlockPosXSYSZSBlockPos2.getY() && !flag) {
            this.applyTextByText("Wrong Y coordinate");
            return betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() < this.betterBlockPosXSYSZSBlockPos2.getY() ? heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true) : heuristicPlanner;
         } else if (flag4) {
            if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2)) {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            } else if (!(Boolean)BaritoneManager.getSettings().overshootTraverse.dataRef
               || !betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2.getBlockPos2(this.getBlockPos()))
                  && !betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2.getBlockPos2(this.getBlockPos()).getBlockPos2(this.getBlockPos()))) {
               Block block2 = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos).getBlock();
               Block block3 = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3())
                  .getBlock();
               double count = (double)this.betterBlockPosXSYSZSBlockPos.count12;
               if (!(this.staticListener.getEntityPlayerSP().posY > count + 0.1)
                  || this.staticListener.getEntityPlayerSP().onGround
                  || block2 != Blocks.block144 && block2 != Blocks.block81 && block3 != Blocks.block144 && block3 != Blocks.block81) {
                  BlockPos blockPos = this.betterBlockPosXSYSZSBlockPos2
                     .getBlockPos(this.betterBlockPosXSYSZSBlockPos)
                     .getBlockPos2(this.betterBlockPosXSYSZSBlockPos2);
                  Block block4 = HeuristicManager.resolveIBlockState(this.staticListener, blockPos).getBlock();
                  Block block5 = HeuristicManager.resolveIBlockState(this.staticListener, blockPos.up()).getBlock();
                  if (this.enabled
                     && (!AStarPredictor.matchesStaticListener2(this.staticListener, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS) || (Boolean)BaritoneManager.getSettings().sprintInWater.dataRef)
                     && (!AStarPredictor.matchesBlockByBlock(block4) || AStarPredictor.matchesBlock(block4))
                     && !AStarPredictor.matchesBlockByBlock(block5)) {
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SPRINT, true);
                  }

                  IBlockState blockState3 = HeuristicManager.resolveIBlockState(
                     this.staticListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4()
                  );
                  Object entry = this.betterBlockPosXSYSZSBlockPosArray[0];
                  if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getY() != this.betterBlockPosXSYSZSBlockPos2.getY()
                     && flag
                     && (blockState3.getBlock() == Blocks.block144 || blockState3.getBlock() == Blocks.block81)) {
                     entry = blockState3.getBlock() == Blocks.block144
                        ? UnableClimbAStarPipeline.resolveBlockPos(
                           new ItemStackEntry(this.activeListener), this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4()
                        )
                        : this.betterBlockPosXSYSZSBlockPos2
                           .resolveBetterBlockPosXSYSZSBlockPos(((EnumFacing)blockState3.getComparable(BlockLadder.autoWalkPath)).getEnumFacing5());
                     if (entry == null) {
                        this.applyText("Unable to climb vines. Consider disabling allowVines.");
                        return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
                     }
                  }

                  AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, (BlockPos)entry);
                  return heuristicPlanner;
               } else {
                  return heuristicPlanner;
               }
            } else {
               return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
            }
         } else {
            this.enabled = false;
            Block block6 = HeuristicManager.resolveIBlockState(this.staticListener, betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.down()).getBlock();
            if ((block6.equals(Blocks.block126) || block6 instanceof ExtraBlockAdapter)
               && Math.max(
                     Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5 - this.staticListener.getEntityPlayerSP().posX),
                     Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5 - this.staticListener.getEntityPlayerSP().posZ)
                  )
                  < 0.85) {
               AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos2);
               return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, false).resolveHeuristicPlanner(BaseCustom.MOVE_BACK, true);
            } else {
               double offset = Math.max(
                  Math.abs(this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5)),
                  Math.abs(this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5))
               );
               DepthFirstNavigator depthFirstNavigator = AStarPredictor.resolveDepthFirstNavigator(
                  heuristicPlanner, this.activeListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4(), false, true
               );
               if ((depthFirstNavigator == DepthFirstNavigator.READY_TO_PLACE || offset < 0.6) && !(Boolean)BaritoneManager.getSettings().assumeSafeWalk.dataRef) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
               }

               switch (WrongCoordinateAStarPipeDepthFirstNavigatorSwi.countArray[depthFirstNavigator.ordinal()]) {
                  case 1:
                     if (this.staticListener.getEntityPlayerSP().isSprinting() || (Boolean)BaritoneManager.getSettings().assumeSafeWalk.dataRef) {
                        heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
                     }

                     return heuristicPlanner;
                  case 2:
                     if (offset > 0.83) {
                        float factor3 = BreadthFirstRecorder.resolveDefaultCustom(
                              this.staticListener.getVecAsVec3(),
                              Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2),
                              this.staticListener.getDefaultCustom()
                           )
                           .getFactor();
                        if ((double)Math.abs(heuristicPlanner.getAStarHandler().defaultCustom.getFactor() - factor3) < 0.1) {
                           return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
                        }
                     } else if (this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom(heuristicPlanner.getAStarHandler().defaultCustom)) {
                        return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
                     }

                     return heuristicPlanner;
                  default:
                     if (betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2)) {
                        double offset2 = ((double)(this.betterBlockPosXSYSZSBlockPos2.getX() + this.betterBlockPosXSYSZSBlockPos.getX()) + 1.0) * 0.5;
                        double offset3 = ((double)(this.betterBlockPosXSYSZSBlockPos2.getY() + this.betterBlockPosXSYSZSBlockPos.getY()) - 1.0) * 0.5;
                        double offset4 = ((double)(this.betterBlockPosXSYSZSBlockPos2.getZ() + this.betterBlockPosXSYSZSBlockPos.getZ()) + 1.0) * 0.5;
                        BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4();
                        DefaultCustom defaultCustom = BreadthFirstRecorder.resolveDefaultCustom(
                           this.staticListener.getVecAsVec3(), new Vec3(offset2, offset3, offset4), this.staticListener.getDefaultCustom()
                        );
                        float factor4 = defaultCustom.getFactor2();
                        if (Math.max(
                              Math.abs(this.staticListener.getEntityPlayerSP().posX - offset2), Math.abs(this.staticListener.getEntityPlayerSP().posZ - offset4)
                           )
                           < 0.29) {
                           float factor5 = BreadthFirstRecorder.resolveDefaultCustom(
                                 Vec3Helper.resolveVec3(this.betterBlockPosXSYSZSBlockPos2),
                                 this.staticListener.getVecAsVec3(),
                                 this.staticListener.getDefaultCustom()
                              )
                              .getFactor();
                           heuristicPlanner.withAStarHandler(new AStarHandler(new DefaultCustom(factor5, factor4), true));
                           heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_BACK, true);
                        } else {
                           heuristicPlanner.withAStarHandler(new AStarHandler(defaultCustom, true));
                        }

                        if (this.staticListener.matchesBlockPos(betterBlockPosXSYSZSBlockPos2)) {
                           return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
                        } else {
                           if (this.staticListener.getDefaultCustom().matchesDefaultCustomByDefaultCustom(heuristicPlanner.getAStarHandler().defaultCustom)) {
                              heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_LEFT, true);
                           }

                           return heuristicPlanner;
                        }
                     } else {
                        AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPosArray[0]);
                        return heuristicPlanner;
                     }
               }
            }
         }
      }
   }

   public WrongCoordinateAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2, new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3(), betterBlockPosXSYSZSBlockPos2}, betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4());
   }

   @Override
   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      return heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING
         || AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4());
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      return computeValue(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.betterBlockPosXSYSZSBlockPos2.count9,
         this.betterBlockPosXSYSZSBlockPos2.count10
      );
   }

   @Override
   public void updateState() {
      super.updateState();
      this.enabled = true;
   }
}
