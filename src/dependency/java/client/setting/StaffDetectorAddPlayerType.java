package client.setting;

public enum StaffDetectorAddPlayerType {
    ADD_PLAYER,
    UPDATE_GAME_MODE,
    UPDATE_LATENCY,
    UPDATE_DISPLAY_NAME,
    REMOVE_PLAYER;

   public static final StaffDetectorAddPlayerType[] ENUM_VALUES = new StaffDetectorAddPlayerType[]{
      ADD_PLAYER, UPDATE_GAME_MODE, UPDATE_LATENCY, UPDATE_DISPLAY_NAME, REMOVE_PLAYER
   };

}
