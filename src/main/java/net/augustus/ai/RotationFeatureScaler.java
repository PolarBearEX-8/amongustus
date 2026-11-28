package net.augustus.ai;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class RotationFeatureScaler {
   private final List featureSpecs;
   private final int version;
   private final List targetSpecs;

   private static float normalizeValue(FeatureNormalizationSpec featureNormalizationSpec, float f) {
      switch (featureNormalizationSpec.method) {
         case MINMAX:
            float f2 = f < featureNormalizationSpec.lo ? featureNormalizationSpec.lo : (f > featureNormalizationSpec.hi ? featureNormalizationSpec.hi : f);
            return 2.0F * (f2 - featureNormalizationSpec.lo) / (featureNormalizationSpec.hi - featureNormalizationSpec.lo) - 1.0F;
         case DIVIDE:
            return f / featureNormalizationSpec.lo;
         case CLAMP:
            return f < featureNormalizationSpec.lo ? featureNormalizationSpec.lo : (f > featureNormalizationSpec.hi ? featureNormalizationSpec.hi : f);
         case IDENTITY:
            return f;
         default:
            throw new IllegalStateException("Unknown method: " + featureNormalizationSpec.method);
      }
   }

   private static List parseSpecs(JsonArray array) {
      ArrayList list = new ArrayList(array.size());

      for (JsonElement elem : array) {
         JsonObject asJsonObject = elem.getAsJsonObject();
         String asString = asJsonObject.get("name").getAsString();
         FeatureScalingMethod featureScalingMethod = parseMethod(asJsonObject.get("method").getAsString());
         float asFloat = asJsonObject.get("lo").getAsFloat();
         float asFloat2 = asJsonObject.get("hi").getAsFloat();
         list.add(new FeatureNormalizationSpec(asString, featureScalingMethod, asFloat, asFloat2));
      }

      return list;
   }

   public static RotationFeatureScaler fromJson(JsonElement elem) {
      JsonObject asJsonObject = elem.getAsJsonObject();
      int n = asJsonObject.has("version") ? asJsonObject.get("version").getAsInt() : 1;
      List list = parseSpecs(asJsonObject.getAsJsonArray("features"));
      List list2 = parseSpecs(asJsonObject.getAsJsonArray("targets"));
      return new RotationFeatureScaler(n, list, list2);
   }

   public int getVersion() {
      return this.version;
   }

   public static RotationFeatureScaler fromFile(File file) throws IOException {

      RotationFeatureScaler rotationFeatureScaler;
      try (
         FileInputStream fileInputStream = new FileInputStream(file);
         InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
      ) {
         rotationFeatureScaler = fromJson(new JsonParser().parse(inputStreamReader));
      }

      return rotationFeatureScaler;
   }

   public int getTargetCount() {
      return this.targetSpecs.size();
   }

   public void normalizeInPlace(float[] floats) {
      if (floats.length != this.featureSpecs.size()) {
         throw new IllegalArgumentException("feature length mismatch: got " + floats.length + ", expected " + this.featureSpecs.size());
      } else {
         for (int i = 0; i < floats.length; i++) {
            floats[i] = normalizeValue((FeatureNormalizationSpec)this.featureSpecs.get(i), floats[i]);
         }
      }
   }

   public static RotationFeatureScaler fromResource(String str) throws IOException {

      RotationFeatureScaler rotationFeatureScaler;
      try (InputStream resourceAsStream = RotationFeatureScaler.class.getResourceAsStream(str)) {
         if (resourceAsStream == null) {
            throw new IOException("normalization resource not found: " + str);
         }

         try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8)) {
            rotationFeatureScaler = fromJson(new JsonParser().parse(inputStreamReader));
         }
      }

      return rotationFeatureScaler;
   }

   private static FeatureScalingMethod parseMethod(String str) {
      switch (str) {
         case "minmax":
            return FeatureScalingMethod.MINMAX;
         case "divide":
            return FeatureScalingMethod.DIVIDE;
         case "clamp":
            return FeatureScalingMethod.CLAMP;
         case "identity":
            return FeatureScalingMethod.IDENTITY;
         default:
            throw new IllegalArgumentException("Unknown normalization method: " + str);
      }
   }

   public FeatureNormalizationSpec getTargetSpec(int n) {
      return (FeatureNormalizationSpec)this.targetSpecs.get(n);
   }

   private RotationFeatureScaler(int n, List list, List list2) {
      this.version = n;
      this.featureSpecs = list;
      this.targetSpecs = list2;
   }

   public float denormalizeTarget(int n, float f) {
      FeatureNormalizationSpec featureNormalizationSpec = (FeatureNormalizationSpec)this.targetSpecs.get(n);
      switch (featureNormalizationSpec.method) {
         case MINMAX:
            return (f + 1.0F) * 0.5F * (featureNormalizationSpec.hi - featureNormalizationSpec.lo) + featureNormalizationSpec.lo;
         case DIVIDE:
            return f * featureNormalizationSpec.lo;
         case CLAMP:
         case IDENTITY:
            return f;
         default:
            throw new IllegalStateException("Unknown method: " + featureNormalizationSpec.method);
      }
   }

   public int getFeatureCount() {
      return this.featureSpecs.size();
   }

}
