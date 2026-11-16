package augustus.modules.render;

import client.Client;
import client.module.Category;
import client.module.Module;
import client.render.DynamicCapeTexture;
import client.setting.ModeSetting;
import client.util.BooleanSupplier;
import client.util.CapeofManager;
import client.util.ConfigManager;
import client.util.OnlineConfigsManager;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;

public class CustomCape extends Module {
    public ModeSetting settingType;
    public ModeSetting settingLabyModCapes;
    public ModeSetting settingMojangCapes;
    public String displayName2;
    public HashMap entryMap;
    public HashMap entryMap2;

    public CustomCape() {
        super("CustomCape", "KundenspezifischeKap", new Color(235, 146, 52), Category.RENDER);
        String[] textArray = new String[2];
        textArray[0] = "Mojang";
        textArray[1] = "LabyMod";
        this.settingType = new ModeSetting(this, "Type", "LabyMod", textArray);
        String[] textArray2 = new String[0];
        ModeSetting modeSetting2 = new ModeSetting(this, "LabyModCapes", "Augustus", textArray2);
        BooleanSupplier booleanSupplier = () -> this.isLabyModCapesVisible();
        ModeSetting setting = modeSetting2;
        BooleanSupplier booleanSupplier2 = booleanSupplier;
        this.settingLabyModCapes = setting.getModeSetting(booleanSupplier2);
        String[] textArray3 = new String[0];
        ModeSetting modeSetting4 = new ModeSetting(this, "MojangCapes", "Migrator", textArray3);
        BooleanSupplier booleanSupplier3 = () -> this.isMojangCapesVisible();
        ModeSetting setting2 = modeSetting4;
        BooleanSupplier booleanSupplier4 = booleanSupplier3;
        this.settingMojangCapes = setting2.getModeSetting(booleanSupplier4);
        StringBuilder stringBuilder = new StringBuilder();
        String text = OnlineConfigsManager.text;
        String text2 = stringBuilder.append(text).append("/customcapes").toString();
        this.displayName2 = text2;
        this.entryMap = new HashMap();
        this.entryMap2 = new HashMap();
        String displayName22 = this.displayName2;
        String[] textArray4 = new String[0];
        Path path = Paths.get(displayName22, textArray4);
        LinkOption[] linkOptionArray = new LinkOption[0];
        if (!Files.exists(path, linkOptionArray)) {
            String displayName23 = this.displayName2;
            String[] textArray5 = new String[0];
            Path path2 = Paths.get(displayName23, textArray5);
            FileAttribute[] fileAttributeArray = new FileAttribute[0];

            try {
                Files.createDirectories(path2, fileAttributeArray);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }

        StringBuilder stringBuilder2 = new StringBuilder();
        String text3 = Client.getClient().getString4().toLowerCase();
        String text4 = stringBuilder2.append(text3).append("/customcapes").toString();
        String text5 = text4;
        ConfigManager.applyString("assets/augustus/cape/Sky.png", text5, false);
        String text6 = text4;
        ConfigManager.applyString("assets/augustus/cape/Augustus.png", text6, false);
        String text7 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusAmethyst.png", text7, false);
        String text8 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusCandy.png", text8, false);
        String text9 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusClassic.png", text9, false);
        String text10 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusMagic.png", text10, false);
        String text11 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusMagma.png", text11, false);
        String text12 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusMango.png", text12, false);
        String text13 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusRose.png", text13, false);
        String text14 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusTitanium.png", text14, false);
        String text15 = text4;
        ConfigManager.applyString("assets/augustus/cape/Ejected.png", text15, false);
        String text16 = text4;
        ConfigManager.applyString("assets/augustus/cape/BlurSky.png", text16, false);
        String text17 = text4;
        ConfigManager.applyString("assets/augustus/cape/BlurSky2.png", text17, false);
        String text18 = text4;
        ConfigManager.applyString("assets/augustus/cape/HorizonSunset by Xolitey.png", text18, false);
        String text19 = text4;
        ConfigManager.applyString("assets/augustus/cape/NightCity.png", text19, false);
        String text20 = text4;
        ConfigManager.applyString("assets/augustus/cape/Pepe.png", text20, false);
        String text21 = text4;
        ConfigManager.applyString("assets/augustus/cape/Pineapples.png", text21, false);
        String text22 = text4;
        ConfigManager.applyString("assets/augustus/cape/Rose.png", text22, false);
        String text23 = text4;
        ConfigManager.applyString("assets/augustus/cape/Esound.png", text23, false);
        String text24 = text4;
        ConfigManager.applyString("assets/augustus/cape/Dog.png", text24, false);
        String text25 = text4;
        ConfigManager.applyString("assets/augustus/cape/AugustusLightBlue.png", text25, false);
        String text26 = text4;
        ConfigManager.applyString("assets/augustus/cape/GigachadSound.png", text26, false);
        String text27 = text4;
        ConfigManager.applyString("assets/augustus/cape/NoobiiHome.png", text27, false);
        String text28 = text4;
        ConfigManager.applyString("assets/augustus/cape/GommeHD.png", text28, false);
        String text29 = text4;
        ConfigManager.applyString("assets/augustus/cape/Seal.png", text29, false);
        String text30 = text4;
        ConfigManager.applyString("assets/augustus/cape/Questionmark.png", text30, false);
        String text31 = text4;
        ConfigManager.applyString("assets/augustus/cape/Creeper.png", text31, false);
        String text32 = text4;
        ConfigManager.applyString("assets/augustus/cape/Karhu.png", text32, false);
        String text33 = text4;
        ConfigManager.applyString("assets/augustus/cape/Moon.png", text33, false);
        String text34 = text4;
        ConfigManager.applyString("assets/augustus/cape/Astolfo.png", text34, false);
        String text35 = text4;
        ConfigManager.applyString("assets/augustus/cape/Astolfotrap.png", text35, false);
        String text36 = text4;
        ConfigManager.applyString("assets/augustus/cape/AstolfoBeta.png", text36, false);
        String text37 = text4;
        ConfigManager.applyString("assets/augustus/cape/AstolfoBeta2.png", text37, false);
        String text38 = text4;
        ConfigManager.applyString("assets/augustus/cape/E.png", text38, false);
        String text39 = text4;
        ConfigManager.applyString("assets/augustus/cape/Grim.png", text39, false);
        String text40 = text4;
        ConfigManager.applyString("assets/augustus/cape/Otter.png", text40, false);
        this.updateState3();
        this.updateState();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (minecraft.thePlayer != null) {
            if (minecraft.thePlayer.getNetworkPlayerInfo() != null) {
                minecraft.thePlayer.getNetworkPlayerInfo().state6 = false;
                minecraft.thePlayer.getNetworkPlayerInfo().updateState();
            }
        }
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            if (minecraft.thePlayer.getNetworkPlayerInfo() != null) {
                minecraft.thePlayer.getNetworkPlayerInfo().state6 = false;
                minecraft.thePlayer.getNetworkPlayerInfo().updateState();
                this.updateState3();
            }
        }
    }

