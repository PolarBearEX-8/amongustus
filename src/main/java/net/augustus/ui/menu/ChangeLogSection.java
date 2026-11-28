package net.augustus.ui.menu;

import net.augustus.changelog.ChangelogEntry;
import java.util.List;
import net.minecraft.client.Minecraft;

public class ChangeLogSection {
   private final String versionTitle;
   private final List entries;

   public int calculateHeight() {
      int n = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 1;
      int n2 = 0;
      n2 += n;
      n2 += n;

      for (ChangelogEntry changelogEntry : (Iterable<ChangelogEntry>)(this.entries)) {
         n2 += changelogEntry.getLines().length * n;
      }

      return n2;
   }

   public List getEntries() {
      return this.entries;
   }

   public String getVersionTitle() {
      return this.versionTitle;
   }

   public ChangeLogSection(String str, List list) {
      this.versionTitle = str;
      this.entries = list;
   }
}
