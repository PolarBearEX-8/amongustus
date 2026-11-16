package cc.baritone.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class DepthFirstStrategy {
   public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

   public static void applyFileByFile(File file, File file2, IndirectListener indirectListener) throws IOException {
      indirectListener.applyIndex(computeCount(file));
      FileOutputStream outputStream = null;
      ZipOutputStream zipOutputStream = null;

      try {
         outputStream = new FileOutputStream(file2);

         try {
            zipOutputStream = new ZipOutputStream(outputStream);
            applyFile(file, zipOutputStream, file.getPath().length() + 1, indirectListener);
         } finally {
            zipOutputStream.close();
         }
      } finally {
         outputStream.close();
      }
   }

   private static int computeCount(File file) {
      if (!file.isDirectory()) {
         return 0;
      } else {
         int count = 0;

         for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
               count += computeCount(file2);
            } else {
               count++;
            }
         }

         return count;
      }
   }

   private DepthFirstStrategy() {
   }

   public static void applyFile(File file, String text, CompositeCustom compositeCustom, IndirectListener indirectListener) throws IOException {
      String text2 = text + "_" + dateFormat.format(new Date());
      switch (DepthFirstStrategyCompositeCustomSwitchMap.countArray[compositeCustom.ordinal()]) {
         case 1:
            return;
         case 2:
            File file2 = new File(file.getParentFile(), text2);
            if (file2.exists()) {
               throw new IOException("Backup folder (" + file2 + ") already exists!");
            }

            applyFile(file, file2, indirectListener);
            return;
         case 3:
            File file3 = new File(file.getParentFile(), text2 + ".zip");
            if (file3.exists()) {
               throw new IOException("Backup file (" + file3 + ") already exists!");
            }

            applyFileByFile(file, file3, indirectListener);
            return;
      }
   }

   private static void applyFile(File file, File file2, int count, IndirectListener indirectListener) throws IOException {
      if (file.isDirectory()) {
         if (!file2.exists()) {
            file2.mkdir();
         }

         for (String text : file.list()) {
            applyFile(new File(file, text), new File(file2, text), count, indirectListener);
         }
      } else {
         indirectListener.applyText(file2.getPath().substring(count));
         FileUtils.copyFile(file, file2, true);
      }
   }

   private static void applyFile(File file, ZipOutputStream zipOutputStream, int count, IndirectListener indirectListener) throws IOException {
      for (File file2 : file.listFiles()) {
         if (file2.isFile()) {
            String text = file2.getPath().substring(count);
            indirectListener.applyText(text);
            ZipEntry zipEntry = new ZipEntry(text);
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream inputStream = new FileInputStream(file2);

            try {
               IOUtils.copy(inputStream, zipOutputStream);
            } finally {
               inputStream.close();
            }

            zipOutputStream.closeEntry();
         } else if (file2.isDirectory()) {
            applyFile(file2, zipOutputStream, count, indirectListener);
         }
      }
   }

   public static void applyFile(File file, File file2, IndirectListener indirectListener) throws IOException {
      indirectListener.applyIndex(computeCount(file));
      applyFile(file, file2, file.getPath().length() + 1, indirectListener);
   }
}
