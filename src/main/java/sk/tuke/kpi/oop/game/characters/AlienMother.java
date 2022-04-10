package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien{
    public AlienMother() {
        super();
        setAnimation(new Animation("sprites/mother.png",112,162,0.1f));
    }
}
