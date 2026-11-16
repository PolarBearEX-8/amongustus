package client.util;

import client.setting.MovementScaleProperty;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPosAdapter;
import net.minecraft.block.Blocks;
import net.minecraft.block.GeometryParser;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CustomRandomEntry extends ClientManagerResolver {
   public Random random;
   public World world;
   public BlockPos blockPos = BlockPos.origin;
   public int count2;
   public int count3;
   public double value3 = 0.618;
   public double value2 = 0.381;
   public double value4 = 1.0;
   public double value = 1.0;
   public int count4 = 1;
   public int count = 12;
   public int count5 = 4;
   public List list;

   public void updateState5() {
      for (BlockPosAdapter blockPosAdapter : (Iterable<BlockPosAdapter>)(Object)(this.list)) {
         this.applyBlockPos(blockPosAdapter);
      }
   }

   private MovementScaleProperty resolveMovementScaleProperty(BlockPos blockPos2, BlockPos blockPos3) {
      MovementScaleProperty movementScaleProperty = MovementScaleProperty.Y;
      int count6 = Math.abs(blockPos3.getX() - blockPos2.getX());
      int count7 = Math.abs(blockPos3.getZ() - blockPos2.getZ());
      int count8 = Math.max(count6, count7);
      if (count8 > 0) {
         if (count6 == count8) {
            movementScaleProperty = MovementScaleProperty.X;
         } else if (count7 == count8) {
            movementScaleProperty = MovementScaleProperty.Z;
         }
      }

      return movementScaleProperty;
   }

   @Override
   public void updateState() {
      this.count5 = 5;
   }

   public boolean checkIndex(int count6) {
      return (double)count6 >= (double)this.count2 * 0.2;
   }

   public void applyBlockPos(BlockPos blockPos2, BlockPos blockPos3, Block block) {
      BlockPos blockPos4 = blockPos3.add(-blockPos2.getX(), -blockPos2.getY(), -blockPos2.getZ());
      int count6 = this.computeCount(blockPos4);
      float factor = (float)blockPos4.getX() / (float)count6;
      float factor2 = (float)blockPos4.getY() / (float)count6;
      float factor3 = (float)blockPos4.getZ() / (float)count6;

      for (int index = 0; index <= count6; index++) {
         BlockPos blockPos5 = blockPos2.resolveBlockPos((double)(0.5F + (float)index * factor), (double)(0.5F + (float)index * factor2), (double)(0.5F + (float)index * factor3));
         MovementScaleProperty movementScaleProperty = this.resolveMovementScaleProperty(blockPos2, blockPos5);
         this.applyWorld(this.world, blockPos5, block.getDefaultState().resolveIBlockState(GeometryParser.immutableSetEntry2, movementScaleProperty));
      }
   }

   public void applyBlockPos(BlockPos blockPos2, float factor, IBlockState blockState) {
      int count6 = (int)((double)factor + 0.618);

      for (int index = -count6; index <= count6; index++) {
         for (int index2 = -count6; index2 <= count6; index2++) {
            if (Math.pow((double)Math.abs(index) + 0.5, 2.0) + Math.pow((double)Math.abs(index2) + 0.5, 2.0) <= (double)(factor * factor)) {
               BlockPos blockPos3 = blockPos2.add(index, 0, index2);
               Material material = this.world.getBlockState(blockPos3).getBlock().getMaterial();
               if (material == Material.coreItemEntry || material == Material.material9) {
                  this.applyWorld(this.world, blockPos3, blockState);
               }
            }
         }
      }
   }

   public float computeFactor(int count6) {
      if ((float)count6 < (float)this.count2 * 0.3F) {
         return -1.0F;
      } else {
         float factor = (float)this.count2 / 2.0F;
         float factor2 = factor - (float)count6;
         float factor3 = MathHelper.sin(factor * factor - factor2 * factor2);
         if (factor2 == 0.0F) {
            factor3 = factor;
         } else if (Math.abs(factor2) >= factor) {
            return 0.0F;
         }

         return factor3 * 0.5F;
      }
   }

   public void updateState2() {
      BlockPos blockPos2 = this.blockPos;
      BlockPos blockPos3 = this.blockPos.resolveBlockPos2(this.count3);
      Block block = Blocks.block140;
      this.applyBlockPos(blockPos2, blockPos3, block);
      if (this.count4 == 2) {
         this.applyBlockPos(blockPos2.getBlockPos3(), blockPos3.getBlockPos3(), block);
         this.applyBlockPos(blockPos2.getBlockPos3().getBlockPos2(), blockPos3.getBlockPos3().getBlockPos2(), block);
         this.applyBlockPos(blockPos2.getBlockPos2(), blockPos3.getBlockPos2(), block);
      }
   }

   public float computeFactorByIndex(int count6) {
      return count6 >= 0 && count6 < this.count5 ? (count6 != 0 && count6 != this.count5 - 1 ? 3.0F : 2.0F) : -1.0F;
   }

   private boolean isActive() {
      Block block = this.world.getBlockState(this.blockPos.down()).getBlock();
      if (block != Blocks.block115 && block != Blocks.blockGrass && block != Blocks.block7) {
         return false;
      } else {
         int count6 = this.computeCount(this.blockPos, this.blockPos.resolveBlockPos2(this.count2 - 1));
         if (count6 == -1) {
            return true;
         } else if (count6 < 6) {
            return false;
         } else {
            this.count2 = count6;
            return true;
         }
      }
   }

   public void applyBlockPos(BlockPos blockPos2) {
      for (int index = 0; index < this.count5; index++) {
         this.applyBlockPos(
            blockPos2.resolveBlockPos2(index),
            this.computeFactorByIndex(index),
            Blocks.decayableManager2.getDefaultState().resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE)
         );
      }
   }

   public void updateState4() {
      for (BlockPosAdapter blockPosAdapter : (Iterable<BlockPosAdapter>)(Object)(this.list)) {
         int count6 = blockPosAdapter.getCount9();
         BlockPos blockPos2 = new BlockPos(this.blockPos.getX(), count6, this.blockPos.getZ());
         if (!blockPos2.equals(blockPosAdapter) && this.checkIndex(count6 - this.blockPos.getY())) {
            this.applyBlockPos(blockPos2, blockPosAdapter, Blocks.block140);
         }
      }
   }

   @Override
   public boolean matchesWorld(World world2, Random random2, BlockPos blockPos2) {
      this.world = world2;
      this.blockPos = blockPos2;
      this.random = new Random(random2.nextLong());
      if (this.count2 == 0) {
         this.count2 = 5 + this.random.nextInt(this.count);
      }

      if (!this.isActive()) {
         return false;
      } else {
         this.updateState3();
         this.updateState5();
         this.updateState2();
         this.updateState4();
         return true;
      }
   }

   public int computeCount(BlockPos blockPos2, BlockPos blockPos3) {
      BlockPos blockPos4 = blockPos3.add(-blockPos2.getX(), -blockPos2.getY(), -blockPos2.getZ());
      int count6 = this.computeCount(blockPos4);
      float factor = (float)blockPos4.getX() / (float)count6;
      float factor2 = (float)blockPos4.getY() / (float)count6;
      float factor3 = (float)blockPos4.getZ() / (float)count6;
      if (count6 == 0) {
         return -1;
      } else {
         for (int index = 0; index <= count6; index++) {
            BlockPos blockPos5 = blockPos2.resolveBlockPos((double)(0.5F + (float)index * factor), (double)(0.5F + (float)index * factor2), (double)(0.5F + (float)index * factor3));
            if (!this.matchesBlock(this.world.getBlockState(blockPos5).getBlock())) {
               return index;
            }
         }

         return -1;
      }
   }

   public void updateState3() {
      this.count3 = (int)((double)this.count2 * this.value3);
      if (this.count3 >= this.count2) {
         this.count3 = this.count2 - 1;
      }

      int count6 = (int)(1.382 + Math.pow(this.value * (double)this.count2 / 13.0, 2.0));
      if (count6 < 1) {
         count6 = 1;
      }

      int count7 = this.blockPos.getY() + this.count3;
      int count8 = this.count2 - this.count5;
      this.list = Lists.newArrayList();
      this.list.add(new BlockPosAdapter(this.blockPos.resolveBlockPos2(count8), count7));

      for (; count8 >= 0; count8--) {
         float factor = this.computeFactor(count8);
         if (factor >= 0.0F) {
            for (int index = 0; index < count6; index++) {
               double offset = this.value4 * (double)factor * ((double)this.random.nextFloat() + 0.328);
               double offset2 = (double)(this.random.nextFloat() * 2.0F) * Math.PI;
               double offset3 = offset * Math.sin(offset2) + 0.5;
               double offset4 = offset * Math.cos(offset2) + 0.5;
               BlockPos blockPos2 = this.blockPos.resolveBlockPos(offset3, (double)(count8 - 1), offset4);
               BlockPos blockPos3 = blockPos2.resolveBlockPos2(this.count5);
               if (this.computeCount(blockPos2, blockPos3) == -1) {
                  int count9 = this.blockPos.getX() - blockPos2.getX();
                  int count10 = this.blockPos.getZ() - blockPos2.getZ();
                  double offset5 = (double)blockPos2.getY() - Math.sqrt((double)(count9 * count9 + count10 * count10)) * this.value2;
                  int count11 = offset5 > (double)count7 ? count7 : (int)offset5;
                  BlockPos blockPos4 = new BlockPos(this.blockPos.getX(), count11, this.blockPos.getZ());
                  if (this.computeCount(blockPos4, blockPos2) == -1) {
                     this.list.add(new BlockPosAdapter(blockPos2, blockPos4.getY()));
                  }
               }
            }
         }
      }
   }

   private int computeCount(BlockPos blockPos2) {
      int count6 = MathHelper.computeCount(blockPos2.getX());
      int count7 = MathHelper.computeCount(blockPos2.getY());
      int count8 = MathHelper.computeCount(blockPos2.getZ());
      return count8 > count6 && count8 > count7 ? count8 : (count7 > count6 ? count7 : count6);
   }

   public CustomRandomEntry(boolean flag) {
      super(flag);
   }
}
