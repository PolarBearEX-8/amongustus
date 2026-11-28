package net.augustus.utils.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class IncolumitasIpLookup implements IpLookupService {
   public static final String API_URL = null;

   @Override
   public IpLookupResult lookup(String str) throws Exception {
      URL url = new URL(String.format("https://api.incolumitas.com/?q=%s", str));
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder sb = new StringBuilder();
      reader.lines().forEach(sb::append);
      reader.close();
      return (IpLookupResult)GSON.fromJson(sb.toString(), IpLookupResult.class);
   }




}
