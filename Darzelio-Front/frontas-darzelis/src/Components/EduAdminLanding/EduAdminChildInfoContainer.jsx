import React, { Component } from 'react';
import { API } from '../../Configuration/AppConfig';
import axios from 'axios';
const EduAdminChildInfoPresentation  = React.lazy(() =>
import('./EduAdminChildInfoPresentation'))

export default class EduAdminChildInfoContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      //childInfo and parentInfo
      childId: '',
      mainParentFirstname: '',
      mainParentLastname: '',
      mainParentEmail: '',
      mainParentPhone: '',
      mainParentAddress: '',
      mainParentNumberOfKids: '',
      mainParentStudying: '',
      mainParentStudyingInstitution: '',
      mainParentDisabled: '',
      childFirstname: '',
      childLastname: '',
      childAddress: '',
      childAdopted: '',
      childBirthdate: '',
      childRating: '',
      kindergartenName: '',

      secondParent: '',
      secondParentFirstname: '',
      secondParentLastname: '',
      secondParentNumberofKids: '',
      secondParentStudying: '',
      secondParentStudyingInstitution: '',
      secondParentDisabled: '',

      //application info
      // "childId": 0,
      fifthPriority: '',
      firstPriority: '',
      fourthPriority: '',
      //registracijos id
      id: 0,
      rating: 0,
      secondPriority: '',
      thirdPriority: '',
      accepted_kindergarten: '',
      admission_id: '',
    };
  }

  componentDidMount() {
    axios
      .get(
        `${API}/api/kindergartens/admission/registrations/${this.props.match.params.id}`
      )
      .then((res) => {
        this.setState({
          childId: this.props.match.params.id,
          mainParentFirstname: res.data.mainParentFirstname,
          mainParentLastname: res.data.mainParentLastname,
          mainParentEmail: res.data.mainParentEmail,
          mainParentPhone: res.data.mainParentPhone,
          mainParentAddress: res.data.mainParentAddress,
          mainParentNumberOfKids: res.data.mainParentNumberOfKids,
          mainParentStudying: res.data.mainParentStudying,
          mainParentStudyingInstitution: res.data.mainParentStudyingInstitution,
          mainParentDisabled: res.data.mainParentDisabled,
          childFirstname: res.data.childFirstname,
          childLastname: res.data.childLastname,
          childAddress: res.data.childAddress,
          childAdopted: res.data.childAdopted,
          childBirthdate: res.data.childBirthdate,
          childRating: res.data.childRating,
          kindergartenName: res.data.kindergartenName,
          secondParent: res.data.secondParent,
          secondParentFirstname: res.data.secondParentFirstname,
          secondParentLastname: res.data.secondParentLastname,
          secondParentNumberofKids: res.data.secondParentNumberofKids,
          secondParentStudying: res.data.secondParentStudying,
          secondParentStudyingInstitution:
            res.data.secondParentStudyingInstitution,
          secondParentDisabled: res.data.secondParentDisabled,
        });

        return axios.get(
          `${API}/api/kindergartens/register/${this.state.childId}`
        );
      })
      .then((res) => {
        this.setState({
          id: res.data.id,
          firstPriority: res.data.firstPriority,
          secondPriority: res.data.secondPriority,
          thirdPriority: res.data.thirdPriority,
          fourthPriority: res.data.fourthPriority,
          fifthPriority: res.data.fifthPriority,
          rating: res.data.rating,
        });
      })
      .catch((error) => 
      {})
      // console.log(error));
  }

  render() {
    return (
      <div>
        <EduAdminChildInfoPresentation
          childId={this.state.childId}
          mainParentFirstname={this.state.mainParentFirstname}
          mainParentLastname={this.state.mainParentLastname}
          mainParentEmail={this.state.mainParentEmail}
          mainParentPhone={this.state.mainParentPhone}
          mainParentAddress={this.state.mainParentAddress}
          mainParentNumberOfKids={this.state.mainParentNumberOfKids}
          mainParentStudying={this.state.mainParentStudying}
          mainParentStudyingInstitution={
            this.state.mainParentStudyingInstitution
          }
          mainParentDisabled={this.state.mainParentDisabled}
          childFirstname={this.state.childFirstname}
          childLastname={this.state.childLastname}
          childAddress={this.state.childAddress}
          childAdopted={this.state.childAdopted}
          childBirthdate={this.state.childBirthdate}
          childRating={this.state.childRating}
          kindergartenName={this.state.kindergartenName}
          secondParent={this.state.secondParent}
          secondParentFirstname={this.state.secondParentFirstname}
          secondParentLastname={this.state.secondParentLastname}
          secondParentNumberofKids={this.state.secondParentNumberofKids}
          secondParentStudying={this.state.secondParentStudying}
          secondParentStudyingInstitution={
            this.state.secondParentStudyingInstitution
          }
          secondParentDisabled={this.state.secondParentDisabled}
          fifthPriority={this.state.fifthPriority}
          firstPriority={this.state.firstPriority}
          fourthPriority={this.state.fourthPriority}
          id={this.state.id}
          rating={this.state.rating}
          secondPriority={this.state.secondPriority}
          thirdPriority={this.state.thirdPriority}
        />
      </div>
    );
  }
}
