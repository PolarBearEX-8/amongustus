package net.augustus.cosmetics.model;

@FunctionalInterface
public interface SoundKeyframeListener {
   void playSound(SoundKeyframeEvent soundKeyframeEvent);
}
