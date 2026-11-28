package net.augustus.utils.network;

public class IpCompanyInfo {
   public final String type;
   public final String whois;
   public final String domain;
   public final String abuser_score;
   public final String network;
   public final String name;

   public IpCompanyInfo(String str, String str2, String str3, String str4, String str5, String str6) {
      this.name = str;
      this.abuser_score = str2;
      this.domain = str3;
      this.type = str4;
      this.network = str5;
      this.whois = str6;
   }
}
