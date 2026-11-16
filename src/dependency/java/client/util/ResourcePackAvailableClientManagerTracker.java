package client.util;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class ResourcePackAvailableClientManagerTracker extends ClientManagerTracker {
   public ResourcePackAvailableClientManagerTracker(Minecraft mc, int count, int count2, List list) {
      super(mc, count, count2, list);
   }

   @Override
   public String getText() {
      return StatCollector.getText("resourcePack.available.title", new Object[0]);
   }
}
