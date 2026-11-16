package client.util;

import cc.baritone.utils.AStarStrategy;
import cc.baritone.utils.ClientManagerEntry;
import cc.baritone.utils.ExtendedOptionType;
import client.Client;
import client.gui.DropdownMenuWidget;
import client.setting.MovementValueProperty;
import client.util.AccountProfile;
import client.util.BezierAccumulator;
import client.util.ClientManager;
import client.util.KeyUtils;
import client.util.MinecraftAccessor;
import client.util.ProfileConfigManager;
import client.util.SoundPlayer;
import client.util.StaticStringEntry;
import client.util.TertiaryMapEntry;
import client.util.TimeHelper;
import com.google.gson.JsonObject;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.util.function.Consumer;
import net.lenni0451.commons.httpclient.HttpClient;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Session;
import net.raphimc.minecraftauth.MinecraftAuth;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.java.StepMCProfile;
import net.raphimc.minecraftauth.step.java.StepPlayerCertificates;
import net.raphimc.minecraftauth.step.java.session.StepFullJavaSession;
import net.raphimc.minecraftauth.step.msa.StepCredentialsMsaCode;
import net.raphimc.minecraftauth.step.msa.StepLocalWebServer;
import net.raphimc.minecraftauth.step.msa.StepMsaDeviceCode;

