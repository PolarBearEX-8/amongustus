package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;

public class CenteredPotionInventory extends Module {

   public CenteredPotionInventory() {
       super("CenteredPotionInventory", "ZentralesGetränkeInventar", new Color(77, 90, 112), Categorys.RENDER);
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
