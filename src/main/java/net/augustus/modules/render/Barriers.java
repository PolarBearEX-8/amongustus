package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class Barriers extends Module {

   public Barriers() {
       super("Barriers", "Barrieren", new Color(93, 98, 227), Categorys.RENDER);
   }

   @Override
   public void onEnable() {
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void onDisable() {
      mc.renderGlobal.loadRenderers();
   }

   @Override
   public void updateEventSubscriptions() {
   }
}
