package net.minecraft.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatComponentTranslation extends ChatComponentStyle {
   private final String key;
   private final Object[] formatArgs;
   private final Object syncLock = new Object();
   private long lastTranslationUpdateTimeInMilliseconds = -1L;
   public List children = Lists.newArrayList();
   public static final Pattern stringVariablePattern = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

   private IChatComponent getFormatArgumentAsComponent(int n) {
      if (n >= this.formatArgs.length) {
         throw new ChatComponentTranslationFormatException(this, n);
      } else {
         Object obj = this.formatArgs[n];
         Object obj2;
         if (obj instanceof IChatComponent) {
            obj2 = (IChatComponent)obj;
         } else {
            obj2 = new ChatComponentText(obj == null ? "null" : obj.toString());
            ((IChatComponent)obj2).getChatStyle().setParentStyle(this.getChatStyle());
         }

         return (IChatComponent)obj2;
      }
   }

   @Override
   public String getUnformattedTextForChat() {
      this.ensureInitialized();
      StringBuilder sb = new StringBuilder();

      for (IChatComponent component : (Iterable<IChatComponent>)(this.children)) {
         sb.append(component.getUnformattedTextForChat());
      }

      return sb.toString();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatComponentTranslation)) {
         return false;
      } else {
         ChatComponentTranslation chatComponentTranslation = (ChatComponentTranslation)obj;
         return Arrays.equals(this.formatArgs, chatComponentTranslation.formatArgs) && this.key.equals(chatComponentTranslation.key) && super.equals(obj);
      }
   }

   public Object[] getFormatArgs() {
      return this.formatArgs;
   }

   @Override
   public Iterator iterator() {
      this.ensureInitialized();
      return Iterators.concat(createDeepCopyIterator(this.children), createDeepCopyIterator(this.siblings));
   }

   public String getKey() {
      return this.key;
   }

   @Override
   public String toString() {
      return "TranslatableComponent{key='"
         + this.key
         + '\''
         + ", args="
         + Arrays.toString(this.formatArgs)
         + ", siblings="
         + this.siblings
         + ", style="
         + this.getChatStyle()
         + '}';
   }

   @Override
   public IChatComponent setChatStyle(ChatStyle chatStyle) {
      super.setChatStyle(chatStyle);

      for (Object obj : this.formatArgs) {
         if (obj instanceof IChatComponent) {
            ((IChatComponent)obj).getChatStyle().setParentStyle(this.getChatStyle());
         }
      }

      if (this.lastTranslationUpdateTimeInMilliseconds > -1L) {
         for (IChatComponent component : (Iterable<IChatComponent>)(this.children)) {
            component.getChatStyle().setParentStyle(chatStyle);
         }
      }

      return this;
   }

   public void initializeFromFormat(String str) {
      Matcher matcher = stringVariablePattern.matcher(str);
      int n = 0;
      int n2 = 0;

      try {
         while (matcher.find(n2)) {
            int n3 = matcher.start();
            int n4 = matcher.end();
            if (n3 > n2) {
               ChatComponentText chatComponentText = new ChatComponentText(String.format(str.substring(n2, n3)));
               chatComponentText.getChatStyle().setParentStyle(this.getChatStyle());
               this.children.add(chatComponentText);
            }

            String str2 = matcher.group(2);
            String str3 = str.substring(n3, n4);
            if ("%".equals(str2) && "%%".equals(str3)) {
               ChatComponentText chatComponentText2 = new ChatComponentText("%");
               chatComponentText2.getChatStyle().setParentStyle(this.getChatStyle());
               this.children.add(chatComponentText2);
            } else {
               if (!"s".equals(str2)) {
                  throw new ChatComponentTranslationFormatException(this, "Unsupported format: '" + str3 + "'");
               }

               String str4 = matcher.group(1);
               int n5 = str4 != null ? Integer.parseInt(str4) - 1 : n++;
               if (n5 < this.formatArgs.length) {
                  this.children.add(this.getFormatArgumentAsComponent(n5));
               }
            }

            n2 = n4;
         }

         if (n2 < str.length()) {
            ChatComponentText chatComponentText3 = new ChatComponentText(String.format(str.substring(n2)));
            chatComponentText3.getChatStyle().setParentStyle(this.getChatStyle());
            this.children.add(chatComponentText3);
         }
      } catch (IllegalFormatException e) {
         throw new ChatComponentTranslationFormatException(this, e);
      }
   }

   @Override
   public IChatComponent copy() {
      return this.createCopy();
   }

   public synchronized void ensureInitialized() {
      synchronized (this.syncLock) {
         long l = StatCollector.getLastTranslationUpdateTimeInMilliseconds();
         if (l == this.lastTranslationUpdateTimeInMilliseconds) {
            return;
         }

         this.lastTranslationUpdateTimeInMilliseconds = l;
         this.children.clear();
      }

      try {
         this.initializeFromFormat(StatCollector.translateToLocal(this.key));
      } catch (ChatComponentTranslationFormatException e) {
         this.children.clear();

         try {
            this.initializeFromFormat(StatCollector.translateToFallback(this.key));
         } catch (ChatComponentTranslationFormatException e2) {
            throw e;
         }
      }
   }

   public ChatComponentTranslation createCopy() {
      Object[] array = new Object[this.formatArgs.length];

      for (int i = 0; i < this.formatArgs.length; i++) {
         if (this.formatArgs[i] instanceof IChatComponent) {
            array[i] = ((IChatComponent)this.formatArgs[i]).copy();
         } else {
            array[i] = this.formatArgs[i];
         }
      }

      ChatComponentTranslation chatComponentTranslation = new ChatComponentTranslation(this.key, array);
      chatComponentTranslation.setChatStyle(this.getChatStyle().createShallowCopy());

      for (IChatComponent component : (Iterable<IChatComponent>)(this.getSiblings())) {
         chatComponentTranslation.appendSibling(component.copy());
      }

      return chatComponentTranslation;
   }

   public ChatComponentTranslation(String str, Object[] array) {
      this.key = str;
      this.formatArgs = array;

      for (Object obj : array) {
         if (obj instanceof IChatComponent) {
            ((IChatComponent)obj).getChatStyle().setParentStyle(this.getChatStyle());
         }
      }
   }

   @Override
   public int hashCode() {
      int n = super.hashCode();
      n = 31 * n + this.key.hashCode();
      return 31 * n + Arrays.hashCode(this.formatArgs);
   }
}
