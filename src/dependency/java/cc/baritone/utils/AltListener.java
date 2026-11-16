package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;

public interface AltListener {
   String getTextAsText();

   String getText();

   ClientSettingsBlackType getClientSettingsBlackType();

   ClientSettingsBlackType getClientSettingsBlackType2();

   boolean isEnabled();
}
