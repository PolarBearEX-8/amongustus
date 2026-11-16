package client.util;

import client.event.CompositeListener;
import client.setting.MovementThresholdProperty;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class AngleProvider implements IChunkProvider {
   public static final List list = Lists.newArrayList();
   public static final int count;
   public static final int count2;
   public final World world;

   @Override
   public BlockPos resolveBlockPos(World world2, String text, BlockPos blockPos) {
      return null;
   }

   public AngleProvider(World world2) {
      this.world = world2;
   }

   static {
      for (Block block : (Iterable<Block>) Block.iterableExtensionBridge) {
         list.addAll(block.getBlockManager().getImmutableList());
      }

      count = MathHelper.computeCount(MathHelper.sin((float)list.size()));
      count2 = MathHelper.computeCount((float)list.size() / (float)count);
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound, int count3, int count4) {
      return false;
   }

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, int count3, int count4) {
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      return true;
   }

   public static IBlockState resolveIBlockState(int count3, int count4) {
      IBlockState blockState = null;
      if (count3 > 0 && count4 > 0 && count3 % 2 != 0 && count4 % 2 != 0) {
         int count5 = count3 / 2;
         int count6 = count4 / 2;
         if (count5 <= count && count6 <= count2) {
            int count7 = MathHelper.computeCount(count5 * count + count6);
            if (count7 < list.size()) {
               blockState = (IBlockState)list.get(count7);
            }
         }
      }

      return blockState;
   }

   @Override
   public boolean checkIndex(int count3, int count4) {
      return true;
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count3, int count4) {
      ShortArrayEntry shortArrayEntry = new ShortArrayEntry();

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            int count5 = count3 * 16 + index;
            int count6 = count4 * 16 + index2;
            shortArrayEntry.applyIBlockState(index, 60, index2, Blocks.block145.getDefaultState());
            IBlockState blockState = resolveIBlockState(count5, count6);
            if (blockState != null) {
               shortArrayEntry.applyIBlockState(index, 70, index2, blockState);
            }
         }
      }

      AlertNotificationSound alertNotificationSound = new AlertNotificationSound(this.world, shortArrayEntry, count3, count4);
      alertNotificationSound.updateState8();
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = this.world
         .getInvalidBiomeIdManager()
         .resolveBiomeIDIsOutOfBoundsManagerA2((BiomeIDIsOutOfBoundsManager[])null, count3 * 16, count4 * 16, 16, 16);
      byte[] byteFlagArray = alertNotificationSound.getByteFlagArray();

      for (int index3 = 0; index3 < byteFlagArray.length; index3++) {
         byteFlagArray[index3] = (byte)biomeIDIsOutOfBoundsManagerArray[index3].count;
      }

      alertNotificationSound.updateState8();
      return alertNotificationSound;
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      return this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos).resolveList(movementThresholdProperty);
   }

   @Override
   public String getText() {
      return "DebugLevelSource";
   }

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider, int count3, int count4) {
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public void updateState() {
   }

   @Override
   public int size() {
      return 0;
   }
}
