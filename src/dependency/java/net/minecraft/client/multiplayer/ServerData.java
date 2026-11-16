package net.minecraft.client.multiplayer;

import client.setting.ComplexOptionType;
import net.minecraft.nbt.NBTTagCompound;

public class ServerData {
   public String labelText3;
   public String text;
   public String labelText5;
   public String labelText;
   public long timestamp;
   public int count = 47;
   public String labelText4;
   public boolean enabled;
   public String labelText6;
   public ComplexOptionType complexOptionType = ComplexOptionType.PROMPT;
   public String labelText2;
   public boolean enabled2;

   public String getLabelText2() {
      return this.labelText2;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public ComplexOptionType getComplexOptionType() {
      return this.complexOptionType;
   }

   public static ServerData resolveServerData(NBTTagCompound tagCompound) {
      ServerData serverData = new ServerData(tagCompound.resolveText("name"), tagCompound.resolveText("ip"), false);
      if (tagCompound.matchesText("icon", 8)) {
         serverData.setLabelText2(tagCompound.resolveText("icon"));
      }

      if (tagCompound.matchesText("acceptTextures", 1)) {
         if (tagCompound.matchesText("acceptTextures")) {
            serverData.setComplexOptionType(ComplexOptionType.ENABLED);
         } else {
            serverData.setComplexOptionType(ComplexOptionType.DISABLED);
         }
      } else {
         serverData.setComplexOptionType(ComplexOptionType.PROMPT);
      }

      return serverData;
   }

   public void setLabelText2(String labelText7) {
      this.labelText2 = labelText7;
   }

   public void setComplexOptionType(ComplexOptionType complexOptionType2) {
      this.complexOptionType = complexOptionType2;
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      tagCompound.put("name", this.labelText3);
      tagCompound.put("ip", this.text);
      if (this.labelText2 != null) {
         tagCompound.put("icon", this.labelText2);
      }

      if (this.complexOptionType == ComplexOptionType.ENABLED) {
         tagCompound.applyText("acceptTextures", true);
      } else if (this.complexOptionType == ComplexOptionType.DISABLED) {
         tagCompound.applyText("acceptTextures", false);
      }

      return tagCompound;
   }

   public void applyServerData(ServerData serverData) {
      this.text = serverData.text;
      this.labelText3 = serverData.labelText3;
      this.setComplexOptionType(serverData.getComplexOptionType());
      this.labelText2 = serverData.labelText2;
      this.enabled2 = serverData.enabled2;
   }

   public ServerData(String text2, String text3, boolean flag) {
      this.labelText3 = text2;
      this.text = text3;
      this.enabled2 = flag;
   }
}
