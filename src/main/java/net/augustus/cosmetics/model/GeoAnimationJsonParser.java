package net.augustus.cosmetics.model;

import net.augustus.cosmetics.molang.MolangParser;
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
import java.util.Optional;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class GeoAnimationJsonParser {

   public static ArrayList getSoundEffectFrames(JsonObject json) {
      JsonObject asJsonObject = json.getAsJsonObject("sound_effects");
      return asJsonObject == null ? new ArrayList() : new ArrayList(getObjectListAsArray(asJsonObject));
   }

   public static List convertJsonArrayToList(JsonArray array) {
      return (List)new Gson().fromJson(array, ArrayList.class);
   }

   private static double calculateAnimationLength(List list) {
      double d = 0.0;

      for (GeoBoneAnimation geoBoneAnimation : (Iterable<GeoBoneAnimation>)(list)) {
         double lastKeyframeTime = geoBoneAnimation.rotationKeyFrames.getLastKeyframeTime();
         double lastKeyframeTime2 = geoBoneAnimation.positionKeyFrames.getLastKeyframeTime();
         double lastKeyframeTime3 = geoBoneAnimation.scaleKeyFrames.getLastKeyframeTime();
         d = maxDouble(new double[]{d, lastKeyframeTime, lastKeyframeTime2, lastKeyframeTime3});
      }

      return d == 0.0 ? Double.MAX_VALUE : d;
   }

   public static double maxDouble(double[] doubles) {
      double d = 0.0;

      for (double d2 : doubles) {
         d = Math.max(d2, d);
      }

      return d;
   }

   public static Entry getAnimation(JsonObject json, String str) throws JsonParseException {
      return new SimpleEntry<>(str, getElementByKey(getAnimations(json), str));
   }

   public static ArrayList getParticleEffectFrames(JsonObject json) {
      JsonObject asJsonObject = json.getAsJsonObject("particle_effects");
      return asJsonObject == null ? new ArrayList() : new ArrayList(getObjectListAsArray(asJsonObject));
   }

   public static ArrayList getCustomInstructionKeyFrames(JsonObject json) {
      JsonObject asJsonObject = json.getAsJsonObject("timeline");
      return asJsonObject == null ? new ArrayList() : new ArrayList(getObjectListAsArray(asJsonObject));
   }

   public static Set getObjectListAsArray(JsonObject json) {
      return json.entrySet();
   }

   public static GeoAnimation deserializeJsonToAnimation(Entry entry, MolangParser molangParser) throws ClassCastException, IllegalStateException {
      GeoAnimation geoAnimation = new GeoAnimation();
      JsonObject asJsonObject = ((JsonElement)entry.getValue()).getAsJsonObject();
      geoAnimation.animationName = (String)entry.getKey();
      JsonElement elem = asJsonObject.get("animation_length");
      geoAnimation.animationLength = elem == null ? null : GeoTickTimeUtil.secondsToTicks(elem.getAsDouble());
      geoAnimation.boneAnimations = new ArrayList();
      JsonElement elem2 = asJsonObject.get("loop");
      geoAnimation.loop = elem2 != null && elem2.getAsBoolean();
      ArrayList soundEffectFrames = getSoundEffectFrames(asJsonObject);
      if (soundEffectFrames != null) {
         for (Entry entry2 : (Iterable<Entry>)(soundEffectFrames)) {
            geoAnimation.soundKeyFrames
               .add(
                  new GeoEventKeyFrame(
                     Double.parseDouble((String)entry2.getKey()) * 20.0, ((JsonElement)entry2.getValue()).getAsJsonObject().get("effect").getAsString()
                  )
               );
         }
      }

      ArrayList particleEffectFrames = getParticleEffectFrames(asJsonObject);
      if (particleEffectFrames != null) {
         for (Entry entry3 : (Iterable<Entry>)(particleEffectFrames)) {
            JsonObject asJsonObject2 = ((JsonElement)entry3.getValue()).getAsJsonObject();
            JsonElement elem3 = asJsonObject2.get("effect");
            JsonElement elem4 = asJsonObject2.get("locator");
            short s = 19792;
            JsonElement elem5 = asJsonObject2.get("pre_effect_script");
            geoAnimation.particleKeyFrames
               .add(
                  new GeoParticleEventKeyFrame(
                     Double.parseDouble((String)entry3.getKey()) * 20.0,
                     elem3 == null ? "" : elem3.getAsString(),
                     elem4 == null ? "" : elem4.getAsString(),
                     elem5 == null ? "" : elem5.getAsString()
                  )
               );
         }
      }

      ArrayList list = getCustomInstructionKeyFrames(asJsonObject);
      if (list != null) {
         for (Entry entry4 : (Iterable<Entry>)(list)) {
            geoAnimation.customInstructionKeyframes.add(new GeoEventKeyFrame(Double.parseDouble((String)entry4.getKey()) * 20.0, convertJsonArrayToList(((JsonElement)entry4.getValue()).getAsJsonArray())));
         }
      }

      for (Entry entry5 : (Iterable<Entry>)(getBones(asJsonObject))) {
         GeoBoneAnimation geoBoneAnimation = new GeoBoneAnimation();
         geoBoneAnimation.boneName = (String)entry5.getKey();
         JsonObject asJsonObject3 = ((JsonElement)entry5.getValue()).getAsJsonObject();

         try {
            Set scaleKeyFrames = getScaleKeyFrames(asJsonObject3);
            geoBoneAnimation.scaleKeyFrames = AnimationKeyframeParser.convertJsonToKeyFrames(new ArrayList(scaleKeyFrames), molangParser);
         } catch (Exception e) {
            geoBoneAnimation.scaleKeyFrames = new GeoVectorKeyFrameList();
         }

         try {
            Set positionKeyFrames = getPositionKeyFrames(asJsonObject3);
            geoBoneAnimation.positionKeyFrames = AnimationKeyframeParser.convertJsonToKeyFrames(new ArrayList(positionKeyFrames), molangParser);
         } catch (Exception e2) {
            geoBoneAnimation.positionKeyFrames = new GeoVectorKeyFrameList();
         }

         try {
            Set rotationKeyFrames = getRotationKeyFrames(asJsonObject3);
            geoBoneAnimation.rotationKeyFrames = AnimationKeyframeParser.convertJsonToRotationKeyFrames(new ArrayList(rotationKeyFrames), molangParser);
         } catch (Exception e3) {
            geoBoneAnimation.rotationKeyFrames = new GeoVectorKeyFrameList();
         }

         geoAnimation.boneAnimations.add(geoBoneAnimation);
      }

      if (geoAnimation.animationLength == null) {
         geoAnimation.animationLength = calculateAnimationLength(geoAnimation.boneAnimations);
      }

      return geoAnimation;
   }

   public static Set getPositionKeyFrames(JsonObject json) {
      JsonElement elem = json.get("position");
      if (elem.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", elem.getAsJsonArray()));
      } else if (elem.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();
         JsonElement elem2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", elem2));
      } else {
         return getObjectListAsArray(elem.getAsJsonObject());
      }
   }

   private static JsonElement getElementByKey(Set set, String str) throws JsonParseException {
      Optional<Entry> optional = set.stream().filter(obj -> ((String)((Entry)obj).getKey()).equals(str)).findFirst();
      return (JsonElement)((Entry)optional.orElseThrow(() -> new JsonParseException("Could not find key: " + str))).getValue();
   }

   public static Set getAnimations(JsonObject json) {
      return getObjectListAsArray(json.getAsJsonObject("animations"));
   }

   public static Set getRotationKeyFrames(JsonObject json) {
      JsonElement elem = json.get("rotation");
      if (elem.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", elem.getAsJsonArray()));
      } else if (elem.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();
         JsonElement elem2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", elem2));
      } else {
         return getObjectListAsArray(elem.getAsJsonObject());
      }
   }

   public static List getBones(JsonObject json) {
      JsonObject asJsonObject = json.getAsJsonObject("bones");
      return asJsonObject == null ? new ArrayList() : new ArrayList(getObjectListAsArray(asJsonObject));
   }

   public static Set getScaleKeyFrames(JsonObject json) {
      JsonElement elem = json.get("scale");
      if (elem.isJsonArray()) {
         return ImmutableSet.of(new SimpleEntry<>("0", elem.getAsJsonArray()));
      } else if (elem.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();
         JsonElement elem2 = new Gson().toJsonTree(Arrays.asList(asJsonPrimitive, asJsonPrimitive, asJsonPrimitive));
         return ImmutableSet.of(new SimpleEntry<>("0", elem2));
      } else {
         return getObjectListAsArray(elem.getAsJsonObject());
      }
   }





}
