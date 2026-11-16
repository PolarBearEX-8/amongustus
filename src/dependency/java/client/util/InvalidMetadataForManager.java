package client.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.item.HandlerItemEntry;
import net.minecraft.util.ChatComponentSerializer;
import net.minecraft.util.IChatComponent;

public class InvalidMetadataForManager {
   public final AuxiliaryIterableExtension auxiliaryIterableExtension = new AddingDuplicateKeyManager();
   public final GsonBuilder gsonBuilder = new GsonBuilder();
   public Gson gson;

   private Gson create() {
      if (this.gson == null) {
         this.gson = this.gsonBuilder.create();
      }

      return this.gson;
   }

   public void applyJsonDeserializerExtension(JsonDeserializerExtension jsonDeserializerExtension, Class clazz) {
      this.auxiliaryIterableExtension.applyTarget(jsonDeserializerExtension.getText(), new HandlerItemEntry(this, jsonDeserializerExtension, clazz, null));
      this.gsonBuilder.registerTypeAdapter(clazz, jsonDeserializerExtension);
      this.gson = null;
   }

   public IMetadataSection resolveIMetadataSection(String text, JsonObject jsonObject) {
      if (text == null) {
         throw new IllegalArgumentException("Metadata section name cannot be null");
      } else if (!jsonObject.has(text)) {
         return (IMetadataSection)null;
      } else if (!jsonObject.get(text).isJsonObject()) {
         throw new IllegalArgumentException("Invalid metadata for '" + text + "' - expected object, found " + jsonObject.get(text));
      } else {
         HandlerItemEntry object = (HandlerItemEntry)this.auxiliaryIterableExtension.getObject(text);
         if (object == null) {
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + text + "'");
         } else {
            return (IMetadataSection)this.create().fromJson(jsonObject.getAsJsonObject(text), object.memberRef);
         }
      }
   }

   public InvalidMetadataForManager() {
      this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new ChatComponentSerializer());
      this.gsonBuilder.registerTypeHierarchyAdapter(StyleHasParentManager.class, new BoldJsonSerializer());
      this.gsonBuilder.registerTypeAdapterFactory(new TypeAdapterFactoryAdapter());
   }
}
