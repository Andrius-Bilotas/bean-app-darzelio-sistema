import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import { HorizontalBar } from 'react-chartjs-2';
import 'chartjs-plugin-zoom';
import axios from 'axios';
import Loading from '../Loading/Loading';
import '../../Style/style.css';

export default class HorizontalChart extends Component {
  constructor() {
    super();
    this.state = {
      chartData: {},

      registrationsInFirstAgeGroup: '',
      registrationsInSecondAgeGroup: '',
      spotsInFirstAgeGroups: '',
      spotsInSecondAgeGroups: '',
      active: '',
      locked: '',
      waitingInFirstAgeGroup: '',
      waitingInSecondAgeGroup: '',
    };
  }
  componentDidMount() {
    this.getChartData();
    axios
      .get(`${API}/api/kindergartens/admission/status`)
      .then((res) => {
        this.setState({
          registrationsInFirstAgeGroup: res.data.registrationsInFirstAgeGroup,
          registrationsInSecondAgeGroup: res.data.registrationsInSecondAgeGroup,
          spotsInFirstAgeGroups: res.data.spotsInFirstAgeGroups,
          spotsInSecondAgeGroups: res.data.spotsInSecondAgeGroups,
          active: res.data.active,
          locked: res.data.locked,
          waitingInFirstAgeGroup:
            res.data.registrationsInFirstAgeGroup -
            res.data.spotsInFirstAgeGroups,
          waitingInSecondAgeGroup:
            res.data.registrationsInSecondAgeGroup -
            res.data.spotsInSecondAgeGroups,
        });
      })
      // .catch((err) => console.log(err));
      .catch((err) => {});
  }
  getChartData() {
    axios.get(`${API}/api/users/statistics`).then((res) => {
      const kindergarten = res.data;
      let sortedKindergarten = [...kindergarten];
      let sortedLabels = [];
      let sortedData = [];

      const sorted = sortedKindergarten.sort((a, b) => {
        if (a.firstPriorityRegistrations > b.firstPriorityRegistrations) {
          return -1;
        }
        if (a.firstPriorityRegistrations < b.firstPriorityRegistrations) {
          return 1;
        }
        return 0;
      });
      sorted.map(({ kindergartenName, firstPriorityRegistrations }) => {
        sortedLabels.push(kindergartenName);
        sortedData.push(firstPriorityRegistrations);
      });

      this.setState({
        chartData: {
          labels: sortedLabels,

          datasets: [
            {
              label: 'Darželių populiarumas pirmu prioritetu',
              data: sortedData,
              backgroundColor: 'rgba(75, 192, 192, 0.6)',
              borderColor: 'rgba(15,15,15)',
              hoverBorderColor: true,
              hoverBackgroundColor: true,
              // barThickness: 12,
              // maxBarThickness: 24,
            },
          ],
          options: {
            responsive: true,
            maintainAspectRatio: false,
            scaleShowValues: true,
            scales: {
              yAxes: [
                {
                  ticks: {
                    display: true,
                    autoSkip: false,
                    autoSkipPadding: 1,
                    source: 'data',
                  },
                },
              ],
            },
          },
        },
      });
    });
  }
  render() {
    return (
      <div className="container mt-5">
        <div className="row">
          <div className="card col">
            <div className="card-body">
              {this.state.registrationsInFirstAgeGroup > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.registrationsInFirstAgeGroup}
                </h5>
              ) : (
                <p className="card-text">Duomenys atnaujinami </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                2-3m. amžiaus vaikų grupėse
              </h6>
              <p>Viso registracijų į darželius</p>
            </div>
          </div>
          <div className="card col">
            <div className="card-body">
              {this.state.registrationsInSecondAgeGroup > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.registrationsInSecondAgeGroup}
                </h5>
              ) : (
                <p className="card-text">Duomenys atnaujinami </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                3-6m. amžiaus vaikų grupėse
              </h6>
              <p>Viso registracijų į darželius</p>
            </div>
          </div>
          <div className="card col">
            <div className="card-body">
              {this.state.spotsInFirstAgeGroups > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.spotsInFirstAgeGroups}
                </h5>
              ) : (
                <p className="card-text">Duomenys atnaujinami </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                2-3m. amžiaus vaikų grupėse
              </h6>
              <p>Viso vietų darželiuose</p>
            </div>
          </div>
          <div className="card col">
            <div className="card-body">
              {this.state.spotsInSecondAgeGroups > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.spotsInSecondAgeGroups}
                </h5>
              ) : (
                <p className="card-text">Duomenys atnaujinami </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                3-6m. amžiaus vaikų grupėse
              </h6>
              <p>Viso vietų darželiuose</p>
            </div>
          </div>

          <div className="card col">
            <div className="card-body">
              {this.state.waitingInFirstAgeGroup > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.waitingInFirstAgeGroup}
                </h5>
              ) : (
                <p className="card-text">Nėra vaikų laukiančiųjų eilėje </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                Laukiantieji vaikai eilėje į darželį 2-3m. amžiaus vaikų grupėse
              </h6>
              <br />
            </div>
          </div>

          <div className="card col">
            <div className="card-body">
              {this.state.waitingInSecondAgeGroup > 0 ? (
                <h5 className="card-title pb-3">
                  {this.state.waitingInSecondAgeGroup}
                </h5>
              ) : (
                <p className="card-text">Nėra vaikų laukiančiųjų eilėje </p>
              )}
              <h6 className="card-subtitle mb-2 text-muted">
                Laukiantieji vaikai eilėje į darželį 3-6m. amžiaus vaikų grupėse
              </h6>

              <br />
            </div>
          </div>
        </div>
        <div>
          <div className="chart">
            {Object.keys(this.state.chartData).length ? (
              <HorizontalBar
                data={this.state.chartData}
                width={1900}
                height={1800}
                options={{
                  maintainAspectRatio: false,
                  responsive: true,
                  scaleShowValues: true,
                  yAxes: [
                    {
                      ticks: {
                        display: true,
                        autoSkip: false,
                        autoSkipPadding: 1,
                        source: 'data',
                      },
                    },
                  ],
                  plugins: {
                    zoom: {
                      pan: {
                        enabled: true,
                        mode: 'xy',
                      },

                      // zoom: {
                      //   enabled: true,
                      //   mode: 'xy',
                      // },
                    },
                  },
                }}
              />
            ) : (
              <Loading />
            )}
          </div>
        </div>
      </div>
    );
  }
}
