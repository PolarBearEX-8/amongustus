package client.util;

public class NativeStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (clip up and down through walls)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 2) {
         double offset = Double.parseDouble(textArray[1]);
         if (MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.thePlayer != null) {
            MinecraftAccessor.minecraft
               .thePlayer
               .processDouble2(
                  MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY + offset, MinecraftAccessor.minecraft.thePlayer.posZ
               );
         }

         String text2 = "vcliped";
         this.applyText(text2);
      } else {
         String text3 = "§7" + this.getString() + " [§2Distance§7]";
         this.applyText(text3);
      }
   }

   public NativeStringEntryAdapter() {
      super("vclip");
   }
}
