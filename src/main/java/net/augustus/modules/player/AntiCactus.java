package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.BooleanValue;

public class AntiCactus extends Module {
   public final BooleanValue onlySide;

   public AntiCactus() {
       super("AntiCactus", "Gegenkaktus", new Color(222, 129, 71), Categorys.PLAYER);
      this.onlySide = new BooleanValue(this, "OnlySide", true);
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
