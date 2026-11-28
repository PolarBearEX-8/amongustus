package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.boss.EntityDragon;

public class ModelAdapterDragon extends ModelAdapter {

   public ModelAdapterDragon() {
      super(EntityDragon.class, "dragon", 0.5F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelDragon(0.0F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase modelBase, float f) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      RenderDragon renderDragon = new RenderDragon(renderManager);
      renderDragon.mainModel = modelBase;
      renderDragon.shadowSize = f;
      return renderDragon;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{
         "head", "spine", "jaw", "body", "rear_leg", "front_leg", "rear_leg_tip", "front_leg_tip", "rear_foot", "front_foot", "wing", "wing_tip"
      };
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase modelBase, String str) {
      if (!(modelBase instanceof ModelDragon)) {
         return null;
      } else {
         ModelDragon modelDragon = (ModelDragon)modelBase;
         switch (str) {
            case "head":
               return modelDragon.head;
            case "spine":
               return modelDragon.spine;
            case "jaw":
               return modelDragon.jaw;
            case "rear_leg":
               return modelDragon.rearLeg;
            case "front_leg":
               return modelDragon.frontLeg;
            case "rear_leg_tip":
               return modelDragon.rearLegTip;
            case "front_leg_tip":
               return modelDragon.frontLegTip;
            case "rear_foot":
               return modelDragon.rearFoot;
            case "front_foot":
               return modelDragon.frontFoot;
            case "wing":
               return modelDragon.wing;
            case "wing_tip":
               return modelDragon.wingTip;
            default:
               return null;
         }
      }
   }
}