    private List getList(String text) {
        ArrayList arrayList = new ArrayList();

        try (
        InputStream inputStream = this.getInputStream(text);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return arrayList;
    }

    public void updateState() {
        ArrayList arrayList = new ArrayList();
        List list = Arrays.asList(
        "Birthday.png",
        "Cobalt.png",
        "dB.png",
        "Migrator.png",
        "MillionthCustomer.png",
        "MineCon2011.png",
        "MineCon2012.png",
        "MineCon2013.png",
        "MineCon2015.png",
        "MineCon2016.png",
        "Mojang.png",
        "MojangClassic.png",
        "MojangStudios.png",
        "MojiraModerator.png",
        "Prismarine.png",
        "RealmsMapmaker.png",
        "Scrolls.png",
        "Snowman.png",
        "Spade.png",
        "Translator.png",
        "Translator(Chinese).png",
        "Translator(Japanese).png",
        "Turtle.png",
        "Valentine.png",
        "Vanilla.png"
        );
        this.entryMap2.clear();

        for (String text : (Iterable<String>) list) {
            String[] textArray = text.split("\\.");
            String text2 = text.replace("." + textArray[textArray.length - 1], "");
            this.entryMap2.put(text2, new ResourceLocation("augustus", "cape/mojang/" + text));
            arrayList.add(text2);
        }

        boolean enabled = false;
        String[] textArray2 = new String[arrayList.size()];

        for (int i = 0; i < textArray2.length; i++) {
            String text3 = (String)arrayList.get(i);
            textArray2[i] = text3;
            if (this.settingMojangCapes.getSelected().equals(text3)) {
                enabled = true;
            }
        }

        if (!enabled) {
            String text4 = textArray2[0];
            this.settingMojangCapes.setSelected(text4);
        }

        this.settingMojangCapes.applyStringArray2(textArray2);
    }

    public void updateState2() {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(this.settingLabyModCapes.getOptions()));
        ArrayList arrayList2 = new ArrayList();
        File[] fileArray = new File(this.displayName2).listFiles();
        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.getName().contains(".png")) {
                    String name = file.getName();
                    String[] textArray = name.split("\\.");
                    String text = name.replace("." + textArray[textArray.length - 1], "");
                    if (!text.isEmpty()) {
                        if (!arrayList.contains(text)) {
                            arrayList.add(text);

                            try {
                                HashMap entryMap3 = this.entryMap;
                                BufferedImage bufferedImage = CapeofManager.getBufferedImage(ImageIO.read(file.getAbsoluteFile()));
                                entryMap3.put(text, DynamicCapeTexture.getIndex(bufferedImage));
                            } catch (IOException iOException) {
                            }
                        }

                        arrayList2.add(text);
                    }
                }
            }

            ((Iterable<?>)(this.entryMap
            .keySet()
            .stream()
            .filter(object -> checkList(arrayList2, (String)object))
            .collect(Collectors.toList())))
            .forEach(value -> this.applyArrayList(arrayList, (String)value));
            boolean enabled = false;
            String[] textArray2 = new String[arrayList.size()];

            for (int i = 0; i < textArray2.length; i++) {
                String text2 = (String)arrayList.get(i);
                textArray2[i] = text2;
                if (this.settingLabyModCapes.getSelected().equals(text2)) {
                    enabled = true;
                }
            }

            if (!enabled && textArray2.length > 0) {
                String text3 = textArray2[0];
                this.settingLabyModCapes.setSelected(text3);
            }

            this.settingLabyModCapes.applyStringArray2(textArray2);
        }
    }

    private InputStream getInputStream(String text) {
        InputStream resourceAsStream = CustomCape.class.getClassLoader().getResourceAsStream(text);
        return resourceAsStream == null ? this.getClass().getResourceAsStream(text) : resourceAsStream;
    }

    private boolean isLabyModCapesVisible() {
        return this.settingType.getSelected().equals("LabyMod");
    }

    public void updateState3() {
        ArrayList arrayList = new ArrayList();
        this.entryMap.clear();
        File[] fileArray = new File(this.displayName2).listFiles();
        if (fileArray != null) {
            int index3 = 0;

            while (index3 < fileArray.length) {
                File file = fileArray[index3];
                if (!file.getName().contains(".png")) {
                    index3++;
                } else {
                    String name = file.getName();
                    String[] textArray = name.split("\\.");
                    String text = "." + textArray[textArray.length - 1];
                    String text2 = name.replace(text, "");
                    if (!text2.equals("") && !arrayList.contains(text2)) {
                        arrayList.add(text2);

                        try {
                            HashMap entryMap3 = this.entryMap;
                            BufferedImage bufferedImage = CapeofManager.getBufferedImage(ImageIO.read(file.getAbsoluteFile()));
                            int index = DynamicCapeTexture.getIndex(bufferedImage);
                            entryMap3.put(text2, index);
                        } catch (IOException iOException) {
                        }
                    }

                    index3++;
                }
            }
        }

        boolean enabled = false;
        String[] textArray2 = new String[arrayList.size()];

        for (int i = 0; i < textArray2.length; i++) {
            String text3 = (String)arrayList.get(i);
            textArray2[i] = text3;
            if (this.settingLabyModCapes.getSelected().equals(text3)) {
                enabled = true;
            }
        }

        if (!enabled) {
            String text4 = textArray2[0];
            this.settingLabyModCapes.setSelected(text4);
        }

        this.settingLabyModCapes.applyStringArray2(textArray2);
    }

    private boolean isMojangCapesVisible() {
        return this.settingType.getSelected().equals("Mojang");
    }

    private void applyArrayList(ArrayList arrayList, String text) {
        this.entryMap.remove(text);
        arrayList.remove(text);
    }

    private static boolean checkList(List list, String text) {
        return !list.contains(text);
    }
}
