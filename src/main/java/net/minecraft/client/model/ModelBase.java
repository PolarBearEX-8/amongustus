package net.minecraft.client.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public abstract class ModelBase {
   public float swingProgress;
   public boolean isRiding;
   public boolean isChild = true;
   public List boxList = Lists.newArrayList();
   private Map modelTextureMap = Maps.newHashMap();
   public int textureWidth = 64;
   public int textureHeight = 32;

   public static void copyModelAngles(ModelRenderer modelRenderer, ModelRenderer modelRenderer2) {
      modelRenderer2.rotateAngleX = modelRenderer.rotateAngleX;
      modelRenderer2.rotateAngleY = modelRenderer.rotateAngleY;
      modelRenderer2.rotateAngleZ = modelRenderer.rotateAngleZ;
      modelRenderer2.rotationPointX = modelRenderer.rotationPointX;
      modelRenderer2.rotationPointY = modelRenderer.rotationPointY;
      modelRenderer2.rotationPointZ = modelRenderer.rotationPointZ;
   }

   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
   }

   public TextureOffset getTextureOffset(String str) {
      return (TextureOffset)this.modelTextureMap.get(str);
   }

   public void setModelAttributes(ModelBase modelBase) {
      this.swingProgress = modelBase.swingProgress;
      this.isRiding = modelBase.isRiding;
      this.isChild = modelBase.isChild;
   }

   public ModelRenderer getRandomModelBox(Random rand) {
      return (ModelRenderer)this.boxList.get(rand.nextInt(this.boxList.size()));
   }

   public void setLivingAnimations(EntityLivingBase entity, float f, float f2, float f3) {
   }

   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
   }

   public void setTextureOffset(String str, int n, int n2) {
      this.modelTextureMap.put(str, new TextureOffset(n, n2));
   }
}
