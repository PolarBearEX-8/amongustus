package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;

public class CustomFireHeight extends Module {
   public final BooleanValue removeFire;
   public final DoubleValue fireHeight;

   public CustomFireHeight() {
       super("CustomFireHeight", "EinstellbareFeureHöhe", new Color(235, 229, 52), Categorys.RENDER);
      this.removeFire = new BooleanValue(this, "RemoveFire", false);
      this.fireHeight = new DoubleValue(this, "FireHeight", -0.3, -0.7, 0.0, 2).visibleWhen(() -> this.isFireHeightVisible());
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
   }

   private boolean isFireHeightVisible() {
      return !this.removeFire.getValue();
   }

}
