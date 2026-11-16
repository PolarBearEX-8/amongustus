package client.util;

import java.net.IDN;
import java.util.Hashtable;
import javax.naming.directory.InitialDirContext;

public class JavaNamingFactoryInitialManager {
   public final String labelText;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public static JavaNamingFactoryInitialManager resolveJavaNamingFactoryInitialMana(String text) {
      if (text == null) {
         return null;
      } else {
         String[] textArray = text.split(":");
         if (text.startsWith("[")) {
            int count2 = text.indexOf("]");
            if (count2 > 0) {
               String text2 = text.substring(1, count2);
               String text3 = text.substring(count2 + 1).trim();
               if (text3.startsWith(":") && text3.length() > 0) {
                  String text4 = text3.substring(1);
                  textArray = new String[]{text2, text4};
               } else {
                  textArray = new String[]{text2};
               }
            }
         }

         if (textArray.length > 2) {
            textArray = new String[]{text};
         }

         String text5 = textArray[0];
         int count3 = textArray.length > 1 ? computeCount(textArray[1], 25565) : 25565;
         if (count3 == 25565) {
            String[] textArray2 = resolveTextArray(text5);
            text5 = textArray2[0];
            count3 = computeCount(textArray2[1], 25565);
         }

         return new JavaNamingFactoryInitialManager(text5, count3);
      }
   }

   private static String[] resolveTextArray(String text) {
      try {
         Class.forName("com.sun.jndi.dns.DnsContextFactory");
         Hashtable hashtable = new Hashtable();
         hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
         hashtable.put("java.naming.provider.url", "dns:");
         hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
         String[] textArray = new InitialDirContext(hashtable).getAttributes("_minecraft._tcp." + text, new String[]{"SRV"}).get("srv").get().toString().split(" ", 4);
         return new String[]{textArray[3], textArray[2]};
      } catch (Throwable throwable) {
         return new String[]{text, Integer.toString(25565)};
      }
   }

   public String toASCII() {
      try {
         return IDN.toASCII(this.labelText);
      } catch (IllegalArgumentException illegalArgumentException) {
         return this.labelText;
      }
   }

   private static int computeCount(String text, int count2) {
      try {
         return Integer.parseInt(text.trim());
      } catch (Exception exception) {
         return count2;
      }
   }

   private JavaNamingFactoryInitialManager(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }
}
