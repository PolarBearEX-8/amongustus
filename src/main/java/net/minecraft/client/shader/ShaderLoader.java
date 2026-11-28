package net.minecraft.client.shader;

import com.google.common.collect.Maps;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;

public class ShaderLoader {
   private final ShaderLoader.ShaderType shaderType;
   private final String shaderFilename;
   private int shader;
   private int shaderAttachCount = 0;

   public static ShaderLoader loadShader(IResourceManager resourceManager, ShaderLoader.ShaderType shaderType2, String str) throws IOException {
      ShaderLoader shaderLoader = (ShaderLoader)shaderType2.getLoadedShaders().get(str);
      if (shaderLoader == null) {
         ResourceLocation location = new ResourceLocation("shaders/program/" + str + shaderType2.getShaderExtension());
         byte[] bytes = toByteArray(new BufferedInputStream(resourceManager.getResource(location).getInputStream()));
         ByteBuffer buf = BufferUtils.createByteBuffer(bytes.length);
         buf.put(bytes);
         ((Buffer)buf).position(0);
         int n = OpenGlHelper.glCreateShader(shaderType2.getShaderMode());
         OpenGlHelper.glShaderSource(n, buf);
         OpenGlHelper.glCompileShader(n);
         if (OpenGlHelper.glGetShaderi(n, OpenGlHelper.GL_COMPILE_STATUS) == 0) {
            String str2 = StringUtils.trim(OpenGlHelper.glGetShaderInfoLog(n, 32768));
            JsonException ex = new JsonException("Couldn't compile " + shaderType2.getShaderName() + " program: " + str2);
            ex.setFilenameAndFlush(location.getResourcePath());
            throw ex;
         }

         shaderLoader = new ShaderLoader(shaderType2, n, str);
         shaderType2.getLoadedShaders().put(str, shaderLoader);
      }

      return shaderLoader;
   }

   public void attachShader(ShaderManager shaderManager) {
      this.shaderAttachCount++;
      OpenGlHelper.glAttachShader(shaderManager.getProgram(), this.shader);
   }

   public static byte[] toByteArray(BufferedInputStream bufferedInputStream) throws IOException {
      byte[] bytes;
      try {
         bytes = IOUtils.toByteArray(bufferedInputStream);
      } finally {
         bufferedInputStream.close();
      }

      return bytes;
   }

   private ShaderLoader(ShaderLoader.ShaderType shaderType2, int n, String str) {
      this.shaderType = shaderType2;
      this.shader = n;
      this.shaderFilename = str;
   }

   public String getShaderFilename() {
      return this.shaderFilename;
   }

   public void deleteShader(ShaderManager shaderManager) {
      this.shaderAttachCount--;
      if (this.shaderAttachCount <= 0) {
         OpenGlHelper.glDeleteShader(this.shader);
         this.shaderType.getLoadedShaders().remove(this.shaderFilename);
      }
   }


   public enum ShaderType {
      VERTEX("vertex", ".vsh", OpenGlHelper.GL_VERTEX_SHADER),
      FRAGMENT("fragment", ".fsh", OpenGlHelper.GL_FRAGMENT_SHADER);

   private final String shaderName;
      private final String shaderExtension;
      private final int shaderMode;
      private final Map loadedShaders = Maps.newHashMap();
   public int getShaderMode() {
         return this.shaderMode;
      }

      public String getShaderName() {
         return this.shaderName;
      }

      public String getShaderExtension() {
         return this.shaderExtension;
      }

      public Map getLoadedShaders() {
         return this.loadedShaders;
      }

      private ShaderType(String str, String str2, int n) {
         this.shaderName = str;
         this.shaderExtension = str2;
         this.shaderMode = n;
      }
   }
}
