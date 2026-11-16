package net.minecraft.item;

import client.setting.ServiceOptionType;
import net.minecraft.entity.EntityRabbit;

public class ItemEntryAdapter extends PrimaryItemEntry {
   public EntityRabbit entityRabbit;
   public boolean enabled2;
   public final EntityRabbit entityRabbit2;

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled2(boolean enabled) {
      this.enabled2 = enabled;
   }

   public ItemEntryAdapter(EntityRabbit entityRabbit3, EntityRabbit entityRabbit4) {
      super(entityRabbit4);
      this.entityRabbit2 = entityRabbit3;
      this.enabled2 = false;
      this.entityRabbit = entityRabbit4;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   @Override
   public void updateState2() {
      if (this.enabled) {
         this.entityRabbit.applyServiceOptionType(ServiceOptionType.STEP);
         this.enabled = false;
      }
   }
}
