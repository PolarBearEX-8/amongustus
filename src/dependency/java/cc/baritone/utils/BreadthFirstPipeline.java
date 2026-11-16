package cc.baritone.utils;

import client.util.AlertNotificationSound;

public interface BreadthFirstPipeline {
   java.util.ArrayList resolveArrayList(String text, int count, int count2, int count3, int count4);

   boolean checkIndex(int count, int count2);

   void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound);

   BlockStateService resolveBlockStateService(int count, int count2);

   void updateState2();

   void updateState();
}
