package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;

public class PortalScreens extends Module {
   public BooleanValue inventories;

   public PortalScreens() {
       super("PortalScreens", "PortalBildschirme", Color.MAGENTA, Categorys.MISC);
      this.inventories = (BooleanValue)(new BooleanValue(this, "Inventories", false)).markVisualOnly();
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
}
