package client.util;

import client.event.PostMotionInterceptor;
import client.setting.JsonObjectSupplementalLi;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;

public class DoubleArrayNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      JsonObjectSupplementalLi jsonObjectSupplementalLi = (JsonObjectSupplementalLi)setting;
      return this.resolveJsonObject(jsonObjectSupplementalLi);
   }

   public void applyJsonObjectSupplementalLi(JsonObjectSupplementalLi jsonObjectSupplementalLi, JsonObject jsonObject) {
      jsonObjectSupplementalLi.setState1(true);
      JsonArray asJsonArray = jsonObject.get("DoubleArray").getAsJsonArray();
      Double[] offsetArray = new Double[asJsonArray.size()];
      List list = asJsonArray.asList();

      for (int index = 0; index < list.size(); index++) {
         offsetArray[index] = ((JsonElement)list.get(index)).getAsDouble();
      }

      jsonObjectSupplementalLi.applyDoubleArray(offsetArray);
   }

   public DoubleArrayNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   public JsonObject resolveJsonObject(JsonObjectSupplementalLi jsonObjectSupplementalLi) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)jsonObjectSupplementalLi);
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(jsonObjectSupplementalLi.getDoubleArray()).forEach(jsonArray::add);
      jsonObject.add("DoubleArray", jsonArray);
      return jsonObject;
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      JsonObjectSupplementalLi jsonObjectSupplementalLi = (JsonObjectSupplementalLi)postMotionInterceptor;
      return this.resolveJsonObject(jsonObjectSupplementalLi);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      JsonObjectSupplementalLi jsonObjectSupplementalLi = (JsonObjectSupplementalLi)postMotionInterceptor;
      this.applyJsonObjectSupplementalLi(jsonObjectSupplementalLi, jsonObject);
   }
}
