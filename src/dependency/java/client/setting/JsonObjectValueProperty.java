package client.setting;

import client.event.Listener;
import client.util.AuxiliaryListener123Extension;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class JsonObjectValueProperty extends ValueProperty {
   public JsonObjectValueProperty(ModuleModeOption moduleModeOption, String text, String[] textArray, String[] textArray2) {
      super(moduleModeOption, text, textArray, textArray2);
   }

   public JsonObjectValueProperty(ModuleModeOption moduleModeOption, String text, String[] textArray) {
      super(moduleModeOption, text, textArray);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension();
      Object[] value = (Object[])this.getValue();
      String string = this.getString();
      auxiliaryListener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension();
      String[] textArray = flag ? (String[])this.getDataRef() : (String[])this.getValue();
      String string = this.getString();
      Object[] entryArray = auxiliaryListener123Extension.resolveTargetArray(string, textArray, jsonObject);
      this.applyTarget(entryArray);
   }
}
