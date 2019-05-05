# Arkkitehtuurikuvaus

## Rakenne

Ohjelma on toteutettu pelien luomiseen tarkoitetulla libGDX-ohjelmistokehyksellä, joka vaikuttaa myös ohjelman pakkausrakenteseen. Pakkausrakenne on seuraava:

<img src="https://raw.githubusercontent.com/PPeltola/ot_harjoitustyo/master/dokumentaatio/kuvat/pakkausarkkitehtuuri.jpg">

_game.launcher_ luo libGDX:n Game-olion sekä konfiguroi sille mm. ruudun koon. _game.gui_ sisältää pelin (vielä tällä hetkellä ainoan) näkymän eli libGDX:n Screen-rajapinnan toteuttavan luokan. libGDX:n suoritusprosessi toimii niin, että peliprosessin aktiivisen ruudun _render(float delta)_-metodia kutsutaan jatkuvalla syötöllä, parametrina viime kutsusta kulunut aika sekunteina. _game.gui_:n näkymäluokka siis vastaa kyseisen näkymän ylläpidosta ja piirtämisestä. 

Piirtämisen ja pelitilanteen päivittämisen suoraviivaistamiseksi libGDX sisältää mahdollisuuden luoda Stage-olioita, joille lisätään ruudulle piirrettäviä Actor-yksiköitä. Actor-yksiköt vastaavat itse siitä, kuinka mm. Actorin piirto tapahtuu, ja Stage vain kutsuu jokaisen Actorinsa piirtometodia aina kun sen omaa piirtometodia kutsutaan (oletettavasti joka suoritussyklissä). _game.gui.actors_ vastaakin siis pelin ruudulle piirettävistä Actoreista, ja siitä kuinka ne piirtyvät. _game.domain_ taas sisältää pelin keskenään vuorovaikuttavat oliot kuten viholliset ja tornit sekä niiden perustoiminnallisuuden. Esim. siis vaikkapa tornin tapauksessa _game.domain_ sisältää tornin määrittelevän luokan ja _game.gui.actors_ sen piirtämisestä vastaavan, torniolion omaavan luokan. 

_game.logic_ sisältää pelin ylläpitoon tarvittavan logiikan jota gui-luokkaan ei olisi mielekästä toteuttaa. Se mm. vastaa pelin hahmojen kollisioiden havaitsemisesta sekä hallinnoi kartan lataamista sekä pitää kirjaa pelaajan rahamäärästä. _game.map_ pitää kirjaa pelin kartasta eli maastosta sekä kartan tukikohdasta, sekä hoitaa kartan lukemisen ja lataamisen tiedostosta. 

_game.logic.ai_ sisältää eri pelin toimijoiden yksinkertaisia toimintamalleja, esim. vihollisten yksinkertaisimman, pelkästään tukikohtaa kohti juoksevan mallin. Nämä "teköälyt" oli mielekästä eriyttää niitä käyttävistä yksiköistä, sillä moni eri yksikkö voi tällöin helposti toimia samaan tapaan, samalla toimintamallilla.

_game.utils_ on yleisiä pieniä apumetodeja varten luotu pakkaus, joka tällä hetkellä sisältää enää pelkästään jonon kloonaavan apumetodin.

## Käyttöliittymä

Käyttöliittymässä on (toistaiseksi) vain yksi näkymä, joka on pelinaikainen missionäkymä. Käyttöliittymä on pyritty eriyttämään varsinaisesta sovelluslogiikasta luomalla logiikan päivittämisestä ja hallinnoimisesta vastava _game.logic_-pakkaus, sekä pitämällä pelin yksiköiden logiikka _game.domain_:issa erillään niiden piirtämisestä vastaavassa _game.gui.actors_:ista. Käyttöliitymän ja samalla koko pelin varsinainen jatkuvalla syötöllä kutsuttava päivitysmetodi on _MissionScreen.java_:n _render_, josta kutsutaan edelleen logiikan omaa päivitys metodia sekä Stagen päivitysmetodia, joka taas kutsuu kaikkien Actorien päivitysmetodeja.

Käyttöliittymän ainoa suoraan interaktiivinen osa on nappi, jota painamalla pelaaja voi rakentaa kartalle uuden tornin. Nappia painettuaan (olettaen että pelaajalla on varaa) pelaaja pääsee rakentamaan tornia, jolloin hiiren kursorin kohdalla näkyy hiiren kursorin kohdalle rakennettavan mahdollisen tornin koko sekä sen laillisuus vihreänä tai punaisena sekä sen kantama. Uudestaan klikkaamalla torni rakentuu jos sen sijainti on laillinen sekä torninrakennusmoodi menee pois päältä. Jos pelaaja torninrakennusmoodissa klikkaa uudestaan rakentamisnappia, menee moodi pois päältä ilman tornin rakentumista.

