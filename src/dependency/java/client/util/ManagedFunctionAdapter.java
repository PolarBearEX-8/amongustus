package client.util;

import com.google.common.base.Function;
import java.util.Iterator;
import net.minecraft.util.IChatComponent;

public final class ManagedFunctionAdapter implements Function {
   public Iterator iterator(IChatComponent iChatComponent) {
      return iChatComponent.iterator();
   }

   public Object apply(Object entry) {
      return this.iterator((IChatComponent)entry);
   }
}
