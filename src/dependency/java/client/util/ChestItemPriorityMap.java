package client.util;

import client.module.Module;
import client.setting.InternalNamedOptionAdapter;
import client.setting.MultiSelectSetting;
import client.setting.NamedOption;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InventoryUtil;
import client.util.TimeHelper;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ChestItemPriorityMap {
    public final HashMap hashMap;

    public boolean checkItem(Item item) {
        TimeHelper.getStringArray();
        InternalNamedOptionAdapter internalNamedOptionAdapter = (InternalNamedOptionAdapter)this.hashMap.get(item);
        if (internalNamedOptionAdapter == null) {
            return false;
        }
        return InternalNamedOptionAdapter.matchesInternalNamedOptionAdapter(internalNamedOptionAdapter) && InventoryUtil.matchesItem(item);
    }

    public ChestItemPriorityMap(Module module, String string, SettingGroup settingGroup, BooleanSupplier booleanSupplier, Function function) {
        TimeHelper.getStringArray();
        this.hashMap = new HashMap();
        Iterator iterator = Item.iterableExtensionAdapter.getSet().iterator();
        while (iterator.hasNext()) {
            String string2 = ((ResourceLocation)iterator.next()).getText1();
            Item item = Item.getItem(string2);
            this.hashMap.put(item, new InternalNamedOptionAdapter(this, string2, (Boolean)function.apply(item)));
        }
        InternalNamedOptionAdapter[] internalArray = (InternalNamedOptionAdapter[])this.hashMap.values().toArray(new InternalNamedOptionAdapter[0]);
        Arrays.sort(internalArray, Comparator.comparing(InternalNamedOptionAdapter::getText));
        NamedOption[] namedOptionArray = internalArray;
        if (settingGroup == null) {
            new MultiSelectSetting(module, string, namedOptionArray).getMultiSelectSetting(booleanSupplier);
        } else {
            new MultiSelectSetting(module, string, namedOptionArray, settingGroup).getMultiSelectSetting(booleanSupplier);
        }
    }
}

