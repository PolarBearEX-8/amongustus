package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.MovementProfileProperty;
import client.util.IBlockState;
import client.util.WorldHolder;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class SpecialBlockAdapter extends Block {
   public final boolean enabled;

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, Block block) {
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      MovementProfileProperty movementProfileProperty = iBlockState.getBlock() == this ? (MovementProfileProperty)iBlockState.getComparable(this.getIProperty()) : null;
      if (movementProfileProperty != null && movementProfileProperty.isActive()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      }
   }

   public IBlockState resolveIBlockStateByWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return world.state ? blockState : new WorldHolder(this, world, blockPos, blockState).resolveWorldHolder(world.matchesBlockPos7(blockPos), flag).getIBlockState();
   }

   public static boolean matchesWorld3(World world, BlockPos blockPos) {
      return matchesIBlockState(world.getBlockState(blockPos));
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      if (((MovementProfileProperty)blockState.getComparable(this.getIProperty())).isActive()) {
         world.applyBlockPosByBlockPos(blockPos.up(), this);
      }

      if (this.enabled) {
         world.applyBlockPosByBlockPos(blockPos, this);
         world.applyBlockPosByBlockPos(blockPos.down(), this);
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         MovementProfileProperty comparable = (MovementProfileProperty)blockState.getComparable(this.getIProperty());
         boolean flag = !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
         if (comparable == MovementProfileProperty.ASCENDING_EAST && !World.matchesIBlockAccess(world, blockPos.getX() + 1, blockPos.getY(), blockPos.getZ())) {
            flag = true;
         } else if (comparable == MovementProfileProperty.ASCENDING_WEST && !World.matchesIBlockAccess(world, blockPos.getX() - 1, blockPos.getY(), blockPos.getZ())) {
            flag = true;
         } else if (comparable == MovementProfileProperty.ASCENDING_NORTH && !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1)) {
            flag = true;
         } else if (comparable == MovementProfileProperty.ASCENDING_SOUTH && !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1)) {
            flag = true;
         }

         if (flag) {
            this.processAction(world, blockPos, blockState, 0);
            world.matchesBlockPos8(blockPos);
         } else {
            this.applyWorldByWorld(world, blockPos, blockState, block);
         }
      }
   }

   @Override
   public int processAction8() {
      return 0;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!world.state) {
         IBlockState blockState2 = this.resolveIBlockStateByWorld(world, blockPos, blockState, true);
         if (this.enabled) {
            this.processAction(world, blockPos, blockState2, this);
         }
      }
   }

   public abstract IProperty getIProperty();

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   public static boolean matchesIBlockState(IBlockState blockState) {
      Block block = blockState.getBlock();
      return block == Blocks.block13 || block == Blocks.block50 || block == Blocks.block40 || block == Blocks.block106;
   }

   @Override
   public MovingObjectPosition resolveMovingObjectPositionByWorld(
      World world, int count, int count2, int count3, double posX, double posX2, double posX3, double posX4, double posY, double posZ
   ) {
      this.processAction(world, count, count2, count3);
      return super.resolveMovingObjectPositionByWorld(world, count, count2, count3, posX, posX2, posX3, posX4, posY, posZ);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public SpecialBlockAdapter(boolean flag) {
      super(Material.material22);
      this.enabled = flag;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.processAction(ItemGroupManager.itemGroupManager12);
   }
}
