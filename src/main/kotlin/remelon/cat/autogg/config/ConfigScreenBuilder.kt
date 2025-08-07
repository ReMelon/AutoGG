package remelon.cat.autogg.gui

import remelon.cat.autogg.config.ConfigManager
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

object ConfigScreenBuilder {

    fun build(parent: Screen?): Screen {
        val config = ConfigManager.config
        val defaults = ConfigManager.defaults

        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.literal("AutoGG Config"))
            .transparentBackground()
            .setSavingRunnable { ConfigManager.write() }

        val entryBuilder = builder.entryBuilder()
        val category = builder.getOrCreateCategory(Text.literal("General"))

        category.addEntry(
            entryBuilder
                .startBooleanToggle(Text.literal("Enable AutoGG"), config.enabled)
                .setDefaultValue(defaults.enabled)
                .setTooltip(Text.literal("Toggle AutoGG on or off"))
                .setSaveConsumer { config.enabled = it }
                .build()
        )

        category.addEntry(
            entryBuilder
                .startStrField(Text.literal("Custom Message"), config.message)
                .setDefaultValue(defaults.message)
                .setTooltip(Text.literal("Set the message AutoGG will send"))
                .setSaveConsumer { config.message = it }
                .build()
        )

        return builder.build()
    }

    fun open() {
        MinecraftClient.getInstance().setScreen(build(MinecraftClient.getInstance().currentScreen))
    }
}
