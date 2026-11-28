package net.minecraft.block;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Random;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnow extends Block {
   public static final PropertyInteger LAYERS;
   public static final AxisAlignedBB[] SNOW_AABB = new AxisAlignedBB[]{
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
      new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
   };

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return (Integer)world.getBlockState(pos).getValue(LAYERS) < 5;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_13)) {
         this.setBlockBounds(SNOW_AABB[(Integer)blockState2.getValue(LAYERS)]);
      } else {
         this.getBoundsForLayers((Integer)blockState2.getValue(LAYERS));
      }
   }

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      return (Integer)world.getBlockState(pos).getValue(LAYERS) == 1;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      IBlockState blockState2 = world.getBlockState(pos.down());
      Block block = blockState2.getBlock();
      return block != Blocks.ice && block != Blocks.packed_ice
         ? (
            block.getMaterial() == Material.leaves
               ? true
               : (block == this && (Integer)blockState2.getValue(LAYERS) >= 7 ? true : block.isOpaqueCube() && block.blockMaterial.blocksMovement())
         )
         : false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(LAYERS) - 1;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{LAYERS});
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   public void getBoundsForLayers(int n) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (float)n / 8.0F, 1.0F);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (world.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) > 11) {
         this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
         world.setBlockToAir(pos);
      }
   }

   static {
      LAYERS = PropertyInteger.create("layers", 1, 8);
   }

   private boolean checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
      if (!this.canPlaceBlockAt(world, pos)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(LAYERS, (n & 7) + 1);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      int n4 = (Integer)state.getValue(LAYERS) - 1;
      float f = 0.125F;
      return new AxisAlignedBB(
         (double)n + this.minX,
         (double)n2 + this.minY,
         (double)n3 + this.minZ,
         (double)n + this.maxX,
         (double)((float)n2 + (float)n4 * f),
         (double)n3 + this.maxZ
      );
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity) {
      spawnAsEntity(world, pos, new ItemStack(Items.snowball, (Integer)state.getValue(LAYERS) + 1, 0));
      world.setBlockToAir(pos);
      player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.checkAndDropBlock(world, pos, state);
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.getBoundsForLayers(0);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing == EnumFacing.UP || super.shouldSideBeRendered(world, n, n2, n3, facing);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.snowball;
   }

   public BlockSnow() {
      super(Material.snow);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LAYERS, 1));
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBoundsForItemRender();
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return (Integer)world.getBlockState2(n, n2, n3).getValue(LAYERS) < 5;
   }
}
