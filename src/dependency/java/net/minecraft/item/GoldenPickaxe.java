package net.minecraft.item;

import client.util.BiomeIDIsOutOfBoundsManager;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.InfoManager;
import client.util.ListenerAdapter28;
import client.util.TargetEstimator;
import client.util.WrongManagerTypeManager;
import java.util.Arrays;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class GoldenPickaxe implements IBlockAccess {
   public final ListenerAdapter28 listenerAdapter28;
   public final int count5;
   public final int count6;
   public final int count8;
   public final int count4;
   public final int count;
   public final int count7;
   public final int count3;
   public int[] countArray;
   public IBlockState[] iBlockStateArray;
   public final int count2;
   public final boolean enabled = BuildManager.isActive28();
   public static final WrongManagerTypeManager wrongManagerTypeManager2 = new WrongManagerTypeManager(int.class, 16);
   public static final WrongManagerTypeManager wrongManagerTypeManager = new WrongManagerTypeManager(IBlockState.class, 16);

   @Override
   public IBlockState getBlockState(BlockPos blockPos) {
      return this.getIBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public int computeCountByBlockPos(BlockPos blockPos, int count9) {
      return this.computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ(), count9);
   }

   @Override
   public int computeCount(BlockPos blockPos, EnumFacing enumFacing) {
      return this.listenerAdapter28.computeCount(blockPos, enumFacing);
   }

   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos) {
      return this.listenerAdapter28.resolveTileEntity(blockPos);
   }

   private int computeCount(BlockPos blockPos, int count9) {
      return this.computeCountByIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ(), count9);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(BlockPos blockPos) {
      return this.listenerAdapter28.resolveBiomeIDIsOutOfBoundsManager(blockPos);
   }

   @Override
   public int computeCount(int count9, int count10, int count11, int count12) {
      int count13 = this.computeCount(count9, count10, count11);
      if (count13 >= 0 && count13 < this.count2 && this.countArray != null) {
         int count14 = this.countArray[count13];
         if (count14 == -1) {
            count14 = this.computeCountByIndex(count9, count10, count11, count12);
            this.countArray[count13] = count14;
         }

         return count14;
      } else {
         return this.computeCountByIndex(count9, count10, count11, count12);
      }
   }

   @Override
   public boolean isEnabled() {
      return this.listenerAdapter28.isEnabled();
   }

   public void updateState() {
      if (this.countArray == null) {
         this.countArray = (int[])wrongManagerTypeManager2.resolveTarget(this.count2);
      }

      Arrays.fill(this.countArray, -1);
      if (this.iBlockStateArray == null) {
         this.iBlockStateArray = (IBlockState[])wrongManagerTypeManager.resolveTarget(this.count2);
      }

      Arrays.fill(this.iBlockStateArray, null);
   }

   @Override
   public InfoManager getInfoManager() {
      return this.listenerAdapter28.getInfoManager();
   }

   @Override
   public boolean checkCondition(int count9, int count10, int count11) {
      return this.listenerAdapter28.checkCondition(count9, count10, count11);
   }

   @Override
   public IBlockState getIBlockState(int count9, int count10, int count11) {
      int count12 = this.computeCount(count9, count10, count11);
      if (count12 >= 0 && count12 < this.count2 && this.iBlockStateArray != null) {
         IBlockState blockState = this.iBlockStateArray[count12];
         if (blockState == null) {
            blockState = this.listenerAdapter28.getIBlockState(count9, count10, count11);
            this.iBlockStateArray[count12] = blockState;
         }

         return blockState;
      } else {
         return this.listenerAdapter28.getIBlockState(count9, count10, count11);
      }
   }

   private int computeCountByIndex(int count9, int count10, int count11, int count12) {
      int count13 = this.listenerAdapter28.computeCount(count9, count10, count11, count12);
      if (this.enabled && !this.getIBlockState(count9, count10, count11).getBlock().processAction15()) {
         count13 = TargetEstimator.computeCount(count9, count10, count11, count13);
      }

      return count13;
   }

   public void updateState2() {
      wrongManagerTypeManager2.applyTarget(this.countArray);
      this.countArray = null;
      wrongManagerTypeManager.applyTarget(this.iBlockStateArray);
      this.iBlockStateArray = null;
   }

   @Override
   public IBlockState getIBlockState2(double posX, double posY, double posZ) {
      return this.getIBlockState(MathHelper.ceiling_double_int(posX), MathHelper.ceiling_double_int(posY), MathHelper.ceiling_double_int(posZ));
   }

   public boolean matchesBlockPos(BlockPos blockPos, EnumFacing enumFacing, boolean flag) {
      return false;
   }

   @Override
   public boolean isAirBlock(BlockPos blockPos) {
      return this.checkCondition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   private int computeCount(int count9, int count10, int count11) {
      int count12 = count9 - this.count5;
      if (count12 >= 0 && count12 < this.count4) {
         int count13 = count10 - this.count6;
         if (count13 >= 0 && count13 < this.count) {
            int count14 = count11 - this.count8;
            return count14 >= 0 && count14 < this.count7 ? count14 * this.count3 + count13 * this.count4 + count12 : -1;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public GoldenPickaxe(ListenerAdapter28 listenerAdapter, BlockPos blockPos, BlockPos blockPos2, int count9) {
      this.listenerAdapter28 = listenerAdapter;
      int count10 = blockPos.getX() - count9 >> 4;
      int count11 = blockPos.getY() - count9 >> 4;
      int count12 = blockPos.getZ() - count9 >> 4;
      int count13 = blockPos2.getX() + count9 >> 4;
      int count14 = blockPos2.getY() + count9 >> 4;
      int count15 = blockPos2.getZ() + count9 >> 4;
      this.count4 = count13 - count10 + 1 << 4;
      this.count = count14 - count11 + 1 << 4;
      this.count7 = count15 - count12 + 1 << 4;
      this.count3 = this.count4 * this.count;
      this.count2 = this.count4 * this.count * this.count7;
      this.count5 = count10 << 4;
      this.count6 = count11 << 4;
      this.count8 = count12 << 4;
   }
}
