package net.augustus.modules.modes.clicking;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ClickPattern {
   private int rotationIndex;
   private int delayIndex;
   public ArrayList recordedRotations;
   public final ArrayList unpressedDelays;
   public final Integer[] allDelays;
   public final String name;
   public final ArrayList pressedDelays = new ArrayList();

   public ClickPattern(String str, Integer[] nums, ArrayList list) {
      this.unpressedDelays = new ArrayList();
      this.recordedRotations = new ArrayList();
      this.delayIndex = 0;
      this.rotationIndex = 0;
      this.allDelays = nums;
      this.name = str;
      this.recordedRotations = list;

      for (int i = 0; i < this.allDelays.length - this.allDelays.length % 2; i += 2) {
         this.pressedDelays.add(nums[i]);
         this.unpressedDelays.add(nums[i + 1]);
      }
   }

   public int getRandomUnpressedDelay() {
      return (Integer)this.unpressedDelays.get(ThreadLocalRandom.current().nextInt(this.unpressedDelays.size()));
   }

   public void resetDelayIndex() {
      this.delayIndex = 0;
   }

   public float[] getCurrentRotation() {
      if (this.recordedRotations.isEmpty()) {
         return new float[2];
      } else {
         int rotationIndex2 = this.rotationIndex;
         if (rotationIndex2 >= this.recordedRotations.size()) {
            rotationIndex2 = this.recordedRotations.size() - 1;
         }

         return (float[])this.recordedRotations.get(rotationIndex2);
      }
   }

   public int getRandomPressedDelay() {
      return (Integer)this.pressedDelays.get(ThreadLocalRandom.current().nextInt(this.pressedDelays.size()));
   }

   public void alignToEvenDelayIndex(int n, int n2, int n3) {
      ClickPattern clickPattern = this;
      if (null == null) {
         if (this.delayIndex % 2 == 0) {
            return;
         }

         clickPattern = this;
      }

      clickPattern.delayIndex--;
   }

   public int nextDelay() {
      if (this.delayIndex >= this.allDelays.length) {
         this.delayIndex = 0;
         this.rotationIndex = 0;
      }

      int n = this.allDelays[this.delayIndex];
      this.delayIndex++;
      return n;
   }

   public void advanceRotationIndex() {
      this.rotationIndex++;
   }



}
