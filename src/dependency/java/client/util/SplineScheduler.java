package client.util;

import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class SplineScheduler extends GuiScreen {
   public final GameSettings gameSettings;
   public final List list3 = Lists.newArrayList();
   public final List list4 = Lists.newArrayList();
   public String labelText;
   public String[] stringArray;
   public SplineSchedulerExtendedMinecraftEntry splineSchedulerExtendedMinecraftEntry;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;

   public SplineScheduler(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            this.gameSettings.updateState();
            this.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(FactoryOptionType.SNOOPER_ENABLED, 1);
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.SNOOPER_ENABLED);
         }
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      this.splineSchedulerExtendedMinecraftEntry.applyIndex(count, count2, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 8, 16777215);
      int count3 = 22;

      for (String text : this.stringArray) {
         applyFontRenderer4(this.fontRenderer, text, this.width / 2, count3, 8421504);
         count3 += this.fontRenderer.indexOffset;
      }

      super.applyIndex(count, count2, factor);
   }

   public static List resolveList(SplineScheduler splineScheduler) {
      return splineScheduler.list3;
   }

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("options.snooper.title", new Object[0]);
      String text = StatCollector.getText("options.snooper.desc", new Object[0]);
      ArrayList list = Lists.newArrayList();

      for (Object element : this.fontRenderer.getList(text, this.width - 30)) {
         list.add((String)element);
      }

      this.stringArray = (String[]) list.toArray(new String[list.size()]);
      this.list3.clear();
      this.list4.clear();
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 152, this.height - 30, 150, 20, this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.SNOOPER_ENABLED)
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(2, this.width / 2 + 2, this.height - 30, 150, 20, StatCollector.getText("gui.done", new Object[0]))
         );
      boolean flag = this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null;

      for (Entry entry : (Iterable<Entry>) new TreeMap(this.mc.getPlayerUsageSnooper().getMap()).entrySet()) {
         this.list3.add((flag ? "C " : "") + (String)entry.getKey());
         this.list4.add(this.fontRenderer.resolveTextByText((String)entry.getValue(), this.width - 220));
      }

      if (flag) {
         for (Entry entry2 : (Iterable<Entry>) new TreeMap(this.mc.getIntegratedServer().getPlayerUsageSnooper().getMap()).entrySet()) {
            this.list3.add("S " + (String)entry2.getKey());
            this.list4.add(this.fontRenderer.resolveTextByText((String)entry2.getValue(), this.width - 220));
         }
      }

      this.splineSchedulerExtendedMinecraftEntry = new SplineSchedulerExtendedMinecraftEntry(this);
   }

   public static List resolveListBySplineScheduler(SplineScheduler splineScheduler) {
      return splineScheduler.list4;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.splineSchedulerExtendedMinecraftEntry.updateState();
   }
}
