package client.util;

import client.setting.ClientSettingsChristmasType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class SoundPlayer {
   public static final ResourceLocation resourceLocation;
   public static final ResourceLocation resourceLocation10;
   public static final ResourceLocation resourceLocation3;
   public static final ResourceLocation resourceLocation4;
   public static final ResourceLocation resourceLocation5;
   public static final ResourceLocation resourceLocation6;
   public static final ResourceLocation resourceLocation7;
   public static final ResourceLocation resourceLocation8;
   public static final ResourceLocation resourceLocation9;
   public static final ResourceLocation resourceLocation2;
   public static final ResourceLocation resourceLocation11;
   public static final ResourceLocation resourceLocation12;
   public static final ResourceLocation resourceLocation13;
   public static final ResourceLocation resourceLocation14;
   public static final ResourceLocation resourceLocation15;

   public static void processAction(ResourceLocation location) {
      if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getMcSoundHandler() != null) {
         Minecraft.getMinecraft().getMcSoundHandler().applyISound2(PositionedSoundRecord.createFullVolumeSound(location));
      }
   }

   static {
      String[] textArray = new String[]{
         "sounds/alarm.ogg",
         "sounds/toggleSound2.ogg",
         "augustus",
         "sounds/loginSuccessful.ogg",
         "sounds/knockknock.ogg",
         "sounds/levelup.ogg",
         "sounds/enable.ogg",
         "sounds/disable.ogg",
         "sounds/jumpscare.ogg",
         "sounds/welcome_to_augustus.ogg",
         "sounds/halloweenToggleSound.ogg",
         "sounds/mlg/hitmarker.ogg",
         "augustus",
         "sounds/loginFailed.ogg",
         "gui.button.press",
         "sounds/buttonClick.ogg",
         "sounds/halloweenToggleSound.ogg",
         "sounds/toggleSound.ogg",
         "sounds/epsound.ogg"
      };
      resourceLocation14 = new ResourceLocation(textArray[12], textArray[15]);
      resourceLocation12 = HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN
         ? new ResourceLocation(textArray[2], textArray[16])
         : new ResourceLocation(textArray[2], textArray[17]);
      resourceLocation15 = HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN
         ? new ResourceLocation(textArray[2], textArray[10])
         : new ResourceLocation(textArray[2], textArray[1]);
      resourceLocation13 = HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN
         ? new ResourceLocation(textArray[2], textArray[10])
         : new ResourceLocation(textArray[2], textArray[6]);
      resourceLocation11 = HolidayHelper.getClientSettingsChristmasType() == ClientSettingsChristmasType.HALLOWEEN
         ? new ResourceLocation(textArray[2], textArray[10])
         : new ResourceLocation(textArray[2], textArray[7]);
      resourceLocation7 = new ResourceLocation(textArray[2], textArray[3]);
      resourceLocation8 = new ResourceLocation(textArray[2], textArray[13]);
      resourceLocation9 = new ResourceLocation(textArray[2], textArray[8]);
      resourceLocation10 = new ResourceLocation(textArray[2], textArray[9]);
      resourceLocation = new ResourceLocation(textArray[2], textArray[0]);
      resourceLocation5 = new ResourceLocation(textArray[2], textArray[11]);
      resourceLocation4 = new ResourceLocation(textArray[2], textArray[4]);
      resourceLocation6 = new ResourceLocation(textArray[2], textArray[5]);
      resourceLocation2 = new ResourceLocation(textArray[2], textArray[18]);
      resourceLocation3 = new ResourceLocation(textArray[14]);
   }
}
