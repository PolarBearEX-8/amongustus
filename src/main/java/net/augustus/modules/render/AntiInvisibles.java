package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.settings.TargetSelectionSettings;
import net.minecraft.entity.Entity;

public class AntiInvisibles extends Module {
   private final TargetSelectionSettings entities;

   public AntiInvisibles() {
       super("AntiInvisibles", "GegenUnsichtbare", new Color(87, 247, 196), Categorys.RENDER);
      this.entities = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilter()).withTargetsDisplayName("Entities");
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public boolean shouldRevealEntity(Entity entity) {
      if (this.entities.getTargetFilter().matches(entity)) {
         if (true) {
            return true;
         }
      }

      return false;
   }


}
