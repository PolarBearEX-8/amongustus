package net.augustus.modules.modes.kbdisplacement;

import java.util.function.BiFunction;
import net.augustus.modules.combat.KBDisplacement;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class NoneKBDisplacementMode extends KBDisplacementMode {

   public NoneKBDisplacementMode(KBDisplacement kbDisplacement, SettingGroup doubleValue) {
      super(kbDisplacement, doubleValue, "None");
   }

   @Override
   public Float computeDisplacementYaw(EntityLivingBase entity, Vec3 vec) {
      return null;
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new NoneKBDisplacementMode((KBDisplacement)obj, (SettingGroup)obj2);
   }

}
