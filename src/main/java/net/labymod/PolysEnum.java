package net.labymod;

import com.google.gson.annotations.SerializedName;
import java.io.IOException;

public enum PolysEnum {
   @SerializedName("quad_list")
   QUAD_LIST,
   @SerializedName("tri_list")
   TRI_LIST;

   public static PolysEnum forValue(String str) throws IOException {
      if (str.equals("quad_list")) {
         return QUAD_LIST;
      }

      if (str.equals("tri_list")) {
         return TRI_LIST;
      }

      throw new IOException("Cannot deserialize PolysEnum");
   }

}
