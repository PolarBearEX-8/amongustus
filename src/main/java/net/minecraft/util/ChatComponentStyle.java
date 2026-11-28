package net.minecraft.util;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public abstract class ChatComponentStyle implements IChatComponent {
   private final StringBuilder textBuilder = new StringBuilder();
   public List siblings = Lists.newArrayList();
   private ChatStyle style;

   private void appendUnformattedText(IChatComponent component, StringBuilder sb) {
      sb.append(component.getUnformattedTextForChat());
      List siblings2 = component.getSiblings();
      int size = siblings2.size();
      if (size > 0) {
         for (int i = 0; i < size; i++) {
            this.appendUnformattedText((IChatComponent)siblings2.get(i), sb);
         }
      }
   }

   @Override
   public IChatComponent setChatStyle(ChatStyle chatStyle) {
      this.style = chatStyle;

      for (IChatComponent component : (Iterable<IChatComponent>)(this.siblings)) {
         component.getChatStyle().setParentStyle(this.getChatStyle());
      }

      return this;
   }

   @Override
   public final String getUnformattedText() {
      this.textBuilder.delete(0, this.textBuilder.length());
      this.appendUnformattedText(this, this.textBuilder);
      return this.textBuilder.toString();
   }

   public static Iterator createDeepCopyIterator(Iterable items) {
      Iterator it = Iterators.concat(Iterators.transform(items.iterator(), new ChatComponentStyle.TransformFunction()));
      return Iterators.transform(it, new ChatComponentStyle.TransformFunction2());
   }

   @Override
   public IChatComponent appendSibling(IChatComponent component) {
      component.getChatStyle().setParentStyle(this.getChatStyle());
      this.siblings.add(component);
      return this;
   }

   @Override
   public final String getFormattedText() {
      this.textBuilder.delete(0, this.textBuilder.length());
      this.textBuilder.append(this.getChatStyle().getFormattingCode());
      this.textBuilder.append(this.getUnformattedTextForChat());
      this.textBuilder.append(EnumChatFormatting.RESET);
      this.appendFormattedSiblings(this.siblings, this.textBuilder);
      return this.textBuilder.toString();
   }

   @Override
   public IChatComponent appendText(String str) {
      return this.appendSibling(new ChatComponentText(str));
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatComponentStyle)) {
         return false;
      } else {
         ChatComponentStyle chatComponentStyle = (ChatComponentStyle)obj;
         return this.siblings.equals(chatComponentStyle.siblings) && this.getChatStyle().equals(chatComponentStyle.getChatStyle());
      }
   }

   private boolean isComponentEmpty(IChatComponent component) {
      List siblings2 = component.getSiblings();
      if (siblings2.isEmpty()) {
         return component.getUnformattedTextForChat().isEmpty();
      } else {
         for (int i = 0; i < siblings2.size(); i++) {
            if (!this.isComponentEmpty((IChatComponent)siblings2.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public int hashCode() {
      return 31 * this.style.hashCode() + this.siblings.hashCode();
   }

   private void appendFormattedSiblings(List list, StringBuilder sb) {
      for (int i = 0; i < list.size(); i++) {
         IChatComponent component = (IChatComponent)list.get(i);
         sb.append(component.getChatStyle().getFormattingCode());
         sb.append(component.getUnformattedTextForChat());
         sb.append(EnumChatFormatting.RESET);
         this.appendFormattedSiblings(component.getSiblings(), sb);
      }
   }

   @Override
   public Iterator iterator() {
      return Iterators.concat(Iterators.forArray(new ChatComponentStyle[]{this}), createDeepCopyIterator(this.siblings));
   }

   @Override
   public boolean isEmpty() {
      return !this.getUnformattedTextForChat().isEmpty() ? false : this.isComponentEmpty(this);
   }

   @Override
   public ChatStyle getChatStyle() {
      if (this.style == null) {
         this.style = new ChatStyle();

         for (IChatComponent component : (Iterable<IChatComponent>)(this.siblings)) {
            component.getChatStyle().setParentStyle(this.style);
         }
      }

      return this.style;
   }

   @Override
   public List getSiblings() {
      return this.siblings;
   }

   @Override
   public String toString() {
      return "BaseComponent{style=" + this.style + ", siblings=" + this.siblings + '}';
   }


   public static final class TransformFunction implements Function {
      public Object apply(Object obj) {
         return this.apply((IChatComponent)obj);
      }

      public Iterator apply(IChatComponent component) {
         return component.iterator();
      }
   }

   public static final class TransformFunction2 implements Function {
      public Object apply(Object obj) {
         return this.apply((IChatComponent)obj);
      }

      public IChatComponent apply(IChatComponent component) {
         IChatComponent component2 = component.copy();
         component2.setChatStyle(component2.getChatStyle().createDeepCopy());
         return component2;
      }
   }
}
