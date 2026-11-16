package client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.item.IronCrown;

public class HttpsApiIncolumitasComQSManager implements AuxiliaryGsonCallback {
   public static final String labelText = "https://api.incolumitas.com/?q=%s";

   @Override
   public IronCrown resolveIronCrown(String text) throws Exception {
      URL url = new URL(String.format("https://api.incolumitas.com/?q=%s", text));
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder builder = new StringBuilder();
      reader.lines().forEach(builder::append);
      reader.close();
      return (IronCrown)gson.fromJson(builder.toString(), IronCrown.class);
   }
}
