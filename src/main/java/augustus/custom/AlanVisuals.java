package augustus.custom;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

/**
 * Alan portrait rendering. Images stretch directly to each surface; no ratio controls.
 */
public final class AlanVisuals {
    private static final String RESOURCE = "/assets/augustus/alan/portrait.png";
    private static int texture;
    private static Object capeLocation;
    public static Object menuMode;
    private static final Set<Object> INITIALIZED = Collections.newSetFromMap(new IdentityHashMap<>());

    private AlanVisuals() {}

    public static boolean legacyCape(boolean custom, Object player) {
        Object module = ClientAccess.module(ClientAccess.resolveClassName("augustus.modules.render.CustomCape"));
        String customCapeClass = ClientAccess.resolveClassName("augustus.modules.render.CustomCape");
        String settingTypeField = ClientAccess.resolveFieldName("augustus.modules.render.CustomCape", "settingType");
        String mcClass = ClientAccess.resolveClassName("net.minecraft.client.Minecraft");
        String thePlayerField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "thePlayer", "Lnet/minecraft/client/entity/EntityPlayerSP;");
        return custom && !(ClientAccess.enabled(module)
                && ClientAccess.choice(ClientAccess.get(module, settingTypeField)).equals("Alan")
                && player == ClientAccess.get(ClientAccess.minecraft(), thePlayerField));
    }

    public static void initializeModule(Object module) {
        if (!INITIALIZED.add(module)) return;
        try {
            String hudClass = ClientAccess.resolveClassName("augustus.modules.render.HUD");
            String customCapeClass = ClientAccess.resolveClassName("augustus.modules.render.CustomCape");
            String clientSettingsClass = ClientAccess.resolveClassName("augustus.modules.misc.ClientSettings");

            if (module.getClass().getName().equals(hudClass)) {
                ClientAccess.choices(ClientAccess.get(module, ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingMode")), "Alan");
            }
            if (module.getClass().getName().equals(customCapeClass)) {
                ClientAccess.choices(ClientAccess.get(module, ClientAccess.resolveFieldName("augustus.modules.render.CustomCape", "settingType")), "Alan");
            }
            if (module.getClass().getName().equals(clientSettingsClass)) {
                menuMode = ModuleCustomization.newChoice(module, "MainMenuBackground", "Original", new String[]{"Original"});
            }
        } catch (Throwable error) {
            ClientAccess.warn("Alan settings", error);
        }
    }

    private static BufferedImage portrait() throws IOException {
        try (InputStream in = AlanVisuals.class.getResourceAsStream(RESOURCE)) {
            if (in == null) throw new FileNotFoundException(RESOURCE);
            BufferedImage image = ImageIO.read(in);
            if (image == null) throw new IOException("Invalid Alan portrait");
            return image;
        }
    }

    private static int texture() throws IOException {
        if (texture != 0) return texture;
        BufferedImage img = portrait();
        int width = img.getWidth();
        int height = img.getHeight();
        ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rGB = img.getRGB(x, y);
                pixels.put((byte) (rGB >>> 16)).put((byte) (rGB >>> 8)).put((byte) rGB).put((byte) (rGB >>> 24));
            }
        }
        pixels.flip();
        int old = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
        texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 33071);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 33071);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, old);
        return texture;
    }

    public static void image(double x, double y, double width, double height) {
        try {
            int id = texture();
            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
            try {
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0.0F, 0.0F);
                GL11.glVertex2d(x, y);
                GL11.glTexCoord2f(0.0F, 1.0F);
                GL11.glVertex2d(x, y + height);
                GL11.glTexCoord2f(1.0F, 1.0F);
                GL11.glVertex2d(x + width, y + height);
                GL11.glTexCoord2f(1.0F, 0.0F);
                GL11.glVertex2d(x + width, y);
                GL11.glEnd();
            } finally {
                GL11.glPopAttrib();
            }
        } catch (Throwable error) {
            ClientAccess.warn("Alan portrait rendering", error);
        }
    }

    public static boolean background(Object screen) {
        return false;
    }

    public static boolean watermark(Object hud, Object resolution) {
        String modeField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingMode");
        if (!ClientAccess.choice(ClientAccess.get(hud, modeField)).equals("Alan")) return false;

        Object mc = ClientAccess.minecraft();
        String displayWidthField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "displayWidth", "I");
        String displayHeightField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "displayHeight", "I");
        int width = ((Number) ClientAccess.get(mc, displayWidthField)).intValue();
        int height = ((Number) ClientAccess.get(mc, displayHeightField)).intValue();

        String scaleFactorMethod = ClientAccess.resolveMethodName("net.minecraft.client.gui.ScaledResolution", "getScaleFactor", "()I");
        float gui = ((Number) ClientAccess.call(resolution, scaleFactorMethod, int.class, new Class<?>[0])).floatValue();

        String xPosField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingXPos");
        String yPosField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingYPos");
        String sizeField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingSize");
        String sideField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingSide");

        double x = ClientAccess.number(ClientAccess.get(hud, xPosField), 1) / 100.0 * width;
        double y = ClientAccess.number(ClientAccess.get(hud, yPosField), 1) / 100.0 * height;
        double size = 256 * ClientAccess.number(ClientAccess.get(hud, sizeField), 0.6);

        Object arrayList = ClientAccess.module(ClientAccess.resolveClassName("augustus.modules.render.ArrayList"));
        boolean sameSide = arrayList != null && ClientAccess.choice(ClientAccess.get(hud, sideField)).equals(
                ClientAccess.choice(ClientAccess.get(arrayList, ClientAccess.resolveFieldName("augustus.modules.render.ArrayList", "settingSide"))));
        float reservedHeight = sameSide ? (int) ((float) size + 1.0F) + 1 : 0;

        String guiIngameClass = ClientAccess.resolveClassName("net.minecraft.client.gui.GuiIngame");
        String reservedOffsetField = ClientAccess.resolveFieldName("net.minecraft.client.gui.GuiIngame", "reservedTopOffset");
        ClientAccess.set(ClientAccess.type(guiIngameClass), reservedOffsetField, reservedHeight);

        if (ClientAccess.choice(ClientAccess.get(hud, sideField)).equals("Right")) {
            x += width - size;
        }

        GL11.glPushMatrix();
        try {
            GL11.glScaled(1.0 / gui, 1.0 / gui, 1.0);
            image(x, y, size, size);
        } finally {
            GL11.glPopMatrix();
        }
        return true;
    }

    public static boolean imageWatermark(boolean original, Object hud) {
        String modeField = ClientAccess.resolveFieldName("augustus.modules.render.HUD", "settingMode");
        return original || ClientAccess.choice(ClientAccess.get(hud, modeField)).equals("Alan");
    }

    public static Object cape(Object player) {
        Object module = ClientAccess.module(ClientAccess.resolveClassName("augustus.modules.render.CustomCape"));
        String settingTypeField = ClientAccess.resolveFieldName("augustus.modules.render.CustomCape", "settingType");
        if (!ClientAccess.enabled(module) || !ClientAccess.choice(ClientAccess.get(module, settingTypeField)).equals("Alan")) {
            return null;
        }

        String thePlayerField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "thePlayer", "Lnet/minecraft/client/entity/EntityPlayerSP;");
        if (player != ClientAccess.get(ClientAccess.minecraft(), thePlayerField)) {
            return null;
        }

        try {
            if (capeLocation == null) {
                BufferedImage photo = portrait();
                BufferedImage atlas = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = atlas.createGraphics();
                try {
                    g.drawImage(photo, 0, 0, 64, 32, null);
                    g.drawImage(photo, 1, 1, 11, 17, 0, 0, photo.getWidth(), photo.getHeight(), null);
                    g.drawImage(photo, 12, 1, 22, 17, 0, 0, photo.getWidth(), photo.getHeight(), null);
                } finally {
                    g.dispose();
                }

                Object mc = ClientAccess.minecraft();
                String textureManagerClass = ClientAccess.resolveClassName("net.minecraft.client.renderer.texture.TextureManager");
                String getTextureManagerMethod = ClientAccess.resolveMethodName("net.minecraft.client.Minecraft", "getTextureManager", "()Lnet/minecraft/client/renderer/texture/TextureManager;");
                Object manager = ClientAccess.call(mc, getTextureManagerMethod, ClientAccess.type(textureManagerClass), new Class<?>[0]);

                String dynamicTextureClass = ClientAccess.resolveClassName("net.minecraft.client.renderer.texture.DynamicTexture");
                Class<?> dynamic = ClientAccess.type(dynamicTextureClass);
                Object dynImage = dynamic.getConstructor(BufferedImage.class).newInstance(atlas);

                String resLocationClass = ClientAccess.resolveClassName("net.minecraft.util.ResourceLocation");
                Method register = null;
                for (Method m : manager.getClass().getMethods()) {
                    if (m.getReturnType().getName().equals(resLocationClass)
                            && m.getParameterTypes().length == 2
                            && m.getParameterTypes()[0] == String.class
                            && m.getParameterTypes()[1].isAssignableFrom(dynamic)) {
                        register = m;
                        break;
                    }
                }
                if (register == null) throw new IllegalStateException("Missing dynamic texture registration");
                capeLocation = register.invoke(manager, "alan-cape", dynImage);
            }
            return capeLocation;
        } catch (Throwable error) {
            ClientAccess.warn("Alan cape", error);
            return null;
        }
    }

    public static void entities(float partialTicks) {
        try {
            Object mc = ClientAccess.minecraft();
            String worldField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "theWorld", "Lnet/minecraft/client/multiplayer/WorldClient;");
            String thePlayerField = ClientAccess.resolveFieldName("net.minecraft.client.Minecraft", "thePlayer", "Lnet/minecraft/client/entity/EntityPlayerSP;");
            Object world = ClientAccess.get(mc, worldField);
            Object self = ClientAccess.get(mc, thePlayerField);

            String entityClass = ClientAccess.resolveClassName("net.minecraft.entity.Entity");
            String renderViewMethod = ClientAccess.resolveMethodName("net.minecraft.client.Minecraft", "getRenderViewEntity", "()Lnet/minecraft/entity/Entity;");
            Object camera = ClientAccess.call(mc, renderViewMethod, ClientAccess.type(entityClass), new Class<?>[0]);
            if (camera == null) camera = self;
            if (world == null || camera == null) return;

            String livingClass = ClientAccess.resolveClassName("net.minecraft.entity.EntityLivingBase");
            Class<?> living = ClientAccess.type(livingClass);

            String worldClass = ClientAccess.resolveClassName("net.minecraft.world.World");
            String loadedEntitiesField = ClientAccess.resolveFieldName("net.minecraft.world.World", "loadedEntityList", "Ljava/util/List;");
            Collection<?> entities = (Collection<?>) ClientAccess.getDeclared(worldClass, world, loadedEntitiesField);
            if (entities == null) return;

            int id = texture();
            String lastTickPosX = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "lastTickPosX");
            String posX = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "posX");
            String lastTickPosY = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "lastTickPosY");
            String posY = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "posY");
            String lastTickPosZ = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "lastTickPosZ");
            String posZ = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "posZ");
            String widthField = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "width");
            String heightField = ClientAccess.resolveFieldName("net.minecraft.entity.Entity", "height");

            double cx = interpolate(camera, lastTickPosX, posX, partialTicks);
            double cy = interpolate(camera, lastTickPosY, posY, partialTicks);
            double cz = interpolate(camera, lastTickPosZ, posZ, partialTicks);

            FloatBuffer view = BufferUtils.createFloatBuffer(16);
            GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, view);
            double rx = view.get(0), ry = view.get(4), rz = view.get(8);
            double ux = view.get(1), uy = view.get(5), uz = view.get(9);
            double rightLength = Math.sqrt(rx * rx + ry * ry + rz * rz);
            double upLength = Math.sqrt(ux * ux + uy * uy + uz * uz);
            if (rightLength < 1e-8 || upLength < 1e-8) return;
            rx /= rightLength; ry /= rightLength; rz /= rightLength;
            ux /= upLength; uy /= upLength; uz /= upLength;

            int activeTexture = GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE);
            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
            try {
                GL13.glActiveTexture(GL13.GL_TEXTURE1);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_FOG);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

                for (Object object : entities.toArray()) {
                    if (object == null || object == self || !living.isInstance(object)) continue;
                    double x = interpolate(object, lastTickPosX, posX, partialTicks) - cx;
                    double y = interpolate(object, lastTickPosY, posY, partialTicks) - cy;
                    double z = interpolate(object, lastTickPosZ, posZ, partialTicks) - cz;
                    double w = Math.max(0.1, ClientAccess.coordinate(object, widthField));
                    double h = Math.max(0.1, ClientAccess.coordinate(object, heightField));
                    double centerY = y + h * 0.5;
                    double halfWidth = w * 0.5;
                    double halfHeight = h * 0.5;

                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glTexCoord2f(0.0F, 1.0F);
                    GL11.glVertex3d(x - rx * halfWidth - ux * halfHeight, centerY - ry * halfWidth - uy * halfHeight, z - rz * halfWidth - uz * halfHeight);
                    GL11.glTexCoord2f(1.0F, 1.0F);
                    GL11.glVertex3d(x + rx * halfWidth - ux * halfHeight, centerY + ry * halfWidth - uy * halfHeight, z + rz * halfWidth - uz * halfHeight);
                    GL11.glTexCoord2f(1.0F, 0.0F);
                    GL11.glVertex3d(x + rx * halfWidth + ux * halfHeight, centerY + ry * halfWidth + uy * halfHeight, z + rz * halfWidth + uz * halfHeight);
                    GL11.glTexCoord2f(0.0F, 0.0F);
                    GL11.glVertex3d(x - rx * halfWidth + ux * halfHeight, centerY - ry * halfWidth + uy * halfHeight, z - rz * halfWidth + uz * halfHeight);
                    GL11.glEnd();
                }
            } finally {
                GL11.glPopAttrib();
                GL13.glActiveTexture(activeTexture);
            }
        } catch (Throwable error) {
            ClientAccess.warn("AlanESP", error);
        }
    }

    private static double interpolate(Object object, String previous, String current, float t) {
        double a = ClientAccess.coordinate(object, previous);
        return a + (ClientAccess.coordinate(object, current) - a) * t;
    }
}
