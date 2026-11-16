package client.gui;

import client.setting.AutoPlayTargetEntity;
import client.util.CommonListEntry;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;

public class KeybindCaptureField extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final CommonGuiScreenAdapter commonGuiScreenAdapter;

   @Override
   public void applySoundHandler(SoundHandler soundHandler) {
      CommonListEntry commonListEntry = soundHandler.resolveCommonListEntry(
         new AutoPlayTargetEntity[]{
            AutoPlayTargetEntity.ANIMALS, AutoPlayTargetEntity.BLOCKS, AutoPlayTargetEntity.MOBS, AutoPlayTargetEntity.PLAYERS, AutoPlayTargetEntity.WEATHER
         }
      );
      if (commonListEntry != null) {
         soundHandler.applyISound2(PositionedSoundRecord.createButtonPress(commonListEntry.getResourceLocation(), 0.5F));
      }
   }

   public KeybindCaptureField(CommonGuiScreenAdapter commonGuiScreenAdapter2, int count, int count2, int count3, int count4, int count5, String text) {
      super(count, count2, count3, count4, count5, text);
      this.commonGuiScreenAdapter = commonGuiScreenAdapter2;
   }
}
