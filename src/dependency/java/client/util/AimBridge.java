package client.util;

import client.event.PostMotionObserver;
import client.setting.ProviderOptionType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.item.DefaultItemEntry;
import org.apache.commons.lang3.math.NumberUtils;

public class AimBridge {
   public static SystemListEntry resolveSystemListEntryByList(List list, DefaultItemEntry defaultItemEntry) throws NumberFormatException, ExtendedExceptionAdapter {
      long value = 0x3e23be3c1f3fL;
      value = 0x5ccccf59b71dL ^ value;
      return resolveSystemListEntry(list, false, defaultItemEntry);
   }

   public static SystemListEntry resolveSystemListEntry(List list, DefaultItemEntry defaultItemEntry) throws NumberFormatException, ExtendedExceptionAdapter {
      long value = 0x3b2358a841deL;
      value = 0x5ccccf59b71dL ^ value;
      SystemListEntry systemListEntry = resolveSystemListEntry(list, true, defaultItemEntry);
      return new SystemListEntry(systemListEntry.list, systemListEntry.list3, systemListEntry.list2);
   }

   private static ProviderOptionType resolveProviderOptionType(JsonElement jsonElement) {
      String asString = jsonElement.getAsJsonObject().get("easing").getAsString();

      try {
         String text = Character.toUpperCase(asString.charAt(0)) + asString.substring(1);
         return ProviderOptionType.valueOf(text);
      } catch (Exception exception) {
         MoreCosmeticsManager.logger.fatal("Unknown easing type: {}", asString);
         throw new RuntimeException(exception);
      }
   }

   private static JsonArray resolveJsonArray(JsonElement jsonElement) {
      return jsonElement.isJsonArray() ? jsonElement.getAsJsonArray() : jsonElement.getAsJsonObject().get("vector").getAsJsonArray();
   }

   private static SystemListEntry resolveSystemListEntry(List list, boolean flag, DefaultItemEntry defaultItemEntry) throws NumberFormatException, ExtendedExceptionAdapter {
      long value = 0x1307a15e9923L;
      value = 0x5ccccf59b71dL ^ value;
      Object entry = null;
      Object entry2 = null;
      Object entry3 = null;
      ArrayList list2 = new ArrayList();
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();

      for (int index = 0; index < list.size(); index++) {
         Entry entry4 = (Entry)list.get(index);
         Entry entry5 = index == 0 ? null : (Entry)list.get(index - 1);
         Double offset = entry5 == null ? 0.0 : Double.parseDouble((String)entry5.getKey());
         Double offset2 = Double.valueOf(NumberUtils.isNumber((String)entry4.getKey()) ? Double.parseDouble((String)entry4.getKey()) : 0.0) - offset;
         JsonArray jsonArray = resolveJsonArray((JsonElement)entry4.getValue());
         JsonElement jsonElement = jsonArray.get(0);
         PostMotionObserver postMotionObserver = resolvePostMotionObserver(defaultItemEntry, jsonElement);
         JsonElement jsonElement2 = jsonArray.get(1);
         PostMotionObserver postMotionObserver2 = resolvePostMotionObserver(defaultItemEntry, jsonElement2);
         JsonElement jsonElement3 = jsonArray.get(2);
         PostMotionObserver postMotionObserver3 = resolvePostMotionObserver(defaultItemEntry, jsonElement3);
         jsonElement = (JsonElement)(flag && postMotionObserver instanceof AuxiliaryListenerController
            ? AuxiliaryListenerController.resolveAuxiliaryListenerController(Math.toRadians(-postMotionObserver.getValue()))
            : postMotionObserver);
         jsonElement2 = (JsonElement)(flag && postMotionObserver2 instanceof AuxiliaryListenerController
            ? AuxiliaryListenerController.resolveAuxiliaryListenerController(Math.toRadians(-postMotionObserver2.getValue()))
            : postMotionObserver2);
         Object entry6 = flag && postMotionObserver3 instanceof AuxiliaryListenerController
            ? AuxiliaryListenerController.resolveAuxiliaryListenerController(Math.toRadians(postMotionObserver3.getValue()))
            : postMotionObserver3;
         AdvancedListEntry advancedListEntry;
         AdvancedListEntry advancedListEntry2;
         AdvancedListEntry advancedListEntry3;
         if (((JsonElement)entry4.getValue()).isJsonObject() && matchesJsonElementByJsonElement((JsonElement)entry4.getValue())) {
            ProviderOptionType providerOptionType = resolveProviderOptionType((JsonElement)entry4.getValue());
            if (matchesJsonElement((JsonElement)entry4.getValue())) {
               List list5 = resolveList((JsonElement)entry4.getValue());
               advancedListEntry = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement : entry, jsonElement, providerOptionType, list5);
               advancedListEntry2 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement2 : entry2, jsonElement2, providerOptionType, list5);
               advancedListEntry3 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? entry6 : entry3, entry6, providerOptionType, list5);
            } else {
               advancedListEntry = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement : entry, jsonElement, providerOptionType);
               advancedListEntry2 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement2 : entry2, jsonElement2, providerOptionType);
               advancedListEntry3 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? entry6 : entry3, entry6, providerOptionType);
            }
         } else {
            advancedListEntry = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement : entry, jsonElement);
            advancedListEntry2 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? jsonElement2 : entry2, jsonElement2);
            advancedListEntry3 = new AdvancedListEntry(InterpolationRanker.computeValue(offset2), index == 0 ? entry6 : entry3, entry6);
         }

         entry = jsonElement;
         entry2 = jsonElement2;
         entry3 = entry6;
         list2.add(advancedListEntry);
         list3.add(advancedListEntry2);
         list4.add(advancedListEntry3);
      }

      return new SystemListEntry(list2, list3, list4);
   }

   private static List resolveList(JsonElement jsonElement) {
      return CouldNotFindKeyManager.fromJson(jsonElement.getAsJsonObject().get("easingArgs").getAsJsonArray());
   }

   private static boolean matchesJsonElementByJsonElement(JsonElement jsonElement) {
      return jsonElement.getAsJsonObject().has("easing");
   }

   private static boolean matchesJsonElement(JsonElement jsonElement) {
      return jsonElement.getAsJsonObject().has("easingArgs");
   }

   public static PostMotionObserver resolvePostMotionObserver(DefaultItemEntry defaultItemEntry, JsonElement jsonElement) throws ExtendedExceptionAdapter {
      long value = 0xae226b6cc0eL;
      value = 0x5ccccf59b71dL ^ value;
      return (PostMotionObserver)(jsonElement.getAsJsonPrimitive().isString()
         ? defaultItemEntry.resolveListenerAdapter10(jsonElement)
         : AuxiliaryListenerController.resolveAuxiliaryListenerController(jsonElement.getAsDouble()));
   }
}
