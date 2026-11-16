package client.util;

import client.event.PreRenderObserver;

public class BezierPipeline implements PreRenderObserver {
   public final SharedDoubleEntry sharedDoubleEntry;

   public BezierPipeline(SharedDoubleEntry sharedDoubleEntry2) {
      this.sharedDoubleEntry = sharedDoubleEntry2;
   }

   @Override
   public void applyIndex(int count) {
      this.sharedDoubleEntry.value2++;
   }
}
