# dhbw-casino

Also ich hab das so gemacht, dass ihr so wenig wie möglich die Infrastruktur vom gesamten Spiel coden müsst. Ihr müsst nur die Spiellogik implementieren und visualisieren, wenn ihr alles so macht wie hier beschrieben.

Rüdiger wollte von uns, dass wir das Model-View-Controller Pattern implementieren und uns danach richten. Ist bei JavaFX easy.

Model ist die Spiellogik, View ist die Ansicht. Controller handled Eingaben und kommuniziert mit Model und mit View. Der Controller sagt der View, was anzuzeigen ist und dem Model, welche Eingaben getätigt wurden (wie viel der Spieler eingesetzt hat z.B.)

Ihr müsst wenn ihr ein Spiel programmieren wollt folgendes machen:

1. In CasinoController eure FXML mit einer der Funktionen verknüpfen (wie loadMuenzeView).
2. Eine eigene Controllerklasse für euer Spiel erstellen (sie muss Controller interface implementieren)
3. Eine Logikklasse (model-abteilung des MVC Pattern) erstellen. Die Logikklasse muss im Konstruktorparameter einen Controller haben und das Objekt der Logikklasse muss in der Controllerklasse, die ihr erstellt habt initialisiert werden. Eure Controllerklasse gibt sich selber als Parameter.
4. Macht danach am besten alle Methoden für eure eigene Controllerklasse, weil durch das Interface beispielsweise "betRejected()" definiert werden muss. Ihr könnt von MuenzeController kopieren und später so anpassen wie ihr wollt.
5. Baut euer Spiel aus. Arbeitet erstmal in der Logikklasse mit allem was ihr braucht. Änderungen an der View könnt ihr über den Controller regeln. Ihr habt eurer Logikklasse in 3. ja die Connection zum Controller gegeben deswegen könnt ihr locker entspannt den Controller als Zwischenstelle benutzen. Zum Beispiel bei Blackjack kann man, wenn eine Karte gezogen wird in der Logikklasse der Controller-Klasse bescheid sagen damit die eure View aktualisiert und die neueste Karte auch anzeigt.
6. Wenn ihr das Spiel gebaut habt und wollt, dass man Geld einsetzen kann, beachtet folgendes: Sobald die "Wette" eures Spiels beginnt, also z.B. bei Roulette die Scheibe gedreht oder bei Slots der Knopf gedrückt wird, ruft ihr in der Logikklasse einen betCheck beim von der CasinoPlayer Klasse auf. Den CasinoPlayer definiert ihr in eurer Logikklasse mit "private static CasinoPlayer player = CasinoPlayer.getPlayer();". Wenn die Wette wegen zu wenig Geld nicht gemacht werden kann, gibt es eine InsufficientFundsException, die catcht ihr (try-catch) und lasst den Controller dementsprechend handeln. Also z.B. ein Pop-up in der View und/oder dass die Wetten zurückgesetzt werden (kein Einsatz mehr bei Roulette, damit der Spieler neu setzen muss mit genug Geld). In InsufficientFundsException.getMessage() kriegt ihr eine Nachricht, wie viel Geld für die Wette fehlt. Mit der könnt ihr arbeiten. Siehe Münzspiel.
7. Achtet darauf, dass während das Spiel läuft, keine anderen Knöpfe gedrückt werden können. Dafür sind die setBlocked() unblockButton() Methoden im Controller da. Bei Blackjack darf man z.B. nicht mitten im Spiel zu Slots wechseln deswegen muss der Slots button oben deaktiviert werden und sobald das Spiel vorbei ist wieder aktiviert werden (z.B. mit onGameEnd())
8. Wenn ihr dem CasinoPlayer das Geld gebt, macht das über setKontostand in der Logikklasse, dann wird automatisch alles aktualisiert. Benutzt immer onGameEnd() vom Controller wenn alles vorbei ist und setzt alles zurück im Spiel, also die Einsätze, die Felder auf die man gesetzt hat usw.
9. Nutzt wenn ihr wollt convertInput() vom Controller damit alle Schreibweisen von Zahlen in eine einheitliche Form gemacht werden
10. Achtet darauf, dass ihr nicht zulasst, dass man bei Einsatz buchstaben oder so nimmt das goofy

Tipp: wenn ihr irgendwas nicht verstanden habt schaut bei den Klassen / dem Code vom Münzspiel, da bin ich genau so vorgegangen wie da. Natürlich wird je nach Spiel paar Sachen unterschiedlich gehandhabt werden, bei Slots z.B. ergibt es keinen Sinn den Einsatz zurückzusetzen weil es dann zu lange für den Spieler dauert.

