# OTM-defence

Ohjelmistotekniikka-kurssille harjoitustyönä tehtävä tower defence -peli. Nimi ei sitten vaihtunutkaan.

## Dokumentaatio

[Käyttöohje](https://github.com/PPeltola/ot_harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/PPeltola/ot_harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/PPeltola/ot_harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

[Testausdokumentti](https://github.com/PPeltola/ot_harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/PPeltola/ot_harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Final](https://github.com/PPeltola/ot_harjoitustyo/releases/tag/Final)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

<pre>
mvn test
</pre>

Testikattavuusraportti luodaan komennolla

<pre>
mvn jacoco:report
</pre>

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

<pre>
mvn package
</pre>

Generoi hakemistoon suoritettavan jar-tiedoston taget-hakemistoon

### JavaDoc

JavaDocit generoidaan komennolla 

<pre>
mvn javadoc:javadoc
</pre>

Ne näkee selaimella tiedostosta _target/site/apidocs/index.html_

### Checkstyle

Checkstylen tarkistukset suoritetaan komennolla

<pre>
 mvn jxr:jxr checkstyle:checkstyle
</pre>

Virheilmoitukset löytyvät tiedostosta _target/site/checkstyle.html_

