package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandlerAdapter;
import client.event.PreMotionFilter;
import client.setting.LocalOptionType;
import client.setting.MovementFactorProperty;
import client.setting.MovementProperty;
import client.util.IBlockState;
import client.util.MovingObjectType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLog;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class FarmingListener extends INACTIVEHandlerAdapter implements CustomListener {
   public boolean enabled;
   public List list3;
   public int count2;
   public int count;
   public BlockPos blockPos;
   public static final List list2 = Arrays.asList(Items.melonSeeds, Items.wheatSeeds, Items.pumpkinSeeds, Items.potato, Items.carrot);
   public static final List list = Arrays.asList(
      Items.melonSeeds,
      Items.melon,
      Item.processAction2(Blocks.block67),
      Items.wheatSeeds,
      Items.wheat,
      Items.pumpkinSeeds,
      Item.processAction2(Blocks.block146),
      Items.potato,
      Items.carrot,
      Items.netherWart,
      Items.reeds,
      Item.processAction2(Blocks.blockCactus)
   );

   @Override
   public String getText() {
      return "Farming";
   }

   private float computeFactor(Vec3 vec, Vec3 vec2) {
      return (float)Math.sqrt(vec.dotProduct(vec2));
   }

   @Override
   public void updateState() {
      this.enabled = false;
   }

   private boolean matchesItemStack(ItemStack stack) {
      return list2.contains(stack.getItem());
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void applyBlockPos(int count3, BlockPos blockPos2) {
      if (blockPos2 == null) {
         this.blockPos = this.baritoneManager.getStaticListener().getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      } else {
         this.blockPos = blockPos2;
      }

      this.count = count3;
      this.enabled = true;
      this.list3 = null;
   }

   private boolean matchesItemStack3(ItemStack stack) {
      return stack != null && stack.getItem() instanceof ItemDye && LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset()) == LocalOptionType.BROWN;
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      java.util.ArrayList list4 = new java.util.ArrayList();

      for (AStarFilter aStarFilter : AStarFilter.values()) {
         list4.add(aStarFilter.block);
      }

      if ((Boolean)BaritoneManager.getSettings().replantCrops.dataRef) {
         list4.add(Blocks.block7);
         list4.add(Blocks.block140);
         if ((Boolean)BaritoneManager.getSettings().replantNetherWart.dataRef) {
            list4.add(Blocks.block126);
         }
      }

      if ((Integer)BaritoneManager.getSettings().mineGoalUpdateInterval.dataRef != 0
         && this.count2++ % (Integer)BaritoneManager.getSettings().mineGoalUpdateInterval.dataRef == 0) {
         BaritoneManager.getExecutor().execute(() -> this.applyArrayList(list4));
      }

      if (this.list3 == null) {
         return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
      } else {
         java.util.ArrayList list5 = new java.util.ArrayList();
         java.util.ArrayList list6 = new java.util.ArrayList();
         java.util.ArrayList list7 = new java.util.ArrayList();
         java.util.ArrayList list8 = new java.util.ArrayList();
         java.util.ArrayList list9 = new java.util.ArrayList();

         for (BlockPos blockPos2 : (Iterable<BlockPos>) this.list3) {
            if (this.count == 0
               || !(
                  this.computeFactor(new Vec3(blockPos2), new Vec3((double)this.blockPos.getX(), (double)this.blockPos.getY(), (double)this.blockPos.getZ()))
                     > (float)this.count
               )) {
               IBlockState blockState = this.staticListener.getWorld().getBlockState(blockPos2);
               boolean flag3 = this.staticListener.getWorld().getBlockState(blockPos2.up()).getBlock() instanceof BlockAir;
               if (blockState.getBlock() == Blocks.block7) {
                  if (flag3) {
                     list6.add(blockPos2);
                  }
               } else if (blockState.getBlock() == Blocks.block126) {
                  if (flag3) {
                     list8.add(blockPos2);
                  }
               } else if (blockState.getBlock() == Blocks.block140) {
                  if (blockState.getComparable(BlockLog.immutableSetEntry3) == MovementProperty.JUNGLE) {
                     for (EnumFacing enumFacing : (Iterable<EnumFacing>) MovementFactorProperty.HORIZONTAL) {
                        if (this.staticListener.getWorld().getBlockState(blockPos2.offset(enumFacing)).getBlock() instanceof BlockAir) {
                           list9.add(blockPos2);
                           break;
                        }
                     }
                  }
               } else if (this.matchesWorld(this.staticListener.getWorld(), blockPos2, blockState)) {
                  list5.add(blockPos2);
               } else if (blockState.getBlock() instanceof PreMotionFilter) {
                  PreMotionFilter block = (PreMotionFilter)blockState.getBlock();
                  if (block.matchesWorld(this.staticListener.getWorld(), blockPos2, blockState, true)
                     && block.nextFloat(this.staticListener.getWorld(), this.staticListener.getWorld().random, blockPos2, blockState)) {
                     list7.add(blockPos2);
                  }
               }
            }
         }

         this.baritoneManager.getMapEntry().clear();

         for (BlockPos blockPos3 : (Iterable<BlockPos>) list5) {
            Optional optional = BreadthFirstRecorder.resolveOptional(this.staticListener, blockPos3);
            if (optional.isPresent() && flag2) {
               this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional.get(), true);
               AStarPredictor.applyStaticListener(this.staticListener, this.staticListener.getWorld().getBlockState(blockPos3));
               if (this.staticListener.matchesBlockPos(blockPos3)) {
                  this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_LEFT, true);
               }

               return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
            }
         }

         java.util.ArrayList list10 = new java.util.ArrayList(list6);
         list10.addAll(list8);

         for (BlockPos blockPos4 : (Iterable<BlockPos>) list10) {
            boolean flag4 = list8.contains(blockPos4);
            Optional optional2 = BreadthFirstRecorder.resolveOptional(
               this.staticListener,
               blockPos4,
               new Vec3((double)blockPos4.getX() + 0.5, (double)(blockPos4.getY() + 1), (double)blockPos4.getZ() + 0.5),
               this.staticListener.getDefaultListener().getValue(),
               false
            );
            if (optional2.isPresent()
               && flag2
               && this.baritoneManager
                  .getRememberingToMoveManager()
                  .matchesPredicate(true, flag4 ? stack -> this.matchesItemStackByItemStack((ItemStack) stack) : stack -> this.matchesItemStack((ItemStack) stack))) {
               MovingObjectPosition hitResult = CoreCustom.resolveMovingObjectPositionByEntity(
                  this.staticListener.getEntityPlayerSP(), (DefaultCustom)optional2.get(), this.staticListener.getDefaultListener().getValue()
               );
               if (hitResult.typeOfHit == MovingObjectType.BLOCK && hitResult.sideHit == EnumFacing.UP) {
                  this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional2.get(), true);
                  if (this.staticListener.matchesBlockPos(blockPos4)) {
                     this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
                  }

                  return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
               }
            }
         }

         for (BlockPos blockPos5 : (Iterable<BlockPos>) list9) {
            for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) MovementFactorProperty.HORIZONTAL) {
               if (this.staticListener.getWorld().getBlockState(blockPos5.offset(enumFacing2)).getBlock() instanceof BlockAir) {
                  Vec3 vec3 = new Vec3(blockPos5).getVec312(0.5, 0.5, 0.5).getVec36(new Vec3(enumFacing2.getVec3i()).getVec3(0.5));
                  Optional optional3 = BreadthFirstRecorder.resolveOptional(
                     this.staticListener, blockPos5, vec3, this.staticListener.getDefaultListener().getValue(), false
                  );
                  if (optional3.isPresent() && flag2 && this.baritoneManager.getRememberingToMoveManager().matchesPredicate(true, stack -> this.matchesItemStack3((ItemStack) stack))) {
                     MovingObjectPosition hitResult2 = CoreCustom.resolveMovingObjectPositionByEntity(
                        this.staticListener.getEntityPlayerSP(), (DefaultCustom)optional3.get(), this.staticListener.getDefaultListener().getValue()
                     );
                     if (hitResult2.typeOfHit == MovingObjectType.BLOCK && hitResult2.sideHit == enumFacing2) {
                        this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional3.get(), true);
                        if (this.staticListener.matchesBlockPos(blockPos5)) {
                           this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
                        }

                        return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
                     }
                  }
               }
            }
         }

         for (BlockPos blockPos6 : (Iterable<BlockPos>) list7) {
            Optional optional4 = BreadthFirstRecorder.resolveOptional(this.staticListener, blockPos6);
            if (optional4.isPresent() && flag2 && this.baritoneManager.getRememberingToMoveManager().matchesPredicate(true, stack -> this.matchesItemStack2((ItemStack) stack))) {
               this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional4.get(), true);
               if (this.staticListener.matchesBlockPos(blockPos6)) {
                  this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
               }

               return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
            }
         }

         if (flag) {
            this.applyText("Farm failed");
            if ((Boolean)BaritoneManager.getSettings().notificationOnFarmFail.dataRef) {
               this.applyText("Farm failed", true);
            }

            this.updateState();
            return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
         } else {
            java.util.ArrayList list11 = new java.util.ArrayList();

            for (BlockPos blockPos7 : (Iterable<BlockPos>) list5) {
               list11.add(new AStarScanner(blockPos7));
            }

            if (this.baritoneManager.getRememberingToMoveManager().matchesPredicate(false, stack -> this.matchesItemStack((ItemStack) stack))) {
               for (BlockPos blockPos8 : (Iterable<BlockPos>) list6) {
                  list11.add(new DepthFirstResolver(blockPos8.up()));
               }
            }

            if (this.baritoneManager.getRememberingToMoveManager().matchesPredicate(false, stack -> this.matchesItemStackByItemStack((ItemStack) stack))) {
               for (BlockPos blockPos9 : (Iterable<BlockPos>) list8) {
                  list11.add(new DepthFirstResolver(blockPos9.up()));
               }
            }

            if (this.baritoneManager.getRememberingToMoveManager().matchesPredicate(false, stack -> this.matchesItemStack3((ItemStack) stack))) {
               for (BlockPos blockPos10 : (Iterable<BlockPos>) list9) {
                  for (EnumFacing enumFacing3 : (Iterable<EnumFacing>) MovementFactorProperty.HORIZONTAL) {
                     if (this.staticListener.getWorld().getBlockState(blockPos10.offset(enumFacing3)).getBlock() instanceof BlockAir) {
                        list11.add(new AStarManager(blockPos10.offset(enumFacing3)));
                     }
                  }
               }
            }

            if (this.baritoneManager.getRememberingToMoveManager().matchesPredicate(false, stack -> this.matchesItemStack2((ItemStack) stack))) {
               for (BlockPos blockPos11 : (Iterable<BlockPos>) list7) {
                  list11.add(new DepthFirstResolver(blockPos11));
               }
            }

            for (Entity entity : (Iterable<Entity>) this.staticListener.getWorld().loadedEntityList) {
               if (entity instanceof EntityItem && entity.onGround) {
                  EntityItem entityItem = (EntityItem)entity;
                  if (list.contains(entityItem.getItemStack().getItem()) || this.matchesItemStack3(entityItem.getItemStack())) {
                     list11.add(new DepthFirstResolver(new BlockPos(entity.posX, entity.posY + 0.1, entity.posZ)));
                  }
               }
            }

            return new Listener5Entry(new DepthFirstCoordinator((SecondaryListener[]) list11.toArray(new SecondaryListener[0])), HeuristicCoordinator.SET_GOAL_AND_PATH);
         }
      }
   }

   private boolean matchesItemStack2(ItemStack stack) {
      return stack != null && stack.getItem() instanceof ItemDye && LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset()) == LocalOptionType.WHITE;
   }

   private boolean matchesWorld(World world, BlockPos blockPos2, IBlockState blockState) {
      for (AStarFilter aStarFilter : AStarFilter.values()) {
         if (aStarFilter.block == blockState.getBlock()) {
            return aStarFilter.test(world, blockPos2, blockState);
         }
      }

      return false;
   }

   private void applyArrayList(java.util.ArrayList list4) {
      this.list3 = SettingsTxtManager.getExtraListener().getSharedListener().resolveList(this.staticListener, list4, 256, 10, 10);
   }

   private boolean matchesItemStackByItemStack(ItemStack stack) {
      return stack != null && stack.getItem() != null && stack.getItem().equals(Items.netherWart);
   }

   public FarmingListener(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
