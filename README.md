Client-server application that allows users to sort Database using a web interface. 



Implementation:
	Programming language: Java
	Web interface: Vaadin
	RESTful connection with database.





=============================================================================

Faginův top(k) algoritmus

1.	Popis projektu
Cílem projektu je vytvoření aplikace identifikující nejlepších k objektů s použitím Faginova algoritmu a porovnáním výsledků s sekvenčním průchodem. Na vstupu aplikace čeká od uživatele N-tice atributů, na kterých vyhodnocovat top-k operátor a parameter k. Na výstupu aplikace připraví pro uživatele seznam k nejlepších objektů vzhledem k zadaným parametrům.
2.	Způsob řešení
Aplikace používá Faginův top(k) algoritmus který spočívá v tom že potřebujeme:
1.	Předem setřídit databáze
2.	Přečíst k atibutu z každé setříděné tabulky pro každý atribut, a uložit to do vlastní tabulky.
3.	Kompletně vyplnit data v této tabulce, a spočítat agregační funkce (zvolil jsem funkce MAX) pro každý produkt.
4.	Vybrat k nejlepších výsledků a vrátit něj uživatele.

3.	Implementace
Programovací jazyk: Java
Webový interface: Vaadin
Restful spojení s databází.

4.	Příklad
![exaple](https://user-images.githubusercontent.com/47738680/146242276-49196cab-e20d-4554-87c3-9d5262d0ff36.png)

5.	Zaver
Top-k operátor dovolí nám setřídit database podle několika atributu a zvolené agregační funkce, a dělá to rychlejší než běžný sekvenční algoritmus. Ten rozdíl je lepší viditelný když máme hromadu dat a malé k. Faginuv algoritm není optimální, např. protože musíme přečíst k atributu z každou setříděnou tabulky. Tento problém může řešit Treshhold algorithm, ale to už řeší jiný projekt.
