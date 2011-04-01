/**
 * 
 * @author claudiu
 * Reprezinta pagina pe care se vor scrie toate obiectele
 */
public class Page {
	public char[][] finalpage; //Corpul paginii
	private int height;
	private int width;
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	
	/**
	 * Constructorul paginii, initializeaza corpul paginii cu spatii
	 */
	public Page(){
		finalpage = new char[30][60];
		int i,j;
		height = 30;
		width = 60;
		for (i=0; i<30 ; i++){
			for (j=0; j<60; j++){
				finalpage[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Redimensioneaza Pagina la noile marimi
	 * @param i - coordonata x
	 * @param j - coordonata y
	 */
	public void resizePage(int i, int j){
		int k,l;
		finalpage = new char[i][j];//Construiesc un nou corp si il umplu cu spatii
		for (k = 1 ; k<i ;k++){
			if (k<height){
				for (l=width; l<j;l++){
					finalpage[k][l] = ' ';
				}
			}
			else{
				for (l=1; l<j;l++){
					finalpage[k][l] = ' ';
				}
			}
		}
		height = i;
		width = j;
	}
	
	/**
	 * Realizeaza afisarea tuturor obiectelor pe pagina prin desenarea tuturor obiectelor peste corpul paginii
	 * @param m - managerul de obiecte
	 */
	public void print(Manager m){
		int i,j;
		//Initializez corpul final cu spatii
		for (i=0; i<height ; i++){
			for (j=0; j<width; j++){
				finalpage[i][j] = ' ';
			}
		}
		i = m.getNo();
		//Parcurg lista de obiecte si apelez metoda draw pentru fiecare obiect
		for (j=0;j<i;j++){
			m.lista[j].draw(this);
		}
		//Afisez matricea
		for (i=0;i<height;i++){
			for (j=0;j<width;j++){
				System.out.print(finalpage[i][j]);
			}
			System.out.println();
		}
		
	}
	
}
