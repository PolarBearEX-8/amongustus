package client.render;

import client.util.DefaultVertexFormats;
import client.util.StencilBlurUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
   public final int indexOffset3;
   public int indexOffset;
   public final String text;
   public final int indexOffset1;
   public final String text2;

   public int processAction(String text3) {
      return GL20.glGetUniformLocation(this.indexOffset, text3);
   }

   public void processFloat(float factor, float factor2) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(0.0, 1.0).updateState();
      worldRenderer.getWorldRenderer(0.0, (double)factor2, 0.0).getWorldRenderer6(0.0, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)factor, (double)factor2, 0.0).getWorldRenderer6(1.0, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)factor, 0.0, 0.0).getWorldRenderer6(1.0, 1.0).updateState();
      tessellator.updateState();
   }

   private static int computeCount(int count, String text3) {
      int count2 = GL20.glCreateShader(count);
      GL20.glShaderSource(count2, resolveText(text3));
      GL20.glCompileShader(count2);
      if (GL20.glGetShaderi(count2, 35713) != 1) {
         String text4 = GL20.glGetShaderInfoLog(count2, 2048);
         System.out.printf("Failed to compile shader %s (stage: %s); Message\n%s%n", text3, GL11.glGetString(count), text4);
         return -1;
      } else {
         return count2;
      }
   }

   public void processAction() {
      GL20.glUseProgram(this.indexOffset);
   }

   public void processAction2() {
      GL20.glUseProgram(0);
   }

   private static String resolveText(String text3) {
      StringBuilder builder = new StringBuilder();

      try {
         InputStream resourceAsStream = ShaderProgram.class.getResourceAsStream(String.format("/assets/augustus/shaders/shaders/%s", text3));

         assert resourceAsStream != null;

         InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
         BufferedReader reader = new BufferedReader(inputStreamReader);

         String text4;
         while ((text4 = reader.readLine()) != null) {
            builder.append(text4).append('\n');
         }
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }

      return builder.toString();
   }

   public void processAction3() {
      GL20.glUseProgram(0);
      GL20.glDeleteProgram(this.indexOffset);
      GL20.glDeleteShader(this.indexOffset1);
      GL20.glDeleteShader(this.indexOffset3);
   }

   public ShaderProgram(String text3, String text4) {
      this.text = text3;
      this.text2 = text4;
      this.indexOffset1 = computeCount(35633, text3);
      this.indexOffset3 = computeCount(35632, text4);
      if (this.indexOffset1 != -1 && this.indexOffset3 != -1) {
         this.indexOffset = GL20.glCreateProgram();
         GL20.glAttachShader(this.indexOffset, this.indexOffset1);
         GL20.glAttachShader(this.indexOffset, this.indexOffset3);
         GL20.glLinkProgram(this.indexOffset);
      }
   }
}
