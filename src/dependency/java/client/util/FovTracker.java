package client.util;

import java.util.Arrays;
import java.util.List;

public class FovTracker extends SimpleStringEntry {
   @Override
   public List asList2() {
      return Arrays.asList("§eE-sound, DasNeueUpdate, EnZaXD", "Creator of Augustus.");
   }

   @Override
   public List asList() {
      return Arrays.asList("Discord: florianmichael", "Discord: dereinzigwahreesound", "Discord: dasneueupdate");
   }

   public FovTracker() {
      super("Maintainer");
   }
}
