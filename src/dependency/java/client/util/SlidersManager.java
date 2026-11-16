package client.util;

import client.event.PreUpdateFilter;
import client.event.PreUpdateObserver;
import client.render.ShadersManager;
import client.setting.OptionManager;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.GlAlphaState;

public class SlidersManager {
   public static final Pattern pattern2 = Pattern.compile("^\\s*#version\\s+.*$");
   public static final Pattern pattern = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
   public static final Set set = getSet();
   public static final Map map = getMapAsMap();
   public static final Map map2 = getMap();

   public static Set resolveSet(Properties properties, OptionManager[] optionManagerArray) {
      HashSet set2 = new HashSet();
      String property = properties.getProperty("sliders");
      if (property == null) {
         return set2;
      } else {
         String[] textArray = BuildManager.resolveTextArray(property, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text = textArray[index];
            if (InterpolationPredictor.resolveOptionManager(text, optionManagerArray) == null) {
               BuildManager.applyText3("Invalid shader option: " + text);
            } else {
               set2.add(text);
            }
         }

         return set2;
      }
   }

   private static PreUpdateObserver resolvePreUpdateObserver(PreUpdateObserver preUpdateObserver) {
      return (PreUpdateObserver)(preUpdateObserver instanceof AimValidator
         ? new AngleEngine((AimValidator)preUpdateObserver)
         : (preUpdateObserver instanceof DistanceBridge ? new NativeCustom((DistanceBridge)preUpdateObserver) : preUpdateObserver));
   }

   private static boolean matchesText(String text, Properties properties, Map map3, GeometryCounter[] geometryCounterArray, OptionManager[] optionManagerArray) {
      String property = properties.getProperty(text);
      if (property == null) {
         return false;
      } else {
         ArrayList list = new ArrayList();
         HashSet set2 = new HashSet();
         String[] textArray = BuildManager.resolveTextArray(property, " ");

         for (int index = 0; index < textArray.length; index++) {
            String text2 = textArray[index];
            if (text2.equals("<empty>")) {
               list.add((OptionManager)null);
            } else if (set2.contains(text2)) {
               BuildManager.applyText3("[Shaders] Duplicate option: " + text2 + ", key: " + text);
            } else {
               set2.add(text2);
               if (text2.equals("<profile>")) {
                  if (geometryCounterArray == null) {
                     BuildManager.applyText3("[Shaders] Option profile can not be used, no profiles defined: " + text2 + ", key: " + text);
                  } else {
                     GeometryPipeline geometryPipeline = new GeometryPipeline(geometryCounterArray, optionManagerArray);
                     list.add(geometryPipeline);
                  }
               } else if (text2.equals("*")) {
                  GeometryAdapter geometryAdapter = new GeometryAdapter("<rest>");
                  list.add(geometryAdapter);
               } else if (text2.startsWith("[") && text2.endsWith("]")) {
                  String text3 = SplineCollector.resolveText3(text2, "[", "]");
                  if (!text3.matches("^[a-zA-Z0-9_]+$")) {
                     BuildManager.applyText3("[Shaders] Invalid screen: " + text2 + ", key: " + text);
                  } else if (!matchesText("screen." + text3, properties, map3, geometryCounterArray, optionManagerArray)) {
                     BuildManager.applyText3("[Shaders] Invalid screen: " + text2 + ", key: " + text);
                  } else {
                     GeometryBuffer geometryBuffer = new GeometryBuffer(text3);
                     list.add(geometryBuffer);
                  }
               } else {
                  OptionManager optionManager = InterpolationPredictor.resolveOptionManager(text2, optionManagerArray);
                  if (optionManager == null) {
                     BuildManager.applyText3("[Shaders] Invalid option: " + text2 + ", key: " + text);
                     list.add((OptionManager)null);
                  } else {
                     optionManager.setEnabled2(true);
                     list.add(optionManager);
                  }
               }
            }
         }

         OptionManager[] optionManagerArray2 = (OptionManager[])list.toArray(new OptionManager[list.size()]);
         int count = BuildManager.computeCount(properties.getProperty(text + ".columns"), 2);
         FovValidator fovValidator = new FovValidator(text, optionManagerArray2, count);
         map3.put(text, fovValidator);
         return true;
      }
   }

