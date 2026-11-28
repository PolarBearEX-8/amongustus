package net.augustus.modules.combat.helper;

import net.minecraft.entity.EntityLivingBase;

public interface ClickModuleContext {
   EntityLivingBase getTarget();

   int applyHitReduction(int n);

   void setHoldingAttack(boolean flag);

   boolean hasLandedCrit();

   boolean isCritFixEnabled();
}
