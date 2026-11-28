package net.minecraft.client.resources.data;

import java.util.Collection;

public class LanguageMetadataSection implements IMetadataSection {
   private final Collection languages;

   public Collection getLanguages() {
      return this.languages;
   }

   public LanguageMetadataSection(Collection items) {
      this.languages = items;
   }
}
