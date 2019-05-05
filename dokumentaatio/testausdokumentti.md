# Testausdokumentti

Ohjelman testaus koostuu lähinnä _game.domain_- ja _game.logic.ai_-luokkien yksikkötestauksesta ja manuaalisista järjestelmätesteistä.

## Yksikkötestaus

### Sovelluslogiikka

Pelin sekä varsinkin ligGDX-kirjaston toteuttaman suoritussyklin vuoksi yksikkötestaus täytyi rajoittaa domain:in ja ai:hin, sillä esim. actorien tai MissionLogicin testaaminen kaatui joka yrityksellä graafisten elementtien kutsuihin, joita varten olisi tarvinnut luoda libGDX:n Game-olio ja asettaa sille aktiivinen ruutu. 

_game.domain_-luokan testit testavat lähinnä jokaisen luokan perustoiminnallisuutta metodi kerrallaan. _game.logic.ai_-luokan testit testaavat yksikön kokonaistoiminnallisuutta laajemmin, sillä ai-luokilla ei tarkoituksella ole juuri gettereitä ja settereitä, vaan ne ovat itsenäisempiä kokonaisuuksia joissa pelin yksiköiden lopullinen päätöksenteko tapahtuu. Näissä paketeissa testikattavuuden ulkopuolelle jäävät lähinnä kollisiometodit, joita kutsutaan suoraa MissionLogicista. Testauksessa on myös osana pelin kollisiotestaajan, _game.logic_- pakkauksessa olevan CollisionCheker-lukan testaus, jolloin olioiden omien suoraviivaisten kollisiorutiinien testaus ei tuntunut niin tarpeelliselta.

### Testikattavuus

Käyttöliittymäluokkia ja niistä ainakin tavalla tai toisella riippuvaisia MapLoader- sekä MissionLogic- luokkia lukuunottamatta on testikattavuus 89%.

kuva tähän

Varsinkin MissionLogic:issa olisi saattanut olla joitain metodeja tai metodin osia joita olisi voinut koittaa eriyttää ja testata, mutta yritykset johtivat monesti vain uusiin libGDX:n virheimoituksiin, joten testaaja näki että luokan toimintaa oli helpompi ja suoraviivaisempi testata manuaalisilla järjestelmätesteillä. Myös _game.map_-pakkauksen Map-luokka jäi vielä testaamatta.

## Järjestelmätestaus

Järjestelmätestaus on suoritettiin manuaalisesti.

### Asennus

Sovellusta on testattu käyttöohjeen kuvaamalla tavalla Linux-ympäristössä. Alun perin kuvien ja kartan pakkaaminen .jar-tiedostoon tuotti hankaluuksia, mutta ongelma on nyt korjattu hieman kömpelösti apukirjaston avulla.


### Toiminnallisuudet

Kaikki määrittelydokumentissa ja käyttöohjeessa listatut kohdat on tarkistettu toimivan ainakin Linux-ympäristössä.

## Sovellukseen jääneet laatuongelmat

Sovelluslogiikan testaaminen osoittautui yllättävän hankalaksi jonka vuoksi sitä ei voitu yksikkötestata. Tämän vuoksi siihen on voinut jäädä järjestelmätestauksessa havaitsemattomia ongelmia.

