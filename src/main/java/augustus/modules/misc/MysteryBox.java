package augustus.modules.misc;

import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.SettingContainer;
import client.util.ConfigManager;
import client.util.JsonPropertyGetter;
import client.util.MysteryBoxService;
import client.util.OnlineConfigsManager;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiConnecting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;

public class MysteryBox extends Module implements TickListener {
    public String boxData;
    public TimeHelper timer;
    public ArrayList rewardTimestamps;
    public ArrayList itemList3;
    public long timestampMillis;
    public int keyCode2;
    public int tickDelay1;
    public String displayName2;
    public boolean expanded2;
    public int tickDelay;

    public MysteryBox() {
        super("MysteryBox", "GeheimnisKiste", Color.GREEN, Category.MISC);
        String text = new StringBuilder().append(OnlineConfigsManager.text).append(File.separator).append("data").toString();
        this.boxData = text;
        this.timer = new TimeHelper();
        ArrayList arrayList = new ArrayList();
        this.rewardTimestamps = arrayList;
        this.itemList3 = new ArrayList();
        this.keyCode2 = 0;
        this.tickDelay1 = 0;
        this.displayName2 = "";
        this.expanded2 = false;
        this.tickDelay = 10000;
        ConfigManager.applyString2(this.boxData);
        this.onUnload();
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.updateState4();
    }

    @Override
    public void onPostTick() {
        if (this.timer.hasReached((long)this.tickDelay)) {
            this.tickDelay = SettingContainer.settingProvider.getInt2(9000, 12000);
            this.timer.reset();
            this.processShort();
            boolean enabled = this.canExecute5();
            if (enabled != this.expanded2) {
                if (enabled) {
                    int index = this.keyCode2 + 1;
                    this.keyCode2 = index;
                } else {
                    int index2 = this.tickDelay1 + 1;
                    this.tickDelay1 = index2;
                }
            }

            if (enabled) {
                this.applyArrayList(this.rewardTimestamps, this.keyCode2, this.getRunnable());
            } else {
                this.applyArrayList(this.itemList3, this.tickDelay1, this.getRunnable2());
            }

            this.expanded2 = enabled;
        }
    }

    private void processShort() {
        long longEntry = this.getLong();
        if (this.timestampMillis != longEntry) {
            this.keyCode2 = this.tickDelay1 = 0;
        }

        this.timestampMillis = this.getLong();
    }

    private Runnable getRunnable() {
        return () -> this.updateState();
    }

    private void applyArrayList(ArrayList arrayList, int index, Runnable runnable) {
        if (index < arrayList.size()) {
            long time = System.currentTimeMillis();
            long time2 = (Long)arrayList.get(index);
            if (time - this.timestampMillis > time2) {
                runnable.run();
            }
        }
    }

    private void updateState() {
        if (this.displayName2 != null) {
            if (!this.displayName2.isEmpty()) {
                if (minecraft.thePlayer != null) {
                    return;
                }

                GuiConnecting secondaryGuiScreenAdapter = new GuiConnecting(
                    new GuiMultiplayer(new GuiMainMenu()), minecraft, new ServerData(this.displayName2, this.displayName2, false)
                );
                minecraft.displayGuiScreen(secondaryGuiScreenAdapter);
            }
        }
    }

    public long getLong() {
        return LocalDateTime.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .with(LocalTime.MIDNIGHT)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();
    }

    private Runnable getRunnable2() {
        return () -> this.updateState2();
    }

    private JsonArray getJsonArray(JsonObject jsonObject, String text) {
        JsonElement jsonElement = jsonObject.get(text);
        return !jsonElement.isJsonArray() ? null : (JsonArray)jsonElement;
    }

