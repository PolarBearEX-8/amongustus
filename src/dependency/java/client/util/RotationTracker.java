package client.util;

import java.util.concurrent.Callable;

public final class RotationTracker implements Callable {
   public final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager;

   public RotationTracker(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2) {
      this.biomeIDIsOutOfBoundsManager = biomeIDIsOutOfBoundsManager2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return String.valueOf(this.biomeIDIsOutOfBoundsManager);
   }
}
