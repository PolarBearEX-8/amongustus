package client.util;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterAdapter implements FilenameFilter {
   public final LevelDatISaveFormatAdapter levelDatISaveFormatAdapter;

   @Override
   public boolean accept(File file, String text) {
      return text.endsWith(".mcr");
   }

   public FilenameFilterAdapter(LevelDatISaveFormatAdapter levelDatISaveFormatAdapter2) {
      this.levelDatISaveFormatAdapter = levelDatISaveFormatAdapter2;
   }
}
