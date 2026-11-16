package client.util;

import com.google.common.util.concurrent.FutureCallback;
import java.io.File;
import net.minecraft.client.resources.ResourceLanguageManager;

public class TargetEngine implements FutureCallback {
   public final File file;
   public final ResourceLanguageManager resourceLanguageManager;

   public void onSuccess(Object entry) {
      this.resourceLanguageManager.resolveListenableFuture(this.file);
   }

   public void onFailure(Throwable throwable) {
   }

   public TargetEngine(ResourceLanguageManager resourceLanguageManager2, File file2) {
      this.resourceLanguageManager = resourceLanguageManager2;
      this.file = file2;
   }
}
