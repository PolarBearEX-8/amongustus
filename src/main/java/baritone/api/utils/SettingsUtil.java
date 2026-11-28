package baritone.api.utils;

import baritone.api.BaritoneAPI;
import baritone.api.Settings;
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
   private static final Pattern SETTING_PATTERN = Pattern.compile("^(?<setting>[^ ]+) +(?<value>.+)");

   private static Path settingsByName(String str) {
      return Minecraft.getMinecraft().mcDataDir.toPath().resolve("baritone").resolve(str);
   }

   public static synchronized void save(Settings settings) {
      try (BufferedWriter writer = Files.newBufferedWriter(settingsByName("settings.txt"))) {
         for (Settings.Setting setting : (Iterable<Settings.Setting>)(modifiedSettings(settings))) {
            writer.write(settingToString(setting) + "\n");
         }
      } catch (Exception e) {
         Helper.HELPER.logDirect2("Exception thrown while saving Baritone settings!");
         e.printStackTrace();
      }
   }

   @Deprecated
   public static boolean javaOnlySetting(Settings.Setting setting) {
      return setting.isJavaOnly();
   }

   public static String settingDefaultToString(Settings.Setting setting) throws IllegalArgumentException {
      return settingValueToString(setting, setting.defaultValue);
   }

   public static String settingToString(Settings.Setting setting) throws IllegalStateException {
      return setting.isJavaOnly() ? setting.getName() : setting.getName() + " " + settingValueToString(setting);
   }

   private static void forEachLine(Path path, Consumer consumer) throws IOException {
      String str;
      try (BufferedReader reader = Files.newBufferedReader(path)) {
         while ((str = reader.readLine()) != null) {
            if (!str.isEmpty() && !isComment(str)) {
               consumer.accept(str);
            }
         }
      }
   }

   public static void parseAndApply(Settings settings, String str, String str2) throws IllegalStateException, NumberFormatException {
      Settings.Setting setting = (Settings.Setting)settings.byLowerName.get(str);
      if (setting == null) {
         throw new IllegalStateException("No setting by that name");
      } else {
         Class valueClass = setting.getValueClass();
         SettingParser parser = SettingParser.getParser(setting.getType());
         Object obj = parser.parse(new SettingParserContext(setting, null), str2);
         if (!valueClass.isInstance(obj)) {
            throw new IllegalStateException(parser + " parser returned incorrect type, expected " + valueClass + " got " + obj + " which is " + obj.getClass());
         } else {
            setting.value = obj;
         }
      }
   }

   public static List<Settings.Setting> modifiedSettings(Settings settings) {
      ArrayList list = new ArrayList();

      for (Settings.Setting setting : (Iterable<Settings.Setting>)(settings.allSettings)) {
         if (setting.value == null) {
            System.out.println("NULL SETTING?" + setting.getName());
         } else if (!setting.isJavaOnly() && setting.value != setting.defaultValue) {
            list.add(setting);
         }
      }

      return list;
   }

   public static String settingTypeToString(Settings.Setting setting) {
      return setting.getType().getTypeName().replaceAll("(?:\\w+\\.)+(\\w+)", "$1");
   }

   public static String maybeCensor(int n) {
      return (Boolean)BaritoneAPI.getSettings().censorCoordinates.value ? "<censored>" : Integer.toString(n);
   }

   public static String settingValueToString(Settings.Setting setting, Object obj) throws IllegalArgumentException {
      SettingParser parser = SettingParser.getParser(setting.getType());
      if (parser == null) {
         throw new IllegalStateException("Missing " + setting.getValueClass() + " " + setting.getName());
      } else {
         return parser.toString(new SettingParserContext(setting, null), obj);
      }
   }

   public static void readAndApply(Settings settings, String str) {
      try {
         forEachLine(settingsByName(str), obj -> {
            Matcher matcher = SETTING_PATTERN.matcher((CharSequence)obj);
            if (!matcher.matches()) {
               Helper.HELPER.logDirect2("Invalid syntax in setting file: " + obj);
            } else {
               String str2 = matcher.group("setting").toLowerCase();
               String str3 = matcher.group("value");

               try {
                  parseAndApply(settings, str2, str3);
               } catch (Exception e) {
                  Helper.HELPER.logDirect2("Unable to parse line " + obj);
                  e.printStackTrace();
               }
            }
         });
      } catch (NoSuchFileException e2) {
         Helper.HELPER.logDirect2("Baritone settings file not found, resetting.");
      } catch (Exception e3) {
         Helper.HELPER.logDirect2("Exception while reading Baritone settings, some settings may be reset to default values!");
         e3.printStackTrace();
      }
   }

   private static boolean isComment(String str) {
      return str.startsWith("#") || str.startsWith("//");
   }

   public static String settingValueToString(Settings.Setting setting) throws IllegalArgumentException {
      return settingValueToString(setting, setting.value);
   }


   public static class InnerEntry {
   }
}
