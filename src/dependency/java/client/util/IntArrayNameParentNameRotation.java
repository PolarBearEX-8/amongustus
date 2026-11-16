package client.util;

import client.event.PostMotionInterceptor;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;

public class IntArrayNameParentNameRotation implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      RuntimeComponentBridge runtimeComponentBridge = (RuntimeComponentBridge)postMotionInterceptor;
      this.applyRuntimeComponentBridge(runtimeComponentBridge, jsonObject);
   }

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      RuntimeComponentBridge runtimeComponentBridge = (RuntimeComponentBridge)postMotionInterceptor;
      return this.resolveJsonObject(runtimeComponentBridge);
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      RuntimeComponentBridge runtimeComponentBridge = (RuntimeComponentBridge)(Object)setting;
      return this.resolveJsonObject(runtimeComponentBridge);
   }

   public void applyRuntimeComponentBridge(RuntimeComponentBridge runtimeComponentBridge, JsonObject jsonObject) {
      ((Setting)(Object)runtimeComponentBridge).setState1(true);
      JsonArray asJsonArray = jsonObject.get("IntArray").getAsJsonArray();
      Integer[] countArray = new Integer[asJsonArray.size()];
      List list = asJsonArray.asList();

      for (int index = 0; index < list.size(); index++) {
         countArray[index] = ((JsonElement)list.get(index)).getAsInt();
      }

      runtimeComponentBridge.applyIntegerArray(countArray, 0x2314bec98309L);
   }

   public JsonObject resolveJsonObject(RuntimeComponentBridge runtimeComponentBridge) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)(Object)runtimeComponentBridge);
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(runtimeComponentBridge.resolveIntegerArray(0x61bbf38eb2c3L)).forEach(jsonArray::add);
      jsonObject.add("IntArray", jsonArray);
      return jsonObject;
   }

   public IntArrayNameParentNameRotation(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }
}
