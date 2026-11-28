package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import net.minecraft.block.state.IBlockState;
import net.optifine.config.Config;

public class CacheObjectArray {
   private static ArrayDeque arrays = new ArrayDeque();
   private static int maxCacheSize = 10;

   private static long testNewObj(int n, int n2) {
      long l = System.currentTimeMillis();

      for (int i = 0; i < n2; i++) {
         IBlockState[] states = new IBlockState[n];
      }

      return System.currentTimeMillis() - l;
   }

   private static long testClone(int n, int n2) {
      long l = System.currentTimeMillis();
      int[] ints = new int[n];

      for (int i = 0; i < n2; i++) {
         int[] ints2 = (int[])ints.clone();
      }

      return System.currentTimeMillis() - l;
   }

   public static void main(String[] args) throws Exception {
      short s = 4096;
      int n = 500000;
      testNew(s, n);
      testClone(s, n);
      testNewObj(s, n);
      testCloneObj(s, n);
      testNewObjDyn(IBlockState.class, s, n);
      long l = testNew(s, n);
      long l2 = testClone(s, n);
      long l3 = testNewObj(s, n);
      long l4 = testCloneObj(s, n);
      long l5 = testNewObjDyn(IBlockState.class, s, n);
      Config.dbg("New: " + l);
      Config.dbg("Clone: " + l2);
      Config.dbg("NewObj: " + l3);
      Config.dbg("CloneObj: " + l4);
      Config.dbg("NewObjDyn: " + l5);
   }

   private static long testNewObjDyn(Class clazz, int n, int n2) {
      long l = System.currentTimeMillis();

      for (int i = 0; i < n2; i++) {
         Object[] array = (Object[])Array.newInstance(clazz, n);
      }

      return System.currentTimeMillis() - l;
   }

   public static synchronized void freeArray(int[] ints) {
      if (arrays.size() < maxCacheSize) {
         arrays.add(ints);
      }
   }

   private static long testNew(int n, int n2) {
      long l = System.currentTimeMillis();

      for (int i = 0; i < n2; i++) {
         int[] ints = (int[])Array.newInstance(int.class, n);
      }

      return System.currentTimeMillis() - l;
   }

   private static long testCloneObj(int n, int n2) {
      long l = System.currentTimeMillis();
      IBlockState[] states = new IBlockState[n];

      for (int i = 0; i < n2; i++) {
         IBlockState[] states2 = (IBlockState[])states.clone();
      }

      return System.currentTimeMillis() - l;
   }

   private static synchronized int[] allocateArray(int n) {
      int[] ints = (int[])arrays.pollLast();
      if (ints == null || ints.length < n) {
         ints = new int[n];
      }

      return ints;
   }
}
