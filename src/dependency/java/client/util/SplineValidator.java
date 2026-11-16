package client.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;

public class SplineValidator implements Callable {
   public final BlockPos blockPos;
   public final AlertNotificationSound alertNotificationSound;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.blockPos);
   }

   public SplineValidator(AlertNotificationSound alertNotificationSound2, BlockPos blockPos2) {
      this.alertNotificationSound = alertNotificationSound2;
      this.blockPos = blockPos2;
   }
}