    private void updateState2() {
        if (minecraft.thePlayer != null) {
            minecraft.state = true;
            boolean gamePaused = minecraft.isGamePaused();
            minecraft.theWorld.updateState();
            minecraft.loadWorld((WorldClient)null);
            if (gamePaused) {
                GuiMainMenu guiMainMenu = new GuiMainMenu();
                minecraft.displayGuiScreen(guiMainMenu);
            } else {
                GuiMultiplayer auxiliaryGuiScreenAdapter = new GuiMultiplayer(new GuiMainMenu());
                minecraft.displayGuiScreen(auxiliaryGuiScreenAdapter);
            }
        }
    }

    private boolean canExecute5() {
        return minecraft.thePlayer != null;
    }

    private void updateState4() {
        RayTraceUtil.applyString("Start loading data...");
        this.rewardTimestamps.clear();
        this.itemList3.clear();
        String settingData2 = this.boxData;
        File file = new File(settingData2);
        ConfigManager.applyString2(file.getAbsolutePath());
        File[] fileArray = file.listFiles(File::isFile);
        if (fileArray != null && minecraft.session != null) {
            int length = Array.getLength(fileArray);

            for (int i = 0; i < length; i++) {
                Object object = fileArray[i];
                String name = ((File)object).getName();
                int index = Math.max(((File)object).getName().length() - 5, 0);
                String text = name.substring(0, index);
                String text2 = minecraft.session.getText1();
                if (text.equals(text2)) {
                    String settingData3 = this.boxData;
                    String name2 = ((File)object).getName();
                    JsonObject jsonObject = ConfigManager.getJsonObject(settingData3, name2, (Runnable)null);
                    JsonPropertyGetter baseListener = MysteryBoxService.mysteryBoxPacketListener.getBaseListener();
                    String displayName22 = this.displayName2;
                    this.displayName2 = (String)baseListener.getEntry("ServerIP", displayName22, jsonObject);
                    JsonArray jsonArray = this.getJsonArray(jsonObject, "Joins");
                    JsonArray jsonArray2 = this.getJsonArray(jsonObject, "Leaves");
                    if (jsonArray != null && jsonArray2 != null) {
                        Iterator iterator = jsonArray.iterator();

                        while (iterator.hasNext()) {
                            Object object2 = iterator.next();
                            JsonElement jsonElement = (JsonElement)object2;
                            ArrayList settings22 = this.rewardTimestamps;
                            Long asLong = jsonElement.getAsLong();
                            settings22.add(asLong);
                        }

                        Iterator iterator2 = jsonArray2.iterator();

                        while (iterator2.hasNext()) {
                            JsonElement jsonElement2 = (JsonElement)iterator2.next();
                            ArrayList itemList32 = this.itemList3;
                            Long asLong2 = jsonElement2.getAsLong();
                            itemList32.add(asLong2);
                        }

                        RayTraceUtil.applyString("Loaded data successfully");
                        break;
                    }
                }
            }
        }

        if (this.rewardTimestamps.isEmpty()) {
            RayTraceUtil.applyString("No data found");
        }

        Collections.sort(this.rewardTimestamps);
        Collections.sort(this.itemList3);
        long longEntry = this.getLong();
        long time2 = System.currentTimeMillis() - longEntry;
        int index2 = 0;

        while (true) {
            int size = this.rewardTimestamps.size();
            if (index2 >= size) {
                break;
            }

            long time3 = (Long)this.rewardTimestamps.get(index2);
            if ((time3 > time2 ? 1 : (time3 == time2 ? 0 : -1)) > 0) {
                this.keyCode2 = index2;
                break;
            }

            index2++;
        }

        int index3 = 0;

        while (true) {
            int size2 = this.itemList3.size();
            if (index3 >= size2) {
                break;
            }

            long time4 = (Long)this.itemList3.get(index3);
            if ((time4 > time2 ? 1 : (time4 == time2 ? 0 : -1)) > 0) {
                this.tickDelay1 = index3;
                break;
            }

            index3++;
        }

        boolean enabled = this.canExecute5();
        this.expanded2 = enabled & true;
    }
}
