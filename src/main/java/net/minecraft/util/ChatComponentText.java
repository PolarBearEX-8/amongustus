package net.minecraft.util;


public class ChatComponentText extends ChatComponentStyle {
   private final String text;

   public ChatComponentText createCopy() {
      ChatComponentText chatComponentText = new ChatComponentText(this.text);
      chatComponentText.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent component : (Iterable<IChatComponent>)(this.getSiblings())) {
         chatComponentText.appendSibling(component.copy());
      }

      return chatComponentText;
   }

   public String getChatComponentText_TextValue() {
      return this.text;
   }

   @Override
   public String toString() {
      return "TextComponent{text='" + this.text + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatStyle() + '}';
   }

   public ChatComponentText(String str) {
      this.text = str;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatComponentText)) {
         return false;
      } else {
         ChatComponentText chatComponentText = (ChatComponentText)obj;
         return this.text.equals(chatComponentText.getChatComponentText_TextValue()) && super.equals(obj);
      }
   }

   @Override
   public IChatComponent copy() {
      return this.createCopy();
   }

   @Override
   public String getUnformattedTextForChat() {
      return this.text;
   }
}
