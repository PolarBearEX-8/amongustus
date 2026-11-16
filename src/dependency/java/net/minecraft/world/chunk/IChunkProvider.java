package net.minecraft.world.chunk;

import client.event.CompositeListener;
import client.setting.MovementThresholdProperty;
import client.util.AlertNotificationSound;
import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IChunkProvider {
   void updateState();

   AlertNotificationSound resolveAlertNotificationSound(int count, int count2);

   boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener);

   boolean checkIndex(int count, int count2);

   boolean isActive();

   void applyIChunkProvider(IChunkProvider iChunkProvider, int count, int count2);

   BlockPos resolveBlockPos(World world, String text, BlockPos blockPos);

   void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, int count, int count2);

   List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos);

   int size();

   String getText();

   AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos);

   boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound, int count, int count2);

   boolean isActiveAsFlag();
}
