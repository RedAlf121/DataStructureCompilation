public class Patron
{
    private String patron;
    
    private int pi[];
    
    public Patron(String patron)
    {
        setPatron(patron);        
    }
    
    public String getPatron() {
        return patron;
    }

    private void setPatron(String patron)
    {
        this.patron = patron;
        crearFuncionPi(patron);
    }

    private void crearFuncionPi(String patron)
    {
        pi = new int[patron.length()];
        pi[0] = 0;
        char aux;
        char actual;
        for(int i = 1; i < pi.length; i++)
        {
            actual = patron.charAt(i);
            aux = patron.charAt(pi[i-1]);
            if(actual == aux)
                pi[i]=pi[i-1]+1;
        }
    }

    public int[] getPi()
    {
        return pi;
    }

    public int proximoFuncionPi(int indice)
    {
        return pi[indice-1];
    }

    public boolean match(int patternPoint, String s, int stringPoint) {
        return patron.charAt(patternPoint) == s.charAt(stringPoint);
    }

    public boolean encontrado(int patternPoint) {
        return patternPoint == patron.length()-1;
    }

    public int size() {
        return pi.length;
    }

}