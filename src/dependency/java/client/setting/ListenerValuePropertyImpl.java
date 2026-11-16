package client.setting;

import client.event.Listener;
import client.util.AuxiliaryListener123Extension;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class ListenerValuePropertyImpl extends ValueProperty {
   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension2();
      Float[] factorArray = flag ? (Float[])this.getDataRef() : (Float[])this.getValue();
      String string = this.getString();
      Object[] entryArray = auxiliaryListener123Extension.resolveTargetArray(string, factorArray, jsonObject);
      this.applyTarget(entryArray);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension2();
      Object[] value = (Object[])this.getValue();
      String string = this.getString();
      auxiliaryListener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public ListenerValuePropertyImpl(ModuleModeOption moduleModeOption, String text, Float[] factorArray) {
      super(moduleModeOption, text, factorArray);
   }

   public ListenerValuePropertyImpl(ModuleModeOption moduleModeOption, String text, Float[] factorArray, Float[] factorArray2) {
      super(moduleModeOption, text, factorArray, factorArray2);
   }
}
