package net.augustus.ui.menu;

import java.util.Arrays;
import java.util.List;

public class DeveloperCreditsSection extends CreditsSection {

   @Override
   public List getMemberLines() {
      return Arrays.asList("§eE-sound, DasNeueUpdate, EnZaXD", "Creator of Augustus.");
   }

   public DeveloperCreditsSection() {
      super("Developer");
   }

   @Override
   public List getSocialLinks() {
      return Arrays.asList("Discord: florianmichael", "Discord: dereinzigwahreesound", "Discord: dasneueupdate");
   }
}
