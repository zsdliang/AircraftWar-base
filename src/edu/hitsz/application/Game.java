package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.data.ScoreDaolmpl;
import edu.hitsz.enemyfactory.BossFactory;
import edu.hitsz.enemyfactory.ElitFactory;
import edu.hitsz.enemyfactory.EnemyFactory;
import edu.hitsz.enemyfactory.MobFactory;
import edu.hitsz.prop.PropBlood;
import edu.hitsz.prop.PropBomb;
import edu.hitsz.prop.PropBullet;
import edu.hitsz.propfactory.PropBloodFactory;
import edu.hitsz.propfactory.PropBombFactory;
import edu.hitsz.propfactory.PropBulletFactory;
import edu.hitsz.propfactory.PropFactory;
import edu.hitsz.shoot.EnemyShoot;
import edu.hitsz.shoot.SprayShoot;
import edu.hitsz.shoot.StraightShoot;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;
    private int enemyshootInterval = 40;
    /**
     *敌机工厂
     */
    private final EnemyFactory bossfactory = new BossFactory();
    private final EnemyFactory elitfactory = new ElitFactory();
    private final EnemyFactory mobfactory = new MobFactory();
    /**
     *道具工厂
     */
    private final PropFactory prop_bloodfactory = new PropBloodFactory();
    private final PropFactory prop_bulletfactory = new PropBulletFactory();
    private final PropFactory prop_bombfactory = new PropBombFactory();
    /**
     *定义各种飞行物体
     */
    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<AbstractBullet> heroBullets;
    private final List<AbstractBullet> enemyBullets;
    private final List<AbstractAircraft> props;

    private int enemyMaxNumber = 10;

    private boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    /**
     *判断boss机是否存在
     */
    private boolean bossexisting = false;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 500;
    private int cycleTime = 0;
    /**
     *精英机射击频率
     */
    private int enemyshootDuration = 2000;
    private int cycleEnemyshoottime = 0;

    ScoreDaolmpl scoreDaolmpl;

    public Game() throws IOException {

        scoreDaolmpl = new ScoreDaolmpl();
        // 产生英雄机
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.getShootNum();
        heroAircraft.setShootNum(1);
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {



            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber && !bossexisting) {
                    Random random = new Random();
                    int i = Math.abs(random.nextInt()) % 20;
                    //以17:3的比例生成敌机
                    if(i < 17) {
                        enemyAircrafts.add(mobfactory.creatEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                                0,
                                3,
                                20));
                    }
                    if(i >= 17) {
                        enemyAircrafts.add(elitfactory.creatEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                                3,
                                1,
                                40));
                    }
                    if(score > 10 && (score % 500 == 0 || score % 500 == 10)){
                        enemyAircrafts.add(bossfactory.creatEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                                3,
                                0,
                                400));
                        bossexisting = true;
                    }
                }
                // 飞机射出子弹
                shootAction();
            }
            if(enemyShootJudge())
            {
                enemyShootaction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                try {
                    System.out.println("请输入你的姓名：");
                    scoreDaolmpl.keepScore(score);
                    scoreDaolmpl.getRankingList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    /**
     *判断敌机是否应射击
     */
    private boolean enemyShootJudge() {
        cycleEnemyshoottime += enemyshootInterval;
        if (cycleEnemyshoottime >= enemyshootDuration && cycleEnemyshoottime - enemyshootInterval < cycleEnemyshoottime) {
            cycleEnemyshoottime %= enemyshootDuration;
            return true;
        } else {
            return false;
        }
    }


    private void shootAction() {
        // 英雄射击
        if(heroAircraft.getShootNum() <= 2) {
            heroAircraft.setShootStrategy(new StraightShoot());
        }
        else if(heroAircraft.getShootNum() >2) {
            heroAircraft.setShootStrategy(new SprayShoot());
        }
        heroBullets.addAll(heroAircraft.executeStrategy());
    }

    private void enemyShootaction() {
        //敌机射击

        for(AbstractAircraft enemy:enemyAircrafts){
            if(enemy instanceof ElitEnemy || enemy instanceof Boss) {
                enemy.setShootStrategy(new EnemyShoot());
                enemyBullets.addAll(enemy.executeStrategy());
            }
        }
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        for (AbstractAircraft prop : props){
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (AbstractBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        if (enemyAircraft instanceof ElitEnemy||enemyAircraft instanceof Boss ) {
                            score += 20;
                            if((int)(Math.random()*7) == 0) {
                                props.add(prop_bloodfactory.creatprop(
                                        enemyAircraft.getLocationX(),
                                        enemyAircraft.getLocationY(),
                                        0,
                                        2,
                                        1
                                ));
                            }
                            else if((int)(Math.random()*7) == 1) {
                                props.add(prop_bombfactory.creatprop(
                                        enemyAircraft.getLocationX(),
                                        enemyAircraft.getLocationY(),
                                        0,
                                        2,
                                        1
                                ));
                            }
                            else if((int)(Math.random()*7) == 2) {
                                props.add(prop_bulletfactory.creatprop(
                                        enemyAircraft.getLocationX(),
                                        enemyAircraft.getLocationY(),
                                        0,
                                        2,
                                        1
                                ));
                            }
                        }
                        if (enemyAircraft instanceof MobEnemy) {
                            score += 10;
                        }
                        if (enemyAircraft instanceof Boss){
                            score += 100;
                            bossexisting = false;
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }

        }
        // Todo: 我方获得道具，道具生效
        for(AbstractAircraft prop : props){
            if (heroAircraft.crash(prop)) {
                prop.vanish();
                if(prop instanceof PropBlood) {
                    System.out.println("hp+25");
                    heroAircraft.decreaseHp(-25);
                }
                else if(prop instanceof PropBomb) {
                    System.out.println("bomb");
                }
                else if(prop instanceof PropBullet) {
                    heroAircraft.setShootNum(heroAircraft.getShootNum()+1);
                    System.out.println("stronger bullet");
                }
                else{
                    System.out.println("");
                }
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, (int)(heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2),
                (int)(heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2), null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, (int)(object.getLocationX() - image.getWidth() / 2),
                    (int)(object.getLocationY() - image.getHeight() / 2), null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(0xFF0000));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
