/**
 * Clasa TextLine, extindere a clasei parinte ModelPrimitiva
 * @author claudiu
 *
 */
public class TextLine extends ModelPrimitiva{
	/**
	 * Constructorul clasei TextLine
	 * Construieste si corpul obiectului in functie de dimensiunea textului
	 * @param name - numele obiectului
	 * @param text - textul de scris
	 */
	public TextLine(String name, String text){
		super();
		nume = name;
		height = 1;
		width = text.length();
		this.text = text;
		MakeBody();//Construiesc corpul
	}
	
	/**
	 * Metoda de construire a corpului obiectului
	 */
	public void MakeBody(){
		body = new char[height][width];//Realoc o matrice in functie de latime si inalime
		int i;
		for (i=0; i<width; i++){
			if (i<text.length()){
				body[0][i] = text.charAt(i);//Construiesc cu literele din text
			}
			else body[0][i] = ' ';//...iar restul cu spatii
		}
	}
	
	/**
	 * Redimensioneaza obiectul cu noile marimi primite ca parametru si reconstruieste corpul
	 */
	public void resize(int i, int j){
		super.resize(i, j);
		this.MakeBody();
	}
}
