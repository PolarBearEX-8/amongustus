package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnderMite extends ModelBase {
   private static final int[][] BODY_SIZES = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
   private static final int[][] BODY_TEXS = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
   private static final int BODY_COUNT = BODY_SIZES.length;
   public final ModelRenderer[] bodyParts = new ModelRenderer[BODY_COUNT];

   public ModelEnderMite() {
      float f = -3.5F;

      for (int i = 0; i < this.bodyParts.length; i++) {
         this.bodyParts[i] = new ModelRenderer(this, BODY_TEXS[i][0], BODY_TEXS[i][1]);
         this.bodyParts[i].addBox((float)BODY_SIZES[i][0] * -0.5F, 0.0F, (float)BODY_SIZES[i][2] * -0.5F, BODY_SIZES[i][0], BODY_SIZES[i][1], BODY_SIZES[i][2]);
         this.bodyParts[i].setRotationPoint(0.0F, (float)(24 - BODY_SIZES[i][1]), f);
         if (i < this.bodyParts.length - 1) {
            f += (float)(BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
         }
      }
   }

   @Override
   public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, boolean flag) {
      this.setRotationAngles(f, f2, f3, f4, f5, f6, entity);

      for (int i = 0; i < this.bodyParts.length; i++) {
         this.bodyParts[i].render(f6);
      }
   }

   @Override
   public void setRotationAngles(float f, float f2, float f3, float f4, float f5, float f6, Entity entity) {
      for (int i = 0; i < this.bodyParts.length; i++) {
         this.bodyParts[i].rotateAngleY = MathHelper.cos(f3 * 0.9F + (float)i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.01F * (float)(1 + Math.abs(i - 2));
         this.bodyParts[i].rotationPointX = MathHelper.sin(f3 * 0.9F + (float)i * 0.15F * (float) Math.PI)
            * (float) Math.PI
            * 0.1F
            * (float)Math.abs(i - 2);
      }
   }
}
