package game.bullet;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;

import java.awt.*;
import java.util.List;


public abstract class BaseBullet extends GameObject {


    public BaseBullet(TeamGroup teamGroup) {
        super(teamGroup);
    }

    protected abstract void paint(Graphics g);

    protected abstract void move();

    @Override
    public void statusCheck(List<GameObject> gameObjects, ImpactCheck<GameObject> impactCheck) {
        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i) == this) {
                return;
            }
            if (this.teamGroup.equals(gameObjects.get(i).getTeamGroup())) {
                continue;
            }
            boolean collide = impactCheck.isCollide(this, gameObjects.get(i));
            if (collide) {
                this.setDie(true);
                gameObjects.get(i).setDie(true);
            }
        }
    }

    @Override
    public void paintElement(Graphics g) {
        this.paint(g);
    }

    @Override
    public void executeElementSpecialField(List<GameObject> elements, FieldActionEnum type, BulletStrategy<GameObject> strategy) {
        this.move();
    }

    @Override
    public void resurrection() {

    }

}
