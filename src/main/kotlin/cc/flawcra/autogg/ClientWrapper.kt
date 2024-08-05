package cc.flawcra.autogg

import net.minecraft.client.MinecraftClient;

class ClientWrapper {
    private var client: MinecraftClient? = null

    init {
        this.client = MinecraftClient.getInstance()
    }

    fun getCurrentServer(): ServerConfig? {
        if (client?.currentServerEntry == null) return null
        val address: String = client!!.currentServerEntry!!.address

        for (server in ServerConfig.entries) {
            if (address.contains(server.ip)) return server
        }
        return null
    }

    fun sendMessage(messageToSend: String?) {
        client!!.networkHandler!!.sendChatMessage(messageToSend)
    }
}