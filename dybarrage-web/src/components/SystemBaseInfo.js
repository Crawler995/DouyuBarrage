import React from 'react';
import { Descriptions } from 'antd';
import {getSystemBaseInfo} from '../features/network';
import {SYS_DATA_UPDATE_INTERVAL} from '../features/constants';
import {getRoomIdFromUrl} from '../features/util';


export default class SystemBaseInfo extends React.Component {
  constructor() {
    super();

    this.state = {
      systemBaseInfo: []
    }

    this.dataUpdateFlag = null;
  }

  getSystemBaseInfo() {
    getSystemBaseInfo(getRoomIdFromUrl())
    .then(res => {
      const data = res.data;

      this.setState({
        systemBaseInfo: data
      });
    })
    .catch(err => {
      console.log(err)
    });
  }

  componentDidMount() {
    this.getSystemBaseInfo();
    this.dataUpdateFlag = setInterval(() => {
      this.getSystemBaseInfo()
    }, SYS_DATA_UPDATE_INTERVAL);
  }

  render() {
    const {systemBaseInfo} = this.state;

    return (
      <Descriptions>
        {
          systemBaseInfo.map((item, index) =>
            <Descriptions.Item key={index} label={item.name}>
              {item.data}
            </Descriptions.Item>
          )
        }
      </Descriptions>
    );
  }
}