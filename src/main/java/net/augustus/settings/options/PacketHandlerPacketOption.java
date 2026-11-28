package net.augustus.settings.options;


import net.augustus.settings.SelectableOption;
public class PacketHandlerPacketOption implements SelectableOption {
   private final Class packetClass;
   private final String name;
   private boolean selected = true;

   public PacketHandlerPacketOption(String str, Class clazz) {
      this.name = str;
      this.packetClass = clazz;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      this.selected = flag;
   }

   public static boolean isOptionSelected(PacketHandlerPacketOption option) {
      return option.selected;
   }

   public static Class getPacketClass(PacketHandlerPacketOption option) {
      return option.packetClass;
   }
}
