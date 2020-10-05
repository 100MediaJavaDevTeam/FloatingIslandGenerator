package com.mystic.floatingislandgenerator.item;

import java.util.Random;

import com.mystic.floatingislandgenerator.client.gui.FloatingIslandGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemMagicStick extends ItemBase
{
	
	 
	
	public ItemMagicStick(String name)
	{
		super(name);				
	}

	
	Random rand = new Random();
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	        ItemStack itemstack = playerIn.getHeldItem(handIn);

			if(!worldIn.isRemote){
				playerIn.sendMessage(new TextComponentString("Right Clicked"));
			}
			else{
				Minecraft.getMinecraft().displayGuiScreen(new FloatingIslandGUI());
			}
	        if (!playerIn.capabilities.isCreativeMode)
	        {
	        	itemstack.shrink(1);
	        }
	        playerIn.addStat(StatList.getObjectUseStats(this));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	    
	    }     

}
