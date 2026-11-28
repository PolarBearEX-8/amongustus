package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class NoBlockParticles extends Module {

   public NoBlockParticles() {
       super("NoBlockParticles", "KeineBlockBrechEffekte", new Color(219, 181, 75), Categorys.RENDER);
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
