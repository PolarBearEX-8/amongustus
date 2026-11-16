package client.util;

public class MouseHelper extends SmoothProfiler {
   @Override
   public void updateState2() {
      super.updateState2();
      FoundMouseManager.count3 = 0;
      FoundMouseManager.count2 = 0;
   }

   @Override
   public void setGrabbed() {
      super.setGrabbed();
      FoundMouseManager.count3 = 0;
      FoundMouseManager.count2 = 0;
   }
}
