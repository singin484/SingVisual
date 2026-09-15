package com.example.singvisuals;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SingVisuals implements ModInitializer {

    public static final String MOD_ID = "singvisuals";

    // Создаём предмет для вашего визуала
    public static final Item VISUAL_ITEM = Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "my_visual"),
            new Item(new Item.Settings())
    );

    @Override
    public void onInitialize() {
        // Добавляем предмет в вкладку творческого режима (Разное / Ingredients)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.add(VISUAL_ITEM);
        });

        // Команда /hello
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    dispatcher.register(
                            CommandManager.literal("hello")
                                    .executes(context -> {
                                        ServerCommandSource source = context.getSource();
                                        source.sendFeedback(
                                                () -> Text.literal("§a[SingVisuals] Привет! Мод и визуалы работают!"),
                                                false
                                        );
                                        return 1;
                                    })
                    );
                }
        );

        System.out.println("SingVisuals успешно загружен!");
    }
}
