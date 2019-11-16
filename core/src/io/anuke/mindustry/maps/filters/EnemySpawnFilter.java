package io.anuke.mindustry.maps.filters;

import io.anuke.arc.collection.*;
import io.anuke.arc.util.*;
import io.anuke.mindustry.content.*;
import io.anuke.mindustry.maps.filters.FilterOption.*;
import io.anuke.mindustry.world.*;

/** Selects X spawns from the spawn pool.*/
public class EnemySpawnFilter extends GenerateFilter{
    int amount = 1;

    @Override
    public FilterOption[] options(){
        return Structs.arr(
        new SliderOption("amount", () -> amount, f -> amount = (int)f, 1, 10)
        );
    }

    @Override
    public void apply(Tiles tiles, GenerateInput in){
        IntArray spawns = new IntArray();
        for(Tile tile : tiles){
            if(tile.overlay() == Blocks.spawn){
                spawns.add(tile.pos());
            }
        }

        spawns.shuffle();

        int used = Math.min(spawns.size, amount);
        for(int i = used; i < spawns.size; i++){
            Tile tile = tiles.getp(spawns.get(i));
            tile.clearOverlay();
        }
    }

    @Override
    public boolean isPost(){
        return true;
    }
}
