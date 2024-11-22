package cc.modlabs.autogg.mixin;

import cc.modlabs.autogg.Autogg;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class MixinChatHud {
    @Unique
    private int lastTime = 0;

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("HEAD"))
    private void sendRespect(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci) {
        var event = Autogg.INSTANCE.detector.scanForEvent(message.getString());

        if (event != null) {
            if (System.currentTimeMillis() - lastTime <= 3000) return;
            lastTime = ((int)System.currentTimeMillis());

            Autogg.INSTANCE.client.sendMessage("gg");
        }
    }
}
