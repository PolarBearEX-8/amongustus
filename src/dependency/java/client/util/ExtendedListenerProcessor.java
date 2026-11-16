package client.util;

import client.event.Listener;
import client.setting.ModuleModeOption;
import client.setting.ValueProperty;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class ExtendedListenerProcessor extends ValueProperty {
   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter5();
      Double offset = flag ? (Double)this.getDataRef() : (Double)this.getValue();
      String string = this.getString();
      Object entry = jsonPropertyGetter.getEntry(string, offset, jsonObject);
      this.applyTarget(entry);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      short shortValue = 14297;
      short shortValue2 = 23257;
      short shortValue3 = 1032;
      super.getJsonObject(listener, jsonObject, mapper);
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter5();
      Object value = this.getValue();
      String string = this.getString();
      jsonPropertyGetter.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public ExtendedListenerProcessor(ModuleModeOption moduleModeOption, String text, double offset) {
      super(moduleModeOption, text, offset);
   }

   public ExtendedListenerProcessor(ModuleModeOption moduleModeOption, String text, double offset, double offset2) {
      super(moduleModeOption, text, offset, offset2);
   }
}
