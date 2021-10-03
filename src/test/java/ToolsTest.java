import org.junit.jupiter.api.Test;
import tools.Game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolsTest {
    @Test
    public void testGetAnswer() {
        assertArrayEquals(Game.guessing(1234, 1234), new Integer[]{4, 0});
        assertArrayEquals(Game.guessing(1234, 4321), new Integer[]{0, 4});
        assertArrayEquals(Game.guessing(1234, 1243), new Integer[]{2, 2});
        assertArrayEquals(Game.guessing(1234, 5678), new Integer[]{0, 0});
        assertArrayEquals(Game.guessing(1234, 5643), new Integer[]{0, 2});
    }

    @Test
    public void testGetMainMap() {
        assertEquals(Game.getMainMap().size(),5040);

    }
}
