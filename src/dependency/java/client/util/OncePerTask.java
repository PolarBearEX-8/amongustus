package client.util;

import java.util.concurrent.Callable;

public class OncePerTask implements Callable {
   public final int count;
   public final SecondaryLoggerEntry secondaryLoggerEntry;

   public String getText() throws Exception {
      String text = "Once per " + this.count + " ticks";
      if (this.count == Integer.MAX_VALUE) {
         text = "Maximum (" + text + ")";
      }

      return text;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public OncePerTask(SecondaryLoggerEntry secondaryLoggerEntry2, int count2) {
      this.secondaryLoggerEntry = secondaryLoggerEntry2;
      this.count = count2;
   }
}
