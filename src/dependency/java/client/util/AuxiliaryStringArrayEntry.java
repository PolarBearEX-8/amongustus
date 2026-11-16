package client.util;

import client.event.Listener;
import client.setting.ModuleModeOption;
import client.setting.ValueProperty;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class AuxiliaryStringArrayEntry extends ValueProperty {
   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter3();
      Boolean flag2 = flag ? (Boolean)this.getDataRef() : (Boolean)this.getValue();
      String string = this.getString();
      Object entry = jsonPropertyGetter.getEntry(string, flag2, jsonObject);
      this.applyTarget(entry);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      short shortValue = 14297;
      short shortValue2 = 23257;
      short shortValue3 = 1032;
      super.getJsonObject(listener, jsonObject, mapper);
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter3();
      Object value = this.getValue();
      String string = this.getString();
      jsonPropertyGetter.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public AuxiliaryStringArrayEntry(ModuleModeOption moduleModeOption, String text, Boolean flag) {
      super(moduleModeOption, text, flag);
   }

   public AuxiliaryStringArrayEntry(ModuleModeOption moduleModeOption, String text, Boolean flag, Boolean flag2) {
      super(moduleModeOption, text, flag, flag2);
   }
}
