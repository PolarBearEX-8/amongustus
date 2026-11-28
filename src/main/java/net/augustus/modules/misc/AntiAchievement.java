package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class AntiAchievement extends Module {

   public AntiAchievement() {
       super("AntiAchievement", "GegenErrungenschaft", new Color(50, 168, 82), Categorys.MISC);
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
