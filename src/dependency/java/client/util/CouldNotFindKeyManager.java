package client.util;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import net.minecraft.item.DefaultItemEntry;
import net.minecraft.item.DiamondTool;

public class CouldNotFindKeyManager {
   private static String[] stringArray;
   public static ArrayList resolveArrayListByJsonObject(JsonObject jsonObject) {
      JsonObject asJsonObject = jsonObject.getAsJsonObject("particle_effects");
      return asJsonObject == null ? new ArrayList() : new ArrayList(entrySet(asJsonObject));
   }

   public static double computeValue(double[] offsetArray) {
      double offset = 0.0;
      double[] offsetArray2 = offsetArray;
      int length = offsetArray.length;

      for (int index = 0; index < length; index++) {
         offset = Math.max(offsetArray2[index], offset);
      }

      return offset;
   }

   public static ArrayList resolveArrayList(JsonObject jsonObject) {
      JsonObject asJsonObject = jsonObject.getAsJsonObject("timeline");
      return asJsonObject == null ? new ArrayList() : new ArrayList(entrySet(asJsonObject));
   }

   private static JsonElement resolveJsonElement(Set set, String text) throws JsonParseException {
      return (JsonElement)(((Set<Entry>)set).stream()
         .filter(entry -> matchesText(text, (Entry)entry))
         .findFirst()
         .orElseThrow(() -> resolveJsonParseException(text)))
         .getValue();
   }

   public static List fromJson(JsonArray jsonArray) {
      return new Gson().fromJson(jsonArray, ArrayList.class);
   }

   private static double computeValue(List list) {
      double offset = 0.0;

      for (DiamondTool diamondTool : (Iterable<DiamondTool>)(Iterable<?>)list) {
         double value = diamondTool.systemListEntry.getValue();
         double value2 = diamondTool.systemListEntry2.getValue();
         double value3 = diamondTool.systemListEntry3.getValue();
         offset = computeValue(new double[]{offset, value, value2, value3});
      }

      return offset == 0.0 ? Double.MAX_VALUE : offset;
   }

