package client.util;

import client.event.PreMotionDispatcher;
import client.render.BloomFilter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPig;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryListenerRegistry implements PreMotionDispatcher {
   public static final ResourceLocation resourceLocation = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   public final BloomFilter bloomFilter;
   public final AimProfiler aimProfiler = new AimProfiler(0.5F);

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityPig((EntityPig)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityPig(EntityPig entityPig, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityPig.isActive32()) {
         this.bloomFilter.applyResourceLocation2(resourceLocation);
         this.aimProfiler.applyPassiveFloatEntry(this.bloomFilter.getPassiveFloatEntry());
         this.aimProfiler.applyEntity(entityPig, posX, posX2, posX4, posX5, posY, posZ, true);
      }
   }

   public AuxiliaryListenerRegistry(BloomFilter bloomFilter2) {
      this.bloomFilter = bloomFilter2;
   }
}
