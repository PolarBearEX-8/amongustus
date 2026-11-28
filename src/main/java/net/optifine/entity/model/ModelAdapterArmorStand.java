package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.optifine.config.Config;

public class ModelAdapterArmorStand extends ModelAdapterBiped {

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      ArmorStandRenderer armorStandRenderer = new ArmorStandRenderer(renderManager);
      armorStandRenderer.mainModel = modelBase;
      armorStandRenderer.shadowSize = f;
      return armorStandRenderer;
   }

   @Override
   public ModelBase makeModel() {
      return new ModelArmorStand();
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelArmorStand)) {
         return null;
      } else {
         ModelArmorStand modelArmorStand = (ModelArmorStand)modelBase;
         return str.equals("right")
            ? modelArmorStand.standRightSide
            : (
               str.equals("left")
                  ? modelArmorStand.standLeftSide
                  : (str.equals("waist") ? modelArmorStand.standWaist : (str.equals("base") ? modelArmorStand.standBase : super.getModelRenderer(modelArmorStand, str)))
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return (String[])Config.addObjectsToArray(super.getModelRendererNames(), new String[]{"right", "left", "waist", "base"});
   }

   public ModelAdapterArmorStand() {
      super(EntityArmorStand.class, "armor_stand", 0.0F);
   }
}
