package client.util;

import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.shader.ShaderUniformFloat;
import net.minecraft.client.shader.ShaderUniformImpl;
import net.minecraft.util.BlockPos;

public enum BiomeTemperatureType {
   BIOME("biome"),
   TEMPERATURE("temperature"),
   RAINFALL("rainfall"),
   HELD_ITEM_ID(PreEndHandManager.stringHandler26),
   HELD_BLOCK_LIGHT_VALUE(PreEndHandManager.stringHandler40),
   HELD_ITEM_ID2(PreEndHandManager.stringHandler),
   HELD_BLOCK_LIGHT_VALUE2(PreEndHandManager.stringHandler24),
   WORLD_TIME(PreEndHandManager.stringHandler16),
   WORLD_DAY(PreEndHandManager.stringHandler30),
   MOON_PHASE(PreEndHandManager.stringHandler2),
   FRAME_COUNTER(PreEndHandManager.stringHandler20),
   FRAME_TIME(PreEndHandManager.floatArrayHolder8),
   FRAME_TIME_COUNTER(PreEndHandManager.floatArrayHolder2),
   SUN_ANGLE(PreEndHandManager.floatArrayHolder9),
   SHADOW_ANGLE(PreEndHandManager.floatArrayHolder7),
   RAIN_STRENGTH(PreEndHandManager.floatArrayHolder14),
   ASPECT_RATIO(PreEndHandManager.floatArrayHolder5),
   VIEW_WIDTH(PreEndHandManager.floatArrayHolder6),
   VIEW_HEIGHT(PreEndHandManager.floatArrayHolder16),
   NEAR(PreEndHandManager.floatArrayHolder17),
   FAR(PreEndHandManager.floatArrayHolder3),
   WETNESS(PreEndHandManager.floatArrayHolder13),
   EYE_ALTITUDE(PreEndHandManager.floatArrayHolder12),
   EYE_BRIGHTNESS(PreEndHandManager.extendedStringHandler4, new String[]{"x", "y"}),
   TERRAIN_TEXTURE_SIZE(PreEndHandManager.extendedStringHandler2, new String[]{"x", "y"}),
   TERRRAIN_ICON_SIZE(PreEndHandManager.stringHandler39),
   IS_EYE_IN_WATER(PreEndHandManager.stringHandler48),
   NIGHT_VISION(PreEndHandManager.floatArrayHolder11),
   BLINDNESS(PreEndHandManager.floatArrayHolder),
   SCREEN_BRIGHTNESS(PreEndHandManager.floatArrayHolder15),
   HIDE_GUI(PreEndHandManager.stringHandler7),
   CENTER_DEPT_SMOOTH(PreEndHandManager.floatArrayHolder10),
   ATLAS_SIZE(PreEndHandManager.extendedStringHandler, new String[]{"x", "y"}),
   CAMERA_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder8, new String[]{"x", "y", "z"}),
   PREVIOUS_CAMERA_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder5, new String[]{"x", "y", "z"}),
   SUN_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder7, new String[]{"x", "y", "z"}),
   MOON_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder3, new String[]{"x", "y", "z"}),
   SHADOW_LIGHT_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder6, new String[]{"x", "y", "z"}),
   UP_POSITION(PreEndHandManager.auxiliaryFloatArrayHolder4, new String[]{"x", "y", "z"}),
   SKY_COLOR(PreEndHandManager.auxiliaryFloatArrayHolder2, new String[]{"r", "g", "b"}),
   GBUFFER_PROJECTION(PreEndHandManager.shaderUniformFloat, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PROJECTION_INVERSE(PreEndHandManager.shaderUniformFloat9, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PREVIOUS_PROJECTION(PreEndHandManager.shaderUniformFloat5, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_MODEL_VIEW(PreEndHandManager.shaderUniformFloat6, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_MODEL_VIEW_INVERSE(PreEndHandManager.shaderUniformFloat8, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PREVIOUS_MODEL_VIEW(PreEndHandManager.shaderUniformFloat2, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_PROJECTION(PreEndHandManager.shaderUniformFloat4, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_PROJECTION_INVERSE(PreEndHandManager.shaderUniformFloat10, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_MODEL_VIEW(PreEndHandManager.shaderUniformFloat3, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_MODEL_VIEW_INVERSE(PreEndHandManager.shaderUniformFloat7, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"});

   public String labelText;
   public ShaderUniform shaderUniform;
   public String[] stringArray;
   public String[] stringArray2;
   public static final BiomeTemperatureType[] ENUM_VALUES = values();

   public String[] getStringArray() {
      return this.stringArray;
   }

   public float computeFactor(int count, int count2) {
      if (this.stringArray == null || count >= 0 && count <= this.stringArray.length) {
         if (this.stringArray2 == null || count2 >= 0 && count2 <= this.stringArray2.length) {
            switch (BiomeTemperatureTypeBiomeTemperatureTypeSwitch.countArray[this.ordinal()]) {
               case 1:
                  BlockPos blockPos = PreEndHandManager.getBlockPos();
                  return (float)PreEndHandManager.getWorld().resolveBiomeIDIsOutOfBoundsManager(blockPos).count;
               case 2:
                  BlockPos blockPos2 = PreEndHandManager.getBlockPos();
                  BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = PreEndHandManager.getWorld().resolveBiomeIDIsOutOfBoundsManager(blockPos2);
                  return biomeIDIsOutOfBoundsManager != null ? biomeIDIsOutOfBoundsManager.computeFactor(blockPos2) : 0.0F;
               case 3:
                  BlockPos blockPos3 = PreEndHandManager.getBlockPos();
                  BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = PreEndHandManager.getWorld().resolveBiomeIDIsOutOfBoundsManager(blockPos3);
                  return biomeIDIsOutOfBoundsManager2 != null ? biomeIDIsOutOfBoundsManager2.getFactor3() : 0.0F;
               default:
                  if (this.shaderUniform instanceof FloatArrayHolder) {
                     return ((FloatArrayHolder)this.shaderUniform).getFactor();
                  } else if (this.shaderUniform instanceof StringHandler) {
                     return (float)((StringHandler)this.shaderUniform).getCountAsIndex();
                  } else if (this.shaderUniform instanceof ExtendedStringHandler) {
                     return (float)((ExtendedStringHandler)this.shaderUniform).getIndexArray()[count];
                  } else if (this.shaderUniform instanceof ShaderUniformImpl) {
                     return ((ShaderUniformImpl)this.shaderUniform).getFloatArray()[count];
                  } else if (this.shaderUniform instanceof AuxiliaryFloatArrayHolder) {
                     return ((AuxiliaryFloatArrayHolder)this.shaderUniform).getFloatArray()[count];
                  } else if (this.shaderUniform instanceof AuxiliaryStringHandler) {
                     return ((AuxiliaryStringHandler)this.shaderUniform).getFloatArray()[count];
                  } else if (this.shaderUniform instanceof ShaderUniformFloat) {
                     return ((ShaderUniformFloat)this.shaderUniform).computeFactor(count, count2);
                  } else {
                     throw new IllegalArgumentException("Unknown uniform type: " + this);
                  }
            }
         } else {
            BuildManager.applyText3("Invalid index2, parameter: " + this + ", index: " + count2);
            return 0.0F;
         }
      } else {
         BuildManager.applyText3("Invalid index1, parameter: " + this + ", index: " + count);
         return 0.0F;
      }
   }

   private BiomeTemperatureType(ShaderUniform shaderUniform2) {
      this.labelText = shaderUniform2.getLabelText();
      this.shaderUniform = shaderUniform2;
      if (!matchesTarget(shaderUniform2, new Class[]{FloatArrayHolder.class, StringHandler.class})) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + shaderUniform2.getClass().getName());
      }
   }

   private BiomeTemperatureType(String text) {
      this.labelText = text;
   }

   public ShaderUniform getShaderUniform() {
      return this.shaderUniform;
   }

   private BiomeTemperatureType(ShaderUniform shaderUniform2, String[] textArray, String[] textArray2) {
      this.labelText = shaderUniform2.getLabelText();
      this.shaderUniform = shaderUniform2;
      this.stringArray = textArray;
      this.stringArray2 = textArray2;
      if (!matchesTarget(shaderUniform2, new Class[]{ShaderUniformFloat.class})) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + shaderUniform2.getClass().getName());
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   private BiomeTemperatureType(ShaderUniform shaderUniform2, String[] textArray) {
      this.labelText = shaderUniform2.getLabelText();
      this.shaderUniform = shaderUniform2;
      this.stringArray = textArray;
      if (!matchesTarget(shaderUniform2, new Class[]{ExtendedStringHandler.class, ShaderUniformImpl.class, AuxiliaryFloatArrayHolder.class, AuxiliaryStringHandler.class})
         )
       {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + shaderUniform2.getClass().getName());
      }
   }

   private static boolean matchesTarget(Object entry, Class[] clazzArray) {
      if (entry == null) {
         return false;
      } else {
         Class classItem = entry.getClass();

         for (int index = 0; index < clazzArray.length; index++) {
            if (clazzArray[index].isAssignableFrom(classItem)) {
               return true;
            }
         }

         return false;
      }
   }

   public String[] getStringArray2() {
      return this.stringArray2;
   }
}
