package tokyo.ramune.blockhunt.menu;

import tokyo.ramune.blockhunt.menu.item.MenuCloseItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuHandler {

    private static final ArrayList<MenuItem> menuItems = new ArrayList<>(
            Arrays.asList(new MenuCloseItem())
    );

    public static void openInventory() {

    }
}
