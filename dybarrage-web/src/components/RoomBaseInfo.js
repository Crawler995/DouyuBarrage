import React from 'react';
import { PageHeader, Spin, Tag } from 'antd';

import {getRoomBaseInfo} from '../features/network';
import {ROOM_DATA_UPDATE_INTERVAL} from '../features/constants';
import {getRoomIdFromUrl} from '../features/util';

export default class RoomBaseInfo extends React.Component {
  constructor() {
    super();

    this.state = {
      roomBaseInfo: null
    };

    this.dataUpdateFlag = null;
  }

  getRoomBaseInfo() {
    getRoomBaseInfo(getRoomIdFromUrl())
    .then(res => {
      const data = res.data.data;

      this.setState({
        roomBaseInfo: {
          owner: data.owner_name,
          roomTitle: data.room_name,
          hotNumber: data.hn,
          startTime: data.start_time,
          avatarURL: data.avatar,
          isLive: data.room_status === '1'
        }
      });
    })
    .catch(err => {
      console.log(err)
    });
  }

  componentDidMount() {
    this.getRoomBaseInfo()
    this.dataUpdateFlag = setInterval(() => {
      this.getRoomBaseInfo()
    }, ROOM_DATA_UPDATE_INTERVAL);
  }

  componentWillUnmount() {
    clearInterval(this.dataUpdateFlag);
  }

  getSpinner() {
    return (
      <center>
        <Spin />
      </center>
    );
  }

  getTags() {

  }

  render() {
    const {roomBaseInfo} = this.state;

    return (
      <div className="bg-light py-3 mb-5">
        {
          roomBaseInfo === null ?
          this.getSpinner() :
          <PageHeader
            title={roomBaseInfo.owner}
            subTitle={roomBaseInfo.roomTitle}
            avatar={{src: roomBaseInfo.avatarURL}}
            extra={
              roomBaseInfo.isLive ? 
              [
                <Tag color="red" key={0}>{'热度 ' + roomBaseInfo.hotNumber}</Tag>,
                <Tag color="orange" key={1}>{'开播时间 ' + roomBaseInfo.startTime}</Tag>
              ] :
              [
                <Tag color="red">{'未开播'}</Tag>,
              ]
            }
          >
            {this.props.children}
          </PageHeader>
        }
      </div>
    );
  }
}
