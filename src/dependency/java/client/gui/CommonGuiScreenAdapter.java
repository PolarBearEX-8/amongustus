package client.gui;

import client.event.PreMotionObserver;
import client.setting.FactoryOptionType;
import client.setting.GameSettings;
import client.setting.GameSettingsPeacefulType;
import client.util.BaseManagerStyleIChatComponentAdapter;
import client.util.DynamicFloatEntry;
import client.util.GeometryEstimator;
import client.util.GeometryMonitor;
import client.util.GeometryPool;
import client.util.GeometryService;
import client.util.InterpolationAdapter;
import client.util.SplineScheduler;
import client.util.SplineStack;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class CommonGuiScreenAdapter extends GuiScreen implements PreMotionObserver {
   public static final FactoryOptionType[] factoryOptionTypeArray = new FactoryOptionType[]{FactoryOptionType.FOV};
   public final GameSettings gameSettings;
   public AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public InterpolationAdapter interpolationAdapter;
   public String labelText = "Options";

   @Override
   public void updateState2() {
      int count = 0;
      this.labelText = StatCollector.getText("options.title", new Object[0]);

      for (FactoryOptionType factoryOptionType : factoryOptionTypeArray) {
         if (factoryOptionType.isEnabled()) {
            this.list.add(new DynamicFloatEntry(factoryOptionType.ordinalAsIndex(), this.width / 2 - 155 + count % 2 * 160, this.height / 6 - 12 + 24 * (count >> 1), factoryOptionType));
         } else {
            ValueSliderControl valueSliderControl = new ValueSliderControl(
               factoryOptionType.ordinalAsIndex(),
               this.width / 2 - 155 + count % 2 * 160,
               this.height / 6 - 12 + 24 * (count >> 1),
               factoryOptionType,
               this.gameSettings.resolveTextByFactoryOptionTyp(factoryOptionType)
            );
            this.list.add(valueSliderControl);
         }

         count++;
      }

      if (this.mc.theWorld != null) {
         GameSettingsPeacefulType gameSettingsPeacefulType = this.mc.theWorld.getGameSettingsPeacefulType();
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
            108, this.width / 2 - 155 + count % 2 * 160, this.height / 6 - 12 + 24 * (count >> 1), 150, 20, this.resolveText(gameSettingsPeacefulType)
         );
         this.list.add(this.auxiliaryTexturesGuiIconsPngManagerAdapter2);
         if (this.mc.isFullScreen() && !this.mc.theWorld.getRandomSeedManager().isEnabled2()) {
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.setCount4(this.auxiliaryTexturesGuiIconsPngManagerAdapter2.getCount4() - 20);
            this.interpolationAdapter = new InterpolationAdapter(
               109,
               this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count + this.auxiliaryTexturesGuiIconsPngManagerAdapter2.getCount4(),
               this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3
            );
            this.list.add(this.interpolationAdapter);
            this.interpolationAdapter.setEnabled6(this.mc.theWorld.getRandomSeedManager().isEnabled());
            this.interpolationAdapter.enabled3 = !this.interpolationAdapter.isEnabled6();
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = !this.interpolationAdapter.isEnabled6();
         } else {
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
         }
      }

      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, StatCollector.getText("options.skinCustomisation", new Object[0])
            )
         );
      this.list.add(new KeybindCaptureField(this, 8675309, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Super Secret Settings..."));
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               106, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, StatCollector.getText("options.sounds", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               107, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, StatCollector.getText("options.stream", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               101, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, StatCollector.getText("options.video", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               100, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, StatCollector.getText("options.controls", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               102, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, StatCollector.getText("options.language", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               103, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, StatCollector.getText("options.chat.title", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               105, this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, StatCollector.getText("options.resourcepack", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(
               104, this.width / 2 + 5, this.height / 6 + 144 - 6, 150, 20, StatCollector.getText("options.snooper.view", new Object[0])
            )
         );
      this.list
         .add(
            new AuxiliaryTexturesGuiIconsPngManagerAdapter(200, this.width / 2 - 100, this.height / 6 + 168, StatCollector.getText("gui.done", new Object[0]))
         );
   }

   public String resolveText(GameSettingsPeacefulType gameSettingsPeacefulType) {
      ChatComponentText chatComponentText = new ChatComponentText("");
      chatComponentText.appendChatComponent(new BaseManagerStyleIChatComponentAdapter("options.difficulty", new Object[0]));
      chatComponentText.resolveIChatComponent(": ");
      chatComponentText.appendChatComponent(new BaseManagerStyleIChatComponentAdapter(gameSettingsPeacefulType.getLabelText(), new Object[0]));
      return chatComponentText.getString2();
   }

   @Override
   public void applyAuxiliaryTexturesGuiIconsP(AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter) throws IOException {
      if (auxiliaryTexturesGuiIconsPngManagerAdapter.enabled3) {
         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 < 100 && auxiliaryTexturesGuiIconsPngManagerAdapter instanceof ValueSliderControl) {
            FactoryOptionType factoryOptionType = ((ValueSliderControl)auxiliaryTexturesGuiIconsPngManagerAdapter).getFactoryOptionType();
            this.gameSettings.applyFactoryOptionTypeByFactoryOptionTyp(factoryOptionType, 1);
            auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = this.gameSettings.resolveTextByFactoryOptionTyp(FactoryOptionType.resolveFactoryOptionType(auxiliaryTexturesGuiIconsPngManagerAdapter.count2));
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 108) {
            this.mc
               .theWorld
               .getRandomSeedManager()
               .setGameSettingsPeacefulType2(
                  GameSettingsPeacefulType.resolveGameSettingsPeacefulType(this.mc.theWorld.getGameSettingsPeacefulType().getIndex() + 1)
               );
            this.auxiliaryTexturesGuiIconsPngManagerAdapter2.labelText = this.resolveText(this.mc.theWorld.getGameSettingsPeacefulType());
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 109) {
            Minecraft mc = this.mc;
            DefaultGuiScreenAdapter defaultGuiScreenAdapter = new DefaultGuiScreenAdapter(
               this,
               new BaseManagerStyleIChatComponentAdapter("difficulty.lock.title", new Object[0]).getString2(),
               new BaseManagerStyleIChatComponentAdapter(
                     "difficulty.lock.question",
                     new Object[]{
                        new BaseManagerStyleIChatComponentAdapter(
                           this.mc.theWorld.getRandomSeedManager().getGameSettingsPeacefulType2().getLabelText(), new Object[0]
                        )
                     }
                  )
                  .getString2(),
               109
            );
            mc.displayGuiScreen(defaultGuiScreenAdapter);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 110) {
            this.mc.gameSettings.updateState();
            Minecraft mc2 = this.mc;
            GeometryMonitor geometryMonitor = new GeometryMonitor(this);
            mc2.displayGuiScreen(geometryMonitor);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 8675309) {
            this.mc.entityRenderer.updateState2();
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 101) {
            this.mc.gameSettings.updateState();
            Minecraft mc3 = this.mc;
            CoreGuiScreenAdapter coreGuiScreenAdapter = new CoreGuiScreenAdapter(this, this.gameSettings);
            mc3.displayGuiScreen(coreGuiScreenAdapter);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 100) {
            this.mc.gameSettings.updateState();
            Minecraft mc4 = this.mc;
            StandardGuiScreenAdapter standardGuiScreenAdapter = new StandardGuiScreenAdapter(this, this.gameSettings);
            mc4.displayGuiScreen(standardGuiScreenAdapter);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 102) {
            this.mc.gameSettings.updateState();
            Minecraft mc5 = this.mc;
            GeometryService geometryService = new GeometryService(this, this.gameSettings, this.mc.getLanguageManager());
            mc5.displayGuiScreen(geometryService);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 103) {
            this.mc.gameSettings.updateState();
            Minecraft mc6 = this.mc;
            SplineStack splineStack = new SplineStack(this, this.gameSettings);
            mc6.displayGuiScreen(splineStack);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 104) {
            this.mc.gameSettings.updateState();
            Minecraft mc7 = this.mc;
            SplineScheduler splineScheduler = new SplineScheduler(this, this.gameSettings);
            mc7.displayGuiScreen(splineScheduler);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 200) {
            this.mc.gameSettings.updateState();
            GuiScreen guiScreen = this.guiScreen;
            this.mc.displayGuiScreen(guiScreen);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 105) {
            this.mc.gameSettings.updateState();
            Minecraft mc8 = this.mc;
            GeometryPool geometryPool = new GeometryPool(this);
            mc8.displayGuiScreen((GuiScreen)geometryPool);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 106) {
            this.mc.gameSettings.updateState();
            Minecraft mc9 = this.mc;
            GeometryEstimator geometryEstimator = new GeometryEstimator(this, this.gameSettings);
            mc9.displayGuiScreen(geometryEstimator);
         }

         if (auxiliaryTexturesGuiIconsPngManagerAdapter.count2 == 107) {
            this.mc.gameSettings.updateState();
         }
      }
   }

   @Override
   public void applyFlag(boolean flag, int count) {
      this.mc.displayGuiScreen(this);
      if (count == 109 && flag && this.mc.theWorld != null) {
         this.mc.theWorld.getRandomSeedManager().setEnabled(true);
         this.interpolationAdapter.setEnabled6(true);
         this.interpolationAdapter.enabled3 = false;
         this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      this.renderElement3();
      applyFontRenderer4(this.fontRenderer, this.labelText, this.width / 2, 15, 16777215);
      super.applyIndex(count, count2, factor);
   }

   public CommonGuiScreenAdapter(GuiScreen screen, GameSettings gameSettings2) {
      super(screen);
      this.gameSettings = gameSettings2;
      GuiScreen.set.add(this);
   }
}
