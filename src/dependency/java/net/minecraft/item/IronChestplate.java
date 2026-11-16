package net.minecraft.item;

import client.util.BaseClientManagerAdapter;
import net.minecraft.entity.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class IronChestplate extends BaseClientManagerAdapter {
   public final EntityVillager entityVillager;

   @Override
   public boolean isActive() {
      if (this.entityVillager.isActive32()) {
         this.entity = this.entityVillager.getEntityPlayer2();
         return true;
      } else {
         return false;
      }
   }

   public IronChestplate(EntityVillager entityVillager2) {
      super(entityVillager2, EntityPlayer.class, 8.0F);
      this.entityVillager = entityVillager2;
   }
}
