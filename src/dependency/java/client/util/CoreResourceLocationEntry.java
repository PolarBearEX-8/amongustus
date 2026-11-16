package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.ResourceLocation;

public class CoreResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/steve.png");
   public FloatEntryRegistry floatEntryRegistry;
   public float factor13;

   public ResourceLocation resolveResourceLocationByEntityMob(EntityMob entityMob) {
      return resourceLocation3;
   }

   @Override
   public void updateState() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityMob((EntityMob)entity);
   }

   public CoreResourceLocationEntry(RenderManager renderManager, FloatEntryRegistry floatEntryRegistry2, float factor) {
      this(renderManager, floatEntryRegistry2, factor, 1.0F);
      this.add(new TertiaryListenerBridge(this));
   }

   public CoreResourceLocationEntry(RenderManager renderManager, FloatEntryRegistry floatEntryRegistry2, float factor, float factor2) {
      super(renderManager, floatEntryRegistry2, factor);
      this.floatEntryRegistry = floatEntryRegistry2;
      this.factor13 = factor2;
      this.add(new ListenerAdapter5(floatEntryRegistry2.boxesManager3));
   }
}
