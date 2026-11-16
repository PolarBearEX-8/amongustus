package client.gui;

import augustus.modules.misc.ClientSettings;
import client.event.PreMotionObserver;
import client.setting.WorldSettingsGameType;
import client.util.AdvancedLongEntry;
import client.util.ExtendedCustom;
import client.util.GeometryPredictor;
import client.util.GeometrySynchronizer;
import client.util.RandomSeedManager;
import client.util.TertiaryExceptionAdapter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.IronCrystal;
import net.minecraft.util.StatCollector;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimaryGuiScreenAdapter extends GuiScreen implements PreMotionObserver {
   public static final Logger logger2 = LogManager.getLogger();
   public final DateFormat dateFormat = new SimpleDateFormat();
   public String labelText3 = "Select world";
   public boolean enabled7;
   public int count2;
   public List list4;
   public IronCrystal ironCrystal;
   public String labelText;
   public String labelText2;
   public String[] stringArray = new String[4];
   public boolean enabled6;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter4;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter3;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter5;

   @Override
   public void updateState2() {
      this.labelText3 = StatCollector.getText("selectWorld.title", new Object[0]);

      try {
         this.renderElement5();
      } catch (TertiaryExceptionAdapter exception) {
         logger2.error("Couldn't load level list", exception);
         Minecraft mc = this.mc;
         GeometrySynchronizer geometrySynchronizer = new GeometrySynchronizer("Unable to load worlds", exception.getMessage());
         mc.displayGuiScreen((GuiScreen)geometrySynchronizer);
         return;
      }

      this.labelText = StatCollector.getText("selectWorld.world", new Object[0]);
      this.labelText2 = StatCollector.getText("selectWorld.conversion", new Object[0]);
      this.stringArray[WorldSettingsGameType.SURVIVAL.getIndex()] = StatCollector.getText("gameMode.survival", new Object[0]);
      this.stringArray[WorldSettingsGameType.CREATIVE.getIndex()] = StatCollector.getText("gameMode.creative", new Object[0]);
      this.stringArray[WorldSettingsGameType.ADVENTURE.getIndex()] = StatCollector.getText("gameMode.adventure", new Object[0]);
      this.stringArray[WorldSettingsGameType.SPECTATOR.getIndex()] = StatCollector.getText("gameMode.spectator", new Object[0]);
      this.ironCrystal = new IronCrystal(this, this.mc);
      this.ironCrystal.applyIndex2(4, 5);
      this.renderElement();
   }

   public String resolveText(int count) {
      return ((ExtendedCustom)this.list4.get(count)).getLabelText();
   }

   public static DefaultGuiScreenAdapter resolveDefaultGuiScreenAdapter(PreMotionObserver preMotionObserver, String text, int count) {
      String text2 = StatCollector.getText("selectWorld.deleteQuestion", new Object[0]);
      String text3 = "'" + text + "' " + StatCollector.getText("selectWorld.deleteWarning", new Object[0]);
      String text4 = StatCollector.getText("selectWorld.deleteButton", new Object[0]);
      String text5 = StatCollector.getText("gui.cancel", new Object[0]);
      return new DefaultGuiScreenAdapter(preMotionObserver, text2, text3, text4, text5, count);
   }

   public static String[] resolveTextArray(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.stringArray;
   }

   public static List resolveList(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.list4;
   }

   public static int computeCount(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.count2;
   }

   public static String resolveText(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.labelText2;
   }

   public static AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng2(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.auxiliaryTexturesGuiIconsPngManagerAdapter2;
   }

   public void renderElement() {
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter3 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               1, this.width / 2 - 154, this.height - 52, 150, 20, StatCollector.getText("selectWorld.select", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               3, this.width / 2 + 4, this.height - 52, 150, 20, StatCollector.getText("selectWorld.create", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               6, this.width / 2 - 154, this.height - 28, 72, 20, StatCollector.getText("selectWorld.rename", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter4 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               2, this.width / 2 - 76, this.height - 28, 72, 20, StatCollector.getText("selectWorld.delete", new Object[0])
            )
         );
      this.list
         .add(
            this.auxiliaryTexturesGuiIconsPngManagerAdapter5 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               7, this.width / 2 + 4, this.height - 28, 72, 20, StatCollector.getText("selectWorld.recreate", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               0, this.width / 2 + 82, this.height - 28, 72, 20, StatCollector.getText("gui.cancel", new Object[0])
            )
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter3.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter4.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter5.enabled3 = false;
   }

   public static AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.auxiliaryTexturesGuiIconsPngManagerAdapter3;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 2) {
            String text = this.resolveTextByIndex(this.count2);
            if (text != null) {
               this.enabled6 = true;
               DefaultGuiScreenAdapter defaultGuiScreenAdapter = resolveDefaultGuiScreenAdapter(this, text, this.count2);
               this.mc.displayGuiScreen(defaultGuiScreenAdapter);
            }
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 1) {
            this.applyIndex(this.count2);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 3) {
            Minecraft mc = this.mc;
            EnhancedGuiScreenAdapter enhancedGuiScreenAdapter = new EnhancedGuiScreenAdapter(this);
            mc.displayGuiScreen(enhancedGuiScreenAdapter);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 6) {
            Minecraft mc2 = this.mc;
            GeometryPredictor geometryPredictor = new GeometryPredictor(this, this.resolveText(this.count2));
            mc2.displayGuiScreen(geometryPredictor);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 0) {
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         } else if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 7) {
            EnhancedGuiScreenAdapter enhancedGuiScreenAdapter2 = new EnhancedGuiScreenAdapter(this);
            ISaveHandler iSaveHandler = this.mc.getISaveFormat().resolveISaveHandler(this.resolveText(this.count2), false);
            RandomSeedManager randomSeedManager = iSaveHandler.getRandomSeedManager();
            iSaveHandler.updateState();
            enhancedGuiScreenAdapter2.applyRandomSeedManager(randomSeedManager);
            this.mc.displayGuiScreen(enhancedGuiScreenAdapter2);
         } else {
            this.ironCrystal.applyAuxiliaryTexturesGuiIconsP(auxiliaryTexturesGuiIconsPngManagerAdapter);
         }
      }
   }

   public static DateFormat resolveDateFormat(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.dateFormat;
   }

   public static AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng3(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.auxiliaryTexturesGuiIconsPngManagerAdapter5;
   }

   @Override
   public void updateState3() throws IOException {
      super.updateState3();
      this.ironCrystal.updateState();
   }

   public String resolveTextByIndex(int count) {
      String labelText4 = ((ExtendedCustom)this.list4.get(count)).getLabelText2();
      if (StringUtils.isEmpty(labelText4)) {
         labelText4 = StatCollector.getText("selectWorld.world", new Object[0]) + " " + (count + 1);
      }

      return labelText4;
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      if (this.enabled6) {
         this.enabled6 = false;
         if (flag) {
            ISaveFormat iSaveFormat = this.mc.getISaveFormat();
            iSaveFormat.updateState();
            iSaveFormat.matchesText2(this.resolveText(count));

            try {
               this.renderElement5();
            } catch (TertiaryExceptionAdapter exception) {
               logger2.error("Couldn't load level list", exception);
            }
         }

         this.mc.displayGuiScreen(this);
      }
   }

   public static int computeCount(PrimaryGuiScreenAdapter primaryGuiScreenAdapter, int count) {
      return primaryGuiScreenAdapter.count2 = count;
   }

   public static AuxiliaryTexturesGuiIconsPngManagerAdapter resolveAuxiliaryTexturesGuiIconsPng4(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.auxiliaryTexturesGuiIconsPngManagerAdapter4;
   }

   @Override
   public void applyIndex(int count, int count3, float factor) {
      this.ironCrystal.applyIndex(count, count3, factor);
      applyFontRenderer4(this.fontRenderer, this.labelText3, this.width / 2, 20, 16777215);
      super.applyIndex(count, count3, factor);
      ClientSettings.processAction();
      ClientSettings.setActive4(false);
   }

   private void renderElement5() throws TertiaryExceptionAdapter {
      ISaveFormat iSaveFormat = this.mc.getISaveFormat();
      this.list4 = iSaveFormat.getList();
      Collections.sort(this.list4);
      this.count2 = -1;
   }

   public static String resolveTextByPrimaryGuiScreen(PrimaryGuiScreenAdapter primaryGuiScreenAdapter) {
      return primaryGuiScreenAdapter.labelText;
   }

   public void applyIndex(int count) {
      if (!this.enabled7) {
         this.enabled7 = true;
         String text = this.resolveText(count);
         if (text == null) {
            text = "World" + count;
         }

         String text2 = this.resolveTextByIndex(count);
         if (text2 == null) {
            text2 = "World" + count;
         }

         if (this.mc.getISaveFormat().isDirectory(text)) {
            AdvancedLongEntry advancedLongEntry = null;
            this.mc.applyText(text, text2, advancedLongEntry);
         }
      }
   }

   public PrimaryGuiScreenAdapter(GuiScreen screen) {
      super(screen);
   }
}
