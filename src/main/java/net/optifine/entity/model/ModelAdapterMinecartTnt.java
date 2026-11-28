package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;

public class ModelAdapterMinecartTnt extends ModelAdapterMinecart {
   public ModelAdapterMinecartTnt() {
      super(EntityMinecartTNT.class, "tnt_minecart", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderTntMinecart renderTntMinecart = new RenderTntMinecart(renderManager);
      renderTntMinecart.modelMinecart = modelBase;
      renderTntMinecart.shadowSize = f;
      return renderTntMinecart;
   }
}
