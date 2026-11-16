package client.util;

import client.event.PostMotionInterceptor;
import client.setting.Setting;
import com.google.gson.JsonObject;

public interface NameParentNameRotationFormatter extends RotationFormatter {
   @Override
   default JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor) {
      Setting setting = (Setting)postMotionInterceptor;
      return this.resolveJsonObject(setting);
   }

   default JsonObject resolveJsonObject(Setting setting) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("Name", setting.getString());
      jsonObject.addProperty("ParentName", setting.getTextAsText());
      return jsonObject;
   }
}
