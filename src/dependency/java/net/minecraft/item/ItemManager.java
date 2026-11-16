package net.minecraft.item;

import client.setting.CrafterLeatherType;
import client.setting.OptionType;
import client.util.BezierSerializer;
import client.util.BuildManager;
import client.util.EnumFacingHolder;
import client.util.FovSorter;
import client.util.GeometryObserver;
import client.util.InternalPngManager;
import client.util.JsonManager;
import client.util.ListenerAdapter27;
import client.util.LocalIntArrayEntry;
import client.util.MissingParticleManager;
import client.util.NativeLoggerEntry;
import client.util.PassiveListEntry;
import client.util.ResourceLocationAdapter;
import client.util.RotationMonitor;
import client.util.ServiceStringEntry;
import client.util.SplineCollector;
import client.util.SystemFloatEntry;
import client.util.TargetAggregator;
import client.util.TargetService;
import client.util.Vector3fHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ItemManager {
   public String labelText = null;
   public String labelText2 = null;
   public int count10 = 1;
   public int[] countArray = null;
   public String labelText3 = null;
   public Map map2 = null;
   public String labelText4 = null;
   public Map map = null;
   public TargetAggregator targetAggregator4 = null;
   public boolean enabled = false;
   public int count9 = 0;
   public TargetAggregator targetAggregator2 = null;
   public TargetAggregator targetAggregator = null;
   public TargetAggregator targetAggregator3 = null;
   public GeometryObserver[] geometryObserverArray = null;
   public int count15 = 0;
   public int count13 = 1;
   public float factor2 = 0.0F;
   public float factor3 = 0.0F;
   public int count = 0;
   public float factor = 1.0F;
   public int count8 = 0;
   public ResourceLocation resourceLocation = null;
   public Map map3 = null;
   public ServiceStringEntry serviceStringEntry = null;
   public Map map5 = null;
   public IBakedModel iBakedModel2 = null;
   public Map map4 = null;
   public IBakedModel iBakedModel = null;
   public Map map6 = null;
   public int count7 = 0;
   public int count4 = 0;
   public static final int count12 = 0;
   public static final int count5 = 1;
   public static final int count2 = 2;
   public static final int count14 = 3;
   public static final int count3 = 0;
   public static final int count11 = 1;
   public static final int count6 = 2;
   public static final String labelText5 = "inventory";

   private int computeCount(String text) {
      if (text == null) {
         return 1;
      } else if (text.equals("item")) {
         return 1;
      } else if (text.equals("enchantment")) {
         return 2;
      } else if (text.equals("armor")) {
         return 3;
      } else {
         BuildManager.applyText3("Unknown method: " + text);
         return 0;
      }
   }

   public float computeFactor3(TextureManager textureManager) {
      if (this.count4 <= 0) {
         if (this.resourceLocation != null) {
            int count16 = textureManager.get(this.resourceLocation).getCount();
            int stateInt = GlStateManager.getStateInt4();
            GlStateManager.setActiveTexture(count16);
            this.count4 = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            GlStateManager.setActiveTexture(stateInt);
         }

         if (this.count4 <= 0) {
            this.count4 = 16;
         }
      }

      return (float)this.count4;
   }

   private static ResourceLocation resolveResourceLocation(String text) {
      return new ResourceLocation(text);
   }

   private TargetAggregator resolveTargetAggregator(String text, BezierSerializer bezierSerializer) {
      if (text == null) {
         return null;
      } else {
         String[] textArray = BuildManager.resolveTextArray(text, " ");
         TargetAggregator targetAggregator5 = new TargetAggregator();

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (bezierSerializer != null) {
               int count16 = bezierSerializer.computeCount(text2, Integer.MIN_VALUE);
               if (count16 != Integer.MIN_VALUE) {
                  targetAggregator5.applyRotationMonitor(new RotationMonitor(count16, count16));
                  continue;
               }
            }

            RotationMonitor rotationMonitor = this.resolveRotationMonitor(text2);
            if (rotationMonitor == null) {
               BuildManager.applyText3("Invalid range list: " + text);
               return null;
            }

            targetAggregator5.applyRotationMonitor(rotationMonitor);
         }

         return targetAggregator5;
      }
   }

   private int[] getIndexArray() {
      Item item = Item.getItem(this.labelText);
      if (item == null) {
         return null;
      } else {
         int index = Item.getIndex(item);
         return index <= 0 ? null : new int[]{index};
      }
   }

   public boolean matchesText(String text) {
      if (this.labelText == null || this.labelText.length() <= 0) {
         BuildManager.applyText3("No name found: " + text);
         return false;
      } else if (this.labelText2 == null) {
         BuildManager.applyText3("No base path found: " + text);
         return false;
      } else if (this.count10 == 0) {
         BuildManager.applyText3("No type defined: " + text);
         return false;
      } else {
         if (this.count10 == 1 || this.count10 == 3) {
            if (this.countArray == null) {
               this.countArray = this.getIndexArray();
            }

            if (this.countArray == null) {
               BuildManager.applyText3("No items defined: " + text);
               return false;
            }
         }

         if (this.labelText3 == null && this.map2 == null && this.labelText4 == null && this.map == null) {
            BuildManager.applyText3("No texture or model specified: " + text);
            return false;
         } else if (this.count10 == 2 && this.targetAggregator == null) {
            BuildManager.applyText3("No enchantmentIDs specified: " + text);
            return false;
         } else {
            return true;
         }
      }
   }

   private static IBakedModel resolveIBakedModel(ListenerAdapter27 listenerAdapter, PassiveListEntry passiveListEntry, String[] textArray, boolean flag) {
      String[] textArray2 = new String[textArray.length];

      for (int index = 0; index < textArray2.length; index++) {
         String text = textArray[index];
         textArray2[index] = SplineCollector.resolveText2(text, "textures/");
      }

      NativeLoggerEntry nativeLoggerEntry = resolveNativeLoggerEntry(textArray2);
      NativeLoggerEntry nativeLoggerEntry2 = passiveListEntry.resolveNativeLoggerEntry(listenerAdapter, nativeLoggerEntry);
      return resolveIBakedModel(listenerAdapter, nativeLoggerEntry2, flag);
   }

   private boolean isActive() {
      return true;
   }

   private static Map resolveMap(Properties properties, String text) {
      String text2 = "texture.";
      Map map7 = resolveMapByProperties(properties, text2);
      if (map7.size() <= 0) {
         return null;
      } else {
         Set keys = map7.keySet();
         LinkedHashMap map8 = new LinkedHashMap();

         for (Object text3Obj : keys) { String text3 = (String)text3Obj;
            String text4 = resolveTextByText((String)map7.get(text3), text);
            map8.put(text3, text4);
         }

         return map8;
      }
   }

   private static String resolveText(String text, String text2) {
      String text3 = InternalPngManager.resolveText(text, text2);
      boolean flag = text3.startsWith("block/") || text3.startsWith("item/");
      if (!text3.startsWith(text2) && !flag && !text3.startsWith("mcpatcher/")) {
         text3 = text2 + "/" + text3;
      }

      String text4 = ".json";
      if (text3.endsWith(text4)) {
         text3 = text3.substring(0, text3.length() - text4.length());
      }

      if (text3.startsWith("/")) {
         text3 = text3.substring(1);
      }

      return text3;
   }

   private GeometryObserver[] resolveGeometryObserverArray(Properties properties) {
      String text = "nbt.";
      Map map7 = resolveMapByProperties(properties, text);
      if (map7.size() <= 0) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (Object text2Obj : map7.keySet()) { String text2 = (String)text2Obj;
            String text3 = (String)map7.get(text2);
            String text4 = text2.substring(text.length());
            GeometryObserver geometryObserver = new GeometryObserver(text4, text3);
            list.add(geometryObserver);
         }

         return (GeometryObserver[])list.toArray(new GeometryObserver[list.size()]);
      }
   }

   private static void applyJsonManager(JsonManager jsonManager, String text) {
      ResourceLocation location = resolveResourceLocation(text);
      ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter(location, "inventory");
      jsonManager.applyText(location.toString(), resourceLocationAdapter, location);
   }

   private static void applyTarget(Object entry, String text) throws NullPointerException {
      if (entry == null) {
         throw new NullPointerException(text);
      }
   }

   public float computeFactorByTextureManager(TextureManager textureManager) {
      if (this.count7 <= 0) {
         if (this.resourceLocation != null) {
            int count16 = textureManager.get(this.resourceLocation).getCount();
            int stateInt = GlStateManager.getStateInt4();
            GlStateManager.setActiveTexture(count16);
            this.count7 = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            GlStateManager.setActiveTexture(stateInt);
         }

         if (this.count7 <= 0) {
            this.count7 = 16;
         }
      }

      return (float)this.count7;
   }

   public IBakedModel resolveIBakedModelByResourceLocation(ResourceLocation location, boolean flag) {
      IBakedModel iBakedModel3;
      Map map7;
      if (flag) {
         iBakedModel3 = this.iBakedModel;
         map7 = this.map6;
      } else {
         iBakedModel3 = this.iBakedModel2;
         map7 = this.map4;
      }

      if (location != null && map7 != null) {
         String text = location.getText1();
         IBakedModel iBakedModel4 = (IBakedModel)map7.get(text);
         if (iBakedModel4 != null) {
            return iBakedModel4;
         }
      }

      return iBakedModel3;
   }

   private int[] resolveIndexArray(String text, String text2) {
      String text3 = text;
      if (text == null) {
         text3 = text2;
      }

      if (text3 == null) {
         return null;
      } else {
         text3 = text3.trim();
         TreeSet treeSet = new TreeSet();
         String[] textArray = BuildManager.resolveTextArray(text3, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text4 = textArray[index];
            int count16 = BuildManager.computeCount(text4, -1);
            if (count16 >= 0) {
               treeSet.add(new Integer(count16));
            } else {
               if (text4.contains("-")) {
                  String[] textArray2 = BuildManager.resolveTextArray(text4, "-");
                  if (textArray2.length == 2) {
                     int count17 = BuildManager.computeCount(textArray2[0], -1);
                     int count18 = BuildManager.computeCount(textArray2[1], -1);
                     if (count17 >= 0 && count18 >= 0) {
                        int count19 = Math.min(count17, count18);

                        for (int index2 = Math.max(count17, count18); count19 <= index2; count19++) {
                           treeSet.add(new Integer(count19));
                        }
                        continue;
                     }
                  }
               }

               Item item = Item.getItem(text4);
               if (item == null) {
                  BuildManager.applyText3("Item not found: " + text4);
               } else {
                  int index3 = Item.getIndex(item);
                  if (index3 <= 0) {
                     BuildManager.applyText3("Item not found: " + text4);
                  } else {
                     treeSet.add(new Integer(index3));
                  }
               }
            }
         }

         Integer[] countArray2 = (Integer[])treeSet.toArray(new Integer[treeSet.size()]);
         int[] countArray3 = new int[countArray2.length];

         for (int index4 = 0; index4 < countArray3.length; index4++) {
            countArray3[index4] = countArray2[index4];
         }

         return countArray3;
      }
   }

   private static String resolveText(String text) {
      int count16 = text.lastIndexOf(47);
      return count16 < 0 ? "" : text.substring(0, count16);
   }

   private ResourceLocation resolveResourceLocation(ResourceLocation location) {
      String text = SplineCollector.resolveText3(SplineCollector.resolveText2(location.getText1(), "textures/"), ".png");
      return new ResourceLocation(location.getText(), text);
   }

   private int computeCountByText(String text) {
      if (text == null) {
         return 0;
      } else {
         String text2 = text.toLowerCase();
         if (text2.equals("any")) {
            return 0;
         } else if (text2.equals("main")) {
            return 1;
         } else if (text2.equals("off")) {
            return 2;
         } else {
            BuildManager.applyText3("Invalid hand: " + text2);
            return 0;
         }
      }
   }

   private TargetAggregator resolveTargetAggregator(String text) {
      return this.resolveTargetAggregator(text, (BezierSerializer)null);
   }

   private String get(Map map7, String text, String text2) {
      if (map7 == null) {
         return text2;
      } else {
         String text3 = (String)map7.get(text);
         return text3 == null ? text2 : text3;
      }
   }

   private static String resolveTextByText(String text) {
      String text2 = text;
      int count16 = text.lastIndexOf(47);
      if (count16 >= 0) {
         text2 = text.substring(count16 + 1);
      }

      int count17 = text2.lastIndexOf(46);
      if (count17 >= 0) {
         text2 = text2.substring(0, count17);
      }

      return text2;
   }

   private ResourceLocation resolveResourceLocation2(String text) {
      if (text == null) {
         return null;
      } else {
         ResourceLocation location = new ResourceLocation(text);
         String text2 = location.getText();
         String text3 = location.getText1();
         if (!text3.contains("/")) {
            text3 = "textures/items/" + text3;
         }

         String text4 = text3 + ".png";
         ResourceLocation location2 = new ResourceLocation(text2, text4);
         if (!BuildManager.matchesResourceLocation3(location2)) {
            BuildManager.applyText3("File not found: " + text4);
         }

         return location2;
      }
   }

   private static Map resolveMap2(Properties properties, String text) {
      String text2 = "model.";
      Map map7 = resolveMapByProperties(properties, text2);
      if (map7.size() <= 0) {
         return null;
      } else {
         Set keys = map7.keySet();
         LinkedHashMap map8 = new LinkedHashMap();

         for (Object text3Obj : keys) { String text3 = (String)text3Obj;
            String text4 = resolveText((String)map7.get(text3), text);
            map8.put(text3, text4);
         }

         return map8;
      }
   }

   public void updateState() {
      IronDust ironDust = BuildManager.getIronDust();
      IBakedModel iBakedModel3 = ironDust.getIBakedModel();
      if (this.labelText4 != null) {
         ResourceLocation location = resolveResourceLocation(this.labelText4);
         ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter(location, "inventory");
         this.iBakedModel = ironDust.resolveIBakedModel(resourceLocationAdapter);
         if (this.iBakedModel == iBakedModel3) {
            BuildManager.applyText3("Custom Items: Model not found " + resourceLocationAdapter.getText1());
            this.iBakedModel = null;
         }
      }

      if (this.count10 == 1 && this.map != null) {
         for (Object textObj : this.map.keySet()) { String text = (String)textObj;
            String text2 = (String)this.map.get(text);
            String text3 = SplineCollector.resolveText2(text, "model.");
            if (text3.startsWith("bow") || text3.startsWith("fishing_rod") || text3.startsWith("shield")) {
               ResourceLocation location2 = resolveResourceLocation(text2);
               ResourceLocationAdapter resourceLocationAdapter2 = new ResourceLocationAdapter(location2, "inventory");
               IBakedModel iBakedModel4 = ironDust.resolveIBakedModel(resourceLocationAdapter2);
               if (iBakedModel4 == iBakedModel3) {
                  BuildManager.applyText3("Custom Items: Model not found " + resourceLocationAdapter2.getText1());
               } else {
                  if (this.map6 == null) {
                     this.map6 = new HashMap();
                  }

                  this.map6.put(text3, iBakedModel4);
               }
            }
         }
      }
   }

   private static IBakedModel resolveIBakedModel(ListenerAdapter27 listenerAdapter, NativeLoggerEntry nativeLoggerEntry, boolean flag) {
      OptionType optionType = OptionType.X0_Y0;
      String text = nativeLoggerEntry.resolveText("particle");
      ServiceStringEntry serviceStringEntry2 = listenerAdapter.get(new ResourceLocation(text).toString());
      MissingParticleManager missingParticleManager = new MissingParticleManager(nativeLoggerEntry).withServiceStringEntry(serviceStringEntry2);

      for (Object vector3fHolderObj : nativeLoggerEntry.getList()) { Vector3fHolder vector3fHolder = (Vector3fHolder)vector3fHolderObj;
         for (Object enumFacingObj : vector3fHolder.map.keySet()) { EnumFacing enumFacing = (EnumFacing)enumFacingObj;
            EnumFacingHolder enumFacingHolder = (EnumFacingHolder)vector3fHolder.map.get(enumFacing);
            if (!flag) {
               enumFacingHolder = new EnumFacingHolder(enumFacingHolder.enumFacing2, -1, enumFacingHolder.labelText, enumFacingHolder.tertiaryFloatArrayEntry);
            }

            String text2 = nativeLoggerEntry.resolveText(enumFacingHolder.labelText);
            ServiceStringEntry serviceStringEntry3 = listenerAdapter.get(new ResourceLocation(text2).toString());
            LocalIntArrayEntry localIntArrayEntry = resolveLocalIntArrayEntry(vector3fHolder, enumFacingHolder, serviceStringEntry3, enumFacing, optionType, false);
            if (enumFacingHolder.enumFacing2 == null) {
               missingParticleManager.add(localIntArrayEntry);
            } else {
               missingParticleManager.resolveMissingParticleManager(optionType.resolveEnumFacing(enumFacingHolder.enumFacing2), localIntArrayEntry);
            }
         }
      }

      return missingParticleManager.getIBakedModel();
   }

   private float computeFactor(String text, float factor4) {
      if (text == null) {
         return factor4;
      } else {
         String text2 = text.trim();
         float factor5 = BuildManager.computeFactor(text2, Float.MIN_VALUE);
         if (factor5 == Float.MIN_VALUE) {
            BuildManager.applyText3("Invalid float: " + text2);
            return factor4;
         } else {
            return factor5;
         }
      }
   }

   public void applyListenerAdapter27(ListenerAdapter27 listenerAdapter, PassiveListEntry passiveListEntry) {
      if (this.labelText3 != null || this.map2 != null) {
         String[] textArray = this.getTextArray();
         boolean active = this.isActive();
         this.iBakedModel2 = resolveIBakedModel(listenerAdapter, passiveListEntry, textArray, active);
         if (this.count10 == 1 && this.map2 != null) {
            for (Object textObj : this.map2.keySet()) { String text = (String)textObj;
               String text2 = (String)this.map2.get(text);
               String text3 = SplineCollector.resolveText2(text, "texture.");
               if (text3.startsWith("bow") || text3.startsWith("fishing_rod") || text3.startsWith("shield")) {
                  String[] textArray2 = new String[]{text2};
                  IBakedModel iBakedModel3 = resolveIBakedModel(listenerAdapter, passiveListEntry, textArray2, active);
                  if (this.map4 == null) {
                     this.map4 = new HashMap();
                  }

                  this.map4.put(text3, iBakedModel3);
               }
            }
         }
      }
   }

   public void applyJsonManager(JsonManager jsonManager) {
      if (this.labelText4 != null) {
         applyJsonManager(jsonManager, this.labelText4);
      }

      if (this.count10 == 1 && this.map != null) {
         for (Object textObj : this.map.keySet()) { String text = (String)textObj;
            String text2 = (String)this.map.get(text);
            String text3 = SplineCollector.resolveText2(text, "model.");
            if (text3.startsWith("bow") || text3.startsWith("fishing_rod") || text3.startsWith("shield")) {
               applyJsonManager(jsonManager, text2);
            }
         }
      }
   }

   private static String resolveText(String text, String text2, String text3, int count16, Map map7) {
      String text4 = text;
      if (text != null) {
         String text5 = ".json";
         if (text.endsWith(text5)) {
            text4 = text.substring(0, text.length() - text5.length());
         }

         return resolveText(text4, text3);
      } else if (count16 == 3) {
         return null;
      } else {
         if (map7 != null) {
            String text6 = (String)map7.get("model.bow_standby");
            if (text6 != null) {
               return text6;
            }
         }

         return text;
      }
   }

   private static String resolveText(String text, String text2, String text3, String text4, String text5, int count16, Map map7, boolean flag) {
      String text6 = text;
      if (text == null) {
         text6 = text2;
      }

      if (text6 == null) {
         text6 = text3;
      }

      if (text6 != null) {
         String text7 = ".png";
         if (text6.endsWith(text7)) {
            text6 = text6.substring(0, text6.length() - text7.length());
         }

         return resolveTextByText(text6, text5);
      } else if (count16 == 3) {
         return null;
      } else {
         if (map7 != null) {
            String text8 = (String)map7.get("texture.bow_standby");
            if (text8 != null) {
               return text8;
            }
         }

         if (!flag) {
            return null;
         } else {
            String text9 = text4;
            int count17 = text4.lastIndexOf(47);
            if (count17 >= 0) {
               text9 = text4.substring(count17 + 1);
            }

            int count18 = text9.lastIndexOf(46);
            if (count18 >= 0) {
               text9 = text9.substring(0, count18);
            }

            return resolveTextByText(text9, text5);
         }
      }
   }

   private static String resolveTextByText(String text, String text2) {
      String text3 = InternalPngManager.resolveText(text, text2);
      if (!text3.startsWith(text2) && !text3.startsWith("textures/") && !text3.startsWith("mcpatcher/")) {
         text3 = text2 + "/" + text3;
      }

      if (text3.endsWith(".png")) {
         text3 = text3.substring(0, text3.length() - 4);
      }

      if (text3.startsWith("/")) {
         text3 = text3.substring(1);
      }

      return text3;
   }

   private static LocalIntArrayEntry resolveLocalIntArrayEntry(
      Vector3fHolder vector3fHolder, EnumFacingHolder enumFacingHolder, ServiceStringEntry serviceStringEntry2, EnumFacing enumFacing, OptionType optionType, boolean flag
   ) {
      return new SystemFloatEntry().resolveLocalIntArrayEntry(vector3fHolder.vector3f2, vector3fHolder.vector3f, enumFacingHolder, serviceStringEntry2, enumFacing, optionType, vector3fHolder.vector3fEntry, flag, vector3fHolder.enabled);
   }

   public void applyListenerAdapter27(ListenerAdapter27 listenerAdapter) {
      if (this.labelText3 != null) {
         this.resourceLocation = this.resolveResourceLocation2(this.labelText3);
         if (this.count10 == 1) {
            ResourceLocation location = this.resolveResourceLocation(this.resourceLocation);
            this.serviceStringEntry = listenerAdapter.resolveServiceStringEntry(location);
         }
      }

      if (this.map2 != null) {
         this.map3 = new HashMap();
         this.map5 = new HashMap();

         for (Object textObj : this.map2.keySet()) { String text = (String)textObj;
            String text2 = (String)this.map2.get(text);
            ResourceLocation location2 = this.resolveResourceLocation2(text2);
            this.map3.put(text, location2);
            if (this.count10 == 1) {
               ResourceLocation location3 = this.resolveResourceLocation(location2);
               ServiceStringEntry serviceStringEntry2 = listenerAdapter.resolveServiceStringEntry(location3);
               this.map5.put(text, serviceStringEntry2);
            }
         }
      }
   }

   private int computeCount(String text, int count16) {
      if (text == null) {
         return count16;
      } else {
         String text2 = text.trim();
         int count17 = BuildManager.computeCount(text2, Integer.MIN_VALUE);
         if (count17 == Integer.MIN_VALUE) {
            BuildManager.applyText3("Invalid integer: " + text2);
            return count16;
         } else {
            return count17;
         }
      }
   }

   private RotationMonitor resolveRotationMonitor(String text) {
      if (text == null) {
         return null;
      } else {
         String text2 = text.trim();
         if (text2.length() - text2.replace("-", "").length() > 1) {
            BuildManager.applyText3("Invalid range: " + text2);
            return null;
         } else {
            String[] textArray = BuildManager.resolveTextArray(text2, "- ");
            int[] countArray2 = new int[textArray.length];

            for (int index = 0; index < textArray.length; index++) {
               int count16 = BuildManager.computeCount(textArray[index], -1);
               if (count16 < 0) {
                  BuildManager.applyText3("Invalid range: " + text2);
                  return null;
               }

               countArray2[index] = count16;
            }

            if (countArray2.length == 1) {
               int count17 = countArray2[0];
               if (text2.startsWith("-")) {
                  return new RotationMonitor(0, count17);
               } else {
                  return text2.endsWith("-") ? new RotationMonitor(count17, 65535) : new RotationMonitor(count17, count17);
               }
            } else if (countArray2.length == 2) {
               int count18 = Math.min(countArray2[0], countArray2[1]);
               int count19 = Math.max(countArray2[0], countArray2[1]);
               return new RotationMonitor(count18, count19);
            } else {
               BuildManager.applyText3("Invalid range: " + text2);
               return null;
            }
         }
      }
   }

   private static Map resolveMapByProperties(Properties properties, String text) {
      LinkedHashMap map7 = new LinkedHashMap();

      for (Object text2Obj : properties.keySet()) { String text2 = (String)text2Obj;
         String property = properties.getProperty(text2);
         if (text2.startsWith(text)) {
            map7.put(text2, property);
         }
      }

      return map7;
   }

   private static NativeLoggerEntry resolveNativeLoggerEntry(String[] textArray) {
      StringBuffer builder = new StringBuffer();
      builder.append("{\"parent\": \"builtin/generated\",\"textures\": {");

      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         if (index > 0) {
            builder.append(", ");
         }

         builder.append("\"layer" + index + "\": \"" + text + "\"");
      }

      builder.append("}}");
      return NativeLoggerEntry.resolveNativeLoggerEntry(builder.toString());
   }

   private String[] getTextArray() {
      if (this.count10 == 1 && this.countArray.length == 1) {
         Item item = Item.processAction(this.countArray[0]);
         if (item == Items.potion && this.targetAggregator4 != null && this.targetAggregator4.getCount() > 0) {
            boolean flag = (this.targetAggregator4.resolveRotationMonitor(0).getCount() & 16384) != 0;
            String text = this.get(this.map2, "texture.potion_overlay", "items/potion_overlay");
            String text2;
            if (flag) {
               text2 = this.get(this.map2, "texture.potion_bottle_splash", "items/potion_bottle_splash");
            } else {
               text2 = this.get(this.map2, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
            }

            return new String[]{text, text2};
         }

         if (item instanceof ItemGoldenBoots) {
            ItemGoldenBoots itemGoldenBoots = (ItemGoldenBoots)item;
            if (itemGoldenBoots.getCrafterLeatherType() == CrafterLeatherType.LEATHER) {
               String text3 = "leather";
               String text4 = "helmet";
               if (itemGoldenBoots.indexOffset3 == 0) {
                  text4 = "helmet";
               }

               if (itemGoldenBoots.indexOffset3 == 1) {
                  text4 = "chestplate";
               }

               if (itemGoldenBoots.indexOffset3 == 2) {
                  text4 = "leggings";
               }

               if (itemGoldenBoots.indexOffset3 == 3) {
                  text4 = "boots";
               }

               String text5 = text3 + "_" + text4;
               String text6 = this.get(this.map2, "texture." + text5, "items/" + text5);
               String text7 = this.get(this.map2, "texture." + text5 + "_overlay", "items/" + text5 + "_overlay");
               return new String[]{text6, text7};
            }
         }
      }

      return new String[]{this.labelText3};
   }

   public ItemManager(Properties properties, String text) {
      this.labelText = resolveTextByText(text);
      this.labelText2 = resolveText(text);
      this.count10 = this.computeCount(properties.getProperty("type"));
      this.countArray = this.resolveIndexArray(properties.getProperty("items"), properties.getProperty("matchItems"));
      this.map = resolveMap2(properties, this.labelText2);
      this.labelText4 = resolveText(properties.getProperty("model"), text, this.labelText2, this.count10, this.map);
      this.map2 = resolveMap(properties, this.labelText2);
      boolean flag = this.map == null && this.labelText4 == null;
      this.labelText3 = resolveText(
         properties.getProperty("texture"), properties.getProperty("tile"), properties.getProperty("source"), text, this.labelText2, this.count10, this.map2, flag
      );
      String property = properties.getProperty("damage");
      if (property != null) {
         this.enabled = property.contains("%");
         String text2 = property.replace("%", "");
         this.targetAggregator4 = this.resolveTargetAggregator(text2);
         this.count9 = this.computeCount(properties.getProperty("damageMask"), 0);
      }

      this.targetAggregator2 = this.resolveTargetAggregator(properties.getProperty("stackSize"));
      this.targetAggregator = this.resolveTargetAggregator(properties.getProperty("enchantmentIDs"), new TargetService());
      this.targetAggregator3 = this.resolveTargetAggregator(properties.getProperty("enchantmentLevels"));
      this.geometryObserverArray = this.resolveGeometryObserverArray(properties);
      this.count15 = this.computeCountByText(properties.getProperty("hand"));
      this.count13 = FovSorter.computeCount(properties.getProperty("blend"));
      this.factor2 = this.computeFactor(properties.getProperty("speed"), 0.0F);
      this.factor3 = this.computeFactor(properties.getProperty("rotation"), 0.0F);
      this.count = this.computeCount(properties.getProperty("layer"), 0);
      this.count8 = this.computeCount(properties.getProperty("weight"), 0);
      this.factor = this.computeFactor(properties.getProperty("duration"), 1.0F);
   }

   @Override
   public String toString() {
      return ""
         + this.labelText2
         + "/"
         + this.labelText
         + ", type: "
         + this.count10
         + ", items: ["
         + BuildManager.resolveText(this.countArray)
         + "], textture: "
         + this.labelText3;
   }
}
