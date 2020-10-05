package com.mystic.floatingislandgenerator.util.handlers;

import com.mystic.floatingislandgenerator.gen.floatingislandgen;
import com.mystic.floatingislandgenerator.init.ModItems;
import com.mystic.floatingislandgenerator.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{

		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new floatingislandgen());
	}
	
	public static void initRegistries(FMLInitializationEvent event)
	{}
	
	public static void postInitRegistries(FMLPostInitializationEvent event)
	{}
	
}