package client.util;

import com.google.common.base.Function;
import net.minecraft.util.IChatComponent;

public final class SecondaryFunctionAdapter implements Function {
   public IChatComponent resolveIChatComponent(IChatComponent iChatComponent) {
      IChatComponent iChatComponent2 = iChatComponent.getIChatComponent();
      iChatComponent2.getIChat(iChatComponent2.getStyleHasParentManager().getStyleHasParentManager());
      return iChatComponent2;
   }

   public Object apply(Object entry) {
      return this.resolveIChatComponent((IChatComponent)entry);
   }
}
