package net.augustus.changelog;

public class ChangelogEntry {
   private String[] lines;
   private final ChangelogEntryType type;

   public String[] getLines() {
      return this.lines;
   }

   public ChangelogEntry(String[] strings, ChangelogEntryType changelogEntryType) {
      this.lines = strings;
      this.type = changelogEntryType;
   }

   public void setLines(String[] strings) {
      this.lines = strings;
   }

   public ChangelogEntryType getType() {
      return this.type;
   }



}
