package net.augustus.render;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public abstract class AbstractSkeletonRenderer {
   private ModelBase currentModel = null;
   private static final FloatBuffer MODELVIEW_MATRIX_BUFFER = BufferUtils.createFloatBuffer(16);
   private final FloatBuffer partMatrixBuffer = BufferUtils.createFloatBuffer(16);
   private float[] vertexCoords = new float[500];
   private int vertexCoordIndex = 0;

   public void renderSkeleton(ModelBase modelBase, double d, double d2, double d3) {
      this.currentModel = modelBase;
      this.vertexCoordIndex = 0;
      this.renderSkeletonBones((float)d, (float)d2, (float)d3);
   }

   public void renderSkeletonBones(float f, float f2, float f3) {
      this.withPartTransform(this.getHead(), (byte)0, (modelRenderer, modelBox, list) -> this.emitHeadJoint(f, modelRenderer, modelBox, list));
      ModelRenderer body = this.getBody();
      SkeletonBoxConsumer skeletonBoxConsumer2 = (modelRenderer3, modelBox2, list2) -> {
         float boxCenterX = this.getBoxCenterX(modelBox2);
         float boxCenterZ = this.getBoxCenterZ(modelBox2);
         this.addTransformedVertex(boxCenterX, this.getTopY(list2), boxCenterZ);
         this.addTransformedVertex(boxCenterX, this.getBottomY(list2) + this.getBodyBottomOffset(), boxCenterZ);
         this.flushLineStrip();
      };
      this.withPartTransform(body, (byte)0, skeletonBoxConsumer2);
      int n;
      this.withPartTransform(this.getRightLeg(), (byte)0, (modelRenderer5, modelBox3, list3) -> this.emitRightLegBone(f3, modelRenderer5, modelBox3, list3));
      this.withPartTransform(this.getBody(), (byte)0, (modelRenderer7, modelBox4, list4) -> this.emitBodyBottomJoint(modelRenderer7, modelBox4, list4));
      this.withPartTransform(this.getLeftLeg(), (byte)0, (modelRenderer9, modelBox5, list5) -> this.emitLeftLegBone(f3, modelRenderer9, modelBox5, list5));
      ModelRenderer rightArm = this.getRightArm();
      n = this.getRightArmCubeIndex();
      this.withPartTransform(rightArm, n, (modelRenderer11, modelBox6, list6) -> this.emitRightArmBone(f2, modelRenderer11, modelBox6, list6));
      this.withPartTransform(this.getBody(), (byte)0, (modelRenderer13, modelBox7, list7) -> this.emitBodyTopJoint(modelRenderer13, modelBox7, list7));
      ModelRenderer leftArm = this.getLeftArm();
      n = this.getLeftArmCubeIndex();
      SkeletonBoxConsumer skeletonBoxConsumer8 = (modelRenderer15, modelBox8, list8) -> {
         float boxCenterX2 = this.getBoxCenterX(modelBox8);
         float boxCenterZ2 = this.getBoxCenterZ(modelBox8);
         this.addTransformedVertex(boxCenterX2, this.getTopY(list8), boxCenterZ2);
         this.addTransformedVertex(boxCenterX2, this.getBottomY(list8) + f2, boxCenterZ2);
         this.flushLineStrip();
      };
      this.withPartTransform(leftArm, n, skeletonBoxConsumer8);
   }

   public void withPartTransform(ModelRenderer modelRenderer, int n, SkeletonBoxConsumer skeletonBoxConsumer) {
      GlStateManager.pushMatrix();
      GL11.glLoadMatrix(MODELVIEW_MATRIX_BUFFER);
      List cubeList = modelRenderer.cubeList;
      double d = 0.0625;
      GlStateManager.translate(modelRenderer.offsetX, modelRenderer.offsetY, modelRenderer.offsetZ);
      boolean flag = modelRenderer.scaleX != 1.0F || modelRenderer.scaleY != 1.0F || modelRenderer.scaleZ != 1.0F;
      ((Buffer)this.partMatrixBuffer).clear();
      if (modelRenderer.rotateAngleX != 0.0F || modelRenderer.rotateAngleY != 0.0F || modelRenderer.rotateAngleZ != 0.0F) {
         GlStateManager.translate((double)modelRenderer.rotationPointX * d, (double)modelRenderer.rotationPointY * d, (double)modelRenderer.rotationPointZ * d);
         if (modelRenderer.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(modelRenderer.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         if (modelRenderer.rotateAngleY != 0.0F) {
            GlStateManager.rotate(modelRenderer.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (modelRenderer.rotateAngleX != 0.0F) {
            GlStateManager.rotate(modelRenderer.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }

         if (flag) {
            GlStateManager.scale(modelRenderer.scaleX, modelRenderer.scaleY, modelRenderer.scaleZ);
         }

         GL11.glGetFloat(2982, this.partMatrixBuffer);
         if (flag) {
            GlStateManager.scale(1.0F / modelRenderer.scaleX, 1.0F / modelRenderer.scaleY, 1.0F / modelRenderer.scaleZ);
         }

         if (modelRenderer.rotateAngleX != 0.0F) {
            GlStateManager.rotate(-modelRenderer.rotateAngleX * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         }

         if (modelRenderer.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-modelRenderer.rotateAngleY * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         }

         if (modelRenderer.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(-modelRenderer.rotateAngleZ * (180.0F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
         }

         GlStateManager.translate((double)(-modelRenderer.rotationPointX) * d, (double)(-modelRenderer.rotationPointY) * d, (double)(-modelRenderer.rotationPointZ) * d);
      } else if (modelRenderer.rotationPointX == 0.0F && modelRenderer.rotationPointY == 0.0F && modelRenderer.rotationPointZ == 0.0F) {
         if (flag) {
            GlStateManager.scale(modelRenderer.scaleX, modelRenderer.scaleY, modelRenderer.scaleZ);
         }

         GL11.glGetFloat(2982, this.partMatrixBuffer);
         if (flag) {
            GlStateManager.scale(1.0F / modelRenderer.scaleX, 1.0F / modelRenderer.scaleY, 1.0F / modelRenderer.scaleZ);
         }
      } else {
         GlStateManager.translate((double)modelRenderer.rotationPointX * d, (double)modelRenderer.rotationPointY * d, (double)modelRenderer.rotationPointZ * d);
         if (flag) {
            GlStateManager.scale(modelRenderer.scaleX, modelRenderer.scaleY, modelRenderer.scaleZ);
         }

         GL11.glGetFloat(2982, this.partMatrixBuffer);
         if (flag) {
            GlStateManager.scale(1.0F / modelRenderer.scaleX, 1.0F / modelRenderer.scaleY, 1.0F / modelRenderer.scaleZ);
         }

         GlStateManager.translate((double)(-modelRenderer.rotationPointX) * d, (double)(-modelRenderer.rotationPointY) * d, (double)(-modelRenderer.rotationPointZ) * d);
      }

      GlStateManager.translate(-modelRenderer.offsetX, -modelRenderer.offsetY, -modelRenderer.offsetZ);
      GlStateManager.popMatrix();
      skeletonBoxConsumer.accept(modelRenderer, (ModelBox)cubeList.get(n), cubeList);
   }

   public void addTransformedVertex(float f, float f2, float f3) {
      if (this.vertexCoordIndex + 3 >= this.vertexCoords.length - 2) {
         this.vertexCoords = Arrays.copyOf(this.vertexCoords, this.vertexCoords.length * 2);
      }

      this.vertexCoords[this.vertexCoordIndex] = this.partMatrixBuffer.get(0) * f + this.partMatrixBuffer.get(4) * f2 + this.partMatrixBuffer.get(8) * f3 + this.partMatrixBuffer.get(12);
      this.vertexCoords[this.vertexCoordIndex + 1] = this.partMatrixBuffer.get(1) * f + this.partMatrixBuffer.get(5) * f2 + this.partMatrixBuffer.get(9) * f3 + this.partMatrixBuffer.get(13);
      this.vertexCoords[this.vertexCoordIndex + 2] = this.partMatrixBuffer.get(2) * f + this.partMatrixBuffer.get(6) * f2 + this.partMatrixBuffer.get(10) * f3 + this.partMatrixBuffer.get(14);
      this.vertexCoordIndex += 3;
   }

   public void flushLineStrip() {
      if (this.vertexCoordIndex > 0) {
         Tessellator instance = Tessellator.getInstance();
         WorldRenderer worldRenderer = instance.getWorldRenderer();
         worldRenderer.begin(3, DefaultVertexFormats.POSITION);

         for (int i = 0; i < this.vertexCoordIndex; i += 3) {
            worldRenderer.pos((double)this.vertexCoords[i], (double)this.vertexCoords[i + 1], (double)this.vertexCoords[i + 2]).endVertex();
         }

         instance.draw();
      }

      this.vertexCoordIndex = 0;
   }

   public float getBoxCenterX(ModelBox modelBox) {
      float posX2 = modelBox.posX2;
      return this.midpoint(modelBox.posX1, posX2) / 16.0F;
   }

   public float getBoxCenterZ(ModelBox modelBox) {
      float posZ2 = modelBox.posZ2;
      return this.midpoint(modelBox.posZ1, posZ2) / 16.0F;
   }

   public float midpoint(float f, float f2) {
      return f + (f2 - f) * 0.5F;
   }

   public float getTopY(List list) {
      return ((ModelBox)list.get(0)).posY1 / 16.0F;
   }

   public float getBottomY(List list) {
      return ((ModelBox)list.get(list.size() - 1)).posY2 / 16.0F;
   }

   public static void captureModelViewMatrix() {
      GL11.glGetFloat(2982, MODELVIEW_MATRIX_BUFFER);
   }

   public final ModelRenderer getHead() {
      return this.getHead(this.currentModel);
   }

   public final ModelRenderer getBody() {
      return this.getBody(this.currentModel);
   }

   public final ModelRenderer getRightLeg() {
      return this.getRightLeg(this.currentModel);
   }

   public final ModelRenderer getLeftLeg() {
      return this.getLeftLeg(this.currentModel);
   }

   public final ModelRenderer getRightArm() {
      return this.getRightArm(this.currentModel);
   }

   public final ModelRenderer getLeftArm() {
      return this.getLeftArm(this.currentModel);
   }

   public abstract ModelRenderer getHead(ModelBase modelBase);

   public abstract ModelRenderer getBody(ModelBase modelBase);

   public abstract ModelRenderer getRightLeg(ModelBase modelBase);

   public abstract ModelRenderer getLeftLeg(ModelBase modelBase);

   public abstract ModelRenderer getRightArm(ModelBase modelBase);

   public abstract ModelRenderer getLeftArm(ModelBase modelBase);

   public int getRightArmCubeIndex() {
      return 0;
   }

   public int getLeftArmCubeIndex() {
      return 0;
   }

   public float getBodyBottomOffset() {
      return 0.0F;
   }

   private void emitBodyTopJoint(ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getTopY(list), boxCenterZ);
   }

   private void emitRightArmBone(float f, ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getBottomY(list) + f, boxCenterZ);
      this.addTransformedVertex(boxCenterX, this.getTopY(list), boxCenterZ);
   }

   private void emitLeftLegBone(float f, ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getTopY(list), boxCenterZ);
      this.addTransformedVertex(boxCenterX, this.getBottomY(list) + f, boxCenterZ);
      this.flushLineStrip();
   }

   private void emitBodyBottomJoint(ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getBottomY(list) + this.getBodyBottomOffset(), boxCenterZ);
   }

   private void emitRightLegBone(float f, ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getBottomY(list) + f, boxCenterZ);
      this.addTransformedVertex(boxCenterX, this.getTopY(list), boxCenterZ);
   }

   private void emitHeadJoint(float f, ModelRenderer modelRenderer, ModelBox modelBox, List list) {
      float boxCenterX = this.getBoxCenterX(modelBox);
      float boxCenterZ = this.getBoxCenterZ(modelBox);
      this.addTransformedVertex(boxCenterX, this.getTopY(list) + f, boxCenterZ);
   }




}
