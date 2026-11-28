package net.minecraft.client.model;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGhast extends ModelBase {
   public ModelRenderer body;
   public ModelRenderer[] tentacles = new ModelRenderer[9];

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      for (int i = 0; i < this.tentacles.length; i++) {
         this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(f3 * 0.3F + (float)i) + 0.4F;
      }
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 0.6F, 0.0F);
      this.body.render(f6);

      for (ModelRenderer modelRenderer : this.tentacles) {
         modelRenderer.render(f6);
      }

      GlStateManager.popMatrix();
   }

   public ModelGhast() {
      byte b = -16;
      this.body = new ModelRenderer(this, 0, 0);
      this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
      this.body.rotationPointY += (float)(24 + b);
      Random rand = new Random(1660L);

      for (int i = 0; i < this.tentacles.length; i++) {
         this.tentacles[i] = new ModelRenderer(this, 0, 0);
         float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
         float f2 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
         int n = rand.nextInt(7) + 8;
         this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, n, 2);
         this.tentacles[i].rotationPointX = f;
         this.tentacles[i].rotationPointZ = f2;
         this.tentacles[i].rotationPointY = (float)(31 + b);
      }
   }
}
