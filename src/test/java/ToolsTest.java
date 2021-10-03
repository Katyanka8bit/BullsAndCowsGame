import org.junit.jupiter.api.Test;
import tools.Game;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolsTest {
    @Test
    public void testGetAnswer() {
        assertArrayEquals(Game.guessing("1234", "1234"), new Integer[]{4, 0});
        assertArrayEquals(Game.guessing("1234", "4321"), new Integer[]{0, 4});
        assertArrayEquals(Game.guessing("1234", "1243"), new Integer[]{2, 2});
        assertArrayEquals(Game.guessing("1234", "5678"), new Integer[]{0, 0});
        assertArrayEquals(Game.guessing("1234", "5643"), new Integer[]{0, 2});
    }

    @Test
    public void testGetMainMap() {
        assertEquals(Game.getMainMap().size(),5040);

    }

    @Test
    public void testSitoMap(){
        Map<String,Boolean> map = new LinkedHashMap<>();
        map.put("0123",true);
        map.put("4210",true);
        Game.getSitoMap(map,"0");
        assertEquals(map.get("0123"),false);
        assertEquals(map.get("4210"),false);
        map.put("0123",true);
        map.put("4210",true);
        Game.getSitoMap(map,"4");
        assertEquals(map.get("0123"),true);
        assertEquals(map.get("4210"),false);
    }
}
