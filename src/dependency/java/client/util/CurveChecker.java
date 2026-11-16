package client.util;

import client.setting.BlockSolidType;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;

public class CurveChecker {
   public BlockSolidType blockSolidType = null;
   public int count = OpenGlHelper.getCount2();
   public int count2 = 4096;
   public int count3 = 0;
   public int count4;
   public NodeDifferentController nodeDifferentController = new NodeDifferentController();
   public GeometryCalculator geometryCalculator = null;
   public IntBuffer intBuffer = BuildManager.resolveIntBuffer(this.count2);
   public IntBuffer intBuffer2 = BuildManager.resolveIntBuffer(this.count2);
   public int count5 = 7;
   public final int count6 = DefaultVertexFormats.auxiliaryLoggerEntry5.getCount2();

   public void applyByteBuffer(ByteBuffer buffer, GeometryCalculator geometryCalculator2) {
      int countAsIndex = geometryCalculator2.getCountAsIndex();
      int count7 = geometryCalculator2.getCount2();
      int count8 = this.computeCount((long)buffer.limit());
      if (count8 <= 0) {
         if (countAsIndex >= 0) {
            geometryCalculator2.setCount(-1);
            geometryCalculator2.setCount2(0);
            this.nodeDifferentController.resolveNodeDifferentControlControll(geometryCalculator2.getNodeDifferentControlController());
            this.count4 -= count7;
         }
      } else {
         if (count8 > count7) {
            geometryCalculator2.setCount(this.count3);
            geometryCalculator2.setCount2(count8);
            this.count3 += count8;
            if (countAsIndex >= 0) {
               this.nodeDifferentController.resolveNodeDifferentControlControll(geometryCalculator2.getNodeDifferentControlController());
            }

            this.nodeDifferentController.applyNodeDifferentControlContro(geometryCalculator2.getNodeDifferentControlController());
         }

         geometryCalculator2.setCount2(count8);
         this.count4 += count8 - count7;
         this.applyIndex2(geometryCalculator2.getCount());
         long value = this.computeTimestamp(geometryCalculator2.getCountAsIndex());
         this.updateState4();
         OpenGlHelper.applyByteBuffer(OpenGlHelper.count14, value, buffer);
         this.updateState();
         if (this.count3 > this.count4 * 11 / 10) {
            this.applyIndexByIndex(1);
         }
      }
   }

   private void updateState2() {
      int count7 = 0;
      int count8 = 0;

      for (GeometryCalculator dataRef = (GeometryCalculator)this.nodeDifferentController.getNodeDifferentControlController().getDataRef();
         dataRef != null;
         dataRef = dataRef.getGeometryCalculatorAsGeometryCalculat()
      ) {
         count7++;
         count8 += dataRef.getCount2();
         if (dataRef.getCountAsIndex() < 0 || dataRef.getCount2() <= 0 || dataRef.getCount() > this.count3) {
            throw new RuntimeException("Invalid range: " + dataRef);
         }

         GeometryCalculator geometryCalculator2 = dataRef.getGeometryCalculator();
         if (geometryCalculator2 != null && dataRef.getCountAsIndex() < geometryCalculator2.getCount()) {
            throw new RuntimeException("Invalid range: " + dataRef);
         }

         GeometryCalculator geometryCalculatorAsGeometryCalculat = dataRef.getGeometryCalculatorAsGeometryCalculat();
         if (geometryCalculatorAsGeometryCalculat != null && dataRef.getCount() > geometryCalculatorAsGeometryCalculat.getCountAsIndex()) {
            throw new RuntimeException("Invalid range: " + dataRef);
         }
      }

      if (count7 != this.nodeDifferentController.getCount()) {
         throw new RuntimeException("Invalid count: " + count7 + " <> " + this.nodeDifferentController.getCount());
      } else if (count8 != this.count4) {
         throw new RuntimeException("Invalid size: " + count8 + " <> " + this.count4);
      }
   }

   public CurveChecker(BlockSolidType blockSolidType2) {
      this.blockSolidType = blockSolidType2;
      this.updateState4();
      long value = this.computeTimestamp(this.count2);
      OpenGlHelper.applyIndex(OpenGlHelper.count14, value, OpenGlHelper.count2);
      this.updateState();
   }

