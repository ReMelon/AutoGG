package cc.flawcra.autogg

import cc.flawcra.autogg.chat.EventDetector
import net.fabricmc.api.ClientModInitializer

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
        client = ClientWrapper()
        detector = EventDetector()
    }
}
