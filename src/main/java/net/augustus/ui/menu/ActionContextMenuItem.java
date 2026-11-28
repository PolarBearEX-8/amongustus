package net.augustus.ui.menu;

public class ActionContextMenuItem implements ContextMenuItem {
   private final Runnable action;
   private final String label;

   @Override
   public void onClick(String str) {
      this.action.run();
   }

   public ActionContextMenuItem(String str, Runnable runnable) {
      this.label = str;
      this.action = runnable;
   }

   @Override
   public String getDisplayText() {
      return this.label;
   }
}
