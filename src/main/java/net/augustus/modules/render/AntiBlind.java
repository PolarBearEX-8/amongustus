package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class AntiBlind extends Module {

   public AntiBlind() {
       super("AntiBlind", "GegenBlind", new Color(38, 111, 145), Categorys.RENDER);
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
