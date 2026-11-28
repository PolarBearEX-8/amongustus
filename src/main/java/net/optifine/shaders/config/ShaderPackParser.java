package net.optifine.shaders.config;

import java.util.Comparator;
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
import net.optifine.shaders.SMCLog;
import net.optifine.expr.IExpressionFloatArray;
import net.optifine.shaders.uniform.ShaderExpressionResolver;
import net.optifine.render.GlAlphaState;
import net.optifine.expr.ExpressionFloatArrayCached;
import net.optifine.expr.ExpressionFloatCached;
import net.optifine.config.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Program;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.uniform.CustomUniform;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.UniformType;
import net.optifine.util.StrUtils;

public class ShaderPackParser {
   private static final Pattern PATTERN_VERSION = Pattern.compile("^\\s*#version\\s+.*$");
   private static final Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
   private static final Set setConstNames = makeSetConstNames();
   private static final Map mapAlphaFuncs = makeMapAlphaFuncs();
   private static final Map mapBlendFactors = makeMapBlendFactors();

   public static ShaderOption[] parseShaderPackOptions(IShaderPack shaderPack, String[] strings, List list) {
      if (shaderPack == null) {
         return new ShaderOption[0];
      } else {
         HashMap map = new HashMap();
         collectShaderOptions(shaderPack, "/shaders", strings, map);

         for (int n : (Iterable<Integer>)(list)) {
            collectShaderOptions(shaderPack, "/shaders/world" + n, strings, map);
         }

         Collection values = map.values();
         ShaderOption[] shaderOptions = (ShaderOption[])values.toArray(new ShaderOption[values.size()]);
         Arrays.sort(shaderOptions, new ShaderPackParser.EntryComparator());
         return shaderOptions;
      }
   }

