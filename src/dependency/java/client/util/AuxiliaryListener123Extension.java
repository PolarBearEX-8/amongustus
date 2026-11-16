package client.util;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AuxiliaryListener123Extension extends JsonPropertyGetter {
   @Override
   default void applyText(String key, Object value, JsonObject jsonObject) {
      Object[] items = (Object[])value;
      this.applyText(key, items, jsonObject);
   }

   default void applyListByList(List list, JsonElement element) {
      this.applyList(list, element);
   }

   void applyList(List list, JsonElement element);

   default void applyText(String key, Object[] items, JsonObject jsonObject) {
      JsonArray jsonArray = new JsonArray();
      Arrays.stream(items).forEach(element -> this.applyJsonArrayByJsonArray(jsonArray, element));
      jsonObject.add(key, jsonArray);
   }

   @Override
   default Object getEntry(String key, Object defaultValue, JsonObject jsonObject) {
      Object[] defaultArray = (Object[])defaultValue;
      return this.resolveTargetArray(key, defaultArray, jsonObject);
   }

   void applyJsonArray(JsonArray jsonArray, Object element);

   default void applyJsonArrayByJsonArray(JsonArray jsonArray, Object element) {
      this.applyJsonArray(jsonArray, element);
   }

   default Object[] resolveTargetArray(String key, Object[] defaultArray, JsonObject jsonObject) {
      AutoReportCache.getStringArray();
      JsonElement jsonElement = jsonObject.get(key);
      if (jsonElement != null && jsonElement.isJsonArray()) {
         List jsonList = jsonElement.getAsJsonArray().asList();
         ArrayList resultList = Lists.newArrayList();
         jsonList.forEach(element -> this.applyListByList(resultList, (JsonElement)element));
         return jsonList.toArray();
      } else {
         return defaultArray;
      }
   }
}
