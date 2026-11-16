package client.util;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.util.ChatComponentText;

public class ChatComponentTextGenericFutureListener implements GenericFutureListener {
   public final ChatComponentText chatComponentText;
   public final AuxiliaryListenerAdapter4 auxiliaryListenerAdapter4;

   public void operationComplete(Future future) throws Exception {
      this.auxiliaryListenerAdapter4.networkManager.applyIChatComponent(this.chatComponentText);
   }

   public ChatComponentTextGenericFutureListener(AuxiliaryListenerAdapter4 auxiliaryListenerAdapter, ChatComponentText chatComponentText2) {
      this.auxiliaryListenerAdapter4 = auxiliaryListenerAdapter;
      this.chatComponentText = chatComponentText2;
   }
}
