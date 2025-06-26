## Domaći zadatak iz predmeta Odabrana poglavlja iz projektovanja poslovnih aplikacija

Zadatak: Dizajn aplikacije za radnju računarske opreme

Ova aplikacija omogućava prodaju računarske opreme putem RESTful API-ja. Izgrađena je korišćenjem Jersey biblioteke za implementaciju REST servisa, povezana je sa MySQL bazom podataka putem JDBC-a i koristi Maven za upravljanje zavisnostima i izgradnju projekta.

Aplikacija koristi DAO sloj za rad sa bazom, servisni sloj za poslovnu logiku, i omogućava operacije kao što su: pregled opreme, kupovina proizvoda, ažuriranje lagera i regulisanje korisničkog računa.

Takođe podržava transakcionu obradu kupovine sa rollback mehanizmom u slučaju grešaka.

Commit :
* data sloj
* korisnikDAO, korisnikRest, implementirana GET metoda i isprobana
* Lista svih proizvoda
* Detaljan prikaz proizvoda
* Registracija (dodavanje korisnika)
* Prikaz podataka o korisniku prilikom dobrodoslice
* Podesavanje pretrage (donja i gornja granica, vrsta, kljucna rec)
* Kupovina proizvoda
* login korisnika
