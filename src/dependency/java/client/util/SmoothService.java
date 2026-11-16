package client.util;

import client.event.PreUpdateObserver;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.shader.ShaderUniformImpl;

public enum SmoothService {
   BOOL,
   INT,
   FLOAT,
   VEC2,
   VEC3,
   VEC4;

   public static final SmoothService[] ENUM_VALUES = values();

   private void applyDistanceBridge(DistanceBridge distanceBridge, AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder) {
      float[] floatArray = distanceBridge.getFloatArray();
      if (floatArray.length != 3) {
         throw new RuntimeException("Value length is not 3, length: " + floatArray.length);
      } else {
         auxiliaryFloatArrayHolder.applyFloat(floatArray[0], floatArray[1], floatArray[2]);
      }
   }

   public boolean matchesAimSorter(AimSorter aimSorter) {
      switch (SmoothServiceSmoothServiceSwitchMap.countArray[this.ordinal()]) {
         case 1:
            return aimSorter == AimSorter.BOOL;
         case 2:
            return aimSorter == AimSorter.FLOAT;
         case 3:
            return aimSorter == AimSorter.FLOAT;
         case 4:
         case 5:
         case 6:
            return aimSorter == AimSorter.FLOAT_ARRAY;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   private void applyAimValidator(AimValidator aimValidator, StringHandler stringHandler) {
      int factor = (int)aimValidator.getFactor();
      stringHandler.applyIndex2(factor);
   }

   private void applyDistanceBridge(DistanceBridge distanceBridge, AuxiliaryStringHandler auxiliaryStringHandler) {
      float[] floatArray = distanceBridge.getFloatArray();
      if (floatArray.length != 4) {
         throw new RuntimeException("Value length is not 4, length: " + floatArray.length);
      } else {
         auxiliaryStringHandler.applyFloat(floatArray[0], floatArray[1], floatArray[2], floatArray[3]);
      }
   }

   public ShaderUniform resolveShaderUniform(String text) {
      switch (SmoothServiceSmoothServiceSwitchMap.countArray[this.ordinal()]) {
         case 1:
            return new StringHandler(text);
         case 2:
            return new StringHandler(text);
         case 3:
            return new FloatArrayHolder(text);
         case 4:
            return new ShaderUniformImpl(text);
         case 5:
            return new AuxiliaryFloatArrayHolder(text);
         case 6:
            return new AuxiliaryStringHandler(text);
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public void applyPreUpdateObserver(PreUpdateObserver preUpdateObserver, ShaderUniform shaderUniform) {
      switch (SmoothServiceSmoothServiceSwitchMap.countArray[this.ordinal()]) {
         case 1:
            this.applyTargetObserver((TargetObserver)preUpdateObserver, (StringHandler)shaderUniform);
            return;
         case 2:
            this.applyAimValidator((AimValidator)preUpdateObserver, (StringHandler)shaderUniform);
            return;
         case 3:
            this.applyAimValidator((AimValidator)preUpdateObserver, (FloatArrayHolder)shaderUniform);
            return;
         case 4:
            this.applyDistanceBridge((DistanceBridge)preUpdateObserver, (ShaderUniformImpl)shaderUniform);
            return;
         case 5:
            this.applyDistanceBridge((DistanceBridge)preUpdateObserver, (AuxiliaryFloatArrayHolder)shaderUniform);
            return;
         case 6:
            this.applyDistanceBridge((DistanceBridge)preUpdateObserver, (AuxiliaryStringHandler)shaderUniform);
            return;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   private void applyAimValidator(AimValidator aimValidator, FloatArrayHolder floatArrayHolder) {
      float factor = aimValidator.getFactor();
      floatArrayHolder.applyFloat(factor);
   }

   private void applyTargetObserver(TargetObserver targetObserver, StringHandler stringHandler) {
      byte byteValue = ((byte)((targetObserver.isActive()) ? 1 : 0));
      stringHandler.applyIndex2(byteValue);
   }

   public static SmoothService resolveSmoothService(String text) {
      SmoothService[] values2 = values();

      for (int index = 0; index < values2.length; index++) {
         SmoothService smoothService = values2[index];
         if (smoothService.name().toLowerCase().equals(text)) {
            return smoothService;
         }
      }

      return null;
   }

   private void applyDistanceBridge(DistanceBridge distanceBridge, ShaderUniformImpl shaderUniformImpl) {
      float[] floatArray = distanceBridge.getFloatArray();
      if (floatArray.length != 2) {
         throw new RuntimeException("Value length is not 2, length: " + floatArray.length);
      } else {
         shaderUniformImpl.applyFloat(floatArray[0], floatArray[1]);
      }
   }
}
