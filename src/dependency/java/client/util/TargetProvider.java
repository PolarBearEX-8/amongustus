package client.util;

import client.event.PreUpdateListener;
import net.minecraft.util.IChatComponent;

public class TargetProvider extends DuplicateStatIdManager {
   @Override
   public DuplicateStatIdManager getDuplicateStatIdManagerAsDuplicateStatIdM() {
      super.getDuplicateStatIdManagerAsDuplicateStatIdM();
      StatCraftItemManager.list2.add(this);
      return this;
   }

   public TargetProvider(String text, IChatComponent iChatComponent) {
      super(text, iChatComponent);
   }

   public TargetProvider(String text, IChatComponent iChatComponent, PreUpdateListener preUpdateListener) {
      super(text, iChatComponent, preUpdateListener);
   }
}
