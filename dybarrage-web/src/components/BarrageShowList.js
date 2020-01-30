import React from 'react';
import {List, Avatar} from 'antd';

import {getBarrageData} from '../features/network';
import {getRoomIdFromUrl} from '../features/util';
import {SYS_DATA_UPDATE_INTERVAL} from '../features/constants';


export default class BarrageShowList extends React.Component {
  state = {
    barrages: []
  }
  dataUpdateFlag = null;

  getBarrageData() {
    getBarrageData(getRoomIdFromUrl())
    .then(res => {
      const data = res.data;

      this.setState({
        barrages: data
      });
    })
    .catch(err => {
      console.log(err);
    });
  }

  componentDidMount() {
    this.getBarrageData();
    this.dataUpdateFlag = setInterval(() => {
      this.getBarrageData()
    }, SYS_DATA_UPDATE_INTERVAL);
  }
  
  render() {
    return (
      <List
        dataSource={this.state.barrages}
        renderItem={
          item => (
            <List.Item>
              <List.Item.Meta 
                avatar={<Avatar src={`https://apic.douyucdn.cn/upload/${item.avatarUrl}_middle.jpg`} />}
                title={<div>{item.username}</div>}
                description={item.barrage}
              />
            </List.Item>
          )
        }
      />
    );
  }
}