package net.minecraft.item;

import client.util.AimReader;
import client.util.InvalidMetadataForManager;
import client.util.JsonDeserializerExtension;

public class HandlerItemEntry {
   public final JsonDeserializerExtension jsonDeserializerExtension;
   public final Class memberRef;
   public final InvalidMetadataForManager invalidMetadataForManager;

   private HandlerItemEntry(InvalidMetadataForManager invalidMetadataForManager2, JsonDeserializerExtension jsonDeserializerExtension2, Class clazz) {
      this.invalidMetadataForManager = invalidMetadataForManager2;
      this.jsonDeserializerExtension = jsonDeserializerExtension2;
      this.memberRef = clazz;
   }

   public HandlerItemEntry(InvalidMetadataForManager invalidMetadataForManager2, JsonDeserializerExtension jsonDeserializerExtension2, Class clazz, AimReader aimReader) {
      this(invalidMetadataForManager2, jsonDeserializerExtension2, clazz);
   }
}
