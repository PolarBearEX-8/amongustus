package client.util;

import client.event.PostMotionInterceptor;
import com.google.gson.JsonObject;

public interface RotationFormatter {
   JsonObject resolveJsonObject(PostMotionInterceptor postMotionInterceptor);

   void applyPostMotionInterceptor(PostMotionInterceptor postMotionInterceptor, JsonObject jsonObject);
}
