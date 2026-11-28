package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;

public class ModelAdapterMinecartMobSpawner extends ModelAdapterMinecart {
   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderMinecartMobSpawner renderMinecartMobSpawner = new RenderMinecartMobSpawner(renderManager);
      renderMinecartMobSpawner.modelMinecart = modelBase;
      renderMinecartMobSpawner.shadowSize = f;
      return renderMinecartMobSpawner;
   }

   public ModelAdapterMinecartMobSpawner() {
      super(EntityMinecartMobSpawner.class, "spawner_minecart", 0.5F);
   }
}
