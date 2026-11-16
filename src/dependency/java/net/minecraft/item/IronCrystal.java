package net.minecraft.item;

import client.gui.PrimaryGuiScreenAdapter;
import client.setting.ClientSettingsBlackType;
import client.util.ExtendedCustom;
import client.util.ExtendedMinecraftEntry;
import java.util.Date;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.StringUtils;

public class IronCrystal extends ExtendedMinecraftEntry {
   public final PrimaryGuiScreenAdapter primaryGuiScreenAdapter;

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      ExtendedCustom extendedCustom = (ExtendedCustom)PrimaryGuiScreenAdapter.resolveList(this.primaryGuiScreenAdapter).get(count);
      String labelText = extendedCustom.getLabelText2();
      if (StringUtils.isEmpty(labelText)) {
         labelText = PrimaryGuiScreenAdapter.resolveTextByPrimaryGuiScreen(this.primaryGuiScreenAdapter) + " " + (count + 1);
      }

      String labelText2 = extendedCustom.getLabelText();
      String text = labelText2 + " (" + PrimaryGuiScreenAdapter.resolveDateFormat(this.primaryGuiScreenAdapter).format(new Date(extendedCustom.getTimestamp()));
      String text2 = text + ")";
      String text3 = "";
      String text4;
      if (extendedCustom.isEnabled()) {
         text4 = PrimaryGuiScreenAdapter.resolveText(this.primaryGuiScreenAdapter) + " " + text3;
      } else {
         text4 = PrimaryGuiScreenAdapter.resolveTextArray(this.primaryGuiScreenAdapter)[extendedCustom.getWorldSettingsGameType().getIndex()];
         if (extendedCustom.isEnabled2()) {
            text4 = ClientSettingsBlackType.DARK_RED + StatCollector.getText("gameMode.hardcore", new Object[0]) + ClientSettingsBlackType.RESET;
         }

         if (extendedCustom.isEnabled3()) {
            text4 = text4 + ", " + StatCollector.getText("selectWorld.cheats", new Object[0]);
         }
      }

      this.primaryGuiScreenAdapter.applyFontRenderer(this.primaryGuiScreenAdapter.fontRenderer, labelText, count2 + 2, count3 + 1, 16777215);
      this.primaryGuiScreenAdapter.applyFontRenderer(this.primaryGuiScreenAdapter.fontRenderer, text2, count2 + 2, count3 + 12, 8421504);
      this.primaryGuiScreenAdapter.applyFontRenderer(this.primaryGuiScreenAdapter.fontRenderer, text4, count2 + 2, count3 + 12 + 10, 8421504);
   }

   @Override
   public boolean checkIndex(int count) {
      return count == PrimaryGuiScreenAdapter.computeCount(this.primaryGuiScreenAdapter);
   }

   @Override
   public void applyIndex(int count, boolean flag, int count2, int count3) {
      PrimaryGuiScreenAdapter.computeCount(this.primaryGuiScreenAdapter, count);
      boolean flag2 = PrimaryGuiScreenAdapter.computeCount(this.primaryGuiScreenAdapter) >= 0
         && PrimaryGuiScreenAdapter.computeCount(this.primaryGuiScreenAdapter) < this.size();
      PrimaryGuiScreenAdapter.resolveAuxiliaryTexturesGuiIconsPng(this.primaryGuiScreenAdapter).enabled3 = flag2;
      PrimaryGuiScreenAdapter.resolveAuxiliaryTexturesGuiIconsPng4(this.primaryGuiScreenAdapter).enabled3 = flag2;
      PrimaryGuiScreenAdapter.resolveAuxiliaryTexturesGuiIconsPng2(this.primaryGuiScreenAdapter).enabled3 = flag2;
      PrimaryGuiScreenAdapter.resolveAuxiliaryTexturesGuiIconsPng3(this.primaryGuiScreenAdapter).enabled3 = flag2;
      if (flag && flag2) {
         this.primaryGuiScreenAdapter.applyIndex(count);
      }
   }

   @Override
   public int getCount() {
      return PrimaryGuiScreenAdapter.resolveList(this.primaryGuiScreenAdapter).size() * 36;
   }

   public IronCrystal(PrimaryGuiScreenAdapter primaryGuiScreenAdapter2, Minecraft mc) {
      super(mc, primaryGuiScreenAdapter2.width, primaryGuiScreenAdapter2.height, 32, primaryGuiScreenAdapter2.height - 64, 36);
      this.primaryGuiScreenAdapter = primaryGuiScreenAdapter2;
   }

   @Override
   public void updateState2() {
      this.primaryGuiScreenAdapter.renderElement3();
   }

   @Override
   public int size() {
      return PrimaryGuiScreenAdapter.resolveList(this.primaryGuiScreenAdapter).size();
   }
}