Käyttöliittymäluokassa on kaksi luokkaa jotka eivät ole suoranaisesti osa domainia taikka Actoreita: HealthBar ja BulletTrail. Nämä luokat ovat nimiensä mukaisten puhtaasti graafisten elementtien piirron toteuttamisen avuksi luotuja luokkia, jotka saatettaisiin jatkokehittäessä sijoittaa erikoistuneempiin pakkauksiin, esim. HealthBar pelinsisäistä käyttöliittymää kuten troninrakennusnappeja sisältävään ui-pakkaukseen.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat _game.domain_-pakkauksen luokat Obstacle, Path, TestBase, TestEnemy ja TestTower sekä _game.map_ luokan Map, _game.logic_ luokan MissionLogic, _game.gui_ luokan BulletTrail sekä _game.logic.ai_-luokan RunnerAi ja TurretAi.

<img src="https://raw.githubusercontent.com/PPeltola/ot_harjoitustyo/master/dokumentaatio/kuvat/sovelluslogiikka.jpg">

Logiikan päivittämisestä vastaa MissionLogic:in update-metodi, mutta pienemmistä muutoksista vastaavat yleensä yksiköt itse. Esim. update-metodissa kutsutaan kaikkien olemassa olevien tornien checkTarget-metodia joka taas kutsuu tornin tekoälyn checkTarget-metodia joka hoitaa kohteen tarkastamisen määretellyllä tavalla.

Kaiken sovelluslogiikan läpikäyminen ei olisi mielekästä mutta pelin etenemisessä tapahtuu suurin piirtein seuraavat asiat:
  - MissionLogic tarkastaa onko peli hävitty ja sulkee pelin jos on.
  - MissionLogic luo uuden vihollisen jonkun polun alkuun jos tarpeeksi aikaa on kulunut.
  - MissionLogic pyytää kaikkia torneja tarkastamaan kohtensa ja jos sitä ei ole, niin tarkastamaan tornin kantaman uuden kohteen varalta.
  - MissionLogic tarkastaa ovatko pelin yksiköt törmänneet keskenään ja kutsuu niiden collide-metodeja jos näin on. Tällä hetkellä ainut merkittävä törmäys on vihollisen pääseminen tukikohdan sisään asti, jolloin vihollinen tekee tukikohtaan vahinkoa ja tuohoutuu samalla itse.
  - MissionLogic poistaa kuolleet viholliset ja antaa pelaajalle rahaa ennalta määrätyn summan per kuollut vihollinen.
  - Kaikkien Actorien act-metodeita kutsutaan ja ne päivittävät tilaansa esim. viholliset liikkumalla ja tornit ampumalla jos tarpeeksi aikaa on kulunut. Yleensä ne siis vielä kutsuvat oman AI:nsa act-metodeja, jossa määritetään, miten ne toimivat.

## Tietojen luku- ja tallennus

Tällä hetkellä sovellus ei tallenna tietoa mihinkään, mutta se lukee kartat erillisistä karttatiedostoista. Karttatiedostot ovat melko alkeellisia: joka rivi yksinkertaisesti ensin määrittelee onko kyseessä tukikohta, maastopolygoni vai polku vihollisille ja sen jälkeen parsii rivin koordinaateista halutun olion. Karttojen rivit ovat muodossa:

<pre>
Base;TestBase;256;576
Obstacle;64;0;768;0;512;96;480;128
Path;RunnerPath;864;800;864;768;896;672;896;640
</pre>

Ensimmäinen kenttä kertoo millaisesta rivistä on kyse ja tukikohdille ja poluille seuraava kenttä kertoo alatyypin, vaikka alatyyppejä ei vielä sovelluksessa hyödynnetäkään. Numerot ovat aina pareittain: tukikohdalle numerot ovat sijainti, maastopolygoneille kulmat ja poluille polun pisteet. Kentät erotellaan toisistaan puolipisteillä.

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä
Käyttöliittymän ison heikkous on pelin oman UI:n, eli esim. 'rakenna torni' -napin ja rahamäärän ruudulle piirtävän metodin toteuttaminen pelkästään osana MissionScreen:ia. Aikanaan kun uusia karttoja ja tornityyppejä lisättäisiin, kannattaisi pelin UI ja todennäköisesti ainakin ostonapit eriyttää omiin luokkiinsa, joiden olioita MissonScreen sitten käyttäisi.

### Domain
Jotkin domain-luokat vaikuttavat hieman toisteisilta, sillä esim, niin tukikohdalla, vihollisella kuin tornillakin on oma sen varaaman alueen kertova ympyrä ja sijainti. Olisi mahdollista toteuttaa jonkinlainen yläluokka kaikille pelissä oleville toimijoille, mutta se luultavasti vain suppenisi ajan kuluessa kun tornien, vihollisten ja tukikohtien luokat alkaisivat toteuttamaan enemmän vain niille ominaisia toiminnallisuuksia. Tällä hetkellä ne muistuttavat enemmän toisiaan, sillä erilaisia yksikitä ei olla vielä ehditty juuri kehittämään.
