package com.dreaminglri.pss_create.mixin;

import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.apache.http.util.Args;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) {
        super(title);
    }
    @Shadow
    private SplashRenderer splash;
    @Shadow
    private static final Component COPYRIGHT_TEXT = Component.translatable("title.credits");
    @Shadow
    private void createNormalMenuOptions(int y, int rowHeight){}

    @Inject(method = "createNormalMenuOptions",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;",
            ordinal = 2),
            cancellable = true)
    private void createNormalMenuOptions(int y, int rowHeight, CallbackInfo ci) {
        ci.cancel();
    }

    @ModifyVariable(method = "init", at = @At(value = "STORE"), ordinal = 3)
    private int modifyL1(int original){
        return original + 24;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;pos(II)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
    private int modifyY1(int original){
        return original - 24;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/SpriteIconButton;setPosition(II)V"), index = 1)
    private int modifyY2(int original){
        return original - 24;
    }
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
    private int modifyY3(int original){
        return original - 24;
    }
}
