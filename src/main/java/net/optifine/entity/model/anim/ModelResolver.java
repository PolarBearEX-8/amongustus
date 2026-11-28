package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.optifine.config.Config;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.expr.IExpression;

public class ModelResolver implements IModelResolver {
   private ModelAdapter modelAdapter;
   private ModelBase modelBase;
   private CustomModelRenderer[] customModelRenderers;
   private ModelRenderer thisModelRenderer;
   private ModelRenderer partModelRenderer;
   private IRenderResolver renderResolver;

   @Override
   public ModelRenderer getModelRenderer(String str) {
      if (str == null) {
         return null;
      } else if (str.indexOf(":") >= 0) {
         String[] strings = Config.tokenize(str, ":");
         ModelRenderer modelRenderer = this.getModelRenderer(strings[0]);

         for (int i = 1; i < strings.length; i++) {
            ModelRenderer childDeep = modelRenderer.getChildDeep(strings[i]);
            if (childDeep == null) {
               return null;
            }

            modelRenderer = childDeep;
         }

         return modelRenderer;
      } else if (this.thisModelRenderer != null && str.equals("this")) {
         return this.thisModelRenderer;
      } else if (this.partModelRenderer != null && str.equals("part")) {
         return this.partModelRenderer;
      } else {
         ModelRenderer modelRenderer2 = this.modelAdapter.getModelRenderer(this.modelBase, str);
         if (modelRenderer2 != null) {
            return modelRenderer2;
         } else {
            for (int j = 0; j < this.customModelRenderers.length; j++) {
               ModelRenderer modelRenderer3 = this.customModelRenderers[j].getModelRenderer();
               if (str.equals(modelRenderer3.getId())) {
                  return modelRenderer3;
               }

               ModelRenderer childDeep2 = modelRenderer3.getChildDeep(str);
               if (childDeep2 != null) {
                  return childDeep2;
               }
            }

            return null;
         }
      }
   }

   @Override
   public IExpression getExpression(String str) {
      ModelVariableFloat modelVariable = this.getModelVariable(str);
      if (modelVariable != null) {
         return modelVariable;
      } else {
         IExpression parameter = this.renderResolver.getParameter(str);
         return parameter != null ? parameter : null;
      }
   }

   public void setThisModelRenderer(ModelRenderer modelRenderer) {
      this.thisModelRenderer = modelRenderer;
   }

   @Override
   public ModelVariableFloat getModelVariable(String str) {
      String[] strings = Config.tokenize(str, ".");
      if (strings.length != 2) {
         return null;
      } else {
         String str2 = strings[0];
         String str3 = strings[1];
         ModelRenderer modelRenderer = this.getModelRenderer(str2);
         if (modelRenderer == null) {
            return null;
         } else {
            ModelVariableType modelVariableType = ModelVariableType.parse(str3);
            return modelVariableType == null ? null : new ModelVariableFloat(str, modelRenderer, modelVariableType);
         }
      }
   }

   public ModelResolver(ModelAdapter modelAdapter2, ModelBase modelBase2, CustomModelRenderer[] customModelRenderers2) {
      this.modelAdapter = modelAdapter2;
      this.modelBase = modelBase2;
      this.customModelRenderers = customModelRenderers2;
      Class entityClass = modelAdapter2.getEntityClass();
      if (TileEntity.class.isAssignableFrom(entityClass)) {
         this.renderResolver = new RenderResolverTileEntity();
      } else {
         this.renderResolver = new RenderResolverEntity();
      }
   }

   public void setPartModelRenderer(ModelRenderer modelRenderer) {
      this.partModelRenderer = modelRenderer;
   }
}
