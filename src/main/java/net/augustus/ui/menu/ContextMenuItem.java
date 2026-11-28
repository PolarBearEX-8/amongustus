package net.augustus.ui.menu;

import java.util.Set;

public interface ContextMenuItem {
   default String getDisplayText() {
      return null;
   }

   default Set getDisplayOptions() {
      return null;
   }

   default boolean isCentered() {
      return false;
   }

   void onClick(String str);
}
