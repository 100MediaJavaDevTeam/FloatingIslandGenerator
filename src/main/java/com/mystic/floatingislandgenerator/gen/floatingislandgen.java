package com.mystic.floatingislandgenerator.gen;

import com.mystic.floatingislandgenerator.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.noise.module.source.Perlin;
import org.spongepowered.noise.module.source.Voronoi;

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
        int randInt = 2/*random.nextInt(3)*/;
        Voronoi voronoi = new Voronoi();
        Perlin perlin = new Perlin();
        if (!event.getWorld().isRemote && event.getEntityPlayer().getHeldItem(event.getHand()).getItem() == ModItems.MAGIC_STICK) {
            Vec3d pos1 = event.getEntityPlayer().getPositionEyes(0);
            Vec3d vec3d = event.getEntityPlayer().getLookVec().scale(diameter + radius).add(pos1);
            BlockPos pos2 = new BlockPos(vec3d.x, vec3d.y, vec3d.z);

            switch (randInt) {
                case 0:
                    voronoi.setFrequency(0.2);
                    for (double x = -diameter - 2; x <= diameter + 2; x++) {

                        for (double y = -diameter - 2; y <= diameter + 2; y++) {

                            for (double z = -diameter - 2; z <= diameter + 2; z++) {
                                double squareNoise1 = voronoi.getValue(x, y, z) * 12 - 6;
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
                                            double squareNoise2 = voronoi.getValue(x, y, z) * 12 - 6;
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

                    voronoi.setFrequency(0.2);
                    for (double x = -radius - 2; x <= radius + 8; x++) {

                        for (double y = -diameter - 2; y <= diameter + 8; y++) {

                            for (double z = -radius - 2; z <= radius + 8; z++) {
                                double squareNoise1 = voronoi.getValue(x, y, z) * 12 - 6;
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
                }
            }
        }
    }
