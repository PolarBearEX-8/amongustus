package net.labymod;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
public class UvUnion {
   public double[] boxUVCoords;
   public UvFaces faceUV;
   public boolean isBoxUV;


   public static class Deserializer implements JsonDeserializer {
      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserialize_m1(elem, type, context);
      }

      public UvUnion deserialize_m1(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         UvUnion uvUnion = new UvUnion();

         try {
            if (elem.isJsonNull()) {
               return null;
            }
         } catch (JsonParseException e) {
            throw e;
         }

         try {
            if (elem.isJsonArray()) {
               uvUnion.boxUVCoords = (double[])context.deserialize(elem, double[].class);
               uvUnion.isBoxUV = true;
               return uvUnion;
            }
         } catch (JsonParseException e2) {
            throw e2;
         }

         try {
            if (elem.isJsonObject()) {
               uvUnion.faceUV = (UvFaces)context.deserialize(elem, UvFaces.class);
               uvUnion.isBoxUV = false;
            }
         } catch (JsonParseException e3) {
            throw e3;
         }

         return uvUnion;
      }

   }
}
