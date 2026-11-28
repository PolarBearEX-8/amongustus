package net.augustus.utils.network;

public enum IpLookupProvider implements IpLookupService {
   INCOLUMITAS("Incolumitas.com (BEST)", new IncolumitasIpLookup()),
   IP_API("ip-api.com", new IpApiLookupService());

private final IpLookupService service;
private final String displayName;



   public String getDisplayName() {
      return this.displayName;
   }

   @Override
   public IpLookupResult lookup(String str) throws Exception {
      return this.service.lookup(str);
   }

   private IpLookupProvider(String str, IpLookupService ipLookupService) {
      this.displayName = str;
      this.service = ipLookupService;
   }


}
