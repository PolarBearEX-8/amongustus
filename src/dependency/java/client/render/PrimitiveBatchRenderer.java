package client.render;

import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySnowman;
import net.minecraft.item.DiamondSpear;
import net.minecraft.item.ProviderItemEntry;
import net.minecraft.util.ResourceLocation;

public class PrimitiveBatchRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/snowman.png");

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return this.getDiamondSpear();
   }

   public DiamondSpear getDiamondSpear() {
      return (DiamondSpear)super.getPassiveFloatEntry();
   }

   public ResourceLocation resolveResourceLocationByEntitySnowman(EntitySnowman entitySnowman) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntitySnowman((EntitySnowman)entity);
   }

   public PrimitiveBatchRenderer(RenderManager renderManager) {
      super(renderManager, new DiamondSpear(), 0.5F);
      this.add(new ProviderItemEntry(this));
   }
}
