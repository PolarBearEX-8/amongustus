package client.util;

import client.Client;
import client.event.JsonListener;
import client.event.PostRenderDispatcher;
import client.module.Module;
import client.setting.BeverageNotifyInfoType;
import client.setting.BeverageNotifyModuleTogglesType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.HashMap;
import net.minecraft.client.Minecraft;

public class BaseHashMapEntry extends JsonListener {
   public final HashMap hashMap = new HashMap();

   private static void applyTextByText(String text, BeverageNotifyInfoType beverageNotifyInfoType) {
      BeverageItemNotification.updateText("Configsystem", text, beverageNotifyInfoType);
   }

   @Override
   public boolean isActive2() {
      return false;
   }

   public void applyText(String text, String text2) {
      JsonObject jsonObject = this.resolveJsonObject(text2, text);
      if (jsonObject != null) {
         Integer count = this.resolveInteger(jsonObject);
         if (count != null) {
            PostRenderDispatcher postRenderDispatcher = (PostRenderDispatcher)this.hashMap.get(count);
            if (postRenderDispatcher != null) {
               try {
                  long timestamp = System.currentTimeMillis();
                  postRenderDispatcher.applyJsonObject(jsonObject);
                  long value2 = System.currentTimeMillis() - timestamp;
                  String text3 = "Config loaded: §c" + text + " (" + value2 + "ms)";
                  BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.INFO;
                  String text4 = text3;
                  this.applyText(text4, beverageNotifyInfoType);
                  Client.getClient().getModuleManager().updateState();
                  SoundPlayer.processAction(SoundPlayer.resourceLocation7);
               } catch (Exception exception) {
               }
            }
         }
      }
   }

   private Integer resolveInteger(JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get("Config_Header");
      if (jsonElement == null) {
         return 1;
      } else {
         try {
            return jsonElement.getAsJsonObject().get("Config_Version").getAsInt();
         } catch (Exception exception) {
            return null;
         }
      }
   }

   public JsonObject getJsonObject() {
      return null;
   }

   public void applyJsonObject(JsonObject jsonObject) {
   }

   @Override
   public JsonElement getJsonElement() {
      return this.getJsonObject();
   }

   @Override
   public String getString() {
      return "";
   }

   public void applyTextByText(String text, String text2) {
      byte byteValue = 0;
      int count = 1683853;
      int count2 = 1579473;

      try {
         JsonObject jsonObject = ((PostRenderDispatcher)this.hashMap.get(2)).resolveJsonObject(text);
         File file = new File(this.getText3() + text2, text + ".json");
         long timestamp = System.currentTimeMillis();
         this.applyFile(file, (JsonElement)jsonObject);
         long value = System.currentTimeMillis() - timestamp;
         SoundPlayer.processAction(SoundPlayer.resourceLocation7);
         String text3 = "Config saved: §c" + text + " (" + value + "ms)";
         BeverageNotifyInfoType beverageNotifyInfoType = BeverageNotifyInfoType.INFO;
         String text4 = text3;
         this.applyText(text4, beverageNotifyInfoType);
         System.out.println("Saved");
      } catch (Exception exception) {
      }
   }

   @Override
   public String getText() {
      return "";
   }

   private void applyText(String text, BeverageNotifyInfoType beverageNotifyInfoType) {
      RayTraceUtil.applyString(text);
      if (((Module)Client.getClient().getModuleManager().notifications).isToggled() && BeverageNotifyModuleTogglesType.CONFIG_SYSTEM.isSelected()) {
         if (Minecraft.getMinecraft().isSingleplayer()) {
            BeverageItemNotification.updateText("Configsystem", text, beverageNotifyInfoType);
         } else {
            ClientManager.applyRunnable2(() -> BaseHashMapEntry.applyTextByText(text, beverageNotifyInfoType));
         }
      }
   }

   public String[] resolveTextArray(String text, String text2) {
      JsonObject jsonObject = this.resolveJsonObject(text2, text);
      if (jsonObject == null) {
         return null;
      } else {
         Integer count = this.resolveInteger(jsonObject);
         if (count == null) {
            return null;
         } else {
            PostRenderDispatcher postRenderDispatcher = (PostRenderDispatcher)this.hashMap.get(count);
            if (postRenderDispatcher == null) {
               return null;
            } else {
               try {
                  return postRenderDispatcher.resolveTextArray(jsonObject);
               } catch (Exception exception) {
                  return null;
               }
            }
         }
      }
   }

   @Override
   public Class getClassAsClass() {
      return JsonObject.class;
   }

   @Override
   public boolean isActiveAsFlag() {
      return false;
   }

   private JsonObject resolveJsonObject(String text, String text2) {
      File file = new File(this.getText3() + text, text2 + ".json");
      return !file.exists() ? null : (JsonObject)this.resolveJsonElement(file);
   }

   @Override
   public void applyJsonElement(JsonElement jsonElement) {
      JsonObject jsonObject = (JsonObject)jsonElement;
      this.applyJsonObject(jsonObject);
   }

   public BaseHashMapEntry() {
      this.hashMap.put(1, new InternalNameManager());
      this.hashMap.put(2, new GroupygroupyManager());
   }
}
