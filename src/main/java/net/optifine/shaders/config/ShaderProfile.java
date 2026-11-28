package net.optifine.shaders.config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ShaderProfile {
   private String name = null;
   private Map mapOptionValues = new LinkedHashMap();
   private Set disabledPrograms = new LinkedHashSet();

   public void applyOptionValues(ShaderOption[] shaderOptions) {
      for (int i = 0; i < shaderOptions.length; i++) {
         ShaderOption shaderOption = shaderOptions[i];
         String name2 = shaderOption.getName();
         String str = (String)this.mapOptionValues.get(name2);
         if (str != null) {
            shaderOption.setValue(str);
         }
      }
   }

   public void addDisabledProgram(String str) {
      this.disabledPrograms.add(str);
   }

   public String getValue(String str) {
      return (String)this.mapOptionValues.get(str);
   }

   public String getName() {
      return this.name;
   }

   public boolean isProgramDisabled(String str) {
      return this.disabledPrograms.contains(str);
   }

   public ShaderProfile(String str) {
      this.name = str;
   }

   public void addOptionValues(ShaderProfile shaderProfile) {
      if (shaderProfile != null) {
         this.mapOptionValues.putAll(shaderProfile.mapOptionValues);
      }
   }

   public void addOptionValue(String str, String str2) {
      this.mapOptionValues.put(str, str2);
   }

   public String[] getOptions() {
      Set keys = this.mapOptionValues.keySet();
      return (String[])keys.toArray(new String[keys.size()]);
   }

   public void addDisabledPrograms(Collection items) {
      this.disabledPrograms.addAll(items);
   }

   public Collection getDisabledPrograms() {
      return new LinkedHashSet(this.disabledPrograms);
   }

   public void removeDisabledProgram(String str) {
      this.disabledPrograms.remove(str);
   }
}
