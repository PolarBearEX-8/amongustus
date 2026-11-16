package client.setting;

public enum DynamicOptionType {
    SET_SIZE,
    LERP_SIZE,
    SET_CENTER,
    INITIALIZE,
    SET_WARNING_TIME,
    SET_WARNING_BLOCKS;

   public static final DynamicOptionType[] ENUM_VALUES = new DynamicOptionType[]{
      SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS
   };

}
