package net.augustus.settings.property.codec;


import net.augustus.settings.property.serializer.GsonConfigSerializerProvider;
public interface JsonCodecRegistryHolder {
   GsonConfigSerializerProvider GSON_SERIALIZER_PROVIDER = new GsonConfigSerializerProvider();
}
