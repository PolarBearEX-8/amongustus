package net.minecraft.util;


public class ChatComponentTranslationFormatException extends IllegalArgumentException {

   public ChatComponentTranslationFormatException(ChatComponentTranslation chatComponentTranslation, Throwable t) {
      super(String.format("Error while parsing: %s", chatComponentTranslation), t);
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation chatComponentTranslation, int n) {
      super(String.format("Invalid index %d requested for %s", n, chatComponentTranslation));
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation chatComponentTranslation, String str) {
      super(String.format("Error parsing: %s: %s", chatComponentTranslation, str));
   }
}
