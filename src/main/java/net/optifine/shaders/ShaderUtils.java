package net.optifine.shaders;

import net.optifine.config.Config;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderProfile;

public class ShaderUtils {
   public static ShaderProfile detectProfile(ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions, boolean flag) {
      if (shaderProfiles == null) {
         return null;
      } else {
         for (int i = 0; i < shaderProfiles.length; i++) {
            ShaderProfile shaderProfile = shaderProfiles[i];
            if (matchProfile(shaderProfile, shaderOptions, flag)) {
               return shaderProfile;
            }
         }

         return null;
      }
   }

   public static boolean matchProfile(ShaderProfile shaderProfile, ShaderOption[] shaderOptions, boolean flag) {
      if (shaderProfile == null) {
         return false;
      } else if (shaderOptions == null) {
         return false;
      } else {
         String[] options = shaderProfile.getOptions();

         for (int i = 0; i < options.length; i++) {
            String str = options[i];
            ShaderOption shaderOption = getShaderOption(str, shaderOptions);
            if (shaderOption != null) {
               if (!Config.equals(flag ? shaderOption.getValueDefault() : shaderOption.getValue(), shaderProfile.getValue(str))) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static ShaderOption getShaderOption(String str, ShaderOption[] shaderOptions) {
      if (shaderOptions == null) {
         return null;
      } else {
         for (int i = 0; i < shaderOptions.length; i++) {
            ShaderOption shaderOption = shaderOptions[i];
            if (shaderOption.getName().equals(str)) {
               return shaderOption;
            }
         }

         return null;
      }
   }
}
