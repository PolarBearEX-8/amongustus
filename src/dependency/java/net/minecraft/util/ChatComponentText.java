package net.minecraft.util;

import client.util.BaseManagerStyleIChatComponent;

public class ChatComponentText extends BaseManagerStyleIChatComponent {
   public final String labelText;

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   public ChatComponentText getChatComponentText() {
      ChatComponentText chatComponentText = new ChatComponentText(this.labelText);
      chatComponentText.getIChat(this.getStyleHasParentManager().getStyleHasParentManager2());

      for (IChatComponent iChatComponent : (Iterable<IChatComponent>) (Iterable) this.getList()) {
         chatComponentText.appendChatComponent(iChatComponent.getIChatComponent());
      }

      return chatComponentText;
   }

   @Override
   public IChatComponent getIChatComponent() {
      return this.getChatComponentText();
   }

   public String getLabelTextAsText() {
      return this.labelText;
   }

   public ChatComponentText(String text) {
      this.labelText = text;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ChatComponentText)) {
         return false;
      } else {
         ChatComponentText chatComponentText = (ChatComponentText)other;
         return this.labelText.equals(chatComponentText.getLabelTextAsText()) && super.equals(other);
      }
   }

   @Override
   public String toString() {
      return "TextComponent{text='" + this.labelText + '\'' + ", siblings=" + this.list + ", style=" + this.getStyleHasParentManager() + '}';
   }
}
