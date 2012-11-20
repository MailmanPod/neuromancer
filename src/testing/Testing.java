/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 *
 * @author Fuyiko Kurosan
 */
public class Testing {

    private static int counter = 0;
    private static String name = "Neuromancer Class";
    
    private static String ter(double[] vectorTest){
        StringBuilder builder = new StringBuilder();
        
        builder.append("\nEnter in ter method");
        
        for(int i = 0; i < vectorTest.length; i++){
            builder.append(" Vector: ").append(i).append(" Content: ").append(vectorTest[i]);
        }
        
        counter += vectorTest.length;
        
        return builder.toString();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        double[][] initial = { {1,2,3,4}, 
                               {5,6,7,8}, 
                               {9,10,11,12} };
        
        for(int i = 0; i < initial.length; i++){
            System.out.println(ter(initial[i]));
        }
        System.out.println("Amount of elements: " + counter);
    }
}
