package net.augustus.modules.modes.kbdisplacement;

import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.combat.KBDisplacement;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public abstract class KBDisplacementMode extends AbstractSubMode {

   public KBDisplacementMode(KBDisplacement kbDisplacement, SettingGroup doubleValue, String str) {
      super(kbDisplacement, doubleValue, str);
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public abstract Float computeDisplacementYaw(EntityLivingBase entity, Vec3 vec);



}
