package client.util;

import java.util.Random;
import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityPillagerAdapter;

public class HitBoxAccumulator extends RotationPool {
   public EntityGhast entityGhast;

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   public HitBoxAccumulator(EntityGhast entityGhast2) {
      this.entityGhast = entityGhast2;
      this.setCount(1);
   }

   @Override
   public boolean isActive() {
      EntityPillagerAdapter entityPillagerAdapter = this.entityGhast.getEntityPillagerAdapter();
      if (!entityPillagerAdapter.isEnabled()) {
         return true;
      } else {
         double offset = entityPillagerAdapter.getValue4() - this.entityGhast.posX;
         double offset2 = entityPillagerAdapter.getValue2() - this.entityGhast.posY;
         double offset3 = entityPillagerAdapter.getValue3() - this.entityGhast.posZ;
         double offset4 = offset * offset + offset2 * offset2 + offset3 * offset3;
         return offset4 < 1.0 || offset4 > 3600.0;
      }
   }

   @Override
   public void updateState3() {
      Random random = this.entityGhast.getRandom();
      double offset = this.entityGhast.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      double offset2 = this.entityGhast.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      double offset3 = this.entityGhast.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
      this.entityGhast.getEntityPillagerAdapter().applyDouble(offset, offset2, offset3, 1.0);
   }
}
