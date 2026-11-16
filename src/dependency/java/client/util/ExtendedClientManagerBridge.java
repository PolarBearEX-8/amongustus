package client.util;

import net.minecraft.entity.EntitySquid;
import net.minecraft.util.MathHelper;

public class ExtendedClientManagerBridge extends RotationPool {
   public EntitySquid entitySquid;

   @Override
   public boolean isActive() {
      return true;
   }

   public ExtendedClientManagerBridge(EntitySquid entitySquid2) {
      this.entitySquid = entitySquid2;
   }

   @Override
   public void updateState2() {
      if (this.entitySquid.getIndexOffset14() > 100) {
         this.entitySquid.applyFloatByFloat(0.0F, 0.0F, 0.0F);
      } else if (this.entitySquid.getRandom().nextInt(50) == 0 || !EntitySquid.matchesEntitySquid(this.entitySquid) || !this.entitySquid.isActive29()) {
         float factor = this.entitySquid.getRandom().nextFloat() * (float) Math.PI * 2.0F;
         float factor2 = MathHelper.cos(factor) * 0.2F;
         float factor3 = -0.1F + this.entitySquid.getRandom().nextFloat() * 0.2F;
         float factor4 = MathHelper.abs(factor) * 0.2F;
         this.entitySquid.applyFloatByFloat(factor2, factor3, factor4);
      }
   }
}
