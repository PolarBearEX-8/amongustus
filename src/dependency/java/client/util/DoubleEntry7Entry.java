package client.util;

import client.Client;
import client.ClientGlobals;
import client.gui.AuxiliaryClientScreenAdapter;
import client.gui.ClientScreen;
import client.gui.TexturesGuiIconsPngManager;
import client.render.RenderUtils;
import java.io.IOException;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DoubleEntry7Entry extends AuxiliaryClientScreenAdapter {
   public final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(44);

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      super.applyIndex(count, count2, count3, value);
      double offset = (double)count2;
      this.altDoubleEntry.applyDouble(offset);
   }

   @Override
   public void applyIndexByIndex(int count, int count2, float factor) {
      this.altDoubleEntry.updateState();
      ClientScreen.applyIndex2(102, 52, this.width, this.height - 2);
      int count4 = (int)(52.0 + this.altDoubleEntry.getValue());
      float factor2 = (float)(this.width + 102) / 2.0F;
      if (Client.getClient().getInternalHashMapHolder() != null) {
         for (Object consumerWrapperObj : Client.getClient().getInternalHashMapHolder().values()) {
            ConsumerWrapper consumerWrapper = (ConsumerWrapper)consumerWrapperObj;
            if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
               RenderUtils.processFloat9(
                  factor2 - 150.0F,
                  (float)count4,
                  factor2 + 150.0F,
                  (float)count4 + 40.0F,
                  (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
                  1,
                  Integer.MIN_VALUE
               );
            } else {
               RenderUtils.processAction(factor2 - 150.0F, (float)count4, factor2 + 150.0F, (float)count4 + 40.0F, Integer.MIN_VALUE);
            }

            String text = "§a§l" + consumerWrapper.getTextAsText();
            float factor3 = factor2 - 146.0F;
            float factor4 = (float)(count4 + 4);
            float factor5 = factor3;
            String text2 = text;
            this.applyTextByText(text2, factor5, factor4, -1);
            text = "§7" + consumerWrapper.getText();
            factor3 = factor2 - 146.0F;
            float factor6 = (float)(count4 + 16);
            float factor7 = factor3;
            String text3 = text;
            this.applyTextByText(text3, factor7, factor6, -1);
            text = "§7" + (int)consumerWrapper.getValue() + "% finished";
            factor3 = factor2 - 146.0F;
            float factor8 = (float)(count4 + 28);
            float factor9 = factor3;
            String text4 = text;
            this.applyTextByText(text4, factor9, factor8, -1);
            if (!consumerWrapper.isEnabled()) {
               String text5 = "§b" + consumerWrapper.getCount() + " XP";
               int count5 = this.fontRenderer.getIndex(text5) * 2;
               float factor10 = (factor2 + 146.0F - (float)count5) / 2.0F;
               float factor11 = (float)((count4 + 12) / 2);
               float factor12 = 2.0F;
               float factor13 = factor11;
               float factor14 = factor10;
               this.applyText(text5, factor14, factor13, factor12, -1);
            } else {
               GlStateManager.color4f2(255.0F, 255.0F, 255.0F, 1.0F);
               int count6 = (int)(factor2 + 115.0F);
               int count7 = count4 + 5;
               short shortValue = (short)0;
               ResourceLocation resourceLocation = GuiMultiplayer.resourceLocation7;
               float factor15 = 30.0F;
               float factor16 = 30.0F;
               int count8 = count7;
               short shortValue2 = shortValue;
               int count9 = count6;
               this.applyResourceLocationByResourceLocation(count9, shortValue2, count8, factor16, factor15, resourceLocation);
            }

            count4 += 44;
         }
      }

      ClientScreen.glDisable();
   }

   public void applyResourceLocationByResourceLocation(int count, short shortValue, int count2, float factor, float factor2, ResourceLocation location) {
      this.mc.getTextureManager().applyResourceLocation3(location);
      GlStateManager.disableBlend();
      TexturesGuiIconsPngManager.applyIndex(count, count2, 0.0F, 0.0F, (int)factor, (int)factor2, factor, factor2);
      GlStateManager.enableBlend();
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
      if (Client.getClient().getInternalHashMapHolder() != null) {
         AltDoubleEntry altDoubleEntry2 = this.altDoubleEntry;
         int size = Client.getClient().getInternalHashMapHolder().values().size();
         altDoubleEntry2.setCount4(size);
      }

      float factor = (float)(this.width + 102) / 2.0F;
      int count = (int)(factor + 152.0F);
      int count2 = (int)(factor + 155.0F);
      int count3 = this.height;
      int count4 = count2;
      byte byteValue = 52;
      int count5 = count;
      this.altDoubleEntry.applyIndex(count5, byteValue, count4, count3);
   }

   public DoubleEntry7Entry() {
      super("Quests");
   }
}
