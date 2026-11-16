package client.util;

import client.event.PostMotionInterceptor;
import client.setting.MultiSelectSetting;
import client.setting.NamedOption;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class KeyPLEASENameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      MultiSelectSetting setting = (MultiSelectSetting)postMotionInterceptor;
      MultiSelectSetting setting2 = setting;
      this.applyMultiSelectSetting(setting2, 20844, (char)19776, jsonObject, (short)964);
   }

   public JsonObject resolveJsonObject(MultiSelectSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      JsonArray jsonArray = new JsonArray();
      JsonObject jsonObject2 = new JsonObject();

      for (NamedOption namedOption : setting.getNamedOptionArray()) {
         if (namedOption.getText() == null) {
            System.err.println(setting.getString() + " is key null PLEASE CHECK IT STH WENT REALLY REALLY WRONG");
         } else {
            jsonObject2.addProperty(namedOption.getText(), namedOption.isSelected());
         }
      }

      jsonArray.add(jsonObject2);
      jsonObject.add("SettingList", jsonArray);
      return jsonObject;
   }

   public KeyPLEASENameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   public void applyMultiSelectSetting(MultiSelectSetting setting, int count, char character, JsonObject jsonObject, short shortValue) {
      short shortValue2 = 20844;
      short shortValue3 = 19776;
      short shortValue4 = 964;
      setting.setState1(true);
      JsonObject jsonObject2 = (JsonObject)((JsonArray)jsonObject.get("SettingList")).get(0);

      for (NamedOption namedOption : setting.getNamedOptionArray()) {
         try {
            boolean asBoolean = jsonObject2.get(namedOption.getText()).getAsBoolean();
            namedOption.setActive(asBoolean);
         } catch (Exception exception) {
            System.err.println("Settings doesn't exist: " + namedOption.getText());
         }
      }
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      MultiSelectSetting setting2 = (MultiSelectSetting)setting;
      return this.resolveJsonObject(setting2);
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      MultiSelectSetting setting = (MultiSelectSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }
}
