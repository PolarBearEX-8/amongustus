package net.minecraft.block;

import client.util.AutoWalkPath;
import client.util.DynamicIntArrayEntry;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class InternalBlockAdapter extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");
   public final boolean enabled;

   @Override
   public void processAction2() {
      float factor = 0.1875F;
      float factor2 = 0.125F;
      float factor3 = 0.125F;
      this.setBlockBounds(0.5F - factor, 0.5F - factor2, 0.5F - factor3, 0.5F + factor, 0.5F + factor2, 0.5F + factor3);
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && (Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         if (this.enabled) {
            this.applyWorld4(world, blockPos, blockState);
         } else {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
            this.applyWorldByWorld(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath));
            world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
            world.applyBlockPos(blockPos, blockPos);
         }
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIBlockState(iBlockAccess.getIBlockState(count, count2, count3));
   }

   private void applyIBlockState(IBlockState blockState) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      float factor = (float)(((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 2) / 16.0F;
      switch (DynamicIntArrayEntry.countArray[comparable.ordinal()]) {
         case 1:
            this.setBlockBounds(0.0F, 0.375F, 0.3125F, factor, 0.625F, 0.6875F);
            break;
         case 2:
            this.setBlockBounds(1.0F - factor, 0.375F, 0.3125F, 1.0F, 0.625F, 0.6875F);
            break;
         case 3:
            this.setBlockBounds(0.3125F, 0.375F, 0.0F, 0.6875F, 0.625F, factor);
            break;
         case 4:
            this.setBlockBounds(0.3125F, 0.375F, 1.0F - factor, 0.6875F, 0.625F, 1.0F);
            break;
         case 5:
            this.setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 0.0F + factor, 0.625F);
            break;
         case 6:
            this.setBlockBounds(0.3125F, 1.0F - factor, 0.375F, 0.6875F, 1.0F, 0.625F);
      }
   }

   private void applyWorld4(World world, BlockPos blockPos, IBlockState blockState) {
      this.applyIBlockState(blockState);
      boolean flag = !world.resolveList(
            EntityArrow.class,
            new AxisAlignedBB(
               (double)blockPos.getX() + this.coordOffset,
               (double)blockPos.getY() + this.coordOffset11,
               (double)blockPos.getZ() + this.coordOffset2,
               (double)blockPos.getX() + this.coordOffset9,
               (double)blockPos.getY() + this.coordOffset6,
               (double)blockPos.getZ() + this.coordOffset15
            )
         )
         .isEmpty();
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      if (flag && !comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
         this.applyWorldByWorld(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath));
         world.applyBlockPos(blockPos, blockPos);
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
      }

      if (!flag && comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
         this.applyWorldByWorld(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath));
         world.applyBlockPos(blockPos, blockPos);
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
      }

      if (flag) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public IBlockState processAction(int count) {
      EnumFacing enumFacing;
      switch (count & 7) {
         case 0:
            enumFacing = EnumFacing.DOWN;
            break;
         case 1:
            enumFacing = EnumFacing.EAST;
            break;
         case 2:
            enumFacing = EnumFacing.WEST;
            break;
         case 3:
            enumFacing = EnumFacing.SOUTH;
            break;
         case 4:
            enumFacing = EnumFacing.NORTH;
            break;
         case 5:
         default:
            enumFacing = EnumFacing.UP;
      }

      return this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing).resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   @Override
   public int processAction(World world) {
      return this.enabled ? 30 : 20;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!world.state && this.enabled && !(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorld4(world, blockPos, blockState);
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorldByWorld(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath));
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return ((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (this.matchesWorld(world, blockPos, blockState) && !matchesWorld2(world, blockPos, ((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5())) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return matchesWorld2(world, blockPos, enumFacing.getEnumFacing5())
         ? this.getDefaultState().resolveIBlockState(autoWalkPath, enumFacing).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
         : this.getDefaultState().resolveIBlockState(autoWalkPath, EnumFacing.DOWN).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return matchesWorld2(world, blockPos, enumFacing.getEnumFacing5());
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         return true;
      } else {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 3);
         world.applyBlockPos(blockPos, blockPos);
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
         this.applyWorldByWorld(world, blockPos, (EnumFacing)blockState.getComparable(autoWalkPath));
         world.applyBlockPos(blockPos, this, this.processAction(world));
         return true;
      }
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         if (matchesWorld2(world, blockPos, enumFacing)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !(Boolean)blockState.getComparable(internalImmutableSetEntry) ? 0 : (blockState.getComparable(autoWalkPath) == enumFacing ? 15 : 0);
   }

   public static boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      BlockPos blockPos2 = blockPos.offset(enumFacing);
      return enumFacing == EnumFacing.DOWN ? World.matchesIBlockAccess(world, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()) : world.getBlockState(blockPos2).getBlock().isActive2();
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (this.processAction7(world, blockPos)) {
         return true;
      } else {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      }
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, EnumFacing enumFacing) {
      world.applyBlockPosByBlockPos(blockPos, this);
      world.applyBlockPosByBlockPos(blockPos.offset(enumFacing.getEnumFacing5()), this);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      byte byteValue;
      switch (DynamicIntArrayEntry.countArray[((EnumFacing)blockState.getComparable(autoWalkPath)).ordinal()]) {
         case 1:
            byteValue = 1;
            break;
         case 2:
            byteValue = 2;
            break;
         case 3:
            byteValue = 3;
            break;
         case 4:
            byteValue = 4;
            break;
         case 5:
         default:
            byteValue = 5;
            break;
         case 6:
            byteValue = 0;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         byteValue |= 8;
      }

      return byteValue;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
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

   public InternalBlockAdapter(boolean flag) {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager9);
      this.enabled = flag;
   }
}
