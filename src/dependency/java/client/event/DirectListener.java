package client.event;

import client.setting.ModeOptionConfigurable;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.function.Function;

public interface DirectListener extends PostMotionInterceptor {
   List getListAsList();

   void add(ModeOptionConfigurable modeOptionConfigurable);

   void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper);

   JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper);
}
