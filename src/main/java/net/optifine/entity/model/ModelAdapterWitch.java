package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;

public class ModelAdapterWitch extends ModelAdapter {

   public ModelAdapterWitch() {
      super(EntityWitch.class, "witch", 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderWitch renderWitch = new RenderWitch(renderManager);
      renderWitch.mainModel = modelBase;
      renderWitch.shadowSize = f;
      return renderWitch;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   @Override
   public ModelBase makeModel() {
      return new ModelWitch(0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelWitch)) {
         return null;
      } else {
         ModelWitch modelWitch = (ModelWitch)modelBase;
         if (str.equals("mole")) {
            return modelWitch.witchMole;
         } else if (str.equals("hat")) {
            return modelWitch.witchHat;
         } else {
            return str.equals("head")
               ? modelWitch.villagerHead
               : (
                  str.equals("body")
                     ? modelWitch.villagerBody
                     : (
                        str.equals("arms")
                           ? modelWitch.villagerArms
                           : (
                              str.equals("left_leg")
                                 ? modelWitch.leftVillagerLeg
                                 : (str.equals("right_leg") ? modelWitch.rightVillagerLeg : (str.equals("nose") ? modelWitch.villagerNose : null))
                           )
                     )
               );
         }
      }
   }
}
