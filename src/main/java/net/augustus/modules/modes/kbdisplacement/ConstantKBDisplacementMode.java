package net.augustus.modules.modes.kbdisplacement;

import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.KBDisplacement;
import net.augustus.settings.SettingGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ConstantKBDisplacementMode extends KBDisplacementMode {
   public final RandomizedRangeSettings yawOffset;

   public ConstantKBDisplacementMode(KBDisplacement kbDisplacement, SettingGroup doubleValue) {
       super(kbDisplacement, doubleValue, "Constant");
      this.yawOffset = (RandomizedRangeSettings)new RandomizedRangeSettings(this.parentModule, "YawOffset", 80.0, 100.0, -180.0, 180.0, 0, this.modeSetting).withVisibility(() -> this.isSelected());
   }

   @Override
   public Float computeDisplacementYaw(EntityLivingBase entity, Vec3 vec) {
      return MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw + (float)this.yawOffset.nextValue());
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new ConstantKBDisplacementMode((KBDisplacement)obj, (SettingGroup)obj2);
   }

}
