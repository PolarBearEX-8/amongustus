package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.util.StringTranslate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager implements IResourceManagerReloadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private final IMetadataSerializer theMetadataSerializer;
   private String currentLanguage;
   public static final Locale currentLocale = new Locale();
   private Map languageMap = Maps.newHashMap();

   public void parseLanguageMetadata(List list) {
      this.languageMap.clear();

      for (IResourcePack resourcePack : (Iterable<IResourcePack>)(list)) {
         try {
            LanguageMetadataSection packMetadata = (LanguageMetadataSection)resourcePack.getPackMetadata(this.theMetadataSerializer, "language");
            if (packMetadata != null) {
               for (Language language : (Iterable<Language>)(packMetadata.getLanguages())) {
                  if (!this.languageMap.containsKey(language.getLanguageCode())) {
                     this.languageMap.put(language.getLanguageCode(), language);
                  }
               }
            }
         } catch (RuntimeException e) {
            LOGGER.warn("Unable to parse metadata section of resourcepack: " + resourcePack.getPackName(), e);
         } catch (IOException e2) {
            LOGGER.warn("Unable to parse metadata section of resourcepack: " + resourcePack.getPackName(), e2);
         }
      }
   }

   @Override
   public void onResourceManagerReload(IResourceManager resourceManager) {
      ArrayList list = Lists.newArrayList(new String[]{"en_US"});
      if (!"en_US".equals(this.currentLanguage)) {
         list.add(this.currentLanguage);
      }

      currentLocale.loadLocaleDataFiles(resourceManager, list);
      StringTranslate.replaceWith(currentLocale.properties);
   }

   public boolean isCurrentLocaleUnicode() {
      return currentLocale.isUnicode();
   }

   public LanguageManager(IMetadataSerializer metadataSerializer, String str) {
      this.theMetadataSerializer = metadataSerializer;
      this.currentLanguage = str;
      I18n.setLocale(currentLocale);
   }

   public Map getLanguageMap() {
      return this.languageMap;
   }

   public boolean isCurrentLanguageBidirectional() {
      return this.getCurrentLanguage() != null && this.getCurrentLanguage().isBidirectional();
   }

   public void setCurrentLanguage(Language language) {
      this.currentLanguage = language.getLanguageCode();
   }

   public SortedSet getLanguages() {
      return Sets.newTreeSet(this.languageMap.values());
   }

   public Language getCurrentLanguage() {
      return this.languageMap.containsKey(this.currentLanguage)
         ? (Language)this.languageMap.get(this.currentLanguage)
         : (Language)this.languageMap.get("en_US");
   }
}
