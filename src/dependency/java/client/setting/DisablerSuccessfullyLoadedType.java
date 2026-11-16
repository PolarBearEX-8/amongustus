package client.setting;

public enum DisablerSuccessfullyLoadedType {
    SUCCESSFULLY_LOADED,
    DECLINED,
    FAILED_DOWNLOAD,
    ACCEPTED;

   public static final DisablerSuccessfullyLoadedType[] ENUM_VALUES = new DisablerSuccessfullyLoadedType[]{
      SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, ACCEPTED
   };

}
