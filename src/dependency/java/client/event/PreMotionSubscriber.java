package client.event;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.util.ResourceLocation;

public interface PreMotionSubscriber {
   void applyType(Type type, ResourceLocation location, MinecraftProfileTexture minecraftProfileTexture);
}