   public static Map resolveMap(Properties properties, GeometryCounter[] geometryCounterArray, OptionManager[] optionManagerArray) {
      HashMap map3 = new HashMap();
      matchesText("screen", properties, map3, geometryCounterArray, optionManagerArray);
      return map3.isEmpty() ? null : map3;
   }

   public static void applyPropertiesByProperties(Properties properties) {
      for (Object textObj : properties.keySet()) {
         String text = (String)textObj;
         String[] textArray = BuildManager.resolveTextArray(text, ".");
         if (textArray.length == 2) {
            String text2 = textArray[0];
            String text3 = textArray[1];
            if (text2.equals("scale")) {
               NoneManager noneManager = PreEndHandManager.resolveNoneManager(text3);
               if (noneManager == null) {
                  ShadersManager.applyTextByText("Invalid program name: " + text3);
               } else {
                  SplineMonitor splineMonitor = resolveSplineMonitor(properties.getProperty(text).trim());
                  if (splineMonitor != null) {
                     noneManager.setSplineMonitor(splineMonitor);
                  }
               }
            }
         }
      }
   }

   public static GeometryCounter[] resolveGeometryCounterArray(Properties properties, OptionManager[] optionManagerArray) {
      String text = "profile.";
      ArrayList list = new ArrayList();

      for (Object text2Obj : properties.keySet()) {
         String text2 = (String)text2Obj;
         if (text2.startsWith(text)) {
            String text3 = text2.substring(text.length());
            properties.getProperty(text2);
            HashSet set2 = new HashSet();
            GeometryCounter geometryCounter = resolveGeometryCounter(text3, properties, set2, optionManagerArray);
            if (geometryCounter != null) {
               list.add(geometryCounter);
            }
         }
      }

      return list.size() <= 0 ? null : (GeometryCounter[])list.toArray(new GeometryCounter[list.size()]);
   }

   private static Set getSet() {
      HashSet set2 = new HashSet();
      set2.add("shadowMapResolution");
      set2.add("shadowMapFov");
      set2.add("shadowDistance");
      set2.add("shadowDistanceRenderMul");
      set2.add("shadowIntervalSize");
      set2.add("generateShadowMipmap");
      set2.add("generateShadowColorMipmap");
      set2.add("shadowHardwareFiltering");
      set2.add("shadowHardwareFiltering0");
      set2.add("shadowHardwareFiltering1");
      set2.add("shadowtex0Mipmap");
      set2.add("shadowtexMipmap");
      set2.add("shadowtex1Mipmap");
      set2.add("shadowcolor0Mipmap");
      set2.add("shadowColor0Mipmap");
      set2.add("shadowcolor1Mipmap");
      set2.add("shadowColor1Mipmap");
      set2.add("shadowtex0Nearest");
      set2.add("shadowtexNearest");
      set2.add("shadow0MinMagNearest");
      set2.add("shadowtex1Nearest");
      set2.add("shadow1MinMagNearest");
      set2.add("shadowcolor0Nearest");
      set2.add("shadowColor0Nearest");
      set2.add("shadowColor0MinMagNearest");
      set2.add("shadowcolor1Nearest");
      set2.add("shadowColor1Nearest");
      set2.add("shadowColor1MinMagNearest");
      set2.add("wetnessHalflife");
      set2.add("drynessHalflife");
      set2.add("eyeBrightnessHalflife");
      set2.add("centerDepthHalflife");
      set2.add("sunPathRotation");
      set2.add("ambientOcclusionLevel");
      set2.add("superSamplingLevel");
      set2.add("noiseTextureResolution");
      return set2;
   }

   private static SplineMonitor resolveSplineMonitor(String text) {
      String[] textArray = BuildManager.resolveTextArray(text, " ");
      float factor = BuildManager.computeFactor(textArray[0], -1.0F);
      float factor2 = 0.0F;
      float factor3 = 0.0F;
      if (textArray.length > 1) {
         if (textArray.length != 3) {
            ShadersManager.applyTextByText("Invalid render scale: " + text);
            return null;
         }

         factor2 = BuildManager.computeFactor(textArray[1], -1.0F);
         factor3 = BuildManager.computeFactor(textArray[2], -1.0F);
      }

      if (BuildManager.checkFloat(factor, 0.0F, 1.0F) && BuildManager.checkFloat(factor2, 0.0F, 1.0F) && BuildManager.checkFloat(factor3, 0.0F, 1.0F)) {
         return new SplineMonitor(factor, factor2, factor3);
      } else {
         ShadersManager.applyTextByText("Invalid render scale: " + text);
         return null;
      }
   }

