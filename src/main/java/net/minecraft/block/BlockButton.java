package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockButton extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing");
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   private final boolean wooden;

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if ((Boolean)state.getValue(POWERED)) {
         this.notifyNeighbors(world, pos, (EnumFacing)state.getValue(FACING));
      }

      super.breakBlock(world, pos, state);
   }

   private void updateBlockBounds(IBlockState state) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      boolean value2 = (Boolean)state.getValue(POWERED);
      float f = 0.25F;
      float f2 = 0.375F;
      float f3 = (float)(value2 ? 1 : 2) / 16.0F;
      float f4 = 0.125F;
      float f5 = 0.1875F;
      switch (value) {
         case EAST:
            this.setBlockBounds(0.0F, 0.375F, 0.3125F, f3, 0.625F, 0.6875F);
            break;
         case WEST:
            this.setBlockBounds(1.0F - f3, 0.375F, 0.3125F, 1.0F, 0.625F, 0.6875F);
            break;
         case SOUTH:
            this.setBlockBounds(0.3125F, 0.375F, 0.0F, 0.6875F, 0.625F, f3);
            break;
         case NORTH:
            this.setBlockBounds(0.3125F, 0.375F, 1.0F - f3, 0.6875F, 0.625F, 1.0F);
            break;
         case UP:
            this.setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 0.0F + f3, 0.625F);
            break;
         case DOWN:
            this.setBlockBounds(0.3125F, 1.0F - f3, 0.375F, 0.6875F, 1.0F, 0.625F);
      }
   }

   @Override
   public int tickRate(World world) {
      return this.wooden ? 30 : 20;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, POWERED});
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      for (EnumFacing facing : EnumFacing.getValues()) {
         if (canPlaceBlock(world, pos, facing)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.updateBlockBounds(world.getBlockState2(n, n2, n3));
   }

   private void notifyNeighbors(World world, BlockPos pos, EnumFacing facing) {
      world.notifyNeighborsOfStateChange(pos, this);
      world.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.1875F;
      float f2 = 0.125F;
      float f3 = 0.125F;
      this.setBlockBounds(0.5F - f, 0.5F - f2, 0.5F - f3, 0.5F + f, 0.5F + f2, 0.5F + f3);
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return !(Boolean)state.getValue(POWERED) ? 0 : (state.getValue(FACING) == facing ? 15 : 0);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (this.checkForDrop(world, pos, state) && !canPlaceBlock(world, pos, ((EnumFacing)state.getValue(FACING)).getOpposite())) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   private void checkForArrows(World world, BlockPos pos, IBlockState state) {
      this.updateBlockBounds(state);
      List entitiesWithinAABB = world.getEntitiesWithinAABB(
         EntityArrow.class,
         new AxisAlignedBB(
            (double)pos.getX() + this.minX,
            (double)pos.getY() + this.minY,
            (double)pos.getZ() + this.minZ,
            (double)pos.getX() + this.maxX,
            (double)pos.getY() + this.maxY,
            (double)pos.getZ() + this.maxZ
         )
      );
      boolean flag = !entitiesWithinAABB.isEmpty();
      boolean value = (Boolean)state.getValue(POWERED);
      if (flag && !value) {
         world.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE));
         this.notifyNeighbors(world, pos, (EnumFacing)state.getValue(FACING));
         world.markBlockRangeForRenderUpdate(pos, pos);
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
      }

      if (!flag && value) {
         world.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE));
         this.notifyNeighbors(world, pos, (EnumFacing)state.getValue(FACING));
         world.markBlockRangeForRenderUpdate(pos, pos);
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
      }

      if (flag) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if ((Boolean)state.getValue(POWERED)) {
         return true;
      } else {
         world.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 3);
         world.markBlockRangeForRenderUpdate(pos, pos);
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
         this.notifyNeighbors(world, pos, (EnumFacing)state.getValue(FACING));
         world.scheduleUpdate(pos, this, this.tickRate(world));
         return true;
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      EnumFacing facing;
      switch (n & 7) {
         case 0:
            facing = EnumFacing.DOWN;
            break;
         case 1:
            facing = EnumFacing.EAST;
            break;
         case 2:
            facing = EnumFacing.WEST;
            break;
         case 3:
            facing = EnumFacing.SOUTH;
            break;
         case 4:
            facing = EnumFacing.NORTH;
            break;
         case 5:
         default:
            facing = EnumFacing.UP;
      }

      return this.getDefaultState().withProperty(FACING, facing).withProperty(POWERED, (n & 8) > 0);
   }

   public BlockButton(boolean flag) {
      super(Material.circuits);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.FALSE));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.wooden = flag;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && this.wooden && !(Boolean)state.getValue(POWERED)) {
         this.checkForArrows(world, pos, state);
      }
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return canPlaceBlock(world, pos, facing.getOpposite())
         ? this.getDefaultState().withProperty(FACING, facing).withProperty(POWERED, Boolean.FALSE)
         : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(POWERED, Boolean.FALSE);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return canPlaceBlock(world, pos, facing.getOpposite());
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   private boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
      if (this.canPlaceBlockAt(world, pos)) {
         return true;
      } else {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
         return false;
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && (Boolean)state.getValue(POWERED)) {
         if (this.wooden) {
            this.checkForArrows(world, pos, state);
         } else {
            world.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE));
            this.notifyNeighbors(world, pos, (EnumFacing)state.getValue(FACING));
            world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
            world.markBlockRangeForRenderUpdate(pos, pos);
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      byte b;
      switch (((EnumFacing)state.getValue(FACING))) {
         case EAST:
            b = 1;
            break;
         case WEST:
            b = 2;
            break;
         case SOUTH:
            b = 3;
            break;
         case NORTH:
            b = 4;
            break;
         case UP:
         default:
            b = 5;
            break;
         case DOWN:
            b = 0;
      }

      if ((Boolean)state.getValue(POWERED)) {
         b |= 8;
      }

      return b;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static boolean canPlaceBlock(World world, BlockPos pos, EnumFacing facing) {
      BlockPos pos2 = pos.offset(facing);
      return facing == EnumFacing.DOWN ? World.doesBlockHaveSolidTopSurface(world, pos2.getX(), pos2.getY(), pos2.getZ()) : world.getBlockState(pos2).getBlock().isNormalCube();
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return (Boolean)state.getValue(POWERED) ? 15 : 0;
   }

}
