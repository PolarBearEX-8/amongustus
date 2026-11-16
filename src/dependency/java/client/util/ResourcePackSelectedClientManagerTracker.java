package client.util;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

public class ResourcePackSelectedClientManagerTracker extends ClientManagerTracker {
   @Override
   public String getText() {
      return StatCollector.getText("resourcePack.selected.title", new Object[0]);
   }

   public ResourcePackSelectedClientManagerTracker(Minecraft mc, int count, int count2, List list) {
      super(mc, count, count2, list);
   }
}
