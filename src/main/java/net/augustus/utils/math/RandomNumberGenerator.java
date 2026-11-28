package net.augustus.utils.math;

public interface RandomNumberGenerator {
   long nextLong(long l, long l2);

   long nextLong(double d, double d2);

   int nextInt(int n, int n2);

   double nextDouble(double d, double d2);

   float nextSecureFloat(double d, double d2);

   double nextSecureDouble(double d, double d2);

   float nextFloat(double d, double d2);

   int nextInt(double d, double d2);

   double nextRandomSin();

   double nextSecureIntAsDouble(double d, double d2);

   int nextSecureInt(int n, int n2);

   float nextFloat(float f, float f2);
}
