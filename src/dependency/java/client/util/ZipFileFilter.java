package client.util;

import java.io.File;
import java.io.FileFilter;

public final class ZipFileFilter implements FileFilter {
   @Override
   public boolean accept(File file) {
      boolean flag = file.isFile() && file.getName().endsWith(".zip");
      boolean flag2 = file.isDirectory() && new File(file, "pack.mcmeta").isFile();
      return flag || flag2;
   }
}
