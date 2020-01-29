import React from 'react';
import RealtimeBarrageVelocityLineChart from './RealtimeBarrageVelocityLineChart';
import SenderLevelBarChart from './SenderLevelBarChart';
import BarrageShowList from './BarrageShowList';
import {Row, Col} from 'antd';

import {getChartData} from '../features/network';
import {getRoomIdFromUrl} from '../features/util';
import {SYS_DATA_UPDATE_INTERVAL} from '../features/constants';


export default class MainArea extends React.Component {
  state = {
    barrageVelocity: this.initBarrageVelocity(),
    senderLevel: {
      xData: [],
      yData: []
    },
    severalBarrages: []
  }
  dataUpdateFlag = null;

  initBarrageVelocity() {
    let data = [];
    for(let i = 0; i < 24; i++) data.push(0);
    return data;
  }

  getChartData() {
    getChartData(getRoomIdFromUrl())
    .then(res => {
      const data = res.data;
      const barrageCurSendVelocity = data.barrageSendVelocity;

      const barrageVelocity = this.state.barrageVelocity;
      barrageVelocity.shift();
      barrageVelocity.push(barrageCurSendVelocity);

      this.setState({
        barrageVelocity,
        senderLevel: {
          xData: data.senderLevel.map(item => item.level),
          yData: data.senderLevel.map(item => item.userNum)
        },
        severalBarrages: data.severalBarrages
      });
    })
    .catch(err => {
      console.log(err);
    });
  }

  componentDidMount() {
    this.getChartData();
    this.dataUpdateFlag = setInterval(() => {
      this.getChartData()
    }, SYS_DATA_UPDATE_INTERVAL);
  }

  componentWillUnmount() {
    clearInterval(this.dataUpdateFlag);
  }
  
  render() {
    return (
      <React.Fragment>
        <Row>
          <Col span={16}>
            <RealtimeBarrageVelocityLineChart 
              id="realtime-barrage-velocity-line-chart"
              height={300}
              data={this.state.barrageVelocity}
            />

            <SenderLevelBarChart 
              id="sender-level-bar-chart"
              height={400}
              data={this.state.senderLevel}
            />
          </Col>

          <Col span={8}>
            <BarrageShowList
              data={this.state.severalBarrages}
            />
          </Col>
        </Row>
      </React.Fragment>
    );
  }
}