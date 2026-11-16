package client.util;

public class LongArrayHolder {
   public final long[] timestampArray2;
   public final long[] timestampArray = new long[5];

   private void applyTimeArray(long[][] valueMatrix) {
      int count = 0;
      int count2 = 0;
      long value = 0x6ae662a6e97bd236L;
      long value2 = -0x1599e85151b80ef6L;

      while (true) {
         int count3 = (int)(value % 6L);
         if (count3 != 5) {
            if (count3 == 3) {
               count2 = 0;
               value = (value ^ 0x352137e7f846adcfL) + 0x6ec32247dfbb79f4L;
               value2 = (value2 ^ 0x137bbd7f9cca3a86L) + 0x216cad53e1cfb15dL;
               continue;
            }

            if (count3 == 2) {
               count = 0;
               value = (value ^ 0x4b5298e87e3be193L) + 0x4003cad6054d9b1aL;
               value2 = (value2 ^ 0x9df7cbd59907016L) - 0x778ee846e90466c6L;
               continue;
            }

            if (count3 != 0) {
               if (count3 == 1) {
                  valueMatrix[count][count2] = valueMatrix[count][count2] ^ this.timestampArray2[count];
                  value = (value ^ -0x738137566903d728L) - 0x770ec492a0f7de67L;
                  value2 = (value2 ^ -0x7c23545c81c14422L) - 0x623195532a8d400dL;
                  continue;
               }
            } else {
               count2++;
               value = (value ^ -0x4f829221dc02f88eL) + 0x727f61cf793ef156L;
               value2 = (value2 ^ 0x6b6db5d6b3b80955L) + 0x15603f8d98e6700fL;
            }

            if (count2 < 5) {
               value = (value ^ -0x341f4bfb48d330f2L) - 0x55ae4d8ddc1ab00fL;
               value2 = (value2 ^ 0x7ad52f9f27ba55efL) - 0xee9acc96a71f744L;
               continue;
            }

            long value3 = (value ^ 0x5e2c4041a1a9d56bL) + 0x1b4f814c0ae6633dL;
            long value4 = (value2 ^ 0x3722218bbf425679L) + 0x42913330a59f66efL;
            count++;
            value = (value3 ^ -0x2ac7768cef8b8d4dL) - 0x376e0ccabf2386a8L;
            value2 = (value4 ^ -0x3f5a28ffba08fc8dL) + 0x5cb52f4653a17198L;
         }

         if (count >= 5) {
            return;
         }

         value = (value ^ -0x528ff06982110912L) + 0x6875787b1899fd76L;
         value2 = (value2 ^ -0x3e7720b473206f83L) - 0x21d6de90e9ad2e5fL;
      }
   }

   public void applyTimeArrayByTimeArray(long[][] valueMatrix) {
      this.applyTimeArray2(valueMatrix);
      this.updateState();
      this.applyTimeArray(valueMatrix);
   }

   private void applyTimeArray2(long[][] valueMatrix) {

      for (int index = 0; index < 5; index++) {
         this.timestampArray[index] = valueMatrix[index][0] ^ valueMatrix[index][1] ^ valueMatrix[index][2] ^ valueMatrix[index][3] ^ valueMatrix[index][4];
      }
   }

   private void updateState() {
      this.timestampArray2[0] = this.timestampArray[4] ^ Long.rotateLeft(this.timestampArray[1], 1);
      this.timestampArray2[1] = this.timestampArray[0] ^ Long.rotateLeft(this.timestampArray[2], 1);
      this.timestampArray2[2] = this.timestampArray[1] ^ Long.rotateLeft(this.timestampArray[3], 1);
      this.timestampArray2[3] = this.timestampArray[2] ^ Long.rotateLeft(this.timestampArray[4], 1);
      this.timestampArray2[4] = this.timestampArray[3] ^ Long.rotateLeft(this.timestampArray[0], 1);
   }

   public LongArrayHolder() {
      this.timestampArray2 = new long[5];
   }
}
