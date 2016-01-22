package Collections.Node.Demo;

import Collections.Node.BilinearNode;

/**
 * Created by ivo on 13-11-2015.
 */
public final class BilinearNodeDemo {

    public static void main() {
        BilinearNode<String> string = new BilinearNode("Hello Bilinear Node");

        System.out.printf("--------------Bilinear Node Demo--------------\n");
        System.out.printf("From Node->" + string.toString() + "\n");
        System.out.printf("-----------------------------------------------\n");


    }
}
