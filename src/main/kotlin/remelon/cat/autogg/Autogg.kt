package remelon.cat.autogg

import remelon.cat.autogg.chat.EventDetector
import net.fabricmc.api.ClientModInitializer
import remelon.cat.autogg.config.ConfigManager

class Autogg : ClientModInitializer {

    companion object {
        lateinit var INSTANCE: Autogg
    }

    init {
        INSTANCE = this
    }

    lateinit var client: ClientWrapper
    lateinit var detector: EventDetector

    override fun onInitializeClient() {
        ConfigManager.init() // ← load config here
        client = ClientWrapper()
        detector = EventDetector()
    }

}
