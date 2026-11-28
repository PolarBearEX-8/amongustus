package net.augustus.ui.sidebar;


import net.augustus.ui.menu.ContextMenuItem;
public class HeaderSidebarItem implements ContextMenuItem {
   private final String headerText;

   public HeaderSidebarItem(String str) {
      this.headerText = str;
   }

   @Override
   public void onClick(String str) {
   }

   @Override
   public boolean isCentered() {
      return true;
   }

   @Override
   public String getDisplayText() {
      return this.headerText;
   }
}
