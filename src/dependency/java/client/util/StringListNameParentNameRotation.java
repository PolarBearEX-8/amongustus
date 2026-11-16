package client.util;

import client.event.PostMotionInterceptor;
import client.setting.ItemListSetting;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class StringListNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public JsonObject resolveJsonObject(ItemListSetting setting) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)setting);
      JsonArray jsonArray = new JsonArray();
      setting.getList().forEach(item -> jsonArray.add((String)item));
      jsonObject.add("StringList", jsonArray);
      return jsonObject;
   }

   public StringListNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      ItemListSetting setting = (ItemListSetting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      ItemListSetting setting2 = (ItemListSetting)setting;
      return this.resolveJsonObject(setting2);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      ItemListSetting setting = (ItemListSetting)postMotionInterceptor;
      this.applyItemListSetting(setting, jsonObject);
   }

   public void applyItemListSetting(ItemListSetting setting, JsonObject jsonObject) {
      setting.setState1(true);
      JsonArray asJsonArray = jsonObject.get("StringList").getAsJsonArray();
      ArrayList list = new ArrayList();
      List list2 = asJsonArray.asList();

      for (int index = 0; index < list2.size(); index++) {
         list.add(((JsonElement)list2.get(index)).getAsString());
      }

      setting.applyList(list);
   }
}
