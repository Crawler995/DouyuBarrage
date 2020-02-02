import React from 'react';
import { PageHeader, Spin, Tag, Modal, Input } from 'antd';

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
      if(data === undefined) {
        this.setState({
          roomBaseInfo: undefined
        });
        return;
      }

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

  getNoThisRoomTip() {
    return (
      <Modal
        visible={true}
        closable={false}
        footer={null}
        title="房间未找到"
      >
        <p>斗鱼没有此房间！请检查并在下方输入正确的房间号，回车确认。</p>
        <Input.Search
          placeholder="房间号"
          enterButton
          style={{ width: '300px' }}
          onSearch={value => window.location.href = `http://localhost:3000?roomid=${value}`}
        />
      </Modal>
    );
  }

  render() {
    const {roomBaseInfo} = this.state;

    return (
      <div className="bg-light py-3 mb-5">
        {
          roomBaseInfo ?
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
          </PageHeader> :
          (roomBaseInfo === null ? this.getSpinner() : this.getNoThisRoomTip())
        }
      </div>
    );
  }
}
