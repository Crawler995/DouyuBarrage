import React from 'react';
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/line';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

export default class RealtimeBarrageVelocityLineChart extends React.Component {
  chart = null;

  getXAxis() {
    let res = [];
    for(let i = 120; i > 0; i -= 5) {
      res.push(i);
    }
    return res;
  }

  componentDidMount() {
    this.chart = echarts.init(document.getElementById(this.props.id));
    this.chart.setOption({
      title: {
        text: '弹幕实时发送速度',
        x: 'center'
      },
      tooltip : {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#6a7985'
          }
        }
      },
      xAxis: {
        name: '前x秒',
        type: 'category',
        data: this.getXAxis(),
        boundaryGap: true,
        axisTick: {
          alignWithLabel: true,
          interval: 1
        },
        // axisLabel: {
        //   interval: 5
        // }
      },
      yAxis: {
        name: '弹幕发送速度',
        type: 'value'
      },
      series: [{
        name: '弹幕实时发送速度',
        type: 'line',
        data: this.props.data
      }]
    });
  }

  componentWillReceiveProps(nextProps) {
    this.chart.setOption({
      series: [{
        data: nextProps.data
      }]
    })
  }

  render() {
    return (
      <div
        id={this.props.id}
        style={{
          width: '100%',
          height: this.props.height + 'px'
        }}
      ></div>
    );
  }
}