package client.util;

import client.event.PreUpdateDispatcher;
import client.gui.StandardGuiScreenAdapter;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.ArrayUtils;

public class AuxiliaryClientManagerProvider extends SplineCalculator {
   public final StandardGuiScreenAdapter standardGuiScreenAdapter;
   public final Minecraft mc2;
   public final PreUpdateDispatcher[] preUpdateDispatcherArray;
   public int count20 = 0;

   public static Minecraft resolveMinecraft(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider) {
      return auxiliaryClientManagerProvider.mc2;
   }

   @Override
   public int size() {
      return this.preUpdateDispatcherArray.length;
   }

   public AuxiliaryClientManagerProvider(StandardGuiScreenAdapter standardGuiScreenAdapter2, Minecraft mc) {
      super(mc, standardGuiScreenAdapter2.width, standardGuiScreenAdapter2.height, 63, standardGuiScreenAdapter2.height - 32, 20);
      this.standardGuiScreenAdapter = standardGuiScreenAdapter2;
      this.mc2 = mc;
      KeyBinding[] keyBindingArray = (KeyBinding[])ArrayUtils.clone(mc.gameSettings.scorePlayerTeamArray2);
      this.preUpdateDispatcherArray = new PreUpdateDispatcher[keyBindingArray.length + KeyBinding.getKeybinds().size()];
      Arrays.sort(keyBindingArray);
      int count = 0;
      String text = null;

      for (KeyBinding keyBinding : keyBindingArray) {
         String keyCategory = keyBinding.getKeyCategory();
         if (!keyCategory.equals(text)) {
            text = keyCategory;
            this.preUpdateDispatcherArray[count++] = new ListenerAdapter15(this, keyCategory);
         }

         int index = mc.fontRendererObj.getIndex(StatCollector.getText(keyBinding.getKeyDescription(), new Object[0]));
         if (index > this.count20) {
            this.count20 = index;
         }

         this.preUpdateDispatcherArray[count++] = new AuxiliaryListenerAdapter3(this, keyBinding, null);
      }
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return this.preUpdateDispatcherArray[count];
   }

   @Override
   public int getCount22() {
      return super.getCount22() + 15;
   }

   public static StandardGuiScreenAdapter resolveStandardGuiScreenAdapter(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider) {
      return auxiliaryClientManagerProvider.standardGuiScreenAdapter;
   }

   public static int computeCount(AuxiliaryClientManagerProvider auxiliaryClientManagerProvider) {
      return auxiliaryClientManagerProvider.count20;
   }

   @Override
   public int getCount20() {
      return super.getCount20() + 32;
   }
}
