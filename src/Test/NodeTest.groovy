package Test

import Main.node
import org.junit.Test

class NodeTest extends groovy.util.GroovyTestCase {


    @Test
    void testSet() {
        node n1 = new node(1);
        node n2 = new node(2);
        n2.addParent(n1)
        assertEquals(n1,n2.getParent().get(0))
        assertEquals(n2,n1.getChild().get(0))

    }
    void testHas() {

        node n1 = new node(1);
        node n2 = new node(2);
        assertFalse(n1.hasChild(2))
        assertFalse(n1.hasParent(2))
        assertFalse(n2.hasChild(1))
        assertFalse(n2.hasParent(1))
        n1.addChild(n2);
        assertTrue(n1.hasChild(2))
        assertFalse(n1.hasParent(2))
        assertFalse(n2.hasChild(1))
        assertTrue(n2.hasParent(1))

    }


}
