package client.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProgramStackEmptyManager {
   public Deque deque = new ArrayDeque();

   public NoneManager getNoneManager() {
      if (this.deque.isEmpty()) {
         throw new RuntimeException("Program stack empty");
      } else {
         return (NoneManager)this.deque.pollLast();
      }
   }

   public void applyNoneManager(NoneManager noneManager) {
      this.deque.addLast(noneManager);
      if (this.deque.size() > 100) {
         throw new RuntimeException("Program stack overflow: " + this.deque.size());
      }
   }
}
