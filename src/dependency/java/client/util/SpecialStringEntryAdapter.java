package client.util;

public class SpecialStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (clip forwards and sidewards through walls)";
      this.applyText(text);
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length == 3) {
         double offset = Double.parseDouble(textArray[1]);
         double offset2 = Double.parseDouble(textArray[2]);
         if (MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.thePlayer != null) {
            MinecraftAccessor.minecraft
               .thePlayer
               .processDouble2(
                  MinecraftAccessor.minecraft.thePlayer.posX + offset,
                  MinecraftAccessor.minecraft.thePlayer.posY,
                  MinecraftAccessor.minecraft.thePlayer.posZ + offset2
               );
         }

         String text2 = "hcliped";
         this.applyText(text2);
      } else if (textArray.length == 2) {
         double offset3 = Double.parseDouble(textArray[1]);
         double offset4 = Math.toRadians((double)MoveUtil.computeFactor(false));
         double offset5 = -(Math.sin(offset4) * offset3);
         double offset6 = Math.cos(offset4) * offset3;
         if (MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.thePlayer != null) {
            MinecraftAccessor.minecraft
               .thePlayer
               .processDouble2(
                  MinecraftAccessor.minecraft.thePlayer.posX + offset5,
                  MinecraftAccessor.minecraft.thePlayer.posY,
                  MinecraftAccessor.minecraft.thePlayer.posZ + offset6
               );
         }
      } else {
         String text3 = "§7" + this.getString() + " [§2DistanceX§7] [§2DistanceZ§7] or";
         this.applyText(text3);
         String text4 = "§7" + this.getString() + " [§2DistanceXZ§7]";
         this.applyText(text4);
      }
   }

   public SpecialStringEntryAdapter() {
      super("hclip");
   }
}
