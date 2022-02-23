package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTest {
    private HeroRepository heroRepository;
    private Hero hero;
    private Item item;


    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
        item = new Item(20, 25, 30);
        hero = new Hero("HeroName", 22, item);

    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenRemovingANonExistentHero() {
        heroRepository.remove("smt");
    }


    @Test(expected = NullPointerException.class)
    public void testAgilityWithNegativeValue() {
        Item item = new Item(20, -5, 30);
        Hero hero2 = new Hero("SecondHeroName", 25, item);
        heroRepository.add(hero2);
        heroRepository.getHeroWithHighestAgility();
    }

    @Test(expected = NullPointerException.class)
    public void testStrengthWithNegativeValue() {
        Item item = new Item(-20, 20, 30);
        Hero hero2 = new Hero("SecondHeroName", 25, item);
        heroRepository.add(hero2);
        heroRepository.getHeroWithHighestStrength();
    }

    @Test(expected = NullPointerException.class)
    public void testIntelligenceWithNegativeValue() {
        Item item = new Item(20, 20, -30);
        Hero hero2 = new Hero("SecondHeroName", 25, item);
        heroRepository.add(hero2);
        heroRepository.getHeroWithHighestIntelligence();
    }


    @Test
    public void shouldAddHero() {
        heroRepository.add(hero);
        Assert.assertEquals(heroRepository.getCount(), 1);
    }

    @Test
    public void shouldReturnStrength() {
        heroRepository.add(hero);
        int strength = heroRepository.getHeroWithHighestStrength().getItem().getStrength();
        Assert.assertEquals(20, strength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicatedHeroShouldReturnException() {
        heroRepository.add(hero);
        heroRepository.add(hero);
    }

    @Test
    public void shouldRemoveHero() {
        heroRepository.add(hero);
        heroRepository.remove("HeroName");
        Assert.assertEquals(0, heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void removeShouldThrowExceptionWhenInvalidHero() {
        heroRepository.add(hero);
        heroRepository.remove("Pesho");
    }

    @Test
    public void shouldReturnHeroWithHighestAgility() {
        heroRepository.add(hero);
        Hero heroWithMaxAgg = heroRepository.getHeroWithHighestAgility();
        Assert.assertEquals(heroWithMaxAgg, this.hero);
    }


    @Test
    public void shouldReturnHeroWithHighestStrength() {
        heroRepository.add(hero);
        Hero heroWithMaxStr = heroRepository.getHeroWithHighestStrength();
        Assert.assertEquals(heroWithMaxStr, this.hero);
    }


    @Test
    public void shouldReturnHeroWithHighestIntelligence() {
        heroRepository.add(hero);
        Hero heroWithMaxInt = heroRepository.getHeroWithHighestIntelligence();
        Assert.assertEquals(heroWithMaxInt, this.hero);
    }


    @Test
    public void mustReturnTheCountOfTheHeroes() {
        Assert.assertEquals(0, heroRepository.getCount());
    }


    @Test
    public void toStringTest() {
        Assert.assertEquals("", heroRepository.toString());
    }

    @Test
    public void getCountOfHeroes() {
        heroRepository.add(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }


}