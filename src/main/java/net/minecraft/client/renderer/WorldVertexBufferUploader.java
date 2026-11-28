package net.minecraft.client.renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.optifine.config.Config;
import net.optifine.shaders.SVertexBuilder;
import org.lwjgl.opengl.GL11;

public class WorldVertexBufferUploader {
   public void draw(WorldRenderer worldRenderer) {
      if (worldRenderer.getVertexCount() > 0) {
         if (worldRenderer.getDrawMode() == 7 && Config.isQuadsToTriangles()) {
            worldRenderer.quadsToTriangles();
         }

         VertexFormat vertexFormat = worldRenderer.getVertexFormat();
         int nextOffset = vertexFormat.getNextOffset();
         ByteBuffer byteBuffer = worldRenderer.getByteBuffer();
         List elements = vertexFormat.getElements();

         for (int i = 0; i < elements.size(); i++) {
            VertexFormatElement vertexFormatElement = (VertexFormatElement)elements.get(i);
            VertexFormatElement.EnumUsage usage = vertexFormatElement.getUsage();
            int glConstant = vertexFormatElement.getType().getGlConstant();
            int index = vertexFormatElement.getIndex();
            ((Buffer)byteBuffer).position(vertexFormat.getOffset(i));
            switch (usage) {
               case POSITION:
                  GL11.glVertexPointer(vertexFormatElement.getElementCount(), glConstant, nextOffset, byteBuffer);
                  GL11.glEnableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + index);
                  GL11.glTexCoordPointer(vertexFormatElement.getElementCount(), glConstant, nextOffset, byteBuffer);
                  GL11.glEnableClientState(32888);
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                  break;
               case COLOR:
                  GL11.glColorPointer(vertexFormatElement.getElementCount(), glConstant, nextOffset, byteBuffer);
                  GL11.glEnableClientState(32886);
                  break;
               case NORMAL:
                  GL11.glNormalPointer(glConstant, nextOffset, byteBuffer);
                  GL11.glEnableClientState(32885);
            }
         }

         if (worldRenderer.isMultiTexture()) {
            worldRenderer.drawMultiTexture();
         } else if (Config.isShaderPackLoaded()) {
            SVertexBuilder.drawArrays(worldRenderer.getDrawMode(), 0, worldRenderer.getVertexCount(), worldRenderer);
         } else {
            GL11.glDrawArrays(worldRenderer.getDrawMode(), 0, worldRenderer.getVertexCount());
         }

         int n = 0;

         for (int j = elements.size(); n < j; n++) {
            VertexFormatElement vertexFormatElement2 = (VertexFormatElement)elements.get(n);
            VertexFormatElement.EnumUsage usage2 = vertexFormatElement2.getUsage();
            int index2 = vertexFormatElement2.getIndex();
            switch (usage2) {
               case POSITION:
                  GL11.glDisableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + index2);
                  GL11.glDisableClientState(32888);
                  OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                  break;
               case COLOR:
                  GL11.glDisableClientState(32886);
                  GlStateManager.resetColor();
                  break;
               case NORMAL:
                  GL11.glDisableClientState(32885);
            }
         }
      }

      worldRenderer.reset();
   }

}
