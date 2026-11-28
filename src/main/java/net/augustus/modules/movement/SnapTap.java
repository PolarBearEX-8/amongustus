package net.augustus.modules.movement;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class SnapTap extends Module {

   public SnapTap() {
       super("SnapTap", "SchnappBerührung", new Color(255, 244, 140), Categorys.MOVEMENT);
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
