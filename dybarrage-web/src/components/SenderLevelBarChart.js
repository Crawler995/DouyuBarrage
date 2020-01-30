import React from 'react';
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/bar';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

export default class SenderLevelBarChart extends React.Component {
  chart = null;

  componentDidMount() {
    this.chart = echarts.init(document.getElementById(this.props.id));
    this.chart.setOption({
      title: {
        text: '弹幕发送用户等级分布',
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
        name: '等级',
        type: 'category',
        data: this.props.data.xData,
        // boundaryGap: true,
        // axisTick: {
        //   alignWithLabel: true,
        //   interval: 1
        // },
        // axisLabel: {
        //   interval: 5
        // }
      },
      yAxis: {
        name: '发送弹幕数',
        type: 'value'
      },
      series: [{
        name: '弹幕发送用户等级分布',
        type: 'bar',
        data: this.props.data.yData
      }]
    });
  }

  componentWillReceiveProps(nextProps) {
    this.chart.setOption({
      xAxis: {
        data: nextProps.data.xData
      },
      series: [{
        data: nextProps.data.yData
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