   private void applyIndexByIndex(int count7) {
      if (!this.nodeDifferentController.isActive()) {
         GeometryCalculator geometryCalculator2 = this.geometryCalculator;
         if (geometryCalculator2 == null || !this.nodeDifferentController.matchesNodeDifferentControlContro(geometryCalculator2.getNodeDifferentControlController())) {
            geometryCalculator2 = (GeometryCalculator)this.nodeDifferentController.getNodeDifferentControlController().getDataRef();
         }

         geometryCalculator2.getCountAsIndex();
         GeometryCalculator geometryCalculator3 = geometryCalculator2.getGeometryCalculator();
         int count8;
         if (geometryCalculator3 == null) {
            count8 = 0;
         } else {
            count8 = geometryCalculator3.getCount();
         }

         int count9 = 0;

         while (geometryCalculator2 != null && count9 < count7) {
            count9++;
            if (geometryCalculator2.getCountAsIndex() == count8) {
               count8 += geometryCalculator2.getCount2();
               geometryCalculator2 = geometryCalculator2.getGeometryCalculatorAsGeometryCalculat();
            } else {
               int count10 = geometryCalculator2.getCountAsIndex() - count8;
               if (geometryCalculator2.getCount2() <= count10) {
                  this.applyIndex(geometryCalculator2.getCountAsIndex(), count8, geometryCalculator2.getCount2());
                  geometryCalculator2.setCount(count8);
                  count8 += geometryCalculator2.getCount2();
                  geometryCalculator2 = geometryCalculator2.getGeometryCalculatorAsGeometryCalculat();
               } else {
                  this.applyIndex2(this.count3 + geometryCalculator2.getCount2());
                  this.applyIndex(geometryCalculator2.getCountAsIndex(), this.count3, geometryCalculator2.getCount2());
                  geometryCalculator2.setCount(this.count3);
                  this.count3 = this.count3 + geometryCalculator2.getCount2();
                  GeometryCalculator geometryCalculatorAsGeometryCalculat = geometryCalculator2.getGeometryCalculatorAsGeometryCalculat();
                  this.nodeDifferentController.resolveNodeDifferentControlControll(geometryCalculator2.getNodeDifferentControlController());
                  this.nodeDifferentController.applyNodeDifferentControlContro(geometryCalculator2.getNodeDifferentControlController());
                  geometryCalculator2 = geometryCalculatorAsGeometryCalculat;
               }
            }
         }

         if (geometryCalculator2 == null) {
            this.count3 = ((GeometryCalculator)this.nodeDifferentController.getNodeDifferentControlController2().getDataRef()).getCount();
         }

         this.geometryCalculator = geometryCalculator2;
      }
   }

   private long computeTimestamp(int count7) {
      return (long)count7 * (long)this.count6;
   }

   public void updateState() {
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, 0);
   }

   private int computeCount(long value) {
      return (int)(value / (long)this.count6);
   }

   private void applyIndex(int count7, int count8, int count9) {
      long value = this.computeTimestamp(count7);
      long value2 = this.computeTimestamp(count8);
      long value3 = this.computeTimestamp(count9);
      OpenGlHelper.applyIndex2(OpenGlHelper.count18, this.count);
      OpenGlHelper.applyIndex2(OpenGlHelper.count30, this.count);
      OpenGlHelper.applyIndex(OpenGlHelper.count18, OpenGlHelper.count30, value, value2, value3);
      BuildManager.applyText5("Copy VBO range");
      OpenGlHelper.applyIndex2(OpenGlHelper.count18, 0);
      OpenGlHelper.applyIndex2(OpenGlHelper.count30, 0);
   }

   private void applyIndex(int count7) {
      int count8 = this.count2 * 6 / 4;

      while (count8 < count7) {
         count8 = count8 * 6 / 4;
      }

      long value = this.computeTimestamp(this.count2);
      long value2 = this.computeTimestamp(count8);
      int count9 = OpenGlHelper.getCount2();
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, count9);
      OpenGlHelper.applyIndex(OpenGlHelper.count14, value2, OpenGlHelper.count2);
      BuildManager.applyText5("Expand VBO");
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, 0);
      OpenGlHelper.applyIndex2(OpenGlHelper.count18, this.count);
      OpenGlHelper.applyIndex2(OpenGlHelper.count30, count9);
      OpenGlHelper.applyIndex(OpenGlHelper.count18, OpenGlHelper.count30, 0L, 0L, value);
      BuildManager.applyText5("Copy VBO: " + value2);
      OpenGlHelper.applyIndex2(OpenGlHelper.count18, 0);
      OpenGlHelper.applyIndex2(OpenGlHelper.count30, 0);
      OpenGlHelper.applyIndex9(this.count);
      this.intBuffer = BuildManager.resolveIntBuffer(count8);
      this.intBuffer2 = BuildManager.resolveIntBuffer(count8);
      this.count = count9;
      this.count2 = count8;
   }

   public int getCount3() {
      return this.count3;
   }

   public void updateState3() {
      if (this.count >= 0) {
         OpenGlHelper.applyIndex9(this.count);
         this.count = -1;
      }
   }

   private void applyIndex2(int count7) {
      if (this.count2 < count7) {
         this.applyIndex(count7);
      }
   }

   public void applyGeometryCalculator(int count7, GeometryCalculator geometryCalculator2) {
      if (this.count5 != count7) {
         if (this.intBuffer.position() > 0) {
            throw new IllegalArgumentException("Mixed region draw modes: " + this.count5 + " != " + count7);
         }

         this.count5 = count7;
      }

      this.intBuffer.put(geometryCalculator2.getCountAsIndex());
      this.intBuffer2.put(geometryCalculator2.getCount2());
   }

   public void updateState4() {
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, this.count);
   }

   public void applySpecialDoubleEntry(SpecialDoubleEntry specialDoubleEntry) {
      this.updateState4();
      specialDoubleEntry.updateState();
      ((Buffer)this.intBuffer).flip();
      ((Buffer)this.intBuffer2).flip();
      GlStateManager.applyIntBuffer(this.count5, this.intBuffer, this.intBuffer2);
      ((Buffer)this.intBuffer).limit(this.intBuffer.capacity());
      ((Buffer)this.intBuffer2).limit(this.intBuffer2.capacity());
      if (this.count3 > this.count4 * 11 / 10) {
         this.applyIndexByIndex(1);
      }
   }
}
