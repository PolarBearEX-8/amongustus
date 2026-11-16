package client.util;

import client.ClientGlobals;
import client.event.BridgeListener;
import client.event.OFFListener;
import client.event.PreUpdateFilter;
import client.event.PreUpdateSubscriber;
import client.module.Module;
import client.render.AllocateTextureMapManager;
import client.render.AuxiliaryShadersManager;
import client.render.ShadersEntityPropertiesManager;
import client.render.ShadersItemPropertiesManager;
import client.render.ShadersManager;
import client.render.WorldRenderer;
import client.setting.BlockSolidType;
import client.setting.GameSettings;
import client.setting.MovementKeybindProperty;
import client.setting.NativeOptionType;
import client.setting.OptionManager;
import client.setting.RenderStateProperty;
import client.setting.SharedOptionType;
import client.setting.StaticOptionType;
import client.setting.VirtualOptionType;
import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlAlphaState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.ShaderUniformFloat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;

public class PreEndHandManager {
   public static Minecraft mc;
   public static EntityRenderer entityRenderer;
   public static boolean enabled13 = false;
   public static boolean enabled45 = false;
   public static ContextCapabilities contextCapabilities;
   public static String labelText4;
   public static String labelText10;
   public static String labelText6;
   public static boolean enabled8 = false;
   public static int count25 = 0;
   public static int count = 0;
   public static int count9 = 0;
   public static int count12 = 0;
   public static int count15 = 0;
   public static boolean enabled31 = false;
   public static boolean enabled18 = false;
   public static boolean enabled27 = false;
   public static boolean enabled24 = false;
   public static boolean enabled12 = false;
   public static boolean enabled28 = false;
   public static boolean enabled36;
   public static boolean enabled29;
   public static boolean enabled43;
   public static boolean enabled32;
   public static boolean enabled5;
   public static boolean enabled2;
   public static boolean enabled38 = false;
   public static boolean enabled35 = false;
   public static boolean enabled3 = false;
   public static float[] factorArray15 = new float[4];
   public static float[] factorArray12 = new float[4];
   public static float[] factorArray7 = new float[4];
   public static float[] factorArray2 = new float[4];
   public static float[] factorArray10 = new float[4];
   public static float[] factorArray11 = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   public static float[] factorArray3 = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   public static float[] factorArray5 = new float[]{0.0F, -100.0F, 0.0F, 0.0F};
   public static float[] factorArray14 = new float[16];
   public static float factor34;
   public static float factor39;
   public static float factor38;
   public static float factor37;
   public static float factor4;
   public static float factor9;
   public static long timestamp3 = 0L;
   public static long timestamp6 = 0L;
   public static long timestamp5 = 0L;
   public static float factor14 = 0.0F;
   public static float factor22 = 0.0F;
   public static float factor26 = 0.0F;
   public static int count48 = 0;
   public static long timestamp2 = 0L;
   public static long timestamp4 = 0L;
   public static long timestamp = 0L;
   public static int count43 = 0;
   public static float factor11 = 0.0F;
   public static float factor21 = 0.0F;
   public static int count24 = 0;
   public static float factor36 = 0.0F;
   public static float factor3 = 0.0F;
   public static float factor13 = 600.0F;
   public static float factor25 = 200.0F;
   public static float factor33 = 10.0F;
   public static boolean enabled16 = false;
   public static int count58 = 0;
   public static int count50 = 0;
   public static float factor = 0.0F;
   public static float factor6 = 0.0F;
   public static float factor7 = 0.0F;
   public static float factor28 = 0.0F;
   public static float factor24 = 0.0F;
   public static float factor23 = 1.0F;
   public static boolean enabled44 = false;
   public static int count35 = 1;
   public static float factor27 = 0.0F;
   public static float factor12 = 0.0F;
   public static boolean enabled6 = false;
   public static boolean enabled40 = true;
   public static int count39 = 10;
   public static int count34 = 11;
   public static int count11 = 12;
   public static boolean enabled21 = false;
   public static boolean enabled7 = false;
   public static boolean enabled39 = false;
   public static boolean enabled20 = false;
   public static boolean enabled25 = false;
   public static boolean enabled41 = false;
   public static boolean enabled22 = false;
   public static int count26 = 3;
   public static boolean enabled23 = false;
   public static int count4 = 0;
   public static int count38 = 0;
   public static AltListEntry altListEntry = new AltListEntry();
   public static AuxiliaryStringHandler auxiliaryStringHandler = altListEntry.add6("entityColor");
   public static StringHandler stringHandler13 = altListEntry.add4("entityId");
   public static StringHandler stringHandler42 = altListEntry.add4("blockEntityId");
   public static StringHandler stringHandler6 = altListEntry.add4("texture");
   public static StringHandler stringHandler36 = altListEntry.add4("lightmap");
   public static StringHandler stringHandler29 = altListEntry.add4("normals");
   public static StringHandler stringHandler17 = altListEntry.add4("specular");
   public static StringHandler stringHandler8 = altListEntry.add4("shadow");
   public static StringHandler stringHandler37 = altListEntry.add4("watershadow");
   public static StringHandler stringHandler4 = altListEntry.add4("shadowtex0");
   public static StringHandler stringHandler12 = altListEntry.add4("shadowtex1");
   public static StringHandler stringHandler45 = altListEntry.add4("depthtex0");
   public static StringHandler stringHandler38 = altListEntry.add4("depthtex1");
   public static StringHandler stringHandler3 = altListEntry.add4("shadowcolor");
   public static StringHandler stringHandler9 = altListEntry.add4("shadowcolor0");
   public static StringHandler stringHandler14 = altListEntry.add4("shadowcolor1");
   public static StringHandler stringHandler25 = altListEntry.add4("noisetex");
   public static StringHandler stringHandler33 = altListEntry.add4("gcolor");
   public static StringHandler stringHandler22 = altListEntry.add4("gdepth");
   public static StringHandler stringHandler19 = altListEntry.add4("gnormal");
   public static StringHandler stringHandler41 = altListEntry.add4("composite");
   public static StringHandler stringHandler23 = altListEntry.add4("gaux1");
   public static StringHandler stringHandler47 = altListEntry.add4("gaux2");
   public static StringHandler stringHandler35 = altListEntry.add4("gaux3");
   public static StringHandler stringHandler10 = altListEntry.add4("gaux4");
   public static StringHandler stringHandler27 = altListEntry.add4("colortex0");
   public static StringHandler stringHandler15 = altListEntry.add4("colortex1");
   public static StringHandler stringHandler18 = altListEntry.add4("colortex2");
   public static StringHandler stringHandler31 = altListEntry.add4("colortex3");
   public static StringHandler stringHandler43 = altListEntry.add4("colortex4");
   public static StringHandler stringHandler21 = altListEntry.add4("colortex5");
   public static StringHandler stringHandler32 = altListEntry.add4("colortex6");
   public static StringHandler stringHandler46 = altListEntry.add4("colortex7");
   public static StringHandler stringHandler28 = altListEntry.add4("gdepthtex");
   public static StringHandler stringHandler11 = altListEntry.add4("depthtex2");
   public static StringHandler stringHandler44 = altListEntry.add4("tex");
   public static StringHandler stringHandler26 = altListEntry.add4("heldItemId");
   public static StringHandler stringHandler40 = altListEntry.add4("heldBlockLightValue");
   public static StringHandler stringHandler = altListEntry.add4("heldItemId2");
   public static StringHandler stringHandler24 = altListEntry.add4("heldBlockLightValue2");
   public static StringHandler stringHandler5 = altListEntry.add4("fogMode");
   public static FloatArrayHolder floatArrayHolder4 = altListEntry.add5("fogDensity");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder = altListEntry.add2("fogColor");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder2 = altListEntry.add2("skyColor");
   public static StringHandler stringHandler16 = altListEntry.add4("worldTime");
   public static StringHandler stringHandler30 = altListEntry.add4("worldDay");
   public static StringHandler stringHandler2 = altListEntry.add4("moonPhase");
   public static StringHandler stringHandler20 = altListEntry.add4("frameCounter");
   public static FloatArrayHolder floatArrayHolder8 = altListEntry.add5("frameTime");
   public static FloatArrayHolder floatArrayHolder2 = altListEntry.add5("frameTimeCounter");
   public static FloatArrayHolder floatArrayHolder9 = altListEntry.add5("sunAngle");
   public static FloatArrayHolder floatArrayHolder7 = altListEntry.add5("shadowAngle");
   public static FloatArrayHolder floatArrayHolder14 = altListEntry.add5("rainStrength");
   public static FloatArrayHolder floatArrayHolder5 = altListEntry.add5("aspectRatio");
   public static FloatArrayHolder floatArrayHolder6 = altListEntry.add5("viewWidth");
   public static FloatArrayHolder floatArrayHolder16 = altListEntry.add5("viewHeight");
   public static FloatArrayHolder floatArrayHolder17 = altListEntry.add5("near");
   public static FloatArrayHolder floatArrayHolder3 = altListEntry.add5("far");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder7 = altListEntry.add2("sunPosition");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder3 = altListEntry.add2("moonPosition");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder6 = altListEntry.add2("shadowLightPosition");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder4 = altListEntry.add2("upPosition");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder5 = altListEntry.add2("previousCameraPosition");
   public static AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder8 = altListEntry.add2("cameraPosition");
   public static ShaderUniformFloat shaderUniformFloat6 = altListEntry.add3("gbufferModelView");
   public static ShaderUniformFloat shaderUniformFloat8 = altListEntry.add3("gbufferModelViewInverse");
   public static ShaderUniformFloat shaderUniformFloat5 = altListEntry.add3("gbufferPreviousProjection");
   public static ShaderUniformFloat shaderUniformFloat = altListEntry.add3("gbufferProjection");
   public static ShaderUniformFloat shaderUniformFloat9 = altListEntry.add3("gbufferProjectionInverse");
   public static ShaderUniformFloat shaderUniformFloat2 = altListEntry.add3("gbufferPreviousModelView");
   public static ShaderUniformFloat shaderUniformFloat4 = altListEntry.add3("shadowProjection");
   public static ShaderUniformFloat shaderUniformFloat10 = altListEntry.add3("shadowProjectionInverse");
   public static ShaderUniformFloat shaderUniformFloat3 = altListEntry.add3("shadowModelView");
   public static ShaderUniformFloat shaderUniformFloat7 = altListEntry.add3("shadowModelViewInverse");
   public static FloatArrayHolder floatArrayHolder13 = altListEntry.add5("wetness");
   public static FloatArrayHolder floatArrayHolder12 = altListEntry.add5("eyeAltitude");
   public static ExtendedStringHandler extendedStringHandler4 = altListEntry.addByText("eyeBrightness");
   public static ExtendedStringHandler extendedStringHandler3 = altListEntry.addByText("eyeBrightnessSmooth");
   public static ExtendedStringHandler extendedStringHandler2 = altListEntry.addByText("terrainTextureSize");
   public static StringHandler stringHandler39 = altListEntry.add4("terrainIconSize");
   public static StringHandler stringHandler48 = altListEntry.add4("isEyeInWater");
   public static FloatArrayHolder floatArrayHolder11 = altListEntry.add5("nightVision");
   public static FloatArrayHolder floatArrayHolder = altListEntry.add5("blindness");
   public static FloatArrayHolder floatArrayHolder15 = altListEntry.add5("screenBrightness");
   public static StringHandler stringHandler7 = altListEntry.add4("hideGUI");
   public static FloatArrayHolder floatArrayHolder10 = altListEntry.add5("centerDepthSmooth");
   public static ExtendedStringHandler extendedStringHandler = altListEntry.addByText("atlasSize");
   public static InternalStringHandler internalStringHandler = altListEntry.add("blendFunc");
   public static StringHandler stringHandler34 = altListEntry.add4("instanceId");
   public static double value5;
   public static double value6;
   public static double value;
   public static double value2;
   public static double value3;
   public static double value4;
   public static int count3;
   public static int count46;
   public static int count49 = 0;
   public static boolean enabled42 = false;
   public static int count10 = 1024;
   public static int count6 = 1024;
   public static int count27 = 1024;
   public static int count54 = 1024;
   public static float factor30 = 90.0F;
   public static float factor2 = 160.0F;
   public static boolean enabled4 = true;
   public static float factor40 = -1.0F;
   public static int count57 = 0;
   public static int count18;
   public static boolean enabled14 = false;
   public static boolean enabled33 = false;
   public static final int count20 = 8;
   public static final int count21 = 8;
   public static final int count28 = 3;
   public static final int count47 = 8;
   public static final int count52 = 2;
   public static int count55 = 0;
   public static int count56 = 0;
   public static int count8 = 0;
   public static int count53 = 0;
   public static int count36 = 0;
   public static int count42 = 0;
   public static int count33 = 0;
   public static int count51 = 0;
   public static int[] countArray3 = new int[8];
   public static boolean[] enabledArray4 = new boolean[8];
   public static Vector4f[] vector4fArray = new Vector4f[8];
   public static StandardListEntry standardListEntry = new StandardListEntry();
   public static final NoneManager noneManager6 = standardListEntry.getNoneManager();
   public static final NoneManager noneManager25 = standardListEntry.resolveNoneManagerByText("shadow", noneManager6);
   public static final NoneManager noneManager28 = standardListEntry.resolveNoneManagerByText("shadow_solid", noneManager25);
   public static final NoneManager noneManager24 = standardListEntry.resolveNoneManagerByText("shadow_cutout", noneManager25);
   public static final NoneManager noneManager12 = standardListEntry.resolveNoneManager("gbuffers_basic", noneManager6);
   public static final NoneManager noneManager5 = standardListEntry.resolveNoneManager("gbuffers_textured", noneManager12);
   public static final NoneManager noneManager30 = standardListEntry.resolveNoneManager("gbuffers_textured_lit", noneManager5);
   public static final NoneManager noneManager22 = standardListEntry.resolveNoneManager("gbuffers_skybasic", noneManager12);
   public static final NoneManager noneManager4 = standardListEntry.resolveNoneManager("gbuffers_skytextured", noneManager5);
   public static final NoneManager noneManager8 = standardListEntry.resolveNoneManager("gbuffers_clouds", noneManager5);
   public static final NoneManager noneManager19 = standardListEntry.resolveNoneManager("gbuffers_terrain", noneManager30);
   public static final NoneManager noneManager3 = standardListEntry.resolveNoneManager("gbuffers_terrain_solid", noneManager19);
   public static final NoneManager noneManager26 = standardListEntry.resolveNoneManager("gbuffers_terrain_cutout_mip", noneManager19);
   public static final NoneManager noneManager16 = standardListEntry.resolveNoneManager("gbuffers_terrain_cutout", noneManager19);
   public static final NoneManager noneManager14 = standardListEntry.resolveNoneManager("gbuffers_damagedblock", noneManager19);
   public static final NoneManager noneManager18 = standardListEntry.resolveNoneManager("gbuffers_block", noneManager19);
   public static final NoneManager noneManager31 = standardListEntry.resolveNoneManager("gbuffers_beaconbeam", noneManager5);
   public static final NoneManager noneManager11 = standardListEntry.resolveNoneManager("gbuffers_item", noneManager30);
   public static final NoneManager noneManager29 = standardListEntry.resolveNoneManager("gbuffers_entities", noneManager30);
   public static final NoneManager noneManager27 = standardListEntry.resolveNoneManager("gbuffers_entities_glowing", noneManager29);
   public static final NoneManager noneManager9 = standardListEntry.resolveNoneManager("gbuffers_armor_glint", noneManager5);
   public static final NoneManager noneManager23 = standardListEntry.resolveNoneManager("gbuffers_spidereyes", noneManager5);
   public static final NoneManager noneManager17 = standardListEntry.resolveNoneManager("gbuffers_hand", noneManager30);
   public static final NoneManager noneManager20 = standardListEntry.resolveNoneManager("gbuffers_weather", noneManager30);
   public static final NoneManager noneManager = standardListEntry.resolveNoneManagerByText("deferred_pre");
   public static final NoneManager[] noneManagerArray = standardListEntry.resolveNoneManagerArray("deferred", 16);
   public static final NoneManager noneManager21 = noneManagerArray[0];
   public static final NoneManager noneManager10 = standardListEntry.resolveNoneManager("gbuffers_water", noneManager19);
   public static final NoneManager noneManager15 = standardListEntry.resolveNoneManager("gbuffers_hand_water", noneManager17);
   public static final NoneManager noneManager13 = standardListEntry.resolveNoneManagerByText("composite_pre");
   public static final NoneManager[] noneManagerArray3 = standardListEntry.resolveNoneManagerArrayByText("composite", 16);
   public static final NoneManager noneManager2 = noneManagerArray3[0];
   public static final NoneManager noneManager32 = standardListEntry.resolveNoneManager2("final");
   public static final int count2 = standardListEntry.size();
   public static final NoneManager[] noneManagerArray2 = standardListEntry.getNoneManagerArray();
   public static NoneManager noneManager7 = noneManager6;
   public static int count30 = 0;
   public static ProgramStackEmptyManager programStackEmptyManager = new ProgramStackEmptyManager();
   public static boolean enabled34 = false;
   public static IntBuffer intBuffer3 = null;
   public static int count13 = 0;
   public static Properties properties = null;
   public static Properties properties2 = null;
   public static BridgeListener bridgeListener = null;
   public static boolean[] enabledArray = new boolean[2];
   public static boolean[] enabledArray2 = new boolean[2];
   public static boolean[] enabledArray5 = new boolean[2];
   public static boolean[] enabledArray3 = new boolean[8];
   public static boolean[] enabledArray6 = new boolean[8];
   public static boolean enabled10 = false;
   public static boolean enabled30 = false;
   public static float factor20 = 0.125F;
   public static float factor31 = 1.0F;
   public static float factor5 = 1.0F;
   public static int count7 = 0;
   public static int count17 = 0;
   public static int count22 = 0;
   public static int count29 = 0;
   public static int count44 = 0;
   public static int count16 = 0;
   public static boolean enabled17 = true;
   public static boolean enabled15 = true;
   public static boolean enabled37 = true;
   public static ManagerAdapter managerAdapter8 = new ManagerAdapter("oldLighting", "Classic Lighting", 0);
   public static ManagerAdapter managerAdapter2 = new ManagerAdapter("oldHandLight", "Old Hand Light", 0);
   public static int count5 = 0;
   public static final int count59 = 3;
   public static final int count40 = 2;
   public static final String[] stringArray2 = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
   public static final String[] stringArray4 = new String[]{"Nearest", "Linear"};
   public static final int[] countArray5 = new int[]{9728, 9984, 9986};
   public static final int[] countArray6 = new int[]{9728, 9729};
   public static PreUpdateFilter preUpdateFilter = null;
   public static boolean enabled19 = false;
   public static String labelText7;
   public static final String labelText2 = "OFF";
   public static final String labelText3 = "(internal)";
   public static final String labelText5 = "shaderpacks";
   public static final String labelText8 = "optionsshaders.txt";
   public static final File file2 = new File(Minecraft.getMinecraft().mcDataDir, "shaderpacks").getAbsoluteFile();
   public static File file = new File(Minecraft.getMinecraft().mcDataDir, "optionsshaders.txt");
   public static OptionManager[] optionManagerArray = null;
   public static Set set = null;
   public static GeometryCounter[] geometryCounterArray = null;
   public static Map map2 = null;
   public static Map map = new HashMap();
   public static final String labelText = "/shaders/shaders.properties";
   public static AuxiliaryManagerAdapter auxiliaryManagerAdapter = new AuxiliaryManagerAdapter("clouds", "Clouds", 0);
   public static ManagerAdapter managerAdapter = new ManagerAdapter("oldLighting", "Classic Lighting", 0);
   public static ManagerAdapter managerAdapter3 = new ManagerAdapter("oldHandLight", "Old Hand Light", 0);
   public static ManagerAdapter managerAdapter16 = new ManagerAdapter("dynamicHandLight", "Dynamic Hand Light", 0);
   public static ManagerAdapter managerAdapter10 = new ManagerAdapter("shadowTranslucent", "Shadow Translucent", 0);
   public static ManagerAdapter managerAdapter15 = new ManagerAdapter("underwaterOverlay", "Underwater Overlay", 0);
   public static ManagerAdapter managerAdapter17 = new ManagerAdapter("sun", "Sun", 0);
   public static ManagerAdapter managerAdapter14 = new ManagerAdapter("moon", "Moon", 0);
   public static ManagerAdapter managerAdapter12 = new ManagerAdapter("vignette", "Vignette", 0);
   public static ManagerAdapter managerAdapter7 = new ManagerAdapter("backFace.solid", "Back-face Solid", 0);
   public static ManagerAdapter managerAdapter5 = new ManagerAdapter("backFace.cutout", "Back-face Cutout", 0);
   public static ManagerAdapter managerAdapter13 = new ManagerAdapter("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
   public static ManagerAdapter managerAdapter4 = new ManagerAdapter("backFace.translucent", "Back-face Translucent", 0);
   public static ManagerAdapter managerAdapter11 = new ManagerAdapter("rain.depth", "Rain Depth", 0);
   public static ManagerAdapter managerAdapter6 = new ManagerAdapter("beacon.beam.depth", "Rain Depth", 0);
   public static ManagerAdapter managerAdapter9 = new ManagerAdapter("separateAo", "Separate AO", 0);
   public static ManagerAdapter managerAdapter18 = new ManagerAdapter("frustum.culling", "Frustum Culling", 0);
   public static Map map3 = new HashMap();
   public static World world = null;
   public static List list = new ArrayList();
   public static PreUpdateSubscriber[] preUpdateSubscriberArray3 = null;
   public static PreUpdateSubscriber[] preUpdateSubscriberArray = null;
   public static PreUpdateSubscriber[] preUpdateSubscriberArray2 = null;
   public static String labelText9 = null;
   public static FovObserver fovObserver = null;
   public static final int count32 = 0;
   public static final int count37 = 1;
   public static final int count14 = 2;
   public static final String[] stringArray3 = new String[]{"gbuffers", "composite", "deferred"};
   public static final boolean enabled26 = true;
   public static final boolean enabled11 = true;
   public static final boolean enabled = System.getProperty("shaders.debug.save", "false").equals("true");
   public static float factor32 = 0.5F;
   public static float factor8 = 0.6F;
   public static float factor18 = 0.8F;
   public static float factor29 = -1.0F;
   public static float factor35 = 0.0F;
   public static float factor17 = 0.0F;
   public static int count31 = 0;
   public static float factor16 = 0.0F;
   public static float factor15;
   public static float factor10;
   public static float factor41;
   public static float factor19 = 2.0F;
   public static int count41 = 16;
   public static int[] countArray2 = new int[2];
   public static PreUpdateSubscriber preUpdateSubscriber;
   public static boolean enabled9 = false;
   public static int count19 = 256;
   public static final int[] countArray4 = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
   public static final int count45 = (285 + 8 * count2) * 4;
   public static final ByteBuffer byteBuffer = (ByteBuffer)((Buffer)BufferUtils.createByteBuffer(count45)).limit(0);
   public static final float[] factorArray13 = new float[16];
   public static final float[] factorArray9 = new float[16];
   public static final float[] factorArray6 = new float[16];
   public static final float[] factorArray8 = new float[16];
   public static final float[] factorArray16 = new float[16];
   public static final float[] factorArray4 = new float[16];
   public static final float[] factorArray = new float[16];
   public static final float[] factorArray17 = new float[16];
   public static final FloatBuffer floatBuffer10 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer5 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer3 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer4 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer9 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer11 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer8 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer7 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer6 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer12 = resolveFloatBuffer(16);
   public static final FloatBuffer floatBuffer2 = resolveFloatBuffer(16);
   public static final IntBuffer intBuffer8 = resolveIntBuffer(16);
   public static final IntBuffer intBuffer4 = resolveIntBuffer(3);
   public static final IntBuffer intBuffer5 = resolveIntBuffer(8);
   public static final IntBuffer intBuffer2 = resolveIntBuffer(2);
   public static final IntBuffer intBuffer9 = resolveIntBuffer(8);
   public static final IntBuffer intBuffer6 = resolveIntBuffer(8);
   public static final IntBuffer intBuffer = (IntBuffer)((Buffer)resolveIntBuffer(8)).limit(0);
   public static final IntBuffer intBuffer7 = (IntBuffer)((Buffer)resolveIntBuffer(8).put(36064)).position(0).limit(1);
   public static final IntBufferEntry intBufferEntry = new IntBufferEntry(intBuffer8, 8);
   public static Map map4;
   public static final String[] stringArray = new String[]{
      "R8",
      "RG8",
      "RGB8",
      "RGBA8",
      "R8_SNORM",
      "RG8_SNORM",
      "RGB8_SNORM",
      "RGBA8_SNORM",
      "R16",
      "RG16",
      "RGB16",
      "RGBA16",
      "R16_SNORM",
      "RG16_SNORM",
      "RGB16_SNORM",
      "RGBA16_SNORM",
      "R16F",
      "RG16F",
      "RGB16F",
      "RGBA16F",
      "R32F",
      "RG32F",
      "RGB32F",
      "RGBA32F",
      "R32I",
      "RG32I",
      "RGB32I",
      "RGBA32I",
      "R32UI",
      "RG32UI",
      "RGB32UI",
      "RGBA32UI",
      "R3_G3_B2",
      "RGB5_A1",
      "RGB10_A2",
      "R11F_G11F_B10F",
      "RGB9_E5"
   };
   public static final int[] countArray = new int[]{
      33321,
      33323,
      32849,
      32856,
      36756,
      36757,
      36758,
      36759,
      33322,
      33324,
      32852,
      32859,
      36760,
      36761,
      36762,
      36763,
      33325,
      33327,
      34843,
      34842,
      33326,
      33328,
      34837,
      34836,
      33333,
      33339,
      36227,
      36226,
      33334,
      33340,
      36209,
      36208,
      10768,
      32855,
      32857,
      35898,
      35901
   };
   public static final Pattern pattern = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
   public static int[] countArray7 = new int[32];
   public static int count23 = 0;

   public static void updateState28() {
      computeCount3("pre endHand");
      computeCountByText("pre endHand");
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GlStateManager.glStateAction3(770, 771);
      computeCount3("endHand");
   }

   private static void updateState42() {
      SplineMonitor splineMonitor = noneManager7.getSplineMonitor();
      if (splineMonitor != null) {
         int count60 = (int)((float)count12 * splineMonitor.getFactor2());
         int count61 = (int)((float)count15 * splineMonitor.getFactor3());
         int count62 = (int)((float)count12 * splineMonitor.getFactor());
         int count63 = (int)((float)count15 * splineMonitor.getFactor());
         GL11.glViewport(count60, count61, count62, count63);
      }
   }

   private static void updateState78() {
      enabled42 = false;
      count10 = Math.round((float)count27 * factor5);
      count6 = Math.round((float)count54 * factor5);
      updateState72();
   }

   public static void updateState34() {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      updateState70();
      int count60 = noneManager7.getCount2();
      if (count60 > 1) {
         for (int index = 1; index < count60; index++) {
            stringHandler34.applyIndex2(index);
            updateState70();
         }

         stringHandler34.applyIndex2(0);
      }
   }

   public static void updateState51() {
      enabled6 = true;
      if (noneManager7 == noneManager5) {
         applyNoneManager2(noneManager30);
      }
   }

   public static void updateState() {
      if (enabled18) {
         applyNoneManager2(noneManager22);
      } else if (noneManager7 == noneManager5 || noneManager7 == noneManager30) {
         applyNoneManager2(noneManager12);
      }
   }

   public static void updateState67() {
      programStackEmptyManager.applyNoneManager(noneManager7);
   }

   private static void updateState75() {
      map3 = new HashMap();
      if (enabled19) {
         ArrayList list2 = new ArrayList();
         String text = "/shaders/lang/";
         String text2 = "en_US";
         String text3 = ".lang";
         list2.add(text + text2 + text3);
         if (!BuildManager.getGameSettings().text93.equals(text2)) {
            list2.add(text + BuildManager.getGameSettings().text93 + text3);
         }

         try {
            for (String text4 : (Iterable<String>)(Iterable)list2) {
               InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text4);
               if (resourceAsStream != null) {
                  PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
                  GeneratorDefaultManager.applyInputStream(resourceAsStream, propertiesAdapter);
                  resourceAsStream.close();

                  for (String text5 : (Iterable<String>)(Iterable)propertiesAdapter.keySet()) {
                     String property = propertiesAdapter.getProperty(text5);
                     map3.put(text5, property);
                  }
               }
            }
         } catch (IOException ioException) {
            ioException.printStackTrace();
         }
      }
   }

