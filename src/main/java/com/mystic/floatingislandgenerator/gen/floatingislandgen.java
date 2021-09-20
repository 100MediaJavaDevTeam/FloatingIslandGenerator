package com.mystic.floatingislandgenerator.gen;

import com.mystic.floatingislandgenerator.init.ModItems;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.noise.module.source.Perlin;

import java.util.Random;

@Mod.EventBusSubscriber
public class floatingislandgen {

    public static Random random = new Random();

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.isCanceled()) {
            return;
        }

        double diameter = 11;
        double size = diameter / 3;
        double radius = diameter / 2;
        int randInt = 5/*random.nextInt(6)*/;
        final IBlockState DEFAULT_TRUNK = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
        final IBlockState DEFAULT_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
        Perlin perlin = new Perlin();
        if (!event.getWorld().isRemote && event.getEntityPlayer().getHeldItem(event.getHand()).getItem() == ModItems.MAGIC_STICK) {
            Vec3d pos1 = event.getEntityPlayer().getPositionEyes(0);
            Vec3d vec3d = event.getEntityPlayer().getLookVec().scale(diameter + radius).add(pos1);
            BlockPos pos2 = new BlockPos(vec3d.x, vec3d.y, vec3d.z);

            switch (1) {
                case 0:
                    perlin.setFrequency(0.2);
                    for (double x = -diameter - 2; x <= diameter + 2; x++) {

                        for (double y = -diameter - 2; y <= diameter + 2; y++) {

                            for (double z = -diameter - 2; z <= diameter + 2; z++) {
                                double squareNoise1 = perlin.getValue(x, y, z) * 12 - 6;
                                double distanceSqt1 = x * x + y * y + z * z + squareNoise1 * squareNoise1;
                                if (distanceSqt1 <= diameter * diameter) {
                                    if (y <= 2) {
                                        new WorldGenTrees(false, 7, DEFAULT_TRUNK, DEFAULT_LEAF, false).generate(event.getWorld(), random, pos2);
                                        if (y <= 1) {
                                            event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.GRASS.getDefaultState());
                                            if (y <= 0) {
                                                event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.DIRT.getDefaultState());
                                                if (y <= -2) {
                                                    event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.STONE.getDefaultState());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // code for the bottom of the island!!!
                    for (double x = -size; x <= 0; x++) {
                        for (double y = -size; y <= 0; y++) {
                            for (double z = -size; z <= 0; z++) {
                                double squareNoise2 = perlin.getValue(x, y, z) * 12 - 6;
                                double distanceSqt2 = x * x + y * y + z * z + squareNoise2 * squareNoise2;
                                if (distanceSqt2 <= diameter * (size + 2)) {
                                    if (y <= 1 && y >= -1) {
                                        if (x <= 1 && x >= -2) {
                                            if (z <= 1 && z >= -2) {
                                                event.getWorld().setBlockState(pos2.add(x + 1, y - 9, z + 1), Blocks.STONE.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    perlin.setFrequency(0.2);
                    for (double x = -diameter - 2; x <= diameter + 2; x++) {
                        for (double y = -diameter - 2; y <= diameter + 11; y++) {
                            for (double z = -diameter - 2; z <= diameter + 2; z++) {
                                double noise = perlin.getValue(x, y, z) * 12;
                                double scaledNoise = (noise / 11) * ((y * 3) / ((x * x) + (z * z)));
                                if (scaledNoise >= 0.5) {
                                    if (y >= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.STONE.getDefaultState());
                                        if (y >= 20) {
                                            event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.DIRT.getDefaultState());
                                            if (y >= 22) {
                                                event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.GRASS.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 2:

                    perlin.setFrequency(0.2);
                    for (double x = -radius - 2; x <= radius + 8; x++) {

                        for (double y = -diameter - 2; y <= diameter + 8; y++) {

                            for (double z = -radius - 2; z <= radius + 8; z++) {
                                double squareNoise1 = perlin.getValue(x, y, z) * 12 - 6;
                                double distanceSqt1 = x * x + y * y + z * z + squareNoise1 * squareNoise1;
                                if (distanceSqt1 <= radius * diameter) {
                                    if (y >= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.DIRT.getDefaultState());
                                        if (y >= 2) {
                                            event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.GRASS.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    perlin.setFrequency(0.2);
                    for (double x = -diameter - 2; x <= diameter + 2; x++) {
                        for (double y = -diameter - 2; y <= diameter + 11; y++) {
                            for (double z = -diameter - 2; z <= diameter + 2; z++) {
                                double noise = perlin.getValue(x, y, z) * 12;
                                double scaledNoise = (noise / 11) * ((y * 3) / ((x * x) + (z * z)));
                                if (scaledNoise >= 0.5) {
                                    if (y >= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.STONE.getDefaultState());
                                        if (y >= 20) {
                                            event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.DIRT.getDefaultState());
                                            if (y >= 22) {
                                                event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.GRASS.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    perlin.setFrequency(0.2);
                    for (double z = -radius; z <= radius - 7; z++) {
                        double x = 0;
                        double y = 23;
                        double noise = perlin.getValue(x, y, z) * 12;
                        double scaledNoise = noise + x + y + z;
                        if (scaledNoise >= 0.5) {
                            if (y == 23) {
                                event.getWorld().setBlockState(pos2.add(x, y - 22, z), Blocks.WATER.getDefaultState());
                            }
                        }
                    }

                    break;

                case 3:
                    perlin.setFrequency(0.2);
                    for (double x = -diameter - 2; x <= diameter + 2; x++) {

                        for (double y = -diameter - 2; y <= diameter + 2; y++) {

                            for (double z = -diameter - 2; z <= diameter + 2; z++) {
                                double squareNoise1 = perlin.getValue(x, y, z) * 12 - 6;
                                double distanceSqt1 = x * x + y * y + z * z + squareNoise1 * squareNoise1;
                                if (distanceSqt1 <= diameter * diameter) {
                                    if (y <= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.GRASS.getDefaultState());
                                        if (y <= 0) {
                                            event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.DIRT.getDefaultState());
                                            if (y <= -2) {
                                                event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.STONE.getDefaultState());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // code for the bottom of the island!!!
                    for (double x = -size; x <= 0; x++) {
                        for (double y = -size; y <= 0; y++) {
                            for (double z = -size; z <= 0; z++) {
                                for (double w = -diameter - 2; w <= diameter + 2; w++) {
                                    for (double v = -diameter - 2; v <= diameter + 2; v++) {
                                        for (double u = -diameter - 2; u <= diameter + 2; u++) {
                                            double squareNoise2 = perlin.getValue(x, y, z) * 12 - 6;
                                            double distanceSqt2 = x * x + y * y + z * z + squareNoise2 * squareNoise2;
                                            if (distanceSqt2 <= diameter * (size + 2)) {
                                                if (y <= 1 && y >= -1) {
                                                    if (x <= 1 && x >= -2) {
                                                        if (z <= 1 && z >= -2) {
                                                            event.getWorld().setBlockState(pos2.add(x + 1, y - 9, z + 1), Blocks.STONE.getDefaultState());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (double x = -radius - 2; x <= radius + 2; x++) {

                        for (double y = -size - 2; y <= size + 2; y++) {

                            for (double z = -radius - 2; z <= radius + 2; z++) {
                                double distanceSqt1 = x * x + y * y + z * z;
                                if (distanceSqt1 <= radius * radius) {
                                    if (y <= 2) {
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.WATER.getDefaultState());
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 4:

                    //Volcano (not an island I know but I wanted to try it :) )
                    int volcanoLength = 5;
                    int volcanoDiameter = 30;
                    perlin.setFrequency(0.2);
                    for (double x = -volcanoDiameter - 11; x <= volcanoDiameter + 11; x++) {
                        for (double y = -volcanoDiameter - 11; y <= volcanoDiameter + 2; y++) {
                            for (double z = -volcanoDiameter - 11; z <= volcanoDiameter + 11; z++) {
                                double noise = perlin.getValue(x, y, z) * 12;
                                double scaledNoise = (noise / 11) * (-(y * volcanoLength) / ((x * x) + (z * z)));
                                if (scaledNoise >= 0.5) {
                                    if (y <= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y + 11, z), Blocks.STONE.getDefaultState());
                                    }
                                }
                            }
                        }
                    }

                    perlin.setFrequency(0.2);
                    for (double x = -volcanoDiameter - 10; x <= volcanoDiameter + 10; x++) {
                        for (double y = -volcanoDiameter - 10; y <= volcanoDiameter + 1; y++) {
                            for (double z = -volcanoDiameter - 10; z <= volcanoDiameter + 10; z++) {
                                double noise = perlin.getValue(x, y, z) * 12;
                                double scaledNoise = (noise / 11) * (-(y * (volcanoLength - 1)) / ((x * x) + (z * z)));
                                if (scaledNoise >= 0.5) {
                                    if (y <= 1) {
                                        event.getWorld().setBlockState(pos2.add(x, y + 11, z), Blocks.LAVA.getDefaultState());
                                    }
                                }
                            }
                        }
                    }

                        break;
                case 5:
                    int missleVar = 10; // this height of the missile and the radius of the surrounding stone :) 10 or less should be default!!!
                    for (double x = -missleVar; x <= missleVar; x++) {
                        for (double y = -missleVar; y <= missleVar; y++) {
                            for (double z = -missleVar; z <= missleVar; z++) {
                                double noise = perlin.getValue(x, y, z) * 12;
                                double scaledNoise = (noise / 11) * ((y * 3) / ((((-x * x) + 20) * .5) + (((-z * z) + 20) * .5)));
                                if (scaledNoise <= 1) {
                                    if (x <= -7 || x >= 7 || z <= -7 || z >= 7) {
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.STONE.getDefaultState());
                                    }else{
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.IRON_BLOCK.getDefaultState());
                                    }
                                }
                            }
                        }
                    }
                    break;
                    }
                }
            }
        }

