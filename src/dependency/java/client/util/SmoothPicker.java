package client.util;

import client.event.Listener;
import client.setting.ModuleModeOption;
import client.setting.ValueProperty;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class SmoothPicker extends ValueProperty {
   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      JsonPropertyGetter baseListener = listener.getBaseListener();
      String text = flag ? (String)this.getDataRef() : (String)this.getValue();
      String string = this.getString();
      Object entry = baseListener.getEntry(string, text, jsonObject);
      this.applyTarget(entry);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      short shortValue = 14297;
      short shortValue2 = 23257;
      short shortValue3 = 1032;
      super.getJsonObject(listener, jsonObject, mapper);
      JsonPropertyGetter baseListener = listener.getBaseListener();
      Object value = this.getValue();
      String string = this.getString();
      baseListener.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public SmoothPicker(ModuleModeOption moduleModeOption, String text, String text2, String text3) {
      super(moduleModeOption, text, text2, text3);
   }

   public SmoothPicker(ModuleModeOption moduleModeOption, String text, String text2) {
      super(moduleModeOption, text, text2);
   }
}
