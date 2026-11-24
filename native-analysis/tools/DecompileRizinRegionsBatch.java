// Decompile Rizin address-window slices while restoring every temporary edge patch.
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressSet;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionManager;
import ghidra.program.model.listing.Listing;
import ghidra.program.model.mem.Memory;
import ghidra.program.model.mem.MemoryBlock;
import ghidra.program.model.symbol.SourceType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DecompileRizinRegionsBatch extends GhidraScript {
    private static class Block {
        long address;
        int size;
        long region;
        long jump;
        long fail;
    }

    private static class Patch {
        Address address;
        Address end;
        MemoryBlock memoryBlock;
        byte originalByte;
        boolean wasWritable;
    }

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 5 && args.length != 6) {
            throw new IllegalArgumentException(
                "Usage: DecompileRizinRegionsBatch.java <blocks.csv> <first-region> <last-region> <output-dir> <max-instructions> [function-entry]"
            );
        }
        Path blocksPath = Path.of(args[0]);
        long firstRegion = Long.parseLong(args[1]);
        long lastRegion = Long.parseLong(args[2]);
        Path outputDir = Path.of(args[3]);
        int maxInstructions = Integer.parseInt(args[4]);
        long functionEntry = args.length == 6 ? Long.decode(args[5]) : 0x18001f120L;
        Map<Long, List<Block>> byRegion = new HashMap<>();
        Map<Long, Block> byAddress = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(blocksPath)) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",", -1);
                Block block = new Block();
                block.region = Long.parseLong(fields[0]);
                block.address = Long.decode(fields[1]);
                block.size = Integer.parseInt(fields[2]);
                block.jump = fields[4].isEmpty() ? -1 : Long.decode(fields[4]);
                block.fail = fields[5].isEmpty() ? -1 : Long.decode(fields[5]);
                byRegion.computeIfAbsent(block.region, ignored -> new ArrayList<>()).add(block);
                byAddress.put(block.address, block);
            }
        }
        Files.createDirectories(outputDir);
        Path summaryPath = outputDir.resolve("summary.tsv");
        boolean writeHeader = !Files.exists(summaryPath) || Files.size(summaryPath) == 0;
        try (BufferedWriter summary = Files.newBufferedWriter(summaryPath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            if (writeHeader) summary.write("region\tentry\tblocks\tstatus\tmessage\tfile\n");
            FunctionManager manager = currentProgram.getFunctionManager();
            Function original = manager.getFunctionAt(toAddr(functionEntry));
            if (original == null) {
                throw new IllegalStateException("Original function not found at " + Long.toHexString(functionEntry));
            }
            manager.removeFunction(original.getEntryPoint());
            Memory memory = currentProgram.getMemory();
            Listing listing = currentProgram.getListing();
            for (long regionId = firstRegion; regionId <= lastRegion; regionId++) {
                monitor.checkCancelled();
                List<Block> selected = byRegion.getOrDefault(regionId, Collections.emptyList());
                if (selected.isEmpty()) {
                    continue;
                }
                AddressSet body = new AddressSet();
                Address entry = null;
                Set<Long> starts = new java.util.HashSet<>();
                for (Block block : selected) {
                    Address start = toAddr(block.address);
                    body.addRange(start, toAddr(block.address + block.size - 1));
                    starts.add(block.address);
                    if (entry == null || start.compareTo(entry) < 0) {
                        entry = start;
                    }
                }
                String functionName = String.format("region_%05d_%x", regionId, entry.getOffset());
                String fileName = String.format("region_%05d_%x.c", regionId, entry.getOffset());
                Path outputPath = outputDir.resolve(fileName);
                Function function = null;
                String status = "ERROR";
                String message = "not started";
                List<Patch> patches = new ArrayList<>();
                List<String> edges = new ArrayList<>();
                List<String> patchNotes = new ArrayList<>();
                try {
                    function = manager.createFunction(functionName, null, entry, body, SourceType.USER_DEFINED);
                    if (function == null) {
                        throw new IllegalStateException("Could not create slice function");
                    }
                    Set<Long> targets = new TreeSet<>();
                    for (Block block : selected) {
                        for (long target : new long[] {block.jump, block.fail}) {
                            if (target >= 0 && !starts.contains(target)) {
                                targets.add(target);
                                edges.add(String.format("%#x -> %#x", block.address, target));
                            }
                        }
                    }
                    for (long target : targets) {
                        Block targetBlock = byAddress.get(target);
                        if (targetBlock == null) {
                            continue;
                        }
                        Address targetAddress = toAddr(target);
                        Address targetEnd = toAddr(targetBlock.address + targetBlock.size - 1);
                        MemoryBlock memoryBlock = memory.getBlock(targetAddress);
                        if (memoryBlock == null || !memoryBlock.contains(targetAddress)) {
                            continue;
                        }
                        Patch patch = new Patch();
                        patch.address = targetAddress;
                        patch.end = targetEnd;
                        patch.memoryBlock = memoryBlock;
                        patch.originalByte = memory.getByte(targetAddress);
                        patch.wasWritable = memoryBlock.isWrite();
                        patches.add(patch);
                        listing.clearCodeUnits(targetAddress, targetEnd, false);
                        memoryBlock.setWrite(true);
                        memory.setByte(targetAddress, (byte)0xc3);
                        disassemble(targetAddress);
                        patchNotes.add(String.format("target %#x original byte %#02x replaced by RET",
                            target, patch.originalByte & 0xff));
                    }
                    try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                        writer.write(String.format("/* Synthetic CFG slice from function at %#x; outgoing edges were patched to RET in a temporary project. */\n", functionEntry));
                        writer.write(String.format("/* region=%d entry=%s blocks=%d maxInstructions=%d */\n\n",
                            regionId, entry, selected.size(), maxInstructions));
                        for (String edge : edges) writer.write("/* cut edge: " + edge + " */\n");
                        for (String note : patchNotes) writer.write("/* " + note + " */\n");
                        writer.write("\n");
                        DecompInterface decompiler = new DecompInterface();
                        try {
                            DecompileOptions options = new DecompileOptions();
                            options.setMaxInstructions(maxInstructions);
                            options.setMaxPayloadMBytes(512);
                            decompiler.setOptions(options);
                            decompiler.toggleCCode(true);
                            decompiler.toggleSyntaxTree(false);
                            if (!decompiler.openProgram(currentProgram)) {
                                throw new IllegalStateException("Could not open program in decompiler");
                            }
                            DecompileResults result = decompiler.decompileFunction(function, 0, monitor);
                            if (result.decompileCompleted() && result.getDecompiledFunction() != null) {
                                writer.write(result.getDecompiledFunction().getC());
                                status = "OK";
                                message = "decompiled";
                            } else {
                                message = result.getErrorMessage();
                                writer.write("/* DECOMPILATION FAILED: " + message + " */\n");
                            }
                        } finally {
                            decompiler.dispose();
                        }
                    }
                } catch (Exception e) {
                    message = e.toString().replace('\t', ' ').replace('\n', ' ');
                    try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                        writer.write("/* SLICE FAILED: " + message + " */\n");
                    } catch (Exception ignored) {}
                } finally {
                    for (int i = patches.size() - 1; i >= 0; i--) {
                        Patch patch = patches.get(i);
                        try {
                            listing.clearCodeUnits(patch.address, patch.end, false);
                            memory.setByte(patch.address, patch.originalByte);
                            patch.memoryBlock.setWrite(patch.wasWritable);
                            disassemble(patch.address);
                        } catch (Exception restoreError) {
                            message = message + "; restore failed at " + patch.address + ": " + restoreError;
                            status = "RESTORE_ERROR";
                        }
                    }
                    if (function != null) {
                        try { manager.removeFunction(function.getEntryPoint()); }
                        catch (Exception ignored) {}
                    }
                }
                summary.write(regionId + "\t" + entry + "\t" + selected.size() + "\t" + status + "\t" +
                    message.replace('\t', ' ').replace('\n', ' ') + "\t" + fileName + "\n");
                summary.flush();
                println("Region " + regionId + " " + status + " blocks=" + selected.size() + " " + message);
            }
        }
    }
}
