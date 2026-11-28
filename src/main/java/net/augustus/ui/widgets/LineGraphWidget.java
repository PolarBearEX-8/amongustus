package net.augustus.ui.widgets;

import net.augustus.Augustus;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.GlStateSaver;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class LineGraphWidget {
   private List samples;
   private int y;
   private final int height;
   private int maxSamples;
   private final int width;
   private final GlStateSaver glStateSaver = GlStateSaver.create();
   private int x;

   public void setSamples(List list) {
      this.samples = list;
   }

   public LineGraphWidget(int n, int n2) {
      this.samples = new LinkedList();
      this.maxSamples = 60;
      this.width = n;
      this.height = n2;
   }

   public List getSamples() {
      return new ArrayList(this.samples);
   }

   public void setMaxSamples(int n) {
      this.maxSamples = n;
   }

   public void addSample(float f) {
      this.samples.add(f);
   }

   public void setPosition(int n, int n2) {
      this.x = n;
      this.y = n2;
   }

   public void clearSamples() {
      this.samples.clear();
   }

   public void drawGraph(int n, int n2, float f) {
      if (!this.samples.isEmpty()) {
         float f2 = (Float)Collections.min(this.samples);
         float f3 = (Float)Collections.max(this.samples);
         if (f2 == f3) {
            f2--;
            f3++;
         }

         while (this.samples.size() > this.maxSamples) {
            this.samples.remove(0);
         }

         GL11.glPushMatrix();
         GL11.glTranslatef((float)this.x, (float)this.y, 0.0F);
         RenderUtil.drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
         float f4 = (float)Math.round(this.samples.stream().mapToDouble(obj -> (double)((Float)obj).floatValue()).average().orElse(0.0) * 100.0) / 100.0F;
         float f5 = (f4 - f2) / (f3 - f2) * (float)this.height;
         RenderUtil.drawRect(
            0.0,
            (double)((float)this.height - f5) - 0.25,
            (double)this.width,
            (double)((float)this.height - f5) + 0.25,
            Color.DARK_GRAY.getRGB()
         );
         float f6 = (Float)this.samples.get(this.samples.size() - 1);
         this.glStateSaver.setTexture2D(false);
         this.glStateSaver.setLineSmooth(true);
         GL11.glHint(3154, 4354);
         float f7 = GL11.glGetFloat(2849);
         GL11.glLineWidth(2.0F);
         RenderUtil.setGlColorWithAlpha(Augustus.getInstance().getClientColor(), 1.0F);
         GL11.glShadeModel(7425);
         GL11.glBegin(3);

         for (int i = 0; i < this.samples.size(); i++) {
            float f8 = ((Float)this.samples.get(i) - f2) / (f3 - f2);
            GL11.glVertex2f((float)i / (float)(this.samples.size() - 1) * (float)this.width, (float)this.height - f8 * (float)this.height);
         }

         GL11.glEnd();
         this.glStateSaver.restoreTexture2D();
         this.glStateSaver.restoreLineSmooth();
         GL11.glLineWidth(f7);
         GL11.glPushMatrix();
         GL11.glScaled(0.5, 0.5, 0.5);
         FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
         fontRendererObj.drawStringWithShadow(String.valueOf(f3), 0.0F, 0.0F, -1);
         fontRendererObj.drawStringWithShadow(String.valueOf(f2), 0.0F, ((float)this.height - (float)fontRendererObj.FONT_HEIGHT / 2.0F) * 2.0F, -1);
         fontRendererObj.drawStringWithShadow(
            String.valueOf(f4),
            ((float)this.width - (float)fontRendererObj.getStringWidth(String.valueOf(f4)) / 2.0F) * 2.0F,
            ((float)this.height - f5 - (float)fontRendererObj.FONT_HEIGHT / 4.0F) * 2.0F,
            -1
         );
         fontRendererObj.drawStringWithShadow(
            String.valueOf(f6),
            (float)(this.width * 2 + 2),
            ((float)this.height - (f6 - f2) / (f3 - f2) * (float)this.height - (float)fontRendererObj.FONT_HEIGHT / 4.0F) * 2.0F,
            -1
         );
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }
}
