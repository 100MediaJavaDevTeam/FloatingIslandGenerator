package com.mystic.floatingislandgenerator.client.gui;

import com.mystic.floatingislandgenerator.util.reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class FloatingIslandGUI extends GuiScreen {

   public final ResourceLocation texture = new ResourceLocation(reference.MODID, "textures/gui/islandgui.png");
   public final int guiHeight = 217;
   public final int guiWidth = 191;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(0, 0, 0 , 0 , guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
