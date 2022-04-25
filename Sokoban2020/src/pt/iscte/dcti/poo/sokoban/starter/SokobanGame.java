package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {

    public final static int RESTART = 82;
    private int NumberLevels;
    private static SokobanGame INSTANCE;
    private ArrayList<AbstractObject> obj = new ArrayList<AbstractObject>();
    private Player player;
    private int level = 0;
    private PlayerStatus status;
    public final static String TUTORIAL = "Welcome to Sokoban!" + "\n" + "How to play:" + "\n" + "-Arrow Keys to move"
            + "\n" + "-R key to restart the Game";

    private SokobanGame() {
        status = new PlayerStatus(JOptionPane.showInputDialog(null, "Nome do Jogador:"));
        JOptionPane.showMessageDialog(null, TUTORIAL);
        buildSampleLevel("levels/level" + level + ".txt");
        ImageMatrixGUI.getInstance()
                .setStatusMessage("Level: " + level + " Moves: " + player.moves() + " Energy: " + player.vida());
        ImageMatrixGUI.getInstance().setName("Sokoban");
        NumberLevels = (new File("levels")).listFiles().length;
    }

    public static SokobanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SokobanGame();

        return INSTANCE;
    }

    private void buildSampleLevel(String nomeFicheiro) {
        try {
            Scanner s = new Scanner(new File(nomeFicheiro));
            for (int y = 0; y != 10; y++) {
                String b = s.nextLine();
                for (int x = 0; x != 10; x++) {
                    char c = b.charAt(x);
                    if (c != ' ') {
                        AbstractObject obj = AbstractObject.fabrica(c, x, y);
                        addObject(obj);
                    }
                    ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(x, y)));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO");
        }
    }

    public void addObject(AbstractObject o) {
        if (o instanceof Player)
            player = (Player) o;
        else
            obj.add(o);
        ImageMatrixGUI.getInstance().addImage(o);
    }

    public void playerNull() {
        player = null;
    }

    public int numberOfLevels() {
        return NumberLevels;
    }

    public Player player() {
        return player;
    }

    public ArrayList<AbstractObject> map() {
        return obj;
    }

    public boolean win() {
        ArrayList<AbstractObject> listaCaixotes = new ArrayList<>();
        ArrayList<AbstractObject> listaAlvo = new ArrayList<>();
        for (AbstractObject a : obj) {
            if (a instanceof Caixote)
                listaCaixotes.add(a);
            if (a instanceof Alvo)
                listaAlvo.add(a);
        }
        int k = 0;
        for (AbstractObject cai : listaCaixotes) {
            for (AbstractObject al : listaAlvo) {
                if (cai.getPosition().equals(al.getPosition()))
                    k++;
            }
        }
        return k == listaAlvo.size();
    }

    public void removeOfGame(AbstractObject a) {
        obj.remove(a);
        ImageMatrixGUI.getInstance().removeImage(a);
    }

    public void changeLevel(int level) {
        ImageMatrixGUI.getInstance().clearImages();
        obj.removeAll(obj);
        INSTANCE.buildSampleLevel("levels/level" + level + ".txt");
        ImageMatrixGUI.getInstance()
                .setStatusMessage("Level: " + level + " Moves: " + player.moves() + " Energy: " + player.vida());
        ImageMatrixGUI.getInstance().update();
    }

    public void finishGame() {
        ImageMatrixGUI.getInstance().dispose();
        JOptionPane.showMessageDialog(null, status.toString());
        Pontuacao.writeScore(status.sendScores(), status.getPlayerName());
        System.exit(0);
    }

    public int level() {
        return level + 1;
    }

    public ObjectoMovivel Movivel(Point2D point) {
        for (AbstractObject a : obj)
            if (point.equals(a.getPosition()))
                if (a instanceof ObjectoMovivel)
                    return (ObjectoMovivel) a;
        return null;
    }

    public ActiveObject Active(Point2D point) {
        for (AbstractObject a : obj)
            if (point.equals(a.getPosition()))
                if (a instanceof ActiveObject)
                    return (ActiveObject) a;
        return null;
    }

    @Override
    public void update(Observed arg0) {
        int lastKeyPressed = ((ImageMatrixGUI) arg0).keyPressed();

        if (lastKeyPressed == RESTART) {
            level = 0;
            changeLevel(level);
            status.reset();
            return;
        }

        if (win() && player != null) {
            status.setScores(player.moves());
            Pontuacao.writeScoresTop3(status.sendTopScores());
            if (level + 1 <= NumberLevels - 1) {
                changeLevel(++level);
                return;
            } else {
                finishGame();
                return;
            }
        }
        if (player != null && player.vida() > 0) {
            player.move(Direction.directionFor(lastKeyPressed));
            if (player != null && player.vida() > 0)
                ImageMatrixGUI.getInstance().setStatusMessage(
                        "Level: " + level + " Moves: " + player.moves() + " Energy: " + player.vida());
            else {
                ImageMatrixGUI.getInstance().setStatusMessage("PERDESTE");
                JOptionPane.showMessageDialog(null, "PRESS R TO RESTART");
            }

        }
    }

    public ArrayList<AbstractObject> objectsPosition(Point2D position) {
        ArrayList<AbstractObject> lista = new ArrayList<>();
        for (AbstractObject a : obj) {
            if (position.equals(a.getPosition())) {
                lista.add(a);
            }
        }
        return lista;
    }
}
