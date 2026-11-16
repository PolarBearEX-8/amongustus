package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.setting.VClipDirection;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.util.SmoothPool;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIronTrapdoor extends Block {
   public static final AutoWalkPath autoWalkPath;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public static final ImmutableSetEntry immutableSetEntry;

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   public static int ordinal(EnumFacing enumFacing) {
      switch (SmoothPool.countArray[enumFacing.ordinal()]) {
         case 1:
            return 0;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
         default:
            return 3;
      }
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return true;
   }

   @Override
   public AxisAlignedBB processAction(World world, BlockPos blockPos) {
      this.processAction(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
      return super.processAction(world, blockPos);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      this.applyIBlockState(iBlockAccess.getIBlockState(count, count2, count3));
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return !(Boolean)iBlockAccess.getIBlockState(count, count2, count3).getComparable(internalImmutableSetEntry);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, resolveEnumFacing(count))
         .resolveIBlockState(internalImmutableSetEntry, (count & 4) != 0)
         .resolveIBlockState(immutableSetEntry, (count & 8) == 0 ? VClipDirection.BOTTOM : VClipDirection.TOP);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.state) {
         BlockPos blockPos2 = blockPos.offset(((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5());
         if (!matchesBlockByBlock(world.getBlockState(blockPos2).getBlock())) {
            world.matchesBlockPos8(blockPos);
            this.processAction(world, blockPos, blockState, 0);
         } else {
            boolean flag = world.matchesBlockPos7(blockPos);
            if ((flag || block.processAction7()) && (Boolean)blockState.getComparable(internalImmutableSetEntry) != flag) {
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, flag), 2);
               world.applyEntityPlayerByEntityPlayer((EntityPlayer)null, flag ? 1003 : 1006, blockPos, 0);
            }
         }
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      IBlockState defaultState = this.getDefaultState();
      if (enumFacing.getEnumFacingXType().isActiveAsFlag()) {
         defaultState = defaultState.resolveIBlockState(autoWalkPath, enumFacing)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, posY > 0.5F ? VClipDirection.TOP : VClipDirection.BOTTOM);
      }

      return defaultState;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (this.coreItemEntry != Material.material5) {
         IBlockState blockState2 = blockState.resolveIBlockState(internalImmutableSetEntry);
         world.matchesBlockPos(blockPos, blockState2, 2);
         world.applyEntityPlayerByEntityPlayer(player, ((Boolean)blockState2.getComparable(internalImmutableSetEntry)) ? 1003 : 1006, blockPos, 0);
      }

      return true;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return !(Boolean)iBlockAccess.getBlockState(blockPos).getComparable(internalImmutableSetEntry);
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return !enumFacing.getEnumFacingXType().isActive() && matchesBlockByBlock(world.getBlockState(blockPos.offset(enumFacing.getEnumFacing5())).getBlock());
   }

   public void applyIBlockState(IBlockState blockState) {
      if (blockState.getBlock() == this) {
         boolean flag = blockState.getComparable(immutableSetEntry) == VClipDirection.TOP;
         Boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
         EnumFacing comparable2 = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (flag) {
            this.setBlockBounds(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
         }

         if (comparable) {
            if (comparable2 == EnumFacing.NORTH) {
               this.setBlockBounds(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
            }

            if (comparable2 == EnumFacing.SOUTH) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
            }

            if (comparable2 == EnumFacing.WEST) {
               this.setBlockBounds(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if (comparable2 == EnumFacing.EAST) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
            }
         }
      }
   }

   private static boolean matchesBlockByBlock(Block block) {
      return block.coreItemEntry.isActive3() && block.processAction14()
         || block == Blocks.block77
         || block instanceof ExtraBlockAdapter
         || block instanceof BlockRedSandstoneStairs;
   }

   public static EnumFacing resolveEnumFacing(int count) {
      switch (count & 3) {
         case 0:
            return EnumFacing.NORTH;
         case 1:
            return EnumFacing.SOUTH;
         case 2:
            return EnumFacing.WEST;
         case 3:
         default:
            return EnumFacing.EAST;
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry, immutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ordinal((EnumFacing)blockState.getComparable(autoWalkPath));
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 4;
      }

      if (blockState.getComparable(immutableSetEntry) == VClipDirection.TOP) {
         count |= 8;
      }

      return count;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      return super.processAction(world, blockState, count, count2, count3);
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

   public BlockIronTrapdoor(Material material) {
      super(material);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, VClipDirection.BOTTOM)
      );
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.processAction(ItemGroupManager.itemGroupManager9);
   }

   static {
      String[] textArray = new String[]{"facing", "open", "half"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[0], MovementFactorProperty.HORIZONTAL);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[2], VClipDirection.class);
   }
}
