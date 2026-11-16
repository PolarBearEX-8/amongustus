package client.setting;

public enum HURTTIMEEnum implements NamedOption {
    HURT_TIME_0("0", 0),
    HURT_TIME_1("1", 1),
    HURT_TIME_2("2", 2),
    HURT_TIME_3("3", 3),
    HURT_TIME_4("4", 4),
    HURT_TIME_5("5", 5),
    HURT_TIME_6("6", 6),
    HURT_TIME_7("7", 7),
    HURT_TIME_8("8", 8),
    HURT_TIME_9("9", 9),
    HURT_TIME_10("10", 10);

   public final String labelText;
   public final int count;
   public boolean enabled = true;
   public static final HURTTIMEEnum[] ENUM_VALUES = new HURTTIMEEnum[]{
      HURT_TIME_0, HURT_TIME_1, HURT_TIME_2, HURT_TIME_3, HURT_TIME_4, HURT_TIME_5, HURT_TIME_6, HURT_TIME_7, HURT_TIME_8, HURT_TIME_9, HURT_TIME_10
   };

   private HURTTIMEEnum(String text, int count2) {
      this.labelText = text;
      this.count = count2;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.enabled;
   }

   @Override
   public void setActive(boolean active) {
      this.enabled = active;
   }

   public static int computeCount(HURTTIMEEnum hURTTIMEEnum) {
      return hURTTIMEEnum.count;
   }

   public static boolean matchesHURTTIMEEnum(HURTTIMEEnum hURTTIMEEnum) {
      return hURTTIMEEnum.enabled;
   }


}
