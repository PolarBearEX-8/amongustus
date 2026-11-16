package client.util;

import java.util.Arrays;
import java.util.List;

public class FovCollector extends SimpleStringEntry {
   @Override
   public List asList2() {
      return Arrays.asList("§eLCA_MODZ", "Shadow, Model renderer, Hit animations, Old verus fly", "", "§eMuffinMaker, ZDibLO", "Augustus Wiki");
   }

   @Override
   public List asList() {
      return Arrays.asList("Discord: lca_modz", "Discord: muffinmaker", "Discord: zdiblo");
   }

   public FovCollector() {
      super("Contributor");
   }
}
