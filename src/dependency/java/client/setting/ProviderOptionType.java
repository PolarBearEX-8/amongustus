package client.setting;

public enum ProviderOptionType {
    EaseInQuad,
    EaseInBounce,
    EaseOutCubic,
    EaseInElastic,
    EaseInOutQuad,
    EaseInSine,
    EaseInOutBack,
    EaseOutQuad,
    EaseOutQuart,
    EaseOutSine,
    EaseInOutCirc,
    EaseInQuart,
    EaseOutBounce,
    CUSTOM,
    EaseInOutQuart,
    EaseInExpo,
    EaseOutBack,
    EaseInOutCubic,
    EaseInOutExpo,
    Linear,
    EaseInOutElastic,
    EaseInOutBounce,
    EaseOutElastic,
    EaseInQuint,
    EaseInCubic,
    EaseInBack,
    EaseOutCirc,
    EaseInCirc,
    EaseOutQuint,
    EaseInOutSine,
    NONE,
    EaseInOutQuint,
    EaseOutExpo,
    Step;

   public static ProviderOptionType resolveProviderOptionType(String text) {
      for (ProviderOptionType providerOptionType : values()) {
         if (providerOptionType.name().compareToIgnoreCase(text) == 0) {
            return providerOptionType;
         }
      }

      return null;
   }


}
