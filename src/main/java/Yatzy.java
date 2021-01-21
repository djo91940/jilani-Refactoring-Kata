import java.util.Arrays;
import java.util.stream.IntStream;

public class Yatzy {

    /**
     * la variable n'est utilisée
     * que dans la classe pas besoin de protected
     */
    private int[] dice;

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * utiliser IntStream de java 8
     */
    public Yatzy(int d1, int d2, int d3, int d4, int d5) {

        dice = IntStream.of(d1, d2, d3, d4, d5).toArray();

    }

    /**
     *
     *  dès que la condition n'est pas
     *  vérifiée on sort du stream
     *  
     */
    public static int yatzy(int... dice) {
        final boolean isYatzy = Arrays.stream(dice)
                .allMatch(Integer.valueOf(dice[0])::equals);
        return isYatzy ? 50 : 0;
    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * l'opération finale sum retourne la somme
     * de tous les élément du IntStream
     */
    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(d1, d2, d3, d4, d5)
                .sum();

    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * retourne la somme des dés occurence == 1
     */
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return getSumOfOcu(1, d1, d2, d3, d4, d5);

    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * retourne la somme des dés occurence == 2
     */
    public static int twos(int d1, int d2, int d3, int d4, int d5) {

        return getSumOfOcu(2, d1, d2, d3, d4, d5);
    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * retourne la somme des dés occurence == 3
     */
    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return getSumOfOcu(3, d1, d2, d3, d4, d5);
    }

    /**    
     * @return
     * retourne la somme des dés occurence == 4
     */
    public int fours() {
        return getSumOfOcu(4, dice);

    }
    /**
     *    
     * @return
     * retourne la somme des dés occurence == 5
     */
    public int fives() {

        return getSumOfOcu(5, dice);
    }
    /**  
     * @return
     * retourne la somme des dés occurence == 6
     */
    public int sixes() {

        return getSumOfOcu(6, dice);
    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * la méthode getNOfAKind retourne une collection
     * max retourne la valeur maximale de cette collection
     * 2 * car on cherche la somme des doublons
     * orElse retourne 0 si la condition n'est pas valide
     */
    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {


        return 2 * IntStream.of(getNOfAKind(2, d1, d2, d3, d4, d5))
                .max()
                .orElse(0);
    }

    /**
     * retourne la somme des dés qui valident la condition (==valueToCheck)
     * la methode sum retourne par defaut 0
     */
    private static int getSumOfOcu(int valueToCheck, int... dices) {
        return IntStream.of(dices)
                .filter(value -> value == valueToCheck)
                .sum();

    }

    /**
     * retourne combien de fois
     * chaque valeur se répéte
     * en la plaçant dans la case qui correspond
     * à sa valeur (par exemple 3 va la placer dans la 3 ème
     * case du tableau == t[2] car le tableau commence par indexe 0)
     * par exemple pour 3,4,3,5,6 en input donne le tableau {0,0,2,1,1,1}
     */
    private static int[] getCounts(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = new int[6];
        IntStream.of(d1, d2, d3, d4, d5)
                .forEach(d -> counts[d - 1]++);
        return counts;
    }

    /**
     *
     * @param n
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     *restitue les valeurs qui se repetent au moins n fois
     * le range correspond à la taille du tableau (startInclusive, endExclusive)
     * ordonné sequentiellement
     *filter garde uniquement les indexes dont les valeurs se repetent n fois ou plus
     * map rajoute 1 à l'indexe du tableau filtré pour avoir la valeur réelle du dé
     * l'exemple précédant du getCounts
     * pour 3,4,3,5,6 en input donne le tableau {0,0,2,1,1,1}
     * le filter avec n==2 par exemple donne : {2}
     * le map donne : {3}
     */
    private static int[] getNOfAKind(int n, int d1, int d2, int d3, int d4, int d5) {

        return IntStream.range(0, 6)
                .filter(i -> getCounts(d1, d2, d3, d4, d5)[i] >= n)
                .map(i -> i + 1)
                .toArray();

    }
    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * la méthode getNOfAKind retourne une collection
     * des valeurs en doublon n==2. cette collection doit
     * avoir deux éléments pour avoir deux paires
     * ==2 car cinq paramètres on peut avoir 2 doublon au maximum
     * 2 * car on cherche la somme des doublons
     * : 0 si pas de paires de doublons
     */
    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        return getNOfAKind(2, d1, d2, d3, d4, d5).length == 2 ?
                IntStream.of(getNOfAKind(2, d1, d2, d3, d4, d5)).map(value -> 2 * value).sum() : 0;

    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * la méthode getNOfAKind retourne une collection
     * des valeurs en quadri n==4. cette collection doit
     * avoir deux éléments pour avoir deux paires
     * >0 car cinq paramètres on peut avoir 1 quadri
     * 4 * car on cherche la somme des quadri
     * : 0 si pas de quadri
     */
    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return getNOfAKind(4, d1, d2, d3, d4, d5).length > 0 ? 4 * getNOfAKind(4, d1, d2, d3, d4, d5)[0] : 0;
    }
    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * la méthode getNOfAKind retourne une collection
     * des valeurs en triplet n==3. cette collection doit
     * avoir deux éléments pour avoir deux paires
     * >0 car cinq paramètres on peut avoir 1 triplet
     * 3 * car on cherche la somme des triplet
     * : 0 si pas de triplet
     */
    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return getNOfAKind(3, d1, d2, d3, d4, d5).length > 0 ? 3 * getNOfAKind(3, d1, d2, d3, d4, d5)[0] : 0;

    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * pour {1,2,3,4,5} get count donne {1,1,1,1,1}
     * range garantie que le stream est ordonné
     * ==1 vérifie que chaque élément se repéte une seule fois
     * length ==5 la taille du tableau ==5
     * donc ne peut être que {1,2,3,4,5}
     */
    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.range(0, 5)
                .filter(i -> getCounts(d1, d2, d3, d4, d5)[i] == 1)
                .toArray().length == 5 ? 15 : 0;

    }
    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * la même logique que smallStraight
     * sauf que le range commence 1 pour eliminer
     * le premier enregistrement du tableau et donc le 1
     */
    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.range(1, 6)
                .filter(i -> getCounts(d1, d2, d3, d4, d5)[i] == 1)
                .toArray().length == 5 ? 20 : 0;
    }

    /**
     *
     * @param d1
     * @param d2
     * @param d3
     * @param d4
     * @param d5
     * @return
     * je récupère les triplets et les doublons
     * sur 5 dès la seule combinaison gagnante est un tripet + un doublon
     * sinon 0
     *kindThree[0] == pairs[0] ? pairs[1] : pairs[0] car la méthode
     * getNOfAKind remonte aussi les triplet comme doublons
     * par exemple {2,2,2,6,6} -> pairs = {2,6} et kindThree = {6}
     * et vue que 6 est déjà dans les triplets donc il ne faut pas la compter
     * dans les doublons
     */
    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] kindThree = getNOfAKind(3, d1, d2, d3, d4, d5);
        int[] pairs = getNOfAKind(2, d1, d2, d3, d4, d5);

        if (kindThree.length != 1 || pairs.length != 2) {
            return 0;
        }
        return 3 * kindThree[0] + 2 * (kindThree[0] == pairs[0] ? pairs[1] : pairs[0]);
    }
}
