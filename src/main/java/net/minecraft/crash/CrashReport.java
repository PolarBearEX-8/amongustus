package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.optifine.CrashReporter;
import net.minecraft.util.ReportedException;
import net.minecraft.world.gen.layer.IntCache;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrashReport {
   private static final Logger LOGGER = LogManager.getLogger();
   private final String description;
   private final Throwable cause;
   private final CrashReportCategory theReportCategory = new CrashReportCategory(this, "System Details");
   private final List crashReportSections = Lists.newArrayList();
   private File crashReportFile;
   private boolean firstCategoryInCrashReport = true;
   private StackTraceElement[] stacktrace = new StackTraceElement[0];
   private boolean reported = false;

   public Throwable getCrashCause() {
      return this.cause;
   }

   public static CrashReport makeCrashReport(Throwable t, String str) {
      CrashReport crashReport;
      if (t instanceof ReportedException) {
         crashReport = ((ReportedException)t).getCrashReport();
      } else {
         crashReport = new CrashReport(str, t);
      }

      return crashReport;
   }

   public CrashReport(String str, Throwable t) {
      this.description = str;
      this.cause = t;
      this.populateEnvironment();
   }

   public String getCompleteReport() {
      if (!this.reported) {
         this.reported = true;
         CrashReporter.onCrashReport(this, this.theReportCategory);
      }

      StringBuilder sb = new StringBuilder();
      sb.append("---- Minecraft Crash Report ----\n");
      sb.append("// ");
      sb.append(getWittyComment());
      sb.append("\n\n");
      sb.append("Time: ");
      sb.append(new SimpleDateFormat().format(new Date()));
      sb.append("\n");
      sb.append("Description: ");
      sb.append(this.description);
      sb.append("\n\n");
      sb.append(this.getCauseStackTraceOrString());
      sb.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");

      for (int i = 0; i < 87; i++) {
         sb.append("-");
      }

      sb.append("\n\n");
      this.getSectionsInStringBuilder(sb);
      return sb.toString();
   }

   public CrashReportCategory makeCategoryDepth(String str, int n) {
      CrashReportCategory crashReportCategory = new CrashReportCategory(this, str);
      if (this.firstCategoryInCrashReport) {
         int prunedStackTrace = crashReportCategory.getPrunedStackTrace(n);
         StackTraceElement[] stackTrace = this.cause.getStackTrace();
         StackTraceElement stackTraceElement = null;
         StackTraceElement stackTraceElement2 = null;
         int n2 = stackTrace.length - prunedStackTrace;
         if (n2 < 0) {
            System.out.println("Negative index in crash report handler (" + stackTrace.length + "/" + prunedStackTrace + ")");
         }

         if (0 <= n2 && n2 < stackTrace.length) {
            stackTraceElement = stackTrace[n2];
            if (stackTrace.length + 1 - prunedStackTrace < stackTrace.length) {
               stackTraceElement2 = stackTrace[stackTrace.length + 1 - prunedStackTrace];
            }
         }

         this.firstCategoryInCrashReport = crashReportCategory.firstTwoElementsOfStackTraceMatch(stackTraceElement, stackTraceElement2);
         if (prunedStackTrace > 0 && !this.crashReportSections.isEmpty()) {
            ((CrashReportCategory)this.crashReportSections.get(this.crashReportSections.size() - 1)).trimStackTraceEntriesFromBottom(prunedStackTrace);
         } else if (stackTrace.length >= prunedStackTrace && 0 <= n2 && n2 < stackTrace.length) {
            this.stacktrace = new StackTraceElement[n2];
            System.arraycopy(stackTrace, 0, this.stacktrace, 0, this.stacktrace.length);
         } else {
            this.firstCategoryInCrashReport = false;
         }
      }

      this.crashReportSections.add(crashReportCategory);
      return crashReportCategory;
   }

   private void populateEnvironment() {
      this.theReportCategory.addCrashSectionCallable("Minecraft Version", () -> "1.8.9");
      this.theReportCategory
         .addCrashSectionCallable(
            "Operating System", () -> System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version")
         );
      this.theReportCategory.addCrashSectionCallable("Java Version", () -> System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
      this.theReportCategory
         .addCrashSectionCallable(
            "Java VM Version",
            () -> System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor")
         );
      this.theReportCategory.addCrashSectionCallable("Memory", () -> {
         Runtime runtime = Runtime.getRuntime();
         long l = runtime.maxMemory();
         long l2 = runtime.totalMemory();
         long l3 = runtime.freeMemory();
         long l4 = l / 1024L / 1024L;
         long l5 = l2 / 1024L / 1024L;
         long l6 = l3 / 1024L / 1024L;
         return l3 + " bytes (" + l6 + " MB) / " + l2 + " bytes (" + l5 + " MB) up to " + l + " bytes (" + l4 + " MB)";
      });
      this.theReportCategory.addCrashSectionCallable("JVM Flags", () -> {
         List inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
         int n = 0;
         StringBuilder sb = new StringBuilder();

         for (String str : (Iterable<String>)(inputArguments)) {
            if (str.startsWith("-X")) {
               if (n++ > 0) {
                  sb.append(" ");
               }

               sb.append(str);
            }
         }

         return String.format("%d total; %s", n, sb);
      });
      this.theReportCategory.addCrashSectionCallable("IntCache", IntCache::getCacheSizes);
   }

   public File getFile() {
      return this.crashReportFile;
   }

   public String getCauseStackTraceOrString() {
      StringWriter stringWriter = null;
      PrintWriter writer = null;
      Throwable cause2 = this.cause;
      if (cause2.getMessage() == null) {
         if (cause2 instanceof NullPointerException) {
            cause2 = new NullPointerException(this.description);
         } else if (cause2 instanceof StackOverflowError) {
            cause2 = new StackOverflowError(this.description);
         } else if (cause2 instanceof OutOfMemoryError) {
            cause2 = new OutOfMemoryError(this.description);
         }

         cause2.setStackTrace(this.cause.getStackTrace());
      }

      String str = cause2.toString();

      try {
         stringWriter = new StringWriter();
         writer = new PrintWriter(stringWriter);
         cause2.printStackTrace(writer);
         str = stringWriter.toString();
      } finally {
         IOUtils.closeQuietly(stringWriter);
         IOUtils.closeQuietly(writer);
      }

      return str;
   }

   public CrashReportCategory makeCategory(String str) {
      return this.makeCategoryDepth(str, 1);
   }

   public CrashReportCategory getCategory() {
      return this.theReportCategory;
   }

   public String getDescription() {
      return this.description;
   }

   private static String getWittyComment() {
      String[] strings = new String[]{
         "Who set us up the TNT?",
         "Everything's going to plan. No, really, that was supposed to happen.",
         "Uh... Did I do that?",
         "Oops.",
         "Why did you do that?",
         "I feel sad now :(",
         "My bad.",
         "I'm sorry, Dave.",
         "I let you down. Sorry :(",
         "On the bright side, I bought you a teddy bear!",
         "Daisy, daisy...",
         "Oh - I know what I did wrong!",
         "Hey, that tickles! Hehehe!",
         "I blame Dinnerbone.",
         "You should try our sister game, Minceraft!",
         "Don't be sad. I'll do better next time, I promise!",
         "Don't be sad, have a hug! <3",
         "I just don't know what went wrong :(",
         "Shall we play a game?",
         "Quite honestly, I wouldn't worry myself about that.",
         "I bet Cylons wouldn't have this problem.",
         "Sorry :(",
         "Surprise! Haha. Well, this is awkward.",
         "Would you like a cupcake?",
         "Hi. I'm Minecraft, and I'm a crashaholic.",
         "Ooh. Shiny.",
         "This doesn't make any sense!",
         "Why is it breaking :(",
         "Don't do that.",
         "Ouch. That hurt :(",
         "You're mean.",
         "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]",
         "There are four lights!",
         "But it works on my machine."
      };

      try {
         return strings[(int)(System.nanoTime() % (long)strings.length)];
      } catch (Throwable e) {
         return "Witty comment unavailable :(";
      }
   }

   public boolean saveToFile(File file) {
      if (this.crashReportFile != null) {
         return false;
      } else {
         if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
         }

         try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(this.getCompleteReport());
            fileWriter.close();
            this.crashReportFile = file;
            return true;
         } catch (Throwable e) {
            LOGGER.error("Could not save crash report to " + file, e);
            return false;
         }
      }
   }

   public void getSectionsInStringBuilder(StringBuilder sb) {
      if ((this.stacktrace == null || this.stacktrace.length == 0) && !this.crashReportSections.isEmpty()) {
         this.stacktrace = (StackTraceElement[])ArrayUtils.subarray(((CrashReportCategory)this.crashReportSections.get(0)).getStackTrace(), 0, 1);
      }

      if (this.stacktrace != null && this.stacktrace.length > 0) {
         sb.append("-- Head --\n");
         sb.append("Stacktrace:\n");

         for (StackTraceElement stackTraceElement : this.stacktrace) {
            sb.append("\t").append("at ").append(stackTraceElement.toString());
            sb.append("\n");
         }

         sb.append("\n");
      }

      for (CrashReportCategory crashReportCategory : (Iterable<CrashReportCategory>)(this.crashReportSections)) {
         crashReportCategory.appendToStringBuilder(sb);
         sb.append("\n\n");
      }

      this.theReportCategory.appendToStringBuilder(sb);
   }

}
