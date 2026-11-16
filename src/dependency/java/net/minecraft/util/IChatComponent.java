package net.minecraft.util;

import client.util.StyleHasParentManager;
import java.util.List;

public interface IChatComponent extends Iterable {
   StyleHasParentManager getStyleHasParentManager();

   String getLabelText();

   IChatComponent resolveIChatComponent(String text);

   boolean isActive();

   IChatComponent getIChat(StyleHasParentManager styleHasParentManager);

   String getString();

   IChatComponent appendChatComponent(IChatComponent iChatComponent);

   String getString2();

   List getList();

   IChatComponent getIChatComponent();
}
