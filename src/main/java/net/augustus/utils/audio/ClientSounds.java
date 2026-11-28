package net.augustus.utils.audio;

import net.augustus.utils.ClientHoliday;
import net.augustus.utils.HolidayDetector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class ClientSounds {
   public static final ResourceLocation AUGUSTUS_TEXTURE = new ResourceLocation("augustus", "sounds/buttonClick.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE2 = new ResourceLocation("augustus", "sounds/loginSuccessful.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE3 = new ResourceLocation("augustus", "sounds/welcome_to_augustus.ogg");
   public static final ResourceLocation GUI_TEXTURE = new ResourceLocation("gui.button.press");
   public static final ResourceLocation AUGUSTUS_TEXTURE4;
   public static final ResourceLocation AUGUSTUS_TEXTURE5;
   public static final ResourceLocation AUGUSTUS_TEXTURE6 = new ResourceLocation("augustus", "sounds/loginFailed.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE7 = new ResourceLocation("augustus", "sounds/alarm.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE8 = new ResourceLocation("augustus", "sounds/epsound.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE9 = new ResourceLocation("augustus", "sounds/levelup.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE10 = new ResourceLocation("augustus", "sounds/mlg/hitmarker.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE11;
   public static final ResourceLocation AUGUSTUS_TEXTURE12 = new ResourceLocation("augustus", "sounds/knockknock.ogg");
   public static final ResourceLocation AUGUSTUS_TEXTURE13;
   public static final ResourceLocation AUGUSTUS_TEXTURE14 = new ResourceLocation("augustus", "sounds/jumpscare.ogg");

   static {
      AUGUSTUS_TEXTURE4 = HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN
         ? new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg")
         : new ResourceLocation("augustus", "sounds/toggleSound.ogg");
      AUGUSTUS_TEXTURE11 = HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN
         ? new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg")
         : new ResourceLocation("augustus", "sounds/toggleSound2.ogg");
      AUGUSTUS_TEXTURE5 = HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN
         ? new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg")
         : new ResourceLocation("augustus", "sounds/enable.ogg");
      AUGUSTUS_TEXTURE13 = HolidayDetector.getCurrentHoliday() == ClientHoliday.HALLOWEEN
         ? new ResourceLocation("augustus", "sounds/halloweenToggleSound.ogg")
         : new ResourceLocation("augustus", "sounds/disable.ogg");
   }

   public static void playSound(ResourceLocation location) {
      if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getSoundHandler() != null) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(location));
      }
   }
}
