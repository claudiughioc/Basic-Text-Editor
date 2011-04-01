/**
 * Clasa Title, extindere a clasei parinte ModelPrimitiva
 * @author claudiu
 *
 */
public class Title extends ModelPrimitiva{
	private char sub;//Caracterul de subliniere
	
	/**
	 * Constructorul clasei Title
	 * @param nume - numele obiectului
	 * @param sub - caracterul de subliniere
	 * @param text - textul de scris
	 */
	public Title(String nume, char sub, String text){
		super();
		this.nume = nume;
		this.text = text;
		this.sub = sub;
	}
	
	/**
	 * Construieste corpul Obiectului
	 */
	public void MakeBody(){
		int i;
		this.body = new char[height][width];
		for (i=0;i<width;i++){
			if (i<text.length()){
				body[0][i] = text.charAt(i);//Umple corpul cu literele textului
				if (height > 1){
					body[1][i] = sub;//Completeaza caracterele de subliniere
				}
			}
			else{			//In rest umplu cu spatii
				body[0][i] = ' ';
				body[1][i] = ' ';
			}
		}
		for (i=2;i<height;i++){		//Restul corpului se umple cu spatii
			for (int j=0;j<width;j++){
				body[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Redimensioneaza obiectul la noile marimi primite ca parametru si reconstruiesc corpul
	 */
	public void resize(int i, int j){
		super.resize(i, j);
		MakeBody();
	}
}
