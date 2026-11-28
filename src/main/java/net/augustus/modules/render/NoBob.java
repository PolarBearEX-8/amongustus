package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.StringValue;

public class NoBob extends Module implements UpdateListener {
   public final StringValue mode;

   public NoBob() {
       super("NoBob", "KeinBob", new Color(121, 232, 163), Categorys.RENDER);
      this.mode = new StringValue(this, "Mode", "Normal", new String[]{"Normal", "NoScreenBobbing"});
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   public void onPostUpdate() {
      if (this.mode.getSelectedValue().equals("Normal")) {
         mc.thePlayer.distanceWalkedModified = 0.0F;
      }
   }

}
