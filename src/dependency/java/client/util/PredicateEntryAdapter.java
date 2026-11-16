package client.util;

import net.minecraft.entity.EntityRabbit;

public class PredicateEntryAdapter extends AuxiliaryPredicateEntry {
   public EntityRabbit entityRabbit;

   public PredicateEntryAdapter(EntityRabbit entityRabbit2, Class clazz, float factor, double offset, double offset2) {
      super(entityRabbit2, clazz, factor, offset, offset2);
      this.entityRabbit = entityRabbit2;
   }

   @Override
   public void updateState2() {
      super.updateState2();
   }
}
