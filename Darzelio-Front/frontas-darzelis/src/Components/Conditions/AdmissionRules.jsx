import React, { Component } from 'react';

export default class AdmissionRules extends Component {
  render() {
    return (
      <div className="conditions">
        <h3 className="text-center mt-5 mb-3">
          <b>VAIKŲ PRIĖMIMO TVARKA</b>
        </h3>
        <h5 className="text-center mt-5 mb-3">
          <b>
            1 SKYRIUS
            <br />
            BENDROSIOS NUOSTATOS
          </b>
        </h5>
        <p>
          1. Pagrindinės sąvokos:
          <br />
          <b>1.1. informacinė sistema </b> – centralizuota prašymų pateikimo ir
          gyventojų informavimo informacinė sistema (toliau – IS). IS paskirtis
          – registruoti tėvų (globėjų) prašymus, sudaryti grupes, tvarkyti
          grupių ir jas lankančių bei pageidaujančių lankyti vaikų duomenis,
          pateikti patikimą ir tikslią informaciją;
          <br />
          <b>1.2. informacinės sistemos administratorius </b>– technologijų
          grupės specialistas, atsakingas už saugią informacinės sistemos
          konfigūraciją ir veiksnumo palaikymą (toliau – IS administratorius);
          <br />
          <b>1.3. informacinės sistemos tvarkytojas </b>– švietimo įstaigos
          vadovas ar darbuotojas, atsakingas už ikimokyklinio ugdymo programų
          įgyvendinimą, lankančių vaikų duomenų bazę, informacijos apie švietimo
          įstaigą ir joje teikiamas paslaugas pateikimą, taip pat už
          centralizuoto vaikų priėmimo į švietimo įstaigas duomenų tvarkymą,
          tvarkantis įstaigų, įgyvendinančių ikimokyklinio ugdymo programas,
          vaikų, lankančių ir pageidaujančių lankyti šių įstaigų grupes, duomenų
          bazę (toliau – IS tvarkytojas)
          <br />
          <b>1.4. informacinės sistemos naudotojas </b>– tėvai (globėjai),
          turintys teisę naudotis informacine sistema miesto savivaldybės
          (toliau – Savivaldybė) interneto svetainėje{' '}
          <a href="http://akademijait.vtmc.lt:8181/bean-app/" rel="noopener noreferrer" target="_blank">
            {' '}
            http://akademijait.vtmc.lt:8181/bean-app/
          </a>{' '}
          ;
        </p>
        <h5 className="text-center mt-5 mb-3">
          <b>
            2 SKYRIUS
            <br />
            TĖVŲ (GLOBĖJŲ) PRAŠYMŲ PATEIKIMAS
          </b>
        </h5>
        <p>
          1. Tėvai (globėjai) prašymus pildo elektroniniu būdu IS prisijungę{' '}
          <a href="http://akademijait.vtmc.lt:8181/bean-app/"rel="noopener noreferrer" target="_blank">
            {' '}
            http://akademijait.vtmc.lt:8181/bean-app/
          </a>{' '}
          .
          <br />
          2. Prašyme nurodoma:
          <br />
          2.1. tėvų (globėjų) duomenys (vardas, pavardė, asmens kodas, mobiliojo
          telefono numeris, elektroninio pašto adresas, gyvenamoji vieta);
          <br />
          2.2. vaiko vardas ir pavardė, gimimo metai, asmens kodas, gyvenamoji
          vieta;
          <br />
          2.3. pageidaujama (-os) lankyti įstaiga (-os) (pirmenybės tvarka
          nurodomos ne daugiau kaip penkios alternatyvos).
          <br />
          2.4. duomenys skirti vaiko konkursinio balo skaičiavimui (žr.
           7 punktą);
          <br /> 3. Prašymai priimami ir IS registruojami nuolat.
          <br />
          4. Prašymai galioja, iki bus suteikta paslauga, vaikui pradėjus
          mokytis pagal pradinio ugdymo programą arba iki prašymas bus atšauktas
          IS vartotojo. Negaliojantys prašymai IS panaikinami. Kiekvienas naujas
          prašymas priimti vaiką ar pakeisti įstaigą panaikina ankstesnį.
          <br /> 5. Teikiant prašymą elektroniniu būdu IS:
          <br /> 5.1. užpildoma elektroninė prašymo forma, prisijungus{' '}
          <a href="http://akademijait.vtmc.lt:8181/bean-app/" rel="noopener noreferrer" target="_blank">
            {' '}
            http://akademijait.vtmc.lt:8181/bean-app/
          </a>{' '}
          (būtina užpildyti privalomas skiltis, abiejų tėvų duomenis). Už
          duomenų teisingumą atsako tėvai (globėjai); 6. Tėvai (globėjai) IS{' '}
          <a href="http://akademijait.vtmc.lt:8181/bean-app/" rel="noopener noreferrer" target="_blank">
            {' '}
            http://akademijait.vtmc.lt:8181/bean-app/
          </a>{' '}
          gali pasitikrinti savo vaiko vietą eilėje bei kitą susijusią
          informaciją.
        </p>
        <h5 className="text-center mt-5 mb-3">
          <b>
            3 SKYRIUS
            <br />
            EILIŲ SUDARYMO TVARKA
          </b>
        </h5>
        <p>
          7. Vaikų priėmimo tvarkos prioritetai (eilės sudarymas):
          <br /> 7.1. Vaiko deklaruojama gyvenamoji vieta yra miesto
          savivaldybė;
          <br /> 7.2. Įvaikinti vaikai;
          <br /> 7.3. Vaikams iš šeimų, auginančių (globojančių) tris ir daugiau
          vaikų, kol jie mokosi pagal bendrojo ugdymo programas;
          <br />
          7.4. Vaikams, kurių vienas iš tėvų (globėjų) mokosi bendrojo ugdymo
          mokykloje;
          <br /> 7.5. Vaikams, kurių vienas iš tėvų (globėjų) turi ne daugiau
          kaip 40 procentų darbingumo lygio;
          <br /> 8. Pirmumo teisę suteikiančios aplinkybės, nurodytos nuo 7.1.
          iki 7.5. punkto, yra sumuojamos. 7.1. punktas suteikia 5 pirmumo
          balus, visi kiti punktai po 1 balą. Pirmumo teisę suteikiančios
          priežastys tikrinamos prašymų pateikimo metu ir prieš priskiriant
          vaikus į grupes.
          <br />
          9. Registracijos data ir laikas nėra prioriteto kriterijus
          <br />
          10. Jei prašyme pirmu numeriu nurodytoje įstaigoje nėra laisvų vietų,
          vieta skiriama antru numeriu pažymėtoje įstaigoje, jei joje yra laisvų
          vietų, ir t. t. Jeigu visose prašyme pažymėtose įstaigose nėra laisvų
          vietų, prašymas lieka laukiančiųjų eilėje.
          <br />
          11. Patikrinus IS duomenis apie pirmenybę skirti vaikui vietą
          įstaigoje ir nustačius vienodą pirmenybių skaičių, vieta eilėje
          skiriama vyresnio pagal gimimo datą amžiaus vaikams, o, sutapus vaikų
          gimimo datai, eilė sudaroma abėcėlės tvarka pagal vaiko pavardę.
          <br /> 12. Nesant pirmenybių, vieta eilėje skiriama vyresnio amžiaus
          vaikams.
          <br />
          13. Į laisvas vietas jau sudarytose grupėse po pagrindinės metų
          atrankos priimama nuolat.
        </p>
        <h5 className="text-center mt-5 mb-3">
          <b>
            4 SKYRIUS
            <br />
            GRUPĖS PATVIRTINIMAS
          </b>
        </h5>
        <p>
          14. Tėvams (globėjams) apie skirtą vietą įstaigoje pranešama
          elektroniniu laišku. Registravusiam prašymą asmeniui prisijungus prie
          IS{' '}
          <a href="http://akademijait.vtmc.lt:8181/bean-app/" rel="noopener noreferrer" target="_blank">
            {' '}
            http://akademijait.vtmc.lt:8181/bean-app/
          </a>{' '}
          galima matyti jam suteiktą vietą.
        </p>
        <h5 className="text-center mt-5 mb-3">
          <b>
            5 SKYRIUS
            <br />
            INFORMACINĖS SISTEMOS ADMINISTRATORIŲ, NAUDOTOJŲ IR TVARKYTOJŲ
            TEISĖS, PAREIGOS IR ATSAKOMYBĖ
          </b>
        </h5>
        <p>
          15. IS tvarkytojo pareigos ir teisės:
          <br /> 15.1. teikti tėvams (globėjams) atnaujintą informaciją apie
          laisvas vietas grupėse ir priėmimo į jas sąlygas;
          <br /> 15.2. pranešti IS vartotojams informaciją apie bet kokią
          įtartiną veiklą, kuri neatitinka informacijos apsaugos politikos
          reikalavimų, bei apie įvairius saugumo incidentus;
          <br />
          15.3. atlikti kitus veiksmus, susijusius su įstaigų komplektavimu ir
          duomenų bazės tvarkymu; <br />
          15.4. reikalauti, kad tėvai (globėjai) ir įstaigų vadovai ištaisytų
          klaidas ar papildytų pateiktus duomenis;
          <br />
          16. IS administratoriaus pareigos ir teisės:
          <br />
          16.1. suteikti IS tvarkytojui ir IS naudotojui prisijungimo duomenis į
          sistemą pagal nustatytas funkcijas;
          <br /> 16.2. konsultuoti IS tvarkytojus naudojimosi IS klausimais;
          <br />
          16.3. atsakyti už saugią IS konfigūraciją ir veiksnumo palaikymą.
          <br /> 17. IS naudotojo pareigos ir teisės:
          <br />
          17.1. pateikti teisingus duomenis.
          <br />
          17.2. pateikti prašymus ir dalyvauti atrankoje į darželius pagal
          prioritetus.
          <br /> 17.2. gauti informaciją apie vaiko priėmimą.
        </p>
      </div>
    );
  }
}
