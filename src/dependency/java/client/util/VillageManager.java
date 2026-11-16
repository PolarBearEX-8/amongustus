package client.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.MathHelper;

public class VillageManager {
   public final List list = Lists.newArrayList();
   public final Map map = Maps.newHashMap();
   public int count;

   public Map getMap() {
      return this.map;
   }

   private static NullManager resolveNullManager(int count2, String text, int count3) {
      String[] textArray = count2 >= 3 ? text.split("\\*", 2) : text.split("x", 2);
      int count4 = 1;
      int count5 = 0;
      if (textArray.length == 2) {
         try {
            count4 = Integer.parseInt(textArray[0]);
            if (count3 + count4 >= 256) {
               count4 = 256 - count3;
            }

            if (count4 < 0) {
               count4 = 0;
            }
         } catch (Throwable throwable) {
            return null;
         }
      }

      Block block;
      try {
         String text2 = textArray[textArray.length - 1];
         if (count2 < 3) {
            String[] textArray2 = text2.split(":", 2);
            if (textArray2.length > 1) {
               count5 = Integer.parseInt(textArray2[1]);
            }

            block = Block.processAction3(Integer.parseInt(textArray2[0]));
         } else {
            String[] textArray3 = text2.split(":", 3);
            block = textArray3.length > 1 ? Block.getBlockFromName(textArray3[0] + ":" + textArray3[1]) : null;
            if (block != null) {
               count5 = textArray3.length > 2 ? Integer.parseInt(textArray3[2]) : 0;
            } else {
               block = Block.getBlockFromName(textArray3[0]);
               if (block != null) {
                  count5 = textArray3.length > 1 ? Integer.parseInt(textArray3[1]) : 0;
               }
            }

            if (block == null) {
               return null;
            }
         }

         if (block == Blocks.air) {
            count5 = 0;
         }

         if (count5 < 0 || count5 > 15) {
            count5 = 0;
         }
      } catch (Throwable throwable2) {
         return null;
      }

      NullManager nullManager = new NullManager(count2, count4, block, count5);
      nullManager.setCount3(count3);
      return nullManager;
   }

   public int getCount() {
      return this.count;
   }

   public static VillageManager resolveVillageManager(String text) {
      if (text == null) {
         return getVillageManager();
      } else {
         String[] textArray = text.split(";", -1);
         int count2 = textArray.length == 1 ? 0 : MathHelper.getIndex(textArray[0], 0);
         if (count2 >= 0 && count2 <= 3) {
            VillageManager villageManager = new VillageManager();
            int count3 = textArray.length == 1 ? 0 : 1;
            List list2 = resolveList(count2, textArray[count3++]);
            if (list2 != null && !list2.isEmpty()) {
               villageManager.getList().addAll(list2);
               villageManager.updateState();
               int count4 = BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count;
               if (count2 > 0 && textArray.length > count3) {
                  count4 = MathHelper.getIndex(textArray[count3++], count4);
               }

               villageManager.setCount(count4);
               if (count2 > 0 && textArray.length > count3) {
                  String[] textArray2 = textArray[count3++].toLowerCase().split(",");
                  int length = textArray2.length;

                  for (int index = 0; index < length; index++) {
                     String[] textArray3 = textArray2[index].split("\\(", 2);
                     HashMap map2 = Maps.newHashMap();
                     if (textArray3[0].length() > 0) {
                        villageManager.getMap().put(textArray3[0], map2);
                        if (textArray3.length > 1 && textArray3[1].endsWith(")") && textArray3[1].length() > 1) {
                           String[] textArray4 = textArray3[1].substring(0, textArray3[1].length() - 1).split(" ");

                           for (int index2 = 0; index2 < textArray4.length; index2++) {
                              String[] textArray5 = textArray4[index2].split("=", 2);
                              if (textArray5.length == 2) {
                                 map2.put(textArray5[0], textArray5[1]);
                              }
                           }
                        }
                     }
                  }
               } else {
                  villageManager.getMap().put("village", Maps.newHashMap());
               }

               return villageManager;
            } else {
               return getVillageManager();
            }
         } else {
            return getVillageManager();
         }
      }
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public void updateState() {
      int count2 = 0;

      for (NullManager nullManager : (Iterable<NullManager>)(Iterable<?>)this.list) {
         nullManager.setCount3(count2);
         count2 += nullManager.getCount2();
      }
   }

   private static List resolveList(int count2, String text) {
      if (text != null && text.length() >= 1) {
         ArrayList list2 = Lists.newArrayList();
         String[] textArray = text.split(",");
         int count3 = 0;

         for (String text2 : textArray) {
            NullManager nullManager = resolveNullManager(count2, text2, count3);
            if (nullManager == null) {
               return null;
            }

            list2.add(nullManager);
            count3 += nullManager.getCount2();
         }

         return list2;
      } else {
         return null;
      }
   }

   public List getList() {
      return this.list;
   }

   public static VillageManager getVillageManager() {
      VillageManager villageManager = new VillageManager();
      villageManager.setCount(BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager35.count);
      villageManager.getList().add(new NullManager(1, Blocks.block95));
      villageManager.getList().add(new NullManager(2, Blocks.block115));
      villageManager.getList().add(new NullManager(1, Blocks.blockGrass));
      villageManager.updateState();
      villageManager.getMap().put("village", Maps.newHashMap());
      return villageManager;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(3);
      builder.append(";");

      for (int index = 0; index < this.list.size(); index++) {
         if (index > 0) {
            builder.append(",");
         }

         builder.append(((NullManager)this.list.get(index)).toString());
      }

      builder.append(";");
      builder.append(this.count);
      if (!this.map.isEmpty()) {
         builder.append(";");
         int count2 = 0;

         for (Entry entry : (Iterable<Entry>)(Iterable<?>)this.map.entrySet()) {
            if (count2++ > 0) {
               builder.append(",");
            }

            builder.append(((String)entry.getKey()).toLowerCase());
            Map value = (Map)entry.getValue();
            if (!value.isEmpty()) {
               builder.append("(");
               int count3 = 0;

               for (Entry entry2 : (Iterable<Entry>)(Iterable<?>)value.entrySet()) {
                  if (count3++ > 0) {
                     builder.append(" ");
                  }

                  builder.append((String)entry2.getKey());
                  builder.append("=");
                  builder.append((String)entry2.getValue());
               }

               builder.append(")");
            }
         }
      } else {
         builder.append(";");
      }

      return builder.toString();
   }
}
