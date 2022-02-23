package heroRepository;

public class Main {
    public static void main(String[] args) {
        HeroRepository heroRepository = new HeroRepository();
        Item item = new Item(20, 25, 30);
        Hero hero = new Hero("Name", 22, item);
        heroRepository.add(hero);

        String str = heroRepository.toString();
        System.out.println(str);
    }
}
