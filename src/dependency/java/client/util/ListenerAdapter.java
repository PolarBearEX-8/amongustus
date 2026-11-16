package client.util;

import client.event.DynamicListener;
import client.setting.ClientSettingsBlackType;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ListenerAdapter implements DynamicListener {
   public final Function function;
   public final Consumer consumer;
   public final List list;
   public final Function function2;
   public final Supplier supplier;

   @Override
   public Set getSet() {
      LinkedHashSet set = new LinkedHashSet();

      for (Object element : this.list) {
         String text = (String)this.function2.apply(element);
         if (element.equals(this.supplier.get())) {
            set.add("§c" + text);
         } else {
            set.add(text);
         }
      }

      return set;
   }

   @Override
   public void run(String text) {
      this.consumer.accept(this.function.apply(ClientSettingsBlackType.resolveText(text)));
   }

   public ListenerAdapter(List list2, Supplier supplier2, Consumer consumer2, Function mapper, Function mapper2) {
      this.list = new LinkedList(list2);
      this.supplier = supplier2;
      this.consumer = consumer2;
      this.function2 = mapper;
      this.function = mapper2;
   }
}
