
/**
 * Clasa care cuprinde toate tipurile de plugin
 * @author claudiu
 *
 */
public class Plugins {
	/**
	 * Dubleaza latimea obiectului prin apelarea metodei resize
	 * @param pr - ModelPrimitiva
	 */
	public void doubleWidth(ModelPrimitiva pr){
		int j = pr.getWidth();
		int i = pr.getHeight();
		pr.resize(i, j*2);
	}
	
	/**
	 * Dubleaza inaltimea obiectului prin apelarea metodei resize
	 * @param pr - ModelPrimitiva
	 */
	public void doubleHeight (ModelPrimitiva pr){
		int i = pr.getHeight();
		int j = pr.getWidth();
		pr.resize(2*i, j);
	}
	
	/**
	 * Realizeaza incadrarea obiectului in toata pagina prin apelarea metodelor move si resize
	 * @param pr - ModelPrimitiva
	 * @param p - Pagina
	 */
	public void maximize(ModelPrimitiva pr , Page p){
		int i = p.getHeight();
		int j = p.getWidth();
		pr.move(0, 0);
		pr.resize(i, j);
	}
	
	/**
	 * Realizeaza incadrarea obiectului in centrul paginii in functie de dimensiunea obiectului
	 * @param pr - ModelPrimitiva
	 * @param p - Page
	 */
	public void center(ModelPrimitiva pr, Page p ){
		int i1 = pr.getHeight();
		int i2 = p.getHeight();
		int j1 = pr.getWidth();
		int j2 = p.getWidth();
		int k = Math.round(i2/2) - Math.round(i1/2);
		int l = Math.round(j2/2) - Math.round(j1/2);
		pr.move(k, l); //Muta obiectul la coordonatele calculate
	}
	
	/**
	 * Redimensioneaza obiectul astfel incat acesta sa aiba latimea si inaltimea egale cu minimul dintre ele
	 * Apeleaza metoda resize
	 * @param pr - ModelPrimitiva
	 */
	public void makeSquare(ModelPrimitiva pr){
		int i = pr.getHeight();
		int j = pr.getWidth();
		int k;
		if (i<=j){
			k = i;
		}
		else k = j;
		pr.resize(k,k);
	}
	
	/**
	 * Transforma toate literele mici din corpul obiectului in litere mari
	 * @param pr - ModelPrimitiva
	 */
	public void toUpperCase(ModelPrimitiva pr){
		int i,j,k;
		for (i=pr.getLine();i<pr.getLine()+pr.getHeight();i++){
			for (j= pr.getColumn();j<pr.getColumn() + pr.getWidth(); j++){
				k = (int) pr.body[i][j];
				if (k>=97 && k<=122){//testez daca fiecare caracter este litera mica
					k -= 32;//Modific codul ASCII cu -32
				}
				pr.body[i][j] = (char) k;//Modific body-ul cu litera mare
			}
		}
		if (pr instanceof TextLine){
			pr.text = pr.text.toUpperCase();
		}
	}
	
	/**
	 * Transforma toate literele mari din interiorul obiectului 
	 * @param pr - ModelPrimitiva
	 */
	public void toLowerCase(ModelPrimitiva pr){
		int i,j,k;
		for (i=pr.getLine();i<pr.getLine()+pr.getHeight();i++){
			for (j= pr.getColumn();j<pr.getColumn() + pr.getWidth(); j++){
				k = (int) pr.body[i][j];
				if (k>=65 && k<=90){
					k += 32;
				}
				pr.body[i][j] = (char) k;
			}
		}
		if (pr instanceof TextLine){
			pr.text = pr.text.toLowerCase();
		}
	}
}
