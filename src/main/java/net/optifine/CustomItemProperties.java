package net.optifine;

import net.minecraft.client.resources.model.SimpleBakedModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.config.IParserInt;
import net.optifine.config.NbtTagValue;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.render.Blender;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties {
   public String name = null;
   public String basePath = null;
   public int type = 1;
   public int[] items = null;
   public String texture = null;
   public Map mapTextures = null;
   public String model = null;
   public Map mapModels = null;
   public RangeListInt damage = null;
   public boolean damagePercent = false;
   public int damageMask = 0;
   public RangeListInt stackSize = null;
   public RangeListInt enchantmentIds = null;
   public RangeListInt enchantmentLevels = null;
   public NbtTagValue[] nbtTagValues = null;
   public int hand = 0;
   public int blend = 1;
   public float speed = 0.0F;
   public float rotation = 0.0F;
   public int layer = 0;
   public float duration = 1.0F;
   public int weight = 0;
   public ResourceLocation textureLocation = null;
   public Map mapTextureLocations = null;
   public TextureAtlasSprite sprite = null;
   public Map mapSprites = null;
   public IBakedModel bakedModelTexture = null;
   public Map mapBakedModelsTexture = null;
   public IBakedModel bakedModelFull = null;
   public Map mapBakedModelsFull = null;
   private int textureWidth = 0;
   private int textureHeight = 0;
   public static final int TYPE_UNKNOWN = 0;
   public static final int TYPE_ITEM = 1;
   public static final int TYPE_ENCHANTMENT = 2;
   public static final int TYPE_ARMOR = 3;
   public static final int HAND_ANY = 0;
   public static final int HAND_MAIN = 1;
   public static final int HAND_OFF = 2;
   public static final String INVENTORY = null;

   private NbtTagValue[] parseNbtTagValues(Properties properties) {
      String str = "nbt.";
      Map matchingProperties = getMatchingProperties(properties, str);
      if (matchingProperties.size() <= 0) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (Object obj : matchingProperties.keySet()) {
            String str2 = (String)obj;
            String str3 = (String)matchingProperties.get(str2);
            String str4 = str2.substring(str.length());
            list.add(new NbtTagValue(str4, str3));
         }

         return (NbtTagValue[])list.toArray(new NbtTagValue[list.size()]);
      }
   }

   private static void checkNull(Object obj, String str) throws NullPointerException {
      if (obj == null) {
         throw new NullPointerException(str);
      }
   }

   private static IBakedModel bakeModel(TextureMap textureMap, ModelBlock modelBlock, boolean flag) {
      ModelRotation x0Y0 = ModelRotation.X0_Y0;
      String str = modelBlock.resolveTextureName("particle");
      TextureAtlasSprite atlasSprite = textureMap.getAtlasSprite(new ResourceLocation(str).toString());
      SimpleBakedModel.Builder builder = new SimpleBakedModel.Builder(modelBlock).setTexture(atlasSprite);

      for (BlockPart blockPart : (Iterable<BlockPart>)(modelBlock.getElements())) {
         for (EnumFacing facing : (Iterable<EnumFacing>)(blockPart.mapFaces.keySet())) {
            BlockPartFace blockPartFace = (BlockPartFace)blockPart.mapFaces.get(facing);
            if (!flag) {
               blockPartFace = new BlockPartFace(blockPartFace.cullFace, -1, blockPartFace.texture, blockPartFace.blockFaceUV);
            }

            String str2 = modelBlock.resolveTextureName(blockPartFace.texture);
            BakedQuad bakedQuad = makeBakedQuad(blockPart, blockPartFace, textureMap.getAtlasSprite(new ResourceLocation(str2).toString()), facing, x0Y0, false);
            if (blockPartFace.cullFace == null) {
               builder.addGeneralQuad(bakedQuad);
            } else {
               builder.addFaceQuad(x0Y0.rotateFace(blockPartFace.cullFace), bakedQuad);
            }
         }
      }

      return builder.makeBakedModel();
   }

   private static BakedQuad makeBakedQuad(BlockPart blockPart, BlockPartFace blockPartFace, TextureAtlasSprite textureAtlasSprite, EnumFacing facing, ModelRotation modelRotation, boolean flag) {
      return new FaceBakery().makeBakedQuad(blockPart.positionFrom, blockPart.positionTo, blockPartFace, textureAtlasSprite, facing, modelRotation, blockPart.partRotation, flag, blockPart.shade);
   }

   public CustomItemProperties(Properties properties, String str) {
      this.name = parseName(str);
      this.basePath = parseBasePath(str);
      this.type = this.parseType(properties.getProperty("type"));
      this.items = this.parseItems(properties.getProperty("items"), properties.getProperty("matchItems"));
      this.mapModels = parseModels(properties, this.basePath);
      this.model = parseModel(properties.getProperty("model"), str, this.basePath, this.type, this.mapModels);
      this.mapTextures = parseTextures(properties, this.basePath);
      boolean flag = this.mapModels == null && this.model == null;
      this.texture = parseTexture(properties.getProperty("texture"), properties.getProperty("tile"), properties.getProperty("source"), str, this.basePath, this.type, this.mapTextures, flag);
      String property = properties.getProperty("damage");
      if (property != null) {
         this.damagePercent = property.contains("%");
         property = property.replace("%", "");
         this.damage = this.parseRangeListInt(property);
         this.damageMask = this.parseInt(properties.getProperty("damageMask"), 0);
      }

      this.stackSize = this.parseRangeListInt(properties.getProperty("stackSize"));
      this.enchantmentIds = this.parseRangeListInt(properties.getProperty("enchantmentIDs"), new ParserEnchantmentId());
      this.enchantmentLevels = this.parseRangeListInt(properties.getProperty("enchantmentLevels"));
      this.nbtTagValues = this.parseNbtTagValues(properties);
      this.hand = this.parseHand(properties.getProperty("hand"));
      this.blend = Blender.parseBlend(properties.getProperty("blend"));
      this.speed = this.parseFloat(properties.getProperty("speed"), 0.0F);
      this.rotation = this.parseFloat(properties.getProperty("rotation"), 0.0F);
      this.layer = this.parseInt(properties.getProperty("layer"), 0);
      this.weight = this.parseInt(properties.getProperty("weight"), 0);
      this.duration = this.parseFloat(properties.getProperty("duration"), 1.0F);
   }

   @Override
   public String toString() {
      return "" + this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
   }

   private static String parseName(String str) {
      String str2 = str;
      int n = str.lastIndexOf(47);
      if (n >= 0) {
         str2 = str.substring(n + 1);
      }

      int n2 = str2.lastIndexOf(46);
      if (n2 >= 0) {
         str2 = str2.substring(0, n2);
      }

      return str2;
   }

   private RangeListInt parseRangeListInt(String str) {
      return this.parseRangeListInt(str, (IParserInt)null);
   }

   public void updateModelsFull() {
      ModelManager modelManager = Config.getModelManager();
      IBakedModel missingModel = modelManager.getMissingModel();
      if (this.model != null) {
         ResourceLocation modelLocation = getModelLocation(this.model);
         ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory");
         this.bakedModelFull = modelManager.getModel(modelResourceLocation);
         if (this.bakedModelFull == missingModel) {
            Config.warn("Custom Items: Model not found " + modelResourceLocation.getResourcePath());
            this.bakedModelFull = null;
         }
      }

      if (this.type == 1 && this.mapModels != null) {
         for (String str : (Iterable<String>)(this.mapModels.keySet())) {
            String str2 = (String)this.mapModels.get(str);
            String str3 = StrUtils.removePrefix(str, "model.");
            if (str3.startsWith("bow") || str3.startsWith("fishing_rod") || str3.startsWith("shield")) {
               ResourceLocation modelLocation2 = getModelLocation(str2);
               ModelResourceLocation modelResourceLocation2 = new ModelResourceLocation(modelLocation2, "inventory");
               IBakedModel model2 = modelManager.getModel(modelResourceLocation2);
               if (model2 == missingModel) {
                  Config.warn("Custom Items: Model not found " + modelResourceLocation2.getResourcePath());
               } else {
                  if (this.mapBakedModelsFull == null) {
                     this.mapBakedModelsFull = new HashMap();
                  }

                  this.mapBakedModelsFull.put(str3, model2);
               }
            }
         }
      }
   }

   private static ResourceLocation getModelLocation(String str) {
      return new ResourceLocation(str);
   }

   public void updateModelTexture(TextureMap textureMap, ItemModelGenerator itemModelGenerator) {
      if (this.texture != null || this.mapTextures != null) {
         String[] modelTextures = this.getModelTextures();
         boolean flag = this.useTint();
         this.bakedModelTexture = makeBakedModel(textureMap, itemModelGenerator, modelTextures, flag);
         if (this.type == 1 && this.mapTextures != null) {
            for (String str : (Iterable<String>)(this.mapTextures.keySet())) {
               String str2 = (String)this.mapTextures.get(str);
               String str3 = StrUtils.removePrefix(str, "texture.");
               if (str3.startsWith("bow") || str3.startsWith("fishing_rod") || str3.startsWith("shield")) {
                  IBakedModel bakedModel = makeBakedModel(textureMap, itemModelGenerator, new String[]{str2}, flag);
                  if (this.mapBakedModelsTexture == null) {
                     this.mapBakedModelsTexture = new HashMap();
                  }

                  this.mapBakedModelsTexture.put(str3, bakedModel);
               }
            }
         }
      }
   }

   public boolean isValid(String str) {
      if (this.name == null || this.name.length() <= 0) {
         Config.warn("No name found: " + str);
         return false;
      } else if (this.basePath == null) {
         Config.warn("No base path found: " + str);
         return false;
      } else if (this.type == 0) {
         Config.warn("No type defined: " + str);
         return false;
      } else {
         if (this.type == 1 || this.type == 3) {
            if (this.items == null) {
               this.items = this.detectItems();
            }

            if (this.items == null) {
               Config.warn("No items defined: " + str);
               return false;
            }
         }

         if (this.texture == null && this.mapTextures == null && this.model == null && this.mapModels == null) {
            Config.warn("No texture or model specified: " + str);
            return false;
         } else if (this.type == 2 && this.enchantmentIds == null) {
            Config.warn("No enchantmentIDs specified: " + str);
            return false;
         } else {
            return true;
         }
      }
   }

   private boolean useTint() {
      return true;
   }

   private RangeListInt parseRangeListInt(String str, IParserInt parserInt) {
      if (str == null) {
         return null;
      } else {
         String[] strings = Config.tokenize(str, " ");
         RangeListInt rangeListInt = new RangeListInt();

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (parserInt != null) {
               int n = parserInt.parse(str2, Integer.MIN_VALUE);
               if (n != Integer.MIN_VALUE) {
                  rangeListInt.addRange(new RangeInt(n, n));
                  continue;
               }
            }

            RangeInt rangeInt = this.parseRangeInt(str2);
            if (rangeInt == null) {
               Config.warn("Invalid range list: " + str);
               return null;
            }

            rangeListInt.addRange(rangeInt);
         }

         return rangeListInt;
      }
   }

   private int[] detectItems() {
      Item byNameOrId = Item.getByNameOrId(this.name);
      if (byNameOrId == null) {
         return null;
      } else {
         int idFromItem = Item.getIdFromItem(byNameOrId);
         return idFromItem <= 0 ? null : new int[]{idFromItem};
      }
   }

   private static String parseTexture(String str, String str2, String str3, String str4, String str5, int n, Map map, boolean flag) {
      if (str == null) {
         str = str2;
      }

      if (str == null) {
         str = str3;
      }

      if (str != null) {
         String str6 = ".png";
         if (str.endsWith(str6)) {
            str = str.substring(0, str.length() - str6.length());
         }

         return fixTextureName(str, str5);
      } else if (n == 3) {
         return null;
      } else {
         if (map != null) {
            String str7 = (String)map.get("texture.bow_standby");
            if (str7 != null) {
               return str7;
            }
         }

         if (!flag) {
            return null;
         } else {
            String str8 = str4;
            int n2 = str4.lastIndexOf(47);
            if (n2 >= 0) {
               str8 = str4.substring(n2 + 1);
            }

            int n3 = str8.lastIndexOf(46);
            if (n3 >= 0) {
               str8 = str8.substring(0, n3);
            }

            return fixTextureName(str8, str5);
         }
      }
   }

   private static String fixTextureName(String str, String str2) {
      str = TextureUtils.fixResourcePath(str, str2);
      if (!str.startsWith(str2) && !str.startsWith("textures/") && !str.startsWith("mcpatcher/")) {
         str = str2 + "/" + str;
      }

      if (str.endsWith(".png")) {
         str = str.substring(0, str.length() - 4);
      }

      if (str.startsWith("/")) {
         str = str.substring(1);
      }

      return str;
   }

   public float getTextureHeight(TextureManager textureManager) {
      if (this.textureHeight <= 0) {
         if (this.textureLocation != null) {
            int glTextureId = textureManager.getTexture(this.textureLocation).getGlTextureId();
            int boundTexture = GlStateManager.getBoundTexture();
            GlStateManager.bindTexture(glTextureId);
            this.textureHeight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            GlStateManager.bindTexture(boundTexture);
         }

         if (this.textureHeight <= 0) {
            this.textureHeight = 16;
         }
      }

      return (float)this.textureHeight;
   }

   private static ModelBlock makeModelBlock(String[] strings) {
      StringBuffer sb = new StringBuffer();
      sb.append("{\"parent\": \"builtin/generated\",\"textures\": {");

      for (int i = 0; i < strings.length; i++) {
         String str = strings[i];
         if (i > 0) {
            sb.append(", ");
         }

         sb.append("\"layer" + i + "\": \"" + str + "\"");
      }

      sb.append("}}");
      return ModelBlock.deserialize(sb.toString());
   }

   public void updateIcons(TextureMap textureMap) {
      if (this.texture != null) {
         this.textureLocation = this.getTextureLocation(this.texture);
         if (this.type == 1) {
            this.sprite = textureMap.registerSprite(this.getSpriteLocation(this.textureLocation));
         }
      }

      if (this.mapTextures != null) {
         this.mapTextureLocations = new HashMap();
         this.mapSprites = new HashMap();

         for (String str : (Iterable<String>)(this.mapTextures.keySet())) {
            ResourceLocation textureLocation2 = this.getTextureLocation((String)this.mapTextures.get(str));
            this.mapTextureLocations.put(str, textureLocation2);
            if (this.type == 1) {
               TextureAtlasSprite textureAtlasSprite = textureMap.registerSprite(this.getSpriteLocation(textureLocation2));
               this.mapSprites.put(str, textureAtlasSprite);
            }
         }
      }
   }

   private ResourceLocation getSpriteLocation(ResourceLocation location) {
      String resourcePath = location.getResourcePath();
      resourcePath = StrUtils.removePrefix(resourcePath, "textures/");
      resourcePath = StrUtils.removeSuffix2(resourcePath, ".png");
      return new ResourceLocation(location.getResourceDomain(), resourcePath);
   }

   private float parseFloat(String str, float f) {
      if (str == null) {
         return f;
      } else {
         str = str.trim();
         float f2 = Config.parseFloat(str, Float.MIN_VALUE);
         if (f2 == Float.MIN_VALUE) {
            Config.warn("Invalid float: " + str);
            return f;
         } else {
            return f2;
         }
      }
   }

   private static Map parseModels(Properties properties, String str) {
      Map matchingProperties = getMatchingProperties(properties, "model.");
      if (matchingProperties.size() <= 0) {
         return null;
      } else {
         Set keys = matchingProperties.keySet();
         LinkedHashMap map = new LinkedHashMap();

         for (Object obj : keys) {
            String str3 = (String)obj;
            String str4 = (String)matchingProperties.get(str3);
            str4 = fixModelName(str4, str);
            map.put(str3, str4);
         }

         return map;
      }
   }

   private static String fixModelName(String str, String str2) {
      str = TextureUtils.fixResourcePath(str, str2);
      boolean flag = str.startsWith("block/") || str.startsWith("item/");
      if (!str.startsWith(str2) && !flag && !str.startsWith("mcpatcher/")) {
         str = str2 + "/" + str;
      }

      String str3 = ".json";
      if (str.endsWith(str3)) {
         str = str.substring(0, str.length() - str3.length());
      }

      if (str.startsWith("/")) {
         str = str.substring(1);
      }

      return str;
   }

   private int parseInt(String str, int n) {
      if (str == null) {
         return n;
      } else {
         str = str.trim();
         int n2 = Config.parseInt(str, Integer.MIN_VALUE);
         if (n2 == Integer.MIN_VALUE) {
            Config.warn("Invalid integer: " + str);
            return n;
         } else {
            return n2;
         }
      }
   }

   public float getTextureWidth(TextureManager textureManager) {
      if (this.textureWidth <= 0) {
         if (this.textureLocation != null) {
            int glTextureId = textureManager.getTexture(this.textureLocation).getGlTextureId();
            int boundTexture = GlStateManager.getBoundTexture();
            GlStateManager.bindTexture(glTextureId);
            this.textureWidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            GlStateManager.bindTexture(boundTexture);
         }

         if (this.textureWidth <= 0) {
            this.textureWidth = 16;
         }
      }

      return (float)this.textureWidth;
   }

   private RangeInt parseRangeInt(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         if ((str.length() - str.replace("-", "").length()) > 1) {
            Config.warn("Invalid range: " + str);
            return null;
         } else {
            String[] strings = Config.tokenize(str, "- ");
            int[] ints = new int[strings.length];

            for (int i = 0; i < strings.length; i++) {
               int n2 = Config.parseInt(strings[i], -1);
               if (n2 < 0) {
                  Config.warn("Invalid range: " + str);
                  return null;
               }

               ints[i] = n2;
            }

            if (ints.length == 1) {
               int n3 = ints[0];
               if (str.startsWith("-")) {
                  return new RangeInt(0, n3);
               } else {
                  return str.endsWith("-") ? new RangeInt(n3, 65535) : new RangeInt(n3, n3);
               }
            } else if (ints.length == 2) {
               int n4 = Math.min(ints[0], ints[1]);
               int n5 = Math.max(ints[0], ints[1]);
               return new RangeInt(n4, n5);
            } else {
               Config.warn("Invalid range: " + str);
               return null;
            }
         }
      }
   }

   private String[] getModelTextures() {
      if (this.type == 1 && this.items.length == 1) {
         Item itemById = Item.getItemById(this.items[0]);
         if (itemById == Items.potionitem && this.damage != null && this.damage.getRanges() > 0) {
            boolean flag = (this.damage.getRange(0).getMin() & 16384) != 0;
            String texture2 = this.getTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
            Object obj = null;
            if (flag) {
               obj = this.getTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash");
            } else {
               obj = this.getTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
            }

            return new String[]{texture2, (String)obj};
         }

         if (itemById instanceof ItemArmor) {
            ItemArmor itemArmor = (ItemArmor)itemById;
            if (itemArmor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER) {
               String str = "leather";
               String str2 = "helmet";
               if (itemArmor.armorType == 0) {
                  str2 = "helmet";
               }

               if (itemArmor.armorType == 1) {
                  str2 = "chestplate";
               }

               if (itemArmor.armorType == 2) {
                  str2 = "leggings";
               }

               if (itemArmor.armorType == 3) {
                  str2 = "boots";
               }

               String str3 = str + "_" + str2;
               String texture3 = this.getTexture(this.mapTextures, "texture." + str3, "items/" + str3);
               String texture4 = this.getTexture(this.mapTextures, "texture." + str3 + "_overlay", "items/" + str3 + "_overlay");
               return new String[]{texture3, texture4};
            }
         }
      }

      return new String[]{this.texture};
   }

   private String getTexture(Map map, String str, String str2) {
      if (map == null) {
         return str2;
      } else {
         String str3 = (String)map.get(str);
         return str3 == null ? str2 : str3;
      }
   }

   private int parseHand(String str) {
      if (str == null) {
         return 0;
      } else {
         str = str.toLowerCase();
         if (str.equals("any")) {
            return 0;
         } else if (str.equals("main")) {
            return 1;
         } else if (str.equals("off")) {
            return 2;
         } else {
            Config.warn("Invalid hand: " + str);
            return 0;
         }
      }
   }

   public IBakedModel getBakedModel(ResourceLocation location, boolean flag) {
      IBakedModel bakedModel;
      Map map;
      if (flag) {
         bakedModel = this.bakedModelFull;
         map = this.mapBakedModelsFull;
      } else {
         bakedModel = this.bakedModelTexture;
         map = this.mapBakedModelsTexture;
      }

      if (location != null && map != null) {
         IBakedModel bakedModel2 = (IBakedModel)map.get(location.getResourcePath());
         if (bakedModel2 != null) {
            return bakedModel2;
         }
      }

      return bakedModel;
   }

   private int parseType(String str) {
      if (str == null) {
         return 1;
      } else if (str.equals("item")) {
         return 1;
      } else if (str.equals("enchantment")) {
         return 2;
      } else if (str.equals("armor")) {
         return 3;
      } else {
         Config.warn("Unknown method: " + str);
         return 0;
      }
   }

   private static Map parseTextures(Properties properties, String str) {
      Map matchingProperties = getMatchingProperties(properties, "texture.");
      if (matchingProperties.size() <= 0) {
         return null;
      } else {
         Set keys = matchingProperties.keySet();
         LinkedHashMap map = new LinkedHashMap();

         for (Object obj : keys) {
            String str3 = (String)obj;
            String str4 = (String)matchingProperties.get(str3);
            str4 = fixTextureName(str4, str);
            map.put(str3, str4);
         }

         return map;
      }
   }

   private static Map getMatchingProperties(Properties properties, String str) {
      LinkedHashMap map = new LinkedHashMap();

      for (Object obj : properties.keySet()) {
         String str2 = (String)obj;
         String property = properties.getProperty(str2);
         if (str2.startsWith(str)) {
            map.put(str2, property);
         }
      }

      return map;
   }

   private static String parseBasePath(String str) {
      int n = str.lastIndexOf(47);
      return n < 0 ? "" : str.substring(0, n);
   }

   private int[] parseItems(String str, String str2) {
      if (str == null) {
         str = str2;
      }

      if (str == null) {
         return null;
      } else {
         str = str.trim();
         TreeSet treeSet = new TreeSet();
         String[] strings = Config.tokenize(str, " ");

         for (int i = 0; i < strings.length; i++) {
            String str3 = strings[i];
            int n = Config.parseInt(str3, -1);
            if (n >= 0) {
               treeSet.add(new Integer(n));
            } else {
               if (str3.contains("-")) {
                  String[] strings2 = Config.tokenize(str3, "-");
                  if (strings2.length == 2) {
                     int n2 = Config.parseInt(strings2[0], -1);
                     int n3 = Config.parseInt(strings2[1], -1);
                     if (n2 >= 0 && n3 >= 0) {
                        int n4 = Math.min(n2, n3);
                        int n5 = Math.max(n2, n3);

                        for (int j = n4; j <= n5; j++) {
                           treeSet.add(new Integer(j));
                        }
                        continue;
                     }
                  }
               }

               Item byNameOrId = Item.getByNameOrId(str3);
               if (byNameOrId == null) {
                  Config.warn("Item not found: " + str3);
               } else {
                  int idFromItem = Item.getIdFromItem(byNameOrId);
                  if (idFromItem <= 0) {
                     Config.warn("Item not found: " + str3);
                  } else {
                     treeSet.add(new Integer(idFromItem));
                  }
               }
            }
         }

         Integer[] nums = (Integer[])treeSet.toArray(new Integer[treeSet.size()]);
         int[] ints = new int[nums.length];

         for (int k = 0; k < ints.length; k++) {
            ints[k] = nums[k];
         }

         return ints;
      }
   }

   public void loadModels(ModelBakery modelBakery) {
      if (this.model != null) {
         loadItemModel(modelBakery, this.model);
      }

      if (this.type == 1 && this.mapModels != null) {
         for (String str : (Iterable<String>)(this.mapModels.keySet())) {
            String str2 = (String)this.mapModels.get(str);
            String str3 = StrUtils.removePrefix(str, "model.");
            if (str3.startsWith("bow") || str3.startsWith("fishing_rod") || str3.startsWith("shield")) {
               loadItemModel(modelBakery, str2);
            }
         }
      }
   }

   private ResourceLocation getTextureLocation(String str) {
      if (str == null) {
         return null;
      } else {
         ResourceLocation location = new ResourceLocation(str);
         String resourceDomain = location.getResourceDomain();
         String resourcePath = location.getResourcePath();
         if (!resourcePath.contains("/")) {
            resourcePath = "textures/items/" + resourcePath;
         }

         String str2 = resourcePath + ".png";
         ResourceLocation location2 = new ResourceLocation(resourceDomain, str2);
         if (!Config.hasResource(location2)) {
            Config.warn("File not found: " + str2);
         }

         return location2;
      }
   }

   private static IBakedModel makeBakedModel(TextureMap textureMap, ItemModelGenerator itemModelGenerator, String[] strings, boolean flag) {
      String[] strings2 = new String[strings.length];

      for (int i = 0; i < strings2.length; i++) {
         strings2[i] = StrUtils.removePrefix(strings[i], "textures/");
      }

      return bakeModel(textureMap, itemModelGenerator.makeItemModel(textureMap, makeModelBlock(strings2)), flag);
   }

   private static String parseModel(String str, String str2, String str3, int n, Map map) {
      if (str != null) {
         String str4 = ".json";
         if (str.endsWith(str4)) {
            str = str.substring(0, str.length() - str4.length());
         }

         return fixModelName(str, str3);
      } else if (n == 3) {
         return null;
      } else {
         if (map != null) {
            String str5 = (String)map.get("model.bow_standby");
            if (str5 != null) {
               return str5;
            }
         }

         return str;
      }
   }

   private static void loadItemModel(ModelBakery modelBakery, String str) {
      ResourceLocation modelLocation = getModelLocation(str);
      ModelResourceLocation modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory");
      modelBakery.loadItemModel(modelLocation.toString(), modelResourceLocation, modelLocation);
   }
}
