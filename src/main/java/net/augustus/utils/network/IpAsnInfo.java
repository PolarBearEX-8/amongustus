package net.augustus.utils.network;

public class IpAsnInfo {
   public final int asn;
   public final String rir;
   public final String country;
   public final String created;
   public final String domain;
   public final String updated;
   public final boolean active;
   public final String descr;
   public final String abuser_score;
   public final String route;
   public final String whois;
   public final String org;
   public final String type;
   public final String abuse;

   public IpAsnInfo(
      int n,
      String str,
      String str2,
      String str3,
      String str4,
      boolean flag,
      String str5,
      String str6,
      String str7,
      String str8,
      String str9,
      String str10,
      String str11,
      String str12
   ) {
      this.asn = n;
      this.abuser_score = str;
      this.route = str2;
      this.descr = str3;
      this.country = str4;
      this.active = flag;
      this.org = str5;
      this.domain = str6;
      this.abuse = str7;
      this.type = str8;
      this.created = str9;
      this.updated = str10;
      this.rir = str11;
      this.whois = str12;
   }
}
