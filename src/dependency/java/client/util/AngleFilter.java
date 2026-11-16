package client.util;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;

public class AngleFilter implements Callable {
   public final BlockPos blockPos;
   public final AlertNotificationSound alertNotificationSound;

   public String getText() throws Exception {
      return CrashReportCategory.resolveText(this.blockPos);
   }

   public AngleFilter(AlertNotificationSound alertNotificationSound2, BlockPos blockPos2) {
      this.alertNotificationSound = alertNotificationSound2;
      this.blockPos = blockPos2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
