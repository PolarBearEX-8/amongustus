package net.augustus.utils.shader;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
   private int previousProgramId;
   private final Object2IntMap uniformLocations = new Object2IntOpenHashMap();
   private final IntList savedTextureBindings = new IntArrayList();
   private final int programId;

   public void setUniform1f(String str, float f) {
      GL20.glUniform1f(this.getUniformLocation(str), f);
   }

   public void drawQuad(float f, float f2, float f3, float f4) {
      GL11.glBegin(7);
      GL11.glVertex2f(f, f4);
      GL11.glVertex2f(f3, f4);
      GL11.glVertex2f(f3, f2);
      GL11.glVertex2f(f, f2);
      GL11.glEnd();
   }

   public void unbind() {

      for (int i = 0; i < this.savedTextureBindings.size(); i++) {
         GlStateManager.setActiveTexture(33984 + i);
         GlStateManager.bindTexture(this.savedTextureBindings.getInt(i));
      }

      this.savedTextureBindings.clear();
      GlStateManager.setActiveTexture(33984);
      GL20.glUseProgram(this.previousProgramId);
   }

   public int getUniformLocation(String str) {
      return this.uniformLocations.computeIntIfAbsent(str, obj -> GL20.glGetUniformLocation(this.programId, str));
   }

   public ShaderProgram(String str, String str2) {
      this.previousProgramId = 0;
      int n = GL20.glCreateShader(35633);
      int n2 = GL20.glCreateShader(35632);
      GL20.glShaderSource(n, str);
      GL20.glShaderSource(n2, str2);
      GL20.glCompileShader(n);
      GL20.glCompileShader(n2);
      if (GL20.glGetShaderi(n, 35713) != 1) {
         throw new RuntimeException("Failed to compile vertex shader: " + GL20.glGetShaderInfoLog(n, 2048));
      } else if (GL20.glGetShaderi(n2, 35713) != 1) {
         throw new RuntimeException("Failed to compile fragment shader: " + GL20.glGetShaderInfoLog(n2, 2048));
      } else {
         this.programId = GL20.glCreateProgram();
         GL20.glAttachShader(this.programId, n);
         GL20.glAttachShader(this.programId, n2);
         GL20.glLinkProgram(this.programId);
         if (GL20.glGetProgrami(this.programId, 35714) != 1) {
            throw new RuntimeException("Failed to link shader program: " + GL20.glGetShaderInfoLog(this.programId, 2048));
         } else {
            GL20.glDeleteShader(n);
            GL20.glDeleteShader(n2);
         }
      }
   }

   public void setUniform1i(String str, int n) {
      GL20.glUniform1i(this.getUniformLocation(str), n);
   }

   public void bindTextureUniform(String str, Framebuffer framebuffer) {
      this.setUniform1i(str, this.savedTextureBindings.size());
      GlStateManager.setActiveTexture(33984 + this.savedTextureBindings.size());
      this.savedTextureBindings.add(GL11.glGetInteger(32873));
      GlStateManager.bindTexture(framebuffer.framebufferTexture);
   }

   public void setUniform1fv(String str, FloatBuffer floatBuffer) {
      GL20.glUniform1(this.getUniformLocation(str), floatBuffer);
   }

   public void setUniform4f(String str, float f, float f2, float f3, float f4) {
      GL20.glUniform4f(this.getUniformLocation(str), f, f2, f3, f4);
   }

   public void bind() {
      this.previousProgramId = GL11.glGetInteger(35725);
      GL20.glUseProgram(this.programId);
   }

   public void setUniform2f(String str, float f, float f2) {
      GL20.glUniform2f(this.getUniformLocation(str), f, f2);
   }





}
