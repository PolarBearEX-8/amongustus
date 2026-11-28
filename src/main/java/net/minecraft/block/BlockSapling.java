package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockSapling extends BlockBush implements IGrowable {
   public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockPlanks.EnumType.class);
   public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(TYPE, BlockPlanks.EnumType.byMetadata(n & 7)).withProperty(STAGE, (n & 8) >> 3);
   }

   public BlockSapling() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockPlanks.EnumType.OAK).withProperty(STAGE, 0));
      float f = 0.4F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         super.updateTick(world, pos, state, rand);
         if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(world, pos, state, rand);
         }
      }
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return (double)world.rand.nextFloat() < 0.45;
   }

   @Override
   public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
      for (BlockPlanks.EnumType enumType : BlockPlanks.EnumType.values()) {
         list.add(new ItemStack(item, 1, enumType.getMetadata()));
      }
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockPlanks.EnumType.OAK.getUnlocalizedName() + ".name");
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return true;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockPlanks.EnumType)state.getValue(TYPE)).getMetadata();
      return n | (Integer)state.getValue(STAGE) << 3;
   }

   public boolean isTypeAt(World world, BlockPos pos, BlockPlanks.EnumType enumType) {
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock() == this && blockState2.getValue(TYPE) == enumType;
   }

   public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
      Object obj = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
      int n = 0;
      int n2 = 0;
      boolean flag = false;
      switch (((BlockPlanks.EnumType)state.getValue(TYPE))) {
         case SPRUCE:
            outerLoop:
            for (n = 0; n >= -1; n--) {
               for (n2 = 0; n2 >= -1; n2--) {
                  if (this.isTwoByTwoOfType(world, pos, n, n2, BlockPlanks.EnumType.SPRUCE)) {
                     obj = new WorldGenMegaPineTree(false, rand.nextBoolean());
                     flag = true;
                     break outerLoop;
                  }
               }
            }

            if (!flag) {
               n2 = 0;
               n = 0;
               obj = new WorldGenTaiga2(true);
            }
            break;
         case BIRCH:
            obj = new WorldGenForest(true, false);
            break;
         case JUNGLE:
            IBlockState state2 = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            IBlockState state3 = Blocks.leaves
               .getDefaultState()
               .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
               .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

            outerLoop2:
            for (n = 0; n >= -1; n--) {
               for (n2 = 0; n2 >= -1; n2--) {
                  if (this.isTwoByTwoOfType(world, pos, n, n2, BlockPlanks.EnumType.JUNGLE)) {
                     obj = new WorldGenMegaJungle(true, 10, 20, state2, state3);
                     flag = true;
                     break outerLoop2;
                  }
               }
            }

            if (!flag) {
               n2 = 0;
               n = 0;
               obj = new WorldGenTrees(true, 4 + rand.nextInt(7), state2, state3, false);
            }
            break;
         case ACACIA:
            obj = new WorldGenSavannaTree(true);
            break;
         case DARK_OAK:
            outerLoop3:
            for (n = 0; n >= -1; n--) {
               for (n2 = 0; n2 >= -1; n2--) {
                  if (this.isTwoByTwoOfType(world, pos, n, n2, BlockPlanks.EnumType.DARK_OAK)) {
                     obj = new WorldGenCanopyTree(true);
                     flag = true;
                     break outerLoop3;
                  }
               }
            }

            if (!flag) {
               return;
            }
         case OAK:
      }

      IBlockState defaultState = Blocks.air.getDefaultState();
      if (flag) {
         world.setBlockState(pos.add(n, 0, n2), defaultState, 4);
         world.setBlockState(pos.add(n + 1, 0, n2), defaultState, 4);
         world.setBlockState(pos.add(n, 0, n2 + 1), defaultState, 4);
         world.setBlockState(pos.add(n + 1, 0, n2 + 1), defaultState, 4);
      } else {
         world.setBlockState(pos, defaultState, 4);
      }

      if (!((WorldGenerator)obj).generate(world, rand, pos.add(n, 0, n2))) {
         if (flag) {
            world.setBlockState(pos.add(n, 0, n2), state, 4);
            world.setBlockState(pos.add(n + 1, 0, n2), state, 4);
            world.setBlockState(pos.add(n, 0, n2 + 1), state, 4);
            world.setBlockState(pos.add(n + 1, 0, n2 + 1), state, 4);
         } else {
            world.setBlockState(pos, state, 4);
         }
      }
   }

   public void grow(World world, BlockPos pos, IBlockState state, Random rand) {
      if ((Integer)state.getValue(STAGE) == 0) {
         world.setBlockState(pos, state.cycleProperty(STAGE), 4);
      } else {
         this.generateTree(world, pos, state, rand);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{TYPE, STAGE});
   }

   @Override
   public int damageDropped(IBlockState state) {
      return ((BlockPlanks.EnumType)state.getValue(TYPE)).getMetadata();
   }

   private boolean isTwoByTwoOfType(World world, BlockPos pos, int n, int n2, BlockPlanks.EnumType enumType) {
      return this.isTypeAt(world, pos.add(n, 0, n2), enumType)
         && this.isTypeAt(world, pos.add(n + 1, 0, n2), enumType)
         && this.isTypeAt(world, pos.add(n, 0, n2 + 1), enumType)
         && this.isTypeAt(world, pos.add(n + 1, 0, n2 + 1), enumType);
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      this.grow(world, pos, state, rand);
   }

}
