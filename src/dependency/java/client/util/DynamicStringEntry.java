package client.util;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.item.IronCrown;

public class DynamicStringEntry implements AuxiliaryGsonCallback {
   public static final String labelText = "http://ip-api.com/json/%s?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query,hosting";

   @Override
   public IronCrown resolveIronCrown(String text) throws Exception {
      long timestamp = System.currentTimeMillis();
      URL url = new URL(
         String.format(
            "http://ip-api.com/json/%s?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query,hosting",
            text
         )
      );
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder builder = new StringBuilder();
      reader.lines().forEach(builder::append);
      reader.close();
      double offset = (double)(System.currentTimeMillis() - timestamp) / 1000.0;
      JsonObject jsonObject = (JsonObject)gson.fromJson(builder.toString(), JsonObject.class);
      String text2 = "as";
      String text3 = this.resolveText(jsonObject, text2);
      if (text3 != null) {
         text3 = text3.split(" ")[0].replace("AS", "");
      }

      String text4 = "query";
      String text5 = this.resolveText(jsonObject, text4);
      String text6 = "mobile";
      boolean flag = this.resolveBoolean(jsonObject, text6);
      String text7 = "hosting";
      boolean flag2 = this.resolveBoolean(jsonObject, text7);
      String text8 = "proxy";
      boolean flag3 = this.resolveBoolean(jsonObject, text8);
      String text9 = "isp";
      AnglePredictor anglePredictor = new AnglePredictor(this.resolveText(jsonObject, text9), null, null, "isp", null, null);
      int count = this.resolveInteger(text3);
      String text10 = "as";
      String text11 = this.resolveText(jsonObject, text10);
      String text12 = "country";
      String text13 = this.resolveText(jsonObject, text12);
      String text14 = "org";
      SmoothReader smoothReader = new SmoothReader(
         count, null, null, text11, text13, true, this.resolveText(jsonObject, text14), null, null, "isp", null, null, null, null
      );
      String text15 = "continent";
      String text16 = this.resolveText(jsonObject, text15);
      String text17 = "country";
      String text18 = this.resolveText(jsonObject, text17);
      String text19 = "countryCode";
      text11 = this.resolveText(jsonObject, text19);
      String text20 = "regionName";
      text13 = this.resolveText(jsonObject, text20);
      String text21 = "city";
      String text22 = this.resolveText(jsonObject, text21);
      String text23 = "lat";
      double offset2 = this.resolveDouble(jsonObject, text23);
      String text24 = "lon";
      double offset3 = this.resolveDouble(jsonObject, text24);
      String text25 = "zip";
      String text26 = this.resolveText(jsonObject, text25);
      String text27 = "timezone";
      return new IronCrown(
         text5,
         null,
         false,
         flag,
         false,
         flag2,
         false,
         flag3,
         false,
         anglePredictor,
         smoothReader,
         new SplineDeserializer(
            text16, text18, text11, text13, text22, offset2, offset3, text26, this.resolveText(jsonObject, text27), null, null, false
         ),
         offset
      );
   }

   private Integer resolveInteger(String text) {
      try {
         return Integer.parseInt(text);
      } catch (NumberFormatException numberFormatException) {
         return null;
      }
   }
}
