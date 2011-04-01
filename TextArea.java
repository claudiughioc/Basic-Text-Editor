import java.util.StringTokenizer;

/**
 * Clasa TextArea extinde clasa parinte ModelPrimitiva
 * @author claudiu
 *
 */
public class TextArea extends ModelPrimitiva{
	private boolean split;//Despart sau nu cuvintele la sfarsitul randurilor
	
	/**
	 * Constructorul clasei TextArea
	 * @param name - numele obiectului
	 * @param split - despart cuvintele la sfaristul randului
	 * @param text - textul care va umple spatiul
	 */
	public TextArea(String name, boolean split, String text){
		super();
		nume = name;
		this.text = text;
		this.split = split;
	}
	
	/**
	 * Construieste corpul obiectului
	 */
	public void MakeBody(){
		this.body = new char[height][width];
		int i,j,k=0;
		int p =text.length();
		//Initializez body-ul cu spatii
		for (i=0; i<height;i++){
			for (j = 0; j <width; j++){
				body[i][j] = ' ';
			}
		}
		//Impart textul in functie de despartitorii:
		StringTokenizer t = new StringTokenizer(text, " !?,.",true);
		if(split == true){
				for (i=0; i<height;i++){
					for (j = 0; j <width; j++){
						if (k<p){
							body[i][j] = text.charAt(k);
							k++;
						}
					}
				}
		}
		String s1 = t.nextToken();
		boolean ok = false;
		if (split == false){ //Daca nu trebuie sa impart cuvintele la sfaristul randului
			for (i=0;i<height;i++){	
				j=0;
				if (t.hasMoreTokens() == false && ok == true) break; //Ma opresc daca nu mai sunt cuvinte si daca Stringul actual a fost scris
				while ((width -j) > s1.length()-1){
					int c=0;
					ok = true;//Marchez scrierea cuvantului activ
					for (k=j;k<j+s1.length();k++){//Completez corpul cu Stringul activ
						body[i][k] = s1.charAt(c);
						c++;
					}
					j+=c;
					if (t.hasMoreTokens()==true){//Daca mai sunt cuvinte
						s1 = t.nextToken();//Consider urmatorul cuvant
						ok = false;//Marchez ca inca nu este scris
					}
					else break;
				}
			}
		}
	}
	
	/**
	 * Redimensioneaza obiectul la noile marimi primite ca parametri si reconstruiesc corpul obiectului
	 */
	public void resize (int i , int j){
		super.resize(i, j);
		MakeBody();
	}
}