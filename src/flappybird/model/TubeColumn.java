package flappybird.model;

import flappybird.view.Window;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TubeColumn {

    private List<Tube> tubes;
    private Random random;
    private int points = 0;
    private int speed = 5;
    private int changeSpeed = speed;
    private int base = Window.HEIGHT - 60;

    public TubeColumn() {
        tubes = new ArrayList<>();
        random = new Random();
        initTubes();
    }

    private void initTubes() {

        int last = base;
        int randWay = random.nextInt(10);

        for (int i = 0; i < 20; i++) {
            Tube tempTube = new Tube(900, last);
            tempTube.setDx(speed);
            last = tempTube.getY() - tempTube.getHeight();
            if (i < randWay || i > randWay + 4)
                tubes.add(tempTube);
        }
    }

    public void tick() {

        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).tick();

            if (tubes.get(i).getX() < 0)
                tubes.remove(tubes.get(i));
        }
        if (tubes.isEmpty()) {
            this.points += 1;
            if (changeSpeed == points) {
                this.speed += 1;
                changeSpeed += 5;
            }
            initTubes();
        }
    }

    public void render(Graphics2D g, ImageObserver obs) {
        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).render(g, obs);
        }
    }

    public int getPoints() { return points; }
    public List<Tube> getTubes() { return tubes; }
    public void setPoints(int points) { this.points = points; }
}
