package client.util;

import client.event.PostMotionInterceptor;
import client.setting.ActionSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonObject;

public class MinValueMaxValueNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public JsonObject resolveJsonObject(ActionSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      jsonObject.addProperty("MinValue", setting.getDouble3());
      jsonObject.addProperty("MaxValue", setting.getDouble6());
      return jsonObject;
   }

   public void applyActionSetting(ActionSetting setting, JsonObject jsonObject) {
      setting.setState1(true);
      double asDouble = jsonObject.get("MinValue").getAsDouble();
      setting.applyDoubleByDouble(asDouble);
      double asDouble2 = jsonObject.get("MaxValue").getAsDouble();
      setting.applyDouble(asDouble2);
      if (setting.getRunnable() != null) {
         setting.getRunnable().run();
      }
   }

   public MinValueMaxValueNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      ActionSetting setting = (ActionSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      ActionSetting setting = (ActionSetting)postMotionInterceptor;
      this.applyActionSetting(setting, jsonObject);
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      ActionSetting setting2 = (ActionSetting)setting;
      return this.resolveJsonObject(setting2);
   }
}
