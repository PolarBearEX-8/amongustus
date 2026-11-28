package net.optifine.entity.model;

import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapter {
   private Class entityClass;
   private String name;
   private float shadowSize;
   private String[] aliases;

   public String[] getAliases() {
      return this.aliases;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }

   public abstract String[] getModelRendererNames();

   public ModelAdapter(Class clazz, String str, float f, String[] strings) {
      this.entityClass = clazz;
      this.name = str;
      this.shadowSize = f;
      this.aliases = strings;
   }

   public String getName() {
      return this.name;
   }

   public abstract IEntityRenderer makeEntityRender(ModelBase modelBase, float f);

   public abstract ModelRenderer getModelRenderer(ModelBase modelBase, String str);

   public abstract ModelBase makeModel();

   public Class getEntityClass() {
      return this.entityClass;
   }

   public ModelRenderer[] getModelRenderers(ModelBase modelBase) {
      String[] modelRendererNames = this.getModelRendererNames();
      ArrayList list = new ArrayList();

      for (int i = 0; i < modelRendererNames.length; i++) {
         ModelRenderer modelRenderer = this.getModelRenderer(modelBase, modelRendererNames[i]);
         if (modelRenderer != null) {
            list.add(modelRenderer);
         }
      }

      return (ModelRenderer[])list.toArray(new ModelRenderer[list.size()]);
   }

   public ModelAdapter(Class clazz, String str, float f) {
      this.entityClass = clazz;
      this.name = str;
      this.shadowSize = f;
   }
}
