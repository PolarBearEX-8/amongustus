package client.setting;

import client.event.Listener;
import client.util.AuxiliaryListener123Extension;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class JsonObjectValuePropertyBridge extends ValueProperty {
   public JsonObjectValuePropertyBridge(ModuleModeOption moduleModeOption, String text, Boolean[] flagArray) {
      super(moduleModeOption, text, flagArray);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension5();
      Object[] value = (Object[])this.getValue();
      String string = this.getString();
      auxiliaryListener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension5();
      Boolean[] flagArray = flag ? (Boolean[])this.getDataRef() : (Boolean[])this.getValue();
      String string = this.getString();
      Object[] entryArray = auxiliaryListener123Extension.resolveTargetArray(string, flagArray, jsonObject);
      this.applyTarget(entryArray);
   }

   public JsonObjectValuePropertyBridge(ModuleModeOption moduleModeOption, String text, Boolean[] flagArray, Boolean[] flagArray2) {
      super(moduleModeOption, text, flagArray, flagArray2);
   }
}
