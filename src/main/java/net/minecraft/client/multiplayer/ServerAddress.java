package net.minecraft.client.multiplayer;

import java.net.IDN;
import java.util.Hashtable;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class ServerAddress {
   private final String ipAddress;
   private final int serverPort;

   public static ServerAddress fromString(String str) {
      if (str == null) {
         return null;
      } else {
         String[] strings = str.split(":");
         if (str.startsWith("[")) {
            int n = str.indexOf("]");
            if (n > 0) {
               String str2 = str.substring(1, n);
               String str3 = str.substring(n + 1).trim();
               if (str3.startsWith(":") && str3.length() > 0) {
                  str3 = str3.substring(1);
                  strings = new String[]{str2, str3};
               } else {
                  strings = new String[]{str2};
               }
            }
         }

         if (strings.length > 2) {
            strings = new String[]{str};
         }

         String str4 = strings[0];
         int n2 = strings.length > 1 ? parseIntWithDefault(strings[1], 25565) : 25565;
         if (n2 == 25565) {
            String[] serverAddress = getServerAddress(str4);
            str4 = serverAddress[0];
            n2 = parseIntWithDefault(serverAddress[1], 25565);
         }

         return new ServerAddress(str4, n2);
      }
   }

   private static String[] getServerAddress(String str) {
      try {
         String str2 = "com.sun.jndi.dns.DnsContextFactory";
         Class.forName("com.sun.jndi.dns.DnsContextFactory");
         Hashtable hashtable = new Hashtable();
         hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
         hashtable.put("java.naming.provider.url", "dns:");
         hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
         String[] strings = new InitialDirContext(hashtable).getAttributes("_minecraft._tcp." + str, new String[]{"SRV"}).get("srv").get().toString().split(" ", 4);
         return new String[]{strings[3], strings[2]};
      } catch (Throwable e) {
         return new String[]{str, Integer.toString(25565)};
      }
   }

   public String getIP() {
      try {
         return IDN.toASCII(this.ipAddress);
      } catch (IllegalArgumentException e) {
         return this.ipAddress;
      }
   }

   public int getPort() {
      return this.serverPort;
   }

   private ServerAddress(String str, int n) {
      this.ipAddress = str;
      this.serverPort = n;
   }

   private static int parseIntWithDefault(String str, int n) {
      try {
         return Integer.parseInt(str.trim());
      } catch (Exception e) {
         return n;
      }
   }
}
