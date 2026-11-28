package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class FlatGeneratorInfo {
   private final List flatLayers = Lists.newArrayList();
   private final Map worldFeatures = Maps.newHashMap();
   private int biomeToUse;

   private static List getLayersFromString(int n, String str) {
      if (str != null && str.length() >= 1) {
         ArrayList list = Lists.newArrayList();
         String[] strings = str.split(",");
         int n2 = 0;

         for (String str2 : strings) {
            FlatLayerInfo layerFromString = getLayerFromString(n, str2, n2);
            if (layerFromString == null) {
               return null;
            }

            list.add(layerFromString);
            n2 += layerFromString.getLayerCount();
         }

         return list;
      } else {
         return null;
      }
   }

   public static FlatGeneratorInfo createFlatGeneratorFromString(String str) {
      if (str == null) {
         return getDefaultFlatGenerator();
      } else {
         String[] strings = str.split(";", -1);
         int n = strings.length == 1 ? 0 : MathHelper.parseIntWithDefault(strings[0], 0);
         if (n >= 0 && n <= 3) {
            FlatGeneratorInfo flatGeneratorInfo = new FlatGeneratorInfo();
            int n2 = strings.length == 1 ? 0 : 1;
            List layersFromString = getLayersFromString(n, strings[n2++]);
            if (layersFromString != null && !layersFromString.isEmpty()) {
               flatGeneratorInfo.getFlatLayers().addAll(layersFromString);
               flatGeneratorInfo.updateLayers();
               int biomeID = BiomeGenBase.plains.biomeID;
               if (n > 0 && strings.length > n2) {
                  biomeID = MathHelper.parseIntWithDefault(strings[n2++], biomeID);
               }

               flatGeneratorInfo.setBiome(biomeID);
               if (n > 0 && strings.length > n2) {

                  for (String str2 : strings[n2++].toLowerCase().split(",")) {
                     String[] strings3 = str2.split("\\(", 2);
                     HashMap map = Maps.newHashMap();
                     if (strings3[0].length() > 0) {
                        flatGeneratorInfo.getWorldFeatures().put(strings3[0], map);
                        if (strings3.length > 1 && strings3[1].endsWith(")") && strings3[1].length() > 1) {
                           String[] strings4 = strings3[1].substring(0, strings3[1].length() - 1).split(" ");

                           for (int i = 0; i < strings4.length; i++) {
                              String[] strings5 = strings4[i].split("=", 2);
                              if (strings5.length == 2) {
                                 map.put(strings5[0], strings5[1]);
                              }
                           }
                        }
                     }
                  }
               } else {
                  flatGeneratorInfo.getWorldFeatures().put("village", Maps.newHashMap());
               }

               return flatGeneratorInfo;
            } else {
               return getDefaultFlatGenerator();
            }
         } else {
            return getDefaultFlatGenerator();
         }
      }
   }

   private static FlatLayerInfo getLayerFromString(int n, String str, int n2) {
      String[] strings = n >= 3 ? str.split("\\*", 2) : str.split("x", 2);
      int n3 = 1;
      int n4 = 0;
      if (strings.length == 2) {
         try {
            n3 = Integer.parseInt(strings[0]);
            if (n2 + n3 >= 256) {
               n3 = 256 - n2;
            }

            if (n3 < 0) {
               n3 = 0;
            }
         } catch (Throwable e) {
            return null;
         }
      }

      Object obj = null;

      try {
         String str2 = strings[strings.length - 1];
         if (n < 3) {
            strings = str2.split(":", 2);
            if (strings.length > 1) {
               n4 = Integer.parseInt(strings[1]);
            }

            obj = Block.getBlockById(Integer.parseInt(strings[0]));
         } else {
            strings = str2.split(":", 3);
            obj = strings.length > 1 ? Block.getBlockFromName(strings[0] + ":" + strings[1]) : null;
            if (obj != null) {
               n4 = strings.length > 2 ? Integer.parseInt(strings[2]) : 0;
            } else {
               obj = Block.getBlockFromName(strings[0]);
               if (obj != null) {
                  n4 = strings.length > 1 ? Integer.parseInt(strings[1]) : 0;
               }
            }

            if (obj == null) {
               return null;
            }
         }

         if (obj == Blocks.air) {
            n4 = 0;
         }

         if (n4 < 0 || n4 > 15) {
            n4 = 0;
         }
      } catch (Throwable e2) {
         return null;
      }

      FlatLayerInfo flatLayerInfo = new FlatLayerInfo(n, n3, (Block)obj, n4);
      flatLayerInfo.setMinY(n2);
      return flatLayerInfo;
   }

   public static FlatGeneratorInfo getDefaultFlatGenerator() {
      FlatGeneratorInfo flatGeneratorInfo = new FlatGeneratorInfo();
      flatGeneratorInfo.setBiome(BiomeGenBase.plains.biomeID);
      flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(1, Blocks.bedrock));
      flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(2, Blocks.dirt));
      flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(1, Blocks.grass));
      flatGeneratorInfo.updateLayers();
      flatGeneratorInfo.getWorldFeatures().put("village", Maps.newHashMap());
      return flatGeneratorInfo;
   }

   public void setBiome(int n) {
      this.biomeToUse = n;
   }

   public Map getWorldFeatures() {
      return this.worldFeatures;
   }

   public void updateLayers() {
      int n = 0;

      for (FlatLayerInfo flatLayerInfo : (Iterable<FlatLayerInfo>)(this.flatLayers)) {
         flatLayerInfo.setMinY(n);
         n += flatLayerInfo.getLayerCount();
      }
   }

   public List getFlatLayers() {
      return this.flatLayers;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(3);
      sb.append(";");

      for (int i = 0; i < this.flatLayers.size(); i++) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((FlatLayerInfo)this.flatLayers.get(i)).toString());
      }

      sb.append(";");
      sb.append(this.biomeToUse);
      if (!this.worldFeatures.isEmpty()) {
         sb.append(";");
         int n = 0;

         for (Entry entry : (Iterable<Entry>)(this.worldFeatures.entrySet())) {
            if (n++ > 0) {
               sb.append(",");
            }

            sb.append(((String)entry.getKey()).toLowerCase());
            Map value = (Map)entry.getValue();
            if (!value.isEmpty()) {
               sb.append("(");
               int n2 = 0;

               for (Entry entry2 : (Iterable<Entry>)(value.entrySet())) {
                  if (n2++ > 0) {
                     sb.append(" ");
                  }

                  sb.append((String)entry2.getKey());
                  sb.append("=");
                  sb.append((String)entry2.getValue());
               }

               sb.append(")");
            }
         }
      } else {
         sb.append(";");
      }

      return sb.toString();
   }

   public int getBiome() {
      return this.biomeToUse;
   }
}
