package woowabros.baseball;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void testCreateRandomNumber(){
        Game game = new Game();
        List<Integer> numberList = game.createRandomNumber();

        assertThat(numberList).isNotEmpty();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(list).containsAll(numberList);

    }

    @Test
    public void testSplitInputNumber(){
        Game game = new Game();
        List<Integer> inputNumbers = game.splitInputNumber("123");
        List<Integer> list = Arrays.asList(1, 2, 3);

        assertThat(list).containsAll(inputNumbers);
    }

    @Test
    public void testCompareNumber(){
        Game game = new Game();
        List<Integer> randomNumberList = Arrays.asList(4, 2, 3);
        List<Integer> inputNumbers = Arrays.asList(4, 2, 3);
        game.compareNumber(randomNumberList, inputNumbers);

        assertThat(3 == game.strike).isTrue();
    }

    @Test
    public void testCountStrike(){
        Game game = new Game();
        game.countStrike(4, 5, 0);
        assertThat(0 == game.strike).isTrue();
    }

    @Test
    public void testCountBall(){
        Game game = new Game();
        game.countBall(Arrays.asList(4, 2, 3), 5, 0);
        assertThat(0 == game.ball).isTrue();
    }

    @Test
    public void testReset(){
        Game game = new Game();
        game.strike = 1;
        game.ball = 2;

        assertThat(1 == game.strike).isTrue();
        assertThat(2 == game.ball).isTrue();

        game.reset();
        assertThat(0 == game.strike).isTrue();
        assertThat(0 == game.ball).isTrue();
    }
}
