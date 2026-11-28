package net.labymod;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
public class LocatorValue {
   public LocatorClass locatorClassValue;
   public double[] doubleArrayValue;


   public static class Deserializer implements JsonDeserializer {
      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserialize_m1(elem, type, context);
      }

      public LocatorValue deserialize_m1(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         LocatorValue locatorValue = new LocatorValue();

         try {
            if (elem.isJsonArray()) {
               locatorValue.doubleArrayValue = (double[])context.deserialize(elem, double[].class);
               return locatorValue;
            }
         } catch (JsonParseException e) {
            throw e;
         }

         try {
            if (elem.isJsonObject()) {
               locatorValue.locatorClassValue = (LocatorClass)context.deserialize(elem, LocatorClass.class);
            }
         } catch (JsonParseException e2) {
            throw e2;
         }

         return locatorValue;
      }

   }
}
