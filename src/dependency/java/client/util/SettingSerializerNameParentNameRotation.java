package client.util;

import client.event.PostMotionInterceptor;
import client.setting.BooleanSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonObject;

public class SettingSerializerNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      BooleanSetting setting = (BooleanSetting)postMotionInterceptor;
      this.applyBooleanSetting(setting, jsonObject);
   }

   public void applyBooleanSetting(BooleanSetting setting, JsonObject jsonObject) {
      setting.setState1(true);
      boolean asBoolean = jsonObject.get("Boolean").getAsBoolean();
      setting.setState3(asBoolean);
   }

   public JsonObject resolveJsonObject(BooleanSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      jsonObject.addProperty("Boolean", setting.isEnabled());
      return jsonObject;
   }

   public SettingSerializerNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      BooleanSetting setting2 = (BooleanSetting)setting;
      return this.resolveJsonObject(setting2);
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      BooleanSetting setting = (BooleanSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }
}
