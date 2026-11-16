package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventWindowAction extends AbstractEvent {
   public int count2;
   public int count3;
   public static final int count = 56;

   public int getIndex() {
      return this.count3;
   }

   public void call(Object entry) {
      WindowActionListener windowActionListener = (WindowActionListener)entry;
      this.applyWindowActionListener(windowActionListener);
   }

   public void applyWindowActionListener(WindowActionListener windowActionListener) {
      windowActionListener.onWindowAction(this);
   }

   public int getIndex2() {
      return this.count2;
   }

   public EventWindowAction(int count4, int count5) {
      this.count2 = count4;
      this.count3 = count5;
   }
}
