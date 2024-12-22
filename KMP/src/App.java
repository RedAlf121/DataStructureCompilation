import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Patron patron = new Patron("ABABCABAB");
        ArrayList<Integer> list = kmp("ABABDABACDABABCABAB", patron);
        System.out.println(list.size());
        for(Integer i : list)
            System.out.println("Aparecio un patron en el indice: " + i);
    }

    public static ArrayList<Integer> kmp(String s, Patron patron)
    {
        ArrayList<Integer> patterns = new ArrayList<>();        
        int stringPoint = 0;
        int patternPoint = 0;
        boolean stop = false;
        while(stringPoint < s.length() && !stop)
        {
            if(patron.match(patternPoint, s, stringPoint))
            {
                stringPoint++;
                patternPoint++;                
                if(patron.encontrado(patternPoint))
                {
                    patterns.add(stringPoint-patternPoint);
                    patternPoint = patron.proximoFuncionPi(patternPoint);
                }
            }
            else if(patternPoint == 0)
            {
                stringPoint++;
                stop = s.length() - stringPoint + 1 < patron.size();
            }
            else    
                patternPoint = patron.proximoFuncionPi(patternPoint); 
        }
        return patterns;
    }

}
