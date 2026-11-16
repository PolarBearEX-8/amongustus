package client.util;

import client.setting.ClientSettingsBlackType;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class BaseManagerStyleIChatComponent implements IChatComponent {
   public final StringBuilder stringBuilder = new StringBuilder();
   public List list = Lists.newArrayList();
   public StyleHasParentManager styleHasParentManager;

   @Override
   public StyleHasParentManager getStyleHasParentManager() {
      if (this.styleHasParentManager == null) {
         this.styleHasParentManager = new StyleHasParentManager();
         Iterator iterator = this.list.iterator();

         while (iterator.hasNext()) {
            ((IChatComponent)iterator.next()).getStyleHasParentManager().withStyleHasParentManager2(this.styleHasParentManager);
         }
      }

      return this.styleHasParentManager;
   }

   @Override
   public boolean isActive() {
      return !this.getLabelText().isEmpty() ? false : this.matchesIChatComponent(this);
   }

   private void applyIChatComponent(IChatComponent iChatComponent, StringBuilder builder) {
      builder.append(iChatComponent.getLabelText());
      List list2 = iChatComponent.getList();
      int size = list2.size();
      if (size > 0) {
         for (int index = 0; index < size; index++) {
            this.applyIChatComponent((IChatComponent)list2.get(index), builder);
         }
      }
   }

   @Override
   public IChatComponent resolveIChatComponent(String text) {
      return this.appendChatComponent(new ChatComponentText(text));
   }

   @Override
   public IChatComponent getIChat(StyleHasParentManager styleHasParentManager2) {
      this.styleHasParentManager = styleHasParentManager2;
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((IChatComponent)iterator.next()).getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
      }

      return this;
   }

   public static Iterator resolveIterator(Iterable collection) {
      return Iterators.transform(Iterators.concat(Iterators.transform(collection.iterator(), new ManagedFunctionAdapter())), new SecondaryFunctionAdapter());
   }

   @Override
   public final String getString() {
      this.stringBuilder.delete(0, this.stringBuilder.length());
      this.applyIChatComponent(this, this.stringBuilder);
      return this.stringBuilder.toString();
   }

   @Override
   public IChatComponent appendChatComponent(IChatComponent iChatComponent) {
      iChatComponent.getStyleHasParentManager().withStyleHasParentManager2(this.getStyleHasParentManager());
      this.list.add(iChatComponent);
      return this;
   }

   @Override
   public final String getString2() {
      this.stringBuilder.delete(0, this.stringBuilder.length());
      this.stringBuilder.append(this.getStyleHasParentManager().getText());
      this.stringBuilder.append(this.getLabelText());
      this.stringBuilder.append(ClientSettingsBlackType.RESET);
      this.applyList(this.list, this.stringBuilder);
      return this.stringBuilder.toString();
   }

   private boolean matchesIChatComponent(IChatComponent iChatComponent) {
      List list2 = iChatComponent.getList();
      if (list2.isEmpty()) {
         return iChatComponent.getLabelText().isEmpty();
      } else {
         for (int index = 0; index < list2.size(); index++) {
            if (!this.matchesIChatComponent((IChatComponent)list2.get(index))) {
               return false;
            }
         }

         return true;
      }
   }

   private void applyList(List list2, StringBuilder builder) {
      for (int index = 0; index < list2.size(); index++) {
         IChatComponent iChatComponent = (IChatComponent)list2.get(index);
         builder.append(iChatComponent.getStyleHasParentManager().getText());
         builder.append(iChatComponent.getLabelText());
         builder.append(ClientSettingsBlackType.RESET);
         this.applyList(iChatComponent.getList(), builder);
      }
   }

   @Override
   public List getList() {
      return this.list;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof BaseManagerStyleIChatComponent)) {
         return false;
      } else {
         BaseManagerStyleIChatComponent baseManagerStyleIChatComponent = (BaseManagerStyleIChatComponent)other;
         return this.list.equals(baseManagerStyleIChatComponent.list) && this.getStyleHasParentManager().equals(baseManagerStyleIChatComponent.getStyleHasParentManager());
      }
   }

   @Override
   public String toString() {
      return "BaseComponent{style=" + this.styleHasParentManager + ", siblings=" + this.list + '}';
   }

   @Override
   public int hashCode() {
      return 31 * this.styleHasParentManager.hashCode() + this.list.hashCode();
   }

   @Override
   public Iterator iterator() {
      return Iterators.concat(Iterators.forArray(new BaseManagerStyleIChatComponent[]{this}), resolveIterator(this.list));
   }
}
