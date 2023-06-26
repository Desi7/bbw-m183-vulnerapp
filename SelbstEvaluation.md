# Vulnerapp - Selbst Evaluation

## Beschreibung und Kriterien

Die Applikation enthält verschiedene Sicherheitslücken, die behoben werden müssen. Das grundlegende Verhalten der
Applikation soll erhalten bleiben. Ziel der Aufgabe ist es, die Sicherheit der Applikation durch die Implementierung von
gängigen Sicherheitsmassnahmen zu verbessern. Die grundlegenden Anforderungen umfassen:

<span style="color:green">✔</span> Verwendung von korrekten REST-Verben.

<span style="color:green">✔</span> Implementierung einer Authentifizierungslösung (z.B. BasicAuth).

<span style="color:green">✔</span> Enforce RBAC (z.B. User- und Admin-Services unterscheiden).

<span style="color:green">✔</span> Aktivieren von CSRF-Protection und Erklärung, warum diese Implementation
funktioniert.

<span style="color:green">✔</span> Implementierung einer sicheren Passwort-Speicherung (Hashing, Passwortregeln).

<span style="color:green">✔</span> Strikte Inputvalidierung (für REST-Endpunkte und Datenbank).

<span style="color:green">✔</span> Behebung der initialen Sicherheitslücken (SQLi, XSS, CSRF).

<span style="color:green">✔</span> Implementierung von securityrelevanten (Unit-)Tests.

## Diskussion

##### Rest-Verben

Als Erstes habe ich die korrekten REST-Verben eingesetzt bei den verschiedenen
Call-Methoden : `@GetMapping` | `@PostMapping` | `@DeleteMapping`
**Verbesserung der Applikation weil:** Es verbessert die Vorhersagbarkeit und Konsistenz der API und reduziert
potenzielle Sicherheitslücken.

##### Authentifizierungslösung (BasicAuth)

Ich habe Basic Auth als Authentifizierungslösung in meiner Applikation implementiert. Bei dieser Methode müssen Benutzer
ihre Anmeldedaten (Benutzername und Passwort) über den HTTP-Header bei jeder Anfrage übermitteln. Dadurch wird
sichergestellt, dass nur authentifizierte Benutzer auf die Applikation zugreifen können und unautorisierte Zugriffe von
Benutzern mit böswilligen Absichten verhindert werden.

- **Verbesserung der Applikation weil:** Die Verwendung von Basic Authentication gewährleistet, dass nur berechtigte
  Benutzer auf geschützte Ressourcen zugreifen können. Durch die Überprüfung von Benutzername und Passwort wird
  sichergestellt, dass nur authentifizierte Benutzer Zugriff erhalten.

##### RBAC (User- und Admin-Services)

Des Weiteren habe ich eine rollenbasierte Zugriffskontrolle (RBAC) eingeführt, um die Funktionalitäten der Applikation
zwischen Benutzern und Administratoren zu unterscheiden. Dadurch wird gewährleistet, dass nur berechtigte Benutzer auf
bestimmte Dienste oder Funktionen zugreifen können.

- **Verbesserung der Applikation weil:** Die Integration von RBAC ermöglicht eine präzise Kontrolle des
  Ressourcenzugriffs basierend auf den Rollen der Benutzer. Dadurch kann zwischen Administrator- und Benutzerdiensten
  differenziert werden.

##### CSRF

Um Angriffe zu verhindern, bei denen ein Angreifer den Benutzer dazu verleitet, ungewollte Aktionen auf der Website
auszuführen, habe ich in meiner Applikation ein CSRF-Schutzmechanismus implementiert. Dieser Mechanismus verwendet
zufällig generierte Tokens, um sicherzustellen, dass nur legitime Anfragen vom Benutzer akzeptiert werden.

`.csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).csrfTokenRequestHandler(requestHandler))`
In dieser Codezeile wird der CSRF-Schutz für das Webanwendungs-Sicherheitsframework festgelegt. Dabei wird das
CookieCsrfTokenRepository und der requestHandler verwendet, um das CSRF-Token zu verwalten.

eim Aufruf der Anwendung wird jedem Benutzer ein CSRF-Token zugewiesen und in einem Cookie gespeichert. Das CSRF-Token
wird dann bei jeder nachfolgenden Anfrage verwendet, um sicherzustellen, dass die Anfrage von derselben Anwendung stammt
und nicht von einer bösartigen Seite.

Durch die Verwendung des CSRF-Tokens im Anfrage-Header kann die Serverseite überprüfen, ob die Anfrage von derselben
Anwendung stammt, die den Benutzer authentifiziert hat. Wenn das CSRF-Token nicht übereinstimmt oder fehlt, kann der
Server die Anfrage ablehnen und vor CSRF-Angriffen schützen.

- **Verbesserung der Applikation weil:** Der CSRF-Schutz ist entscheidend, um Angriffe zu verhindern, bei denen ein
  Angreifer im Namen eines authentifizierten Benutzers manipulierte Anfragen einschleust, um Aktionen auszuführen.

