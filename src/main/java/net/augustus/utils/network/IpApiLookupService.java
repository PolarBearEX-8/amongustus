package net.augustus.utils.network;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class IpApiLookupService implements IpLookupService {
   private static final String API_URL = null;

   private Integer parseIntOrNull(String str) {
      try {
         return Integer.parseInt(str);
      } catch (NumberFormatException e) {
         return null;
      }
   }

   public IpLookupResult lookup(String str) throws Exception {
      long currentTimeMs = System.currentTimeMillis();
      URL url = new URL(
         String.format(
            "http://ip-api.com/json/%s?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query,hosting",
            str
         )
      );
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder sb = new StringBuilder();
      reader.lines().forEach(sb::append);
      reader.close();
      double d = (double)(System.currentTimeMillis() - currentTimeMs) / 1000.0;
      JsonObject json = (JsonObject)GSON.fromJson(sb.toString(), JsonObject.class);
      String stringOrNull = this.getStringOrNull(json, "as");
      if (stringOrNull != null) {
         stringOrNull = stringOrNull.split(" ")[0].replace("AS", "");
      }

      String stringOrNull2 = this.getStringOrNull(json, "query");
      boolean booleanOrNull = this.getBooleanOrNull(json, "mobile");
      boolean booleanOrNull2 = this.getBooleanOrNull(json, "hosting");
      boolean booleanOrNull3 = this.getBooleanOrNull(json, "proxy");
      String str7 = "isp";
      IpCompanyInfo ipCompanyInfo = new IpCompanyInfo(this.getStringOrNull(json, str7), null, null, "isp", null, null);
      int n = this.parseIntOrNull(stringOrNull);
      String stringOrNull3 = this.getStringOrNull(json, "as");
      String stringOrNull4 = this.getStringOrNull(json, "country");
      String str10 = "org";
      IpAsnInfo ipAsnInfo = new IpAsnInfo(
         n, null, null, stringOrNull3, stringOrNull4, true, this.getStringOrNull(json, str10), null, null, "isp", null, null, null, null
      );
      String stringOrNull5 = this.getStringOrNull(json, "continent");
      String stringOrNull6 = this.getStringOrNull(json, "country");
      stringOrNull3 = this.getStringOrNull(json, "countryCode");
      stringOrNull4 = this.getStringOrNull(json, "regionName");
      String stringOrNull7 = this.getStringOrNull(json, "city");
      double doubleOrNull = this.getDoubleOrNull(json, "lat");
      double doubleOrNull2 = this.getDoubleOrNull(json, "lon");
      String stringOrNull8 = this.getStringOrNull(json, "zip");
      String str19 = "timezone";
      return new IpLookupResult(
         stringOrNull2,
         null,
         false,
         booleanOrNull,
         false,
         booleanOrNull2,
         false,
         booleanOrNull3,
         false,
         ipCompanyInfo,
         ipAsnInfo,
         new IpLocationInfo(stringOrNull5, stringOrNull6, stringOrNull3, stringOrNull4, stringOrNull7, doubleOrNull, doubleOrNull2, stringOrNull8, this.getStringOrNull(json, str19), null, null, false),
         d
      );
   }


}
