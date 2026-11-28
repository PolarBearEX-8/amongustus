package net.minecraft.client.resources.model;

import java.util.Comparator;
import net.minecraft.client.renderer.texture.IIconCreator;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelBakery {
   public static final ModelResourceLocation MODEL_MISSING = new ModelResourceLocation("builtin/missing", "missing");
   private static final Set LOCATIONS_BUILTIN_TEXTURES = Sets.newHashSet(
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
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Map BUILT_IN_MODELS = Maps.newHashMap();
   private static final Joiner JOINER = Joiner.on(" -> ");
   private static final ModelBlock MODEL_GENERATED = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   private static final ModelBlock MODEL_COMPASS = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   private static final ModelBlock MODEL_CLOCK = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   private static final ModelBlock MODEL_ENTITY = ModelBlock.deserialize(
      "{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}"
   );
   private final IResourceManager resourceManager;
   private final Map sprites = Maps.newHashMap();
   private final Map models = Maps.newLinkedHashMap();
   private final Map variants = Maps.newLinkedHashMap();
   private final TextureMap textureMap;
   private final BlockModelShapes blockModelShapes;
   private final FaceBakery faceBakery = new FaceBakery();
   private final ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
   private final Map blockDefinitions = Maps.newHashMap();
   private final RegistrySimple bakedRegistry = new RegistrySimple();
   private final Map itemLocations = Maps.newLinkedHashMap();
   private final Map variantNames = Maps.newIdentityHashMap();

   private ResourceLocation getItemLocation(String str) {
      ResourceLocation location = new ResourceLocation(str);
      return new ResourceLocation(location.getResourceDomain(), "item/" + location.getResourcePath());
   }

   private List getVariantNames(Item item) {
      List list = (List)this.variantNames.get(item);
      if (list == null) {
         list = Collections.singletonList(((ResourceLocation)Item.itemRegistry.getNameForObject(item)).toString());
      }

      return list;
   }

   private List getParentPath(ResourceLocation location) {
      ArrayList list = Lists.newArrayList(new ResourceLocation[]{location});
      ResourceLocation location2 = location;

      while ((location2 = this.getParentLocation(location2)) != null) {
         list.add(0, location2);
      }

      return list;
   }

   private Set getVariantsTextureLocations() {
      HashSet set = Sets.newHashSet();
      ArrayList list = Lists.newArrayList(this.variants.keySet());
      Collections.sort(list, new ModelBakery.EntryComparator(this));

      for (ModelResourceLocation modelResourceLocation : (Iterable<ModelResourceLocation>)(list)) {

         for (ModelBlockDefinition.Variant variant : (Iterable<ModelBlockDefinition.Variant>)(((ModelBlockDefinition.Variants)this.variants.get(modelResourceLocation)).getVariants())) {
            ModelBlock modelBlock = (ModelBlock)this.models.get(variant.getModelLocation());
            if (modelBlock == null) {
               LOGGER.warn("Missing model for: " + modelResourceLocation);
            } else {
               set.addAll(this.getTextureLocations(modelBlock));
            }
         }
      }

      set.addAll(LOCATIONS_BUILTIN_TEXTURES);
      return set;
   }

   private void registerVariant(ModelBlockDefinition modelBlockDefinition, ModelResourceLocation modelResourceLocation) {
      this.variants.put(modelResourceLocation, modelBlockDefinition.getVariants(modelResourceLocation.getVariant()));
   }

   private void loadItemModels() {
      this.registerVariantNames();

      for (Item item : (Iterable<Item>)(Item.itemRegistry)) {
         for (String str : (Iterable<String>)(this.getVariantNames(item))) {
            ResourceLocation itemLocation = this.getItemLocation(str);
            this.itemLocations.put(str, itemLocation);
            if (this.models.get(itemLocation) == null) {
               try {
                  ModelBlock modelBlock = this.loadModel(itemLocation);
                  this.models.put(itemLocation, modelBlock);
               } catch (Exception e) {
                  LOGGER.warn("Unable to load item model: '" + itemLocation + "' for item: '" + Item.itemRegistry.getNameForObject(item) + "'", e);
               }
            }
         }
      }
   }

   private ResourceLocation getModelLocation(ResourceLocation location) {
      ResourceLocation location2 = location;
      String resourcePath = location.getResourcePath();
      if (!resourcePath.startsWith("mcpatcher") && !resourcePath.startsWith("optifine")) {
         return new ResourceLocation(location.getResourceDomain(), "models/" + location.getResourcePath() + ".json");
      } else {
         if (!resourcePath.endsWith(".json")) {
            location2 = new ResourceLocation(location.getResourceDomain(), resourcePath + ".json");
         }

         return location2;
      }
   }

   private void loadVariantModels() {
      ArrayDeque arrayDeque = Queues.newArrayDeque();
      HashSet set = Sets.newHashSet();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(this.models.keySet())) {
         set.add(location);
         ResourceLocation parentLocation = ((ModelBlock)this.models.get(location)).getParentLocation();
         if (parentLocation != null) {
            arrayDeque.add(parentLocation);
         }
      }

      while (!arrayDeque.isEmpty()) {
         ResourceLocation location2 = (ResourceLocation)arrayDeque.pop();

         try {
            if (this.models.get(location2) != null) {
               continue;
            }

            ModelBlock modelBlock = this.loadModel(location2);
            this.models.put(location2, modelBlock);
            ResourceLocation parentLocation2 = modelBlock.getParentLocation();
            if (parentLocation2 != null && !set.contains(parentLocation2)) {
               arrayDeque.add(parentLocation2);
            }
         } catch (Exception e) {
            LOGGER.warn("In parent chain: " + JOINER.join(this.getParentPath(location2)) + "; unable to load model: '" + location2 + "'");
         }

         set.add(location2);
      }
   }

   public static ResourceLocation fixResourceLocation(ResourceLocation location, String str) {
      if (location != null && str != null) {
         if (!location.getResourceDomain().equals("minecraft")) {
            return location;
         } else {
            String resourcePath = location.getResourcePath();
            String str2 = fixResourcePath(resourcePath, str);
            if (str2 != resourcePath) {
               location = new ResourceLocation(location.getResourceDomain(), str2);
            }

            return location;
         }
      } else {
         return location;
      }
   }

   public IRegistry setupModelRegistry() {
      this.loadVariantItemModels();
      this.loadModelsCheck();
      this.loadSprites();
      this.bakeItemModels();
      this.bakeBlockModels();
      return this.bakedRegistry;
   }

   @Deprecated
   public static void addVariantName(Item item, String[] strings) {
   }

   private void loadVariants(Collection items) {
      for (ModelResourceLocation modelResourceLocation : (Iterable<ModelResourceLocation>)(items)) {
         try {
            ModelBlockDefinition modelBlockDefinition = this.getModelBlockDefinition(modelResourceLocation);

            try {
               this.registerVariant(modelBlockDefinition, modelResourceLocation);
            } catch (Exception e) {
               LOGGER.warn("Unable to load variant: " + modelResourceLocation.getVariant() + " from " + modelResourceLocation, e);
            }
         } catch (Exception e2) {
            LOGGER.warn("Unable to load definition " + modelResourceLocation, e2);
         }
      }
   }

   private boolean hasItemModel(ModelBlock modelBlock) {
      if (modelBlock == null) {
         return false;
      } else {
         ModelBlock rootModel = modelBlock.getRootModel();
         return rootModel == MODEL_GENERATED || rootModel == MODEL_COMPASS || rootModel == MODEL_CLOCK;
      }
   }

   public static void registerItemVariants(Item item, ResourceLocation[] locations) {
   }

   private void registerVariantNames() {
      this.variantNames.clear();
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stone),
            Lists.newArrayList(new String[]{"stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth"})
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.dirt), Lists.newArrayList(new String[]{"dirt", "coarse_dirt", "podzol"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.planks),
            Lists.newArrayList(new String[]{"oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks"})
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.sapling),
            Lists.newArrayList(new String[]{"oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling"})
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.sand), Lists.newArrayList(new String[]{"sand", "red_sand"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.log), Lists.newArrayList(new String[]{"oak_log", "spruce_log", "birch_log", "jungle_log"}));
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.leaves), Lists.newArrayList(new String[]{"oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.sponge), Lists.newArrayList(new String[]{"sponge", "sponge_wet"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.sandstone), Lists.newArrayList(new String[]{"sandstone", "chiseled_sandstone", "smooth_sandstone"}));
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.red_sandstone), Lists.newArrayList(new String[]{"red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.tallgrass), Lists.newArrayList(new String[]{"dead_bush", "tall_grass", "fern"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.deadbush), Lists.newArrayList(new String[]{"dead_bush"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.wool),
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
      this.variantNames.put(Item.getItemFromBlock(Blocks.yellow_flower), Lists.newArrayList(new String[]{"dandelion"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.red_flower),
            Lists.newArrayList(
               new String[]{"poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy"}
            )
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stone_slab),
            Lists.newArrayList(
               new String[]{"stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"}
            )
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.stone_slab2), Lists.newArrayList(new String[]{"red_sandstone_slab"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_glass),
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
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.monster_egg),
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
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stonebrick),
            Lists.newArrayList(new String[]{"stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick"})
         );
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.wooden_slab),
            Lists.newArrayList(new String[]{"oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab"})
         );
      this.variantNames.put(Item.getItemFromBlock(Blocks.cobblestone_wall), Lists.newArrayList(new String[]{"cobblestone_wall", "mossy_cobblestone_wall"}));
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.anvil), Lists.newArrayList(new String[]{"anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged"}));
      this.variantNames
         .put(Item.getItemFromBlock(Blocks.quartz_block), Lists.newArrayList(new String[]{"quartz_block", "chiseled_quartz_block", "quartz_column"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_hardened_clay),
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
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.stained_glass_pane),
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
      this.variantNames.put(Item.getItemFromBlock(Blocks.leaves2), Lists.newArrayList(new String[]{"acacia_leaves", "dark_oak_leaves"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.log2), Lists.newArrayList(new String[]{"acacia_log", "dark_oak_log"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.prismarine), Lists.newArrayList(new String[]{"prismarine", "prismarine_bricks", "dark_prismarine"}));
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.carpet),
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
      this.variantNames
         .put(
            Item.getItemFromBlock(Blocks.double_plant),
            Lists.newArrayList(new String[]{"sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia"})
         );
      this.variantNames.put(Items.bow, Lists.newArrayList(new String[]{"bow", "bow_pulling_0", "bow_pulling_1", "bow_pulling_2"}));
      this.variantNames.put(Items.coal, Lists.newArrayList(new String[]{"coal", "charcoal"}));
      this.variantNames.put(Items.fishing_rod, Lists.newArrayList(new String[]{"fishing_rod", "fishing_rod_cast"}));
      this.variantNames.put(Items.fish, Lists.newArrayList(new String[]{"cod", "salmon", "clownfish", "pufferfish"}));
      this.variantNames.put(Items.cooked_fish, Lists.newArrayList(new String[]{"cooked_cod", "cooked_salmon"}));
      this.variantNames
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
      this.variantNames.put(Items.potionitem, Lists.newArrayList(new String[]{"bottle_drinkable", "bottle_splash"}));
      this.variantNames.put(Items.skull, Lists.newArrayList(new String[]{"skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence_gate), Lists.newArrayList(new String[]{"oak_fence_gate"}));
      this.variantNames.put(Item.getItemFromBlock(Blocks.oak_fence), Lists.newArrayList(new String[]{"oak_fence"}));
      this.variantNames.put(Items.oak_door, Lists.newArrayList(new String[]{"oak_door"}));
      CustomItems.update();
      CustomItems.loadModels(this);
   }

   private void bakeBlockModels() {
      for (ModelResourceLocation modelResourceLocation : (Iterable<ModelResourceLocation>)(this.variants.keySet())) {
         WeightedBakedModel.Builder builder = new WeightedBakedModel.Builder();
         int n = 0;

         for (ModelBlockDefinition.Variant variant : (Iterable<ModelBlockDefinition.Variant>)(((ModelBlockDefinition.Variants)this.variants.get(modelResourceLocation)).getVariants())) {
            ModelBlock modelBlock = (ModelBlock)this.models.get(variant.getModelLocation());
            if (modelBlock != null && modelBlock.isResolved()) {
               n++;
               builder.add(this.bakeModel(modelBlock, variant.getRotation(), variant.isUvLocked()), variant.getWeight());
            } else {
               LOGGER.warn("Missing model for: " + modelResourceLocation);
            }
         }

         if (n == 0) {
            LOGGER.warn("No weighted models for: " + modelResourceLocation);
         } else if (n == 1) {
            this.bakedRegistry.putObject(modelResourceLocation, builder.first());
         } else {
            this.bakedRegistry.putObject(modelResourceLocation, builder.build());
         }
      }

      for (Entry entry : (Iterable<Entry>)(this.itemLocations.entrySet())) {
         ResourceLocation value = (ResourceLocation)entry.getValue();
         ModelResourceLocation modelResourceLocation2 = new ModelResourceLocation((String)entry.getKey(), "inventory");
         ModelBlock modelBlock2 = (ModelBlock)this.models.get(value);
         if (modelBlock2 == null || !modelBlock2.isResolved()) {
            LOGGER.warn("Missing model for: " + value);
         } else if (this.isCustomRenderer(modelBlock2)) {
            this.bakedRegistry.putObject(modelResourceLocation2, new BuiltInModel(modelBlock2.getAllTransforms()));
         } else {
            this.bakedRegistry.putObject(modelResourceLocation2, this.bakeModel(modelBlock2, ModelRotation.X0_Y0, false));
         }
      }
   }

   private void loadVariantItemModels() {
      this.loadVariants(this.blockModelShapes.getBlockStateMapper().putAllStateModelLocations().values());
      this.variants
         .put(
            MODEL_MISSING,
            new ModelBlockDefinition.Variants(
               MODEL_MISSING.getVariant(),
               Lists.newArrayList(
                  new ModelBlockDefinition.Variant[]{
                     new ModelBlockDefinition.Variant(new ResourceLocation(MODEL_MISSING.getResourcePath()), ModelRotation.X0_Y0, false, 1)
                  }
               )
            )
         );
      ResourceLocation location = new ResourceLocation("item_frame");
      ModelBlockDefinition modelBlockDefinition = this.getModelBlockDefinition(location);
      this.registerVariant(modelBlockDefinition, new ModelResourceLocation(location, "normal"));
      this.registerVariant(modelBlockDefinition, new ModelResourceLocation(location, "map"));
      this.loadModels();
      this.loadItemModels();
   }

   private boolean isCustomRenderer(ModelBlock modelBlock) {
      if (modelBlock == null) {
         return false;
      } else {
         return modelBlock.getRootModel() == MODEL_ENTITY;
      }
   }

   private ModelBlock makeItemModel(ModelBlock modelBlock) {
      return this.itemModelGenerator.makeItemModel(this.textureMap, modelBlock);
   }

   private void loadModelsCheck() {
      this.loadVariantModels();

      for (ModelBlock modelBlock : (Iterable<ModelBlock>)(this.models.values())) {
         modelBlock.getParentFromMap(this.models);
      }

      ModelBlock.checkModelHierarchy(this.models);
   }

   private Set getTextureLocations(ModelBlock modelBlock) {
      HashSet set = Sets.newHashSet();

      for (BlockPart blockPart : (Iterable<BlockPart>)(modelBlock.getElements())) {
         for (BlockPartFace blockPartFace : (Iterable<BlockPartFace>)(blockPart.mapFaces.values())) {
            set.add(new ResourceLocation(modelBlock.resolveTextureName(blockPartFace.texture)));
         }
      }

      set.add(new ResourceLocation(modelBlock.resolveTextureName("particle")));
      return set;
   }

   private ResourceLocation getParentLocation(ResourceLocation location) {
      for (Entry entry : (Iterable<Entry>)(this.models.entrySet())) {
         ModelBlock value = (ModelBlock)entry.getValue();
         if (value != null && location.equals(value.getParentLocation())) {
            return (ResourceLocation)entry.getKey();
         }
      }

      return null;
   }

   private static String fixResourcePath(String str, String str2) {
      str = TextureUtils.fixResourcePath(str, str2);
      str = StrUtils.removeSuffix2(str, ".json");
      return StrUtils.removeSuffix2(str, ".png");
   }

   public static Map accessSprites(ModelBakery modelBakery) {
      return modelBakery.sprites;
   }

   static {
      BUILT_IN_MODELS.put(
         "missing",
         "{ \"textures\": {   \"particle\": \"missingno\",   \"missingno\": \"missingno\"}, \"elements\": [ {     \"from\": [ 0, 0, 0 ],     \"to\": [ 16, 16, 16 ],     \"faces\": {         \"down\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"down\", \"texture\": \"#missingno\" },         \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"up\", \"texture\": \"#missingno\" },         \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"north\", \"texture\": \"#missingno\" },         \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"south\", \"texture\": \"#missingno\" },         \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"west\", \"texture\": \"#missingno\" },         \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"east\", \"texture\": \"#missingno\" }    }}]}"
      );
      MODEL_GENERATED.name = "generation marker";
      MODEL_COMPASS.name = "compass generation marker";
      MODEL_CLOCK.name = "class generation marker";
      MODEL_ENTITY.name = "block entity marker";
   }

   private void bakeItemModels() {
      for (ResourceLocation location : (Iterable<ResourceLocation>)(this.itemLocations.values())) {
         ModelBlock modelBlock = (ModelBlock)this.models.get(location);
         if (this.hasItemModel(modelBlock)) {
            ModelBlock modelBlock2 = this.makeItemModel(modelBlock);
            if (modelBlock2 != null) {
               modelBlock2.name = location.toString();
            }

            this.models.put(location, modelBlock2);
         } else if (this.isCustomRenderer(modelBlock)) {
            this.models.put(location, modelBlock);
         }
      }

      for (TextureAtlasSprite textureAtlasSprite : (Iterable<TextureAtlasSprite>)(this.sprites.values())) {
         if (!textureAtlasSprite.hasAnimationMetadata()) {
            textureAtlasSprite.clearFramesTextureData();
         }
      }
   }

   private void loadModels() {
      for (ModelResourceLocation modelResourceLocation : (Iterable<ModelResourceLocation>)(this.variants.keySet())) {
         for (ModelBlockDefinition.Variant variant : (Iterable<ModelBlockDefinition.Variant>)(((ModelBlockDefinition.Variants)this.variants.get(modelResourceLocation)).getVariants())) {
            ResourceLocation modelLocation = variant.getModelLocation();
            if (this.models.get(modelLocation) == null) {
               try {
                  ModelBlock modelBlock = this.loadModel(modelLocation);
                  this.models.put(modelLocation, modelBlock);
               } catch (Exception e) {
                  LOGGER.warn("Unable to load block model: '" + modelLocation + "' for variant: '" + modelResourceLocation + "'", e);
               }
            }
         }
      }
   }

   public ModelBlock getModelBlock(ResourceLocation location) {
      return (ModelBlock)this.models.get(location);
   }

   public void loadItemModel(String str, ResourceLocation location, ResourceLocation location2) {
      this.itemLocations.put(str, location);
      if (this.models.get(location) == null) {
         try {
            ModelBlock modelBlock = this.loadModel(location);
            this.models.put(location, modelBlock);
         } catch (Exception e) {
            LOGGER.warn("Unable to load item model: '{}' for item: '{}'", new Object[]{location, location2});
            LOGGER.warn(e.getClass().getName() + ": " + e.getMessage());
         }
      }
   }

   public ModelBakery(IResourceManager resourceManager2, TextureMap textureMap2, BlockModelShapes blockModelShapes2) {
      this.resourceManager = resourceManager2;
      this.textureMap = textureMap2;
      this.blockModelShapes = blockModelShapes2;
   }

   private void loadSprites() {
      Set set = this.getVariantsTextureLocations();
      set.addAll(this.getItemsTextureLocations());
      set.remove(TextureMap.LOCATION_MISSING_TEXTURE);
      ModelBakery.DefaultIconCreator obj = new ModelBakery.DefaultIconCreator(this, set);
      this.textureMap.loadSprites(this.resourceManager, obj);
      this.sprites.put(new ResourceLocation("missingno"), this.textureMap.getMissingSprite());
   }

   public static void fixModelLocations(ModelBlock modelBlock, String str) {
      ResourceLocation location = fixResourceLocation(modelBlock.getParentLocation(), str);
      if (location != modelBlock.getParentLocation()) {
         modelBlock.parentLocation = location;
      }

      Map textures = modelBlock.textures;
      if (textures != null) {
         for (Entry entry : (Iterable<Entry>)(textures.entrySet())) {
            String value = (String)entry.getValue();
            String str2 = fixResourcePath(value, str);
            if (str2 != value) {
               entry.setValue(str2);
            }
         }
      }
   }

   private ResourceLocation getBlockstateLocation(ResourceLocation location) {
      return new ResourceLocation(location.getResourceDomain(), "blockstates/" + location.getResourcePath() + ".json");
   }

   public IBakedModel bakeModel(ModelBlock modelBlock, ModelRotation modelRotation, boolean flag) {
      TextureAtlasSprite textureAtlasSprite = (TextureAtlasSprite)this.sprites.get(new ResourceLocation(modelBlock.resolveTextureName("particle")));
      SimpleBakedModel.Builder builder = new SimpleBakedModel.Builder(modelBlock).setTexture(textureAtlasSprite);

      for (BlockPart blockPart : (Iterable<BlockPart>)(modelBlock.getElements())) {
         for (EnumFacing facing : (Iterable<EnumFacing>)(blockPart.mapFaces.keySet())) {
            BlockPartFace blockPartFace = (BlockPartFace)blockPart.mapFaces.get(facing);
            TextureAtlasSprite textureAtlasSprite2 = (TextureAtlasSprite)this.sprites.get(new ResourceLocation(modelBlock.resolveTextureName(blockPartFace.texture)));
            if (blockPartFace.cullFace != null && true) {
               builder.addFaceQuad(modelRotation.rotate(blockPartFace.cullFace), this.makeBakedQuad(blockPart, blockPartFace, textureAtlasSprite2, facing, modelRotation, flag));
            } else {
               builder.addGeneralQuad(this.makeBakedQuad(blockPart, blockPartFace, textureAtlasSprite2, facing, modelRotation, flag));
            }
         }
      }

      return builder.makeBakedModel();
   }

   private ModelBlock loadModel(ResourceLocation location) throws IOException {
      String resourcePath = location.getResourcePath();
      if ("builtin/generated".equals(resourcePath)) {
         return MODEL_GENERATED;
      } else if ("builtin/compass".equals(resourcePath)) {
         return MODEL_COMPASS;
      } else if ("builtin/clock".equals(resourcePath)) {
         return MODEL_CLOCK;
      } else if ("builtin/entity".equals(resourcePath)) {
         return MODEL_ENTITY;
      } else {
         Object obj;
         if (resourcePath.startsWith("builtin/")) {
            String str = resourcePath.substring("builtin/".length());
            String str2 = (String)BUILT_IN_MODELS.get(str);
            if (str2 == null) {
               throw new FileNotFoundException(location.toString());
            }

            obj = new StringReader(str2);
         } else {
            location = this.getModelLocation(location);
            IResource resource = this.resourceManager.getResource(location);
            obj = new InputStreamReader(resource.getInputStream(), Charsets.UTF_8);
         }

         ModelBlock modelBlock;
         try {
            ModelBlock modelBlock2 = ModelBlock.deserialize((Reader)obj);
            modelBlock2.name = location.toString();
            modelBlock = modelBlock2;
            fixModelLocations(modelBlock2, TextureUtils.getBasePath(location.getResourcePath()));
         } finally {
            ((Reader)obj).close();
         }

         return modelBlock;
      }
   }

   private Set getItemsTextureLocations() {
      HashSet set = Sets.newHashSet();

      for (ResourceLocation location : (Iterable<ResourceLocation>)(this.itemLocations.values())) {
         ModelBlock modelBlock = (ModelBlock)this.models.get(location);
         if (modelBlock != null) {
            set.add(new ResourceLocation(modelBlock.resolveTextureName("particle")));
            if (this.hasItemModel(modelBlock)) {
               for (String str : (Iterable<String>)(ItemModelGenerator.LAYERS)) {
                  ResourceLocation location2 = new ResourceLocation(modelBlock.resolveTextureName(str));
                  if (modelBlock.getRootModel() == MODEL_COMPASS && !TextureMap.LOCATION_MISSING_TEXTURE.equals(location2)) {
                     TextureAtlasSprite.setLocationNameCompass(location2.toString());
                  } else if (modelBlock.getRootModel() == MODEL_CLOCK && !TextureMap.LOCATION_MISSING_TEXTURE.equals(location2)) {
                     TextureAtlasSprite.setLocationNameClock(location2.toString());
                  }

                  set.add(location2);
               }
            } else if (!this.isCustomRenderer(modelBlock)) {
               for (BlockPart blockPart : (Iterable<BlockPart>)(modelBlock.getElements())) {
                  for (BlockPartFace blockPartFace : (Iterable<BlockPartFace>)(blockPart.mapFaces.values())) {
                     set.add(new ResourceLocation(modelBlock.resolveTextureName(blockPartFace.texture)));
                  }
               }
            }
         }
      }

      return set;
   }

   private ModelBlockDefinition getModelBlockDefinition(ResourceLocation location) {
      ResourceLocation blockstateLocation = this.getBlockstateLocation(location);
      ModelBlockDefinition modelBlockDefinition = (ModelBlockDefinition)this.blockDefinitions.get(blockstateLocation);
      if (modelBlockDefinition == null) {
         ArrayList list = Lists.newArrayList();

         try {
            for (IResource resource : (Iterable<IResource>)(this.resourceManager.getAllResources(blockstateLocation))) {
               InputStream in = null;

               try {
                  in = resource.getInputStream();
                  list.add(ModelBlockDefinition.parseFromReader(new InputStreamReader(in, Charsets.UTF_8)));
               } catch (Exception e) {
                  throw new RuntimeException(
                     "Encountered an exception when loading model definition of '"
                        + location
                        + "' from: '"
                        + resource.getResourceLocation()
                        + "' in resourcepack: '"
                        + resource.getResourcePackName()
                        + "'",
                     e
                  );
               } finally {
                  IOUtils.closeQuietly(in);
               }
            }
         } catch (IOException e2) {
            throw new RuntimeException("Encountered an exception when loading model definition of model " + blockstateLocation, e2);
         }

         modelBlockDefinition = new ModelBlockDefinition((List)list);
         this.blockDefinitions.put(blockstateLocation, modelBlockDefinition);
      }

      return modelBlockDefinition;
   }

   private BakedQuad makeBakedQuad(BlockPart blockPart, BlockPartFace blockPartFace, TextureAtlasSprite textureAtlasSprite, EnumFacing facing, ModelRotation modelRotation, boolean flag) {
      return this.faceBakery.makeBakedQuad(blockPart.positionFrom, blockPart.positionTo, blockPartFace, textureAtlasSprite, facing, modelRotation, blockPart.partRotation, flag, blockPart.shade);
   }


   public static class EntryComparator implements Comparator {
      public final ModelBakery modelBakery;

      public EntryComparator(ModelBakery modelBakery2) {
         this.modelBakery = modelBakery2;
      }

      @Override
      public int compare(Object a, Object b) {
         return this.compare((ModelResourceLocation)a, (ModelResourceLocation)b);
      }

      public int compare(ModelResourceLocation a, ModelResourceLocation b) {
         return a.toString().compareTo(b.toString());
      }
   }

   public static class DefaultIconCreator implements IIconCreator {
      public final Set spriteLocations;
      public final ModelBakery modelBakery;

      @Override
      public void registerSprites(TextureMap textureMap) {
         for (ResourceLocation location : (Iterable<ResourceLocation>)(this.spriteLocations)) {
            TextureAtlasSprite textureAtlasSprite = textureMap.registerSprite(location);
            ModelBakery.accessSprites(this.modelBakery).put(location, textureAtlasSprite);
         }
      }

      public DefaultIconCreator(ModelBakery modelBakery2, Set set) {
         this.modelBakery = modelBakery2;
         this.spriteLocations = set;
      }
   }
}
