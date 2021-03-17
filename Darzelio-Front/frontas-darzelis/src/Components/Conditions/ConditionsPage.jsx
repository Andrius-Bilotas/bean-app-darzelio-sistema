import React, { Component } from 'react';
import '../../Style/style.css';

export default class ConditionsPage extends Component {
  render() {
    return (
      <div className="conditions">
        <h2 className="text-center mt-5 mb-3">
          <b>SĄLYGOS</b>
        </h2>
        <h5 className="text-center mt-5 mb-3">
          <b>ASMENS DUOMENŲ RINKIMAS IR NAUDOJIMAS</b>
        </h5>
        <p>
          <b>Asmens duomenų valdytojas:</b> miesto savivaldybės administracija.
          <br />
          <b>Asmens duomenų tvarkymo tikslas:</b> nustatyti Jūsų asmens tapatybę
          ir gauti papildomą informaciją, reikalingą paslaugų teikimui.
          Netvarkant Jūsų duomenų negalėsime gauti reikalingos informacijos,
          todėl neturėsime teisinio pagrindo Jums suteikti prašomų paslaugų.
          Jūsų duomenys Savivaldybės administracijoje bus saugomi miesto
          savivaldybės administracijos nustatyta tvarka ir terminais.
          Kreipiantis raštu turite teisę prašyti, kad duomenų valdytojas leistų
          susipažinti su Jūsų asmens duomenimis ir juos ištaisytų arba ištrintų,
          arba apribotų duomenų tvarkymą, taip pat turite teisę nesutikti, kad
          duomenys būtų tvarkomi, teisę perkelti duomenis, teisę atšaukti duotą
          sutikimą bei teisę pateikti skundą Valstybinei duomenų apsaugos
          inspekcijai. Miesto savivaldybės administracijos Asmens duomenų
          apsaugos pareigūnas, el. p. duomenuapsauga@savivaldybe.lt
        </p>
       
      </div>
    );
  }
}
