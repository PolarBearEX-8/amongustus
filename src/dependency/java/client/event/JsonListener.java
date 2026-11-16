package client.event;

import client.Client;
import client.util.AngleStack;
import client.util.ConfigManager;
import client.util.GsonCallback;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.item.AltItemEntry;

public abstract class JsonListener implements PostMotionInterceptor {
   public final AngleStack angleStack = new AngleStack();
   public boolean enabled;
   public JsonElement jsonElement = null;
   public static final String labelText = ConfigManager.labelText + Client.getClient().getString4().toLowerCase(Locale.ROOT);

   public boolean isActive2() {
      return true;
   }

   public String getText3() {
      return labelText + File.separator + this.getText();
   }

   public void applyFile(File file, JsonElement jsonElement2) {
      AngleStack angleStack2 = this.angleStack;
      Gson gson = this.getGson2();
      angleStack2.applyFile(file, jsonElement2, gson);
   }

   public void updateState2() {
      this.jsonElement = this.getJsonElement();
   }

   private List resolveList(List list) {
      return ((List<File>)list).stream()
         .map(File::getName)
         .filter(JsonListener::matchesText)
         .map(JsonListener::resolveText)
         .filter(this::matchesTextByText)
         .map(Long::parseLong)
         .collect(Collectors.toList());
   }

   private static boolean matchesFileByFile(File file) {
      return file.getName().endsWith(".json");
   }

   private static String resolveText(String text) {
      return text.substring(0, text.length() - 5);
   }

   private JsonElement getJsonElementAsJsonElement() {
      if (!this.isActiveAsFlag()) {
         return null;
      } else {
         File file = new File(this.getText2());
         File[] fileArray = file.listFiles(JsonListener::matchesFile);
         if (fileArray != null) {
            ArrayList list = Lists.newArrayList(fileArray);
            List list2 = this.resolveList(list);
            Collections.sort(list2);
            Collections.reverse(list2);

            for (long value : (Iterable<Long>)(Iterable<?>)list2) {
               File file2 = new File(file, value + ".json");
               if (file2.exists()) {
                  AngleStack angleStack2 = this.angleStack;
                  Class classAsClass = this.getClassAsClass();
                  Gson gson = this.getGson2();
                  JsonElement jsonElement2 = angleStack2.resolveJsonElement(file2, gson, classAsClass);
                  if (!this.angleStack.matchesJsonElement(jsonElement2) && jsonElement2 != null) {
                     return jsonElement2;
                  }
               }
            }
         }

         return null;
      }
   }

   public void setEnabled(boolean enabled2) {
      if (enabled2 || this.enabled) {
         if (this.jsonElement == null || this.isActive()) {
            this.updateState2();
         }

         JsonElement jsonElement2 = this.jsonElement;
         if (jsonElement2 != null) {
            File file = new File(this.getTextAsText());
            this.applyFile(file, jsonElement2);
            this.applyJsonElementByJsonElement(jsonElement2);
         }
      }
   }

   private static boolean matchesFile(File file) {
      return file.getName().endsWith(".json");
   }

   private void applyList(List list, File file) {
      if (list.size() > 10) {
         ((List<Long>)list).stream().sorted().limit((long)(list.size() - 10)).collect(Collectors.toList()).forEach(timestamp -> JsonListener.applyFile(file, timestamp));
      }
   }

   public String getTextAsText() {
      return this.getText3() + File.separator + this.getString() + ".json";
   }

   public abstract JsonElement getJsonElement();

   public boolean isActive() {
      return false;
   }

   private static boolean matchesText(String text) {
      return text.length() >= 7 && text.endsWith(".json");
   }

   public String getText2() {
      return labelText + File.separator + this.getText() + File.separator + "backups" + File.separator + this.getString();
   }

   public JsonElement resolveJsonElement(File file) {
      byte byteValue = 0;
      int count = 2740909;
      int count2 = 1299902;
      AngleStack angleStack2 = this.angleStack;
      Gson gson = this.getGson2();
      Class classAsClass = this.getClassAsClass();
      Supplier supplier = this::getJsonElementAsJsonElement;
      Class clazz = classAsClass;
      Gson gson2 = gson;
      return angleStack2.resolveJsonElement(file, gson2, clazz, supplier);
   }

   private void applyJsonElementByJsonElement(JsonElement jsonElement2) {
      if (this.isActiveAsFlag()) {
         File file = new File(this.getText2());
         File[] fileArray = file.listFiles(JsonListener::matchesFileByFile);
         String text = String.valueOf(System.currentTimeMillis());
         if (fileArray != null) {
            ArrayList list = Lists.newArrayList(fileArray);
            List list2 = this.resolveList(list);
            this.applyList(list2, file);
         }

         File file2 = new File(this.getText2(), text + ".json");
         AngleStack angleStack2 = this.angleStack;
         Gson gson = this.getGson2();
         angleStack2.applyFile(file2, jsonElement2, gson);
      }
   }

   public void updateState3() {
      File file = new File(this.getTextAsText());
      if (!file.exists()) {
         JsonElement jsonElementAsJsonElement = this.getJsonElementAsJsonElement();
         if (jsonElementAsJsonElement != null) {
            this.applyJsonElement(jsonElementAsJsonElement);
            this.enabled = true;
            return;
         }

         this.setEnabled(true);
      }

      JsonElement jsonElement2 = this.resolveJsonElement(file);
      this.enabled = true;
      if (jsonElement2 != null) {
         this.applyJsonElement(jsonElement2);
      }
   }

   public abstract String getText();

   public Gson getGson2() {
      return GsonCallback.gson2;
   }

   private boolean matchesTextByText(String text) {
      return text.chars().allMatch(Character::isDigit);
   }

   public abstract Class getClassAsClass();

   public boolean isActiveAsFlag() {
      return true;
   }

   public void updateState() {
      this.setEnabled(false);
   }

   private static void applyFile(File file, Long value) {
      ConfigManager.applyFile(new File(file, value + ".json"));
   }

   public abstract void applyJsonElement(JsonElement jsonElement2);

   public JsonListener() {
      this.enabled = false;
      AltItemEntry.arrayList.add(this);
   }
}
