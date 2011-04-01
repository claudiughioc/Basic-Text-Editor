/**
 * 
 * @author claudiu
 * Reprezinta managerul de obiecte grafice
 * Contine o lista de obiecte curente
 */
public class Manager {
	public ModelPrimitiva [] lista; 
	private int no;//Contor de obiecte
	
	/**
	 * Constructorul clasei Manager, initializeaza lista cu 100 elemente
	 */
	public Manager(){
		lista = new ModelPrimitiva [100];
		no = 0;
	}
	
	/**
	 * Adauga fiecare obiect in lista manager si verific daca obiectul exista deja
	 * @param pr - modelul de obiect grafic
	 */
	public void add(ModelPrimitiva pr){
		int i;
		if (no>0){
			for (i=0;i<no;i++){
				if (pr.nume.equals(lista[i].nume)==true){
					System.out.println("Obiect deja existent");//Returnez eroarea in caz ca obiectul exista deja
					return;
				}
			}
		}
		lista[no] = pr;
		no++;
	}
	
	/**
	 * Getter pentru no
	 * @return contorul listei
	 */
	public int getNo(){
		return no;
	}
	
	/**
	 * Seteaza contorul listei cu valoarea data ca parametru
	 * @param no - contorul listei
	 */
	public void setNo(int no){
		this.no = no;
	}
	
}
