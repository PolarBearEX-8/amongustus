package net.augustus.ui.menu;

import java.util.Arrays;
import java.util.List;

public class ContributorCreditsSection extends CreditsSection {

   public ContributorCreditsSection() {
      super("Contributor");
   }

   @Override
   public List getMemberLines() {
      return Arrays.asList("§eLCA_MODZ", "Shadow, Model renderer, Hit animations, Old verus fly", "", "§eMuffinMaker, ZDibLO", "Augustus Wiki");
   }

   @Override
   public List getSocialLinks() {
      return Arrays.asList("Discord: lca_modz", "Discord: muffinmaker", "Discord: zdiblo");
   }
}
