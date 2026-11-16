package client.util;

import client.event.DynamicListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExtendedListenerAdapter implements DynamicListener {
   public final String labelText;
   public final Supplier supplier;
   public final Consumer consumer;

   @Override
   public void run(String text) {
      this.consumer.accept(!(Boolean)this.supplier.get());
   }

   @Override
   public String getLabelText() {
      return (((boolean)this.supplier.get() ? "§a" : "§4")) + this.labelText;
   }

   public ExtendedListenerAdapter(String text, Supplier supplier2, Consumer consumer2) {
      this.labelText = text;
      this.supplier = supplier2;
      this.consumer = consumer2;
   }
}
