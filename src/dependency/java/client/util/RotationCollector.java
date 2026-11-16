package client.util;

import client.setting.GameSettings;
import java.util.HashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;

public class RotationCollector {
   private static String resolveTextByCrashReport(CrashReport crashReport) {
      StringBuffer builder = new StringBuffer();
      builder.append("OptiFineVersion: " + BuildManager.getText2() + "\n");
      builder.append("Summary: " + resolveText(crashReport) + "\n");
      builder.append("\n");
      builder.append(crashReport.getText());
      builder.append("\n");
      return builder.toString();
   }

   private static String resolveText(CrashReport crashReport) {
      Throwable throwable = crashReport.getThrowable();
      if (throwable == null) {
         return "Unknown";
      } else {
         StackTraceElement[] stackTrace = throwable.getStackTrace();
         String text = "unknown";
         if (stackTrace.length > 0) {
            text = stackTrace[0].toString().trim();
         }

         return throwable.getClass().getName() + ": " + throwable.getMessage() + " (" + crashReport.getLabelText() + ") [" + text + "]";
      }
   }

   public static void applyCrashReportCategory(CrashReportCategory crashReportCategory) {
      crashReportCategory.add("OptiFine Version", BuildManager.getText2());
      crashReportCategory.add("OptiFine Build", BuildManager.getText4());
      if (BuildManager.getGameSettings() != null) {
         crashReportCategory.add("Render Distance Chunks", "" + BuildManager.getCount7());
         crashReportCategory.add("Mipmaps", "" + BuildManager.getCount8());
         crashReportCategory.add("Anisotropic Filtering", "" + BuildManager.getCount());
         crashReportCategory.add("Antialiasing", "" + BuildManager.getCount3());
         crashReportCategory.add("Multitexture", "" + BuildManager.isActive46());
      }

      crashReportCategory.add("Shaders", "" + PreEndHandManager.getText());
      crashReportCategory.add("OpenGlVersion", "" + BuildManager.labelText5);
      crashReportCategory.add("OpenGlRenderer", "" + BuildManager.labelText);
      crashReportCategory.add("OpenGlVendor", "" + BuildManager.labelText6);
      crashReportCategory.add("CpuCount", "" + BuildManager.getCount5());
   }

   public static void applyCrashReport(CrashReport crashReport, CrashReportCategory crashReportCategory) {
      try {
         Throwable throwable = crashReport.getThrowable();
         if (throwable == null) {
            return;
         }

         if (throwable.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         if (throwable.getClass() == Throwable.class) {
            return;
         }

         applyCrashReportCategory(crashReportCategory);
         GameSettings gameSettings = BuildManager.getGameSettings();
         if (gameSettings == null) {
            return;
         }

         if (!gameSettings.state115) {
            return;
         }

         String text = "http://optifine.net/crashReport";
         byte[] bytes = resolveTextByCrashReport(crashReport).getBytes("ASCII");
         ThrowableThrowableCallback throwableThrowableCallback = new ThrowableThrowableCallback();
         HashMap map = new HashMap();
         map.put("OF-Version", BuildManager.getText2());
         map.put("OF-Summary", resolveText(crashReport));
         ThrowableCallbackThread throwableCallbackThread = new ThrowableCallbackThread(text, map, bytes, throwableThrowableCallback);
         throwableCallbackThread.setPriority(10);
         throwableCallbackThread.start();
         Thread.sleep(1000L);
      } catch (Exception exception) {
         BuildManager.applyText4(exception.getClass().getName() + ": " + exception.getMessage());
      }
   }
}
