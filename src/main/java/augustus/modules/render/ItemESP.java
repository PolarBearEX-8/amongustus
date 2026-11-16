package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import augustus.modes.render.ItemEspOption;
import client.event.RenderListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.Setting;
import client.util.BooleanSupplier;
import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Items;

public class ItemESP extends Module implements RenderListener, TickListener {
    public ModeSetting settingMode;
    public ColorSetting settingBoxColor;
    public BooleanSetting settingRenderCustomItemnames;
    public BooleanSetting settingApplyNametagsRendering;
    public static BooleanSetting booleanSetting;
    public static HashMap entryMap = new HashMap();
    public static String[] stringArray;

    public ItemESP() {
        super("ItemESP", "ArtikelDurchWandSehen", Color.RED, Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "Box";
        textArray[1] = "Vanilla";
        this.settingMode = new ModeSetting(this, "Mode", "Box", textArray);
        Color color = new Color(71, 151, 255, 100);
        this.settingBoxColor = new ColorSetting(this, "BoxColor", color);
        this.settingRenderCustomItemnames = new BooleanSetting(this, "Render custom itemnames", false);
        this.settingApplyNametagsRendering = (BooleanSetting)new BooleanSetting(this, "Apply nametags rendering", false).getSetting(() -> this.settingRenderCustomItemnames.isEnabled());
        booleanSetting = new BooleanSetting(this, "Only show whitelisted items", false);
        HashSet hashSet = new HashSet();
        Iterator iterator = Item.iterableExtensionAdapter.iterator();

        while (iterator.hasNext()) {
            Item item = (Item)iterator.next();
            String text = item.getString().substring(5);
            if (hashSet.contains(text)) {
                StringBuilder sb = new StringBuilder().append(text).append("-");
                int index = Item.getIndex(item);
                text = sb.append(index).toString();
            } else {
                hashSet.add(text);
            }

            HashMap map = entryMap;
            boolean enabled = item instanceof ItemTool
                || item instanceof ItemGoldenBoots
                || item instanceof ItemSword
                || item == Items.goldIngot
                || item == Items.ironIngot
                || item == Items.diamond
                || item == Items.brick
                || item == Items.goldenApple;
            ItemEspOption itemEspOption = (ItemEspOption) RuntimeBridge.createItemEspOption(text, enabled);
            map.put(item, itemEspOption);
        }

        Collection collection = entryMap.values();
        ItemEspOption[] itemEspOptionArray = ((ItemEspOption[])(collection.toArray(new ItemEspOption[0])));
        Comparator comparator = Comparator.comparing(object -> ((ItemEspOption)object).getText());
        Arrays.sort(itemEspOptionArray, comparator);
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting(this, "Whitelisted items", itemEspOptionArray);
        BooleanSetting setting2 = booleanSetting;
        multiSelectSetting.getMultiSelectSetting(() -> ((BooleanSetting)setting2).isEnabled());
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

    @Override
    public void onPreTick() {
        String selected = this.settingMode.getSelected();
        this.applyString4(selected);
    }

    public static void setStringArray(String[] textArray) {
        stringArray = textArray;
    }

    public static boolean checkItem(Item item) {
        if (!booleanSetting.isEnabled()) {
            return true;
        } else {
            ItemEspOption itemEspOption;
            return (itemEspOption = (ItemEspOption)entryMap.get(item)) == null ? false : itemEspOption.state;
        }
    }

    public static String[] getStringArray() {
        return stringArray;
    }
}
