# LTU-OOP
# Manual
1) Starta upp appen och i välj "Load bank data" för att ladda in bankens redan existerande kunder. 
2) Använd sedan menyknapparna för att interagera med bankens system.
3) De skapade filerna återfinns i mappen freull-0_files. Mappen skall skapas automatiskt när man sparar banken via knappvalet "Save Bank".
# Utförande
Jag har lagt till dialogrutor och exceptions på de ställen jag anser att de behövs, speciellt vid ladda och spara bank. Jag använder olika metoder för att skriva till textfil samt till .dat-fil.
# Utmaningar 
## Uppdatera kontonummer efter att man laddat in bank 
Det här momentet var väldigt tidskrävande. Det gick rätt snabbt att kunna ladda in kontonummer som objekt. Det tog betydligt längre tid att lyckas uppdatera det sista kontonummret till att vara det sista i listan av de laddade kontona. Jag kanske överkomplicerade uppgiften men det här tog 40% av min tid och var väldigt frustrerande. Laddade jag in banken så kunde jag få ut en lista med alla kontonummer. men när jag skapade ett nytt konto så användes inte det sista kontonummret. Så varje gång banken laddades in så behövde jag sätta det sista kontonummret till det sista i listan av kontonummer. 
Ett problem är att det tar otroligt lång tid att starta upp GUit för att testa varje gång. Man kan komma undan med metod anrop utan guit ett tag, men så småningom så måste man slut-testa i gui:t.
Jag valde att skapa en setter i Account-klasen som tar emot det senast kända kontonummret vid laddning av banken.
## Ladda en bankfil 
Utmaningen här var att ladda in kunddata korrekt. Det tog ett tag men jag lyckades använda mig av en stream för att läsa in objekten i programmet. 
## Spara en bankfil
Även den här delen var svår, jag har aldrig skrivit till filer förut. Det svåraste var att få till filvägen, men efter mängder med testande så förstod jag att filerna inte visas förräns man laddar om mappstrukturen. Rätt irriterade för det fungerade förmodligen länge, utan att jag faktiskt såg filerna. Skapar man gränssnitt med mer moderna verktyg så finns inte detta problemet. 
## Skriva om logik 
Refaktorisering och ändring i klasser som används av GUIt tog lite tid. Jag använder tyvärr två datorer varav en inte har versionshantering så det är svårt att se exakt vilka ändringar som gjordes mellan lab3 och lab4, men en del ändringar har skett i bankcontrollern och Customerlogic. Det känns bra att jag skapade upp bankcontrollern som är gui:t anropspunkt för det blir enkelt att debugga och söka sig fram till fel på det viset. Alternativet hade varit att anropa logik-klasserna utan en controller, och det blir snabbt rörigt då.
## Test [lab4_freull0](lab4_freull0)[epty](epty)
testande över lag har varit komplicerat. Det fungerade bra att skriva testklasser som kör metoderna som anropas i Guit men så fort GUIt behöver testas så blir det mer handpåläggning, vilket som sagt, ofta innebär mycket tid. En lösning var att anropa en metod för att ladda in banken, men efter ett tag så måste jag även börja testa funktionen att just ladda in banken via GUit. 
## Ändringar efter feedback (underkänt)
i saveAllCustomersFile & loadCustomersFile (Customerlogic) så lade jag till en parameter false i anropet new FileOutputStream(filePath, false). Detta säkerställer att man skriver över istället för att lägga till (appendar). 
Jag ser även till att rensa listan med kunder customers.clear(); (CustomerLogic) vilket även det säkerställer den senaste datan ifrån filer och program. 
