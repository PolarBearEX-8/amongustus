package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.setting.EnumFacingXType;
import client.setting.ExternalOptionType;
import client.setting.MovementFactorProperty;
import client.setting.MovementModeProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.MovingObjectType;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockIronTrapdoor;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockMonsterEgg;
import net.minecraft.block.BlockRedSandstoneStairs;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.BlockWaterlily;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.block.ExtraBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public interface AStarPredictor extends DRNHandler, ServiceEntry {
   @Deprecated
   static boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState, HeuristicManager heuristicManager) {
      return matchesIBlockState2(count, count2, count3, blockState, heuristicManager);
   }

   static boolean matchesHeuristicManager2(HeuristicManager heuristicManager, int count, int count2, int count3) {
      return matchesHeuristicManager3(heuristicManager, count, count2, count3, heuristicManager.resolveIBlockState(count, count2, count3));
   }

   static boolean matchesStaticListener2(StaticListener staticListener, BlockPos blockPos) {
      return HeuristicManager.resolveBlock(staticListener, blockPos) instanceof BlockLiquid;
   }

   static YESMAYBEEnum resolveYESMAYBEEnumByIBlockState(IBlockState blockState) {
      Block block = blockState.getBlock();
      if (block == Blocks.air) {
         return YESMAYBEEnum.YES;
      } else if (block != Blocks.blockFire
         && block != Blocks.block6
         && block != Blocks.web
         && block != Blocks.block144
         && block != Blocks.block81
         && block != Blocks.block90
         && !(block instanceof BlockDarkOakDoor)
         && !(block instanceof BlockAcaciaFenceGate)
         && !(block instanceof BlockSnowLayer)
         && !(block instanceof BlockLiquid)
         && !(block instanceof BlockIronTrapdoor)
         && !(block instanceof BlockEndPortal)
         && !(block instanceof BlockSkull)) {
         try {
            return block.matchesIBlockAccess(null, null) ? YESMAYBEEnum.YES : YESMAYBEEnum.NO;
         } catch (Throwable throwable) {
            System.out.println("The block " + blockState.getBlock().getText() + " requires a special case due to the exception " + throwable.getMessage());
            return YESMAYBEEnum.MAYBE;
         }
      } else {
         return YESMAYBEEnum.NO;
      }
   }

   static boolean matchesIBlockStateByIBlockState(IBlockState blockState) {
      return blockState.getBlock() instanceof ExtraBlockAdapter
         && !((ExtraBlockAdapter)blockState.getBlock()).isActive5()
         && blockState.getComparable(ExtraBlockAdapter.immutableSetEntry) == MovementModeProperty.BOTTOM;
   }

   static boolean matchesIBlockState(IBlockState blockState) {
      return blockState.getBlock() instanceof BlockLiquid && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0;
   }

   static boolean matchesItemStackEntry2(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      return matchesItemStackEntry3(itemStackEntry, count, count2, count3, itemStackEntry.resolveIBlockState(count, count2, count3));
   }

   static boolean matchesStaticListenerByStaticListener(StaticListener staticListener, BlockPos blockPos, BlockPos blockPos2) {
      if (blockPos2.equals(blockPos)) {
         return false;
      } else {
         IBlockState blockState = HeuristicManager.resolveIBlockState(staticListener, blockPos);
         return !(blockState.getBlock() instanceof BlockDarkOakDoor) ? true : matchesBlockPos(blockPos, blockState, blockPos2, BlockDarkOakDoor.internalImmutableSetEntry2);
      }
   }

   static void applyStaticListener(StaticListener staticListener, IBlockState blockState, DepthFirstPolicy depthFirstPolicy, boolean flag) {
      if ((Boolean)BaritoneManager.getSettings().autoTool.dataRef && !(Boolean)BaritoneManager.getSettings().assumeExternalAutoTool.dataRef) {
         staticListener.getEntityPlayerSP().inventoryPlayer.currentItem = depthFirstPolicy.computeCount(blockState.getBlock(), flag);
      }
   }

   static boolean matchesStaticListener(StaticListener staticListener, BlockPos blockPos, BlockPos blockPos2) {
      if (blockPos2.equals(blockPos)) {
         return false;
      } else {
         IBlockState blockState = HeuristicManager.resolveIBlockState(staticListener, blockPos);
         return !(blockState.getBlock() instanceof BlockAcaciaFenceGate) ? true : (Boolean)blockState.getComparable(BlockAcaciaFenceGate.internalImmutableSetEntry3);
      }
   }

   static boolean matchesStaticListener3(StaticListener staticListener, BlockPos blockPos) {
      IBlockState blockState = staticListener.getWorld().getBlockState(blockPos);
      YESMAYBEEnum yESMAYBEEnum = resolveYESMAYBEEnumByIBlockState(blockState);
      if (yESMAYBEEnum == YESMAYBEEnum.YES) {
         return true;
      } else {
         return yESMAYBEEnum == YESMAYBEEnum.NO ? false : matchesHeuristicManager6(new HeuristicManager(staticListener), blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState);
      }
   }

   static boolean matchesHeuristicManager(HeuristicManager heuristicManager, int count, int count2, int count3) {
      return matchesHeuristicManager(heuristicManager, count, count2, count3, heuristicManager.resolveIBlockState(count, count2, count3));
   }

   static boolean matchesHeuristicManager3(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      return !heuristicManager.directCustom.checkIndex(count, count3)
         ? false
         : blockState.getBlock().isActive4() || blockState.getBlock().isState18() || blockState.getBlock() == Blocks.block68 || blockState.getBlock() == Blocks.blockStainedGlass;
   }

   static DepthFirstNavigator resolveDepthFirstNavigator(HeuristicPlanner heuristicPlanner, ActiveListener activeListener, BlockPos blockPos, boolean flag, boolean flag2) {
      StaticListener staticListener = activeListener.getStaticListener();
      Optional optional = BreadthFirstRecorder.resolveOptional(staticListener, blockPos, flag2);
      boolean flag3 = false;
      if (optional.isPresent()) {
         heuristicPlanner.withAStarHandler(new AStarHandler((DefaultCustom)optional.get(), true));
         flag3 = true;
      }

      int count = 0;

      while (true) {
         searchBlock: {
            if (count < 5) {
               BlockPos blockPos2 = blockPos.offset(AStarPipeline.enumFacingArray[count]);
               if (!matchesStaticListenerByStaticListener(staticListener, blockPos2)) {
                  break searchBlock;
               }

               if (!((BaritoneManager)activeListener).getRememberingToMoveManager().checkFlag(false, blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
                  DRNHandler.dRNHandler.applyTextByText("bb pls get me some blocks. dirt, netherrack, cobble");
                  heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
                  return DepthFirstNavigator.NO_OPTION;
               }

               double offset = ((double)(blockPos.getX() + blockPos2.getX()) + 1.0) * 0.5;
               double offset2 = ((double)(blockPos.getY() + blockPos2.getY()) + 0.5) * 0.5;
               double offset3 = ((double)(blockPos.getZ() + blockPos2.getZ()) + 1.0) * 0.5;
               DefaultCustom defaultCustom = BreadthFirstRecorder.resolveDefaultCustom(
                  flag2 ? CoreCustom.resolveVec3(staticListener.getEntityPlayerSP()) : staticListener.getVecAsVec3(), new Vec3(offset, offset2, offset3), staticListener.getDefaultCustom()
               );
               DefaultCustom defaultCustom2 = activeListener.getGlobalListener().getStandardListener().resolveDefaultCustom(defaultCustom);
               MovingObjectPosition hitResult = CoreCustom.resolveMovingObjectPosition(staticListener.getEntityPlayerSP(), defaultCustom2, staticListener.getDefaultListener().getValue(), flag2);
               if (hitResult == null
                  || hitResult.typeOfHit != MovingObjectType.BLOCK
                  || !hitResult.getBlockPos().equals(blockPos2)
                  || !hitResult.getBlockPos().offset(hitResult.sideHit).equals(blockPos)) {
                  break searchBlock;
               }

               heuristicPlanner.withAStarHandler(new AStarHandler(defaultCustom, true));
               flag3 = true;
               if (flag) {
                  break searchBlock;
               }
            }

            if (staticListener.getOptional().isPresent()) {
               BlockPos blockPos3 = (BlockPos)staticListener.getOptional().get();
               EnumFacing sideHit = staticListener.getMovingObjectPosition().sideHit;
               if (blockPos3.equals(blockPos) || matchesStaticListenerByStaticListener(staticListener, blockPos3) && blockPos3.offset(sideHit).equals(blockPos)) {
                  if (flag2) {
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
                  }

                  ((BaritoneManager)activeListener).getRememberingToMoveManager().checkFlag(true, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                  return DepthFirstNavigator.READY_TO_PLACE;
               }
            }

            if (flag3) {
               if (flag2) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
               }

               ((BaritoneManager)activeListener).getRememberingToMoveManager().checkFlag(true, blockPos.getX(), blockPos.getY(), blockPos.getZ());
               return DepthFirstNavigator.ATTEMPTING;
            }

            return DepthFirstNavigator.NO_OPTION;
         }

         count++;
      }
   }

   static void applyStaticListener(StaticListener staticListener, IBlockState blockState) {
      applyStaticListener(staticListener, blockState, new DepthFirstPolicy(staticListener.getEntityPlayerSP()), (Boolean)SettingsTxtManager.getSettings().preferSilkTouch.dataRef);
   }

   static boolean matchesStaticListener4(StaticListener staticListener, BlockPos blockPos) {
      return matchesBlock(HeuristicManager.resolveBlock(staticListener, blockPos));
   }

   static boolean matchesHeuristicManagerByHeuristicManager(HeuristicManager heuristicManager, int count, int count2, int count3) {
      return matchesHeuristicManager4(heuristicManager, count, count2, count3, heuristicManager.resolveIBlockState(count, count2, count3));
   }

   static boolean matchesBlock(Block block) {
      return block == Blocks.blockFlowingLava || block == Blocks.blockLava2;
   }

   static boolean matchesIBlockState2(int count, int count2, int count3, IBlockState blockState, HeuristicManager heuristicManager) {
      Block block = blockState.getBlock();
      if (block == Blocks.air || matchesBlock(block)) {
         return true;
      } else if (block instanceof BlockSnowLayer) {
         return !heuristicManager.checkIndex(count, count3) ? true : (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) == 1;
      } else if (!(block instanceof BlockDoublePlant)) {
         return blockState.getBlock().getMaterial().canExecute();
      } else {
         ExternalOptionType comparable = (ExternalOptionType)blockState.getComparable(BlockDoublePlant.immutableSetEntry);
         return comparable == ExternalOptionType.FERN || comparable == ExternalOptionType.GRASS;
      }
   }

   static boolean matchesItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, IBlockState blockState) {
      Block block = blockState.getBlock();
      if (block != Blocks.block81 && block != Blocks.block144) {
         if (block instanceof BlockLiquid) {
            if (itemStackEntry.enabled7) {
               return false;
            }

            if (itemStackEntry.resolveBlock(count, count2 + 1, count3) instanceof BlockLiquid) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   static boolean matchesItemStackEntry3(ItemStackEntry itemStackEntry, int count, int count2, int count3, IBlockState blockState) {
      return itemStackEntry.breadthFirstPolicy.matchesHeuristicManager(itemStackEntry.heuristicManager, count, count2, count3, blockState);
   }

   static YESMAYBEEnum resolveYESMAYBEEnum(IBlockState blockState) {
      Block block = blockState.getBlock();
      if (block == Blocks.air) {
         return YESMAYBEEnum.YES;
      } else if (block != Blocks.blockFire
         && block != Blocks.block6
         && block != Blocks.web
         && block != Blocks.block2
         && block != Blocks.block90
         && !(block instanceof BlockSkull)
         && !(block instanceof BlockIronTrapdoor)) {
         if (((List)BaritoneManager.getSettings().blocksToAvoid.dataRef).contains(block)) {
            return YESMAYBEEnum.NO;
         } else if (!(block instanceof BlockDarkOakDoor) && !(block instanceof BlockAcaciaFenceGate)) {
            if (block == Blocks.block141) {
               return YESMAYBEEnum.MAYBE;
            } else if (block instanceof BlockSnowLayer) {
               return YESMAYBEEnum.MAYBE;
            } else if (block instanceof BlockLiquid) {
               return (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0 ? YESMAYBEEnum.NO : YESMAYBEEnum.MAYBE;
            } else if (block instanceof BlockCauldron) {
               return YESMAYBEEnum.NO;
            } else {
               try {
                  return block.matchesIBlockAccess(null, null) ? YESMAYBEEnum.YES : YESMAYBEEnum.NO;
               } catch (Throwable throwable) {
                  System.out.println("The block " + blockState.getBlock().getText() + " requires a special case due to the exception " + throwable.getMessage());
                  return YESMAYBEEnum.MAYBE;
               }
            }
         } else {
            return block == Blocks.ironDoor ? YESMAYBEEnum.NO : YESMAYBEEnum.YES;
         }
      } else {
         return YESMAYBEEnum.NO;
      }
   }

   static boolean matchesHeuristicManager2(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      Block block = blockState.getBlock();
      if (!matchesBlock(block)) {
         return matchesBlock2(block)
            && !matchesIBlockStateByIBlockState(count, count2, count3, blockState, heuristicManager)
            && (Boolean)BaritoneManager.getSettings().assumeWalkOnLava.dataRef;
      } else {
         Block block2 = heuristicManager.resolveIBlockState(count, count2 + 1, count3).getBlock();
         if (block2 == Blocks.block33 || block2 == Blocks.block141) {
            return true;
         } else {
            return !matchesIBlockStateByIBlockState(count, count2, count3, blockState, heuristicManager) && block != Blocks.blockFlowingLava
               ? matchesBlock(block2) ^ (Boolean)BaritoneManager.getSettings().assumeWalkOnWater.dataRef
               : matchesBlock(block2) && !(Boolean)BaritoneManager.getSettings().assumeWalkOnWater.dataRef;
         }
      }
   }

   static boolean matchesBlock2(Block block) {
      return block == Blocks.blockFlowingLava2 || block == Blocks.blockLava;
   }

   static boolean matchesBlockPos(BlockPos blockPos, IBlockState blockState, BlockPos blockPos2, InternalImmutableSetEntry internalImmutableSetEntry) {
      if (blockPos2.equals(blockPos)) {
         return false;
      } else {
         EnumFacingXType enumFacingXType = ((EnumFacing)blockState.getComparable(AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL)))
            .getEnumFacingXType();
         boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
         EnumFacingXType enumFacingXType2;
         if (!blockPos2.getBlockPosAsBlockPos().equals(blockPos) && !blockPos2.getBlockPos2().equals(blockPos)) {
            if (!blockPos2.getBlockPos3().equals(blockPos) && !blockPos2.getBlockPos().equals(blockPos)) {
               return true;
            }

            enumFacingXType2 = EnumFacingXType.X;
         } else {
            enumFacingXType2 = EnumFacingXType.Z;
         }

         return enumFacingXType == enumFacingXType2 == comparable;
      }
   }

   static double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3, IBlockState blockState, boolean flag) {
      Block block = blockState.getBlock();
      if (!matchesItemStackEntry2(itemStackEntry, count, count2, count3, blockState)) {
         if (block instanceof BlockLiquid) {
            return 1000000.0;
         } else {
            double offset = itemStackEntry.computeValue(count, count2, count3, blockState);
            if (offset >= 1000000.0) {
               return 1000000.0;
            } else if (matchesHeuristicManagerByHeuristicManager(itemStackEntry.heuristicManager, count, count2, count3, blockState)) {
               return 1000000.0;
            } else {
               double offset2 = itemStackEntry.depthFirstPolicy.computeValue(blockState);
               if (offset2 <= 0.0) {
                  return 1000000.0;
               } else {
                  double offset3 = (1.0 / offset2 + itemStackEntry.value2) * offset;
                  if (flag) {
                     IBlockState blockState2 = itemStackEntry.resolveIBlockState(count, count2 + 1, count3);
                     if (blockState2.getBlock() instanceof CustomBlockAdapter) {
                        offset3 += computeValue(itemStackEntry, count, count2 + 1, count3, blockState2, true);
                     }
                  }

                  return offset3;
               }
            }
         }
      } else {
         return 0.0;
      }
   }

   static boolean matchesHeuristicManager6(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      return blockState.getBlock().matchesIBlockAccess(heuristicManager.iBlockAccess, heuristicManager.auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count, count2, count3));
   }

   static boolean matchesItemStackEntryByItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      return itemStackEntry.breadthFirstPolicy
         .matchesHeuristicManagerByHeuristicManager(itemStackEntry.heuristicManager, count, count2, count3, itemStackEntry.resolveIBlockState(count, count2, count3));
   }

   static double computeValue(ItemStackEntry itemStackEntry, int count, int count2, int count3, boolean flag) {
      return computeValue(itemStackEntry, count, count2, count3, itemStackEntry.resolveIBlockState(count, count2, count3), flag);
   }

   static boolean matchesItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3) {
      return matchesItemStackEntryByItemStackEntry(itemStackEntry, count, count2, count3, itemStackEntry.resolveIBlockState(count, count2, count3));
   }

   static boolean matchesBlock3(Block block) {
      return block == Blocks.air || block == Blocks.blockFlowingLava2 || block == Blocks.blockFlowingLava || block == Blocks.blockLava2;
   }

   static boolean matchesItemStackEntry2(ItemStackEntry itemStackEntry, int count, int count2, int count3, IBlockState blockState) {
      return itemStackEntry.breadthFirstPolicy.matchesHeuristicManagerByHeuristicManager(itemStackEntry.heuristicManager, count, count2, count3, blockState);
   }

   static boolean matchesStaticListener(StaticListener staticListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, IBlockState blockState) {
      return matchesHeuristicManager(new HeuristicManager(staticListener), betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10, blockState);
   }

   static boolean matchesHeuristicManager4(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      YESMAYBEEnum yESMAYBEEnum = resolveYESMAYBEEnum(blockState);
      if (yESMAYBEEnum == YESMAYBEEnum.YES) {
         return true;
      } else {
         return yESMAYBEEnum == YESMAYBEEnum.NO ? false : matchesHeuristicManager5(heuristicManager, count, count2, count3, blockState);
      }
   }

   static boolean matchesStaticListener(StaticListener staticListener, BlockPos blockPos) {
      return matchesHeuristicManager(new HeuristicManager(staticListener), blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   static boolean matchesHeuristicManager(HeuristicManager heuristicManager, BlockPos blockPos) {
      return matchesHeuristicManager2(heuristicManager, blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   static boolean matchesHeuristicManager(HeuristicManager heuristicManager, int count, int count2, int count3, boolean flag) {
      IBlockState blockState = heuristicManager.resolveIBlockState(count, count2, count3);
      Block block = blockState.getBlock();
      if (!flag
         && block instanceof CustomBlockAdapter
         && (Boolean)BaritoneManager.getSettings().avoidUpdatingFallingBlocks.dataRef
         && CustomBlockAdapter.matchesIBlockState(heuristicManager.resolveIBlockState(count, count2 - 1, count3))) {
         return true;
      } else if (block instanceof BlockLiquid) {
         if (flag || (Boolean)BaritoneManager.getSettings().strictLiquidCheck.dataRef) {
            return true;
         } else {
            return (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0
               ? true
               : !(heuristicManager.resolveIBlockState(count, count2 - 1, count3).getBlock() instanceof BlockLiquid);
         }
      } else {
         return false;
      }
   }

   static boolean matchesIBlockStateByIBlockState(int count, int count2, int count3, IBlockState blockState, HeuristicManager heuristicManager) {
      if (!(blockState.getBlock() instanceof BlockLiquid)) {
         return false;
      } else {
         return (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0
            ? true
            : matchesIBlockState(heuristicManager.resolveIBlockState(count + 1, count2, count3))
               || matchesIBlockState(heuristicManager.resolveIBlockState(count - 1, count2, count3))
               || matchesIBlockState(heuristicManager.resolveIBlockState(count, count2, count3 + 1))
               || matchesIBlockState(heuristicManager.resolveIBlockState(count, count2, count3 - 1));
      }
   }

   static boolean matchesHeuristicManagerByHeuristicManager(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      if (!heuristicManager.directCustom.checkIndex(count, count3)) {
         return true;
      } else {
         Block block = blockState.getBlock();
         return ((List)BaritoneManager.getSettings().blocksToDisallowBreaking.dataRef).contains(block)
            || block == Blocks.block53
            || block instanceof BlockMonsterEgg
            || matchesHeuristicManager(heuristicManager, count, count2 + 1, count3, true)
            || matchesHeuristicManager(heuristicManager, count + 1, count2, count3, false)
            || matchesHeuristicManager(heuristicManager, count - 1, count2, count3, false)
            || matchesHeuristicManager(heuristicManager, count, count2, count3 + 1, false)
            || matchesHeuristicManager(heuristicManager, count, count2, count3 - 1, false);
      }
   }

   static boolean matchesHeuristicManager5(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      Block block = blockState.getBlock();
      if (block == Blocks.block141) {
         return matchesHeuristicManager(heuristicManager, count, count2 - 1, count3);
      } else if (block instanceof BlockSnowLayer) {
         if (!heuristicManager.checkIndex(count, count3)) {
            return true;
         } else {
            return (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) >= 3 ? false : matchesHeuristicManager(heuristicManager, count, count2 - 1, count3);
         }
      } else if (!(block instanceof BlockLiquid)) {
         return block.matchesIBlockAccess(heuristicManager.iBlockAccess, heuristicManager.auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(count, count2, count3));
      } else if (matchesIBlockStateByIBlockState(count, count2, count3, blockState, heuristicManager)) {
         return false;
      } else if ((Boolean)BaritoneManager.getSettings().assumeWalkOnWater.dataRef) {
         return false;
      } else {
         IBlockState blockState2 = heuristicManager.resolveIBlockState(count, count2 + 1, count3);
         return !(blockState2.getBlock() instanceof BlockLiquid) && !(blockState2.getBlock() instanceof BlockWaterlily)
            ? block == Blocks.blockLava2 || block == Blocks.blockFlowingLava
            : false;
      }
   }

   static YESMAYBEEnum resolveYESMAYBEEnum2(IBlockState blockState) {
      Block block = blockState.getBlock();
      if (blockState.getBlock().isActive4()) {
         return YESMAYBEEnum.YES;
      } else if (block != Blocks.block81 && (block != Blocks.block144 || !(Boolean)BaritoneManager.getSettings().allowVines.dataRef)) {
         if (block == Blocks.block7) {
            return YESMAYBEEnum.YES;
         } else if (block == Blocks.block60 || block == Blocks.blockTrappedChest || block == Blocks.block128) {
            return YESMAYBEEnum.YES;
         } else if (block == Blocks.block68 || block == Blocks.blockStainedGlass) {
            return YESMAYBEEnum.YES;
         } else if (block instanceof BlockRedSandstoneStairs) {
            return YESMAYBEEnum.YES;
         } else if (matchesBlock(block)) {
            return YESMAYBEEnum.MAYBE;
         } else if (matchesBlock2(block) && (Boolean)BaritoneManager.getSettings().assumeWalkOnLava.dataRef) {
            return YESMAYBEEnum.MAYBE;
         } else if (!(block instanceof ExtraBlockAdapter)) {
            return YESMAYBEEnum.NO;
         } else if ((Boolean)BaritoneManager.getSettings().allowWalkOnBottomSlab.dataRef) {
            return YESMAYBEEnum.YES;
         } else if (((ExtraBlockAdapter)block).isActive5()) {
            return YESMAYBEEnum.YES;
         } else {
            return blockState.getComparable(ExtraBlockAdapter.immutableSetEntry) != MovementModeProperty.BOTTOM ? YESMAYBEEnum.YES : YESMAYBEEnum.NO;
         }
      } else {
         return YESMAYBEEnum.YES;
      }
   }

   static boolean matchesStaticListenerByStaticListener(StaticListener staticListener, BlockPos blockPos) {
      return matchesHeuristicManager(new HeuristicManager(staticListener), blockPos);
   }

   static boolean matchesBlockByBlock(Block block) {
      return block instanceof BlockLiquid || block == Blocks.blockCactus || block == Blocks.blockFire || block == Blocks.block2 || block == Blocks.web;
   }

   static boolean matchesStaticListenerByStaticListener(StaticListener staticListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      return matchesHeuristicManagerByHeuristicManager(new HeuristicManager(staticListener), betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10);
   }

   static void applyStaticListener(StaticListener staticListener, HeuristicPlanner heuristicPlanner, BlockPos blockPos) {
      heuristicPlanner.withAStarHandler(
            new AStarHandler(
               BreadthFirstRecorder.resolveDefaultCustom(staticListener.getVecAsVec3(), Vec3Helper.resolveVec3(blockPos), staticListener.getDefaultCustom())
                  .resolveDefaultCustom(staticListener.getDefaultCustom().getFactor2()),
               false
            )
         )
         .resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
   }

   static boolean matchesHeuristicManager(HeuristicManager heuristicManager, int count, int count2, int count3, IBlockState blockState) {
      YESMAYBEEnum yESMAYBEEnum = resolveYESMAYBEEnum2(blockState);
      if (yESMAYBEEnum == YESMAYBEEnum.YES) {
         return true;
      } else {
         return yESMAYBEEnum == YESMAYBEEnum.NO ? false : matchesHeuristicManager2(heuristicManager, count, count2, count3, blockState);
      }
   }

   static boolean matchesItemStackEntryByItemStackEntry(ItemStackEntry itemStackEntry, int count, int count2, int count3, IBlockState blockState) {
      return itemStackEntry.breadthFirstPolicy.matchesHeuristicManager2(itemStackEntry.heuristicManager, count, count2, count3, blockState);
   }

   static boolean matchesStaticListener(StaticListener staticListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      return matchesHeuristicManager(new HeuristicManager(staticListener), betterBlockPosXSYSZSBlockPos.count9, betterBlockPosXSYSZSBlockPos.count12, betterBlockPosXSYSZSBlockPos.count10);
   }
}
