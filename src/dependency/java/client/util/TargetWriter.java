package client.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;

public class TargetWriter implements Callable {
   public final int count;
   public final int count2;
   public final int count3;
   public final AlertNotificationSound alertNotificationSound;

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(
         new BlockPos(this.alertNotificationSound.indexOffset1 * 16 + this.count, this.count2, this.alertNotificationSound.indexOffset * 16 + this.count3)
      );
   }

   public TargetWriter(AlertNotificationSound alertNotificationSound2, int count4, int count5, int count6) {
      this.alertNotificationSound = alertNotificationSound2;
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
