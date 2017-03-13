# Bookie

<p>Az alkalmazás célja, hogy a felhasználó az ISBN kód alapján információkat kaphasson az adott könyvről, 
és ezzel együtt el is menthesse azokat.</p>
<p>Az ISBN kódot beütheti kézzel, vagy telefonja kamerájával beolvashatja a könyvön lévő barkódot.</p>

## Specifikáció
* Userkezelés (Firebase): felhasználók adatainak, elmentett könyveinek, és egyedi beállításainak tárolása adatbázisban (User, User_Setting, Book táblák)
* Autentikáció - Firebase: felhasználó név - jelszó páros igénylése első belépés során, ezek titkosított tárolása adatbázisban
* Barkód olvasása, fordítása: Barcode beolvasása, ISBN kód kinyerése, feldolgozása
* ISBN kód alapján való könyv keresés: könyv megtalálása után a részleteket eltárolnánk, hogy az app-ban meg tudjunk jeleníteni egyéb információt
* API (ha lehet találni magyar forrást): ISBN kód alapján való kereséshez, például a "moly" egy lehetőség lehet erre, CURL: nCore privát funkcióval bővíteni (eBook keresés)
* Kód alapján talált könyv elmentése listába: a listában lehet kedvenceket megjelölni, amiket komolyabb szándékkal mentünk le, lehet rendezni többféleképpen

## Csapat tagjai
* Demján Zsolt
* Gratzer Ildi
* Verbovszky Bence
