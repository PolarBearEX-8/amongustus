package client.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class IntListEntry {
   public final IntList intList;
   public final Object2IntMap object2IntMap = new Object2IntOpenHashMap();
   public int count2;
   public final int count;

   public void applyText(String text, float factor) {
      GL20.glUniform1f(this.computeIntIfAbsent(text), factor);
   }

   public void updateState2() {
      this.count2 = GL11.glGetInteger(35725);
      GL20.glUseProgram(this.count);
   }

   public void applyText(String text, int count3) {
      GL20.glUniform1i(this.computeIntIfAbsent(text), count3);
   }

   public void updateState() {
      for (int index = 0; index < this.intList.size(); index++) {
         GlStateManager.enableState5(33984 + index);
         GlStateManager.setActiveTexture(this.intList.getInt(index));
      }

      this.intList.clear();
      GlStateManager.enableState5(33984);
      GL20.glUseProgram(this.count2);
   }

   public void applyFloat(float posX, float posX2, float posY, float posZ) {
      GL11.glBegin(7);
      GL11.glVertex2f(posX, posZ);
      GL11.glVertex2f(posY, posZ);
      GL11.glVertex2f(posY, posX2);
      GL11.glVertex2f(posX, posX2);
      GL11.glEnd();
   }

   public void applyText(String text, float factor, float factor2) {
      GL20.glUniform2f(this.computeIntIfAbsent(text), factor, factor2);
   }

   public void applyText(String text, FloatBuffer buffer) {
      GL20.glUniform1(this.computeIntIfAbsent(text), buffer);
   }

   private int glGetUniformLocation(String text, String text2) {
      return GL20.glGetUniformLocation(this.count, text);
   }

   public int computeIntIfAbsent(String text) {
      return this.object2IntMap.computeIntIfAbsent(text, key -> this.glGetUniformLocation(text, (String)key));
   }

   public void applyText(char character, String text, int count3, int count4, Framebuffer framebuffer) {
      int size = this.intList.size();
      int count8 = size;
      this.applyText(text, count8);
      GlStateManager.enableState5(33984 + this.intList.size());
      this.intList.add(GL11.glGetInteger(32873));
      GlStateManager.setActiveTexture(framebuffer.indexOffset);
   }

   public IntListEntry(String text, String text2) {
      this.intList = new IntArrayList();
      this.count2 = 0;
      int count3 = GL20.glCreateShader(35633);
      int count4 = GL20.glCreateShader(35632);
      GL20.glShaderSource(count3, text);
      GL20.glShaderSource(count4, text2);
      GL20.glCompileShader(count3);
      GL20.glCompileShader(count4);
      if (GL20.glGetShaderi(count3, 35713) != 1) {
         throw new RuntimeException("Failed to compile vertex shader: " + GL20.glGetShaderInfoLog(count3, 2048));
      } else if (GL20.glGetShaderi(count4, 35713) != 1) {
         throw new RuntimeException("Failed to compile fragment shader: " + GL20.glGetShaderInfoLog(count4, 2048));
      } else {
         this.count = GL20.glCreateProgram();
         GL20.glAttachShader(this.count, count3);
         GL20.glAttachShader(this.count, count4);
         GL20.glLinkProgram(this.count);
         if (GL20.glGetProgrami(this.count, 35714) != 1) {
            throw new RuntimeException("Failed to link shader program: " + GL20.glGetShaderInfoLog(this.count, 2048));
         } else {
            GL20.glDeleteShader(count3);
            GL20.glDeleteShader(count4);
         }
      }
   }
}
