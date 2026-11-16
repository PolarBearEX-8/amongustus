package client.util;

import client.event.PostMotionInterceptor;
import client.setting.JsonObjectSecondarySettingAdapte;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class DoubleListNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      JsonObjectSecondarySettingAdapte jsonObjectSecondarySettingAdapte = (JsonObjectSecondarySettingAdapte)setting;
      return this.resolveJsonObject(jsonObjectSecondarySettingAdapte);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      JsonObjectSecondarySettingAdapte jsonObjectSecondarySettingAdapte = (JsonObjectSecondarySettingAdapte)postMotionInterceptor;
      this.applyJsonObjectSecondarySetting(jsonObjectSecondarySettingAdapte, jsonObject);
   }

   public void applyJsonObjectSecondarySetting(JsonObjectSecondarySettingAdapte jsonObjectSecondarySettingAdapte, JsonObject jsonObject) {
      jsonObjectSecondarySettingAdapte.setState1(true);
      JsonArray asJsonArray = jsonObject.get("DoubleList").getAsJsonArray();
      ArrayList list = new ArrayList();
      List list2 = asJsonArray.asList();

      for (int index = 0; index < list2.size(); index++) {
         list.add(((JsonElement)list2.get(index)).getAsInt());
      }

      jsonObjectSecondarySettingAdapte.applyList(list);
   }

   public JsonObject resolveJsonObject(JsonObjectSecondarySettingAdapte jsonObjectSecondarySettingAdapte) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)jsonObjectSecondarySettingAdapte);
      JsonArray jsonArray = new JsonArray();
      jsonObjectSecondarySettingAdapte.getList().forEach(item -> jsonArray.add((Integer)item));
      jsonObject.add("DoubleList", jsonArray);
      return jsonObject;
   }

   public DoubleListNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      JsonObjectSecondarySettingAdapte jsonObjectSecondarySettingAdapte = (JsonObjectSecondarySettingAdapte)postMotionInterceptor;
      return this.resolveJsonObject(jsonObjectSecondarySettingAdapte);
   }
}
