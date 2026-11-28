package net.augustus.modules.modes.kbdisplacement;

import net.augustus.modules.combat.KnockbackSimulationResult;
import net.augustus.modules.combat.helper.KnockbackTrajectoryUtil;
import net.augustus.settings.ModeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.KBDisplacement;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class DynamicKBDisplacementMode extends KBDisplacementMode {
   public final DoubleValue minFallDownDistance;
   public final DoubleValue candidates;
   public final ModeSetting dynamicFallbackMode;

   public DynamicKBDisplacementMode(KBDisplacement kbDisplacement, SettingGroup doubleValue) {
       super(kbDisplacement, doubleValue, "Dynamic");
      this.minFallDownDistance = new DoubleValue(this.parentModule, "MinFallDownDistance", 20.0, 0.0, 100.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.candidates = new DoubleValue(this.parentModule, "Candidates", 16.0, 4.0, 36.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.dynamicFallbackMode = new ModeSetting(
         this.parentModule, "DynamicFallbackMode", "Constant", this.modeSetting, new BiFunction[]{ConstantKBDisplacementMode.factory(), NoneKBDisplacementMode.factory()}
      ).named("FallbackMode").visibleWhen(() -> this.isSelected());
   }

   @Override
   public Float computeDisplacementYaw(EntityLivingBase entity, Vec3 vec) {
      int value = (int)this.minFallDownDistance.getValue();
      int value2 = (int)this.candidates.getValue();
      float f = 0.0F;
      double negativeinfinity = Double.NEGATIVE_INFINITY;
      float f2 = 360.0F / (float)value2;

      for (int i = 0; i < value2; i++) {
         float f3 = (float)i * f2;
         KnockbackSimulationResult result = KnockbackTrajectoryUtil.simulateKnockback(entity, vec, f3, value);
         if (result.dropScore > negativeinfinity && result.fallsOffEdge) {
            negativeinfinity = result.dropScore;
            System.out.println(entity.posY - result.endPosition.yCoord);
            f = f3;
         }
      }

      return negativeinfinity <= 0.0 ? ((KBDisplacementMode)this.dynamicFallbackMode.getAbstractSubMode()).computeDisplacementYaw(entity, vec) : MathHelper.wrapAngleTo180_float(f);
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new DynamicKBDisplacementMode((KBDisplacement)obj, (SettingGroup)obj2);
   }

}
