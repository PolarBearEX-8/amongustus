package net.augustus.utils.random;


import net.augustus.utils.math.RandomNumberGenerator;
public class DeterministicRandomProvider implements RandomNumberGenerator {
   @Override
   public int nextInt(int min, int max) {
      return min;
   }

   @Override
   public float nextSecureFloat(double min, double max) {
      return (float)max;
   }

   @Override
   public float nextFloat(float min, float max) {
      return max;
   }

   @Override
   public double nextDouble(double min, double max) {
      return max;
   }

   @Override
   public long nextLong(long min, long max) {
      return max;
   }

   @Override
   public double nextRandomSin() {
      return 0.5;
   }

   @Override
   public long nextLong(double min, double max) {
      return (long)min;
   }

   @Override
   public int nextSecureInt(int min, int max) {
      return min;
   }

   @Override
   public double nextSecureIntAsDouble(double d, double d2) {
      return d;
   }

   @Override
   public double nextSecureDouble(double min, double max) {
      return max;
   }

   @Override
   public float nextFloat(double min, double max) {
      return (float)min;
   }

   @Override
   public int nextInt(double min, double max) {
      return (int)max;
   }
}
