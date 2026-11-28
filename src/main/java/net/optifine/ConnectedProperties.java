package net.optifine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import net.optifine.config.Matches;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.util.MathUtils;
import net.optifine.util.TextureUtils;

public class ConnectedProperties {
   public String name = null;
   public String basePath = null;
   public MatchBlock[] matchBlocks = null;
   public int[] metadatas = null;
   public String[] matchTiles = null;
   public int method = 0;
   public String[] tiles = null;
   public int connect = 0;
   public int faces = 63;
   public BiomeGenBase[] biomes = null;
   public RangeListInt heights = null;
   public int renderPass = 0;
   public boolean innerSeams = false;
   public int[] ctmTileIndexes = null;
   public int width = 0;
   public int height = 0;
   public int[] weights = null;
   public int randomLoops = 0;
   public int symmetry = 1;
   public boolean linked = false;
   public NbtTagValue nbtName = null;
   public int[] sumWeights = null;
   public int sumAllWeights = 1;
   public TextureAtlasSprite[] matchTileIcons = null;
   public TextureAtlasSprite[] tileIcons = null;
   public MatchBlock[] connectBlocks = null;
   public String[] connectTiles = null;
   public TextureAtlasSprite[] connectTileIcons = null;
   public int tintIndex = -1;
   public IBlockState tintBlockState = Blocks.air.getDefaultState();
   public EnumWorldBlockLayer layer = null;
   public static final int METHOD_NONE = 0;
   public static final int METHOD_CTM = 1;
   public static final int METHOD_HORIZONTAL = 2;
   public static final int METHOD_TOP = 3;
   public static final int METHOD_RANDOM = 4;
   public static final int METHOD_REPEAT = 5;
   public static final int METHOD_VERTICAL = 6;
   public static final int METHOD_FIXED = 7;
   public static final int METHOD_HORIZONTAL_VERTICAL = 8;
   public static final int METHOD_VERTICAL_HORIZONTAL = 9;
   public static final int METHOD_CTM_COMPACT = 10;
   public static final int METHOD_OVERLAY = 11;
   public static final int METHOD_OVERLAY_FIXED = 12;
   public static final int METHOD_OVERLAY_RANDOM = 13;
   public static final int METHOD_OVERLAY_REPEAT = 14;
   public static final int METHOD_OVERLAY_CTM = 15;
   public static final int CONNECT_NONE = 0;
   public static final int CONNECT_BLOCK = 1;
   public static final int CONNECT_TILE = 2;
   public static final int CONNECT_MATERIAL = 3;
   public static final int CONNECT_UNKNOWN = 128;
   public static final int FACE_BOTTOM = 1;
   public static final int FACE_TOP = 2;
   public static final int FACE_NORTH = 4;
   public static final int FACE_SOUTH = 8;
   public static final int FACE_WEST = 16;
   public static final int FACE_EAST = 32;
   public static final int FACE_SIDES = 60;
   public static final int FACE_ALL = 63;
   public static final int FACE_UNKNOWN = 128;
   public static final int SYMMETRY_NONE = 1;
   public static final int SYMMETRY_OPPOSITE = 2;
   public static final int SYMMETRY_ALL = 6;
   public static final int SYMMETRY_UNKNOWN = 128;
   public static final String TILE_SKIP_PNG = null;
   public static final String TILE_DEFAULT_PNG = null;

