package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;

public class ModelAdapterMinecart extends ModelAdapter {

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelMinecart)) {
         return null;
      } else {
         ModelMinecart modelMinecart = (ModelMinecart)modelBase;
         return str.equals("bottom")
            ? modelMinecart.sideModels[0]
            : (
               str.equals("back")
                  ? modelMinecart.sideModels[1]
                  : (
                     str.equals("front")
                        ? modelMinecart.sideModels[2]
                        : (
                           str.equals("right")
                              ? modelMinecart.sideModels[3]
                              : (str.equals("left") ? modelMinecart.sideModels[4] : (str.equals("dirt") ? modelMinecart.sideModels[5] : null))
                        )
                  )
            );
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderMinecart renderMinecart = new RenderMinecart(renderManager);
      renderMinecart.modelMinecart = modelBase;
      renderMinecart.shadowSize = f;
      return renderMinecart;
   }

   public ModelAdapterMinecart(Class clazz, String str, float f) {
      super(clazz, str, f);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelMinecart();
   }

   public ModelAdapterMinecart() {
      super(EntityMinecart.class, "minecart", 0.5F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
   }
}
