package client.util;

import net.minecraft.entity.EntityRabbit;

public class HitBoxMonitor extends CommonClientManagerAdapter {
   public EntityRabbit entityRabbit;

   public HitBoxMonitor(EntityRabbit entityRabbit2, double offset) {
      super(entityRabbit2, offset);
      this.entityRabbit = entityRabbit2;
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.entityRabbit.applyDouble(this.value);
   }
}
