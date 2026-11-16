package client.util;

import client.setting.OptionType;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonManager {
   public static final ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter("builtin/missing", "missing");
   public static final Set set = Sets.newHashSet(
      new ResourceLocation[]{
         new ResourceLocation("blocks/water_flow"),
         new ResourceLocation("blocks/water_still"),
         new ResourceLocation("blocks/lava_flow"),
         new ResourceLocation("blocks/lava_still"),
         new ResourceLocation("blocks/destroy_stage_0"),
         new ResourceLocation("blocks/destroy_stage_1"),
         new ResourceLocation("blocks/destroy_stage_2"),
         new ResourceLocation("blocks/destroy_stage_3"),
         new ResourceLocation("blocks/destroy_stage_4"),
         new ResourceLocation("blocks/destroy_stage_5"),
         new ResourceLocation("blocks/destroy_stage_6"),
         new ResourceLocation("blocks/destroy_stage_7"),
         new ResourceLocation("blocks/destroy_stage_8"),
         new ResourceLocation("blocks/destroy_stage_9"),
         new ResourceLocation("items/empty_armor_slot_helmet"),
         new ResourceLocation("items/empty_armor_slot_chestplate"),
         new ResourceLocation("items/empty_armor_slot_leggings"),
         new ResourceLocation("items/empty_armor_slot_boots")
      }
   );
   public static final Logger logger = LogManager.getLogger();
   public static final Map map6 = Maps.newHashMap();
   public static final Joiner joiner = Joiner.on(" -> ");
   public static final NativeLoggerEntry nativeLoggerEntry3 = NativeLoggerEntry.resolveNativeLoggerEntry(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static final NativeLoggerEntry nativeLoggerEntry2 = NativeLoggerEntry.resolveNativeLoggerEntry(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static final NativeLoggerEntry nativeLoggerEntry4 = NativeLoggerEntry.resolveNativeLoggerEntry(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public static final NativeLoggerEntry nativeLoggerEntry = NativeLoggerEntry.resolveNativeLoggerEntry(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   public final IResourceManager iResourceManager;
   public final Map map2 = Maps.newHashMap();
   public final Map map5 = Maps.newLinkedHashMap();
   public final Map map4 = Maps.newLinkedHashMap();
   public final ListenerAdapter27 listenerAdapter27;
   public final CommonMapEntry commonMapEntry;
   public final SystemFloatEntry systemFloatEntry = new SystemFloatEntry();
   public final PassiveListEntry passiveListEntry = new PassiveListEntry();
   public final Map map = Maps.newHashMap();
   public final AddingDuplicateKeyManager addingDuplicateKeyManager = new AddingDuplicateKeyManager();
   public final Map map7 = Maps.newLinkedHashMap();
   public final Map map3 = Maps.newIdentityHashMap();

   private List resolveListByResourceLocation(ResourceLocation location) {
      ArrayList list = Lists.newArrayList(new ResourceLocation[]{location});
      ResourceLocation location2 = location;

      while ((location2 = this.resolveResourceLocation2(location2)) != null) {
         list.add(0, location2);
      }

      return list;
   }

   private static String resolveText(String text, String text2) {
      text = InternalPngManager.resolveText(text, text2);
      text = SplineCollector.resolveText3(text, ".json");
      return SplineCollector.resolveText3(text, ".png");
   }

   private void updateState3() {
      Set set2 = this.getSet();
      set2.addAll(this.getSetAsSet());
      set2.remove(ListenerAdapter27.resourceLocation2);
      ListenerProcessor listenerProcessor = new ListenerProcessor(this, set2);
      this.listenerAdapter27.applyIResourceManager(this.iResourceManager, listenerProcessor);
      this.map2.put(new ResourceLocation("missingno"), this.listenerAdapter27.getServiceStringEntry());
   }

   private void updateState2() {
      this.updateState6();
      Iterator iterator = this.map5.values().iterator();

      while (iterator.hasNext()) {
         ((NativeLoggerEntry)iterator.next()).get(this.map5);
      }

      NativeLoggerEntry.applyMap(this.map5);
   }

   @Deprecated
   public static void applyItem(Item item, String[] textArray) {
   }

   public NativeLoggerEntry get(ResourceLocation location) {
      return (NativeLoggerEntry)this.map5.get(location);
   }

   private LocalIntArrayEntry resolveLocalIntArrayEntry(
      Vector3fHolder vector3fHolder, EnumFacingHolder enumFacingHolder, ServiceStringEntry serviceStringEntry, EnumFacing enumFacing, OptionType optionType, boolean flag
   ) {
      return this.systemFloatEntry.resolveLocalIntArrayEntry(vector3fHolder.vector3f2, vector3fHolder.vector3f, enumFacingHolder, serviceStringEntry, enumFacing, optionType, vector3fHolder.vector3fEntry, flag, vector3fHolder.enabled);
   }

   public static ResourceLocation resolveResourceLocation(ResourceLocation location, String text) {
      if (location != null && text != null) {
         if (!location.getText().equals("minecraft")) {
            return location;
         } else {
            String text2 = location.getText1();
            String text3 = resolveText(text2, text);
            if (text3 != text2) {
               location = new ResourceLocation(location.getText(), text3);
            }

            return location;
         }
      } else {
         return location;
      }
   }

   public IBakedModel resolveIBakedModel(NativeLoggerEntry nativeLoggerEntry5, OptionType optionType, boolean flag) {
      ServiceStringEntry serviceStringEntry = (ServiceStringEntry)this.map2.get(new ResourceLocation(nativeLoggerEntry5.resolveText("particle")));
      MissingParticleManager missingParticleManager = new MissingParticleManager(nativeLoggerEntry5).withServiceStringEntry(serviceStringEntry);

      for (Vector3fHolder vector3fHolder : (Iterable<Vector3fHolder>)(Iterable)(nativeLoggerEntry5.getList())) {
         for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable)(vector3fHolder.map.keySet())) {
            EnumFacingHolder enumFacingHolder = (EnumFacingHolder)vector3fHolder.map.get(enumFacing);
            ServiceStringEntry serviceStringEntry2 = (ServiceStringEntry)this.map2.get(new ResourceLocation(nativeLoggerEntry5.resolveText(enumFacingHolder.labelText)));
            if (enumFacingHolder.enumFacing2 != null) {
               missingParticleManager.resolveMissingParticleManager(
                  optionType.resolveEnumFacingByEnumFacing(enumFacingHolder.enumFacing2), this.resolveLocalIntArrayEntry(vector3fHolder, enumFacingHolder, serviceStringEntry2, enumFacing, optionType, flag)
               );
            } else {
               missingParticleManager.add(this.resolveLocalIntArrayEntry(vector3fHolder, enumFacingHolder, serviceStringEntry2, enumFacing, optionType, flag));
            }
         }
      }

      return missingParticleManager.getIBakedModel();
   }

   private void updateState() {
      for (ResourceLocationAdapter resourceLocationAdapter2 : (Iterable<ResourceLocationAdapter>)(Iterable)(this.map4.keySet())) {
         SharedListEntry sharedListEntry = new SharedListEntry();
         int count = 0;

         for (DynamicResourceLocationEntry dynamicResourceLocationEntry : (Iterable<DynamicResourceLocationEntry>)(Iterable)(((EnhancedStringEntry)this.map4.get(resourceLocationAdapter2)).getList())) {
            NativeLoggerEntry nativeLoggerEntry5 = (NativeLoggerEntry)this.map5.get(dynamicResourceLocationEntry.getResourceLocation());
            if (nativeLoggerEntry5 != null && nativeLoggerEntry5.isActive2()) {
               count++;
               sharedListEntry.add(this.resolveIBakedModel(nativeLoggerEntry5, dynamicResourceLocationEntry.getOptionType(), dynamicResourceLocationEntry.isEnabled()), dynamicResourceLocationEntry.getCount());
            } else {
               logger.warn("Missing model for: " + resourceLocationAdapter2);
            }
         }

         if (count == 0) {
            logger.warn("No weighted models for: " + resourceLocationAdapter2);
         } else if (count == 1) {
            this.addingDuplicateKeyManager.applyTarget(resourceLocationAdapter2, sharedListEntry.get());
         } else {
            this.addingDuplicateKeyManager.applyTarget(resourceLocationAdapter2, sharedListEntry.sort());
         }
      }

      for (Entry entry : (Iterable<Entry>)(Iterable)(this.map7.entrySet())) {
         ResourceLocation value = (ResourceLocation)entry.getValue();
         ResourceLocationAdapter resourceLocationAdapter3 = new ResourceLocationAdapter((String)entry.getKey(), "inventory");
         NativeLoggerEntry nativeLoggerEntry6 = (NativeLoggerEntry)this.map5.get(value);
         if (nativeLoggerEntry6 == null || !nativeLoggerEntry6.isActive2()) {
            logger.warn("Missing model for: " + value);
         } else if (this.matchesNativeLoggerEntryByNativeLoggerEntr(nativeLoggerEntry6)) {
            this.addingDuplicateKeyManager.applyTarget(resourceLocationAdapter3, new AuxiliaryListenerTracker(nativeLoggerEntry6.getSpecialItemEntry()));
         } else {
            this.addingDuplicateKeyManager.applyTarget(resourceLocationAdapter3, this.resolveIBakedModel(nativeLoggerEntry6, OptionType.X0_Y0, false));
         }
      }
   }

   public static Map resolveMap(JsonManager jsonManager) {
      return jsonManager.map2;
   }

   public AuxiliaryIterableExtension getAuxiliaryIterableExtension() {
      this.updateState5();
      this.updateState2();
      this.updateState3();
      this.updateState9();
      this.updateState();
      return this.addingDuplicateKeyManager;
   }

   private Set resolveSet(NativeLoggerEntry nativeLoggerEntry5) {
      HashSet set2 = Sets.newHashSet();
      Iterator iterator = nativeLoggerEntry5.getList().iterator();

      while (iterator.hasNext()) {
         for (EnumFacingHolder enumFacingHolder : (Iterable<EnumFacingHolder>)(Iterable)(((Vector3fHolder)iterator.next()).map.values())) {
            ResourceLocation location = new ResourceLocation(nativeLoggerEntry5.resolveText(enumFacingHolder.labelText));
            set2.add(location);
         }
      }

      set2.add(new ResourceLocation(nativeLoggerEntry5.resolveText("particle")));
      return set2;
   }

   private void updateState5() {
      this.applyCollection(this.commonMapEntry.getEnhancedMapEntry().getMap().values());
      this.map4
         .put(
            resourceLocationAdapter,
            new EnhancedStringEntry(
               resourceLocationAdapter.getLabelText(),
               Lists.newArrayList(
                  new DynamicResourceLocationEntry[]{
                     new DynamicResourceLocationEntry(new ResourceLocation(resourceLocationAdapter.getText1()), OptionType.X0_Y0, false, 1)
                  }
               )
            )
         );
      ResourceLocation location = new ResourceLocation("item_frame");
      GsonEntry gsonEntry = this.resolveGsonEntryByResourceLocation(location);
      this.applyGsonEntry(gsonEntry, new ResourceLocationAdapter(location, "normal"));
      this.applyGsonEntry(gsonEntry, new ResourceLocationAdapter(location, "map"));
      this.updateState4();
      this.updateState8();
   }

   public void applyText(String text, ResourceLocation location, ResourceLocation location2) {
      this.map7.put(text, location);
      if (this.map5.get(location) == null) {
         try {
            NativeLoggerEntry nativeLoggerEntry5 = this.resolveNativeLoggerEntry2(location);
            this.map5.put(location, nativeLoggerEntry5);
         } catch (Exception exception) {
            logger.warn("Unable to load item model: '{}' for item: '{}'", new Object[]{location, location2});
            logger.warn(exception.getClass().getName() + ": " + exception.getMessage());
         }
      }
   }

   private GsonEntry resolveGsonEntryByResourceLocation(ResourceLocation location) {
      ResourceLocation location2 = this.resolveResourceLocation(location);
      GsonEntry gsonEntry = (GsonEntry)this.map.get(location2);
      if (gsonEntry == null) {
         ArrayList list = Lists.newArrayList();

         try {
            for (IResource iResource : (Iterable<IResource>)(Iterable)(this.iResourceManager.resolveListByResourceLocation(location2))) {
               InputStream inputStream = null;

               try {
                  inputStream = iResource.getInputStream();
                  GsonEntry gsonEntry2 = GsonEntry.fromJson(new InputStreamReader(inputStream, Charsets.UTF_8));
                  list.add(gsonEntry2);
               } catch (Exception exception) {
                  throw new RuntimeException(
                     "Encountered an exception when loading model definition of '"
                        + location
                        + "' from: '"
                        + iResource.getResourceLocation()
                        + "' in resourcepack: '"
                        + iResource.getLabelText()
                        + "'",
                     exception
                  );
               } finally {
                  IOUtils.closeQuietly(inputStream);
               }
            }
         } catch (IOException ioException) {
            throw new RuntimeException("Encountered an exception when loading model definition of model " + location2, ioException);
         }

         gsonEntry = new GsonEntry((List)list);
         this.map.put(location2, gsonEntry);
      }

      return gsonEntry;
   }

   private void applyGsonEntry(GsonEntry gsonEntry, ResourceLocationAdapter resourceLocationAdapter2) {
      this.map4.put(resourceLocationAdapter2, gsonEntry.get(resourceLocationAdapter2.getLabelText()));
   }

   private void applyCollection(Collection collection) {
      for (ResourceLocationAdapter resourceLocationAdapter2 : (Iterable<ResourceLocationAdapter>)(Iterable)(collection)) {
         try {
            GsonEntry gsonEntry = this.resolveGsonEntryByResourceLocation(resourceLocationAdapter2);

            try {
               this.applyGsonEntry(gsonEntry, resourceLocationAdapter2);
            } catch (Exception exception) {
               logger.warn("Unable to load variant: " + resourceLocationAdapter2.getLabelText() + " from " + resourceLocationAdapter2, exception);
            }
         } catch (Exception exception2) {
            logger.warn("Unable to load definition " + resourceLocationAdapter2, exception2);
         }
      }
   }

   private void updateState8() {
      this.updateState7();

      for (Item item : (Iterable<Item>)(Iterable)(Item.iterableExtensionAdapter)) {
         for (String text : (Iterable<String>)(Iterable)(this.resolveList(item))) {
            ResourceLocation location = this.resolveResourceLocation(text);
            this.map7.put(text, location);
            if (this.map5.get(location) == null) {
               try {
                  NativeLoggerEntry nativeLoggerEntry5 = this.resolveNativeLoggerEntry2(location);
                  this.map5.put(location, nativeLoggerEntry5);
               } catch (Exception exception) {
                  logger.warn("Unable to load item model: '" + location + "' for item: '" + Item.iterableExtensionAdapter.getObject2(item) + "'", exception);
               }
            }
         }
      }
   }

   private List resolveList(Item item) {
      List list = (List)this.map3.get(item);
      if (list == null) {
         list = Collections.singletonList(((ResourceLocation)Item.iterableExtensionAdapter.getObject2(item)).toString());
      }

      return list;
   }

   private ResourceLocation resolveResourceLocation(String text) {
      ResourceLocation location = new ResourceLocation(text);
      return new ResourceLocation(location.getText(), "item/" + location.getText1());
   }

   private NativeLoggerEntry resolveNativeLoggerEntry2(ResourceLocation location) throws IOException {
      NativeLoggerEntry entry = null;
      String text = location.getText1();
      if ("builtin/generated".equals(text)) {
         return nativeLoggerEntry3;
      } else if ("builtin/compass".equals(text)) {
         return nativeLoggerEntry2;
      } else if ("builtin/clock".equals(text)) {
         return nativeLoggerEntry4;
      } else if ("builtin/entity".equals(text)) {
         return nativeLoggerEntry;
      } else {
         Reader entry2;
         if (text.startsWith("builtin/")) {
            text = text.substring("builtin/".length());
            text = (String)map6.get(text);
            if (text == null) {
               throw new FileNotFoundException(location.toString());
            }

            entry2 = new StringReader(text);
         } else {
            location = this.resolveResourceLocation4(location);
            IResource iResource = this.iResourceManager.resolveIResourceByResourceLocation(location);
            entry2 = new InputStreamReader(iResource.getInputStream(), Charsets.UTF_8);
         }

         try {
            entry = NativeLoggerEntry.fromJson((Reader)entry2);
            entry.labelText = location.toString();
            String text2 = InternalPngManager.resolveText(location.getText1());
            applyNativeLoggerEntry((NativeLoggerEntry)(entry), text2);
         } finally {
            entry2.close();
         }

         return (NativeLoggerEntry)(entry);
      }
   }

   public static void applyNativeLoggerEntry(NativeLoggerEntry nativeLoggerEntry5, String text) {
      ResourceLocation location = resolveResourceLocation(nativeLoggerEntry5.getResourceLocation(), text);
      if (location != nativeLoggerEntry5.getResourceLocation()) {
         nativeLoggerEntry5.resourceLocation = location;
      }

      Map map8 = nativeLoggerEntry5.map;
      if (map8 != null) {
         for (Entry entry : (Iterable<Entry>)(Iterable)(map8.entrySet())) {
            String value = (String)entry.getValue();
            String text2 = resolveText(value, text);
            if (text2 != value) {
               entry.setValue(text2);
            }
         }
      }
   }

   private ResourceLocation resolveResourceLocation4(ResourceLocation location) {
      ResourceLocation location2 = location;
      String text = location.getText1();
      if (!text.startsWith("mcpatcher") && !text.startsWith("optifine")) {
         return new ResourceLocation(location.getText(), "models/" + location.getText1() + ".json");
      } else {
         if (!text.endsWith(".json")) {
            location2 = new ResourceLocation(location.getText(), text + ".json");
         }

         return location2;
      }
   }

   private void updateState9() {
      for (ResourceLocation location : (Iterable<ResourceLocation>)(Iterable)(this.map7.values())) {
         NativeLoggerEntry nativeLoggerEntry5 = (NativeLoggerEntry)this.map5.get(location);
         if (this.matchesNativeLoggerEntry(nativeLoggerEntry5)) {
            nativeLoggerEntry5 = this.resolveNativeLoggerEntry(nativeLoggerEntry5);
            if (nativeLoggerEntry5 != null) {
               nativeLoggerEntry5.labelText = location.toString();
            }

            this.map5.put(location, nativeLoggerEntry5);
         } else if (this.matchesNativeLoggerEntryByNativeLoggerEntr(nativeLoggerEntry5)) {
            this.map5.put(location, nativeLoggerEntry5);
         }
      }

      for (ServiceStringEntry serviceStringEntry : (Iterable<ServiceStringEntry>)(Iterable)(this.map2.values())) {
         if (!serviceStringEntry.isActive()) {
            serviceStringEntry.updateState4();
         }
      }
   }

   private void updateState6() {
      ArrayDeque arrayDeque = Queues.newArrayDeque();
      HashSet set2 = Sets.newHashSet();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Iterable)(this.map5.keySet())) {
         set2.add(location);
         location = ((NativeLoggerEntry)this.map5.get(location)).getResourceLocation();
         if (location != null) {
            arrayDeque.add(location);
         }
      }

      while (!arrayDeque.isEmpty()) {
         ResourceLocation location2 = (ResourceLocation)arrayDeque.pop();

         try {
            if (this.map5.get(location2) != null) {
               continue;
            }

            NativeLoggerEntry nativeLoggerEntry5 = this.resolveNativeLoggerEntry2(location2);
            this.map5.put(location2, nativeLoggerEntry5);
            ResourceLocation resourceLocation = nativeLoggerEntry5.getResourceLocation();
            if (resourceLocation != null && !set2.contains(resourceLocation)) {
               arrayDeque.add(resourceLocation);
            }
         } catch (Exception exception) {
            logger.warn("In parent chain: " + joiner.join(this.resolveListByResourceLocation(location2)) + "; unable to load model: '" + location2 + "'");
         }

         set2.add(location2);
      }
   }

   private Set getSet() {
      HashSet set2 = Sets.newHashSet();
      ArrayList list = Lists.newArrayList(this.map4.keySet());
      Collections.sort(list, new InternalComparatorAdapter(this));

      for (ResourceLocationAdapter resourceLocationAdapter2 : (Iterable<ResourceLocationAdapter>)(Iterable)(list)) {
         for (DynamicResourceLocationEntry dynamicResourceLocationEntry : (Iterable<DynamicResourceLocationEntry>)(Iterable)(((EnhancedStringEntry)this.map4.get(resourceLocationAdapter2)).getList())) {
            NativeLoggerEntry nativeLoggerEntry5 = (NativeLoggerEntry)this.map5.get(dynamicResourceLocationEntry.getResourceLocation());
            if (nativeLoggerEntry5 == null) {
               logger.warn("Missing model for: " + resourceLocationAdapter2);
            } else {
               set2.addAll(this.resolveSet(nativeLoggerEntry5));
            }
         }
      }

      set2.addAll(set);
      return set2;
   }

   private ResourceLocation resolveResourceLocation(ResourceLocation location) {
      return new ResourceLocation(location.getText(), "blockstates/" + location.getText1() + ".json");
   }

   private boolean matchesNativeLoggerEntryByNativeLoggerEntr(NativeLoggerEntry nativeLoggerEntry5) {
      return nativeLoggerEntry5 == null ? false : nativeLoggerEntry5.getNativeLoggerEntry() == nativeLoggerEntry;
   }

   private NativeLoggerEntry resolveNativeLoggerEntry(NativeLoggerEntry nativeLoggerEntry5) {
      return this.passiveListEntry.resolveNativeLoggerEntry(this.listenerAdapter27, nativeLoggerEntry5);
   }

   private Set getSetAsSet() {
      HashSet set2 = Sets.newHashSet();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(Iterable)(this.map7.values())) {
         NativeLoggerEntry nativeLoggerEntry5 = (NativeLoggerEntry)this.map5.get(location);
         if (nativeLoggerEntry5 != null) {
            set2.add(new ResourceLocation(nativeLoggerEntry5.resolveText("particle")));
            if (this.matchesNativeLoggerEntry(nativeLoggerEntry5)) {
               for (String text : (Iterable<String>)(Iterable)(PassiveListEntry.list)) {
                  ResourceLocation location2 = new ResourceLocation(nativeLoggerEntry5.resolveText(text));
                  if (nativeLoggerEntry5.getNativeLoggerEntry() == nativeLoggerEntry2 && !ListenerAdapter27.resourceLocation2.equals(location2)) {
                     ServiceStringEntry.setLabelText(location2.toString());
                  } else if (nativeLoggerEntry5.getNativeLoggerEntry() == nativeLoggerEntry4 && !ListenerAdapter27.resourceLocation2.equals(location2)) {
                     ServiceStringEntry.setLabelText2(location2.toString());
                  }

                  set2.add(location2);
               }
            } else if (!this.matchesNativeLoggerEntryByNativeLoggerEntr(nativeLoggerEntry5)) {
               for (Vector3fHolder vector3fHolder : (Iterable<Vector3fHolder>)(Iterable)(nativeLoggerEntry5.getList())) {
                  for (EnumFacingHolder enumFacingHolder : (Iterable<EnumFacingHolder>)(Iterable)(vector3fHolder.map.values())) {
                     ResourceLocation location3 = new ResourceLocation(nativeLoggerEntry5.resolveText(enumFacingHolder.labelText));
                     set2.add(location3);
                  }
               }
            }
         }
      }

      return set2;
   }

   private void updateState7() {
      this.map3.clear();
      this.map3
         .put(
            Item.processAction2(Blocks.stone),
            Lists.newArrayList(new String[]{"stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth"})
         );
      this.map3.put(Item.processAction2(Blocks.block115), Lists.newArrayList(new String[]{"dirt", "coarse_dirt", "podzol"}));
      this.map3
         .put(
            Item.processAction2(Blocks.block142),
            Lists.newArrayList(new String[]{"oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks"})
         );
      this.map3
         .put(
            Item.processAction2(Blocks.block83),
            Lists.newArrayList(new String[]{"oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling"})
         );
      this.map3.put(Item.processAction2(Blocks.blockSand), Lists.newArrayList(new String[]{"sand", "red_sand"}));
      this.map3.put(Item.processAction2(Blocks.block140), Lists.newArrayList(new String[]{"oak_log", "spruce_log", "birch_log", "jungle_log"}));
      this.map3
         .put(Item.processAction2(Blocks.decayableManager2), Lists.newArrayList(new String[]{"oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves"}));
      this.map3.put(Item.processAction2(Blocks.block11), Lists.newArrayList(new String[]{"sponge", "sponge_wet"}));
      this.map3.put(Item.processAction2(Blocks.block136), Lists.newArrayList(new String[]{"sandstone", "chiseled_sandstone", "smooth_sandstone"}));
      this.map3.put(Item.processAction2(Blocks.block119), Lists.newArrayList(new String[]{"red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"}));
      this.map3.put(Item.processAction2(Blocks.blockTallgrass), Lists.newArrayList(new String[]{"dead_bush", "tall_grass", "fern"}));
      this.map3.put(Item.processAction2(Blocks.blockDeadbush), Lists.newArrayList(new String[]{"dead_bush"}));
      this.map3
         .put(
            Item.processAction2(Blocks.wool),
            Lists.newArrayList(
               new String[]{
                  "black_wool",
                  "red_wool",
                  "green_wool",
                  "brown_wool",
                  "blue_wool",
                  "purple_wool",
                  "cyan_wool",
                  "silver_wool",
                  "gray_wool",
                  "pink_wool",
                  "lime_wool",
                  "yellow_wool",
                  "light_blue_wool",
                  "magenta_wool",
                  "orange_wool",
                  "white_wool"
               }
            )
         );
      this.map3.put(Item.processAction2(Blocks.immutableSetEntry2Entry), Lists.newArrayList(new String[]{"dandelion"}));
      this.map3
         .put(
            Item.processAction2(Blocks.immutableSetEntry2Entry2),
            Lists.newArrayList(
               new String[]{"poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy"}
            )
         );
      this.map3
         .put(
            Item.processAction2(Blocks.extraBlockAdapter6),
            Lists.newArrayList(
               new String[]{"stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"}
            )
         );
      this.map3.put(Item.processAction2(Blocks.extraBlockAdapter4), Lists.newArrayList(new String[]{"red_sandstone_slab"}));
      this.map3
         .put(
            Item.processAction2(Blocks.blockStainedGlass),
            Lists.newArrayList(
               new String[]{
                  "black_stained_glass",
                  "red_stained_glass",
                  "green_stained_glass",
                  "brown_stained_glass",
                  "blue_stained_glass",
                  "purple_stained_glass",
                  "cyan_stained_glass",
                  "silver_stained_glass",
                  "gray_stained_glass",
                  "pink_stained_glass",
                  "lime_stained_glass",
                  "yellow_stained_glass",
                  "light_blue_stained_glass",
                  "magenta_stained_glass",
                  "orange_stained_glass",
                  "white_stained_glass"
               }
            )
         );
      this.map3
         .put(
            Item.processAction2(Blocks.block22),
            Lists.newArrayList(
               new String[]{
                  "stone_monster_egg",
                  "cobblestone_monster_egg",
                  "stone_brick_monster_egg",
                  "mossy_brick_monster_egg",
                  "cracked_brick_monster_egg",
                  "chiseled_brick_monster_egg"
               }
            )
         );
      this.map3
         .put(
            Item.processAction2(Blocks.block42),
            Lists.newArrayList(new String[]{"stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick"})
         );
      this.map3
         .put(
            Item.processAction2(Blocks.extraBlockAdapter2),
            Lists.newArrayList(new String[]{"oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab"})
         );
      this.map3.put(Item.processAction2(Blocks.block63), Lists.newArrayList(new String[]{"cobblestone_wall", "mossy_cobblestone_wall"}));
      this.map3.put(Item.processAction2(Blocks.block51), Lists.newArrayList(new String[]{"anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged"}));
      this.map3.put(Item.processAction2(Blocks.block17), Lists.newArrayList(new String[]{"quartz_block", "chiseled_quartz_block", "quartz_column"}));
      this.map3
         .put(
            Item.processAction2(Blocks.block107),
            Lists.newArrayList(
               new String[]{
                  "black_stained_hardened_clay",
                  "red_stained_hardened_clay",
                  "green_stained_hardened_clay",
                  "brown_stained_hardened_clay",
                  "blue_stained_hardened_clay",
                  "purple_stained_hardened_clay",
                  "cyan_stained_hardened_clay",
                  "silver_stained_hardened_clay",
                  "gray_stained_hardened_clay",
                  "pink_stained_hardened_clay",
                  "lime_stained_hardened_clay",
                  "yellow_stained_hardened_clay",
                  "light_blue_stained_hardened_clay",
                  "magenta_stained_hardened_clay",
                  "orange_stained_hardened_clay",
                  "white_stained_hardened_clay"
               }
            )
         );
      this.map3
         .put(
            Item.processAction2(Blocks.blockStainedGlassPane),
            Lists.newArrayList(
               new String[]{
                  "black_stained_glass_pane",
                  "red_stained_glass_pane",
                  "green_stained_glass_pane",
                  "brown_stained_glass_pane",
                  "blue_stained_glass_pane",
                  "purple_stained_glass_pane",
                  "cyan_stained_glass_pane",
                  "silver_stained_glass_pane",
                  "gray_stained_glass_pane",
                  "pink_stained_glass_pane",
                  "lime_stained_glass_pane",
                  "yellow_stained_glass_pane",
                  "light_blue_stained_glass_pane",
                  "magenta_stained_glass_pane",
                  "orange_stained_glass_pane",
                  "white_stained_glass_pane"
               }
            )
         );
      this.map3.put(Item.processAction2(Blocks.decayableManager), Lists.newArrayList(new String[]{"acacia_leaves", "dark_oak_leaves"}));
      this.map3.put(Item.processAction2(Blocks.block104), Lists.newArrayList(new String[]{"acacia_log", "dark_oak_log"}));
      this.map3.put(Item.processAction2(Blocks.block58), Lists.newArrayList(new String[]{"prismarine", "prismarine_bricks", "dark_prismarine"}));
      this.map3
         .put(
            Item.processAction2(Blocks.block141),
            Lists.newArrayList(
               new String[]{
                  "black_carpet",
                  "red_carpet",
                  "green_carpet",
                  "brown_carpet",
                  "blue_carpet",
                  "purple_carpet",
                  "cyan_carpet",
                  "silver_carpet",
                  "gray_carpet",
                  "pink_carpet",
                  "lime_carpet",
                  "yellow_carpet",
                  "light_blue_carpet",
                  "magenta_carpet",
                  "orange_carpet",
                  "white_carpet"
               }
            )
         );
      this.map3
         .put(
            Item.processAction2(Blocks.blockDoublePlant),
            Lists.newArrayList(new String[]{"sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia"})
         );
      this.map3.put(Items.bow, Lists.newArrayList(new String[]{"bow", "bow_pulling_0", "bow_pulling_1", "bow_pulling_2"}));
      this.map3.put(Items.coal, Lists.newArrayList(new String[]{"coal", "charcoal"}));
      this.map3.put(Items.fishingRod, Lists.newArrayList(new String[]{"fishing_rod", "fishing_rod_cast"}));
      this.map3.put(Items.fish, Lists.newArrayList(new String[]{"cod", "salmon", "clownfish", "pufferfish"}));
      this.map3.put(Items.cookedFish, Lists.newArrayList(new String[]{"cooked_cod", "cooked_salmon"}));
      this.map3
         .put(
            Items.dye,
            Lists.newArrayList(
               new String[]{
                  "dye_black",
                  "dye_red",
                  "dye_green",
                  "dye_brown",
                  "dye_blue",
                  "dye_purple",
                  "dye_cyan",
                  "dye_silver",
                  "dye_gray",
                  "dye_pink",
                  "dye_lime",
                  "dye_yellow",
                  "dye_light_blue",
                  "dye_magenta",
                  "dye_orange",
                  "dye_white"
               }
            )
         );
      this.map3.put(Items.potion, Lists.newArrayList(new String[]{"bottle_drinkable", "bottle_splash"}));
      this.map3.put(Items.skull, Lists.newArrayList(new String[]{"skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper"}));
      this.map3.put(Item.processAction2(Blocks.block117), Lists.newArrayList(new String[]{"oak_fence_gate"}));
      this.map3.put(Item.processAction2(Blocks.block111), Lists.newArrayList(new String[]{"oak_fence"}));
      this.map3.put(Items.woodenDoor, Lists.newArrayList(new String[]{"oak_door"}));
      InvalidItemIDManager.updateState2();
      InvalidItemIDManager.applyJsonManager(this);
   }

   private ResourceLocation resolveResourceLocation2(ResourceLocation location) {
      for (Entry entry : (Iterable<Entry>)(Iterable)(this.map5.entrySet())) {
         NativeLoggerEntry value = (NativeLoggerEntry)entry.getValue();
         if (value != null && location.equals(value.getResourceLocation())) {
            return (ResourceLocation)entry.getKey();
         }
      }

      return null;
   }

   public static void applyItemByItem(Item item, ResourceLocation[] locationArray) {
   }

   private boolean matchesNativeLoggerEntry(NativeLoggerEntry nativeLoggerEntry5) {
      if (nativeLoggerEntry5 == null) {
         return false;
      } else {
         NativeLoggerEntry nativeLoggerEntry6 = nativeLoggerEntry5.getNativeLoggerEntry();
         return nativeLoggerEntry6 == nativeLoggerEntry3 || nativeLoggerEntry6 == nativeLoggerEntry2 || nativeLoggerEntry6 == nativeLoggerEntry4;
      }
   }

   private void updateState4() {
      for (ResourceLocationAdapter resourceLocationAdapter2 : (Iterable<ResourceLocationAdapter>)(Iterable)(this.map4.keySet())) {
         Iterator iterator = ((EnhancedStringEntry)this.map4.get(resourceLocationAdapter2)).getList().iterator();

         while (iterator.hasNext()) {
            ResourceLocation resourceLocation = ((DynamicResourceLocationEntry)iterator.next()).getResourceLocation();
            if (this.map5.get(resourceLocation) == null) {
               try {
                  NativeLoggerEntry nativeLoggerEntry5 = this.resolveNativeLoggerEntry2(resourceLocation);
                  this.map5.put(resourceLocation, nativeLoggerEntry5);
               } catch (Exception exception) {
                  logger.warn("Unable to load block model: '" + resourceLocation + "' for variant: '" + resourceLocationAdapter2 + "'", exception);
               }
            }
         }
      }
   }

   public JsonManager(IResourceManager iResourceManager2, ListenerAdapter27 listenerAdapter, CommonMapEntry commonMapEntry2) {
      this.iResourceManager = iResourceManager2;
      this.listenerAdapter27 = listenerAdapter;
      this.commonMapEntry = commonMapEntry2;
   }

   static {
      map6.put(
         "missing",
         "{ \"textures\": {   \"particle\": \"missingno\",   \"missingno\": \"missingno\"}, \"elements\": [ {     \"from\": [ 0, 0, 0 ],     \"to\": [ 16, 16, 16 ],     \"faces\": {         \"down\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"down\", \"texture\": \"#missingno\" },         \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"up\", \"texture\": \"#missingno\" },         \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"north\", \"texture\": \"#missingno\" },         \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"south\", \"texture\": \"#missingno\" },         \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"west\", \"texture\": \"#missingno\" },         \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"east\", \"texture\": \"#missingno\" }    }}]}"
      );
      nativeLoggerEntry3.labelText = "generation marker";
      nativeLoggerEntry2.labelText = "compass generation marker";
      nativeLoggerEntry4.labelText = "class generation marker";
      nativeLoggerEntry.labelText = "block entity marker";
   }
}
