package net.optifine.shaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.optifine.util.StrUtils;

public class ShaderPackFolder implements IShaderPack {
   public File packFile;

   @Override
   public InputStream getResourceAsStream(String str) {
      try {
         String str2 = StrUtils.removePrefixSuffix(str, "/", "/");
         File file = new File(this.packFile, str2);
         return !file.exists() ? null : new BufferedInputStream(new FileInputStream(file));
      } catch (Exception e) {
         return null;
      }
   }

   @Override
   public String getName() {
      return this.packFile.getName();
   }

   public ShaderPackFolder(String str, File file) {
      this.packFile = file;
   }

   @Override
   public boolean hasDirectory(String str) {
      File file = new File(this.packFile, str.substring(1));
      return !file.exists() ? false : file.isDirectory();
   }

   @Override
   public void close() {
   }
}
