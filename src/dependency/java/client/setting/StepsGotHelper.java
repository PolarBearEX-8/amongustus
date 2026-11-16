package client.setting;

import client.util.FunctionController;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.DoubleStream;

public class StepsGotHelper {
   public static Function function = FunctionController.resolveFunctionByFunction(controller -> StepsGotHelper.resolveFunction((DoubleController)controller));
   public static Function function2 = resolveFunction(5.0);
   public static Function function3 = resolveFunction(4.0);

   public static double computeValue2(double offset) {
      return offset * offset * offset;
   }

   public static Function doubleValueByDouble(Double offset) {
      double factor = offset == null ? 1.70158 : offset * 1.70158;
      return item -> StepsGotHelper.resolveDouble4(factor, (Double)item);
   }

   public static Function getFunctionAsFunction() {
      return (item -> StepsGotHelper.resolveDouble2((Double)item));
   }

   public static Function resolveFunctionByDouble(Double offset) {
      double offset2 = offset == null ? 0.5 : offset;
      Function mapper = item -> StepsGotHelper.resolveDoubleByDouble((Double)item);
      Function mapper2 = item -> StepsGotHelper.resolveDouble(offset2, (Double)item);
      Function mapper3 = item -> StepsGotHelper.resolveDouble2(offset2, (Double)item);
      Function mapper4 = item -> StepsGotHelper.resolveDoubleByDouble(offset2, (Double)item);
      return item -> StepsGotHelper.resolveDouble(mapper, mapper2, mapper3, mapper4, (Double)item);
   }

   private static Double resolveDouble(Function mapper, Double offset) {
      return offset < 0.5 ? (Double)mapper.apply(offset * 2.0) / 2.0 : 1.0 - (Double)mapper.apply((1.0 - offset) * 2.0) / 2.0;
   }

   public static Function resolveFunction(double offset) {
      return item -> StepsGotHelper.resolveDouble5(offset, (Double)item);
   }

   private static Double resolveDoubleByFunction(Function mapper, Double offset) {
      return 1.0 - (Double)mapper.apply(1.0 - offset);
   }

   private static Double resolveDouble4(double offset, Double offset2) {
      return offset2 * offset2 * ((offset + 1.0) * offset2 - offset);
   }

   public static double computeValue(double offset, ProviderOptionType providerOptionType, List list) {
      Double offset2 = list != null && list.size() >= 1 ? (Double)list.get(0) : null;
      return (Double)((Function)function.apply(new DoubleController(providerOptionType, offset2))).apply(offset);
   }

   private static Double resolveDouble2(Double offset) {
      return offset > 0.0 ? 1.0 : 0.0;
   }

   public static Function getFunction() {
      return (item -> StepsGotHelper.resolveDouble((Double)item));
   }

   private static Double resolveDouble(Function mapper, Function mapper2, Function mapper3, Function mapper4, Double offset) {
      return computeValue((Double)mapper.apply(offset), (Double)mapper2.apply(offset), (Double)mapper3.apply(offset), (Double)mapper4.apply(offset));
   }

   private static double getAndIncrement(AtomicInteger atomicInteger, double offset) {
      return (double)atomicInteger.getAndIncrement() * offset;
   }

   public static double pow(double offset) {
      return Math.pow(2.0, 10.0 * (offset - 1.0));
   }

   private static Double resolveDouble3(double offset, Double offset2) {
      return 1.0 - Math.pow(Math.cos((double)((float)(offset2 * Math.PI / 2.0))), 3.0) * Math.cos((double)((float)(offset2 * offset)));
   }

