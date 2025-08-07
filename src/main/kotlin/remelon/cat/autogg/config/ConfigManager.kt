package remelon.cat.autogg.config

import com.google.gson.GsonBuilder
import net.fabricmc.loader.api.FabricLoader
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.StandardCharsets

object ConfigManager {
     val gson = GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create()
     val configFile = File(FabricLoader.getInstance().configDir.toFile(), "autogg.json")

    @JvmField
    var config: Config = Config()
    val defaults = Config()


    fun init() {
        if (configFile.exists()) {
            try {
                val json = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8)
                config = gson.fromJson(json, Config::class.java)
                if (config.validate()) write()
            } catch (e: Exception) {
                e.printStackTrace()
                config = Config()
                write()
            }
        } else {
            write()
        }
    }

    fun write() {
        try {
            val json = gson.toJson(config)
            FileUtils.writeStringToFile(configFile, json, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
