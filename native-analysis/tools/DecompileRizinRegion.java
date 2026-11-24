// Decompile one address-window slice from a Rizin basic-block inventory.
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressSet;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionManager;
import ghidra.program.model.listing.Listing;
import ghidra.program.model.mem.MemoryBlock;
import ghidra.program.model.symbol.SourceType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecompileRizinRegion extends GhidraScript {
    private static class Block {
        long address;
        int size;
        long region;
        long jump;
        long fail;
    }

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length != 4) {
            throw new IllegalArgumentException(
                "Usage: DecompileRizinRegion.java <blocks.csv> <region-index> <output.c> <max-instructions>"
            );
        }
        Path blocksPath = Path.of(args[0]);
        long selectedRegion = Long.parseLong(args[1]);
        Path outputPath = Path.of(args[2]);
        int maxInstructions = Integer.parseInt(args[3]);
        Map<Long, List<Block>> byRegion = new HashMap<>();
        Map<Long, Block> byAddress = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(blocksPath)) {
            String line = reader.readLine(); // header
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
        List<Block> selected = byRegion.getOrDefault(selectedRegion, Collections.emptyList());
        if (selected.isEmpty()) {
            throw new IllegalArgumentException("No basic blocks in region " + selectedRegion);
        }

        FunctionManager manager = currentProgram.getFunctionManager();
        Function original = manager.getFunctionAt(toAddr(0x18001f120L));
        if (original == null) {
            throw new IllegalStateException("Original function not found at 0x18001f120");
        }
        manager.removeFunction(original.getEntryPoint());
        AddressSet regionBody = new AddressSet();
        Address entry = null;
        for (Block block : selected) {
            Address start = toAddr(block.address);
            Address end = toAddr(block.address + block.size - 1);
            regionBody.addRange(start, end);
            if (entry == null || start.compareTo(entry) < 0) {
                entry = start;
            }
        }
        String name = String.format("region_%05d_%x", selectedRegion, entry.getOffset());
        Function region = manager.createFunction(name, null, entry, regionBody, SourceType.USER_DEFINED);
        if (region == null) {
            throw new IllegalStateException("Could not create synthetic function for region " + selectedRegion);
        }
        java.util.Set<Long> boundaryTargets = new java.util.TreeSet<>();
        java.util.Set<Long> selectedStarts = new java.util.HashSet<>();
        for (Block block : selected) {
            selectedStarts.add(block.address);
        }
        List<String> boundaryEdges = new ArrayList<>();
        Listing listing = currentProgram.getListing();
        List<String> patchNotes = new ArrayList<>();
        for (Block block : selected) {
            for (long target : new long[] {block.jump, block.fail}) {
                if (target >= 0 && !selectedStarts.contains(target)) {
                    boundaryTargets.add(target);
                    boundaryEdges.add(String.format("%#x -> %#x", block.address, target));
                }
            }
        }
        for (long target : boundaryTargets) {
            if (byAddress.get(target) == null) {
                continue;
            }
            Address targetAddress = toAddr(target);
            MemoryBlock block = currentProgram.getMemory().getBlock(targetAddress);
            if (block == null || !block.contains(targetAddress)) {
                continue;
            }
            byte originalByte = currentProgram.getMemory().getByte(targetAddress);
            Block targetBlock = byAddress.get(target);
            Address targetEnd = targetBlock == null ? targetAddress :
                toAddr(targetBlock.address + targetBlock.size - 1);
            listing.clearCodeUnits(targetAddress, targetEnd, false);
            block.setWrite(true);
            currentProgram.getMemory().setByte(targetAddress, (byte)0xc3);
            disassemble(targetAddress);
            patchNotes.add(String.format("target %#x original byte %#02x replaced by RET",
                target, originalByte & 0xff));
        }
        println("Created " + region.getName() + ": blocks=" + selected.size() +
            " bodyBytes=" + region.getBody().getNumAddresses() +
            " bodyRanges=" + region.getBody().getNumAddressRanges() +
            " boundaryTargets=" + boundaryTargets.size() + " patchedTargets=" + patchNotes.size() +
            " totalFunctions=" + manager.getFunctionCount());
        outputPath.getParent().toFile().mkdirs();
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("/* Synthetic CFG slice from net/40U.49(J)V; exits below were patched to RET in this temporary Ghidra project. */\n");
            writer.write(String.format("/* region=%d entry=%s blocks=%d maxInstructions=%d */\n\n",
                selectedRegion, region.getEntryPoint(), selected.size(), maxInstructions));
            for (String edge : boundaryEdges) {
                writer.write("/* cut edge: " + edge + " */\n");
            }
            for (String note : patchNotes) {
                writer.write("/* " + note + " */\n");
            }
            writer.write("\n");
            DecompOptionsAndWrite(region, maxInstructions, writer);
        }
    }

    private void DecompOptionsAndWrite(Function function, int maxInstructions, BufferedWriter writer) throws Exception {
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
                println("Decompiled region " + function.getName());
            } else {
                writer.write("/* DECOMPILATION FAILED: " + result.getErrorMessage() + " */\n");
                println("Region failure: " + result.getErrorMessage());
            }
        } finally {
            decompiler.dispose();
        }
    }
}
