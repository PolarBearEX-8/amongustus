package net.minecraft.client.shader;

import java.io.IOException;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.util.JsonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderLinkHelper {
   private static final Logger LOGGER = LogManager.getLogger();
   private static ShaderLinkHelper staticShaderLinkHelper;

   public int createProgram() throws JsonException {
      int n = OpenGlHelper.glCreateProgram();
      if (n <= 0) {
         throw new JsonException("Could not create shader program (returned program ID " + n + ")");
      } else {
         return n;
      }
   }

   public static void setNewStaticShaderLinkHelper() {
      staticShaderLinkHelper = new ShaderLinkHelper();
   }

   public void linkProgram(ShaderManager shaderManager) throws IOException {
      shaderManager.getFragmentShaderLoader().attachShader(shaderManager);
      shaderManager.getVertexShaderLoader().attachShader(shaderManager);
      OpenGlHelper.glLinkProgram(shaderManager.getProgram());
      if (OpenGlHelper.glGetProgrami(shaderManager.getProgram(), OpenGlHelper.GL_LINK_STATUS) == 0) {
         LOGGER.warn(
            "Error encountered when linking program containing VS "
               + shaderManager.getVertexShaderLoader().getShaderFilename()
               + " and FS "
               + shaderManager.getFragmentShaderLoader().getShaderFilename()
               + ". Log output:"
         );
         LOGGER.warn(OpenGlHelper.glGetProgramInfoLog(shaderManager.getProgram(), 32768));
      }
   }

   public static ShaderLinkHelper getStaticShaderLinkHelper() {
      return staticShaderLinkHelper;
   }

   public void deleteShader(ShaderManager shaderManager) {
      shaderManager.getFragmentShaderLoader().deleteShader(shaderManager);
      shaderManager.getVertexShaderLoader().deleteShader(shaderManager);
      OpenGlHelper.glDeleteProgram(shaderManager.getProgram());
   }
}