   private static FovFactory[] resolveFovFactoryArray(String text, FovFactory[] fovFactoryArray) {
      ArrayList list = new ArrayList();

      for (int index = 0; index < fovFactoryArray.length; index++) {
         FovFactory fovFactory = fovFactoryArray[index];
         if (text.contains(fovFactory.getLabelText())) {
            list.add(fovFactory);
         }
      }

      return (FovFactory[])list.toArray(new FovFactory[list.size()]);
   }

   public static void applyProperties(Properties properties) {
      for (Object textObj : properties.keySet()) {
         String text = (String)textObj;
         String[] textArray = BuildManager.resolveTextArray(text, ".");
         if (textArray.length == 3) {
            String text2 = textArray[0];
            String text3 = textArray[1];
            String text4 = textArray[2];
            if (text2.equals("flip")) {
               NoneManager noneManager = PreEndHandManager.resolveNoneManager(text3);
               if (noneManager == null) {
                  ShadersManager.applyTextByText("Invalid program name: " + text3);
               } else {
                  Boolean[] itemArray = noneManager.getItemArray();
                  int count = PreEndHandManager.computeCount(text4);
                  if (count >= 0 && count < itemArray.length) {
                     String text5 = properties.getProperty(text).trim();
                     Boolean flag = BuildManager.resolveBoolean(text5, (Boolean)null);
                     if (flag == null) {
                        ShadersManager.applyTextByText("Invalid boolean value: " + text5);
                     } else {
                        itemArray[count] = flag;
                     }
                  } else {
                     ShadersManager.applyTextByText("Invalid buffer name: " + text4);
                  }
               }
            }
         }
      }
   }

   private static Map getMap() {
      HashMap map3 = new HashMap();
      map3.put("ZERO", new Integer(0));
      map3.put("ONE", new Integer(1));
      map3.put("SRC_COLOR", new Integer(768));
      map3.put("ONE_MINUS_SRC_COLOR", new Integer(769));
      map3.put("DST_COLOR", new Integer(774));
      map3.put("ONE_MINUS_DST_COLOR", new Integer(775));
      map3.put("SRC_ALPHA", new Integer(770));
      map3.put("ONE_MINUS_SRC_ALPHA", new Integer(771));
      map3.put("DST_ALPHA", new Integer(772));
      map3.put("ONE_MINUS_DST_ALPHA", new Integer(773));
      map3.put("CONSTANT_COLOR", new Integer(32769));
      map3.put("ONE_MINUS_CONSTANT_COLOR", new Integer(32770));
      map3.put("CONSTANT_ALPHA", new Integer(32771));
      map3.put("ONE_MINUS_CONSTANT_ALPHA", new Integer(32772));
      map3.put("SRC_ALPHA_SATURATE", new Integer(776));
      return Collections.unmodifiableMap(map3);
   }

