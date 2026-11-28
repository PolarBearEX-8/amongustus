package net.augustus.cosmetics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TrustedHostWhitelist {
   private static Set officialHosts;
   private static Set thirdPartyHosts;

   static {

      officialHosts = new HashSet();
      thirdPartyHosts = new HashSet();
      officialHosts.add("https://cl.cosmeticsmod.com/");
      officialHosts.add("https://dl.cosmeticsmod.com/");
      officialHosts.add("http://dl.cosmeticsmod.com/");
      officialHosts.add("http://logo.cosmeticsmod.com/");
      thirdPartyHosts.add("https://cdn.discordapp.com/");
      thirdPartyHosts.add("https://media.discordapp.net/");
   }

   public static boolean isOfficialHost(String str) {
      return matchesAnyPrefix(str, officialHosts);
   }

   public static boolean isTrustedHost(String str) {
      if (!matchesAnyPrefix(str, thirdPartyHosts)) {
         if (!matchesAnyPrefix(str, officialHosts)) {
            return false;
         }
      }

      return true;
   }

   private static boolean matchesAnyPrefix(String str, Collection items) {
      String str2 = str.toLowerCase();

      for (String str3 : (Iterable<String>)(items)) {
         if (str2.startsWith(str3)) {
            return true;
         }
      }

      return false;
   }



}
