package client.util;

import client.event.PostMotionInterceptor;
import client.setting.ModeSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonObject;

public class SelectedOptionNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public SelectedOptionNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      ModeSetting setting2 = (ModeSetting)setting;
      return this.resolveJsonObject(setting2);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      ModeSetting setting = (ModeSetting)postMotionInterceptor;
      this.applyModeSetting(setting, jsonObject);
   }

   public void applyModeSetting(ModeSetting setting, JsonObject jsonObject) {
      setting.setState1(true);
      String asString = jsonObject.get("SelectedOption").getAsString();
      setting.setSelected(asString);
   }

   public JsonObject resolveJsonObject(ModeSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      jsonObject.addProperty("SelectedOption", setting.getSelected());
      return jsonObject;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      ModeSetting setting = (ModeSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }
}