   public static Function resolveFunction(DoubleController doubleController) {
      switch (StepsGotHelperProviderOptionTypeSwitchMap.countArray[doubleController.providerOptionType.ordinal()]) {
         case 1:
         default:
            return resolveFunction2((item -> StepsGotHelper.computeValueByDouble((Double)item)));
         case 2:
            return resolveFunction2(resolveFunction(doubleController.memberRef));
         case 3:
            return resolveFunction2((item -> StepsGotHelper.cos((Double)item)));
         case 4:
            return resolveFunction((Function)(item -> StepsGotHelper.cos((Double)item)));
         case 5:
            return resolveFunctionByFunction((item -> StepsGotHelper.cos((Double)item)));
         case 6:
            return resolveFunction2((item -> StepsGotHelper.computeValue((Double)item)));
         case 7:
            return resolveFunction((item -> StepsGotHelper.computeValue((Double)item)));
         case 8:
            return resolveFunctionByFunction((item -> StepsGotHelper.computeValue((Double)item)));
         case 9:
            return resolveFunction2((item -> StepsGotHelper.computeValue2((Double)item)));
         case 10:
            return resolveFunction((Function)(item -> StepsGotHelper.computeValue2((Double)item)));
         case 11:
            return resolveFunctionByFunction((item -> StepsGotHelper.computeValue2((Double)item)));
         case 12:
            return resolveFunction2((item -> StepsGotHelper.pow((Double)item)));
         case 13:
            return resolveFunction((Function)(item -> StepsGotHelper.pow((Double)item)));
         case 14:
            return resolveFunctionByFunction((item -> StepsGotHelper.pow((Double)item)));
         case 15:
            return resolveFunction2((item -> StepsGotHelper.sqrt((Double)item)));
         case 16:
            return resolveFunction((Function)(item -> StepsGotHelper.sqrt((Double)item)));
         case 17:
            return resolveFunctionByFunction((item -> StepsGotHelper.sqrt((Double)item)));
         case 18:
            return resolveFunction2(function3);
         case 19:
            return resolveFunction(function3);
         case 20:
            return resolveFunctionByFunction(function3);
         case 21:
            return resolveFunction2(function2);
         case 22:
            return resolveFunction(function2);
         case 23:
            return resolveFunctionByFunction(function2);
         case 24:
            return resolveFunction2(doubleValueByDouble(doubleController.memberRef));
         case 25:
            return resolveFunction(doubleValueByDouble(doubleController.memberRef));
         case 26:
            return resolveFunctionByFunction(doubleValueByDouble(doubleController.memberRef));
         case 27:
            return resolveFunction2(doubleValue(doubleController.memberRef));
         case 28:
            return resolveFunction(doubleValue(doubleController.memberRef));
         case 29:
            return resolveFunctionByFunction(doubleValue(doubleController.memberRef));
         case 30:
            return resolveFunction2(resolveFunctionByDouble(doubleController.memberRef));
         case 31:
            return resolveFunction(resolveFunctionByDouble(doubleController.memberRef));
         case 32:
            return resolveFunctionByFunction(resolveFunctionByDouble(doubleController.memberRef));
      }
   }

   private static Double resolveDouble(double[] offsetArray, Double offset) {
      return offsetArray[computeCount(offset, offsetArray, false)];
   }

   public static double sqrt(double offset) {
      return 1.0 - Math.sqrt(1.0 - offset * offset);
   }

   public static Function resolveFunction(Double offset) {
      double[] steps = resolveDoubleArray(offset != null ? offset.intValue() : 2);
      return item -> StepsGotHelper.resolveDouble(steps, (Double)item);
   }

   public static Function resolveFunction(Function mapper) {
      return item -> StepsGotHelper.resolveDoubleByFunction(mapper, (Double)item);
   }

   public static double computeValue(double offset) {
      return offset * offset;
   }

   private static Double resolveDouble(double offset, Double offset2) {
      return 30.25 * offset * Math.pow(offset2 - 0.5454545454545454, 2.0) + 1.0 - offset;
   }

   public static double computeValue(double posX, double posX2, double posY, double posZ) {
      return Math.min(Math.min(posX, posX2), Math.min(posY, posZ));
   }

   public static double cos(double offset) {
      return 1.0 - Math.cos((double)((float)(offset * Math.PI / 2.0)));
   }

   public static int computeCount(double offset, double[] offsetArray, boolean flag) {
      if (offset < offsetArray[0]) {
         return 0;
      } else if (offset > offsetArray[offsetArray.length - 1]) {
         return offsetArray.length - 1;
      } else {
         int count = 0;
         int count2 = offsetArray.length - 1;

         while (count2 - count != 1) {
            int count3 = count + (count2 - count) / 2;
            if (offset >= offsetArray[count3]) {
               count = count3;
            } else {
               count2 = count3;
            }
         }

         return flag ? count2 : count;
      }
   }

   private static Double resolveDouble5(double offset, Double offset2) {
      return Math.pow(offset2, offset);
   }

   private static Double resolveDouble2(double offset, Double offset2) {
      return 121.0 * offset * offset * Math.pow(offset2 - 0.8181818181818182, 2.0) + 1.0 - offset * offset;
   }

   private static Double resolveDoubleByDouble(double offset, Double offset2) {
      return 484.0 * offset * offset * offset * Math.pow(offset2 - 0.9545454545454546, 2.0) + 1.0 - offset * offset * offset;
   }

   public static Function resolveFunctionByFunction(Function mapper) {
      return item -> StepsGotHelper.resolveDouble(mapper, (Double)item);
   }

   public static Function doubleValue(Double offset) {
      double factor = (offset == null ? 1.0 : offset) * Math.PI;
      return item -> StepsGotHelper.resolveDouble3(factor, (Double)item);
   }

   public static double[] resolveDoubleArray(int count) {
      if (count < 2) {
         throw new IllegalArgumentException("steps must be > 2, got:" + count);
      } else {
         double offset = 1.0 / (double)count;
         AtomicInteger counter = new AtomicInteger();
         return DoubleStream.generate(() -> StepsGotHelper.getAndIncrement(counter, offset)).limit((long)count).toArray();
      }
   }

   private static Double resolveDouble(Double offset) {
      return offset >= 1.0 ? 1.0 : 0.0;
   }

   public static double computeValueByDouble(double offset) {
      return offset;
   }

   private static Double resolveDoubleByDouble(Double offset) {
      return 7.5625 * offset * offset;
   }

   public static Function resolveFunction2(Function mapper) {
      return mapper;
   }
}
