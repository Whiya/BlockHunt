package tokyo.ramune.blockhunt.game.item;

import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GameItemBuilder {
    private ItemStack itemStack;
    private Consumer<? super PlayerInteractEvent> clickItemConsumer;

    public GameItemBuilder() {
        this.itemStack = new ItemStack(Material.GRASS_BLOCK);
        this.clickItemConsumer = (event) -> {
        };
    }

    public GameItemBuilder setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public GameItemBuilder setClickItemConsumer(Consumer<? super PlayerInteractEvent> clickItemConsumer) {
        this.clickItemConsumer = clickItemConsumer;
        return this;
    }

    public GameItem build() {
        return new GameItem() {
            public ItemStack getItemStack() {
                return GameItemBuilder.this.itemStack;
            }

            public void onClick(PlayerInteractEvent event) {
                GameItemBuilder.this.clickItemConsumer.accept(event);
            }
        };
    }
}
