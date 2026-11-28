package net.augustus.settings.property;


import net.augustus.settings.Setting;
@FunctionalInterface
public interface IndexedSettingFactory {
   Setting createSetting(int n);
}
