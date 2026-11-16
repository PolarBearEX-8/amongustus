package client.util;

import client.event.PostMotionInterceptor;
import client.setting.Setting;
import client.setting.SettingSerializer;
import client.setting.StringSetting;
import com.google.gson.JsonObject;

public class KeyNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public void applyStringSetting(StringSetting setting, JsonObject jsonObject) {
      setting.setState1(true);
      int asInt = jsonObject.get("Key").getAsInt();
      setting.processAction(asInt);
   }

   public KeyNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      StringSetting setting = (StringSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      StringSetting setting = (StringSetting)postMotionInterceptor;
      this.applyStringSetting(setting, jsonObject);
   }

   public JsonObject resolveJsonObject(StringSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      jsonObject.addProperty("Key", setting.getInt3());
      return jsonObject;
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      StringSetting setting2 = (StringSetting)setting;
      return this.resolveJsonObject(setting2);
   }
}
