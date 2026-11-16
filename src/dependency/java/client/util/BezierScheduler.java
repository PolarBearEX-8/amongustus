package client.util;

import net.minecraft.entity.EntityPillagerAdapter;
import net.minecraft.entity.EntityRabbit;

public class BezierScheduler extends EntityPillagerAdapter {
   public EntityRabbit entityRabbit;

   public BezierScheduler(EntityRabbit entityRabbit2) {
      super(entityRabbit2);
      this.entityRabbit = entityRabbit2;
   }

   @Override
   public void updateState() {
      if (this.entityRabbit.onGround && !this.entityRabbit.onReset()) {
         this.entityRabbit.applyDouble(0.0);
      }

      super.updateState();
   }
}
