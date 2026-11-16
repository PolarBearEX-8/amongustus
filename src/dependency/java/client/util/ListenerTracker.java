package client.util;

import client.event.Listener;
import client.setting.ModeOptionConfigurable;
import client.setting.ModuleModeOption;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class ListenerTracker extends ModuleModeOption implements ModeOptionConfigurable {
   public ModuleModeOption moduleModeOption = null;

   @Override
   public void setModuleModeOption(ModuleModeOption moduleModeOption2) {
      this.moduleModeOption = moduleModeOption2;
   }

   private static void applyFunction(Function mapper, Listener listener, JsonObject jsonObject, ModeOptionConfigurable modeOptionConfigurable) {
      if ((Boolean)mapper.apply(modeOptionConfigurable)) {
         modeOptionConfigurable.getJsonObject(listener, jsonObject, mapper);
      }
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonObject jsonObject2;
      if (this.getString() != null) {
         JsonElement jsonElement = jsonObject.get(this.getString());
         if (jsonElement == null) {
            jsonObject2 = jsonObject;
         } else {
            if (!jsonElement.isJsonObject()) {
               return;
            }

            jsonObject2 = jsonElement.getAsJsonObject();
         }
      } else {
         jsonObject2 = jsonObject;
      }

      this.getListAsList().forEach(item -> ListenerTracker.applyFunction(mapper, listener, jsonObject2, flag, (ModeOptionConfigurable)item));
      this.updateState15();
   }

   @Override
   public ModuleModeOption getModuleModeOption() {
      return this.moduleModeOption;
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      this.updateState16();
      JsonObject jsonObject2 = new JsonObject();
      this.getListAsList().forEach(item -> ListenerTracker.applyFunction(mapper, listener, jsonObject2, (ModeOptionConfigurable)item));
      if (this.getString() != null && jsonObject != null) {
         jsonObject.add(this.getString(), jsonObject2);
         return jsonObject;
      } else {
         return jsonObject2;
      }
   }

   private static void applyFunction(Function mapper, Listener listener, JsonObject jsonObject, boolean flag, ModeOptionConfigurable modeOptionConfigurable) {
      if ((Boolean)mapper.apply(modeOptionConfigurable)) {
         modeOptionConfigurable.updateListener(listener, jsonObject, flag, mapper);
      }
   }

   public void updateState15() {
   }

   public void updateState16() {
   }

   public ListenerTracker(ModuleModeOption moduleModeOption2, String text) {
      super(text);
      this.moduleModeOption = moduleModeOption2;
      if (moduleModeOption2 != null) {
         moduleModeOption2.add(this);
      }
   }

   public ListenerTracker(String text) {
      super(text);
   }
}
