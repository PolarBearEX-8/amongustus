package net.augustus.render;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class GlStateDiffDebugger {
   private static final Map GL_CONSTANT_NAMES;
   private static final int[] TRACKED_INTEGERS;
   private static final int[] TRACKED_CAPABILITIES;
   private static final int[] TRACKED_FLOATS;

   private static String resolveGlConstantName(int n) {
      return n == 0 ? "NONE/ZERO" : (String)GL_CONSTANT_NAMES.getOrDefault(n, "UNKNOWN_" + n);
   }

   static {
      TRACKED_CAPABILITIES = new int[]{
      3552,
      3553,
      32879,
      34067,
      3168,
      3169,
      3170,
      3171,
      2896,
      16384,
      16385,
      16386,
      16387,
      2903,
      2977,
      32826,
      3456,
      3042,
      3008,
      2929,
      2960,
      3089,
      2884,
      3024,
      2912,
      3057,
      3058,
      2832,
      2848,
      2881,
      10753,
      10754,
      32823,
      3479,
      32925,
      32826,
      34370,
      34371
      };
      TRACKED_INTEGERS = new int[]{
      2976,
      2900,
      2886,
      2885,
      2880,
      3041,
      3040,
      32969,
      32968,
      32971,
      32970,
      32777,
      32777,
      34877,
      2932,
      3009,
      2962,
      2964,
      2965,
      2966,
      3056,
      32872,
      32873,
      32874,
      34068,
      34016,
      34964,
      34965,
      35725,
      2930,
      3105,
      3152,
      3153,
      3154,
      3155,
      3156
      };
      TRACKED_FLOATS = new int[]{2833, 2849, 2816, 2818, 3106, 2931, 2961, 2914, 2915, 2916, 3010};
      GL_CONSTANT_NAMES = new HashMap();
      registerGlConstants(GL11.class);
      registerGlConstants(GL12.class);
      registerGlConstants(GL13.class);
      registerGlConstants(GL14.class);
      registerGlConstants(GL15.class);
      registerGlConstants(GL20.class);
   }

   private static void registerGlConstants(Class clazz) {
      for (Field field : clazz.getDeclaredFields()) {
         if (field.getType() == int.class) {
            try {
               field.setAccessible(true);
               int n = field.getInt(null);
               GL_CONSTANT_NAMES.putIfAbsent(n, field.getName());
            } catch (IllegalAccessException e) {
            }
         }
      }
   }

   public static GlStateDiffSnapshot captureState() {
      GlStateDiffSnapshot snapshot = new GlStateDiffSnapshot();
      for (int cap : TRACKED_CAPABILITIES) {
         try {
            snapshot.capabilities.put(cap, GL11.glIsEnabled(cap));
         } catch (Exception ignored) {
         }
      }
      for (int param : TRACKED_INTEGERS) {
         try {
            snapshot.integers.put(param, GL11.glGetInteger(param));
         } catch (Exception ignored) {
         }
      }
      for (int param : TRACKED_FLOATS) {
         try {
            snapshot.floats.put(param, GL11.glGetFloat(param));
         } catch (Exception ignored) {
         }
      }
      return snapshot;
   }

   public static boolean printStateDiff(GlStateDiffSnapshot glStateDiffSnapshot) {
      boolean flag = false;
      System.out.println("=== GL STATE DIFF START ===");

      for (int n : TRACKED_CAPABILITIES) {
         boolean flag2;
         try {
            flag2 = GL11.glIsEnabled(n);
         } catch (Exception e) {
            continue;
         }

         boolean orDefault = (Boolean)(glStateDiffSnapshot.capabilities.getOrDefault(n, flag2));
         if (flag2 != orDefault) {
            System.out.printf("DIFF: Cap %s | Alt: %b -> Neu: %b%n", resolveGlConstantName(n), orDefault, flag2);
            flag = true;
         }
      }

      for (int n2 : TRACKED_INTEGERS) {
         int n3;
         try {
            n3 = GL11.glGetInteger(n2);
         } catch (Exception e2) {
            continue;
         }

         int orDefault2 = (Integer)(glStateDiffSnapshot.integers.getOrDefault(n2, n3));
         if (n3 != orDefault2) {
            String str = resolveGlConstantName(orDefault2);
            String str2 = resolveGlConstantName(n3);
            System.out.printf("DIFF: Int %s | Alt: %s (%d) -> Neu: %s (%d)%n", resolveGlConstantName(n2), str, orDefault2, str2, n3);
            flag = true;
         }
      }

      for (int n4 : TRACKED_FLOATS) {
         float f;
         try {
            f = GL11.glGetFloat(n4);
         } catch (Exception e3) {
            continue;
         }

         float orDefault3 = (Float)(glStateDiffSnapshot.floats.getOrDefault(n4, f));
         if (Math.abs(f - orDefault3) > 1.0E-4F) {
            System.out.printf("DIFF: Float %s | Alt: %.4f -> Neu: %.4f%n", resolveGlConstantName(n4), orDefault3, f);
            flag = true;
         }
      }

      if (!flag) {
         System.out.println("Keine Unterschiede gefunden.");
      }

      System.out.println("=== GL STATE DIFF END ===");
      return flag;
   }

}
