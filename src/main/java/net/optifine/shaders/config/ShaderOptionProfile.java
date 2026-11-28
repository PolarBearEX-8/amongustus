package net.optifine.shaders.config;

import java.util.ArrayList;
import net.optifine.Lang;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;

public class ShaderOptionProfile extends ShaderOption {
   private ShaderProfile[] profiles = null;
   private ShaderOption[] options = null;
   private static final String NAME_PROFILE = null;
   private static final String VALUE_CUSTOM = null;

   @Override
   public String getNameText() {
      return Lang.get("of.shaders.profile");
   }

   private static String[] detectProfileNames(ShaderProfile[] shaderProfiles) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < shaderProfiles.length; i++) {
         list.add(shaderProfiles[i].getName());
      }

      list.add("<custom>");
      return (String[])list.toArray(new String[list.size()]);
   }

   @Override
   public void nextValue() {
      super.nextValue();
      if (this.getValue().equals("<custom>")) {
         super.nextValue();
      }

      this.applyProfileOptions();
   }

   @Override
   public String getValueText(String str) {
      return str.equals("<custom>") ? Lang.get("of.general.custom", "<custom>") : Shaders.translate("profile." + str, str);
   }

   private static String detectProfileName(ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
      return detectProfileName(shaderProfiles, shaderOptions, false);
   }

   @Override
   public String getValueColor(String str) {
      return str.equals("<custom>") ? "§c" : "§a";
   }

   public ShaderOptionProfile(ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
      super("<profile>", "", detectProfileName(shaderProfiles, shaderOptions), detectProfileNames(shaderProfiles), detectProfileName(shaderProfiles, shaderOptions, true), (String)null);
      this.profiles = shaderProfiles;
      this.options = shaderOptions;
   }

   public void updateProfile() {
      ShaderProfile profile = this.getProfile(this.getValue());
      if (profile == null || !ShaderUtils.matchProfile(profile, this.options, false)) {
         this.setValue(detectProfileName(this.profiles, this.options));
      }
   }

   private static String detectProfileName(ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions, boolean flag) {
      ShaderProfile shaderProfile = ShaderUtils.detectProfile(shaderProfiles, shaderOptions, flag);
      return shaderProfile == null ? "<custom>" : shaderProfile.getName();
   }

   private ShaderOption getOption(String str) {
      for (int i = 0; i < this.options.length; i++) {
         ShaderOption shaderOption = this.options[i];
         if (shaderOption.getName().equals(str)) {
            return shaderOption;
         }
      }

      return null;
   }

   @Override
   public String getDescriptionText() {
      String str = Shaders.translate("profile.comment", (String)null);
      if (str != null) {
         return str;
      } else {
         StringBuffer sb = new StringBuffer();

         for (int i = 0; i < this.profiles.length; i++) {
            String name2 = this.profiles[i].getName();
            if (name2 != null) {
               String str2 = Shaders.translate("profile." + name2 + ".comment", (String)null);
               if (str2 != null) {
                  sb.append(str2);
                  if (!str2.endsWith(". ")) {
                     sb.append(". ");
                  }
               }
            }
         }

         return sb.toString();
      }
   }

   private void applyProfileOptions() {
      ShaderProfile profile = this.getProfile(this.getValue());
      if (profile != null) {
         String[] options2 = profile.getOptions();

         for (int i = 0; i < options2.length; i++) {
            String str = options2[i];
            ShaderOption option = this.getOption(str);
            if (option != null) {
               option.setValue(profile.getValue(str));
            }
         }
      }
   }

   private ShaderProfile getProfile(String str) {
      for (int i = 0; i < this.profiles.length; i++) {
         ShaderProfile shaderProfile = this.profiles[i];
         if (shaderProfile.getName().equals(str)) {
            return shaderProfile;
         }
      }

      return null;
   }
}
