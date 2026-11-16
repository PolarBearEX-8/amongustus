package client.util;

import client.event.PostMotionInterceptor;
import client.setting.Setting;
import client.setting.SettingSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.GoldenNugget;

public class DoubleListNameParentNameRotationImpl implements NameParentNameRotationFormatter {
   public final SettingSerializer settingSerializer;

   @Override
   public JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      GoldenNugget goldenNugget = (GoldenNugget)postMotionInterceptor;
      return this.resolveJsonObject(goldenNugget);
   }

   @Override
   public void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject) {
      GoldenNugget goldenNugget = (GoldenNugget)postMotionInterceptor;
      this.applyGoldenNugget(goldenNugget, jsonObject);
   }

   @Override
   public JsonObject resolveJsonObject(Setting setting) {
      GoldenNugget goldenNugget = (GoldenNugget)setting;
      return this.resolveJsonObject(goldenNugget);
   }

   public void applyGoldenNugget(GoldenNugget goldenNugget, JsonObject jsonObject) {
      goldenNugget.setState1(true);
      JsonArray asJsonArray = jsonObject.get("DoubleList").getAsJsonArray();
      ArrayList list = new ArrayList();
      List list2 = asJsonArray.asList();

      for (int index = 0; index < list2.size(); index++) {
         list.add(((JsonElement)list2.get(index)).getAsDouble());
      }

      goldenNugget.applyList(list);
   }

   public JsonObject resolveJsonObject(GoldenNugget goldenNugget) {
      JsonObject jsonObject = NameParentNameRotationFormatter.super.resolveJsonObject((Setting)goldenNugget);
      JsonArray jsonArray = new JsonArray();
      goldenNugget.getList().forEach(item -> jsonArray.add((Number)item));
      jsonObject.add("DoubleList", jsonArray);
      return jsonObject;
   }

   public DoubleListNameParentNameRotationImpl(SettingSerializer settingSerializer2) {
      this.settingSerializer = settingSerializer2;
   }
}
