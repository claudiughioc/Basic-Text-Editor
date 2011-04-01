Tema 2 este structurata astfel:
- interfata GraphicPrimitive descrie toate obiectele grafice;
- clasa parinte ModelPrimitiva cuprinde campurile si metodele specifice unui obiect grafic
- clase pentru fiecare obiect grafic: TextLine, Title, TextArea, TextBox, Rectangle, List, mostenesc clasa parinte ModelPrimitiva
- clasa Plugins ce cuprinde metode pentru fiecare plugin
- clasa Manager ce cuprinde o lista in care voi stoca toate obiectele dintr-o pagina, iar afisarea lor sa va face in ordinea in care s-au pus
- clasa Page descrie pagina pe care voi scrie toate obiectele
- clasa BPage descrie pagina auxiliara, specifica cerintelor bonusului
- clasa MainClass care citeste si interpreteaza fiecare comanda

	Descriere program:
	Programul citeste fiecare linie din consola pana intalneste comanda quit. Se creeaza o pagina noua, goala. La citirea unei comenzi se interpreteaza si se creeaza obiecte noi, sau se executa operatii asupra celor existente. La creearea unor obiecte noi se apeleaza constructorul specific fiecarei clase si se adauga obiectul intr-o lista (manager). Daca obiectul exista deja, metoda add din manager afiseaza un mesaj specific. La intalnirea comenzii print, se va executa metoda print a paginii curente in modul urmator: se parcurge managerul de obiecte si pentru fiecare obiect se apeleaza metoda draw. Aceasta suprascrie corpul (matricea) paginii cu corpul obiectului respectiv, in limita inaltimii si a latimii. Metodele bringFront si sendBack parcurg managerul si trimit obiectul respectiv pe ultima respectiv prima pozitie, iar celelalte le muta cu o pozitie, incat ordinea lor initiala sa fie pastrata. La intalnirea comenzii move, obiectul isi va muta coordonatele x si y in functie de noii parametri, iar la intalnirea comenzii resize, acesta isi va modifica campurile height (inaltime) si width (latime) si se va construi din nou matricea caracteristica obiectului respectiv. La intalnirea comenzii quit programul se opreste.

	Desccriere clase:
	Clasa Page cuprinde o matrice de char, finalpage, care va memora varianta finala a paginii. Este insotita de caracteristicile sale, height, width, getter si setteri pentru acestea. Afisarea ei in final se va face cu metoda print. Contine si metoda resizePage care ii va modifica ialtimea si latimea. Initial aceasta este de 30 x 60.
	Clasa ModelPrimitiva contine campurile de descriere a fiecarui obiect : nume, text (in cazul TextLine), height, width, line, column, body (matricea de char-uri) si metode:
		-move: schimba coordonatele line si column cu cele primite ca parametri
		-resize: schimba marimea corpului obiectului height si widht cu cele primite ca parametri
		-sendBack: trimite obiectul in fundal prin parcurgerea listei de obiectele si modificare pozitiei obiectului respectiv la 0
		-bringFront: aduce obiectul in prim - plan prin parcurgerea listei de obiecte si modificarea pozitiei obiectului respectiv la n-1 			(numarul total de obiecte)
		-delete: sterge obiectul prin indepartarea lui din lista manager
		-draw: Este apelata in metoda print a paginii. Completeaza corpul paginii cu corpul obiectului grafic (body) in limita dimensiunilor 			height si width a obiectului, la pozitia line si column. 
	Clasele TextLine, List, TextBox, TextArea, Title, Rectangle mostenesc clasa ModelPrimitiva. In plus vor avea metoda MakeBody care construieste body, matricea de caractere, intr-un mod specific fiecarui obiect grafic. De asemenea la metoda resize se va apela in plus MakeBody pentru a reconstrui matricea in functie de noile dimensiuni.
	Clasa Plugins contine metodele:
		- doubleWidth: apeleaza resize pe un obiect cu aceeasi inaltime si dublul latimii
		- doubleHeight: apeleaza resize pe un obiect cu aceeasi latime si dublul inaltimii
		- maximize: apeleaza move la 0 si 0, si resize cu coordonatele paginii actuale.
		- center: centreaza obiectul prin apelarea metodei move la pozitii calculate in functie de dimensiunile paginii si ale obiectului
		- makeSquare: apeleaza resize la minimul dintre dimensiunile actuale
		- toUpperCase/ tolowerCase rescrie matricea obiectului inlocuind literele mici cu litere mari, respectiv invers
	Clasa Manager cuprinde o lista de obiecte grafice, un contor, si metoda add care adauga un obect in lista si verifica daca obiectul exista deja, caz in care afiseaza mesajul:"Obiect deja existent"
	Pentru implementarea bonusului am creat clasa BPage. Aceasta mosteneste, ca si celelalte obiecte, clasa parinte ModelPrimitiva. Are in plus Stringul path (calea catre fisierul din care citeste) si o pagina clona p. La creare se construieste matricea finalpage al paginii p, si se copie in body. Crearea clonei s-a facut pentru a se putea apela pe acest obiect metode de gen center, maximize care au fost deja implementate cu unul din parametri de tip Page. In plus pentru crearea aspectului final al obiectului pagina, se apeleaza metoda draw a fiecarui obiect din manager, metoda care are ca parametru o pagina pe care sa deseneze. In cazut bonusului, aceasta pagina va fi pagina clona, iar toate obiectele din fisier se vor scrie pe matricea p.finalpage. La sfarsitul metodei MakeBody, matricea body copie matricea finalpage.
