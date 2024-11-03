package net.lrsoft.foxbladeex;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.flammpfeil.slashblade.SlashBlade;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        SlashBlade.InitEventBus.register(new ItemSlashblade_foxex());
        SlashBlade.InitEventBus.register(new ItemSlashblade_foxex2());
        SlashBlade.InitEventBus.register(new ItemSlashblade_finalfox());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
