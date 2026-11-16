package client.util;

import client.event.AimSorterAimSorterCallback;
import client.event.AimSorterAimSorterCallbackImpl;
import client.event.AimSorterCallback;
import client.event.PreUpdateObserver;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MathHelper;

public enum PlusMinusType {
   PLUS(10, AimSorter.FLOAT, "+", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   MINUS(10, AimSorter.FLOAT, "-", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   MUL(11, AimSorter.FLOAT, "*", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   DIV(11, AimSorter.FLOAT, "/", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   MOD(11, AimSorter.FLOAT, "%", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   NEG(12, AimSorter.FLOAT, "neg", new AimSorter[]{AimSorter.FLOAT}),
   PI(AimSorter.FLOAT, "pi", new AimSorter[0]),
   SIN(AimSorter.FLOAT, "sin", new AimSorter[]{AimSorter.FLOAT}),
   COS(AimSorter.FLOAT, "cos", new AimSorter[]{AimSorter.FLOAT}),
   ASIN(AimSorter.FLOAT, "asin", new AimSorter[]{AimSorter.FLOAT}),
   ACOS(AimSorter.FLOAT, "acos", new AimSorter[]{AimSorter.FLOAT}),
   TAN(AimSorter.FLOAT, "tan", new AimSorter[]{AimSorter.FLOAT}),
   ATAN(AimSorter.FLOAT, "atan", new AimSorter[]{AimSorter.FLOAT}),
   ATAN2(AimSorter.FLOAT, "atan2", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   TORAD(AimSorter.FLOAT, "torad", new AimSorter[]{AimSorter.FLOAT}),
   TODEG(AimSorter.FLOAT, "todeg", new AimSorter[]{AimSorter.FLOAT}),
   MIN(
      AimSorter.FLOAT,
      "min",
      new AimSorterAimSorterCallback()
         .resolveAimSorterAimSorterCallback(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallbackByAimSorterArray(new AimSorter[]{AimSorter.FLOAT})
   ),
   MAX(
      AimSorter.FLOAT,
      "max",
      new AimSorterAimSorterCallback()
         .resolveAimSorterAimSorterCallback(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallbackByAimSorterArray(new AimSorter[]{AimSorter.FLOAT})
   ),
   CLAMP(AimSorter.FLOAT, "clamp", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT}),
   ABS(AimSorter.FLOAT, "abs", new AimSorter[]{AimSorter.FLOAT}),
   FLOOR(AimSorter.FLOAT, "floor", new AimSorter[]{AimSorter.FLOAT}),
   CEIL(AimSorter.FLOAT, "ceil", new AimSorter[]{AimSorter.FLOAT}),
   EXP(AimSorter.FLOAT, "exp", new AimSorter[]{AimSorter.FLOAT}),
   FRAC(AimSorter.FLOAT, "frac", new AimSorter[]{AimSorter.FLOAT}),
   LOG(AimSorter.FLOAT, "log", new AimSorter[]{AimSorter.FLOAT}),
   POW(AimSorter.FLOAT, "pow", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   RANDOM(AimSorter.FLOAT, "random", new AimSorter[0]),
   ROUND(AimSorter.FLOAT, "round", new AimSorter[]{AimSorter.FLOAT}),
   SIGNUM(AimSorter.FLOAT, "signum", new AimSorter[]{AimSorter.FLOAT}),
   SQRT(AimSorter.FLOAT, "sqrt", new AimSorter[]{AimSorter.FLOAT}),
   FMOD(AimSorter.FLOAT, "fmod", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   TIME(AimSorter.FLOAT, "time", new AimSorter[0]),
   IF(
      AimSorter.FLOAT,
      "if",
      new AimSorterAimSorterCallback()
         .resolveAimSorterAimSorterCallback(new AimSorter[]{AimSorter.BOOL, AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallbackByAimSorterArray(new AimSorter[]{AimSorter.BOOL, AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallback2(new AimSorter[]{AimSorter.FLOAT})
   ),
   NOT(12, AimSorter.BOOL, "!", new AimSorter[]{AimSorter.BOOL}),
   AND(3, AimSorter.BOOL, "&&", new AimSorter[]{AimSorter.BOOL, AimSorter.BOOL}),
   OR(2, AimSorter.BOOL, "||", new AimSorter[]{AimSorter.BOOL, AimSorter.BOOL}),
   GREATER(8, AimSorter.BOOL, ">", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   GREATER_OR_EQUAL(8, AimSorter.BOOL, ">=", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   SMALLER(8, AimSorter.BOOL, "<", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   SMALLER_OR_EQUAL(8, AimSorter.BOOL, "<=", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   EQUAL(7, AimSorter.BOOL, "==", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   NOT_EQUAL(7, AimSorter.BOOL, "!=", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   BETWEEN(7, AimSorter.BOOL, "between", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT}),
   EQUALS(7, AimSorter.BOOL, "equals", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT}),
   IN(
      AimSorter.BOOL,
      "in",
      new AimSorterAimSorterCallback()
         .resolveAimSorterAimSorterCallback(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallbackByAimSorterArray(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallback2(new AimSorter[]{AimSorter.FLOAT})
   ),
   SMOOTH(
      AimSorter.FLOAT,
      "smooth",
      new AimSorterAimSorterCallback()
         .resolveAimSorterAimSorterCallback(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallbackByAimSorterArray(new AimSorter[]{AimSorter.FLOAT})
         .resolveAimSorterAimSorterCallback(4)
   ),
   TRUE(AimSorter.BOOL, "true", new AimSorter[0]),
   FALSE(AimSorter.BOOL, "false", new AimSorter[0]),
   VEC2(AimSorter.FLOAT_ARRAY, "vec2", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT}),
   VEC3(AimSorter.FLOAT_ARRAY, "vec3", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT}),
   VEC4(AimSorter.FLOAT_ARRAY, "vec4", new AimSorter[]{AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT, AimSorter.FLOAT});

   public int count;
   public AimSorter aimSorter;
   public String labelText;
   public AimSorterCallback aimSorterCallback;
   public static PlusMinusType[] VALUES = values();
   public static final Map map = new HashMap();
   public static final PlusMinusType[] ENUM_VALUES = values();

   public AimSorter getAimSorter() {
      return this.aimSorter;
   }

   private float computeFactor(PreUpdateObserver[] preUpdateObserverArray) {
      if (preUpdateObserverArray.length == 2) {
         return Math.max(computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1));
      } else {
         float factor = computeFactor(preUpdateObserverArray, 0);

         for (int index = 1; index < preUpdateObserverArray.length; index++) {
            float factor2 = computeFactor(preUpdateObserverArray, index);
            if (factor2 > factor) {
               factor = factor2;
            }
         }

         return factor;
      }
   }

   public static PlusMinusType resolvePlusMinusType(String text) {
      for (int index = 0; index < VALUES.length; index++) {
         PlusMinusType plusMinusType = VALUES[index];
         if (plusMinusType.getLabelText().equals(text)) {
            return plusMinusType;
         }
      }

      return null;
   }

   public float computeFactor2(PreUpdateObserver[] preUpdateObserverArray) {
      switch (PlusMinusTypePlusMinusTypeSwitchMap.countArray[this.ordinal()]) {
         case 1:
            return computeFactor(preUpdateObserverArray, 0) + computeFactor(preUpdateObserverArray, 1);
         case 2:
            return computeFactor(preUpdateObserverArray, 0) - computeFactor(preUpdateObserverArray, 1);
         case 3:
            return computeFactor(preUpdateObserverArray, 0) * computeFactor(preUpdateObserverArray, 1);
         case 4:
            return computeFactor(preUpdateObserverArray, 0) / computeFactor(preUpdateObserverArray, 1);
         case 5:
            float factor = computeFactor(preUpdateObserverArray, 0);
            float factor2 = computeFactor(preUpdateObserverArray, 1);
            return factor - factor2 * (float)((int)(factor / factor2));
         case 6:
            return -computeFactor(preUpdateObserverArray, 0);
         case 7:
            return (float) Math.PI;
         case 8:
            return MathHelper.abs(computeFactor(preUpdateObserverArray, 0));
         case 9:
            return MathHelper.cos(computeFactor(preUpdateObserverArray, 0));
         case 10:
            return AltFloatEntry.computeFactorByFloat(computeFactor(preUpdateObserverArray, 0));
         case 11:
            return AltFloatEntry.computeFactor2(computeFactor(preUpdateObserverArray, 0));
         case 12:
            return (float)Math.tan((double)computeFactor(preUpdateObserverArray, 0));
         case 13:
            return (float)Math.atan((double)computeFactor(preUpdateObserverArray, 0));
         case 14:
            return (float)MathHelper.getDouble6((double)computeFactor(preUpdateObserverArray, 0), (double)computeFactor(preUpdateObserverArray, 1));
         case 15:
            return AltFloatEntry.computeFactor3(computeFactor(preUpdateObserverArray, 0));
         case 16:
            return AltFloatEntry.computeFactor(computeFactor(preUpdateObserverArray, 0));
         case 17:
            return this.computeFactorByPreUpdateObserve(preUpdateObserverArray);
         case 18:
            return this.computeFactor(preUpdateObserverArray);
         case 19:
            return MathHelper.clamp_float(computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1), computeFactor(preUpdateObserverArray, 2));
         case 20:
            return MathHelper.computeFactor(computeFactor(preUpdateObserverArray, 0));
         case 21:
            return (float)Math.exp((double)computeFactor(preUpdateObserverArray, 0));
         case 22:
            return (float)MathHelper.floor_float(computeFactor(preUpdateObserverArray, 0));
         case 23:
            return (float)MathHelper.computeCount(computeFactor(preUpdateObserverArray, 0));
         case 24:
            return (float)MathHelper.wrapAngleTo180_double((double)computeFactor(preUpdateObserverArray, 0));
         case 25:
            return (float)Math.log((double)computeFactor(preUpdateObserverArray, 0));
         case 26:
            return (float)Math.pow((double)computeFactor(preUpdateObserverArray, 0), (double)computeFactor(preUpdateObserverArray, 1));
         case 27:
            return (float)Math.random();
         case 28:
            return (float)Math.round(computeFactor(preUpdateObserverArray, 0));
         case 29:
            return Math.signum(computeFactor(preUpdateObserverArray, 0));
         case 30:
            return MathHelper.sin(computeFactor(preUpdateObserverArray, 0));
         case 31:
            float factor3 = computeFactor(preUpdateObserverArray, 0);
            float factor4 = computeFactor(preUpdateObserverArray, 1);
            return factor3 - factor4 * (float)MathHelper.floor_float(factor3 / factor4);
         case 32:
            WorldClient world = Minecraft.getMinecraft().theWorld;
            if (world == null) {
               return 0.0F;
            }

            return (float)(world.getTimestamp2() % 24000L) + BuildManager.factor;
         case 33:
            int count2 = (preUpdateObserverArray.length - 1) / 2;

            for (int index = 0; index < count2; index++) {
               int count3 = index * 2;
               if (matchesPreUpdateObserverArray(preUpdateObserverArray, count3)) {
                  return computeFactor(preUpdateObserverArray, count3 + 1);
               }
            }

            return computeFactor(preUpdateObserverArray, count2 * 2);
         case 34:
            int count4 = (int)computeFactor(preUpdateObserverArray, 0);
            float factor5 = computeFactor(preUpdateObserverArray, 1);
            float factor6 = preUpdateObserverArray.length > 2 ? computeFactor(preUpdateObserverArray, 2) : 1.0F;
            float factor7 = preUpdateObserverArray.length > 3 ? computeFactor(preUpdateObserverArray, 3) : factor6;
            return ExtraMapEntry.computeFactor(count4, factor5, factor6, factor7);
         default:
            BuildManager.applyText3("Unknown function type: " + this);
            return 0.0F;
      }
   }

   private static float computeFactor(PreUpdateObserver[] preUpdateObserverArray, int count2) {
      return ((AimValidator)preUpdateObserverArray[count2]).getFactor();
   }

   private PlusMinusType(AimSorter aimSorter2, String text, AimSorter[] aimSorterArray) {
      this(0, aimSorter2, text, aimSorterArray);
   }

   public AimSorterCallback getAimSorterCallback() {
      return this.aimSorterCallback;
   }

   public int computeCount(PreUpdateObserver[] preUpdateObserverArray) {
      return this.aimSorterCallback.resolveAimSorterArray(preUpdateObserverArray).length;
   }

   public float[] resolveFloatArray(PreUpdateObserver[] preUpdateObserverArray) {
      switch (PlusMinusTypePlusMinusTypeSwitchMap.countArray[this.ordinal()]) {
         case 49:
            return new float[]{computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1)};
         case 50:
            return new float[]{computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1), computeFactor(preUpdateObserverArray, 2)};
         case 51:
            return new float[]{computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1), computeFactor(preUpdateObserverArray, 2), computeFactor(preUpdateObserverArray, 3)};
         default:
            BuildManager.applyText3("Unknown function type: " + this);
            return null;
      }
   }

   public int getCount() {
      return this.count;
   }

   private PlusMinusType(AimSorter aimSorter2, String text, AimSorterCallback aimSorterCallback2) {
      this(0, aimSorter2, text, aimSorterCallback2);
   }

   private PlusMinusType(int count2, AimSorter aimSorter2, String text, AimSorter[] aimSorterArray) {
      this(count2, aimSorter2, text, new AimSorterAimSorterCallbackImpl(aimSorterArray));
   }

   private static boolean matchesPreUpdateObserverArray(PreUpdateObserver[] preUpdateObserverArray, int count2) {
      return ((TargetObserver)preUpdateObserverArray[count2]).isActive();
   }

   public boolean matchesPreUpdateObserverArray(PreUpdateObserver[] preUpdateObserverArray) {
      switch (PlusMinusTypePlusMinusTypeSwitchMap.countArray[this.ordinal()]) {
         case 35:
            return true;
         case 36:
            return false;
         case 37:
            return !matchesPreUpdateObserverArray(preUpdateObserverArray, 0);
         case 38:
            return matchesPreUpdateObserverArray(preUpdateObserverArray, 0) && matchesPreUpdateObserverArray(preUpdateObserverArray, 1);
         case 39:
            return matchesPreUpdateObserverArray(preUpdateObserverArray, 0) || matchesPreUpdateObserverArray(preUpdateObserverArray, 1);
         case 40:
            return computeFactor(preUpdateObserverArray, 0) > computeFactor(preUpdateObserverArray, 1);
         case 41:
            return computeFactor(preUpdateObserverArray, 0) >= computeFactor(preUpdateObserverArray, 1);
         case 42:
            return computeFactor(preUpdateObserverArray, 0) < computeFactor(preUpdateObserverArray, 1);
         case 43:
            return computeFactor(preUpdateObserverArray, 0) <= computeFactor(preUpdateObserverArray, 1);
         case 44:
            return computeFactor(preUpdateObserverArray, 0) == computeFactor(preUpdateObserverArray, 1);
         case 45:
            return computeFactor(preUpdateObserverArray, 0) != computeFactor(preUpdateObserverArray, 1);
         case 46:
            float factor = computeFactor(preUpdateObserverArray, 0);
            return factor >= computeFactor(preUpdateObserverArray, 1) && factor <= computeFactor(preUpdateObserverArray, 2);
         case 47:
            float factor2 = computeFactor(preUpdateObserverArray, 0) - computeFactor(preUpdateObserverArray, 1);
            float factor3 = computeFactor(preUpdateObserverArray, 2);
            return Math.abs(factor2) <= factor3;
         case 48:
            float factor4 = computeFactor(preUpdateObserverArray, 0);

            for (int index = 1; index < preUpdateObserverArray.length; index++) {
               float factor5 = computeFactor(preUpdateObserverArray, index);
               if (factor4 == factor5) {
                  return true;
               }
            }

            return false;
         default:
            BuildManager.applyText3("Unknown function type: " + this);
            return false;
      }
   }

   private PlusMinusType(int count2, AimSorter aimSorter2, String text, AimSorterCallback aimSorterCallback2) {
      this.count = count2;
      this.aimSorter = aimSorter2;
      this.labelText = text;
      this.aimSorterCallback = aimSorterCallback2;
   }

   private float computeFactorByPreUpdateObserve(PreUpdateObserver[] preUpdateObserverArray) {
      if (preUpdateObserverArray.length == 2) {
         return Math.min(computeFactor(preUpdateObserverArray, 0), computeFactor(preUpdateObserverArray, 1));
      } else {
         float factor = computeFactor(preUpdateObserverArray, 0);

         for (int index = 1; index < preUpdateObserverArray.length; index++) {
            float factor2 = computeFactor(preUpdateObserverArray, index);
            if (factor2 < factor) {
               factor = factor2;
            }
         }

         return factor;
      }
   }

   public String getLabelText() {
      return this.labelText;
   }

   public AimSorter[] resolveAimSorterArray(PreUpdateObserver[] preUpdateObserverArray) {
      return this.aimSorterCallback.resolveAimSorterArray(preUpdateObserverArray);
   }
}
