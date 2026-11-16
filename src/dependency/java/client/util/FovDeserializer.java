package client.util;

import client.setting.BooleanSetting;

public class FovDeserializer extends ExtendedListenerAdapter {
   public FovDeserializer(String text, BooleanSetting setting) {
      super(text, setting::isEnabled, (item -> setting.setState3((Boolean)item)));
   }
}
