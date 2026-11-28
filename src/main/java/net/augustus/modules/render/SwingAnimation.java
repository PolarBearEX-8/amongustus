package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class SwingAnimation extends Module {

   public SwingAnimation() {
       super("SwingAnimation", "SchlagAnimation", new Color(235, 64, 52), Categorys.RENDER);
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
