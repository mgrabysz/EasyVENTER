# EasyVENTER

## Skład zespołu
- [Łukasz Szarejko](https://github.com/szaryvip)
- [Patryk Będkowski](https://github.com/patrick-bedkowski)
- [Szymon Skarzyński](https://github.com/Cat107)
- [Marcin Grabysz](https://github.com/mgrabysz)

## Wstęp
Aplikacja będzie platformą do zakupu biletów na wydarzenia sportowe.
Dostępne będą dwa tryby dostępu. Organizatorzy imprez za pomocą aplikacji
będą mogli publikować informacje na temat wydarzeń, a zwykli użytkownicy
będą mieć możliwość przeglądania listy nadchodzących wydarzeń, zakupu biletów, a także zarządzania tymi, które już posiadają (np. anulowanie biletu).

## Architektura rozwiązania

### Program
Aplikacja desktopowa napisana w Javie przy pomocy Swinga
- Frontend, okna logowania, rejestracji, przeglądania wydarzeń etc. (Marcin Grabysz, Szymon Skarzyński)
- Moduł logowania/rejestracji przy uruchomieniu aplikacji (Patryk Będkowski)
- Przeglądarka wydarzeń z możliwością kupienia biletu (Szymon Skarzyński)
- Moduł do zarządzania biletami (Łukasz Szarejko)
- Moduł do tworzenia wydarzeń (Łukasz Szarejko)
<p>Aplikacja będzie korzystała z bazy danych Oracle SQL. (Główny odpowiedzialny za łączenie z bazą danych - Patryk Będkowski)</p>

### Testy
Wszystkie klasy odpowiadające za backend aplikacji zostaną przetestowane za pomocą testów jednostkowych.
Natomiast okienka (frontend) zostaną przetestowany poprzez korzystanie z programu przez kilku uczestników, aby wykryć potencjalne bugi.
Testy jednostkowe każdy członek napisze indywidualnie do swoich klas.

### Wstępne plany bazy danych
1. Wydarzenia: ID, miejsce, data, godzina, nazwa, id organizatora, bilety
2. Bilety: ID, typ, miejsce, sektor, cena
3. Organizatorzy: ID, nazwa, login, hasło, email, wydarzenia organizowane
4. Klienci: ID, imię, nazwisko, login, hasło, email, kupione bilety

![ERD_EasyVENT.png](./diagrams/ERD_EasyVENT.png)

### Model Relacyjny bazy
![EasyVEND_rel_model.png](./diagrams/EASYVENT_DATABASE_RELATIONAL_MODEL.png)

### Diagram funkcjonalności aplikacji

![EasyVENT_diagram_flow.png](./diagrams/EasyVENT_diagram_flow.png)

### Diagram klas

![ClassDiagram.png](./diagrams/ClassDiagram.png)
