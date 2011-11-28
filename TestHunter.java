/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @class TestHunter
 * @description:
 *
 *
 * @author Sam Sherar (sbs1)
 */
public class TestHunter {
    public static void main(String[] args) {
        Hunter h = new Hunter(0,11);
        h.echoPath();
        Position fakePos = new Position(11,11);
        System.out.println(h.comparePosition(fakePos));
    }
}
