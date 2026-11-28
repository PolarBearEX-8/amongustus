package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMycelium extends Block {
   public static final PropertyBool SNOWY;

   public BlockMycelium() {
      super(Material.grass, MapColor.purpleColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.FALSE));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{SNOWY});
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, n);
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.randomDisplayTick(world, pos, state, rand);
      if (rand.nextInt(10) == 0) {
         world.spawnParticle(
            EnumParticleTypes.TOWN_AURA,
            (double)((float)pos.getX() + rand.nextFloat()),
            (double)((float)pos.getY() + 1.1F),
            (double)((float)pos.getZ() + rand.nextFloat()),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   static {
      SNOWY = PropertyBool.create("snowy");
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      Block block = world.getBlockState2(n, n2 + 1, n3).getBlock();
      return state.withProperty(SNOWY, block == Blocks.snow || block == Blocks.snow_layer);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity() > 2) {
            world.setBlockState(pos, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
         } else if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) >= 9) {
            for (int i = 0; i < 4; i++) {
               BlockPos pos2 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
               IBlockState blockState2 = world.getBlockState(pos2);
               Block block = world.getBlockState(pos2.up()).getBlock();
               if (blockState2.getBlock() == Blocks.dirt
                  && blockState2.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT
                  && world.getLight(pos2.getX(), pos2.getY() + 1, pos2.getZ()) >= 4
                  && block.getLightOpacity() <= 2) {
                  world.setBlockState(pos2, this.getDefaultState());
               }
            }
         }
      }
   }
}
