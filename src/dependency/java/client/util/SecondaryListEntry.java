package client.util;

import client.setting.BooleanSetting;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.ServiceItemEntry;

public class SecondaryListEntry {
   public final List list = new LinkedList();

   public SecondaryListEntry add(String text, Runnable task) {
      this.list.add(new InternalListenerAdapter(text, task));
      return this;
   }

   public SecondaryListEntry add(String text, Supplier supplier, Consumer consumer) {
      this.list.add(new ExtendedListenerAdapter(text, supplier, consumer));
      return this;
   }

   public SecondaryListEntry add(List list2, Supplier supplier, Consumer consumer, Function mapper, Function mapper2) {
      this.list.add(new ListenerAdapter(list2, supplier, consumer, mapper, mapper2));
      return this;
   }

   public ServiceItemEntry getServiceItemEntry() {
      return new ServiceItemEntry(this.list);
   }

   public SecondaryListEntry add(String text, List list2, Supplier supplier, Consumer consumer) {
      this.list.add(new CurveBridge(text, list2, supplier, consumer));
      return this;
   }

   @Deprecated
   public SecondaryListEntry add(Supplier supplier, Runnable task) {
      this.list.add(new SecondaryListenerRegistry(supplier, task));
      return this;
   }

   public SecondaryListEntry add(String text) {
      this.list.add(new AuxiliaryListenerAdapter(text));
      return this;
   }

   public SecondaryListEntry resolveSecondaryListEntry(List list2, Supplier supplier, Consumer consumer) {
      this.list.add(new ListenerAdapter(list2, supplier, consumer, Function.identity(), Function.identity()));
      return this;
   }

   public SecondaryListEntry add(String text, BooleanSetting setting) {
      this.list.add(new FovDeserializer(text, setting));
      return this;
   }

   public SecondaryListEntry add() {
      this.list.add(new VectorTracker());
      return this;
   }
}
