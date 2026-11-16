package client.util;

import client.setting.MovementNumberProperty;
import net.minecraft.nbt.NBTTagCompound;

public class PropertiesTypeDampenerSorter extends DampenerSorter {
   public final NBTTagCompound nBTTagCompound;
   public final String labelText;
   public final PassiveStringEntry passiveStringEntry;

   public PropertiesTypeDampenerSorter(PassiveStringEntry passiveStringEntry2, NBTTagCompound tagCompound) {
      this(passiveStringEntry2, tagCompound.getNBTTagCompound("Properties"), tagCompound.resolveText("Type"), tagCompound.computeCount("Weight"));
   }

   private PropertiesTypeDampenerSorter(PassiveStringEntry passiveStringEntry2, NBTTagCompound tagCompound, String text, int count) {
      super(count);
      this.passiveStringEntry = passiveStringEntry2;
      if (text.equals("Minecart")) {
         if (tagCompound != null) {
            text = MovementNumberProperty.resolveMovementNumberProperty(tagCompound.computeCount("Type")).getLabelText();
         } else {
            text = "MinecartRideable";
         }
      }

      this.nBTTagCompound = tagCompound;
      this.labelText = text;
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.applyText("Properties", this.nBTTagCompound);
      tagCompound.put("Type", this.labelText);
      tagCompound.put("Weight", this.count);
      return tagCompound;
   }

   public static NBTTagCompound resolveNBTTagCompound(PropertiesTypeDampenerSorter propertiesTypeDampenerSorter) {
      return propertiesTypeDampenerSorter.nBTTagCompound;
   }

   public static String resolveText(PropertiesTypeDampenerSorter propertiesTypeDampenerSorter) {
      return propertiesTypeDampenerSorter.labelText;
   }

   public PropertiesTypeDampenerSorter(PassiveStringEntry passiveStringEntry2, NBTTagCompound tagCompound, String text) {
      this(passiveStringEntry2, tagCompound, text, 1);
   }
}
