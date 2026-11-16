package client.util;

import client.setting.CombatGroupProperty;
import java.util.ArrayDeque;
import java.util.Arrays;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IBlockStateHolder extends ListenerAdapter28 {
   public static final IBlockState iBlockState = Blocks.air.getDefaultState();
   public final int count5;
   public final int count7;
   public final int count6;
   public int[] countArray;
   public IBlockState[] iBlockStateArray;
   public static ArrayDeque arrayDeque = new ArrayDeque();
   public static ArrayDeque arrayDeque2 = new ArrayDeque();
   public static int count3 = BuildManager.computeCount(Runtime.getRuntime().availableProcessors(), 1, 32);

   @Override
   public IBlockState getBlockState(BlockPos blockPos) {
      int count = this.computeCount(blockPos);
      IBlockState blockState = this.iBlockStateArray[count];
      if (blockState == null) {
         blockState = this.resolveIBlockState(blockPos);
         this.iBlockStateArray[count] = blockState;
      }

      return blockState;
   }

   private IBlockState resolveIBlockState(int count, int count2, int count4) {
      if (count2 >= 0 && count2 < 256) {
         int count8 = (count >> 4) - this.count2;
         int count9 = (count4 >> 4) - this.count;
         return this.alertNotificationSoundArray[count8][count9].resolveIBlockState(count, count2, count4);
      } else {
         return iBlockState;
      }
   }

   public void updateState() {
      applyIndexArray(this.countArray);
      applyIBlockStateArray(this.iBlockStateArray);
   }

   private static IBlockState[] resolveIBlockStateArray(int count) {
      synchronized (arrayDeque2) {
         IBlockState[] blockStateArray = (IBlockState[])arrayDeque2.pollLast();
         if (blockStateArray != null && blockStateArray.length >= count) {
            Arrays.fill(blockStateArray, null);
         } else {
            blockStateArray = new IBlockState[count];
         }

         return blockStateArray;
      }
   }

   @Override
   public TileEntity resolveTileEntity(BlockPos blockPos) {
      int count = (blockPos.getX() >> 4) - this.count2;
      int count2 = (blockPos.getZ() >> 4) - this.count;
      return this.alertNotificationSoundArray[count][count2].resolveTileEntity(blockPos, CombatGroupProperty.QUEUED);
   }

   public static void applyIndexArray(int[] countArray2) {
      synchronized (arrayDeque) {
         if (arrayDeque.size() < count3) {
            arrayDeque.add(countArray2);
         }
      }
   }

   @Override
   public int computeCount(int count, int count2, int count4, int count8) {
      int count9 = this.computeCount(count, count2, count4);
      int count10 = this.countArray[count9];
      if (count10 == -1) {
         count10 = super.computeCount(count, count2, count4, count8);
         if (BuildManager.isActive28() && !this.getIBlockState(count, count2, count4).getBlock().processAction15()) {
            count10 = TargetEstimator.computeCount(count, count2, count4, count10);
         }

         this.countArray[count9] = count10;
      }

      return count10;
   }

   private IBlockState resolveIBlockState(BlockPos blockPos) {
      if (blockPos.getY() >= 0 && blockPos.getY() < 256) {
         int count = (blockPos.getX() >> 4) - this.count2;
         int count2 = (blockPos.getZ() >> 4) - this.count;
         return this.alertNotificationSoundArray[count][count2].resolveIBlockState(blockPos);
      } else {
         return iBlockState;
      }
   }

   private static int[] resolveIndexArray(int count) {
      synchronized (arrayDeque) {
         int[] countArray2 = (int[])arrayDeque.pollLast();
         if (countArray2 == null || countArray2.length < count) {
            countArray2 = new int[count];
         }

         return countArray2;
      }
   }

   private int computeCount(BlockPos blockPos) {
      int count = blockPos.getX() - this.count5;
      int count2 = blockPos.getY() - this.count7;
      int count4 = blockPos.getZ() - this.count6;
      return count * 400 + count4 * 20 + count2;
   }

   @Override
   public IBlockState getIBlockState(int count, int count2, int count4) {
      int count8 = this.computeCount(count, count2, count4);
      IBlockState blockState = this.iBlockStateArray[count8];
      if (blockState == null) {
         blockState = this.resolveIBlockState(count, count2, count4);
         this.iBlockStateArray[count8] = blockState;
      }

      return blockState;
   }

   @Override
   public IBlockState getIBlockState2(double posX, double posY, double posZ) {
      int count = MathHelper.ceiling_double_int(posX);
      int count2 = MathHelper.ceiling_double_int(posY);
      int count4 = MathHelper.ceiling_double_int(posZ);
      int count8 = this.computeCount(count, count2, count4);
      IBlockState blockState = this.iBlockStateArray[count8];
      if (blockState == null) {
         blockState = this.resolveIBlockState(count, count2, count4);
         this.iBlockStateArray[count8] = blockState;
      }

      return blockState;
   }

   private int computeCount(int count, int count2, int count4) {
      int count8 = count - this.count5;
      int count9 = count2 - this.count7;
      int count10 = count4 - this.count6;
      return count8 * 400 + count10 * 20 + count9;
   }

   public static void applyIBlockStateArray(IBlockState[] blockStateArray) {
      synchronized (arrayDeque2) {
         if (arrayDeque2.size() < count3) {
            arrayDeque2.add(blockStateArray);
         }
      }
   }

   public IBlockStateHolder(World world, int count, int count2, int count4, int count8, int count9, int count10, int count11) {
      super(world, count, count2, count4, count8, count9, count10, count11);
      this.count5 = count - count11;
      this.count7 = count2 - count11;
      this.count6 = count4 - count11;
      this.countArray = resolveIndexArray(8000);
      Arrays.fill(this.countArray, -1);
      this.iBlockStateArray = resolveIBlockStateArray(8000);
   }
}
