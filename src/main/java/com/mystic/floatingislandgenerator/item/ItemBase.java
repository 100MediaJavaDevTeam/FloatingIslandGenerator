package com.mystic.floatingislandgenerator.item;



import com.mystic.floatingislandgenerator.Main;
import com.mystic.floatingislandgenerator.init.ModItems;
import com.mystic.floatingislandgenerator.util.IHasModel;
import com.mystic.floatingislandgenerator.util.reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) 
	{
		setTranslationKey(reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		this.maxStackSize = 16;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(this, 0);
	}
	
}
