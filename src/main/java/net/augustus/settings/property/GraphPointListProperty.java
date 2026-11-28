package net.augustus.settings.property;

import net.augustus.settings.GraphSetting;
import net.augustus.settings.graph.GraphPoint;
import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.utils.UnaryFunction;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;

public class GraphPointListProperty extends AbstractConfigProperty {
   private final List defaultPointCoords;
   private final GraphSetting graphSetting;

   public GraphPointListProperty(GraphSetting graphSetting2, String str, List list) {
       this(graphSetting2, str, list, new JsonArray());
   }

   private GraphPointListProperty(GraphSetting graphSetting2, String str, List list, JsonArray array) {
       super(graphSetting2, str, array, array);
      this.graphSetting = graphSetting2;
      this.defaultPointCoords = list;
      this.setDefaultValue(this.serializeDefaultPoints());
   }

   private void applyPointsFromJson(JsonArray array) {
      List points = this.graphSetting.getPoints();
      points.clear();

      for (JsonElement elem : array) {
         if (elem != null) {
            double d;
            double d2;
            if (elem.isJsonObject()) {
               JsonObject asJsonObject = elem.getAsJsonObject();
               d = asJsonObject.has("X") ? asJsonObject.get("X").getAsDouble() : 0.0;
               d2 = asJsonObject.has("Y") ? asJsonObject.get("Y").getAsDouble() : 0.0;
            } else {
               if (!elem.isJsonArray()) {
                  continue;
               }

               JsonArray asJsonArray = elem.getAsJsonArray();
               d = asJsonArray.size() > 0 ? asJsonArray.get(0).getAsDouble() : 0.0;
               d2 = asJsonArray.size() > 1 ? asJsonArray.get(1).getAsDouble() : 0.0;
            }

            points.add(this.graphSetting.getPointBuilder().buildAt(d, d2));
         }
      }

      if (!points.isEmpty()) {
         ((GraphPoint)points.get(0)).getOnChangeCallback().run();
      }
   }

   private JsonArray serializeDefaultPoints() {
      JsonArray array = new JsonArray();
      if (this.defaultPointCoords == null) {
         return array;
      } else {
         for (double[] doubles : (Iterable<double[]>)(this.defaultPointCoords)) {
            if (doubles != null && doubles.length >= 2) {
               JsonObject json = new JsonObject();
               json.addProperty("X", doubles[0]);
               json.addProperty("Y", doubles[1]);
               array.add(json);
            }
         }

         return array;
      }
   }

   public void deserialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, boolean flag, UnaryFunction unaryFunction) {
      if (!json.has(this.getName())) {
         if (flag) {
            this.applyPointsFromJson((JsonArray)this.getDefaultValue());
         }
      } else {
         JsonArray array3 = (JsonArray)configSerializerProvider.getJsonArrayCodec().read(this.getName(), new JsonArray(), json);
         this.applyPointsFromJson(array3);
         this.applyValue(array3);
      }
   }

   @Override
   public JsonObject serialize(ConfigSerializerProvider configSerializerProvider, JsonObject json, UnaryFunction unaryFunction) {
      super.serialize(configSerializerProvider, json, unaryFunction);
      JsonArray array = this.serializeCurrentPoints();
      configSerializerProvider.getJsonArrayCodec().write(this.getName(), array, json);
      this.setValue(array);
      return json;
   }

   private JsonArray serializeCurrentPoints() {
      JsonArray array = new JsonArray();

      for (GraphPoint graphPoint : (Iterable<GraphPoint>)(this.graphSetting.getPoints())) {
         JsonObject json = new JsonObject();
         json.addProperty("X", graphPoint.getX());
         json.addProperty("Y", graphPoint.getY());
         array.add(json);
      }

      return array;
   }
}
