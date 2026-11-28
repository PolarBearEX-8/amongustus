package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class BrightPlayers extends Module {

   public BrightPlayers() {
       super("BrightPlayers", "HelleSpieler", new Color(175, 187, 250), Categorys.RENDER);
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
