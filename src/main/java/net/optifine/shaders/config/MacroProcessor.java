package net.optifine.shaders.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class MacroProcessor {

   public static String process(String str) throws IOException {
      StringReader stringReader = new StringReader(str);
      BufferedReader reader = new BufferedReader(stringReader);
      MacroState macroState = new MacroState();
      StringBuilder sb = new StringBuilder();

      while (true) {
         String str2 = reader.readLine();
         if (str2 == null) {
            return sb.toString();
         }

         if (macroState.processLine(str2) && !MacroState.isMacroLine(str2)) {
            sb.append(str2);
            sb.append("\n");
         }
      }
   }

   private static List getMacroOptions() {
      ArrayList list = new ArrayList();
      ShaderOption[] shaderPackOptions = Shaders.getShaderPackOptions();

      for (int i = 0; i < shaderPackOptions.length; i++) {
         ShaderOption shaderOption = shaderPackOptions[i];
         String sourceLine = shaderOption.getSourceLine();
         if (sourceLine != null && sourceLine.startsWith("#")) {
            list.add(shaderOption);
         }
      }

      return list;
   }

   private static String getMacroHeader(String str) throws IOException {
      StringBuilder sb = new StringBuilder();
      Object obj = null;
      ArrayList list = null;
      StringReader stringReader = new StringReader(str);
      BufferedReader reader = new BufferedReader(stringReader);

      while (true) {
         String str2 = reader.readLine();
         if (str2 == null) {
            return sb.toString();
         }

         if (MacroState.isMacroLine(str2)) {
            if (sb.length() == 0) {
               sb.append(ShaderMacros.getFixedMacroLines());
            }

            if (list == null) {
               list = new ArrayList<>(Arrays.asList(ShaderMacros.getExtensions()));
            }

            Iterator it = list.iterator();

            while (it.hasNext()) {
               ShaderMacro shaderMacro = (ShaderMacro)it.next();
               if (str2.contains(shaderMacro.getName())) {
                  sb.append(shaderMacro.getSourceLine());
                  sb.append("\n");
                  it.remove();
               }
            }
         }
      }
   }

   public static InputStream process(InputStream in, String str) throws IOException {
      String str2 = Config.readInputStream(in, "ASCII");
      String macroHeader = getMacroHeader(str2);
      if (!macroHeader.isEmpty()) {
         str2 = macroHeader + str2;
         if (Shaders.saveFinalShaders) {
            Shaders.saveShader(str.replace(':', '/') + ".pre", str2);
         }

         str2 = process(str2);
      }

      if (Shaders.saveFinalShaders) {
         Shaders.saveShader(str.replace(':', '/'), str2);
      }

      byte[] bytes = str2.getBytes("ASCII");
      return new ByteArrayInputStream(bytes);
   }
}
