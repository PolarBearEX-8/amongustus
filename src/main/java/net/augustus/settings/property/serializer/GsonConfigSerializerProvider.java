package net.augustus.settings.property.serializer;


import net.augustus.settings.property.ConfigSerializerProvider;
import net.augustus.settings.property.codec.BooleanJsonPropertyCodec;
import net.augustus.settings.property.codec.BooleanSetJsonCodec;
import net.augustus.settings.property.codec.DoubleJsonPropertyCodec;
import net.augustus.settings.property.codec.DoubleSetJsonCodec;
import net.augustus.settings.property.codec.FloatArrayJsonCodec;
import net.augustus.settings.property.codec.FloatListJsonCodec;
import net.augustus.settings.property.codec.IntegerJsonPropertyCodec;
import net.augustus.settings.property.codec.IntegerListJsonCodec;
import net.augustus.settings.property.codec.JsonListPropertyCodec;
import net.augustus.settings.property.codec.JsonObjectPropertyCodec;
import net.augustus.settings.property.codec.JsonPropertyCodec;
public class GsonConfigSerializerProvider implements ConfigSerializerProvider {
   public static JsonPropertyCodec JSON_ARRAY_CODEC = new JsonArrayPropertySerializer();
   public static PrimitivePropertySerializer FLOAT_CODEC = new FloatPropertySerializer();
   private static final SetPropertySerializer INTEGER_SET_CODEC = new IntegerSetPropertySerializer();
   public static JsonListPropertyCodec BOOLEAN_LIST_CODEC = new BooleanListPropertySerializer();
   public static JsonListPropertyCodec FLOAT_LIST_CODEC = new FloatListJsonCodec();
   public static PrimitivePropertySerializer BOOLEAN_CODEC = new BooleanJsonPropertyCodec();
   public static JsonPropertyCodec JSON_OBJECT_CODEC = new JsonObjectPropertyCodec();
   public static PrimitivePropertySerializer INTEGER_CODEC = new IntegerJsonPropertyCodec();
   private static final SetPropertySerializer DOUBLE_SET_CODEC = new DoubleSetJsonCodec();
   public static ArrayPropertySerializer FLOAT_ARRAY_CODEC = new FloatArrayJsonCodec();
   public static ArrayPropertySerializer BOOLEAN_ARRAY_CODEC = new BooleanArrayPropertySerializer();
   public static ArrayPropertySerializer DOUBLE_ARRAY_CODEC = new DoubleArrayPropertySerializer();
   public static JsonListPropertyCodec STRING_LIST_CODEC = new StringListPropertySerializer();
   public static PrimitivePropertySerializer DOUBLE_CODEC = new DoubleJsonPropertyCodec();
   public static PrimitivePropertySerializer LONG_CODEC = new LongPropertySerializer();
   private static final SetPropertySerializer FLOAT_SET_CODEC = new FloatSetPropertySerializer();
   private static final SetPropertySerializer STRING_SET_CODEC = new StringSetPropertySerializer();
   public static ArrayPropertySerializer STRING_ARRAY_CODEC = new StringArrayPropertySerializer();
   public static ArrayPropertySerializer INTEGER_ARRAY_CODEC = new IntegerArrayPropertySerializer();
   private static final SetPropertySerializer BOOLEAN_SET_CODEC = new BooleanSetJsonCodec();
   public static PrimitivePropertySerializer STRING_CODEC = new StringPropertySerializer();
   public static JsonListPropertyCodec DOUBLE_LIST_CODEC = new DoubleListPropertySerializer();
   public static JsonListPropertyCodec INTEGER_LIST_CODEC = new IntegerListJsonCodec();

   @Override
   public JsonListPropertyCodec getStringListCodec() {
      return STRING_LIST_CODEC;
   }

   @Override
   public ArrayPropertySerializer getDoubleArrayCodec() {
      return DOUBLE_ARRAY_CODEC;
   }

   @Override
   public JsonListPropertyCodec getIntegerListCodec() {
      return INTEGER_LIST_CODEC;
   }

   @Override
   public ArrayPropertySerializer getStringArrayCodec() {
      return STRING_ARRAY_CODEC;
   }

   @Override
   public ArrayPropertySerializer getFloatArrayCodec() {
      return FLOAT_ARRAY_CODEC;
   }

   @Override
   public ArrayPropertySerializer getBooleanArrayCodec() {
      return BOOLEAN_ARRAY_CODEC;
   }

   @Override
   public JsonPropertyCodec getLongCodec() {
      return LONG_CODEC;
   }


   @Override
   public JsonPropertyCodec getStringCodec() {
      return STRING_CODEC;
   }

   @Override
   public JsonPropertyCodec getJsonObjectCodec() {
      return JSON_OBJECT_CODEC;
   }

   @Override
   public JsonListPropertyCodec getFloatListCodec() {
      return FLOAT_LIST_CODEC;
   }

   @Override
   public JsonPropertyCodec getFloatCodec() {
      return FLOAT_CODEC;
   }

   @Override
   public JsonPropertyCodec getJsonArrayCodec() {
      return JSON_ARRAY_CODEC;
   }

   @Override
   public SetPropertySerializer getDoubleSetCodec() {
      return DOUBLE_SET_CODEC;
   }

   @Override
   public JsonPropertyCodec getDoubleCodec() {
      return DOUBLE_CODEC;
   }

   @Override
   public SetPropertySerializer getBooleanSetCodec() {
      return BOOLEAN_SET_CODEC;
   }

   @Override
   public JsonListPropertyCodec getBooleanListCodec() {
      return BOOLEAN_LIST_CODEC;
   }

   @Override
   public SetPropertySerializer getIntegerSetCodec() {
      return INTEGER_SET_CODEC;
   }

   @Override
   public ArrayPropertySerializer getIntegerArrayCodec() {
      return INTEGER_ARRAY_CODEC;
   }

   @Override
   public SetPropertySerializer getFloatSetCodec() {
      return FLOAT_SET_CODEC;
   }

   @Override
   public JsonPropertyCodec getBooleanCodec() {
      return BOOLEAN_CODEC;
   }

   @Override
   public SetPropertySerializer getStringSetCodec() {
      return STRING_SET_CODEC;
   }

   @Override
   public JsonListPropertyCodec getDoubleListCodec() {
      return DOUBLE_LIST_CODEC;
   }

   @Override
   public JsonPropertyCodec getIntegerCodec() {
      return INTEGER_CODEC;
   }



}
