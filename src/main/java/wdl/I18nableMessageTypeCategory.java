package wdl;

import net.minecraft.client.resources.I18n;

public class I18nableMessageTypeCategory extends MessageTypeCategory {
   public final String i18nKey;

   @Override
   public String getDisplayName() {
      return I18n.format(this.i18nKey, new Object[0]);
   }

   public I18nableMessageTypeCategory(String str, String str2) {
      super(str);
      this.i18nKey = str2;
   }
}
