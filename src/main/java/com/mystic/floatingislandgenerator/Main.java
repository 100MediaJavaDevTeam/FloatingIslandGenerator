package com.mystic.floatingislandgenerator;

import com.mystic.floatingislandgenerator.proxy.CommonProxy;
import com.mystic.floatingislandgenerator.util.handlers.RegistryHandler;
import com.mystic.floatingislandgenerator.util.reference;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

@Mod(modid = reference.MODID, name = reference.NAME, version = reference.VERSION)
public class Main {

    @Mod.Instance
    public static Main instance;

    public static Logger logger;

    @SidedProxy(clientSide = reference.CLIENT_PROXY_CLASS, serverSide = reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        RegistryHandler.postInitRegistries(event);
    }

}
