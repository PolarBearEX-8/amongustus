package client.util;

import client.event.PostMotionInterceptor;
import client.setting.Setting;
import client.setting.SettingSerializer;
import client.setting.SupplementalListenerSupplementalLi;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;

public class StringArrayNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   public JsonObject resolveJsonObject(SupplementalListenerSupplementalLi supplementalListenerSupplementalLi) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)supplementalListenerSupplementalLi);
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(supplementalListenerSupplementalLi.getTextArrayAsTextArray()).forEach(jsonArray::add);
      jsonObject.add("StringArray", jsonArray);
      return jsonObject;
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      SupplementalListenerSupplementalLi supplementalListenerSupplementalLi = (SupplementalListenerSupplementalLi)postMotionInterceptor;
      this.applySupplementalListenerSupple(supplementalListenerSupplementalLi, jsonObject);
   }

   public StringArrayNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }

   public void applySupplementalListenerSupple(SupplementalListenerSupplementalLi supplementalListenerSupplementalLi, JsonObject jsonObject) {
      supplementalListenerSupplementalLi.setState1(true);
      JsonArray asJsonArray = jsonObject.get("StringArray").getAsJsonArray();
      String[] textArray = new String[asJsonArray.size()];
      List list = asJsonArray.asList();

      for (int index = 0; index < list.size(); index++) {
         textArray[index] = ((JsonElement)list.get(index)).getAsString();
      }

      supplementalListenerSupplementalLi.applyTextArray(textArray);
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      SupplementalListenerSupplementalLi supplementalListenerSupplementalLi = (SupplementalListenerSupplementalLi)setting;
      return this.resolveJsonObject(supplementalListenerSupplementalLi);
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      SupplementalListenerSupplementalLi supplementalListenerSupplementalLi = (SupplementalListenerSupplementalLi)postMotionInterceptor;
      return this.resolveJsonObject(supplementalListenerSupplementalLi);
   }
}
