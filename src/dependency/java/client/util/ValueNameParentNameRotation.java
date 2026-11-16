package client.util;

import client.event.PostMotionInterceptor;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonObject;

public class ValueNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public JsonObject resolveJsonObject(NumberSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      jsonObject.addProperty("Value", setting.getValue());
      return jsonObject;
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      NumberSetting setting2 = (NumberSetting)setting;
      return this.resolveJsonObject(setting2);
   }

   public void applyNumberSetting(NumberSetting setting, int count, short shortValue, char character, JsonObject jsonObject) {
      setting.setState1(true);
      double asDouble = jsonObject.get("Value").getAsDouble();
      setting.applyDouble(asDouble);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      NumberSetting setting = (NumberSetting)postMotionInterceptor;
      NumberSetting setting2 = setting;
      this.applyNumberSetting(setting2, 10716, (short)-24248, (char)53956, jsonObject);
   }

   public ValueNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      NumberSetting setting = (NumberSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }
}
