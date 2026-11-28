package net.augustus.ui.sidebar;

import net.augustus.ui.menu.ContextMenuItem;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BooleanSidebarItem implements ContextMenuItem {
   private final Supplier valueGetter;
   private final Consumer valueSetter;
   private final String label;

   @Override
   public String getDisplayText() {
      StringBuilder sb = new StringBuilder();
      String str;
      if ((Boolean)this.valueGetter.get()) {
         str = "§a";
      } else {
         short s = 906;
         str = "§4";
      }

      return sb.append(str).append(this.label).toString();
   }

   @Override
   public void onClick(String str) {
      this.valueSetter.accept(!(Boolean)this.valueGetter.get());
   }

   public BooleanSidebarItem(String str, Supplier supplier, Consumer consumer) {
      this.label = str;
      this.valueGetter = supplier;
      this.valueSetter = consumer;
   }

}
