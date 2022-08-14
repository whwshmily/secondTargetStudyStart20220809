import game.check.RectangleImpactCheck;
import game.frame.PlayFrame;

//坦克大战入口
public class MainClass {

    public static void main(String[] args) throws Exception {
        final PlayFrame pf = new PlayFrame("tank awt", new RectangleImpactCheck());
        Thread thread = new Thread(() -> {
            while (pf.getCreatedComputedTankCounts() <= 20) {
                pf.gameDetection();
            }
        });
        thread.start();
        while (true) {
            Thread.sleep(50);
            pf.repaint();
        }

    }
}
