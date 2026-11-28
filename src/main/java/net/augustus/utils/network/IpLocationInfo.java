package net.augustus.utils.network;

public class IpLocationInfo {
   public final String continent;
   public final String zip;
   public final String state;
   public final double longitude;
   public final String country;
   public final String local_time;
   public final String timezone;
   public final String city;
   public final String local_time_unix;
   public final double latitude;
   public final boolean is_dst;
   public final String country_code;

   public IpLocationInfo(
      String str,
      String str2,
      String str3,
      String str4,
      String str5,
      double d,
      double d2,
      String str6,
      String str7,
      String str8,
      String str9,
      boolean flag
   ) {
      this.continent = str;
      this.country = str2;
      this.country_code = str3;
      this.state = str4;
      this.city = str5;
      this.latitude = d;
      this.longitude = d2;
      this.zip = str6;
      this.timezone = str7;
      this.local_time = str8;
      this.local_time_unix = str9;
      this.is_dst = flag;
   }
}
