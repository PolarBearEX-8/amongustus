package client.util;

public class AnimationTimer {
   public int indexOffset = 0;

   public void processAction() {
      this.indexOffset++;
   }

   public boolean checkCondition(int count) {
      return this.indexOffset >= count;
   }

   public long processAction2() {
      return (long)this.indexOffset;
   }

   public void processAction3() {
      this.indexOffset = 0;
   }
}
