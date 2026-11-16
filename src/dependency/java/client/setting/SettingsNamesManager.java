package client.setting;

import client.Client;
import client.ClientGlobals;
import client.util.ConfigManager;
import client.util.OnlineConfigsManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class SettingsNamesManager {
   public static String labelText = OnlineConfigsManager.text + "/settings/settingsnames/";

   private String resolveText(String[] textArray) {
      StringBuilder builder = new StringBuilder();

      for (int index = 1; index < textArray.length; index++) {
         builder.append(textArray[index]);
         if (index > 1 && index < textArray.length - 1) {
            builder.append("::");
         }
      }

      return builder.deleteCharAt(builder.length() - 1).toString();
   }

   public String[] getStringArray() {
      ArrayList list = new ArrayList();
      File file = new File(labelText);
      ConfigManager.applyString2(labelText);
      if (!new File(labelText + "English_New.txt").exists()) {
         String defaultPath = Client.getClient().getString4().toLowerCase() + "/settings/settingsnames";
         ConfigManager.applyString("assets/augustus/English_New.txt", defaultPath, true);
      }
      File[] fileArray = file.listFiles();
      list.add("English_Default");
      if (fileArray != null) {
         int length = fileArray.length;

         for (int index = 0; index < length; index++) {
            String name = fileArray[index].getName();
            if (name.endsWith(".txt")) {
               String text = name.substring(0, Math.max(0, name.length() - 4));
               list.add(text);
            }
         }
      }

      return ((String[])list.toArray(new String[0]));
   }

   public void updateState() {
      ConfigManager.applyString2(labelText);
      String text = Client.getClient().getString4().toLowerCase() + "/settings/settingsnames";
      ConfigManager.applyString("assets/augustus/English_New.txt", text, false);

      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(labelText + "English_New.txt"));
         Throwable throwable = null;
         boolean flag = false ;

         try {
            flag = true;

            for (Object settingObj : ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
               Setting setting = (Setting)settingObj;
               if (!setting.getModule().equals(ClientGlobals.moduleManager.motionModifier)
                  && !setting.getModule().equals(ClientGlobals.moduleManager.clientAntiCheat)) {
                  for (String text2 : setting.getTextArray()) {
                     String text3 = setting.getModule().getCategory().name().toLowerCase(Locale.ROOT)
                        + "."
                        + setting.getTextAsText().toLowerCase(Locale.ROOT)
                        + "."
                        + text2.toLowerCase(Locale.ROOT);
                     String text4 = setting.getText3() + ";";
                     writer.write(text3 + "::" + text4);
                     writer.newLine();
                  }
               }
            }

            flag = false;
         } catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
         } finally {
            if (flag) {
               if (throwable != null) {
                  try {
                     writer.close();
                  } catch (Throwable throwable3) {
                     throwable.addSuppressed(throwable3);
                  }
               } else {
                  writer.close();
               }
            }
         }

         writer.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      }
   }

   public synchronized void applyString(String text) {
      if (text.equals("English_Default")) {
         for (Object settingObj : ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
            Setting setting = (Setting)settingObj;
            String text2 = "";
            setting.applyText(text2, text);
         }
      } else {
         String text3 = labelText + text + ".txt";
         ConfigManager.applyString2(labelText);
         String text4 = Client.getClient().getString4().toLowerCase() + "/settings/settingsnames";
         ConfigManager.applyString("assets/augustus/English_New.txt", text4, true);
         if (!new File(text3).exists()) {
            this.updateState();
         }

         try {
            BufferedReader reader = new BufferedReader(new FileReader(text3));

            String text5;
            outerLoop:
            while ((text5 = reader.readLine()) != null) {
               String[] textArray = text5.split("::");
               if (textArray.length > 1) {
                  String[] textArray2 = textArray[0].split("\\.");
                  if (textArray2.length > 1) {
                     for (Object setting2Obj : ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
                        Setting setting2 = (Setting)setting2Obj;
                        if (textArray2[0].equalsIgnoreCase(setting2.getModule().getCategory().name()) && textArray2[1].equalsIgnoreCase(setting2.getModule().getString())) {
                           for (String text6 : setting2.getTextArray()) {
                              if (textArray2[2].equalsIgnoreCase(text6)) {
                                 String text7 = textArray2[2];
                                 String text8 = this.resolveText(textArray);
                                 String text9 = text7;
                                 setting2.applyText(text9, text8);
                                 continue outerLoop;
                              }
                           }
                        }
                     }
                  }
               }
            }

            reader.close();
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }

   public String getText(String text, String[] textArray) {
      if (textArray.length < 1) {
         return "";
      } else {
         String[] textArray2 = textArray;
         int length = textArray.length;

         for (int index = 0; index < length; index++) {
            if (textArray2[index].equals(text)) {
               return text;
            }
         }

         return textArray[0];
      }
   }
}
