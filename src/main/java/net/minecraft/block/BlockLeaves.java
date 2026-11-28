package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public abstract class BlockLeaves extends BlockLeavesBase {
   public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
   public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
   public int[] surroundings;
   public int iconIndex;
   public boolean isTransparent;

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      byte b = 1;
      int n = b + 1;
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      if (world.isAreaLoaded(new BlockPos(x - n, y - n, z - n), new BlockPos(x + n, y + n, z + n))) {
         for (int i = -b; i <= b; i++) {
            for (int j = -b; j <= b; j++) {
               for (int k = -b; k <= b; k++) {
                  BlockPos pos2 = pos.add(i, j, k);
                  IBlockState blockState2 = world.getBlockState(pos2);
                  if (blockState2.getBlock().getMaterial() == Material.leaves && !(Boolean)blockState2.getValue(CHECK_DECAY)) {
                     world.setBlockState(pos2, blockState2.withProperty(CHECK_DECAY, Boolean.TRUE), 4);
                  }
               }
            }
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Item.getItemFromBlock(Blocks.sapling);
   }

   @Override
   public int getBlockColor() {
      return ColorizerFoliage.getFoliageColor(0.5, 1.0);
   }

   private void destroy(World world, BlockPos pos) {
      this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
      world.setBlockToAir(pos);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return this.isTransparent ? EnumWorldBlockLayer.CUTOUT_MIPPED : EnumWorldBlockLayer.SOLID;
   }

   public BlockLeaves() {
      super(Material.leaves, false);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setHardness(0.2F);
      this.setLightOpacity(1);
      this.setStepSound(soundTypeGrass);
   }

   @Override
   public int getRenderColor(IBlockState state) {
      return ColorizerFoliage.getFoliageColorBasic();
   }

   @Override
   public int quantityDropped(Random rand) {
      return rand.nextInt(20) == 0 ? 1 : 0;
   }

   public int getSaplingDropChance(IBlockState state) {
      return 20;
   }

   @Override
   public boolean isOpaqueCube() {
      return !this.fancyGraphics;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (!world.isRemote) {
         int saplingDropChance = this.getSaplingDropChance(state);
         if (n > 0) {
            saplingDropChance -= 2 << n;
            if (saplingDropChance < 10) {
               saplingDropChance = 10;
            }
         }

         if (world.rand.nextInt(saplingDropChance) == 0) {
            Item itemDropped = this.getItemDropped(state, world.rand, n);
            spawnAsEntity(world, pos, new ItemStack(itemDropped, 1, this.damageDropped(state)));
         }

         saplingDropChance = 200;
         if (n > 0) {
            saplingDropChance -= 10 << n;
            if (saplingDropChance < 40) {
               saplingDropChance = 40;
            }
         }

         this.dropApple(world, pos, state, saplingDropChance);
      }
   }

   public void dropApple(World world, BlockPos pos, IBlockState state, int n) {
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (world.isRainingAt(pos.up()) && !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) && rand.nextInt(15) == 1) {
         double d = (double)((float)pos.getX() + rand.nextFloat());
         double d2 = (double)pos.getY() - 0.05;
         double d3 = (double)((float)pos.getZ() + rand.nextFloat());
         world.spawnParticle(EnumParticleTypes.DRIP_WATER, d, d2, d3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return BiomeColorHelper.getFoliageColorAtPos(world, pos);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && (Boolean)state.getValue(CHECK_DECAY) && (Boolean)state.getValue(DECAYABLE)) {
         byte b = 4;
         int n = b + 1;
         int x = pos.getX();
         int y = pos.getY();
         int z = pos.getZ();
         byte b2 = 32;
         int n2 = b2 * b2;
         int n3 = b2 / 2;
         if (this.surroundings == null) {
            this.surroundings = new int[b2 * b2 * b2];
         }

         if (world.isAreaLoaded(new BlockPos(x - n, y - n, z - n), new BlockPos(x + n, y + n, z + n))) {
            BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

            for (int i = -b; i <= b; i++) {
               for (int j = -b; j <= b; j++) {
                  for (int k = -b; k <= b; k++) {
                     Block block = world.getBlockState(pos2.set(x + i, y + j, z + k)).getBlock();
                     if (block != Blocks.log && block != Blocks.log2) {
                        if (block.getMaterial() == Material.leaves) {
                           this.surroundings[(i + n3) * n2 + (j + n3) * b2 + k + n3] = -2;
                        } else {
                           this.surroundings[(i + n3) * n2 + (j + n3) * b2 + k + n3] = -1;
                        }
                     } else {
                        this.surroundings[(i + n3) * n2 + (j + n3) * b2 + k + n3] = 0;
                     }
                  }
               }
            }

            for (int m = 1; m <= 4; m++) {
               for (int idx = -b; idx <= b; idx++) {
                  for (int index = -b; index <= b; index++) {
                     for (int i2 = -b; i2 <= b; i2++) {
                        if (this.surroundings[(idx + n3) * n2 + (index + n3) * b2 + i2 + n3] == m - 1) {
                           if (this.surroundings[(idx + n3 - 1) * n2 + (index + n3) * b2 + i2 + n3] == -2) {
                              this.surroundings[(idx + n3 - 1) * n2 + (index + n3) * b2 + i2 + n3] = m;
                           }

                           if (this.surroundings[(idx + n3 + 1) * n2 + (index + n3) * b2 + i2 + n3] == -2) {
                              this.surroundings[(idx + n3 + 1) * n2 + (index + n3) * b2 + i2 + n3] = m;
                           }

                           if (this.surroundings[(idx + n3) * n2 + (index + n3 - 1) * b2 + i2 + n3] == -2) {
                              this.surroundings[(idx + n3) * n2 + (index + n3 - 1) * b2 + i2 + n3] = m;
                           }

                           if (this.surroundings[(idx + n3) * n2 + (index + n3 + 1) * b2 + i2 + n3] == -2) {
                              this.surroundings[(idx + n3) * n2 + (index + n3 + 1) * b2 + i2 + n3] = m;
                           }

                           if (this.surroundings[(idx + n3) * n2 + (index + n3) * b2 + (i2 + n3 - 1)] == -2) {
                              this.surroundings[(idx + n3) * n2 + (index + n3) * b2 + (i2 + n3 - 1)] = m;
                           }

                           if (this.surroundings[(idx + n3) * n2 + (index + n3) * b2 + i2 + n3 + 1] == -2) {
                              this.surroundings[(idx + n3) * n2 + (index + n3) * b2 + i2 + n3 + 1] = m;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (this.surroundings[n3 * n2 + n3 * b2 + n3] >= 0) {
            world.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.FALSE), 4);
         } else {
            this.destroy(world, pos);
         }
      }
   }

   public void setGraphicsLevel(boolean flag) {
      this.isTransparent = flag;
      this.fancyGraphics = flag;
      this.iconIndex = flag ? 0 : 1;
   }

   public abstract BlockPlanks.EnumType getWoodType(int n);

   @Override
   public boolean isVisuallyOpaque() {
      return false;
   }
}
