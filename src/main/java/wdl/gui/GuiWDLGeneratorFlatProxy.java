package wdl.gui;

import wdl.WDL;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateFlatWorld;

public class GuiWDLGeneratorFlatProxy extends GuiCreateFlatWorld {
   public final GuiWDLGenerator generatorGui;

   @Override
   public void initGui() {
      GuiWDLGenerator generatorGui2 = this.generatorGui;
      this.mc.displayGuiScreen(generatorGui2);
   }

   @Override
   public void setPreset(String str) {
      if (str == null) {
         str = "";
      }

      WDL.worldProps.setProperty("GeneratorOptions", str);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
   }

   @Override
   public String getPreset() {
      return WDL.worldProps.getProperty("GeneratorOptions", "");
   }

   public GuiWDLGeneratorFlatProxy(GuiWDLGenerator guiWDLGenerator) {
      super(null, WDL.worldProps.getProperty("GeneratorOptions", ""));
      this.generatorGui = guiWDLGenerator;
   }
}
