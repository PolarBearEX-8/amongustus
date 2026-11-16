package client.util;

import client.render.ShaderManager;
import java.io.IOException;
import net.minecraft.client.renderer.OpenGlHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AndFSManager {
   public static final Logger logger = LogManager.getLogger();
   public static AndFSManager andFSManager;

   public static AndFSManager getAndFSManager() {
      return andFSManager;
   }

   public static void updateState() {
      andFSManager = new AndFSManager();
   }

   public void applyShaderManager(ShaderManager shaderManager) throws IOException {
      shaderManager.getShadersProgramManager2().applyShaderManagerByShaderManager(shaderManager);
      shaderManager.getShadersProgramManager().applyShaderManagerByShaderManager(shaderManager);
      OpenGlHelper.applyIndex3(shaderManager.getCount2());
      if (OpenGlHelper.computeCount(shaderManager.getCount2(), OpenGlHelper.count9) == 0) {
         logger.warn(
            "Error encountered when linking program containing VS "
               + shaderManager.getShadersProgramManager().getLabelText()
               + " and FS "
               + shaderManager.getShadersProgramManager2().getLabelText()
               + ". Log output:"
         );
         logger.warn(OpenGlHelper.resolveText(shaderManager.getCount2(), 32768));
      }
   }

   public void applyShaderManagerByShaderManager(ShaderManager shaderManager) {
      shaderManager.getShadersProgramManager2().applyShaderManager(shaderManager);
      shaderManager.getShadersProgramManager().applyShaderManager(shaderManager);
      OpenGlHelper.applyIndex(shaderManager.getCount2());
   }

   public int getCount() throws FileNotFoundIOException {
      int count = OpenGlHelper.getCount3();
      if (count <= 0) {
         throw new FileNotFoundIOException("Could not create shader program (returned program ID " + count + ")");
      } else {
         return count;
      }
   }
}
