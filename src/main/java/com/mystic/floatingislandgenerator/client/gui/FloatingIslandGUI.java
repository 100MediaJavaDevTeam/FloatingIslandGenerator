package com.mystic.floatingislandgenerator.client.gui;

import com.mystic.floatingislandgenerator.util.reference;
import com.teamwizardry.librarianlib.features.facade.GuiBase;
import com.teamwizardry.librarianlib.features.facade.layout.StackComponent;
import com.teamwizardry.librarianlib.features.facade.layout.StackLayoutBuilder;
import com.teamwizardry.librarianlib.features.facade.provided.pastry.components.PastryScrollPane;
import com.teamwizardry.librarianlib.features.facade.provided.pastry.components.ScrollPane;
import com.teamwizardry.librarianlib.features.math.Vec2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class FloatingIslandGUI extends GuiBase {

   public final ResourceLocation texture = new ResourceLocation(reference.MODID, "textures/gui/islandgui.png");
   public final int guiHeight = 217;
   public final int guiWidth = 191;



    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//
//
//        drawDefaultBackground();
//        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
//        drawTexturedModalRect(0, 0, 0 , 0 , guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();

        getMain().setSize(new Vec2d(200, 400));

        PastryScrollPane pane = new PastryScrollPane(0, 0, 200, 400);

        StackComponent builder = new StackLayoutBuilder(0.0E00, 0.0E00).vertical().component();

        builder.add();

        getMain().add(pane);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
