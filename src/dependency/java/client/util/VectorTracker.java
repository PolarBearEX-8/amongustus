package client.util;

public class VectorTracker extends InternalListenerAdapter {
   private static void updateState() {
   }

   public VectorTracker() {
      super("", VectorTracker::updateState);
   }
}
