package 实验三;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *
 * @author Administrator
 */
public class ArithmeticExpression {
    public Queue<String> token = new LinkedBlockingDeque<>();

    public boolean start(){
        if(E() && token.size() == 0){
            return true;
        }else {
            return false;
        }
    }
    
    public boolean E(){
        return T() && EM();
    }

    public boolean T(){
        return F() && TM();
    }

    public boolean EM(){
        if(token.size() == 0){
            return true;
        }

        String temp = token.peek();

        if(temp.equals("+") || temp.equals("-")){
            token.poll();
            E();
        }

        return true;
    }

    public boolean F(){
        if(token.size() == 0){
            return true;
        }

        if(token.peek().equals("(")){
            token.poll();
            E();
            
            if(token.peek().equals(")")){
                token.poll();
            }else{
                return false;
            }
        }else {
            String statement = token.peek();
            for(char c : statement.toCharArray()){
                if(!Character.isDigit(c)){
                    return false;
                }
            }

            token.poll();
        }
        
        return true;
    }
    
    

    
    public boolean TM(){
        if(token.size() == 0){
            return true;
        }

        String temp = token.peek();

        if(temp.equals("*") || temp.equals("/")){
            token.poll();
            F();
            T();
        }
        
        return true;
    }

    public ArithmeticExpression(String s){
        buildStack(s);
    }

    public void buildStack(String s){
        ArrayList<Character> characters = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '(', ')'));
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()){
            if(characters.contains(c)){
                if(sb.length()>0)
                    this.token.offer(sb.toString());
                this.token.offer(c+"");

                sb = new StringBuilder();
            }else {
                sb.append(c);
            }
        }

        if(sb.length()>0)
            this.token.offer(sb.toString());

    }


    public static void main(String[] args) {
        String s = "(3+7)/5";

        ArithmeticExpression ae = new ArithmeticExpression(s);
        System.out.println(ae.start());
    }
}
