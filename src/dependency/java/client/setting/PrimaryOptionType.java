package client.setting;

public enum PrimaryOptionType {
   PENDING,
   COMPILING,
   UPLOADING,
   DONE;

   public static final PrimaryOptionType[] ENUM_VALUES = values();
}
