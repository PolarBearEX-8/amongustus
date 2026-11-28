package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventTimerUpdate;
import net.augustus.events.listeners.TimerUpdateListener;
import net.augustus.Augustus;

public class CustomClientColor extends Module implements TimerUpdateListener {
   public final ColorSetting color2;

   public CustomClientColor() {
       super("CustomClientColor", "EinstellbareKundenFarbe", new Color(145, 100, 250), Categorys.RENDER);
      this.color2 = new ColorSetting(this, "ColorSetting", new Color(41, 146, 222));
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      Augustus.getInstance().setClientColor(new Color(41, 146, 222).getRGB());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(42);
   }

   @Override
   public void onTimerUpdate(EventTimerUpdate event) {
      Augustus.getInstance().setClientColor(this.color2.getColorInt((byte)0));
   }
}
