package client.util;

import client.event.PreUpdateObserver;
import java.util.ArrayList;
import java.util.Map;

public class FovObserver {
   public VectorQueue[] vectorQueueArray;
   public Callback22[] callback22Array;

   public void updateState2() {
      this.updateState();

      for (int index = 0; index < this.vectorQueueArray.length; index++) {
         this.vectorQueueArray[index].updateState();
      }
   }

   public FovObserver(VectorQueue[] vectorQueueArray2, Map map) {
      this.vectorQueueArray = vectorQueueArray2;
      ArrayList list = new ArrayList();

      for (String text : (Iterable<String>)(Iterable)(map.keySet())) {
         PreUpdateObserver preUpdateObserver = (PreUpdateObserver)map.get(text);
         if (preUpdateObserver instanceof Callback22) {
            Callback22 callback = (Callback22)preUpdateObserver;
            list.add(callback);
         }
      }

      this.callback22Array = ((Callback22[])list.toArray(new Callback22[list.size()]));
   }

   public void updateState3() {
      for (int index = 0; index < this.vectorQueueArray.length; index++) {
         this.vectorQueueArray[index].updateState2();
      }
   }

   private void updateState() {
      for (int index = 0; index < this.callback22Array.length; index++) {
         this.callback22Array[index].updateState();
      }
   }

   public void applyIndex(int count) {
      for (int index = 0; index < this.vectorQueueArray.length; index++) {
         this.vectorQueueArray[index].applyIndex(count);
      }
   }
}
