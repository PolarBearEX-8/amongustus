package client.render;

import client.setting.BlockSolidType;
import client.setting.RenderBooleanProperty;
import client.util.AltLoggerEntry;
import client.util.AuxiliaryLoggerEntry;
import client.util.BaseComparatorAdapter;
import client.util.BaseIBlockStateEntry;
import client.util.BitSetHolder;
import client.util.BridgeIntArrayEntry;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.InternalLongArrayEntry;
import client.util.InternalPngManager;
import client.util.NativeIntArrayEntry;
import client.util.ServiceStringEntry;
import client.util.VectorMatcher;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {
   public ByteBuffer byteBuffer;
   public IntBuffer intBuffer;
   public ShortBuffer shortBuffer;
   public FloatBuffer floatBuffer;
   public int count2;
   public AltLoggerEntry altLoggerEntry;
   public int count;
   public boolean enabled;
   public int count3;
   public double value2;
   public double value;
   public double value3;
   public AuxiliaryLoggerEntry auxiliaryLoggerEntry;
   public boolean enabled3;
   public BlockSolidType blockSolidType = null;
   public boolean[] enabledArray = new boolean[256];
   public ServiceStringEntry[] serviceStringEntryArray2 = null;
   public ServiceStringEntry[] serviceStringEntryArray = null;
   public ServiceStringEntry serviceStringEntry = null;
   public InternalLongArrayEntry internalLongArrayEntry;
   public BaseIBlockStateEntry baseIBlockStateEntry = null;
   public BitSet bitSet = null;
   public BitSet bitSet2 = new BitSet();
   public boolean enabled2 = false;
   public ByteBuffer byteBuffer2;

   public void applyFloatByFloat(float posX, float posY, float posZ) {
      int count4 = this.count2 / 4;
      float[] factorArray = new float[count4];

      for (int index = 0; index < count4; index++) {
         factorArray[index] = computeFactor(
            this.floatBuffer,
            (float)((double)posX + this.value2),
            (float)((double)posY + this.value),
            (float)((double)posZ + this.value3),
            this.auxiliaryLoggerEntry.getCountAsIndex(),
            index * this.auxiliaryLoggerEntry.getCount2()
         );
      }

      Integer[] countArray = new Integer[count4];

      for (int index2 = 0; index2 < countArray.length; index2++) {
         countArray[index2] = index2;
      }

      Arrays.sort(countArray, new BaseComparatorAdapter(this, factorArray));
      BitSet bitSet3 = new BitSet();
      int count5 = this.auxiliaryLoggerEntry.getCount2();
      int[] countArray2 = new int[count5];

      for (int index3 = 0; (index3 = bitSet3.nextClearBit(index3)) < countArray.length; index3++) {
         int count6 = countArray[index3];
         if (count6 != index3) {
            ((Buffer)this.intBuffer).limit(count6 * count5 + count5);
            ((Buffer)this.intBuffer).position(count6 * count5);
            this.intBuffer.get(countArray2);

            for (int index4 = countArray[count6]; count6 != index3; index4 = countArray[index4]) {
               ((Buffer)this.intBuffer).limit(index4 * count5 + count5);
               ((Buffer)this.intBuffer).position(index4 * count5);
               IntBuffer buffer = this.intBuffer.slice();
               ((Buffer)this.intBuffer).limit(count6 * count5 + count5);
               ((Buffer)this.intBuffer).position(count6 * count5);
               this.intBuffer.put(buffer);
               bitSet3.set(count6);
               count6 = index4;
            }

            ((Buffer)this.intBuffer).limit(index3 * count5 + count5);
            ((Buffer)this.intBuffer).position(index3 * count5);
            this.intBuffer.put(countArray2);
         }

         bitSet3.set(index3);
      }

      ((Buffer)this.intBuffer).limit(this.intBuffer.capacity());
      ((Buffer)this.intBuffer).position(this.getCount2());
      if (this.serviceStringEntryArray2 != null) {
         ServiceStringEntry[] serviceStringEntryArray3 = new ServiceStringEntry[this.count2 / 4];
         int count7 = this.auxiliaryLoggerEntry.getCount2() / 4;

         for (int index5 = 0; index5 < countArray.length; index5++) {
            int count8 = countArray[index5];
            serviceStringEntryArray3[index5] = this.serviceStringEntryArray2[count8];
         }

         System.arraycopy(serviceStringEntryArray3, 0, this.serviceStringEntryArray2, 0, serviceStringEntryArray3.length);
      }
   }

   private void renderElement4() {
      this.count++;
      this.count = this.count % this.auxiliaryLoggerEntry.size();
      this.altLoggerEntry = this.auxiliaryLoggerEntry.get(this.count);
      if (this.altLoggerEntry.getRenderBooleanProperty() == RenderBooleanProperty.PADDING) {
         this.renderElement4();
      }
   }

   public BridgeIntArrayEntry getBridgeIntArrayEntry() {
      ((Buffer)this.intBuffer).rewind();
      int count4 = this.getCount2();
      ((Buffer)this.intBuffer).limit(count4);
      int[] countArray = new int[count4];
      this.intBuffer.get(countArray);
      ((Buffer)this.intBuffer).limit(this.intBuffer.capacity());
      ((Buffer)this.intBuffer).position(count4);
      ServiceStringEntry[] serviceStringEntryArray3 = null;
      if (this.serviceStringEntryArray2 != null) {
         int count5 = this.count2 / 4;
         serviceStringEntryArray3 = new ServiceStringEntry[count5];
         System.arraycopy(this.serviceStringEntryArray2, 0, serviceStringEntryArray3, 0, count5);
      }

      return new BridgeIntArrayEntry(this, countArray, new AuxiliaryLoggerEntry(this.auxiliaryLoggerEntry), serviceStringEntryArray3);
   }

   public int getCount2() {
      return this.count2 * this.auxiliaryLoggerEntry.getCountAsIndex();
   }

   public void applyServiceStringEntry(ServiceStringEntry serviceStringEntry2) {
      if (this.bitSet != null && serviceStringEntry2 != null && serviceStringEntry2.getCount11() >= 0) {
         this.bitSet.set(serviceStringEntry2.getCount11());
      }

      if (this.serviceStringEntryArray2 != null) {
         int count4 = this.count2 / 4;
         this.serviceStringEntryArray2[count4 - 1] = serviceStringEntry2;
      }
   }

   public void applyIndex(int count4, int count5, int count6, int count7) {
      int count8 = (this.count2 - 4) * this.auxiliaryLoggerEntry.getCountAsIndex() + this.auxiliaryLoggerEntry.getInt(1) / 4;
      int count9 = this.auxiliaryLoggerEntry.getCount2() >> 2;
      this.intBuffer.put(count8, count4);
      this.intBuffer.put(count8 + count9, count5);
      this.intBuffer.put(count8 + count9 * 2, count6);
      this.intBuffer.put(count8 + count9 * 3, count7);
   }

   private void applyIndex(int count4, int count5) {
      int count6 = count5 - count4;
      if (count6 > 0) {
         int count7 = count4 * 4;
         int count8 = count6 * 4;
         GL11.glDrawArrays(this.count3, count7, count8);
      }
   }

   public void updateState() {
      this.count2++;
      this.applyIndexByIndex(this.auxiliaryLoggerEntry.getCountAsIndex());
      this.count = 0;
      this.altLoggerEntry = this.auxiliaryLoggerEntry.get(this.count);
      if (BuildManager.canExecute()) {
         InternalLongArrayEntry.applyWorldRenderer6(this);
      }
   }

   public void applyDouble(double posX, double posY, double posZ) {
      this.value2 = posX;
      this.value = posY;
      this.value3 = posZ;
   }

   public WorldRenderer getWorldRenderer(double posX, double posY, double posZ) {
      if (BuildManager.canExecute()) {
         InternalLongArrayEntry.applyWorldRenderer5(this);
      }

      int count4 = this.count2 * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getIntByIndex(this.count);
      switch (NativeIntArrayEntry.countArray[this.altLoggerEntry.getCombatValueProperty().ordinal()]) {
         case 1:
            this.byteBuffer.putFloat(count4, (float)(posX + this.value2));
            this.byteBuffer.putFloat(count4 + 4, (float)(posY + this.value));
            this.byteBuffer.putFloat(count4 + 8, (float)(posZ + this.value3));
            break;
         case 2:
         case 3:
            this.byteBuffer.putInt(count4, Float.floatToRawIntBits((float)(posX + this.value2)));
            this.byteBuffer.putInt(count4 + 4, Float.floatToRawIntBits((float)(posY + this.value)));
            this.byteBuffer.putInt(count4 + 8, Float.floatToRawIntBits((float)(posZ + this.value3)));
            break;
         case 4:
         case 5:
            this.byteBuffer.putShort(count4, (short)((int)(posX + this.value2)));
            this.byteBuffer.putShort(count4 + 2, (short)((int)(posY + this.value)));
            this.byteBuffer.putShort(count4 + 4, (short)((int)(posZ + this.value3)));
            break;
         case 6:
         case 7:
            this.byteBuffer.put(count4, (byte)((int)(posX + this.value2)));
            this.byteBuffer.put(count4 + 1, (byte)((int)(posY + this.value)));
            this.byteBuffer.put(count4 + 2, (byte)((int)(posZ + this.value3)));
      }

      this.renderElement4();
      return this;
   }

   public void applyFloat(float posX, float posY, float posZ) {
      int count4 = (byte)((int)(posX * 127.0F)) & 255;
      int count5 = (byte)((int)(posY * 127.0F)) & 255;
      int count6 = (byte)((int)(posZ * 127.0F)) & 255;
      int count7 = count4 | count5 << 8 | count6 << 16;
      int count8 = this.auxiliaryLoggerEntry.getCount2() >> 2;
      int count9 = (this.count2 - 4) * count8 + this.auxiliaryLoggerEntry.getCount() / 4;
      this.intBuffer.put(count9, count7);
      this.intBuffer.put(count9 + count8, count7);
      this.intBuffer.put(count9 + count8 * 2, count7);
      this.intBuffer.put(count9 + count8 * 3, count7);
   }

   public int computeCount(int count4) {
      return ((this.count2 - count4) * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getCount3()) / 4;
   }

   public WorldRenderer resolveWorldRenderer(int count4, int count5, int count6, int count7) {
      if (this.enabled) {
         return this;
      } else {
         int count8 = this.count2 * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getIntByIndex(this.count);
         switch (NativeIntArrayEntry.countArray[this.altLoggerEntry.getCombatValueProperty().ordinal()]) {
            case 1:
               this.byteBuffer.putFloat(count8, (float)count4 / 255.0F);
               this.byteBuffer.putFloat(count8 + 4, (float)count5 / 255.0F);
               this.byteBuffer.putFloat(count8 + 8, (float)count6 / 255.0F);
               this.byteBuffer.putFloat(count8 + 12, (float)count7 / 255.0F);
               break;
            case 2:
            case 3:
               this.byteBuffer.putFloat(count8, (float)count4);
               this.byteBuffer.putFloat(count8 + 4, (float)count5);
               this.byteBuffer.putFloat(count8 + 8, (float)count6);
               this.byteBuffer.putFloat(count8 + 12, (float)count7);
               break;
            case 4:
            case 5:
               this.byteBuffer.putShort(count8, (short)count4);
               this.byteBuffer.putShort(count8 + 2, (short)count5);
               this.byteBuffer.putShort(count8 + 4, (short)count6);
               this.byteBuffer.putShort(count8 + 6, (short)count7);
               break;
            case 6:
            case 7:
               if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                  this.byteBuffer.put(count8, (byte)count4);
                  this.byteBuffer.put(count8 + 1, (byte)count5);
                  this.byteBuffer.put(count8 + 2, (byte)count6);
                  this.byteBuffer.put(count8 + 3, (byte)count7);
               } else {
                  this.byteBuffer.put(count8, (byte)count7);
                  this.byteBuffer.put(count8 + 1, (byte)count6);
                  this.byteBuffer.put(count8 + 2, (byte)count5);
                  this.byteBuffer.put(count8 + 3, (byte)count4);
               }
         }

         this.renderElement4();
         return this;
      }
   }

   public int getCountAsIndex() {
      return this.enabled2 ? 4 : this.count3;
   }

   public WorldRenderer resolveWorldRenderer(float posX, float posY, float posZ) {
      int count4 = this.count2 * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getIntByIndex(this.count);
      switch (NativeIntArrayEntry.countArray[this.altLoggerEntry.getCombatValueProperty().ordinal()]) {
         case 1:
            this.byteBuffer.putFloat(count4, posX);
            this.byteBuffer.putFloat(count4 + 4, posY);
            this.byteBuffer.putFloat(count4 + 8, posZ);
            break;
         case 2:
         case 3:
            this.byteBuffer.putInt(count4, (int)posX);
            this.byteBuffer.putInt(count4 + 4, (int)posY);
            this.byteBuffer.putInt(count4 + 8, (int)posZ);
            break;
         case 4:
         case 5:
            this.byteBuffer.putShort(count4, (short)((int)(posX * 32767.0F) & 65535));
            this.byteBuffer.putShort(count4 + 2, (short)((int)(posY * 32767.0F) & 65535));
            this.byteBuffer.putShort(count4 + 4, (short)((int)(posZ * 32767.0F) & 65535));
            break;
         case 6:
         case 7:
            this.byteBuffer.put(count4, (byte)((int)(posX * 127.0F) & 0xFF));
            this.byteBuffer.put(count4 + 1, (byte)((int)(posY * 127.0F) & 0xFF));
            this.byteBuffer.put(count4 + 2, (byte)((int)(posZ * 127.0F) & 0xFF));
      }

      this.renderElement4();
      return this;
   }

   public double getValue3() {
      return this.value3;
   }

   public void applyFloat(float posX, float posY, float posZ, int count4) {
      int count5 = this.computeCount(count4);
      int intItem = MathHelper.getInt12((int)(posX * 255.0F), 0, 255);
      int intItem2 = MathHelper.getInt12((int)(posY * 255.0F), 0, 255);
      int intItem3 = MathHelper.getInt12((int)(posZ * 255.0F), 0, 255);
      this.applyIndex(count5, intItem, intItem2, intItem3, 255);
   }

   public void renderElement() {
      if (this.serviceStringEntryArray2 != null) {
         int countAsIndex = BuildManager.getMc().getListenerAdapter27().getCountAsIndex();
         if (this.enabledArray.length <= countAsIndex) {
            this.enabledArray = new boolean[countAsIndex + 1];
         }

         Arrays.fill(this.enabledArray, false);
         int count4 = 0;
         int count5 = -1;
         int count6 = this.count2 / 4;

         for (int index = 0; index < count6; index++) {
            ServiceStringEntry serviceStringEntry2 = this.serviceStringEntryArray2[index];
            if (serviceStringEntry2 != null) {
               int count7 = serviceStringEntry2.getCount8();
               if (!this.enabledArray[count7]) {
                  if (serviceStringEntry2 == InternalPngManager.serviceStringEntry) {
                     if (count5 < 0) {
                        count5 = index;
                     }
                  } else {
                     index = this.computeCount(serviceStringEntry2, index) - 1;
                     count4++;
                     if (this.blockSolidType != BlockSolidType.TRANSLUCENT) {
                        this.enabledArray[count7] = true;
                     }
                  }
               }
            }
         }

         if (count5 >= 0) {
            this.computeCount(InternalPngManager.serviceStringEntry, count5);
            count4++;
         }
      }
   }

   private int computeCount(ServiceStringEntry serviceStringEntry2, int count4) {
      GL11.glBindTexture(3553, serviceStringEntry2.count7);
      int count5 = -1;
      int count6 = -1;
      int count7 = this.count2 / 4;

      for (int index = count4; index < count7; index++) {
         if (this.serviceStringEntryArray2[index] == serviceStringEntry2) {
            if (count6 < 0) {
               count6 = index;
            }
         } else if (count6 >= 0) {
            this.applyIndex(count6, index);
            if (this.blockSolidType == BlockSolidType.TRANSLUCENT) {
               return index;
            }

            count6 = -1;
            if (count5 < 0) {
               count5 = index;
            }
         }
      }

      if (count6 >= 0) {
         this.applyIndex(count6, count7);
      }

      if (count5 < 0) {
         count5 = count7;
      }

      return count5;
   }

   private int getCount() {
      return this.intBuffer.capacity() * 4 / (this.auxiliaryLoggerEntry.getCountAsIndex() * 4);
   }

   public WorldRenderer resolveWorldRenderer(int count4, int count5) {
      int count6 = this.count2 * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getIntByIndex(this.count);
      switch (NativeIntArrayEntry.countArray[this.altLoggerEntry.getCombatValueProperty().ordinal()]) {
         case 1:
            this.byteBuffer.putFloat(count6, (float)count4);
            this.byteBuffer.putFloat(count6 + 4, (float)count5);
            break;
         case 2:
         case 3:
            this.byteBuffer.putInt(count6, count4);
            this.byteBuffer.putInt(count6 + 4, count5);
            break;
         case 4:
         case 5:
            this.byteBuffer.putShort(count6, (short)count5);
            this.byteBuffer.putShort(count6 + 2, (short)count4);
            break;
         case 6:
         case 7:
            this.byteBuffer.put(count6, (byte)count5);
            this.byteBuffer.put(count6 + 1, (byte)count4);
      }

      this.renderElement4();
      return this;
   }

   public void applyServiceStringEntryByServiceStringEnt(ServiceStringEntry serviceStringEntry2) {
      if (this.bitSet != null && serviceStringEntry2 != null && serviceStringEntry2.getCount11() >= 0) {
         this.bitSet.set(serviceStringEntry2.getCount11());
      }

      if (this.serviceStringEntryArray2 != null) {
         this.serviceStringEntry = serviceStringEntry2;
      }
   }

   public void updateAuxiliaryLoggerEntry(int count4, AuxiliaryLoggerEntry auxiliaryLoggerEntry2) {
      if (this.enabled3) {
         throw new IllegalStateException("Already building!");
      } else {
         this.enabled3 = true;
         this.renderElement5();
         this.count3 = count4;
         this.auxiliaryLoggerEntry = auxiliaryLoggerEntry2;
         this.altLoggerEntry = auxiliaryLoggerEntry2.get(this.count);
         this.enabled = false;
         ((Buffer)this.byteBuffer).limit(this.byteBuffer.capacity());
         if (BuildManager.canExecute()) {
            InternalLongArrayEntry.applyWorldRenderer4(this);
         }

         if (BuildManager.isActive46()) {
            if (this.blockSolidType != null) {
               if (this.serviceStringEntryArray2 == null) {
                  this.serviceStringEntryArray2 = this.serviceStringEntryArray;
               }

               if (this.serviceStringEntryArray2 == null || this.serviceStringEntryArray2.length < this.getCount()) {
                  this.serviceStringEntryArray2 = new ServiceStringEntry[this.getCount()];
               }
            }
         } else {
            if (this.serviceStringEntryArray2 != null) {
               this.serviceStringEntryArray = this.serviceStringEntryArray2;
            }

            this.serviceStringEntryArray2 = null;
         }
      }
   }

   public void applyFloatByFloat(float posX, float posY, float posZ, int count4) {
      int count5 = this.computeCount(count4);
      int count6 = -1;
      if (!this.enabled) {
         int count7 = this.intBuffer.get(count5);
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int count8 = (int)((float)(count7 & 0xFF) * posX);
            int count9 = (int)((float)(count7 >> 8 & 0xFF) * posY);
            int count10 = (int)((float)(count7 >> 16 & 0xFF) * posZ);
            count6 = count7 & 0xFF000000 | count10 << 16 | count9 << 8 | count8;
         } else {
            int count11 = (int)((float)(count7 >> 24 & 0xFF) * posX);
            int count12 = (int)((float)(count7 >> 16 & 0xFF) * posY);
            int count13 = (int)((float)(count7 >> 8 & 0xFF) * posZ);
            count6 = count7 & 0xFF | count11 << 24 | count12 << 16 | count13 << 8;
         }
      }

      this.intBuffer.put(count5, count6);
   }

   public void applyIndex(int count4) {
      for (int index = 0; index < 4; index++) {
         this.applyIndexByIndex(count4, index + 1);
      }
   }

   public WorldRenderer resolveWorldRenderer(float posX, float posX2, float posY, float posZ) {
      return this.resolveWorldRenderer((int)(posX * 255.0F), (int)(posX2 * 255.0F), (int)(posY * 255.0F), (int)(posZ * 255.0F));
   }

   public WorldRenderer getWorldRenderer6(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      if (this.serviceStringEntry != null && this.serviceStringEntryArray2 != null) {
         offset3 = (double)this.serviceStringEntry.computeFactorByFloat((float)offset);
         offset4 = (double)this.serviceStringEntry.computeFactor((float)offset2);
         this.serviceStringEntryArray2[this.count2 / 4] = this.serviceStringEntry;
      }

      int count4 = this.count2 * this.auxiliaryLoggerEntry.getCount2() + this.auxiliaryLoggerEntry.getIntByIndex(this.count);
      switch (NativeIntArrayEntry.countArray[this.altLoggerEntry.getCombatValueProperty().ordinal()]) {
         case 1:
            this.byteBuffer.putFloat(count4, (float)offset3);
            this.byteBuffer.putFloat(count4 + 4, (float)offset4);
            break;
         case 2:
         case 3:
            this.byteBuffer.putInt(count4, (int)offset3);
            this.byteBuffer.putInt(count4 + 4, (int)offset4);
            break;
         case 4:
         case 5:
            this.byteBuffer.putShort(count4, (short)((int)offset4));
            this.byteBuffer.putShort(count4 + 2, (short)((int)offset3));
            break;
         case 6:
         case 7:
            this.byteBuffer.put(count4, (byte)((int)offset4));
            this.byteBuffer.put(count4 + 1, (byte)((int)offset3));
      }

      this.renderElement4();
      return this;
   }

   public void renderElement3() {
      this.enabled = true;
   }

   public boolean isEnabled3() {
      return this.enabled3;
   }

   public BaseIBlockStateEntry resolveBaseIBlockStateEntry(IBlockState blockState, BlockPos blockPos) {
      if (this.baseIBlockStateEntry == null) {
         this.baseIBlockStateEntry = new BaseIBlockStateEntry(blockState, blockPos);
         return this.baseIBlockStateEntry;
      } else {
         this.baseIBlockStateEntry.applyIBlockState(blockState, blockPos);
         return this.baseIBlockStateEntry;
      }
   }

   public void renderElement5() {
      this.count2 = 0;
      this.altLoggerEntry = null;
      this.count = 0;
      this.serviceStringEntry = null;
      if (BitSetHolder.isActive()) {
         if (this.bitSet == null) {
            this.bitSet = this.bitSet2;
         }

         this.bitSet.clear();
      } else if (this.bitSet != null) {
         this.bitSet = null;
      }

      this.enabled2 = false;
   }

   public void applyFloat2(float posX, float posY, float posZ) {
      for (int index = 0; index < 4; index++) {
         this.applyFloat(posX, posY, posZ, index + 1);
      }
   }

   public ByteBuffer getByteBuffer() {
      return this.enabled2 ? this.byteBuffer2 : this.byteBuffer;
   }

   public int getCount3() {
      return this.enabled2 ? this.count2 / 4 * 6 : this.count2;
   }

   public void renderElement6() {
      if (!this.enabled3) {
         throw new IllegalStateException("Not building!");
      } else {
         this.enabled3 = false;
         ((Buffer)this.byteBuffer).position(0);
         ((Buffer)this.byteBuffer).limit(this.getCount2() * 4);
      }
   }

   public double getValue2() {
      return this.value2;
   }

   public boolean isActive() {
      return this.serviceStringEntryArray2 != null;
   }

   public double getValue() {
      return this.value;
   }

   public void applyBridgeIntArrayEntry(BridgeIntArrayEntry bridgeIntArrayEntry) {
      ((Buffer)this.intBuffer).clear();
      this.applyIndexByIndex(bridgeIntArrayEntry.getCountArray().length);
      this.intBuffer.put(bridgeIntArrayEntry.getCountArray());
      this.count2 = bridgeIntArrayEntry.getCount();
      this.auxiliaryLoggerEntry = new AuxiliaryLoggerEntry(bridgeIntArrayEntry.getAuxiliaryLoggerEntry());
      if (BridgeIntArrayEntry.resolveServiceStringEntryArray(bridgeIntArrayEntry) != null) {
         if (this.serviceStringEntryArray2 == null) {
            this.serviceStringEntryArray2 = this.serviceStringEntryArray;
         }

         if (this.serviceStringEntryArray2 == null || this.serviceStringEntryArray2.length < this.getCount()) {
            this.serviceStringEntryArray2 = new ServiceStringEntry[this.getCount()];
         }

         ServiceStringEntry[] serviceStringEntryArray3 = BridgeIntArrayEntry.resolveServiceStringEntryArray(bridgeIntArrayEntry);
         System.arraycopy(serviceStringEntryArray3, 0, this.serviceStringEntryArray2, 0, serviceStringEntryArray3.length);
      } else {
         if (this.serviceStringEntryArray2 != null) {
            this.serviceStringEntryArray = this.serviceStringEntryArray2;
         }

         this.serviceStringEntryArray2 = null;
      }
   }

   public void applyFloat(float posX, float posX2, float posY, float posZ, int count4) {
      int count5 = this.computeCount(count4);
      int count6 = -1;
      if (!this.enabled) {
         int count7 = this.intBuffer.get(count5);
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            int count8 = (int)((float)(count7 & 0xFF) * posX);
            int count9 = (int)((float)(count7 >> 8 & 0xFF) * posX2);
            int count10 = (int)((float)(count7 >> 16 & 0xFF) * posY);
            count6 = (int)((float)(count7 >> 24 & 0xFF) * posZ) << 24 | count10 << 16 | count9 << 8 | count8;
         } else {
            int count11 = (int)((float)(count7 >> 24 & 0xFF) * posX);
            int count12 = (int)((float)(count7 >> 16 & 0xFF) * posX2);
            int count13 = (int)((float)(count7 >> 8 & 0xFF) * posY);
            int count14 = (int)((float)(count7 & 0xFF) * posZ);
            count6 = count11 << 24 | count12 << 16 | count13 << 8 | count14;
         }
      }

      this.intBuffer.put(count5, count6);
   }

   public BlockSolidType getBlockSolidType() {
      return this.blockSolidType;
   }

   public void applyIndex(int count4, int count5, int count6, int count7, int count8) {
      if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
         this.intBuffer.put(count4, count8 << 24 | count7 << 16 | count6 << 8 | count5);
      } else {
         this.intBuffer.put(count4, count5 << 24 | count6 << 16 | count7 << 8 | count8);
      }
   }

   public void applyIndexArray(int[] countArray) {
      if (BuildManager.canExecute()) {
         InternalLongArrayEntry.applyWorldRenderer(this, countArray);
      }

      this.applyIndexByIndex(countArray.length);
      ((Buffer)this.intBuffer).position(this.getCount2());
      this.intBuffer.put(countArray);
      this.count2 = this.count2 + countArray.length / this.auxiliaryLoggerEntry.getCountAsIndex();
      if (BuildManager.canExecute()) {
         InternalLongArrayEntry.applyWorldRenderer3(this);
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void applyIndexByIndex(int count4, int count5) {
      int count6 = this.computeCount(count5);
      int count7 = count4 >> 16 & 0xFF;
      int count8 = count4 >> 8 & 0xFF;
      int count9 = count4 & 0xFF;
      int count10 = count4 >> 24 & 0xFF;
      this.applyIndex(count6, count7, count8, count9, count10);
   }

   private static float computeFactor(FloatBuffer buffer, float posX, float posY, float posZ, int count4, int count5) {
      float factor = buffer.get(count5 + count4 * 0 + 0);
      float factor2 = buffer.get(count5 + count4 * 0 + 1);
      float factor3 = buffer.get(count5 + count4 * 0 + 2);
      float factor4 = buffer.get(count5 + count4 * 1 + 0);
      float factor5 = buffer.get(count5 + count4 * 1 + 1);
      float factor6 = buffer.get(count5 + count4 * 1 + 2);
      float factor7 = buffer.get(count5 + count4 * 2 + 0);
      float factor8 = buffer.get(count5 + count4 * 2 + 1);
      float factor9 = buffer.get(count5 + count4 * 2 + 2);
      float factor10 = buffer.get(count5 + count4 * 3 + 0);
      float factor11 = buffer.get(count5 + count4 * 3 + 1);
      float factor12 = buffer.get(count5 + count4 * 3 + 2);
      float factor13 = (factor + factor4 + factor7 + factor10) * 0.25F - posX;
      float factor14 = (factor2 + factor5 + factor8 + factor11) * 0.25F - posY;
      float factor15 = (factor3 + factor6 + factor9 + factor12) * 0.25F - posZ;
      return factor13 * factor13 + factor14 * factor14 + factor15 * factor15;
   }

   public void applyBlockSolidType(BlockSolidType blockSolidType2) {
      this.blockSolidType = blockSolidType2;
      if (blockSolidType2 == null) {
         if (this.serviceStringEntryArray2 != null) {
            this.serviceStringEntryArray = this.serviceStringEntryArray2;
         }

         this.serviceStringEntryArray2 = null;
         this.serviceStringEntry = null;
      }
   }

   public AuxiliaryLoggerEntry getAuxiliaryLoggerEntry() {
      return this.auxiliaryLoggerEntry;
   }

   private void applyIndexByIndex(int count4) {
      if (count4 > this.intBuffer.remaining()) {
         int count5 = this.byteBuffer.capacity();
         int count6 = count5 % 2097152;
         int count7 = count6 + (((this.intBuffer.position() + count4) * 4 - count6) / 2097152 + 1) * 2097152;
         LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + count5 + " bytes, new size " + count7 + " bytes.");
         int count8 = this.intBuffer.position();
         ByteBuffer buffer = VectorMatcher.resolveByteBuffer(count7);
         ((Buffer)this.byteBuffer).position(0);
         buffer.put(this.byteBuffer);
         ((Buffer)buffer).rewind();
         this.byteBuffer = buffer;
         this.floatBuffer = this.byteBuffer.asFloatBuffer();
         this.intBuffer = this.byteBuffer.asIntBuffer();
         ((Buffer)this.intBuffer).position(count8);
         this.shortBuffer = this.byteBuffer.asShortBuffer();
         ((Buffer)this.shortBuffer).position(count8 << 1);
         if (this.serviceStringEntryArray2 != null) {
            ServiceStringEntry[] serviceStringEntryArray3 = this.serviceStringEntryArray2;
            int count9 = this.getCount();
            this.serviceStringEntryArray2 = new ServiceStringEntry[count9];
            System.arraycopy(serviceStringEntryArray3, 0, this.serviceStringEntryArray2, 0, Math.min(serviceStringEntryArray3.length, this.serviceStringEntryArray2.length));
            this.serviceStringEntryArray = null;
         }
      }
   }

   public void renderElement2() {
      if (this.count3 == 7) {
         if (this.byteBuffer2 == null) {
            this.byteBuffer2 = VectorMatcher.resolveByteBuffer(this.byteBuffer.capacity() * 2);
         }

         if (this.byteBuffer2.capacity() < this.byteBuffer.capacity() * 2) {
            this.byteBuffer2 = VectorMatcher.resolveByteBuffer(this.byteBuffer.capacity() * 2);
         }

         int count4 = this.auxiliaryLoggerEntry.getCount2();
         int count5 = this.byteBuffer.limit();
         ((Buffer)this.byteBuffer).rewind();
         ((Buffer)this.byteBuffer2).clear();

         for (int byteValue = 0; byteValue < this.count2; byteValue += 4) {
            ((Buffer)this.byteBuffer).limit((byteValue + 3) * count4);
            ((Buffer)this.byteBuffer).position(byteValue * count4);
            this.byteBuffer2.put(this.byteBuffer);
            ((Buffer)this.byteBuffer).limit((byteValue + 1) * count4);
            ((Buffer)this.byteBuffer).position(byteValue * count4);
            this.byteBuffer2.put(this.byteBuffer);
            ((Buffer)this.byteBuffer).limit((byteValue + 2 + 2) * count4);
            ((Buffer)this.byteBuffer).position((byteValue + 2) * count4);
            this.byteBuffer2.put(this.byteBuffer);
         }

         ((Buffer)this.byteBuffer).limit(count5);
         ((Buffer)this.byteBuffer).rewind();
         ((Buffer)this.byteBuffer2).flip();
         this.enabled2 = true;
      }
   }

   public void applyDoubleByDouble(double posX, double posY, double posZ) {
      int countAsIndex = this.auxiliaryLoggerEntry.getCountAsIndex();
      int count4 = (this.count2 - 4) * countAsIndex;

      for (int index = 0; index < 4; index++) {
         int count5 = count4 + index * countAsIndex;
         int count6 = count5 + 1;
         int count7 = count6 + 1;
         this.intBuffer.put(count5, Float.floatToRawIntBits((float)(posX + this.value2) + Float.intBitsToFloat(this.intBuffer.get(count5))));
         this.intBuffer.put(count6, Float.floatToRawIntBits((float)(posY + this.value) + Float.intBitsToFloat(this.intBuffer.get(count6))));
         this.intBuffer.put(count7, Float.floatToRawIntBits((float)(posZ + this.value3) + Float.intBitsToFloat(this.intBuffer.get(count7))));
      }
   }

   public WorldRenderer(int count4) {
      this.byteBuffer = VectorMatcher.resolveByteBuffer(count4 * 4);
      this.intBuffer = this.byteBuffer.asIntBuffer();
      this.shortBuffer = this.byteBuffer.asShortBuffer();
      this.floatBuffer = this.byteBuffer.asFloatBuffer();
      InternalLongArrayEntry.applyWorldRenderer2(this);
   }
}
