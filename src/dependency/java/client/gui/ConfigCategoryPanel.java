package client.gui;

import augustus.modules.misc.ClientSettings;
import client.Client;
import client.ClientGlobals;
import client.render.RenderUtils;
import client.setting.ClientSettingsBlackType;
import client.util.AltDoubleEntry;
import client.util.ModularStringEntry;
import java.io.IOException;
import java.util.List;
import net.minecraft.item.GoldenShard;
import org.lwjgl.opengl.GL11;

public class ConfigCategoryPanel extends AuxiliaryClientScreenAdapter {
   public final AltDoubleEntry altDoubleEntry = new AltDoubleEntry(21);

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
      float factor2 = (float)(this.width + 102) / 2.0F;

      for (ModularStringEntry modularStringEntry : (Iterable<ModularStringEntry>)(Object)(this.getList())) {
         float factor3 = (float)((double)((float)((double)count3 * this.altDoubleEntry.value)) + this.altDoubleEntry.getValue());
         double offset = this.altDoubleEntry.value + ((double)count3 * this.altDoubleEntry.value - 2.0);
         if (ClientGlobals.moduleManager.clientSettings.settingRoundedButtons.isEnabled()) {
            RenderUtils.processFloat9(
               factor2 - 150.0F,
               factor3,
               factor2 + 150.0F,
               (float)((double)((float)offset) + this.altDoubleEntry.getValue()),
               (float)ClientGlobals.moduleManager.clientSettings.settingRoundFactor.getValue(),
               1,
               Integer.MIN_VALUE
            );
         } else {
            RenderUtils.processAction(
               factor2 - 150.0F, factor3, factor2 + 150.0F, (float)((double)((float)offset) + this.altDoubleEntry.getValue()), Integer.MIN_VALUE
            );
         }

         ModularStringEntry modularStringEntry2 = Client.getClient().getGoldenShard().getModularStringEntry();
         String labelText = modularStringEntry.getLabelText();
         String text = (modularStringEntry2 == modularStringEntry ? ClientSettingsBlackType.GREEN : ClientSettingsBlackType.RESET) + labelText;
         float factor4 = factor2 / 1.5F;
         float factor5 = factor3 / 1.5F + 2.0F;
         float factor6 = 1.5F;
         float factor7 = factor5;
         float factor8 = factor4;
         String text2 = text;
         this.applyText(text2, factor8, factor7, factor6);
         count3++;
      }

      GL11.glPopMatrix();
      GL11.glDisable(3089);
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.altDoubleEntry.updateState5();
   }

   private List getList() {
      return Client.getClient().getGoldenShard().getList();
   }

   @Override
   public void processAction(int count, int count2, int count3) throws IOException {
      super.processAction(count, count2, count3);
      int count4 = count2 - 52;
      int count5 = 0;

      for (ModularStringEntry modularStringEntry : (Iterable<ModularStringEntry>)(Object)(this.getList())) {
         float factor = (float)(this.width + 102) / 2.0F;
         float factor2 = (float)((double)((float)((double)count5 * this.altDoubleEntry.value)) + this.altDoubleEntry.getValue());
         if (RenderUtils.checkCondition(
            count,
            count4,
            (double)(factor - 150.0F),
            (double)factor2,
            (double)(factor + 150.0F),
            (double)((float)((double)((float)(this.altDoubleEntry.value + ((double)count5 * this.altDoubleEntry.value - 2.0))) + this.altDoubleEntry.getValue()))
         )) {
            GoldenShard goldenShard = Client.getClient().getGoldenShard();
            String text = modularStringEntry.getLabelText().toLowerCase();
            goldenShard.applyText(text);
            ClientSettings.processShort();
         }

         count5++;
      }

      double offset = (double)count;
      double offset2 = (double)count4;
      double offset3 = offset;
      this.altDoubleEntry.applyDouble(offset3, offset2);
   }

   @Override
   public void renderElement() {
      super.renderElement();
      AltDoubleEntry altDoubleEntry2 = this.altDoubleEntry;
      int size = this.getList().size();
      altDoubleEntry2.setCount4(size);
      float factor = (float)(this.width + 102) / 2.0F;
      int count = (int)(factor + 152.0F);
      int count2 = (int)(factor + 155.0F);
      int count3 = this.height;
      int count4 = count2;
      byte byteValue = 52;
      int count5 = count;
      this.altDoubleEntry.applyIndex(count5, byteValue, count4, count3);
   }

   public ConfigCategoryPanel() {
      super("Background");
   }
}
