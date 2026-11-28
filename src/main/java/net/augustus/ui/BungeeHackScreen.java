package net.augustus.ui;

import java.util.UUID;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;

public class BungeeHackScreen extends AugustusGuiScreen {
   public static boolean enabled = false;
   private GuiTextField targetAddressField;
   public static String spoofedUuid = UUID.randomUUID().toString();
   public static String spoofedIp = "127.0.0.1";
   private GuiTextField playerUuidField;

   private void onToggleButtonClicked(GuiButton button) {
      enabled = !enabled;
      button.displayString = this.getToggleButtonLabel();
   }

   public BungeeHackScreen(GuiScreen screen) {
      super(screen);
   }

   private String getToggleButtonLabel() {
      return "Enabled: " + (enabled ? EnumChatFormatting.GREEN + "yes" : EnumChatFormatting.RED + "no");
   }

   public static String appendBungeeForwarding(String str) {
      if (enabled) {
         str = str + "\u0000" + spoofedIp + "\u00000" + spoofedUuid.replace("-", "");
      }

      return str;
   }

   @Override
   public void drawContent(int n, int n2, float f) {
      this.mc
         .fontRendererObj
         .drawStringWithShadow(
            "Target Address", (float)(this.targetAddressField.xPosition + this.targetAddressField.getWidth() + 12), (float)this.targetAddressField.yPosition + (float)this.targetAddressField.getHeight() / 4.0F, -1
         );
      this.mc
         .fontRendererObj
         .drawStringWithShadow("Player UUID", (float)(this.playerUuidField.xPosition + this.playerUuidField.getWidth() + 12), (float)this.playerUuidField.yPosition + (float)this.playerUuidField.getHeight() / 4.0F, -1);
   }

   @Override
   public void initContent() {
      super.initContent();
      int n = this.height / 4 + 48;
      int n2 = this.width / 2 - 100;
      this.targetAddressField = this.addTextField(n2, n, (short)200, (byte)20);
      n2 = this.width / 2 - 100;
      this.playerUuidField = this.addTextField(n2, n + 20 + 3, (short)200, (byte)20);
      this.targetAddressField.setText(spoofedIp);
      this.playerUuidField.setText(spoofedUuid);
      this.targetAddressField.setTextChangeListener(obj -> spoofedIp = (String)obj);
      this.playerUuidField.setTextChangeListener(obj2 -> spoofedUuid = (String)obj2);
      int n7 = this.width / 2 - 100;
      n2 = this.height / 2 + 40;
      this.addButton(n7, n2, (short)200, (byte)20, this.getToggleButtonLabel(), button -> this.onToggleButtonClicked(button));
   }

}
