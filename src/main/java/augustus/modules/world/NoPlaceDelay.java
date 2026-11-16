package augustus.modules.world;

import client.module.Category;
import client.module.Module;
import client.setting.MultiSelectSetting;
import client.setting.NamedModeOption;
import client.setting.NamedOption;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.util.EnumFacing;

public class NoPlaceDelay extends Module {
    public MultiSelectSetting settingOnlyOnTheseBlockFaces;
    public static HashMap entryMap = new HashMap();

    public NoPlaceDelay() {
        super("NoPlaceDelay", "OhnePlazierungsDelay", new Color(235, 64, 52), Category.WORLD);
        NamedOption[] namedOptionArray = new NamedOption[6];

        for (int i = 0; i < namedOptionArray.length; i++) {
            Object object = EnumFacing.getEntries()[i];
            namedOptionArray[i] = new NamedModeOption((EnumFacing)object, true);
            HashMap map = entryMap;
            Object object2 = EnumFacing.getEntries()[i];
            map.put(object2, namedOptionArray[i]);
        }

        ((NamedModeOption)entryMap.get(EnumFacing.UP)).setActive(false);
        this.settingOnlyOnTheseBlockFaces = new MultiSelectSetting(this, "Only on these block faces", namedOptionArray);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public static boolean checkEnumFacing(EnumFacing enumFacing) {
        return ((NamedModeOption)entryMap.get(enumFacing)).isSelected();
    }
}
