package com.lqw.patterns.abstractfactory;

import java.util.function.Supplier;

/**
 * @author LQW
 * @date 2021-04-10 10:05
 **/
interface Obstacle {
     void action();
}

interface Player{
    void interactWith(Obstacle ob);
}

class Puzzle implements Obstacle{

    @Override
    public void action() {
        System.out.println("Puzzle");
    }
}

class NastyWeapon implements Obstacle{
    @Override
    public void action() {
        System.out.println("NastyWeapon");
    }
}

class Kitty implements Player{

    @Override
    public void interactWith(Obstacle ob) {
        System.out.print("Kitty has encountered a ");
        ob.action();
    }
}

class KungFuGuy implements Player{

    @Override
    public void interactWith(Obstacle ob) {
        System.out.print("KungFuGuy now battles a ");
        ob.action();
    }
}

//interface GameElementFactory{
//    Obstacle createObstacle();
//
//    Player createPlayer();
//}
//
//class KittiesAndPuzzles implements GameElementFactory{
//
//    @Override
//    public Obstacle createObstacle() {
//        return new Puzzle();
//    }
//
//    @Override
//    public Player createPlayer() {
//        return new Kitty();
//    }
//}
//
//class KillAndDismember implements GameElementFactory{
//
//    @Override
//    public Obstacle createObstacle() {
//        return new NastyWeapon();
//    }
//
//    @Override
//    public Player createPlayer() {
//        return new KungFuGuy();
//    }
//}

class GameElementFactory{
    protected Supplier<Obstacle> obstacleSupplier;

    protected Supplier<Player> playerSupplier;
}

class KittiesAndPuzzles extends GameElementFactory{
    public KittiesAndPuzzles() {
        obstacleSupplier=Puzzle::new;
        playerSupplier=Kitty::new;
    }
}

class KillAndDismember extends GameElementFactory{

    public KillAndDismember() {
        obstacleSupplier=NastyWeapon::new;
        playerSupplier=KungFuGuy::new;
    }
}

public class GameEnvironment{

    private Obstacle obstacle;

    private Player player;

//    public GameEnvironment(GameElementFactory factory) {
//        obstacle=factory.createObstacle();
//        player=factory.createPlayer();
//    }

    public GameEnvironment(GameElementFactory factory) {
        obstacle=factory.obstacleSupplier.get();
        player=factory.playerSupplier.get();
    }

    public void play(){
        player.interactWith(obstacle);
    }

    public static void main(String[] args) {
        GameElementFactory kpf=new KittiesAndPuzzles();
        GameElementFactory kdf=new KillAndDismember();

        GameEnvironment kpe=new GameEnvironment(kpf);

        GameEnvironment kde=new GameEnvironment(kdf);

        kpe.play();

        kde.play();
    }
}