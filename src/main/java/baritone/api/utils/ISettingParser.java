package baritone.api.utils;

import java.lang.reflect.Type;

public interface ISettingParser {
   boolean accepts(Type type);

   Object parse(SettingParserContext settingParserContext, String str);

   String toString(SettingParserContext settingParserContext, Object obj);
}
