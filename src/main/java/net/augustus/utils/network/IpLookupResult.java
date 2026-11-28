package net.augustus.utils.network;

public class IpLookupResult {
   public final double elapsed_ms;
   public final boolean is_tor;
   public final boolean is_bogon;
   public final IpLocationInfo location;
   public final String rir;
   public final boolean is_mobile;
   public final IpAsnInfo asn;
   public final IpCompanyInfo company;
   public final boolean is_proxy;
   public final String ip;
   public final boolean is_datacenter;
   public final boolean is_vpn;
   public final boolean is_crawler;

   public IpLookupResult(
      String str,
      String str2,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      IpCompanyInfo ipCompanyInfo,
      IpAsnInfo ipAsnInfo,
      IpLocationInfo ipLocationInfo,
      double d
   ) {
      this.ip = str;
      this.rir = str2;
      this.is_bogon = flag;
      this.is_mobile = flag2;
      this.is_crawler = flag3;
      this.is_datacenter = flag4;
      this.is_tor = flag5;
      this.is_proxy = flag6;
      this.is_vpn = flag7;
      this.company = ipCompanyInfo;
      this.asn = ipAsnInfo;
      this.location = ipLocationInfo;
      this.elapsed_ms = d;
   }



}
