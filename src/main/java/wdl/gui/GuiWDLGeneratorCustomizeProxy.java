package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;

public class GuiWDLGeneratorCustomizeProxy extends GuiCreateWorld {
   public final GuiWDLGenerator generatorGui;

   public GuiWDLGeneratorCustomizeProxy(GuiWDLGenerator guiWDLGenerator) {
      super(guiWDLGenerator);
      this.generatorGui = guiWDLGenerator;
      this.chunkProviderSettingsJson = WDL.worldProps.getProperty("GeneratorOptions", "");
   }

   @Override
   public void initGui() {
      GuiWDLGenerator generatorGui2 = this.generatorGui;
      this.mc.displayGuiScreen(generatorGui2);
      WDL.worldProps.setProperty("GeneratorOptions", this.chunkProviderSettingsJson);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
   }
}
