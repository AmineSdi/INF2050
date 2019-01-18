public class FizzBuz{
    public static void main(String[] args){
        for(int i = 0; i<=100){
            System.out.println(fizzBuzz(i));
        }
    }

    public static String fizzBuzz(int n){
        final String FIZZ = "Fizz";
        final String BUZZ = "Buzz";
        final String FIZZBUZZ = "FizzBuzz";
        if(n%3 == 0 && n%5 == 0){
            return FIZZBUZZ;
        }else if(n%3 == 0){
            return FIZZ;
        }else if(n%5 == 0){
            return BUZZ; 
        }
    }
