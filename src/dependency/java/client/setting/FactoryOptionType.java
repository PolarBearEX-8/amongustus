package client.setting;

import client.util.BuildManager;
import net.minecraft.util.MathHelper;

public enum FactoryOptionType {
    INVERT_MOUSE("options.invertMouse", false, true),
    SENSITIVITY("options.sensitivity", true, false),
    FOV("options.fov", true, false, 30.0F, 110.0F, 1.0F),
    GAMMA("options.gamma", true, false),
    SATURATION("options.saturation", true, false),
    RENDER_DISTANCE("options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
    VIEW_BOBBING("options.viewBobbing", false, true),
    ANAGLYPH("options.anaglyph", false, true),
    FRAMERATE_LIMIT("options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
    FBO_ENABLE("options.fboEnable", false, true),
    RENDER_CLOUDS("options.renderClouds", false, false),
    GRAPHICS("options.graphics", false, false),
    AMBIENT_OCCLUSION("options.ao", false, false),
    GUI_SCALE("options.guiScale", false, false),
    PARTICLES("options.particles", false, false),
    CHAT_VISIBILITY("options.chat.visibility", false, false),
    CHAT_COLOR("options.chat.color", false, true),
    CHAT_LINKS("options.chat.links", false, true),
    CHAT_OPACITY("options.chat.opacity", true, false),
    CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true),
    SNOOPER_ENABLED("options.snooper", false, true),
    USE_FULLSCREEN("options.fullscreen", false, true),
    ENABLE_VSYNC("options.vsync", false, true),
    USE_VBO("options.vbo", false, true),
    TOUCHSCREEN("options.touchscreen", false, true),
    CHAT_SCALE("options.chat.scale", true, false),
    CHAT_WIDTH("options.chat.width", true, false),
    CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
    CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false),
    MIPMAP_LEVELS("options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
    FORCE_UNICODE_FONT("options.forceUnicodeFont", false, true),
    STREAM_BYTES_PER_PIXEL("options.stream.bytesPerPixel", true, false),
    STREAM_VOLUME_MIC("options.stream.micVolumne", true, false),
    STREAM_VOLUME_SYSTEM("options.stream.systemVolume", true, false),
    STREAM_KBPS("options.stream.kbps", true, false),
    STREAM_FPS("options.stream.fps", true, false),
    STREAM_COMPRESSION("options.stream.compression", false, false),
    STREAM_SEND_METADATA("options.stream.sendMetadata", false, true),
    STREAM_CHAT_ENABLED("options.stream.chat.enabled", false, false),
    STREAM_CHAT_USER_FILTER("options.stream.chat.userFilter", false, false),
    STREAM_MIC_TOGGLE_BEHAVIOR("options.stream.micToggleBehavior", false, false),
    BLOCK_ALTERNATIVES("options.blockAlternatives", false, true),
    REDUCED_DEBUG_INFO("options.reducedDebugInfo", false, true),
    ENTITY_SHADOWS("options.entityShadows", false, true),
    FOG_FANCY("of.options.FOG_FANCY", false, false),
    FOG_START("of.options.FOG_START", false, false),
    MIPMAP_TYPE("of.options.MIPMAP_TYPE", true, false, 0.0F, 3.0F, 1.0F),
    SMOOTH_FPS("of.options.SMOOTH_FPS", false, false),
    CLOUDS("of.options.CLOUDS", false, false),
    CLOUD_HEIGHT("of.options.CLOUD_HEIGHT", true, false),
    TREES("of.options.TREES", false, false),
    RAIN("of.options.RAIN", false, false),
    ANIMATED_WATER("of.options.ANIMATED_WATER", false, false),
    ANIMATED_LAVA("of.options.ANIMATED_LAVA", false, false),
    ANIMATED_FIRE("of.options.ANIMATED_FIRE", false, false),
    ANIMATED_PORTAL("of.options.ANIMATED_PORTAL", false, false),
    AO_LEVEL("of.options.AO_LEVEL", true, false),
    LAGOMETER("of.options.LAGOMETER", false, false),
    SHOW_FPS("of.options.SHOW_FPS", false, false),
    AUTOSAVE_TICKS("of.options.AUTOSAVE_TICKS", false, false),
    BETTER_GRASS("of.options.BETTER_GRASS", false, false),
    ANIMATED_REDSTONE("of.options.ANIMATED_REDSTONE", false, false),
    ANIMATED_EXPLOSION("of.options.ANIMATED_EXPLOSION", false, false),
    ANIMATED_FLAME("of.options.ANIMATED_FLAME", false, false),
    ANIMATED_SMOKE("of.options.ANIMATED_SMOKE", false, false),
    WEATHER("of.options.WEATHER", false, false),
    SKY("of.options.SKY", false, false),
    STARS("of.options.STARS", false, false),
    SUN_MOON("of.options.SUN_MOON", false, false),
    VIGNETTE("of.options.VIGNETTE", false, false),
    CHUNK_UPDATES("of.options.CHUNK_UPDATES", false, false),
    CHUNK_UPDATES_DYNAMIC("of.options.CHUNK_UPDATES_DYNAMIC", false, false),
    TIME("of.options.TIME", false, false),
    CLEAR_WATER("of.options.CLEAR_WATER", false, false),
    SMOOTH_WORLD("of.options.SMOOTH_WORLD", false, false),
    VOID_PARTICLES("of.options.VOID_PARTICLES", false, false),
    WATER_PARTICLES("of.options.WATER_PARTICLES", false, false),
    RAIN_SPLASH("of.options.RAIN_SPLASH", false, false),
    PORTAL_PARTICLES("of.options.PORTAL_PARTICLES", false, false),
    POTION_PARTICLES("of.options.POTION_PARTICLES", false, false),
    FIREWORK_PARTICLES("of.options.FIREWORK_PARTICLES", false, false),
    PROFILER("of.options.PROFILER", false, false),
    DRIPPING_WATER_LAVA("of.options.DRIPPING_WATER_LAVA", false, false),
    BETTER_SNOW("of.options.BETTER_SNOW", false, false),
    FULLSCREEN_MODE("of.options.FULLSCREEN_MODE", true, false, 0.0F, (float)BuildManager.getDisplayModeArray().length, 1.0F),
    ANIMATED_TERRAIN("of.options.ANIMATED_TERRAIN", false, false),
    SWAMP_COLORS("of.options.SWAMP_COLORS", false, false),
    RANDOM_ENTITIES("of.options.RANDOM_ENTITIES", false, false),
    SMOOTH_BIOMES("of.options.SMOOTH_BIOMES", false, false),
    CUSTOM_FONTS("of.options.CUSTOM_FONTS", false, false),
    CUSTOM_COLORS("of.options.CUSTOM_COLORS", false, false),
    SHOW_CAPES("of.options.SHOW_CAPES", false, false),
    CONNECTED_TEXTURES("of.options.CONNECTED_TEXTURES", false, false),
    CUSTOM_ITEMS("of.options.CUSTOM_ITEMS", false, false),
    AA_LEVEL("of.options.AA_LEVEL", true, false, 0.0F, 16.0F, 1.0F),
    AF_LEVEL("of.options.AF_LEVEL", true, false, 1.0F, 16.0F, 1.0F),
    ANIMATED_TEXTURES("of.options.ANIMATED_TEXTURES", false, false),
    NATURAL_TEXTURES("of.options.NATURAL_TEXTURES", false, false),
    EMISSIVE_TEXTURES("of.options.EMISSIVE_TEXTURES", false, false),
    HELD_ITEM_TOOLTIPS("of.options.HELD_ITEM_TOOLTIPS", false, false),
    DROPPED_ITEMS("of.options.DROPPED_ITEMS", false, false),
    LAZY_CHUNK_LOADING("of.options.LAZY_CHUNK_LOADING", false, false),
    CUSTOM_SKY("of.options.CUSTOM_SKY", false, false),
    FAST_MATH("of.options.FAST_MATH", false, false),
    FAST_RENDER("of.options.FAST_RENDER", false, false),
    TRANSLUCENT_BLOCKS("of.options.TRANSLUCENT_BLOCKS", false, false),
    DYNAMIC_FOV("of.options.DYNAMIC_FOV", false, false),
    DYNAMIC_LIGHTS("of.options.DYNAMIC_LIGHTS", false, false),
    ALTERNATE_BLOCKS("of.options.ALTERNATE_BLOCKS", false, false),
    CUSTOM_ENTITY_MODELS("of.options.CUSTOM_ENTITY_MODELS", false, false),
    ADVANCED_TOOLTIPS("of.options.ADVANCED_TOOLTIPS", false, false),
    SCREENSHOT_SIZE("of.options.SCREENSHOT_SIZE", false, false),
    CUSTOM_GUIS("of.options.CUSTOM_GUIS", false, false),
    RENDER_REGIONS("of.options.RENDER_REGIONS", false, false),
    SHOW_GL_ERRORS("of.options.SHOW_GL_ERRORS", false, false),
    SMART_ANIMATIONS("of.options.SMART_ANIMATIONS", false, false);

   public final boolean enabled;
   public final boolean enabled2;
   public final String labelText;
   public final float factor3;
   public final float factor;
   public float factor2;
   public static final FactoryOptionType[] ENUM_VALUES = new FactoryOptionType[]{
      INVERT_MOUSE,
      SENSITIVITY,
      FOV,
      GAMMA,
      SATURATION,
      RENDER_DISTANCE,
      VIEW_BOBBING,
      ANAGLYPH,
      FRAMERATE_LIMIT,
      FBO_ENABLE,
      RENDER_CLOUDS,
      GRAPHICS,
      AMBIENT_OCCLUSION,
      GUI_SCALE,
      PARTICLES,
      CHAT_VISIBILITY,
      CHAT_COLOR,
      CHAT_LINKS,
      CHAT_OPACITY,
      CHAT_LINKS_PROMPT,
      SNOOPER_ENABLED,
      USE_FULLSCREEN,
      ENABLE_VSYNC,
      USE_VBO,
      TOUCHSCREEN,
      CHAT_SCALE,
      CHAT_WIDTH,
      CHAT_HEIGHT_FOCUSED,
      CHAT_HEIGHT_UNFOCUSED,
      MIPMAP_LEVELS,
      FORCE_UNICODE_FONT,
      STREAM_BYTES_PER_PIXEL,
      STREAM_VOLUME_MIC,
      STREAM_VOLUME_SYSTEM,
      STREAM_KBPS,
      STREAM_FPS,
      STREAM_COMPRESSION,
      STREAM_SEND_METADATA,
      STREAM_CHAT_ENABLED,
      STREAM_CHAT_USER_FILTER,
      STREAM_MIC_TOGGLE_BEHAVIOR,
      BLOCK_ALTERNATIVES,
      REDUCED_DEBUG_INFO,
      ENTITY_SHADOWS,
      FOG_FANCY,
      FOG_START,
      MIPMAP_TYPE,
      SMOOTH_FPS,
      CLOUDS,
      CLOUD_HEIGHT,
      TREES,
      RAIN,
      ANIMATED_WATER,
      ANIMATED_LAVA,
      ANIMATED_FIRE,
      ANIMATED_PORTAL,
      AO_LEVEL,
      LAGOMETER,
      SHOW_FPS,
      AUTOSAVE_TICKS,
      BETTER_GRASS,
      ANIMATED_REDSTONE,
      ANIMATED_EXPLOSION,
      ANIMATED_FLAME,
      ANIMATED_SMOKE,
      WEATHER,
      SKY,
      STARS,
      SUN_MOON,
      VIGNETTE,
      CHUNK_UPDATES,
      CHUNK_UPDATES_DYNAMIC,
      TIME,
      CLEAR_WATER,
      SMOOTH_WORLD,
      VOID_PARTICLES,
      WATER_PARTICLES,
      RAIN_SPLASH,
      PORTAL_PARTICLES,
      POTION_PARTICLES,
      FIREWORK_PARTICLES,
      PROFILER,
      DRIPPING_WATER_LAVA,
      BETTER_SNOW,
      FULLSCREEN_MODE,
      ANIMATED_TERRAIN,
      SWAMP_COLORS,
      RANDOM_ENTITIES,
      SMOOTH_BIOMES,
      CUSTOM_FONTS,
      CUSTOM_COLORS,
      SHOW_CAPES,
      CONNECTED_TEXTURES,
      CUSTOM_ITEMS,
      AA_LEVEL,
      AF_LEVEL,
      ANIMATED_TEXTURES,
      NATURAL_TEXTURES,
      EMISSIVE_TEXTURES,
      HELD_ITEM_TOOLTIPS,
      DROPPED_ITEMS,
      LAZY_CHUNK_LOADING,
      CUSTOM_SKY,
      FAST_MATH,
      FAST_RENDER,
      TRANSLUCENT_BLOCKS,
      DYNAMIC_FOV,
      DYNAMIC_LIGHTS,
      ALTERNATE_BLOCKS,
      CUSTOM_ENTITY_MODELS,
      ADVANCED_TOOLTIPS,
      SCREENSHOT_SIZE,
      CUSTOM_GUIS,
      RENDER_REGIONS,
      SHOW_GL_ERRORS,
      SMART_ANIMATIONS
   };

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public float computeFactor(float factor4) {
      return MathHelper.clamp_float((this.computeFactor3(factor4) - this.factor) / (this.factor2 - this.factor), 0.0F, 1.0F);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static float computeFactor(FactoryOptionType factoryOptionType) {
      return factoryOptionType.factor2;
   }

   private float computeFactorByFloat(float factor4) {
      float factor5 = factor4;
      if (this.factor3 > 0.0F) {
         factor5 = this.factor3 * (float)Math.round(factor4 / this.factor3);
      }

      return factor5;
   }

   public int ordinalAsIndex() {
      return this.ordinal();
   }

   public float computeFactor3(float factor4) {
      float factor5 = this.computeFactorByFloat(factor4);
      return MathHelper.clamp_float(factor5, this.factor, this.factor2);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public static float computeFactorByFactoryOptionTyp(FactoryOptionType factoryOptionType) {
      return factoryOptionType.factor;
   }

   public static FactoryOptionType resolveFactoryOptionType(int count) {
      for (FactoryOptionType factoryOptionType : values()) {
         if (factoryOptionType.ordinalAsIndex() == count) {
            return factoryOptionType;
         }
      }

      return null;
   }

   public float computeFactor2(float factor4) {
      return this.computeFactor3(this.factor + (this.factor2 - this.factor) * MathHelper.clamp_float(factor4, 0.0F, 1.0F));
   }

   public void setFactor2(float factor4) {
      this.factor2 = factor4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   private FactoryOptionType(String text, boolean flag, boolean flag2) {
      this(text, flag, flag2, 0.0F, 1.0F, 0.0F);
   }

   private FactoryOptionType(String text, boolean flag, boolean flag2, float posX, float posY, float posZ) {
      this.labelText = text;
      this.enabled = flag;
      this.enabled2 = flag2;
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
   }


}
