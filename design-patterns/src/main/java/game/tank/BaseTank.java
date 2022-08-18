package game.tank;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;
import game.tank.impl.PlayerTank;

import java.awt.*;
import java.util.List;


public abstract class BaseTank extends GameObject {

    public BaseTank(TeamGroup teamGroup) {
        super(teamGroup);
    }

    protected abstract void paint(Graphics g);

    protected abstract void move();

    protected abstract void fire(List<GameObject> elements, BulletStrategy<GameObject> strategy);

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
                if (this.getClass() == PlayerTank.class) {
                    this.setDie(true);
                    if (!(gameObjects.get(i) instanceof BaseTank)) {
                        gameObjects.get(i).setDie(true);
                    }
                } else if (gameObjects.get(i).getClass() == PlayerTank.class) {
                    gameObjects.get(i).setDie(true);
                    if (!(gameObjects.get(i) instanceof BaseTank)) {
                        this.setDie(true);
                    }
                } else {
                    this.setDie(true);
                    gameObjects.get(i).setDie(true);
                }
            }
            if (gameObjects.get(i) instanceof BaseTank && collide) {
                this.setDie(false);
            }
        }
    }

    @Override
    public void paintElement(Graphics g) {
        this.paint(g);
    }

    @Override
    public void executeElementSpecialField(List<GameObject> elements, FieldActionEnum type, BulletStrategy<GameObject> strategy) {
        if (type == FieldActionEnum.MOVE) {
            this.move();
        } else if (type == FieldActionEnum.FIRE) {
            this.fire(elements, strategy);
        }
    }

    @Override
    public void resurrection() {

    }
}
