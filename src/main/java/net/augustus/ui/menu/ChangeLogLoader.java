package net.augustus.ui.menu;

import net.augustus.changelog.ChangelogEntry;
import net.augustus.changelog.ChangelogEntryType;
import net.augustus.changelog.ChangelogReadException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;

public class ChangeLogLoader {
   private static final String CHANGELOG_RESOURCE_PATH = "assets/augustus/ChangeLog.txt";
   private static final String LINE_SEPARATOR = "\r\n";
   public final List sections = new ArrayList();

   private String readChangelogResource() {
      InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("assets/augustus/ChangeLog.txt");

      try {
         return IOUtils.toString((InputStream)Objects.requireNonNull(resourceAsStream), StandardCharsets.UTF_8);
      } catch (IOException e) {
         throw new ChangelogReadException(0L, e.getMessage());
      }
   }

   public void loadChangelog() {
      String str = this.readChangelogResource();
      if (str != null && !str.isEmpty()) {
         ArrayList list = new ArrayList();
         String str2 = "";

         for (String str3 : str.split("\r\n")) {
            if (str3.endsWith(":")) {
               str2 = str3.replace(":", "");
            }

            if (str3.startsWith("-")) {
               String str4 = str3.substring(1).split(" ")[0];
               ChangelogEntryType added = ChangelogEntryType.ADDED;
               switch (str4) {
                  case "changed":
                  case "improved":
                     added = ChangelogEntryType.IMPROVED;
                     break;
                  case "moved":
                  case "renamed":
                  case "fixed":
                  case "reworked":
                     added = ChangelogEntryType.FIXED;
                     break;
                  case "removed":
                     added = ChangelogEntryType.REMOVED;
               }

               list.add(new ChangelogEntry(new String[]{str3}, added));
            }

            if (str3.trim().isEmpty()) {
               this.sections.add(new ChangeLogSection(str2, new ArrayList(list)));
               str2 = "";
               list.clear();
            }
         }
      } else {
         throw new ChangelogReadException(0L, "empty");
      }
   }

   public List getWrappedSections(double d) {
      ArrayList list = new ArrayList(this.sections);

      for (Object obj : list) {

         for (Object obj2 : ((ChangeLogSection)obj).getEntries()) {
            ChangelogEntry changelogEntry = (ChangelogEntry)obj2;
            String str = changelogEntry.getLines()[0];
            if ((double)Minecraft.getMinecraft().fontRendererObj.getStringWidth(str) > d) {
               StringBuilder sb = new StringBuilder();
               String[] parts = str.split(" ");
               ArrayList list2 = new ArrayList();

               for (String str2 : parts) {
                  if ((double)Minecraft.getMinecraft().fontRendererObj.getStringWidth(sb + str2 + " ") > d) {
                     list2.add(sb.toString());
                     sb = new StringBuilder();
                  }

                  sb.append(str2);
                  sb.append(" ");
               }

               list2.add(sb.toString());
               changelogEntry.setLines((String[])list2.toArray(new String[0]));
            }
         }
      }

      return list;
   }
}
