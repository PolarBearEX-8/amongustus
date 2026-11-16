package client.util;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SpecialMapEntry {
   public static final Map map = Maps.newHashMap();

   public static DataInputStream resolveDataInputStream(File file, int count, int count2) {
      return resolveExtraByteArrayEntry(file, count, count2).resolveDataInputStream(count & 31, count2 & 31);
   }

   public static DataOutputStream resolveDataOutputStream(File file, int count, int count2) {
      return resolveExtraByteArrayEntry(file, count, count2).resolveDataOutputStream(count & 31, count2 & 31);
   }

   public static synchronized void updateState() {
      for (ExtraByteArrayEntry extraByteArrayEntry : (Iterable<ExtraByteArrayEntry>)(Iterable<?>)map.values()) {
         try {
            if (extraByteArrayEntry != null) {
               extraByteArrayEntry.close();
            }
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }

      map.clear();
   }

   public static synchronized ExtraByteArrayEntry resolveExtraByteArrayEntry(File file, int count, int count2) {
      File file2 = new File(file, "region");
      File file3 = new File(file2, "r." + (count >> 5) + "." + (count2 >> 5) + ".mca");
      ExtraByteArrayEntry extraByteArrayEntry = (ExtraByteArrayEntry)map.get(file3);
      if (extraByteArrayEntry != null) {
         return extraByteArrayEntry;
      } else {
         if (!file2.exists()) {
            file2.mkdirs();
         }

         if (map.size() >= 256) {
            updateState();
         }

         ExtraByteArrayEntry extraByteArrayEntry2 = new ExtraByteArrayEntry(file3);
         map.put(file3, extraByteArrayEntry2);
         return extraByteArrayEntry2;
      }
   }
}
