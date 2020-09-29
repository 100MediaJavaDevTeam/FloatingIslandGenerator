package com.mystic.floatingislandgenerator.util.handlers;

import com.mystic.floatingislandgenerator.gen.floatingislandgen;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@EventBusSubscriber
public class RegistryHandler 
{
	
	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new floatingislandgen());
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{}
	
}