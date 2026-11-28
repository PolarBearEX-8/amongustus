package com.thealtening.api.response;

public class License {
   private String username;
   private boolean hasLicense;
   private String licenseType;
   private String expires;

   public boolean hasLicense() {
      return this.hasLicense;
   }

   public String getExpires() {
      return this.expires;
   }

   @Override
   public String toString() {
      return String.format("License[%s:%s:%s:%s]", this.username, this.hasLicense, this.licenseType, this.expires);
   }

   public String getUsername() {
      return this.username;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof License)) {
         return false;
      } else {
         License license = (License)obj;
         return license.getExpires().equals(this.getExpires()) && license.getLicenseType().equals(this.getLicenseType()) && license.hasLicense() == this.hasLicense() && license.getUsername().equals(this.getUsername());
      }
   }

   public String getLicenseType() {
      return this.licenseType;
   }
}
