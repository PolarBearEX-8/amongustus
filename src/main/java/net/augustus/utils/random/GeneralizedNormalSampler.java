package net.augustus.utils.random;

import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.special.Gamma;

public class GeneralizedNormalSampler {
   private RandomGenerator randomGenerator;
   private double alpha;
   private double beta;
   private double stdDev;
   private GammaDistribution gammaDistribution;

   public void setStdDev(double d) {
      if (this.stdDev != d) {
         this.stdDev = d;
         this.alpha = computeAlpha(d, this.beta);
      }
   }

   public GeneralizedNormalSampler() {
       super();
      this.randomGenerator = new SecureRandomGeneratorAdapter();
      this.stdDev = 1.0;
      this.setBeta(1.0);
   }

   public void setBeta(double d) {
      if (this.beta != d) {
         this.beta = d;
         double d2 = 1.0 / d;
         double d3 = 1.0;
         this.gammaDistribution = new GammaDistribution(this.randomGenerator, d2, d3);
         this.alpha = computeAlpha(this.stdDev, d);
      }
   }

   public double sample(double d) {
      double d3 = Math.pow(this.gammaDistribution.sample(), 1.0 / this.beta);
      double d4 = this.randomGenerator.nextBoolean() ? 1.0 : -1.0;
      return d + this.alpha * d4 * d3;
   }

   private static double computeAlpha(double d, double d2) {
      double d3 = Gamma.gamma(1.0 / d2);
      double d4 = Gamma.gamma(3.0 / d2);
      return Math.sqrt(d * d * d3 / d4);
   }
}
