import java.io.File;
import java.util.Arrays;
import net.augustus.utils.OSUtil;
import net.minecraft.client.main.Main;

public class Start {

   public static Object[] concat(Object[] array, Object[] array2) {
      Object[] array3 = Arrays.copyOf(array, array.length + array2.length);
      System.arraycopy(array2, 0, array3, array.length, array2.length);
      return array3;
   }

   private static String resolveAssetsDir() {
      if (new File("assets/indexes").isDirectory()) {
         return "assets";
      }
      if (new File("run/assets/indexes").isDirectory()) {
         return "run/assets";
      }
      try {
         File mcAssets = new File(OSUtil.detectOS().getMinecraftDirectory(), "assets");
         if (new File(mcAssets, "indexes").isDirectory()) {
            return mcAssets.getAbsolutePath();
         }
      } catch (Exception ignored) {
      }
      return "assets";
   }

   public static void main(String[] args) {
      Main.main(
         (String[])concat(new String[]{"--version", "1.8.9", "--accessToken", "0", "--assetsDir", resolveAssetsDir(), "--assetIndex", "1.8", "--userProperties", "{}"}, args)
      );
   }
}
