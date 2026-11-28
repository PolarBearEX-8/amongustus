package wdl;

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

public class WorldBackup {
   private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

   private static int countFilesInFolder(File file) {
      if (!file.isDirectory()) {
         return 0;
      } else {
         int n = 0;

         for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
               n += countFilesInFolder(file2);
            } else {
               n++;
            }
         }

         return n;
      }
   }

   private WorldBackup() {
   }

   public static void zipDirectory(File file, File file2, IBackupProgressMonitor backupProgressMonitor) throws IOException {
      backupProgressMonitor.setNumberOfFiles(countFilesInFolder(file));
      FileOutputStream fileOutputStream = null;
      ZipOutputStream zipOutputStream = null;

      try {
         fileOutputStream = new FileOutputStream(file2);

         try {
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            zipFolder(file, zipOutputStream, file.getPath().length() + 1, backupProgressMonitor);
         } finally {
            zipOutputStream.close();
         }
      } finally {
         fileOutputStream.close();
      }
   }

   public static void copyDirectory(File file, File file2, IBackupProgressMonitor backupProgressMonitor) throws IOException {
      backupProgressMonitor.setNumberOfFiles(countFilesInFolder(file));
      copy(file, file2, file.getPath().length() + 1, backupProgressMonitor);
   }

   private static void copy(File file, File file2, int n, IBackupProgressMonitor backupProgressMonitor) throws IOException {
      if (file.isDirectory()) {
         if (!file2.exists()) {
            file2.mkdir();
         }

         for (String str : file.list()) {
            copy(new File(file, str), new File(file2, str), n, backupProgressMonitor);
         }
      } else {
         backupProgressMonitor.onNextFile(file2.getPath().substring(n));
         FileUtils.copyFile(file, file2, true);
      }
   }

   private static void zipFolder(File file, ZipOutputStream zipOutputStream, int n, IBackupProgressMonitor backupProgressMonitor) throws IOException {

      for (File file2 : file.listFiles()) {
         if (file2.isFile()) {
            String str = file2.getPath().substring(n);
            backupProgressMonitor.onNextFile(str);
            zipOutputStream.putNextEntry(new ZipEntry(str));
            FileInputStream fileInputStream = new FileInputStream(file2);

            try {
               IOUtils.copy(fileInputStream, zipOutputStream);
            } finally {
               fileInputStream.close();
            }

            zipOutputStream.closeEntry();
         } else if (file2.isDirectory()) {
            zipFolder(file2, zipOutputStream, n, backupProgressMonitor);
         }
      }
   }

   public static void backupWorld(File file, String str, WorldBackupType worldBackupType, IBackupProgressMonitor backupProgressMonitor) throws IOException {
      String str2 = str + "_" + DATE_FORMAT.format(new Date());
      switch (WorldBackupTypeSwitch.SWITCH_MAP[worldBackupType.ordinal()]) {
         case 1:
            return;
         case 2:
            File file2 = new File(file.getParentFile(), str2);
            if (file2.exists()) {
               throw new IOException("Backup folder (" + file2 + ") already exists!");
            }

            copyDirectory(file, file2, backupProgressMonitor);
            return;
         case 3:
            File file3 = new File(file.getParentFile(), str2 + ".zip");
            if (file3.exists()) {
               throw new IOException("Backup file (" + file3 + ") already exists!");
            }

            zipDirectory(file, file3, backupProgressMonitor);
            return;
      }
   }
}
