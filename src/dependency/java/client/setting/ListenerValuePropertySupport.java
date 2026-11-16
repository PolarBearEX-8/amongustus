package client.setting;

import client.event.Listener;
import client.util.AuxiliaryListener123Extension;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class ListenerValuePropertySupport extends ValueProperty {
   public ListenerValuePropertySupport(ModuleModeOption moduleModeOption, String text, Double[] offsetArray) {
      super(moduleModeOption, text, offsetArray);
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension4();
      Double[] offsetArray = flag ? (Double[])this.getDataRef() : (Double[])this.getValue();
      String string = this.getString();
      Object[] entryArray = auxiliaryListener123Extension.resolveTargetArray(string, offsetArray, jsonObject);
      this.applyTarget(entryArray);
   }

   public ListenerValuePropertySupport(ModuleModeOption moduleModeOption, String text, Double[] offsetArray, Double[] offsetArray2) {
      super(moduleModeOption, text, offsetArray, offsetArray2);
   }

   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      AuxiliaryListener123Extension auxiliaryListener123Extension = listener.getAuxiliaryListener123Extension4();
      Object[] value = (Object[])this.getValue();
      String string = this.getString();
      auxiliaryListener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }
}
