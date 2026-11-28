package baritone.pathing.movement;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.pathing.movement.ActionCosts;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.RayTraceUtils;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.VecUtils;
import baritone.api.utils.input.Input;
import baritone.pathing.precompute.Ternary;
import baritone.utils.ToolSet;
import baritone.utils.BlockStateInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public interface MovementHelper extends Helper, ActionCosts {

   static boolean isGatePassable(IPlayerContext playerContext, BlockPos pos, BlockPos pos2) {
      if (pos2.equals(pos)) {
         return false;
      } else {
         IBlockState state = BlockStateInterface.get(playerContext, pos);
         return !(state.getBlock() instanceof BlockFenceGate) ? true : (Boolean)state.getValue(BlockFenceGate.OPEN);
      }
   }

   static boolean isWater(Block block) {
      return block == Blocks.flowing_water || block == Blocks.water;
   }

   static boolean isDoorPassable(BlockPos pos, IBlockState state, BlockPos pos2, PropertyBool propertyBool) {
      if (pos2.equals(pos)) {
         return false;
      } else {
         EnumFacing.Axis axis = ((EnumFacing)state.getValue(PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL))).getAxis();
         boolean value = (Boolean)state.getValue(propertyBool);
         EnumFacing.Axis axis2;
         if (!pos2.north().equals(pos) && !pos2.south().equals(pos)) {
            if (!pos2.east().equals(pos) && !pos2.west().equals(pos)) {
               return true;
            }

            axis2 = EnumFacing.Axis.X;
         } else {
            axis2 = EnumFacing.Axis.Z;
         }

         return axis == axis2 == value;
      }
   }

   @Deprecated
   static boolean isReplacable(int n, int n2, int n3, IBlockState state, BlockStateInterface blockStateInterface) {
      return isReplaceable(n, n2, n3, state, blockStateInterface);
   }

   static boolean mustBeSolidToWalkOn(CalculationContext calculationContext, int n, int n2, int n3, IBlockState state) {
      Block block = state.getBlock();
      if (block != Blocks.ladder && block != Blocks.vine) {
         if (block instanceof BlockLiquid) {
            if (calculationContext.assumeWalkOnWater) {
               return false;
            }

            if (calculationContext.getBlock(n, n2 + 1, n3) instanceof BlockLiquid) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   static Ternary fullyPassableState(IBlockState state) {
      Block block = state.getBlock();
      if (block == Blocks.air) {
         return Ternary.YES;
      } else if (block != Blocks.fire
         && block != Blocks.tripwire
         && block != Blocks.web
         && block != Blocks.vine
         && block != Blocks.ladder
         && block != Blocks.cocoa
         && !(block instanceof BlockDoor)
         && !(block instanceof BlockFenceGate)
         && !(block instanceof BlockSnow)
         && !(block instanceof BlockLiquid)
         && !(block instanceof BlockTrapDoor)
         && !(block instanceof BlockEndPortal)
         && !(block instanceof BlockSkull)) {
         try {
            return block.isPassable(null, null) ? Ternary.YES : Ternary.NO;
         } catch (Throwable e) {
            System.out.println("The block " + state.getBlock().getLocalizedName() + " requires a special case due to the exception " + e.getMessage());
            return Ternary.MAYBE;
         }
      } else {
         return Ternary.NO;
      }
   }

   static boolean canWalkOn(IPlayerContext playerContext, BetterBlockPos betterBlockPos, IBlockState state) {
      return canWalkOn(new BlockStateInterface(playerContext), betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, state);
   }

   static boolean canWalkOn2(IPlayerContext playerContext, BetterBlockPos betterBlockPos) {
      return canWalkOn(new BlockStateInterface(playerContext), betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2);
   }

   static boolean canWalkOn(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      Ternary ternary = canWalkOnState(state);
      if (ternary == Ternary.YES) {
         return true;
      } else {
         return ternary == Ternary.NO ? false : canWalkOnPosition(blockStateInterface, n, n2, n3, state);
      }
   }

   static void switchToBestToolFor(IPlayerContext playerContext, IBlockState state, ToolSet toolSet, boolean flag) {
      if ((Boolean)Baritone.settings().autoTool.value && !(Boolean)Baritone.settings().assumeExternalAutoTool.value) {
         playerContext.player().inventory.currentItem = toolSet.getBestSlot(state.getBlock(), flag);
      }
   }

   static boolean canWalkOn2(CalculationContext calculationContext, int n, int n2, int n3) {
      return canWalkOn2(calculationContext, n, n2, n3, calculationContext.get(n, n2, n3));
   }

   static boolean canPlaceAgainst2(BlockStateInterface blockStateInterface, BlockPos pos) {
      return canPlaceAgainst(blockStateInterface, pos.getX(), pos.getY(), pos.getZ());
   }

   static boolean isWater(IPlayerContext playerContext, BlockPos pos) {
      return isWater(BlockStateInterface.getBlock(playerContext, pos));
   }

   static boolean canWalkThroughPosition(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      Block block = state.getBlock();
      if (block == Blocks.carpet) {
         return canWalkOn(blockStateInterface, n, n2 - 1, n3);
      } else if (block instanceof BlockSnow) {
         if (!blockStateInterface.worldContainsLoadedChunk(n, n3)) {
            return true;
         } else {
            return (Integer)state.getValue(BlockSnow.LAYERS) >= 3 ? false : canWalkOn(blockStateInterface, n, n2 - 1, n3);
         }
      } else if (!(block instanceof BlockLiquid)) {
         return block.isPassable(blockStateInterface.access, blockStateInterface.isPassableBlockPos.set(n, n2, n3));
      } else if (isFlowing(n, n2, n3, state, blockStateInterface)) {
         return false;
      } else if ((Boolean)Baritone.settings().assumeWalkOnWater.value) {
         return false;
      } else {
         IBlockState state2 = blockStateInterface.get0(n, n2 + 1, n3);
         return !(state2.getBlock() instanceof BlockLiquid) && !(state2.getBlock() instanceof BlockLilyPad)
            ? block == Blocks.water || block == Blocks.flowing_water
            : false;
      }
   }

   static boolean avoidAdjacent(BlockStateInterface blockStateInterface, int n, int n2, int n3, boolean flag) {
      IBlockState state = blockStateInterface.get0(n, n2, n3);
      Block block = state.getBlock();
      if (!flag
         && block instanceof BlockFalling
         && (Boolean)Baritone.settings().avoidUpdatingFallingBlocks.value
         && BlockFalling.canFallThrough(blockStateInterface.get0(n, n2 - 1, n3))) {
         return true;
      } else if (block instanceof BlockLiquid) {
         if (!flag && !(Boolean)Baritone.settings().strictLiquidCheck.value) {
            int value = (Integer)state.getValue(BlockLiquid.LEVEL);
            return value == 0 ? true : !(blockStateInterface.get0(n, n2 - 1, n3).getBlock() instanceof BlockLiquid);
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   static boolean canWalkOn(IPlayerContext playerContext, BlockPos pos) {
      return canWalkOn(new BlockStateInterface(playerContext), pos.getX(), pos.getY(), pos.getZ());
   }

   static double getMiningDurationTicks(CalculationContext calculationContext, int n, int n2, int n3, boolean flag) {
      return getMiningDurationTicks(calculationContext, n, n2, n3, calculationContext.get(n, n2, n3), flag);
   }

   static boolean isReplaceable(int n, int n2, int n3, IBlockState state, BlockStateInterface blockStateInterface) {
      Block block = state.getBlock();
      if (block == Blocks.air || isWater(block)) {
         return true;
      } else if (block instanceof BlockSnow) {
         return !blockStateInterface.worldContainsLoadedChunk(n, n3) ? true : (Integer)state.getValue(BlockSnow.LAYERS) == 1;
      } else if (!(block instanceof BlockDoublePlant)) {
         return state.getBlock().getMaterial().isReplaceable();
      } else {
         BlockDoublePlant.EnumPlantType value = (BlockDoublePlant.EnumPlantType)state.getValue(BlockDoublePlant.VARIANT);
         return value == BlockDoublePlant.EnumPlantType.FERN || value == BlockDoublePlant.EnumPlantType.GRASS;
      }
   }

   static Ternary canWalkThroughState(IBlockState state) {
      Block block = state.getBlock();
      if (block == Blocks.air) {
         return Ternary.YES;
      } else if (block != Blocks.fire
         && block != Blocks.tripwire
         && block != Blocks.web
         && block != Blocks.end_portal
         && block != Blocks.cocoa
         && !(block instanceof BlockSkull)
         && !(block instanceof BlockTrapDoor)) {
         if (((List)Baritone.settings().blocksToAvoid.value).contains(block)) {
            return Ternary.NO;
         } else if (!(block instanceof BlockDoor) && !(block instanceof BlockFenceGate)) {
            if (block == Blocks.carpet) {
               return Ternary.MAYBE;
            } else if (block instanceof BlockSnow) {
               return Ternary.MAYBE;
            } else if (block instanceof BlockLiquid) {
               return (Integer)state.getValue(BlockLiquid.LEVEL) != 0 ? Ternary.NO : Ternary.MAYBE;
            } else if (block instanceof BlockCauldron) {
               return Ternary.NO;
            } else {
               try {
                  return block.isPassable(null, null) ? Ternary.YES : Ternary.NO;
               } catch (Throwable e) {
                  System.out.println("The block " + state.getBlock().getLocalizedName() + " requires a special case due to the exception " + e.getMessage());
                  return Ternary.MAYBE;
               }
            }
         } else {
            return block == Blocks.iron_door ? Ternary.NO : Ternary.YES;
         }
      } else {
         return Ternary.NO;
      }
   }

   static boolean isFlowing(int n, int n2, int n3, IBlockState state, BlockStateInterface blockStateInterface) {
      if (!(state.getBlock() instanceof BlockLiquid)) {
         return false;
      } else {
         return (Integer)state.getValue(BlockLiquid.LEVEL) != 0
            ? true
            : possiblyFlowing(blockStateInterface.get0(n + 1, n2, n3))
               || possiblyFlowing(blockStateInterface.get0(n - 1, n2, n3))
               || possiblyFlowing(blockStateInterface.get0(n, n2, n3 + 1))
               || possiblyFlowing(blockStateInterface.get0(n, n2, n3 - 1));
      }
   }

   static boolean isTransparent(Block block) {
      return block == Blocks.air || block == Blocks.flowing_lava || block == Blocks.flowing_water || block == Blocks.water;
   }

   static boolean fullyPassable(CalculationContext calculationContext, int n, int n2, int n3) {
      return fullyPassable(calculationContext, n, n2, n3, calculationContext.get(n, n2, n3));
   }

   static boolean avoidWalkingInto(Block block) {
      return block instanceof BlockLiquid || block == Blocks.cactus || block == Blocks.fire || block == Blocks.end_portal || block == Blocks.web;
   }

   static boolean isDoorPassable(IPlayerContext playerContext, BlockPos pos, BlockPos pos2) {
      if (pos2.equals(pos)) {
         return false;
      } else {
         IBlockState state = BlockStateInterface.get(playerContext, pos);
         return !(state.getBlock() instanceof BlockDoor) ? true : isDoorPassable(pos, state, pos2, BlockDoor.OPEN);
      }
   }

   static boolean canWalkThrough(CalculationContext calculationContext, int n, int n2, int n3) {
      return calculationContext.precomputedData.canWalkThrough(calculationContext.bsi, n, n2, n3, calculationContext.get(n, n2, n3));
   }

   static boolean canWalkOn2(CalculationContext calculationContext, int n, int n2, int n3, IBlockState state) {
      return calculationContext.precomputedData.canWalkOn(calculationContext.bsi, n, n2, n3, state);
   }

   static boolean canWalkOnPosition(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      Block block = state.getBlock();
      if (!isWater(block)) {
         return isLava(block) && !isFlowing(n, n2, n3, state, blockStateInterface) && (Boolean)Baritone.settings().assumeWalkOnLava.value;
      } else {
         Block block2 = blockStateInterface.get0(n, n2 + 1, n3).getBlock();
         if (block2 == Blocks.waterlily || block2 == Blocks.carpet) {
            return true;
         } else {
            return !isFlowing(n, n2, n3, state, blockStateInterface) && block != Blocks.flowing_water
               ? isWater(block2) ^ (Boolean)Baritone.settings().assumeWalkOnWater.value
               : isWater(block2) && !(Boolean)Baritone.settings().assumeWalkOnWater.value;
         }
      }
   }

   static void moveTowards(IPlayerContext playerContext, MovementState movementState, BlockPos pos) {
      movementState.setTarget(new MovementTarget(RotationUtils.calcRotationFromVec3d(playerContext.playerHead(), VecUtils.getBlockPosCenter(pos), playerContext.playerRotations()).withPitch(playerContext.playerRotations().getPitch()), false))
         .setInput(Input.MOVE_FORWARD, true);
   }

   static void switchToBestToolFor(IPlayerContext playerContext, IBlockState state) {
      switchToBestToolFor(playerContext, state, new ToolSet(playerContext.player()), (Boolean)BaritoneAPI.getSettings().preferSilkTouch.value);
   }

   static boolean canPlaceAgainst(BlockStateInterface blockStateInterface, int n, int n2, int n3) {
      return canPlaceAgainst(blockStateInterface, n, n2, n3, blockStateInterface.get0(n, n2, n3));
   }

   static boolean fullyPassablePosition(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      return state.getBlock().isPassable(blockStateInterface.access, blockStateInterface.isPassableBlockPos.set(n, n2, n3));
   }

   static boolean possiblyFlowing(IBlockState state) {
      return state.getBlock() instanceof BlockLiquid && (Integer)(Integer)state.getValue(BlockLiquid.LEVEL) != 0;
   }

   static double getMiningDurationTicks(CalculationContext calculationContext, int n, int n2, int n3, IBlockState state, boolean flag) {
      Block block = state.getBlock();
      if (!canWalkThrough2(calculationContext, n, n2, n3, state)) {
         if (block instanceof BlockLiquid) {
            return 1000000.0;
         } else {
            double d = calculationContext.breakCostMultiplierAt(n, n2, n3, state);
            if (d >= 1000000.0) {
               return 1000000.0;
            } else if (avoidBreaking(calculationContext.bsi, n, n2, n3, state)) {
               return 1000000.0;
            } else {
               double strVsBlock = calculationContext.toolSet.getStrVsBlock(state);
               if (strVsBlock <= 0.0) {
                  return 1000000.0;
               } else {
                  double d2 = 1.0 / strVsBlock;
                  d2 += calculationContext.breakBlockAdditionalCost;
                  d2 *= d;
                  if (flag) {
                     IBlockState state2 = calculationContext.get(n, n2 + 1, n3);
                     if (state2.getBlock() instanceof BlockFalling) {
                        d2 += getMiningDurationTicks(calculationContext, n, n2 + 1, n3, state2, true);
                     }
                  }

                  return d2;
               }
            }
         }
      } else {
         return 0.0;
      }
   }

   static boolean canPlaceAgainst(IPlayerContext playerContext, BlockPos pos) {
      return canPlaceAgainst2(new BlockStateInterface(playerContext), pos);
   }

   static boolean canWalkThrough2(BlockStateInterface blockStateInterface, int n, int n2, int n3) {
      return canWalkThrough(blockStateInterface, n, n2, n3, blockStateInterface.get0(n, n2, n3));
   }

   static boolean isLiquid(IPlayerContext playerContext, BlockPos pos) {
      return BlockStateInterface.getBlock(playerContext, pos) instanceof BlockLiquid;
   }

   static Ternary canWalkOnState(IBlockState state) {
      Block block = state.getBlock();
      if (state.getBlock().isBlockNormalCube()) {
         return Ternary.YES;
      } else if (block != Blocks.ladder && (block != Blocks.vine || !(Boolean)Baritone.settings().allowVines.value)) {
         if (block == Blocks.farmland) {
            return Ternary.YES;
         } else if (block == Blocks.ender_chest || block == Blocks.chest || block == Blocks.trapped_chest) {
            return Ternary.YES;
         } else if (block == Blocks.glass || block == Blocks.stained_glass) {
            return Ternary.YES;
         } else if (block instanceof BlockStairs) {
            return Ternary.YES;
         } else if (isWater(block)) {
            return Ternary.MAYBE;
         } else if (isLava(block) && (Boolean)Baritone.settings().assumeWalkOnLava.value) {
            return Ternary.MAYBE;
         } else if (!(block instanceof BlockSlab)) {
            return Ternary.NO;
         } else if ((Boolean)Baritone.settings().allowWalkOnBottomSlab.value) {
            return Ternary.YES;
         } else if (((BlockSlab)block).isDouble()) {
            return Ternary.YES;
         } else {
            return state.getValue(BlockSlab.HALF) != BlockSlab.EnumBlockHalf.BOTTOM ? Ternary.YES : Ternary.NO;
         }
      } else {
         return Ternary.YES;
      }
   }

   static PlaceResult attemptToPlaceABlock(MovementState movementState, IBaritone baritone, BlockPos pos, boolean flag, boolean flag2) {
      IPlayerContext playerContext = baritone.getPlayerContext();
      Optional optional = RotationUtils.reachable2(playerContext, pos, flag2);
      boolean flag3 = false;
      if (optional.isPresent()) {
         movementState.setTarget(new MovementTarget((Rotation)optional.get(), true));
         flag3 = true;
      }

      for (int n = 0; n < 5; n++) {
         BlockPos pos2 = pos.offset(Movement.HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[n]);
         if (!canPlaceAgainst(playerContext, pos2)) {
            continue;
         }

         if (!((Baritone)baritone).getInventoryBehavior().throwaway(false, pos.getX(), pos.getY(), pos.getZ())) {
            Helper.HELPER.logDebug("bb pls get me some blocks. dirt, netherrack, cobble");
            movementState.setStatus(MovementStatus.UNREACHABLE);
            return PlaceResult.NO_OPTION;
         }

         double d = ((double)(pos.getX() + pos2.getX()) + 1.0) * 0.5;
         double d2 = ((double)(pos.getY() + pos2.getY()) + 0.5) * 0.5;
         double d3 = ((double)(pos.getZ() + pos2.getZ()) + 1.0) * 0.5;
         Rotation rotation = RotationUtils.calcRotationFromVec3d(flag2 ? RayTraceUtils.inferSneakingEyePosition(playerContext.player()) : playerContext.playerHead(), new Vec3(d, d2, d3), playerContext.playerRotations());
         Rotation rotation2 = baritone.getLookBehavior().getAimProcessor().peekRotation(rotation);
         MovingObjectPosition hit = RayTraceUtils.rayTraceTowards(playerContext.player(), rotation2, playerContext.playerController().getBlockReachDistance(), flag2);
         if (hit == null
            || hit.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
            || !hit.getBlockPos().equals(pos2)
            || !hit.getBlockPos().offset(hit.sideHit).equals(pos)) {
            continue;
         }

         movementState.setTarget(new MovementTarget(rotation, true));
         flag3 = true;
         if (!flag) {
            break;
         }
      }

      if (playerContext.getSelectedBlock().isPresent()) {
         BlockPos pos3 = (BlockPos)playerContext.getSelectedBlock().get();
         EnumFacing sideHit = playerContext.objectMouseOver().sideHit;
         if (pos3.equals(pos) || canPlaceAgainst(playerContext, pos3) && pos3.offset(sideHit).equals(pos)) {
            if (flag2) {
               movementState.setInput(Input.SNEAK, true);
            }

            ((Baritone)baritone).getInventoryBehavior().throwaway(true, pos.getX(), pos.getY(), pos.getZ());
            return PlaceResult.READY_TO_PLACE;
         }
      }

      if (flag3) {
         if (flag2) {
            movementState.setInput(Input.SNEAK, true);
         }

         ((Baritone)baritone).getInventoryBehavior().throwaway(true, pos.getX(), pos.getY(), pos.getZ());
         return PlaceResult.ATTEMPTING;
      }

      return PlaceResult.NO_OPTION;
   }

   static boolean canWalkThrough2(CalculationContext calculationContext, int n, int n2, int n3, IBlockState state) {
      return calculationContext.precomputedData.canWalkThrough(calculationContext.bsi, n, n2, n3, state);
   }

   static boolean canWalkThrough(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      Ternary ternary = canWalkThroughState(state);
      if (ternary == Ternary.YES) {
         return true;
      } else {
         return ternary == Ternary.NO ? false : canWalkThroughPosition(blockStateInterface, n, n2, n3, state);
      }
   }

   static boolean isLava(Block block) {
      return block == Blocks.flowing_lava || block == Blocks.lava;
   }

   static boolean fullyPassable(CalculationContext calculationContext, int n, int n2, int n3, IBlockState state) {
      return calculationContext.precomputedData.fullyPassable(calculationContext.bsi, n, n2, n3, state);
   }

   static boolean isBottomSlab(IBlockState state) {
      return state.getBlock() instanceof BlockSlab
         && !((BlockSlab)state.getBlock()).isDouble()
         && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM;
   }

   static boolean fullyPassable(IPlayerContext playerContext, BlockPos pos) {
      IBlockState blockState = playerContext.world().getBlockState(pos);
      Ternary ternary = fullyPassableState(blockState);
      if (ternary == Ternary.YES) {
         return true;
      } else {
         return ternary == Ternary.NO ? false : fullyPassablePosition(new BlockStateInterface(playerContext), pos.getX(), pos.getY(), pos.getZ(), blockState);
      }
   }

   static boolean canPlaceAgainst(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      return !blockStateInterface.worldBorder.canPlaceAt(n, n3)
         ? false
         : state.getBlock().isBlockNormalCube() || state.getBlock().isFullBlock() || state.getBlock() == Blocks.glass || state.getBlock() == Blocks.stained_glass;
   }

   static boolean canWalkThrough(IPlayerContext playerContext, BetterBlockPos betterBlockPos) {
      return canWalkThrough2(new BlockStateInterface(playerContext), betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2);
   }

   static boolean avoidBreaking(BlockStateInterface blockStateInterface, int n, int n2, int n3, IBlockState state) {
      if (!blockStateInterface.worldBorder.canPlaceAt(n, n3)) {
         return true;
      } else {
         Block block = state.getBlock();
         return ((List)Baritone.settings().blocksToDisallowBreaking.value).contains(block)
            || block == Blocks.ice
            || block instanceof BlockSilverfish
            || avoidAdjacent(blockStateInterface, n, n2 + 1, n3, true)
            || avoidAdjacent(blockStateInterface, n + 1, n2, n3, false)
            || avoidAdjacent(blockStateInterface, n - 1, n2, n3, false)
            || avoidAdjacent(blockStateInterface, n, n2, n3 + 1, false)
            || avoidAdjacent(blockStateInterface, n, n2, n3 - 1, false);
      }
   }

   static boolean canWalkOn(BlockStateInterface blockStateInterface, int n, int n2, int n3) {
      return canWalkOn(blockStateInterface, n, n2, n3, blockStateInterface.get0(n, n2, n3));
   }
}
