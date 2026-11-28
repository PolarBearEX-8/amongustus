package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public class BlockGrass extends Block implements IGrowable {
   public static final PropertyBool SNOWY;

   @Override
   public int getMetaFromState(IBlockState state) {
      return 0;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) < 4 && world.getBlockState2(pos.getX(), pos.getY() + 1, pos.getZ()).getBlock().getLightOpacity() > 2) {
            world.setBlockState(pos, Blocks.dirt.getDefaultState());
         } else if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) >= 9) {
            for (int i = 0; i < 4; i++) {
               int n = pos.getX() + rand.nextInt(3) - 1;
               int n2 = pos.getY() + rand.nextInt(5) - 3;
               int n3 = pos.getZ() + rand.nextInt(3) - 1;
               IBlockState blockState2 = world.getBlockState2(n, n2, n3);
               if (blockState2.getBlock() == Blocks.dirt && blockState2.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && world.getLight(n, n2 + 1, n3) >= 4) {
                  if (world.getBlockState2(n, n2 + 1, n3).getBlock().getLightOpacity() <= 2) {
                     world.setBlockState(new BlockPos(n, n2, n3), Blocks.grass.getDefaultState());
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return true;
   }

   @Override
   public int getBlockColor() {
      return ColorizerGrass.getGrassColor(0.5, 1.0);
   }

   public BlockGrass() {
      super(Material.grass);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.FALSE));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return BiomeColorHelper.getGrassColorAtPos(world, pos);
   }

   @Override
   public int getRenderColor(IBlockState state) {
      return this.getBlockColor();
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, n);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{SNOWY});
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      Block block = world.getBlockState2(n, n2 + 1, n3).getBlock();
      return state.withProperty(SNOWY, block == Blocks.snow || block == Blocks.snow_layer);
   }

   static {
      SNOWY = PropertyBool.create("snowy");
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      BlockPos pos2 = pos.up();

      outerLoop:
      for (int i = 0; i < 128; i++) {
         BlockPos pos3 = pos2;

         for (int j = 0; j < i / 16; j++) {
            pos3 = pos3.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
            if (world.getBlockState(pos3.down()).getBlock() != Blocks.grass || world.getBlockState(pos3).getBlock().isNormalCube()) {
               continue outerLoop;
            }
         }

         if (world.getBlockState(pos3).getBlock().blockMaterial == Material.air) {
            if (rand.nextInt(8) == 0) {
               BlockFlower.EnumFlowerType enumFlowerType = world.getBiomeGenForCoords(pos3).pickRandomFlower(rand, pos3);
               BlockFlower block = enumFlowerType.getBlockType().getBlock();
               IBlockState state2 = block.getDefaultState().withProperty(block.getTypeProperty(), enumFlowerType);
               if (block.canBlockStay(world, pos3, state2)) {
                  world.setBlockState(pos3, state2, 3);
               }
            } else {
               IBlockState state3 = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);
               if (Blocks.tallgrass.canBlockStay(world, pos3, state3)) {
                  world.setBlockState(pos3, state3, 3);
               }
            }
         }
      }
   }
}
