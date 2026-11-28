package net.augustus.ui.menu;

import java.util.List;
import net.minecraft.client.Minecraft;

public abstract class CreditsSection {
   private final String sectionTitle;

   public abstract List getSocialLinks();

   public int calculateHeight() {
      int n = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1;
      int n2 = 0;
      n2 += n * 2;
      n2 += n;
      n2 += this.getMemberLines().size() * n;
      n2 += n;
      n2 += n;
      return n2 + this.getSocialLinks().size() * n;
   }

   public CreditsSection(String str) {
      this.sectionTitle = str;
   }

   public abstract List getMemberLines();

   public String getSectionTitle() {
      return this.sectionTitle;
   }



}
