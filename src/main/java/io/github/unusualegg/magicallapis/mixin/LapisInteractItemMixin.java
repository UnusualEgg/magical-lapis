package io.github.unusualegg.magicallapis.mixin;

import io.github.unusualegg.magicallapis.CanDespawnAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class LapisInteractItemMixin {
    @Inject(method = "useOnEntity", at = @At(value = "HEAD"))
    private void interactMixin(ItemStack itemStack, LivingEntity entity, CallbackInfo ci) {
        if (!(entity instanceof AnimalEntity)) return;
        System.out.println("Interact: "+itemStack.toString()+", "+entity.id);
        if (itemStack!=null && itemStack.itemId== Item.LEATHER.id && ((CanDespawnAccessor)entity).getCanDespawn_magicallapis()) {
            itemStack.count--;
            ((CanDespawnAccessor)entity).setCanDespawn_magicallapis(false);
            System.out.println(((CanDespawnAccessor)entity).getCanDespawn_magicallapis());
        }
    }
}
