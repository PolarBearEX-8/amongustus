package cc.baritone.utils;

import java.io.PrintWriter;

public class JlcConvertionHelper {
   public static void main(String[] textArray) {
      System.currentTimeMillis();
      String[] textArray2 = new String[textArray.length + 1];
      textArray2[0] = "jlc";

      for (int index = 0; index < textArray.length; index++) {
         textArray2[index + 1] = textArray[index];
      }

      RuntimeComponentBridge runtimeComponentBridge = new RuntimeComponentBridge();
      if (!runtimeComponentBridge.matchesTextArray(textArray2)) {
         System.exit(1);
      }

      OutputBuffersController outputBuffersController = new OutputBuffersController();
      int count = runtimeComponentBridge.enabled ? runtimeComponentBridge.count : 0;
      FileReadThrowableService fileReadThrowableService = new FileReadThrowableService(new PrintWriter(System.out, true), count);

      try {
         outputBuffersController.applyText(runtimeComponentBridge.labelText, runtimeComponentBridge.labelText2, fileReadThrowableService);
      } catch (AuxiliaryExceptionAdapter exception) {
         System.err.println("Convertion failure: " + exception);
      }

      System.exit(0);
   }
}
