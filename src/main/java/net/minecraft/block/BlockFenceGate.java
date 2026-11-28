package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFenceGate extends BlockDirectional {
   public static final PropertyBool OPEN = PropertyBool.create("open");
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, OPEN, POWERED, IN_WALL});
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      if ((Boolean)state.getValue(OPEN)) {
         return null;
      } else {
         return ((EnumFacing)state.getValue(FACING)).getAxis() == EnumFacing.Axis.Z
            ? new AxisAlignedBB(
               (double)n, (double)n2, (double)((float)n3 + 0.375F), (double)(n + 1), (double)((float)n2 + 1.5F), (double)((float)n3 + 0.625F)
            )
            : new AxisAlignedBB(
               (double)((float)n + 0.375F), (double)n2, (double)n3, (double)((float)n + 0.625F), (double)((float)n2 + 1.5F), (double)(n3 + 1)
            );
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         n |= 8;
      }

      if ((Boolean)state.getValue(OPEN)) {
         n |= 4;
      }

      return n;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         boolean isBlockPowered = world.isBlockPowered(pos);
         if (isBlockPowered || block.canProvidePower()) {
            if (isBlockPowered && !(Boolean)state.getValue(OPEN) && !(Boolean)state.getValue(POWERED)) {
               world.setBlockState(pos, state.withProperty(OPEN, Boolean.TRUE).withProperty(POWERED, Boolean.TRUE), 2);
               world.playAuxSFXAtEntity((EntityPlayer)null, 1003, pos, 0);
            } else if (!isBlockPowered && (Boolean)state.getValue(OPEN) && (Boolean)state.getValue(POWERED)) {
               world.setBlockState(pos, state.withProperty(OPEN, Boolean.FALSE).withProperty(POWERED, Boolean.FALSE), 2);
               world.playAuxSFXAtEntity((EntityPlayer)null, 1006, pos, 0);
            } else if (isBlockPowered != (Boolean)state.getValue(POWERED)) {
               world.setBlockState(pos, state.withProperty(POWERED, isBlockPowered), 2);
            }
         }
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      if (((EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING)).getAxis() == EnumFacing.Axis.Z) {
         this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
      } else {
         this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if ((Boolean)state.getValue(OPEN)) {
         state = state.withProperty(OPEN, Boolean.FALSE);
         world.setBlockState(pos, state, 2);
      } else {
         EnumFacing facing2 = EnumFacing.fromAngle((double)player.rotationYaw);
         if (state.getValue(FACING) == facing2.getOpposite()) {
            state = state.withProperty(FACING, facing2);
         }

         state = state.withProperty(OPEN, Boolean.TRUE);
         world.setBlockState(pos, state, 2);
      }

      world.playAuxSFXAtEntity(player, (Boolean)state.getValue(OPEN) ? 1003 : 1006, pos, 0);
      return true;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      EnumFacing.Axis axis = ((EnumFacing)state.getValue(FACING)).getAxis();
      if (axis == EnumFacing.Axis.Z
            && (world.getBlockState2(n - 1, n2, n3).getBlock() == Blocks.cobblestone_wall || world.getBlockState2(n + 1, n2, n3).getBlock() == Blocks.cobblestone_wall)
         || axis == EnumFacing.Axis.X
            && (world.getBlockState2(n, n2, n3 - 1).getBlock() == Blocks.cobblestone_wall || world.getBlockState2(n, n2, n3 + 1).getBlock() == Blocks.cobblestone_wall)
         )
       {
         state = state.withProperty(IN_WALL, Boolean.TRUE);
      }

      return state;
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return (Boolean)world.getBlockState2(n, n2, n3).getValue(OPEN);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return true;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState()
         .withProperty(FACING, entity.getHorizontalFacing())
         .withProperty(OPEN, Boolean.FALSE)
         .withProperty(POWERED, Boolean.FALSE)
         .withProperty(IN_WALL, Boolean.FALSE);
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return (Boolean)world.getBlockState(pos).getValue(OPEN);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, EnumFacing.getHorizontal(n))
         .withProperty(OPEN, (n & 4) != 0)
         .withProperty(POWERED, (n & 8) != 0);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).getBlock().getMaterial().isSolid() ? super.canPlaceBlockAt(world, pos) : false;
   }

   public BlockFenceGate(BlockPlanks.EnumType enumType) {
      super(Material.wood, enumType.getMapColor());
      this.setDefaultState(
         this.blockState.getBaseState().withProperty(OPEN, Boolean.FALSE).withProperty(POWERED, Boolean.FALSE).withProperty(IN_WALL, Boolean.FALSE)
      );
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }
}
