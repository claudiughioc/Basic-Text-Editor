
/**
 * Clasa parinte pentru toate obiectele grafice
 * Implementeaza interfata GraphicPrimitive
 * @author claudiu
 * 
 */
public class ModelPrimitiva implements GraphicPrimitive{
	protected String nume;//numele obiectului
	protected String text;//Textul primit ca parametru pentru unele obiecte
	public char[][] body;//Corpul obiectului
	protected int line;//Coordonata x
	protected int column;//Coordonata y
	protected int height;//Inaltimea
	protected int width;//Latimea obiectului
	/**
	 * Constructorul pentru clasa ModelPrimitiva
	 * Initializeaza linia, coloana, inaltimea si latimea cu 0
	 */
	public ModelPrimitiva(){
		line = 0;
		column = 0;
		height = 0;
		width = 0;
	}
	/**
	 * 
	 * @return line - coordonata x a obiectului
	 */
	public int getLine(){
		return line;
	}
	public int getColumn(){;
		return column;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public String getNume(){
		return nume;
	}
	
	/**
	 * Muta obiectul la coordonatele i si j primite ca parametru
	 * @param i
	 * @param j - noile coordonate
	 */
	public void move(int i, int j){
		this.line = i;
		this.column = j;
	}
	
	/**
	 * Redimensioneaza obiectul la noile marimi
	 * @param i
	 * @param j - noile dimensiuni
	 */
	public void resize (int i, int j){
		this.height = i;
		this.width = j;
	}
	
	/**
	 * Aduce obiectul in prim - plan
	 * @param m - lista de obiecte
	 */
	public void bringFront(Manager m){
		int n = m.getNo(),i;
		ModelPrimitiva aux = new ModelPrimitiva();
		for (i = 0; i<n;i++){ //Parcurge managerul de obiecte 
			if (nume.equals(m.lista[i].nume)){//Gaseste obiectul dupa nume
				aux = m.lista[i];
				for (int j = i; j<n-1;j++){ //Plaseaza obiectul pe ultima pozitie in lista
					m.lista[j] = m.lista[j+1];//Shiftez toate celelalte obiectele cu o pozitie
				}
			}
		}
		m.lista[n-1] = aux;
	}
	
	/**
	 * Trimite obiectul in fundal
	 * @param m - lista de obiecte
	 */
	public void sendBack(Manager m){
		int n = m.getNo(),i;
		ModelPrimitiva aux = new ModelPrimitiva();
		for (i = 0; i<n;i++){ //Parcurge managerul de obiecte
			if (nume.equals(m.lista[i].nume)){//Gaseste obiectul dupa nume
				aux = m.lista[i];
				for (int j = i; j>0;j--){
					m.lista[j] = m.lista[j-1];//Shifteaza toate obiectele cu o pozitie
				}
				
			}
		}
		m.lista[0] = aux;//Plaseaza obiectul pe prima pozitie in lista, astfel incat sa fie afisat primul
	}
	
	/**
	 * Sterge un obiect din managerul de obiecte
	 * @param m - managerul de obiecte
	 */
	public void delete(Manager m){
		int i,j;
		for (i=0; i<m.getNo(); i++){//Parcurge lista si cauta obiectul dupa nume
			if (m.lista[i].getNume().equals(nume)){
				for(j=i;j<m.getNo()-1;j++){//Shifteaza toate obiectele ramanse cu o pozitie
					m.lista[j] = m.lista[j+1];
				}
			}
		}
		i = m.getNo()-1;//Scade contorul
		m.setNo(i);
	}
	
	/**
	 * Deseneaza obiectul pe pagina
	 * @param p - pagina pe care desenez
	 */
	public void draw(Page p){
		int i,j,k=0,l=0;
		for (i=line;i<line+height;i++){
			if (i<p.getHeight()){
				l=0;
				for (j = column;j<column+width;j++){
					if(j < p.getWidth()){
						p.finalpage[i][j] = body[k][l];//Corpul paginii primeste corpul obiectului in fucntie de pozitia, inaltimea si latimea obiectului
						l++;
					}
				}
				k++;
			}
		}
	}

}
