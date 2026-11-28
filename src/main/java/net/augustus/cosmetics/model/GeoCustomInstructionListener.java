package net.augustus.cosmetics.model;

@FunctionalInterface
public interface GeoCustomInstructionListener {
   void executeInstruction(CustomInstructionKeyframeEvent event);
}
