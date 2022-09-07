package learn.rpg;

import learn.rpg.data.NameRepository;
import learn.rpg.data.PlayerRepository;
import learn.rpg.domain.PlayerService;
import learn.rpg.models.Hero;
import learn.rpg.models.Player;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream<Player> playerStream = getPlayers().stream();
        // playerStream.forEach(System.out::println);

        // lambda: accept a Player and print it to the console.
        // playerStream.forEach(player -> System.out.println(player));

        // filter
        // Stream<Player> playersFromThailand = playerStream.filter(player -> player.getCountry().equalsIgnoreCase("Thailand"));
        // playersFromThailand.forEach(System.out::println);

        // chaining method calls together to avoid variables
        getPlayers().stream().filter(player -> player.getCountry()
                .equalsIgnoreCase("Thailand")).forEach(System.out::println);

        // find players whose last name starts with B
        getPlayers().stream().filter(player -> player.getLastName().startsWith("B"))
                .forEach(System.out::println);

        // find players who aren't currently playing a hero
        getPlayers().stream().filter(player -> player.getHeroes().size() == 0)
                .forEach(System.out::println);

        // SKIP and LIMIT
        // Skip over 100 of the 106 players who have a last name that starts with "B"
        // and print the last 6.
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .skip(100)
                .forEach(System.out::println);

        // Only print the FIRST 5 players who have a last name that starts with "B".
        getPlayers().stream()
                .filter(player -> player.getLastName().startsWith("B"))
                .limit(5)
                .forEach(System.out::println);

        // Skip the first 500 players, then print the next 10 players.
        getPlayers().stream()
                .skip(500)
                .limit(10)
                .forEach(System.out::println);

        // FINDFIRST
        // returns an Optional<T>
        Optional<Player> firstThaiPlayer = getPlayers().stream().filter
                (player -> player.getCountry().equalsIgnoreCase("Thailand")).findFirst();
        Optional<Player> firstMarsPlayer = getPlayers().stream().filter
                (player -> player.getCountry().equalsIgnoreCase("Mars")).findFirst(); // result is an empty stream

        if (firstThaiPlayer.isPresent()) {
            Player p = firstThaiPlayer.get();
            System.out.println("Found a player from Thailand: " + p.getLastName());
        } else {
            System.out.println("There are no players from Thailand.");
        }

        if (firstMarsPlayer.isPresent()) {
            Player p = firstMarsPlayer.get();
            System.out.println("Found a player from Mars: " + p.getLastName());
        } else {
            System.out.println("There are no players from Mars.");
        }

        // COLLECT
        //produces new values and collections based on a stream.

        // toList produces a List interface
        List<Player> playersWithNoHero = getPlayers().stream().filter(player -> player.getHeroes()
                .size() == 0).toList();

        // for a concrete collection, use the toCollection method
        ArrayList<Player> playersFromNigeria = getPlayers().stream().filter(player -> player.getCountry()
                .equals("Nigeria")).collect(Collectors.toCollection(ArrayList::new));

        // SORT
        // accepts a Comparator instance - method that accepts two arguments of the stream type and returns an int
        // negative means the first param is smaller than the second
        // positive means the second param is smaller than the first

        // lambda that returns an int:
        // sorts by the length of the country name.
        getPlayers().stream().sorted((a, b) -> a.getCountry().length() - b.getCountry().length())
                .forEach(System.out::println);

        // lambda for sorting by country descending
        getPlayers().stream().sorted((a, b) -> a.getCountry().compareTo(b.getCountry()))
                .forEach(System.out::println);

        // Comparator for sorting by country descending
        getPlayers().stream().sorted(Comparator.comparing(Player::getCountry))
                .forEach(System.out::println);

        // Comparator for sorting by country ascending
        getPlayers().stream().sorted(Comparator.comparing(Player::getCountry).reversed())
                .forEach(System.out::println);

        // sort by last name, then first name
        getPlayers().stream().sorted(Comparator.comparing(Player::getLastName).thenComparing(Player::getFirstName))
                .forEach(System.out::println);

        // TRANSFORM
        // takes a stream of one type and transforms it into a stream of another type
        Stream<Player> players = getPlayers().stream();
        // Mapping lambda: a player is the input, String is the output
        Stream<String> fullNames = players.map(p -> p.getFirstName() + " " + p.getLastName());
        fullNames.forEach(System.out::println);
        // Or
        getPlayers().stream().map(p -> p.getFirstName() + " " + p.getLastName()).forEach(System.out::println);

        // Map from player to their first hero
        getPlayers().stream().map(p -> p.getHeroes().size() == 0 ? null : p.getHeroes().get(0))
                .forEach(System.out::println);
        // if a primitive type is required, there are special mapping methods
        int[] heroCounts = getPlayers().stream().mapToInt(p -> p.getHeroes().size()).toArray();

        // FLATMAP
        // "flattens" each List<Hero> inside a player into a single Stream<Hero>, which acts as a sequence of heroes
        Stream<Hero> heroes = getPlayers().stream().flatMap(p -> p.getHeroes().stream());
        heroes.forEach(System.out::println);


        // AGGREGATE
        // single value calculated from many values
        // ex. count, sum, average, min, max
        // hides the "how" allows us to focus on the "what"

        List<Player> players1 = getPlayers();
        List<Hero> allHeroes = players1.stream().flatMap(p -> p.getHeroes().stream()).collect(Collectors.toList());

        long playerCount = players1.stream().count();

        String sevenLastNames = players1.stream().map(p -> p.getLastName()).limit(7).collect(Collectors.joining(","));

        // Collectors.summarizingInt returns an IntSummaryStatistics object.
        // From that, we can determine various aggregate values:
        // getCount, getMin, getMax, getSum, getAverage.

        long sumLevels = allHeroes.stream()
                .collect(Collectors.summarizingInt(Hero::getLevel))
                .getSum();

        double averageLevel = allHeroes.stream()
                .collect(Collectors.summarizingInt(Hero::getLevel))
                .getAverage();

        System.out.println("Player Count: " + playerCount);
        System.out.println("7 Last Names: " + sevenLastNames);
        System.out.println("Sum of Levels: " + sumLevels);
        System.out.println("Average Level: " + averageLevel);


        // ANYMATCH and ALLMATCH
        // check for the existence of a condition across an entire stream
        // anyMatch returns true if any element in stream matches a condition
        // allMatch returns true of all elements in stream matches the condition

        List<Player> players2 = getPlayers();

        boolean anyPlayerIsFromChile = players2.stream()
                .anyMatch(p -> p.getCountry().equals("Chile"));
        boolean allPlayersAreFromChile = players2.stream()
                .allMatch(p -> p.getCountry().equals("Chile"));
        boolean allPlayersHaveFirstNames = players2.stream()
                .allMatch(p -> !p.getFirstName().isBlank());

        System.out.println("At least one player from Chile: " + anyPlayerIsFromChile);
        System.out.println("All players are from Chile: " + allPlayersAreFromChile);
        System.out.println("All players have first names: " + allPlayersHaveFirstNames);

        // Below we use anyMatch inside a fliter to include players who match the TINKERER condition

        getPlayers().stream()
                .filter(p -> p.getHeroes().stream()
                        .anyMatch(h -> h.getProfession() == Profession.TINKERER)) // check for TINKERER
                .forEach(System.out::println);

        long playerWithTinkererCount = getPlayers().stream()
                .filter(p -> p.getHeroes().stream()
                        .anyMatch(h -> h.getProfession() == Profession.TINKERER))
                .count();

        System.out.println(playerWithTinkererCount); // 256


        // GROUPING
        // Collectors.groupingBy can create groups of elements based on a key, then calculate an aggregate value for each group
        // number of players per country

        // determines key
        Map<String, Long> playersByCountry = getPlayers().stream()
                .collect(Collectors.groupingBy(Player::getCountry,
                        Collectors.counting()));
        // aggregate operation
        for (String country : playersByCountry.keySet()) {
            System.out.println(country + ": " + playersByCountry.get(country));
        }



        // To count the number of each Profession, we flatMap to heroes and then group
        Map<Profession, Long> professionCount = getPlayers().stream()
                .flatMap(p -> p.getHeroes().stream())
                .collect(Collectors.groupingBy(Hero::getProfession,
                        Collectors.counting()));
        for (Profession profession : professionCount.keySet()) {
            System.out.println(profession + ": " + professionCount.get(profession));
        }

        // To determine the average amount of coin (currency) carried per profession, change the
            // aggregate argument in groupingBy
        // We can take control of the averagingDouble collector with a lambda
        // Each hero's coin is tracked as a BigDecimal
        // There's no averagingBigDecimal collector, so we convert the BigDecimal to a double and
            // complete the average calculation

        Map<Profession, Double> avgCoinsPerProfession = getPlayers().stream()
                .flatMap(p -> p.getHeroes().stream())
                .collect(Collectors.groupingBy(Hero::getProfession,
                        Collectors.averagingDouble(h -> h.getCoin().doubleValue()))); // <- this changed

        for (Profession profession : avgCoinsPerProfession.keySet()) {
            System.out.printf("%s: %.2f%n", profession, avgCoinsPerProfession.get(profession));
        }

    }

    static List<Player> getPlayers() {
        PlayerRepository playerRepo = new PlayerRepository("players.csv");
        NameRepository nameRepo = new NameRepository("characters.csv");
        PlayerService service = new PlayerService(playerRepo, nameRepo);
        return service.generate();
    }


}
