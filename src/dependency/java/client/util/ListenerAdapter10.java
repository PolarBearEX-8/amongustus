package client.util;

import client.event.PostMotionObserver;
import client.setting.CompositeOptionType;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.IronBoots;

public abstract class ListenerAdapter10 implements PostMotionObserver {
   public DefaultItemEntry defaultItemEntry;

   public static boolean matchesListenerAdapterByListenerAdapter1(ListenerAdapter10 listenerAdapter) {
      double offset = 1.0;
      return matchesListenerAdapter10(listenerAdapter, offset);
   }

   public static boolean matchesListenerAdapter10(ListenerAdapter10 listenerAdapter) {
      double offset = 0.0;
      return matchesListenerAdapter10(listenerAdapter, offset);
   }

   public JsonElement getJsonElement() {
      return new JsonPrimitive(this.toString());
   }

   public static boolean matchesListenerAdapter2(ListenerAdapter10 listenerAdapter) {
      return listenerAdapter instanceof IronBoots ? ((IronBoots)listenerAdapter).postMotionObserver instanceof ExtendedListenerController : false;
   }

   public static boolean matchesListenerAdapter10(ListenerAdapter10 listenerAdapter, double offset) {
      if (!(listenerAdapter instanceof IronBoots)) {
         return false;
      } else {
         IronBoots ironBoots = (IronBoots)listenerAdapter;
         return ironBoots.postMotionObserver instanceof ExtendedListenerController && CompositeOptionType.abs(ironBoots.postMotionObserver.getValue(), offset);
      }
   }

   public ListenerAdapter10(DefaultItemEntry defaultItemEntry2) {
      this.defaultItemEntry = defaultItemEntry2;
   }
}
