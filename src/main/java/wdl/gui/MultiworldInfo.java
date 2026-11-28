package wdl.gui;

import wdl.WDL;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiworldInfo {
   public final String folderName;
   public final String displayName;
   private List description;

   public MultiworldInfo(String str, String str2) {
      this.folderName = str;
      this.displayName = str2;
   }

   public List getDescription() {
      if (this.description == null) {
         this.description = new ArrayList();
         this.description.add("Defined dimensions:");
         File file = new File(WDL.mc.mcDataDir, "saves");
         File[] files = new File(file, WDL.getWorldFolderName(this.folderName)).listFiles();
         if (files != null) {
            for (File file3 : files) {
               if (file3.listFiles() != null && file3.listFiles().length != 0) {
                  if (file3.getName().equals("region")) {
                     this.description.add(" * Overworld (#0)");
                  } else if (file3.getName().startsWith("DIM")) {
                     String str = file3.getName().substring(3);
                     if (str.equals("-1")) {
                        this.description.add(" * Nether (#-1)");
                     } else if (str.equals("1")) {
                        this.description.add(" * The End (#1)");
                     } else {
                        this.description.add(" * #" + str);
                     }
                  }
               }
            }
         }
      }

      return this.description;
   }
}
