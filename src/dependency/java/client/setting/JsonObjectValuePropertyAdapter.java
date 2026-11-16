package client.setting;

import client.event.Listener;
import client.event.PreMotionNotifier;
import com.google.gson.JsonObject;
import java.util.Set;
import java.util.function.Function;

public class JsonObjectValuePropertyAdapter extends ValueProperty {
   @Override
   public JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper) {
      super.getJsonObject(listener, jsonObject, mapper);
      PreMotionNotifier preMotionNotifier = listener.getPreMotionNotifier3();
      Set value = (Set)this.getValue();
      String string = this.getString();
      preMotionNotifier.applyText(string, value, jsonObject);
      return jsonObject;
   }

   public JsonObjectValuePropertyAdapter(ModuleModeOption moduleModeOption, String text, Set set, Set set2) {
      super(moduleModeOption, text, set, set2);
   }

   public JsonObjectValuePropertyAdapter(ModuleModeOption moduleModeOption, String text, Set set) {
      super(moduleModeOption, text, set);
   }

   @Override
   public void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper) {
      PreMotionNotifier preMotionNotifier = listener.getPreMotionNotifier3();
      Set set = flag ? (Set)this.getDataRef() : (Set)this.getValue();
      String string = this.getString();
      Set set2 = preMotionNotifier.resolveSet(string, set, jsonObject);
      this.applyTarget(set2);
   }
}
