package cc.baritone.utils;

import java.io.File;
import java.util.List;

public class DefinedDimensionsRecord {
   public final String labelText;
   public final String labelText2;
   public List list;

   public DefinedDimensionsRecord(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
   }

   public List getList() {
      if (this.list == null) {
         this.list = new java.util.ArrayList();
         this.list.add("Defined dimensions:");
         File file = new File(Pokechu22WorldDownloaderManager.minecraft.mcDataDir, "saves");
         File[] fileArray = new File(file, Pokechu22WorldDownloaderManager.resolveText(this.labelText)).listFiles();
         if (fileArray != null) {
            for (File file2 : fileArray) {
               if (file2.listFiles() != null && file2.listFiles().length != 0) {
                  if (file2.getName().equals("region")) {
                     this.list.add(" * Overworld (#0)");
                  } else if (file2.getName().startsWith("DIM")) {
                     String text = file2.getName().substring(3);
                     if (text.equals("-1")) {
                        this.list.add(" * Nether (#-1)");
                     } else if (text.equals("1")) {
                        this.list.add(" * The End (#1)");
                     } else {
                        this.list.add(" * #" + text);
                     }
                  }
               }
            }
         }
      }

      return this.list;
   }
}
