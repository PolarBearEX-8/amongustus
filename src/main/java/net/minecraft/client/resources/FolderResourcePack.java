package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {
   @Override
   public InputStream getInputStreamByName(String str) throws IOException {
      return new BufferedInputStream(new FileInputStream(new File(this.resourcePackFile, str)));
   }

   @Override
   public boolean hasResourceName(String str) {
      return new File(this.resourcePackFile, str).isFile();
   }

   @Override
   public Set getResourceDomains() {
      HashSet set = Sets.newHashSet();
      File file = new File(this.resourcePackFile, "assets/");
      if (file.isDirectory()) {
         for (File file2 : file.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)) {
            String relativeName = getRelativeName(file, file2);
            if (!relativeName.equals(relativeName.toLowerCase())) {
               this.logNameNotLowercase(relativeName);
            } else {
               set.add(relativeName.substring(0, relativeName.length() - 1));
            }
         }
      }

      return set;
   }

   public FolderResourcePack(File file) {
      super(file);
   }
}
