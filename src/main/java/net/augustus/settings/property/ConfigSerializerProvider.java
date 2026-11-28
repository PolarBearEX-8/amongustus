package net.augustus.settings.property;


import net.augustus.settings.property.codec.JsonListPropertyCodec;
import net.augustus.settings.property.codec.JsonPropertyCodec;
import net.augustus.settings.property.serializer.ArrayPropertySerializer;
import net.augustus.settings.property.serializer.SetPropertySerializer;
public interface ConfigSerializerProvider {
   JsonPropertyCodec getJsonObjectCodec();

   JsonPropertyCodec getFloatCodec();

   JsonPropertyCodec getDoubleCodec();

   JsonPropertyCodec getIntegerCodec();

   SetPropertySerializer getStringSetCodec();

   JsonListPropertyCodec getStringListCodec();

   ArrayPropertySerializer getBooleanArrayCodec();

   JsonListPropertyCodec getBooleanListCodec();

   SetPropertySerializer getDoubleSetCodec();

   JsonListPropertyCodec getFloatListCodec();

   SetPropertySerializer getIntegerSetCodec();

   ArrayPropertySerializer getStringArrayCodec();

   JsonListPropertyCodec getDoubleListCodec();

   SetPropertySerializer getFloatSetCodec();

   SetPropertySerializer getBooleanSetCodec();

   JsonPropertyCodec getLongCodec();

   ArrayPropertySerializer getFloatArrayCodec();

   JsonPropertyCodec getJsonArrayCodec();

   JsonPropertyCodec getStringCodec();

   ArrayPropertySerializer getDoubleArrayCodec();

   ArrayPropertySerializer getIntegerArrayCodec();

   JsonPropertyCodec getBooleanCodec();

   JsonListPropertyCodec getIntegerListCodec();
}