   private static void collectShaderOptions(IShaderPack shaderPack, String str, String[] strings, Map map) {
      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         if (!str2.equals("")) {
            String str3 = str + "/" + str2 + ".vsh";
            String str4 = str + "/" + str2 + ".fsh";
            collectShaderOptions(shaderPack, str3, map);
            collectShaderOptions(shaderPack, str4, map);
         }
      }
   }

   public static CustomUniforms parseCustomUniforms(Properties properties) {
      String str = "uniform";
      String str2 = "variable";
      String str3 = str + ".";
      String str4 = str2 + ".";
      HashMap map = new HashMap();
      ArrayList list = new ArrayList();

      for (Object obj : properties.keySet()) {
         String str5 = (String)obj;
         String[] strings = Config.tokenize(str5, ".");
         if (strings.length == 3) {
            String str6 = strings[0];
            String str7 = strings[1];
            String str8 = strings[2];
            String str9 = properties.getProperty(str5).trim();
            if (map.containsKey(str8)) {
               SMCLog.warning("Expression already defined: " + str8);
            } else if (str6.equals(str) || str6.equals(str2)) {
               SMCLog.info("Custom " + str6 + ": " + str8);
               CustomUniform customUniform = parseCustomUniform(str6, str8, str7, str9, map);
               if (customUniform != null) {
                  map.put(str8, customUniform.getExpression());
                  if (!str6.equals(str2)) {
                     list.add(customUniform);
                  }
               }
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         CustomUniform[] customUniforms = (CustomUniform[])list.toArray(new CustomUniform[list.size()]);
         return new CustomUniforms(customUniforms, map);
      }
   }

   private static ShaderProfile parseProfile(String str, Properties properties, Set set, ShaderOption[] shaderOptions) {
      String str2 = "profile.";
      String str3 = str2 + str;
      if (set.contains(str3)) {
         Config.warn("[Shaders] Profile already parsed: " + str);
         return null;
      } else {
         set.add(str);
         ShaderProfile shaderProfile = new ShaderProfile(str);
         String[] strings = Config.tokenize(properties.getProperty(str3), " ");

         for (int i = 0; i < strings.length; i++) {
            String str4 = strings[i];
            if (str4.startsWith(str2)) {
               ShaderProfile shaderProfile2 = parseProfile(str4.substring(str2.length()), properties, set, shaderOptions);
               if (shaderProfile != null) {
                  shaderProfile.addOptionValues(shaderProfile2);
                  shaderProfile.addDisabledPrograms(shaderProfile2.getDisabledPrograms());
               }
            } else {
               String[] strings2 = Config.tokenize(str4, ":=");
               if (strings2.length == 1) {
                  String str6 = strings2[0];
                  boolean flag = true;
                  if (str6.startsWith("!")) {
                     flag = false;
                     str6 = str6.substring(1);
                  }

                  String str7 = "program.";
                  if (str6.startsWith(str7)) {
                     String str8 = str6.substring(str7.length());
                     if (!Shaders.isProgramPath(str8)) {
                        Config.warn("Invalid program: " + str8 + " in profile: " + shaderProfile.getName());
                     } else if (flag) {
                        shaderProfile.removeDisabledProgram(str8);
                     } else {
                        shaderProfile.addDisabledProgram(str8);
                     }
                  } else {
                     ShaderOption shaderOption = ShaderUtils.getShaderOption(str6, shaderOptions);
                     if (!(shaderOption instanceof ShaderOptionSwitch)) {
                        Config.warn("[Shaders] Invalid option: " + str6);
                     } else {
                        shaderProfile.addOptionValue(str6, String.valueOf(flag));
                        shaderOption.setVisible(true);
                     }
                  }
               } else if (strings2.length != 2) {
                  Config.warn("[Shaders] Invalid option value: " + str4);
               } else {
                  String str9 = strings2[0];
                  String str10 = strings2[1];
                  ShaderOption shaderOption2 = ShaderUtils.getShaderOption(str9, shaderOptions);
                  if (shaderOption2 == null) {
                     Config.warn("[Shaders] Invalid option: " + str4);
                  } else if (!shaderOption2.isValidValue(str10)) {
                     Config.warn("[Shaders] Invalid value: " + str4);
                  } else {
                     shaderOption2.setVisible(true);
                     shaderProfile.addOptionValue(str9, str10);
                  }
               }
            }
         }

         return shaderProfile;
      }
   }

   private static Set makeSetConstNames() {
      HashSet set = new HashSet();
      set.add("shadowMapResolution");
      set.add("shadowMapFov");
      set.add("shadowDistance");
      set.add("shadowDistanceRenderMul");
      set.add("shadowIntervalSize");
      set.add("generateShadowMipmap");
      set.add("generateShadowColorMipmap");
      set.add("shadowHardwareFiltering");
      set.add("shadowHardwareFiltering0");
      set.add("shadowHardwareFiltering1");
      set.add("shadowtex0Mipmap");
      set.add("shadowtexMipmap");
      set.add("shadowtex1Mipmap");
      set.add("shadowcolor0Mipmap");
      set.add("shadowColor0Mipmap");
      set.add("shadowcolor1Mipmap");
      set.add("shadowColor1Mipmap");
      set.add("shadowtex0Nearest");
      set.add("shadowtexNearest");
      set.add("shadow0MinMagNearest");
      set.add("shadowtex1Nearest");
      set.add("shadow1MinMagNearest");
      set.add("shadowcolor0Nearest");
      set.add("shadowColor0Nearest");
      set.add("shadowColor0MinMagNearest");
      set.add("shadowcolor1Nearest");
      set.add("shadowColor1Nearest");
      set.add("shadowColor1MinMagNearest");
      set.add("wetnessHalflife");
      set.add("drynessHalflife");
      set.add("eyeBrightnessHalflife");
      set.add("centerDepthHalflife");
      set.add("sunPathRotation");
      set.add("ambientOcclusionLevel");
      set.add("superSamplingLevel");
      set.add("noiseTextureResolution");
      return set;
   }

   public static void parseBuffersFlip(Properties properties) {
      for (Object obj : properties.keySet()) {
         String str = (String)obj;
         String[] strings = Config.tokenize(str, ".");
         if (strings.length == 3) {
            String str2 = strings[0];
            String str3 = strings[1];
            String str4 = strings[2];
            if (str2.equals("flip")) {
               Program program = Shaders.getProgram(str3);
               if (program == null) {
                  SMCLog.severe("Invalid program name: " + str3);
               } else {
                  Boolean[] buffersFlip = program.getBuffersFlip();
                  int bufferIndexFromString = Shaders.getBufferIndexFromString(str4);
                  if (bufferIndexFromString >= 0 && bufferIndexFromString < buffersFlip.length) {
                     String str5 = properties.getProperty(str).trim();
                     Boolean bool = Config.parseBoolean(str5, (Boolean)null);
                     if (bool == null) {
                        SMCLog.severe("Invalid boolean value: " + str5);
                     } else {
                        buffersFlip[bufferIndexFromString] = bool;
                     }
                  } else {
                     SMCLog.severe("Invalid buffer name: " + str4);
                  }
               }
            }
         }
      }
   }

   public static Set parseOptionSliders(Properties properties, ShaderOption[] shaderOptions) {
      HashSet set = new HashSet();
      String property = properties.getProperty("sliders");
      if (property == null) {
         return set;
      } else {
         String[] strings = Config.tokenize(property, " ");

         for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            if (ShaderUtils.getShaderOption(str, shaderOptions) == null) {
               Config.warn("Invalid shader option: " + str);
            } else {
               set.add(str);
            }
         }

         return set;
      }
   }

   private static GlBlendState parseBlendState(String str) {
      String[] strings = Config.tokenize(str, " ");
      if (strings.length == 1) {
         String str2 = strings[0];
         if (str2.equals("off") || str2.equals("false")) {
            return new GlBlendState(false);
         }
      } else if (strings.length == 2 || strings.length == 4) {
         String str3 = strings[0];
         String str4 = strings[1];
         String str5 = str3;
         String str6 = str4;
         if (strings.length == 4) {
            str5 = strings[2];
            str6 = strings[3];
         }

         Integer num = (Integer)mapBlendFactors.get(str3);
         Integer num2 = (Integer)mapBlendFactors.get(str4);
         Integer num3 = (Integer)mapBlendFactors.get(str5);
         Integer num4 = (Integer)mapBlendFactors.get(str6);
         if (num != null && num2 != null && num3 != null && num4 != null) {
            return new GlBlendState(true, num, num2, num3, num4);
         }
      }

      SMCLog.severe("Invalid blend mode: " + str);
      return null;
   }

   private static boolean isOptionUsed(ShaderOption shaderOption, String[] strings) {
      for (int i = 0; i < strings.length; i++) {
         if (shaderOption.isUsedInLine(strings[i])) {
            return true;
         }
      }

      return false;
   }

   private static GlAlphaState parseAlphaState(String str) {
      String[] strings = Config.tokenize(str, " ");
      if (strings.length == 1) {
         String str2 = strings[0];
         if (str2.equals("off") || str2.equals("false")) {
            return new GlAlphaState(false);
         }
      } else if (strings.length == 2) {
         String str3 = strings[0];
         String str4 = strings[1];
         Integer num = (Integer)mapAlphaFuncs.get(str3);
         float f = Config.parseFloat(str4, -1.0F);
         if (num != null && f >= 0.0F) {
            return new GlAlphaState(true, num, f);
         }
      }

      SMCLog.severe("Invalid alpha test: " + str);
      return null;
   }

   private static Map makeMapBlendFactors() {
      HashMap map = new HashMap();
      map.put("ZERO", new Integer(0));
      map.put("ONE", new Integer(1));
      map.put("SRC_COLOR", new Integer(768));
      map.put("ONE_MINUS_SRC_COLOR", new Integer(769));
      map.put("DST_COLOR", new Integer(774));
      map.put("ONE_MINUS_DST_COLOR", new Integer(775));
      map.put("SRC_ALPHA", new Integer(770));
      map.put("ONE_MINUS_SRC_ALPHA", new Integer(771));
      map.put("DST_ALPHA", new Integer(772));
      map.put("ONE_MINUS_DST_ALPHA", new Integer(773));
      map.put("CONSTANT_COLOR", new Integer(32769));
      map.put("ONE_MINUS_CONSTANT_COLOR", new Integer(32770));
      map.put("CONSTANT_ALPHA", new Integer(32771));
      map.put("ONE_MINUS_CONSTANT_ALPHA", new Integer(32772));
      map.put("SRC_ALPHA_SATURATE", new Integer(776));
      return Collections.unmodifiableMap(map);
   }

   private static ShaderOption getShaderOption(String str, String str2) {
      ShaderOption shaderOption = ShaderOptionSwitch.parseOption(str, str2);
      if (shaderOption == null) {
         shaderOption = ShaderOptionVariable.parseOption(str, str2);
      }

      if (shaderOption != null) {
         return shaderOption;
      } else {
         shaderOption = ShaderOptionSwitchConst.parseOption2(str, str2);
         if (shaderOption == null) {
            shaderOption = ShaderOptionVariableConst.parseOption2(str, str2);
         }

         return shaderOption != null && setConstNames.contains(shaderOption.getName()) ? shaderOption : null;
      }
   }

   public static void parseRenderScales(Properties properties) {
      for (Object obj : properties.keySet()) {
         String str = (String)obj;
         String[] strings = Config.tokenize(str, ".");
         if (strings.length == 2) {
            String str2 = strings[0];
            String str3 = strings[1];
            if (str2.equals("scale")) {
               Program program = Shaders.getProgram(str3);
               if (program == null) {
                  SMCLog.severe("Invalid program name: " + str3);
               } else {
                  RenderScale renderScale = parseRenderScale(properties.getProperty(str).trim());
                  if (renderScale != null) {
                     program.setRenderScale(renderScale);
                  }
               }
            }
         }
      }
   }

   private static ShaderMacro[] getMatchingMacros(String str, ShaderMacro[] shaderMacros) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < shaderMacros.length; i++) {
         ShaderMacro shaderMacro = shaderMacros[i];
         if (str.contains(shaderMacro.getName())) {
            list.add(shaderMacro);
         }
      }

      return (ShaderMacro[])list.toArray(new ShaderMacro[list.size()]);
   }

   private static String loadFile(String str, IShaderPack shaderPack, int n, List list, int n2) throws IOException {
      if (n2 >= 10) {
         throw new IOException("#include depth exceeded: " + n2 + ", file: " + str);
      } else {
         n2++;
         InputStream resourceAsStream = shaderPack.getResourceAsStream(str);
         if (resourceAsStream == null) {
            return null;
         } else {
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "ASCII");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            reader = resolveIncludes(reader, str, shaderPack, n, list, n2);
            CharArrayWriter charArrayWriter = new CharArrayWriter();

            while (true) {
               String str2 = reader.readLine();
               if (str2 == null) {
                  return charArrayWriter.toString();
               }

               charArrayWriter.write(str2);
               charArrayWriter.write("\n");
            }
         }
      }
   }

   private static String[] getLines(IShaderPack shaderPack, String str) {
      try {
         String str2 = loadFile(str, shaderPack, 0, new ArrayList(), 0);
         if (str2 == null) {
            return new String[0];
         } else {
            return Config.readLines(new ByteArrayInputStream(str2.getBytes()));
         }
      } catch (IOException e) {
         Config.dbg(e.getClass().getName() + ": " + e.getMessage());
         return new String[0];
      }
   }

   public static void parseBlendStates(Properties properties) {
      for (Object obj : properties.keySet()) {
         String str = (String)obj;
         String[] strings = Config.tokenize(str, ".");
         if (strings.length == 2) {
            String str2 = strings[0];
            String str3 = strings[1];
            if (str2.equals("blend")) {
               Program program = Shaders.getProgram(str3);
               if (program == null) {
                  SMCLog.severe("Invalid program name: " + str3);
               } else {
                  GlBlendState glBlendState = parseBlendState(properties.getProperty(str).trim());
                  if (glBlendState != null) {
                     program.setBlendState(glBlendState);
                  }
               }
            }
         }
      }
   }

   public static Map parseGuiScreens(Properties properties, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
      HashMap map = new HashMap();
      parseGuiScreen("screen", properties, map, shaderProfiles, shaderOptions);
      return map.isEmpty() ? null : map;
   }

   private static IExpression makeCached(IExpression expression) {
      return (IExpression)(expression instanceof IExpressionFloat
         ? new ExpressionFloatCached((IExpressionFloat)expression)
         : (expression instanceof IExpressionFloatArray ? new ExpressionFloatArrayCached((IExpressionFloatArray)expression) : expression));
   }

   public static ShaderProfile[] parseProfiles(Properties properties, ShaderOption[] shaderOptions) {
      String str = "profile.";
      ArrayList list = new ArrayList();

      for (Object obj : properties.keySet()) {
         String str2 = (String)obj;
         if (str2.startsWith(str)) {
            String str3 = str2.substring(str.length());
            properties.getProperty(str2);
            ShaderProfile shaderProfile = parseProfile(str3, properties, new HashSet(), shaderOptions);
            if (shaderProfile != null) {
               list.add(shaderProfile);
            }
         }
      }

      return list.size() <= 0 ? null : (ShaderProfile[])list.toArray(new ShaderProfile[list.size()]);
   }

   public static Map parseProgramConditions(Properties properties, ShaderOption[] shaderOptions) {
      String str = "program.";
      Pattern pattern = Pattern.compile("program\\.([^.]+)\\.enabled");
      HashMap map = new HashMap();

      for (Object obj : properties.keySet()) {
         String str2 = (String)obj;
         Matcher matcher = pattern.matcher(str2);
         if (matcher.matches()) {
            String str3 = matcher.group(1);
            IExpressionBool expressionBool = parseProgramCondition(properties.getProperty(str2).trim(), shaderOptions);
            if (expressionBool == null) {
               SMCLog.severe("Error parsing program condition: " + str2);
            } else {
               map.put(str3, expressionBool);
            }
         }
      }

      return map;
   }

   private static boolean parseGuiScreen(String str, Properties properties, Map map, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
      String property = properties.getProperty(str);
      if (property == null) {
         return false;
      } else {
         ArrayList list = new ArrayList();
         HashSet set = new HashSet();
         String[] strings = Config.tokenize(property, " ");

         for (int i = 0; i < strings.length; i++) {
            String str2 = strings[i];
            if (str2.equals("<empty>")) {
               list.add((ShaderOption)null);
            } else if (set.contains(str2)) {
               Config.warn("[Shaders] Duplicate option: " + str2 + ", key: " + str);
            } else {
               set.add(str2);
               if (str2.equals("<profile>")) {
                  if (shaderProfiles == null) {
                     Config.warn("[Shaders] Option profile can not be used, no profiles defined: " + str2 + ", key: " + str);
                  } else {
                     list.add(new ShaderOptionProfile(shaderProfiles, shaderOptions));
                  }
               } else if (str2.equals("*")) {
                  list.add(new ShaderOptionRest("<rest>"));
               } else if (str2.startsWith("[") && str2.endsWith("]")) {
                  String str3 = StrUtils.removePrefixSuffix(str2, "[", "]");
                  if (!str3.matches("^[a-zA-Z0-9_]+$")) {
                     Config.warn("[Shaders] Invalid screen: " + str2 + ", key: " + str);
                  } else if (!parseGuiScreen("screen." + str3, properties, map, shaderProfiles, shaderOptions)) {
                     Config.warn("[Shaders] Invalid screen: " + str2 + ", key: " + str);
                  } else {
                     list.add(new ShaderOptionScreen(str3));
                  }
               } else {
                  ShaderOption shaderOption = ShaderUtils.getShaderOption(str2, shaderOptions);
                  if (shaderOption == null) {
                     Config.warn("[Shaders] Invalid option: " + str2 + ", key: " + str);
                     list.add((ShaderOption)null);
                  } else {
                     shaderOption.setVisible(true);
                     list.add(shaderOption);
                  }
               }
            }
         }

         ShaderOption[] shaderOptions2 = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
         int n = Config.parseInt(properties.getProperty(str + ".columns"), 2);
         map.put(str, new ScreenShaderOptions(str, shaderOptions2, n));
         return true;
      }
   }

   private static RenderScale parseRenderScale(String str) {
      String[] strings = Config.tokenize(str, " ");
      float f = Config.parseFloat(strings[0], -1.0F);
      float f2 = 0.0F;
      float f3 = 0.0F;
      if (strings.length > 1) {
         if (strings.length != 3) {
            SMCLog.severe("Invalid render scale: " + str);
            return null;
         }

         f2 = Config.parseFloat(strings[1], -1.0F);
         f3 = Config.parseFloat(strings[2], -1.0F);
      }

      if (Config.between2(f, 0.0F, 1.0F) && Config.between2(f2, 0.0F, 1.0F) && Config.between2(f3, 0.0F, 1.0F)) {
         return new RenderScale(f, f2, f3);
      } else {
         SMCLog.severe("Invalid render scale: " + str);
         return null;
      }
   }

   private static void collectShaderOptions(IShaderPack shaderPack, String str, Map map) {
      String[] lines = getLines(shaderPack, str);

      for (int i = 0; i < lines.length; i++) {
         lines[i] = lines[i].trim();
      }

      for (int j = 0; j < lines.length; j++) {
         ShaderOption shaderOption = getShaderOption(lines[j], str);
         if (shaderOption != null && !shaderOption.getName().startsWith(ShaderMacros.getPrefixMacro()) && (!shaderOption.checkUsed() || isOptionUsed(shaderOption, lines))) {
            String name = shaderOption.getName();
            ShaderOption shaderOption2 = (ShaderOption)map.get(name);
            if (shaderOption2 != null) {
               if (!Config.equals(shaderOption2.getValueDefault(), shaderOption.getValueDefault())) {
                  Config.warn("Ambiguous shader option: " + shaderOption.getName());
                  Config.warn(" - in " + Config.arrayToString3((Object[])shaderOption2.getPaths()) + ": " + shaderOption2.getValueDefault());
                  Config.warn(" - in " + Config.arrayToString3((Object[])shaderOption.getPaths()) + ": " + shaderOption.getValueDefault());
                  shaderOption2.setEnabled(false);
               }

               if (shaderOption2.getDescription() == null || shaderOption2.getDescription().length() <= 0) {
                  shaderOption2.setDescription(shaderOption.getDescription());
               }

               shaderOption2.addPaths(shaderOption.getPaths());
            } else {
               map.put(name, shaderOption);
            }
         }
      }
   }

   private static IExpressionBool parseProgramCondition(String str, ShaderOption[] shaderOptions) {
      try {
         ShaderOptionResolver shaderOptionResolver = new ShaderOptionResolver(shaderOptions);
         return new ExpressionParser(shaderOptionResolver).parseBool(str);
      } catch (ParseException e) {
         SMCLog.warning(e.getClass().getName() + ": " + e.getMessage());
         return null;
      }
   }

   private static CustomUniform parseCustomUniform(String str, String str2, String str3, String str4, Map map) {
      try {
         UniformType uniformType = UniformType.parse(str3);
         if (uniformType == null) {
            SMCLog.warning("Unknown " + str + " type: " + uniformType);
            return null;
         } else {
            ShaderExpressionResolver shaderExpressionResolver = new ShaderExpressionResolver(map);
            IExpression expression = new ExpressionParser(shaderExpressionResolver).parse(str4);
            ExpressionType floatValue = expression.getFloatValue();
            if (!uniformType.matchesExpressionType(floatValue)) {
               SMCLog.warning("Expression type does not match " + str + " type, expression: " + floatValue + ", " + str + ": " + uniformType + " " + str2);
               return null;
            } else {
               expression = makeCached(expression);
               return new CustomUniform(str2, uniformType, expression);
            }
         }
      } catch (ParseException e) {
         SMCLog.warning(e.getClass().getName() + ": " + e.getMessage());
         return null;
      }
   }

   public static BufferedReader resolveIncludes(BufferedReader reader, String str, IShaderPack shaderPack, int n, List list, int n2) throws IOException {
      String str2 = "/";
      int n3 = str.lastIndexOf("/");
      if (n3 >= 0) {
         str2 = str.substring(0, n3);
      }

      CharArrayWriter charArrayWriter = new CharArrayWriter();
      int n4 = -1;
      LinkedHashSet set = new LinkedHashSet();
      int n5 = 1;

      while (true) {
         String str3 = reader.readLine();
         if (str3 == null) {
            char[] chars = charArrayWriter.toCharArray();
            if (n4 >= 0 && set.size() > 0) {
               StringBuilder sb = new StringBuilder();

               for (ShaderMacro shaderMacro : (Iterable<ShaderMacro>)(set)) {
                  sb.append("#define ");
                  sb.append(shaderMacro.getName());
                  sb.append(" ");
                  sb.append(shaderMacro.getValue());
                  sb.append("\n");
               }

               String str4 = sb.toString();
               StringBuilder sb2 = new StringBuilder(new String(chars));
               sb2.insert(n4, str4);
               chars = sb2.toString().toCharArray();
            }

            CharArrayReader charArrayReader = new CharArrayReader(chars);
            return new BufferedReader(charArrayReader);
         }

         if (n4 < 0) {
            if (PATTERN_VERSION.matcher(str3).matches()) {
               String str6 = ShaderMacros.getFixedMacroLines() + ShaderMacros.getOptionMacroLines();
               String str7 = str3 + "\n" + str6;
               str3 = str7 + ("#line " + (n5 + 1) + " " + n);
               n4 = charArrayWriter.size() + str7.length();
            }
         }

         Matcher matcher2 = PATTERN_INCLUDE.matcher(str3);
         if (matcher2.matches()) {
            String str9 = matcher2.group(1);
            String str10 = str9.startsWith("/") ? "/shaders" + str9 : str2 + "/" + str9;
            if (!list.contains(str10)) {
               list.add(str10);
            }

            int n6 = list.indexOf(str10) + 1;
            str3 = loadFile(str10, shaderPack, n6, list, n2);
            if (str3 == null) {
               throw new IOException("Included file not found: " + str);
            }

            if (str3.endsWith("\n")) {
               str3 = str3.substring(0, str3.length() - 1);
            }

            String str11 = "#line 1 " + n6 + "\n";
            if (str3.startsWith("#version ")) {
               str11 = "";
            }

            str3 = str11 + str3 + "\n#line " + (n5 + 1) + " " + n;
         }

         if (n4 >= 0 && str3.contains(ShaderMacros.getPrefixMacro())) {
            ShaderMacro[] matchingMacros = getMatchingMacros(str3, ShaderMacros.getExtensions());

            for (int i = 0; i < matchingMacros.length; i++) {
               set.add(matchingMacros[i]);
            }
         }

         charArrayWriter.write(str3);
         charArrayWriter.write("\n");
         n5++;
      }
   }

   public static void parseAlphaStates(Properties properties) {
      for (Object obj : properties.keySet()) {
         String str = (String)obj;
         String[] strings = Config.tokenize(str, ".");
         if (strings.length == 2) {
            String str2 = strings[0];
            String str3 = strings[1];
            if (str2.equals("alphaTest")) {
               Program program = Shaders.getProgram(str3);
               if (program == null) {
                  SMCLog.severe("Invalid program name: " + str3);
               } else {
                  GlAlphaState glAlphaState = parseAlphaState(properties.getProperty(str).trim());
                  if (glAlphaState != null) {
                     program.setAlphaState(glAlphaState);
                  }
               }
            }
         }
      }
   }

   private static Map makeMapAlphaFuncs() {
      HashMap map = new HashMap();
      map.put("NEVER", new Integer(512));
      map.put("LESS", new Integer(513));
      map.put("EQUAL", new Integer(514));
      map.put("LEQUAL", new Integer(515));
      map.put("GREATER", new Integer(516));
      map.put("NOTEQUAL", new Integer(517));
      map.put("GEQUAL", new Integer(518));
      map.put("ALWAYS", new Integer(519));
      return Collections.unmodifiableMap(map);
   }


   public static final class EntryComparator implements Comparator {
      @Override
      public int compare(Object a, Object b) {
         return this.compare2((ShaderOption)a, (ShaderOption)b);
      }

      public int compare2(ShaderOption shaderOption, ShaderOption shaderOption2) {
         return shaderOption.getName().compareToIgnoreCase(shaderOption2.getName());
      }
   }
}
