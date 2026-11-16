package client.util;

import client.Client;
import client.render.RenderUtils;
import client.setting.PassiveOptionType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class CoreListEntry {
   public List list = new LinkedList();
   public int count3;
   public final int count4;
   public int count5;
   public int count = 60;
   public final int count2;

   public void clear() {
      this.list.clear();
   }

   public List getList() {
      return new ArrayList(this.list);
   }

   public void setList(List list2) {
      this.list = list2;
   }

   public void applyIndex(int count6, int count7) {
      this.count3 = count6;
      this.count5 = count7;
   }

   public void applyIndex(int count6, int count7, float factor) {
      if (!this.list.isEmpty()) {
         float factor2 = ((Float)Collections.min(this.list));
         float factor3 = ((Float)Collections.max(this.list));
         if (factor2 == factor3) {
            factor2--;
            factor3++;
         }

         while (this.list.size() > this.count) {
            this.list.remove(0);
         }

         GL11.glPushMatrix();
         GL11.glTranslatef((float)this.count3, (float)this.count5, 0.0F);
         RenderUtils.processAction(0, 0, this.count4, this.count2, Integer.MIN_VALUE);
         float factor4 = (float)Math.round(this.list.stream().mapToDouble((item -> CoreListEntry.floatValue((Float)item))).average().orElse(0.0) * 100.0) / 100.0F;
         float factor5 = (factor4 - factor2) / (factor3 - factor2) * (float)this.count2;
         RenderUtils.processDouble4(
            0.0, (double)((float)this.count2 - factor5) - 0.25, (double)this.count4, (double)((float)this.count2 - factor5) + 0.25, Color.DARK_GRAY.getRGB()
         );
         float factor6 = (Float)this.list.get(this.list.size() - 1);
         boolean flag = false;
         PassiveOptionType.TEXTURE_2D.setEnabled(flag);
         boolean flag2 = true;
         PassiveOptionType.LINE_SMOOTH.setEnabled(flag2);
         GL11.glHint(3154, 4354);
         float factor7 = GL11.glGetFloat(2849);
         GL11.glLineWidth(2.0F);
         Color color = Client.getClient().getColor();
         GlStateManager.color4f2((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 1.0F);
         GL11.glShadeModel(7425);
         GL11.glBegin(3);

         for (int index = 0; index < this.list.size(); index++) {
            float factor8 = ((Float)this.list.get(index) - factor2) / (factor3 - factor2);
            float factor9 = (float)index / (float)(this.list.size() - 1) * (float)this.count4;
            float factor10 = (float)this.count2 - factor8 * (float)this.count2;
            GL11.glVertex2f(factor9, factor10);
         }

         GL11.glEnd();
         PassiveOptionType.TEXTURE_2D.updateState();
         PassiveOptionType.LINE_SMOOTH.updateState();
         GL11.glLineWidth(factor7);
         GL11.glPushMatrix();
         GL11.glScaled(0.5, 0.5, 0.5);
         FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
         fontRendererObj.getIndex2(String.valueOf(factor3), 0.0F, 0.0F, -1);
         fontRendererObj.getIndex2(String.valueOf(factor2), 0.0F, ((float)this.count2 - (float)fontRendererObj.indexOffset / 2.0F) * 2.0F, -1);
         fontRendererObj.getIndex2(
            String.valueOf(factor4),
            ((float)this.count4 - (float)fontRendererObj.getIndex(String.valueOf(factor4)) / 2.0F) * 2.0F,
            ((float)this.count2 - factor5 - (float)fontRendererObj.indexOffset / 4.0F) * 2.0F,
            -1
         );
         fontRendererObj.getIndex2(
            String.valueOf(factor6),
            (float)(this.count4 * 2 + 2),
            ((float)this.count2 - (factor6 - factor2) / (factor3 - factor2) * (float)this.count2 - (float)fontRendererObj.indexOffset / 4.0F) * 2.0F,
            -1
         );
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public void setCount(int count6) {
      this.count = count6;
   }

   public void applyFloat(float factor) {
      this.list.add(factor);
   }

   private static double floatValue(Float factor) {
      return (double)factor.floatValue();
   }

   public CoreListEntry(int count6, int count7) {
      this.count4 = count6;
      this.count2 = count7;
   }
}
