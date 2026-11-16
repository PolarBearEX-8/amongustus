package cc.baritone.utils;

import net.minecraft.util.StatCollector;

public enum CompositeCustom {
    NONE("wdl.backup.none", ""),
    FOLDER("wdl.backup.folder", "wdl.saveProgress.backingUp.title.folder"),
    ZIP("wdl.backup.zip", "wdl.saveProgress.backingUp.title.zip");

   public final String descriptionKey;
   public final String titleKey;
   public static final CompositeCustom[] ENUM_VALUES = new CompositeCustom[]{NONE, FOLDER, ZIP};

   public String getTextAsText() {
      return StatCollector.getText(this.titleKey, new Object[0]);
   }

   public static CompositeCustom resolveCompositeCustom(String text) {
      for (CompositeCustom compositeCustom : values()) {
         if (compositeCustom.name().equalsIgnoreCase(text)) {
            return compositeCustom;
         }
      }

      return NONE;
   }

   private CompositeCustom(String text, String text2) {
      this.descriptionKey = text;
      this.titleKey = text2;
   }

   public String getText() {
      return StatCollector.getText(this.descriptionKey, new Object[0]);
   }


}
