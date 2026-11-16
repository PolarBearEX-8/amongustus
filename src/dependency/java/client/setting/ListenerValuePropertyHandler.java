package client.setting;

import client.event.Listener;
import client.util.AuxiliaryListener123Extension;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class ListenerValuePropertyHandler extends ValueProperty {
   public ListenerValuePropertyHandler(ModuleModeOption moduleModeOption, String text, Integer[] countArray, Integer[] countArray2) {
      super(moduleModeOption, text, countArray, countArray2);
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension3();
      Integer[] countArray = flag ? (Integer[])this.getDataRef() : (Integer[])this.getValue();
      String string = this.getString();
      Object[] entryArray = auxiliaryListener123Extension.resolveTargetArray(string, countArray, jsonObject);
      this.applyTarget(entryArray);
   }

   public ListenerValuePropertyHandler(ModuleModeOption moduleModeOption, String text, Integer[] countArray) {
      super(moduleModeOption, text, countArray);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension3();
      Object[] value = (Object[])this.getValue();
      String string = this.getString();
      auxiliaryListener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }
}
