package remelon.cat.autogg.config

import com.google.gson.annotations.Expose

data class Config(
    @Expose var enabled: Boolean = true,
    @Expose var message: String = "gg"
) {
    fun validate(): Boolean {
        if (message.isBlank()) message = "gg"
        return true
    }
}
