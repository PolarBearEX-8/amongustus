package client.util;

import client.event.PreRenderProcessor;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class VectorFormatter extends Entity {
   public final PreRenderProcessor preRenderProcessor;
   public final String labelText;

   @Override
   public boolean isActive5() {
      return true;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public boolean matchesDamageSource(DamageSource damageSource, float factor) {
      return this.matchesDamageSource(damageSource) ? false : this.preRenderProcessor.matchesVectorFormatter(this, damageSource, factor);
   }

   @Override
   public void updateState7() {
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
   }

   @Override
   public boolean matchesEntity(Entity entity) {
      return this == entity || this.preRenderProcessor == entity;
   }

   public VectorFormatter(PreRenderProcessor preRenderProcessor2, String text, float factor, float factor2) {
      super(preRenderProcessor2.getWorld2());
      this.applyFloat(factor, factor2);
      this.preRenderProcessor = preRenderProcessor2;
      this.labelText = text;
   }
}
