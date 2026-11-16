package net.minecraft.item;

import client.util.AuxiliaryLoggerEntry;
import client.util.CurveChecker;
import client.util.GeometryCalculator;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class DiamondChestplate {
   public int count;
   public final AuxiliaryLoggerEntry auxiliaryLoggerEntry;
   public int count3;
   public CurveChecker curveChecker;
   public GeometryCalculator geometryCalculator;
   public int count2;

   public void applyByteBuffer(ByteBuffer buffer) {
      if (this.curveChecker != null) {
         this.curveChecker.applyByteBuffer(buffer, this.geometryCalculator);
      } else {
         this.updateState2();
         OpenGlHelper.applyByteBuffer(OpenGlHelper.count14, buffer, 35044);
         this.updateState();
         this.count3 = buffer.limit() / this.auxiliaryLoggerEntry.getCount2();
      }
   }

   public void updateState2() {
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, this.count);
   }

   public void setCount2(int count4) {
      this.count2 = count4;
   }

   public CurveChecker getCurveChecker() {
      return this.curveChecker;
   }

   public void applyIndex(int count4) {
      int count5 = count4;
      if (this.count2 > 0) {
         count5 = this.count2;
      }

      if (this.curveChecker != null) {
         this.curveChecker.applyGeometryCalculator(count5, this.geometryCalculator);
      } else {
         GL11.glDrawArrays(count5, 0, this.count3);
      }
   }

   public void updateState3() {
      if (this.count >= 0) {
         OpenGlHelper.applyIndex9(this.count);
         this.count = -1;
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public GeometryCalculator getGeometryCalculator() {
      return this.geometryCalculator;
   }

   public void updateState() {
      OpenGlHelper.applyIndex2(OpenGlHelper.count14, 0);
   }

   public void applyCurveChecker(CurveChecker curveChecker2) {
      if (curveChecker2 != null) {
         this.updateState3();
         this.curveChecker = curveChecker2;
         this.geometryCalculator = new GeometryCalculator();
      }
   }

   public DiamondChestplate(AuxiliaryLoggerEntry auxiliaryLoggerEntry2) {
      this.auxiliaryLoggerEntry = auxiliaryLoggerEntry2;
      this.count = OpenGlHelper.getCount2();
   }
}
