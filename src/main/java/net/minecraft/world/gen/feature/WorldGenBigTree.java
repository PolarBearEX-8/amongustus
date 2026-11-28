package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenBigTree extends WorldGenAbstractTree {
   private Random rand;
   private World world;
   private BlockPos basePos = BlockPos.ORIGIN;
   public int heightLimit;
   public int height;
   public double heightAttenuation = 0.618;
   public double branchSlope = 0.381;
   public double scaleWidth = 1.0;
   public double leafDensity = 1.0;
   public int trunkSize = 1;
   public int heightLimitLimit = 12;
   public int leafDistanceLimit = 4;
   public List foliageCoords;

   public int checkBlockLine(BlockPos pos, BlockPos pos2) {
      BlockPos pos3 = pos2.add(-pos.getX(), -pos.getY(), -pos.getZ());
      int greatestDistance = this.getGreatestDistance(pos3);
      float f = (float)pos3.getX() / (float)greatestDistance;
      float f2 = (float)pos3.getY() / (float)greatestDistance;
      float f3 = (float)pos3.getZ() / (float)greatestDistance;
      if (greatestDistance == 0) {
         return -1;
      } else {
         for (int i = 0; i <= greatestDistance; i++) {
            BlockPos pos4 = pos.add((double)(0.5F + (float)i * f), (double)(0.5F + (float)i * f2), (double)(0.5F + (float)i * f3));
            if (!this.canGrowInto(this.world.getBlockState(pos4).getBlock())) {
               return i;
            }
         }

         return -1;
      }
   }

   public boolean leafNodeNeedsBase(int n) {
      return (double)n >= (double)this.heightLimit * 0.2;
   }

   @Override
   public void setDecorationDefaults() {
      this.leafDistanceLimit = 5;
   }

   public void generateLeafNodeBases() {
      for (WorldGenBigTree.FoliageCoordinates foliageCoordinates : (Iterable<WorldGenBigTree.FoliageCoordinates>)(this.foliageCoords)) {
         int branchBase = foliageCoordinates.getBranchBase();
         BlockPos pos = new BlockPos(this.basePos.getX(), branchBase, this.basePos.getZ());
         if (!pos.equals(foliageCoordinates) && this.leafNodeNeedsBase(branchBase - this.basePos.getY())) {
            this.limb(pos, foliageCoordinates, Blocks.log);
         }
      }
   }

   public void generateLeafNode(BlockPos pos) {
      for (int i = 0; i < this.leafDistanceLimit; i++) {
         this.crosSection(pos.up(i), this.leafSize(i), Blocks.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
      }
   }

   @Override
   public boolean generate(World world2, Random rand2, BlockPos pos) {
      this.world = world2;
      this.basePos = pos;
      this.rand = new Random(rand2.nextLong());
      if (this.heightLimit == 0) {
         this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
      }

      if (!this.validTreeLocation()) {
         return false;
      } else {
         this.generateLeafNodeList();
         this.generateLeaves();
         this.generateTrunk();
         this.generateLeafNodeBases();
         return true;
      }
   }

   public void generateLeaves() {
      for (WorldGenBigTree.FoliageCoordinates foliageCoordinates : (Iterable<WorldGenBigTree.FoliageCoordinates>)(this.foliageCoords)) {
         this.generateLeafNode(foliageCoordinates);
      }
   }

   public void limb(BlockPos pos, BlockPos pos2, Block block) {
      BlockPos pos3 = pos2.add(-pos.getX(), -pos.getY(), -pos.getZ());
      int greatestDistance = this.getGreatestDistance(pos3);
      float f = (float)pos3.getX() / (float)greatestDistance;
      float f2 = (float)pos3.getY() / (float)greatestDistance;
      float f3 = (float)pos3.getZ() / (float)greatestDistance;

      for (int i = 0; i <= greatestDistance; i++) {
         BlockPos pos4 = pos.add((double)(0.5F + (float)i * f), (double)(0.5F + (float)i * f2), (double)(0.5F + (float)i * f3));
         BlockLog.EnumAxis logAxis = this.getLogAxis(pos, pos4);
         this.setBlockAndNotifyAdequately(this.world, pos4, block.getDefaultState().withProperty(BlockLog.LOG_AXIS, logAxis));
      }
   }

   public float layerSize(int n) {
      if ((float)n < (float)this.heightLimit * 0.3F) {
         return -1.0F;
      } else {
         float f = (float)this.heightLimit / 2.0F;
         float f2 = f - (float)n;
         float f3 = MathHelper.sqrt_float(f * f - f2 * f2);
         if (f2 == 0.0F) {
            f3 = f;
         } else if (Math.abs(f2) >= f) {
            return 0.0F;
         }

         return f3 * 0.5F;
      }
   }

   public void generateTrunk() {
      BlockPos basePos2 = this.basePos;
      BlockPos pos = this.basePos.up(this.height);
      Block log = Blocks.log;
      this.limb(basePos2, pos, log);
      if (this.trunkSize == 2) {
         this.limb(basePos2.east(), pos.east(), log);
         this.limb(basePos2.east().south(), pos.east().south(), log);
         this.limb(basePos2.south(), pos.south(), log);
      }
   }

   public void generateLeafNodeList() {
      this.height = (int)((double)this.heightLimit * this.heightAttenuation);
      if (this.height >= this.heightLimit) {
         this.height = this.heightLimit - 1;
      }

      int n = (int)(1.382 + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0, 2.0));
      if (n < 1) {
         n = 1;
      }

      int n2 = this.basePos.getY() + this.height;
      int n3 = this.heightLimit - this.leafDistanceLimit;
      this.foliageCoords = Lists.newArrayList();
      this.foliageCoords.add(new WorldGenBigTree.FoliageCoordinates(this.basePos.up(n3), n2));

      for (; n3 >= 0; n3--) {
         float f = this.layerSize(n3);
         if (f >= 0.0F) {
            for (int i = 0; i < n; i++) {
               double d = this.scaleWidth * (double)f * ((double)this.rand.nextFloat() + 0.328);
               double d2 = (double)(this.rand.nextFloat() * 2.0F) * Math.PI;
               double d3 = d * Math.sin(d2) + 0.5;
               double d4 = d * Math.cos(d2) + 0.5;
               BlockPos pos = this.basePos.add(d3, (double)(n3 - 1), d4);
               if (this.checkBlockLine(pos, pos.up(this.leafDistanceLimit)) == -1) {
                  int n4 = this.basePos.getX() - pos.getX();
                  int n5 = this.basePos.getZ() - pos.getZ();
                  double d5 = (double)pos.getY() - Math.sqrt((double)(n4 * n4 + n5 * n5)) * this.branchSlope;
                  int n6 = d5 > (double)n2 ? n2 : (int)d5;
                  BlockPos pos3 = new BlockPos(this.basePos.getX(), n6, this.basePos.getZ());
                  if (this.checkBlockLine(pos3, pos) == -1) {
                     this.foliageCoords.add(new WorldGenBigTree.FoliageCoordinates(pos, pos3.getY()));
                  }
               }
            }
         }
      }
   }

   private boolean validTreeLocation() {
      Block block = this.world.getBlockState(this.basePos.down()).getBlock();
      if (block != Blocks.dirt && block != Blocks.grass && block != Blocks.farmland) {
         return false;
      } else {
         int n = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));
         if (n == -1) {
            return true;
         } else if (n < 6) {
            return false;
         } else {
            this.heightLimit = n;
            return true;
         }
      }
   }

   public void crosSection(BlockPos pos, float f, IBlockState state) {
      int n = (int)((double)f + 0.618);

      for (int i = -n; i <= n; i++) {
         for (int j = -n; j <= n; j++) {
            if (Math.pow((double)Math.abs(i) + 0.5, 2.0) + Math.pow((double)Math.abs(j) + 0.5, 2.0) <= (double)(f * f)) {
               BlockPos pos2 = pos.add(i, 0, j);
               Material material = this.world.getBlockState(pos2).getBlock().getMaterial();
               if (material == Material.air || material == Material.leaves) {
                  this.setBlockAndNotifyAdequately(this.world, pos2, state);
               }
            }
         }
      }
   }

   public float leafSize(int n) {
      return n >= 0 && n < this.leafDistanceLimit ? (n != 0 && n != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
   }

   private BlockLog.EnumAxis getLogAxis(BlockPos pos, BlockPos pos2) {
      BlockLog.EnumAxis enumAxis = BlockLog.EnumAxis.Y;
      int n = Math.abs(pos2.getX() - pos.getX());
      int n2 = Math.abs(pos2.getZ() - pos.getZ());
      int n3 = Math.max(n, n2);
      if (n3 > 0) {
         if (n == n3) {
            enumAxis = BlockLog.EnumAxis.X;
         } else if (n2 == n3) {
            enumAxis = BlockLog.EnumAxis.Z;
         }
      }

      return enumAxis;
   }

   private int getGreatestDistance(BlockPos pos) {
      int n = MathHelper.abs_int(pos.getX());
      int n2 = MathHelper.abs_int(pos.getY());
      int n3 = MathHelper.abs_int(pos.getZ());
      return n3 > n && n3 > n2 ? n3 : (n2 > n ? n2 : n);
   }

   public WorldGenBigTree(boolean flag) {
      super(flag);
   }


   public static class FoliageCoordinates extends BlockPos {
      private final int branchBase;

      public FoliageCoordinates(BlockPos pos, int n) {
         super(pos.getX(), pos.getY(), pos.getZ());
         this.branchBase = n;
      }

      public int getBranchBase() {
         return this.branchBase;
      }
   }
}
