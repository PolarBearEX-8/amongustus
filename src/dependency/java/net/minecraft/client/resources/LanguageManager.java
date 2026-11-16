package net.minecraft.client.resources;

import client.event.PreUpdateProcessor;
import client.util.AuxiliaryListenerAdapter7;
import client.util.BrandProperty;
import client.util.FormatErrorManager;
import client.util.InvalidMetadataForManager;
import client.util.LangSLangManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanguageManager implements IResourceManagerReloadListener {
   public static final Logger logger = LogManager.getLogger();
   public final InvalidMetadataForManager invalidMetadataForManager;
   public String labelText;
   public static final LangSLangManager langSLangManager = new LangSLangManager();
   public Map map = Maps.newHashMap();

   public boolean isActiveAsFlag() {
      return langSLangManager.isEnabled();
   }

   public BrandProperty getBrandProperty() {
      return this.map.containsKey(this.labelText) ? (BrandProperty)this.map.get(this.labelText) : (BrandProperty)this.map.get("en_US");
   }

   public boolean isActive() {
      return this.getBrandProperty() != null && this.getBrandProperty().isEnabled();
   }

   public SortedSet getSortedSet() {
      return Sets.newTreeSet(this.map.values());
   }

   public void applyBrandProperty(BrandProperty brandProperty) {
      this.labelText = brandProperty.getString();
   }

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      ArrayList list = Lists.newArrayList(new String[]{"en_US"});
      if (!"en_US".equals(this.labelText)) {
         list.add(this.labelText);
      }

      langSLangManager.applyIResourceManager(iResourceManager, list);
      FormatErrorManager.applyMap(langSLangManager.map);
   }

   public void applyList(List list) {
      this.map.clear();

      for (PreUpdateProcessor preUpdateProcessor : (Iterable<PreUpdateProcessor>) (list)) {
         try {
            AuxiliaryListenerAdapter7 auxiliaryListenerAdapter = (AuxiliaryListenerAdapter7)preUpdateProcessor.resolveIMetadataSection(this.invalidMetadataForManager, "language");
            if (auxiliaryListenerAdapter != null) {
               for (BrandProperty brandProperty : (Iterable<BrandProperty>) (auxiliaryListenerAdapter.getCollection())) {
                  if (!this.map.containsKey(brandProperty.getString())) {
                     this.map.put(brandProperty.getString(), brandProperty);
                  }
               }
            }
         } catch (RuntimeException exception) {
            logger.warn("Unable to parse metadata section of resourcepack: " + preUpdateProcessor.getName(), exception);
         } catch (IOException ioException) {
            logger.warn("Unable to parse metadata section of resourcepack: " + preUpdateProcessor.getName(), ioException);
         }
      }
   }

   public Map getEntryMap() {
      return this.map;
   }

   public LanguageManager(InvalidMetadataForManager invalidMetadataForManager2, String text) {
      this.invalidMetadataForManager = invalidMetadataForManager2;
      this.labelText = text;
      StatCollector.setLangSLangManager(langSLangManager);
   }
}
