package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;

public enum MovementNumberProperty {
    RIDEABLE(0, "MinecartRideable"),
    CHEST(1, "MinecartChest"),
    FURNACE(2, "MinecartFurnace"),
    TNT(3, "MinecartTNT"),
    SPAWNER(4, "MinecartSpawner"),
    HOPPER(5, "MinecartHopper"),
    COMMAND_BLOCK(6, "MinecartCommandBlock");

   public static final Map map = Maps.newHashMap();
   public final int count;
   public final String labelText;
   public static final MovementNumberProperty[] ENUM_VALUES = new MovementNumberProperty[]{RIDEABLE, CHEST, FURNACE, TNT, SPAWNER, HOPPER, COMMAND_BLOCK};

   public String getLabelText() {
      return this.labelText;
   }

   public int getCount() {
      return this.count;
   }

   public static MovementNumberProperty resolveMovementNumberProperty(int count2) {
      MovementNumberProperty movementNumberProperty = (MovementNumberProperty)map.get(count2);
      return movementNumberProperty == null ? RIDEABLE : movementNumberProperty;
   }

   private MovementNumberProperty(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }



   static {
      for (MovementNumberProperty entry : values()) {
               map.put(entry.getCount(), entry);
            }
   }
}
