package client.setting;

import client.event.Listener;
import client.util.JsonPropertyGetter;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class JsonObjectValuePropertyHandler extends ValueProperty {
   public JsonObjectValuePropertyHandler(ModuleModeOption moduleModeOption, String text, Float factor) {
      super(moduleModeOption, text, factor);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      short shortValue = 14297;
      short shortValue2 = 23257;
      short shortValue3 = 1032;
      super.getJsonObject(listener, jsonObject, mapper);
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter2();
      Object value = this.getValue();
      String string = this.getString();
      jsonPropertyGetter.applyText(string, value, jsonObject);
      return jsonObject;
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonPropertyGetter jsonPropertyGetter = listener.getJsonPropertyGetter2();
      Float factor = flag ? (Float)this.getDataRef() : (Float)this.getValue();
      String string = this.getString();
      Object entry = jsonPropertyGetter.getEntry(string, factor, jsonObject);
      this.applyTarget(entry);
   }

   public JsonObjectValuePropertyHandler(ModuleModeOption moduleModeOption, String text, Float factor, Float factor2) {
      super(moduleModeOption, text, factor, factor2);
   }
}
