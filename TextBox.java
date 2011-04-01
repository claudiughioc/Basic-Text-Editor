import java.util.StringTokenizer;

/**
 * Clasa TextBox, extindere a clasei parinte ModelPrimitiva
 * @author claudiu
 *
 */
public class TextBox extends ModelPrimitiva{
	private boolean split;
	private char border; //Caracterul de marginire a spatiului
	private int h2,w2;//Inaltimea si latimea efectiva a textului
	
	/*
	 * Constructorul clasei TextBox
	 * @param name, split, border, text
	 */
	public TextBox(String name, boolean split, char border, String text){
		super();
		this.nume = name;
		this.split = split;
		this.border = border;
		this.text = text;
	}
	
	/*
	 * Metoda de construire a corpului obiectului. Algoritmul este asemanator cu cel de la TextArea cu modificari la inaltime si latime
	 */
	public void MakeBody(){
		this.body = new char[height][width];
		int i,j,k=0;
		int p =text.length();
		//Initializez body-ul cu spatii si caracter pe margini
		for (i=0; i<height;i++){
			for (j = 0; j <width; j++){
				if (i==0 || i==height-1){
					body[i][j] = border;
				}
				else if (j==0 || j==width-1){
					body[i][j] = border;
					}
				else body[i][j] = ' ';
			}
		}
		//Modific inaltimea si latimea corpului
		w2 = width -1;
		h2 = height -1;
		
		StringTokenizer t = new StringTokenizer(text, " !?,.",true);
		if(split == true){
				for (i=1; i<h2;i++){
					for (j = 1; j <w2; j++){
						if (k<p){
							body[i][j] = text.charAt(k);
							k++;
						}
					}
				}
		}
		String s1 = t.nextToken();
		boolean ok = false;
		if (split == false){
			for (i=1;i<h2;i++){	
				j=1;
				if (t.hasMoreTokens() == false && ok == true) break;
				while ((w2 -j) > s1.length()-1){
					int c=0;
					ok = true;
					for (k=j;k<j+s1.length();k++){
						body[i][k] = s1.charAt(c);
						c++;
					}
					j+=c;
					if (t.hasMoreTokens()==true){
						s1 = t.nextToken();
						ok = false;
					}
					else break;
				}
			}
		}
		
	}
	
	/**
	 * Redimensionez obiectul cu noile marimi primite ca parametri si reconstruiesc corpul
	 */
	public void resize(int i, int j){
		super.resize(i, j);
		MakeBody();
	}
}
