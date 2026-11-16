package client.util;

import client.event.SSSListener;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SSSListenerAdapter extends SSSListener implements Closeable {
   public static final Splitter splitter = Splitter.on('/').omitEmptyStrings().limit(3);
   public ZipFile zipFile;

   @Override
   public InputStream resolveInputStream(String text) throws IOException {
      ZipFile zipFile2 = this.getZipFile();
      ZipEntry entry = zipFile2.getEntry(text);
      if (entry == null) {
         throw new SInResourcePackSFileNotFoundException(this.file, text);
      } else {
         return zipFile2.getInputStream(entry);
      }
   }

   @Override
   public boolean isFile(String text) {
      try {
         return this.getZipFile().getEntry(text) != null;
      } catch (IOException ioException) {
         return false;
      }
   }

   private ZipFile getZipFile() throws IOException {
      if (this.zipFile == null) {
         this.zipFile = new ZipFile(this.file);
      }

      return this.zipFile;
   }

   @Override
   public Set getSet() {
      ZipFile zipFile2;
      try {
         zipFile2 = this.getZipFile();
      } catch (IOException ioException) {
         return Collections.emptySet();
      }

      Enumeration enumeration = zipFile2.entries();
      HashSet set = Sets.newHashSet();

      while (enumeration.hasMoreElements()) {
         String name = ((ZipEntry)enumeration.nextElement()).getName();
         if (name.startsWith("assets/")) {
            ArrayList list = Lists.newArrayList(splitter.split(name));
            if (list.size() > 1) {
               name = (String)list.get(1);
               if (!name.equals(name.toLowerCase())) {
                  this.warn(name);
               } else {
                  set.add(name);
               }
            }
         }
      }

      return set;
   }

   public SSSListenerAdapter(File file) {
      super(file);
   }

   @Override
   public void finalize() throws Throwable {
      this.close();
      super.finalize();
   }

   @Override
   public void close() throws IOException {
      if (this.zipFile != null) {
         this.zipFile.close();
         this.zipFile = null;
      }
   }
}
