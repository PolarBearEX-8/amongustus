package net.minecraft.client.model;

import net.minecraft.client.renderer.WorldRenderer;

public class ModelBox {
   private PositionTextureVertex[] vertexPositions;
   private TexturedQuad[] quadList;
   public final float posX1;
   public final float posY1;
   public final float posZ1;
   public final float posX2;
   public final float posY2;
   public final float posZ2;
   public String boxName;

   public ModelBox setBoxName(String str) {
      this.boxName = str;
      return this;
   }

   public void render(WorldRenderer worldRenderer, float f) {
      for (int i = 0; i < this.quadList.length; i++) {
         TexturedQuad texturedQuad = this.quadList[i];
         if (texturedQuad != null) {
            texturedQuad.draw(worldRenderer, f);
         }
      }
   }

   public ModelBox(ModelRenderer modelRenderer, int[][] intsArr, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean flag) {
      this.posX1 = f;
      this.posY1 = f2;
      this.posZ1 = f3;
      this.posX2 = f + f4;
      this.posY2 = f2 + f5;
      this.posZ2 = f3 + f6;
      this.vertexPositions = new PositionTextureVertex[8];
      this.quadList = new TexturedQuad[6];
      float f8 = f + f4;
      float f9 = f2 + f5;
      float f10 = f3 + f6;
      f -= f7;
      f2 -= f7;
      f3 -= f7;
      f8 += f7;
      f9 += f7;
      f10 += f7;
      if (flag) {
         float f11 = f8;
         f8 = f;
         f = f11;
      }

      PositionTextureVertex positionTextureVertex = new PositionTextureVertex(f, f2, f3, 0.0F, 0.0F);
      PositionTextureVertex positionTextureVertex2 = new PositionTextureVertex(f8, f2, f3, 0.0F, 8.0F);
      PositionTextureVertex positionTextureVertex3 = new PositionTextureVertex(f8, f9, f3, 8.0F, 8.0F);
      PositionTextureVertex positionTextureVertex4 = new PositionTextureVertex(f, f9, f3, 8.0F, 0.0F);
      PositionTextureVertex positionTextureVertex5 = new PositionTextureVertex(f, f2, f10, 0.0F, 0.0F);
      PositionTextureVertex positionTextureVertex6 = new PositionTextureVertex(f8, f2, f10, 0.0F, 8.0F);
      PositionTextureVertex positionTextureVertex7 = new PositionTextureVertex(f8, f9, f10, 8.0F, 8.0F);
      PositionTextureVertex positionTextureVertex8 = new PositionTextureVertex(f, f9, f10, 8.0F, 0.0F);
      this.vertexPositions[0] = positionTextureVertex;
      this.vertexPositions[1] = positionTextureVertex2;
      this.vertexPositions[2] = positionTextureVertex3;
      this.vertexPositions[3] = positionTextureVertex4;
      this.vertexPositions[4] = positionTextureVertex5;
      this.vertexPositions[5] = positionTextureVertex6;
      this.vertexPositions[6] = positionTextureVertex7;
      this.vertexPositions[7] = positionTextureVertex8;
      this.quadList[0] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex6, positionTextureVertex2, positionTextureVertex3, positionTextureVertex7}, intsArr[4], false, modelRenderer.textureWidth, modelRenderer.textureHeight);
      this.quadList[1] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex, positionTextureVertex5, positionTextureVertex8, positionTextureVertex4}, intsArr[5], false, modelRenderer.textureWidth, modelRenderer.textureHeight);
      this.quadList[2] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex6, positionTextureVertex5, positionTextureVertex, positionTextureVertex2}, intsArr[1], true, modelRenderer.textureWidth, modelRenderer.textureHeight);
      this.quadList[3] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex3, positionTextureVertex4, positionTextureVertex8, positionTextureVertex7}, intsArr[0], true, modelRenderer.textureWidth, modelRenderer.textureHeight);
      this.quadList[4] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex2, positionTextureVertex, positionTextureVertex4, positionTextureVertex3}, intsArr[2], false, modelRenderer.textureWidth, modelRenderer.textureHeight);
      this.quadList[5] = this.makeTexturedQuad(new PositionTextureVertex[]{positionTextureVertex5, positionTextureVertex6, positionTextureVertex7, positionTextureVertex8}, intsArr[3], false, modelRenderer.textureWidth, modelRenderer.textureHeight);
      if (flag) {
         for (TexturedQuad texturedQuad : this.quadList) {
            texturedQuad.flipFace();
         }
      }
   }

   private TexturedQuad makeTexturedQuad(PositionTextureVertex[] positionTextureVertexs, int[] ints, boolean flag, float f, float f2) {
      return ints == null
         ? null
         : (
            flag
               ? new TexturedQuad(positionTextureVertexs, ints[2], ints[3], ints[0], ints[1], f, f2)
               : new TexturedQuad(positionTextureVertexs, ints[0], ints[1], ints[2], ints[3], f, f2)
         );
   }

   public ModelBox(ModelRenderer modelRenderer, int n, int n2, float f, float f2, float f3, int n3, int n4, int n5, float f4) {
      this(modelRenderer, n, n2, f, f2, f3, n3, n4, n5, f4, modelRenderer.mirror);
   }

   public ModelBox(ModelRenderer modelRenderer, int n, int n2, float f, float f2, float f3, int n3, int n4, int n5, float f4, boolean flag) {
      this.posX1 = f;
      this.posY1 = f2;
      this.posZ1 = f3;
      this.posX2 = f + (float)n3;
      this.posY2 = f2 + (float)n4;
      this.posZ2 = f3 + (float)n5;
      this.vertexPositions = new PositionTextureVertex[8];
      this.quadList = new TexturedQuad[6];
      float f5 = f + (float)n3;
      float f6 = f2 + (float)n4;
      float f7 = f3 + (float)n5;
      f -= f4;
      f2 -= f4;
      f3 -= f4;
      f5 += f4;
      f6 += f4;
      f7 += f4;
      if (flag) {
         float f8 = f5;
         f5 = f;
         f = f8;
      }

      PositionTextureVertex positionTextureVertex = new PositionTextureVertex(f, f2, f3, 0.0F, 0.0F);
      PositionTextureVertex positionTextureVertex2 = new PositionTextureVertex(f5, f2, f3, 0.0F, 8.0F);
      PositionTextureVertex positionTextureVertex3 = new PositionTextureVertex(f5, f6, f3, 8.0F, 8.0F);
      PositionTextureVertex positionTextureVertex4 = new PositionTextureVertex(f, f6, f3, 8.0F, 0.0F);
      PositionTextureVertex positionTextureVertex5 = new PositionTextureVertex(f, f2, f7, 0.0F, 0.0F);
      PositionTextureVertex positionTextureVertex6 = new PositionTextureVertex(f5, f2, f7, 0.0F, 8.0F);
      PositionTextureVertex positionTextureVertex7 = new PositionTextureVertex(f5, f6, f7, 8.0F, 8.0F);
      PositionTextureVertex positionTextureVertex8 = new PositionTextureVertex(f, f6, f7, 8.0F, 0.0F);
      this.vertexPositions[0] = positionTextureVertex;
      this.vertexPositions[1] = positionTextureVertex2;
      this.vertexPositions[2] = positionTextureVertex3;
      this.vertexPositions[3] = positionTextureVertex4;
      this.vertexPositions[4] = positionTextureVertex5;
      this.vertexPositions[5] = positionTextureVertex6;
      this.vertexPositions[6] = positionTextureVertex7;
      this.vertexPositions[7] = positionTextureVertex8;
      this.quadList[0] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex6, positionTextureVertex2, positionTextureVertex3, positionTextureVertex7},
         n + n5 + n3,
         n2 + n5,
         n + n5 + n3 + n5,
         n2 + n5 + n4,
         modelRenderer.textureWidth,
         modelRenderer.textureHeight
      );
      this.quadList[1] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex, positionTextureVertex5, positionTextureVertex8, positionTextureVertex4}, n, n2 + n5, n + n5, n2 + n5 + n4, modelRenderer.textureWidth, modelRenderer.textureHeight
      );
      this.quadList[2] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex6, positionTextureVertex5, positionTextureVertex, positionTextureVertex2}, n + n5, n2, n + n5 + n3, n2 + n5, modelRenderer.textureWidth, modelRenderer.textureHeight
      );
      this.quadList[3] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex3, positionTextureVertex4, positionTextureVertex8, positionTextureVertex7}, n + n5 + n3, n2 + n5, n + n5 + n3 + n3, n2, modelRenderer.textureWidth, modelRenderer.textureHeight
      );
      this.quadList[4] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex2, positionTextureVertex, positionTextureVertex4, positionTextureVertex3}, n + n5, n2 + n5, n + n5 + n3, n2 + n5 + n4, modelRenderer.textureWidth, modelRenderer.textureHeight
      );
      this.quadList[5] = new TexturedQuad(
         new PositionTextureVertex[]{positionTextureVertex5, positionTextureVertex6, positionTextureVertex7, positionTextureVertex8},
         n + n5 + n3 + n5,
         n2 + n5,
         n + n5 + n3 + n5 + n3,
         n2 + n5 + n4,
         modelRenderer.textureWidth,
         modelRenderer.textureHeight
      );
      if (flag) {
         for (int i = 0; i < this.quadList.length; i++) {
            this.quadList[i].flipFace();
         }
      }
   }
}
