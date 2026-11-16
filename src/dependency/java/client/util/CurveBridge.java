package client.util;

import client.event.DynamicListener;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CurveBridge implements DynamicListener {
   public final Supplier supplier;
   public final List list;
   public final String labelText;
   public final Consumer consumer;

   public CurveBridge(String text, List list2, Supplier supplier2, Consumer consumer2) {
      this.labelText = text;
      this.list = list2;
      this.supplier = supplier2;
      this.consumer = consumer2;
   }

   @Override
   public void run(String text) {
      int count = (Integer)this.supplier.get();
      if (++count >= this.list.size()) {
         count = 0;
      }

      this.consumer.accept(count);
   }

   @Override
   public String getLabelText() {
      return this.labelText + ": §6" + (String)this.list.get((Integer)this.supplier.get());
   }
}
