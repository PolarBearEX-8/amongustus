package client.gui;

import client.Client;
import client.ClientGlobals;
import client.util.AugustusMainMenuAdapter;
import client.util.AuxiliaryAugustusMainMenuAdapter;
import client.util.SoundPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

public class AugustusMainMenu extends ClientScreen {
   public final String labelText3 = StatCollector.getText("menu.singleplayer", new Object[0]);
   public final String labelText2;
   public static final int count5 = 2;
   public final String labelText = StatCollector.getText("menu.multiplayer", new Object[0]);
   public final String labelText4;

   public static GuiScreen resolveGuiScreen(short shortValue, char character, int count) {
      return (GuiScreen)(Client.getClient().getModuleManager().clientSettings.settingSideMainMenu.isEnabled()
         ? new AugustusMainMenuAdapter()
         : new AuxiliaryAugustusMainMenuAdapter());
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      int count3 = (int)((0xed85a06cf9bL ^ 0x40b6309b7ad7L) >>> 32);
      int count4 = (int)((0xed85a06cf9bL ^ 0x40b6309b7ad7L) << 32 >>> 32);
      if (Client.getClient().enabled6) {
         Client.getClient().getClass();
         SoundPlayer.processAction(SoundPlayer.resourceLocation10);
         Client.getClient().getTimeHelper().reset();
         Client.getClient().enabled6 = false;
      }

      super.applyIndex(count, count2, factor);
      Client clientItem = Client.getClient();
      String text = ClientGlobals.moduleManager.getCopyOnWriteArrayList2().size()
         + " modules and "
         + clientItem.getAuxiliaryCopyOnWriteArrayLis().getList().size()
         + " settings loaded";
      float factor2 = (float)(super.width - this.computeCount(text) - 2);
      float factor3 = 2.0F;
      float factor4 = factor2;
      this.applyTextByText(text, factor4, factor3);
      String text2 = "§e"
         + Client.getClient().getString7()
         + " §7[§e"
         + Client.getClient().getInt2()
         + "§7]§r ["
         + Client.getClient().getString3()
         + Client.getClient().getString6()
         + "§r]";
      float factor5 = 2.0F;
      float factor6 = 2.0F;
      this.applyTextByText(text2, factor6, factor5);
      String text3 = "Created by " + clientItem.resolveText(count3, count4);
      factor2 = (float)(super.width - this.computeCount(text3) - 2);
      float factor7 = (float)(super.height - super.fontRenderer.indexOffset - 2);
      float factor8 = factor2;
      this.applyTextByText(text3, factor8, factor7);
      String string = clientItem.getString5();
      float factor9 = (float)(super.height - super.fontRenderer.indexOffset - 2);
      float factor10 = 2.0F;
      String text4 = string;
      this.applyTextByText(text4, factor10, factor9);
   }

   public AugustusMainMenu() {
      this.labelText2 = StatCollector.getText("menu.options", new Object[0]);
      this.labelText4 = StatCollector.getText("menu.quit", new Object[0]);
   }
}
