package client.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipInputStreamController {
   public static final int count = 4096;

   private void applyZipInputStream(ZipInputStream zipInputStream, String text) throws IOException {
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(text));
      byte[] byteValueArray = new byte[4096];

      int count2;
      while ((count2 = zipInputStream.read(byteValueArray)) != -1) {
         bufferedOutputStream.write(byteValueArray, 0, count2);
      }

      bufferedOutputStream.close();
   }

   public void applyText(String text, String text2) throws IOException {
      File file = new File(text2);
      if (!file.exists()) {
         file.mkdir();
      }

      ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(text));

      for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
         String text3 = text2 + File.separator + nextEntry.getName();
         if (!nextEntry.isDirectory()) {
            this.applyZipInputStream(zipInputStream, text3);
         } else {
            new File(text3).mkdirs();
         }

         zipInputStream.closeEntry();
      }

      zipInputStream.close();
   }
}
