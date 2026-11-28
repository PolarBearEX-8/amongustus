package net.augustus.utils.render;

import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class RenderSystemAssetLoader {
   public static String loadAssetString(String str) {
      try {
         return IOUtils.toString(Objects.requireNonNull(RenderSystemAssetLoader.class.getResourceAsStream("/assets/rendersystem/" + str)));
      } catch (IOException e) {
         return null;
      }
   }
}