   public static Set resolveSet(JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get("rotation");
      if (jsonElement.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement.getAsJsonArray()));
      } else if (jsonElement.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
         JsonElement jsonElement2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement2));
      } else {
         return entrySet(jsonElement.getAsJsonObject());
      }
   }

   public static List resolveList(JsonObject jsonObject) {
      JsonObject asJsonObject = jsonObject.getAsJsonObject("bones");
      return asJsonObject == null ? new ArrayList() : new ArrayList(entrySet(asJsonObject));
   }

   private static JsonParseException resolveJsonParseException(String text) {
      return new JsonParseException("Could not find key: " + text);
   }

   private static boolean matchesText(String text, Entry entry) {
      return ((String)entry.getKey()).equals(text);
   }

   public static Set entrySet(JsonObject jsonObject) {
      return jsonObject.entrySet();
   }

   public static Set resolveSet3(JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get("position");
      if (jsonElement.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement.getAsJsonArray()));
      } else if (jsonElement.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
         JsonElement jsonElement2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement2));
      } else {
         return entrySet(jsonElement.getAsJsonObject());
      }
   }

   public static Set resolveSet2(JsonObject jsonObject) {
      JsonElement jsonElement = jsonObject.get("scale");
      if (jsonElement.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement.getAsJsonArray()));
      } else if (jsonElement.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
         JsonElement jsonElement2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", jsonElement2));
      } else {
         return entrySet(jsonElement.getAsJsonObject());
      }
   }

   public static Entry resolveEntry(JsonObject jsonObject, String text) throws JsonParseException {
      return new SimpleEntry<>(text, resolveJsonElement(resolveSetByJsonObject(jsonObject), text));
   }

   public static ArrayList resolveArrayList2(JsonObject jsonObject) {
      JsonObject asJsonObject = jsonObject.getAsJsonObject("sound_effects");
      return asJsonObject == null ? new ArrayList() : new ArrayList(entrySet(asJsonObject));
   }

   public static Set resolveSetByJsonObject(JsonObject jsonObject) {
      return entrySet(jsonObject.getAsJsonObject("animations"));
   }

   public static String[] getStringArray() {
      return null;
   }

   public static SupplementalStringEntry resolveSupplementalStringEntry(Entry entry, DefaultItemEntry defaultItemEntry) throws ClassCastException, IllegalStateException {
      SupplementalStringEntry supplementalStringEntry = new SupplementalStringEntry();
      JsonObject asJsonObject = ((JsonElement)entry.getValue()).getAsJsonObject();
      supplementalStringEntry.labelText = (String)entry.getKey();
      JsonElement jsonElement = asJsonObject.get("animation_length");
      supplementalStringEntry.memberRef = jsonElement == null ? null : InterpolationRanker.computeValue(jsonElement.getAsDouble());
      supplementalStringEntry.list3 = new ArrayList();
      JsonElement jsonElement2 = asJsonObject.get("loop");
      supplementalStringEntry.enabled = jsonElement2 != null && jsonElement2.getAsBoolean();
      ArrayList list = resolveArrayList2(asJsonObject);
      if (list != null) {
         for (Entry entry2 : (Iterable<Entry>)(Iterable<?>)list) {
            supplementalStringEntry.list2
               .add(
                  new VectorCollector(
                     Double.parseDouble((String)entry2.getKey()) * 20.0, ((JsonElement)entry2.getValue()).getAsJsonObject().get("effect").getAsString()
                  )
               );
         }
      }

      ArrayList list2 = resolveArrayListByJsonObject(asJsonObject);
      if (list2 != null) {
         for (Entry entry3 : (Iterable<Entry>)(Iterable<?>)list2) {
            JsonObject asJsonObject2 = ((JsonElement)entry3.getValue()).getAsJsonObject();
            JsonElement jsonElement3 = asJsonObject2.get("effect");
            JsonElement jsonElement4 = asJsonObject2.get("locator");
            JsonElement jsonElement5 = asJsonObject2.get("pre_effect_script");
            supplementalStringEntry.list4
               .add(
                  new VectorProfiler(
                     Double.parseDouble((String)entry3.getKey()) * 20.0,
                     jsonElement3 == null ? "" : jsonElement3.getAsString(),
                     jsonElement4 == null ? "" : jsonElement4.getAsString(),
                     jsonElement5 == null ? "" : jsonElement5.getAsString()
                  )
               );
         }
      }

      ArrayList list3 = resolveArrayList(asJsonObject);
      if (list3 != null) {
         for (Entry entry4 : (Iterable<Entry>)(Iterable<?>)list3) {
            supplementalStringEntry.list.add(new VectorCollector(Double.parseDouble((String)entry4.getKey()) * 20.0, fromJson(((JsonElement)entry4.getValue()).getAsJsonArray())));
         }
      }

      for (Entry entry5 : (Iterable<Entry>)(Iterable<?>)resolveList(asJsonObject)) {
         DiamondTool diamondTool = new DiamondTool();
         diamondTool.labelText = (String)entry5.getKey();
         JsonObject asJsonObject3 = ((JsonElement)entry5.getValue()).getAsJsonObject();

         try {
            Set set = resolveSet2(asJsonObject3);
            diamondTool.systemListEntry3 = AimBridge.resolveSystemListEntryByList(new ArrayList(set), defaultItemEntry);
         } catch (Exception exception) {
            diamondTool.systemListEntry3 = new SystemListEntry();
         }

         try {
            Set set2 = resolveSet3(asJsonObject3);
            diamondTool.systemListEntry2 = AimBridge.resolveSystemListEntryByList(new ArrayList(set2), defaultItemEntry);
         } catch (Exception exception2) {
            diamondTool.systemListEntry2 = new SystemListEntry();
         }

         try {
            Set set3 = resolveSet(asJsonObject3);
            diamondTool.systemListEntry = AimBridge.resolveSystemListEntry(new ArrayList(set3), defaultItemEntry);
         } catch (Exception exception3) {
            diamondTool.systemListEntry = new SystemListEntry();
         }

         supplementalStringEntry.list3.add(diamondTool);
      }

      if (supplementalStringEntry.memberRef == null) {
         supplementalStringEntry.memberRef = computeValue(supplementalStringEntry.list3);
      }

      return supplementalStringEntry;
   }
}
