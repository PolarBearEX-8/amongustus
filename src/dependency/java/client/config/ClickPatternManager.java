package client.config;

import client.Client;
import client.event.EventBusManager;
import client.event.TickListener;
import client.util.ConfigManager;
import client.util.MinecraftAccessor;
import client.util.OnlineConfigsManager;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import org.lwjgl.input.Mouse;

public class ClickPatternManager implements TickListener, MinecraftAccessor {
   public String labelText;
   public boolean enabled4;
   public float factor;
   public Thread thread = null;
   public ArrayList arrayList;
   public boolean enabled2 = false;
   public boolean enabled;
   public boolean enabled3;
   public float factor2;

   public static String getText(String text, ArrayList list) {
      if (list.isEmpty()) {
         return "";
      } else {
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            if (((ClickPatternProfile)iterator.next()).labelText.equals(text)) {
               return text;
            }
         }

         return ((ClickPatternProfile)list.get(0)).labelText;
      }
   }

   private void updateState2() {
      ArrayList list = new ArrayList();
      int keyCode = MinecraftAccessor.minecraft.gameSettings.keyBindAttack.getKeyCode();
      long timestamp = System.currentTimeMillis();
      boolean flag = false;

      while (this.isEnabled4()) {
         this.sleep();
         if (!this.isEnabled()) {
            while (Mouse.next()) {
            }

            flag = false;
         } else {
            while (Mouse.next()) {
               if (Mouse.getEventButton() - 100 == keyCode) {
                  boolean eventButtonState = Mouse.getEventButtonState();
                  if (flag || eventButtonState) {
                     long timestamp2 = System.currentTimeMillis();
                     if (!flag) {
                        timestamp = System.currentTimeMillis();
                     } else {
                        list.add((int)(timestamp2 - timestamp));
                        timestamp = timestamp2;
                     }

                     flag = true;
                  }
               }
            }
         }
      }

      if (!this.isEnabled3()) {
         if (!list.isEmpty()) {
            if (list.size() % 2 != 0) {
               list.remove(list.size() - 1);
            }

            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            JsonArray jsonArray2 = new JsonArray();

            for (int count : (Iterable<Integer>)(Iterable)(list)) {
               jsonArray.add(count);
            }

            for (int index = 0; index < this.getArrayListAsArrayList().size(); index++) {
               float[] factorArray = (float[])this.getArrayListAsArrayList().get(index);
               JsonArray jsonArray3 = new JsonArray();

               for (float factor3 : factorArray) {
                  jsonArray3.add(factor3);
               }

               jsonArray2.add(jsonArray3);
            }

            jsonObject.add("Delays", jsonArray);
            jsonObject.add("Rotations", jsonArray2);
            String text = OnlineConfigsManager.text + "/clickpattern";
            String text2 = this.getLabelText() + ".json";
            ConfigManager.applyText(text, text2, jsonObject);
         }
      }
   }

   public static String[] getTextArray(ArrayList list) {
      String[] textArray = new String[list.size()];

      for (int index = 0; index < list.size(); index++) {
         textArray[index] = ((ClickPatternProfile)list.get(index)).labelText;
      }

      return textArray;
   }

   public synchronized void setEnabled4(boolean enabled5) {
      this.enabled4 = enabled5;
   }

   public static ArrayList getArrayList() {
      String text = Client.getClient().getString4().toLowerCase() + "/clickpattern";
      ConfigManager.applyString("assets/augustus/clicks/DefaultButterfly.json", text, true);
      ArrayList list = new ArrayList();
      String text2 = OnlineConfigsManager.text + "/clickpattern";
      File file = new File(text2);
      ConfigManager.applyString2(text2);
      File[] fileArray = file.listFiles();
      if (fileArray != null) {
         for (File file2 : fileArray) {
            String name = file2.getName();
            if (name.endsWith(".json")) {
               try {
                  if (file2.exists()) {
                     JsonObject jsonObject = ConfigManager.getJsonObject(OnlineConfigsManager.text + "/clickpattern", name, (Runnable)null);
                     if (jsonObject != null) {
                        ArrayList list2 = new ArrayList();
                        ArrayList list3 = new ArrayList();

                        for (Entry entry : jsonObject.entrySet()) {
                           JsonArray asJsonArray = ((JsonElement)entry.getValue()).getAsJsonArray();
                           if (asJsonArray != null) {
                              if (((String)entry.getKey()).equals("Delays")) {
                                 asJsonArray.forEach(elem -> ClickPatternManager.applyArrayList(list2, (JsonElement)elem));
                              } else if (((String)entry.getKey()).equals("Rotations")) {
                                 Iterator iterator = asJsonArray.iterator();

                                 while (iterator.hasNext()) {
                                    JsonArray asJsonArray2 = ((JsonElement)iterator.next()).getAsJsonArray();
                                    float[] factorArray = new float[asJsonArray2.size()];

                                    for (int index = 0; index < factorArray.length; index++) {
                                       factorArray[index] = asJsonArray2.get(index).getAsFloat();
                                    }

                                    list3.add(factorArray);
                                 }
                              }
                           }
                        }

                        if (list2.isEmpty()) {
                           RayTraceUtil.applyString("Failed to load click pattern " + name + "!");
                        } else {
                           StringBuilder builder = new StringBuilder();

                           for (int index2 = 0; index2 < file2.getName().length() - 5; index2++) {
                              builder.append(file2.getName().charAt(index2));
                           }

                           list.add(new ClickPatternProfile(builder.toString(), (Integer[])list2.toArray(new Integer[0]), list3));
                        }
                     }
                  }
               } catch (Exception exception) {
                  RayTraceUtil.applyString("Failed to load click pattern " + name + "!");
               }
            }
         }
      }

      return list;
   }

   public boolean isEnabled2() {
      if (!this.enabled2 && !this.isEnabled4()) {
         this.enabled2 = true;
         EventBusManager.getDietrichEvents2().subscribe(40, this);
         this.setEnabled3(false);
         this.setEnabled4(true);
         this.setEnabled(false);
         this.thread = new Thread(this::updateState2);
         this.thread.start();
         return true;
      } else {
         return false;
      }
   }

   public boolean matchesText(String text) {
      this.enabled2 = false;
      EventBusManager.getDietrichEvents2().unsubscribe(40, this);
      this.setLabelText(text);
      this.setEnabled4(false);
      return true;
   }

   private void sleep() {
      try {
         Thread.sleep(0L, 250000);
      } catch (InterruptedException interruptedException) {
         throw new RuntimeException(interruptedException);
      }
   }

   private static void applyArrayList(ArrayList list, JsonElement jsonElement) {
      list.add(jsonElement.getAsInt());
   }

   public synchronized boolean isEnabled4() {
      return this.enabled4;
   }

   public synchronized boolean isEnabled() {
      return this.enabled;
   }

   public static ClickPatternProfile getClickPatternProfile(String text, ArrayList list) {

      for (ClickPatternProfile clickPatternProfile : (Iterable<ClickPatternProfile>)(Iterable)(list)) {
         if (clickPatternProfile.labelText.equals(text)) {
            clickPatternProfile.updateState2();
            return clickPatternProfile;
         }
      }

      return null;
   }

   public synchronized boolean isEnabled3() {
      return this.enabled3;
   }

   public void updateState() {
      this.enabled2 = false;
      EventBusManager.getDietrichEvents2().unsubscribe(40, this);
      this.setEnabled3(true);
      this.setEnabled4(false);
   }

   public synchronized void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public synchronized void setEnabled(boolean enabled5) {
      this.enabled = enabled5;
   }

   public synchronized ArrayList getArrayListAsArrayList() {
      return this.arrayList;
   }

   public synchronized String getLabelText() {
      return this.labelText;
   }

   public synchronized void setEnabled3(boolean enabled5) {
      this.enabled3 = enabled5;
   }

   @Override
   public void onPreTick() {
      if (this.enabled2) {
         if (!this.isEnabled()) {
            this.factor = MinecraftAccessor.minecraft.thePlayer.rotationYaw;
            this.factor2 = MinecraftAccessor.minecraft.thePlayer.rotationPitch;
         }

         boolean sprinting = MinecraftAccessor.minecraft.thePlayer.isSprinting();
         this.setEnabled(sprinting);
         if (this.isEnabled()) {
            this.getArrayListAsArrayList()
               .add(
                  new float[]{
                     MinecraftAccessor.minecraft.thePlayer.rotationYaw - this.factor, MinecraftAccessor.minecraft.thePlayer.rotationPitch - this.factor2
                  }
               );
         }
      }
   }

   public ClickPatternManager() {
      this.enabled4 = false;
      this.enabled = false;
      this.enabled3 = false;
      this.labelText = "";
      this.factor = 0.0F;
      this.factor2 = 0.0F;
      this.arrayList = new ArrayList();
   }
}
