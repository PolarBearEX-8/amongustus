package client.gui;

import client.Client;
import client.event.EventRenderArrayListListener;
import client.module.Module;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import client.util.AuxiliaryManager;
import client.util.BuildManager;
import client.util.CurveCalculator;
import client.util.CurveTransformer;
import client.util.FloatEntryHandler;
import client.util.GeneratorDefaultManager;
import client.util.ListenerAdapter14;
import client.util.SplineCoordinator;
import client.util.SplineDispatcher;
import client.util.SplineEstimator;
import client.util.TooltipManager;
import java.io.IOException;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StatCollector;

public class CoreGuiScreenAdapter extends GuiScreen {
   public String labelText = "Video Settings";
   public GameSettings gameSettings;
   public static FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{
      FactoryOptionType.GRAPHICS,
      FactoryOptionType.RENDER_DISTANCE,
      FactoryOptionType.AMBIENT_OCCLUSION,
      FactoryOptionType.FRAMERATE_LIMIT,
      FactoryOptionType.AO_LEVEL,
      FactoryOptionType.VIEW_BOBBING,
      FactoryOptionType.GUI_SCALE,
      FactoryOptionType.USE_VBO,
      FactoryOptionType.GAMMA,
      FactoryOptionType.BLOCK_ALTERNATIVES,
      FactoryOptionType.DYNAMIC_LIGHTS,
      FactoryOptionType.DYNAMIC_FOV
   };
   public AuxiliaryManager auxiliaryManager = new AuxiliaryManager(this, new TooltipManager());

   @Override
   public void updateState2() {
      this.labelText = StatCollector.getText("options.videoTitle", new Object[0]);
      this.list.clear();

      for (int index = 0; index < factoryOptionTypeArray.length; index++) {
         FactoryOptionType factoryOptionType = factoryOptionTypeArray[index];
         if (factoryOptionType != null) {
            int count = this.width / 2 - 155 + index % 2 * 160;
            int count2 = this.height / 6 + 21 * (index / 2) - 12;
            if (factoryOptionType.isEnabled()) {
               this.list.add(new FloatEntryHandler(factoryOptionType.ordinalAsIndex(), count, count2, factoryOptionType));
            } else {
               this.list.add(new ListenerAdapter14(factoryOptionType.ordinalAsIndex(), count, count2, factoryOptionType, this.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)));
            }
         }
      }

