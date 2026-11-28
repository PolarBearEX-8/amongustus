package net.optifine.shaders;

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
import net.optifine.config.Config;
import net.optifine.util.StrUtils;

public class ShaderPackZip implements IShaderPack {
   public File packFile;
   public ZipFile packZipFile;
   public String baseFolder;

   public ShaderPackZip(String str, File file) {
      this.packFile = file;
      this.packZipFile = null;
      this.baseFolder = "";
   }

   private String resolveRelative(String str) {
      ArrayDeque arrayDeque = new ArrayDeque();
      String[] strings = Config.tokenize(str, "/");

      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         if (str2.equals("..")) {
            if (arrayDeque.isEmpty()) {
               return "";
            }

            arrayDeque.removeLast();
         } else {
            arrayDeque.add(str2);
         }
      }

      return Joiner.on('/').join(arrayDeque);
   }

   private String detectBaseFolder(ZipFile zipFile) {
      ZipEntry entry = zipFile.getEntry("shaders/");
      if (entry != null && entry.isDirectory()) {
         return "";
      } else {
         Pattern pattern = Pattern.compile("([^/]+/)shaders/");
         Enumeration en = zipFile.entries();

         while (en.hasMoreElements()) {
            Matcher matcher = pattern.matcher(((ZipEntry)en.nextElement()).getName());
            if (matcher.matches()) {
               String str = matcher.group(1);
               if (str != null) {
                  if (str.equals("shaders/")) {
                     return "";
                  }

                  return str;
               }
            }
         }

         return "";
      }
   }

   @Override
   public String getName() {
      return this.packFile.getName();
   }

   @Override
   public boolean hasDirectory(String str) {
      try {
         if (this.packZipFile == null) {
            this.packZipFile = new ZipFile(this.packFile);
            this.baseFolder = this.detectBaseFolder(this.packZipFile);
         }

         String str2 = StrUtils.removePrefix(str, "/");
         return this.packZipFile.getEntry(this.baseFolder + str2) != null;
      } catch (IOException e) {
         return false;
      }
   }

   @Override
   public InputStream getResourceAsStream(String str) {
      try {
         if (this.packZipFile == null) {
            this.packZipFile = new ZipFile(this.packFile);
            this.baseFolder = this.detectBaseFolder(this.packZipFile);
         }

         String str2 = StrUtils.removePrefix(str, "/");
         if (str2.contains("..")) {
            str2 = this.resolveRelative(str2);
         }

         ZipEntry entry = this.packZipFile.getEntry(this.baseFolder + str2);
         return entry == null ? null : this.packZipFile.getInputStream(entry);
      } catch (Exception e) {
         return null;
      }
   }

   @Override
   public void close() {
      if (this.packZipFile != null) {
         try {
            this.packZipFile.close();
         } catch (Exception e) {
         }

         this.packZipFile = null;
      }
   }
}
