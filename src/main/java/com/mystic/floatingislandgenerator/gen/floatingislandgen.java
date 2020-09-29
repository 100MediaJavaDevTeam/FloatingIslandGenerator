package com.mystic.floatingislandgenerator.gen;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.noise.module.source.Perlin;

@Mod.EventBusSubscriber
public class floatingislandgen {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.isCanceled()) {
            return;
        }
        int radius = 11;
        int size = radius / 3;
        if (!event.getWorld().isRemote && event.getEntityPlayer().getHeldItem(event.getHand()).getItem() == Items.STICK) {
            Vec3d pos1 = event.getEntityPlayer().getPositionEyes(0);
            Vec3d vec3d = event.getEntityPlayer().getLookVec().scale(radius + 5).add(pos1);
            BlockPos pos2 = new BlockPos(vec3d.x, vec3d.y, vec3d.z);

            Perlin perlin = new Perlin();
            perlin.setFrequency(0.2);
            for (int x = -radius - 2; x <= radius + 2; x++) {

                for (int y = -radius - 2; y <= radius + 2; y++) {

                    for (int z = -radius - 2; z <= radius + 2; z++) {
                        double squareNoise1 = perlin.getValue(x, y, z) * 12 - 6;
                        double distanceSqt1 = x * x + y * y + z * z + squareNoise1 * squareNoise1;
                        if (distanceSqt1 <= radius * radius) {
                            if (y <= 1) {
                                event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.GRASS.getDefaultState());
                                if (y <= 0) {
                                    event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.DIRT.getDefaultState());
                                    if (y <= -3) {
                                        event.getWorld().setBlockState(pos2.add(x, y, z), Blocks.STONE.getDefaultState());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // code for the bottom of the island!!!
            for (int x = -size; x <= 0; x++) {
                for (int y = -size; y <= 0; y++) {
                    for (int z = -size; z <= 0; z++) {
                        for (int w = -radius - 2; w <= radius + 2; w++) {
                            for (int v = -radius - 2; v <= radius + 2; v++) {
                                for (int u = -radius - 2; u <= radius + 2; u++) {
                                    double squareNoise2 = perlin.getValue(x, y, z) * 12 - 6;
                                    double distanceSqt2 = x * x + y * y + z * z + squareNoise2 * squareNoise2;
                                    if (distanceSqt2 <= radius * (size + 2)) {
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
        }
    }
}
