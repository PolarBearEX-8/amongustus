package net.minecraft.util;


public class ChatComponentSelector extends ChatComponentStyle {
   private final String selector;

   public String getSelector() {
      return this.selector;
   }

   @Override
   public IChatComponent copy() {
      return this.createCopy();
   }

   public ChatComponentSelector(String str) {
      this.selector = str;
   }

   public ChatComponentSelector createCopy() {
      ChatComponentSelector chatComponentSelector = new ChatComponentSelector(this.selector);
      chatComponentSelector.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent component : (Iterable<IChatComponent>)(this.getSiblings())) {
         chatComponentSelector.appendSibling(component.copy());
      }

      return chatComponentSelector;
   }

   @Override
   public String getUnformattedTextForChat() {
      return this.selector;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatComponentSelector)) {
         return false;
      } else {
         ChatComponentSelector chatComponentSelector = (ChatComponentSelector)obj;
         return this.selector.equals(chatComponentSelector.selector) && super.equals(obj);
      }
   }

   @Override
   public String toString() {
      return "SelectorComponent{pattern='" + this.selector + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatStyle() + '}';
   }
}