   private static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter, String text, String[] textArray, Map map3) {
      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         if (!text2.equals("")) {
            String text3 = text + "/" + text2 + ".vsh";
            String text4 = text + "/" + text2 + ".fsh";
            applyPreUpdateFilter(preUpdateFilter, text3, map3);
            applyPreUpdateFilter(preUpdateFilter, text4, map3);
         }
      }
   }

   private static EnabledManager resolveEnabledManager(String text) {
      String[] textArray = BuildManager.resolveTextArray(text, " ");
      if (textArray.length == 1) {
         String text2 = textArray[0];
         if (text2.equals("off") || text2.equals("false")) {
            return new EnabledManager(false);
         }
      } else if (textArray.length == 2 || textArray.length == 4) {
         String text3 = textArray[0];
         String text4 = textArray[1];
         String text5 = text3;
         String text6 = text4;
         if (textArray.length == 4) {
            text5 = textArray[2];
            text6 = textArray[3];
         }

         Integer count = (Integer)map2.get(text3);
         Integer count2 = (Integer)map2.get(text4);
         Integer count3 = (Integer)map2.get(text5);
         Integer count4 = (Integer)map2.get(text6);
         if (count != null && count2 != null && count3 != null && count4 != null) {
            return new EnabledManager(true, count, count2, count3, count4);
         }
      }

      ShadersManager.applyTextByText("Invalid blend mode: " + text);
      return null;
   }

   private static OptionManager resolveOptionManager(String text, String text2) {
      OptionManager optionManager = RayTraceDeserializer.resolveOptionManager(text, text2);
      if (optionManager == null) {
         optionManager = RayTraceChecker.resolveOptionManager(text, text2);
      }

      if (optionManager != null) {
         return optionManager;
      } else {
         OptionManager optionManager2 = GeometryStack.resolveOptionManagerByText(text, text2);
         if (optionManager2 == null) {
            optionManager2 = RayTraceConverter.resolveOptionManagerByText(text, text2);
         }

         return optionManager2 != null && set.contains(optionManager2.getLabelText()) ? optionManager2 : null;
      }
   }

   private static String resolveText(String text, PreUpdateFilter preUpdateFilter, int count, List list, int count2) throws IOException {
      if (count2 >= 10) {
         throw new IOException("#include depth exceeded: " + count2 + ", file: " + text);
      } else {
         int count3 = count2 + 1;
         InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text);
         if (resourceAsStream == null) {
            return null;
         } else {
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "ASCII");
            BufferedReader reader = resolveBufferedReader(new BufferedReader(inputStreamReader), text, preUpdateFilter, count, list, count3);
            CharArrayWriter charArrayWriter = new CharArrayWriter();

            while (true) {
               String line = reader.readLine();
               if (line == null) {
                  return charArrayWriter.toString();
               }

               charArrayWriter.write(line);
               charArrayWriter.write("\n");
            }
         }
      }
   }

   private static TargetObserver resolveTargetObserver(String text, OptionManager[] optionManagerArray) {
      try {
         AimWriter aimWriter = new AimWriter(optionManagerArray);
         return new TargetSorter(aimWriter).resolveTargetObserver(text);
      } catch (AuxiliaryExceptionAdapter exception) {
         ShadersManager.applyText3(exception.getClass().getName() + ": " + exception.getMessage());
         return null;
      }
   }

   public static FovObserver resolveFovObserver(Properties properties) {
      String text = "uniform";
      String text2 = "variable";
      new StringBuilder().append(text).append(".").toString();
      new StringBuilder().append(text2).append(".").toString();
      HashMap map3 = new HashMap();
      ArrayList list = new ArrayList();

      for (Object text3Obj : properties.keySet()) {
         String text3 = (String)text3Obj;
         String[] textArray = BuildManager.resolveTextArray(text3, ".");
         if (textArray.length == 3) {
            String text4 = textArray[0];
            String text5 = textArray[1];
            String text6 = textArray[2];
            String text7 = properties.getProperty(text3).trim();
            if (map3.containsKey(text6)) {
               ShadersManager.applyText3("Expression already defined: " + text6);
            } else if (text4.equals(text) || text4.equals(text2)) {
               ShadersManager.applyText("Custom " + text4 + ": " + text6);
               VectorQueue vectorQueue = resolveVectorQueue(text4, text6, text5, text7, map3);
               if (vectorQueue != null) {
                  map3.put(text6, vectorQueue.getPreUpdateObserver());
                  if (!text4.equals(text2)) {
                     list.add(vectorQueue);
                  }
               }
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         VectorQueue[] vectorQueueArray = (VectorQueue[])list.toArray(new VectorQueue[list.size()]);
         return new FovObserver(vectorQueueArray, map3);
      }
   }

   public static Map resolveMap(Properties properties, OptionManager[] optionManagerArray) {
      Pattern pattern3 = Pattern.compile("program\\.([^.]+)\\.enabled");
      HashMap map3 = new HashMap();

      for (Object textObj : properties.keySet()) {
         String text = (String)textObj;
         Matcher matcher = pattern3.matcher(text);
         if (matcher.matches()) {
            String text2 = matcher.group(1);
            TargetObserver targetObserver = resolveTargetObserver(properties.getProperty(text).trim(), optionManagerArray);
            if (targetObserver == null) {
               ShadersManager.applyTextByText("Error parsing program condition: " + text);
            } else {
               map3.put(text2, targetObserver);
            }
         }
      }

      return map3;
   }

   private static GeometryCounter resolveGeometryCounter(String text, Properties properties, Set set2, OptionManager[] optionManagerArray) {
      String text2 = "profile.";
      String text3 = text2 + text;
      if (set2.contains(text3)) {
         BuildManager.applyText3("[Shaders] Profile already parsed: " + text);
         return null;
      } else {
         set2.add(text);
         GeometryCounter geometryCounter = new GeometryCounter(text);
         String[] textArray = BuildManager.resolveTextArray(properties.getProperty(text3), " ");

         for (int index = 0; index < textArray.length; index++) {
            String text4 = textArray[index];
            if (text4.startsWith(text2)) {
               GeometryCounter geometryCounter2 = resolveGeometryCounter(text4.substring(text2.length()), properties, set2, optionManagerArray);
               geometryCounter.putAll(geometryCounter2);
               geometryCounter.addAll(geometryCounter2.getCollection());
            } else {
               String[] textArray2 = BuildManager.resolveTextArray(text4, ":=");
               if (textArray2.length == 1) {
                  String text5 = textArray2[0];
                  boolean flag = true;
                  if (text5.startsWith("!")) {
                     flag = false;
                     text5 = text5.substring(1);
                  }

                  String text6 = "program.";
                  if (text5.startsWith(text6)) {
                     String text7 = text5.substring(text6.length());
                     if (!PreEndHandManager.matchesText(text7)) {
                        BuildManager.applyText3("Invalid program: " + text7 + " in profile: " + geometryCounter.getLabelText());
                     } else if (flag) {
                        geometryCounter.remove(text7);
                     } else {
                        geometryCounter.add(text7);
                     }
                  } else {
                     OptionManager optionManager = InterpolationPredictor.resolveOptionManager(text5, optionManagerArray);
                     if (!(optionManager instanceof RayTraceDeserializer)) {
                        BuildManager.applyText3("[Shaders] Invalid option: " + text5);
                     } else {
                        geometryCounter.put(text5, String.valueOf(flag));
                        optionManager.setEnabled2(true);
                     }
                  }
               } else if (textArray2.length != 2) {
                  BuildManager.applyText3("[Shaders] Invalid option value: " + text4);
               } else {
                  String text8 = textArray2[0];
                  String text9 = textArray2[1];
                  OptionManager optionManager2 = InterpolationPredictor.resolveOptionManager(text8, optionManagerArray);
                  if (optionManager2 == null) {
                     BuildManager.applyText3("[Shaders] Invalid option: " + text4);
                  } else if (!optionManager2.matchesText2(text9)) {
                     BuildManager.applyText3("[Shaders] Invalid value: " + text4);
                  } else {
                     optionManager2.setEnabled2(true);
                     geometryCounter.put(text8, text9);
                  }
               }
            }
         }

         return geometryCounter;
      }
   }

   public static BufferedReader resolveBufferedReader(BufferedReader reader, String text, PreUpdateFilter preUpdateFilter, int count, List list, int count2) throws IOException {
      String text2 = "/";
      int count3 = text.lastIndexOf("/");
      if (count3 >= 0) {
         text2 = text.substring(0, count3);
      }

      CharArrayWriter charArrayWriter = new CharArrayWriter();
      int count4 = -1;
      LinkedHashSet set2 = new LinkedHashSet();
      int count5 = 1;

      while (true) {
         String line = reader.readLine();
         if (line == null) {
            char[] characterArray = charArrayWriter.toCharArray();
            if (count4 >= 0 && set2.size() > 0) {
               StringBuilder builder = new StringBuilder();

               for (Object fovFactoryObj : set2) {
                  FovFactory fovFactory = (FovFactory)fovFactoryObj;
                  builder.append("#define ");
                  builder.append(fovFactory.getLabelText());
                  builder.append(" ");
                  builder.append(fovFactory.getLabelText2());
                  builder.append("\n");
               }

               String text3 = builder.toString();
               StringBuilder builder2 = new StringBuilder(new String(characterArray));
               builder2.insert(count4, text3);
               characterArray = builder2.toString().toCharArray();
            }

            CharArrayReader charArrayReader = new CharArrayReader(characterArray);
            return new BufferedReader(charArrayReader);
         }

         if (count4 < 0 && pattern2.matcher(line).matches()) {
            String text4 = BezierEstimator.getText4() + BezierEstimator.getText();
            String text5 = line + "\n" + text4;
            String text6 = "#line " + (count5 + 1) + " " + count;
            line = text5 + text6;
            count4 = charArrayWriter.size() + text5.length();
         }

         Matcher matcher = pattern.matcher(line);
         if (matcher.matches()) {
            String text7 = matcher.group(1);
            String text8 = text7.startsWith("/") ? "/shaders" + text7 : text2 + "/" + text7;
            if (!list.contains(text8)) {
               list.add(text8);
            }

            int count6 = list.indexOf(text8) + 1;
            String text9 = resolveText(text8, preUpdateFilter, count6, list, count2);
            if (text9 == null) {
               throw new IOException("Included file not found: " + text);
            }

            if (text9.endsWith("\n")) {
               text9 = text9.substring(0, text9.length() - 1);
            }

            String text10 = "#line 1 " + count6 + "\n";
            if (text9.startsWith("#version ")) {
               text10 = "";
            }

            line = text10 + text9 + "\n#line " + (count5 + 1) + " " + count;
         }

         if (count4 >= 0 && line.contains(BezierEstimator.getLabelText())) {
            FovFactory[] fovFactoryArray = resolveFovFactoryArray(line, BezierEstimator.getFovFactoryArray());

            for (int index = 0; index < fovFactoryArray.length; index++) {
               FovFactory fovFactory2 = fovFactoryArray[index];
               set2.add(fovFactory2);
            }
         }

         charArrayWriter.write(line);
         charArrayWriter.write("\n");
         count5++;
      }
   }

   private static String[] resolveTextArray(PreUpdateFilter preUpdateFilter, String text) {
      try {
         ArrayList list = new ArrayList();
         String text2 = resolveText(text, preUpdateFilter, 0, list, 0);
         return text2 == null ? new String[0] : BuildManager.resolveTextArray(new ByteArrayInputStream(text2.getBytes()));
      } catch (IOException ioException) {
         BuildManager.applyText4(ioException.getClass().getName() + ": " + ioException.getMessage());
         return new String[0];
      }
   }

   private static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter, String text, Map map3) {
      String[] textArray = resolveTextArray(preUpdateFilter, text);

      for (int index = 0; index < textArray.length; index++) {
         textArray[index] = textArray[index].trim();
      }

      for (int index2 = 0; index2 < textArray.length; index2++) {
         OptionManager optionManager = resolveOptionManager(textArray[index2], text);
         if (optionManager != null && !optionManager.getLabelText().startsWith(BezierEstimator.getLabelText()) && (!optionManager.isActive() || matchesOptionManager(optionManager, textArray))) {
            String labelText = optionManager.getLabelText();
            OptionManager optionManager2 = (OptionManager)map3.get(labelText);
            if (optionManager2 != null) {
               if (!BuildManager.matchesTarget(optionManager2.getLabelText7(), optionManager.getLabelText7())) {
                  BuildManager.applyText3("Ambiguous shader option: " + optionManager.getLabelText());
                  BuildManager.applyText3(" - in " + BuildManager.resolveText((Object[])optionManager2.getStringArray2()) + ": " + optionManager2.getLabelText7());
                  BuildManager.applyText3(" - in " + BuildManager.resolveText((Object[])optionManager.getStringArray2()) + ": " + optionManager.getLabelText7());
                  optionManager2.setEnabled(false);
               }

               if (optionManager2.getLabelText3() == null || optionManager2.getLabelText3().length() <= 0) {
                  optionManager2.setLabelText3(optionManager.getLabelText3());
               }

               optionManager2.applyTextArray(optionManager.getStringArray2());
            } else {
               map3.put(labelText, optionManager);
            }
         }
      }
   }

   public static void applyProperties3(Properties properties) {
      for (Object textObj : properties.keySet()) {
         String text = (String)textObj;
         String[] textArray = BuildManager.resolveTextArray(text, ".");
         if (textArray.length == 2) {
            String text2 = textArray[0];
            String text3 = textArray[1];
            if (text2.equals("alphaTest")) {
               NoneManager noneManager = PreEndHandManager.resolveNoneManager(text3);
               if (noneManager == null) {
                  ShadersManager.applyTextByText("Invalid program name: " + text3);
               } else {
                  GlAlphaState glAlphaState = resolveGlAlphaState(properties.getProperty(text).trim());
                  if (glAlphaState != null) {
                     noneManager.setGlAlphaState(glAlphaState);
                  }
               }
            }
         }
      }
   }

   private static GlAlphaState resolveGlAlphaState(String text) {
      String[] textArray = BuildManager.resolveTextArray(text, " ");
      if (textArray.length == 1) {
         String text2 = textArray[0];
         if (text2.equals("off") || text2.equals("false")) {
            return new GlAlphaState(false);
         }
      } else if (textArray.length == 2) {
         String text3 = textArray[0];
         String text4 = textArray[1];
         Integer count = (Integer)map.get(text3);
         float factor = BuildManager.computeFactor(text4, -1.0F);
         if (count != null && factor >= 0.0F) {
            return new GlAlphaState(true, count, factor);
         }
      }

      ShadersManager.applyTextByText("Invalid alpha test: " + text);
      return null;
   }

   private static Map getMapAsMap() {
      HashMap map3 = new HashMap();
      map3.put("NEVER", new Integer(512));
      map3.put("LESS", new Integer(513));
      map3.put("EQUAL", new Integer(514));
      map3.put("LEQUAL", new Integer(515));
      map3.put("GREATER", new Integer(516));
      map3.put("NOTEQUAL", new Integer(517));
      map3.put("GEQUAL", new Integer(518));
      map3.put("ALWAYS", new Integer(519));
      return Collections.unmodifiableMap(map3);
   }

   public static void applyProperties2(Properties properties) {
      for (Object textObj : properties.keySet()) {
         String text = (String)textObj;
         String[] textArray = BuildManager.resolveTextArray(text, ".");
         if (textArray.length == 2) {
            String text2 = textArray[0];
            String text3 = textArray[1];
            if (text2.equals("blend")) {
               NoneManager noneManager = PreEndHandManager.resolveNoneManager(text3);
               if (noneManager == null) {
                  ShadersManager.applyTextByText("Invalid program name: " + text3);
               } else {
                  EnabledManager enabledManager = resolveEnabledManager(properties.getProperty(text).trim());
                  if (enabledManager != null) {
                     noneManager.setEnabledManager(enabledManager);
                  }
               }
            }
         }
      }
   }

   private static boolean matchesOptionManager(OptionManager optionManager, String[] textArray) {
      for (int index = 0; index < textArray.length; index++) {
         String text = textArray[index];
         if (optionManager.matchesTextByText(text)) {
            return true;
         }
      }

      return false;
   }

   public static OptionManager[] resolveOptionManagerArray(PreUpdateFilter preUpdateFilter, String[] textArray, List list) {
      if (preUpdateFilter == null) {
         return new OptionManager[0];
      } else {
         HashMap map3 = new HashMap();
         applyPreUpdateFilter(preUpdateFilter, "/shaders", textArray, map3);

         for (Object countObj : list) {
            int count = (Integer)countObj;
            String text = "/shaders/world" + count;
            applyPreUpdateFilter(preUpdateFilter, text, textArray, map3);
         }

         Collection values = map3.values();
         OptionManager[] optionManagerArray = (OptionManager[])values.toArray(new OptionManager[values.size()]);
         CommonComparatorAdapter commonComparatorAdapter = new CommonComparatorAdapter();
         Arrays.sort(optionManagerArray, commonComparatorAdapter);
         return optionManagerArray;
      }
   }

   private static VectorQueue resolveVectorQueue(String text, String text2, String text3, String text4, Map map3) {
      try {
         SmoothService smoothService = SmoothService.resolveSmoothService(text3);
         if (smoothService == null) {
            ShadersManager.applyText3("Unknown " + text + " type: " + smoothService);
            return null;
         } else {
            AngleDeserializer angleDeserializer = new AngleDeserializer(map3);
            PreUpdateObserver preUpdateObserver = new TargetSorter(angleDeserializer).resolvePreUpdateObserver(text4);
            AimSorter bOOL = preUpdateObserver.getBOOL();
            if (!smoothService.matchesAimSorter(bOOL)) {
               ShadersManager.applyText3("Expression type does not match " + text + " type, expression: " + bOOL + ", " + text + ": " + smoothService + " " + text2);
               return null;
            } else {
               PreUpdateObserver preUpdateObserver2 = resolvePreUpdateObserver(preUpdateObserver);
               return new VectorQueue(text2, smoothService, preUpdateObserver2);
            }
         }
      } catch (AuxiliaryExceptionAdapter exception) {
         ShadersManager.applyText3(exception.getClass().getName() + ": " + exception.getMessage());
         return null;
      }
   }
}
