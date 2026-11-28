package net.minecraft.block;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Random;
import net.augustus.viaversion.ViaAugustus;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFarmland extends Block {
   public static final PropertyInteger MOISTURE = PropertyInteger.create("moisture", 0, 7);

   public BlockFarmland() {
      super(Material.ground);
      this.setDefaultState(this.blockState.getBaseState().withProperty(MOISTURE, 0));
      this.setTickRandomly(true);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.setLightOpacity(255);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      super.onNeighborBlockChange(world, pos, state, block);
      if (world.getBlockState(pos.up()).getBlock().getMaterial().isSolid()) {
         world.setBlockState(pos, Blocks.dirt.getDefaultState());
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Item.getItemFromBlock(Blocks.dirt);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{MOISTURE});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(MOISTURE, n & 7);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, n);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      switch (facing) {
         case UP:
            return true;
         case NORTH:
         case SOUTH:
         case WEST:
         case EAST:
            Block block = world.getBlockState2(n, n2, n3).getBlock();
            return !block.isOpaqueCube() && block != Blocks.farmland;
         default:
            return super.shouldSideBeRendered(world, n, n2, n3, facing);
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(MOISTURE);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_10)
         ? new AxisAlignedBB((double)n, (double)n2, (double)n3, (double)n + 1.0, (double)n2 + 0.9375, (double)n3 + 1.0)
         : new AxisAlignedBB((double)n, (double)n2, (double)n3, (double)(n + 1), (double)(n2 + 1), (double)(n3 + 1));
   }

   private boolean hasWater(World world, BlockPos pos) {
      for (BlockPos.MutableBlockPos pos2 : (Iterable<BlockPos.MutableBlockPos>)(BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)))) {
         if (world.getBlockState(pos2).getBlock().getMaterial() == Material.water) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onFallenUpon(World world, BlockPos pos, Entity entity, float f) {
      if (entity instanceof EntityLivingBase) {
         if (!world.isRemote && world.rand.nextFloat() < f - 0.5F) {
            if (!(entity instanceof EntityPlayer) && !world.getGameRules().getBoolean("mobGriefing")) {
               return;
            }

            world.setBlockState(pos, Blocks.dirt.getDefaultState());
         }

         super.onFallenUpon(world, pos, entity, f);
      }
   }

   private boolean hasCrops(World world, BlockPos pos) {
      Block block = world.getBlockState(pos.up()).getBlock();
      return block instanceof BlockCrops || block instanceof BlockStem;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      int value = (Integer)state.getValue(MOISTURE);
      if (!this.hasWater(world, pos) && !world.isRainingAt(pos.up())) {
         if (value > 0) {
            world.setBlockState(pos, state.withProperty(MOISTURE, value - 1), 2);
         } else if (!this.hasCrops(world, pos)) {
            world.setBlockState(pos, Blocks.dirt.getDefaultState());
         }
      } else if (value < 7) {
         world.setBlockState(pos, state.withProperty(MOISTURE, 7), 2);
      }
   }

}
