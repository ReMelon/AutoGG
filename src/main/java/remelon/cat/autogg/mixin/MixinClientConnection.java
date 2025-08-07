package remelon.cat.autogg.mixin;

import remelon.cat.autogg.Autogg;
import remelon.cat.autogg.ServerConfig;
import remelon.cat.autogg.chat.EventDetector;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void onDisconnect(Text disconnectReason, CallbackInfo ci) {
        if (Autogg.INSTANCE.client.getCurrentServer() == ServerConfig.MINEPLEX) {
            EventDetector.Companion.setMineplexStart(false);
        }
    }
}
