@echo off
mkdir project
mkdir project\src
mkdir project\src\game

echo package game; public class Main { public static void main(String[] args){ Game game = new Game(); game.start(); }} > project\src\game\Main.java

echo package game; public class Game { public void start(){ System.out.println("Добро пожаловать в игру!"); }} > project\src\game\Game.java

echo package game; public class Player { private String name; private int karma; public Player(String n){name=n;} public String getName(){return name;} public int getKarma(){return karma;} public void changeKarma(int v){karma+=v;} } > project\src\game\Player.java

echo package game; public class Choice { public String text; public int karma; public Choice(String t,int k){text=t;karma=k;} } > project\src\game\Choice.java

echo package game; import java.util.List; public class StoryNode { public String text; public List choices; public StoryNode(String t, List c){text=t;choices=c;} } > project\src\game\StoryNode.java

echo package game; import java.util.Scanner; public class Utils { static Scanner sc = new Scanner(System.in); public static int readInt(){return Integer.parseInt(sc.nextLine());}} > project\src\game\Utils.java

echo === Файлы созданы! ===
pause
