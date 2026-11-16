import client.setting.RenderProperty;
import client.util.FovEvaluator;
import java.io.File;
import java.util.Arrays;

public class StartMinecraftDirectory {
   public static Object[] concat(Object[] entryArray, Object[] entryArray2) {
      Object[] entryArray3 = Arrays.copyOf(entryArray, entryArray.length + entryArray2.length);
      System.arraycopy(entryArray2, 0, entryArray3, entryArray.length, entryArray2.length);
      return entryArray3;
   }

   public static void main(String[] textArray) {
      File file = new File(RenderProperty.getRenderProperty().getText());
      File file2 = new File(file + File.separator + "assets");
      FovEvaluator.main(
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
            textArray
         )
      );
   }
}
