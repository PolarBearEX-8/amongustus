package client.util;

import java.util.HashMap;
import java.util.function.Function;

public class AdvancedHashMapEntry {
   public static final HashMap hashMap = new HashMap();

   public static ConsumerEntry resolveConsumerEntry(int count, int count2) {
      return (ConsumerEntry)((Function)hashMap.get(count)).apply(count2);
   }

   static {
      hashMap.put(0, (Function<Integer, ConsumerEntry>)InternalPacketListenerAdapter::new);
      hashMap.put(1, (Function<Integer, ConsumerEntry>)ConsumerEntryAdapter::new);
      hashMap.put(2, (Function<Integer, ConsumerEntry>)AbstractEventCallbackBridge::new);
      hashMap.put(3, (Function<Integer, ConsumerEntry>)TickListenerAdapter::new);
      hashMap.put(4, (Function<Integer, ConsumerEntry>)ExtendedPacketListenerAdapter::new);
      hashMap.put(5, (Function<Integer, ConsumerEntry>)ClickListenerAdapter::new);
      hashMap.put(6, (Function<Integer, ConsumerEntry>)AuxiliaryTickListenerAdapter::new);
   }
}
