package client.setting;

import client.event.Listener;
import client.util.Listener123Extension;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.function.Function;

public class JsonObjectValuePropertyImpl extends ValueProperty {
   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      Listener123Extension listener123Extension = listener.getListener123Extension5();
      List value = (List)this.getValue();
      String string = this.getString();
      listener123Extension.applyText(string, value, jsonObject);
      return jsonObject;
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      Listener123Extension listener123Extension = listener.getListener123Extension5();
      List list = flag ? (List)this.getDataRef() : (List)this.getValue();
      String string = this.getString();
      List list2 = listener123Extension.resolveList(string, list, jsonObject);
      this.applyTarget(list2);
   }

   public JsonObjectValuePropertyImpl(ModuleModeOption moduleModeOption, String text, List list) {
      super(moduleModeOption, text, list);
   }

   public JsonObjectValuePropertyImpl(ModuleModeOption moduleModeOption, String text, List list, List list2) {
      super(moduleModeOption, text, list, list2);
   }
}
