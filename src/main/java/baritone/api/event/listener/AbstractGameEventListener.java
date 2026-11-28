package baritone.api.event.listener;

import baritone.api.event.events.BlockInteractEvent;
import baritone.api.event.events.ChatEvent;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.PacketEvent;
import baritone.api.event.events.PathEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.events.SprintStateEvent;
import baritone.api.event.events.TabCompleteEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.events.BlockChangeEvent;

public interface AbstractGameEventListener extends IGameEventListener {
   @Override
   default void onRenderPass(RenderEvent renderEvent) {
   }

   @Override
   default void onTick(TickEvent tickEvent) {
   }

   @Override
   default void onPlayerDeath() {
   }

   @Override
   default void onBlockInteract(BlockInteractEvent blockInteractEvent) {
   }

   @Override
   default void onSendPacket(PacketEvent packetEvent) {
   }

   @Override
   default void onPlayerSprintState(SprintStateEvent sprintStateEvent) {
   }

   @Override
   default void onBlockChange(BlockChangeEvent blockChangeEvent) {
   }

   @Override
   default void onChunkEvent(ChunkEvent chunkEvent) {
   }

   @Override
   default void onReceivePacket(PacketEvent packetEvent) {
   }

   @Override
   default void onPathEvent(PathEvent pathEvent) {
   }

   @Override
   default void onSendChatMessage(ChatEvent chatEvent) {
   }

   @Override
   default void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
   }

   @Override
   default void onWorldEvent(WorldEvent worldEvent) {
   }

   @Override
   default void onPreTabComplete(TabCompleteEvent tabCompleteEvent) {
   }

   @Override
   default void onPostTick(TickEvent tickEvent) {
   }
}
