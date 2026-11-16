package net.minecraft.entity.player;

public enum EntityPlayerAction {
    START_SNEAKING,
    STOP_SNEAKING,
    STOP_SLEEPING,
    START_SPRINTING,
    STOP_SPRINTING,
    RIDING_JUMP,
    OPEN_INVENTORY;

   public static final EntityPlayerAction[] ENUM_VALUES = new EntityPlayerAction[]{
      START_SNEAKING, STOP_SNEAKING, STOP_SLEEPING, START_SPRINTING, STOP_SPRINTING, RIDING_JUMP, OPEN_INVENTORY
   };

}
