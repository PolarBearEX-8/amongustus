package cc.baritone.utils;

import client.util.DampenerProvider;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class ABCDEFMD5Helper {
   public static final char[] charCodeArray = "0123456789ABCDEF".toCharArray();

   public static String resolveText(byte[] byteValueArray) {
      char[] characterArray = new char[byteValueArray.length * 2];

      for (int index = 0; index < byteValueArray.length; index++) {
         int count = byteValueArray[index] & 255;
         characterArray[index * 2] = charCodeArray[count >>> 4];
         characterArray[index * 2 + 1] = charCodeArray[count & 15];
      }

      return new String(characterArray);
   }

   public static String resolveText(String text, String text2) throws ClassNotFoundException, FileNotFoundException, Exception {
      Class clazz = Class.forName(DampenerProvider.a(text));
      MessageDigest instance = MessageDigest.getInstance("MD5");
      InputStream inputStream = null;

      try {
         inputStream = clazz.getResourceAsStream(text2);
         if (inputStream == null) {
            throw new FileNotFoundException(text2 + " relative to " + text);
         }

         DigestInputStream digestInputStream = null;
         boolean flag = false ;

         try {
            flag = true;
            digestInputStream = new DigestInputStream(inputStream, instance);

            while (digestInputStream.read() != -1) {
            }

            flag = false;
         } finally {
            if (flag) {
               if (digestInputStream != null) {
                  digestInputStream.close();
               }
            }
         }

         digestInputStream.close();
      } finally {
         if (inputStream != null) {
            inputStream.close();
         }
      }

      return resolveText(instance.digest());
   }
}
