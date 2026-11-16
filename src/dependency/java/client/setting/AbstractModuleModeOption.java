package client.setting;

import client.event.Listener;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractModuleModeOption extends ModuleModeOption implements ModeOptionConfigurable {
   public ModuleModeOption moduleModeOption;
   public Consumer consumer = null;
   public Supplier supplier = null;

   public abstract Supplier getSupplier();

   @Override
   public ModuleModeOption getModuleModeOption() {
      return this.moduleModeOption;
   }

   public void setConsumer(Consumer consumer2) {
      this.consumer = consumer2;
   }

   public abstract void applySupplier(Supplier supplier2);

   public AbstractModuleModeOption(String text) {
      super(text);
      this.moduleModeOption = null;
   }

   private static void applyFunction(Function mapper, Listener listener, JsonObject jsonObject, ModeOptionConfigurable modeOptionConfigurable) {
      if ((Boolean)mapper.apply(modeOptionConfigurable)) {
         modeOptionConfigurable.getJsonObject(listener, jsonObject, mapper);
      }
   }

   public AbstractModuleModeOption(ModuleModeOption moduleModeOption2, String text) {
      super(text);
      this.moduleModeOption = moduleModeOption2;
      if (moduleModeOption2 != null) {
         moduleModeOption2.add(this);
      }
   }

   public void setSupplier(Supplier supplier2) {
      this.supplier = supplier2;
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      JsonObject jsonObject2 = new JsonObject();
      this.getListAsList().forEach(item -> applyFunction(mapper, listener, jsonObject2, (ModeOptionConfigurable)item));
      if (this.getString() != null) {
         jsonObject.add(this.getString(), jsonObject2);
         return jsonObject;
      } else {
         return jsonObject2;
      }
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonObject jsonObject2;
      if (this.getString() != null) {
         JsonElement jsonElement = jsonObject.get(this.getString());
         if (jsonElement == null || !jsonElement.isJsonObject()) {
            return;
         }

         jsonObject2 = jsonElement.getAsJsonObject();
      } else {
         jsonObject2 = jsonObject;
      }

      Iterator iterator = jsonObject2.entrySet().iterator();

      while (iterator.hasNext()) {
         JsonElement value = (JsonElement)((Entry)iterator.next()).getValue();
         if (value.isJsonObject()) {
            ModeOptionConfigurable modeOptionConfigurable = (ModeOptionConfigurable)this.getSupplier().get();
            JsonObject asJsonObject = value.getAsJsonObject();
            modeOptionConfigurable.updateListener(listener, asJsonObject, flag, mapper);
            Supplier supplier2 = () -> resolveModeOptionConfigurable(modeOptionConfigurable);
            this.applySupplier(supplier2);
         }
      }
   }

   private static ModeOptionConfigurable resolveModeOptionConfigurable(ModeOptionConfigurable modeOptionConfigurable) {
      return modeOptionConfigurable;
   }

   @Override
   public void setModuleModeOption(ModuleModeOption moduleModeOption2) {
      this.moduleModeOption = moduleModeOption2;
   }

   public AbstractModuleModeOption() {
      super(null);
   }
}
