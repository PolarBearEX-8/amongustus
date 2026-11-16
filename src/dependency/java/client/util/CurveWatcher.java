package client.util;

import java.util.concurrent.Callable;

public final class CurveWatcher implements Callable {
   public final BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public CurveWatcher(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2) {
      this.biomeIDIsOutOfBoundsManager = biomeIDIsOutOfBoundsManager2;
   }

   public String getText() throws Exception {
      return String.valueOf(this.biomeIDIsOutOfBoundsManager);
   }
}
