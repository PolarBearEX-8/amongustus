package client.util;

import client.setting.MovementFactorProperty;
import client.setting.MovementProfileProperty;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.SpecialBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldHolder {
   public final World world;
   public final BlockPos blockPos;
   public final SpecialBlockAdapter specialBlockAdapter;
   public final boolean enabled;
   public final List list;
   public IBlockState iBlockState;
   public final SpecialBlockAdapter specialBlockAdapter2;

   private void applyMovementProfileProperty(MovementProfileProperty movementProfileProperty) {
      this.list.clear();
      switch (DampenerSystem.countArray[movementProfileProperty.ordinal()]) {
         case 1:
            this.list.add(this.blockPos.getBlockPosAsBlockPos());
            this.list.add(this.blockPos.getBlockPos2());
            break;
         case 2:
            this.list.add(this.blockPos.getBlockPos());
            this.list.add(this.blockPos.getBlockPos3());
            break;
         case 3:
            this.list.add(this.blockPos.getBlockPos());
            this.list.add(this.blockPos.getBlockPos3().up());
            break;
         case 4:
            this.list.add(this.blockPos.getBlockPos().up());
            this.list.add(this.blockPos.getBlockPos3());
            break;
         case 5:
            this.list.add(this.blockPos.getBlockPosAsBlockPos().up());
            this.list.add(this.blockPos.getBlockPos2());
            break;
         case 6:
            this.list.add(this.blockPos.getBlockPosAsBlockPos());
            this.list.add(this.blockPos.getBlockPos2().up());
            break;
         case 7:
            this.list.add(this.blockPos.getBlockPos3());
            this.list.add(this.blockPos.getBlockPos2());
            break;
         case 8:
            this.list.add(this.blockPos.getBlockPos());
            this.list.add(this.blockPos.getBlockPos2());
            break;
         case 9:
            this.list.add(this.blockPos.getBlockPos());
            this.list.add(this.blockPos.getBlockPosAsBlockPos());
            break;
         case 10:
            this.list.add(this.blockPos.getBlockPos3());
            this.list.add(this.blockPos.getBlockPosAsBlockPos());
      }
   }

   private void updateState() {
      for (int index = 0; index < this.list.size(); index++) {
         WorldHolder worldHolder = this.resolveWorldHolder((BlockPos)this.list.get(index));
         if (worldHolder != null && worldHolder.matchesWorldHolder(this)) {
            this.list.set(index, worldHolder.blockPos);
         } else {
            this.list.remove(index--);
         }
      }
   }

   public int getCount() {
      int count = 0;

      for (EnumFacing enumFacing : (Iterable<EnumFacing>) (Iterable) (MovementFactorProperty.HORIZONTAL)) {
         if (this.matchesBlockPosByBlockPos(this.blockPos.offset(enumFacing))) {
            count++;
         }
      }

      return count;
   }

   private boolean matchesWorldHolder(WorldHolder worldHolder) {
      return this.matchesBlockPos(worldHolder.blockPos);
   }

   public WorldHolder(SpecialBlockAdapter specialBlockAdapter3, World world2, BlockPos blockPos2, IBlockState blockState) {
      this.specialBlockAdapter2 = specialBlockAdapter3;
      this.list = Lists.newArrayList();
      this.world = world2;
      this.blockPos = blockPos2;
      this.iBlockState = blockState;
      this.specialBlockAdapter = (SpecialBlockAdapter)blockState.getBlock();
      MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(specialBlockAdapter3.getIProperty());
      this.enabled = this.specialBlockAdapter.enabled;
      this.applyMovementProfileProperty(comparable);
   }

   private boolean matchesBlockPos2(BlockPos blockPos2) {
      WorldHolder worldHolder = this.resolveWorldHolder(blockPos2);
      if (worldHolder == null) {
         return false;
      } else {
         worldHolder.updateState();
         return worldHolder.matchesWorldHolderByWorldHolder(this);
      }
   }

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos2) {
      return SpecialBlockAdapter.matchesWorld3(this.world, blockPos2)
         || SpecialBlockAdapter.matchesWorld3(this.world, blockPos2.up())
         || SpecialBlockAdapter.matchesWorld3(this.world, blockPos2.down());
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   private boolean matchesWorldHolderByWorldHolder(WorldHolder worldHolder) {
      return this.matchesWorldHolder(worldHolder) || this.list.size() != 2;
   }

   private boolean matchesBlockPos(BlockPos blockPos2) {
      for (int index = 0; index < this.list.size(); index++) {
         BlockPos blockPos3 = (BlockPos)this.list.get(index);
         if (blockPos3.getX() == blockPos2.getX() && blockPos3.getZ() == blockPos2.getZ()) {
            return true;
         }
      }

      return false;
   }

   private void applyWorldHolder(WorldHolder worldHolder) {
      this.list.add(worldHolder.blockPos);
      BlockPos blockPosAsBlockPos = this.blockPos.getBlockPosAsBlockPos();
      BlockPos blockPos2 = this.blockPos.getBlockPos2();
      BlockPos blockPos3 = this.blockPos.getBlockPos();
      BlockPos blockPos4 = this.blockPos.getBlockPos3();
      boolean flag = this.matchesBlockPos(blockPosAsBlockPos);
      boolean flag2 = this.matchesBlockPos(blockPos2);
      boolean flag3 = this.matchesBlockPos(blockPos3);
      boolean flag4 = this.matchesBlockPos(blockPos4);
      MovementProfileProperty movementProfileProperty = null;
      if (flag || flag2) {
         movementProfileProperty = MovementProfileProperty.NORTH_SOUTH;
      }

      if (flag3 || flag4) {
         movementProfileProperty = MovementProfileProperty.EAST_WEST;
      }

      if (!this.enabled) {
         if (flag2 && flag4 && !flag && !flag3) {
            movementProfileProperty = MovementProfileProperty.SOUTH_EAST;
         }

         if (flag2 && flag3 && !flag && !flag4) {
            movementProfileProperty = MovementProfileProperty.SOUTH_WEST;
         }

         if (flag && flag3 && !flag2 && !flag4) {
            movementProfileProperty = MovementProfileProperty.NORTH_WEST;
         }

         if (flag && flag4 && !flag2 && !flag3) {
            movementProfileProperty = MovementProfileProperty.NORTH_EAST;
         }
      }

      if (movementProfileProperty == MovementProfileProperty.NORTH_SOUTH) {
         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPosAsBlockPos.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_NORTH;
         }

         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos2.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_SOUTH;
         }
      }

      if (movementProfileProperty == MovementProfileProperty.EAST_WEST) {
         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos4.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_EAST;
         }

         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos3.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_WEST;
         }
      }

      if (movementProfileProperty == null) {
         movementProfileProperty = MovementProfileProperty.NORTH_SOUTH;
      }

      this.iBlockState = this.iBlockState.resolveIBlockState(this.specialBlockAdapter.getIProperty(), movementProfileProperty);
      this.world.matchesBlockPos(this.blockPos, this.iBlockState, 3);
   }

   public WorldHolder resolveWorldHolder(boolean flag, boolean flag2) {
      BlockPos blockPosAsBlockPos = this.blockPos.getBlockPosAsBlockPos();
      BlockPos blockPos2 = this.blockPos.getBlockPos2();
      BlockPos blockPos3 = this.blockPos.getBlockPos();
      BlockPos blockPos4 = this.blockPos.getBlockPos3();
      boolean flag3 = this.matchesBlockPos2(blockPosAsBlockPos);
      boolean flag4 = this.matchesBlockPos2(blockPos2);
      boolean flag5 = this.matchesBlockPos2(blockPos3);
      boolean flag6 = this.matchesBlockPos2(blockPos4);
      MovementProfileProperty movementProfileProperty = null;
      if ((flag3 || flag4) && !flag5 && !flag6) {
         movementProfileProperty = MovementProfileProperty.NORTH_SOUTH;
      }

      if ((flag5 || flag6) && !flag3 && !flag4) {
         movementProfileProperty = MovementProfileProperty.EAST_WEST;
      }

      if (!this.enabled) {
         if (flag4 && flag6 && !flag3 && !flag5) {
            movementProfileProperty = MovementProfileProperty.SOUTH_EAST;
         }

         if (flag4 && flag5 && !flag3 && !flag6) {
            movementProfileProperty = MovementProfileProperty.SOUTH_WEST;
         }

         if (flag3 && flag5 && !flag4 && !flag6) {
            movementProfileProperty = MovementProfileProperty.NORTH_WEST;
         }

         if (flag3 && flag6 && !flag4 && !flag5) {
            movementProfileProperty = MovementProfileProperty.NORTH_EAST;
         }
      }

      if (movementProfileProperty == null) {
         if (flag3 || flag4) {
            movementProfileProperty = MovementProfileProperty.NORTH_SOUTH;
         }

         if (flag5 || flag6) {
            movementProfileProperty = MovementProfileProperty.EAST_WEST;
         }

         if (!this.enabled) {
            if (flag) {
               if (flag4 && flag6) {
                  movementProfileProperty = MovementProfileProperty.SOUTH_EAST;
               }

               if (flag5 && flag4) {
                  movementProfileProperty = MovementProfileProperty.SOUTH_WEST;
               }

               if (flag6 && flag3) {
                  movementProfileProperty = MovementProfileProperty.NORTH_EAST;
               }

               if (flag3 && flag5) {
                  movementProfileProperty = MovementProfileProperty.NORTH_WEST;
               }
            } else {
               if (flag3 && flag5) {
                  movementProfileProperty = MovementProfileProperty.NORTH_WEST;
               }

               if (flag6 && flag3) {
                  movementProfileProperty = MovementProfileProperty.NORTH_EAST;
               }

               if (flag5 && flag4) {
                  movementProfileProperty = MovementProfileProperty.SOUTH_WEST;
               }

               if (flag4 && flag6) {
                  movementProfileProperty = MovementProfileProperty.SOUTH_EAST;
               }
            }
         }
      }

      if (movementProfileProperty == MovementProfileProperty.NORTH_SOUTH) {
         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPosAsBlockPos.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_NORTH;
         }

         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos2.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_SOUTH;
         }
      }

      if (movementProfileProperty == MovementProfileProperty.EAST_WEST) {
         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos4.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_EAST;
         }

         if (SpecialBlockAdapter.matchesWorld3(this.world, blockPos3.up())) {
            movementProfileProperty = MovementProfileProperty.ASCENDING_WEST;
         }
      }

      if (movementProfileProperty == null) {
         movementProfileProperty = MovementProfileProperty.NORTH_SOUTH;
      }

      this.applyMovementProfileProperty(movementProfileProperty);
      this.iBlockState = this.iBlockState.resolveIBlockState(this.specialBlockAdapter.getIProperty(), movementProfileProperty);
      if (flag2 || this.world.getBlockState(this.blockPos) != this.iBlockState) {
         this.world.matchesBlockPos(this.blockPos, this.iBlockState, 3);

         for (int index = 0; index < this.list.size(); index++) {
            WorldHolder worldHolder = this.resolveWorldHolder((BlockPos)this.list.get(index));
            if (worldHolder != null) {
               worldHolder.updateState();
               if (worldHolder.matchesWorldHolderByWorldHolder(this)) {
                  worldHolder.applyWorldHolder(this);
               }
            }
         }
      }

      return this;
   }

   private WorldHolder resolveWorldHolder(BlockPos blockPos2) {
      IBlockState blockState = this.world.getBlockState(blockPos2);
      if (SpecialBlockAdapter.matchesIBlockState(blockState)) {
         SpecialBlockAdapter specialBlockAdapter3 = this.specialBlockAdapter2;
         this.specialBlockAdapter2.getClass();
         return new WorldHolder(specialBlockAdapter3, this.world, blockPos2, blockState);
      } else {
         BlockPos blockPos3 = blockPos2.up();
         IBlockState blockState2 = this.world.getBlockState(blockPos3);
         if (SpecialBlockAdapter.matchesIBlockState(blockState2)) {
            SpecialBlockAdapter specialBlockAdapter4 = this.specialBlockAdapter2;
            this.specialBlockAdapter2.getClass();
            return new WorldHolder(specialBlockAdapter4, this.world, blockPos3, blockState2);
         } else {
            BlockPos blockPos4 = blockPos2.down();
            IBlockState blockState3 = this.world.getBlockState(blockPos4);
            WorldHolder worldHolder;
            if (SpecialBlockAdapter.matchesIBlockState(blockState3)) {
               SpecialBlockAdapter specialBlockAdapter5 = this.specialBlockAdapter2;
               this.specialBlockAdapter2.getClass();
               worldHolder = new WorldHolder(specialBlockAdapter5, this.world, blockPos4, blockState3);
            } else {
               worldHolder = null;
            }

            return worldHolder;
         }
      }
   }
}
