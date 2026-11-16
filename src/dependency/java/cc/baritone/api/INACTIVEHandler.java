package cc.baritone.api;

import cc.baritone.utils.Listener5Entry;

public interface INACTIVEHandler {
   double value = -1.0;

   String getText();

   boolean isActive();

   void updateState();

   boolean isEnabled();

   default double getValue() {
      return -1.0;
   }

   default String getTextAsText() {
      return !this.isEnabled() ? "INACTIVE" : this.getText();
   }

   Listener5Entry resolveListener5Entry(boolean flag, boolean flag2);
}
