package tokyo.ramune.blockhunt.util;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
    String title = "";
    ArrayList<String> lore = new ArrayList();
    Material material;
    int amount;

    public ItemStackBuilder() {
        this.material = Material.GRASS_BLOCK;
        this.amount = 1;
    }

    public ItemStackBuilder(String title, ArrayList<String> lore, Material material, int amount) {
        this.material = Material.GRASS_BLOCK;
        this.amount = 1;
        this.title = title;
        this.lore = lore;
        this.material = material;
        this.amount = amount;
    }

    public ItemStackBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemStackBuilder addLore(String loreLine) {
        this.lore.add(loreLine);
        return this;
    }

    public ItemStackBuilder setLore(ArrayList<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemStackBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.title);
        itemMeta.setLore(this.lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
