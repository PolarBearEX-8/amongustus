package client.render;

import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class AssetsRendersystemManager {
   public static String resolveText(String text) {
      try {
         return IOUtils.toString(Objects.requireNonNull(AssetsRendersystemManager.class.getResourceAsStream("/assets/rendersystem/" + text)));
      } catch (IOException ioException) {
         return null;
      }
   }
}
