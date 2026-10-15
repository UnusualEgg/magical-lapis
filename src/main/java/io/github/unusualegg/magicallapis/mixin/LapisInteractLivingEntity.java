package io.github.unusualegg.magicallapis.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.github.unusualegg.magicallapis.CanDespawnAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public class LapisInteractLivingEntity implements CanDespawnAccessor {
    @Unique
    protected boolean shouldDespawn = true;

    @Override
    public void setCanDespawn_magicallapis(boolean canDespawn) {
        shouldDespawn = canDespawn;
    }

    @Override
    public boolean getCanDespawn_magicallapis() {
        return shouldDespawn;
    }
}
