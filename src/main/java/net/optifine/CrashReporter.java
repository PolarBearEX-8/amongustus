package net.optifine;

import java.util.HashMap;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.optifine.config.Config;
import net.optifine.http.FileUploadThread;
import net.optifine.shaders.Shaders;

public class CrashReporter {

   private static String makeReport(CrashReport crashReport) {
      StringBuffer sb = new StringBuffer();
      sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
      sb.append("Summary: " + makeSummary(crashReport) + "\n");
      sb.append("\n");
      sb.append(crashReport.getCompleteReport());
      sb.append("\n");
      return sb.toString();
   }

   public static void extendCrashReport(CrashReportCategory crashReportCategory) {
      crashReportCategory.addCrashSection("OptiFine Version", Config.getVersion());
      crashReportCategory.addCrashSection("OptiFine Build", Config.getBuild());
      if (Config.getGameSettings() != null) {
         crashReportCategory.addCrashSection("Render Distance Chunks", "" + Config.getChunkViewDistance());
         crashReportCategory.addCrashSection("Mipmaps", "" + Config.getMipmapLevels());
         crashReportCategory.addCrashSection("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
         crashReportCategory.addCrashSection("Antialiasing", "" + Config.getAntialiasingLevel());
         crashReportCategory.addCrashSection("Multitexture", "" + Config.isMultiTexture());
      }

      crashReportCategory.addCrashSection("Shaders", "" + Shaders.getShaderPackName());
      crashReportCategory.addCrashSection("OpenGlVersion", "" + Config.openGlVersion);
      crashReportCategory.addCrashSection("OpenGlRenderer", "" + Config.openGlRenderer);
      crashReportCategory.addCrashSection("OpenGlVendor", "" + Config.openGlVendor);
      crashReportCategory.addCrashSection("CpuCount", "" + Config.getAvailableProcessors());
   }

   public static void onCrashReport(CrashReport crashReport, CrashReportCategory crashReportCategory) {
      try {
         Throwable crashCause = crashReport.getCrashCause();
         if (crashCause == null) {
            return;
         }

         if (crashCause.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         if (crashCause.getClass() == Throwable.class) {
            return;
         }

         extendCrashReport(crashReportCategory);
         GameSettings gameSettings = Config.getGameSettings();
         if (gameSettings == null) {
            return;
         }

         if (!gameSettings.snooperEnabled) {
            return;
         }

         String str = "http://optifine.net/crashReport";
         byte[] bytes = makeReport(crashReport).getBytes("ASCII");
         CrashReportUploadListener listener = new CrashReportUploadListener();
         HashMap map = new HashMap();
         map.put("OF-Version", Config.getVersion());
         map.put("OF-Summary", makeSummary(crashReport));
         FileUploadThread fileUploadThread = new FileUploadThread(str, map, bytes, listener);
         fileUploadThread.setPriority(10);
         fileUploadThread.start();
         Thread.sleep(1000L);
      } catch (Exception e) {
         Config.dbg(e.getClass().getName() + ": " + e.getMessage());
      }
   }

   private static String makeSummary(CrashReport crashReport) {
      Throwable crashCause = crashReport.getCrashCause();
      if (crashCause == null) {
         return "Unknown";
      } else {
         StackTraceElement[] stackTrace = crashCause.getStackTrace();
         String str = "unknown";
         if (stackTrace.length > 0) {
            str = stackTrace[0].toString().trim();
         }

         return crashCause.getClass().getName() + ": " + crashCause.getMessage() + " (" + crashReport.getDescription() + ") [" + str + "]";
      }
   }
}
