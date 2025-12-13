package game;

import java.util.*;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Player player;
    private final Map<String, StoryNode> nodes = new HashMap<>();

    public Game() {
        initPlayer();
        initStory();
    }

    private void initPlayer() {
        System.out.println("Введите имя вашей героини:");
        String name = Utils.readLineTrimmed();
        if (name.isEmpty()) name = "Антагонист";
        player = new Player(name, 5, 5); // базовые статы: харизма, сила
    }

    private void initStory() {
        // ===== Первая сцена =====
        StoryNode start = new StoryNode("start",
                "Медленно открываешь глаза. Ты ожидала услышать гудение машины, " +
                        "или любой другой шум. Но вокруг тишина. Врачи отошли на минутку? " +
                        "Или ты не переживала операцию? Что странно — ты смотришь на незнакомый потолок. " +
                        "Она не белая, а красная, украшенная золотыми узорами. " +
                        "Ты непонимающе встаешь и тут же чувствуешь пульсацию в голове, достаточно сильное, " +
                        "но куда слабее чем должна быть. После такой операции ты вообще не должна была " +
                        "быть способна двигаться на долгое время.\n\n" +
                        "Ты осматриваешь комнату. Это точно не реанимация. Это даже не больница. " +
                        "Ты понимаешь, что находишься в роскошной комнате, красной и позолоченной золотом. " +
                        "Твой взгляд падает на большое зеркало с противоположной стороны комнаты. " +
                        "Там ты видишь девушку. Черные, вьющиеся волосы до колен, красные глаза напоминающие кровь, " +
                        "черты лица резкие и бледная кожа. Она была красивой, но важнее было то, что она могла " +
                        "ответить на твои вопросы, поэтому ты открыла рот, чтобы обратиться к ней. " +
                        "Ты замкнулась, решив дать ей говорить первой, но и она молчала. Наконец, ты решаешь встать, " +
                        "и она тоже встала.\n\n" +
                        "Осознание пришло внезапно — ты медленно помахала рукой, она делала то же самое. " +
                        "Это была ты, которое отражалась в зеркале.\n\n" +
                        "Пока ты любовалась отражением и думала, что делать, послышался грохот. " +
                        "Ты оглянулась — в дверном проёме стола девушка в форме служанки, которая уронила поднос. " +
                        "Она судорожно собирала то, что уронила, и без конца извинялась.\n\n" +
                        "Ты подошла к ней, чтобы задать вопрос.\n\n- Риана (или другое имя по выбору), — крикнул мужчина с коридора."
        );
        start.addChoice(new Choice("Я не Риханна", "choice1", (p) -> p.changeCharisma(1)));
        start.addChoice(new Choice("Что тебе надо", "choice2", (p) -> p.changeStrength(1)));
        nodes.put(start.getId(), start);

        // ===== Ветвь после первого выбора =====
        StoryNode choice1 = new StoryNode("choice1",
                "Ты сказала, что не Риханна. Девушка-слуга удивленно посмотрела на тебя, но продолжила собирать поднос. " +
                        "Мужчина из коридора приблизился чуть ближе, оценивая твою реакцию."
        );
        choice1.addChoice(new Choice("Подойти к мужчине", "end"));
        choice1.addChoice(new Choice("Остаться с девушкой", "end"));
        nodes.put(choice1.getId(), choice1);

        StoryNode choice2 = new StoryNode("choice2",
                "Ты спросила, что ему нужно. Мужчина внимательно посмотрел на тебя, а девушка продолжала судорожно собирать поднос. " +
                        "Ситуация напряженная, но у тебя есть возможность действовать."
        );
        choice2.addChoice(new Choice("Подойти к мужчине", "end"));
        choice2.addChoice(new Choice("Остаться с девушкой", "end"));
        nodes.put(choice2.getId(), choice2);

        StoryNode end = new StoryNode("end",
                "Конец первой сцены. Ты можешь сохранить прогресс и вернуться к этой точке позже."
        );
        nodes.put(end.getId(), end);
    }

    public void start() {
        System.out.println("\n--- Игра начинается ---\n");
        String currentId = "start";
        while (true) {
            StoryNode node = nodes.get(currentId);
            if (node == null) {
                System.out.println("Ошибка: узел не найден: " + currentId);
                break;
            }

            printStatus();
            System.out.println(node.getText());
            List<Choice> choices = node.getChoices();

            if (choices.isEmpty()) {
                System.out.println("\n--- Конец ветки ---");
                break;
            }

            for (int i = 0; i < choices.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, choices.get(i).getDescription());
            }

            int pick = Utils.readChoice(1, choices.size());
            Choice chosen = choices.get(pick - 1);

            // применить эффект выбора
            if (chosen.getEffect() != null) {
                chosen.getEffect().accept(player);
            }

            currentId = chosen.getNextNodeId();
        }

        System.out.println("\nСпасибо за игру, " + player.getName() + "!");
        scanner.close();
    }

    private void printStatus() {
        System.out.println("\n--- Статус ---");
        System.out.println("Имя: " + player.getName() + " | Сила: " + player.getStrength() + " | Харизма: " + player.getCharisma());
        System.out.println("Флаги: " + player.getFlags());
        System.out.println("----------------\n");
    }
}
