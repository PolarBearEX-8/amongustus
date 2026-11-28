package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.util.EnumFacing;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBanner extends BlockContainer {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

   @Override
   public boolean canSpawnInBlock() {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.banner;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.banner;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      TileEntity tileEntity = world.getTileEntity(pos);
      if (tileEntity instanceof TileEntityBanner) {
         ItemStack stack = new ItemStack(Items.banner, 1, ((TileEntityBanner)tileEntity).getBaseColor());
         NBTTagCompound tag = new NBTTagCompound();
         tileEntity.writeToNBT(tag);
         tag.removeTag("x");
         tag.removeTag("y");
         tag.removeTag("z");
         tag.removeTag("id");
         stack.setTagInfo("BlockEntityTag", tag);
         spawnAsEntity(world, pos, stack);
      } else {
         super.dropBlockAsItemWithChance(world, pos, state, f, n);
      }
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      if (tileEntity instanceof TileEntityBanner) {
         TileEntityBanner tileEntityBanner = (TileEntityBanner)tileEntity;
         ItemStack stack = new ItemStack(Items.banner, 1, ((TileEntityBanner)tileEntity).getBaseColor());
         NBTTagCompound tag = new NBTTagCompound();
         TileEntityBanner.setBaseColorAndPatterns(tag, tileEntityBanner.getBaseColor(), tileEntityBanner.getPatterns());
         stack.setTagInfo("BlockEntityTag", tag);
         spawnAsEntity(world, pos, stack);
      } else {
         super.harvestBlock(world, player, pos, state, (TileEntity)null);
      }
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityBanner();
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.banner.white.name");
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return !this.hasInvalidNeighbor(world, pos) && super.canPlaceBlockAt(world, pos);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public BlockBanner() {
      super(Material.wood);
      float f = 0.25F;
      float f2 = 1.0F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f2, 0.5F + f);
   }

public static class BlockBannerHanging extends BlockBanner {
      @Override
      public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
         EnumFacing value = (EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING);
         float f = 0.0F;
         float f2 = 0.78125F;
         float f3 = 0.0F;
         float f4 = 1.0F;
         float f5 = 0.125F;
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         switch (value) {
            case NORTH:
            default:
               this.setBlockBounds(f3, f, 1.0F - f5, f4, f2, 1.0F);
               break;
            case SOUTH:
               this.setBlockBounds(f3, f, 0.0F, f4, f2, f5);
               break;
            case WEST:
               this.setBlockBounds(1.0F - f5, f, f3, 1.0F, f2, f4);
               break;
            case EAST:
               this.setBlockBounds(0.0F, f, f3, f5, f2, f4);
         }
      }

      @Override
      public BlockState createBlockState() {
         return new BlockState(this, new IProperty[]{FACING});
      }

      @Override
      public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
         if (!world.getBlockState(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite())).getBlock().getMaterial().isSolid()) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         }

         super.onNeighborBlockChange(world, pos, state, block);
      }

      @Override
      public IBlockState getStateFromMeta(int n) {
         EnumFacing front = EnumFacing.getFront(n);
         if (front.getAxis() == EnumFacing.Axis.Y) {
            front = EnumFacing.NORTH;
         }

         return this.getDefaultState().withProperty(FACING, front);
      }

      public BlockBannerHanging() {
         this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      }

      @Override
      public int getMetaFromState(IBlockState state) {
         return ((EnumFacing)state.getValue(FACING)).getIndex();
      }
   }

   public static class BlockBannerStanding extends BlockBanner {
      @Override
      public int getMetaFromState(IBlockState state) {
         return (Integer)state.getValue(ROTATION);
      }

      public BlockBannerStanding() {
         this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
      }

      @Override
      public IBlockState getStateFromMeta(int n) {
         return this.getDefaultState().withProperty(ROTATION, n);
      }

      @Override
      public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
         if (!world.getBlockState(pos.down()).getBlock().getMaterial().isSolid()) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         }

         super.onNeighborBlockChange(world, pos, state, block);
      }

      @Override
      public BlockState createBlockState() {
         return new BlockState(this, new IProperty[]{ROTATION});
      }
   }
}
