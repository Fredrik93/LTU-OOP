# Manual
Starta applikationen och använd de olika menyalternativen för att genomföra 
transaktioner eller hämta konton och konton. 
Ett par kunder har lagts för att förenkla testande. 

### Följande val finns att göra
1. Create customer: Skapar en ny kund 
2. Create Account: skapar ett konto för en kund 
3. Close an account: Stänger konto för en kund 
4. Get All Customers: Visar alla kunder som finns på banken 
5. Get Customer: Hämtar information om en kund 
6. Withdraw an amount: Ta ut en summa från en kunds konto 
7. Deposit an amount: Sätt in en summa till en kunds konto
8. Delete a customer: Tar bort en kund 

# Rapport
## Om lösningen av uppgiften
Jag följde genomgångarna i modulerna för att få en initial förståelse hur komponenter och 
lyssnare fungerar. När jag hade skapat en komponent så gjorde jag alla de andra komponenterna 
på samma sätt, och lade till listor och dialogrutor i efterhand. på det sättet så fick jag en
minimum viable product tidigt som jag sen iterativt kunde förbättra. Jag använde mig även av 
versions-kontrollhantering och sparade kontinuerligt fungerande versioner i ett privat projekt. 

## Problem under arbetets gång
Att hitta dokumentation om de olika komponenterna var riktigt klurigt. Jag är bra på att söka upp
information men allt kändes så gammaldags eller så svårtläst. Den här inlämningen var också mycket större än de tidigare, förmodligen på grund av att jag inte använt Swing innan. Det tog kanske fyra gånger så lång tid som de tidigare uppgifterna, och många saker med komponenterna var svåra att förstå.
Ett bra exempel är vad en actionListener är och hur den fungerar. Det är enkelt att använda när man följer en genomgång men det tog ett tag att förstå hur jag skriva dessa komponentera själv. Metoden verkar vara versatil och efter ett tag så blev det enklare, t.ex att refaktorisera ut metoden i en separat klass. 
Dela värden mellan metoden är också en utmaning. Jag har som sagt aldrig använt swing innan, så t.ex JtextField var lite klurigt att förstå, med setText(), och andra metoder. Men det fungerar tillsynes ganska väl. 
Så, överlag så var problemen mångfaldiga men inte kritiska. Många metoder såg snarlika ut när jag skulle implementera gränssnittet för dem. 
Jag blev dock lite överväldigad av svårigheten. Det tog lång tid att hitta dokumentation och hjälp online och ett problem där var att jag förväntade mig mindre arbetsbelastning.
Men inget som jag tycker borde ändras. Jag prioriterade bara min tid fel.  
Tekniska problem var såklart många, på grund av att jag inte tidigare använt swing. Det var t.ex svårt att förstå hur jag lade till information till en panel. Och det var svårt att förstå hur jag skulle kunna vidarebefordra informationen till andra paneler.

## Lösning av problemen 
Att kunna klicka på de olika komponenterna är väldigt hjälpsamt. Jag kan t.ex Ctrl+click på JPanel och direkt få en liten beskrivning om komponenten och jag kan tillochmed klicka på en länk till oracles hemsida för ytterliggare information. 
Lösning av dokumentationsproblemet var i princip bara att fortsätta söka. Det tog ett tag.
Jag felsökte också mycket. Det är enklare att debugga i ren backend, men det här var ganska enkelt. Jag satte en brytpunkt där jag ville undersöka och körde appen, vilket triggade brytpunkten. Med hjälp av det verktyget så kunde jag lösa många logik-relaterade problem t.ex varför en summa inte visades eller var inkorrekt. ofta pågrund 
av att jag skrivit in fel variabel eller använt en metod som jag inte förstod helt. 

## Förändringar
## GUI 
Klassen som skapar upp gränssnittet, vår app. Använder sig av Swing för att rendera de olika komponenterna. 
## Logic
Jag har refaktorerat om all logik in i en egen mapp (logic) med separata Logik-klasser TransactionLogic, 
AccountLogic och CustomerLogic som sedan används i en Controller, BankController.

## Controller 
En BankController används som låter appen anropa en enda punkt. Det skulle eventuellt vara snyggt med flera controllers, en för vardera logic-klass i.e., AccountLogic, CustomerLogic & TransactionLogic.
### Design (flöde)
GUI -> Controller -> Logic -> Model 

