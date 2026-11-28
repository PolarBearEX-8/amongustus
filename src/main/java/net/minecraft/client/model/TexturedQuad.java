package net.minecraft.client.model;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec3;
import net.optifine.config.Config;
import net.optifine.shaders.SVertexFormat;

public class TexturedQuad {
   public PositionTextureVertex[] vertexPositions;
   public int nVertices;
   private boolean invertNormal;

   public TexturedQuad(PositionTextureVertex[] positionTextureVertexs, int n, int n2, int n3, int n4, float f, float f2) {
      this(positionTextureVertexs);
      float f3 = 0.0F / f;
      float f4 = 0.0F / f2;
      positionTextureVertexs[0] = positionTextureVertexs[0].setTexturePosition((float)n3 / f - f3, (float)n2 / f2 + f4);
      positionTextureVertexs[1] = positionTextureVertexs[1].setTexturePosition((float)n / f + f3, (float)n2 / f2 + f4);
      positionTextureVertexs[2] = positionTextureVertexs[2].setTexturePosition((float)n / f + f3, (float)n4 / f2 - f4);
      positionTextureVertexs[3] = positionTextureVertexs[3].setTexturePosition((float)n3 / f - f3, (float)n4 / f2 - f4);
   }

   public void draw(WorldRenderer worldRenderer, float f) {
      Vec3 vec = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[0].vector3D);
      Vec3 vec3 = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[2].vector3D).crossProduct(vec).normalize();
      float xCoord = (float)vec3.xCoord;
      float yCoord = (float)vec3.yCoord;
      float zCoord = (float)vec3.zCoord;
      if (this.invertNormal) {
         xCoord = -xCoord;
         yCoord = -yCoord;
         zCoord = -zCoord;
      }

      boolean flag = !worldRenderer.isDrawing();
      if (flag) {
         if (Config.isShaderPackLoaded()) {
            worldRenderer.begin(7, SVertexFormat.defVertexFormatTextured);
         } else {
            worldRenderer.begin(7, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);
         }
      }

      for (int i = 0; i < 4; i++) {
         PositionTextureVertex positionTextureVertex = this.vertexPositions[i];
         worldRenderer.pos(positionTextureVertex.vector3D.xCoord * (double)f, positionTextureVertex.vector3D.yCoord * (double)f, positionTextureVertex.vector3D.zCoord * (double)f)
            .tex((double)positionTextureVertex.texturePositionX, (double)positionTextureVertex.texturePositionY)
            .normal(xCoord, yCoord, zCoord)
            .endVertex();
      }

      if (flag) {
         Tessellator.getInstance().draw();
      }
   }

   public TexturedQuad(PositionTextureVertex[] positionTextureVertexs) {
      this.vertexPositions = positionTextureVertexs;
      this.nVertices = positionTextureVertexs.length;
   }

   public void flipFace() {
      PositionTextureVertex[] positionTextureVertexs = new PositionTextureVertex[this.vertexPositions.length];

      for (int i = 0; i < this.vertexPositions.length; i++) {
         positionTextureVertexs[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
      }

      this.vertexPositions = positionTextureVertexs;
   }
}
