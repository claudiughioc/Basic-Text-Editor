import java.util.ArrayList;
/**
 * 
 * @author claudiu
 * Clasa Lista
 */

public class Lista extends ModelPrimitiva{
	private ArrayList<String> lst;
	private char ind;
	/**
	 * Constructorul obiectului Lista
	 * @param name - numele obiectului
	 * @param ind - caracterul de identare
	 * @param lst - lista de Stringuri ce va fi scrisa
	 */
	public Lista(String name, char ind, ArrayList<String> lst){
		this.nume = name;
		this.ind = ind;
		this.lst = lst;
		this.height = lst.size();
	}
	
	/**
	 * Construieste corpul listei
	 */
	public void MakeBody(){
		this.body = new char[height][width];
		int i,j,k;
		String s;
		for (i=0;i<height;i++){
			if (i<lst.size()){
				body[i][0] = this.ind;//Pun pe prima pozitie a fiecarei linii caracterul ind
				s = lst.get(i);
				k=0;
				for (j=1;j<width;j++){
					if (k<s.length()){
						body[i][j] = s.charAt(k);//Scriu pe fiecare linie Stringul respectiv
						k++;
					}
					else body[i][j] = ' ';//In rest umplu cu spatii
				}
			}
			else for (j=0;j<width;j++){
				body[i][j] = ' ';//Umplu restul corpului cu spatii
			}
		}
	}
	/*
	 * Redimensioneaza conform clasei parinte si reconstruieste corpul
	 * @see ModelPrimitiva#resize(int, int)
	 * 
	 */
	public void resize(int i, int j){
		super.resize(i, j);
		MakeBody();
	}
}
