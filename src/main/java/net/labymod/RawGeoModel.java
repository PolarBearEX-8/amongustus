package net.labymod;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RawGeoModel {
   @SerializedName("format_version")
   private FormatVersion formatVersion;
   @SerializedName("minecraft:geometry")
   private MinecraftGeometry[] minecraftGeometry;

   public void setFormatVersion(FormatVersion formatVersion2) {
      this.formatVersion = formatVersion2;
   }

   public FormatVersion getFormatVersion() {
      return this.formatVersion;
   }

   private void read(JsonObject json) {
   }

   public MinecraftGeometry[] getMinecraftGeometry() {
      return this.minecraftGeometry;
   }

   public void setMinecraftGeometry(MinecraftGeometry[] minecraftGeometrys) {
      this.minecraftGeometry = minecraftGeometrys;
   }

   private void write(JsonObject json) {
   }
}
