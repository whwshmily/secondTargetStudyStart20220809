package game.strategy.bulletStrategy;

import game.bullet.BaseBullet;
import game.bullet.impl.Bullet;
import game.face.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ThreeBulletStrategy implements BulletStrategy<GameObject> {
    @Override
    public List<BaseBullet> produceBullet(GameObject condition) {
        List<BaseBullet> baseBullets = new ArrayList<>(1);
        //x + width / 2, y - high / 2, dir, getTeamGroup()
        baseBullets.add(new Bullet(condition.getX() + condition.getWidth() / 2,
                condition.getY() - condition.getHigh() / 2, condition.getDir(), condition.getTeamGroup()
        ));
        baseBullets.add(new Bullet(condition.getX() + condition.getWidth() / 2,
                condition.getY() - condition.getHigh() / 2, condition.getDir(), condition.getTeamGroup()
        ));
        baseBullets.add(new Bullet(condition.getX() + condition.getWidth() / 2,
                condition.getY() - condition.getHigh() / 2, condition.getDir(), condition.getTeamGroup()
        ));
        return baseBullets;
    }
}
