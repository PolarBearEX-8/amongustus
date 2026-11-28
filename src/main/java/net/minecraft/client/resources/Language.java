package net.minecraft.client.resources;

public class Language implements Comparable {
   private final String languageCode;
   private final String region;
   private final String name;
   private final boolean bidirectional;

   public Language(String str, String str2, String str3, boolean flag) {
      this.languageCode = str;
      this.region = str2;
      this.name = str3;
      this.bidirectional = flag;
   }

   @Override
   public boolean equals(Object obj) {
      return this == obj ? true : (!(obj instanceof Language) ? false : this.languageCode.equals(((Language)obj).languageCode));
   }

   @Override
   public int compareTo(Object other) {
      return this.compareTo((Language)other);
   }

   public String getLanguageCode() {
      return this.languageCode;
   }

   public int compareTo(Language other) {
      return this.languageCode.compareTo(other.languageCode);
   }

   @Override
   public int hashCode() {
      return this.languageCode.hashCode();
   }

   public boolean isBidirectional() {
      return this.bidirectional;
   }

   @Override
   public String toString() {
      return String.format("%s (%s)", this.name, this.region);
   }
}