      int count3 = this.height / 6 + 21 * (factoryOptionTypeArray.length / 2) - 12;
      int count4 = this.width / 2 - 155 + 0;
      this.list.add(new ValueSliderControl(231, count4, count3, GeneratorDefaultManager.resolveText("of.options.shaders")));
      int count5 = this.width / 2 - 155 + 160;
      this.list.add(new ValueSliderControl(202, count5, count3, GeneratorDefaultManager.resolveText("of.options.quality")));
      int count6 = count3 + 21;
      int count7 = this.width / 2 - 155 + 0;
      this.list.add(new ValueSliderControl(201, count7, count6, GeneratorDefaultManager.resolveText("of.options.details")));
      int count8 = this.width / 2 - 155 + 160;
      this.list.add(new ValueSliderControl(212, count8, count6, GeneratorDefaultManager.resolveText("of.options.performance")));
      int count9 = count6 + 21;
      int count10 = this.width / 2 - 155 + 0;
      this.list.add(new ValueSliderControl(211, count10, count9, GeneratorDefaultManager.resolveText("of.options.animations")));
      int count11 = this.width / 2 - 155 + 160;
      this.list.add(new ValueSliderControl(222, count11, count9, GeneratorDefaultManager.resolveText("of.options.other")));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               200, this.width / 2 - 100, this.height / 6 + 168 + 11, StatCollector.getText("gui.done", new Object[0])
            )
         );
   }

   public static int computeCount(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      return auxiliaryTexturesGuiIconsPngManagerAdapter.count4;
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         int indexOffset = this.gameSettings.indexOffset109;
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 200 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
            this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType(), 1);
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            this.mc.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         }

         if (this.gameSettings.indexOffset109 != indexOffset) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            int scaledWidth = scaledResolution.getScaledWidth();
            int scaledHeight = scaledResolution.getScaledHeight();
            this.applyMinecraft2(this.mc, scaledWidth, scaledHeight);
            EventRenderArrayListListener.updateState12();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 201) {
            this.mc.gameSettings.updateState();
            SplineEstimator splineEstimator = new SplineEstimator(this, this.gameSettings);
            this.mc.displayGuiScreen(splineEstimator);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 202) {
            this.mc.gameSettings.updateState();
            CurveTransformer curveTransformer = new CurveTransformer(this, this.gameSettings);
            this.mc.displayGuiScreen(curveTransformer);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 211) {
            this.mc.gameSettings.updateState();
            CurveCalculator curveCalculator = new CurveCalculator(this, this.gameSettings);
            this.mc.displayGuiScreen(curveCalculator);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 212) {
            this.mc.gameSettings.updateState();
            SharedGuiScreenAdapter sharedGuiScreenAdapter = new SharedGuiScreenAdapter(this, this.gameSettings);
            this.mc.displayGuiScreen(sharedGuiScreenAdapter);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 222) {
            this.mc.gameSettings.updateState();
            SplineCoordinator splineCoordinator = new SplineCoordinator(this, this.gameSettings);
            this.mc.displayGuiScreen(splineCoordinator);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 231) {
            if (BuildManager.canExecute4() || BuildManager.isActive6()) {
               BuildManager.applyText(
                  GeneratorDefaultManager.resolveText("of.message.shaders.aa1"), GeneratorDefaultManager.resolveText("of.message.shaders.aa2")
               );
               return;
            }

            if (BuildManager.canExecute2()) {
               BuildManager.applyText(
                  GeneratorDefaultManager.resolveText("of.message.shaders.af1"), GeneratorDefaultManager.resolveText("of.message.shaders.af2")
               );
               return;
            }

            if (BuildManager.canExecute3()) {
               BuildManager.applyText(
                  GeneratorDefaultManager.resolveText("of.message.shaders.fr1"), GeneratorDefaultManager.resolveText("of.message.shaders.fr2")
               );
               return;
            }

            if (BuildManager.getGameSettings().state105) {
               BuildManager.applyText(
                  GeneratorDefaultManager.resolveText("of.message.shaders.an1"), GeneratorDefaultManager.resolveText("of.message.shaders.an2")
               );
               return;
            }

            this.mc.gameSettings.updateState();
            SplineDispatcher splineDispatcher = new SplineDispatcher(this, this.gameSettings);
            this.mc.displayGuiScreen(splineDispatcher);
         }
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      super.applyChar(character, count);
      if (count == 1) {
         this.mc.gameSettings.updateState();
      }
   }

   public static void applyGuiScreenByGuiScreen(GuiScreen screen, int count, int count2, int count3, int count4, int count5, int count6) {
      screen.applyIndex(count, count2, count3, count4, count5, count6);
   }

   public static String resolveText(GuiChat guiChat) {
      return guiChat.internalTexturesGuiIconsPngManagerAdapter.getLabelText();
   }

   public static int computeCountByAuxiliaryTexture(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) {
      return auxiliaryTexturesGuiIconsPngManagerAdapter.count5;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      this.applyFontRenderer(
         this.fontRenderer,
         ((Module)Client.getClient().getModuleManager().invisMode).isToggled()
            ? "OptiFine HD M5 Ultra"
            : "OptiFine HD M5 Ultra (Hard-forked by EnZaXD & DasNeueUpdate)",
         2,
         this.height - 10,
         8421504
      );
      String text = "Minecraft 1.8.9";
      int index = this.fontRenderer.getIndex(text);
      this.applyFontRenderer(this.fontRenderer, text, this.width - index - 2, this.height - 10, 8421504);
      super.applyIndex(count, count2, factor);
      this.auxiliaryManager.applyList(count, count2, this.list);
   }

   public CoreGuiScreenAdapter(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }
}
