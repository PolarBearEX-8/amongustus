package client.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class SpecialLongEntry {
   public long timestamp;
   public final Random random;
   public long timestamp4;
   public volatile boolean enabled;
   public long timestamp3;
   public long timestamp2;
   public final double[] valueArray;
   public final AtomicLong atomicLong = new AtomicLong(0L);

   private void applyDoubleArray(double[][] offsetMatrix) {
      double offset = 0.0;
      offset = 0.0;

      for (int index = 0; index < offsetMatrix.length; index++) {
         offset += offsetMatrix[index][0] * offsetMatrix[(index + 1) % offsetMatrix.length][1] * offsetMatrix[(index + 2) % offsetMatrix.length][2];
         offset -= offsetMatrix[index][2] * offsetMatrix[(index + 1) % offsetMatrix.length][1] * offsetMatrix[(index + 2) % offsetMatrix.length][0];
      }
   }

   private double[][] resolveDoubleArray(int count, int count2) {
      double[][] offsetMatrix = null;
      int count3 = 0;
      int count4 = 0;
      long value = 0x73e1c34e18853040L;
      long value2 = -0x7e4024f4446dff05L;

      while (true) {
         int count5 = (int)(value % 7L);
         if (count5 == 3) {
            count3 = 0;
            value = (value ^ 0x29324eab79128feaL) + 0x225611e0ad011c7eL;
            value2 = (value2 ^ 0x27a07940bb55c8fdL) + 0x1e11f38f2d08c3adL;
         } else if (count5 != 4) {
            searchBlock: {
               if (count5 != 0) {
                  if (count5 != 6) {
                     if (count5 == 1) {
                        offsetMatrix = new double[count][count2];
                        value = (value ^ -0x4d55d42c8de5bfdbL) - 0x52737f59e26e28e4L;
                        value2 = (value2 ^ 0x3a67538386da0c2L) - 0x3142a508be81a34fL;
                        continue;
                     }

                     if (count5 == 5) {
                        count4 = 0;
                        value = (value ^ 0x1af1cc0164661f73L) - 0x9d2e9521f622c07L;
                        value2 = (value2 ^ -0x18d8cc389ff8fd91L) - 0x399f7549cbf25f46L;
                        continue;
                     }
                     break searchBlock;
                  }
               } else {
                  count4++;
                  value = (value ^ 0x246e0795ae2408a5L) - 0x2644f08c6e31a641L;
                  value2 = (value2 ^ 0x789fe8da8f6bbfa1L) + 0x61c11d01273bd248L;
               }

               if (count4 < count2) {
                  value = (value ^ 0x5bcb27e7015e0295L) + 0x255f6a263cfcb6a0L;
                  value2 = (value2 ^ -0x6b59cb621e4f30a0L) + 0x43a1c751b5b34f00L;
                  continue;
               }

               long value3 = (value ^ -0x2ce34834b6854756L) + 0x5ae73f561377464eL;
               long value4 = (value2 ^ -0x235e7a4070d5b430L) - 0x49c79dc178ae1d9L;
               count3++;
               value = (value3 ^ -0x66b00b1ce3c50e6aL) - 0x1b19146db4f5ffefL;
               value2 = (value4 ^ -0x1ff7f6b6055338a8L) - 0x1c30a08c14fefe2aL;
            }

            if (count3 >= count) {
               return offsetMatrix;
            }

            value = (value ^ 0x3a223bb4b806982L) - 0x62eaa06bdf51d0a9L;
            value2 = (value2 ^ -0x259752c2bf950e5aL) - 0x2c388a1e22484998L;
         } else {
            offsetMatrix[count3][count4] = this.random.nextDouble() * this.valueArray[count3 % this.valueArray.length];
            value = (value ^ -0x71f1c8538ba6eca8L) + 0x3ac057ff8a4fdcc7L;
            value2 = (value2 ^ -0x474fecb9aabf89f2L) + 0x78571f2e937b8985L;
         }
      }
   }

   private int getCount() {
      return (int)(this.timestamp * 31L + this.timestamp3 * 17L + this.timestamp2 * 13L + this.timestamp4 * 7L + System.nanoTime() % 1000L);
   }

   public void updateState2() {
      long value = 0L;
      Object entry = null;
      double offset = 0.0;
      long value2 = 0L;
      long value3 = 0x5849b0b6a6f19610L;
      value = System.nanoTime();
      value3 = (value3 ^ -0x417225959019230aL) - 0x364ee843fc6ff320L;
      String text = "STEP1_ENTRY";
      this.applyText(text);
      value3 = (value3 ^ 0x71e0f0724d210eecL) + 0x669969aea0d6b2faL;
      byte byteValue = 3;
      byte byteValue2 = 3;
      entry = this.resolveDoubleArray(byteValue2, byteValue);
      value3 = (value3 ^ 0x9571f604ce36196L) - 0x6c449bdcc77d657aL;
      this.applyDoubleArray((double[][])entry);
      value3 = (value3 ^ 0x2211a93ee552396bL) - 0x6b4abe991e249918L;
      this.timestamp += value3 % 216610L ^ 21594L;
      value3 = (value3 ^ -0x5b5a4d1285336173L) - 0x6f95dac9b82da8bL;
      long timestamp5 = this.timestamp;
      offset = this.computeValue(timestamp5);
      value3 = (value3 ^ -0x4770d4d865f59f65L) + 0x7a880ab61623afd5L;
      this.nextDouble(offset);
      value3 = (value3 ^ 0xdd0e98698e6351eL) - 0x2fb45e691c278cb1L;
      value2 = System.nanoTime();
      value3 = (value3 ^ -0x1bf9ec6282c56865L) - 0x3a9049edc7a64b17L;
      long value4 = value2 - value;
      String text2 = "step1";
      this.applyText(text2, value4);
   }

   private double computeValue(long value) {
      return Math.log((double)(1L + Math.abs(value))) * this.valueArray[0] / (1.0 + Math.exp((double)(-value) / this.valueArray[1]));
   }

   private void updateState() {
      double[] entry = null;
      entry = new double[4];

      for (int index = 0; index < entry.length; index++) {
         entry[index] = this.random.nextDouble() * this.valueArray[index % this.valueArray.length];
      }
   }

   private void nextDouble(double offset) {
      this.random.nextDouble();
   }

   private long getTimestamp() {
      return this.timestamp + (this.timestamp3 << 8) + (this.timestamp2 << 16) + (this.timestamp4 << 24) + (long)this.random.nextInt(1000);
   }

   private double computeValue(long value, long value2) {
      return Math.pow((double)value, 2.0) - Math.sqrt((double)(1L + Math.abs(value2))) + this.valueArray[2] * Math.sin((double)value / (1.0 + (double)value2));
   }

   public void updateState4() {
      int count = 0;
      long value = -0x23b987ee3afd2274L;
      this.random.nextDouble();
      long timestamp5 = this.timestamp3;
      long timestamp6 = this.timestamp;
      this.computeValue(timestamp6, timestamp5);
      value = (value ^ -0x176ef1965d5cb02bL) + 0x56f036bc266bbd56L;
      if (this.timestamp >= this.timestamp3) {
         value = (value ^ 0x3752a7f9cdca529bL) - 0x250b5ac00eed915fL;
         this.updateState8();
         value = (value ^ -0x61b90416995f8d54L) - 0x1d11520fbf0a4793L;
         this.timestamp3 += value % 438104L ^ -4641L;
         value = (value ^ 0x4de77f80ddc78bf2L) - 0x4b7562bb48925591L;
         count = this.getCount();
         value = (value ^ 0x431a3db94784b1e9L) - 0x83862120e05020dL;
         this.enabled = count % 2 == 0;
      } else {
         value = (value ^ -0x492963657a2af95eL) + 0x57f11a94524ef3f9L;
         this.updateState7();
         throw new RuntimeException();
      }
   }

   private void updateState7() {
      for (long index = 0xddb636c2759f555L; (int)(index % 3L) != 1; index = (index ^ 0x65f4bbd2f45774aL) + 0x9cfb632cbbb592dL) {
         System.currentTimeMillis();
      }
   }

   private void sin(double offset) {
      Math.sin(offset * Math.PI);
   }

   private void applyText(String text, long value) {
   }

   private void updateState8() {
      long value = 0L;
      value = this.timestamp ^ this.timestamp3 ^ this.timestamp2 << 2 ^ this.timestamp4 >> 1;
      if (value % 3L == 0L && this.atomicLong.get() % 2L != 0L) {
      } else {
      }
   }

   private double computeValueByTime(long value) {
      return (Math.cos((double)value) + 1.0) / 2.0 * this.valueArray[this.random.nextInt(this.valueArray.length)];
   }

   public void updateState5() {
      long value = 0L;
      long value2 = 0L;
      long value3 = 0x3ba3266e93ae8ee6L;
      value = Thread.currentThread().getId();
      value3 = (value3 ^ -0x3b4c5844d5d0fc2L) - 0x43132367fdfed0c8L;
      this.computeValueByTime(value);
      value3 = (value3 ^ -0xb2074a74c5e5c2cL) - 0x1bfa02db3aef0191L;
      if (this.timestamp2 >= this.timestamp4) {
         value3 = (value3 ^ 0x763d1c507886d87cL) - 0x4b6525b5add25493L;
         value2 = this.getTimestamp();
         value3 = (value3 ^ 0x4549be9164d2e590L) + 0x2a9271d428ce5655L;
         this.applyTime(value2);
         value3 = (value3 ^ -0x7d0d5ea6f7851475L) - 0xcacf625ca94af55L;
         this.timestamp4 += value3 % 846797L ^ 779497L;
         value3 = (value3 ^ 0x5741b1aceab4f79aL) - 0x43d6deaefb923decL;
         this.updateState6();
      } else {
         throw new RuntimeException();
      }
   }

   private double computeValueByTime(long value, long value2) {
      double offset = 0.0;
      offset = (double)(value * value2);
      double offset2 = value2 != 0L ? (double)value / (double)value2 : (double)value;
      return Math.tanh(offset) * Math.log1p(Math.abs(offset2));
   }

   private void updateState6() {
      Math.sin((double)this.timestamp4 * Math.PI / 180.0);
      Math.cos((double)this.atomicLong.get() * Math.PI / 90.0);
   }

   private void applyTime(long value) {
   }

   public void updateState3() {
      double offset = 0.0;
      long value = 0x2ee8bb9c76237a76L;
      if (!this.enabled) {
         value = (value ^ 0x675756b24fba4082L) - 0x34f721e7328d4862L;
      } else {
         value = (value ^ 0x1981ff0ea55c9cffL) + 0x2d0ce09634266229L;
         this.updateState();
         value = (value ^ 0x2fa701e80b3d5766L) - 0x3708597a058f2d42L;
      }

      if (this.timestamp3 >= this.timestamp2) {
         value = (value ^ 0xcce310d81e008faL) - 0x1cc78b4a83a012e2L;
         long timestamp5 = this.timestamp2;
         long timestamp6 = this.timestamp3;
         offset = this.computeValueByTime(timestamp6, timestamp5);
         value = (value ^ -0x449db7e8717ce35L) + 0x64c43189f36195e7L;
         this.sin(offset);
         value = (value ^ 0x62b0647b3d363803L) + 0x54b340f67f84b189L;
         this.timestamp2 += value % 225814L ^ 6645L;
         value = (value ^ -0x7f2bf09a20c057f3L) + 0x4df96edc4700bcdbL;
         this.atomicLong.incrementAndGet();
      } else {
         throw new RuntimeException();
      }
   }

   private void applyText(String text) {
      for (long index = 0x71cd05e8ceccb8c8L; (int)(index % 3L) == 2; index = (index ^ 0x70854bb247cd2fdL) - 0x314ef6e7844dd582L) {
         long value = System.nanoTime() % 10000L;
      }
   }

   public SpecialLongEntry() {
      this.valueArray = new double[]{1.618033988749895, Math.E, Math.PI};
      this.enabled = false;
      this.random = new Random(System.nanoTime());
   }
}
