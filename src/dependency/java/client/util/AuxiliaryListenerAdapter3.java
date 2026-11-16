package client.util;

import client.event.PreUpdateDispatcher;
import client.gui.AuxiliaryTexturesGuiIconsPngManagerAdapter;
import client.setting.ClientSettingsBlackType;
import client.setting.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;

public class AuxiliaryListenerAdapter3 implements PreUpdateDispatcher {
   public final KeyBinding keyBinding;
   public final String labelText;
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter;
   public final AuxiliaryTexturesGuiIconsPngManagerAdapter auxiliaryTexturesGuiIconsPngManagerAdapter2;
   public final AuxiliaryClientManagerProvider auxiliaryClientManagerProvider;

   @Override
   public boolean checkIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      if (this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .matchesMinecraft(AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider), count2, count3)) {
         AuxiliaryClientManagerProvider.resolveStandardGuiScreenAdapter(this.auxiliaryClientManagerProvider).keyBinding = this.keyBinding;
         return true;
      } else if (this.auxiliaryTexturesGuiIconsPngManagerAdapter2
         .matchesMinecraft(AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider), count2, count3)) {
         AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider)
            .gameSettings
            .processAction(this.keyBinding, this.keyBinding.getKeyCodeDefault());
         KeyBinding.unPressAllKeys();
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6, int count7, boolean flag) {
      boolean flag2 = AuxiliaryClientManagerProvider.resolveStandardGuiScreenAdapter(this.auxiliaryClientManagerProvider).keyBinding == this.keyBinding;
      AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider)
         .fontRendererObj
         .getIndex(
            this.labelText,
            count2 + 90 - AuxiliaryClientManagerProvider.computeCount(this.auxiliaryClientManagerProvider),
            count3 + count5 / 2 - AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider).fontRendererObj.indexOffset / 2,
            16777215
         );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count = count2 + 190;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.count3 = count3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.enabled3 = this.keyBinding.getKeyCode() != this.keyBinding.getKeyCodeDefault();
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2
         .applyMinecraftByMinecraft(AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider), count6, count7);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count = count2 + 105;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.count3 = count3;
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = GameSettings.resolveText(this.keyBinding.getKeyCode());
      boolean flag3 = false;
      if (this.keyBinding.getKeyCode() != 0) {
         for (KeyBinding keyBinding2 : AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider).gameSettings.scorePlayerTeamArray2) {
            if (keyBinding2 != this.keyBinding && keyBinding2.getKeyCode() == this.keyBinding.getKeyCode()) {
               flag3 = true;
               break;
            }
         }
      }

      if (flag2) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = ClientSettingsBlackType.WHITE
            + "> "
            + ClientSettingsBlackType.YELLOW
            + this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText
            + ClientSettingsBlackType.WHITE
            + " <";
      } else if (flag3) {
         this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText = ClientSettingsBlackType.RED + this.auxiliaryTexturesGuiIconsPngManagerAdapter.labelText;
      }

      this.auxiliaryTexturesGuiIconsPngManagerAdapter
         .applyMinecraftByMinecraft(AuxiliaryClientManagerProvider.resolveMinecraft(this.auxiliaryClientManagerProvider), count6, count7);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      this.auxiliaryTexturesGuiIconsPngManagerAdapter.applyIndex(count2, count3);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2.applyIndex(count2, count3);
   }

   public AuxiliaryListenerAdapter3(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider2, KeyBinding keyBinding2, Controller24 controller) {
      this(auxiliaryClientManagerProvider2, keyBinding2);
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   private AuxiliaryListenerAdapter3(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider2, KeyBinding keyBinding2) {
      this.auxiliaryClientManagerProvider = auxiliaryClientManagerProvider2;
      this.keyBinding = keyBinding2;
      this.labelText = StatCollector.getText(keyBinding2.getKeyDescription(), new Object[0]);
      this.auxiliaryTexturesGuiIconsPngManagerAdapter = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, 0, 0, 75, 20, StatCollector.getText(keyBinding2.getKeyDescription(), new Object[0])
      );
      this.auxiliaryTexturesGuiIconsPngManagerAdapter2 = new AuxiliaryTexturesGuiIconsPngManagerAdapter(
         0, 0, 0, 50, 20, StatCollector.getText("controls.reset", new Object[0])
      );
   }
}
