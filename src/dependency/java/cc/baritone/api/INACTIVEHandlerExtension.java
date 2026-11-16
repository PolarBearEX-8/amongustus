package cc.baritone.api;

import cc.baritone.utils.SecondaryListener;

public interface INACTIVEHandlerExtension extends INACTIVEHandler {
   default void applySecondaryListenerBySecondaryListene(SecondaryListener secondaryListener) {
      this.applySecondaryListener(secondaryListener);
      this.updateState2();
   }

   SecondaryListener getSecondaryListener2();

   void applySecondaryListener(SecondaryListener secondaryListener);

   SecondaryListener getSecondaryListener();

   void updateState2();
}
