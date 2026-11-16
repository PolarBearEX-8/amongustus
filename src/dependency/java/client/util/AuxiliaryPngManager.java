package client.util;

import client.setting.BlockSolidType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AuxiliaryPngManager {
   public String labelText3 = null;
   public String labelText4 = null;
   public SmoothMatcher[] smoothMatcherArray2 = null;
   public int[] countArray4 = null;
   public String[] stringArray = null;
   public int count2 = 0;
   public String[] stringArray3 = null;
   public int count13 = 0;
   public int count17 = 63;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = null;
   public TargetAggregator targetAggregator = null;
   public int count22 = 0;
   public boolean enabled2 = false;
   public int[] countArray2 = null;
   public int count11 = 0;
   public int count26 = 0;
   public int[] countArray = null;
   public int count28 = 0;
   public int count35 = 1;
   public boolean enabled = false;
   public GeometryObserver geometryObserver = null;
   public int[] countArray3 = null;
   public int count23 = 1;
   public ServiceStringEntry[] serviceStringEntryArray2 = null;
   public ServiceStringEntry[] serviceStringEntryArray = null;
   public SmoothMatcher[] smoothMatcherArray = null;
   public String[] stringArray2 = null;
   public ServiceStringEntry[] serviceStringEntryArray3 = null;
   public int count32 = -1;
   public IBlockState iBlockState = Blocks.air.getDefaultState();
   public BlockSolidType blockSolidType = null;
   public static final int count12 = 0;
   public static final int count16 = 1;
   public static final int count37 = 2;
   public static final int count29 = 3;
   public static final int count4 = 4;
   public static final int count19 = 5;
   public static final int count34 = 6;
   public static final int count14 = 7;
   public static final int count31 = 8;
   public static final int count6 = 9;
   public static final int count33 = 10;
   public static final int count36 = 11;
   public static final int count5 = 12;
   public static final int count9 = 13;
   public static final int count40 = 14;
   public static final int count18 = 15;
   public static final int count21 = 0;
   public static final int count10 = 1;
   public static final int count25 = 2;
   public static final int count7 = 3;
   public static final int count27 = 128;
   public static final int count3 = 1;
   public static final int count = 2;
   public static final int count43 = 4;
   public static final int count44 = 8;
   public static final int count39 = 16;
   public static final int count24 = 32;
   public static final int count42 = 60;
   public static final int count41 = 63;
   public static final int count38 = 128;
   public static final int count20 = 1;
   public static final int count15 = 2;
   public static final int count30 = 6;
   public static final int count8 = 128;
   public static final String labelText = "<skip>.png";
   public static final String labelText2 = "<default>.png";

   private String[] resolveTextArray(String text) {
      if (text == null) {
         return null;
      } else {
         String[] textArray = BuildManager.resolveTextArray(text, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text2.endsWith(".png")) {
               text2 = text2.substring(0, text2.length() - 4);
            }

            String text3 = InternalPngManager.resolveText(text2, this.labelText4);
            textArray[index] = text3;
         }

         return textArray;
      }
   }

   private static ServiceStringEntry resolveServiceStringEntry(String text) {
      ListenerAdapter27 listenerAdapter = Minecraft.getMinecraft().getListenerAdapter27();
      ServiceStringEntry serviceStringEntry = listenerAdapter.resolveServiceStringEntry(text);
      return serviceStringEntry != null ? serviceStringEntry : listenerAdapter.resolveServiceStringEntry("blocks/" + text);
   }

   private boolean matchesText6(String text) {
      if (!this.matchesText8(text)) {
         return false;
      } else if (this.blockSolidType != null && this.blockSolidType != BlockSolidType.SOLID) {
         return true;
      } else {
         BuildManager.applyText3("Invalid overlay layer: " + this.blockSolidType);
         return false;
      }
   }

   private int computeCount(int[] countArray5, int count45) {
      int count46 = count45;
      if (countArray5 == null) {
         return count45;
      } else {
         for (int index = 0; index < countArray5.length; index++) {
            int count47 = countArray5[index];
            if (count47 > count46) {
               count46 = count47;
            }
         }

         return count46;
      }
   }

   private static int computeCount2(String text) {
      String text2 = text.toLowerCase();
      if (text2.equals("bottom") || text2.equals("down")) {
         return 1;
      } else if (text2.equals("top") || text2.equals("up")) {
         return 2;
      } else if (text2.equals("north")) {
         return 4;
      } else if (text2.equals("south")) {
         return 8;
      } else if (text2.equals("east")) {
         return 32;
      } else if (text2.equals("west")) {
         return 16;
      } else if (text2.equals("sides")) {
         return 60;
      } else if (text2.equals("all")) {
         return 63;
      } else {
         BuildManager.applyText3("Unknown face: " + text2);
         return 128;
      }
   }

   private int[] resolveIndexArray(Properties properties) {
      if (this.stringArray3 == null) {
         return null;
      } else {
         HashMap map = new HashMap();

         for (Object element : properties.keySet()) {
            if (element instanceof String) {
               String text = (String)element;
               String text2 = "ctm.";
               if (text.startsWith(text2)) {
                  String text3 = text.substring(text2.length());
                  String property = properties.getProperty(text);
                  if (property != null) {
                     String text4 = property.trim();
                     int count45 = BuildManager.computeCount(text3, -1);
                     if (count45 >= 0 && count45 <= 46) {
                        int count46 = BuildManager.computeCount(text4, -1);
                        if (count46 >= 0 && count46 < this.stringArray3.length) {
                           map.put(count45, count46);
                        } else {
                           BuildManager.applyText3("Invalid CTM tile index: " + text4);
                        }
                     } else {
                        BuildManager.applyText3("Invalid CTM index: " + text3);
                     }
                  }
               }
            }
         }

         if (map.isEmpty()) {
            return null;
         } else {
            int[] countArray5 = new int[47];

            for (int index = 0; index < countArray5.length; index++) {
               countArray5[index] = -1;
               if (map.containsKey(index)) {
                  countArray5[index] = (Integer)map.get(index);
               }
            }

            return countArray5;
         }
      }
   }

   private boolean matchesText2(String text) {
      if (this.stringArray3 == null) {
         this.stringArray3 = this.resolveTextArrayByText("0-16");
      }

      if (this.stringArray3.length < 17) {
         BuildManager.applyText3("Invalid tiles, must be at least 17: " + text);
         return false;
      } else if (this.blockSolidType != null && this.blockSolidType != BlockSolidType.SOLID) {
         return true;
      } else {
         BuildManager.applyText3("Invalid overlay layer: " + this.blockSolidType);
         return false;
      }
   }

   public void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      if (this.stringArray != null) {
         this.serviceStringEntryArray2 = resolveServiceStringEntryArray(this.stringArray, listenerAdapter, false, false);
      }

      if (this.stringArray2 != null) {
         this.serviceStringEntryArray3 = resolveServiceStringEntryArray(this.stringArray2, listenerAdapter, false, false);
      }

      if (this.stringArray3 != null) {
         this.serviceStringEntryArray = resolveServiceStringEntryArray(this.stringArray3, listenerAdapter, true, !checkIndexByIndex(this.count2));
      }
   }

   public boolean matchesBiomeIDIsOutOfBoundsManage(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      return GeometrySelector.matchesBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager, this.biomeIDIsOutOfBoundsManagerArray);
   }

   private int getCount() {
      return this.smoothMatcherArray2 != null ? 1 : (this.stringArray != null ? 2 : 128);
   }

   private static String resolveText(String text) {
      String text2 = text;
      int count45 = text.lastIndexOf(47);
      if (count45 >= 0) {
         text2 = text.substring(count45 + 1);
      }

      int count46 = text2.lastIndexOf(46);
      if (count46 >= 0) {
         text2 = text2.substring(0, count46);
      }

      return text2;
   }

   public boolean checkIndex(int count45) {
      return GeometrySelector.matchesSmoothMatcherArray(count45, this.smoothMatcherArray2);
   }

   public boolean matchesServiceStringEntry(ServiceStringEntry serviceStringEntry) {
      return GeometrySelector.matchesServiceStringEntry(serviceStringEntry, this.serviceStringEntryArray2);
   }

   private boolean matchesTextByText(String text) {
      if (!this.matchesText11(text)) {
         return false;
      } else if (this.blockSolidType != null && this.blockSolidType != BlockSolidType.SOLID) {
         return true;
      } else {
         BuildManager.applyText3("Invalid overlay layer: " + this.blockSolidType);
         return false;
      }
   }

   public boolean checkIndex(int count45, int count46) {
      return !GeometrySelector.matchesSmoothMatcherArray(count45, count46, this.smoothMatcherArray2)
         ? false
         : GeometrySelector.matchesIndexArray(count46, this.countArray4);
   }

   private boolean matchesText(String text) {
      if (!this.matchesText5(text)) {
         return false;
      } else if (this.blockSolidType != null && this.blockSolidType != BlockSolidType.SOLID) {
         return true;
      } else {
         BuildManager.applyText3("Invalid overlay layer: " + this.blockSolidType);
         return false;
      }
   }

   private String[] getTextArray() {
      return resolveServiceStringEntry(this.labelText3) == null ? null : new String[]{this.labelText3};
   }

   private static String resolveTextByText(String text) {
      int count45 = text.lastIndexOf(47);
      return count45 < 0 ? "" : text.substring(0, count45);
   }

   private boolean matchesText4(String text) {
      if (this.stringArray3 == null) {
         BuildManager.applyText3("No tiles defined for vertical: " + text);
         return false;
      } else if (this.stringArray3.length != 4) {
         BuildManager.applyText3("Invalid tiles, must be exactly 4: " + text);
         return false;
      } else {
         return true;
      }
   }

   public int getCountAsIndex() {
      int count45 = this.computeCount(this.countArray4, -1);
      if (this.smoothMatcherArray2 != null) {
         for (int index = 0; index < this.smoothMatcherArray2.length; index++) {
            SmoothMatcher smoothMatcher = this.smoothMatcherArray2[index];
            count45 = this.computeCount(smoothMatcher.getCountArray(), count45);
         }
      }

      return count45;
   }

   private String[] resolveTextArrayByText(String text) {
      if (text == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] textArray = BuildManager.resolveTextArray(text, " ,");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text2.contains("-")) {
               String[] textArray2 = BuildManager.resolveTextArray(text2, "-");
               if (textArray2.length == 2) {
                  int count45 = BuildManager.computeCount(textArray2[0], -1);
                  int count46 = BuildManager.computeCount(textArray2[1], -1);
                  if (count45 >= 0 && count46 >= 0) {
                     if (count45 > count46) {
                        BuildManager.applyText3("Invalid interval: " + text2 + ", when parsing: " + text);
                        continue;
                     }

                     while (count45 <= count46) {
                        list.add(String.valueOf(count45));
                        count45++;
                     }
                     continue;
                  }
               }
            }

            list.add(text2);
         }

         String[] textArray3 = ((String[])list.toArray(new String[list.size()]));

         for (int index2 = 0; index2 < textArray3.length; index2++) {
            String text3 = InternalPngManager.resolveText(textArray3[index2], this.labelText4);
            if (!text3.startsWith(this.labelText4) && !text3.startsWith("textures/") && !text3.startsWith("mcpatcher/")) {
               text3 = this.labelText4 + "/" + text3;
            }

            if (text3.endsWith(".png")) {
               text3 = text3.substring(0, text3.length() - 4);
            }

            if (text3.startsWith("/")) {
               text3 = text3.substring(1);
            }

            textArray3[index2] = text3;
         }

         return textArray3;
      }
   }

   private boolean matchesText14(String text) {
      if (this.stringArray3 == null) {
         BuildManager.applyText3("Tiles not defined: " + text);
         return false;
      } else if (this.count11 <= 0) {
         BuildManager.applyText3("Invalid width: " + text);
         return false;
      } else if (this.count26 <= 0) {
         BuildManager.applyText3("Invalid height: " + text);
         return false;
      } else if (this.stringArray3.length != this.count11 * this.count26) {
         BuildManager.applyText3("Number of tiles does not equal width x height: " + text);
         return false;
      } else {
         return true;
      }
   }

   private static boolean checkIndexByIndex(int count45) {
      switch (count45) {
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

   public static IProperty resolveIProperty(String text, Collection collection) {
      for (IProperty iProperty : (Iterable<IProperty>)(Iterable)(collection)) {
         if (text.equals(iProperty.getLabelText())) {
            return iProperty;
         }
      }

      return null;
   }

   private boolean matchesText15(String text) {
      if (this.stringArray3 == null) {
         this.stringArray3 = this.resolveTextArrayByText("66");
      }

      if (this.stringArray3.length != 1) {
         BuildManager.applyText3("Invalid tiles, must be exactly 1: " + text);
         return false;
      } else {
         return true;
      }
   }

   private int[] getIndexArray() {
      if (!this.labelText3.startsWith("block")) {
         return null;
      } else {
         int length = "block".length();

         int count45;
         for (count45 = length; count45 < this.labelText3.length(); count45++) {
            char character = this.labelText3.charAt(count45);
            if (character < '0' || character > '9') {
               break;
            }
         }

         if (count45 == length) {
            return null;
         } else {
            int count46 = BuildManager.computeCount(this.labelText3.substring(length, count45), -1);
            return count46 < 0 ? null : new int[]{count46};
         }
      }
   }

   private static int computeCount4(String text) {
      if (text == null) {
         return 1;
      } else {
         String text2 = text.trim();
         if (text2.equals("ctm") || text2.equals("glass")) {
            return 1;
         } else if (text2.equals("ctm_compact")) {
            return 10;
         } else if (text2.equals("horizontal") || text2.equals("bookshelf")) {
            return 2;
         } else if (text2.equals("vertical")) {
            return 6;
         } else if (text2.equals("top")) {
            return 3;
         } else if (text2.equals("random")) {
            return 4;
         } else if (text2.equals("repeat")) {
            return 5;
         } else if (text2.equals("fixed")) {
            return 7;
         } else if (text2.equals("horizontal+vertical") || text2.equals("h+v")) {
            return 8;
         } else if (text2.equals("vertical+horizontal") || text2.equals("v+h")) {
            return 9;
         } else if (text2.equals("overlay")) {
            return 11;
         } else if (text2.equals("overlay_fixed")) {
            return 12;
         } else if (text2.equals("overlay_random")) {
            return 13;
         } else if (text2.equals("overlay_repeat")) {
            return 14;
         } else if (text2.equals("overlay_ctm")) {
            return 15;
         } else {
            BuildManager.applyText3("Unknown method: " + text2);
            return 0;
         }
      }
   }

   private static int computeCountByText(String text) {
      if (text == null) {
         return 0;
      } else {
         String text2 = text.trim();
         if (text2.equals("block")) {
            return 1;
         } else if (text2.equals("tile")) {
            return 2;
         } else if (text2.equals("material")) {
            return 3;
         } else {
            BuildManager.applyText3("Unknown connect: " + text2);
            return 128;
         }
      }
   }

   private boolean matchesText5(String text) {
      if (this.stringArray3 == null) {
         BuildManager.applyText3("Tiles not defined: " + text);
         return false;
      } else if (this.stringArray3.length != 1) {
         BuildManager.applyText3("Number of tiles should be 1 for method: fixed.");
         return false;
      } else {
         return true;
      }
   }

   private static int computeCount3(String text) {
      if (text == null) {
         return 63;
      } else {
         String[] textArray = BuildManager.resolveTextArray(text, " ,");
         int count45 = 0;

         for (int index = 0; index < textArray.length; index++) {
            int count46 = computeCount2(textArray[index]);
            count45 |= count46;
         }

         return count45;
      }
   }

   private boolean matchesText7(String text) {
      if (this.stringArray3 == null) {
         BuildManager.applyText3("No tiles defined for horizontal+vertical: " + text);
         return false;
      } else if (this.stringArray3.length != 7) {
         BuildManager.applyText3("Invalid tiles, must be exactly 7: " + text);
         return false;
      } else {
         return true;
      }
   }

   private static ServiceStringEntry[] resolveServiceStringEntryArray(String[] textArray, ListenerAdapter27 listenerAdapter, boolean flag, boolean flag2) {
      if (textArray == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            ResourceLocation location = new ResourceLocation(text);
            String text2 = location.getText();
            String text3 = location.getText1();
            if (!text3.contains("/")) {
               text3 = "textures/blocks/" + text3;
            }

            String text4 = text3 + ".png";
            if (flag && text4.endsWith("<skip>.png")) {
               list.add(null);
            } else if (flag2 && text4.endsWith("<default>.png")) {
               list.add(McpatcherCtmDefaultManager.serviceStringEntry2);
            } else {
               if (!BuildManager.matchesResourceLocation3(new ResourceLocation(text2, text4))) {
                  BuildManager.applyText3("File not found: " + text4);
               }

               String text5 = "textures/";
               if (text3.startsWith(text5)) {
                  text3 = text3.substring(text5.length());
               }

               ResourceLocation location2 = new ResourceLocation(text2, text3);
               ServiceStringEntry serviceStringEntry = listenerAdapter.resolveServiceStringEntry(location2);
               list.add(serviceStringEntry);
            }
         }

         return ((ServiceStringEntry[])list.toArray(new ServiceStringEntry[list.size()]));
      }
   }

   private SmoothMatcher[] getSmoothMatcherArray() {
      int[] indexArray = this.getIndexArray();
      if (indexArray == null) {
         return null;
      } else {
         SmoothMatcher[] smoothMatcherArray3 = new SmoothMatcher[indexArray.length];

         for (int index = 0; index < smoothMatcherArray3.length; index++) {
            smoothMatcherArray3[index] = new SmoothMatcher(indexArray[index]);
         }

         return smoothMatcherArray3;
      }
   }

   private static int computeCount(String text) {
      if (text == null) {
         return 1;
      } else {
         String text2 = text.trim();
         if (text2.equals("opposite")) {
            return 2;
         } else if (text2.equals("all")) {
            return 6;
         } else {
            BuildManager.applyText3("Unknown symmetry: " + text2);
            return 1;
         }
      }
   }

   private boolean matchesText3(String text) {
      if (this.stringArray3 == null) {
         this.stringArray3 = this.resolveTextArrayByText("0-4");
      }

      if (this.stringArray3.length < 5) {
         BuildManager.applyText3("Invalid tiles, must be at least 5: " + text);
         return false;
      } else {
         return true;
      }
   }

   private boolean matchesText9(String text) {
      if (!this.matchesText14(text)) {
         return false;
      } else if (this.blockSolidType != null && this.blockSolidType != BlockSolidType.SOLID) {
         return true;
      } else {
         BuildManager.applyText3("Invalid overlay layer: " + this.blockSolidType);
         return false;
      }
   }

   private boolean matchesText13(String text) {
      if (this.stringArray3 == null) {
         BuildManager.applyText3("No tiles defined for vertical+horizontal: " + text);
         return false;
      } else if (this.stringArray3.length != 7) {
         BuildManager.applyText3("Invalid tiles, must be exactly 7: " + text);
         return false;
      } else {
         return true;
      }
   }

   private boolean matchesText12(String text) {
      if (this.stringArray3 == null) {
         this.stringArray3 = this.resolveTextArrayByText("12-15");
      }

      if (this.stringArray3.length != 4) {
         BuildManager.applyText3("Invalid tiles, must be exactly 4: " + text);
         return false;
      } else {
         return true;
      }
   }

   public boolean matchesText10(String text) {
      if (this.labelText3 == null || this.labelText3.length() <= 0) {
         BuildManager.applyText3("No name found: " + text);
         return false;
      } else if (this.labelText4 == null) {
         BuildManager.applyText3("No base path found: " + text);
         return false;
      } else {
         if (this.smoothMatcherArray2 == null) {
            this.smoothMatcherArray2 = this.getSmoothMatcherArray();
         }

         if (this.stringArray == null && this.smoothMatcherArray2 == null) {
            this.stringArray = this.getTextArray();
         }

         if (this.smoothMatcherArray2 == null && this.stringArray == null) {
            BuildManager.applyText3("No matchBlocks or matchTiles specified: " + text);
            return false;
         } else if (this.count2 == 0) {
            BuildManager.applyText3("No method: " + text);
            return false;
         } else if (this.stringArray3 != null && this.stringArray3.length > 0) {
            if (this.count13 == 0) {
               this.count13 = this.getCount();
            }

            if (this.count13 == 128) {
               BuildManager.applyText3("Invalid connect in: " + text);
               return false;
            } else if (this.count22 > 0) {
               BuildManager.applyText3("Render pass not supported: " + this.count22);
               return false;
            } else if ((this.count17 & 128) != 0) {
               BuildManager.applyText3("Invalid faces in: " + text);
               return false;
            } else if ((this.count35 & 128) != 0) {
               BuildManager.applyText3("Invalid symmetry in: " + text);
               return false;
            } else {
               switch (this.count2) {
                  case 1:
                     return this.matchesText8(text);
                  case 2:
                     return this.matchesText12(text);
                  case 3:
                     return this.matchesText15(text);
                  case 4:
                     return this.matchesText11(text);
                  case 5:
                     return this.matchesText14(text);
                  case 6:
                     return this.matchesText4(text);
                  case 7:
                     return this.matchesText5(text);
                  case 8:
                     return this.matchesText7(text);
                  case 9:
                     return this.matchesText13(text);
                  case 10:
                     return this.matchesText3(text);
                  case 11:
                     return this.matchesText2(text);
                  case 12:
                     return this.matchesText(text);
                  case 13:
                     return this.matchesTextByText(text);
                  case 14:
                     return this.matchesText9(text);
                  case 15:
                     return this.matchesText6(text);
                  default:
                     BuildManager.applyText3("Unknown method: " + text);
                     return false;
               }
            }
         } else {
            BuildManager.applyText3("No tiles specified: " + text);
            return false;
         }
      }
   }

   private boolean matchesText11(String text) {
      if (this.stringArray3 != null && this.stringArray3.length > 0) {
         if (this.countArray != null) {
            if (this.countArray.length > this.stringArray3.length) {
               BuildManager.applyText3("More weights defined than tiles, trimming weights: " + text);
               int[] countArray5 = new int[this.stringArray3.length];
               System.arraycopy(this.countArray, 0, countArray5, 0, countArray5.length);
               this.countArray = countArray5;
            }

            if (this.countArray.length < this.stringArray3.length) {
               BuildManager.applyText3("Less weights defined than tiles, expanding weights: " + text);
               int[] countArray6 = new int[this.stringArray3.length];
               System.arraycopy(this.countArray, 0, countArray6, 0, this.countArray.length);
               int count45 = AltFloatEntry.computeCount(this.countArray);

               for (int index = this.countArray.length; index < countArray6.length; index++) {
                  countArray6[index] = count45;
               }

               this.countArray = countArray6;
            }

            this.countArray3 = new int[this.countArray.length];
            int count46 = 0;

            for (int index2 = 0; index2 < this.countArray.length; index2++) {
               count46 += this.countArray[index2];
               this.countArray3[index2] = count46;
            }

            this.count23 = count46;
            if (this.count23 <= 0) {
               BuildManager.applyText3("Invalid sum of all weights: " + count46);
               this.count23 = 1;
            }
         }

         if (this.count28 >= 0 && this.count28 <= 9) {
            return true;
         } else {
            BuildManager.applyText3("Invalid randomLoops: " + this.count28);
            return false;
         }
      } else {
         BuildManager.applyText3("Tiles not defined: " + text);
         return false;
      }
   }

   private boolean matchesText8(String text) {
      if (this.stringArray3 == null) {
         this.stringArray3 = this.resolveTextArrayByText("0-11 16-27 32-43 48-58");
      }

      if (this.stringArray3.length < 47) {
         BuildManager.applyText3("Invalid tiles, must be at least 47: " + text);
         return false;
      } else {
         return true;
      }
   }

   public AuxiliaryPngManager(Properties properties, String text) {
      NetherManager netherManager = new NetherManager("ConnectedTextures");
      this.labelText3 = netherManager.resolveText(text);
      this.labelText4 = netherManager.resolveTextByText(text);
      this.smoothMatcherArray2 = netherManager.resolveSmoothMatcherArrayByText(properties.getProperty("matchBlocks"));
      this.countArray4 = netherManager.resolveIndexArrayByText(properties.getProperty("metadata"));
      this.stringArray = this.resolveTextArray(properties.getProperty("matchTiles"));
      this.count2 = computeCount4(properties.getProperty("method"));
      this.stringArray3 = this.resolveTextArrayByText(properties.getProperty("tiles"));
      this.count13 = computeCountByText(properties.getProperty("connect"));
      this.count17 = computeCount3(properties.getProperty("faces"));
      this.biomeIDIsOutOfBoundsManagerArray = netherManager.resolveBiomeIDIsOutOfBoundsManagerA(properties.getProperty("biomes"));
      this.targetAggregator = netherManager.resolveTargetAggregator(properties.getProperty("heights"));
      if (this.targetAggregator == null) {
         int count45 = netherManager.computeCount(properties.getProperty("minHeight"), -1);
         int count46 = netherManager.computeCount(properties.getProperty("maxHeight"), 1024);
         if (count45 != -1 || count46 != 1024) {
            this.targetAggregator = new TargetAggregator(new RotationMonitor(count45, count46));
         }
      }

      this.count22 = netherManager.computeCount(properties.getProperty("renderPass"), -1);
      this.enabled2 = netherManager.matchesText(properties.getProperty("innerSeams"), false);
      this.countArray2 = this.resolveIndexArray(properties);
      this.count11 = netherManager.computeCount(properties.getProperty("width"), -1);
      this.count26 = netherManager.computeCount(properties.getProperty("height"), -1);
      this.countArray = netherManager.resolveIndexArrayByText(properties.getProperty("weights"));
      this.count28 = netherManager.computeCount(properties.getProperty("randomLoops"), 0);
      this.count35 = computeCount(properties.getProperty("symmetry"));
      this.enabled = netherManager.matchesText(properties.getProperty("linked"), false);
      this.geometryObserver = netherManager.resolveGeometryObserver("name", properties.getProperty("name"));
      this.smoothMatcherArray = netherManager.resolveSmoothMatcherArrayByText(properties.getProperty("connectBlocks"));
      this.stringArray2 = this.resolveTextArray(properties.getProperty("connectTiles"));
      this.count32 = netherManager.computeCount(properties.getProperty("tintIndex"), -1);
      this.iBlockState = netherManager.resolveIBlockState(properties.getProperty("tintBlock"), Blocks.air.getDefaultState());
      this.blockSolidType = netherManager.resolveBlockSolidType(properties.getProperty("layer"), BlockSolidType.CUTOUT_MIPPED);
   }

   @Override
   public String toString() {
      return "CTM name: "
         + this.labelText3
         + ", basePath: "
         + this.labelText4
         + ", matchBlocks: "
         + BuildManager.resolveText(this.smoothMatcherArray2)
         + ", matchTiles: "
         + BuildManager.resolveText(this.stringArray);
   }
}
