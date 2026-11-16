package client.util;

import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class AltDoubleEntry {
   public double value4;
   public double value9;
   public double value8;
   public double value5;
   public long timestamp;
   public final int count3 = 0;
   public final double value;
   public int count4;
   public double value6;
   public double value3;
   public double value2;
   public boolean enabled;
   public double value10;
   public int count;
   public double value7;
   public int count5;

   public void updateState3() {
      double offset = (double)this.count4 * this.value + 0.0;
      double offset2 = (double)(this.count5 - this.count);
      if (offset2 < offset) {
         double offset3 = offset2 / offset;
         double offset4 = offset3 * offset2;
         double offset5 = this.value10 / offset3 * offset3 * offset3;
         this.value9 = -offset5 + (double)this.count;
         this.value7 = offset4;
         this.value5 = offset2;
      }
   }

   public boolean isActive() {
      return this.value10 == MathHelper.getDouble7(-9999999.0, Math.min(0.0, -((double)this.count4 * this.value - (double)(this.count5 - this.count))), 0.0);
   }

   public void setCount4(int count2) {
      this.count4 = count2;
   }

   public void applyDouble(double offset, double offset2) {
      this.updateState3();
      double offset3 = this.value5 / ((double)this.count4 * this.value + 0.0);
      double offset4 = (double)((int)(-offset2 / offset3));
      if (this.enabled) {
         this.enabled = false;
      } else if (this.checkDouble(offset, offset2)) {
         this.enabled = true;
         this.value3 = offset4 - this.getValue();
      }

      this.updateState4();
   }

   public void applyDouble(double offset) {
      this.updateState3();
      double offset2 = this.value5 / ((double)this.count4 * this.value + 0.0);
      double offset3 = (double)((int)(-offset / offset2));
      if (this.enabled) {
         this.value10 = offset3 - this.value3;
      }

      this.updateState4();
   }

   public void updateState() {
      long value11 = System.nanoTime() - this.timestamp;
      double value12 = this.value4;
      double doubleItem = MathHelper.getDouble7(value12, -this.value6 * ((double)value11 / 1000000.0), this.value6 * ((double)value11 / 1000000.0));
      this.timestamp = System.nanoTime();
      this.value4 -= doubleItem;
      this.value10 += doubleItem;
      double value13 = this.value10;
      this.updateState4();
      if (value13 != this.value10) {
         this.value4 = 0.0;
      }

      if (!this.isActiveAsFlag()) {
         this.updateState3();
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GL11.glDisable(3008);
         GlStateManager.glShadeModel(7425);
         GL11.glDisable(3553);
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         double value14 = this.value2;
         double value15 = this.value8;
         double value16 = this.value9;
         double value17 = this.value7;
         worldRenderer.getWorldRenderer(value14, (double)this.count5, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer(value15, (double)this.count5, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer(value15, (double)this.count, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         worldRenderer.getWorldRenderer(value14, (double)this.count, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
         tessellator.updateState();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer(value14, value16 + value17, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
         worldRenderer.getWorldRenderer(value15, value16 + value17, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
         worldRenderer.getWorldRenderer(value15, value16, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
         worldRenderer.getWorldRenderer(value14, value16, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(128, 128, 128, 255).updateState();
         tessellator.updateState();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer(value14, value16 + value17 - 1.0, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
         worldRenderer.getWorldRenderer(value15 - 1.0, value16 + value17 - 1.0, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
         worldRenderer.getWorldRenderer(value15 - 1.0, value16, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
         worldRenderer.getWorldRenderer(value14, value16, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(192, 192, 192, 255).updateState();
         tessellator.updateState();
         GL11.glEnable(3553);
         GlStateManager.glShadeModel(7424);
         GL11.glEnable(3008);
         GlStateManager.enableBlend();
      }
   }

   public void applyIndex(int count2, int count6, int count7, int count8) {
      this.value2 = (double)count2;
      this.count = count6;
      this.value8 = (double)count7;
      this.count5 = count8;
      this.updateState3();
   }

   public double getValue() {
      return MathHelper.getDouble7(this.value10, Math.min(0.0, -((double)this.count4 * this.value - (double)(this.count5 - this.count))), 0.0);
   }

   public void setValue2(double value11) {
      this.value2 = value11;
   }

   public void updateState5() {
      int count2 = Math.round((float)Mouse.getDWheel() / 3.0F);
      this.value4 += (double)count2;
      if (count2 != 0) {
         this.value6 = Math.abs(this.value4 / 200.0);
      }

      this.value10 = MathHelper.getDouble7(this.value10, Math.min(0.0, -((double)this.count4 * this.value - (double)(this.count5 - this.count))), 0.0);
   }

   public boolean isActiveAsFlag() {
      return this.count4 == 0 ? true : (double)(this.count5 - this.count) >= (double)this.count4 * this.value + 0.0;
   }

   public void updateState6() {
      int count2 = new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() / 2 + 150;
      int count6 = new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() / 2 + 156;
      int count7 = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 40;
      int count8 = count6;
      byte byteValue = 40;
      int count9 = count2;
      this.applyIndex(count9, byteValue, count8, count7);
   }

   public boolean checkDouble(double offset, double offset2) {
      return offset < this.value8 && offset >= this.value2 && offset2 >= this.value9 && offset2 < this.value9 + this.value7;
   }

   public void updateState2() {
      this.value10 = MathHelper.getDouble7(-9999999.0, Math.min(0.0, -((double)this.count4 * this.value - (double)(this.count5 - this.count))), 0.0);
   }

   public void updateState4() {
      this.value10 = MathHelper.getDouble7(this.value10, Math.min(0.0, -((double)this.count4 * this.value - (double)(this.count5 - this.count))), 0.0);
   }

   public AltDoubleEntry(int count2) {
      this.value = (double)count2;
      this.updateState6();
   }
}
