package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.NoArmorOverlayTargetArmorOption;
import net.augustus.settings.options.NoArmorOverlaySelfArmorOption;
import net.minecraft.entity.Entity;

public class NoArmorOverlay extends Module {
   private final MultiOptionSetting armorTypes;
   private final MultiOptionSetting armorTypesMyself;

   public NoArmorOverlay() {
       super("NoArmorOverlay", "KeineArmor", new Color(157, 224, 190), Categorys.RENDER);
      this.armorTypes = new MultiOptionSetting(this, "ArmorTypes", NoArmorOverlayTargetArmorOption.values());
      this.armorTypesMyself = new MultiOptionSetting(this, "ArmorTypesMyself", NoArmorOverlaySelfArmorOption.values());
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

   public boolean shouldHideHelmet(Entity entity) {
      return entity.equals(mc.thePlayer) ? NoArmorOverlaySelfArmorOption.HELMET.isSelected() : NoArmorOverlayTargetArmorOption.HELMET.isSelected();
   }

   public boolean shouldHideChestplate(Entity entity) {
      return entity.equals(mc.thePlayer) ? NoArmorOverlaySelfArmorOption.CHEST_PLATE.isSelected() : NoArmorOverlayTargetArmorOption.CHEST_PLATE.isSelected();
   }

   public boolean shouldHideLeggings(Entity entity) {
      return entity.equals(mc.thePlayer) ? NoArmorOverlaySelfArmorOption.TROUSERS.isSelected() : NoArmorOverlayTargetArmorOption.TROUSERS.isSelected();
   }

   public boolean shouldHideBoots(Entity entity) {
      return entity.equals(mc.thePlayer) ? NoArmorOverlaySelfArmorOption.BOOTS.isSelected() : NoArmorOverlayTargetArmorOption.BOOTS.isSelected();
   }
}
