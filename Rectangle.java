/**
 * Clasa Rectangle
 * extinde clasa parinte ModelPrimitiva
 * @author claudiu
 * 
 */
public class Rectangle extends ModelPrimitiva{
	private char fld; //Caracterul cu care se va umple corpul obiectului
	/**
	 * Constructorul clasei Rectangle
	 * @param name - numele obiectului
	 * @param fld - caracterul de umplere
	 */
	public Rectangle (String name, char fld){
		super();
		this.nume = name;
		this.fld = fld;
	}
	
	/**
	 * Construieste matricea cu caractere
	 */
	public void MakeBody(){
		body = new char[height][width];
		int i, j;
		for (i=0;i<height;i++){
			for (j=0;j<width;j++){
				body[i][j] = fld;
			}
		}
	}
	
	/**
	 * Redimensioneaza obiectul la noile marimi conform parametrilor si reconstruieste corpul
	 * @param i
	 * @param j - noile dimensiuni
	 */
	public void resize(int i, int j){
		super.resize(i, j);
		MakeBody();//Reconstruiesc corpul
	}
}
