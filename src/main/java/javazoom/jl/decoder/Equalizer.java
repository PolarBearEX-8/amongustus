package javazoom.jl.decoder;

public final class Equalizer {
   public static final float BAND_NOT_PRESENT = Float.NEGATIVE_INFINITY;
   public static final Equalizer PASS_THRU_EQ = new Equalizer();
   private static final int BANDS = 32;
   private final float[] settings = new float[32];

   public float getBand(int n) {
      float f = 0.0F;
      if (n >= 0 && n < 32) {
         f = this.settings[n];
      }

      return f;
   }

   public void setFrom2(EqualizerFunction equalizerFunction) {
      this.reset();
      int n = 32;

      for (int i = 0; i < n; i++) {
         this.settings[i] = this.limit(equalizerFunction.getBand(i));
      }
   }

   public Equalizer() {
   }

   public void setFrom(Equalizer equalizer) {
      if (equalizer != this) {
         this.setFrom3(equalizer.settings);
      }
   }

   public float[] getBandFactors() {
      float[] floats = new float[32];
      int n = 0;

      for (int i = 32; n < i; n++) {
         floats[n] = this.getBandFactor(this.settings[n]);
      }

      return floats;
   }

   public void setFrom3(float[] floats) {
      this.reset();
      int n = floats.length > 32 ? 32 : floats.length;

      for (int i = 0; i < n; i++) {
         this.settings[i] = this.limit(floats[i]);
      }
   }

   public Equalizer(EqualizerFunction equalizerFunction) {
      this.setFrom2(equalizerFunction);
   }

   public float setBand(int n, float f) {
      float f2 = 0.0F;
      if (n >= 0 && n < 32) {
         f2 = this.settings[n];
         this.settings[n] = this.limit(f);
      }

      return f2;
   }

   public void reset() {
      for (int i = 0; i < 32; i++) {
         this.settings[i] = 0.0F;
      }
   }

   private float limit(float f) {
      if (f == Float.NEGATIVE_INFINITY) {
         return f;
      } else if (f > 1.0F) {
         return 1.0F;
      } else {
         return f < -1.0F ? -1.0F : f;
      }
   }

   public int getSettings() {
      return this.settings.length;
   }

   public float getBandFactor(float f) {
      return f == Float.NEGATIVE_INFINITY ? 0.0F : (float)Math.pow(2.0, (double)f);
   }

   public Equalizer(float[] floats) {
      this.setFrom3(floats);
   }
}
