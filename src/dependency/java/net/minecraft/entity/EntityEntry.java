package net.minecraft.entity;

import client.event.PostRenderObserver;
import client.util.BiomeIDIsOutOfBoundsManager;
import net.minecraft.util.BlockPos;

public class EntityEntry implements PostRenderObserver {
   public Entity entity;

   @Override
   public int getCount2() {
      return !(this.entity instanceof EntityMob) ? 0 : (int)((EntityMob)this.entity).getFloat();
   }

   @Override
   public int getCount() {
      return !(this.entity instanceof EntityMob) ? 0 : (int)((EntityMob)this.entity).getFloat2();
   }

   public void setEntity(Entity entity2) {
      this.entity = entity2;
   }

   public Entity getEntityAsEntity() {
      return this.entity;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.entity.getDataWatcher().blockPos;
   }

   @Override
   public String getText() {
      return this.entity.processAction() ? this.entity.getText2() : null;
   }

   @Override
   public int getCountAsIndex() {
      return (int)(this.entity.getEntityUniqueID().getLeastSignificantBits() & 2147483647L);
   }

   @Override
   public BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManager() {
      return this.entity.getDataWatcher().biomeIDIsOutOfBoundsManager;
   }
}
