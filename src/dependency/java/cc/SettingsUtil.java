package cc;

import cc.baritone.utils.AStarPolicy;
import cc.baritone.utils.AuxiliaryStringEntry;
import cc.baritone.utils.DRNHandler;
import cc.baritone.utils.DepthFirstWatcher;
import cc.baritone.utils.SettingsTxtManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;

public class SettingsUtil {
   public static final String SETTINGS_DEFAULT_NAME = "settings.txt";
   public static final Pattern SETTING_PATTERN = Pattern.compile("^(?<setting>[^ ]+) +(?<value>.+)");

   public static synchronized void save(Settings settings) {
      try {
         boolean flag = false ;

         try (BufferedWriter writer = Files.newBufferedWriter(settingsByName("settings.txt"))) {
            flag = true;

            for (Object auxiliaryStringEntryObj : modifiedSettings(settings)) {
               AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)auxiliaryStringEntryObj;
               writer.write(settingToString(auxiliaryStringEntry) + "\n");
            }

            flag = false;
         }
      } catch (Exception exception) {
         DRNHandler.dRNHandler.applyText("Exception thrown while saving Baritone settings!");
         exception.printStackTrace();
      }
   }

   public static String settingTypeToString(AuxiliaryStringEntry tingTypeToString) {
      return tingTypeToString.get().getTypeName().replaceAll("(?:\\w+\\.)+(\\w+)", "$1");
   }

   public static String settingValueToString(AuxiliaryStringEntry auxiliaryStringEntry, Object entry) throws IllegalArgumentException {
      DepthFirstWatcher depthFirstWatcher = DepthFirstWatcher.resolveDepthFirstWatcher(auxiliaryStringEntry.get());
      if (depthFirstWatcher == null) {
         throw new IllegalStateException("Missing " + auxiliaryStringEntry.getClassAsClass() + " " + auxiliaryStringEntry.getLabelText());
      } else {
         return depthFirstWatcher.apply(new AStarPolicy(auxiliaryStringEntry, null), entry);
      }
   }

   public static String settingValueToString(AuxiliaryStringEntry tingValueToString) throws IllegalArgumentException {
      return settingValueToString(tingValueToString, tingValueToString.dataRef);
   }

   private static void lambda$readAndApply$0(Settings settings, String text) {
      Matcher matcher = SETTING_PATTERN.matcher(text);
      if (!matcher.matches()) {
         DRNHandler.dRNHandler.applyText("Invalid syntax in setting file: " + text);
      } else {
         String text2 = matcher.group("setting").toLowerCase();
         String text3 = matcher.group("value");

         try {
            parseAndApply(settings, text2, text3);
         } catch (Exception exception) {
            DRNHandler.dRNHandler.applyText("Unable to parse line " + text);
            exception.printStackTrace();
         }
      }
   }

   public static String settingDefaultToString(AuxiliaryStringEntry tingDefaultToString) throws IllegalArgumentException {
      return settingValueToString(tingDefaultToString, tingDefaultToString.dataRef2);
   }

   public static void readAndApply(Settings settings, String text) {
      try {
         forEachLine(settingsByName(text), line -> lambda$readAndApply$0(settings, (String)line));
      } catch (NoSuchFileException noSuchFileException) {
         DRNHandler.dRNHandler.applyText("Baritone settings file not found, resetting.");
      } catch (Exception exception) {
         DRNHandler.dRNHandler.applyText("Exception while reading Baritone settings, some settings may be reset to default values!");
         exception.printStackTrace();
      }
   }

   private static void forEachLine(Path path, Consumer consumer) throws IOException {
      BufferedReader reader = Files.newBufferedReader(path);
      Throwable throwable = null;

      while (true) {
         boolean flag = false ;

         try {
            flag = true;
            String text;
            if ((text = reader.readLine()) == null) {
               flag = false;
               break;
            }

            if (!text.isEmpty() && !isComment(text)) {
               consumer.accept(text);
            }
         } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
         } finally {
            if (flag) {
               if (reader != null) {
                  if (throwable != null) {
                     try {
                        reader.close();
                     } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                     }
                  } else {
                     reader.close();
                  }
               }
            }
         }
      }

      if (reader != null) {
         reader.close();
      }
   }

   private static Path settingsByName(String tingsByName) {
      return Minecraft.getMinecraft().mcDataDir.toPath().resolve("baritone").resolve(tingsByName);
   }

   public static String settingToString(AuxiliaryStringEntry tingToString) throws IllegalStateException {
      return tingToString.isEnabled() ? tingToString.getLabelText() : tingToString.getLabelText() + " " + settingValueToString(tingToString);
   }

   public static List modifiedSettings(Settings settings) {
      ArrayList list = new ArrayList();

      for (Object auxiliaryStringEntryObj : settings.allSettings) {
         AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)auxiliaryStringEntryObj;
         if (auxiliaryStringEntry.dataRef == null) {
            System.out.println("NULL SETTING?" + auxiliaryStringEntry.getLabelText());
         } else if (!auxiliaryStringEntry.isEnabled() && auxiliaryStringEntry.dataRef != auxiliaryStringEntry.dataRef2) {
            list.add(auxiliaryStringEntry);
         }
      }

      return list;
   }

   public static void parseAndApply(Settings settings, String text, String text2) throws IllegalStateException, NumberFormatException {
      AuxiliaryStringEntry auxiliaryStringEntry = (AuxiliaryStringEntry)settings.byLowerName.get(text);
      if (auxiliaryStringEntry == null) {
         throw new IllegalStateException("No setting by that name");
      } else {
         Class classAsClass = auxiliaryStringEntry.getClassAsClass();
         DepthFirstWatcher depthFirstWatcher = DepthFirstWatcher.resolveDepthFirstWatcher(auxiliaryStringEntry.get());
         Object entry = depthFirstWatcher.resolveTarget(new AStarPolicy(auxiliaryStringEntry, null), text2);
         if (!classAsClass.isInstance(entry)) {
            throw new IllegalStateException(depthFirstWatcher + " parser returned incorrect type, expected " + classAsClass + " got " + entry + " which is " + entry.getClass());
         } else {
            auxiliaryStringEntry.dataRef = entry;
         }
      }
   }

   public static String maybeCensor(int count) {
      return (Boolean)SettingsTxtManager.getSettings().censorCoordinates.dataRef ? "<censored>" : Integer.toString(count);
   }

   private static boolean isComment(String text) {
      return text.startsWith("#") || text.startsWith("//");
   }

   @Deprecated
   public static boolean javaOnlySetting(AuxiliaryStringEntry auxiliaryStringEntry) {
      return auxiliaryStringEntry.isEnabled();
   }
}
