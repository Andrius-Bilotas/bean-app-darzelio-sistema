import React, { Component, lazy } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
import Loading from '../Loading/Loading';

const KindergartenTablePresentation = lazy(() =>
  import('./KindergartenTablePresentation')
);
export default class KindergartenContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      kindergartens: [],
    };
  }

  componentDidMount() {
    axios
      .get(`${API}/api/kindergartens/admission/queues`)

      .then((res) => {
        this.setState({
          kindergartens: res.data,
        });
      })
    // .catch((err) => console.log(err));
    .catch((err) =>  {});
  }

  render() {
    return (
      <div>
        {this.state.kindergartens.length > 0 ? (
          <KindergartenTablePresentation
            kindergartens={this.state.kindergartens}
          />
        ) : (
          <div>
            <Loading />
          </div>
        )}
      </div>
    );
  }
}
