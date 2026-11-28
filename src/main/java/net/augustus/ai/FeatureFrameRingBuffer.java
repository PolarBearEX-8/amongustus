package net.augustus.ai;


public final class FeatureFrameRingBuffer {
   private final int capacity;
   private int writeIndex;
   private int size;
   private final float[][] frames;
   private final int numFeatures;

   public FeatureFrameRingBuffer(int n, int n2) {
       super();
      if (n > 0 && n2 > 0) {
         this.capacity = n;
         this.numFeatures = n2;
         this.frames = new float[n][n2];
         this.writeIndex = 0;
         this.size = 0;
      } else {
         throw new IllegalArgumentException("capacity and numFeatures must be > 0");
      }
   }

   public int getSize() {
      return this.size;
   }

   public void push(float[] floats) {
      if (floats.length != this.numFeatures) {
         throw new IllegalArgumentException("frame length mismatch: got " + floats.length + ", expected " + this.numFeatures);
      } else {
         System.arraycopy(floats, 0, this.frames[this.writeIndex], 0, this.numFeatures);
         this.writeIndex = (this.writeIndex + 1) % this.capacity;
         if (this.size < this.capacity) {
            this.size++;
         }
      }
   }

   public float[] toFlatArray() {
      float[] floats = new float[this.capacity * this.numFeatures];
      int n = this.capacity - this.size;
      int n2 = (this.writeIndex - this.size + this.capacity) % this.capacity;

      for (int i = 0; i < this.size; i++) {
         int n3 = (n2 + i) % this.capacity;
         int n4 = (n + i) * this.numFeatures;
         System.arraycopy(this.frames[n3], 0, floats, n4, this.numFeatures);
      }

      return floats;
   }

   public void clear() {
      this.writeIndex = 0;
      this.size = 0;
   }

   public int getCapacity() {
      return this.capacity;
   }

   public int getNumFeatures() {
      return this.numFeatures;
   }
}
