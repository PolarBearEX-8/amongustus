package client.render;

import client.util.PassiveFloatEntry;
import client.util.SecondaryClientManagerAdapter;
import client.util.TertiaryListenerBridge;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGiant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class PointSpriteRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/zombie/zombie.png");
   public float factor13;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityGiant((EntityGiant)livingEntity, factor);
   }

   public void applyEntityGiant(EntityGiant entityGiant, float factor) {
      GlStateManager.translate3f3(this.factor13, this.factor13, this.factor13);
   }

   @Override
   public void updateState() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityGiant)entity);
   }

   public ResourceLocation resolveResourceLocation(EntityGiant entityGiant) {
      return resourceLocation3;
   }

   public PointSpriteRenderer(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor, float factor2) {
      super(renderManager, passiveFloatEntry, factor * factor2);
      this.factor13 = factor2;
      this.add(new TertiaryListenerBridge(this));
      this.add(new SecondaryClientManagerAdapter(this, this));
   }
}
