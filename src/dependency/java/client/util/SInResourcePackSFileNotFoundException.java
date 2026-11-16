package client.util;

import java.io.File;
import java.io.FileNotFoundException;

public class SInResourcePackSFileNotFoundException extends FileNotFoundException {
   public SInResourcePackSFileNotFoundException(File file, String text) {
      super(String.format("'%s' in ResourcePack '%s'", text, file));
   }
}
