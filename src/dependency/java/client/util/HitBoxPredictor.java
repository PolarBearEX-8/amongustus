package client.util;

import net.minecraft.entity.EntityMob;

public class HitBoxPredictor extends BaseClientManagerAdapter {
   public HitBoxPredictor(EntityMob entityMob, Class clazz, float factor, float factor2) {
      super(entityMob, clazz, factor, factor2);
      this.setCount(3);
   }
}
