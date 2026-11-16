package client.gui;

import client.ClientGlobals;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.util.AltDoubleEntry;
import client.util.FovCollector;
import client.util.FovTracker;
import client.util.SimpleStringEntry;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class ThemeSelectorWidget extends AuxiliaryClientScreenAdapter {
   public static final List list6 = Arrays.asList(new FovTracker(), new FovCollector());
   public final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(Minecraft.getMinecraft().fontRendererObj.indexOffset + 1);

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
      ClientScreen.applyIndex2(102, 52, this.width, this.height);
      int count3 = 52;

      for (SimpleStringEntry simpleStringEntry : (Iterable<SimpleStringEntry>)(Iterable)(list6)) {
         int count4 = (int)((double)count3 + this.altDoubleEntry.getValue());
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)(this.width + 102) / 2.0F - 150.0F, (float)count4, 0.0F);
         if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
            RenderUtils.processFloat9(
               0.0F,
               0.0F,
               300.0F,
               (float)(simpleStringEntry.getCount() - 2),
               (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
               1,
               Integer.MIN_VALUE
            );
         } else {
            processAction(0, 0, 300, simpleStringEntry.getCount() - 2, Integer.MIN_VALUE);
         }

         String labelText = simpleStringEntry.getLabelText();
         float factor2 = 2.0F;
         float factor3 = 2.0F;
         float factor4 = 75.0F;
         String text = labelText;
         this.applyText(text, factor4, factor3, factor2);
         int count5 = 0;

         for (String text2 : (Iterable<String>)(Iterable)(simpleStringEntry.asList2())) {
            float factor5 = (float)(29 + count5);
            float factor6 = 3.0F;
            this.applyTextByText(text2, factor6, factor5);
            count5 += this.fontRenderer.indexOffset + 1;
         }

         int count6 = 39 + count5;
         String text3 = ClientSettingsBlackType.GOLD + "Social Media ";
         float factor7 = (float)count6;
         float factor8 = 3.0F;
         String text4 = text3;
         this.applyTextByText(text4, factor8, factor7);
         int count7 = 0;

         for (Object elementObj : simpleStringEntry.asList()) {
            String element = (String)elementObj;
            if (element.startsWith("Discord")) {
               element = ClientSettingsBlackType.BLUE + element;
            }

            if (element.startsWith("GitHub")) {
               element = ClientSettingsBlackType.GRAY + element;
            }

            if (element.startsWith("YouTube")) {
               element = ClientSettingsBlackType.RED + element;
            }

            float factor9 = (float)(10 + count6 + count7);
            float factor10 = 3.0F;
            this.applyTextByText(element, factor10, factor9);
            count7 += this.fontRenderer.indexOffset + 1;
         }

         GlStateManager.popMatrix();
         count3 += simpleStringEntry.getCount();
      }

      ClientScreen.glDisable();
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.altDoubleEntry.updateState5();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      double offset = (double)count;
      double offset2 = (double)count2;
      double offset3 = offset;
      this.altDoubleEntry.applyDouble(offset3, offset2);
   }

   @Override
   public void renderElement() {
      super.renderElement();
      int count = 0;

      for (SimpleStringEntry simpleStringEntry : (Iterable<SimpleStringEntry>)(Iterable)(list6)) {
         count = (int)((double)count + (double)simpleStringEntry.getCount() / this.altDoubleEntry.value);
      }

      this.altDoubleEntry.setCount4(count);
      float factor = (float)(this.width + 102) / 2.0F;
      int count2 = (int)(factor + 152.0F);
      int count3 = (int)(factor + 155.0F);
      int count4 = this.height;
      int count5 = count3;
      byte byteValue = 52;
      int count6 = count2;
      this.altDoubleEntry.applyIndex(count6, byteValue, count5, count4);
   }

   public ThemeSelectorWidget() {
      super("Credits");
   }
}
