package client.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ExtendedJsonDeserializerAdapter implements JsonDeserializer, JsonSerializer {
   public JsonElement resolveJsonElement(AuxiliaryGsonEntry auxiliaryGsonEntry, Type type, JsonSerializationContext jsonSerializationContext) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("coordinateScale", auxiliaryGsonEntry.factor8);
      jsonObject.addProperty("heightScale", auxiliaryGsonEntry.factor9);
      jsonObject.addProperty("lowerLimitScale", auxiliaryGsonEntry.factor12);
      jsonObject.addProperty("upperLimitScale", auxiliaryGsonEntry.factor11);
      jsonObject.addProperty("depthNoiseScaleX", auxiliaryGsonEntry.factor4);
      jsonObject.addProperty("depthNoiseScaleZ", auxiliaryGsonEntry.factor5);
      jsonObject.addProperty("depthNoiseScaleExponent", auxiliaryGsonEntry.factor6);
      jsonObject.addProperty("mainNoiseScaleX", auxiliaryGsonEntry.factor);
      jsonObject.addProperty("mainNoiseScaleY", auxiliaryGsonEntry.factor2);
      jsonObject.addProperty("mainNoiseScaleZ", auxiliaryGsonEntry.factor3);
      jsonObject.addProperty("baseSize", auxiliaryGsonEntry.factor7);
      jsonObject.addProperty("stretchY", auxiliaryGsonEntry.factor10);
      jsonObject.addProperty("biomeDepthWeight", auxiliaryGsonEntry.factor13);
      jsonObject.addProperty("biomeDepthOffset", auxiliaryGsonEntry.factor14);
      jsonObject.addProperty("biomeScaleWeight", auxiliaryGsonEntry.factor15);
      jsonObject.addProperty("biomeScaleOffset", auxiliaryGsonEntry.factor16);
      jsonObject.addProperty("seaLevel", auxiliaryGsonEntry.count4);
      jsonObject.addProperty("useCaves", auxiliaryGsonEntry.enabled);
      jsonObject.addProperty("useDungeons", auxiliaryGsonEntry.enabled2);
      jsonObject.addProperty("dungeonChance", auxiliaryGsonEntry.count);
      jsonObject.addProperty("useStrongholds", auxiliaryGsonEntry.enabled3);
      jsonObject.addProperty("useVillages", auxiliaryGsonEntry.enabled4);
      jsonObject.addProperty("useMineShafts", auxiliaryGsonEntry.enabled5);
      jsonObject.addProperty("useTemples", auxiliaryGsonEntry.enabled6);
      jsonObject.addProperty("useMonuments", auxiliaryGsonEntry.enabled11);
      jsonObject.addProperty("useRavines", auxiliaryGsonEntry.enabled7);
      jsonObject.addProperty("useWaterLakes", auxiliaryGsonEntry.enabled8);
      jsonObject.addProperty("waterLakeChance", auxiliaryGsonEntry.count2);
      jsonObject.addProperty("useLavaLakes", auxiliaryGsonEntry.enabled9);
      jsonObject.addProperty("lavaLakeChance", auxiliaryGsonEntry.count3);
      jsonObject.addProperty("useLavaOceans", auxiliaryGsonEntry.enabled10);
      jsonObject.addProperty("fixedBiome", auxiliaryGsonEntry.count5);
      jsonObject.addProperty("biomeSize", auxiliaryGsonEntry.count6);
      jsonObject.addProperty("riverSize", auxiliaryGsonEntry.count7);
      jsonObject.addProperty("dirtSize", auxiliaryGsonEntry.count8);
      jsonObject.addProperty("dirtCount", auxiliaryGsonEntry.count9);
      jsonObject.addProperty("dirtMinHeight", auxiliaryGsonEntry.count10);
      jsonObject.addProperty("dirtMaxHeight", auxiliaryGsonEntry.count11);
      jsonObject.addProperty("gravelSize", auxiliaryGsonEntry.count12);
      jsonObject.addProperty("gravelCount", auxiliaryGsonEntry.count13);
      jsonObject.addProperty("gravelMinHeight", auxiliaryGsonEntry.count14);
      jsonObject.addProperty("gravelMaxHeight", auxiliaryGsonEntry.count15);
      jsonObject.addProperty("graniteSize", auxiliaryGsonEntry.count16);
      jsonObject.addProperty("graniteCount", auxiliaryGsonEntry.count17);
      jsonObject.addProperty("graniteMinHeight", auxiliaryGsonEntry.count18);
      jsonObject.addProperty("graniteMaxHeight", auxiliaryGsonEntry.count19);
      jsonObject.addProperty("dioriteSize", auxiliaryGsonEntry.count20);
      jsonObject.addProperty("dioriteCount", auxiliaryGsonEntry.count21);
      jsonObject.addProperty("dioriteMinHeight", auxiliaryGsonEntry.count22);
      jsonObject.addProperty("dioriteMaxHeight", auxiliaryGsonEntry.count23);
      jsonObject.addProperty("andesiteSize", auxiliaryGsonEntry.count24);
      jsonObject.addProperty("andesiteCount", auxiliaryGsonEntry.count25);
      jsonObject.addProperty("andesiteMinHeight", auxiliaryGsonEntry.count26);
      jsonObject.addProperty("andesiteMaxHeight", auxiliaryGsonEntry.count27);
      jsonObject.addProperty("coalSize", auxiliaryGsonEntry.count28);
      jsonObject.addProperty("coalCount", auxiliaryGsonEntry.count29);
      jsonObject.addProperty("coalMinHeight", auxiliaryGsonEntry.count30);
      jsonObject.addProperty("coalMaxHeight", auxiliaryGsonEntry.count31);
      jsonObject.addProperty("ironSize", auxiliaryGsonEntry.count32);
      jsonObject.addProperty("ironCount", auxiliaryGsonEntry.count33);
      jsonObject.addProperty("ironMinHeight", auxiliaryGsonEntry.count34);
      jsonObject.addProperty("ironMaxHeight", auxiliaryGsonEntry.count35);
      jsonObject.addProperty("goldSize", auxiliaryGsonEntry.count36);
      jsonObject.addProperty("goldCount", auxiliaryGsonEntry.count37);
      jsonObject.addProperty("goldMinHeight", auxiliaryGsonEntry.count38);
      jsonObject.addProperty("goldMaxHeight", auxiliaryGsonEntry.count39);
      jsonObject.addProperty("redstoneSize", auxiliaryGsonEntry.count40);
      jsonObject.addProperty("redstoneCount", auxiliaryGsonEntry.count41);
      jsonObject.addProperty("redstoneMinHeight", auxiliaryGsonEntry.count42);
      jsonObject.addProperty("redstoneMaxHeight", auxiliaryGsonEntry.count43);
      jsonObject.addProperty("diamondSize", auxiliaryGsonEntry.count44);
      jsonObject.addProperty("diamondCount", auxiliaryGsonEntry.count45);
      jsonObject.addProperty("diamondMinHeight", auxiliaryGsonEntry.count46);
      jsonObject.addProperty("diamondMaxHeight", auxiliaryGsonEntry.count47);
      jsonObject.addProperty("lapisSize", auxiliaryGsonEntry.count48);
      jsonObject.addProperty("lapisCount", auxiliaryGsonEntry.count49);
      jsonObject.addProperty("lapisCenterHeight", auxiliaryGsonEntry.count50);
      jsonObject.addProperty("lapisSpread", auxiliaryGsonEntry.count51);
      return jsonObject;
   }

   public JsonElement serialize(Object entry, Type type, JsonSerializationContext jsonSerializationContext) {
      return this.resolveJsonElement((AuxiliaryGsonEntry)entry, type, jsonSerializationContext);
   }

   public AuxiliaryGsonEntry resolveAuxiliaryGsonEntry(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      JsonObject asJsonObject = jsonElement.getAsJsonObject();
      AuxiliaryGsonEntry auxiliaryGsonEntry = new AuxiliaryGsonEntry();

      try {
         auxiliaryGsonEntry.factor8 = MissingManager.computeFactor(asJsonObject, "coordinateScale", auxiliaryGsonEntry.factor8);
         auxiliaryGsonEntry.factor9 = MissingManager.computeFactor(asJsonObject, "heightScale", auxiliaryGsonEntry.factor9);
         auxiliaryGsonEntry.factor12 = MissingManager.computeFactor(asJsonObject, "lowerLimitScale", auxiliaryGsonEntry.factor12);
         auxiliaryGsonEntry.factor11 = MissingManager.computeFactor(asJsonObject, "upperLimitScale", auxiliaryGsonEntry.factor11);
         auxiliaryGsonEntry.factor4 = MissingManager.computeFactor(asJsonObject, "depthNoiseScaleX", auxiliaryGsonEntry.factor4);
         auxiliaryGsonEntry.factor5 = MissingManager.computeFactor(asJsonObject, "depthNoiseScaleZ", auxiliaryGsonEntry.factor5);
         auxiliaryGsonEntry.factor6 = MissingManager.computeFactor(asJsonObject, "depthNoiseScaleExponent", auxiliaryGsonEntry.factor6);
         auxiliaryGsonEntry.factor = MissingManager.computeFactor(asJsonObject, "mainNoiseScaleX", auxiliaryGsonEntry.factor);
         auxiliaryGsonEntry.factor2 = MissingManager.computeFactor(asJsonObject, "mainNoiseScaleY", auxiliaryGsonEntry.factor2);
         auxiliaryGsonEntry.factor3 = MissingManager.computeFactor(asJsonObject, "mainNoiseScaleZ", auxiliaryGsonEntry.factor3);
         auxiliaryGsonEntry.factor7 = MissingManager.computeFactor(asJsonObject, "baseSize", auxiliaryGsonEntry.factor7);
         auxiliaryGsonEntry.factor10 = MissingManager.computeFactor(asJsonObject, "stretchY", auxiliaryGsonEntry.factor10);
         auxiliaryGsonEntry.factor13 = MissingManager.computeFactor(asJsonObject, "biomeDepthWeight", auxiliaryGsonEntry.factor13);
         auxiliaryGsonEntry.factor14 = MissingManager.computeFactor(asJsonObject, "biomeDepthOffset", auxiliaryGsonEntry.factor14);
         auxiliaryGsonEntry.factor15 = MissingManager.computeFactor(asJsonObject, "biomeScaleWeight", auxiliaryGsonEntry.factor15);
         auxiliaryGsonEntry.factor16 = MissingManager.computeFactor(asJsonObject, "biomeScaleOffset", auxiliaryGsonEntry.factor16);
         auxiliaryGsonEntry.count4 = MissingManager.computeCount(asJsonObject, "seaLevel", auxiliaryGsonEntry.count4);
         auxiliaryGsonEntry.enabled = MissingManager.matchesJsonObject(asJsonObject, "useCaves", auxiliaryGsonEntry.enabled);
         auxiliaryGsonEntry.enabled2 = MissingManager.matchesJsonObject(asJsonObject, "useDungeons", auxiliaryGsonEntry.enabled2);
         auxiliaryGsonEntry.count = MissingManager.computeCount(asJsonObject, "dungeonChance", auxiliaryGsonEntry.count);
         auxiliaryGsonEntry.enabled3 = MissingManager.matchesJsonObject(asJsonObject, "useStrongholds", auxiliaryGsonEntry.enabled3);
         auxiliaryGsonEntry.enabled4 = MissingManager.matchesJsonObject(asJsonObject, "useVillages", auxiliaryGsonEntry.enabled4);
         auxiliaryGsonEntry.enabled5 = MissingManager.matchesJsonObject(asJsonObject, "useMineShafts", auxiliaryGsonEntry.enabled5);
         auxiliaryGsonEntry.enabled6 = MissingManager.matchesJsonObject(asJsonObject, "useTemples", auxiliaryGsonEntry.enabled6);
         auxiliaryGsonEntry.enabled11 = MissingManager.matchesJsonObject(asJsonObject, "useMonuments", auxiliaryGsonEntry.enabled11);
         auxiliaryGsonEntry.enabled7 = MissingManager.matchesJsonObject(asJsonObject, "useRavines", auxiliaryGsonEntry.enabled7);
         auxiliaryGsonEntry.enabled8 = MissingManager.matchesJsonObject(asJsonObject, "useWaterLakes", auxiliaryGsonEntry.enabled8);
         auxiliaryGsonEntry.count2 = MissingManager.computeCount(asJsonObject, "waterLakeChance", auxiliaryGsonEntry.count2);
         auxiliaryGsonEntry.enabled9 = MissingManager.matchesJsonObject(asJsonObject, "useLavaLakes", auxiliaryGsonEntry.enabled9);
         auxiliaryGsonEntry.count3 = MissingManager.computeCount(asJsonObject, "lavaLakeChance", auxiliaryGsonEntry.count3);
         auxiliaryGsonEntry.enabled10 = MissingManager.matchesJsonObject(asJsonObject, "useLavaOceans", auxiliaryGsonEntry.enabled10);
         auxiliaryGsonEntry.count5 = MissingManager.computeCount(asJsonObject, "fixedBiome", auxiliaryGsonEntry.count5);
         if (auxiliaryGsonEntry.count5 >= 38 || auxiliaryGsonEntry.count5 < -1) {
            auxiliaryGsonEntry.count5 = -1;
         } else if (auxiliaryGsonEntry.count5 >= BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager38.count) {
            auxiliaryGsonEntry.count5 += 2;
         }

         auxiliaryGsonEntry.count6 = MissingManager.computeCount(asJsonObject, "biomeSize", auxiliaryGsonEntry.count6);
         auxiliaryGsonEntry.count7 = MissingManager.computeCount(asJsonObject, "riverSize", auxiliaryGsonEntry.count7);
         auxiliaryGsonEntry.count8 = MissingManager.computeCount(asJsonObject, "dirtSize", auxiliaryGsonEntry.count8);
         auxiliaryGsonEntry.count9 = MissingManager.computeCount(asJsonObject, "dirtCount", auxiliaryGsonEntry.count9);
         auxiliaryGsonEntry.count10 = MissingManager.computeCount(asJsonObject, "dirtMinHeight", auxiliaryGsonEntry.count10);
         auxiliaryGsonEntry.count11 = MissingManager.computeCount(asJsonObject, "dirtMaxHeight", auxiliaryGsonEntry.count11);
         auxiliaryGsonEntry.count12 = MissingManager.computeCount(asJsonObject, "gravelSize", auxiliaryGsonEntry.count12);
         auxiliaryGsonEntry.count13 = MissingManager.computeCount(asJsonObject, "gravelCount", auxiliaryGsonEntry.count13);
         auxiliaryGsonEntry.count14 = MissingManager.computeCount(asJsonObject, "gravelMinHeight", auxiliaryGsonEntry.count14);
         auxiliaryGsonEntry.count15 = MissingManager.computeCount(asJsonObject, "gravelMaxHeight", auxiliaryGsonEntry.count15);
         auxiliaryGsonEntry.count16 = MissingManager.computeCount(asJsonObject, "graniteSize", auxiliaryGsonEntry.count16);
         auxiliaryGsonEntry.count17 = MissingManager.computeCount(asJsonObject, "graniteCount", auxiliaryGsonEntry.count17);
         auxiliaryGsonEntry.count18 = MissingManager.computeCount(asJsonObject, "graniteMinHeight", auxiliaryGsonEntry.count18);
         auxiliaryGsonEntry.count19 = MissingManager.computeCount(asJsonObject, "graniteMaxHeight", auxiliaryGsonEntry.count19);
         auxiliaryGsonEntry.count20 = MissingManager.computeCount(asJsonObject, "dioriteSize", auxiliaryGsonEntry.count20);
         auxiliaryGsonEntry.count21 = MissingManager.computeCount(asJsonObject, "dioriteCount", auxiliaryGsonEntry.count21);
         auxiliaryGsonEntry.count22 = MissingManager.computeCount(asJsonObject, "dioriteMinHeight", auxiliaryGsonEntry.count22);
         auxiliaryGsonEntry.count23 = MissingManager.computeCount(asJsonObject, "dioriteMaxHeight", auxiliaryGsonEntry.count23);
         auxiliaryGsonEntry.count24 = MissingManager.computeCount(asJsonObject, "andesiteSize", auxiliaryGsonEntry.count24);
         auxiliaryGsonEntry.count25 = MissingManager.computeCount(asJsonObject, "andesiteCount", auxiliaryGsonEntry.count25);
         auxiliaryGsonEntry.count26 = MissingManager.computeCount(asJsonObject, "andesiteMinHeight", auxiliaryGsonEntry.count26);
         auxiliaryGsonEntry.count27 = MissingManager.computeCount(asJsonObject, "andesiteMaxHeight", auxiliaryGsonEntry.count27);
         auxiliaryGsonEntry.count28 = MissingManager.computeCount(asJsonObject, "coalSize", auxiliaryGsonEntry.count28);
         auxiliaryGsonEntry.count29 = MissingManager.computeCount(asJsonObject, "coalCount", auxiliaryGsonEntry.count29);
         auxiliaryGsonEntry.count30 = MissingManager.computeCount(asJsonObject, "coalMinHeight", auxiliaryGsonEntry.count30);
         auxiliaryGsonEntry.count31 = MissingManager.computeCount(asJsonObject, "coalMaxHeight", auxiliaryGsonEntry.count31);
         auxiliaryGsonEntry.count32 = MissingManager.computeCount(asJsonObject, "ironSize", auxiliaryGsonEntry.count32);
         auxiliaryGsonEntry.count33 = MissingManager.computeCount(asJsonObject, "ironCount", auxiliaryGsonEntry.count33);
         auxiliaryGsonEntry.count34 = MissingManager.computeCount(asJsonObject, "ironMinHeight", auxiliaryGsonEntry.count34);
         auxiliaryGsonEntry.count35 = MissingManager.computeCount(asJsonObject, "ironMaxHeight", auxiliaryGsonEntry.count35);
         auxiliaryGsonEntry.count36 = MissingManager.computeCount(asJsonObject, "goldSize", auxiliaryGsonEntry.count36);
         auxiliaryGsonEntry.count37 = MissingManager.computeCount(asJsonObject, "goldCount", auxiliaryGsonEntry.count37);
         auxiliaryGsonEntry.count38 = MissingManager.computeCount(asJsonObject, "goldMinHeight", auxiliaryGsonEntry.count38);
         auxiliaryGsonEntry.count39 = MissingManager.computeCount(asJsonObject, "goldMaxHeight", auxiliaryGsonEntry.count39);
         auxiliaryGsonEntry.count40 = MissingManager.computeCount(asJsonObject, "redstoneSize", auxiliaryGsonEntry.count40);
         auxiliaryGsonEntry.count41 = MissingManager.computeCount(asJsonObject, "redstoneCount", auxiliaryGsonEntry.count41);
         auxiliaryGsonEntry.count42 = MissingManager.computeCount(asJsonObject, "redstoneMinHeight", auxiliaryGsonEntry.count42);
         auxiliaryGsonEntry.count43 = MissingManager.computeCount(asJsonObject, "redstoneMaxHeight", auxiliaryGsonEntry.count43);
         auxiliaryGsonEntry.count44 = MissingManager.computeCount(asJsonObject, "diamondSize", auxiliaryGsonEntry.count44);
         auxiliaryGsonEntry.count45 = MissingManager.computeCount(asJsonObject, "diamondCount", auxiliaryGsonEntry.count45);
         auxiliaryGsonEntry.count46 = MissingManager.computeCount(asJsonObject, "diamondMinHeight", auxiliaryGsonEntry.count46);
         auxiliaryGsonEntry.count47 = MissingManager.computeCount(asJsonObject, "diamondMaxHeight", auxiliaryGsonEntry.count47);
         auxiliaryGsonEntry.count48 = MissingManager.computeCount(asJsonObject, "lapisSize", auxiliaryGsonEntry.count48);
         auxiliaryGsonEntry.count49 = MissingManager.computeCount(asJsonObject, "lapisCount", auxiliaryGsonEntry.count49);
         auxiliaryGsonEntry.count50 = MissingManager.computeCount(asJsonObject, "lapisCenterHeight", auxiliaryGsonEntry.count50);
         auxiliaryGsonEntry.count51 = MissingManager.computeCount(asJsonObject, "lapisSpread", auxiliaryGsonEntry.count51);
      } catch (Exception exception) {
      }

      return auxiliaryGsonEntry;
   }

   public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      return this.resolveAuxiliaryGsonEntry(jsonElement, type, jsonDeserializationContext);
   }
}
