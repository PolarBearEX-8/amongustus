package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.util.TextureUtils;

public class NaturalTextures {
   private static NaturalProperties[] propertiesByIndex = new NaturalProperties[0];

   public static BakedQuad getNaturalTexture(BlockPos pos, BakedQuad bakedQuad) {
      TextureAtlasSprite sprite = bakedQuad.getSprite();
      if (sprite == null) {
         return bakedQuad;
      } else {
         NaturalProperties naturalProperties = getNaturalProperties(sprite);
         if (naturalProperties == null) {
            return bakedQuad;
         } else {
            int random = Config.getRandom(pos, ConnectedTextures.getSide(bakedQuad.getFace()));
            int n = 0;
            boolean flag = false;
            if (naturalProperties.rotation > 1) {
               n = random & 3;
            }

            if (naturalProperties.rotation == 2) {
               n = n / 2 * 2;
            }

            if (naturalProperties.flip) {
               flag = (random & 4) != 0;
            }

            return naturalProperties.getQuad(bakedQuad, n, flag);
         }
      }
   }

   public static void update() {
      propertiesByIndex = new NaturalProperties[0];
      if (Config.isNaturalTextures()) {
         String str = "optifine/natural.properties";

         try {
            ResourceLocation location = new ResourceLocation(str);
            if (!Config.hasResource(location)) {
               Config.dbg("NaturalTextures: configuration \"" + str + "\" not found");
               return;
            }

            boolean flag = Config.isFromDefaultResourcePack(location);
            InputStream resourceStream = Config.getResourceStream(location);
            ArrayList list = new ArrayList(256);
            String str2 = Config.readInputStream(resourceStream);
            resourceStream.close();
            String[] strings = Config.tokenize(str2, "\n\r");
            if (flag) {
               Config.dbg("Natural Textures: Parsing default configuration \"" + str + "\"");
               Config.dbg("Natural Textures: Valid only for textures from default resource pack");
            } else {
               Config.dbg("Natural Textures: Parsing configuration \"" + str + "\"");
            }

            TextureMap textureMapBlocks = TextureUtils.getTextureMapBlocks();

            for (int i = 0; i < strings.length; i++) {
               String str3 = strings[i].trim();
               if (!str3.startsWith("#")) {
                  String[] strings2 = Config.tokenize(str3, "=");
                  if (strings2.length != 2) {
                     Config.warn("Natural Textures: Invalid \"" + str + "\" line: " + str3);
                  } else {
                     String str4 = strings2[0].trim();
                     String str5 = strings2[1].trim();
                     TextureAtlasSprite spriteSafe = textureMapBlocks.getSpriteSafe("minecraft:blocks/" + str4);
                     if (spriteSafe == null) {
                        Config.warn("Natural Textures: Texture not found: \"" + str + "\" line: " + str3);
                     } else {
                        int indexInMap = spriteSafe.getIndexInMap();
                        if (indexInMap < 0) {
                           Config.warn("Natural Textures: Invalid \"" + str + "\" line: " + str3);
                        } else {
                           if (flag && !Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/" + str4 + ".png"))) {
                              return;
                           }

                           NaturalProperties naturalProperties = new NaturalProperties(str5);
                           if (naturalProperties.isValid()) {
                              while (list.size() <= indexInMap) {
                                 list.add(null);
                              }

                              list.set(indexInMap, naturalProperties);
                              Config.dbg("NaturalTextures: " + str4 + " = " + str5);
                           }
                        }
                     }
                  }
               }
            }

            propertiesByIndex = (NaturalProperties[])list.toArray(new NaturalProperties[list.size()]);
         } catch (FileNotFoundException e) {
            Config.warn("NaturalTextures: configuration \"" + str + "\" not found");
            return;
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }

   public static NaturalProperties getNaturalProperties(TextureAtlasSprite textureAtlasSprite) {
      if (!(textureAtlasSprite instanceof TextureAtlasSprite)) {
         return null;
      } else {
         int indexInMap = textureAtlasSprite.getIndexInMap();
         return indexInMap >= 0 && indexInMap < propertiesByIndex.length ? propertiesByIndex[indexInMap] : null;
      }
   }
}
