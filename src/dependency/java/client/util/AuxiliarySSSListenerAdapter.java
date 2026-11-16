package client.util;

import client.event.SSSListener;
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

public class AuxiliarySSSListenerAdapter extends SSSListener {
   @Override
   public InputStream resolveInputStream(String text) throws IOException {
      return new BufferedInputStream(new FileInputStream(new File(this.file, text)));
   }

   @Override
   public boolean isFile(String text) {
      return new File(this.file, text).isFile();
   }

   @Override
   public Set getSet() {
      HashSet set = Sets.newHashSet();
      File file = new File(this.file, "assets/");
      if (file.isDirectory()) {
         for (File file2 : file.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)) {
            String text = resolveText(file, file2);
            if (!text.equals(text.toLowerCase())) {
               this.warn(text);
            } else {
               set.add(text.substring(0, text.length() - 1));
            }
         }
      }

      return set;
   }

   public AuxiliarySSSListenerAdapter(File file) {
      super(file);
   }
}
