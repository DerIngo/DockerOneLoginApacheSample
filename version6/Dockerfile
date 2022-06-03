FROM openjdk:8
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Main.java
CMD ["java", "Main"]

ach so geht das nicht so pralle. Dependency Management und so.

TODOs
-----
Projekt 1, "version5":
Neuen User registrieren lassen
Wie kann der Supporter/das Support-Team/der App-Admin darüber eine Info erhalten?
Neuen User "zulassen"?
Wenn ein User einen Covestro Account hat, wie kann er einen CCP-Zugang anfragen?

Logout? "irgendwie" über Link: https://deringo-dev.onelogin.com/oidc/2/logout?post_logout_redirect_uri=http://localhost&id_token_hint=xyz
Im RP den Header iv-user setzen

Projekt 2:
Anleitung Daten einsammeln (API-Key etc.)
Doku: https://developers.onelogin.com/api-docs/2/getting-started/dev-overview
Beispielcode für AccessToken, alle User, alle Rollen, alle Infos zu einem User anzeigen
neuen User "zulassen"


Projekt 3:
Primefaces Beispiel App um alle User anzeigen zu lassen. ggf. alle OneLogin-Rollen.
ggf. Config in der Oberfläche
Alle Userdetails
User aktivieren, User einer Application Rolle zuweisen, oder sowas 


AccessFilter für CCP