   public static void applyFloat(float posX, float posY, float posZ) {
      factor15 = posX;
      factor10 = posY;
      factor41 = posZ;
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder, factor15, factor10, factor41);
   }

   public static void updateState16() {
      if (enabled31) {
         if (!enabled12) {
            updateState48();
            applyNoneManager2(noneManager10);
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
         } else {
            GlStateManager.depthMask(true);
         }
      }
   }

   public static boolean isEnabled3() {
      return enabled3;
   }

   public static PreUpdateFilter resolvePreUpdateFilter(String text) {
      if (text == null) {
         return null;
      } else {
         text = text.trim();
         if (text.isEmpty() || text.equals("OFF")) {
            return null;
         } else if (text.equals("(internal)")) {
            return new DampenerAggregator();
         } else {
            try {
               File file3 = new File(file2, text);
               return (PreUpdateFilter)(file3.isDirectory()
                  ? new FovBuilder(text, file3)
                  : (file3.isFile() && text.toLowerCase().endsWith(".zip") ? new GeometryBridge(text, file3) : null));
            } catch (Exception exception) {
               exception.printStackTrace();
               return null;
            }
         }
      }
   }

   public static int computeCount2(int count60) {
      countArray7[count23 * 2] = countArray7[count23 * 2] & 65535 | count60 << 16;
      return count60;
   }

   private static NoneManager resolveNoneManager(int count60) {
      for (int index = 0; index < noneManagerArray2.length; index++) {
         NoneManager noneManager33 = noneManagerArray2[index];
         if (noneManager33.getCount() == count60) {
            return noneManager33;
         }
      }

      return noneManager6;
   }

   private static FloatBuffer resolveFloatBuffer(int count60) {
      ByteBuffer buffer = byteBuffer;
      int count61 = buffer.limit();
      ((Buffer)buffer).position(count61).limit(count61 + count60 * 4);
      return buffer.asFloatBuffer();
   }

   private static int computeCount(World world2) {
      return world2 == null ? Integer.MIN_VALUE : world2.secondaryFloatArrayEntry.getCountAsIndex();
   }

   private static void applyPreUpdateFilter(PreUpdateFilter preUpdateFilter2, Properties properties3) throws IOException {
      String text = "shaderpacks/" + preUpdateFilter2.getName() + ".txt";
      File file3 = new File(Minecraft.getMinecraft().mcDataDir, text);
      if (properties3.isEmpty()) {
         file3.delete();
      } else {
         FileOutputStream outputStream = new FileOutputStream(file3);
         properties3.store(outputStream, (String)null);
         outputStream.flush();
         outputStream.close();
      }
   }

   private static String resolveText(int count60) {
      switch (count60) {
         case 33305:
            return "Undefined";
         case 36053:
            return "Complete";
         case 36054:
            return "Incomplete attachment";
         case 36055:
            return "Incomplete missing attachment";
         case 36059:
            return "Incomplete draw buffer";
         case 36060:
            return "Incomplete read buffer";
         case 36061:
            return "Unsupported";
         case 36182:
            return "Incomplete multisample";
         case 36264:
            return "Incomplete layer targets";
         default:
            return "Unknown";
      }
   }

   private static PreUpdateSubscriber resolvePreUpdateSubscriber2(int count60, String text) {
      String text2 = text.trim();
      byte byteValue = 0;
      if (text2.startsWith("minecraft:textures/")) {
         text2 = SplineCollector.resolveText6(text2, ".png");
         if (text2.endsWith("_n.png")) {
            text2 = SplineCollector.resolveTextByText(text2, "_n.png", ".png");
            byteValue = 1;
         } else if (text2.endsWith("_s.png")) {
            text2 = SplineCollector.resolveTextByText(text2, "_s.png", ".png");
            byteValue = 2;
         }
      }

      ResourceLocation location = new ResourceLocation(text2);
      return new DistancePipeline(count60, location, byteValue);
   }

   private static int computeCountByNoneManager(NoneManager noneManager33, String text) {
      int count60 = ARBShaderObjects.glCreateShaderObjectARB(35632);
      if (count60 == 0) {
         return 0;
      } else {
         StringBuilder builder = new StringBuilder(131072);

         BufferedReader reader;
         try {
            reader = new BufferedReader(resolveReader(text));
         } catch (Exception exception) {
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         OptionManager[] optionManagerArray2 = resolveOptionManagerArrayByOptionManagerArr(optionManagerArray);
         ArrayList list2 = new ArrayList();

         try {
            reader = SlidersManager.resolveBufferedReader(reader, text, preUpdateFilter, 0, list2, 0);
            AimAggregator aimAggregator = new AimAggregator();

            while (true) {
               String line = reader.readLine();
               if (line == null) {
                  reader.close();
                  break;
               }

               line = resolveText(line, optionManagerArray2);
               builder.append(line).append('\n');
               if (aimAggregator.matchesText(line)) {
                  AngleFactory angleFactory = SecondaryCustom.resolveAngleFactory(line);
                  if (angleFactory != null) {
                     if (angleFactory.isActive7()) {
                        String labelText11 = angleFactory.getLabelText();
                        int count61;
                        if ((count61 = SecondaryCustom.computeCount2(labelText11)) >= 0) {
                           count53 = Math.max(count53, count61 + 1);
                        } else if ((count61 = SecondaryCustom.computeCountByText(labelText11)) >= 0) {
                           count8 = Math.max(count8, count61 + 1);
                        } else if ((count61 = SecondaryCustom.computeCount(labelText11)) >= 0) {
                           count56 = Math.max(count56, count61 + 1);
                        } else if (labelText11.equals("gdepth") && countArray3[1] == 6408) {
                           countArray3[1] = 34836;
                        } else if ((count61 = SecondaryCustom.computeCount3(labelText11)) >= 0) {
                           count55 = Math.max(count55, count61 + 1);
                        } else if (labelText11.equals("centerDepthSmooth")) {
                           enabled44 = true;
                        }
                     } else if (angleFactory.matchesText4("shadowMapResolution") || angleFactory.matchesText("SHADOWRES")) {
                        count27 = count54 = angleFactory.getCountAsIndex();
                        count10 = count6 = Math.round((float)count27 * factor5);
                        ShadersManager.applyText("Shadow map resolution: " + count27);
                     } else if (angleFactory.matchesText8("shadowMapFov") || angleFactory.matchesText("SHADOWFOV")) {
                        factor30 = angleFactory.getFactor();
                        enabled4 = false;
                        ShadersManager.applyText("Shadow map field of view: " + factor30);
                     } else if (angleFactory.matchesText8("shadowDistance") || angleFactory.matchesText("SHADOWHPL")) {
                        factor2 = angleFactory.getFactor();
                        enabled4 = true;
                        ShadersManager.applyText("Shadow map distance: " + factor2);
                     } else if (angleFactory.matchesText8("shadowDistanceRenderMul")) {
                        factor40 = angleFactory.getFactor();
                        ShadersManager.applyText("Shadow distance render mul: " + factor40);
                     } else if (angleFactory.matchesText8("shadowIntervalSize")) {
                        factor19 = angleFactory.getFactor();
                        ShadersManager.applyText("Shadow map interval size: " + factor19);
                     } else if (angleFactory.matchesText("generateShadowMipmap", true)) {
                        Arrays.fill(enabledArray2, true);
                        ShadersManager.applyText("Generate shadow mipmap");
                     } else if (angleFactory.matchesText("generateShadowColorMipmap", true)) {
                        Arrays.fill(enabledArray3, true);
                        ShadersManager.applyText("Generate shadow color mipmap");
                     } else if (angleFactory.matchesText("shadowHardwareFiltering", true)) {
                        Arrays.fill(enabledArray, true);
                        ShadersManager.applyText("Hardware shadow filtering enabled.");
                     } else if (angleFactory.matchesText("shadowHardwareFiltering0", true)) {
                        enabledArray[0] = true;
                        ShadersManager.applyText("shadowHardwareFiltering0");
                     } else if (angleFactory.matchesText("shadowHardwareFiltering1", true)) {
                        enabledArray[1] = true;
                        ShadersManager.applyText("shadowHardwareFiltering1");
                     } else if (angleFactory.matchesText("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                        enabledArray2[0] = true;
                        ShadersManager.applyText("shadowtex0Mipmap");
                     } else if (angleFactory.matchesText("shadowtex1Mipmap", true)) {
                        enabledArray2[1] = true;
                        ShadersManager.applyText("shadowtex1Mipmap");
                     } else if (angleFactory.matchesText("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                        enabledArray3[0] = true;
                        ShadersManager.applyText("shadowcolor0Mipmap");
                     } else if (angleFactory.matchesText("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                        enabledArray3[1] = true;
                        ShadersManager.applyText("shadowcolor1Mipmap");
                     } else if (angleFactory.matchesText("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                        enabledArray5[0] = true;
                        ShadersManager.applyText("shadowtex0Nearest");
                     } else if (angleFactory.matchesText("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                        enabledArray5[1] = true;
                        ShadersManager.applyText("shadowtex1Nearest");
                     } else if (angleFactory.matchesText("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                        enabledArray6[0] = true;
                        ShadersManager.applyText("shadowcolor0Nearest");
                     } else if (angleFactory.matchesText("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                        enabledArray6[1] = true;
                        ShadersManager.applyText("shadowcolor1Nearest");
                     } else if (angleFactory.matchesText8("wetnessHalflife") || angleFactory.matchesText("WETNESSHL")) {
                        factor13 = angleFactory.getFactor();
                        ShadersManager.applyText("Wetness halflife: " + factor13);
                     } else if (angleFactory.matchesText8("drynessHalflife") || angleFactory.matchesText("DRYNESSHL")) {
                        factor25 = angleFactory.getFactor();
                        ShadersManager.applyText("Dryness halflife: " + factor25);
                     } else if (angleFactory.matchesText8("eyeBrightnessHalflife")) {
                        factor33 = angleFactory.getFactor();
                        ShadersManager.applyText("Eye brightness halflife: " + factor33);
                     } else if (angleFactory.matchesText8("centerDepthHalflife")) {
                        factor23 = angleFactory.getFactor();
                        ShadersManager.applyText("Center depth halflife: " + factor23);
                     } else if (angleFactory.matchesText8("sunPathRotation")) {
                        factor35 = angleFactory.getFactor();
                        ShadersManager.applyText("Sun path rotation: " + factor35);
                     } else if (angleFactory.matchesText8("ambientOcclusionLevel")) {
                        factor29 = BuildManager.computeFactor(angleFactory.getFactor(), 0.0F, 1.0F);
                        ShadersManager.applyText("AO Level: " + factor29);
                     } else if (angleFactory.matchesText4("superSamplingLevel")) {
                        int countAsIndex = angleFactory.getCountAsIndex();
                        if (countAsIndex > 1) {
                           ShadersManager.applyText("Super sampling level: " + countAsIndex + "x");
                           count35 = countAsIndex;
                        } else {
                           count35 = 1;
                        }
                     } else if (angleFactory.matchesText4("noiseTextureResolution")) {
                        count19 = angleFactory.getCountAsIndex();
                        enabled9 = true;
                        ShadersManager.applyText("Noise texture enabled");
                        ShadersManager.applyText("Noise texture resolution: " + count19);
                     } else if (angleFactory.matchesTextByText("Format")) {
                        String text2 = SplineCollector.resolveText3(angleFactory.getLabelText(), "Format");
                        line = angleFactory.getLabelText2();
                        int count62 = computeCount(text2);
                        int count63 = computeCount2(line);
                        if (count62 >= 0 && count63 != 0) {
                           countArray3[count62] = count63;
                           ShadersManager.applyText2("%s format: %s", new Object[]{text2, line});
                        }
                     } else if (angleFactory.matchesTextByText("Clear", false)) {
                        if (SecondaryCustom.matchesText(text) || SecondaryCustom.matchesText3(text)) {
                           String text3 = SplineCollector.resolveText3(angleFactory.getLabelText(), "Clear");
                           int count64 = computeCount(text3);
                           if (count64 >= 0) {
                              enabledArray4[count64] = false;
                              ShadersManager.applyText2("%s clear disabled", new Object[]{text3});
                           }
                        }
                     } else if (angleFactory.matchesText2("ClearColor")) {
                        if (SecondaryCustom.matchesText(text) || SecondaryCustom.matchesText3(text)) {
                           String text4 = SplineCollector.resolveText3(angleFactory.getLabelText(), "ClearColor");
                           int count65 = computeCount(text4);
                           if (count65 >= 0) {
                              Vector4f vector4f = angleFactory.getVector4f();
                              if (vector4f != null) {
                                 vector4fArray[count65] = vector4f;
                                 ShadersManager.applyText2(
                                    "%s clear color: %s %s %s %s", new Object[]{text4, vector4f.getX(), vector4f.getY(), vector4f.getZ(), vector4f.getW()}
                                 );
                              } else {
                                 ShadersManager.applyText3("Invalid color value: " + angleFactory.getLabelText2());
                              }
                           }
                        }
                     } else if (angleFactory.matchesTextByText("GAUX4FORMAT", "RGBA32F")) {
                        countArray3[7] = 34836;
                        ShadersManager.applyText("gaux4 format : RGB32AF");
                     } else if (angleFactory.matchesTextByText("GAUX4FORMAT", "RGB32F")) {
                        countArray3[7] = 34837;
                        ShadersManager.applyText("gaux4 format : RGB32F");
                     } else if (angleFactory.matchesTextByText("GAUX4FORMAT", "RGB16")) {
                        countArray3[7] = 32852;
                        ShadersManager.applyText("gaux4 format : RGB16");
                     } else if (angleFactory.matchesTextByText("MipmapEnabled", true)) {
                        if (SecondaryCustom.matchesText(text) || SecondaryCustom.matchesText3(text) || SecondaryCustom.matchesTextByText(text)) {
                           String text5 = SplineCollector.resolveText3(angleFactory.getLabelText(), "MipmapEnabled");
                           int count66 = computeCount(text5);
                           if (count66 >= 0) {
                              int count67 = noneManager33.getCount5();
                              count67 |= 1 << count66;
                              noneManager33.setCount5(count67);
                              ShadersManager.applyText2("%s mipmap enabled", new Object[]{text5});
                           }
                        }
                     } else if (angleFactory.matchesText("DRAWBUFFERS")) {
                        String labelText12 = angleFactory.getLabelText2();
                        if (SecondaryCustom.matchesText2(labelText12)) {
                           noneManager33.setLabelText2(labelText12);
                        } else {
                           ShadersManager.applyText3("Invalid draw buffers: " + labelText12);
                        }
                     }
                  }
               }
            }
         } catch (Exception exception2) {
            ShadersManager.applyTextByText("Couldn't read " + text + "!");
            exception2.printStackTrace();
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         if (enabled) {
            applyText(text, builder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(count60, builder);
         ARBShaderObjects.glCompileShaderARB(count60);
         if (GL20.glGetShaderi(count60, 35713) != 1) {
            ShadersManager.applyTextByText("Error compiling fragment shader: " + text);
         }

         matchesText(count60, text, list2);
         return count60;
      }
   }

   public static boolean contains(String text) {
      return set == null ? false : set.contains(text);
   }

   public static void applyItemStack(ItemStack stack) {
      enabled35 = matchesItemStack(stack);
   }

   private static PreUpdateSubscriber resolvePreUpdateSubscriber(
      int count60,
      String text,
      String text2,
      RenderStateProperty renderStateProperty,
      NativeOptionType nativeOptionType,
      int count61,
      int count62,
      int count63,
      VirtualOptionType virtualOptionType,
      StaticOptionType staticOptionType
   ) {
      try {
         String text3 = "shaders/" + SplineCollector.resolveText2(text2, "/");
         InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text3);
         if (resourceAsStream == null) {
            ShadersManager.applyText3("Raw texture not found: " + text2);
            return null;
         } else {
            byte[] byteValueArray = BuildManager.resolveByteArray(resourceAsStream);
            IOUtils.closeQuietly(resourceAsStream);
            ByteBuffer buffer = VectorMatcher.resolveByteBuffer(byteValueArray.length);
            buffer.put(byteValueArray);
            ((Buffer)buffer).flip();
            SupplementalListEntry supplementalListEntry = SmoothRegistry.resolveSupplementalListEntry(text3, new SupplementalListEntry(true, true, new ArrayList()));
            return new TargetPicker(renderStateProperty, nativeOptionType, count61, count62, count63, virtualOptionType, staticOptionType, buffer, count60, supplementalListEntry.isEnabled(), supplementalListEntry.isEnabled2());
         }
      } catch (IOException ioException) {
         ShadersManager.applyText3("Error loading raw texture: " + text2);
         ShadersManager.applyText3("" + ioException.getClass().getName() + ": " + ioException.getMessage());
         return null;
      }
   }

   public static void setEnabledByFlag(boolean enabledByFlag) {
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      if (enabledByFlag) {
         applyNoneManager2(noneManager15);
      } else {
         applyNoneManager2(noneManager17);
      }

      computeCount3("beginHand");
      computeCountByText("beginHand");
   }

   public static void updateState12() {
      if (enabled31 && noneManager23.getCount() != noneManager6.getCount()) {
         applyNoneManager2(noneManager29);
         GlStateManager.applyState10();
      }
   }

   public static void applyFloat(float posX, float posX2, float posY, float posZ) {
      GlStateManager.applyFloat(posX, posX2, posY, posZ);
      factor34 = posX;
      factor39 = posX2;
      factor38 = posY;
   }

   public static BlockPos getBlockPos() {
      return new BlockPos(value2, value3, value4);
   }

   public static void applyIndex2(int count60) {
      GL11.glEnable(count60);
      updateState2();
   }

   public static void applyText(String text, String text2) {
      try {
         File file3 = new File(file2, "debug/" + text);
         file3.getParentFile().mkdirs();
         BuildManager.applyFile(file3, text2);
      } catch (IOException ioException) {
         BuildManager.applyText3("Error saving: " + text);
         ioException.printStackTrace();
      }
   }

   private static OptionManager[] resolveOptionManagerArray(OptionManager[] optionManagerArray2) {
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < optionManagerArray2.length; index++) {
         OptionManager optionManager = optionManagerArray2[index];
         if (optionManager.isEnabled2()) {
            list2.add(optionManager);
         }
      }

      return (OptionManager[])list2.toArray(new OptionManager[list2.size()]);
   }

   private static int computeCount(TileEntity tileEntity) {
      if (tileEntity == null) {
         return -1;
      } else {
         Block block = tileEntity.getBlock();
         if (block == null) {
            return 0;
         } else {
            int idFromBlock = Block.getIdFromBlock(block);
            int count60 = tileEntity.getCount();
            count60 = AuxiliaryShadersManager.computeCount(idFromBlock, count60);
            if (count60 >= 0) {
               idFromBlock = count60;
            }

            return idFromBlock;
         }
      }
   }

   public static void applyIndex(int count60, int count61) {
      count23++;
      countArray7[count23 * 2] = count60 & 65535 | count61 << 16;
      countArray7[count23 * 2 + 1] = 0;
   }

   public static ArrayList getArrayList() {
      ArrayList list2 = new ArrayList();
      list2.add("OFF");
      list2.add("(internal)");
      int size = list2.size();

      try {
         if (!file2.exists()) {
            file2.mkdirs();
         }

         File[] fileArray = file2.listFiles();

         for (int index = 0; index < fileArray.length; index++) {
            File file3 = fileArray[index];
            String name = file3.getName();
            if (file3.isDirectory()) {
               if (!name.equals("debug")) {
                  file3 = new File(file3, "shaders");
                  if (file3.exists() && file3.isDirectory()) {
                     list2.add(name);
                  }
               }
            } else if (file3.isFile() && name.toLowerCase().endsWith(".zip")) {
               list2.add(name);
            }
         }
      } catch (Exception exception) {
      }

      List list3 = list2.subList(size, list2.size());
      Collections.sort(list3, String.CASE_INSENSITIVE_ORDER);
      return list2;
   }

   public static boolean isActive10() {
      return managerAdapter11.isActiveAsFlag();
   }

   public static void applyFlag(boolean flag, boolean flag2) {
      enabled5 = flag;
      enabled2 = flag2;
   }

   public static void applyVec3(Vec3 vec) {
      factor37 = (float)vec.xCoord;
      factor4 = (float)vec.yCoord;
      factor9 = (float)vec.zCoord;
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder2, factor37, factor4, factor9);
   }

   public static float getFactor() {
      return factor40 < 0.0F ? -1.0F : factor2 * factor40;
   }

   private static void applyFile(File file3) {
      if (file3.exists() && file3.isDirectory()) {
         File[] fileArray = file3.listFiles();
         if (fileArray != null) {
            for (int index = 0; index < fileArray.length; index++) {
               File file4 = fileArray[index];
               if (file4.isDirectory()) {
                  applyFile(file4);
               }

               file4.delete();
            }
         }
      }
   }

   public static int computeCountByText(String text) {
      int count60 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (count60 != 36053) {
         System.err.format("FramebufferStatus 0x%04X at %s\n", count60, text);
      }

      return count60;
   }

   public static OptionManager[] resolveOptionManagerArray(String text) {
      OptionManager[] optionManagerArray2 = (OptionManager[])optionManagerArray.clone();
      if (map2 == null) {
         if (geometryCounterArray != null) {
            GeometryPipeline geometryPipeline = new GeometryPipeline(geometryCounterArray, optionManagerArray2);
            optionManagerArray2 = (OptionManager[])BuildManager.resolveTargetArray(optionManagerArray2, geometryPipeline, 0);
         }

         return resolveOptionManagerArray(optionManagerArray2);
      } else {
         String text2 = text != null ? "screen." + text : "screen";
         FovValidator fovValidator = (FovValidator)map2.get(text2);
         if (fovValidator == null) {
            return new OptionManager[0];
         } else {
            OptionManager[] optionManagerArray3 = fovValidator.getOptionManagerArray();
            ArrayList list2 = new ArrayList();

            for (int index = 0; index < optionManagerArray3.length; index++) {
               OptionManager optionManager = optionManagerArray3[index];
               if (optionManager == null) {
                  list2.add((OptionManager)null);
               } else if (optionManager instanceof GeometryAdapter) {
                  OptionManager[] optionManagerArray4 = resolveOptionManagerArray(map2, optionManagerArray2);
                  list2.addAll(Arrays.asList(optionManagerArray4));
               } else {
                  list2.add(optionManager);
               }
            }

            return (OptionManager[])list2.toArray(new OptionManager[list2.size()]);
         }
      }
   }

   private static void applyShaderUniformFloat(ShaderUniformFloat shaderUniformFloat11, boolean flag, FloatBuffer buffer) {
      shaderUniformFloat11.applyFloatBuffer(flag, buffer);
   }

   private static int computeCount(NoneManager noneManager33, String text, int count60) {
      int count61 = 0;
      if (count60 >= text.length()) {
         return count61;
      } else {
         int count62 = text.charAt(count60) - '0';
         if (noneManager33 == noneManager25) {
            if (count62 >= 0 && count62 <= 1) {
               count61 = count62 + 36064;
               count8 = Math.max(count8, count62);
            }

            return count61;
         } else {
            if (count62 >= 0 && count62 <= 7) {
               noneManager33.getEnabledArray()[count62] = true;
               count61 = count62 + 36064;
               count36 = Math.max(count36, count62);
               count55 = Math.max(count55, count62);
            }

            return count61;
         }
      }
   }

   private static int computeCount(int count60) {
      switch (count60) {
         case 33333:
         case 33334:
         case 33339:
         case 33340:
         case 36208:
         case 36209:
         case 36226:
         case 36227:
            return 36251;
         default:
            return 32993;
      }
   }

   private static PreUpdateSubscriber resolvePreUpdateSubscriber(int count60, String text) {
      if (text == null) {
         return null;
      } else {
         text = text.trim();
         return text.indexOf(58) >= 0
            ? resolvePreUpdateSubscriber2(count60, text)
            : (text.indexOf(32) >= 0 ? resolvePreUpdateSubscriberByText(count60, text) : resolvePreUpdateSubscriber3(count60, text));
      }
   }

   public static void applyIndexByIndex(int count60) {
      GL11.glEnable(count60);
      updateState62();
   }

   private static void applyNoneManagerArray(NoneManager[] noneManagerArray4, boolean flag) {
      if (!enabled12) {
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0, 1.0, 0.0, 1.0, 0.0, 1.0);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableTexture2D();
         GlStateManager.applyState10();
         GlStateManager.enableBlend();
         GlStateManager.disableDepth();
         GlStateManager.glDepthFunc(519);
         GlStateManager.depthMask(false);
         GlStateManager.applyState3();
         if (count53 >= 1) {
            GlStateManager.enableState5(33988);
            GlStateManager.setActiveTexture(intBuffer2.get(0));
            if (count53 >= 2) {
               GlStateManager.enableState5(33989);
               GlStateManager.setActiveTexture(intBuffer2.get(1));
            }
         }

         for (int index = 0; index < count55; index++) {
            GlStateManager.enableState5(33984 + countArray4[index]);
            GlStateManager.setActiveTexture(intBufferEntry.computeCount(index));
         }

         GlStateManager.enableState5(33990);
         GlStateManager.setActiveTexture(intBuffer4.get(0));
         if (count56 >= 2) {
            GlStateManager.enableState5(33995);
            GlStateManager.setActiveTexture(intBuffer4.get(1));
            if (count56 >= 3) {
               GlStateManager.enableState5(33996);
               GlStateManager.setActiveTexture(intBuffer4.get(2));
            }
         }

         for (int index2 = 0; index2 < count8; index2++) {
            GlStateManager.enableState5(33997 + index2);
            GlStateManager.setActiveTexture(intBuffer5.get(index2));
         }

         if (enabled9) {
            GlStateManager.enableState5(33984 + preUpdateSubscriber.getCountAsIndex());
            GlStateManager.setActiveTexture(preUpdateSubscriber.getCount2());
         }

         if (flag) {
            applyPreUpdateSubscriberArray(preUpdateSubscriberArray);
         } else {
            applyPreUpdateSubscriberArray(preUpdateSubscriberArray2);
         }

         GlStateManager.enableState5(33984);

         for (int index3 = 0; index3 < count55; index3++) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index3, 3553, intBufferEntry.computeCountByIndex(index3), 0);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, intBuffer4.get(0), 0);
         GL20.glDrawBuffers(intBuffer9);
         computeCount3("pre-composite");

         for (int index4 = 0; index4 < noneManagerArray4.length; index4++) {
            NoneManager noneManager33 = noneManagerArray4[index4];
            if (noneManager33.getCount() != 0) {
               applyNoneManager2(noneManager33);
               computeCount3(noneManager33.getLabelText());
               if (count13 != 0) {
                  updateState20();
               }

               updateState42();
               updateState34();
               updateState54();

               for (int index5 = 0; index5 < count55; index5++) {
                  if (noneManager33.getEnabledArray()[index5]) {
                     intBufferEntry.applyIndex(index5);
                     GlStateManager.enableState5(33984 + countArray4[index5]);
                     GlStateManager.setActiveTexture(intBufferEntry.computeCount(index5));
                     EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index5, 3553, intBufferEntry.computeCountByIndex(index5), 0);
                  }
               }

               GlStateManager.enableState5(33984);
            }
         }

         computeCount3("composite");
         if (flag) {
            updateState5();
            enabled27 = true;
         }

         GlStateManager.applyState13();
         GlStateManager.disableTexture2D();
         GlStateManager.applyState14();
         GlStateManager.disableBlend();
         GlStateManager.glDepthFunc(515);
         GlStateManager.depthMask(true);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         applyNoneManager2(noneManager6);
      }
   }

   private static void applyNoneManager(NoneManager noneManager33) {
      boolean[] enabledArray7 = noneManager33.getEnabledArray();
      Boolean[] itemArray = noneManager33.getItemArray();

      for (int index = 0; index < itemArray.length; index++) {
         Boolean flag = itemArray[index];
         if (flag != null) {
            enabledArray7[index] = flag;
         }
      }
   }

   public static PreUpdateFilter getPreUpdateFilter() {
      return preUpdateFilter;
   }

   public static boolean isActive9() {
      return fovObserver != null;
   }

   public static void updateState11() {
      computeCount3("endUpdateChunks1");
      computeCountByText("endUpdateChunks1");
      if (!enabled12) {
         applyNoneManager2(noneManager19);
      }

      computeCount3("endUpdateChunks2");
      computeCountByText("endUpdateChunks2");
   }

   public static OptionManager[] getOptionManagerArray() {
      return optionManagerArray;
   }

   public static NoneManager resolveNoneManager(String text) {
      return standardListEntry.resolveNoneManager3(text);
   }

   public static void setEnabled(boolean enabled46) {
      if (enabled46) {
         count5 += 2;
         if (count5 > 4) {
            count5 = 0;
         }
      } else {
         count5 -= 2;
         if (count5 < 0) {
            count5 = 4;
         }
      }

      count5 = count5 / 2 * 2;
      count5 = BuildManager.computeCount(count5, 0, 4);
   }

   public static boolean isActive() {
      return !managerAdapter17.isActive2();
   }

   public static void applyText(String text, IntBuffer buffer) {
      StringBuilder builder = new StringBuilder(128);
      builder.append(text).append(" [pos ").append(buffer.position()).append(" lim ").append(buffer.limit()).append(" cap ").append(buffer.capacity()).append(" :");
      int count60 = buffer.limit();

      for (int index = 0; index < count60; index++) {
         builder.append(" ").append(buffer.get(index));
      }

      builder.append("]");
      ShadersManager.applyText(builder.toString());
   }

   private static PreUpdateSubscriber[] resolvePreUpdateSubscriberArray(Properties properties3, int count60) {
      String text = "texture." + stringArray3[count60] + ".";
      Set keys = properties3.keySet();
      ArrayList list2 = new ArrayList();

      for (String text2 : (Iterable<String>)(Iterable)keys) {
         if (text2.startsWith(text)) {
            String text3 = SplineCollector.resolveText2(text2, text);
            text3 = SplineCollector.resolveTextByText(text3, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String text4 = properties3.getProperty(text2).trim();
            int count61 = computeCount(count60, text3);
            if (count61 < 0) {
               ShadersManager.applyText3("Invalid texture name: " + text2);
            } else {
               PreUpdateSubscriber preUpdateSubscriber2 = resolvePreUpdateSubscriber(count61, text4);
               if (preUpdateSubscriber2 != null) {
                  ShadersManager.applyText("Custom texture: " + text2 + " = " + text4);
                  list2.add(preUpdateSubscriber2);
               }
            }
         }
      }

      return list2.size() <= 0 ? null : (PreUpdateSubscriber[])list2.toArray(new PreUpdateSubscriber[list2.size()]);
   }

   public static void applyIntBuffer(IntBuffer buffer) {
      if (buffer == null) {
         buffer = intBuffer;
      }

      if (intBuffer3 != buffer) {
         intBuffer3 = buffer;
         GL20.glDrawBuffers(buffer);
         computeCount3("setDrawBuffers");
      }
   }

   public static int computeCount(String text) {
      return text.equals("colortex0") || text.equals("gcolor")
         ? 0
         : (
            text.equals("colortex1") || text.equals("gdepth")
               ? 1
               : (
                  text.equals("colortex2") || text.equals("gnormal")
                     ? 2
                     : (
                        text.equals("colortex3") || text.equals("composite")
                           ? 3
                           : (
                              text.equals("colortex4") || text.equals("gaux1")
                                 ? 4
                                 : (
                                    text.equals("colortex5") || text.equals("gaux2")
                                       ? 5
                                       : (
                                          !text.equals("colortex6") && !text.equals("gaux3")
                                             ? (!text.equals("colortex7") && !text.equals("gaux4") ? -1 : 7)
                                             : 6
                                       )
                                 )
                           )
                     )
               )
         );
   }

   private static void applyAuxiliaryFloatArrayHolder(AuxiliaryFloatArrayHolder auxiliaryFloatArrayHolder9, float posX, float posY, float posZ) {
      auxiliaryFloatArrayHolder9.applyFloat(posX, posY, posZ);
   }

   public static int getCount() {
      return countArray7[count23 * 2];
   }

   private static Reader resolveReader(String text) {
      return new InputStreamReader(preUpdateFilter.getResourceAsStream(text));
   }

   private static int computeCount(NoneManager noneManager33, String text) {
      int count60 = ARBShaderObjects.glCreateShaderObjectARB(35633);
      if (count60 == 0) {
         return 0;
      } else {
         StringBuilder builder = new StringBuilder(131072);

         BufferedReader reader;
         try {
            reader = new BufferedReader(resolveReader(text));
         } catch (Exception exception) {
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         OptionManager[] optionManagerArray2 = resolveOptionManagerArrayByOptionManagerArr(optionManagerArray);
         ArrayList list2 = new ArrayList();

         try {
            reader = SlidersManager.resolveBufferedReader(reader, text, preUpdateFilter, 0, list2, 0);
            AimAggregator aimAggregator = new AimAggregator();

            while (true) {
               String line = reader.readLine();
               if (line == null) {
                  reader.close();
                  break;
               }

               line = resolveText(line, optionManagerArray2);
               builder.append(line).append('\n');
               if (aimAggregator.matchesText(line)) {
                  AngleFactory angleFactory = SecondaryCustom.resolveAngleFactory(line);
                  if (angleFactory != null) {
                     if (angleFactory.matchesText5("mc_Entity")) {
                        enabled21 = true;
                        enabled20 = true;
                     } else if (angleFactory.matchesText5("mc_midTexCoord")) {
                        enabled7 = true;
                        enabled25 = true;
                     } else if (angleFactory.matchesText5("at_tangent")) {
                        enabled39 = true;
                        enabled41 = true;
                     }

                     if (angleFactory.matchesText4("countInstances")) {
                        noneManager33.setCount2(angleFactory.getCountAsIndex());
                        ShadersManager.applyText("countInstances: " + noneManager33.getCount2());
                     }
                  }
               }
            }
         } catch (Exception exception2) {
            ShadersManager.applyTextByText("Couldn't read " + text + "!");
            exception2.printStackTrace();
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         if (enabled) {
            applyText(text, builder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(count60, builder);
         ARBShaderObjects.glCompileShaderARB(count60);
         if (GL20.glGetShaderi(count60, 35713) != 1) {
            ShadersManager.applyTextByText("Error compiling vertex shader: " + text);
         }

         matchesText(count60, text, list2);
         return count60;
      }
   }

   private static void updateState3() {
      map4 = new IdentityHashMap(300);
      if (map4.isEmpty()) {
         for (ResourceLocation location : (Iterable<ResourceLocation>)(Iterable)Block.iterableExtensionBridge.getSet()) {
            Block object = (Block)Block.iterableExtensionBridge.getObject(location);
            int index = Block.iterableExtensionBridge.getIndex(object);
            map4.put(object, index);
         }
      }

      BufferedReader reader = null;

      try {
         reader = new BufferedReader(new InputStreamReader(preUpdateFilter.getResourceAsStream("/mc_Entity_x.txt")));
      } catch (Exception exception) {
      }

      if (reader != null) {
         String text;
         try {
            while ((text = reader.readLine()) != null) {
               Matcher matcher = pattern.matcher(text);
               if (matcher.matches()) {
                  text = matcher.group(1);
                  int count60 = Integer.parseInt(matcher.group(2));
                  Block blockFromName = Block.getBlockFromName(text);
                  if (blockFromName != null) {
                     map4.put(blockFromName, count60);
                  } else {
                     ShadersManager.applyText3("Unknown block name %s", new Object[]{text});
                  }
               } else {
                  ShadersManager.applyText3("unmatched %s\n", new Object[]{text});
               }
            }
         } catch (Exception exception2) {
            ShadersManager.applyText3("Error parsing mc_Entity_x.txt");
         }
      }

      if (reader != null) {
         try {
            reader.close();
         } catch (Exception exception3) {
         }
      }
   }

   public static OptionManager resolveOptionManager(String text) {
      return InterpolationPredictor.resolveOptionManager(text, optionManagerArray);
   }

   public static void applyIndex(int count60) {
      count23++;
      countArray7[count23 * 2] = count60 & 65535;
      countArray7[count23 * 2 + 1] = 0;
   }

   public static void updateState30() {
      FloatBuffer buffer = floatBuffer12;
      ((Buffer)buffer).clear();
      GL11.glGetFloat(2982, buffer);
      buffer.get(factorArray14, 0, 16);
      AimAccumulator.applyFloatArrayByFloatArray(factorArray15, factorArray14, factorArray3);
      AimAccumulator.applyFloatArrayByFloatArray(factorArray12, factorArray14, factorArray5);
      System.arraycopy(factor26 == factor22 ? factorArray15 : factorArray12, 0, factorArray7, 0, 3);
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder7, factorArray15[0], factorArray15[1], factorArray15[2]);
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder3, factorArray12[0], factorArray12[1], factorArray12[2]);
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder6, factorArray7[0], factorArray7[1], factorArray7[2]);
      if (fovObserver != null) {
         fovObserver.updateState2();
      }

      computeCount3("postCelestialRotate");
   }

   public static boolean isEnabled5() {
      return enabled5;
   }

   public static void updateState76() {
      if (enabled12) {
         computeCount3("shadow endRender");
      } else {
         if (!enabled27) {
            updateState32();
         }

         enabled31 = false;
         GlStateManager.applyFlag(true, true, true, true);
         applyNoneManager2(noneManager6);
         RenderHelper.disableStandardItemLighting();
         computeCount3("endRender end");
      }
   }

   public static void updateState52() {
      if (enabled31) {
         applyNoneManager2(noneManager29);
      }
   }

   public static void updateState33() {
      updateState50();
      updateState39();
      applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
   }

   public static void updateState26() {
      if (!enabled12 && enabled44) {
         ((Buffer)floatBuffer2).clear();
         GL11.glReadPixels(count12 / 2, count15 / 2, 1, 1, 6402, 5126, floatBuffer2);
         factor28 = floatBuffer2.get(0);
         float factor42 = (float)timestamp * 0.01F;
         factor42 = (float)Math.exp(Math.log(0.5) * (double)factor42 / (double)factor23);
         factor24 = factor24 * factor42 + factor28 * (1.0F - factor42);
      }
   }

   public static void updateState23() {
      if (enabled12) {
         computeCount3("shadow clear pre");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, intBuffer2.get(0), 0);
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL20.glDrawBuffers(noneManager25.getIntBuffer());
         computeCountByText("shadow clear");
         GL11.glClear(16640);
         computeCount3("shadow clear");
      } else {
         computeCount3("clear pre");
         if (enabledArray4[0]) {
            Vector4f vector4f = vector4fArray[0];
            if (vector4f != null) {
               GL11.glClearColor(vector4f.getX(), vector4f.getY(), vector4f.getZ(), vector4f.getW());
            }

            if (intBufferEntry.checkIndex(0)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, intBufferEntry.computeCountByIndex(0), 0);
               GL20.glDrawBuffers(36064);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, intBufferEntry.computeCount(0), 0);
            }

            GL20.glDrawBuffers(36064);
            GL11.glClear(16384);
         }

         if (enabledArray4[1]) {
            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            Vector4f vector4f2 = vector4fArray[1];
            if (vector4f2 != null) {
               GL11.glClearColor(vector4f2.getX(), vector4f2.getY(), vector4f2.getZ(), vector4f2.getW());
            }

            if (intBufferEntry.checkIndex(1)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, intBufferEntry.computeCountByIndex(1), 0);
               GL20.glDrawBuffers(36065);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, intBufferEntry.computeCount(1), 0);
            }

            GL20.glDrawBuffers(36065);
            GL11.glClear(16384);
         }

         for (int index = 2; index < count55; index++) {
            if (enabledArray4[index]) {
               GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
               Vector4f vector4f3 = vector4fArray[index];
               if (vector4f3 != null) {
                  GL11.glClearColor(vector4f3.getX(), vector4f3.getY(), vector4f3.getZ(), vector4f3.getW());
               }

               if (intBufferEntry.checkIndex(index)) {
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index, 3553, intBufferEntry.computeCountByIndex(index), 0);
                  GL20.glDrawBuffers(36064 + index);
                  GL11.glClear(16384);
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index, 3553, intBufferEntry.computeCount(index), 0);
               }

               GL20.glDrawBuffers(36064 + index);
               GL11.glClear(16384);
            }
         }

         applyIntBuffer(intBuffer9);
         computeCountByText("clear");
         computeCount3("clear");
      }
   }

   public static void updateState60() {
      enabled42 = true;
   }

   public static void updateState55() {
      applyNoneManager2(noneManager30);
   }

   public static void updateState35() {
      if (enabled31) {
         applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
      }
   }

   public static void updateState63() {
      boolean flag = enabled19;
      boolean active = isActive12();
      if (mc.renderGlobal != null) {
         mc.renderGlobal.updateState8();
      }

      enabled19 = false;
      if (preUpdateFilter != null) {
         preUpdateFilter.close();
         preUpdateFilter = null;
         map3.clear();
         list.clear();
         optionManagerArray = null;
         set = null;
         geometryCounterArray = null;
         map2 = null;
         map.clear();
         auxiliaryManagerAdapter.updateState();
         managerAdapter3.updateState();
         managerAdapter16.updateState();
         managerAdapter.updateState();
         updateState21();
         labelText9 = null;
      }

      boolean flag2 = false;
      if (BuildManager.canExecute4()) {
         ShadersManager.applyText("Shaders can not be loaded, Antialiasing is enabled: " + BuildManager.getCount3() + "x");
         flag2 = true;
      }

      if (BuildManager.canExecute2()) {
         ShadersManager.applyText("Shaders can not be loaded, Anisotropic Filtering is enabled: " + BuildManager.getCount() + "x");
         flag2 = true;
      }

      if (BuildManager.canExecute3()) {
         ShadersManager.applyText("Shaders can not be loaded, Fast Render is enabled.");
         flag2 = true;
      }

      String property = properties2.getProperty(MovementKeybindProperty.SHADER_PACK.getLabelText3(), "(internal)");
      if (!flag2) {
         preUpdateFilter = resolvePreUpdateFilter(property);
         enabled19 = preUpdateFilter != null;
      }

      if (enabled19) {
         ShadersManager.applyText("Loaded shaderpack: " + getText());
      } else {
         ShadersManager.applyText("No shaderpack loaded.");
         preUpdateFilter = new OFFListener();
      }

      if (enabled) {
         applyFile(new File(file2, "debug"));
      }

      updateState75();
      updateState19();
      optionManagerArray = getOptionManagerArrayAsC47MeArray();
      updateState71();
      flag = enabled19 != flag;
      active = isActive12() != active;
      if (flag || active) {
         DefaultVertexFormats.updateState();
         updateState53();
      }

      if (mc.getResourceLanguageManager() != null) {
         LayerManager.updateState();
      }

      if (mc.renderGlobal != null) {
         mc.renderGlobal.updateState17();
      }

      if ((flag || active) && mc.getIResourceManager() != null) {
         mc.getListenableFuture();
      }
   }

   public static String get(String text, String text2) {
      String text3 = (String)map3.get(text);
      return text3 == null ? text2 : text3;
   }

   private static OptionManager[] resolveOptionManagerArray(Map map5, OptionManager[] optionManagerArray2) {
      HashSet set2 = new HashSet();

      for (String text : (Iterable<String>)(Iterable)map5.keySet()) {
         FovValidator fovValidator = (FovValidator)map5.get(text);
         OptionManager[] optionManagerArray3 = fovValidator.getOptionManagerArray();

         for (int index = 0; index < optionManagerArray3.length; index++) {
            OptionManager optionManager = optionManagerArray3[index];
            if (optionManager != null) {
               set2.add(optionManager.getLabelText());
            }
         }
      }

      ArrayList list2 = new ArrayList();

      for (int index2 = 0; index2 < optionManagerArray2.length; index2++) {
         OptionManager optionManager2 = optionManagerArray2[index2];
         if (optionManager2.isEnabled2()) {
            String labelText11 = optionManager2.getLabelText();
            if (!set2.contains(labelText11)) {
               list2.add(optionManager2);
            }
         }
      }

      return (OptionManager[])list2.toArray(new OptionManager[list2.size()]);
   }

   private static void updateState14() {
      if (count33 != 0) {
         EXTFramebufferObject.glDeleteFramebuffersEXT(count33);
         GlStateManager.glStateAction(intBuffer4);
         GlStateManager.glStateAction(intBuffer8);
      }

      count33 = EXTFramebufferObject.glGenFramebuffersEXT();
      GL11.glGenTextures((IntBuffer)((Buffer)intBuffer4).clear().limit(count56));
      GL11.glGenTextures((IntBuffer)((Buffer)intBuffer8).clear().limit(16));
      ((Buffer)intBuffer4).position(0);
      ((Buffer)intBuffer8).position(0);
      EXTFramebufferObject.glBindFramebufferEXT(36160, count33);
      GL20.glDrawBuffers(0);
      GL11.glReadBuffer(0);

      for (int index = 0; index < count56; index++) {
         GlStateManager.setActiveTexture(intBuffer4.get(index));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 34891, 6409);
         GL11.glTexImage2D(3553, 0, 6402, count12, count15, 0, 6402, 5126, (FloatBuffer)null);
      }

      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, intBuffer4.get(0), 0);
      GL20.glDrawBuffers(intBuffer9);
      GL11.glReadBuffer(0);
      computeCount3("FT d");

      for (int index2 = 0; index2 < count55; index2++) {
         GlStateManager.setActiveTexture(intBufferEntry.computeCount(index2));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, countArray3[index2], count12, count15, 0, computeCount(countArray3[index2]), 33639, (ByteBuffer)null);
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index2, 3553, intBufferEntry.computeCount(index2), 0);
         computeCount3("FT c");
      }

      for (int index3 = 0; index3 < count55; index3++) {
         GlStateManager.setActiveTexture(intBufferEntry.computeCountByIndex(index3));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, countArray3[index3], count12, count15, 0, computeCount(countArray3[index3]), 33639, (ByteBuffer)null);
         computeCount3("FT ca");
      }

      int count60 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (count60 == 36058) {
         applyTextByText("[Shaders] Error: Failed framebuffer incomplete formats");

         for (int index4 = 0; index4 < count55; index4++) {
            GlStateManager.setActiveTexture(intBufferEntry.computeCount(index4));
            GL11.glTexImage2D(3553, 0, 6408, count12, count15, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index4, 3553, intBufferEntry.computeCount(index4), 0);
            computeCount3("FT c");
         }

         count60 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (count60 == 36053) {
            ShadersManager.applyText("complete");
         }
      }

      GlStateManager.setActiveTexture(0);
      if (count60 != 36053) {
         applyTextByText("[Shaders] Error: Failed creating framebuffer! (Status " + count60 + ")");
      } else {
         ShadersManager.applyText("Framebuffer created.");
      }
   }

   private static Properties resolveProperties(PreUpdateFilter preUpdateFilter2) throws IOException {
      PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
      String text = "shaderpacks/" + preUpdateFilter2.getName() + ".txt";
      File file3 = new File(Minecraft.getMinecraft().mcDataDir, text);
      if (file3.exists() && file3.isFile() && file3.canRead()) {
         FileInputStream inputStream = new FileInputStream(file3);
         propertiesAdapter.load(inputStream);
         inputStream.close();
         return propertiesAdapter;
      } else {
         return propertiesAdapter;
      }
   }

   private static boolean matchesText(int count60, String text, List list2) {
      BufferUtils.createIntBuffer(1);
      int count61 = GL20.glGetShaderi(count60, 35716);
      if (count61 <= 1) {
         return true;
      } else {
         for (int index = 0; index < list2.size(); index++) {
            String text2 = (String)list2.get(index);
            ShadersManager.applyText("File: " + (index + 1) + " = " + text2);
         }

         String text3 = GL20.glGetShaderInfoLog(count60, count61);
         text3 = SplineCollector.resolveText5(text3, " \n\r\t");
         ShadersManager.applyText("Shader info log: " + text + "\n" + text3);
         return false;
      }
   }

   private static void applyEntity3(Entity entity) {
      double offset = Math.abs(value2 - value5);
      double offset2 = Math.abs(value4 - value);
      double offset3 = Math.abs(value2);
      double offset4 = Math.abs(value4);
      if (offset > 1000.0 || offset2 > 1000.0 || offset3 > 1000000.0 || offset4 > 1000000.0) {
         applyEntityByEntity(entity);
      }
   }

   private static void applyNoneManagerByNoneManager(NoneManager noneManager33) {
      int count60 = GL11.glGetInteger(34852);
      Arrays.fill(noneManager33.getEnabledArray(), false);
      if (noneManager33 == noneManager32) {
         noneManager33.setIntBuffer((IntBuffer)null);
      } else if (noneManager33.getCount() == 0) {
         if (noneManager33 == noneManager25) {
            noneManager33.setIntBuffer(intBuffer);
         } else {
            noneManager33.setIntBuffer(intBuffer7);
         }
      } else {
         String labelText11 = noneManager33.getLabelText2();
         if (labelText11 == null) {
            if (noneManager33 != noneManager25 && noneManager33 != noneManager28 && noneManager33 != noneManager24) {
               noneManager33.setIntBuffer(intBuffer9);
               count42 = count55;
               Arrays.fill(noneManager33.getEnabledArray(), 0, count55, true);
            } else {
               noneManager33.setIntBuffer(intBuffer6);
            }
         } else {
            IntBuffer intBuffer10 = noneManager33.getIntBuffer2();
            int length = labelText11.length();
            count42 = Math.max(count42, length);
            length = Math.min(length, count60);
            noneManager33.setIntBuffer(intBuffer10);
            ((Buffer)intBuffer10).limit(length);

            for (int index = 0; index < length; index++) {
               int count61 = computeCount(noneManager33, labelText11, index);
               intBuffer10.put(index, count61);
            }
         }
      }
   }

   public static void applyTileEntity(TileEntity tileEntity) {
      if (stringHandler42.isActive()) {
         int count60 = computeCount(tileEntity);
         stringHandler42.applyIndex2(count60);
      }
   }

   private static void applyNoneManager(NoneManager noneManager33, NoneManager noneManager34) {
      if (noneManager33.getGlAlphaState() != null) {
         GlStateManager.updateState();
      }

      if (noneManager33.getEnabledManager() != null) {
         GlStateManager.updateState3();
      }

      GlAlphaState glAlphaState = noneManager34.getGlAlphaState();
      if (glAlphaState != null) {
         GlStateManager.applyGlAlphaState(glAlphaState);
      }

      EnabledManager enabledManager = noneManager34.getEnabledManager();
      if (enabledManager != null) {
         GlStateManager.applyEnabledManager2(enabledManager);
      }
   }

   public static boolean isActive12() {
      return !managerAdapter8.isActive() ? managerAdapter8.isActiveAsFlag() : (!managerAdapter.isActive() ? managerAdapter.isActiveAsFlag() : true);
   }

   private static void applyPreUpdateSubscriberArray(PreUpdateSubscriber[] preUpdateSubscriberArray4) {
      if (preUpdateSubscriberArray4 != null) {
         for (int index = 0; index < preUpdateSubscriberArray4.length; index++) {
            PreUpdateSubscriber preUpdateSubscriber2 = preUpdateSubscriberArray4[index];
            GlStateManager.enableState5(33984 + preUpdateSubscriber2.getCountAsIndex());
            int count60 = preUpdateSubscriber2.getCount2();
            int count61 = preUpdateSubscriber2.getCount();
            if (count61 == 3553) {
               GlStateManager.setActiveTexture(count60);
            } else {
               GL11.glBindTexture(count61, count60);
            }
         }
      }
   }

   public static OptionManager[] resolveOptionManagerArrayByOptionManagerArr(OptionManager[] optionManagerArray2) {
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < optionManagerArray2.length; index++) {
         OptionManager optionManager = optionManagerArray2[index];
         if (optionManager.isEnabled() && optionManager.isActiveAsFlag()) {
            list2.add(optionManager);
         }
      }

      return (OptionManager[])list2.toArray(new OptionManager[list2.size()]);
   }

   public static void applyIndex4(int count60) {
      GL11.glDisable(count60);
      updateState();
   }

   private static void applyMovementKeybindProperty(MovementKeybindProperty movementKeybindProperty, String text) {
      if (text == null) {
         text = movementKeybindProperty.getLabelText();
      }

      switch (AdapterIntArrayEntry.countArray3[movementKeybindProperty.ordinal()]) {
         case 1:
            count5 = BuildManager.computeCount(text, 0);
            break;
         case 2:
            enabled15 = BuildManager.matchesText(text, true);
            break;
         case 3:
            enabled37 = BuildManager.matchesText(text, true);
            break;
         case 4:
            factor31 = BuildManager.computeFactor(text, 1.0F);
            break;
         case 5:
            factor5 = BuildManager.computeFactor(text, 1.0F);
            break;
         case 6:
            factor20 = BuildManager.computeFactor(text, 0.125F);
            break;
         case 7:
            enabled30 = BuildManager.matchesText(text, true);
            break;
         case 8:
            managerAdapter2.matchesText(text);
            break;
         case 9:
            managerAdapter8.matchesText(text);
            break;
         case 10:
            labelText7 = text;
            break;
         case 11:
            enabled10 = BuildManager.matchesText(text, true);
            break;
         case 12:
            enabled17 = BuildManager.matchesText(text, true);
            break;
         case 13:
            count7 = BuildManager.computeCount(text, 0);
            break;
         case 14:
            count17 = BuildManager.computeCount(text, 0);
            break;
         case 15:
            count22 = BuildManager.computeCount(text, 0);
            break;
         case 16:
            count29 = BuildManager.computeCount(text, 0);
            break;
         case 17:
            count29 = BuildManager.computeCount(text, 0);
            break;
         case 18:
            count29 = BuildManager.computeCount(text, 0);
            break;
         default:
            throw new IllegalArgumentException("Unknown option: " + movementKeybindProperty);
      }
   }

   public static void applyNoneManager2(NoneManager noneManager33) {
      computeCount3("pre-useProgram");
      if (enabled12) {
         noneManager33 = noneManager25;
      } else if (enabled28) {
         noneManager33 = noneManager27;
      }

      if (noneManager7 != noneManager33) {
         applyNoneManager(noneManager7, noneManager33);
         noneManager7 = noneManager33;
         int count60 = noneManager33.getCount();
         count30 = count60;
         ARBShaderObjects.glUseProgramObjectARB(count60);
         if (computeCount3("useProgram") != 0) {
            noneManager33.setCount(0);
            count60 = noneManager33.getCount();
            count30 = count60;
            ARBShaderObjects.glUseProgramObjectARB(count60);
         }

         altListEntry.applyIndex(count60);
         if (fovObserver != null) {
            fovObserver.applyIndex(count60);
         }

         if (count60 != 0) {
            IntBuffer intBuffer10 = noneManager33.getIntBuffer();
            if (enabled24) {
               applyIntBuffer(intBuffer10);
            }

            count13 = noneManager33.getCount5();
            switch (AdapterIntArrayEntry.countArray[noneManager33.getSharedOptionType().ordinal()]) {
               case 1:
                  applyStringHandler(stringHandler6, 0);
                  applyStringHandler(stringHandler36, 1);
                  applyStringHandler(stringHandler29, 2);
                  applyStringHandler(stringHandler17, 3);
                  applyStringHandler(stringHandler8, enabled33 ? 5 : 4);
                  applyStringHandler(stringHandler37, 4);
                  applyStringHandler(stringHandler4, 4);
                  applyStringHandler(stringHandler12, 5);
                  applyStringHandler(stringHandler45, 6);
                  if (preUpdateSubscriberArray3 != null || enabled34) {
                     applyStringHandler(stringHandler23, 7);
                     applyStringHandler(stringHandler47, 8);
                     applyStringHandler(stringHandler35, 9);
                     applyStringHandler(stringHandler10, 10);
                  }

                  applyStringHandler(stringHandler38, 11);
                  applyStringHandler(stringHandler3, 13);
                  applyStringHandler(stringHandler9, 13);
                  applyStringHandler(stringHandler14, 14);
                  applyStringHandler(stringHandler25, 15);
                  break;
               case 2:
               case 3:
                  applyStringHandler(stringHandler33, 0);
                  applyStringHandler(stringHandler22, 1);
                  applyStringHandler(stringHandler19, 2);
                  applyStringHandler(stringHandler41, 3);
                  applyStringHandler(stringHandler23, 7);
                  applyStringHandler(stringHandler47, 8);
                  applyStringHandler(stringHandler35, 9);
                  applyStringHandler(stringHandler10, 10);
                  applyStringHandler(stringHandler27, 0);
                  applyStringHandler(stringHandler15, 1);
                  applyStringHandler(stringHandler18, 2);
                  applyStringHandler(stringHandler31, 3);
                  applyStringHandler(stringHandler43, 7);
                  applyStringHandler(stringHandler21, 8);
                  applyStringHandler(stringHandler32, 9);
                  applyStringHandler(stringHandler46, 10);
                  applyStringHandler(stringHandler8, enabled33 ? 5 : 4);
                  applyStringHandler(stringHandler37, 4);
                  applyStringHandler(stringHandler4, 4);
                  applyStringHandler(stringHandler12, 5);
                  applyStringHandler(stringHandler28, 6);
                  applyStringHandler(stringHandler45, 6);
                  applyStringHandler(stringHandler38, 11);
                  applyStringHandler(stringHandler11, 12);
                  applyStringHandler(stringHandler3, 13);
                  applyStringHandler(stringHandler9, 13);
                  applyStringHandler(stringHandler14, 14);
                  applyStringHandler(stringHandler25, 15);
                  break;
               case 4:
                  applyStringHandler(stringHandler44, 0);
                  applyStringHandler(stringHandler6, 0);
                  applyStringHandler(stringHandler36, 1);
                  applyStringHandler(stringHandler29, 2);
                  applyStringHandler(stringHandler17, 3);
                  applyStringHandler(stringHandler8, enabled33 ? 5 : 4);
                  applyStringHandler(stringHandler37, 4);
                  applyStringHandler(stringHandler4, 4);
                  applyStringHandler(stringHandler12, 5);
                  if (preUpdateSubscriberArray3 != null) {
                     applyStringHandler(stringHandler23, 7);
                     applyStringHandler(stringHandler47, 8);
                     applyStringHandler(stringHandler35, 9);
                     applyStringHandler(stringHandler10, 10);
                  }

                  applyStringHandler(stringHandler3, 13);
                  applyStringHandler(stringHandler9, 13);
                  applyStringHandler(stringHandler14, 14);
                  applyStringHandler(stringHandler25, 15);
            }

            ItemStack stack = mc.thePlayer != null ? mc.thePlayer.getItemStack() : null;
            Item item = stack != null ? stack.getItem() : null;
            count60 = -1;
            Block block = null;
            if (item != null) {
               count60 = Item.iterableExtensionAdapter.getIndex(item);
               block = (Block)Block.iterableExtensionBridge.getObject3(count60);
               count60 = ShadersItemPropertiesManager.computeCount(count60);
            }

            int count61 = block != null ? block.getIndexOffset1() : 0;
            applyStringHandler(stringHandler26, count60);
            applyStringHandler(stringHandler40, count61);
            applyStringHandler(stringHandler5, enabled40 ? count31 : 0);
            applyFloatArrayHolder(floatArrayHolder4, enabled40 ? factor16 : 0.0F);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder, factor15, factor10, factor41);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder2, factor37, factor4, factor9);
            applyStringHandler(stringHandler16, (int)(timestamp3 % 24000L));
            applyStringHandler(stringHandler30, (int)(timestamp3 / 24000L));
            applyStringHandler(stringHandler2, count48);
            applyStringHandler(stringHandler20, count43);
            applyFloatArrayHolder(floatArrayHolder8, factor11);
            applyFloatArrayHolder(floatArrayHolder2, factor21);
            applyFloatArrayHolder(floatArrayHolder9, factor22);
            applyFloatArrayHolder(floatArrayHolder7, factor26);
            applyFloatArrayHolder(floatArrayHolder14, factor36);
            applyFloatArrayHolder(floatArrayHolder5, (float)count12 / (float)count15);
            applyFloatArrayHolder(floatArrayHolder6, (float)count12);
            applyFloatArrayHolder(floatArrayHolder16, (float)count15);
            applyFloatArrayHolder(floatArrayHolder17, 0.05F);
            applyFloatArrayHolder(floatArrayHolder3, (float)(mc.gameSettings.indexOffset16 * 16));
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder7, factorArray15[0], factorArray15[1], factorArray15[2]);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder3, factorArray12[0], factorArray12[1], factorArray12[2]);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder6, factorArray7[0], factorArray7[1], factorArray7[2]);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder4, factorArray2[0], factorArray2[1], factorArray2[2]);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder5, (float)value5, (float)value6, (float)value);
            applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder8, (float)value2, (float)value3, (float)value4);
            applyShaderUniformFloat(shaderUniformFloat6, false, floatBuffer5);
            applyShaderUniformFloat(shaderUniformFloat8, false, floatBuffer3);
            applyShaderUniformFloat(shaderUniformFloat5, false, floatBuffer7);
            applyShaderUniformFloat(shaderUniformFloat, false, floatBuffer10);
            applyShaderUniformFloat(shaderUniformFloat9, false, floatBuffer);
            applyShaderUniformFloat(shaderUniformFloat2, false, floatBuffer6);
            if (count53 > 0) {
               applyShaderUniformFloat(shaderUniformFloat4, false, floatBuffer4);
               applyShaderUniformFloat(shaderUniformFloat10, false, floatBuffer9);
               applyShaderUniformFloat(shaderUniformFloat3, false, floatBuffer11);
               applyShaderUniformFloat(shaderUniformFloat7, false, floatBuffer8);
            }

            applyFloatArrayHolder(floatArrayHolder13, factor3);
            applyFloatArrayHolder(floatArrayHolder12, factor7);
            applyExtendedStringHandler(extendedStringHandler4, count50 & 65535, count50 >> 16);
            applyExtendedStringHandler(extendedStringHandler3, Math.round(factor), Math.round(factor6));
            applyExtendedStringHandler(extendedStringHandler2, countArray2[0], countArray2[1]);
            applyStringHandler(stringHandler39, count41);
            applyStringHandler(stringHandler48, count58);
            applyFloatArrayHolder(floatArrayHolder11, factor27);
            applyFloatArrayHolder(floatArrayHolder, factor12);
            applyFloatArrayHolder(floatArrayHolder15, mc.gameSettings.scaleFactor100);
            applyStringHandler(stringHandler7, ((mc.gameSettings.state11) ? 1 : 0));
            applyFloatArrayHolder(floatArrayHolder10, factor24);
            applyExtendedStringHandler(extendedStringHandler, count4, count38);
            if (fovObserver != null) {
               fovObserver.updateState2();
            }

            computeCount3("end useProgram");
         }
      }
   }

   public static void updateState18() {
      applyNoneManager2(noneManager30);
   }

   private static String resolveText(int count60, String text) {
      StringBuilder builder = new StringBuilder();
      if (count60 == 1286) {
         int count61 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         String text2 = resolveText(count61);
         text2 = ", fbStatus: " + count61 + " (" + text2 + ")";
         builder.append(text2);
      }

      String labelText11 = noneManager7.getLabelText();
      if (labelText11.isEmpty()) {
         labelText11 = "none";
      }

      builder.append(", program: " + labelText11);
      NoneManager noneManager33 = resolveNoneManager(count30);
      if (noneManager33 != noneManager7) {
         labelText11 = noneManager33.getLabelText();
         if (labelText11.isEmpty()) {
            labelText11 = "none";
         }

         builder.append(" (" + labelText11 + ")");
      }

      if (text.equals("setDrawBuffers")) {
         builder.append(", drawBuffers: " + noneManager7.getLabelText2());
      }

      return builder.toString();
   }

   public static World getWorld() {
      return world;
   }

   public static void applyIndex6(int count60) {
      GL11.glEnable(count60);
      if (count60 == 3553) {
         updateState2();
      } else if (count60 == 2912) {
         updateState62();
      }
   }

   private static void applyExtendedStringHandler(ExtendedStringHandler extendedStringHandler5, int count60, int count61) {
      extendedStringHandler5.applyIndex(count60, count61);
   }

   private static ByteBuffer resolveByteBuffer(int count60) {
      ByteBuffer buffer = byteBuffer;
      int count61 = buffer.limit();
      ((Buffer)buffer).position(count61).limit(count61 + count60);
      return buffer.slice();
   }

   public static boolean isActive14() {
      return !managerAdapter12.isActive2();
   }

   public static void applyFloatByFloat(float factor42) {
      Entity renderViewEntity = mc.getRenderViewEntity();
      double offset = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor42;
      double offset2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor42;
      double offset3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor42;
      applyEntity3(renderViewEntity);
      value2 = offset - (double)count3;
      value3 = offset2;
      value4 = offset3 - (double)count46;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)floatBuffer10).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer).position(0), (FloatBuffer)((Buffer)floatBuffer10).position(0), factorArray9, factorArray13
      );
      ((Buffer)floatBuffer10).position(0);
      ((Buffer)floatBuffer).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)floatBuffer5).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer3).position(0), (FloatBuffer)((Buffer)floatBuffer5).position(0), factorArray8, factorArray6
      );
      ((Buffer)floatBuffer5).position(0);
      ((Buffer)floatBuffer3).position(0);
      computeCount3("setCamera");
   }

   public static IntBuffer resolveIntBuffer(int count60) {
      ByteBuffer buffer = byteBuffer;
      int count61 = buffer.limit();
      ((Buffer)buffer).position(count61).limit(count61 + count60 * 4);
      return buffer.asIntBuffer();
   }

   private static void applyNoneManager(NoneManager noneManager33, String text, String text2, String text3) {
      computeCount3("pre setupProgram");
      int count60 = ARBShaderObjects.glCreateProgramObjectARB();
      computeCount3("create");
      if (count60 != 0) {
         enabled20 = false;
         enabled25 = false;
         enabled41 = false;
         int count61 = computeCount(noneManager33, text);
         int count62 = computeCount2(noneManager33, text2);
         int count63 = computeCountByNoneManager(noneManager33, text3);
         computeCount3("create");
         if (count61 == 0 && count62 == 0 && count63 == 0) {
            ARBShaderObjects.glDeleteObjectARB(count60);
            noneManager33.updateState();
         } else {
            if (count61 != 0) {
               ARBShaderObjects.glAttachObjectARB(count60, count61);
               computeCount3("attach");
            }

            if (count62 != 0) {
               ARBShaderObjects.glAttachObjectARB(count60, count62);
               computeCount3("attach");
               if (enabled22) {
                  ARBGeometryShader4.glProgramParameteriARB(count60, 36315, 4);
                  ARBGeometryShader4.glProgramParameteriARB(count60, 36316, 5);
                  ARBGeometryShader4.glProgramParameteriARB(count60, 36314, count26);
                  computeCount3("arbGeometryShader4");
               }

               enabled23 = true;
            }

            if (count63 != 0) {
               ARBShaderObjects.glAttachObjectARB(count60, count63);
               computeCount3("attach");
            }

            if (enabled20) {
               ARBVertexShader.glBindAttribLocationARB(count60, count39, "mc_Entity");
               computeCount3("mc_Entity");
            }

            if (enabled25) {
               ARBVertexShader.glBindAttribLocationARB(count60, count34, "mc_midTexCoord");
               computeCount3("mc_midTexCoord");
            }

            if (enabled41) {
               ARBVertexShader.glBindAttribLocationARB(count60, count11, "at_tangent");
               computeCount3("at_tangent");
            }

            ARBShaderObjects.glLinkProgramARB(count60);
            if (GL20.glGetProgrami(count60, 35714) != 1) {
               ShadersManager.applyTextByText("Error linking program: " + count60 + " (" + noneManager33.getLabelText() + ")");
            }

            matchesText(count60, noneManager33.getLabelText());
            if (count61 != 0) {
               ARBShaderObjects.glDetachObjectARB(count60, count61);
               ARBShaderObjects.glDeleteObjectARB(count61);
            }

            if (count62 != 0) {
               ARBShaderObjects.glDetachObjectARB(count60, count62);
               ARBShaderObjects.glDeleteObjectARB(count62);
            }

            if (count63 != 0) {
               ARBShaderObjects.glDetachObjectARB(count60, count63);
               ARBShaderObjects.glDeleteObjectARB(count63);
            }

            noneManager33.setCount(count60);
            noneManager33.setCount3(count60);
            applyNoneManager2(noneManager33);
            ARBShaderObjects.glValidateProgramARB(count60);
            applyNoneManager2(noneManager6);
            matchesText(count60, noneManager33.getLabelText());
            if (GL20.glGetProgrami(count60, 35715) != 1) {
               String text4 = "\"";
               applyTextByText("[Shaders] Error: Invalid program " + text4 + noneManager33.getLabelText() + text4);
               ARBShaderObjects.glDeleteObjectARB(count60);
               noneManager33.updateState();
            }
         }
      }
   }

   public static void applyItemStackByItemStack(ItemStack stack) {
      enabled3 = matchesItemStack(stack);
   }

   private static boolean matchesText(int count60, String text) {
      IntBuffer buffer = BufferUtils.createIntBuffer(1);
      ARBShaderObjects.glGetObjectParameterARB(count60, 35716, buffer);
      int count61 = buffer.get();
      if (count61 > 1) {
         ByteBuffer buffer2 = BufferUtils.createByteBuffer(count61);
         ((Buffer)buffer).flip();
         ARBShaderObjects.glGetInfoLogARB(count60, buffer, buffer2);
         byte[] byteValueArray = new byte[count61];
         buffer2.get(byteValueArray);
         if (byteValueArray[count61 - 1] == 0) {
            byteValueArray[count61 - 1] = 10;
         }

         String text2 = new String(byteValueArray, Charsets.US_ASCII);
         String text3 = SplineCollector.resolveText5(text2, " \n\r\t");
         ShadersManager.applyText("Info log: " + text + "\n" + text3);
         return false;
      } else {
         return true;
      }
   }

   public static void applyIndex(int count60, int count61, int count62, int count63) {
      GlStateManager.applyFlag(true, true, true, true);
      if (enabled12) {
         GL11.glViewport(0, 0, count10, count6);
      } else {
         GL11.glViewport(0, 0, count12, count15);
         EXTFramebufferObject.glBindFramebufferEXT(36160, count33);
         enabled24 = true;
         GlStateManager.enableLighting();
         GlStateManager.disableDepth();
         applyIntBuffer(intBuffer);
         applyNoneManager2(noneManager5);
         computeCount3("beginRenderPass");
      }
   }

   private static int computeCount2(NoneManager noneManager33, String text) {
      int count60 = ARBShaderObjects.glCreateShaderObjectARB(36313);
      if (count60 == 0) {
         return 0;
      } else {
         StringBuilder builder = new StringBuilder(131072);

         BufferedReader reader;
         try {
            reader = new BufferedReader(resolveReader(text));
         } catch (Exception exception) {
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         OptionManager[] optionManagerArray2 = resolveOptionManagerArrayByOptionManagerArr(optionManagerArray);
         ArrayList list2 = new ArrayList();
         enabled22 = false;
         count26 = 3;

         try {
            reader = SlidersManager.resolveBufferedReader(reader, text, preUpdateFilter, 0, list2, 0);
            AimAggregator aimAggregator = new AimAggregator();

            while (true) {
               String line = reader.readLine();
               if (line == null) {
                  reader.close();
                  break;
               }

               line = resolveText(line, optionManagerArray2);
               builder.append(line).append('\n');
               if (aimAggregator.matchesText(line)) {
                  AngleFactory angleFactory = SecondaryCustom.resolveAngleFactory(line);
                  if (angleFactory != null) {
                     if (angleFactory.matchesText9("GL_ARB_geometry_shader4")) {
                        String text2 = BuildManager.resolveTextByText(angleFactory.getLabelText2());
                        if (text2.equals("enable") || text2.equals("require") || text2.equals("warn")) {
                           enabled22 = true;
                        }
                     }

                     if (angleFactory.matchesText4("maxVerticesOut")) {
                        count26 = angleFactory.getCountAsIndex();
                     }
                  }
               }
            }
         } catch (Exception exception2) {
            ShadersManager.applyTextByText("Couldn't read " + text + "!");
            exception2.printStackTrace();
            ARBShaderObjects.glDeleteObjectARB(count60);
            return 0;
         }

         if (enabled) {
            applyText(text, builder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(count60, builder);
         ARBShaderObjects.glCompileShaderARB(count60);
         if (GL20.glGetShaderi(count60, 35713) != 1) {
            ShadersManager.applyTextByText("Error compiling geometry shader: " + text);
         }

         matchesText(count60, text, list2);
         return count60;
      }
   }

   public static void applyFloatByFloat(float posX, float posX2, float posY, float posZ) {
      if (enabled31 && !enabled12) {
         auxiliaryStringHandler.applyFloat(posX, posX2, posY, posZ);
      }
   }

   public static void applyText2(String text) {
      labelText7 = text;
      properties2.setProperty(MovementKeybindProperty.SHADER_PACK.getLabelText3(), text);
      updateState63();
   }

   public static int getCountAsIndex() {
      return countArray7[count23 * 2 + 1];
   }

   public static void setEnabled29(boolean enabled46) {
      enabled29 = enabled46;
   }

   public static void applyMinecraftByMinecraft(Minecraft mc2, float factor42, long value7) {
      computeCount3("pre beginRender");
      applyWorldByWorld(mc.theWorld);
      mc = mc2;
      mc.uNKNOWNManager.applyTextByText("init");
      entityRenderer = mc.entityRenderer;
      if (!enabled45) {
         try {
            updateState29();
         } catch (IllegalStateException illegalStateException) {
            if (BuildManager.resolveTextByText(illegalStateException.getMessage()).equals("Function is not supported")) {
               applyTextByText("[Shaders] Error: " + illegalStateException.getMessage());
               illegalStateException.printStackTrace();
               applyText2("OFF");
               return;
            }
         }
      }

      if (mc.displayWidth != count || mc.displayHeight != count9) {
         updateState7();
      }

      if (enabled42) {
         updateState78();
      }

      timestamp3 = mc.theWorld.getTimestampAsTime();
      timestamp5 = (timestamp3 - timestamp6) % 24000L;
      if (timestamp5 < 0L) {
         timestamp5 += 24000L;
      }

      timestamp6 = timestamp3;
      count48 = mc.theWorld.getCount2();
      count43++;
      if (count43 >= 720720) {
         count43 = 0;
      }

      timestamp2 = System.currentTimeMillis();
      if (timestamp4 == 0L) {
         timestamp4 = timestamp2;
      }

      timestamp = timestamp2 - timestamp4;
      timestamp4 = timestamp2;
      factor11 = (float)timestamp / 1000.0F;
      factor21 = factor21 + factor11;
      factor21 %= 3600.0F;
      factor36 = mc2.theWorld.getFloat6(factor42);
      float factor43 = (float)timestamp * 0.01F;
      factor43 = (float)Math.exp(Math.log(0.5) * (double)factor43 / (double)(factor3 < factor36 ? factor25 : factor13));
      factor3 = factor3 * factor43 + factor36 * (1.0F - factor43);
      Entity renderViewEntity = mc.getRenderViewEntity();
      if (renderViewEntity != null) {
         enabled36 = renderViewEntity instanceof EntityLivingBase && ((EntityLivingBase)renderViewEntity).isState5();
         factor7 = (float)renderViewEntity.posY * factor42 + (float)renderViewEntity.lastTickPosY * (1.0F - factor42);
         count50 = renderViewEntity.computeCount(factor42);
         factor43 = (float)timestamp * 0.01F;
         factor43 = (float)Math.exp(Math.log(0.5) * (double)factor43 / (double)factor33);
         factor = factor * factor43 + (float)(count50 & 65535) * (1.0F - factor43);
         factor6 = factor6 * factor43 + (float)(count50 >> 16) * (1.0F - factor43);
         Material material = AuxiliaryIntBufferEntry.resolveBlock(mc.theWorld, renderViewEntity, factor42).getMaterial();
         if (material == Material.coreItemEntry1) {
            count58 = 1;
         } else if (material == Material.material29) {
            count58 = 2;
         } else {
            count58 = 0;
         }

         if (mc.thePlayer != null) {
            factor27 = 0.0F;
            if (mc.thePlayer.checkPotion(Potion.potion31)) {
               factor27 = BuildManager.getMc().entityRenderer.computeFactor(mc.thePlayer, factor42);
            }

            factor12 = 0.0F;
            if (mc.thePlayer.checkPotion(Potion.potion16) && !((Module)ClientGlobals.moduleManager.antiBlind).isToggled()) {
               factor12 = BuildManager.computeFactor((float)mc.thePlayer.getPotionEffect(Potion.potion16).getIndexOffset() / 20.0F, 0.0F, 1.0F);
            }
         }

         Vec3 vec = mc.theWorld.resolveVec3(renderViewEntity, factor42);
         Vec3 vec2 = LoadingManager.resolveVec4(vec, world, renderViewEntity, factor42);
         factor37 = (float)vec2.xCoord;
         factor4 = (float)vec2.yCoord;
         factor9 = (float)vec2.zCoord;
      }

      enabled31 = true;
      enabled27 = false;
      enabled12 = false;
      enabled43 = false;
      enabled32 = false;
      enabled5 = false;
      enabled2 = false;
      updateState13();
      value5 = value2;
      value6 = value3;
      value = value4;
      ((Buffer)floatBuffer7).position(0);
      ((Buffer)floatBuffer10).position(0);
      floatBuffer7.put(floatBuffer10);
      ((Buffer)floatBuffer7).position(0);
      ((Buffer)floatBuffer10).position(0);
      ((Buffer)floatBuffer6).position(0);
      ((Buffer)floatBuffer5).position(0);
      floatBuffer6.put(floatBuffer5);
      ((Buffer)floatBuffer6).position(0);
      ((Buffer)floatBuffer5).position(0);
      computeCount3("beginRender");
      RotationBridge.applyEntityRenderer(entityRenderer, 0, factor42, value7);
      mc.uNKNOWNManager.updateState();
      EXTFramebufferObject.glBindFramebufferEXT(36160, count33);

      for (int index = 0; index < count55; index++) {
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index, 3553, intBufferEntry.computeCount(index), 0);
      }

      computeCount3("end beginRender");
   }

   public static void applyIndex7(int count60) {
      count31 = count60;
      if (enabled40) {
         applyStringHandler(stringHandler5, count60);
      }
   }

   public static String getText() {
      return preUpdateFilter == null ? null : (preUpdateFilter instanceof OFFListener ? null : preUpdateFilter.getName());
   }

   public static void applyEntity7(Entity entity) {
      if (enabled31) {
         applyNoneManager2(noneManager29);
         applyEntity5(entity);
      }
   }

   private static boolean matchesItemStack(ItemStack stack) {
      if (stack == null) {
         return false;
      } else {
         Item item = stack.getItem();
         if (item == null) {
            return false;
         } else if (!(item instanceof ItemBlock)) {
            return false;
         } else {
            Block block = ((ItemBlock)item).getBlock();
            return block == null ? false : block.processAction3() == BlockSolidType.TRANSLUCENT;
         }
      }
   }

   private static void applyStringHandler(StringHandler stringHandler49, int count60) {
      stringHandler49.applyIndex2(count60);
   }

   private static PreUpdateSubscriber resolvePreUpdateSubscriber3(int count60, String text) {
      text = text.trim();
      if (text.indexOf(46) < 0) {
         text = text + ".png";
      }

      try {
         String text2 = "shaders/" + SplineCollector.resolveText2(text, "/");
         InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text2);
         if (resourceAsStream == null) {
            ShadersManager.applyText3("Texture not found: " + text);
            return null;
         } else {
            IOUtils.closeQuietly(resourceAsStream);
            SmoothRegistry smoothRegistry = new SmoothRegistry(text2);
            smoothRegistry.applyIResourceManager(mc.getIResourceManager());
            return new CurveAggregator(count60, text2, smoothRegistry);
         }
      } catch (IOException ioException) {
         ShadersManager.applyText3("Error loading texture: " + text);
         ShadersManager.applyText3("" + ioException.getClass().getName() + ": " + ioException.getMessage());
         return null;
      }
   }

   private static String resolveText(String text, OptionManager[] optionManagerArray2) {
      if (optionManagerArray2 != null && optionManagerArray2.length > 0) {
         for (int index = 0; index < optionManagerArray2.length; index++) {
            OptionManager optionManager = optionManagerArray2[index];
            if (optionManager.matchesText(text)) {
               text = optionManager.getText2();
               break;
            }
         }

         return text;
      } else {
         return text;
      }
   }

   public static boolean isActive15() {
      return managerAdapter9.isActiveAsFlag();
   }

   public static void forName() {
      try {
         Class.forName("shadersmod.transform.SMCClassTransformer");
      } catch (Throwable throwable) {
         return;
      }

      throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
   }

   public static void applyFloat2(float factor42) {
      Entity renderViewEntity = mc.getRenderViewEntity();
      double offset = renderViewEntity.lastTickPosX + (renderViewEntity.posX - renderViewEntity.lastTickPosX) * (double)factor42;
      double offset2 = renderViewEntity.lastTickPosY + (renderViewEntity.posY - renderViewEntity.lastTickPosY) * (double)factor42;
      double offset3 = renderViewEntity.lastTickPosZ + (renderViewEntity.posZ - renderViewEntity.lastTickPosZ) * (double)factor42;
      applyEntity3(renderViewEntity);
      value2 = offset - (double)count3;
      value3 = offset2;
      value4 = offset3 - (double)count46;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)floatBuffer10).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer).position(0), (FloatBuffer)((Buffer)floatBuffer10).position(0), factorArray9, factorArray13
      );
      ((Buffer)floatBuffer10).position(0);
      ((Buffer)floatBuffer).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)floatBuffer5).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer3).position(0), (FloatBuffer)((Buffer)floatBuffer5).position(0), factorArray8, factorArray6
      );
      ((Buffer)floatBuffer5).position(0);
      ((Buffer)floatBuffer3).position(0);
      GL11.glViewport(0, 0, count10, count6);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      if (enabled4) {
         GL11.glOrtho((double)(-factor2), (double)factor2, (double)(-factor2), (double)factor2, 0.05F, 256.0);
      } else {
         GLU.gluPerspective(factor30, (float)count10 / (float)count6, 0.05F, 256.0F);
      }

      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -100.0F);
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      factor14 = mc.theWorld.computeFactor(factor42);
      factor22 = factor14 < 0.75F ? factor14 + 0.25F : factor14 - 0.75F;
      float factor43 = factor14 * -360.0F;
      float factor44 = factor17 > 0.0F ? factor43 % factor17 - factor17 * 0.5F : 0.0F;
      if ((double)factor22 <= 0.5) {
         GL11.glRotatef(factor43 - factor44, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(factor35, 1.0F, 0.0F, 0.0F);
         factor26 = factor22;
      } else {
         GL11.glRotatef(factor43 + 180.0F - factor44, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(factor35, 1.0F, 0.0F, 0.0F);
         factor26 = factor22 - 0.5F;
      }

      if (enabled4) {
         factor43 = factor19;
         factor44 = factor43 / 2.0F;
         GL11.glTranslatef((float)offset % factor43 - factor44, (float)offset2 % factor43 - factor44, (float)offset3 % factor43 - factor44);
      }

      factor43 = factor22 * (float) (Math.PI * 2);
      factor44 = (float)Math.cos((double)factor43);
      factor43 = (float)Math.sin((double)factor43);
      float factor45 = factor35 * (float) (Math.PI * 2);
      float factor46 = factor43 * (float)Math.cos((double)factor45);
      factor43 *= (float)Math.sin((double)factor45);
      if ((double)factor22 > 0.5) {
         factor44 = -factor44;
         factor46 = -factor46;
         factor43 = -factor43;
      }

      factorArray10[0] = factor44;
      factorArray10[1] = factor46;
      factorArray10[2] = factor43;
      factorArray10[3] = 0.0F;
      GL11.glGetFloat(2983, (FloatBuffer)((Buffer)floatBuffer4).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer9).position(0), (FloatBuffer)((Buffer)floatBuffer4).position(0), factorArray4, factorArray16
      );
      ((Buffer)floatBuffer4).position(0);
      ((Buffer)floatBuffer9).position(0);
      GL11.glGetFloat(2982, (FloatBuffer)((Buffer)floatBuffer11).position(0));
      AimAccumulator.applyFloatBuffer(
         (FloatBuffer)((Buffer)floatBuffer8).position(0), (FloatBuffer)((Buffer)floatBuffer11).position(0), factorArray17, factorArray
      );
      ((Buffer)floatBuffer11).position(0);
      ((Buffer)floatBuffer8).position(0);
      applyShaderUniformFloat(shaderUniformFloat, false, floatBuffer10);
      applyShaderUniformFloat(shaderUniformFloat9, false, floatBuffer);
      applyShaderUniformFloat(shaderUniformFloat5, false, floatBuffer7);
      applyShaderUniformFloat(shaderUniformFloat6, false, floatBuffer5);
      applyShaderUniformFloat(shaderUniformFloat8, false, floatBuffer3);
      applyShaderUniformFloat(shaderUniformFloat2, false, floatBuffer6);
      applyShaderUniformFloat(shaderUniformFloat4, false, floatBuffer4);
      applyShaderUniformFloat(shaderUniformFloat10, false, floatBuffer9);
      applyShaderUniformFloat(shaderUniformFloat3, false, floatBuffer11);
      applyShaderUniformFloat(shaderUniformFloat7, false, floatBuffer8);
      mc.gameSettings.indexOffset64 = 1;
      computeCount3("setCamera");
   }

   private static int computeCount(int count60, String text) {
      if (count60 == 0) {
         if (text.equals("texture")) {
            return 0;
         }

         if (text.equals("lightmap")) {
            return 1;
         }

         if (text.equals("normals")) {
            return 2;
         }

         if (text.equals("specular")) {
            return 3;
         }

         if (text.equals("shadowtex0") || text.equals("watershadow")) {
            return 4;
         }

         if (text.equals("shadow")) {
            return enabled33 ? 5 : 4;
         }

         if (text.equals("shadowtex1")) {
            return 5;
         }

         if (text.equals("depthtex0")) {
            return 6;
         }

         if (text.equals("gaux1")) {
            return 7;
         }

         if (text.equals("gaux2")) {
            return 8;
         }

         if (text.equals("gaux3")) {
            return 9;
         }

         if (text.equals("gaux4")) {
            return 10;
         }

         if (text.equals("depthtex1")) {
            return 12;
         }

         if (text.equals("shadowcolor0") || text.equals("shadowcolor")) {
            return 13;
         }

         if (text.equals("shadowcolor1")) {
            return 14;
         }

         if (text.equals("noisetex")) {
            return 15;
         }
      }

      if (count60 == 1 || count60 == 2) {
         if (text.equals("colortex0") || text.equals("colortex0")) {
            return 0;
         }

         if (text.equals("colortex1") || text.equals("gdepth")) {
            return 1;
         }

         if (text.equals("colortex2") || text.equals("gnormal")) {
            return 2;
         }

         if (text.equals("colortex3") || text.equals("composite")) {
            return 3;
         }

         if (text.equals("shadowtex0") || text.equals("watershadow")) {
            return 4;
         }

         if (text.equals("shadow")) {
            return enabled33 ? 5 : 4;
         }

         if (text.equals("shadowtex1")) {
            return 5;
         }

         if (text.equals("depthtex0") || text.equals("gdepthtex")) {
            return 6;
         }

         if (text.equals("colortex4") || text.equals("gaux1")) {
            return 7;
         }

         if (text.equals("colortex5") || text.equals("gaux2")) {
            return 8;
         }

         if (text.equals("colortex6") || text.equals("gaux3")) {
            return 9;
         }

         if (text.equals("colortex7") || text.equals("gaux4")) {
            return 10;
         }

         if (text.equals("depthtex1")) {
            return 11;
         }

         if (text.equals("depthtex2")) {
            return 12;
         }

         if (text.equals("shadowcolor0") || text.equals("shadowcolor")) {
            return 13;
         }

         if (text.equals("shadowcolor1")) {
            return 14;
         }

         if (text.equals("noisetex")) {
            return 15;
         }
      }

      return -1;
   }

   private static OptionManager[] getOptionManagerArrayAsC47MeArray() {
      try {
         String[] textArray = standardListEntry.getTextArray();
         OptionManager[] optionManagerArray2 = SlidersManager.resolveOptionManagerArray(preUpdateFilter, textArray, list);
         Properties properties3 = resolveProperties(preUpdateFilter);

         for (int index = 0; index < optionManagerArray2.length; index++) {
            OptionManager optionManager = optionManagerArray2[index];
            String property = properties3.getProperty(optionManager.getLabelText());
            if (property != null) {
               optionManager.updateState2();
               if (!optionManager.matchesText3(property)) {
                  BuildManager.applyText3("[Shaders] Invalid value, option: " + optionManager.getLabelText() + ", value: " + property);
               }
            }
         }

         return optionManagerArray2;
      } catch (IOException ioException) {
         BuildManager.applyText3("[Shaders] Error reading configuration for " + preUpdateFilter.getName());
         ioException.printStackTrace();
         return null;
      }
   }

   public static InputStream resolveInputStream(String text) {
      return preUpdateFilter == null ? null : preUpdateFilter.getResourceAsStream(text);
   }

   public static void applyIndexByIndex(int count60, int count61) {
      GL11.glFogi(count60, count61);
      if (count60 == 2917) {
         count31 = count61;
         if (enabled40) {
            applyStringHandler(stringHandler5, count31);
         }
      }
   }

   public static void applyBlock(Block block) {
      count23++;
      int count60 = block.processAction13();
      countArray7[count23 * 2] = Block.iterableExtensionBridge.getIndex(block) & 65535 | count60 << 16;
      countArray7[count23 * 2 + 1] = 0;
   }

   public static void applyFlagByFlag(boolean flag, boolean flag2) {
      enabled43 = flag;
      enabled32 = flag2;
   }

   public static void applyTileEntityByTileEntity(TileEntity tileEntity) {
      if (enabled31) {
         computeCount3("nextBlockEntity");
         applyNoneManager2(noneManager18);
         applyTileEntity(tileEntity);
      }
   }

   public static int computeCount(String text, int count60) {
      String text2 = text != null ? "screen." + text : "screen";
      if (map2 == null) {
         return count60;
      } else {
         FovValidator fovValidator = (FovValidator)map2.get(text2);
         return fovValidator == null ? count60 : fovValidator.getCount();
      }
   }

   public static void updateState15() {
      updateState38();
   }

   private static IntBuffer[] resolveIntBufferArray(int count60, int count61) {
      IntBuffer[] bufferArray = new IntBuffer[count60];

      for (int index = 0; index < count60; index++) {
         bufferArray[index] = resolveIntBuffer(count61);
      }

      return bufferArray;
   }

   public static int computeCount3(String text) {
      int error = GlStateManager.getError();
      if (error != 0 && SuppressedManager.checkIndex(error)) {
         String text2 = BuildManager.resolveText(error);
         String text3 = resolveText(error, text);
         ShadersManager.applyTextByText(String.format("OpenGL error: %s (%s)%s, at: %s", error, text2, text3, text));
         if (BuildManager.isActive20() && SupplementalMapEntry.matchesText("ShowGlErrorShaders", 10000L)) {
            applyText(StatCollector.getText("of.message.openglError", new Object[]{error, text2}));
         }
      }

      return error;
   }

   private static void applyTextByText(String text) {
      ShadersManager.applyTextByText(text);
      mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(new ChatComponentText(text));
   }

   public static boolean isEnabled29() {
      return enabled29;
   }

   public static int computeCountByIndex(int count60) {
      countArray7[count23 * 2 + 1] = countArray7[count23 * 2 + 1] & -65536 | count60 & 65535;
      return count60;
   }

   private static IntBuffer resolveIntBuffer(IntBuffer buffer) {
      int count60 = buffer.limit();

      for (int index = buffer.position(); index < count60; index++) {
         buffer.put(index, 0);
      }

      return buffer;
   }

   public static void applyEntity5(Entity entity) {
      if (stringHandler13.isActive()) {
         int count60 = TargetWatcher.computeCount(entity);
         int count61 = ShadersEntityPropertiesManager.computeCount(count60);
         if (count61 >= 0) {
            count60 = count61;
         }

         stringHandler13.applyIndex2(count60);
      }
   }

   public static boolean matchesText(String text) {
      if (text == null) {
         return false;
      } else if (text.length() <= 0) {
         return false;
      } else {
         int count60 = text.lastIndexOf("/");
         if (count60 >= 0) {
            text = text.substring(count60 + 1);
         }

         return resolveNoneManager(text) != null;
      }
   }

   private static void applyOptionManagerArray(OptionManager[] optionManagerArray2, PreUpdateFilter preUpdateFilter2) {
      PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
      if (optionManagerArray != null) {
         for (int index = 0; index < optionManagerArray2.length; index++) {
            OptionManager optionManager = optionManagerArray2[index];
            if (optionManager.isActiveAsFlag() && optionManager.isEnabled()) {
               propertiesAdapter.setProperty(optionManager.getLabelText(), optionManager.getLabelText2());
            }
         }
      }

      try {
         applyPreUpdateFilter(preUpdateFilter2, propertiesAdapter);
      } catch (IOException ioException) {
         BuildManager.applyText3("[Shaders] Error saving configuration for " + preUpdateFilter.getName());
         ioException.printStackTrace();
      }
   }

   public static void applyIndex5(int count60) {
      GL11.glDisable(count60);
      updateState50();
   }

   public static void applyFloat(float factor42) {
      factor16 = factor42;
      if (enabled40) {
         applyFloatArrayHolder(floatArrayHolder4, factor42);
      }
   }

   private static void applyFloatArrayHolder(FloatArrayHolder floatArrayHolder18, float factor42) {
      floatArrayHolder18.applyFloat(factor42);
   }

   private static void applyPreUpdateSubscriberArrayByPreUpdateSubscri(PreUpdateSubscriber[] preUpdateSubscriberArray4) {
      if (preUpdateSubscriberArray4 != null) {
         for (int index = 0; index < preUpdateSubscriberArray4.length; index++) {
            preUpdateSubscriberArray4[index].glDeleteTextures();
         }
      }
   }

   public static void applyMinecraftByMinecraft(Minecraft mc2) {
      forName();
      mc = mc2;
      contextCapabilities = GLContext.getCapabilities();
      labelText4 = GL11.glGetString(7938);
      labelText10 = GL11.glGetString(7936);
      labelText6 = GL11.glGetString(7937);
      ShadersManager.applyText("OpenGL Version: " + labelText4);
      ShadersManager.applyText("Vendor:  " + labelText10);
      ShadersManager.applyText("Renderer: " + labelText6);
      ShadersManager.applyText(
         "Capabilities: "
            + (contextCapabilities.OpenGL20 ? " 2.0 " : " - ")
            + (contextCapabilities.OpenGL21 ? " 2.1 " : " - ")
            + (contextCapabilities.OpenGL30 ? " 3.0 " : " - ")
            + (contextCapabilities.OpenGL32 ? " 3.2 " : " - ")
            + (contextCapabilities.OpenGL40 ? " 4.0 " : " - ")
      );
      ShadersManager.applyText("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
      ShadersManager.applyText("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
      ShadersManager.applyText("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
      enabled8 = contextCapabilities.OpenGL30;
      updateState46();
   }

   public static boolean isActive13() {
      return !managerAdapter2.isActive() ? managerAdapter2.isActiveAsFlag() : (!managerAdapter3.isActive() ? managerAdapter3.isActiveAsFlag() : true);
   }

   public static void updateState17() {
      GL11.glRotatef(factor35 * 1.0F, 0.0F, 0.0F, 1.0F);
      computeCount3("preCelestialRotate");
   }

   private static int computeCount2(String text) {
      text = text.trim();

      for (int index = 0; index < stringArray.length; index++) {
         String text2 = stringArray[index];
         if (text.equals(text2)) {
            return countArray[index];
         }
      }

      return 0;
   }

   private static boolean matchesNoneManager(NoneManager noneManager33) {
      boolean flag = false;
      Boolean[] itemArray = noneManager33.getItemArray();

      for (int index = 0; index < count55; index++) {
         if (BuildManager.booleanValue(itemArray[index])) {
            intBufferEntry.applyIndex(index);
            flag = true;
         }
      }

      return flag;
   }

   public static boolean matchesGameSettings(GameSettings gameSettings) {
      if (!enabled19) {
         return true;
      } else {
         computeCount3("shouldRenderClouds");
         return enabled12 ? enabled30 : gameSettings.indexOffset33 > 0;
      }
   }

   private static PreUpdateSubscriber resolvePreUpdateSubscriberByText(int count60, String text) {
      NetherManager netherManager = new NetherManager("Shaders");
      String[] textArray = BuildManager.resolveTextArray(text, " ");
      ArrayDeque arrayDeque = new ArrayDeque<>(Arrays.asList(textArray));
      String text2 = (String)arrayDeque.poll();
      RenderStateProperty renderStateProperty = (RenderStateProperty)netherManager.resolveEnum((String)arrayDeque.poll(), RenderStateProperty.values(), "texture type");
      if (renderStateProperty == null) {
         ShadersManager.applyText3("Invalid raw texture type: " + text);
         return null;
      } else {
         NativeOptionType nativeOptionType = (NativeOptionType)netherManager.resolveEnum((String)arrayDeque.poll(), NativeOptionType.values(), "internal format");
         if (nativeOptionType == null) {
            ShadersManager.applyText3("Invalid raw texture internal format: " + text);
            return null;
         } else {
            int count61 = 0;
            int count62 = 0;
            int count63;
            switch (AdapterIntArrayEntry.countArray4[renderStateProperty.ordinal()]) {
               case 1:
                  count63 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  break;
               case 2:
                  count63 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  count61 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  break;
               case 3:
                  count63 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  count61 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  count62 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  break;
               case 4:
                  count63 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  count61 = netherManager.computeCount((String)arrayDeque.poll(), -1);
                  break;
               default:
                  ShadersManager.applyText3("Invalid raw texture type: " + renderStateProperty);
                  return null;
            }

            if (count63 >= 0 && count61 >= 0 && count62 >= 0) {
               VirtualOptionType virtualOptionType = (VirtualOptionType)netherManager.resolveEnum((String)arrayDeque.poll(), VirtualOptionType.values(), "pixel format");
               if (virtualOptionType == null) {
                  ShadersManager.applyText3("Invalid raw texture pixel format: " + text);
                  return null;
               } else {
                  StaticOptionType staticOptionType = (StaticOptionType)netherManager.resolveEnum((String)arrayDeque.poll(), StaticOptionType.values(), "pixel type");
                  if (staticOptionType == null) {
                     ShadersManager.applyText3("Invalid raw texture pixel type: " + text);
                     return null;
                  } else if (!arrayDeque.isEmpty()) {
                     ShadersManager.applyText3("Invalid raw texture, too many parameters: " + text);
                     return null;
                  } else {
                     return resolvePreUpdateSubscriber(count60, text, text2, renderStateProperty, nativeOptionType, count63, count61, count62, virtualOptionType, staticOptionType);
                  }
               }
            } else {
               ShadersManager.applyText3("Invalid raw texture size: " + text);
               return null;
            }
         }
      }
   }

   public static boolean isActive11() {
      return !managerAdapter10.isActive2();
   }

   private static void applyText(String text) {
      mc.ingameGUI.getInternalLoggerEntry().applyIChatComponent(new ChatComponentText(text));
   }

   private static void applyEntityByEntity(Entity entity) {
      if (entity == null) {
         count3 = 0;
         count46 = 0;
      } else {
         count3 = (int)entity.posX / 1000 * 1000;
         count46 = (int)entity.posZ / 1000 * 1000;
      }
   }

   private static void updateState13() {
      if (count53 >= 1) {
         GlStateManager.enableState5(33988);
         GlStateManager.setActiveTexture(intBuffer2.get(0));
         if (count53 >= 2) {
            GlStateManager.enableState5(33989);
            GlStateManager.setActiveTexture(intBuffer2.get(1));
         }
      }

      GlStateManager.enableState5(33984);

      for (int index = 0; index < count55; index++) {
         GlStateManager.setActiveTexture(intBufferEntry.computeCount(index));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
         GlStateManager.setActiveTexture(intBufferEntry.computeCountByIndex(index));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
      }

      GlStateManager.setActiveTexture(0);

      for (int index2 = 0; index2 < 4 && 4 + index2 < count55; index2++) {
         GlStateManager.enableState5(33991 + index2);
         GlStateManager.setActiveTexture(intBufferEntry.computeCount(4 + index2));
      }

      GlStateManager.enableState5(33990);
      GlStateManager.setActiveTexture(intBuffer4.get(0));
      if (count56 >= 2) {
         GlStateManager.enableState5(33995);
         GlStateManager.setActiveTexture(intBuffer4.get(1));
         if (count56 >= 3) {
            GlStateManager.enableState5(33996);
            GlStateManager.setActiveTexture(intBuffer4.get(2));
         }
      }

      for (int index3 = 0; index3 < count8; index3++) {
         GlStateManager.enableState5(33997 + index3);
         GlStateManager.setActiveTexture(intBuffer5.get(index3));
      }

      if (enabled9) {
         GlStateManager.enableState5(33984 + preUpdateSubscriber.getCountAsIndex());
         GlStateManager.setActiveTexture(preUpdateSubscriber.getCount2());
      }

      applyPreUpdateSubscriberArray(preUpdateSubscriberArray3);
      GlStateManager.enableState5(33984);
   }

   public static boolean matchesBlockSolidType(BlockSolidType blockSolidType) {
      switch (AdapterIntArrayEntry.countArray2[blockSolidType.ordinal()]) {
         case 1:
            return managerAdapter7.isActiveAsFlag();
         case 2:
            return managerAdapter5.isActiveAsFlag();
         case 3:
            return managerAdapter13.isActiveAsFlag();
         case 4:
            return managerAdapter4.isActiveAsFlag();
         default:
            return false;
      }
   }

   public static void applyIndex(int count60, float factor42, long value7) {
      if (!enabled12) {
         EXTFramebufferObject.glBindFramebufferEXT(36160, count33);
         GL11.glViewport(0, 0, count12, count15);
         intBuffer3 = null;
         AllocateTextureMapManager.applyDistanceTransformerByDistanceTransfor(bridgeListener.getDistanceTransformer());
         applyNoneManager2(noneManager5);
         computeCount3("end beginRenderPass");
      }
   }

   public static void applyIndex3(int count60) {
      GL11.glDisable(count60);
      if (count60 == 3553) {
         updateState();
      } else if (count60 == 2912) {
         updateState50();
      }
   }

   public static void applyWorldByWorld(World world2) {
      if (world != world2) {
         World world3 = world;
         world = world2;
         applyEntityByEntity(mc.getRenderViewEntity());
         int count60 = computeCount(world3);
         int count61 = computeCount(world2);
         if (count61 != count60) {
            boolean flag = list.contains(count60);
            boolean flag2 = list.contains(count61);
            if (flag || flag2) {
               updateState77();
            }
         }

         ExtraMapEntry.clear();
      }
   }

   public static String resolveText(MovementKeybindProperty movementKeybindProperty) {
      switch (AdapterIntArrayEntry.countArray3[movementKeybindProperty.ordinal()]) {
         case 1:
            return Integer.toString(count5);
         case 2:
            return Boolean.toString(enabled15);
         case 3:
            return Boolean.toString(enabled37);
         case 4:
            return Float.toString(factor31);
         case 5:
            return Float.toString(factor5);
         case 6:
            return Float.toString(factor20);
         case 7:
            return Boolean.toString(enabled30);
         case 8:
            return managerAdapter2.getText();
         case 9:
            return managerAdapter8.getText();
         case 10:
            return labelText7;
         case 11:
            return Boolean.toString(enabled10);
         case 12:
            return Boolean.toString(enabled17);
         case 13:
            return Integer.toString(count7);
         case 14:
            return Integer.toString(count17);
         case 15:
            return Integer.toString(count22);
         case 16:
            return Integer.toString(count29);
         case 17:
            return Integer.toString(count29);
         case 18:
            return Integer.toString(count29);
         default:
            throw new IllegalArgumentException("Unknown option: " + movementKeybindProperty);
      }
   }

   public static void updateState25() {
      ShadersManager.applyText("Save shaders configuration.");
      if (properties2 == null) {
         properties2 = new PropertiesAdapter();
      }

      MovementKeybindProperty[] values = MovementKeybindProperty.values();

      for (int index = 0; index < values.length; index++) {
         MovementKeybindProperty movementKeybindProperty = values[index];
         String labelText11 = movementKeybindProperty.getLabelText3();
         String text = resolveText(movementKeybindProperty);
         properties2.setProperty(labelText11, text);
      }

      try {
         FileWriter writer = new FileWriter(file);
         properties2.store(writer, (String)null);
         writer.close();
      } catch (Exception exception) {
         ShadersManager.applyTextByText("Error saving configuration: " + exception.getClass().getName() + ": " + exception.getMessage());
      }
   }

   public static void updateState9() {
      enabled40 = true;
      applyIndex(-3, 0);
      applyNoneManager2(noneManager8);
   }

   public static void updateState10() {
      if (count56 >= 2) {
         GlStateManager.enableState5(33995);
         computeCount3("pre copy depth");
         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, count12, count15);
         computeCount3("copy depth");
         GlStateManager.enableState5(33984);
      }

      AllocateTextureMapManager.applyDistanceTransformerByDistanceTransfor(bridgeListener.getDistanceTransformer());
   }

   private static void updateState7() {
      count = mc.displayWidth;
      count9 = mc.displayHeight;
      count12 = Math.round((float)count * factor31);
      count15 = Math.round((float)count9 * factor31);
      updateState14();
   }

   public static boolean isEnabled35() {
      return enabled35;
   }

   public static void updateState4() {
      applyOptionManagerArray(optionManagerArray, preUpdateFilter);
   }

   public static void updateState2() {
      if (enabled18) {
         applyNoneManager2(noneManager4);
      } else if (noneManager7 == noneManager12) {
         applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
      }
   }

   public static boolean isActive8() {
      return !enabled5 && !enabled2;
   }

   private static void updateState5() {
      enabled24 = false;
      mc.getFramebuffer().setState(true);
      OpenGlHelper.applyIndex(OpenGlHelper.count36, OpenGlHelper.count17, 3553, mc.getFramebuffer().indexOffset, 0);
      GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
      if (EntityRenderer.enabled5) {
         boolean flag = EntityRenderer.count10 != 0;
         GlStateManager.applyFlag(flag, !flag, !flag, true);
      }

      GlStateManager.depthMask(true);
      GL11.glClearColor(factor34, factor39, factor38, 1.0F);
      GL11.glClear(16640);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.applyState10();
      GlStateManager.enableBlend();
      GlStateManager.disableDepth();
      GlStateManager.glDepthFunc(519);
      GlStateManager.depthMask(false);
      computeCount3("pre-final");
      applyNoneManager2(noneManager32);
      computeCount3("final");
      if (count13 != 0) {
         updateState20();
      }

      updateState34();
      computeCount3("renderCompositeFinal");
   }

   public static boolean isActive3() {
      return !managerAdapter18.isActive2();
   }

   public static boolean isActive2() {
      return enabled23 ? contextCapabilities.GL_NV_geometry_shader4 : true;
   }

   public static boolean isActive5() {
      return managerAdapter6.isActiveAsFlag();
   }

   public static boolean isActiveAsFlag() {
      return enabled43 && enabled32;
   }

   public static void updateState8() {
      enabled6 = false;
      if (noneManager7 == noneManager30) {
         applyNoneManager2(noneManager5);
      }
   }

   public static boolean isEnabled43() {
      return enabled43;
   }

   public static void updateState20() {
      if (enabled8) {
         for (int index = 0; index < count55; index++) {
            if ((count13 & 1 << index) != 0) {
               GlStateManager.enableState5(33984 + countArray4[index]);
               GL11.glTexParameteri(3553, 10241, 9987);
               GL30.glGenerateMipmap(3553);
            }
         }

         GlStateManager.enableState5(33984);
      }
   }

   public static void updateState59() {
      mc.uNKNOWNManager.updateState();
   }

   public static void updateState46() {
      ShadersManager.applyText("Load shaders configuration.");

      try {
         if (!file2.exists()) {
            file2.mkdirs();
         }
      } catch (Exception exception) {
         ShadersManager.applyTextByText("Failed to open the shaderpacks directory: " + file2);
      }

      properties2 = new PropertiesAdapter();
      properties2.setProperty(MovementKeybindProperty.SHADER_PACK.getLabelText3(), "");
      if (file.exists()) {
         try {
            FileReader reader = new FileReader(file);
            properties2.load(reader);
            reader.close();
         } catch (Exception exception2) {
         }
      }

      if (!file.exists()) {
         try {
            updateState25();
         } catch (Exception exception3) {
         }
      }

      MovementKeybindProperty[] values = MovementKeybindProperty.values();

      for (int index = 0; index < values.length; index++) {
         MovementKeybindProperty movementKeybindProperty = values[index];
         String labelText11 = movementKeybindProperty.getLabelText3();
         String labelText12 = movementKeybindProperty.getLabelText();
         labelText11 = properties2.getProperty(labelText11, labelText12);
         applyMovementKeybindProperty(movementKeybindProperty, labelText11);
      }

      updateState63();
   }

   public static void updateState58() {
      computeCount3("beginUpdateChunks1");
      computeCountByText("beginUpdateChunks1");
      if (!enabled12) {
         applyNoneManager2(noneManager19);
      }

      computeCount3("beginUpdateChunks2");
      computeCountByText("beginUpdateChunks2");
   }

   public static void updateState77() {
      if (enabled45) {
         computeCount3("Shaders.uninit pre");

         for (int index = 0; index < noneManagerArray2.length; index++) {
            NoneManager noneManager33 = noneManagerArray2[index];
            if (noneManager33.getCount3() != 0) {
               ARBShaderObjects.glDeleteObjectARB(noneManager33.getCount3());
               computeCount3("del programRef");
            }

            noneManager33.setCount3(0);
            noneManager33.setCount(0);
            noneManager33.setLabelText2((String)null);
            noneManager33.setIntBuffer((IntBuffer)null);
            noneManager33.setCount5(0);
         }

         enabled34 = false;
         if (count33 != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(count33);
            count33 = 0;
            computeCount3("del dfb");
         }

         if (count51 != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(count51);
            count51 = 0;
            computeCount3("del sfb");
         }

         if (intBuffer4 != null) {
            GlStateManager.glStateAction(intBuffer4);
            resolveIntBuffer(intBuffer4);
            computeCount3("del dfbDepthTextures");
         }

         if (intBuffer8 != null) {
            GlStateManager.glStateAction(intBuffer8);
            resolveIntBuffer(intBuffer8);
            computeCount3("del dfbTextures");
         }

         if (intBuffer2 != null) {
            GlStateManager.glStateAction(intBuffer2);
            resolveIntBuffer(intBuffer2);
            computeCount3("del shadow depth");
         }

         if (intBuffer5 != null) {
            GlStateManager.glStateAction(intBuffer5);
            resolveIntBuffer(intBuffer5);
            computeCount3("del shadow color");
         }

         if (intBuffer9 != null) {
            resolveIntBuffer(intBuffer9);
         }

         if (preUpdateSubscriber != null) {
            preUpdateSubscriber.glDeleteTextures();
            preUpdateSubscriber = null;
         }

         ShadersManager.applyText("Uninit");
         count49 = 0;
         enabled14 = false;
         enabled45 = false;
         computeCount3("Shaders.uninit");
      }
   }

   private static void updateState54() {
      if (noneManager7.getSplineMonitor() != null) {
         GL11.glViewport(0, 0, count12, count15);
      }
   }

   public static void updateState57() {
      GlStateManager.enableBlend();
      applyNoneManager2(noneManager30);
   }

   public static void updateState69() {
      if (enabled31) {
         computeCount3("beginBlockEntities");
         applyNoneManager2(noneManager18);
      }
   }

   public static void glScaled() {
      if ((double)factor20 != 1.0) {
         GL11.glScaled(1.0, 1.0, (double)factor20);
      }
   }

   public static void updateState32() {
      if (!enabled12) {
         matchesNoneManager(noneManager13);
         computeCount3("pre-render CompositeFinal");
         applyNoneManagerArray(noneManagerArray3, true);
      }
   }

   public static void updateState41() {
      count9 = 0;
   }

   public static void updateState43() {
      updateState73();
      GL11.glColor3f(factor15, factor10, factor41);
      updateState68();
      GL11.glColor3f(factor37, factor4, factor9);
   }

   public static void updateState64() {
      enabled18 = true;
      enabled40 = true;
      applyIntBuffer(intBuffer9);
      applyNoneManager2(noneManager4);
      applyIndex(-2, 0);
   }

   public static void updateState6() {
      if (enabled31) {
         enabled28 = true;
      }
   }

   public static boolean isActive7() {
      return !managerAdapter15.isActive2();
   }

   public static boolean isActive4() {
      return !managerAdapter14.isActive2();
   }

   private static void updateState19() {
      list.clear();

      for (int index = -128; index <= 128; index++) {
         String text = "/shaders/world" + index;
         if (preUpdateFilter.matchesText(text)) {
            list.add(index);
         }
      }

      if (list.size() > 0) {
         Integer[] countArray8 = (Integer[])list.toArray(new Integer[list.size()]);
         BuildManager.applyText4("[Shaders] Worlds: " + BuildManager.resolveText(countArray8));
      }
   }

   public static void updateState50() {
      enabled40 = false;
      applyStringHandler(stringHandler5, 0);
   }

   public static void updateState61() {
   }

   private static void updateState71() {
      auxiliaryManagerAdapter.updateState();
      managerAdapter3.updateState();
      managerAdapter16.updateState();
      managerAdapter.updateState();
      managerAdapter10.updateState();
      managerAdapter15.updateState();
      managerAdapter17.updateState();
      managerAdapter14.updateState();
      managerAdapter12.updateState();
      managerAdapter7.updateState();
      managerAdapter5.updateState();
      managerAdapter13.updateState();
      managerAdapter4.updateState();
      managerAdapter11.updateState();
      managerAdapter6.updateState();
      managerAdapter9.updateState();
      managerAdapter18.updateState();
      AuxiliaryShadersManager.renderElement2();
      ShadersItemPropertiesManager.renderElement();
      ShadersEntityPropertiesManager.renderElement();
      fovObserver = null;

      for (int index = 0; index < noneManagerArray2.length; index++) {
         NoneManager noneManager33 = noneManagerArray2[index];
         noneManager33.fill();
      }

      if (preUpdateFilter != null) {
         AuxiliaryShadersManager.applyPreUpdateFilter(preUpdateFilter);
         ShadersItemPropertiesManager.applyPreUpdateFilter(preUpdateFilter);
         ShadersEntityPropertiesManager.applyPreUpdateFilter(preUpdateFilter);
         String text = "/shaders/shaders.properties";

         try {
            InputStream resourceAsStream = preUpdateFilter.getResourceAsStream(text);
            if (resourceAsStream == null) {
               return;
            }

            resourceAsStream = BezierCounter.resolveInputStream(resourceAsStream, text);
            PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
            propertiesAdapter.load(resourceAsStream);
            resourceAsStream.close();
            auxiliaryManagerAdapter.matchesProperties(propertiesAdapter);
            managerAdapter3.matchesProperties(propertiesAdapter);
            managerAdapter16.matchesProperties(propertiesAdapter);
            managerAdapter.matchesProperties(propertiesAdapter);
            managerAdapter10.matchesProperties(propertiesAdapter);
            managerAdapter15.matchesProperties(propertiesAdapter);
            managerAdapter17.matchesProperties(propertiesAdapter);
            managerAdapter12.matchesProperties(propertiesAdapter);
            managerAdapter14.matchesProperties(propertiesAdapter);
            managerAdapter7.matchesProperties(propertiesAdapter);
            managerAdapter5.matchesProperties(propertiesAdapter);
            managerAdapter13.matchesProperties(propertiesAdapter);
            managerAdapter4.matchesProperties(propertiesAdapter);
            managerAdapter11.matchesProperties(propertiesAdapter);
            managerAdapter6.matchesProperties(propertiesAdapter);
            managerAdapter9.matchesProperties(propertiesAdapter);
            managerAdapter18.matchesProperties(propertiesAdapter);
            set = SlidersManager.resolveSet(propertiesAdapter, optionManagerArray);
            geometryCounterArray = SlidersManager.resolveGeometryCounterArray(propertiesAdapter, optionManagerArray);
            map2 = SlidersManager.resolveMap(propertiesAdapter, geometryCounterArray, optionManagerArray);
            map = SlidersManager.resolveMap(propertiesAdapter, optionManagerArray);
            preUpdateSubscriberArray3 = resolvePreUpdateSubscriberArray(propertiesAdapter, 0);
            preUpdateSubscriberArray = resolvePreUpdateSubscriberArray(propertiesAdapter, 1);
            preUpdateSubscriberArray2 = resolvePreUpdateSubscriberArray(propertiesAdapter, 2);
            labelText9 = propertiesAdapter.getProperty("texture.noise");
            if (labelText9 != null) {
               enabled9 = true;
            }

            fovObserver = SlidersManager.resolveFovObserver(propertiesAdapter);
            SlidersManager.applyProperties3(propertiesAdapter);
            SlidersManager.applyProperties2(propertiesAdapter);
            SlidersManager.applyPropertiesByProperties(propertiesAdapter);
            SlidersManager.applyProperties(propertiesAdapter);
         } catch (IOException ioException) {
            BuildManager.applyText3("[Shaders] Error reading: " + text);
         }
      }
   }

   public static void updateState68() {
      WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
      float factor42 = (float)(mc.gameSettings.indexOffset16 * 16);
      double offset = (double)factor42 * 0.9238;
      double offset2 = (double)factor42 * 0.3826;
      double offset3 = -offset2;
      double offset4 = -offset;
      double offset5 = 16.0;
      double offset6 = -value3;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer(offset3, offset6, offset4).updateState();
      worldRenderer.getWorldRenderer(offset3, offset5, offset4).updateState();
      worldRenderer.getWorldRenderer(offset4, offset5, offset3).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset3).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset3).updateState();
      worldRenderer.getWorldRenderer(offset4, offset5, offset3).updateState();
      worldRenderer.getWorldRenderer(offset4, offset5, offset2).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset2).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset2).updateState();
      worldRenderer.getWorldRenderer(offset4, offset5, offset2).updateState();
      worldRenderer.getWorldRenderer(offset3, offset5, offset).updateState();
      worldRenderer.getWorldRenderer(offset3, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset3, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset3, offset5, offset).updateState();
      worldRenderer.getWorldRenderer(offset2, offset5, offset).updateState();
      worldRenderer.getWorldRenderer(offset2, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset2, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset2, offset5, offset).updateState();
      worldRenderer.getWorldRenderer(offset, offset5, offset2).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset2).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset2).updateState();
      worldRenderer.getWorldRenderer(offset, offset5, offset2).updateState();
      worldRenderer.getWorldRenderer(offset, offset5, offset3).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset3).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset3).updateState();
      worldRenderer.getWorldRenderer(offset, offset5, offset3).updateState();
      worldRenderer.getWorldRenderer(offset2, offset5, offset4).updateState();
      worldRenderer.getWorldRenderer(offset2, offset6, offset4).updateState();
      worldRenderer.getWorldRenderer(offset2, offset6, offset4).updateState();
      worldRenderer.getWorldRenderer(offset2, offset5, offset4).updateState();
      worldRenderer.getWorldRenderer(offset3, offset5, offset4).updateState();
      worldRenderer.getWorldRenderer(offset3, offset6, offset4).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset4).updateState();
      worldRenderer.getWorldRenderer(offset4, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset).updateState();
      worldRenderer.getWorldRenderer(offset, offset6, offset4).updateState();
      Tessellator.getTessellator().updateState();
   }

   public static void updateState47() {
      if (enabled31 && !enabled12) {
         applyIntBuffer(noneManager29.getIntBuffer());
      }
   }

   public static void updateState39() {
      countArray7[count23 * 2] = 0;
      countArray7[count23 * 2 + 1] = 0;
      count23--;
   }

   public static void updateState65() {
      enabled18 = false;
      applyIntBuffer(intBuffer9);
      applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
      updateState39();
   }

   public static void updateState45() {
      ShadersManager.applyText("Reset model renderers");
      count25++;
      ShadersManager.applyText("Reset world renderers");
      mc.renderGlobal.updateState();
   }

   public static void updateState37() {
      if (enabled31) {
         enabled28 = false;
      }
   }

   public static void updateState49() {
      GlStateManager.applyState3();
      GlStateManager.enableBlend();
   }

   public static void updateState40() {
      if (enabled31 && noneManager23.getCount() != noneManager6.getCount()) {
         applyNoneManager2(noneManager23);
         GlStateManager.applyState14();
         GlStateManager.alphaFunc(516, 0.0F);
         GlStateManager.glStateAction3(770, 771);
      }
   }

   private static void updateState72() {
      if (count53 != 0) {
         if (count51 != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(count51);
            GlStateManager.glStateAction(intBuffer2);
            GlStateManager.glStateAction(intBuffer5);
         }

         count51 = EXTFramebufferObject.glGenFramebuffersEXT();
         EXTFramebufferObject.glBindFramebufferEXT(36160, count51);
         GL11.glDrawBuffer(0);
         GL11.glReadBuffer(0);
         GL11.glGenTextures((IntBuffer)((Buffer)intBuffer2).clear().limit(count53));
         GL11.glGenTextures((IntBuffer)((Buffer)intBuffer5).clear().limit(count8));
         ((Buffer)intBuffer2).position(0);
         ((Buffer)intBuffer5).position(0);

         for (int index = 0; index < count53; index++) {
            GlStateManager.setActiveTexture(intBuffer2.get(index));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            int count60 = enabledArray5[index] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, count60);
            GL11.glTexParameteri(3553, 10240, count60);
            if (enabledArray[index]) {
               GL11.glTexParameteri(3553, 34892, 34894);
            }

            GL11.glTexImage2D(3553, 0, 6402, count10, count6, 0, 6402, 5126, (FloatBuffer)null);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, intBuffer2.get(0), 0);
         computeCount3("FT sd");

         for (int index2 = 0; index2 < count8; index2++) {
            GlStateManager.setActiveTexture(intBuffer5.get(index2));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            int count61 = enabledArray6[index2] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, count61);
            GL11.glTexParameteri(3553, 10240, count61);
            GL11.glTexImage2D(3553, 0, 6408, count10, count6, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index2, 3553, intBuffer5.get(index2), 0);
            computeCount3("FT sc");
         }

         GlStateManager.setActiveTexture(0);
         if (count8 > 0) {
            GL20.glDrawBuffers(intBuffer6);
         }

         int count62 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (count62 != 36053) {
            applyTextByText("[Shaders] Error: Failed creating shadow framebuffer! (Status " + count62 + ")");
         } else {
            ShadersManager.applyText("Shadow framebuffer created.");
         }
      }
   }

   public static void updateState31() {
      if (enabled31) {
         applyEntity5((Entity)null);
         applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
      }
   }

   public static void updateState48() {
      if (!enabled12) {
         boolean flag = matchesNoneManager(noneManager);
         if (enabled34) {
            computeCount3("pre-render Deferred");
            applyNoneManagerArray(noneManagerArray, false);
            flag = true;
         }

         if (flag) {
            updateState13();

            for (int index = 0; index < count55; index++) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + index, 3553, intBufferEntry.computeCount(index), 0);
            }

            if (noneManager10.getIntBuffer() != null) {
               applyIntBuffer(noneManager10.getIntBuffer());
            } else {
               applyIntBuffer(intBuffer9);
            }

            GlStateManager.enableState5(33984);
            mc.getTextureManager().applyResourceLocation3(ListenerAdapter27.resourceLocation);
         }
      }
   }

   public static void updateState56() {
      if (!enabled12) {
         if (count56 >= 3) {
            GlStateManager.enableState5(33996);
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, count12, count15);
            GlStateManager.enableState5(33984);
         }

         GlStateManager.disableDepth();
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 771);
         GlStateManager.applyState14();
         applyNoneManager2(noneManager20);
      }
   }

   public static void updateState74() {
      applyNoneManager2(noneManager5);
   }

   public static void updateState53() {
      if (isActive12()) {
         factor32 = 0.5F;
         factor8 = 0.6F;
         factor18 = 0.8F;
      } else {
         factor32 = 1.0F;
         factor8 = 1.0F;
         factor18 = 1.0F;
      }
   }

   public static void updateState22() {
      if (enabled31) {
         computeCount3("endBlockEntities");
         applyTileEntity((TileEntity)null);
         applyNoneManager2(enabled6 ? noneManager30 : noneManager5);
         AllocateTextureMapManager.applyDistanceTransformerByDistanceTransfor(bridgeListener.getDistanceTransformer());
      }
   }

   public static void updateState44() {
      updateState67();
      applyNoneManager2(noneManager12);
   }

   public static void updateState36() {
      if (enabled31) {
         applyNoneManager2(noneManager31);
      }
   }

   public static void updateState66() {
      if (enabled31) {
         applyNoneManager2(noneManager18);
      }
   }

   public static void updateState38() {
      applyNoneManager2(programStackEmptyManager.getNoneManager());
   }

   public static void updateState24() {
      if (enabled31) {
         AllocateTextureMapManager.applyBridgeListener(bridgeListener);
         if (!enabled12) {
            applyIntBuffer(intBuffer7);
         }
      }
   }

   public static boolean isEnabled32() {
      return enabled32;
   }

   private static void updateState70() {
      if (!isActive2()) {
         GL11.glBegin(5);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glEnd();
      } else {
         GL11.glBegin(7);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glEnd();
      }
   }

   public static void updateState29() {
      if (!enabled13) {
         enabled13 = true;
      }

      if (!enabled45) {
         computeCount3("Shaders.init pre");
         getText();
         if (!contextCapabilities.OpenGL20) {
            applyTextByText("No OpenGL 2.0");
         }

         if (!contextCapabilities.GL_EXT_framebuffer_object) {
            applyTextByText("No EXT_framebuffer_object");
         }

         ((Buffer)intBuffer9).position(0).limit(8);
         ((Buffer)intBuffer8).position(0).limit(16);
         ((Buffer)intBuffer4).position(0).limit(3);
         ((Buffer)intBuffer6).position(0).limit(8);
         ((Buffer)intBuffer2).position(0).limit(2);
         ((Buffer)intBuffer5).position(0).limit(8);
         count55 = 4;
         count56 = 1;
         count8 = 0;
         count53 = 0;
         count36 = 1;
         count42 = 1;
         Arrays.fill(countArray3, 6408);
         Arrays.fill(enabledArray4, true);
         Arrays.fill(vector4fArray, null);
         Arrays.fill(enabledArray, false);
         Arrays.fill(enabledArray2, false);
         Arrays.fill(enabledArray5, false);
         Arrays.fill(enabledArray3, false);
         Arrays.fill(enabledArray6, false);
         enabled44 = false;
         enabled9 = false;
         factor35 = 0.0F;
         factor19 = 2.0F;
         count10 = 1024;
         count6 = 1024;
         count27 = 1024;
         count54 = 1024;
         factor30 = 90.0F;
         factor2 = 160.0F;
         enabled4 = true;
         factor40 = -1.0F;
         factor29 = -1.0F;
         enabled21 = false;
         enabled7 = false;
         enabled39 = false;
         enabled33 = false;
         enabled23 = false;
         updateState53();
         ExtraMapEntry.clear();
         altListEntry.updateState();
         if (fovObserver != null) {
            fovObserver.updateState3();
         }

         GeometryCounter geometryCounter = InterpolationPredictor.resolveGeometryCounter(geometryCounterArray, optionManagerArray, false);
         String text = "";
         if (world != null) {
            int countAsIndex = world.secondaryFloatArrayEntry.getCountAsIndex();
            if (list.contains(countAsIndex)) {
               text = "world" + countAsIndex + "/";
            }
         }

         for (int index = 0; index < noneManagerArray2.length; index++) {
            NoneManager noneManager33 = noneManagerArray2[index];
            noneManager33.updateState();
            noneManager33.updateState2();
            if (noneManager33.getSharedOptionType() != SharedOptionType.NONE) {
               String labelText11 = noneManager33.getLabelText();
               String text2 = text + labelText11;
               boolean flag = true;
               if (map.containsKey(text2)) {
                  flag = ((TargetObserver)map.get(text2)).isActive();
               }

               if (geometryCounter != null) {
                  flag = flag && !geometryCounter.contains(text2);
               }

               if (!flag) {
                  ShadersManager.applyText("Program disabled: " + text2);
                  labelText11 = "<disabled>";
                  text2 = text + labelText11;
               }

               labelText11 = "/shaders/" + text2;
               String text3 = labelText11 + ".vsh";
               String text4 = labelText11 + ".gsh";
               labelText11 = labelText11 + ".fsh";
               applyNoneManager(noneManager33, text3, text4, labelText11);
               if (noneManager33.getCount() > 0) {
                  ShadersManager.applyText("Program loaded: " + text2);
               }

               applyNoneManagerByNoneManager(noneManager33);
               applyNoneManager(noneManager33);
            }
         }

         enabled34 = false;

         for (int index2 = 0; index2 < noneManagerArray.length; index2++) {
            if (noneManagerArray[index2].getCount() != 0) {
               enabled34 = true;
               break;
            }
         }

         count36 = count55;
         count49 = count53 > 0 ? 1 : 0;
         enabled14 = count53 > 0;
         ShadersManager.applyText("usedColorBuffers: " + count55);
         ShadersManager.applyText("usedDepthBuffers: " + count56);
         ShadersManager.applyText("usedShadowColorBuffers: " + count8);
         ShadersManager.applyText("usedShadowDepthBuffers: " + count53);
         ShadersManager.applyText("usedColorAttachs: " + count36);
         ShadersManager.applyText("usedDrawBuffers: " + count42);
         ((Buffer)intBuffer9).position(0).limit(count42);
         ((Buffer)intBuffer8).position(0).limit(count55 * 2);
         intBufferEntry.updateState();

         for (int index3 = 0; index3 < count42; index3++) {
            intBuffer9.put(index3, 36064 + index3);
         }

         int count60 = GL11.glGetInteger(34852);
         if (count42 > count60) {
            applyTextByText("[Shaders] Error: Not enough draw buffers, needed: " + count42 + ", available: " + count60);
         }

         ((Buffer)intBuffer6).position(0).limit(count8);

         for (int index4 = 0; index4 < count8; index4++) {
            intBuffer6.put(index4, 36064 + index4);
         }

         for (int index5 = 0; index5 < noneManagerArray2.length; index5++) {
            NoneManager noneManager34 = noneManagerArray2[index5];
            NoneManager noneManager35 = noneManager34;

            while (noneManager35.getCount() == 0 && noneManager35.getNoneManager() != noneManager35) {
               noneManager35 = noneManager35.getNoneManager();
            }

            if (noneManager35 != noneManager34 && noneManager34 != noneManager25) {
               noneManager34.applyNoneManager(noneManager35);
            }
         }

         updateState7();
         updateState78();
         if (enabled9) {
            updateState79();
         }

         if (bridgeListener == null) {
            bridgeListener = AllocateTextureMapManager.getBridgeListener();
         }

         GlStateManager.pushMatrix();
         GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         updateState17();
         updateState30();
         GlStateManager.popMatrix();
         enabled45 = true;
         updateState3();
         updateState45();
         computeCount3("Shaders.init");
      }
   }

   public static void updateState62() {
      enabled40 = true;
      applyStringHandler(stringHandler5, count31);
      applyFloatArrayHolder(floatArrayHolder4, factor16);
   }

   public static void updateState73() {
      FloatBuffer buffer = floatBuffer12;
      ((Buffer)buffer).clear();
      GL11.glGetFloat(2982, buffer);
      buffer.get(factorArray14, 0, 16);
      AimAccumulator.applyFloatArrayByFloatArray(factorArray2, factorArray14, factorArray11);
      applyAuxiliaryFloatArrayHolder(auxiliaryFloatArrayHolder4, factorArray2[0], factorArray2[1], factorArray2[2]);
      if (fovObserver != null) {
         fovObserver.updateState2();
      }
   }

   public static boolean isActive6() {
      return !managerAdapter16.isActive() ? managerAdapter16.isActiveAsFlag() : true;
   }

   public static void updateState27() {
      updateState75();
      if (enabled19) {
         AuxiliaryShadersManager.renderElement();
         ShadersItemPropertiesManager.renderElement2();
         ShadersEntityPropertiesManager.renderElement2();
      }
   }

   private static void updateState79() {
      if (preUpdateSubscriber == null && labelText9 != null) {
         preUpdateSubscriber = resolvePreUpdateSubscriber(15, labelText9);
      }

      if (preUpdateSubscriber == null) {
         preUpdateSubscriber = new CurveDeserializer(count19, count19);
      }
   }

   private static void updateState21() {
      applyPreUpdateSubscriberArrayByPreUpdateSubscri(preUpdateSubscriberArray3);
      applyPreUpdateSubscriberArrayByPreUpdateSubscri(preUpdateSubscriberArray);
      applyPreUpdateSubscriberArrayByPreUpdateSubscri(preUpdateSubscriberArray2);
      preUpdateSubscriberArray3 = null;
      preUpdateSubscriberArray = null;
      preUpdateSubscriberArray2 = null;
   }
}
