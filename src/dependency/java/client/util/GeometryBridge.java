package client.util;

import client.event.PreUpdateFilter;
import com.google.common.base.Joiner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class GeometryBridge implements PreUpdateFilter {
   public File file;
   public ZipFile zipFile;
   public String labelText;

   @Override
   public String getName() {
      return this.file.getName();
   }

   private String resolveText(String text) {
      ArrayDeque arrayDeque = new ArrayDeque();
      String[] textArray = BuildManager.resolveTextArray(text, "/");

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         if (text2.equals("..")) {
            if (arrayDeque.isEmpty()) {
               return "";
            }

            arrayDeque.removeLast();
         } else {
            arrayDeque.add(text2);
         }
      }

      return Joiner.on('/').join(arrayDeque);
   }

   public GeometryBridge(String text, File file2) {
      this.file = file2;
      this.zipFile = null;
      this.labelText = "";
   }

   @Override
   public void close() {
      if (this.zipFile != null) {
         try {
            this.zipFile.close();
         } catch (Exception exception) {
         }

         this.zipFile = null;
      }
   }

   @Override
   public boolean matchesText(String text) {
      try {
         if (this.zipFile == null) {
            this.zipFile = new ZipFile(this.file);
            this.labelText = this.resolveText(this.zipFile);
         }

         String text2 = SplineCollector.resolveText2(text, "/");
         return this.zipFile.getEntry(this.labelText + text2) != null;
      } catch (IOException ioException) {
         return false;
      }
   }

   private String resolveText(ZipFile zipFile2) {
      ZipEntry entry = zipFile2.getEntry("shaders/");
      if (entry != null && entry.isDirectory()) {
         return "";
      } else {
         Pattern pattern = Pattern.compile("([^/]+/)shaders/");
         Enumeration enumeration = zipFile2.entries();

         while (enumeration.hasMoreElements()) {
            String name = ((ZipEntry)enumeration.nextElement()).getName();
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
               name = matcher.group(1);
               if (name != null) {
                  if (name.equals("shaders/")) {
                     return "";
                  }

                  return name;
               }
            }
         }

         return "";
      }
   }

   @Override
   public InputStream getResourceAsStream(String text) {
      try {
         if (this.zipFile == null) {
            this.zipFile = new ZipFile(this.file);
            this.labelText = this.resolveText(this.zipFile);
         }

         String text2 = SplineCollector.resolveText2(text, "/");
         if (text2.contains("..")) {
            text2 = this.resolveText(text2);
         }

         ZipEntry entry = this.zipFile.getEntry(this.labelText + text2);
         return entry == null ? null : this.zipFile.getInputStream(entry);
      } catch (Exception exception) {
         return null;
      }
   }
}
