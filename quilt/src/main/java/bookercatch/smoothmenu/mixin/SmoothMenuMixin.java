package bookercatch.smoothmenu.mixin;

import com.mojang.blaze3d.platform.FramerateLimitTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FramerateLimitTracker.class)
public abstract class SmoothMenuMixin {
    @Shadow
    private int framerateLimit;

    @Shadow
    public abstract FramerateLimitTracker.FramerateThrottleReason getThrottleReason();

    @Inject(method = "getFramerateLimit", at = @At("HEAD"), cancellable = true)
    private void smoothmenu$uncapOutOfLevelMenu(CallbackInfoReturnable<Integer> cir) {
        if (this.getThrottleReason() == FramerateLimitTracker.FramerateThrottleReason.OUT_OF_LEVEL_MENU) {
            cir.setReturnValue(this.framerateLimit);
        }
    }
}
