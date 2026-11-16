package client.util;

import client.Client;
import client.ClientGlobals;
import client.gui.AuxiliaryClientScreenAdapter;
import client.render.RenderUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class AuxiliaryDoubleEntry7Entry extends AuxiliaryClientScreenAdapter {
   public final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(Minecraft.getMinecraft().fontRendererObj.indexOffset + 1);
   public List list6 = new ArrayList();

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      double offset = (double)count2;
      this.altDoubleEntry.applyDouble(offset);
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      super.applyIndexByIndex(count, count2, factor);
      this.altDoubleEntry.updateState();
      GL11.glEnable(3089);
      RenderUtils.drawBorderedRect(102.0F, 52.0F, (float)this.width, (float)this.height);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 52.0F, 0.0F);
      int count3 = 0;

      for (TertiaryListEntry tertiaryListEntry : (Iterable<TertiaryListEntry>)(Iterable<?>)this.getList6()) {
         float factor2 = (float)(this.width + 102) / 2.0F;
         float count4 = (float)tertiaryListEntry.getCount();
         float factor3 = (float)((double)((float)count3) + this.altDoubleEntry.getValue());
         if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
            RenderUtils.processFloat9(
               factor2 - 150.0F,
               factor3,
               factor2 + 150.0F,
               factor3 + count4 - 1.0F,
               MathHelper.clamp_float((float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(), 0.0F, 6.0F),
               1,
               Integer.MIN_VALUE
            );
         } else {
            RenderUtils.processAction(factor2 - 150.0F, factor3, factor2 + 150.0F, factor3 + count4 - 1.0F, Integer.MIN_VALUE);
         }

         String labelText = tertiaryListEntry.getLabelText();
         float factor4 = factor2 - 148.0F;
         float factor5 = factor3 + 2.0F;
         float factor6 = factor4;
         String text = labelText;
         this.applyTextByText(text, factor6, factor5);
         int count5 = 0;

         for (AdvancedStringArrayEntry advancedStringArrayEntry : (Iterable<AdvancedStringArrayEntry>)(Iterable<?>)tertiaryListEntry.getList()) {
            for (int index = 0; index < advancedStringArrayEntry.getStringArray().length; index++) {
               String text2 = advancedStringArrayEntry.getStringArray()[index];
               float factor7 = (float)((double)factor3 + this.altDoubleEntry.value + 6.0 + (double)count5 * this.altDoubleEntry.value);
               factor4 = factor2 - 148.0F + (index > 0 ? 6.5F : 0.0F);
               float factor8 = factor7 + 2.0F;
               int rgb = advancedStringArrayEntry.getCombatOptionProperty().getColor().getRGB();
               float factor9 = factor8;
               float factor10 = factor4;
               this.applyTextByText(text2, factor10, factor9, rgb);
               count5++;
            }
         }

         count3 = (int)((float)count3 + count4);
      }

      GL11.glPopMatrix();
      GL11.glDisable(3089);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.altDoubleEntry.updateState5();
   }

   private List getList6() {
      return this.list6;
   }

   @Override
   public void renderElement() {
      super.renderElement();
      StringHolder stringHolder = Client.getClient().getStringHolder();
      double offset = 296.0;
      this.list6 = stringHolder.resolveList(offset);
      int count = 0;
      float factor = (float)(this.width + 102) / 2.0F;

      for (TertiaryListEntry tertiaryListEntry : (Iterable<TertiaryListEntry>)(Iterable<?>)this.getList6()) {
         count += tertiaryListEntry.getCount();
      }

      int count2 = (int)((double)count / this.altDoubleEntry.value);
      this.altDoubleEntry.setCount4(count2);
      int count3 = (int)(factor + 152.0F);
      int count4 = (int)(factor + 155.0F);
      int count5 = this.height;
      int count6 = count4;
      byte byteValue = 52;
      int count7 = count3;
      this.altDoubleEntry.applyIndex(count7, byteValue, count6, count5);
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      double offset = (double)count;
      double offset2 = (double)count2;
      double offset3 = offset;
      this.altDoubleEntry.applyDouble(offset3, offset2);
   }

   public AuxiliaryDoubleEntry7Entry() {
      super("Changelog");
   }
}
