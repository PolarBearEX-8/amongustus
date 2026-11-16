package client.util;

import client.event.PostMotionObserver;

public abstract class LocalStringEntry implements PostMotionObserver {
   public String labelText;
   public PostMotionObserver[] postMotionObserverArray;

   public int getCount() {
      return 0;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public double computeValue(int count) {
      return count >= 0 && count < this.postMotionObserverArray.length ? this.postMotionObserverArray[count].getValue() : 0.0;
   }

   public LocalStringEntry(PostMotionObserver[] postMotionObserverArray2, String text) throws Exception {
      if (postMotionObserverArray2.length < this.getCount()) {
         String text2 = String.format("Function '%s' requires at least %s arguments. %s are given!", this.getLabelText(), this.getCount(), postMotionObserverArray2.length);
         throw new Exception(text2);
      } else {
         this.postMotionObserverArray = postMotionObserverArray2;
         this.labelText = text;
      }
   }

   @Override
   public String toString() {
      String text = "";

      for (int index = 0; index < this.postMotionObserverArray.length; index++) {
         text = text + this.postMotionObserverArray[index].toString();
         if (index < this.postMotionObserverArray.length - 1) {
            text = text + ", ";
         }
      }

      return this.getLabelText() + "(" + text + ")";
   }
}
