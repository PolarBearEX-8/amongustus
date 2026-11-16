package client.event;

import client.setting.MovementThresholdProperty;
import client.util.AlertNotificationSound;
import client.util.InternalFloatEntry;
import client.util.InterpolationCollector;
import client.util.LoggerEntryAdapter;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiplayerChunkCacheListener implements IChunkProvider {
   public static final Logger logger = LogManager.getLogger();
   public AlertNotificationSound alertNotificationSound;
   public InternalFloatEntry internalFloatEntry = new InternalFloatEntry();
   public List list = Lists.newArrayList();
   public World world;

   public void applyIndex(int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = this.resolveAlertNotificationSound(count, count2);
      if (!alertNotificationSound2.canExecute()) {
         alertNotificationSound2.updateState2();
      }

      this.internalFloatEntry.resolveTargetByTime(InterpolationCollector.computeTimestamp(count, count2));
      this.list.remove(alertNotificationSound2);
   }

   @Override
   public void updateState() {
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return true;
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = (AlertNotificationSound)this.internalFloatEntry.resolveTarget(InterpolationCollector.computeTimestamp(count, count2));
      return alertNotificationSound2 == null ? this.alertNotificationSound : alertNotificationSound2;
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      return true;
   }

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider, int count, int count2) {
   }

   @Override
   public boolean isActive() {
      long timestamp = System.currentTimeMillis();
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((AlertNotificationSound)iterator.next()).setEnabled4(System.currentTimeMillis() - timestamp > 5L);
      }

      if (System.currentTimeMillis() - timestamp > 100L) {
         logger.info("Warning: Clientside chunk ticking took {} ms", new Object[]{System.currentTimeMillis() - timestamp});
      }

      return false;
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      return null;
   }

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound2, int count, int count2) {
   }

   @Override
   public BlockPos resolveBlockPos(World world2, String text, BlockPos blockPos) {
      return null;
   }

   @Override
   public int size() {
      return this.list.size();
   }

   @Override
   public String getText() {
      return "MultiplayerChunkCache: " + this.internalFloatEntry.getCount2() + ", " + this.list.size();
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound2, int count, int count2) {
      return false;
   }

   public AlertNotificationSound resolveAlertNotificationSoundByIndex(int count, int count2) {
      AlertNotificationSound alertNotificationSound2 = new AlertNotificationSound(this.world, count, count2);
      this.internalFloatEntry.applyTarget(InterpolationCollector.computeTimestamp(count, count2), alertNotificationSound2);
      this.list.add(alertNotificationSound2);
      alertNotificationSound2.setEnabled5(true);
      return alertNotificationSound2;
   }

   public MultiplayerChunkCacheListener(World world2) {
      this.alertNotificationSound = new LoggerEntryAdapter(world2, 0, 0);
      this.world = world2;
   }
}