   private boolean isValidVerticalHorizontal(String str) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for vertical+horizontal: " + str);
         return false;
      } else if (this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + str);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidOverlay(String str) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-16");
      }

      if (this.tiles.length < 17) {
         Config.warn("Invalid tiles, must be at least 17: " + str);
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   private String[] parseTileNames(String str) {
      if (str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] strings = Config.tokenize(str, " ,");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (str2.contains("-")) {
               String[] strings2 = Config.tokenize(str2, "-");
               if (strings2.length == 2) {
                  int n = Config.parseInt(strings2[0], -1);
                  int n2 = Config.parseInt(strings2[1], -1);
                  if (n >= 0 && n2 >= 0) {
                     if (n > n2) {
                        Config.warn("Invalid interval: " + str2 + ", when parsing: " + str);
                        continue;
                     }

                     for (int j = n; j <= n2; j++) {
                        list.add(String.valueOf(j));
                     }
                     continue;
                  }
               }
            }

            list.add(str2);
         }

         String[] strings3 = (String[])list.toArray(new String[list.size()]);

         for (int k = 0; k < strings3.length; k++) {
            String str3 = strings3[k];
            str3 = TextureUtils.fixResourcePath(str3, this.basePath);
            if (!str3.startsWith(this.basePath) && !str3.startsWith("textures/") && !str3.startsWith("mcpatcher/")) {
               str3 = this.basePath + "/" + str3;
            }

            if (str3.endsWith(".png")) {
               str3 = str3.substring(0, str3.length() - 4);
            }

            if (str3.startsWith("/")) {
               str3 = str3.substring(1);
            }

            strings3[k] = str3;
         }

         return strings3;
      }
   }

   private boolean isValidCtm(String str) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-11 16-27 32-43 48-58");
      }

      if (this.tiles.length < 47) {
         Config.warn("Invalid tiles, must be at least 47: " + str);
         return false;
      } else {
         return true;
      }
   }

   public boolean isValid(String str) {
      if (this.name == null || this.name.length() <= 0) {
         Config.warn("No name found: " + str);
         return false;
      } else if (this.basePath == null) {
         Config.warn("No base path found: " + str);
         return false;
      } else {
         if (this.matchBlocks == null) {
            this.matchBlocks = this.detectMatchBlocks();
         }

         if (this.matchTiles == null && this.matchBlocks == null) {
            this.matchTiles = this.detectMatchTiles();
         }

         if (this.matchBlocks == null && this.matchTiles == null) {
            Config.warn("No matchBlocks or matchTiles specified: " + str);
            return false;
         } else if (this.method == 0) {
            Config.warn("No method: " + str);
            return false;
         } else if (this.tiles != null && this.tiles.length > 0) {
            if (this.connect == 0) {
               this.connect = this.detectConnect();
            }

            if (this.connect == 128) {
               Config.warn("Invalid connect in: " + str);
               return false;
            } else if (this.renderPass > 0) {
               Config.warn("Render pass not supported: " + this.renderPass);
               return false;
            } else if ((this.faces & 128) != 0) {
               Config.warn("Invalid faces in: " + str);
               return false;
            } else if ((this.symmetry & 128) != 0) {
               Config.warn("Invalid symmetry in: " + str);
               return false;
            } else {
               switch (this.method) {
                  case 1:
                     return this.isValidCtm(str);
                  case 2:
                     return this.isValidHorizontal(str);
                  case 3:
                     return this.isValidTop(str);
                  case 4:
                     return this.isValidRandom(str);
                  case 5:
                     return this.isValidRepeat(str);
                  case 6:
                     return this.isValidVertical(str);
                  case 7:
                     return this.isValidFixed(str);
                  case 8:
                     return this.isValidHorizontalVertical(str);
                  case 9:
                     return this.isValidVerticalHorizontal(str);
                  case 10:
                     return this.isValidCtmCompact(str);
                  case 11:
                     return this.isValidOverlay(str);
                  case 12:
                     return this.isValidOverlayFixed(str);
                  case 13:
                     return this.isValidOverlayRandom(str);
                  case 14:
                     return this.isValidOverlayRepeat(str);
                  case 15:
                     return this.isValidOverlayCtm(str);
                  default:
                     Config.warn("Unknown method: " + str);
                     return false;
               }
            }
         } else {
            Config.warn("No tiles specified: " + str);
            return false;
         }
      }
   }

   private boolean isValidOverlayRandom(String str) {
      if (!this.isValidRandom(str)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public boolean matchesBlock(int n, int n2) {
      return !Matches.block(n, n2, this.matchBlocks) ? false : Matches.metadata(n2, this.metadatas);
   }

   public static IProperty getProperty(String str, Collection items) {
      for (Object obj : items) {
         IProperty property = (IProperty)obj;
         if (str.equals(property.getName())) {
            return property;
         }
      }

      return null;
   }

   private static int parseSymmetry(String str) {
      if (str == null) {
         return 1;
      } else {
         str = str.trim();
         if (str.equals("opposite")) {
            return 2;
         } else if (str.equals("all")) {
            return 6;
         } else {
            Config.warn("Unknown symmetry: " + str);
            return 1;
         }
      }
   }

   private int[] detectMatchBlockIds() {
      if (!this.name.startsWith("block")) {
         return null;
      } else {
         int length = "block".length();

         int n;
         for (n = length; n < this.name.length(); n++) {
            char ch = this.name.charAt(n);
            if (ch < '0' || ch > '9') {
               break;
            }
         }

         if (n == length) {
            return null;
         } else {
            int n2 = Config.parseInt(this.name.substring(length, n), -1);
            return n2 < 0 ? null : new int[]{n2};
         }
      }
   }

   private static int parseFace(String str) {
      str = str.toLowerCase();
      if (str.equals("bottom") || str.equals("down")) {
         return 1;
      } else if (str.equals("top") || str.equals("up")) {
         return 2;
      } else if (str.equals("north")) {
         return 4;
      } else if (str.equals("south")) {
         return 8;
      } else if (str.equals("east")) {
         return 32;
      } else if (str.equals("west")) {
         return 16;
      } else if (str.equals("sides")) {
         return 60;
      } else if (str.equals("all")) {
         return 63;
      } else {
         Config.warn("Unknown face: " + str);
         return 128;
      }
   }

   private boolean isValidFixed(String str) {
      if (this.tiles == null) {
         Config.warn("Tiles not defined: " + str);
         return false;
      } else if (this.tiles.length != 1) {
         Config.warn("Number of tiles should be 1 for method: fixed.");
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidOverlayCtm(String str) {
      if (!this.isValidCtm(str)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   public boolean matchesBlockId(int n) {
      return Matches.blockId(n, this.matchBlocks);
   }

   private static boolean isMethodOverlay(int n) {
      switch (n) {
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
            return true;
         default:
            return false;
      }
   }

   private static TextureAtlasSprite[] registerIcons(String[] strings, TextureMap textureMap, boolean flag, boolean flag2) {
      if (strings == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            ResourceLocation location = new ResourceLocation(str);
            String resourceDomain = location.getResourceDomain();
            String resourcePath = location.getResourcePath();
            if (!resourcePath.contains("/")) {
               resourcePath = "textures/blocks/" + resourcePath;
            }

            String str2 = resourcePath + ".png";
            if (flag && str2.endsWith("<skip>.png")) {
               list.add(null);
            } else if (flag2 && str2.endsWith("<default>.png")) {
               list.add(ConnectedTextures.SPRITE_DEFAULT);
            } else {
               if (!Config.hasResource(new ResourceLocation(resourceDomain, str2))) {
                  Config.warn("File not found: " + str2);
               }

               String str3 = "textures/";
               String str4 = resourcePath;
               if (resourcePath.startsWith(str3)) {
                  str4 = resourcePath.substring(str3.length());
               }

               list.add(textureMap.registerSprite(new ResourceLocation(resourceDomain, str4)));
            }
         }

         return (TextureAtlasSprite[])list.toArray(new TextureAtlasSprite[list.size()]);
      }
   }

   private boolean isValidCtmCompact(String str) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("0-4");
      }

      if (this.tiles.length < 5) {
         Config.warn("Invalid tiles, must be at least 5: " + str);
         return false;
      } else {
         return true;
      }
   }

   private static int parseMethod(String str) {
      if (str == null) {
         return 1;
      } else {
         str = str.trim();
         if (str.equals("ctm") || str.equals("glass")) {
            return 1;
         } else if (str.equals("ctm_compact")) {
            return 10;
         } else if (str.equals("horizontal") || str.equals("bookshelf")) {
            return 2;
         } else if (str.equals("vertical")) {
            return 6;
         } else if (str.equals("top")) {
            return 3;
         } else if (str.equals("random")) {
            return 4;
         } else if (str.equals("repeat")) {
            return 5;
         } else if (str.equals("fixed")) {
            return 7;
         } else if (str.equals("horizontal+vertical") || str.equals("h+v")) {
            return 8;
         } else if (str.equals("vertical+horizontal") || str.equals("v+h")) {
            return 9;
         } else if (str.equals("overlay")) {
            return 11;
         } else if (str.equals("overlay_fixed")) {
            return 12;
         } else if (str.equals("overlay_random")) {
            return 13;
         } else if (str.equals("overlay_repeat")) {
            return 14;
         } else if (str.equals("overlay_ctm")) {
            return 15;
         } else {
            Config.warn("Unknown method: " + str);
            return 0;
         }
      }
   }

   private static TextureAtlasSprite getIcon(String str) {
      TextureMap textureMapBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
      TextureAtlasSprite spriteSafe = textureMapBlocks.getSpriteSafe(str);
      return spriteSafe != null ? spriteSafe : textureMapBlocks.getSpriteSafe("blocks/" + str);
   }

   private boolean isValidHorizontal(String str) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("12-15");
      }

      if (this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + str);
         return false;
      } else {
         return true;
      }
   }

   public boolean matchesIcon(TextureAtlasSprite textureAtlasSprite) {
      return Matches.sprite(textureAtlasSprite, this.matchTileIcons);
   }

   public void updateIcons(TextureMap textureMap) {
      if (this.matchTiles != null) {
         this.matchTileIcons = registerIcons(this.matchTiles, textureMap, false, false);
      }

      if (this.connectTiles != null) {
         this.connectTileIcons = registerIcons(this.connectTiles, textureMap, false, false);
      }

      if (this.tiles != null) {
         this.tileIcons = registerIcons(this.tiles, textureMap, true, !isMethodOverlay(this.method));
      }
   }

   private boolean isValidRandom(String str) {
      if (this.tiles != null && this.tiles.length > 0) {
         if (this.weights != null) {
            if (this.weights.length > this.tiles.length) {
               Config.warn("More weights defined than tiles, trimming weights: " + str);
               int[] ints = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, ints, 0, ints.length);
               this.weights = ints;
            }

            if (this.weights.length < this.tiles.length) {
               Config.warn("Less weights defined than tiles, expanding weights: " + str);
               int[] ints2 = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, ints2, 0, this.weights.length);
               int average = MathUtils.getAverage(this.weights);

               for (int i = this.weights.length; i < ints2.length; i++) {
                  ints2[i] = average;
               }

               this.weights = ints2;
            }

            this.sumWeights = new int[this.weights.length];
            int n = 0;

            for (int j = 0; j < this.weights.length; j++) {
               n += this.weights[j];
               this.sumWeights[j] = n;
            }

            this.sumAllWeights = n;
            if (this.sumAllWeights <= 0) {
               Config.warn("Invalid sum of all weights: " + n);
               this.sumAllWeights = 1;
            }
         }

         if (this.randomLoops >= 0 && this.randomLoops <= 9) {
            return true;
         } else {
            Config.warn("Invalid randomLoops: " + this.randomLoops);
            return false;
         }
      } else {
         Config.warn("Tiles not defined: " + str);
         return false;
      }
   }

   private static int parseConnect(String str) {
      if (str == null) {
         return 0;
      } else {
         str = str.trim();
         if (str.equals("block")) {
            return 1;
         } else if (str.equals("tile")) {
            return 2;
         } else if (str.equals("material")) {
            return 3;
         } else {
            Config.warn("Unknown connect: " + str);
            return 128;
         }
      }
   }

   private MatchBlock[] detectMatchBlocks() {
      int[] ints = this.detectMatchBlockIds();
      if (ints == null) {
         return null;
      } else {
         MatchBlock[] matchBlocks2 = new MatchBlock[ints.length];

         for (int i = 0; i < matchBlocks2.length; i++) {
            matchBlocks2[i] = new MatchBlock(ints[i]);
         }

         return matchBlocks2;
      }
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

   private String[] detectMatchTiles() {
      return getIcon(this.name) == null ? null : new String[]{this.name};
   }

   private int[] parseCtmTileIndexes(Properties properties) {
      if (this.tiles == null) {
         return null;
      } else {
         HashMap map = new HashMap();

         for (Object obj : properties.keySet()) {
            if (obj instanceof String) {
               String str = (String)obj;
               String str2 = "ctm.";
               if (str.startsWith(str2)) {
                  String str3 = str.substring(str2.length());
                  String property = properties.getProperty(str);
                  if (property != null) {
                     property = property.trim();
                     int n = Config.parseInt(str3, -1);
                     if (n >= 0 && n <= 46) {
                        int n2 = Config.parseInt(property, -1);
                        if (n2 >= 0 && n2 < this.tiles.length) {
                           map.put(n, n2);
                        } else {
                           Config.warn("Invalid CTM tile index: " + property);
                        }
                     } else {
                        Config.warn("Invalid CTM index: " + str3);
                     }
                  }
               }
            }
         }

         if (map.isEmpty()) {
            return null;
         } else {
            int[] ints = new int[47];

            for (int i = 0; i < ints.length; i++) {
               ints[i] = -1;
               if (map.containsKey(i)) {
                  ints[i] = (Integer)map.get(i);
               }
            }

            return ints;
         }
      }
   }

   private boolean isValidVertical(String str) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for vertical: " + str);
         return false;
      } else if (this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + str);
         return false;
      } else {
         return true;
      }
   }

   private static String parseBasePath(String str) {
      int n = str.lastIndexOf(47);
      return n < 0 ? "" : str.substring(0, n);
   }

   private String[] parseMatchTiles(String str) {
      if (str == null) {
         return null;
      } else {
         String[] strings = Config.tokenize(str, " ");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (str2.endsWith(".png")) {
               str2 = str2.substring(0, str2.length() - 4);
            }

            str2 = TextureUtils.fixResourcePath(str2, this.basePath);
            strings[i] = str2;
         }

         return strings;
      }
   }

   public int getMetadataMax() {
      int n = -1;
      n = this.getMax(this.metadatas, n);
      if (this.matchBlocks != null) {
         for (int i = 0; i < this.matchBlocks.length; i++) {
            n = this.getMax(this.matchBlocks[i].getMetadatas(), n);
         }
      }

      return n;
   }

   private boolean isValidOverlayFixed(String str) {
      if (!this.isValidFixed(str)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   private boolean isValidRepeat(String str) {
      if (this.tiles == null) {
         Config.warn("Tiles not defined: " + str);
         return false;
      } else if (this.width <= 0) {
         Config.warn("Invalid width: " + str);
         return false;
      } else if (this.height <= 0) {
         Config.warn("Invalid height: " + str);
         return false;
      } else if (this.tiles.length != this.width * this.height) {
         Config.warn("Number of tiles does not equal width x height: " + str);
         return false;
      } else {
         return true;
      }
   }

   private static int parseFaces(String str) {
      if (str == null) {
         return 63;
      } else {
         String[] strings = Config.tokenize(str, " ,");
         int n = 0;

         for (int i = 0; i < strings.length; i++) {
            int n2 = parseFace(strings[i]);
            n |= n2;
         }

         return n;
      }
   }

   private int detectConnect() {
      return this.matchBlocks != null ? 1 : (this.matchTiles != null ? 2 : 128);
   }

   public ConnectedProperties(Properties properties, String str) {
      ConnectedParser connectedParser = new ConnectedParser("ConnectedTextures");
      this.name = connectedParser.parseName(str);
      this.basePath = connectedParser.parseBasePath(str);
      this.matchBlocks = connectedParser.parseMatchBlocks(properties.getProperty("matchBlocks"));
      this.metadatas = connectedParser.parseIntList(properties.getProperty("metadata"));
      this.matchTiles = this.parseMatchTiles(properties.getProperty("matchTiles"));
      this.method = parseMethod(properties.getProperty("method"));
      this.tiles = this.parseTileNames(properties.getProperty("tiles"));
      this.connect = parseConnect(properties.getProperty("connect"));
      this.faces = parseFaces(properties.getProperty("faces"));
      this.biomes = connectedParser.parseBiomes(properties.getProperty("biomes"));
      this.heights = connectedParser.parseRangeListInt(properties.getProperty("heights"));
      if (this.heights == null) {
         int n = connectedParser.parseInt(properties.getProperty("minHeight"), -1);
         int n2 = connectedParser.parseInt(properties.getProperty("maxHeight"), 1024);
         if (n != -1 || n2 != 1024) {
            this.heights = new RangeListInt(new RangeInt(n, n2));
         }
      }

      this.renderPass = connectedParser.parseInt(properties.getProperty("renderPass"), -1);
      this.innerSeams = connectedParser.parseBoolean(properties.getProperty("innerSeams"), false);
      this.ctmTileIndexes = this.parseCtmTileIndexes(properties);
      this.width = connectedParser.parseInt(properties.getProperty("width"), -1);
      this.height = connectedParser.parseInt(properties.getProperty("height"), -1);
      this.weights = connectedParser.parseIntList(properties.getProperty("weights"));
      this.randomLoops = connectedParser.parseInt(properties.getProperty("randomLoops"), 0);
      this.symmetry = parseSymmetry(properties.getProperty("symmetry"));
      this.linked = connectedParser.parseBoolean(properties.getProperty("linked"), false);
      this.nbtName = connectedParser.parseNbtTagValue("name", properties.getProperty("name"));
      this.connectBlocks = connectedParser.parseMatchBlocks(properties.getProperty("connectBlocks"));
      this.connectTiles = this.parseMatchTiles(properties.getProperty("connectTiles"));
      this.tintIndex = connectedParser.parseInt(properties.getProperty("tintIndex"), -1);
      this.tintBlockState = connectedParser.parseBlockState(properties.getProperty("tintBlock"), Blocks.air.getDefaultState());
      this.layer = connectedParser.parseBlockRenderLayer(properties.getProperty("layer"), EnumWorldBlockLayer.CUTOUT_MIPPED);
   }

   @Override
   public String toString() {
      return "CTM name: " + this.name + ", basePath: " + this.basePath + ", matchBlocks: " + Config.arrayToString3(this.matchBlocks) + ", matchTiles: " + Config.arrayToString3(this.matchTiles);
   }

   public boolean matchesBiome(BiomeGenBase biomeGenBase) {
      return Matches.biome(biomeGenBase, this.biomes);
   }

   private boolean isValidHorizontalVertical(String str) {
      if (this.tiles == null) {
         Config.warn("No tiles defined for horizontal+vertical: " + str);
         return false;
      } else if (this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + str);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidOverlayRepeat(String str) {
      if (!this.isValidRepeat(str)) {
         return false;
      } else if (this.layer != null && this.layer != EnumWorldBlockLayer.SOLID) {
         return true;
      } else {
         Config.warn("Invalid overlay layer: " + this.layer);
         return false;
      }
   }

   private boolean isValidTop(String str) {
      if (this.tiles == null) {
         this.tiles = this.parseTileNames("66");
      }

      if (this.tiles.length != 1) {
         Config.warn("Invalid tiles, must be exactly 1: " + str);
         return false;
      } else {
         return true;
      }
   }

   private int getMax(int[] ints, int n) {
      if (ints == null) {
         return n;
      } else {
         for (int i = 0; i < ints.length; i++) {
            int n2 = ints[i];
            if (n2 > n) {
               n = n2;
            }
         }

         return n;
      }
   }
}
