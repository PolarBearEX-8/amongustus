package net.augustus.cosmetics.model;

import net.augustus.cosmetics.MoreCosmeticsCore;
import net.augustus.cosmetics.molang.MolangException;
import net.augustus.cosmetics.molang.MolangParser;
import net.augustus.cosmetics.molang.ast.ConstantValue;
import net.augustus.cosmetics.molang.ast.MolangValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.math.NumberUtils;

public class AnimationKeyframeParser {

   private static boolean hasEasingType(JsonElement elem) {
      return elem.getAsJsonObject().has("easing");
   }

   private static List getEasingArgs(JsonElement elem) {
      return GeoAnimationJsonParser.convertJsonArrayToList(elem.getAsJsonObject().get("easingArgs").getAsJsonArray());
   }

   private static boolean hasEasingArgs(JsonElement elem) {
      return elem.getAsJsonObject().has("easingArgs");
   }

   private static GeoVectorKeyFrameList convertJson(List list, boolean flag, MolangParser molangParser) throws NumberFormatException, MolangException {
      Object obj = null;
      Object obj2 = null;
      Object obj3 = null;
      ArrayList list2 = new ArrayList();
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();

      for (int i = 0; i < list.size(); i++) {
         Entry entry = (Entry)list.get(i);
         Entry entry2 = i == 0 ? null : (Entry)list.get(i - 1);
         Double d = entry2 == null ? 0.0 : Double.parseDouble((String)entry2.getKey());
         Double d2 = NumberUtils.isNumber((String)entry.getKey()) ? Double.parseDouble((String)entry.getKey()) : 0.0;
         Double d3 = d2 - d;
         JsonArray keyFrameVector = getKeyFrameVector((JsonElement)entry.getValue());
         MolangValue molangValue = parseExpression(molangParser, keyFrameVector.get(0));
         MolangValue molangValue2 = parseExpression(molangParser, keyFrameVector.get(1));
         MolangValue molangValue3 = parseExpression(molangParser, keyFrameVector.get(2));
         Object obj4 = flag && molangValue instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(-molangValue.get())) : molangValue;
         Object obj5 = flag && molangValue2 instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(-molangValue2.get())) : molangValue2;
         Object obj6 = flag && molangValue3 instanceof ConstantValue ? ConstantValue.fromDouble(Math.toRadians(molangValue3.get())) : molangValue3;
         AnimationKeyframe animationKeyframe;
         AnimationKeyframe animationKeyframe2;
         AnimationKeyframe animationKeyframe3;
         if (((JsonElement)entry.getValue()).isJsonObject() && hasEasingType((JsonElement)entry.getValue())) {
            GeoEasingType easingType = getEasingType((JsonElement)entry.getValue());
            JsonElement value = (JsonElement)entry.getValue();
            if (hasEasingArgs(value)) {
               value = (JsonElement)entry.getValue();
               List easingArgs = getEasingArgs(value);
               animationKeyframe = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj4 : obj, obj4, easingType, easingArgs);
               animationKeyframe2 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj5 : obj2, obj5, easingType, easingArgs);
               animationKeyframe3 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj6 : obj3, obj6, easingType, easingArgs);
            } else {
               animationKeyframe = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj4 : obj, obj4, easingType);
               animationKeyframe2 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj5 : obj2, obj5, easingType);
               animationKeyframe3 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj6 : obj3, obj6, easingType);
            }
         } else {
            animationKeyframe = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj4 : obj, obj4);
            animationKeyframe2 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj5 : obj2, obj5);
            animationKeyframe3 = new AnimationKeyframe(GeoTickTimeUtil.secondsToTicks(d3), i == 0 ? obj6 : obj3, obj6);
         }

         obj = obj4;
         obj2 = obj5;
         obj3 = obj6;
         list2.add(animationKeyframe);
         list3.add(animationKeyframe2);
         list4.add(animationKeyframe3);
      }

      return new GeoVectorKeyFrameList(list2, list3, list4);
   }

   public static GeoVectorKeyFrameList convertJsonToKeyFrames(List list, MolangParser molangParser) throws NumberFormatException, MolangException {
      return convertJson(list, false, molangParser);
   }

   public static MolangValue parseExpression(MolangParser molangParser, JsonElement elem) throws MolangException {
      return (MolangValue)(elem.getAsJsonPrimitive().isString() ? molangParser.parseJson(elem) : ConstantValue.fromDouble(elem.getAsDouble()));
   }

   private static GeoEasingType getEasingType(JsonElement elem) {
      String asString = elem.getAsJsonObject().get("easing").getAsString();

      try {
         return GeoEasingType.valueOf(Character.toUpperCase(asString.charAt(0)) + asString.substring(1));
      } catch (Exception e) {
         MoreCosmeticsCore.LOGGER.fatal("Unknown easing type: {}", new Object[]{asString});
         throw new RuntimeException(e);
      }
   }

   private static JsonArray getKeyFrameVector(JsonElement elem) {
      return elem.isJsonArray() ? elem.getAsJsonArray() : elem.getAsJsonObject().get("vector").getAsJsonArray();
   }

   public static GeoVectorKeyFrameList convertJsonToRotationKeyFrames(List list, MolangParser molangParser) throws NumberFormatException, MolangException {
      GeoVectorKeyFrameList geoVectorKeyFrameList = convertJson(list, true, molangParser);
      return new GeoVectorKeyFrameList(geoVectorKeyFrameList.xKeyFrames, geoVectorKeyFrameList.yKeyFrames, geoVectorKeyFrameList.zKeyFrames);
   }

}
