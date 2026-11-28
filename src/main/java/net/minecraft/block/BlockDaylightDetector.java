package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDaylightDetector extends BlockContainer {
   public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
   private final boolean inverted;

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (player.isAllowEdit()) {
         if (!world.isRemote) {
            if (this.inverted) {
               world.setBlockState(pos, Blocks.daylight_detector.getDefaultState().withProperty(POWER, state.getValue(POWER)), 4);
               Blocks.daylight_detector.updatePower(world, pos);
            } else {
               world.setBlockState(pos, Blocks.daylight_detector_inverted.getDefaultState().withProperty(POWER, state.getValue(POWER)), 4);
               Blocks.daylight_detector_inverted.updatePower(world, pos);
            }
         }

         return true;
      } else {
         return super.onBlockActivated(world, pos, state, player, facing, f, f2, f3);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{POWER});
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(POWER, n);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.daylight_detector);
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return player.isAllowEdit() ? true : super.onBlockActivated(world, pos, state, player, facing, f, f2, f3);
   }

   public BlockDaylightDetector(boolean flag) {
      super(Material.wood);
      this.inverted = flag;
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setHardness(0.2F);
      this.setStepSound(soundTypeWood);
      this.setUnlocalizedName("daylightDetector");
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      if (!this.inverted) {
         super.getSubBlocks(item, creativeTabs, list);
      }
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return (Integer)state.getValue(POWER);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.daylight_detector);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(POWER);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityDaylightDetector();
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
   }

   public void updatePower(World world, BlockPos pos) {
      if (!world.provider.getHasNoSky()) {
         IBlockState blockState2 = world.getBlockState(pos);
         int n = world.getLightFor(EnumSkyBlock.SKY, pos.getX(), pos.getY(), pos.getZ()) - world.getSkylightSubtracted();
         float celestialAngleRadians = world.getCelestialAngleRadians(1.0F);
         float f = celestialAngleRadians < (float) Math.PI ? 0.0F : (float) (Math.PI * 2);
         celestialAngleRadians += (f - celestialAngleRadians) * 0.2F;
         n = Math.round((float)n * MathHelper.cos(celestialAngleRadians));
         n = MathHelper.clamp_int(n, 0, 15);
         if (this.inverted) {
            n = 15 - n;
         }

         if ((Integer)blockState2.getValue(POWER) != n) {
            world.setBlockState(pos, blockState2.withProperty(POWER, n), 3);
         }
      }
   }
}
