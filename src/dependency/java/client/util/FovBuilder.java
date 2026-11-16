package client.util;

import client.event.PreUpdateFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FovBuilder implements PreUpdateFilter {
   public File file;

   @Override
   public InputStream getResourceAsStream(String text) {
      try {
         String text2 = SplineCollector.resolveText3(text, "/", "/");
         File file2 = new File(this.file, text2);
         return !file2.exists() ? null : new BufferedInputStream(new FileInputStream(file2));
      } catch (Exception exception) {
         return null;
      }
   }

   @Override
   public void close() {
   }

   @Override
   public String getName() {
      return this.file.getName();
   }

   @Override
   public boolean matchesText(String text) {
      File file2 = new File(this.file, text.substring(1));
      return !file2.exists() ? false : file2.isDirectory();
   }

   public FovBuilder(String text, File file2) {
      this.file = file2;
   }
}
