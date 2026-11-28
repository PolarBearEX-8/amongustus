package wdl;

import net.minecraft.client.resources.I18n;

public enum WorldBackupType {
   NONE("wdl.backup.none", ""),
   FOLDER("wdl.backup.folder", "wdl.saveProgress.backingUp.title.folder"),
   ZIP("wdl.backup.zip", "wdl.saveProgress.backingUp.title.zip");

public final String descriptionKey;
   public final String titleKey;
private WorldBackupType(String str, String str2) {
      this.descriptionKey = str;
      this.titleKey = str2;
   }

   public String getDescription() {
      return I18n.format(this.descriptionKey, new Object[0]);
   }

   public String getTitle() {
      return I18n.format(this.titleKey, new Object[0]);
   }

   public static WorldBackupType match(String str) {
      for (WorldBackupType worldBackupType : values()) {
         if (worldBackupType.name().equalsIgnoreCase(str)) {
            return worldBackupType;
         }
      }

      return NONE;
   }
}
