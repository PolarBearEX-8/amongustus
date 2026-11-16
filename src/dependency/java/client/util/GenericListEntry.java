package client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.labymod.ModelProperties;

public class GenericListEntry {
   public List list = new ArrayList();
   public ModelProperties modelProperties;

   private ListenerAdapter23 resolveListenerAdapter23(String text, ListenerAdapter23 listenerAdapter) {
      if (listenerAdapter.labelText.equals(text)) {
         return listenerAdapter;
      } else {
         for (ListenerAdapter23 listenerAdapter2 : (Iterable<ListenerAdapter23>)(Iterable<?>)listenerAdapter.list2) {
            if (listenerAdapter2.labelText.equals(text)) {
               return listenerAdapter2;
            }

            ListenerAdapter23 listenerAdapter3 = this.resolveListenerAdapter23(text, listenerAdapter2);
            if (listenerAdapter3 != null) {
               return listenerAdapter3;
            }
         }

         return null;
      }
   }

   public Optional resolveOptional(String text) {
      for (ListenerAdapter23 listenerAdapter : (Iterable<ListenerAdapter23>)(Iterable<?>)this.list) {
         ListenerAdapter23 listenerAdapter2 = this.resolveListenerAdapter23(text, listenerAdapter);
         if (listenerAdapter2 != null) {
            return Optional.of(listenerAdapter2);
         }
      }

      return Optional.empty();
   }
}
