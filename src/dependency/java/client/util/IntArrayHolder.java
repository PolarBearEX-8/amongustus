package client.util;

import client.setting.LagSendMessageType;

public class IntArrayHolder {
   public static final int[] $SwitchMap$LagAction = new int[LagSendMessageType.values().length];

   static {
      try {
         $SwitchMap$LagAction[LagSendMessageType.SEND_MESSAGE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         $SwitchMap$LagAction[LagSendMessageType.CLICK.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         $SwitchMap$LagAction[LagSendMessageType.CLOSE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
