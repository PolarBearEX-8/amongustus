package net.augustus.ui.sidebar;

import net.augustus.ui.menu.ContextMenuItem;
import java.util.function.Supplier;

@Deprecated
public class DynamicActionSidebarItem implements ContextMenuItem {
   private final Supplier labelSupplier;
   private final Runnable action;

   public DynamicActionSidebarItem(Supplier supplier, Runnable runnable) {
      this.labelSupplier = supplier;
      this.action = runnable;
   }

   @Override
   public String getDisplayText() {
      return (String)this.labelSupplier.get();
   }

   @Override
   public void onClick(String str) {
      this.action.run();
   }
}
