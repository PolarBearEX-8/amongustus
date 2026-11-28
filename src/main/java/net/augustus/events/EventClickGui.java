package net.augustus.events;

import net.augustus.events.listeners.ClickGuiListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventClickGui extends AbstractEvent<ClickGuiListener> {
   public static final int ID = 6;
   private boolean renderPass;

   public static void reset(EventClickGui event, boolean flag) {
      event.setRenderPass(flag);
   }


   @Override
   public void call(ClickGuiListener listener) {
      if (this.renderPass) {
         listener.onClickGuiRender();
      } else {
         listener.onClickGuiUpdate();
      }
   }

   private void setRenderPass(boolean flag) {
      this.renderPass = flag;
   }
}
