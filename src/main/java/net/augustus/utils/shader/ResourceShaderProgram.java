package net.augustus.utils.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ResourceShaderProgram {
   private final int vertexShaderId;
   private final int fragmentShaderId;
   private final String vertexShaderPath;
   private final String fragmentShaderPath;
   private int programId;

   private static int compileShader(int n, String str) {
      int n2 = GL20.glCreateShader(n);
      GL20.glShaderSource(n2, loadShaderSource(str));
      GL20.glCompileShader(n2);
      if (!(GL20.glGetShaderi(n2, 35713) == 1)) {
         String str2 = GL20.glGetShaderInfoLog(n2, 2048);
         System.out.printf("Failed to compile shader %s (stage: %s); Message\n%s%n", str, GL11.glGetString(n), str2);
         return -1;
      } else {
         return n2;
      }
   }

   public void unbind() {
      GL20.glUseProgram(0);
   }

   private static String loadShaderSource(String str) {
      StringBuilder sb = new StringBuilder();

      try {
         InputStream resourceAsStream = ResourceShaderProgram.class.getResourceAsStream(String.format("/assets/augustus/shaders/shaders/%s", str));

         assert resourceAsStream != null;

         InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
         BufferedReader reader = new BufferedReader(inputStreamReader);

         String str2;
         while ((str2 = reader.readLine()) != null) {
            sb.append(str2).append('\n');
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return sb.toString();
   }

   public void delete() {
      GL20.glUseProgram(0);
      GL20.glDeleteProgram(this.programId);
      GL20.glDeleteShader(this.vertexShaderId);
      GL20.glDeleteShader(this.fragmentShaderId);
   }

   public void drawFullscreenQuad(float f, float f2) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(0.0, 0.0, 0.0).tex(0.0, 1.0).endVertex();
      worldRenderer.pos(0.0, (double)f2, 0.0).tex(0.0, 0.0).endVertex();
      worldRenderer.pos((double)f, (double)f2, 0.0).tex(1.0, 0.0).endVertex();
      worldRenderer.pos((double)f, 0.0, 0.0).tex(1.0, 1.0).endVertex();
      instance.draw();
   }

   public ResourceShaderProgram(String str, String str2) {
       super();
      this.vertexShaderPath = str;
      this.fragmentShaderPath = str2;
      this.vertexShaderId = compileShader(35633, str);
      this.fragmentShaderId = compileShader(35632, str2);
      if (this.vertexShaderId != -1 && this.fragmentShaderId != -1) {
         this.programId = GL20.glCreateProgram();
         GL20.glAttachShader(this.programId, this.vertexShaderId);
         GL20.glAttachShader(this.programId, this.fragmentShaderId);
         GL20.glLinkProgram(this.programId);
      }
   }

   public int getUniformLocation(String str) {
      return GL20.glGetUniformLocation(this.programId, str);
   }

   public void bind() {
      GL20.glUseProgram(this.programId);
   }

}
