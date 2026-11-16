package client.util;

import augustus.modes.movement.MovementInvalidIndexDRequestedForSMode;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BaseManagerStyleIChatComponentAdapter extends BaseManagerStyleIChatComponent {
   public final String labelText;
   public final Object[] objectArray;
   public final Object dataRef = new Object();
   public long timestamp = -1L;
   public List list2 = Lists.newArrayList();
   public static final Pattern pattern = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

   @Override
   public String getLabelText() {
      this.updateState();
      StringBuilder builder = new StringBuilder();

      for (IChatComponent iChatComponent : (Iterable<IChatComponent>)(Iterable)(this.list2)) {
         builder.append(iChatComponent.getLabelText());
      }

      return builder.toString();
   }

   @Override
   public IChatComponent getIChat(StyleHasParentManager styleHasParentManager) {
      super.getIChat(styleHasParentManager);

      for (Object element : this.objectArray) {
         if (element instanceof IChatComponent) {
            ((IChatComponent)element).getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
         }
      }

      if (this.timestamp > -1L) {
         Iterator iterator = this.list2.iterator();

         while (iterator.hasNext()) {
            ((IChatComponent)iterator.next()).getStyleHasParentManager().withStyleHasParentManager2(styleHasParentManager);
         }
      }

      return this;
   }

   public String getLabelTextAsText() {
      return this.labelText;
   }

   public void applyText(String text) {
      Matcher matcher = pattern.matcher(text);
      int count = 0;
      int count2 = 0;

      try {
         while (matcher.find(count2)) {
            int count3 = matcher.start();
            int count4 = matcher.end();
            if (count3 > count2) {
               ChatComponentText chatComponentText = new ChatComponentText(String.format(text.substring(count2, count3)));
               chatComponentText.getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
               this.list2.add(chatComponentText);
            }

            String text2 = matcher.group(2);
            String text3 = text.substring(count3, count4);
            if ("%".equals(text2) && "%%".equals(text3)) {
               ChatComponentText chatComponentText2 = new ChatComponentText("%");
               chatComponentText2.getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
               this.list2.add(chatComponentText2);
            } else {
               if (!"s".equals(text2)) {
                  throw new MovementInvalidIndexDRequestedForSMode(this, "Unsupported format: '" + text3 + "'");
               }

               String text4 = matcher.group(1);
               int count5 = text4 != null ? Integer.parseInt(text4) - 1 : count++;
               if (count5 < this.objectArray.length) {
                  this.list2.add(this.resolveIChatComponent(count5));
               }
            }

            count2 = count4;
         }

         if (count2 < text.length()) {
            ChatComponentText chatComponentText3 = new ChatComponentText(String.format(text.substring(count2)));
            chatComponentText3.getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
            this.list2.add(chatComponentText3);
         }
      } catch (IllegalFormatException illegalFormatException) {
         throw new MovementInvalidIndexDRequestedForSMode(this, illegalFormatException);
      }
   }

   public Object[] getObjectArray() {
      return this.objectArray;
   }

   private IChatComponent resolveIChatComponent(int count) {
      if (count >= this.objectArray.length) {
         throw new MovementInvalidIndexDRequestedForSMode(this, count);
      } else {
         Object entry = this.objectArray[count];
         Object entry2;
         if (entry instanceof IChatComponent) {
            entry2 = (IChatComponent)entry;
         } else {
            entry2 = new ChatComponentText(entry == null ? "null" : entry.toString());
            ((IChatComponent)entry2).getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
         }

         return (IChatComponent)entry2;
      }
   }

   public BaseManagerStyleIChatComponentAdapter getBaseManagerStyleIChatCompone() {
      Object[] entryArray = new Object[this.objectArray.length];

      for (int index = 0; index < this.objectArray.length; index++) {
         if (this.objectArray[index] instanceof IChatComponent) {
            entryArray[index] = ((IChatComponent)this.objectArray[index]).getIChatComponent();
         } else {
            entryArray[index] = this.objectArray[index];
         }
      }

      BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(this.labelText, entryArray);
      baseManagerStyleIChatComponentAdapter.getIChat(this.getStyleHasParentManager().getStyleHasParentManager2());

      for (IChatComponent iChatComponent : (Iterable<IChatComponent>)(Iterable)(this.getList())) {
         baseManagerStyleIChatComponentAdapter.appendChatComponent(iChatComponent.getIChatComponent());
      }

      return baseManagerStyleIChatComponentAdapter;
   }

   public synchronized void updateState() {
      synchronized (this.dataRef) {
         long timestamp2 = I18n.getTimestamp();
         if (timestamp2 == this.timestamp) {
            return;
         }

         this.timestamp = timestamp2;
         this.list2.clear();
      }

      try {
         this.applyText(I18n.getString2(this.labelText));
      } catch (MovementInvalidIndexDRequestedForSMode exception) {
         this.list2.clear();

         try {
            this.applyText(I18n.resolveText(this.labelText));
         } catch (MovementInvalidIndexDRequestedForSMode exception2) {
            throw exception;
         }
      }
   }

   @Override
   public IChatComponent getIChatComponent() {
      return this.getBaseManagerStyleIChatCompone();
   }

   public BaseManagerStyleIChatComponentAdapter(String text, Object[] entryArray) {
      this.labelText = text;
      this.objectArray = entryArray;

      for (Object element : entryArray) {
         if (element instanceof IChatComponent) {
            ((IChatComponent)element).getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
         }
      }
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof BaseManagerStyleIChatComponentAdapter)) {
         return false;
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = (BaseManagerStyleIChatComponentAdapter)other;
         return Arrays.equals(this.objectArray, baseManagerStyleIChatComponentAdapter.objectArray) && this.labelText.equals(baseManagerStyleIChatComponentAdapter.labelText) && super.equals(other);
      }
   }

   @Override
   public String toString() {
      return "TranslatableComponent{key='"
         + this.labelText
         + '\''
         + ", args="
         + Arrays.toString(this.objectArray)
         + ", siblings="
         + this.list
         + ", style="
         + this.getStyleHasParentManager()
         + '}';
   }

   @Override
   public int hashCode() {
      int hash = super.hashCode();
      int count = 31 * hash + this.labelText.hashCode();
      return 31 * count + Arrays.hashCode(this.objectArray);
   }

   @Override
   public Iterator iterator() {
      this.updateState();
      return Iterators.concat(resolveIterator(this.list2), resolveIterator(this.list));
   }
}
