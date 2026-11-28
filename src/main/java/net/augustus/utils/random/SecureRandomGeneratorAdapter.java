package net.augustus.utils.random;

import java.security.SecureRandom;
import org.apache.commons.math3.random.RandomGenerator;

public class SecureRandomGeneratorAdapter extends SecureRandom implements RandomGenerator {
   public void setSeed(int[] ints) {
   }

   public void setSeed(int n) {
   }
}
