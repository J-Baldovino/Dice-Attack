package application.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import application.model.Champions;
import application.model.Monster;
import application.model.Person;
import application.model.TwoDice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class BattleController {
	
	MediaPlayer mp;
	
	@FXML
	private AnchorPane lC;
	
	@FXML
	private AnchorPane wC;
	
    @FXML
    private Button defendButton;

    @FXML
    private Button burstButton;

    @FXML
    private Button attackButton;

    @FXML
    private ImageView enemy;

    @FXML
    private ImageView player;
    
    @FXML
    private Button sceneAction1;
    
    @FXML
    private Button sceneAction2;
    
    @FXML
    private Button sceneAction3;
    
    @FXML
    private Button endTurn;
    
    @FXML
    private TextField dice1;
    
    @FXML
    private TextField dice2;
    
    @FXML
    private TextField BattleText;
    
    @FXML
    private ImageView diceImage;
    
    @FXML
    private ImageView diceImage2;
    
    @FXML
    private Label playerName;
    
    @FXML
    private Label playerHealth;
    
    @FXML
    private Label playerMana;

    @FXML
    private Label EnemyName;
    
    @FXML
    private Label EnemyHealth;
    
    @FXML
    private Button basicAttackButton;

    @FXML
    private Button defendBtn;

    @FXML
    private ImageView goomba;

    @FXML
    private ImageView heal;

    @FXML
    private Button healButton;

    @FXML
    private Button multiAttackButton;

    @FXML
    private ImageView sanic;

    @FXML
    private ImageView shield;

    @FXML
    private ImageView sword;
    
    Random random = new Random();
    
    //ARNOLD PART //This is where I will put the players name but it is set at the moment
	//Person DiceHero = new Person(10, 10, 0, 10); //Person is different from 
    
//	TwoDice dice = new TwoDice();
	ArrayList<Monster> list = new ArrayList<>();
    
    @FXML
    private void initialize() throws MalformedURLException
    {
    	File media = new File("music/battle.mp3");
        Media song = new Media(media.toURI().toURL().toString());
        mp= new MediaPlayer(song);
       	mp.setOnEndOfMedia(new Runnable() {
       		@Override
        	public void run()
        	{
        		mp.seek(mp.getStartTime());
        	}
       	});
        mp.setVolume(0.1);
        mp.play();
        
        Person DiceHero = new Person(); //initalally get mana
        TwoDice dice = new TwoDice();
        dice.rollOneDice(); //dont use rolling function here
        System.out.println(dice.getDie1());
        DiceHero.addMana(dice.getDie1());
        
//		dice.roll();
		Monster gremlin;
		gremlin = new Monster("Gremlin", 10, 3);
		list.add(gremlin);
		Monster gremlin2;
		gremlin2 = new Monster("Goblin", 11, 7);
		list.add(gremlin2);
		
		playerName.setText("Change");
		//playerHealth.setText(DiceHero.getHealthRatio());
//		EnemyName.setText(list.get(DiceHero.getBattlesWon()).getName());
//		EnemyHealth.setText(Integer.toString(list.get(DiceHero.getBattlesWon()).getHealth()));
		update();
	}

    @FXML
    void sceneWin(ActionEvent event) {
    	mp.stop();
    	try {
    		URL url = new File("Victory.fxml").toURI().toURL();
    		URL styleUrl = new File("src/application/application.css").toURI().toURL();
			wC = FXMLLoader.load(url);
			Stage classifieds= (Stage) ((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(wC);
			scene.getStylesheets().add(styleUrl.toString());
			classifieds.setScene(scene);
			classifieds.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void sceneLose(ActionEvent event) {
    	mp.stop();
    	try {
    		URL url = new File("Game_Over.fxml").toURI().toURL();
    		URL styleUrl = new File("src/application/application.css").toURI().toURL();
			lC = FXMLLoader.load(url);
			Stage classifieds= (Stage) ((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(lC);
			scene.getStylesheets().add(styleUrl.toString());
			classifieds.setScene(scene);
			classifieds.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void basicAttackButton(ActionEvent event) { //1 mana
    	Person DiceHero = new Person();
    	
    	if(DiceHero.getMana() >= 1)
    	{
    	
        System.out.println( list.get(DiceHero.getBattlesWon()).takeDamage(DiceHero.basicStrike(rollingFunction())));
        System.out.println("The monster's hp is now = " + Integer.toString(list.get(DiceHero.getBattlesWon()).getHealth()) + "\n");// + " the thread is fucking me here please help God");
        DiceHero.subMana(1);
        update();
        BattleText.setText("Name here your hero will be named at the start " + " has used basic strike! \n" + list.get(DiceHero.getBattlesWon()).getName() + " has " +  list.get(DiceHero.getBattlesWon()).getHealth() + ".");
        
    	}
    	else
    	{
    		BattleText.setText("You do not have enough mana");
    	}
    }
    
    @FXML
    void multiStrikeButton(ActionEvent event) {
    	Person DiceHero = new Person();

    	if(DiceHero.getMana() >= 4)
    	{
    		//4 mana to dice1 * dice1
    		System.out.println( list.get(DiceHero.getBattlesWon()).takeDamage(DiceHero.multistrike((rollingFunction()))));
    		BattleText.setText("Name here your hero will be named at the start " + " has used multi-strike! " + list.get(DiceHero.getBattlesWon()).getName() + " has " +  list.get(DiceHero.getBattlesWon()).getHealth() + ".");
    	}
    	else
    	{
    		BattleText.setText("You do not have enough mana");
    	}
//    	System.out.println(DiceHero.getHealth());
//    	DiceHero.takeDamage(1);
    	update();
		
    }
    
    @FXML
    void healButton(ActionEvent event) {
    	rollingFunction();
//    	dice.roll();
//		System.out.println("Dice one: " + dice.getDie1() + " Dice two: " + dice.getDie2());
//		System.out.println(list.get(DiceHero.getBattlesWon()).takeDamage( DiceHero.basicStrike(dice.getDie1())) );
//		
//		//System.out.println(DiceHero.takeDamage(list.get(DiceHero.getBattlesWon()).getAttackPower()));
//		System.out.println();
//		
//		if(DiceHero.getHealth() <= 0)
//		{
//			System.out.println( "NAME has died has died"); //DiceHero.getName() was removed
//		}
//		if(list.get(DiceHero.getBattlesWon()).getHealth() <= 0)
//		{
//			System.out.println(list.get(DiceHero.getBattlesWon()).getName() + " has died" );
//		}
//		
//		//System.out.println(DiceHero.getName() + " has " + DiceHero.getHealth() + " hp");
//		System.out.println(list.get(DiceHero.getBattlesWon()).getName() + " has " + list.get(DiceHero.getBattlesWon()).getHealth() + " hp");
//    	
//		
//		playerHealth.setText(DiceHero.getHealthRatio());
    }
    
    @FXML
    void defendButton(ActionEvent event) {
    	
    }
    
    
    
    
    @FXML
    void endTurn(ActionEvent event) throws InterruptedException {
    	Person DiceHero = new Person();
    	TwoDice dice = new TwoDice();
    	if(endTurn.getText().equals("End Turn"))
    	{
//    	BattleText.setText(list.get(DiceHero.getBattlesWon()).getName() + "'s turn" );
    	Thread.sleep(1000); //small delay 
    	//System.out.println(list.get(DiceHero.getBattlesWon()).getName() + " attacks " + "`Add hero's name later` " + "for " + list.get(DiceHero.getBattlesWon()).getAttackPower());
    	BattleText.setText(list.get(DiceHero.getBattlesWon()).getName() + " attacks " + "`Add hero's name later` " + "for " + list.get(DiceHero.getBattlesWon()).getAttackPower());
    	DiceHero.takeDamage(list.get(DiceHero.getBattlesWon()).getAttackPower());
    	endTurn.setText("Start turn");
    	}
    	else
    	{
    		DiceHero.addMana(rollingFunction());
    		BattleText.setText("Hero's name turn! Hero's name gains " + dice.getDie1() + " mana!");
    		endTurn.setText("End Turn");
    		
    	}
    	update();
    	
    }
    
    @FXML
    void battleWonScene(ActionEvent event) {
    	Person DiceHero = new Person();
    	DiceHero.setBattlesWon();
    	
    	try {
    		URL url = new File("Stage.fxml").toURI().toURL();
    		URL styleUrl = new File("src/application/application.css").toURI().toURL();
			wC = FXMLLoader.load(url);
			Stage classifieds= (Stage) ((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(wC);
			scene.getStylesheets().add(styleUrl.toString());
			classifieds.setScene(scene);
			classifieds.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void update(){
    	Person DiceHero = new Person(); //have to do this to access stats
    	//playerName.setText("name here");
    	playerHealth.setText(DiceHero.getHealthRatio());
    	playerMana.setText(Integer.toString(DiceHero.getMana()));
    	EnemyName.setText(list.get(DiceHero.getBattlesWon()).getName());
    	EnemyHealth.setText(Integer.toString(list.get(DiceHero.getBattlesWon()).getHealth()));
    }
    
    public int rollingFunction(){
    	basicAttackButton.setDisable(true);
    	multiAttackButton.setDisable(true);
    	healButton.setDisable(true);
    	defendButton.setDisable(true);
    	
    	TwoDice dice = new TwoDice();
        Thread thread = new Thread(){
            public void run(){
                //System.out.println("Thread Running");
                try {
                    for (int i = 0; i <= 15; i++) {
                    	dice.rollOneDice();
                    	dice1.setText( Integer.toString(dice.getDie1()) );
                        File file = new File("/../../images/dice" + dice.getDie1() +".png");
                        System.out.print(dice.getDie1() + " ");
                        diceImage.setImage(new Image(file.toURI().toString()));
                        Thread.sleep(100);
                    }
                    basicAttackButton.setDisable(false);
                	multiAttackButton.setDisable(false);
                	healButton.setDisable(false);
                	defendButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        while(thread.isAlive())
        {	
        }
        
        return dice.getDie1();
   }
   
    public int rollingFunction2(){
    	basicAttackButton.setDisable(true);
    	multiAttackButton.setDisable(true);
    	healButton.setDisable(true);
    	defendButton.setDisable(true);
    	
    	TwoDice dice = new TwoDice();
        Thread thread = new Thread(){
            public void run(){
                //System.out.println("Thread Running");
                try {
                    for (int i = 0; i <= 15; i++) {
                    	dice.rollOneDice();
                    	dice2.setText( Integer.toString(dice.getDie1()) );
                        File file = new File("/../../images/dice" + dice.getDie1() +".png");
                        System.out.print(dice.getDie1() + " ");
                        diceImage2.setImage(new Image(file.toURI().toString()));
                        Thread.sleep(50);
                    }
                    basicAttackButton.setDisable(false);
                	multiAttackButton.setDisable(false);
                	healButton.setDisable(false);
                	defendButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        while(thread.isAlive())
        {	
        }
        
        return dice.getDie1();
   }

}

