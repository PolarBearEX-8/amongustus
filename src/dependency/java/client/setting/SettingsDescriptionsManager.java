package client.setting;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
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

public class SettingsDescriptionsManager {
   public static final String labelText = OnlineConfigsManager.text + "/settings/descriptions/";

   public String[] getStringArray() {
      ArrayList list = new ArrayList();
      File file = new File(labelText);
      ConfigManager.applyString2(labelText);
      if (!new File(labelText + "Default.txt").exists()) {
         String defaultPath = Client.getClient().getString4().toLowerCase() + "/settings/descriptions";
         ConfigManager.applyString("assets/augustus/Default.txt", defaultPath, true);
      }
      File[] fileArray = file.listFiles();
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
      String text = Client.getClient().getString4().toLowerCase() + "/settings/descriptions";
      ConfigManager.applyString("assets/augustus/Default.txt", text, false);

      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(labelText + "Default.txt"));
         Throwable throwable = null;
         boolean flag = false ;

         try {
            flag = true;

            for (Module module : (Iterable<Module>)(Iterable<?>)ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
               String text2 = module.getCategory().name().toLowerCase(Locale.ROOT) + "." + module.getString().toLowerCase(Locale.ROOT);
               String text3 = module.getDisplayName() + ";";
               writer.write(text2 + "::" + text3);
               writer.newLine();
            }

            for (Setting setting : (Iterable<Setting>)(Iterable<?>)ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
               if (!setting.getModule().equals(ClientGlobals.moduleManager.motionModifier)
                  && !setting.getModule().equals(ClientGlobals.moduleManager.clientAntiCheat)) {
                  String text4 = setting.getModule().getCategory().name().toLowerCase(Locale.ROOT)
                     + "."
                     + setting.getTextAsText().toLowerCase(Locale.ROOT)
                     + "."
                     + setting.getString().toLowerCase(Locale.ROOT);
                  String text5 = setting.getText() + ";";
                  writer.write(text4 + "::" + text5);
                  writer.newLine();
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

   public synchronized void applyString(String text) {
      if (text == null || text.isEmpty()) {
         text = "Default";
      }
      String text2 = labelText + text + ".txt";
      ConfigManager.applyString2(labelText);
      String text3 = Client.getClient().getString4().toLowerCase() + "/settings/descriptions";
      ConfigManager.applyString("assets/augustus/Default.txt", text3, true);
      if (!new File(text2).exists()) {
         this.updateState();
      }

      try {
         BufferedReader reader = new BufferedReader(new FileReader(text2));

         String text4;
         while ((text4 = reader.readLine()) != null) {
            String[] textArray = text4.split("::");
            if (textArray.length > 1) {
               String[] textArray2 = textArray[0].split("\\.");

               for (Module module : (Iterable<Module>)(Iterable<?>)ClientGlobals.moduleManager.getCopyOnWriteArrayList2()) {
                  if (textArray2.length > 1 && textArray2[0].equalsIgnoreCase(module.getCategory().name()) && textArray2[1].equalsIgnoreCase(module.getString())) {
                     if (textArray2.length > 2) {
                        for (Setting setting : (Iterable<Setting>)(Iterable<?>)ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.getList()) {
                           if (setting.getModule().equals(module)
                              && setting.getModule().getCategory().equals(module.getCategory())
                              && textArray2[2].equalsIgnoreCase(setting.getString())) {
                              String text5 = this.resolveText(textArray);
                              setting.setText(text5);
                              break;
                           }
                        }
                     } else {
                        String text6 = this.resolveText(textArray);
                        module.setDisplayName(text6);
                     }
                     break;
                  }
               }
            }
         }

         reader.close();
      } catch (IOException ioException) {
         ioException.printStackTrace();
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
