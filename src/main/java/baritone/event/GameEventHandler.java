package baritone.event;

import baritone.Baritone;
import baritone.api.event.events.BlockInteractEvent;
import baritone.api.event.events.ChatEvent;
import baritone.api.cache.IWorldData;
import baritone.api.event.events.ChunkEvent;
import baritone.api.event.events.PacketEvent;
import baritone.api.event.events.PathEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.RenderEvent;
import baritone.api.event.events.SprintStateEvent;
import baritone.api.event.events.TabCompleteEvent;
import baritone.api.event.events.TickEvent;
import baritone.api.event.events.WorldEvent;
import baritone.api.event.events.type.EventState;
import baritone.api.event.listener.IEventBus;
import baritone.api.event.listener.IGameEventListener;
import baritone.api.utils.Helper;
import baritone.cache.CachedChunk;
import baritone.cache.WorldProvider;
import baritone.api.utils.Pair;
import baritone.utils.ChunkPos;
import baritone.api.event.events.BlockChangeEvent;
import baritone.utils.BlockStateInterface;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public final class GameEventHandler implements IEventBus, Helper {
   private final Baritone baritone;
   private final List<IGameEventListener> listeners = new CopyOnWriteArrayList<>();

   @Override
   public final void onSendPacket(PacketEvent packetEvent) {
      this.listeners.forEach(listener -> listener.onSendPacket(packetEvent));
   }

   @Override
   public final void onWorldEvent(WorldEvent worldEvent) {
      WorldProvider worldProvider2 = this.baritone.getWorldProvider2();
      if (worldEvent.getState() == EventState.POST) {
         worldProvider2.closeWorld();
         if (worldEvent.getWorld() != null) {
            worldProvider2.loadWorld(worldEvent.getWorld());
         }
      }

      this.listeners.forEach(listener -> listener.onWorldEvent(worldEvent));
   }

   @Override
   public void onPostTick(TickEvent tickEvent) {
      this.listeners.forEach(listener -> listener.onPostTick(tickEvent));
   }

   @Override
   public void onBlockChange(BlockChangeEvent blockChangeEvent) {
      if ((Boolean)Baritone.settings().repackOnAnyBlockChange.value) {
         if (blockChangeEvent.getBlocks().stream().map(obj -> ((Pair)obj).getSecond()).map(obj2 -> ((IBlockState)obj2).getBlock()).anyMatch(CachedChunk.BLOCKS_TO_KEEP_TRACK_OF::contains)) {
            this.baritone.getWorldProvider2().ifWorldLoaded(obj3 -> {
               World world = this.baritone.getPlayerContext().world();
               ChunkPos chunkPos = blockChangeEvent.getChunkPos();
               ((IWorldData)obj3).getCachedWorld().queueForPacking(world.getChunkFromChunkCoords(chunkPos.x, chunkPos.z));
            });
         }
      }

      this.listeners.forEach(listener -> listener.onBlockChange(blockChangeEvent));
   }

   @Override
   public final void onReceivePacket(PacketEvent packetEvent) {
      this.listeners.forEach(listener -> listener.onReceivePacket(packetEvent));
   }

   @Override
   public void onPreTabComplete(TabCompleteEvent tabCompleteEvent) {
      this.listeners.forEach(listener -> listener.onPreTabComplete(tabCompleteEvent));
   }

   @Override
   public final void onRenderPass(RenderEvent renderEvent) {
      this.listeners.forEach(listener -> listener.onRenderPass(renderEvent));
   }

   @Override
   public void onPlayerSprintState(SprintStateEvent sprintStateEvent) {
      this.listeners.forEach(listener -> listener.onPlayerSprintState(sprintStateEvent));
   }

   public GameEventHandler(Baritone baritone2) {
      this.baritone = baritone2;
   }

   @Override
   public void onChunkEvent(ChunkEvent chunkEvent) {
      EventState state = chunkEvent.getState();
      ChunkEvent.Type type = chunkEvent.getType();
      World world = this.baritone.getPlayerContext().world();
      boolean flag = state == EventState.PRE && type == ChunkEvent.Type.UNLOAD && world.getChunkProvider().chunkExists(chunkEvent.getX(), chunkEvent.getZ());
      if (chunkEvent.isPostPopulate() || flag) {
         this.baritone.getWorldProvider2().ifWorldLoaded(obj -> {
            Chunk chunkFromChunkCoords = world.getChunkFromChunkCoords(chunkEvent.getX(), chunkEvent.getZ());
            ((IWorldData)obj).getCachedWorld().queueForPacking(chunkFromChunkCoords);
         });
      }

      this.listeners.forEach(listener -> listener.onChunkEvent(chunkEvent));
   }

   @Override
   public final void onTick(TickEvent tickEvent) {
      if (tickEvent.getType() == TickEvent.Type.IN) {
         try {
            this.baritone.bsi = new BlockStateInterface(this.baritone.getPlayerContext(), true);
         } catch (Exception e) {
            this.baritone.bsi = null;
         }
      } else {
         this.baritone.bsi = null;
      }

      this.listeners.forEach(listener -> listener.onTick(tickEvent));
   }

   @Override
   public void onPathEvent(PathEvent pathEvent) {
      this.listeners.forEach(listener -> listener.onPathEvent(pathEvent));
   }

   @Override
   public final void registerEventListener(IGameEventListener listener) {
      this.listeners.add(listener);
   }

   @Override
   public final void onSendChatMessage(ChatEvent chatEvent) {
      this.listeners.forEach(listener -> listener.onSendChatMessage(chatEvent));
   }

   @Override
   public final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
      this.listeners.forEach(listener -> listener.onPlayerUpdate(playerUpdateEvent));
   }

   @Override
   public void onPlayerDeath() {
      this.listeners.forEach(IGameEventListener::onPlayerDeath);
   }

   @Override
   public void onBlockInteract(BlockInteractEvent blockInteractEvent) {
      this.listeners.forEach(listener -> listener.onBlockInteract(blockInteractEvent));
   }
}