##### Sichere Passwortspeicherung (Hashing)

Die sichere Speicherung von Passwörtern habe ich durch den Einsatz von Hashing-Algorithmen und die Implementierung von
Passwortregeln umgesetzt. Dadurch wird verhindert, dass Passwörter im Klartext gespeichert werden, und die Sicherheit
der Benutzerkonten wird erhöht.

- **Verbesserung der Applikation weil:** Die sichere Speicherung von Passwörtern gewährleistet, dass die Passwörter in
  der Datenbank verschlüsselt werden, wodurch die Sicherheit der Benutzerkonten verbessert wird.

##### Inputvalidierung (REST-Endpunkte / Datenbank)

Zur Verbesserung der Sicherheit der Applikation habe ich für die REST-Endpunkte eine strikte Inputvalidierung
implementiert. Dadurch werden potenziell schädliche oder nicht autorisierte Eingaben blockiert.

- **Verbesserung der Applikation weil:** Eine gründliche Validierung der Eingaben ist unerlässlich, um potenzielle
  Sicherheitslücken wie SQL-Injection und Cross-Site Scripting (XSS) zu verhindern.

##### Weiter mögliche Sicherheitsmechanismen

1. Zwei-Faktor-Authentifizierung (2FA): Die Implementierung von 2FA bietet eine zusätzliche Sicherheitsebene, indem
   neben
   dem Benutzernamen und Passwort ein weiterer Authentifizierungsfaktor benötigt wird.
2. Rate Limiting: Durch die Implementierung von Rate Limiting können Anfragen von Benutzern oder IP-Adressen begrenzt
   werden, um Brute-Force-Angriffe zu verhindern. Bei solchen Angriffen werden innerhalb kurzer Zeit eine große Anzahl
   von Anfragen gesendet.
3. Kontinuierliches Sicherheits-Monitoring: Einrichtung eines Sicherheits-Monitoring-Systems, das verdächtige
   Aktivitäten
   und Angriffe in Echtzeit erkennt und benachrichtigt. Dadurch können Sicherheitsvorfälle schnell identifiziert und
   entsprechende Gegenmaßnahmen ergriffen werden.

##### Probleme, Schwierigkeiten und Verbesserungen

- Probleme gab es bei mir von Anfang an. Zuerst hat IntelliJ mir einige Probleme bereitet, wegen der JDK und der
  gradle.settings.
- Probleme gab es auch oft bei den verschiedenen Implementierungen der Security, meist weil ich eine Kleinigkeit nicht
  genau so gemacht habe wie es gestanden ist.


- Schwierigkeiten empfand ich nur bei dem Verstehen der verschiedenen Sicherheitsmassnahmen und deren Verhinderungen.
  Jedoch haben die Tutorials zu meinem Verständnis geholfen.


- Mir ist aufgefallen das beim 'health'-Endpunkt eine 'SSRF'-Vulnerability gibt. Da ich dies erst am Tag der Abgabe
  gefunden habe, konnte und wollte ich nichts dagegen unternehmen.
- Wenn ich ganz ehrlich bin, könnte man in dieser App noch einige Verbesserungen implementieren. Und nochmals muss ich
  ganz ehrlich sagen, dass mir dieses Thema einfach nicht liegt.

## Selbstevaluation

Im Allgemeinen war die Umsetzung des CSRF-Schutzes zu Beginn überraschend gut. Ich hatte kein größeres Problem damit,
das Cross-Site Request Forgery (CSRF)-Token zu generieren und in meine Formulare einzubinden. Es fühlte sich gut an, die
grundlegenden Sicherheitsvorkehrungen zu implementieren und so die Angriffsfläche meiner Anwendung zu verringern.

Allerdings stieß ich im Verlauf des Projekts auf einige Schwierigkeiten im Zusammenhang mit der 'Security'. Besonders in
während der Mitte und am Ende des Projekts hatte ich mit einigen Herausforderungen zu kämpfen. Spring Security erwies
sich als recht komplex, und ich musste mich intensiv mit den verschiedenen Konzepten und Konfigurationen
auseinandersetzen. Die Vielzahl an Optionen und Funktionen hat mich zunächst etwas überfordert. Es war nicht immer
einfach, die richtigen Entscheidungen zu treffen und die Sicherheitskonfigurationen auf meine spezifischen Anforderungen
anzupassen.

Um mein Verständnis für Spring Security zu verbessern, habe ich mich auf Tutorials und Dokumentationen gestützt. Diese
Ressourcen haben mir geholfen.

Trotz meiner Bemühungen muss ich jedoch feststellen, dass Spring Security nicht unbedingt mein bevorzugtes Thema ist.
Dennoch habe ich mich bemüht, das Beste aus der Situation zu machen und mein Bestes zu geben, um die Sicherheit meiner
Spring Boot-Anwendung zu gewährleisten.

Insgesamt war das Projekt eine gute Lernerfahrung. Es hat mir gezeigt, dass die Sicherheit von Webanwendungen eine
komplexe und herausfordernde Aufgabe ist.


> Inspiration von ChatGPT