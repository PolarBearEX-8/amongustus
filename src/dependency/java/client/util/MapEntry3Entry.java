package client.util;

import net.minecraft.client.Minecraft;

public class MapEntry3Entry {
   public final InternalMapHolder internalMapHolder = new InternalMapHolder();
   public final StringBuilder stringBuilder = new StringBuilder();

   public boolean isEmpty() {
      return this.internalMapHolder.map.isEmpty();
   }

   private String resolveText(String text) {
      return text;
   }

   private boolean matchesInternalMapHolder(InternalMapHolder internalMapHolder2, String text, int count) {
      if (count == text.length()) {
         if (!internalMapHolder2.enabled) {
            return false;
         } else {
            internalMapHolder2.enabled = false;
            internalMapHolder2.labelText = null;
            return internalMapHolder2.map.isEmpty();
         }
      } else {
         char character = text.charAt(count);
         InternalMapHolder internalMapHolder3 = (InternalMapHolder)internalMapHolder2.map.get(character);
         if (internalMapHolder3 == null) {
            return false;
         } else {
            int count2 = count + 1;
            if (!this.matchesInternalMapHolder(internalMapHolder3, text, count2)) {
               return false;
            } else {
               internalMapHolder2.map.remove(character);
               return internalMapHolder2.map.isEmpty() && !internalMapHolder2.enabled;
            }
         }
      }
   }

   public boolean matchesText(String text) {
      InternalMapHolder internalMapHolder2 = this.internalMapHolder;
      return this.matchesInternalMapHolder(internalMapHolder2, text, 0);
   }

   public String resolveTextByText(String text) {
      if (!Minecraft.getMinecraft().isSingleplayer()) {
         return null;
      } else {
         this.stringBuilder.delete(0, this.stringBuilder.length());
         int count = 0;
         boolean flag = false;

         while (count < text.length()) {
            InternalMapHolder internalMapHolder2 = this.internalMapHolder;
            int count2 = count;
            InternalMapHolder internalMapHolder3 = null;

            int count3;
            for (count3 = count; count2 < text.length() && internalMapHolder2.map.containsKey(text.charAt(count2)); count2++) {
               internalMapHolder2 = (InternalMapHolder)internalMapHolder2.map.get(text.charAt(count2));
               if (internalMapHolder2.enabled) {
                  internalMapHolder3 = internalMapHolder2;
                  count3 = count2;
               }
            }

            if (internalMapHolder3 != null) {
               String labelText = internalMapHolder3.labelText;
               this.stringBuilder.append(this.resolveText(labelText));
               count = count3 + 1;
               flag = true;
            } else {
               this.stringBuilder.append(text.charAt(count));
               count++;
            }
         }

         return flag ? this.stringBuilder.toString() : null;
      }
   }

   public void applyText(String text) {
      InternalMapHolder internalMapHolder2 = this.internalMapHolder;

      for (char character : text.toCharArray()) {
         internalMapHolder2.map.putIfAbsent(character, new InternalMapHolder());
         internalMapHolder2 = (InternalMapHolder)internalMapHolder2.map.get(character);
      }

      internalMapHolder2.enabled = true;
      internalMapHolder2.labelText = text;
   }
}
