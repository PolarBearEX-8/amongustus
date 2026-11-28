package net.augustus.utils.shader;

import net.augustus.Augustus;
import net.augustus.utils.render.GlStateSaver;
import net.augustus.utils.render.RenderSystemAssetLoader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import net.augustus.utils.interfaces.MC;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Mouse;

public class BackgroundShader implements MC {
   private ShaderProgram shaderProgram;
   private final String vertexShaderSource;
   private long startTimeMs;
   private String name;
   private boolean selected;
   private final GlStateSaver glStateSaver = GlStateSaver.create();

   public String loadResourceSource(String str) {
      try {
         return IOUtils.toString(Objects.requireNonNull(RenderSystemAssetLoader.class.getResourceAsStream(str)));
      } catch (IOException e) {
         return null;
      }
   }

   public void render(float f) {
      if (this.shaderProgram != null) {
         this.shaderProgram.bind();
         float f2 = (float)(System.currentTimeMillis() - this.startTimeMs) / f;
         String str = "time";
         this.shaderProgram.setUniform1f(str, f2);
         String str2 = "timeDelta";
         this.shaderProgram.setUniform1f(str2, f2);
         if (Augustus.getInstance().getModuleManager().clientSettings.mouseInputsOnBackgrounds.getValue()) {
            ShaderProgram shaderProgram2 = this.shaderProgram;
            float f3 = (float)(-Mouse.getY());
            float x = (float)Mouse.getX();
            shaderProgram2.setUniform2f("mouse", x, f3);
         }

         float displayWidth = (float)mc.displayWidth;
         float displayHeight = (float)mc.displayHeight;
         String str4 = "resolution";
         this.shaderProgram.setUniform2f(str4, displayWidth, displayHeight);
         this.glStateSaver.setAlpha(false);
         float displayWidth2 = (float)mc.displayWidth;
         float displayHeight2 = (float)mc.displayHeight;
         float f6 = 0.0F;
         float f7 = 0.0F;
         this.shaderProgram.drawQuad(f7, f6, displayWidth2, displayHeight2);
         this.glStateSaver.restoreAlpha();
         this.shaderProgram.unbind();
      }
   }

   public void loadFromFile(String str, String str2) {
      if (this.shaderProgram == null) {
         try {
            this.shaderProgram = new ShaderProgram(this.vertexShaderSource, this.readFileSource(str));
         } catch (RuntimeException e) {
         }

         this.startTimeMs = System.currentTimeMillis();
      }

      this.name = str2;
   }

   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setName(String str) {
      this.name = str;
   }

   public String readFileSource(String str) {

      try {
         StringBuilder sb = new StringBuilder();
         BufferedReader reader = new BufferedReader(new FileReader(str));

         String str2;
         while ((str2 = reader.readLine()) != null) {
            sb.append(str2).append('\n');
         }

         reader.close();
         return sb.toString();
      } catch (IOException e) {
         System.err.println("Fragment shader was not load properly");
         return null;
      }
   }

   public void loadFromResource(String str) {
      if (this.shaderProgram == null) {
         this.shaderProgram = new ShaderProgram(this.vertexShaderSource, this.loadResourceSource(str));
         this.startTimeMs = System.currentTimeMillis();
      }
   }

   public BackgroundShader() {
      this.vertexShaderSource = this.loadResourceSource("/assets/augustus/shaders/shader.vert");
   }


}
