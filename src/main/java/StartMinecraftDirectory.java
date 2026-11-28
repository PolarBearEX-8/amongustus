import java.io.File;
import java.util.Arrays;
import net.augustus.utils.OSUtil;
import net.minecraft.client.main.Main;

public class StartMinecraftDirectory {

   public static Object[] concat(Object[] array, Object[] array2) {
      Object[] array3 = Arrays.copyOf(array, array.length + array2.length);
      System.arraycopy(array2, 0, array3, array.length, array2.length);
      return array3;
   }

   public static void main(String[] args) {
      File file = new File(OSUtil.detectOS().getMinecraftDirectory());
      File file2 = new File(file + File.separator + "assets");
      Main.main(
         (String[])concat(
            new String[]{
               "--version",
               "1.8.9",
               "--accessToken",
               "0",
               "--assetsDir",
               file2.toString(),
               "--gameDir",
               file.toString(),
               "--assetIndex",
               "1.8",
               "--userProperties",
               "{}"
            },
            args
         )
      );
   }
}
