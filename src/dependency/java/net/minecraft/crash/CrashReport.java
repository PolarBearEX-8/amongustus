package net.minecraft.crash;

import client.util.CacheManager;
import client.util.RotationCollector;
import client.util.SecondaryRuntimeExceptionAdapter;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrashReport {
   public static final Logger logger = LogManager.getLogger();
   public final String labelText;
   public final Throwable throwable;
   public final CrashReportCategory crashReportCategory = new CrashReportCategory(this, "System Details");
   public final List list = Lists.newArrayList();
   public File file;
   public boolean enabled2 = true;
   public StackTraceElement[] stackTraceElementArray = new StackTraceElement[0];
   public boolean enabled = false;

   public CrashReportCategory resolveCrashReportCategory(String text) {
      return this.resolveCrashReportCategory(text, 1);
   }

   private static String getText5() throws Exception {
      return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
   }

   private static String getText2() throws Exception {
      return "1.8.9";
   }

   public File getFile() {
      return this.file;
   }

   public CrashReportCategory resolveCrashReportCategory(String text, int count) {
      CrashReportCategory crashReportCategory2 = new CrashReportCategory(this, text);
      if (this.enabled2) {
         int count2 = crashReportCategory2.computeCount(count);
         StackTraceElement[] stackTrace = this.throwable.getStackTrace();
         StackTraceElement stackTraceElement = null;
         StackTraceElement stackTraceElement2 = null;
         int count3 = stackTrace.length - count2;
         if (count3 < 0) {
            System.out.println("Negative index in crash report handler (" + stackTrace.length + "/" + count2 + ")");
         }

         if (0 <= count3 && count3 < stackTrace.length) {
            stackTraceElement = stackTrace[count3];
            if (stackTrace.length + 1 - count2 < stackTrace.length) {
               stackTraceElement2 = stackTrace[stackTrace.length + 1 - count2];
            }
         }

         this.enabled2 = crashReportCategory2.matchesStackTraceElement(stackTraceElement, stackTraceElement2);
         if (count2 > 0 && !this.list.isEmpty()) {
            ((CrashReportCategory)this.list.get(this.list.size() - 1)).applyIndex(count2);
         } else if (stackTrace.length >= count2 && 0 <= count3 && count3 < stackTrace.length) {
            this.stackTraceElementArray = new StackTraceElement[count3];
            System.arraycopy(stackTrace, 0, this.stackTraceElementArray, 0, this.stackTraceElementArray.length);
         } else {
            this.enabled2 = false;
         }
      }

      this.list.add(crashReportCategory2);
      return crashReportCategory2;
   }

   private static String getTextAsText() throws Exception {
      List inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
      int count = 0;
      StringBuilder builder = new StringBuilder();

      for (String text : (Iterable<String>)(Iterable<?>)inputArguments) {
         if (text.startsWith("-X")) {
            if (count++ > 0) {
               builder.append(" ");
            }

            builder.append(text);
         }
      }

      return String.format("%d total; %s", count, builder);
   }

   public String getText() {
      if (!this.enabled) {
         this.enabled = true;
         RotationCollector.applyCrashReport(this, this.crashReportCategory);
      }

      StringBuilder builder = new StringBuilder();
      builder.append("---- Minecraft Crash Report ----\n");
      builder.append("// ");
      builder.append(getText8());
      builder.append("\n\n");
      builder.append("Time: ");
      builder.append(new SimpleDateFormat().format(new Date()));
      builder.append("\n");
      builder.append("Description: ");
      builder.append(this.labelText);
      builder.append("\n\n");
      builder.append(this.getText4());
      builder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");

      for (int index = 0; index < 87; index++) {
         builder.append("-");
      }

      builder.append("\n\n");
      this.applyStringBuilder(builder);
      return builder.toString();
   }

   public String getLabelText() {
      return this.labelText;
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   private static String getText7() throws Exception {
      return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
   }

   private static String getText8() {
      String[] textArray = new String[]{
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
         return textArray[(int)(System.nanoTime() % (long)textArray.length)];
      } catch (Throwable throwable2) {
         return "Witty comment unavailable :(";
      }
   }

   public void applyStringBuilder(StringBuilder builder) {
      if ((this.stackTraceElementArray == null || this.stackTraceElementArray.length == 0) && !this.list.isEmpty()) {
         this.stackTraceElementArray = (StackTraceElement[])ArrayUtils.subarray(((CrashReportCategory)this.list.get(0)).getStackTraceElementArray(), 0, 1);
      }

      if (this.stackTraceElementArray != null && this.stackTraceElementArray.length > 0) {
         builder.append("-- Head --\n");
         builder.append("Stacktrace:\n");

         for (StackTraceElement stackTraceElement : this.stackTraceElementArray) {
            builder.append("\t").append("at ").append(stackTraceElement.toString());
            builder.append("\n");
         }

         builder.append("\n");
      }

      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((CrashReportCategory)iterator.next()).applyStringBuilder(builder);
         builder.append("\n\n");
      }

      this.crashReportCategory.applyStringBuilder(builder);
   }

   public String getText4() {
      StringWriter stringWriter = null;
      PrintWriter printWriter = null;
      Throwable throwable2 = this.throwable;
      if (throwable2.getMessage() == null) {
         if (throwable2 instanceof NullPointerException) {
            throwable2 = new NullPointerException(this.labelText);
         } else if (throwable2 instanceof StackOverflowError) {
            throwable2 = new StackOverflowError(this.labelText);
         } else if (throwable2 instanceof OutOfMemoryError) {
            throwable2 = new OutOfMemoryError(this.labelText);
         }

         throwable2.setStackTrace(this.throwable.getStackTrace());
      }

      throwable2.toString();

      String text;
      try {
         stringWriter = new StringWriter();
         printWriter = new PrintWriter(stringWriter);
         throwable2.printStackTrace(printWriter);
         text = stringWriter.toString();
      } finally {
         IOUtils.closeQuietly(stringWriter);
         IOUtils.closeQuietly(printWriter);
      }

      return text;
   }

   public CrashReportCategory getCrashReportCategory() {
      return this.crashReportCategory;
   }

   public static CrashReport resolveCrashReport(Throwable throwable2, String text) {
      CrashReport crashReport;
      if (throwable2 instanceof SecondaryRuntimeExceptionAdapter) {
         crashReport = ((SecondaryRuntimeExceptionAdapter)throwable2).getCrashReport();
      } else {
         crashReport = new CrashReport(text, throwable2);
      }

      return crashReport;
   }

   private static String getText6() throws Exception {
      Runtime runtime = Runtime.getRuntime();
      long value = runtime.maxMemory();
      long value2 = runtime.totalMemory();
      long value3 = runtime.freeMemory();
      long value4 = value / 1024L / 1024L;
      long value5 = value2 / 1024L / 1024L;
      long value6 = value3 / 1024L / 1024L;
      return value3 + " bytes (" + value6 + " MB) / " + value2 + " bytes (" + value5 + " MB) up to " + value + " bytes (" + value4 + " MB)";
   }

   private static String getText3() throws Exception {
      return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
   }

   public boolean matchesFile(File file2) {
      if (this.file != null) {
         return false;
      } else {
         if (file2.getParentFile() != null) {
            file2.getParentFile().mkdirs();
         }

         try {
            FileWriter writer = new FileWriter(file2);
            writer.write(this.getText());
            writer.close();
            this.file = file2;
            return true;
         } catch (Throwable throwable2) {
            logger.error("Could not save crash report to " + file2, throwable2);
            return false;
         }
      }
   }

   private void updateState() {
      this.crashReportCategory.applyText("Minecraft Version", CrashReport::getText2);
      this.crashReportCategory.applyText("Operating System", CrashReport::getText3);
      this.crashReportCategory.applyText("Java Version", CrashReport::getText7);
      this.crashReportCategory.applyText("Java VM Version", CrashReport::getText5);
      this.crashReportCategory.applyText("Memory", CrashReport::getText6);
      this.crashReportCategory.applyText("JVM Flags", CrashReport::getTextAsText);
      this.crashReportCategory.applyText("IntCache", CacheManager::getText);
   }

   public CrashReport(String text, Throwable throwable2) {
      this.labelText = text;
      this.throwable = throwable2;
      this.updateState();
   }
}
