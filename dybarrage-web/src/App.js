import React from 'react';
import './App.css';
import {Result, Input} from 'antd';
import Header from './components/Header';
import MainArea from './components/MainArea';
import {getRoomIdFromUrl} from './features/util';
import {crawlHeartbeat} from './features/network';
import {CRAWL_HEARTBEAT_INTERVAL} from './features/constants';


export default class App extends React.Component {
  constructor() {
    super();
    this.roomId = getRoomIdFromUrl();
  }

  crawlHeartbeat() {
    crawlHeartbeat(this.roomId);
    setInterval(() => {
      crawlHeartbeat(this.roomId);
    }, CRAWL_HEARTBEAT_INTERVAL);
  }

  componentDidMount() {
    if(this.roomId !== null) {
      this.crawlHeartbeat();
    }
  }

  render() {
    if(this.roomId === null) {
      return (
        <Result
          title="未指定房间！"
          subTitle="在下方输入房间号回车，开始抓取指定房间弹幕"
          extra={
            <Input.Search
              placeholder="房间号"
              enterButton
              style={{ width: '300px' }}
              onSearch={value => window.location.href = `http://localhost:3000?roomid=${value}`}
            />
          }
        />
      );
    }
    
    return (
      <div className="App">
        <Header />

        <MainArea />
      </div>
    );
  }
}
