package net.youtunity.devathlon.kit;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.youtunity.devathlon.spell.Spell;
import net.youtunity.devathlon.spell.SpellMeta;
import net.youtunity.devathlon.user.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by thecrealm on 16.07.16.
 */
public class Kit {

    private Map<Integer, Class<?>> availableSpells = Maps.newHashMap();

    public void addSpell(Class<? extends Spell> spell, int slot) {
        availableSpells.put(slot, spell);
    }


    public Spell createSpell(int index) {

        Class<?> raw = availableSpells.get(index);
        try {
            return ((Spell) raw.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void giveItems(User user) {

        availableSpells.forEach((integer, spell) -> {
            PlayerInventory inventory = user.getPlayer().getInventory();
            int offset = 36;
            SpellMeta meta = spell.getAnnotation(SpellMeta.class);
            inventory.setItem(offset + integer, new ItemStack(meta.material()));

        });
    }
}
