package client.util;

import client.event.SimpleListener;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class ListenerProcessor implements SimpleListener {
   public final Set set;
   public final JsonManager jsonManager;

   @Override
   public void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      for (ResourceLocation location : (Set<ResourceLocation>)this.set) {
         ServiceStringEntry serviceStringEntry = listenerAdapter.resolveServiceStringEntry(location);
         JsonManager.resolveMap(this.jsonManager).put(location, serviceStringEntry);
      }
   }

   public ListenerProcessor(JsonManager jsonManager2, Set set2) {
      this.jsonManager = jsonManager2;
      this.set = set2;
   }
}
