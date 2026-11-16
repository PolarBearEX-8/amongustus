package client.util;

import client.event.Listener;
import client.setting.ModuleModeOption;
import client.setting.ValueProperty;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class InternalListenerCoordinator extends ValueProperty {
   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonPropertyGetter jsonPropertyGetterAsJsonPropertyGett = listener.getJsonPropertyGetterAsJsonPropertyGett();
      Integer count = flag ? (Integer)this.getDataRef() : (Integer)this.getValue();
      String string = this.getString();
      Object entry = jsonPropertyGetterAsJsonPropertyGett.getEntry(string, count, jsonObject);
      this.applyTarget(entry);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      short shortValue = 14297;
      short shortValue2 = 23257;
      short shortValue3 = 1032;
      super.getJsonObject(listener, jsonObject, mapper);
      JsonPropertyGetter jsonPropertyGetterAsJsonPropertyGett = listener.getJsonPropertyGetterAsJsonPropertyGett();
      Object value = this.getValue();
      String string = this.getString();
      jsonPropertyGetterAsJsonPropertyGett.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public InternalListenerCoordinator(ModuleModeOption moduleModeOption, String text, Integer count, Integer count2) {
      super(moduleModeOption, text, count, count2);
   }

   public InternalListenerCoordinator(ModuleModeOption moduleModeOption, String text, Integer count) {
      super(moduleModeOption, text, count);
   }
}
