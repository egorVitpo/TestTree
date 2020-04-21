package tree;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    @DisplayName("Удаление из пустого дерева")
    public void deleteFromEmptyTree() {
        AVLTree t = new AVLTree();

        for (int i = 0; i < 100; i++)
            t.delete(i);

        assertEquals("", t.toString());
    }

    @Test
    @DisplayName("Верное составление дерева")
    public void createCorrectTree() {

        AVLTree t = new AVLTree();

        for (int i = 0; i < 30; i++)
            t.add(i);

        String correctAnswer = "15 7 3 1 0 2 5 4 6 11 9 8 10 13 12 14 23 19 17 16 18 21 20 22 27 25 24 26 28 29";

        assertEquals(correctAnswer, t.toString());
    }

    @Test
    @DisplayName("Верное удаление из дерева")
    public void correctDeleteFromTree() {

        AVLTree t = new AVLTree();

        for (int i = 0; i < 30; i++)
            t.add(i);

        t.delete(5);
        t.delete(6);
        t.delete(23);
        t.delete(29);

        String correctAnswer = "15 7 3 1 0 2 4 11 9 8 10 13 12 14 24 19 17 16 18 21 20 22";

        assertEquals(correctAnswer, t.toString());
    }

    @Test
    @DisplayName("Удаление несуществующих элеентов из непустого дерева")
    public void deleteNonexistentElementsFromTree() {

        AVLTree t = new AVLTree();

        for (int i = 0; i < 30; i++)
            t.add(i);

        t.delete(50);
        t.delete(60);
        t.delete(230);
        t.delete(290);

        String correctAnswer = "15 7 3 1 0 2 5 4 6 11 9 8 10 13 12 14 23 19 17 16 18 21 20 22 27 25 24 26 28 29";

        assertEquals(correctAnswer, t.toString());
    }
}