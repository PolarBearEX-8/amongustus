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

public interface IGameEventListener {
   void onBlockInteract(BlockInteractEvent blockInteractEvent);

   void onReceivePacket(PacketEvent packetEvent);

   void onTick(TickEvent tickEvent);

   void onPreTabComplete(TabCompleteEvent tabCompleteEvent);

   void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent);

   void onBlockChange(BlockChangeEvent blockChangeEvent);

   void onSendPacket(PacketEvent packetEvent);

   void onPlayerDeath();

   void onChunkEvent(ChunkEvent chunkEvent);

   void onWorldEvent(WorldEvent worldEvent);

   void onRenderPass(RenderEvent renderEvent);

   void onPathEvent(PathEvent pathEvent);

   void onPostTick(TickEvent tickEvent);

   void onPlayerSprintState(SprintStateEvent sprintStateEvent);

   void onSendChatMessage(ChatEvent chatEvent);
}
