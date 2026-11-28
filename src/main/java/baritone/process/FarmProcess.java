package baritone.process;

import net.minecraft.block.BlockPlanks;
import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.api.process.IFarmProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.api.utils.RayTraceUtils;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.MovementHelper;
import baritone.utils.BaritoneProcessHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class FarmProcess extends BaritoneProcessHelper implements IFarmProcess {
   private boolean active;
   private List locations;
   private int tickCount;
   private int range;
   private BlockPos center;
   private static final List FARMLAND_PLANTABLE = Arrays.asList(Items.melon_seeds, Items.wheat_seeds, Items.pumpkin_seeds, Items.potato, Items.carrot);
   private static final List PICKUP_DROPPED = Arrays.asList(
      Items.melon_seeds,
      Items.melon,
      Item.getItemFromBlock(Blocks.melon_block),
      Items.wheat_seeds,
      Items.wheat,
      Items.pumpkin_seeds,
      Item.getItemFromBlock(Blocks.pumpkin),
      Items.potato,
      Items.carrot,
      Items.nether_wart,
      Items.reeds,
      Item.getItemFromBlock(Blocks.cactus)
   );

   @Override
   public boolean isActive() {
      return this.active;
   }

   private boolean readyForHarvest(World world, BlockPos pos, IBlockState state) {
      for (FarmCrop farmCrop : FarmCrop.values()) {
         if (farmCrop.block == state.getBlock()) {
            return farmCrop.readyToHarvest(world, pos, state);
         }
      }

      return false;
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      ArrayList list = new ArrayList();

      for (FarmCrop farmCrop : FarmCrop.values()) {
         list.add(farmCrop.block);
      }

      if ((Boolean)Baritone.settings().replantCrops.value) {
         list.add(Blocks.farmland);
         list.add(Blocks.log);
         if ((Boolean)Baritone.settings().replantNetherWart.value) {
            list.add(Blocks.soul_sand);
         }
      }

      if ((Integer)Baritone.settings().mineGoalUpdateInterval.value != 0 && this.tickCount++ % (Integer)Baritone.settings().mineGoalUpdateInterval.value == 0) {
         Baritone.getThreadPool().execute(() -> this.locations = BaritoneAPI.getProvider().getInstance3().scanChunkRadius2(this.ctx, list, 256, 10, 10));
      }

      if (this.locations == null) {
         return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
      } else {
         ArrayList list2 = new ArrayList();
         ArrayList list3 = new ArrayList();
         ArrayList list4 = new ArrayList();
         ArrayList list5 = new ArrayList();
         ArrayList list6 = new ArrayList();

         for (BlockPos pos : (Iterable<BlockPos>)(this.locations)) {
            if (this.range == 0
               || !(this.distanceTo(new Vec3(pos), new Vec3((double)this.center.getX(), (double)this.center.getY(), (double)this.center.getZ())) > (float)this.range)) {
               IBlockState blockState = this.ctx.world().getBlockState(pos);
               boolean flag3 = this.ctx.world().getBlockState(pos.up()).getBlock() instanceof BlockAir;
               if (blockState.getBlock() == Blocks.farmland) {
                  if (flag3) {
                     list3.add(pos);
                  }
               } else if (blockState.getBlock() == Blocks.soul_sand) {
                  if (flag3) {
                     list5.add(pos);
                  }
               } else if (blockState.getBlock() == Blocks.log) {
                  if (blockState.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.JUNGLE) {
                     for (EnumFacing facing : (Iterable<EnumFacing>)EnumFacing.Plane.HORIZONTAL) {
                        if (this.ctx.world().getBlockState(pos.offset(facing)).getBlock() instanceof BlockAir) {
                           list6.add(pos);
                           break;
                        }
                     }
                  }
               } else if (this.readyForHarvest(this.ctx.world(), pos, blockState)) {
                  list2.add(pos);
               } else if (blockState.getBlock() instanceof IGrowable) {
                  IGrowable block = (IGrowable)blockState.getBlock();
                  if (block.canGrow(this.ctx.world(), pos, blockState, true) && block.canUseBonemeal(this.ctx.world(), this.ctx.world().rand, pos, blockState)) {
                     list4.add(pos);
                  }
               }
            }
         }

         this.baritone.getInputOverrideHandler2().clearAllKeys();

         for (BlockPos pos2 : (Iterable<BlockPos>)(list2)) {
            Optional optional = RotationUtils.reachable(this.ctx, pos2);
            if (optional.isPresent() && flag2) {
               this.baritone.getLookBehavior2().updateTarget((Rotation)optional.get(), true);
               MovementHelper.switchToBestToolFor(this.ctx, this.ctx.world().getBlockState(pos2));
               if (this.ctx.isLookingAt(pos2)) {
                  this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_LEFT, true);
               }

               return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            }
         }

         ArrayList list7 = new ArrayList(list3);
         list7.addAll(list5);

         for (BlockPos pos3 : (Iterable<BlockPos>)(list7)) {
            boolean flag4 = list5.contains(pos3);
            Optional optional2 = RotationUtils.reachableOffset2(
               this.ctx, pos3, new Vec3((double)pos3.getX() + 0.5, (double)(pos3.getY() + 1), (double)pos3.getZ() + 0.5), this.ctx.playerController().getBlockReachDistance(), false
            );
            if (optional2.isPresent()
               && flag2
               && this.baritone
                  .getInventoryBehavior()
                  .throwaway(
                     true,
                     flag4
                        ? (Predicate<ItemStack>)stack -> stack != null && stack.getItem() != null && stack.getItem().equals(Items.nether_wart)
                        : (Predicate<ItemStack>)stack2 -> FARMLAND_PLANTABLE.contains(stack2.getItem())
                  )) {
               MovingObjectPosition hit = RayTraceUtils.rayTraceTowards(this.ctx.player(), (Rotation)optional2.get(), this.ctx.playerController().getBlockReachDistance());
               if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && hit.sideHit == EnumFacing.UP) {
                  this.baritone.getLookBehavior2().updateTarget((Rotation)optional2.get(), true);
                  if (this.ctx.isLookingAt(pos3)) {
                     this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
                  }

                  return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
               }
            }
         }

         for (BlockPos pos4 : (Iterable<BlockPos>)(list6)) {
            for (EnumFacing facing2 : (Iterable<EnumFacing>)EnumFacing.Plane.HORIZONTAL) {
               if (this.ctx.world().getBlockState(pos4.offset(facing2)).getBlock() instanceof BlockAir) {
                  Vec3 vec = new Vec3(pos4).addVector(0.5, 0.5, 0.5).add(new Vec3(facing2.getDirectionVec()).scale(0.5));
                  Optional optional3 = RotationUtils.reachableOffset2(this.ctx, pos4, vec, this.ctx.playerController().getBlockReachDistance(), false);
                  if (optional3.isPresent() && flag2 && this.baritone.getInventoryBehavior().throwaway(true, stack3 -> this.isCocoa(stack3))) {
                     MovingObjectPosition hit2 = RayTraceUtils.rayTraceTowards(this.ctx.player(), (Rotation)optional3.get(), this.ctx.playerController().getBlockReachDistance());
                     if (hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && hit2.sideHit == facing2) {
                        this.baritone.getLookBehavior2().updateTarget((Rotation)optional3.get(), true);
                        if (this.ctx.isLookingAt(pos4)) {
                           this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
                        }

                        return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
                     }
                  }
               }
            }
         }

         for (BlockPos pos5 : (Iterable<BlockPos>)(list4)) {
            Optional optional4 = RotationUtils.reachable(this.ctx, pos5);
            if (optional4.isPresent()
               && flag2
               && this.baritone
                  .getInventoryBehavior()
                  .throwaway(
                     true, stack4 -> stack4 != null && stack4.getItem() instanceof ItemDye && EnumDyeColor.byDyeDamage(stack4.getMetadata()) == EnumDyeColor.WHITE
                  )) {
               this.baritone.getLookBehavior2().updateTarget((Rotation)optional4.get(), true);
               if (this.ctx.isLookingAt(pos5)) {
                  this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
               }

               return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
            }
         }

         if (flag) {
            this.logDirect2("Farm failed");
            if ((Boolean)Baritone.settings().notificationOnFarmFail.value) {
               this.logNotification("Farm failed", true);
            }

            this.onLostControl();
            return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
         } else {
            ArrayList list8 = new ArrayList();

            for (BlockPos pos6 : (Iterable<BlockPos>)(list2)) {
               list8.add(new GoalBreak(pos6));
            }

            if (this.baritone.getInventoryBehavior().throwaway(false, stack5 -> FARMLAND_PLANTABLE.contains(stack5.getItem()))) {
               for (BlockPos pos7 : (Iterable<BlockPos>)(list3)) {
                  list8.add(new GoalBlock(pos7.up()));
               }
            }

            if (this.baritone.getInventoryBehavior().throwaway(false, stack6 -> stack6 != null && stack6.getItem() != null && stack6.getItem().equals(Items.nether_wart))) {
               for (BlockPos pos8 : (Iterable<BlockPos>)(list5)) {
                  list8.add(new GoalBlock(pos8.up()));
               }
            }

            if (this.baritone.getInventoryBehavior().throwaway(false, stack7 -> this.isCocoa(stack7))) {
               for (BlockPos pos9 : (Iterable<BlockPos>)(list6)) {
                  for (EnumFacing facing3 : (Iterable<EnumFacing>)EnumFacing.Plane.HORIZONTAL) {
                     if (this.ctx.world().getBlockState(pos9.offset(facing3)).getBlock() instanceof BlockAir) {
                        list8.add(new GoalGetToBlock(pos9.offset(facing3)));
                     }
                  }
               }
            }

            if (this.baritone
               .getInventoryBehavior()
               .throwaway(false, stack8 -> stack8 != null && stack8.getItem() instanceof ItemDye && EnumDyeColor.byDyeDamage(stack8.getMetadata()) == EnumDyeColor.WHITE)
               )
             {
               for (BlockPos pos10 : (Iterable<BlockPos>)(list4)) {
                  list8.add(new GoalBlock(pos10));
               }
            }

            for (Entity entity : (Iterable<Entity>)(this.ctx.world().loadedEntityList)) {
               if (entity instanceof EntityItem && entity.onGround) {
                  EntityItem entityItem = (EntityItem)entity;
                  if (PICKUP_DROPPED.contains(entityItem.getEntityItem().getItem()) || this.isCocoa(entityItem.getEntityItem())) {
                     list8.add(new GoalBlock(new BlockPos(entity.posX, entity.posY + 0.1, entity.posZ)));
                  }
               }
            }

            return new PathingCommand(new GoalComposite((Goal[])list8.toArray(new Goal[0])), PathingCommandType.SET_GOAL_AND_PATH);
         }
      }
   }

   private boolean isCocoa(ItemStack stack) {
      return stack != null && stack.getItem() instanceof ItemDye && EnumDyeColor.byDyeDamage(stack.getMetadata()) == EnumDyeColor.BROWN;
   }

   @Override
   public String displayName0() {
      return "Farming";
   }

   @Override
   public void farm(int n, BlockPos pos) {
      if (pos == null) {
         this.center = this.baritone.getPlayerContext().playerFeet();
      } else {
         this.center = pos;
      }

      this.range = n;
      this.active = true;
      this.locations = null;
   }

   private float distanceTo(Vec3 vec, Vec3 vec2) {
      return (float)Math.sqrt(vec.squareDistanceTo(vec2));
   }

   public FarmProcess(Baritone baritone2) {
      super(baritone2);
   }

   @Override
   public void onLostControl() {
      this.active = false;
   }



   public static class InnerEntry {
   }
}
