package client.setting;

import client.event.Listener;
import client.event.PostMotionInterceptor;
import com.google.gson.JsonObject;
import java.util.function.Function;

public interface ModeOptionConfigurable extends PostMotionInterceptor {
   void setModuleModeOption(ModuleModeOption moduleModeOption);

   void updateListener(Listener listener, JsonObject jsonObject, boolean flag, Function mapper);

   ModuleModeOption getModuleModeOption();

   JsonObject getJsonObject(Listener listener, JsonObject jsonObject, Function mapper);
}