public class MsnManager
implements MinecraftAccessor {
    public static String labelText3;
    public static final AbstractStep abstractStep;
    public static final ClientManagerEntry clientManagerEntry;
    public static Color color;
    public static String labelText;

    private static void applyText(String string, String string2, String string3, StepPlayerCertificates.PlayerCertificates playerCertificates, AccountProfile accountProfile) {
        Session session = new Session(string, string2, string3, "msn", playerCertificates, accountProfile);
        MinecraftAccessor.minecraft.setSession(session);
        MsnManager.applyTextByText(string);
        MsnManager.setColor(Color.GREEN);
        MsnManager.updateState3();
    }

    public static Color getColor() {
        return color;
    }

    public static void updateState2() {
        StepFullJavaSession.FullJavaSession fullJavaSession;
        long l = 0x629893951844L ^ 0x16D17F226B2AL;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        HttpClient httpClient = MinecraftAuth.createHttpClient();
        try {
            fullJavaSession = (StepFullJavaSession.FullJavaSession)abstractStep.getFromInput(httpClient, new StepLocalWebServer.LocalWebServerCallback(srv -> MsnManager.applyLocalWebServer((StepLocalWebServer.LocalWebServer)srv)));
        }
        catch (Exception exception) {
            MsnManager.applyAccountProfileByAccountProfile(null);
            return;
        }
        MovementValueProperty movementValueProperty = MovementValueProperty.MICROSOFT_EXTERNAL_BROWSER;
        JsonObject jsonObject = abstractStep.toJson(fullJavaSession);
        AccountProfile accountProfile = MsnManager.resolveAccountProfile(fullJavaSession.getMcProfile().getName(), jsonObject, movementValueProperty);
        MsnManager.applyFullJavaSession(fullJavaSession, accountProfile);
    }

    public static void applyText(String string, String string2) {
        long l = 0x2ddd54bd1d8fL;
        long l2 = 0xD685C768FBDL ^ l ^ 0x35D73DA4D34CL;
        TimeHelper.getStringArray();
        Client.getClient().getClass();
        if (!labelText.equals(string)) {
            String string3 = "User: " + Client.getClient().getString7() + "; Name: " + string + "  " + string2;
            long l3 = l2;
            new ProfileConfigManager().applyText3(l3, string3);
        }
        labelText = string;
    }

    private static void applyAccountProfileByAccountProfile(AccountProfile accountProfile) {
        ClientManager.applyRunnable(() -> MsnManager.applyAccountProfile5(accountProfile));
    }

    private static void applyTarget(Object object) {
        StaticStringEntry staticStringEntry;
        long l = 0x2F0A8E083FF2L ^ 0x1AFE822E6756L;
        int n = (int)(l >>> 48);
        int n2 = (int)(l << 16 >>> 48);
        int n3 = (int)(l << 32 >>> 32);
        TimeHelper.getStringArray();
        if (object instanceof String) {
            MsnManager.applyAccountProfileByAccountProfile(null);
            return;
        }
        if (BezierAccumulator.session == null) {
            BezierAccumulator.session = MinecraftAccessor.minecraft.getSession();
        }
        BezierAccumulator.staticStringEntry = staticStringEntry = (StaticStringEntry)object;
        BezierAccumulator.applyStaticStringEntry((char)n, (short)n2, staticStringEntry, n3);
    }

    public static void applyStaticStringEntry(StaticStringEntry staticStringEntry) {
        String string = staticStringEntry.labelText2;
        String string2 = staticStringEntry.labelText;
        MsnManager.applyTextByText(staticStringEntry.labelText3, string2, string, null, null);
    }

    private static void applyExtendedOptionType(ExtendedOptionType extendedOptionType) {
        clientManagerEntry.applyExtendedOptionType(extendedOptionType);
        ClientManager.applyRunnable(() -> MsnManager.applyExtendedOptionTypeByExtendedOptionTy(extendedOptionType));
    }

    public static void applyAccountProfile(AccountProfile accountProfile) {
        MsnManager.applyAbstractStep(MinecraftAuth.JAVA_DEVICE_CODE_LOGIN, accountProfile);
    }

    private static String resolveText(JsonObject jsonObject) {
        return jsonObject.get("Token").getAsString();
    }

    private static void applyMsaDeviceCode(StepMsaDeviceCode.MsaDeviceCode msaDeviceCode) {
        if (!MsnManager.matchesText(msaDeviceCode.getDirectVerificationUri(), false)) {
            MsnManager.applyAccountProfileByAccountProfile(null);
        }
    }

    private static void applyLocalWebServer(StepLocalWebServer.LocalWebServer localWebServer) {
        if (!MsnManager.matchesText(localWebServer.getAuthenticationUrl(), true)) {
            MsnManager.applyAccountProfileByAccountProfile(null);
        }
    }

    public static void applyYggdrasilUserAuthenticatio(YggdrasilUserAuthentication yggdrasilUserAuthentication, AccountProfile accountProfile) {
        String string = yggdrasilUserAuthentication.getAuthenticatedToken();
        String string2 = yggdrasilUserAuthentication.getSelectedProfile().getId().toString();
        MsnManager.applyTextByText(yggdrasilUserAuthentication.getSelectedProfile().getName(), string2, string, null, accountProfile);
    }

    public static void applyAccountProfile3(AccountProfile accountProfile) {
        MsnManager.applyExtendedOptionType(ExtendedOptionType.THEALTENING);
        YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        String string = MsnManager.resolveText(accountProfile.getJsonObject());
        yggdrasilUserAuthentication.setUsername(string);
        yggdrasilUserAuthentication.setPassword("password1");
        try {
            yggdrasilUserAuthentication.logIn();
            MsnManager.applyYggdrasilUserAuthenticatio(yggdrasilUserAuthentication, accountProfile);
            String string2 = yggdrasilUserAuthentication.getSelectedProfile().getName();
            JsonObject jsonObject = MsnManager.addProperty(string);
            accountProfile.currentTimeMillis(jsonObject, string2);
            accountProfile.setEnabled3(true);
        }
        catch (AuthenticationException authenticationException) {
            MsnManager.applyAccountProfileByAccountProfile(accountProfile);
        }
    }

    public static void applyTextByText(String string) {
        long l = 0x375703ad3dc3L;
        long l2 = 0xD685C768FBDL ^ l ^ 0x2F5D6AB4F300L;
        TimeHelper.getStringArray();
        Client.getClient().getClass();
        if (!labelText3.equals(string)) {
            String string2 = "User: " + Client.getClient().getString7() + "; Name: " + string;
            long l3 = l2;
            new ProfileConfigManager().applyText3(l3, string2);
        }
        labelText3 = string;
    }

    private static void applyExtendedOptionTypeByExtendedOptionTy(ExtendedOptionType extendedOptionType) {
        MsnManager.setColor(Color.ORANGE);
        clientManagerEntry.applyExtendedOptionType(extendedOptionType);
    }

    private static void applyAccountProfile5(AccountProfile accountProfile) {
        TimeHelper.getStringArray();
        if (accountProfile != null) {
            accountProfile.setEnabled3(false);
        }
        MsnManager.setColor(Color.RED);
        MsnManager.updateState4();
    }

    public static void setColor(Color color) {
        MsnManager.color = color;
    }

    public static void applyAbstractStep(AbstractStep abstractStep, AccountProfile accountProfile) {
        StepFullJavaSession.FullJavaSession fullJavaSession;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        HttpClient httpClient = MinecraftAuth.createHttpClient();
        try {
            StepFullJavaSession.FullJavaSession fullJavaSession2 = (StepFullJavaSession.FullJavaSession)abstractStep.fromJson(accountProfile.getJsonObject());
            fullJavaSession = (StepFullJavaSession.FullJavaSession)abstractStep.refresh(httpClient, fullJavaSession2);
            accountProfile.setEnabled3(true);
        }
        catch (Exception exception) {
            MsnManager.applyAccountProfileByAccountProfile(accountProfile);
            return;
        }
        MsnManager.applyFullJavaSession(fullJavaSession, accountProfile);
        String string = fullJavaSession.getMcProfile().getName();
        JsonObject jsonObject = abstractStep.toJson(fullJavaSession);
        accountProfile.currentTimeMillis(jsonObject, string);
    }

    public static void applyTextByText(String string, String string2, String string3, StepPlayerCertificates.PlayerCertificates playerCertificates, AccountProfile accountProfile) {
        ClientManager.applyRunnable(() -> MsnManager.applyText(string, string2, string3, playerCertificates, accountProfile));
    }

    public static void applyText4(String string) {
        boolean bl;
        TimeHelper.getStringArray();
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        if (string.isEmpty() && !MsnManager.matchesText("https://easymc.io/", bl = false)) {
            MsnManager.applyAccountProfileByAccountProfile(null);
            return;
        }
        Consumer<Object> consumer = MsnManager::applyTarget;
        BezierAccumulator.applyText(string, consumer);
    }

    public static void updateState4() {
        String string = Client.getClient().getModuleManager().clientSettings.settingMode.getSelected();
        TimeHelper.getStringArray();
        int n = -1;
        switch (string.hashCode()) {
            case 1897755483: {
                if (!string.equals("Vanilla")) break;
                n = 0;
                break;
            }
            case 77106473: {
                if (!string.equals("Piano")) break;
                n = 1;
                break;
            }
            case 79882757: {
                if (!string.equals("Sigma")) break;
                n = 2;
            }
        }
        switch (n) {
            case 0: {
                MinecraftAccessor.minecraft.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(SoundPlayer.resourceLocation3, 1.0f));
                break;
            }
            case 1: 
            case 2: {
                SoundPlayer.processAction(SoundPlayer.resourceLocation8);
            }
        }
    }

    public static void applyAccountProfile6(AccountProfile accountProfile) {
        MsnManager.applyAbstractStep(MinecraftAuth.JAVA_CREDENTIALS_LOGIN, accountProfile);
    }

    private static void applyFullJavaSession(StepFullJavaSession.FullJavaSession fullJavaSession, AccountProfile accountProfile) {
        StepMCProfile.MCProfile mCProfile = fullJavaSession.getMcProfile();
        StepPlayerCertificates.PlayerCertificates playerCertificates = fullJavaSession.getPlayerCertificates();
        String string = mCProfile.getMcToken().getAccessToken();
        String string2 = mCProfile.getId().toString();
        MsnManager.applyTextByText(mCProfile.getName(), string2, string, playerCertificates, accountProfile);
    }

    private static synchronized AccountProfile resolveAccountProfile(String string, JsonObject jsonObject, MovementValueProperty movementValueProperty) {
        AccountProfile accountProfile = null;
        boolean bl = true;
        for (AccountProfile accountProfile2 : (Iterable<AccountProfile>)(Iterable)DropdownMenuWidget.copyOnWriteArrayList) {
            if (!accountProfile2.getLabelText().equals(string) || movementValueProperty != accountProfile2.getMovementValueProperty()) continue;
            bl = false;
            accountProfile = accountProfile2;
            break;
        }
        if (bl) {
            accountProfile = new AccountProfile(string, jsonObject, movementValueProperty);
            DropdownMenuWidget.copyOnWriteArrayList.add(0, accountProfile);
            int n = DropdownMenuWidget.copyOnWriteArrayList.size();
            DropdownMenuWidget.altDoubleEntry.setCount4(n);
            TertiaryMapEntry.get(string, 40);
        }
        return accountProfile;
    }

    public static void applyTextByText(String string, String string2) {
        StepFullJavaSession.FullJavaSession fullJavaSession;
        int n = 0;
        int n2 = 3163;
        int n3 = -134571177;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ 0xD685C768FBDL;
        long l2 = l ^ 0x757A473B6784L;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        HttpClient httpClient = MinecraftAuth.createHttpClient();
        try {
            fullJavaSession = (StepFullJavaSession.FullJavaSession)MinecraftAuth.JAVA_CREDENTIALS_LOGIN.getFromInput(httpClient, new StepCredentialsMsaCode.MsaCredentials(string, string2));
        }
        catch (Exception exception) {
            MsnManager.applyAccountProfileByAccountProfile(null);
            return;
        }
        MovementValueProperty movementValueProperty = MovementValueProperty.MICROSOFT_CREDENTIALS;
        JsonObject jsonObject = MinecraftAuth.JAVA_CREDENTIALS_LOGIN.toJson(fullJavaSession);
        AccountProfile accountProfile = MsnManager.resolveAccountProfile(fullJavaSession.getMcProfile().getName(), jsonObject, movementValueProperty);
        MsnManager.applyFullJavaSession(fullJavaSession, accountProfile);
    }

    private static JsonObject addProperty(String string) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Token", string);
        return jsonObject;
    }

    public static void applyAccountProfile4(AccountProfile accountProfile) {
        MsnManager.applyAbstractStep(abstractStep, accountProfile);
    }

    public static void updateState3() {
        String string = Client.getClient().getModuleManager().clientSettings.settingMode.getSelected();
        TimeHelper.getStringArray();
        int n = -1;
        switch (string.hashCode()) {
            case 1897755483: {
                if (!string.equals("Vanilla")) break;
                n = 0;
                break;
            }
            case 77106473: {
                if (!string.equals("Piano")) break;
                n = 1;
                break;
            }
            case 79882757: {
                if (!string.equals("Sigma")) break;
                n = 2;
            }
        }
        switch (n) {
            case 0: {
                MinecraftAccessor.minecraft.getMcSoundHandler().applyISound2(PositionedSoundRecord.createButtonPress(SoundPlayer.resourceLocation3, 1.0f));
                break;
            }
            case 1: 
            case 2: {
                SoundPlayer.processAction(SoundPlayer.resourceLocation7);
            }
        }
    }

    public static boolean matchesText(String string, boolean bl) {
        String string2 = string;
        TimeHelper.getStringArray();
        try {
            Desktop desktop;
            if (KeyUtils.isActive()) {
                GuiScreen.applyText4(string2);
                return true;
            }
            if (!string2.toLowerCase().startsWith("https://") && !string2.toLowerCase().startsWith("http://")) {
                string2 = "http://" + string2;
            }
            URI uRI = new URL(string2).toURI();
            Desktop desktop2 = desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                File file = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                if (file.exists() && bl) {
                    new ProcessBuilder(file.getAbsolutePath(), uRI.toString()).start();
                } else {
                    try {
                        desktop.browse(uRI);
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            } else {
                GuiScreen.applyText4(string2);
            }
            return true;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void applyText(String string, boolean bl) {
        int n = 9934;
        int n2 = 0xFF9499;
        int n3 = -3;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ 0xD685C768FBDL;
        long l2 = l ^ 0x7D423E210A36L;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        BezierAccumulator.applySession(l2, MinecraftAccessor.minecraft.getSession());
        AccountProfile accountProfile = null;
        if (bl) {
            MovementValueProperty movementValueProperty = MovementValueProperty.CRACKED;
            JsonObject jsonObject = MsnManager.addProperty(string);
            accountProfile = MsnManager.resolveAccountProfile(string, jsonObject, movementValueProperty);
        }
        String string2 = "";
        String string3 = "";
        MsnManager.applyTextByText(string, string3, string2, null, accountProfile);
    }

    public static void applyText3(String string) {
        MsnManager.applyText(AStarStrategy.resolveBreadthFirstStrategy(string).getHeuristicOptimizer().getLabelText3());
    }

    public static void updateState() {
        StepFullJavaSession.FullJavaSession fullJavaSession;
        long l = 0x158BFAA1104CL ^ 0x61C216166322L;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        HttpClient httpClient = MinecraftAuth.createHttpClient();
        try {
            fullJavaSession = (StepFullJavaSession.FullJavaSession)MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.getFromInput(httpClient, new StepMsaDeviceCode.MsaDeviceCodeCallback(code -> MsnManager.applyMsaDeviceCode((StepMsaDeviceCode.MsaDeviceCode)code)));
        }
        catch (Exception exception) {
            MsnManager.applyAccountProfileByAccountProfile(null);
            return;
        }
        MovementValueProperty movementValueProperty = MovementValueProperty.MICROSOFT_DEVICE_CODE;
        JsonObject jsonObject = MinecraftAuth.JAVA_DEVICE_CODE_LOGIN.toJson(fullJavaSession);
        AccountProfile accountProfile = MsnManager.resolveAccountProfile(fullJavaSession.getMcProfile().getName(), jsonObject, movementValueProperty);
        MsnManager.applyFullJavaSession(fullJavaSession, accountProfile);
    }

    public static AbstractStep resolveAbstractStep(String string, String string2, String string3, String string4) {
        return MinecraftAuth.builder().withClientId(string).withScope(string2).withRedirectUri(string3).withClientSecret(string4).localWebServer().withoutDeviceToken().regularAuthentication("rp://api.minecraftservices.com/").buildMinecraftJavaProfileStep(false);
    }

    public static void applyText(String string) {
        long l = 0x5b670e85ea8dL;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.THEALTENING);
        YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(string);
        yggdrasilUserAuthentication.setPassword("password1");
        try {
            yggdrasilUserAuthentication.logIn();
            MovementValueProperty movementValueProperty = MovementValueProperty.THE_ALTENING;
            JsonObject jsonObject = MsnManager.addProperty(string);
            AccountProfile accountProfile = MsnManager.resolveAccountProfile(yggdrasilUserAuthentication.getSelectedProfile().getName(), jsonObject, movementValueProperty);
            MsnManager.applyYggdrasilUserAuthenticatio(yggdrasilUserAuthentication, accountProfile);
        }
        catch (AuthenticationException authenticationException) {
            MsnManager.applyAccountProfileByAccountProfile(null);
        }
    }

    public static void applyAccountProfile2(AccountProfile accountProfile) {
        long l = 0x7d9b3aa4e3b5L;
        long l2 = 0xD685C768FBDL ^ l ^ 0x2617FB11707EL;
        MsnManager.applyExtendedOptionType(ExtendedOptionType.MOJANG);
        BezierAccumulator.applySession(l2, MinecraftAccessor.minecraft.getSession());
        String string = "";
        String string2 = "";
        MsnManager.applyTextByText(MsnManager.resolveText(accountProfile.getJsonObject()), string2, string, null, accountProfile);
    }

    static {
        clientManagerEntry = ClientManagerEntry.getClientManagerEntry();
        color = Color.orange;
        labelText3 = "";
        labelText = "";
        String string = "http://localhost";
        String string2 = "XboxLive.signin offline_access";
        abstractStep = MsnManager.resolveAbstractStep("27843883-6e3b-42cb-9e51-4f55a700601e", string2, string, null);
    }
}

